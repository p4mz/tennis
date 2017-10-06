package com.jenbodint.tennis;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements MainActivityContact.View {

    private final String TAG = getClass().getSimpleName();

    String filePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/tennis/input.txt";

    private MainActivityContact.Action mAction;

    TextView tv_input;
    TextView tv_result;
    Button btn_read_file;
    Button btn_transform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAction = new MainActivityPresenter(this, new TennisGame());

        tv_input = (TextView) findViewById(R.id.tv_input);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_read_file = (Button) findViewById(R.id.btn_read_file);
        btn_transform = (Button) findViewById(R.id.btn_transform);

        btn_read_file.setOnClickListener(view -> readInputFile());
        btn_transform.setOnClickListener(view -> transformScore());
    }

    private void readInputFile() {
        List<String> inputs = getInput();
        if (inputs != null && !inputs.isEmpty()) {
            displayInput(buildInputs(inputs));
            this.btn_read_file.setVisibility(View.GONE);
            this.btn_transform.setVisibility(View.VISIBLE);
        } else {
            alertFileNotFound();
        }
    }

    private void transformScore() {
        try {
            mAction.transform();
            this.btn_transform.setVisibility(View.GONE);
        } catch (Exception e) {
            Log.e(TAG, "mAction.transform", e);
            alertTransformFailure();
        }
    }

    private void alertFileNotFound() {
        new AlertDialog.Builder(this)
                .setTitle("File is not found")
                .setMessage("Please put input to the following path and try again:\n\n" + filePath)
                .setPositiveButton("OK", null)
                .show();
    }

    private void alertTransformFailure() {
        new AlertDialog.Builder(this)
                .setTitle("Cannot transform score")
                .setMessage("There are some errors or you put a wrong format text, please check input format and try again")
                .setPositiveButton("OK", null)
                .show();
    }

    private String buildInputs(List<String> inputs) {
        return TextUtils.join("\n", inputs);
    }

    @Override
    public void displayInput(String input) {
        tv_input.setText(input);
    }

    @Override
    public void displayResult(String result) {
        tv_result.setText(result);
    }

    @Override
    public List<String> getInput() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String text;
            while ((text = br.readLine()) != null) {
                list.add(text);
            }
        } catch (IOException e) {
            Log.e(TAG, "getInput", e);
        }
        return list;
    }
}
