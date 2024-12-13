package com.example.myapplication.myApp.app.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.myApp.app.utilities.Constants;
import com.example.myapplication.myApp.app.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class BaseActivity extends AppCompatActivity {

    private DocumentReference documentReference;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager preferenceManager = new PreferenceManager(getApplicationContext());
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        documentReference = database.collection(Constants.KEY_COLLECTION_USERS)
                .document(preferenceManager.getString(Constants.KEY_USER_ID));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preferenceManager == null) {
            preferenceManager = new PreferenceManager(getApplicationContext());
        }
        if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            updateAvailability(1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (preferenceManager != null && preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            updateAvailability(0);
        }
    }

    private void updateAvailability(int availability) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String userId = preferenceManager.getString(Constants.KEY_USER_ID);
        database.collection(Constants.KEY_COLLECTION_USERS)
                .document(userId)
                .update(Constants.KEY_AVAILABILITY, availability);
    }
}