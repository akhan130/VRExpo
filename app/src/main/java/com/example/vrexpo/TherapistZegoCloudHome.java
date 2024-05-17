package com.example.vrexpo;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.vrexpo.treatments.Arachnophobia;
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

public class TherapistZegoCloudHome extends AppCompatActivity {
    EditText userIdEditText;
    Button startBtn, cancelBtn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_home:
                Intent dashIntent = new Intent(TherapistZegoCloudHome.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(TherapistZegoCloudHome.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(TherapistZegoCloudHome.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(TherapistZegoCloudHome.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(TherapistZegoCloudHome.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(TherapistZegoCloudHome.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(TherapistZegoCloudHome.this, TherapistZegoCloudHome.class);
                startActivity(zoom);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(TherapistZegoCloudHome.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
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
        cancelBtn = findViewById(R.id.cancel_btn);

        startBtn.setOnClickListener((v) -> {
            String userID = userIdEditText.getText().toString().trim();
            if (userID.isEmpty()) {
                return;
            }
            // Start the service
            startService(userID);
            Intent intent = new Intent(TherapistZegoCloudHome.this, TherapistCallActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent);
        });

        cancelBtn.setOnClickListener((v) -> {
            Intent cancelIntent = new Intent(TherapistZegoCloudHome.this, TherapistDashboard.class);
            startActivity(cancelIntent);
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
