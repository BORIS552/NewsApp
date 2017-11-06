package com.example.boris.worldexplorer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogoutActivity extends AppCompatActivity {
    private TextView textEmail,textName;
    private Button btnLogout;
    private FirebaseAuth auth;
    private   FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        btnLogout = (Button)findViewById(R.id.logout);
        textEmail = (TextView)findViewById(R.id.userEmail);
        textName = (TextView)findViewById(R.id.username);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
        String name = user.getUid();
        textEmail.setText(email);
        textName.setText(name);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                System.out.println("--------Logged out---------");
                Toast.makeText(getApplicationContext(),"Logout Successful",Toast.LENGTH_SHORT).show();
                System.out.println("logged out");
                startActivity(new Intent(LogoutActivity.this,LoginActivity.class));
                authStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if(user == null){
                            startActivity(new Intent(LogoutActivity.this,LoginActivity.class));
                        }
                    }
                };
            }
        });
    }
}
