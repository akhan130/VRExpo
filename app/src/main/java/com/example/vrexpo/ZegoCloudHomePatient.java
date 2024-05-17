package com.example.vrexpo;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zegocloud.uikit.components.audiovideocontainer.ZegoLayout;
import com.zegocloud.uikit.components.audiovideocontainer.ZegoLayoutGalleryConfig;
import com.zegocloud.uikit.components.audiovideocontainer.ZegoLayoutMode;
import com.zegocloud.uikit.components.common.ZegoShowFullscreenModeToggleButtonRules;
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallConfig;
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService;
import com.zegocloud.uikit.prebuilt.call.config.ZegoMenuBarButtonName;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;
import com.zegocloud.uikit.prebuilt.call.invite.internal.ZegoCallInvitationData;
import com.zegocloud.uikit.prebuilt.call.invite.internal.ZegoUIKitPrebuiltCallConfigProvider;

import java.util.Arrays;

public class ZegoCloudHomePatient extends AppCompatActivity {
    EditText userIdEditText;
    Button startBtn;

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
                Intent dashIntent = new Intent(ZegoCloudHomePatient.this, Dashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_accountInfo:
                Intent actInfoIntent = new Intent(ZegoCloudHomePatient.this, AccountInfo.class);
                startActivity(actInfoIntent);
                return true;
            case R.id.action_find_therapist:
                Intent findIntent = new Intent(ZegoCloudHomePatient.this, FindTherapist.class);
                startActivity(findIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(ZegoCloudHomePatient.this, PatientAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_sessionStart:
                Intent sessionStart = new Intent(ZegoCloudHomePatient.this, SessionStart.class);
                startActivity(sessionStart);
                return true;
            case R.id.action_patient_settings:
                Intent settingsIntent = new Intent(ZegoCloudHomePatient.this, PatientSettings.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zegocloud_home);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        userIdEditText = findViewById(R.id.user_id_edit_text);
        startBtn = findViewById(R.id.start_btn);

        startBtn.setOnClickListener((v) -> {
            String userID = userIdEditText.getText().toString().trim();
            if (userID.isEmpty()) {
                return;
            }
            // Start the service
            startService(userID);
            Intent intent = new Intent(ZegoCloudHomePatient.this, CallActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent);
        });
    }

    void startService(String userID) {
        Application application = getApplication();
        long appID = 1118425384;
        String appSign = "107e9a09bc0fba2eb8bfcba62640518897293ed00ed0d6be44063292bbaf550d";
        String userName = userID;

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        callInvitationConfig.provider = new ZegoUIKitPrebuiltCallConfigProvider() {
            @Override
            public ZegoUIKitPrebuiltCallConfig requireConfig(ZegoCallInvitationData invitationData) {
                ZegoUIKitPrebuiltCallConfig config = ZegoUIKitPrebuiltCallConfig.groupVideoCall();
                config.bottomMenuBarConfig.buttons = Arrays.asList(
                        ZegoMenuBarButtonName.TOGGLE_CAMERA_BUTTON,
                        ZegoMenuBarButtonName.TOGGLE_MICROPHONE_BUTTON,
                        ZegoMenuBarButtonName.HANG_UP_BUTTON,
                        ZegoMenuBarButtonName.SCREEN_SHARING_TOGGLE_BUTTON,
                        ZegoMenuBarButtonName.CHAT_BUTTON
                );

                ZegoLayoutGalleryConfig galleryConfig = new ZegoLayoutGalleryConfig();
                galleryConfig.removeViewWhenAudioVideoUnavailable = true;
                galleryConfig.showNewScreenSharingViewInFullscreenMode = true;
                galleryConfig.showScreenSharingFullscreenModeToggleButtonRules = ZegoShowFullscreenModeToggleButtonRules.SHOW_WHEN_SCREEN_PRESSED;
                config.layout = new ZegoLayout(ZegoLayoutMode.GALLERY, galleryConfig);
                return config;
            }
        };

        ZegoUIKitPrebuiltCallService.init(getApplication(), appID, appSign, userID, userName, callInvitationConfig);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}
