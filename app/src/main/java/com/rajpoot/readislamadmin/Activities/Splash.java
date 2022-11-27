package com.rajpoot.readislamadmin.Activities;

import static com.google.firebase.messaging.Constants.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.window.SplashScreen;

import com.rajpoot.readislamadmin.R;
import com.rajpoot.readislamadmin.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {
   ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.loginBtn.setOnClickListener(v->Click());
        binding.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(binding.password.getCurrentTextColor()==getResources().getColor(R.color.white)){
                    binding.password.setTextColor(getResources().getColor(R.color.Color1));
                }else{
                    binding.password.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
        binding.username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(binding.username.getCurrentTextColor()==getResources().getColor(R.color.white)){
                    binding.username.setTextColor(getResources().getColor(R.color.Color1));
                }else{
                    binding.username.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        binding.password.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER){
                Click();
            }

            return false;
        });
//        binding.username.setTextColor(getResources().getColor(R.color.white));


    }

    private void Click(){
        int u = 0;

        String username = binding.username.getText().toString();



        if(!binding.password.getText().toString().isEmpty() &&!username.isEmpty()){
            int pass = Integer.parseInt(binding.password.getText().toString());
            if(binding.username.getText().length()<5 ||binding.password.getText().length()<4){
                Toast.makeText(getApplicationContext(),"Please fill fields completely",Toast.LENGTH_SHORT).show();
            }else{
                if(pass == 2435){
                    Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Exiting",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    },2000);
                }
            }



        }else {
            Toast.makeText(getApplicationContext(),"Please Fill Fields",Toast.LENGTH_SHORT).show();
        }
    }


}