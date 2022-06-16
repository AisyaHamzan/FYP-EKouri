package com.example.myekouri.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myekouri.Adapter.CategoryAdapter;
import com.example.myekouri.Adapter.FreshAdapter;
import com.example.myekouri.Adapter.PopularAdapter;
import com.example.myekouri.Adapter.ViewProductAdapter;
import com.example.myekouri.Domain.CategoryDomain;
import com.example.myekouri.Domain.FreshDomain;
import com.example.myekouri.Domain.PopularDomain;
import com.example.myekouri.Domain.ViewProductDomain;
import com.example.myekouri.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView.Adapter categoryAdapter, popularAdapter, freshAdapter;
    RecyclerView recyclerViewCategoryList, recyclerViewPopularList, recyclerViewFreshList;
    FirebaseFirestore mStore;

    //Fresh
    List<FreshDomain> freshDomainList;

    //Category
    List<CategoryDomain> categoryDomains;

    //Popular
    List<PopularDomain> popularDomainList;

    //SearchView
    EditText searchBox;
    private List<ViewProductDomain> viewProductDomainList;
    private RecyclerView recyclerViewSearch;
    private ViewProductAdapter viewProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigation();
        mStore = FirebaseFirestore.getInstance();


        //fresh items
        recyclerViewFreshList = findViewById(R.id.freshRecView);
        recyclerViewFreshList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        freshDomainList = new ArrayList<>();
        freshAdapter = new FreshAdapter(this, freshDomainList);
        recyclerViewFreshList.setAdapter(freshAdapter);

        mStore.collection("Fresh")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                FreshDomain freshDomain = document.toObject(FreshDomain.class);
                                freshDomainList.add(freshDomain);
                                freshAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //category items
        recyclerViewCategoryList = findViewById(R.id.catRecView);
        recyclerViewCategoryList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        categoryDomains = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, categoryDomains);
        recyclerViewCategoryList.setAdapter(categoryAdapter);

        mStore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryDomain categoryDomain = document.toObject(CategoryDomain.class);
                                categoryDomains.add(categoryDomain);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //popular items
        recyclerViewPopularList = findViewById(R.id.popRecView);
        recyclerViewPopularList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        popularDomainList = new ArrayList<>();
        popularAdapter = new PopularAdapter(this, popularDomainList);
        recyclerViewPopularList.setAdapter(popularAdapter);

        mStore.collection("Popular")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                PopularDomain popularDomain = documentSnapshot.toObject(PopularDomain.class);
                                popularDomainList.add(popularDomain);
                                popularAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //search items
      /*  recyclerViewSearch = findViewById(R.id.searchRecView);
        searchBox = findViewById(R.id.search);
        viewProductDomainList = new ArrayList<>();
        viewProductAdapter = new ViewProductAdapter(this,viewProductDomainList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSearch.setAdapter(viewProductAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                //called as and when user type each letter
                try {
                    viewProductAdapter.getFilter().filter(s);
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
           if(s.toString().isEmpty()){
                viewProductDomainList.clear();
                viewProductAdapter.notifyDataSetChanged();
            }else{
                searchProduct(s.toString());
            }
            }
        });

            }

    private void searchProduct(String Category) {
        if(!Category.isEmpty()){
            mStore.collection("Product")
                    .whereEqualTo("Category",Category).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                          if(task.isSuccessful() && task.getResult()!= null){

                              viewProductDomainList.clear();
                              viewProductAdapter.notifyDataSetChanged();
                              for(DocumentSnapshot doc : task.getResult().getDocuments()){
                                  ViewProductDomain viewProductDomain = doc.toObject(ViewProductDomain.class);
                                  viewProductDomainList.add(viewProductDomain);
                                  viewProductAdapter.notifyDataSetChanged();
                              }

                          }
                        }
                    });
    }*/

}

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        LinearLayout settingBtn = findViewById(R.id.settingbtn);
        //LinearLayout logoutBtn = findViewById(R.id.logoutBtn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyProfileActivity.class));
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AllProductActivity.class));
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
