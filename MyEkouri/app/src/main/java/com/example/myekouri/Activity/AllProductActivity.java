package com.example.myekouri.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myekouri.Adapter.AllProductAdapter;
import com.example.myekouri.Adapter.CartListAdapter;
import com.example.myekouri.Adapter.CategoryAdapter;
import com.example.myekouri.Adapter.ViewProductAdapter;
import com.example.myekouri.Domain.AllProductDomain;
import com.example.myekouri.Domain.CartListDomain;
import com.example.myekouri.Domain.CategoryDomain;
import com.example.myekouri.Domain.ViewProductDomain;
import com.example.myekouri.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllProductActivity extends AppCompatActivity {

    FirebaseFirestore mStore;
    RecyclerView allProRecView;
   AllProductAdapter allProductAdapter;
   List<AllProductDomain> allProductDomainList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);

        bottomNavigation();



        allProRecView = findViewById(R.id.allProRecView);
        allProRecView.setHasFixedSize(true);
        allProRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        mStore = FirebaseFirestore.getInstance();
        allProductDomainList = new ArrayList<>();
        allProductAdapter = new AllProductAdapter(AllProductActivity.this,allProductDomainList);

        allProRecView.setAdapter(allProductAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {
        mStore.collection("Product").orderBy("Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                      if(error!= null){
                          Log.e("Firestore error",error.getMessage());
                          return;
                      }
                        for (DocumentChange doc : value.getDocumentChanges()){
                            if(doc.getType()==DocumentChange.Type.ADDED){
                                allProductDomainList.add(doc.getDocument().toObject(AllProductDomain.class));
                            }
                            allProductAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    private void bottomNavigation() {
            FloatingActionButton floatingActionButton = findViewById(R.id.addBtn);
            LinearLayout homeBtn = findViewById(R.id.homeBtn);
            LinearLayout profileBtn = findViewById(R.id.profileBtn);
            LinearLayout settingBtn = findViewById(R.id.settingbtn);
            //LinearLayout logoutBtn = findViewById(R.id.logoutBtn);


            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AllProductActivity.this, AddProductActivity.class));
                }
            });
            homeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AllProductActivity.this, MainActivity.class));
                }
            });

            profileBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AllProductActivity.this, MyProfileActivity.class));
                }
            });

            settingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AllProductActivity.this, AllProductActivity.class));
                }
            });
       /* logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });*/


        }
}
