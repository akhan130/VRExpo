package com.example.vrexpo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FirebaseUtil {

    public static String currentUserId() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static boolean isLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public static DatabaseReference currentUserDetails() {
        return FirebaseDatabase.getInstance().getReference().child("PatientAccount").child(currentUserId());
    }

    public static DatabaseReference allUserDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference().child("PatientAccount");
    }

    // Modify the methods to work with Realtime Database
    public static DatabaseReference getChatroomReference(String chatroomId) {
        return FirebaseDatabase.getInstance().getReference().child("Chatrooms").child(chatroomId);
    }

    public static DatabaseReference getChatroomMessageReference(String chatroomId) {
        return getChatroomReference(chatroomId).child("Chats");
    }

    public static String getChatroomId(String userId1, String userId2) {
        if (userId1.hashCode() < userId2.hashCode()) {
            return userId1 + "_" + userId2;
        } else {
            return userId2 + "_" + userId1;
        }
    }

    public static DatabaseReference allChatroomDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference().child("Chatrooms");
    }

    public static DatabaseReference getOtherUserFromChatroom(List<String> userIds) {
        if (userIds.get(0).equals(FirebaseUtil.currentUserId())) {
            return allUserDatabaseReference().child(userIds.get(1));
        } else {
            return allUserDatabaseReference().child(userIds.get(0));
        }
    }

    public static void logout() {
        FirebaseAuth.getInstance().signOut();
    }
}
