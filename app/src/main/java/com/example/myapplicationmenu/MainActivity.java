package com.example.myapplicationmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    //Exit
    public void appExitMenu(MenuItem item) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //View view = findViewById(R.id.menu_option1);

        switch(item.getItemId()) {
            case R.id.menu_option1:
                if(item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this,"Opcja ktoras, chyba ta pierwsza, ale kliknij raz jeszcze" + item.getTitle(), Toast.LENGTH_SHORT).show();
                }
            case R.id.menu_option2:
                if(item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this,"Jeszcze inna opcje wybrales tutaj" + item.getTitle(), Toast.LENGTH_SHORT).show();
                }
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
