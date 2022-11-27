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


public class SendDuaFrag extends Fragment {
    private EditText duaTitleEn,duaTitleUr,duaBodyEn,duaBodyUr,duaBodyAr;
    private Button sendBtn;
   private FirebaseFirestore dataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dataBase = FirebaseFirestore.getInstance();

        View view = inflater.inflate(R.layout.fragment_send_dua, container, false);
        duaBodyAr = view.findViewById(R.id.duaBodyAr);
        duaBodyEn = view.findViewById(R.id.tasbeehReferenceEng);
        duaBodyUr = view.findViewById(R.id.duaBodyUr);
        duaTitleEn = view.findViewById(R.id.duaTitleEn);
        duaTitleUr = view.findViewById(R.id.duaTitleUr);
        sendBtn = view.findViewById(R.id.uploadDua);
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
        if(!duaBodyAr.getText().toString().isEmpty()&&!duaBodyEn.getText().toString().isEmpty()&&!duaBodyUr.getText().toString().isEmpty()&&!duaTitleUr.getText().toString().isEmpty()&&!duaTitleEn.getText().toString().isEmpty()){

            record.put("UrduTitle",duaTitleUr.getText().toString());
            record.put("EnglishTitle",duaTitleEn.getText().toString());
            record.put("EnglishBody",duaBodyEn.getText().toString());
            record.put("UrduBody",duaBodyUr.getText().toString());
            record.put("ArabicBody",duaBodyAr.getText().toString());

            dataBase.collection("Duas").add(record).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(getContext(),"Uploaded", Toast.LENGTH_SHORT).show();


                    duaBodyUr.setText("");
                    duaBodyAr.setText("");
                    duaBodyEn.setText("");
                    duaTitleEn.setText("");
                    duaTitleUr.setText("");
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