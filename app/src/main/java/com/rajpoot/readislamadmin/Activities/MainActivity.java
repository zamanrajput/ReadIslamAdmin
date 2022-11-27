package com.rajpoot.readislamadmin.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rajpoot.readislamadmin.R;
import com.rajpoot.readislamadmin.Fragments.SendDuaFrag;
import com.rajpoot.readislamadmin.Fragments.SendHadeesFrag;
import com.rajpoot.readislamadmin.Fragments.SendIslamicStoryFrag;
import com.rajpoot.readislamadmin.Fragments.SendNotificationFrag;
import com.rajpoot.readislamadmin.Fragments.UploadTasbeehFragment;

public class MainActivity extends AppCompatActivity {
    public  static  Fragment SELECTED=null;
    public  static  String TITLE=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }
    public void StartContainer(Fragment fragment,String Title){
        TITLE = Title;
        SELECTED = fragment;
        startActivity(new Intent(MainActivity.this,MainContainer.class));
    }
    public void SendDua(View v){
        StartContainer(new SendDuaFrag(),"UPLOAD DUA");
    }
    public void SendNotification(View v){
        StartContainer(new SendNotificationFrag(),"Send Notification");
    }
    public void SendHadees(View v){
        StartContainer(new SendHadeesFrag(),"UPLOAD HADEES");
    }
    public void SendStory(View v){
        StartContainer(new SendIslamicStoryFrag(),"UPLOAD Story");
    }
    public void UploadTasbeeh(View v){
        StartContainer(new UploadTasbeehFragment(),"Upload Tasbeeh");
    }

}