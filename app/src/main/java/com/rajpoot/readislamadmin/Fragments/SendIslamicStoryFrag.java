package com.rajpoot.readislamadmin.Fragments;

import android.animation.TimeAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rajpoot.readislamadmin.R;

import java.util.HashMap;
import java.util.Map;


public class SendIslamicStoryFrag extends Fragment {
    private EditText storyTitleEn,storyTitleUr,storyBodyEn,storyBodyUr;
    private Button sendBtn;
    private FirebaseFirestore dataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dataBase = FirebaseFirestore.getInstance();

        View view = inflater.inflate(R.layout.fragment_send_islamic_story, container, false);

        storyBodyEn = view.findViewById(R.id.storyBodyEn);
        storyBodyUr = view.findViewById(R.id.storyBodyUr);
        storyTitleEn = view.findViewById(R.id.storyTitleEn);
        storyTitleUr = view.findViewById(R.id.storyTitleUr);
        sendBtn = view.findViewById(R.id.uploadStory);
        sendBtn.setOnClickListener(view1 -> insert());




        return view;
    }



    private void insert(){
        Map<String,String> record;

        record = new HashMap<>();
        if(!storyBodyEn.getText().toString().isEmpty()&&!storyBodyUr.getText().toString().isEmpty()&&!storyTitleUr.getText().toString().isEmpty()&&!storyTitleEn.getText().toString().isEmpty()){

            record.put("titleUrdu",storyTitleUr.getText().toString());
            record.put("titleEnglish",storyTitleEn.getText().toString());
            record.put("bodyEnglish",storyBodyEn.getText().toString());
            record.put("bodyUrdu",storyBodyUr.getText().toString());


            dataBase.collection("Stories").add(record).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(getContext(),"Uploaded", Toast.LENGTH_SHORT).show();
                    storyBodyEn.setText("");
                    storyBodyUr.setText("");
                    storyTitleEn.setText("");
                    storyTitleUr.setText("");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


        }else {
            Toast.makeText(getContext(), "Please fill all fields and try again",Toast.LENGTH_LONG).show();
        }







    }

}