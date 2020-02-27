package com.example.gohasu.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    TextView textView;

    public void setLanguage(String language) {

        sharedPreferences.edit().putString("language", language).apply();


        textView.setText(language);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.english:
                setLanguage(item.getTitle().toString());
                return true;
            case R.id.french:
                setLanguage(item.getTitle().toString());
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.example.gohasu.languagepreferences", Context.MODE_PRIVATE);

        String language = sharedPreferences.getString("language", "Error");

        if (language.equals("Error")) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose your language preference")
                    .setMessage("Which language you want to select")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("French", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setLanguage("French");
                        }
                    })
                    .show();
        } else {

            textView.setText(language);

        }
    }
}
