package com.example.sivagurunathanvelayutham.batterylow;

import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sivagurunathanvelayutham on 2/17/18.
 */
public class EmailTextValidator extends TextValidator {

    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private TextView textView;

    public EmailTextValidator(TextView textView){
        super();
        this.textView = textView;
    }

    @Override
    public boolean validateAfterEntered() {
        String email = textView.getText().toString().trim();
        if(!email.isEmpty() && email.matches(emailPattern)){
            return true;
        }
        else{
            return false;
        }
    }
}
