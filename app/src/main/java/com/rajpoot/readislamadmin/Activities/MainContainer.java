package com.rajpoot.readislamadmin.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.rajpoot.readislamadmin.R;

public class MainContainer extends AppCompatActivity {
TextView title;


    FirebaseFirestore dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        title= findViewById(R.id.titleMain);
        title.setText(MainActivity.TITLE);

        dataBase = FirebaseFirestore.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,MainActivity.SELECTED).commit();
    }


    public void OpenList(View c){
        startActivity(new Intent(MainContainer.this, TasbehaatListActivity.class));
    }
    public void OpenDuasList(View c){
        startActivity(new Intent(MainContainer.this, DuasListActivity.class));
    }
    public void OpenHadeesatList(View c){
        startActivity(new Intent(MainContainer.this,HadeesatListActivity.class));
    }
    public void OpenStoriesList(View c){
        startActivity(new Intent(MainContainer.this,StoriesListActivity.class));
    }



}