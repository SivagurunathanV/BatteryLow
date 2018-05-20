package com.example.sivagurunathanvelayutham.batterylow;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {

    EmailTextValidator emailTextValidator;

    @Mock
    TextView mTextView;

    @Test
    public void validateEmailWhichReturnsTrue(){
        String email_id = "abc_de@gmail.com";
        emailTextValidator = new EmailTextValidator(mTextView);
        when(mTextView.getText()).thenReturn(email_id);
        assertTrue("Validate Correct Email ID",emailTextValidator.validateAfterEntered());
    }
g
    @Test
    public void validateInvalidEmailWhichReturnsFalse(){
        String emailId = "ab.cd.e@gmail";
        emailTextValidator = new EmailTextValidator(mTextView);
        when(mTextView.getText()).thenReturn(emailId);
        assertFalse("Invalid Email Address", emailTextValidator.validateAfterEntered());
    }
}
