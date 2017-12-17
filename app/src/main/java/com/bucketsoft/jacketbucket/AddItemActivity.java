package com.bucketsoft.jacketbucket;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AddItemActivity extends HomeActivity {

    private static final int RC_PHOTO_PICKER = 2;

    private ToggleButton kkButton;
    private ImageButton mPhotoPickerButton;
    private Button addButton;
    private EditText author;
    private EditText bookName;
    private Spinner locations;            //TODO: Multiline spinner yaptıktan sonra ArrayList<String>'e çevir.
    private Uri downloadUrl;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mJacketsDatabaseReference;
    private DatabaseReference mBooksDatabaseReference;

    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mPhotosStorageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

        mJacketsDatabaseReference = mFirebaseDatabase.getReference().child("Jackets");
        mBooksDatabaseReference = mFirebaseDatabase.getReference().child("Books");
        mPhotosStorageReference = mFirebaseStorage.getReference().child("photos");


        // Initialize references to views
        kkButton = (ToggleButton) findViewById(R.id.kkButton);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        addButton = (Button) findViewById(R.id.addButton);
        author = (EditText) findViewById(R.id.authorTxt);
        bookName = (EditText) findViewById(R.id.bookNameTxt);
        locations = (Spinner) findViewById(R.id.placesSpinner);


        //Set visible some views ( Kitap/Kıyafet formları)
        kkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });



        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
            }
        });


        //Add the item to the library/wardrobe
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Books book = new Books(author.getText().toString(), bookName.getText().toString() , downloadUrl.toString(), locations.getSelectedItem().toString());
                mBooksDatabaseReference.push().setValue(book);
                Intent intent = new Intent(AddItemActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();

            // Get a reference to store file at chat_photos/<FILENAME>
            StorageReference photoRef = mPhotosStorageReference.child(selectedImageUri.getLastPathSegment());

            photoRef.putFile(selectedImageUri)
                    .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // When the image has successfully uploaded, we get its download URL
                            downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                    });
        }
    }




}
