package com.example.topic_seven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {
    EditText etWord, etMeaning;
    Button btnAddWord,btnWordMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAddWord = findViewById(R.id.btnAddWord);
        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        btnWordMeaning=findViewById(R.id.btnWordMeaning);
        btnWordMeaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer();
            }
        });

    }

    private void save() {
        try {
            PrintStream printStream = new PrintStream(openFileOutput("words.txt", MODE_PRIVATE | MODE_APPEND));
            printStream.println(etWord.getText().toString() + "->" + etMeaning.getText().toString());
            Toast.makeText(this, "Saved to" + getFilesDir(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.d("Dictionary app", "Error" + e.toString());
            e.printStackTrace();
        }
    }
    private void transfer(){
        Intent intent = new Intent(this,AddWordActivity.class);
        startActivity(intent);


        Toast.makeText(this,"You Can Find The Meaning Of The Different Words",Toast.LENGTH_SHORT).show();
    }
}
