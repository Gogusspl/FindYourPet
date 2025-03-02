package com.example.myapplication.myApp.app.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
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

        shopButton.setOnClickListener(v -> {
            handleButtonClick(shopButton);
            Intent intent = new Intent(MainActivity.this, ShopActivity.class);
            startActivity(intent);
        });

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

        drawerLayout = findViewById(R.id.main);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Obsługa kliknięcia ikony menu w Toolbar
        toolbar.setNavigationIcon(R.drawable.menu2);
        toolbar.setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                    setEnabled(true);
                }
            }
        });

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}
