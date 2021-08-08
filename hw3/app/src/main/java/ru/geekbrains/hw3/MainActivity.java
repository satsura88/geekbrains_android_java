package ru.geekbrains.hw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView resultField;
    private TextView operationField;
    private Calculator calculator;
    private final static String keyCalc = "calculator";
    private static final String NameSharedPreference = "CALCULATOR";
    private static final String AppTheme = "APP_THEME";
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 99;

    //для задания со * Intent i = new Intent("runSimpleCalc");
    //startActivity(i);

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putSerializable(keyCalc, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        calculator = (Calculator) instanceState.getSerializable(keyCalc);
        setFields();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyTheme));
        setContentView(R.layout.activity_main);

        resultField = findViewById(R.id.resultField);
        operationField = findViewById(R.id.operationField);
        calculator = new Calculator();

        setButtonNumClickListener();
        setButtonSettingsClickListener();
        setButtonACClickListener();
        setButtonBackspaceClickListener();
        setButtonPlusClickListener();
        setButtonSubtractionClickListener();
        setButtonMultiplicationClickListener();
        setButtonDivisionClickListener();
        setButtonPointClickListener();
        setButtonEqClickListener();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK){
            int retCodeStyle = data.getIntExtra(Constant.SETTINGS_THEME_STYLE,-1);

            if (retCodeStyle == Constant.DarkThemeCodeStyle) {
                setAppTheme(Constant.DarkThemeCodeStyle);
            } else {
                setAppTheme(Constant.LightThemeCodeStyle);
            }
            recreate();
        }
    }

    private void setButtonNumClickListener() {
        HashMap<Integer,String> valuesNumButton = getValuesNumButton();

        View.OnClickListener buttonNumClickListener = v -> {
            calculator.setCurNumber(operationField.getText()+valuesNumButton.get(v.getId()));
            setFields();
        };

        findViewById(R.id.button0).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button1).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button2).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button3).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button4).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button5).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button6).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button7).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button8).setOnClickListener(buttonNumClickListener);
        findViewById(R.id.button9).setOnClickListener(buttonNumClickListener);
    }

    private HashMap<Integer, String> getValuesNumButton() {
        HashMap<Integer, String> valuesNumButton = new HashMap<>();

        valuesNumButton.put(R.id.button0,"0");
        valuesNumButton.put(R.id.button1,"1");
        valuesNumButton.put(R.id.button2,"2");
        valuesNumButton.put(R.id.button3,"3");
        valuesNumButton.put(R.id.button4,"4");
        valuesNumButton.put(R.id.button5,"5");
        valuesNumButton.put(R.id.button6,"6");
        valuesNumButton.put(R.id.button7,"7");
        valuesNumButton.put(R.id.button8,"8");
        valuesNumButton.put(R.id.button9,"9");

        return valuesNumButton;
    }

    private void setButtonSettingsClickListener() {
        findViewById(R.id.button_settings).setOnClickListener(v -> {
            Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);

            runSettings.putExtra(Constant.SETTINGS_THEME_STYLE,getCodeStyle(Constant.LightThemeCodeStyle));
            startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
        });
    }

    private void setButtonACClickListener() {
        findViewById(R.id.button_delete).setOnClickListener(v -> {
            calculator.setCurNumber("0");
            setFields();
        });
    }

    private void setButtonBackspaceClickListener() {
        findViewById(R.id.button_backspace).setOnClickListener(v -> {
            if (operationField.getText().length()==0) return;

            calculator.setCurNumber(operationField.getText().subSequence(0,operationField.getText().length()-1).toString());
            setFields();
        });
    }

    private void setButtonPlusClickListener() {
        findViewById(R.id.button_plus).setOnClickListener(v -> {
            if (operationField.getText().length()==0) return;

            setInMem(CalcOperation.PLUS);
        });
    }

    private void setButtonSubtractionClickListener() {
        findViewById(R.id.button_subtraction).setOnClickListener(v -> {
            if (operationField.getText().length()==0) return;

            setInMem(CalcOperation.SUBTRACTION);
        });
    }

    private void setButtonMultiplicationClickListener() {
        findViewById(R.id.button_multiplication).setOnClickListener(v -> {
            if (operationField.getText().length()==0) return;

            setInMem(CalcOperation.MULTIPLICATION);
        });
    }

    private void setButtonDivisionClickListener() {
        findViewById(R.id.button_division).setOnClickListener(v -> {
            if (operationField.getText().length()==0) return;

            setInMem(CalcOperation.DIVISION);
        });
    }

    private void setButtonPointClickListener() {
        findViewById(R.id.button_point).setOnClickListener(v -> {
            if (operationField.getText().toString().contains(".")) return;
            if (operationField.getText().length()==0) return;

            calculator.setCurNumber(operationField.getText()+".");
            setFields();
        });
    }

    private void setButtonEqClickListener() {
        findViewById(R.id.button_eq).setOnClickListener(v -> {
            if (operationField.getText().length()==0) return;
            if (calculator.getOperation().equals(CalcOperation.EMPTY)) return;

            calculator.calc(Double.valueOf(operationField.getText().toString()));
            setFields();
        });
    }

    private void setInMem(CalcOperation calcoper){
        calculator.setInMem(operationField.getText().toString(),calcoper);
        setFields();
    }

    private void setFields(){
        resultField.setText(calculator.getResultField());
        operationField.setText(calculator.getOperationField());
    }

    private int getAppTheme(int myTheme) {
        return codeStyleToStyleId(getCodeStyle(R.style.MyTheme));
    }

    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case Constant.DarkThemeCodeStyle:
                return R.style.AppThemeDark;
            default:
                return R.style.MyTheme;
        }
    }

    private int getCodeStyle(int codeStyle){
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPref.getInt(AppTheme, codeStyle);
    }


}