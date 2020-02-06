package cn.shui.thread1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-vv";

    private Button mBtnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnButton = findViewById(R.id.btn_button);
        mBtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Run 4 thread to work
                SaleTickets thread1 = new SaleTickets(5);
//                SaleTickets thread2 = new SaleTickets(6);
//                SaleTickets thread3 = new SaleTickets(8);
//                SaleTickets thread4 = new SaleTickets(10);
                thread1.start();
//                thread2.start();
//                thread3.start();
//                thread4.start();
                Log.d(TAG, "onClick: wait thread done!");
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onClick: join returned!");
            }
        });
    }

    private class SaleTickets extends Thread {

        private int tickets;

        public SaleTickets(int tickets) {
            this.tickets = tickets;
        }

        @Override
        public void run() {
            super.run();

            // sale tickets
            while (tickets > 0) {
                saleTicket();
            }
            Log.d(TAG, Thread.currentThread() + "run: sale tickets done!");
        }

        private void saleTicket() {
            tickets--;
            if (tickets != 1) {
                Log.d(TAG, Thread.currentThread() + "saleTicket: Saled one ticket, left " + tickets + " tickets!");
            } else {
                Log.d(TAG, Thread.currentThread() + "saleTicket: Saled one ticket, left " + tickets + " ticket!");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
