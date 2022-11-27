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
import com.rajpoot.readislamadmin.Models.TasbehatModel;
import com.rajpoot.readislamadmin.R;

import java.util.ArrayList;
import java.util.List;

public class TasbeehAdapter extends RecyclerView.Adapter<TasbeehAdapter.Holder> {

    ArrayList<TasbehatModel> list;

    public TasbeehAdapter(ArrayList<TasbehatModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.Name.setText(list.get(position).getNameEng());
        holder.No.setText(list.get(position).getNo());
        String tempNo=list.get(position).getNo();
        String temName = list.get(position).getNameEng();
        TasbehatModel model = list.get(position);
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(holder.delBtn.getContext())
                        .setMessage("Are you to delete tashbeeh "+temName+"?").
                        setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(holder.delBtn.getContext(),"Canceled",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseFirestore firestoe = FirebaseFirestore.getInstance();
                                firestoe.collection("Tashbehat").whereEqualTo("No",tempNo)
                                        .whereEqualTo("NameEng",temName)
                                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                        WriteBatch batch = firestoe.batch();



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
                        }).show();

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
