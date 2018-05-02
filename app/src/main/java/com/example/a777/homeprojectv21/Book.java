package com.example.a777.homeprojectv21;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Book extends AppCompatActivity {

    ListView list;
    ActionBar actionBar;
    final String[] array = new String[]{
            "Международная система едениц (СИ)",
            "Барсик"

    };
    //getResources().getStringArray(R.array.topics);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        //Кнопочка НАЗАД в ActionBar
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        list = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array);//Своя размета R.layout.list_item
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position){
                    case 0:
                        Toast.makeText(getApplicationContext(),"тема 1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),"тема 2", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                //По нажатию на конопочку назад в ActionBar
                //завершается текущая активность
                //TODO ВОЗМОЖНО ЗДЕСЬ НЕОБХОДИМО СОХРАНИНЬ КАКИЕ-ЛИБО ДАННЫЕ, но это не точно
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
//TODO  чнение из файла
/*
textView = (TextView) topic.findViewById(R.id.section_label_one);
        //Чтение из файла
        InputStream path =getResources().openRawResource(R.raw.topic_one);
        BufferedReader br = new BufferedReader(new InputStreamReader(path));
        textView.setText("");
        try {
        String str = "";
        while ((str = br.readLine()) != null) {

        textView.append(str);
        }
        }catch (IOException e) {
        e.printStackTrace();
        }*/

