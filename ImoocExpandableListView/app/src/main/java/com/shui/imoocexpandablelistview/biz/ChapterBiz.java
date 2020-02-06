package com.shui.imoocexpandablelistview.biz;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shui.imoocexpandablelistview.bean.Chapter;
import com.shui.imoocexpandablelistview.bean.ChapterItem;
import com.shui.imoocexpandablelistview.dao.ChapterDao;
import com.shui.imoocexpandablelistview.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChapterBiz {

    private static final String TAG = "ChapterBiz";

    private ChapterDao mChapterDao = new ChapterDao();

    public void loadDatas(final Context context, final Callback callback, boolean useCache) {
        AsyncTask<Boolean, Void, List<Chapter>> asyncTask = new AsyncTask<Boolean, Void, List<Chapter>>() {

            private Exception ex;

            @Override
            protected List<Chapter> doInBackground(Boolean... booleans) {
                boolean isUseCache = booleans[0];
                List<Chapter> chapterList = new ArrayList<>();
                try {
                    if (isUseCache) {
                        // todo load datas from db
                        List<Chapter> chapterListFromDb = mChapterDao.loadFromDb(context);
                        Log.d(TAG, "doInBackground: chapterListFromDb = " + chapterListFromDb);
                        chapterList.addAll(chapterListFromDb);
                    }
                    Log.d(TAG, "doInBackground: " + chapterList.isEmpty());
                    // load from net
                    if (chapterList.isEmpty()) {
                        // load from net
                        List<Chapter> chapterListListFromNet = loadFromNet(context);
                        // cache to db
                        mChapterDao.insert2Db(context, chapterListListFromNet);
                        chapterList.addAll(chapterListListFromNet);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ex = e;
                }
                return chapterList;
            }

            @Override
            protected void onPostExecute(List<Chapter> chapters) {
                if (ex != null) {
                    callback.onFailed(ex);
                    return;
                } else {
                    callback.onSuccess(chapters);
                }
            }
        };

        asyncTask.execute(useCache);
    }

    private List<Chapter> loadFromNet(Context context) {
        List<Chapter> chapterList = new ArrayList<>();
        String urlPath = "http://www.imooc.com/api/expandablelistview";
        // 1.发请求获取string数据
        String content = HttpUtils.doGet(urlPath);
        Log.d(TAG, "loadFromNet: content=" + content);
        // 2.content -> List<Chapter>
        if (content != null) {
            chapterList = parseContent(content);
            Log.d(TAG, "loadFromNet: parse finish chapterList = " + chapterList);
        }
        return chapterList;
    }

    private List<Chapter> parseContent(String content) {
        List<Chapter> chapterList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(content);
            int errorCode = root.optInt("errorCode");
            if (errorCode == 0) {
                JSONArray dataJsonArray = root.optJSONArray("data");
                for (int i = 0; i < dataJsonArray.length(); i++) {
                    //
                    JSONObject chapterJsonObj = dataJsonArray.getJSONObject(i);
                    int id = chapterJsonObj.optInt("id");
                    String name = chapterJsonObj.optString("name");
                    Chapter chapter = new Chapter(id, name);
                    chapterList.add(chapter);

                    // parse chapter items
                    JSONArray childrenJsonArray = chapterJsonObj.optJSONArray("children");
                    if (childrenJsonArray != null) {
                        for (int j = 0; j < childrenJsonArray.length(); j++) {
                            JSONObject chapterItemJsonObj = childrenJsonArray.getJSONObject(j);
                            int cid = chapterItemJsonObj.optInt("id");
                            String cname = chapterItemJsonObj.optString("name");
                            ChapterItem chapterItem = new ChapterItem(cid, cname);
                            chapter.addChild(chapterItem);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chapterList;
    }

    public static interface Callback {
        void onSuccess(List<Chapter> chapterList)   ;

        void onFailed(Exception e);
    }
}
