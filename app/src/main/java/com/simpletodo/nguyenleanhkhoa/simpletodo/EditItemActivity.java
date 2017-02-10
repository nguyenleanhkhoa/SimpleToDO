package com.simpletodo.nguyenleanhkhoa.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    EditText edt;
    Button btnsave;
    Intent inten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        edt= (EditText) findViewById(R.id.edit);
        btnsave= (Button) findViewById(R.id.btnsave);
        String intent2=getIntent().getStringExtra("name");
        edt.setText(intent2);
        buttonSave();

    }
	
	

    private void buttonSave() {
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inten=new Intent();
                inten.putExtra("Entersave",edt.getText().toString());
                setResult(3,inten);
                finish();
            }
        });
    }
}
