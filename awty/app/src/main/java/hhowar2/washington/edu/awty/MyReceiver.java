package hhowar2.washington.edu.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by iguest on 5/13/15.
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
