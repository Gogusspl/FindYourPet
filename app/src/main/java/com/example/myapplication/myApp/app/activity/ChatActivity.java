package com.example.myapplication.myApp.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class ChatActivity extends AppCompatActivity {

    private boolean isSearchSubmitted = false; // Flaga do kontroli zatwierdzenia wyszukiwania

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final SearchView searchView = findViewById(R.id.search_view);

        // Obsługa wpisywania i zatwierdzania wyszukiwania
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Logika wyszukiwania
                isSearchSubmitted = true; // Ustaw flagę, że wyszukiwanie zostało zatwierdzone
                // Możesz dodać tu wyszukiwanie np. filtrowanie użytkowników
                return false; // Pozwól SearchView zachowywać się domyślnie
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Możesz tutaj dodać logikę filtrowania wyników w czasie rzeczywistym
                return false;
            }
        });

        // Obsługa kliknięcia na SearchView
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchView.isIconified()) {
                    // Jeśli kliknięcie w prostokąt, przekierowanie do UsersActivity
                    Intent intent = new Intent(ChatActivity.this, UsersActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Obsługa zamknięcia SearchView po kliknięciu "X"
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (!isSearchSubmitted) { // Przeładuj aktywność tylko jeśli wyszukiwanie NIE zostało zatwierdzone
                    reloadActivity();
                }
                isSearchSubmitted = false; // Reset flagi po zamknięciu
                return true; // Zatrzymujemy standardowe działanie
            }
        });
    }

    // Funkcja do przeładowania aktywności
    private void reloadActivity() {
        Intent intent = getIntent(); // Pobierz bieżący Intent
        finish(); // Zakończ bieżącą aktywność
        startActivity(intent); // Uruchom ją ponownie
    }
}
