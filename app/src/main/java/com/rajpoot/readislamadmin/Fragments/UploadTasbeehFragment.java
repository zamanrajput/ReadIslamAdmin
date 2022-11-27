package com.rajpoot.readislamadmin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;
import com.rajpoot.readislamadmin.R;

import java.util.HashMap;
import java.util.Map;


public class UploadTasbeehFragment extends Fragment {
    EditText TasbeehNo, TasbeehTitleEng, TasbeehNameAr, TasbeehNameEng, TasbeehMeaningEng, TasbeehReferenceEng;
    Button sendBtn;
    private FirebaseFirestore dataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBase = FirebaseFirestore.getInstance();
        View view = inflater.inflate(R.layout.fragment_upload_tasbeeh, container, false);


        TasbeehNo = view.findViewById(R.id.tasbeehNo);
        TasbeehTitleEng = view.findViewById(R.id.tasbeehTitleEnglish);
        TasbeehNameEng = view.findViewById(R.id.tasbeehNameEnglish);
        TasbeehNameAr = view.findViewById(R.id.tasbeehNameArabic);
        TasbeehMeaningEng = view.findViewById(R.id.tasbeehMeaningEng);
        TasbeehReferenceEng = view.findViewById(R.id.tasbeehReferenceEng);
        sendBtn = view.findViewById(R.id.uploadDua);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        return view;
    }

    private void insert() {
        Map<String, String> record;

        record = new HashMap<>();
        if (!TasbeehNo.getText().toString().isEmpty() && !TasbeehTitleEng.getText().toString().isEmpty() && !TasbeehMeaningEng.getText().toString().isEmpty() && !TasbeehReferenceEng.getText().toString().isEmpty() && !TasbeehNameEng.getText().toString().isEmpty() && !TasbeehNameAr.getText().toString().isEmpty()) {

            record.put("No", TasbeehNo.getText().toString());
            record.put("NameEng", TasbeehNameEng.getText().toString());
            record.put("Refrence", TasbeehReferenceEng.getText().toString());
            record.put("TitleArabic", TasbeehNameAr.getText().toString());
            record.put("TitleEng", TasbeehTitleEng.getText().toString());
            record.put("Meaning", TasbeehMeaningEng.getText().toString());
            dataBase.collection("Tashbehat")
                    .add(record)
                    .addOnCompleteListener(task -> {
                        Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        TasbeehTitleEng.setText("");
                        TasbeehNameAr.setText("");
                        TasbeehNameEng.setText("");
                        TasbeehMeaningEng.setText("");
                        TasbeehReferenceEng.setText("");
                    })
                    .addOnFailureListener(e -> Toast.makeText(getContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show());


        }else {
            Toast.makeText(getContext(), "Please fill all fields and try again",Toast.LENGTH_LONG).show();
        }


    }

}