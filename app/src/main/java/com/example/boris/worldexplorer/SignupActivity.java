package com.example.boris.worldexplorer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.boris.worldexplorer.model.transaction.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity implements AnimationListener {

    private EditText inputEmail, inputPassword, userName;
    private Button btnSignUp, btnLogin;
    private ProgressBar pbar;
    private FirebaseAuth auth;
    private DatabaseReference databaseUsers;
    private User user;
    private Animation animBlink,animShake;
    private CardView cardView;
    //private FirebaseDatabase mFirebaseInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SignupActivity.this, LogoutActivity.class));
            finish();
        }

        btnSignUp = (Button)findViewById(R.id.signup);
        inputEmail = (EditText)findViewById(R.id.emailsignup);
        inputPassword = (EditText)findViewById(R.id.passwordsignup);
        userName = (EditText)findViewById(R.id.username);
        btnLogin = (Button)findViewById(R.id.loginSec);
        pbar  = (ProgressBar)findViewById(R.id.progressBar);
        cardView = (CardView)findViewById(R.id.cardviewSignup);

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        animBlink.setAnimationListener(this);
        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        animShake.setAnimationListener(this);

        auth = FirebaseAuth.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String username = userName.getText().toString();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(), "Enter your Name!",Toast.LENGTH_SHORT).show();
                    userName.startAnimation(animBlink);
                    cardView.startAnimation(animShake);
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter email Address!",Toast.LENGTH_SHORT).show();
                    inputEmail.startAnimation(animBlink);
                    cardView.startAnimation(animShake);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter password!",Toast.LENGTH_SHORT).show();
                    inputPassword.startAnimation(animBlink);
                    cardView.startAnimation(animShake);
                    return;
                }
                if(password.length() < 6){
                    Toast.makeText(getApplicationContext(), "Weak Password! Enter more than 6 characters",Toast.LENGTH_SHORT).show();
                    return;
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);

                final String id = databaseUsers.push().getKey();

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:"+ task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this,MainActivity.class));
                                pbar.setVisibility(View.GONE);
                                if(!task.isSuccessful()){
                                    Toast.makeText(SignupActivity.this, "Authentication Failed."+task.getException(),Toast.LENGTH_SHORT).show();
                                }else{
                                    user = new User(username,auth.getCurrentUser().toString());
                                    databaseUsers.child(id).setValue(user);
                                    finish();
                                }
                            }
                        });



            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        pbar.setVisibility(View.GONE);
    }
}
