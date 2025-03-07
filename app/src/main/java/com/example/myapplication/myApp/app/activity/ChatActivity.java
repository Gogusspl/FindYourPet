package com.example.myapplication.myApp.app.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;

import com.example.myapplication.R;

public class ChatActivity extends BaseActivity {

    private boolean isSearchSubmitted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final SearchView searchView = findViewById(R.id.search_view);
        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton chatButton = findViewById(R.id.chat);
        ImageButton shopButton = findViewById(R.id.gotoshop);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                isSearchSubmitted = true;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnClickListener(v -> {
            if (searchView.isIconified()) {
                Intent intent = new Intent(ChatActivity.this, UsersActivity.class);
                startActivity(intent);
            }
        });

        searchView.setOnCloseListener(() -> {
            if (!isSearchSubmitted) {
                reloadActivity();
            }
            isSearchSubmitted = false;
            return true;
        });


        homeButton.setOnClickListener(v -> {
            animateButton(homeButton, true);
            Intent intent = new Intent(ChatActivity.this, MainActivity.class);
            intent.putExtra("selectedTab", "home");
            startActivity(intent);
            finish();
        });

        shopButton.setOnClickListener(v -> {
            animateButton(shopButton, true);
            Intent intent = new Intent(ChatActivity.this, ShopActivity.class);
            intent.putExtra("selectedTab", "home");
            startActivity(intent);
            finish();
        });

        highlightButton(chatButton);
    }


    private void reloadActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void highlightButton(ImageButton button) {
        button.setColorFilter(Color.parseColor("#85614A"));
        button.setTranslationY(-10f);
    }

    private void animateButton(ImageButton button, boolean highlight) {
        float targetY = highlight ? -10f : 0f;
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(button, "translationY", targetY);
        animatorY.setDuration(200);
        animatorY.start();

        if (highlight) {
            button.setColorFilter(Color.parseColor("#85614A"));
        } else {
            button.setColorFilter(null);
        }
    }
}
