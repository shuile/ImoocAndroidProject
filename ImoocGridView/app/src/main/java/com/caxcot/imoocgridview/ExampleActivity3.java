package com.caxcot.imoocgridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.caxcot.imoocgridview.adapter.GridAdapter3;
import com.caxcot.imoocgridview.model.ImageInfo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExampleActivity3 extends AppCompatActivity {

    private GridView gridView;
    private List<String> imgList;
    private List<ImageInfo> imageInfoList;
    private GridAdapter3 gridAdapter;
    private ImageLoadTask imageLoadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example3);

        initUI();

        initData();
    }

    private void initData() {
        imgList=new ArrayList<String>();
        imgList.add("http://img5.duitang.com/uploads/item/201406/26/20140626164837_dzKds.jpeg");
        imgList.add("http://img2.imgtn.bdimg.com/it/u=3980629563,3881837630&fm=21&gp=0.jpg");
        imgList.add("http://img5q.duitang.com/uploads/item/201505/08/20150508155052_XJaNW.jpeg");
        imgList.add("http://img4.duitang.com/uploads/item/201407/02/20140702105736_FdN5P.jpeg");
        imgList.add("http://img2.imgtn.bdimg.com/it/u=2866652161,3841912673&fm=21&gp=0.jpg");
        imgList.add("http://img4.imgtn.bdimg.com/it/u=883757693,2063816225&fm=21&gp=0.jpg");
        imgList.add("http://cdn.duitang.com/uploads/item/201309/26/20130926110955_QtUdX.jpeg");
        imgList.add("http://zjimg.5054399.com/allimg/160815/14_160815161625_9.jpg");
        imgList.add("http://i-7.vcimg.com/trim/09ce7067d2467c54cf05bbd271ee3ec8430415/trim.jpg");

        imageInfoList = new ArrayList<ImageInfo>();
        for (int i = 0; i < 9; i++) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setImagePath(imgList.get(i));
            imageInfo.setText("图片" + i);
            imageInfoList.add(imageInfo);
        }
        gridAdapter = new GridAdapter3(this, imageInfoList);
        gridView.setAdapter(gridAdapter);
//        imageLoadTask = new ImageLoadTask(this, gridAdapter);
//        imageLoadTask.execute("");
    }

    private void initUI() {
        gridView = (GridView) findViewById(R.id.gridView);
    }

    public Bitmap getImageFromNetwork(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public class ImageLoadTask extends AsyncTask<String, Void, Void> {

        private Context context;
        private GridAdapter3 gridAdapter;

        public ImageLoadTask(Context context, GridAdapter3 gridAdapter) {
            this.context = context;
            this.gridAdapter = gridAdapter;
        }

        @Override
        protected Void doInBackground(String... strings) {
            for (int i = 0; i < gridAdapter.getCount(); i++) {
                ImageInfo imageInfo = (ImageInfo) gridAdapter.getItem(i);
                String imagePath = imageInfo.getImagePath();
                Bitmap bitmap = getImageFromNetwork(imagePath);
                imageInfo.setBitmap(bitmap);
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            gridAdapter.notifyDataSetChanged();
        }
    }
}
