package com.example.traincrossinglocator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class ViewTrains extends AppCompatActivity {


    FirebaseFirestore firestore;

    List<Train> trainList;

    RecyclerView recyclerView;

    ViewTrainsAdapter viewTrainsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trains);

        firestore=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.recycler);

        trainList=new ArrayList<>();
        viewTrainsAdapter=new ViewTrainsAdapter(trainList);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(viewTrainsAdapter);


        firestore.collection("trains").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Toast.makeText(ViewTrains.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                for(DocumentSnapshot doc:queryDocumentSnapshots){


                        Train train=new Train(doc.get("trainName").toString(),doc.get("expectedDeparture").toString());
                    Log.d("FireDebug",""+train.getName());
                        trainList.add(train);

                        viewTrainsAdapter.notifyDataSetChanged();
                    }


                }
        });




    }
}
