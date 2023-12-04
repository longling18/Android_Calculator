package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class c11 extends AppCompatActivity implements View.OnClickListener {
    TextView result_cal, solution_cal;

    private String input, output, newOutput;
    MaterialButton button_ac,button_del,button_percent,button_point;
    MaterialButton button_divide,button_x,button_plus,button_minus,button_equals;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_cal = findViewById(R.id.result_cal);
        solution_cal = findViewById(R.id.solution_cal);

        assignId(button_del,R.id.button_del);
        assignId(button_divide,R.id.button_divide);
        assignId(button_x,R.id.button_x);
        assignId(button_plus,R.id.button_plus);
        assignId(button_minus,R.id.button_minus);
        assignId(button_equals,R.id.button_equal);
        assignId(button_0,R.id.button_0);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_ac,R.id.button_ac);
        assignId(button_point,R.id.button_point);
        assignId(button_percent,R.id.button_percent);

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "AC":
                input = null;
                output=null;
                newOutput=null;
                result_cal.setText("");
                break;
            case "DEL":
                delete();
                break;
            case "x":
                solve();
                input += "*";
                break;
            case "=":
                solve();
                break;
            case "%":
                input += "%";
                double d = Double.parseDouble(solution_cal.getText().toString()) / 100;
                result_cal.setText(String.valueOf(d));
                break;

            default:
                if (input == null) {
                    input = "";
                }
                if (buttonText.equals("+") || buttonText.equals("/") || buttonText.equals("-")) {
                    solve();
                }
                input += buttonText;
        }
        solution_cal.setText(input);
    }
    private void solve() {
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                result_cal.setText(newOutput);
                input = d +"";
            }catch (Exception e) {
                result_cal.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\*").length == 2) {
            String numbers[] = input.split("\\*");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                result_cal.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                result_cal.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\/").length == 2) {
            String numbers[] = input.split("\\/");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                result_cal.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                result_cal.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\^").length == 2) {
            String numbers[] = input.split("\\^");
            try {
                double d = Math.pow(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                result_cal.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                result_cal.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])){
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    result_cal.setText("-" + newOutput);
                    input = d +"";
                }
                else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    result_cal.setText(newOutput);
                    input = d + "";
                }
            }catch (Exception e){
                result_cal.setText(e.getMessage().toString());
            }
        }
    }
    private void delete() {
        if (input != null && !input.isEmpty()) {
            input = input.substring(0, input.length() - 1);
            solution_cal.setText(input);
        }
    }

    private String cutDecimal(String number){
        String n [] = number.split("\\.");
        if (n.length >1){
            if (n[1].equals("0")){
                number = n[0];
            }
        }
        return number;
    }
}