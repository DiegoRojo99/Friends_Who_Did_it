package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameEt, passwordET, emailET;

    TextView alreadyLoggedInTV;

    private FirebaseAuth mAuth;

    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();

        registerButton=(Button) findViewById(R.id.btn_register_register);
        registerButton.setOnClickListener(view -> registerUser());

        alreadyLoggedInTV=(TextView) findViewById(R.id.tv_register_already_logged_in);
        alreadyLoggedInTV.setOnClickListener(view -> goToLogin());
    }

    public void goToLogin(){
        Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }

    public void registerUser(){

        emailET=(EditText)findViewById(R.id.et_register_email) ;
        passwordET=(EditText)findViewById(R.id.et_register_password);
        usernameEt=(EditText)findViewById(R.id.et_register_username);

        String email=emailET.getText().toString();
        String username=usernameEt.getText().toString();
        String password=passwordET.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Usuario usuario= new Usuario(username, email, password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility(View.VISIBLE);

                                        //Dirigir a login
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "No se ha podido registrar al usuario", Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "No se ha podido registrar al usuario",Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });

        Usuario usuario= new Usuario(username, email, password);
        createUsername(usuario);
        goToLogin();
    }

    public void createUsername(Usuario u){

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        Map<String,Object> userToInsert=new HashMap<>();

        //Sacar userId del user con el mismo email y guardarlo como UserID
        //userToInsert.put("UserId",user.getUid());
        userToInsert.put("Username",u.nombre);
        userToInsert.put("Email",u.email);


        db.collection("usernames")
                .add(userToInsert)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

}