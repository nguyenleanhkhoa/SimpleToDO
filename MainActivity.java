package com.simpletodo.nguyenleanhkhoa.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayList<String> dsitem;
    ArrayAdapter<String>adapter;
    EditText edtenter;
    Button btnenter; 
    Integer row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtenter = (EditText) findViewById(R.id.edt);
        btnenter = (Button) findViewById(R.id.btnenter);
        list = (ListView) findViewById(R.id.listvv);
        setArray();
        list.setAdapter(adapter);
        setButtonclick();
        setLongclickList();
        setclickList();
    }
    private void setArray() {
        readItem();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, dsitem);
    }
    private void setButtonclick() {
        btnenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additem();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==9 && resultCode==3){
           String fileDir=getFilesDir().getAbsolutePath();
            File todoFile=new File(fileDir,"tode.txt");
            if(todoFile.exists()){
                todoFile.delete();
            }
            dsitem.set(row,data.getStringExtra("Entersave"));
            writeitem();
        }
    }
    public void setclickList() {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pos=dsitem.get(position);
                row=position;
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("name", pos);
                startActivityForResult(intent,9);
            }
        });
    }
    public void setLongclickList() {
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dsitem.remove(position);
                adapter.notifyDataSetChanged();
                writeitem();
                return true;
            }
        });
    }
    public void additem() {
        String text=edtenter.getText().toString();
        if(text.equals("")){
            Toast.makeText(MainActivity.this,"Please Enter text!",Toast.LENGTH_SHORT).show();
        }else{
            dsitem.add(edtenter.getText().toString());
            adapter.notifyDataSetChanged();
            edtenter.setText("");
            writeitem();
        }

    }
    public void readItem() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "tode.txt");
        try {
            dsitem = new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {
            dsitem=new ArrayList<String>();
        }
    }
    public void writeitem() {
        File fileDir=getFilesDir();
        File todoFile=new File(fileDir,"tode.txt");
        try{
            FileUtils.writeLines(todoFile,dsitem);
        } catch (IOException e) {
            Log.e("write","fail");
        }
    }
}
