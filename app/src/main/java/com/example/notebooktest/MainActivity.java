package com.example.notebooktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String id=null;
    //public static final String content="content://com.example.wordnote.provider";
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button add=(Button)findViewById(R.id.btn_add);
        uri= Uri.parse("content://com.example.words.provider/words");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri=Uri.parse(content);
                ContentValues values=new ContentValues();
                //插入其他的改下面四行
                values.put("_id","1");
                values.put("word","news");
                values.put("meaning","新闻");
                values.put("sample","there is news ");
                getContentResolver().insert(uri,values);
                //id=newUri.getPathSegments().get(1);
            }
        });

        Button del=(Button)findViewById(R.id.btn_del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Uri uri=Uri.parse(content+"/"+id);
                getContentResolver().delete(uri,"word = ?",new String[]{"news"});
            }
        });

        Button query=(Button)findViewById(R.id.btn_query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor query = getContentResolver().query(uri, null, "word = ?", new String[]{"news"}, null);
                if (query.moveToFirst()){
                    do {
                        String word = query.getString(query.getColumnIndex("sample"));
                        Toast.makeText(MainActivity.this,word,Toast.LENGTH_LONG).show();
                    }while (query.moveToNext());
                }
            }
        });

        Button update=(Button)findViewById(R.id.btn_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("meaning","wefwf ff");
                getContentResolver().update(uri,values,"word = ?", new String[]{"news"});
            }
        });

    }
}
