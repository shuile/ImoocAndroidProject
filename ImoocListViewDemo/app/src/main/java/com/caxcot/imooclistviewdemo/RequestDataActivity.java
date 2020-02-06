package com.caxcot.imooclistviewdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.caxcot.imooclistviewdemo.adapter.RequestDataAdapter;
import com.caxcot.imooclistviewdemo.model.LessonResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestDataActivity extends AppCompatActivity {

    public static final String STATUS = "status";
    public static final String MSG = "msg";
    public static final String DATA = "data";
    public static final String NAME = "name";
    public static final String REQUEST_DATA_URL = "http://www.imooc.com/api/teacher?type=2&page=1";
    public static final String REQUEST_METHOD = "GET";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.app_list_view);

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View foot = layoutInflater.inflate(R.layout.header_list_view, null);
        mListView.addFooterView(foot);

        new RequestDataAsynctask().execute();
    }

    public class RequestDataAsynctask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return request(REQUEST_DATA_URL);
        }

        private String request(String urlStr) {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(30 * 1000);
                connection.setRequestMethod(REQUEST_METHOD);
                connection.connect();

                int responseCode = connection.getResponseCode();
                String reponseMessage = connection.getResponseMessage();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    Log.e("cyt", "request: " + stringBuilder.toString());
                    return stringBuilder.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Loading 消失，数据处理 刷新界面
            LessonResult lessonResult = new LessonResult();
            try {
                JSONObject jsonObject = new JSONObject(result);
                final int status = jsonObject.getInt(STATUS);
                final String msg = jsonObject.getString(MSG);
                lessonResult.setmStatus(status);
                lessonResult.setmMessage(msg);

                List<LessonResult.LessonInfo> lessonInfos = new ArrayList<>();
                JSONArray dataArray = jsonObject.getJSONArray(DATA);

                for (int index = 0; index < dataArray.length(); index++) {
                    LessonResult.LessonInfo lessonInfo = new LessonResult.LessonInfo();
                    JSONObject tempJSONObject = (JSONObject) dataArray.get(index);
                    final String name = tempJSONObject.getString(NAME);
                    lessonInfo.setmName(name);
                    lessonInfos.add(lessonInfo);
                }
                lessonResult.setmLessonInfoList(lessonInfos);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mListView.setAdapter(new RequestDataAdapter(RequestDataActivity.this, lessonResult.getmLessonInfoList()));

        }
    }
}
