package com.example.libri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BooksFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_feed);
    }

    /**
     * INFLATE DO MENU
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Ações do Menu
     **/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d("MENUITEM- ", String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case R.id.menuBookRegistration:
                startActivity(new Intent(this, BookRegistrationActivity.class));
                break;

            case R.id.menuBookFeed:
                startActivity(new Intent(this, BooksFeedActivity.class));
                break;

            case R.id.menuSignOut:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}