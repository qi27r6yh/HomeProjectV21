package com.example.a777.homeprojectv21;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    public final String keyFIRST_NAME = "FIRST_NamE";
    public final String keyLAST_NAME = "LAST_NamE";
    boolean flag = true;
    ActionBar actionBar;
    EditText firstName, lastName;

    SharedPreferences shared;
    String tempLast="null",
            tempFirst="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Кнопочка назад
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);

        shared = getSharedPreferences("Profile",MODE_PRIVATE);
        tempFirst = LoadData(keyFIRST_NAME);
        tempLast = LoadData(keyLAST_NAME);

        if (tempFirst.equals(""))
            firstName.setHint("Имя");
        else
            firstName.setText(tempFirst);

        if (tempLast.equals(""))
            lastName.setHint("Фамилия");
        else
            lastName.setText(tempLast);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String tempF = firstName.getText().toString();
        String tempL = lastName.getText().toString();

        switch(id){
            case android.R.id.home:
                if (!tempF.equals(tempFirst) || !tempL.equals(tempLast)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle("Предпреждение");
                    builder.setMessage("Сохранить изменения?");
                    //Возможность закрыть окно кнопкой Back
                    builder.setCancelable(false);
                    builder.setPositiveButton("Да!",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    SaveData(keyFIRST_NAME, firstName.getText().toString());
                                    SaveData(keyLAST_NAME, lastName.getText().toString());
                                    MainActivity.person_name.setText(LoadData(keyFIRST_NAME) + " " + LoadData(keyLAST_NAME));
                                    dialog.cancel();
                                    finish();
                                }
                            });
                    builder.setNegativeButton("Нет!",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    this.finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        String tempF = firstName.getText().toString();
        String tempL = lastName.getText().toString();
        if (!tempF.equals(tempFirst) || !tempL.equals(tempLast)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
            builder.setTitle("Предпреждение");
            builder.setMessage("Сохранить изменения?");
            //Возможность закрыть окно кнопкой Back
            builder.setCancelable(false);
            builder.setPositiveButton("Да!",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            SaveData(keyFIRST_NAME, firstName.getText().toString());
                            SaveData(keyLAST_NAME, lastName.getText().toString());
                            MainActivity.person_name.setText(LoadData(keyFIRST_NAME) + " " + LoadData(keyLAST_NAME));
                            dialog.cancel();
                            finish();
                        }
                    });
            builder.setNegativeButton("Нет!",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            this.finish();
        }
    }

    private String LoadData(String key){
        //" параментр возвращается в случае отсутствия
        String value = shared.getString(key,"NULL_SHARED");
        return value;
    }
    private void SaveData(String key,String value){
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(key,value);
        editor.apply();

    }
}


