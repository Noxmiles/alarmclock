package laboratory.fra_uas.eu.hefnerklammenzianochs.alarmclockusingthecalendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiverActivity extends BroadcastReceiver /*Activity */{

    private final static String TAG = AlarmReceiverActivity.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() != null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Device has been booted, alarm must be reset
//            Alarm alarms = new Alarms(context);
//            alarms.scheduleNextAlarm(context);
        } else {
            // An alarm is due, turn power on and Radio
            Log.v(TAG, "onReceive() - Alarm up");
//            MainActivity.accquireWakeLock(context);

            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.putExtra(MainActivity.ALARM_UP, true);
            context.startActivity(i);
        }
    }




    /*private MediaPlayer mediaPlayer;
    private PowerManager.WakeLock wl;
    private Button buttonStopAlarm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, TAG);
        wl.acquire();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.alarm_layout);

        buttonStopAlarm = (Button) findViewById(R.id.bStopAlarm);
        buttonStopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                finish();
            }
        });
        playSound(this, getAlarmUri());
    }

    private void playSound(Context context, Uri alert) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(context, alert);
            final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if(audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } else {
                //audioManager.set

            }
        } catch (IOException ioe) {
            Log.e(TAG, "There is something wrong with the filesystem - unable to write / read data!");
        } catch (Exception e) {
            Log.e(TAG, "No audio files are found!");
        }
    }

    private Uri getAlarmUri() {
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if(alert == null) {
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        return alert;
    }

    protected void onStop() {
        super.onStop();
        wl.release();
    }*/
}
