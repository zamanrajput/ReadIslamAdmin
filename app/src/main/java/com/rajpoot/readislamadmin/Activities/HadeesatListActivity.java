package com.rajpoot.readislamadmin.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.rajpoot.readislamadmin.Adapters.DuasAdapter;
import com.rajpoot.readislamadmin.Adapters.HadeesAdapter;
import com.rajpoot.readislamadmin.Models.DuaModel;
import com.rajpoot.readislamadmin.Models.HadeesModel;
import com.rajpoot.readislamadmin.R;

import java.util.ArrayList;
import java.util.List;

public class HadeesatListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadeesat_list);


        recyclerView = findViewById(R.id.recyViewHadeesat);

        ArrayList<HadeesModel> list = new ArrayList<>();


        FirebaseFirestore dataBase = FirebaseFirestore.getInstance();


        HadeesAdapter adapter = new HadeesAdapter(list);
        recyclerView.setAdapter(adapter);
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading Hadeesat please wait!");
        dialog.setCancelable(false);
        dialog.show();

        dataBase.collection("Hadeesat").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                dialog.dismiss();

                for (DocumentSnapshot x : list1) {
                    HadeesModel modelObj = x.toObject(HadeesModel.class);
                    list.add(modelObj);
                }
                adapter.notifyDataSetChanged();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}