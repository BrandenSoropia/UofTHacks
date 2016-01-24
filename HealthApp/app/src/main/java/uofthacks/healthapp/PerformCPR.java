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
        while (i != 5) {
            instructions.add("I'm awesome! x" + i + '\n');
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
