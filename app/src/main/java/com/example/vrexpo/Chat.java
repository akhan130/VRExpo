package com.example.vrexpo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Date;

public class Chat extends AppCompatActivity {

    PatientModel patient;
    String chatroomId;
    ChatroomModel chatroomModel;
    EditText messageInput;
    ImageButton sendMessageButton;
    TextView messagePatient;
    RecyclerView recyclerView;
    ChatRecyclerAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.therapist_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dashboard:
                Intent dashIntent = new Intent(Chat.this, TherapistDashboard.class);
                startActivity(dashIntent);
                return true;
            case R.id.action_appointments:
                Intent appointmentsIntent = new Intent(Chat.this, TherapistAppointments.class);
                startActivity(appointmentsIntent);
                return true;
            case R.id.action_view_patient:
                Intent patientInfoIntent = new Intent(Chat.this, SearchPatient.class);
                startActivity(patientInfoIntent);
                return true;
            case R.id.action_messages:
                Intent messagesIntent = new Intent(Chat.this, TherapistMessages.class);
                startActivity(messagesIntent);
                return true;
            case R.id.action_write_report:
                Intent reportIntent = new Intent(Chat.this, WriteReport.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_account_settings:
                Intent settingsIntent = new Intent(Chat.this, TherapistAccountSettings.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_treatmentPlans:
                Intent treatmentPlans = new Intent(Chat.this, TreatmentPlans.class);
                startActivity(treatmentPlans);
                return true;
            case R.id.action_zoom:
                Intent zoom = new Intent(Chat.this, Zoom.class);
                startActivity(zoom);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        patient = AndroidUtil.getPatientModelFromIntent(getIntent());
        chatroomId = FirebaseUtil.getChatroomId(FirebaseUtil.currentUserId(), patient.getName());
        messageInput = findViewById(R.id.message_input);
        sendMessageButton = findViewById(R.id.send_message_button);
        messagePatient = findViewById(R.id.patient_title);
        recyclerView = findViewById(R.id.chat_recycler_view);


        messagePatient.setText(patient.getName());

        sendMessageButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessageToPatient(message);
            }
        });

        getOrCreateChatroomModel();
        setupChatRecyclerView();
    }

    void setupChatRecyclerView() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(chatroomId);
        Query query = databaseReference.orderByChild("timestamp");

        FirebaseRecyclerOptions<ChatModel> options = new FirebaseRecyclerOptions.Builder<ChatModel>()
                .setQuery(query, ChatModel.class).build();

        adapter = new ChatRecyclerAdapter(options, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }


    void sendMessageToPatient(String message) {
        chatroomModel.setLastMessageSenderId(FirebaseUtil.currentUserId());
        chatroomModel.setLastMessage(message); // Update last message

        // Set current timestamp as last message timestamp
        chatroomModel.setLastMessageTimestamp(new Date());

        FirebaseUtil.getChatroomReference(chatroomId).setValue(chatroomModel);

        ChatModel chatMessageModel = new ChatModel(message, FirebaseUtil.currentUserId(), System.currentTimeMillis());

        FirebaseUtil.getChatroomMessageReference(chatroomId).push().setValue(chatMessageModel)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        messageInput.setText("");
                    }
                });
    }


    void getOrCreateChatroomModel() {
        FirebaseUtil.getChatroomReference(chatroomId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    chatroomModel = dataSnapshot.getValue(ChatroomModel.class);
                    if (chatroomModel == null) {

                    }
                } else {
                    DatabaseReference chatroomRef = FirebaseUtil.getChatroomReference(chatroomId);
                    chatroomModel = new ChatroomModel(
                            chatroomId,
                            Arrays.asList(FirebaseUtil.currentUserId(), patient.getName()),
                            null,
                            ""
                    );
                    chatroomRef.setValue(chatroomModel);

                    chatroomRef.child("lastMessageTimestamp").setValue(ServerValue.TIMESTAMP);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
}

