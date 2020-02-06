package cn.shui.thread2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-vv";

    private Button mBtnButton;

    private SaleTicket mSaleTicjets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnButton = findViewById(R.id.btn_button);
        mBtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(mSaleTicjets, "队伍一").start();
            }
        });

        mSaleTicjets = new SaleTicket(10);
    }

    private class SaleTicket implements Runnable {

        private int tickets = 0;

        public SaleTicket(int tickets) {
            this.tickets = tickets;
        }

        @Override
        public void run() {
            while (tickets > 0) {
                sale();
            }
            Log.d(TAG,  Thread.currentThread().getName() + "run: 票卖完了");

        }

        private void sale() {
            tickets--;
            Log.d(TAG, Thread.currentThread().getName() + "sale: 买了一张票，还剩下" + tickets + "张票");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
