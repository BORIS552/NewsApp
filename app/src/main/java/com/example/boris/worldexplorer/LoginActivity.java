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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements AnimationListener {
    private EditText inputEmail, inputPassword;
    private Button btnLogin, btnsignUp;
    private FirebaseAuth auth;
    private ProgressBar pbar;
    private Animation animBlink,animShake;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        inputEmail = (EditText)findViewById(R.id.emaillogin);
        inputPassword = (EditText)findViewById(R.id.passwordlogin);
        btnLogin = (Button)findViewById(R.id.login);
        btnsignUp = (Button)findViewById(R.id.signupSec);
        pbar = (ProgressBar)findViewById(R.id.progressBar2);
        cardView = (CardView)findViewById(R.id.cardviewLogin);

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        animBlink.setAnimationListener(this);
        animShake = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        animShake.setAnimationListener(this);

        auth = FirebaseAuth.getInstance();
        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SignupActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter the email Address!",Toast.LENGTH_SHORT).show();
                    inputEmail.startAnimation(animBlink);
                    cardView.startAnimation(animShake);
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter your password!",Toast.LENGTH_SHORT).show();
                    inputPassword.startAnimation(animBlink);
                    cardView.startAnimation(animShake);
                    return;
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                pbar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pbar.setVisibility(View.GONE);
                                if(!task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"There was an Error Occured while signing in!Try Again",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Successfully logged In!",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
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
}
