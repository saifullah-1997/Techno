package com.example.techno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.techno.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Login extends AppCompatActivity {
    private EditText emailET;
    private EditText passwordET;
    private Button signInUserBtn;
    private ProgressBar objectProgressBar;
    private FirebaseAuth objectFirebaseAuth;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        connectXMLToJava();
        objectFirebaseAuth=FirebaseAuth.getInstance();
    }

    private void connectXMLToJava()
    {
        try
        {
            emailET=findViewById(R.id.editText);
            passwordET=findViewById(R.id.editText2);
            register=findViewById(R.id.btn2);
            signInUserBtn=findViewById(R.id.btn);
            objectProgressBar=findViewById(R.id.signInUserProgressBar);

            signInUserBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signInUser();
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "connectXMLToJava:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void signInUser()
    {
        try
        {
            if(!emailET.getText().toString().isEmpty() && !passwordET.getText().toString().isEmpty())
            {
                if(objectFirebaseAuth!=null)
                {
                    if(objectFirebaseAuth.getCurrentUser()!=null)
                    {
                        objectFirebaseAuth.signOut();
                        Toast.makeText(this, "Sign Out Successfully", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        objectProgressBar.setVisibility(View.VISIBLE);
                        signInUserBtn.setEnabled(false);

                        objectFirebaseAuth.signInWithEmailAndPassword(emailET.getText().toString(),
                                passwordET.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(Login.this, "User sign in successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this,Splash_Screen.class));

                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        objectProgressBar.setVisibility(View.INVISIBLE);
                                        signInUserBtn.setEnabled(true);

                                        Toast.makeText(Login.this, "Fails to sign in user:"+
                                                e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            }
            else if(emailET.getText().toString().isEmpty())
            {
                emailET.setError(getString(R.string.input_error_name));
                emailET.requestFocus();
            }
            else if(passwordET.getText().toString().isEmpty())
            {
                passwordET.setError(getString(R.string.input_error_password));
                passwordET.requestFocus();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "signInUser:"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
