package com.youngdredstudios.friends_whodidit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView registrar, passwordOlvidada;
    private EditText etEmail, etPassword;
    private Button loginButton;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseUser usuario;


    ActionCodeSettings actionCodeSettings =
            ActionCodeSettings.newBuilder()
                    // URL you want to redirect back to. The domain (www.example.com) for this
                    // URL must be whitelisted in the Firebase Console.
                    .setUrl("https://www.example.com/finishSignUp?cartId=1234")
                    // This must be true
                    .setHandleCodeInApp(true)
                    .setIOSBundleId("com.example.ios")
                    .setAndroidPackageName(
                            "com.example.android",
                            true, /* installIfNotAvailable */
                            "12"    /* minimumVersion */)
                    .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registrar=(TextView) findViewById(R.id.tv_login_registrar);
        registrar.setOnClickListener(this);

        loginButton=(Button) findViewById(R.id.btn_login_login);
        loginButton.setOnClickListener(this);

        etEmail=(EditText) findViewById(R.id.et_login_email);
        etPassword=(EditText) findViewById(R.id.et_login_password);

        progressBar=(ProgressBar) findViewById(R.id.progressBarLogin);

        passwordOlvidada=(TextView)findViewById(R.id.tv_login_password_olvidada);
        passwordOlvidada.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        checkIfUserIsLoggedIn();
    }

    @Override
    public void onClick(View v) {

        if(v==registrar){
            startActivity(new Intent(this,RegisterActivity.class));
        }else if(v==loginButton){
            loginUser();
        }else if(v==passwordOlvidada){
            //startActivity(new Intent(this, PasswordOlvidadaActivity.class));
        }
    }

    public void checkIfUserIsLoggedIn(){

        try {
            usuario=FirebaseAuth.getInstance().getCurrentUser();
            if(usuario.isEmailVerified()){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }catch (Exception e){

        }
    }

    public void loginUser(){
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError("An e-mail is requires");
            etEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("The e-mail has to be valid");
            etEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            etPassword.setError("A password is required");
            etPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            etPassword.setError("Password has to be at least 6 characters long");
            etPassword.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            if(user.isEmailVerified()){
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }else{
                                user.sendEmailVerification();
                                Toast.makeText(LoginActivity.this, "Check your email for verifying your account",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Could not log in, Check your credentials",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}