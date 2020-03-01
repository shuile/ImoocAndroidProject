package cn.shui.bookapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class BookListActivity extends AppCompatActivity {

    private static final String TAG = "BookListActivity";
    private ListView mBookLv;
    private List<BookListResult.DataBean> mBooks;
    private AsyncHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mBookLv = findViewById(R.id.lv_book);
        String url = "http://www.imooc.com/api/teacher?type=10";

        mClient = new AsyncHttpClient();
        mClient.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i(TAG, "onSuccess: " + new String(bytes));
                String result = new String(bytes);
                Gson gson = new Gson();
                BookListResult bookListResult = gson.fromJson(result, BookListResult.class);

                mBooks = bookListResult.getData();

                mBookLv.setAdapter(new BookListAdapter());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, BookListActivity.class);
        context.startActivity(intent);
    }

    private class BookListAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mBooks.size();
        }

        @Override
        public Object getItem(int position) {
            return mBooks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final BookListResult.DataBean book = mBooks.get(position);
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_book_list_view, null);
                viewHolder = new ViewHolder();
                viewHolder.mTvBookName = convertView.findViewById(R.id.tv_book_name);
                viewHolder.mBtnOpen = convertView.findViewById(R.id.btn_book_open);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mTvBookName.setText(book.getBookname());

            final String path = Environment.getExternalStorageDirectory() + "/imooc/" + book.getBookname() + ".txt";
            final File file = new File(path);
            viewHolder.mBtnOpen.setText(file.exists() ? "点击打开" : "点击下载");
            viewHolder.mBtnOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 下载的功能
                    if (file.exists()) {
                        BookActivity.start(BookListActivity.this, path);
                    } else {
                        mClient.addHeader("Accept-Encoding", "identity");
                        mClient.get(book.getBookfile(), new FileAsyncHttpResponseHandler(file) {
                            @Override
                            public void onFailure(int i, Header[] headers, Throwable throwable, File file) {
                                viewHolder.mBtnOpen.setText("下载失败");
                            }

                            @Override
                            public void onSuccess(int i, Header[] headers, File file) {
                                viewHolder.mBtnOpen.setText("点击打开");
                            }

                            @Override
                            public void onProgress(long bytesWritten, long totalSize) {
                                super.onProgress(bytesWritten, totalSize);
                                viewHolder.mBtnOpen.setText(String.valueOf(bytesWritten * 100 / totalSize + "%"));
                            }
                        });
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            public TextView mTvBookName;
            public Button mBtnOpen;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish();
            }
        }
    }
}
