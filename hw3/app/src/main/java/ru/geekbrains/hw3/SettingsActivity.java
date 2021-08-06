package ru.geekbrains.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    private RadioButton rbLightTheme;
    private RadioButton rbDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}