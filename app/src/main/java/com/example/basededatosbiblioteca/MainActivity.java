package com.example.basededatosbiblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txt_isbn, txt_titulo, txt_author, txt_editorial, txt_paginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_isbn =(EditText)findViewById(R.id.txtIsbn);
        txt_titulo = (EditText)findViewById(R.id.txtTitulo);
        txt_author = (EditText)findViewById(R.id.txtAuthor);
        txt_editorial = (EditText)findViewById(R.id.txtEditorial);
        txt_paginas = (EditText)findViewById(R.id.txtPaginas);
    }

    //Creamos el método para dar de alta los libros
    public void Registrar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biblioteca",  null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String isbn = txt_isbn.getText().toString();
        String titulo = txt_titulo.getText().toString();
        String author = txt_author.getText().toString();
        String editorial = txt_editorial.getText().toString();
        String paginas = txt_paginas.getText().toString();

        if (!isbn.isEmpty() && !titulo.isEmpty() && !author.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("isbn", isbn);
            registro.put("titulo", titulo);
            registro.put("author", author);
            registro.put("ediorial", editorial);
            registro.put("paginas", paginas);


            BaseDeDatos.insert("libros", null, registro);

            //Cerramos la base de datos
            BaseDeDatos.close();

            txt_isbn.setText("");
            txt_titulo.setText("");
            txt_author.setText("");
            txt_editorial.setText("");
            txt_paginas.setText("");

            Toast.makeText(this, "El libro se ha grabado correctamente", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Debes rellenar los tres primeros campos", Toast.LENGTH_SHORT).show();
        }
    }
}
