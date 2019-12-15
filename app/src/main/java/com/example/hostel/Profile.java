package com.example.hostel;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hostel.helpers.TableNames;
import com.example.hostel.models.Users;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Profile extends Fragment {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    TextView profileUsername;
    ImageButton Addhome, logoutImgBtn;
    ImageView chooseProfileImgBtn, profileAvatarIV;

    Uri imgUri;

    public static final int PICK_IMAGE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myview1= inflater.inflate(R.layout.profile_frag,container,false);

        Addhome = myview1.findViewById(R.id.imageButton5);
        logoutImgBtn = myview1.findViewById(R.id.imageButton4);
        chooseProfileImgBtn = myview1.findViewById(R.id.chooseProfileImgBtn);
        profileAvatarIV = myview1.findViewById(R.id.profileAvatarIV);

        profileUsername = myview1.findViewById(R.id.profileUsername);

        Addhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getActivity(),HomeDetails.class));
            }
        });
        logoutImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();

                getContext().startActivity(new Intent(getContext(), SignupActivity.class));
            }
        });

        firestore.collection(TableNames.USERS).document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            Users user = task.getResult().toObject(Users.class);

                            profileUsername.setText(user.getUser_name());

                            if (user.getUserAvatar() != null) {
                                Picasso.get().load(user.getUserAvatar()).into(profileAvatarIV);
                            }
                        }
                    }
                });


        chooseProfileImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileSelector();
            }
        });

        return myview1;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("user_profile_images");
    }


    public String getFileExtension(Uri uri) {
        ContentResolver cr = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


    public void openFileSelector() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            imgUri = data.getData();

            final String LOGGED_IN_UID = firebaseAuth.getCurrentUser().getUid();

            final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imgUri));

            fileRef.putFile(imgUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) throw task.getException();
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        final Uri downloadedUri = task.getResult();

                        firestore.collection(TableNames.USERS)
                                .document(LOGGED_IN_UID)
                                .update(Users.USER_AVATAR, downloadedUri.toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Picasso.get().load(imgUri).into(profileAvatarIV);
                                        }
                                    }
                                });
                    }
                }
            });

        }
    }
}
