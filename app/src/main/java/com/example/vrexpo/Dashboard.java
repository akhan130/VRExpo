package com.example.vrexpo;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class Dashboard extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(Dashboard.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(Dashboard.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(Dashboard.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Dashboard.this, PatientAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_sessionStart:
                Intent sessionStart = new Intent(Dashboard.this, SessionStart.class);
                startActivity(sessionStart);
                return true;
            case R.id.action_messages:
                Intent messages = new Intent(Dashboard.this, PatientMessages.class);
                startActivity(messages);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(Dashboard.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    Button nButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //notification button setup
        nButton = findViewById(R.id.notifButton);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(Dashboard.this, Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Dashboard.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
        nButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeNotification();
            }
        });

        //Setting up the action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
    public void makeNotification(){
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), channelID);
        builder.setSmallIcon(R.drawable.vr_headset)
                .setContentTitle("VRExpo")
                .setContentText("Click the link for VR")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notifIntent = new Intent(getApplicationContext(), Notification.class);
        notifIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //can pass a url with this
        notifIntent.putExtra("data", "https://www.youtube.com/watch?v=vyt20Gg2Ckg&ab_channel=CodesEasy");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notifIntent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "Description", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        notificationManager.notify(0, builder.build());
    }
}