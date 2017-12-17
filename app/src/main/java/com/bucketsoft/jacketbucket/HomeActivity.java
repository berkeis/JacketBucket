package com.bucketsoft.jacketbucket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {


    private ChildEventListener mChildEventListener;
    protected MessageAdapter mBooksAdapter;
    private ListView mBooksListView;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mBooksDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button shareBtn = (Button) findViewById(R.id.shareBtn);
        Button findBtn = (Button) findViewById(R.id.findBtn);
        mBooksListView = (ListView) findViewById(R.id.booksListView);

        // Initialize message ListView and its adapter
        List<Books> books = new ArrayList<>();
        mBooksAdapter = new MessageAdapter(this, R.layout.item_message, books);
        mBooksListView.setAdapter(mBooksAdapter);

        //Firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mBooksDatabaseReference = mFirebaseDatabase.getReference().child("Books");


        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddItemActivity.class);
                startActivity(intent);

            }
        });

        attachDatabaseReadListener();

    }


    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Books books= dataSnapshot.getValue(Books.class);
                    mBooksAdapter.add(books);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            mBooksDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }





}
