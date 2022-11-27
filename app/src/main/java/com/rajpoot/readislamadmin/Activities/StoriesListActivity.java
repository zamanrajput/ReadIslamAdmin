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
import com.rajpoot.readislamadmin.Adapters.StoryAdapter;
import com.rajpoot.readislamadmin.Models.DuaModel;
import com.rajpoot.readislamadmin.Models.StoryModel;
import com.rajpoot.readislamadmin.R;

import java.util.ArrayList;
import java.util.List;

public class StoriesListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories_list);
        recyclerView = findViewById(R.id.storyRecylierView);

        ArrayList<StoryModel> list = new ArrayList<>();


        FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
        StoryAdapter adapter = new StoryAdapter(list);
        recyclerView.setAdapter(adapter);
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading Stories please wait!");
        dialog.setCancelable(false);
        dialog.show();

        dataBase.collection("Stories").get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

            dialog.dismiss();

            for (DocumentSnapshot x : list1) {
                StoryModel modelObj = x.toObject(StoryModel.class);
                list.add(modelObj);
            }
            adapter.notifyDataSetChanged();


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}