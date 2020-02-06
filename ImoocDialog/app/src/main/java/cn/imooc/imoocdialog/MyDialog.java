package cn.imooc.imoocdialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

public class MyDialog extends Dialog {

    public MyDialog(@NonNull Context context) {
        super(context, R.style.mydialog);
        setContentView(R.layout.layout);

        Button yesBtn = (Button) findViewById(R.id.yes_btn);
        Button noBtn = (Button) findViewById(R.id.no_btn);

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();  //控制对话框消失
            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
