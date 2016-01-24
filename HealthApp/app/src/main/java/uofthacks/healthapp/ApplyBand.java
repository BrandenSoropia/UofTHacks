package uofthacks.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
