package com.example.sivagurunathanvelayutham.batterylow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class BatteryLowActivity extends AppCompatActivity {

    private BroadcastReceiver batteryLowReceiver;
    private EditText emailIdTxt;
    private CheckBox _15percent;
    private CheckBox _10percent;
    private CheckBox _5percent;
    private Button alertButton;
    int batteryLevel;

    public class BatteryLowReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            checkBatteryStatusAndNotify(_15percent.isChecked(),batteryLevel, 15);
            checkBatteryStatusAndNotify(_10percent.isChecked(),batteryLevel, 10);
            checkBatteryStatusAndNotify(_5percent.isChecked(),batteryLevel, 5);
        }

        public void checkBatteryStatusAndNotify(boolean isAsked, int currPercentage, int askedPercentage){
            if(isAsked == true && currPercentage == askedPercentage){
                // send notification
                new BatteryLowActivity.SendMail().execute("");
                System.out.println("Sent");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        batteryLowReceiver = new BatteryLowReceiver();
        emailIdTxt = findViewById(R.id.emailId);
        _15percent = findViewById(R.id._15percent);
        _10percent = findViewById(R.id._10percent);
        _5percent = findViewById(R.id._5percent);
        alertButton = findViewById(R.id.alert_me);
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailTextValidator emailTextValidator = new EmailTextValidator(emailIdTxt);
                if(!emailTextValidator.validateAfterEntered()){
                    showToastMessage("Invalid Email Address");
                    emailIdTxt.setText("");
                }
                else{
//                    resetPage();
                    showToastMessage("Email Will be notified");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        registerReceiver(batteryLowReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(batteryLowReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(batteryLowReceiver);
        super.onDestroy();
    }

    public class SendMail extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                GmailSenderHelper sender = new GmailSenderHelper(Constants.CHARGE_MOB_GMAIL,Constants.CHARGE_MOB_PASSWORD);
                sender.sendMail(Constants.SUBJECT,Constants.getBody(batteryLevel),Constants.CHARGE_MOB_GMAIL,emailIdTxt.getText().toString().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Done";
        }
    }

    public void showToastMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);
    }


    public void resetPage(){
        this.emailIdTxt.setText("");
        this._5percent.setChecked(false);
        this._10percent.setChecked(false);
        this._15percent.setChecked(false);
    }
}
