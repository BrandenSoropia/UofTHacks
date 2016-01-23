package uofthacks.healthapp;

import com.microsoft.band.BandClient;
import com.microsoft.band.BandClientManager;
import com.microsoft.band.BandException;
import com.microsoft.band.BandInfo;
import com.microsoft.band.BandException;
import com.microsoft.band.BandIOException;
import com.microsoft.band.BandPendingResult;
import com.microsoft.band.ConnectionState;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView text;
    private TextView firmaware;
    private BandClient client = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.hellowordTextBox);
        firmaware = (TextView)findViewById(R.id.firmawareTextView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*BandInfo[] pairedBands =
                BandClientManager.getInstance().getPairedBands();

        BandClient bandClient =
                BandClientManager.getInstance().create(this, pairedBands[0]);*/

        // Note: The BandPendingResult.await() method must be called from a
        // background thread. An exception will be thrown if called from the UI
        //thread.
        /*BandPendingResult<ConnectionState> pendingResult =
                bandClient.connect();
        try {
            ConnectionState state = pendingResult.await();
            if(state == ConnectionState.CONNECTED) {
                String fwVersion = null;
                String hwVersion = null;
                try {
                    fwVersion = bandClient.getFirmwareVersion().await();
                    hwVersion = bandClient.getHardwareVersion().await();
                    text.setText(fwVersion);
                } catch (InterruptedException ex) {
                    // handle InterruptedException
                } catch (BandIOException ex) {
                    // handle BandIOException
                } catch (BandException ex) {
                    // handle BandException
                }

            } else {
                // do work on failure
            }
        } catch(InterruptedException ex) {
            // handle InterruptedException
        } catch(BandException ex) {
            // handle BandException
        }*/
    }

    public void refresh(View v)
    {
        BandInfo[] pairedBands =
                BandClientManager.getInstance().getPairedBands();

        BandClient bandClient =
                BandClientManager.getInstance().create(this, pairedBands[0]);
        ///text.setText("working");
        BandPendingResult<ConnectionState> pendingResult =
                bandClient.connect();
        try{
            if (getConnectedBandClient()) {
                text.setText("Band is connected.\n");
                getFirmawareInfo(pairedBands, bandClient, pendingResult);

            } else {
                text.setText("Band isn't connected. Please make sure bluetooth is on and the band is in range.\n");
            }
        }
        catch(Exception e)
        {}

    }

    private void getFirmawareInfo(BandInfo[] pairedBands, BandClient bandClient, BandPendingResult<ConnectionState> pendingResult)
    {
        try {
            firmaware.setText("testing");
            ConnectionState state = pendingResult.await();

            /*if(state == ConnectionState.CONNECTED) {
                String fwVersion = null;
                String hwVersion = null;
                try {
                    fwVersion = bandClient.getFirmwareVersion().await();
                    hwVersion = bandClient.getHardwareVersion().await();
                   // firmaware.setText(fwVersion);
                    Log.d("firmaware",fwVersion + "this is testing");
                   // finish();
                   // startActivity(getIntent());
                } catch (InterruptedException ex) {
                    // handle InterruptedException
                } catch (BandIOException ex) {
                    // handle BandIOException
                } catch (BandException ex) {
                    // handle BandException
                }

            } else {
                // do work on failure
            }*/
        } catch(InterruptedException ex) {
            // handle InterruptedException
        } catch(BandException ex) {
            // handle BandException
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private boolean getConnectedBandClient() throws InterruptedException, BandException {
        if (client == null) {
            BandInfo[] devices = BandClientManager.getInstance().getPairedBands();
            if (devices.length == 0) {
                //appendToUI("Band isn't paired with your phone.\n");
                return false;
            }
            client = BandClientManager.getInstance().create(getBaseContext(), devices[0]);
        } else if (ConnectionState.CONNECTED == client.getConnectionState()) {
            return true;
        }

        //appendToUI("Band is connecting...\n");
        return ConnectionState.CONNECTED == client.connect().await();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
