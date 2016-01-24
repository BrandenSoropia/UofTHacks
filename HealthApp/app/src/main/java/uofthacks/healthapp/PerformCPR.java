package uofthacks.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.view.View;

public class PerformCPR extends AppCompatActivity {

    private ListView lv;
    ArrayList<String> instructions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_cpr);

        lv = (ListView) findViewById(R.id.CPR_Instructions);
        List<String> instructions = new ArrayList<String>();
        // Add all strings here
        int i =0;
        while (i != 4) {
            if (i==0) {
                instructions.add("Landmark at centre of chest (hand under armpit, pull palm to centre of chest)\n");
            }
            if (i==1) {
                instructions.add("Cross hands and lock elbows\n");
            }
            if (i==2) {
                instructions.add("100 compressions/minute \n");
            }
            if (i==3) {
                instructions.add("Push hard and fast (half the depth of victim's chest)\n");
            }
            i++;
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                instructions );

        lv.setAdapter(arrayAdapter);

    }

    public void sendToRecoveryPosition(View view) {
        Intent intent = new Intent(this, RecoveryPosition.class);
        startActivity(intent);
    }
}
