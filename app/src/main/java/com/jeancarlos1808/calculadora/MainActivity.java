package com.jeancarlos1808.calculadora;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        int[] numberButtonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot};
        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(this::onNumberButtonClick);
        }

        int[] operatorButtonIds = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(this::onOperatorButtonClick);
        }

        findViewById(R.id.btnEqual).setOnClickListener(this::onEqualButtonClick);
        findViewById(R.id.btnClear).setOnClickListener(v -> clear());
    }

    private void onNumberButtonClick(View view) {
        Button button = (Button) view;
        String number = button.getText().toString();

        if (isOperatorPressed) {
            currentInput = "";
            isOperatorPressed = false;
        }

        currentInput += number;
        textViewResult.setText(currentInput);
    }

    private void onOperatorButtonClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();

        if (!currentInput.isEmpty()) {
            firstValue = Double.parseDouble(currentInput);
        }

        isOperatorPressed = true;
    }

    private void onEqualButtonClick(View view) {
        double secondValue = currentInput.isEmpty() ? 0 : Double.parseDouble(currentInput);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    result = firstValue / secondValue;
                } else {
                    textViewResult.setText("Error");
                    return;
                }
                break;
        }

        textViewResult.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
        firstValue = result;
        operator = "";
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstValue = 0;
        isOperatorPressed = false;
        textViewResult.setText("0");
    }
}
