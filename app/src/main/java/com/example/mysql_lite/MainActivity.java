package com.example.mysql_lite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper myDb;
    private EditText name;
    private EditText surname;
    private EditText marks;
    private Button button;
    private EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindActivity();
    }

    private void bindActivity() {
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        marks = findViewById(R.id.marks);
        id = findViewById(R.id.id);
        button = findViewById(R.id.button);
        Button Update = findViewById(R.id.update);

        myDb = new DataBaseHelper(this);

    }

    public void AddData(View view) {
        Boolean isInserted = myDb.insertData(name.getText().toString(),
                surname.getText().toString(),
                marks.getText().toString()
        );

        if (isInserted == false) {
            Toast.makeText(this, "Data is Not Added , Try Again", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Added Successfull", Toast.LENGTH_SHORT).show();

        }

    }

    public void SeeAllData(View view) {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            // Some Message to be shown
            ShowMessage("Error", "Nothing Found ");
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Name :" + res.getString(1) + "\n");
            buffer.append("Surname :" + res.getString(2) + "\n");
            buffer.append("Marks :" + res.getString(3) + "\n\n");
        }
        ShowMessage("Data", buffer.toString());

    }

    public void ShowMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setMessage(title)
                .setMessage(message)
                .create().show();

    }

    public void Update(View view) {
        boolean isUpdate = myDb.UpdateData(
                id.getText().toString(),
                name.getText().toString(),
                surname.getText().toString(),
                marks.getText().toString()
        );
        if (isUpdate) {
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data not Updated ", Toast.LENGTH_SHORT).show();
        }
    }

    public void Delete(View view) {
        int DeletedData = myDb.DeleteData(id.getText().toString());
        if (DeletedData > 0) {
            Toast.makeText(this, "Data is Deleted ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data not Deleted", Toast.LENGTH_SHORT).show();
        }
    }

}
