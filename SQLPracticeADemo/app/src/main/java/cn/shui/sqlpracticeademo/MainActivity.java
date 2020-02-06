package cn.shui.sqlpracticeademo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String URL = "http://www.imooc.com/api/teacher?type=2&page=1";
    private static final String DATA = "data";
    private static final String LEARNER = "learner";
    private static final String NAME = "name";
    private ListView mListView;
    private static MessageDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        getData();
    }

    private void initView() {
        setTitle("书籍列表");
        mListView = findViewById(R.id.data_lv);
    }

    private void getData() {
        GetAsyncTask task = new GetAsyncTask();
        task.execute();
        dao = new MessageDao(this);
    }

    private class GetAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(30 * 1000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setRequestProperty("Accept-Charset", "UTF-8");

                connection.setDoOutput(true);
                connection.setDoInput(true);

                connection.setUseCaches(false);
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    return streamToString(inputStream);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)) {
                List<Message> msgList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray(DATA);
                    for (int index = 0; index < jsonArray.length(); index++) {
                        JSONObject object = (JSONObject) jsonArray.get(index);
                        int learner = object.getInt(LEARNER);
                        String name = object.getString(NAME);
                        Message msg = new Message(learner, name);
                        msgList.add(msg);
                        dao.addMessage(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MessageAdapter msgAdapter = new MessageAdapter(msgList, MainActivity.this);
                mListView.setAdapter(msgAdapter);
            }
        }
    }

    private static String streamToString(InputStream inputStream) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            inputStream.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
