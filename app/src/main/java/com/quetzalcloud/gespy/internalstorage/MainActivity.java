package com.quetzalcloud.gespy.internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Button buttonSave;
    private Button buttonRead;

    private TextView textView;
    private EditText editText;

    private String simpleFileName = "note.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonSave = (Button)findViewById(R.id.buttonSave);
        this.buttonRead = (Button)findViewById(R.id.buttonRead);

        this.textView = (TextView) findViewById(R.id.textView);
        this.editText = (EditText) findViewById(R.id.editText);

        this.buttonSave.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View view){

                saveData();
            }
        });

        this.buttonRead.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                readData();
            }
        });
    }

    private void saveData(){
        String data = this.editText.getText().toString();

        try{
            FileOutputStream out = this.openFileOutput(simpleFileName, MODE_PRIVATE);
            out.write(data.getBytes());
            out.close();
            Toast.makeText(this, "Archivo guardado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void readData(){
        try{
            FileInputStream in = this.openFileInput(simpleFileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            StringBuilder sb = new StringBuilder();
            String s = null;
            while((s = br.readLine()) != null){
                sb.append(s).append("\n");
            }
            this.textView.setText(sb.toString());
        }catch (Exception e){
            Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
