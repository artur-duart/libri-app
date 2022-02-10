package com.example.libri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;

public class SignUpActivity extends AppCompatActivity {

    /**
     * REPRESENTAÇÃO DOS CAMPOS DA ACTIVITY
     **/
    private EditText etNameSignUp;
    private EditText etSurnameSignUp;
    private EditText etUserSignUp;
    private EditText etEmailSignUp;
    private EditText etPasswordSignUp;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /** CAPTURA DOS COMPONENTES GRÁFICOS DA ACTIVITY **/
        etNameSignUp = findViewById(R.id.etNameSignUp);
        etSurnameSignUp = findViewById(R.id.etSurnameSignUp);
        etUserSignUp = findViewById(R.id.etUserSignUp);
        etEmailSignUp = findViewById(R.id.etEmailSignUp);
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);

        /** TRATAMENTO DO EVENTO DE CLICK DO BOTÃO **/
        btnSignUp.setOnClickListener(view -> { // O corpo do Lambda trata a ação que será qxecutada após o clique

            if (!validate()) {
                Toast.makeText(this, "PREENCHA TODOS OS CAMPOS!", Toast.LENGTH_LONG).show();
                return;
            }

            /** PROCESSO DE GRAVAÇÃO DO USUÁRIO **/
            AlertDialog dialog = new AlertDialog.Builder(this)

                    .setTitle(getString(R.string.title_sign_up))
                    .setMessage(getString(R.string.message_sign_up))

                    .setPositiveButton(R.string.save, (dialog1, which) -> {

                        /** ACÃO DO POSITIVE BUTTON **/
                        String name = etNameSignUp.getText().toString();
                        String surname = etSurnameSignUp.getText().toString();
                        String email = etEmailSignUp.getText().toString();
                        String user = etUserSignUp.getText().toString();
                        String password = etPasswordSignUp.getText().toString();

                        boolean userSignup = SQLHelper.getInstance(SignUpActivity.this).addUser(name, surname, email, user, password, "03/02/2022 00:00:00");

                        if (userSignup) {

                            Toast.makeText(this, "Usuário Cadastrado", Toast.LENGTH_LONG).show();

                        }

                    })

                    .setNegativeButton(R.string.cancel, (dialog1, which) -> {}).create();

            dialog.show();

        });

        findViewById(R.id.textSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.imageBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    } // Final do Método Oncreate

    /**
     * MÉTODO DE VALIDAÇÃO
     **/
    private boolean validate() {

        return (
                !etNameSignUp.getText().toString().isEmpty() &&
                !etSurnameSignUp.getText().toString().isEmpty() &&
                !etUserSignUp.getText().toString().isEmpty() &&
                !etEmailSignUp.getText().toString().isEmpty() &&
                !etPasswordSignUp.getText().toString().isEmpty()
        );

    }

} // Final da Classe