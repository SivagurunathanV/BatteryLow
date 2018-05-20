package com.example.sivagurunathanvelayutham.batterylow;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by sivagurunathanvelayutham on 2/17/18.
 */

public abstract class TextValidator implements TextWatcher {

    public abstract boolean validateAfterEntered();

    @Override
    public void afterTextChanged(Editable s) {
        validateAfterEntered();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
