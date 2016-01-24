package uofthacks.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class ReceivedConsent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.received_consent_activity);
    }

    public void sendToApplyBand(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ApplyBand.class);
        startActivity(intent);
    }

    public void sendToSecondCheck(View view) {
        Intent intent = new Intent(this, SecondaryAssesment.class);
        startActivity(intent);
    }
}
