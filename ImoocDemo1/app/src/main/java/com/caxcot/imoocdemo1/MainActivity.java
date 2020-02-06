package com.caxcot.imoocdemo1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private TextView mTextView;
    private Button mButton;
    private String mResult;
    private Button mParseDataButton;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        setListeners();
    }

    private void findView() {
        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.getButton);
        mParseDataButton = findViewById(R.id.parseButton);
    }

    private void setListeners() {
        mButton.setOnClickListener(this);
        mParseDataButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getButton:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestDataByGet();

                    }
                }).start();
                break;
            case R.id.parseButton:
                handleJSONData(mResult);
                break;
            default:
                break;
        }
    }

    private void handleJSONData(String mResult) {
        try {
            final LeesonResult leesonResult = new LeesonResult();
            JSONObject jsonObject = new JSONObject(mResult);
            List<LeesonResult.Lesson> lessonList = new ArrayList<>();
            int status = jsonObject.getInt("status");
            JSONArray lessons = jsonObject.getJSONArray("data");
            leesonResult.setmStatus(status);
            if (lessons != null && lessons.length() > 0) {
                for (int index = 0; index < lessons.length(); index++) {
                    JSONObject lesson = (JSONObject) lessons.get(index);
                    int id = lesson.getInt("id");
                    int learner = lesson.getInt("learner");
                    String name = lesson.getString("name");
                    String smallPicture = lesson.getString("picSmall");
                    String bigPicture = lesson.getString("pinBig");
                    String description = lesson.getString("description");
                    LeesonResult.Lesson lessonItem = new LeesonResult.Lesson();
                    lessonItem.setmID(id);
                    lessonItem.setmLearnerNumber(learner);
                    lessonItem.setmName(name);
                    lessonItem.setmSmallPicture(smallPicture);
                    lessonItem.setmBigPicture(bigPicture);
                    lessonItem.setmDescription(description);
                    lessonList.add(lessonItem);
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText(leesonResult.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void requestDataByGet() {
        try {
            String urlStr = "https://www.imooc.com/api/teacher?type=2&page=1";
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30 * 1000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.connect();   //发起连接

            int responseCode = connection.getResponseCode();
            String message = connection.getResponseMessage();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                result = streamToString(inputStream);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResult = decode(result);
                        mTextView.setText(mResult);
                    }
                });
            } else {
                Log.e(TAG, "run: error code:" + responseCode + ", message:" + message);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestDataByPost() {
        try {
            //?type=2&page=1
            String urlStr = "https://www.imooc.com/api/teacher";
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30 * 1000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Accept-Charset", "UTF-8");

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setUseCaches(false);
            connection.connect();   //发起连接

            String data = "username=" + getEncodeValue("imooc") + "&number=" + getEncodeValue("15088886666");

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            String message = connection.getResponseMessage();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                result = streamToString(inputStream);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result = decode(result);
                        mTextView.setText(result);
                    }
                });
            } else {
                Log.e(TAG, "run: error code:" + responseCode + ", message:" + message);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getEncodeValue(String imooc) {
        String encode = null;
        try {
            encode = URLEncoder.encode(imooc, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encode;
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取输入流
     * @return 字符串
     */
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    /**
     * 将Unicode字符转换成UTF-8类型字符串
     */
    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuilder resBuf = new StringBuilder();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U'))) {
                    try {
                        resBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 5)));
                        i += 5;
                    } catch (NumberFormatException e) {
                        resBuf.append(unicodeStr.charAt(i));
                    }
                } else {
                    resBuf.append(unicodeStr.charAt(i));
                }
            } else {
                resBuf.append(unicodeStr.charAt(i));
            }
        }
        return resBuf.toString();
    }
}
