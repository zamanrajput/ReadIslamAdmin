package com.rajpoot.readislamadmin.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.rajpoot.readislamadmin.FireBaseMessaging.FcmNotificationsSender;
import com.rajpoot.readislamadmin.R;


public class SendNotificationFrag extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {







        View view = inflater.inflate(R.layout.fragment_send_notification, container, false);


        TextView titleTv = view.findViewById(R.id.titleTV);
        TextView bodyTv = view.findViewById(R.id.bodyTv);

        Button sendBtn = view.findViewById(R.id.sendNotBtn);

        sendBtn.setOnClickListener(v->{
            if(!titleTv.getText().toString().isEmpty()&& !bodyTv.getText().toString().isEmpty()){
                FcmNotificationsSender fcmNotificationsSender = new FcmNotificationsSender("/topics/all",titleTv.getText().toString(),bodyTv.getText().toString(),view.getContext().getApplicationContext(),(Activity) view.getContext());
                fcmNotificationsSender.SendNotifications();
                titleTv.setText("");
                bodyTv.setText("");

            }else {
                Toast.makeText(view.getContext(),"Enter details",Toast.LENGTH_SHORT).show();
            }
        });






        return view;
    }
}