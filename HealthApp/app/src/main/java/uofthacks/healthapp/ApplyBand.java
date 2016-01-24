package uofthacks.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class ApplyBand extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_band);
    }

    public void evaluateSituation(View view) {
        // Take band info and evaluate what to do next
        // Move to CPR
        // Move to HAINES recovery position
        // Conduct secodnary assesment
    }

    /* Used to tests movement between all activities*/
    public void sendToCPR(View view) {
            Intent intent = new Intent(this, PerformCPR.class);
            startActivity(intent);

    }

    public void sendToRecoveryPosition(View view) {
        Intent intent = new Intent(this, RecoveryPosition.class);
        startActivity(intent);
    }

    public void sendToSecondaryAssessment(View view) {
        Intent intent = new Intent(this, SecondaryAssesment.class);
        startActivity(intent);
    }
}
