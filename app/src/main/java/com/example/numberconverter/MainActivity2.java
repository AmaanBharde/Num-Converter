package com.example.numberconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spinner=findViewById(R.id.spinner);
        List<String> categories = new ArrayList<>();
        categories.add(0,"Select Number System");
        categories.add(1,"Decimal to Binary, Octal, Hexadecimal");
        categories.add(2,"Binary to Decimal, Octal, Hexadecimal");
        categories.add(3,"Octal to Decimal, Binary, Hexadecimal");
        categories.add(4,"Hexadecimal to Decimal, Binary, Octal");

        ArrayAdapter<String> dataAdapter;
        dataAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Select Number System"))
                {

                }
                else
                {
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(),"Selected:"+item, Toast.LENGTH_SHORT).show();

                    if(parent.getItemAtPosition(position).equals("Decimal to Binary, Octal, Hexadecimal"))
                    {
                        Intent intent=new Intent(MainActivity2.this,Dec.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Binary to Decimal, Octal, Hexadecimal"))
                    {
                        Intent intent=new Intent(MainActivity2.this,Bin.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Octal to Decimal, Binary, Hexadecimal"))
                    {
                        Intent intent=new Intent(MainActivity2.this,Oct.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Hexadecimal to Decimal, Binary, Octal"))
                    {
                        Intent intent=new Intent(MainActivity2.this,Hex.class);
                        startActivity(intent);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.converter,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent about = new Intent(MainActivity2.this,About.class);
                startActivity(about);
                Toast.makeText(this,"About Selected",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void learn(View view){
        Intent learn = new Intent(MainActivity2.this,Learn.class);
        startActivity(learn);
    }
}