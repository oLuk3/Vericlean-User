  package io.agora.openlive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.agora.openlive.api.AbstractAPIListener;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//Login
        final EditText emailField = findViewById(R.id.emailText);
        final EditText passwordField = findViewById(R.id.passwordText);
        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                final Model model = Model.getInstance(Login.this.getApplication());
                model.login(email, password, new AbstractAPIListener(){
                    @Override
                    public void onLogin(User user){
                        if(user != null) {
                            model.setUser(user);
                            Intent intent = new Intent(Login.this, Dashboard.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Login Successful!",Toast.LENGTH_SHORT).show();
                            //Toast.makeText(Login.this, "User:" + user.getName()+ " Token: "+user.getToken(),Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(Login.this, "Invalid Credentials",Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(Login.this, "User:" + user.getName()+ " Token: "+user.getToken(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    }
