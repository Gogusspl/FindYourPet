package com.example.myapplication.myApp.app.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import com.example.myapplication.R;

public class menu_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_header);

        TextView menuText = findViewById(R.id.menuText);

        String htmlText = "<font color='#FF69B4'>F</font>ind <font color='#FF69B4'>Y</font>our <font color='#FF69B4'>P</font>et";
        menuText.setText(HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY));
    }
}
