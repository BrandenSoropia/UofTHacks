package uofthacks.healthapp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.*;
import android.widget.Toast;
import android.content.Intent;

import java.lang.ref.WeakReference;


//MIT License:
//
//Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
//documentation files (the  "Software"), to deal in the Software without restriction, including without limitation
//the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
//to permit persons to whom the Software is furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all copies or substantial portions of
//the Software.
//
//THE SOFTWARE IS PROVIDED ""AS IS"", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
//TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
//THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
//CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
//IN THE SOFTWARE.

import com.microsoft.band.BandClient;
import com.microsoft.band.BandClientManager;
import com.microsoft.band.BandException;
import com.microsoft.band.BandIOException;
import com.microsoft.band.BandInfo;
import com.microsoft.band.ConnectionState;
import com.microsoft.band.UserConsent;
import com.microsoft.band.sensors.BandDistanceEvent;
import com.microsoft.band.sensors.BandDistanceEventListener;
import com.microsoft.band.sensors.BandHeartRateEvent;
import com.microsoft.band.sensors.BandHeartRateEventListener;
import com.microsoft.band.sensors.BandPedometerEvent;
import com.microsoft.band.sensors.BandPedometerEventListener;
import com.microsoft.band.sensors.BandSkinTemperatureEvent;
import com.microsoft.band.sensors.BandSkinTemperatureEventListener;
import com.microsoft.band.sensors.HeartRateConsentListener;

import android.R.bool;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView txtStatus;
    private TextView heartRateText;
    private TextView temperatureText;
    private Button connectButon;
    private Boolean isConnect = false;
    private enum VitalSigns{HeartRate,SkinTemperature, DeviceStatus};
    private BandClient client = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        heartRateText = (TextView)findViewById(R.id.heartRateTextView);
        temperatureText = (TextView)findViewById(R.id.temperatureTextView);
        connectButon = (Button)findViewById(R.id.btnStart);
        connectButon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(isConnect == false){
                    new ListenerTask().execute();
                    connectButon.setText("Exit");
                } else{
                    finish();
                }
            }
        });
    }

    // Heart rate consent Listener
    private HeartRateConsentListener heartRateConsentListener = new HeartRateConsentListener() {
        @Override
        public void userAccepted(boolean b) {
        }
    };

    private BandHeartRateEventListener heartRateListener = new BandHeartRateEventListener() {
        @Override
        public void onBandHeartRateChanged(final BandHeartRateEvent event) {
            if (event != null) {
                String text = String.format("Heart Rate: %d BPM \nQuality: %s ",
                                                event.getHeartRate(), event.getQuality());

                appendToUI(text , VitalSigns.HeartRate);
            }
        }
    };

    private BandSkinTemperatureEventListener temperatureListener = new BandSkinTemperatureEventListener() {

        @Override
        public void onBandSkinTemperatureChanged(BandSkinTemperatureEvent event) {
            appendToUI(String.valueOf(event.getTemperature()), VitalSigns.SkinTemperature);
        }
    };

<<<<<<< HEAD
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
=======
>>>>>>> master

    private boolean getConnectedBandClient() throws InterruptedException, BandException {
        if (client == null) {
            BandInfo[] devices = BandClientManager.getInstance().getPairedBands();
            if (devices.length == 0) {
                appendToUI("Band isn't paired with your phone.", VitalSigns.DeviceStatus);
                //statusText.setTextColor(Color.parseColor("#d04545"));
                return false;
            }
            client = BandClientManager.getInstance().create(getBaseContext(), devices[0]);
        } else if (ConnectionState.CONNECTED == client.getConnectionState()) {
            return true;
        } else if(ConnectionState.UNBOUND == client.getConnectionState())
            return false;

<<<<<<< HEAD
        btnConsent = (Button) findViewById(R.id.btnConsent);
        btnConsent.setOnClickListener(new OnClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {
                new HeartRateConsentTask().execute(reference);

            }
        }, 50000);
        Intent intent = new Intent(this, CheckConsciousness.class);
        startActivity(intent);
    }

    private void doStuff() {
        Toast.makeText(this, "Delayed Toast!", Toast.LENGTH_SHORT).show();
=======
        appendToUI("Band is connecting...", VitalSigns.DeviceStatus);
        return ConnectionState.CONNECTED == client.connect().await();
>>>>>>> master
    }

    // Unregister listener
    private void unRegisterListeners(){
        try {
            client.getSensorManager().unregisterAllListeners();
        } catch (BandIOException e) {
            appendToUI(e.getMessage(), VitalSigns.DeviceStatus);
        }
    }

    // Display values in the corresponding TextView
    private void appendToUI(final String text, final VitalSigns type) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (type == VitalSigns.DeviceStatus)
                    txtStatus.setText(text);
                else if (type == VitalSigns.HeartRate)
                    heartRateText.setText(text);
                else if (type == VitalSigns.SkinTemperature)
                    temperatureText.setText(String.format("Skin Temperature: %s Celcius", text));
            }
        });
    }

    private void setConsentPermission()
    {
        if(client.getSensorManager().getCurrentHeartRateConsent() !=
                UserConsent.GRANTED) {
            client.getSensorManager().requestHeartRateConsent(MainActivity.this, heartRateConsentListener);
        }
    }

    // execute thread di asynctask
    private class ListenerTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (getConnectedBandClient()) {
                    appendToUI("Band is connected.", VitalSigns.DeviceStatus);
                    isConnect = true;
                    //statusText.setTextColor(Color.parseColor("#3d7336"));

                    setConsentPermission();

                    client.getSensorManager().registerHeartRateEventListener(heartRateListener);
                    client.getSensorManager().registerSkinTemperatureEventListener(temperatureListener);
                } else {
                    appendToUI("Band isn't connected. Please make sure bluetooth is on and the band is in range.", VitalSigns.DeviceStatus);
                    //statusText.setTextColor(Color.parseColor("#d04545"));

                }
            } catch (BandException e) {
                String exceptionMessage="";
                switch (e.getErrorType()) {
                    case UNSUPPORTED_SDK_VERSION_ERROR:
                        exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.";
                        break;
                    case SERVICE_ERROR:
                        exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.";
                        break;
                    default:
                        exceptionMessage = e.getMessage();
                        break;
                }
                appendToUI(e.getMessage() + "\nAccept permision of Microsoft Health Service, then restart counting", VitalSigns.DeviceStatus);
                //  statusText.setTextColor(Color.parseColor("#d04545"));

            } catch (Exception e) {
                appendToUI(e.getMessage(), VitalSigns.DeviceStatus);
                // statusText.setTextColor(Color.parseColor("#d04545"));
            }

            return null;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unRegisterListeners();
    }
}