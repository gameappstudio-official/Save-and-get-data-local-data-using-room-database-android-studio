package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.EntityClass.UserModel;

public class MainActivity extends AppCompatActivity {

    EditText name, phoneno, address;
    Button save, getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        phoneno = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        save = findViewById(R.id.btn_save);
        getData = findViewById(R.id.btn_getData);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GetData.class));
            }
        });

    }

    private void saveData() {

        String name_txt = name.getText().toString().trim();
        String phone_txt = phoneno.getText().toString().trim();
        String address_txt = address.getText().toString().trim();


        UserModel model = new UserModel();
        model.setName(name_txt);
        model.setAddress(address_txt);
        model.setPhoneno(phone_txt);
        DatabaseClass.getDatabase(getApplicationContext()).getDao().insertAllData(model);

        name.setText("");
        phoneno.setText("");
        address.setText("");
        Toast.makeText(this, "Data Successfully Saved", Toast.LENGTH_SHORT).show();


    }
}