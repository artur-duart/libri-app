package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    /**
     * ATRIBUTOS DA CLASSE DE CONNECTION
     **/
    private static final String DB_NAME = "libri";
    private static final int DB_VERSION = 1;
    private static SQLHelper INSTANCE;

    /**
     * Método de verificar se a conexão está aberta
     **/
    public static SQLHelper getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new SQLHelper(context);
        }

        return INSTANCE;
    }

    /**
     * Método construtor: Recebe os valores iniciais de abertura de conexão
     **/
    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE tbl_usuario" +
                "(cod_usuario INTEGER PRIMARY KEY," +
                "nome TEXT," +
                "sobrenome TEXT," +
                "email TEXT," +
                "login TEXT," +
                "senha TEXT," +
                "created_date DATETIME)");

        Log.d("SQLITE-", "BANCO DE DADOS CRIADO! - " + DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(String nome, String sobrenome, String email, String login, String senha, String created_date) {

        //Configura o SQLITE para escrita:
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("nome", nome);
            values.put("sobrenome", sobrenome);
            values.put("email", email);
            values.put("login", login);
            values.put("senha", senha);
            values.put("created_date", created_date);

            sqLiteDatabase.insertOrThrow("tbl_usuario", null, values);
            sqLiteDatabase.setTransactionSuccessful();

        } catch (Exception e) {
            Log.d("SQLITE-", e.getMessage());
            return false;

        } finally {
            if (sqLiteDatabase.isOpen()){
                sqLiteDatabase.endTransaction();
            }


        }
    }
}
