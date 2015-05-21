package hhowar2.washington.edu.awty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    /*private BroadcastReceiver receiver = new BroadcastReceiver() {

        //Log.i("Application", "created");

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("Application", "received");
            EditText messageField = (EditText) findViewById(R.id.editText);

            //String message = messageField.getText().toString();

            Toast toast = Toast.makeText(context, "Test", Toast.LENGTH_SHORT);
            toast.show();

        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //IntentFilter filter = new IntentFilter("com.tutorialspoint.CUSTOM_INTENT");
        //registerReceiver(receiver, filter);

        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent("Toast Message");
        PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, cal.getTimeInMillis(), 10000, pintent);
        Log.i("Application", "alarm triggered");

        Button button = (Button) findViewById(R.id.button);
        button.getText();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //broadcastIntent();

                EditText messageField = (EditText) findViewById(R.id.editText);
                EditText phoneNumber = (EditText) findViewById(R.id.editText2);
                EditText minutesField = (EditText) findViewById(R.id.editText3);
                int minutes = Integer.parseInt(minutesField.getText().toString());
                Calendar cal = Calendar.getInstance();
                String message = messageField.getText().toString();
                String phone = phoneNumber.getText().toString();

                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent();
                intent.setAction("com.tutorialspoint.CUSTOM_INTENT");

                intent.putExtra("message", message);
                intent.putExtra("phoneNumber", phone);
                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                Button b = (Button)v;
                if(b.getText().toString().equals("Start")) {
                    alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000 * minutes, pintent);
                    b.setText("Stop");
                } else {
                    b.setText("Start");
                    alarm.cancel(pintent);
                }
            }
        });


    }

    public void broadcastIntent()
    {
        EditText messageField = (EditText) findViewById(R.id.editText);
        EditText phoneNumber = (EditText) findViewById(R.id.editText2);
        EditText minutesField = (EditText) findViewById(R.id.editText3);
        int minutes = Integer.parseInt(minutesField.getText().toString());
        Calendar cal = Calendar.getInstance();

        String message = messageField.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("message", message);
        intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
        sendBroadcast(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
