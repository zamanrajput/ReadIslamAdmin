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


public class SendHadeesFrag extends Fragment {
    private EditText hadeesTitleEn,hadeesTitleUr,hadeesBodyEn,hadeesBodyUr,hadeesBodyAr,reference;
    private Button sendBtn;
    private FirebaseFirestore dataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dataBase = FirebaseFirestore.getInstance();

        View view = inflater.inflate(R.layout.fragment_send_hadees, container, false);
        hadeesBodyAr = view.findViewById(R.id.hadeesBodyAr);
        hadeesBodyEn = view.findViewById(R.id.hadeesBodyEn);
        hadeesBodyUr = view.findViewById(R.id.hadeesBodyUr);
        hadeesTitleEn = view.findViewById(R.id.hadeesTitleEn);
        hadeesTitleUr  = view.findViewById(R.id.hadeesTitleUr);
        reference = view.findViewById(R.id.hadeesReference);

        sendBtn = view.findViewById(R.id.uploadHadees);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });




        return view;
    }



    private void insert(){
        Map<String,String> record;

        record = new HashMap<>();
        if(!hadeesBodyAr.getText().toString().isEmpty()&&!hadeesBodyEn.getText().toString().isEmpty()&&!hadeesBodyUr.getText().toString().isEmpty()&&!hadeesTitleUr.getText().toString().isEmpty()&&!hadeesTitleEn.getText().toString().isEmpty() && !reference.getText().toString().isEmpty()){

            record.put("titleUrdu",hadeesTitleUr.getText().toString());
            record.put("titleEnglish",hadeesTitleEn.getText().toString());
            record.put("bodyEnglish",hadeesBodyEn.getText().toString());
            record.put("bodyUrdu",hadeesBodyUr.getText().toString());
            record.put("bodyArabic",hadeesBodyAr.getText().toString());
            record.put("reference",reference.getText().toString());
            dataBase.collection("Hadeesat").add(record).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(getContext(),"Uploaded", Toast.LENGTH_SHORT).show();

                    hadeesTitleUr.setText("");
                    hadeesTitleEn.setText("");
                    hadeesBodyAr.setText("");
                    hadeesBodyEn.setText("");
                    hadeesBodyUr.setText("");
                    reference.setText("");

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