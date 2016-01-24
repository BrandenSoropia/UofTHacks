package uofthacks.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class CheckConsciousness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_consciousness_activity);
    }

    public void sendToEmergencyPage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, EmergencyContact.class);
        startActivity(intent);
    }

    public void sendToConsentCheck(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ReceivedConsent.class);
        startActivity(intent);
    }
}
