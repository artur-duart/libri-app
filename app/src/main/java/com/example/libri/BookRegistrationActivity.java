package com.example.libri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.SQLHelper;
import helpers.DateFormat;

public class BookRegistrationActivity extends AppCompatActivity {

    private EditText etBookTitleRegister;
    private EditText etBookDescRegister;
    private EditText etBookPhotoRegister;
    private Button btnRegisterBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_registration);

        etBookTitleRegister = findViewById(R.id.etBookTitleRegister);
        etBookDescRegister = findViewById(R.id.etBookDescRegister);
        etBookPhotoRegister = findViewById(R.id.etBookPhotoRegister);
        btnRegisterBook = findViewById(R.id.btnRegisterBook);

        btnRegisterBook.setOnClickListener(view->{

            if (!validate()) {
                Toast.makeText(this, "PREENCHA TODOS OS CAMPOS!", Toast.LENGTH_LONG).show();
                return;
            }

            /** PROCESSO DE GRAVAÇÃO DO USUÁRIO **/
            AlertDialog dialog = new AlertDialog.Builder(this)

                    .setTitle(getString(R.string.title_book_register))
                    .setMessage(getString(R.string.message_book_register))

                    .setPositiveButton(R.string.save, (dialog1, which) -> {

                        /** ACÃO DO POSITIVE BUTTON **/
                        String title = etBookTitleRegister.getText().toString();
                        String desc = etBookDescRegister.getText().toString();
                        String photo = etBookPhotoRegister.getText().toString();

                        /** DATA DE INSERÇÃO DO LIVRO **/
                        DateFormat df = new DateFormat();
                        String created_date = df.getDateFormat();

                        boolean bookRegister = SQLHelper.getInstance(BookRegistrationActivity.this).addBook(1, title, desc, photo, created_date);

                        if (bookRegister) {

                            Toast.makeText(this, "Livro Cadastrado", Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(this, "Erro ao Cadastrar Livro", Toast.LENGTH_LONG).show();
                        }

                    })

                    .setNegativeButton(R.string.cancel, (dialog1, which) -> {}).create();

            dialog.show();

        });

    }// FIM DO MÉTODO ON CREATE

    /**
     * MÉTODO DE VALIDAÇÃO
     **/
    private boolean validate() {

        return (
                !etBookTitleRegister.getText().toString().isEmpty() &&
                !etBookDescRegister.getText().toString().isEmpty() &&
                !etBookPhotoRegister.getText().toString().isEmpty() &&
                !btnRegisterBook.getText().toString().isEmpty()
        );
    }

}// FIM DA CLASSE