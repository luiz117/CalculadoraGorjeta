package com.example.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView txtGorjeta, txtTotal, txtPercent ;
    TextInputEditText textInputGorjeta;
    SeekBar seekBarGorjeta;
    private double soma=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtGorjeta = findViewById(R.id.txtGorjeta);
        txtPercent = findViewById(R.id.txtPercent);
        txtTotal = findViewById(R.id.txtTotal);
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);
        textInputGorjeta = findViewById(R.id.textInputGorjeta);
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                double result = calculate(i);
                double total= soma +  result;
                txtGorjeta.setText("R$ " + String.format("%.2f", result));
                txtPercent.setText(i+"%");
                txtTotal.setText("R$ " + String.format("%.2f", (soma+result)));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(!checkNull(textInputGorjeta)){
                    Toast.makeText(
                            getApplicationContext(),
                            "Digite algum valor",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(!checkNull(textInputGorjeta)){
                    Toast.makeText(
                            getApplicationContext(),
                            "Digite algum valor",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }


    public double calculate(int i){
        double finalCalc = 0;
        Double parseTextDouble;
        if(checkNull(textInputGorjeta)&&i>0){
            soma = Double.valueOf(Objects.requireNonNull(textInputGorjeta.getText()).toString());;
            parseTextDouble = Double.valueOf(Objects.requireNonNull(textInputGorjeta.getText()).toString());
            finalCalc = parseTextDouble * i/100;
        }else{
            Toast.makeText(
                    getApplicationContext(),
                    "Digite algum valor",
                    Toast.LENGTH_SHORT
            ).show();
        }
        return finalCalc;
    }

    public boolean checkNull(TextInputEditText txt){
        if(txt.getText().toString() != null && !txt.getText().toString().equals(".") && !txt.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }
}