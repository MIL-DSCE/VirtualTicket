package com.example.supriya.virtualticket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText nn, em, pn, pw;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nn = (EditText) findViewById(R.id.et_nn);
        em = (EditText) findViewById(R.id.et_em);
        pn = (EditText) findViewById(R.id.et_pn);
        pw = (EditText) findViewById(R.id.et_pw);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d("sm", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {

                    Log.d("sm", "onAuthStateChanged:signed_out");
                }

            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    public void OnSubmit(View v) {

        String T1, T2, T3, T4;
        T1 = nn.getText().toString();
        T2 = em.getText().toString();
        T3 = pn.getText().toString();
        T4 = pw.getText().toString();

        mAuth.createUserWithEmailAndPassword(T2,T4)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("sm", "createUserWithEmail:onComplete:" + task.isSuccessful());


                        if (task.isSuccessful()) {
                            Intent I = new Intent(Register.this, transaction.class);
                            startActivity(I);
                            finish();
                        }

                    }
                });

    }

}

