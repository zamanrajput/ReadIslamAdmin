package com.rajpoot.readislamadmin.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.rajpoot.readislamadmin.Models.DuaModel;
import com.rajpoot.readislamadmin.Models.TasbehatModel;
import com.rajpoot.readislamadmin.R;

import java.util.ArrayList;
import java.util.List;

public class DuasAdapter extends RecyclerView.Adapter<DuasAdapter.Holder> {

    ArrayList<DuaModel> list;

    public DuasAdapter(ArrayList<DuaModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.Name.setText(list.get(position).getEnglishTitle());
        holder.No.setText(list.get(position).getUrduTitle());
        String tempUT=list.get(position).getUrduTitle();
        String temNE = list.get(position).getEnglishTitle();
        DuaModel model = list.get(position);
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(holder.delBtn.getContext())
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("Duas").whereEqualTo("UrduTitle",tempUT)
                                        .whereEqualTo("EnglishTitle",temNE)
                                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                        WriteBatch batch = db.batch();



                                        List<DocumentSnapshot> documentSnapshots = queryDocumentSnapshots.getDocuments();
                                        for(DocumentSnapshot document:documentSnapshots){

                                            batch.delete(document.getReference());


                                            list.remove(model);

                                            notifyDataSetChanged();

                                        }
                                        batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(view.getContext(),"Deleted",Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(view.getContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                                            }
                                        });





                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(view.getContext(), e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.delBtn.getContext(),"Canceled",Toast.LENGTH_SHORT).show();

                    }
                }).setMessage("Are you to delete tashbeeh "+temNE+"?").show();





            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView No,Name;
        Button delBtn;

        public Holder(@NonNull View itemView) {
            super(itemView);
            No = itemView.findViewById(R.id.No);
            Name = itemView.findViewById(R.id.Name);
            delBtn = itemView.findViewById(R.id.Delete);

        }
    }
}
