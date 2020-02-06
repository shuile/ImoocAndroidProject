package cn.imooc.ui_button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button01;

    /*
     *为按钮添加监听器：
     * 第一步：XML布局文件中添加<Button/>控件并为其设置id
     * 第二部：在java代码中（需要显示该控件的Activity）
     * 通过setContentView，装载Button所在的XML布局
     * 第三步：通过Button的id在Activity中获取到Button的对象
     * 第四步：为button按钮添加点击事件
     * a：重写onclick方法
     * b：
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第二步
        setContentView(R.layout.activity_linearlayout);
        //第三步
        button01 = (Button) findViewById(R.id.btn01);
        MyButtonListener myButtonListener = new MyButtonListener();
        button01.setOnClickListener(myButtonListener);
    }

    class MyButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //该方法将在绑定的按钮被点击时调用，v对象为绑定的控件
            //吐司使用方法：1、当前界面对象（上下文环境），2，显示内容，3、吐司显示时长短
            Toast.makeText(MainActivity.this, "按钮被点击了！！！", Toast.LENGTH_SHORT)
                    .show();

        }
    }
}
