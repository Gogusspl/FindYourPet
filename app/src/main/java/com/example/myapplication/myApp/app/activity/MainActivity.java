package com.example.myapplication.myApp.app.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class MainActivity extends BaseActivity {

    private ImageButton selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton homeButton = findViewById(R.id.homeButton);
        ImageButton shopButton = findViewById(R.id.gotoshop);
        ImageButton chatButton = findViewById(R.id.chat);

        chatButton.setOnClickListener(v -> {
            handleButtonClick(chatButton);
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
        });

        shopButton.setOnClickListener((v -> {
            handleButtonClick(shopButton);
            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
            startActivity(intent);
        }));

        String selectedTab = getIntent().getStringExtra("selectedTab");
        if (selectedTab != null) {
            switch (selectedTab) {
                case "home":
                    animateAndHighlightButton(homeButton, true);
                    selectedButton = homeButton;
                    break;
                case "shop":
                    animateAndHighlightButton(shopButton, true);
                    selectedButton = shopButton;
                    break;
                case "chat":
                    animateAndHighlightButton(chatButton, true);
                    selectedButton = chatButton;
                    break;
            }
        } else {
            animateAndHighlightButton(homeButton, true);
            selectedButton = homeButton;
        }
    }

    private void handleButtonClick(ImageButton clickedButton) {
        if (selectedButton != null) {
            animateAndHighlightButton(selectedButton, false);
        }
        selectedButton = clickedButton;
        animateAndHighlightButton(clickedButton, true);
    }

    private void animateAndHighlightButton(ImageButton button, boolean highlight) {
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
