package com.example.sivagurunathanvelayutham.batterylow;

/**
 * Created by sivagurunathanvelayutham on 2/8/18.
 */
public class Constants {
    public static final String CHARGE_MOB_GMAIL = "chargemobnow@gmail.com";
    public static final String CHARGE_MOB_PASSWORD = "MicAma@18";
    public static final String SUBJECT = "PHONE IS GOING TO DIE";

    public static final String getBody(int batteryLevel){
        return "Hi,<br><br>Your Battery is going to die in some time.<br><br><strong>Current Battery is " + batteryLevel + "% :-(</strong><br><br>"
                    +  "<br>Please Plug in the charger before it dies fully.!" +
                "<br><br>This is an automated message, do not reply for this message.";
    }

}
