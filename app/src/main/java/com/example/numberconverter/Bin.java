package com.example.numberconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bin extends AppCompatActivity {

    EditText etb_input;
    Button b_convert;
    TextView tvb_output;
    Button b_save;
    DatabaseHelper myDb;
    Button b_view;
    Button b_clear;
    Button b_del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin);
        etb_input = (EditText)findViewById(R.id.etb_input);
        b_convert=(Button)findViewById(R.id.b_convert);
        tvb_output=(TextView)findViewById(R.id.tvb_output);
        b_save=(Button)findViewById(R.id.b_save);
        myDb = new DatabaseHelper(this);
        b_save();
        b_view=(Button)findViewById(R.id.b_view);
        b_view();
        b_clear=(Button)findViewById(R.id.b_clear);
        b_clear();
        b_del=(Button)findViewById(R.id.b_del);

        b_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etb_input.getText().toString();
                //convert value to decimal
                int decimalValue = Integer.parseInt(input,2);
                String oct = Integer.toOctalString(decimalValue);
                String hex = Integer.toHexString(decimalValue);
                tvb_output.setText("BINARY FORM: " + input + "\n" +
                        "DECIMAL FORM: " + decimalValue + "\n" +
                        "OCTAl FORM: " + oct + "\n" +
                        "HEXADECIMAL FORM: " + hex);



            }
        });
        b_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etb_input.setText("");
                tvb_output.setText("");
            }
        });


    }
    public void  b_save(){
        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(etb_input.getText().toString(),tvb_output.getText().toString());
                if(isInserted =true)
                    Toast.makeText(Bin.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Bin.this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void b_view() {
        b_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount()==0){
                    //show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("INPUT :"+ res.getString(1)+"\n");
                    buffer.append("OUTPUT :"+ res.getString(2)+"\n\n");
                }
                //show all data
                showMessage("Data",buffer.toString());

            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }
    public void b_clear() {
        b_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor des = myDb.deleteData();
                if (des.getCount() == 0) {
                    //show message
                    showMessage("Data", "Deleted");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (des.moveToNext()) {
                    buffer.append("ID :" + des.getString(0) + "\n");
                    buffer.append("INPUT :" + des.getString(1) + "\n");
                    buffer.append("OUTPUT :"+ des.getString(2)+"\n\n");
                }
                //show all data
                showMessage("Error", buffer.toString());

            }
        });
    }
}