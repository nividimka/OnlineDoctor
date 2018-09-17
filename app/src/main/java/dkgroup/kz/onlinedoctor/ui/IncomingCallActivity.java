package dkgroup.kz.onlinedoctor.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import dkgroup.kz.onlinedoctor.Constants;
import dkgroup.kz.onlinedoctor.R;
import dkgroup.kz.onlinedoctor.ui.base.BaseActivity;
import me.kevingleason.pnwebrtc.PnPeerConnectionClient;


public class IncomingCallActivity extends Activity {
    private String callUser;

    private Pubnub mPubNub;
    private TextView mCallerID;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);


        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.containsKey(Constants.CALL_USER)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Need to pass username to IncomingCallActivity in intent extras (Constants.CALL_USER).",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        this.username = extras.getString(Constants.USER_NAME, "");
        this.callUser = extras.getString(Constants.CALL_USER, "");
        this.mCallerID = (TextView) findViewById(R.id.caller_id);
        this.mCallerID.setText(this.callUser);

        this.mPubNub = new Pubnub(Constants.PUB_KEY, Constants.SUB_KEY);
        this.mPubNub.setUUID(this.username);
    }

    public void acceptCall(View view) {
        Intent intent = new Intent(IncomingCallActivity.this, VideoChatActivity.class);
        intent.putExtra(Constants.USER_NAME, this.username);
        intent.putExtra(Constants.CALL_USER, this.callUser);
        intent.putExtra("dialed", false);
        Log.i("satiyem", "iki keno ora ?");
        startActivity(intent);
        finish();
    }

    /**
     * Publish a hangup command if rejecting call.
     *
     * @param view
     */
    public void rejectCall(View view) {
        JSONObject hangupMsg = PnPeerConnectionClient.generateHangupPacket(this.username);
        this.mPubNub.publish(this.callUser, hangupMsg, new Callback() {
            @Override
            public void successCallback(String channel, Object message) {
                Intent intent = new Intent(IncomingCallActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.mPubNub != null) {
            this.mPubNub.unsubscribeAll();
        }
    }

    @Nullable
    public static Intent getStartIntent(@NotNull BaseActivity context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}
