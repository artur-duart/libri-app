package com.example.libri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import model.Book;
import model.Item;

public class BooksFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_feed);
    }

    /**
     * INFLATE DO MENU
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Ações do Menu
     **/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d("MENUITEM- ", String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case R.id.menuBookRegistration:
                startActivity(new Intent(this, BookRegistrationActivity.class));
                break;

            case R.id.menuBookFeed:
                startActivity(new Intent(this, BooksFeedActivity.class));
                break;

            case R.id.menuSignOut:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ADAPTER DO RECYCLERVIEW
     **/
    class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        //ATRIBUTO QUE RECEBE OS OBJETOS DE "ITEMS"
        public List<Item> item;

        //CONSTRUTOR DA CLASSE BookAdapter
        public BookAdapter(List<Item> item) {

            this.item = item;

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            
            if (viewType == 0){
                return new BookAdapter.BookViewHolder();
            }

            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        /**
         * VIEWHOLDER
         **/
        class BookViewHolder extends RecyclerView.ViewHolder {

            private TextView tvBookTitle, tvBookDescription;
            private int cod_livro;

            /**
             * MÉTODO CONSTRUTOR DA VIEWHOLDER
             **/
            public BookViewHolder(@NonNull View itemView) {
                super(itemView);

                tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
                tvBookDescription = itemView.findViewById(R.id.tvBookDescription);
            }

            /**
             * MÉTODO DE SET DE DADOS NAS TEXTVIEWS
             **/
            public void setBookDate(Book book) {

                tvBookTitle.setText(book.getTitle());
                tvBookDescription.setText(book.getDescription());

            }
        }
    }
}