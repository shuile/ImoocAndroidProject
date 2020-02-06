package cn.imooc.view01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //第一步定义TextView类（根据xml布局文件中定义的空间类型）
    private TextView textView;
    //监听器：1、定义Button类
    private Button button;
    private int sum = 0;
    private ButtonListener buttonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过findViewById方法获取控件对象
        //（传入的内容为代表控件的id，需要向下转型成对应空间类型）
        textView = (TextView) findViewById(R.id.textView01);

        System.out.println("**************是否获取成功：" + textView);
        textView.setText("Hello，Imooc！");

        //监听器：2、获取button对象
        button = (Button) findViewById(R.id.button);
        //监听器：5、生成监听器对象
        buttonListener = new ButtonListener();
        //监听器：6、为按钮添加监听器
        button.setOnClickListener(buttonListener);
    }

    //监听器：3、定义内部类并实现OnClickListener接口
    class ButtonListener implements View.OnClickListener {
        @Override
        //监听器：4、重写onClick方法
        public void onClick(View v) {
            //当空间被点击之后调用
            sum++;
            System.out.println("当前点击次数" + sum);
            textView.setText("当前点击次数：" + sum);
        }
    }
}
