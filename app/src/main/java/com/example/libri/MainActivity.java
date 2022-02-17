package com.example.libri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import database.SQLHelper;
import helpers.Login;

public class MainActivity extends AppCompatActivity {

    private EditText etEmailLogin;
    private EditText etPasswordLogin;
    private Button btnSignIn;
    private TextView tvCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        btnSignIn.setOnClickListener(view -> {

            String email = etEmailLogin.getText().toString();
            String senha = etPasswordLogin.getText().toString();

            int cod_usuario = SQLHelper.getInstance(this).login(email, senha);

            if (cod_usuario > 0) {

                Login.setCod_usuario(cod_usuario);

                startActivity(new Intent(MainActivity.this, BooksFeedActivity.class));

            } else {
                Toast.makeText(this, "Usuário ou Senha Incorretos", Toast.LENGTH_LONG).show();
            }

        });
    }
}