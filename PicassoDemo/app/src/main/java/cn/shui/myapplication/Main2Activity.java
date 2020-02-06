package cn.shui.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private static final String PATH_BASE_URI = "http://www.imooc.com/data/picasso/images/";

    private ImageView mImageIv;
    private List<String> mDataUrisList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initData();

        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mDataUrisList = new ArrayList<>();
        mDataUrisList.add(PATH_BASE_URI + "1.jpg");
        mDataUrisList.add(PATH_BASE_URI + "2.jpg");
        mDataUrisList.add(PATH_BASE_URI + "3.jpg");
        mDataUrisList.add(PATH_BASE_URI + "4.jpg");
        mDataUrisList.add(PATH_BASE_URI + "5.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
        mDataUrisList.add(PATH_BASE_URI + "6.jpg");
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mImageIv = findViewById(R.id.img_iv);


        //
        Picasso picasso = Picasso.get();
        RequestCreator request = picasso.load(R.mipmap.ic_launcher);
        // 裁剪
//        request.resize(300, 300);
//        request.centerCrop();
        // 设置占位符图片
//        request.placeholder(R.mipmap.ic_launcher);
        // 旋转
//        request.rotate(90);
        // 网络加载错误显示的图片
//        request.error(R.drawable.ic_launcher_background);
        // 添加自定义图像类型
        request.transform(new DefineTransformation());
        request.into(mImageIv);
    }

    /**
     * 类型转换
     */
    private class DefineTransformation implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size / 2, size / 2);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "icon";
        }
    }
}
