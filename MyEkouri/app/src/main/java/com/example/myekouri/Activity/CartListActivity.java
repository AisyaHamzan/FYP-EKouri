package com.example.myekouri.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myekouri.Adapter.CartListAdapter;
import com.example.myekouri.Domain.CartListDomain;
import com.example.myekouri.Helper.ManagementCart;
import com.example.myekouri.Interface.ChangeNumberItemsListener;
import com.example.myekouri.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartListActivity extends AppCompatActivity {

   // private RecyclerView.Adapter adapter;
   // private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt,chargeTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    FirebaseFirestore mStore;
    FirebaseAuth mAuth;

    RecyclerView recyclerViewList;
    CartListAdapter cartListAdapter;
    List<CartListDomain> cartListDomainList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        recyclerViewList = findViewById(R.id.cartView);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));

        cartListDomainList = new ArrayList<>();
        cartListAdapter = new CartListAdapter(this,cartListDomainList);
        recyclerViewList.setAdapter(cartListAdapter);

        LocalBroadcastManager.getInstance(this).registerReceiver(eMessageReceiver,new IntentFilter("MyTotalPrice"));

        mStore.collection("Cart").document(mAuth.getCurrentUser().getUid())
               .collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                String documentId = documentSnapshot.getId();

                                CartListDomain cartListDomain = documentSnapshot.toObject(CartListDomain.class);

                                cartListDomain.setDocumentId(documentId);

                                cartListDomainList.add(cartListDomain);
                                cartListAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        managementCart = new ManagementCart(this);


        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    public BroadcastReceiver eMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalPrice",0);
            totalFeeTxt.setText("RM " +totalBill);
            }
    };

    private void CalculateCart() {
        double delivery = 10;


        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("RM" + itemTotal);
        chargeTxt.setText("RM" + delivery);
        totalTxt.setText("RM" + total);
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.catRecView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        chargeTxt = findViewById(R.id.chargeTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
       /* adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }*/

    }
}
