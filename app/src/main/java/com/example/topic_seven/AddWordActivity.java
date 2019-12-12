package com.example.topic_seven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ArrayAdapter.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddWordActivity extends AppCompatActivity {


    private ListView lstDictionary;
    private Map<String,String> dictionary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);


       //ListView lstDictionary = (ListView) findViewById(R.id.lstDictionary);
       lstDictionary = findViewById(R.id.lstDictionary);
        dictionary=new HashMap<>();
        readFromFile();
        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        lstDictionary.setAdapter(adapter);
        lstDictionary.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                String key = parent.getItemAtPosition(position).toString();
                String meaning = dictionary.get(key);
                Intent intent = new Intent(AddWordActivity.this,MeaningActivity.class);
                intent.putExtra("meaning",meaning);
                startActivity(intent);

            }
        });

    }
    private void readFromFile(){
        try {
            FileInputStream fos = openFileInput("words.txt");
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line=br.readLine())!=null){
                String[]parts = line.split("->");
                dictionary.put(parts[0],parts[1]);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
