package com.example.myapplication.myApp.app.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;

import com.example.myapplication.R;

public class ShopActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton chatButton = findViewById(R.id.chat);
        ImageButton shopButton = findViewById(R.id.gotoshop);

        homeButton.setOnClickListener(v -> {
            animateButton(homeButton, true);
            Intent intent = new Intent(ShopActivity.this, MainActivity.class);
            intent.putExtra("selectedTab", "home");
            startActivity(intent);
            finish();
        });

        chatButton.setOnClickListener(v -> {
            animateButton(chatButton, true);
            Intent intent = new Intent(ShopActivity.this, ChatActivity.class);
            intent.putExtra("selectedTab", "home");
            startActivity(intent);
            finish();
        });

        highlightButton(shopButton);
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
