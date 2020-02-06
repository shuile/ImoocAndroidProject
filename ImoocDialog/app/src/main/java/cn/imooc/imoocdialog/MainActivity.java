package cn.imooc.imoocdialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                showNormalDialog1();
                break;
            case R.id.button2:
                showNormalDialog2();
                break;
            case R.id.button3:
                showListDialog();
                break;
            case R.id.button4:
                showSingleDialog();
                break;
            case R.id.button5:
                showMultiDialog();
                break;
            case R.id.button6:
                showWaitingDialog();
                break;
            case R.id.button7:
                showProgressDialog();
                break;
            case R.id.button8:
                showInputDialog();
                break;
            case R.id.button9:
                //1、自定义一个类，集成于Dialog类,在构造方法中调用setContentView(R.lauoyt.xx)来设定对话框的布局
                //2、设定自定对话框的风格（不显示标题栏 不显示背景）
                //调用含设定对话框风格参数的构造
                //3、对自定义对话框中的某些控件添加点击事件

                //实例化自定义的对话框，显示
                MyDialog dialog = new MyDialog(this);
                dialog.show();
                break;
            case R.id.button10:
                showArrayDialog();
                break;
        }
    }

    private void showArrayDialog() {
        final String[] items = {"Java", "Mysql", "Android", "HTML", "C", "C++", "Python", "Go", "Ruby"};
        //数组适配器
        //参数1：黄环境
        //参数2：布局资源索引，指的是每一项数据所呈现的样式android.R.layout.xxx
        //参数3：数据源
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items);
        //参数3：int textviewId 制定文本需要放在布局中对应id文本控制的位置
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.array_item_layout, R.id.item_tv, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择")
                //参数1：适配器对象（对数据显示样式的规则制定器）
                //参数2：监听器
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    private void showInputDialog() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您输入的是：" + editText.getText().toString().trim(), Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        builder.show();
    }

    private void showProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("下载中");
        dialog.setMessage("请等待");
        //设置进度条进度模糊
        dialog.setIndeterminate(false);
        //设置对话框的样式为水平样式
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
//        dialog.setProgress(40);
        new Thread() {
            @Override
            public void run() {
                super.run();
                //让进度条从1到100的运动
                for (int i = 0; i <= 100; i++) {
                    dialog.setProgress(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        }.start();
    }

    private void showWaitingDialog() {
        //进度对话框，默认样式就是转圈
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("我是一个等待对话框");
        dialog.setMessage("请等待。。。");
        dialog.setCancelable(false);//默认取值true
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();//设置对话框消失
            }
        }, 2000);
    }

    private void showMultiDialog() {
        final String[] sports = {"篮球", "足球", "乒乓球", "网球", "滑板", "游泳"};
        final boolean[] checked = {true, false, true, true, false, false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择你最喜欢的运动")
                //参数1：选项
                //参数2：默认备选项（true：选中，false：未选中）
                //参数3：被点击时会触发的事件
                .setMultiChoiceItems(sports, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //参数1：对话框本身
                        //参数2：按钮的索引
                        //参数3：当标志按钮是否处于被选中（true：选中，false：取消选中）
                        //无论是选中还是取消选中都会触发onclick方法
                        Log.e("log", sports[which]);
//                        checked[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "您的爱好是：";
                        for (int i = 0; i < checked.length; i++) {
                            if (checked[i]) {
                                msg += sports[i] + " ";
                            }
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    private int idx = 0;

    private void showSingleDialog() {
        final String[] stars = {"奥黛丽·赫本", "安妮", "艾玛", "布洛克"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择你喜欢的明星")
                //参数1：选项
                //参数2：默认备选项，传某个选项的索引
                //参数3：被选中时的事件
                .setSingleChoiceItems(stars, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "您最喜欢的明星是：" + stars[which], Toast.LENGTH_SHORT).show();
                        idx = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您最喜欢的明星是：" + stars[idx], Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    private void showListDialog() {
        final String[] items = {"我是1", "我是2", "我是3", "我是4", "我是5"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择")
                //设置列表项
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //参数2：被点击的索引
                        Toast.makeText(MainActivity.this, "您选择了：" + items[which], Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    private void showNormalDialog2() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setMessage("请为本次课堂打分");
        //5 3 1
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "5分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您选择了：5分", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "3分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您选择了：3分", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "您选择了：1分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您选择了：1分", Toast.LENGTH_SHORT).show();
            }
        });
        //一定要调用show方法，否则对话框不展示
        dialog.show();
    }

    private void showNormalDialog1() {
        //AlterDialog
        //AlterDialog的构造方法是被修饰为protected
        //因此包外是无法使用的，所以我们利用构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置对话框的标题
        builder.setTitle("提示");
        //设置内容
        builder.setMessage("您是否退出当前程序");
        //设置按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //退出当前程序
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", null);
//        builder.setNeutralButton()
        //创建对话框
//        builder.create();
        //对话框展示方法
        builder.show();
    }
}
