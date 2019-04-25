package com.dictionaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnglishToNepaliDictionary extends AppCompatActivity {

    Button btnAdd;
    private ListView lstDictionary;
    private Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_to_nepali_dictionary);

//        btnAdd=findViewById(R.id.btnAdd);

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EnglishToNepaliDictionary.this, AddWordActivity.class);
//
//                startActivity(intent);
//            }
//        });
        lstDictionary = findViewById(R.id.lstDictionary);
        dictionary = new HashMap<>();
//        for (int i = 0; i < words.length; i += 2) {
//            dictionary.put(words[i], words[i + 1]);
//        }

        readFromFile();
        ArrayAdapter english = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        lstDictionary.setAdapter(english);

        lstDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                String meaning = dictionary.get(key);

                Intent intent = new Intent(EnglishToNepaliDictionary.this, SecondActivity.class);

                intent.putExtra("meaning", meaning);
                startActivity(intent);
            }
        });


    }

      private void readFromFile() {
        try{
            FileInputStream fos=openFileInput("words.txt");
            InputStreamReader isr =new InputStreamReader(fos);
            BufferedReader br =new BufferedReader(isr);
            String line="";
            while ((line=br.readLine())!=null){
                String [] parts=line.split(">");
                dictionary.put(parts[0], parts[1]);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
