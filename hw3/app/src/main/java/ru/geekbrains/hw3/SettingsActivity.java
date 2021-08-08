package ru.geekbrains.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    private RadioButton rbLightTheme;
    private RadioButton rbDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeDark);
        setContentView(R.layout.activity_settings);
        initRBThemes();
    }

    @Override
    public void onBackPressed() {
        Intent intentResult = new Intent();

        if (rbLightTheme.isChecked()) {
            intentResult.putExtra(Constant.SETTINGS_THEME_STYLE, Constant.LightThemeCodeStyle);
        }
        else{
            intentResult.putExtra(Constant.SETTINGS_THEME_STYLE, Constant.DarkThemeCodeStyle);
        }
        setResult(RESULT_OK, intentResult);

        super.onBackPressed();
    }

    private void initRBThemes() {
        rbLightTheme = findViewById(R.id.rb_lightTheme);
        rbDarkTheme = findViewById(R.id.rb_darkTheme);

        int curCodeStyle = getIntent().getExtras().getInt(Constant.SETTINGS_THEME_STYLE);
        if (curCodeStyle == Constant.LightThemeCodeStyle) {
            rbLightTheme.setChecked(true);
        }
        else {
            rbDarkTheme.setChecked(true);
        }
    }
}