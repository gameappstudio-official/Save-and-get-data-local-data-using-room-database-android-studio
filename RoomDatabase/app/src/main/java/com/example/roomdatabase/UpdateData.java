package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {


    EditText name, phoneno, address;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        name = findViewById(R.id.name);
        phoneno = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        update = findViewById(R.id.btn_update);


        //GET DATA
        name.setText(getIntent().getExtras().getString("name"));
        address.setText(getIntent().getExtras().getString("address"));
        phoneno.setText(getIntent().getExtras().getString("phoneno"));
        final String id = getIntent().getExtras().getString("id");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_txt = name.getText().toString().trim();
                String phone_txt = phoneno.getText().toString().trim();
                String address_txt = address.getText().toString().trim();
                if (name_txt.equals("") || phone_txt.equals("") || address_txt.equals("")) {
                    Toast.makeText(UpdateData.this, "All Field is required ....", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseClass.getDatabase(getApplicationContext()).getDao().updateData(name_txt, phone_txt, address_txt, Integer.parseInt(id));
                    finish();

                }


            }
        });
    }
}