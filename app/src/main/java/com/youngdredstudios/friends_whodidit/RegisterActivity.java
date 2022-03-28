package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameEt, passwordET, emailET;

    private FirebaseAuth mAuth;

    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();

        registerButton=(Button) findViewById(R.id.btn_register_register);
        registerButton.setOnClickListener(view -> registerUser());
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

        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }
}