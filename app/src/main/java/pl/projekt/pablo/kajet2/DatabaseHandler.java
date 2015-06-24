package pl.projekt.pablo.kajet2;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    /*
    stałe do bazy
    wersja bazy
    */
    private static final int DATABASE_VERSION = 3;

    // nazwa bazy
    private static final String DATABASE_NAME = "bazaNotatek";

    // tabela z notatkami
    private static final String TABLE_NOTES = "notatki";

    // nazwy kolumn naszej tabeli
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "tytul";
    public static final String KEY_BODY = "tresc";
    public static final String KEY_DATE = "date";
    public static final String KEY_PRIOR = "prior";

    // referencja do bazy
    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    // tworzenie tabeli
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_BODY + " TEXT," + KEY_DATE + " TEXT," + KEY_PRIOR + " TEXT " + ")";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    // aktualizacja bazy
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // usuni&#x119;cie tabeli z notatkami
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);

        // ponowne utworzenie tabeli
        onCreate(db);
    }

    // dodanie notatki
    public void addNote(Notatka notatka) {

        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, notatka.getTytul()); // tytu� notatki
        values.put(KEY_BODY, notatka.getTresc());  // tre�� notatki
        values.put(KEY_DATE, notatka.getDate());
        values.put(KEY_PRIOR, notatka.getPrior());

        // wstawienie notatki do bazy
        db.insert(TABLE_NOTES, null, values);
        db.close();


    }

    // pobranie pojedynczej notatki
    public Notatka getNote(long id) {

        // zamiast new String[] { KEY_ID, KEY_TITLE, KEY_BODY }
        // mo�emy u�y� null (wszystkie kolumny)
        Cursor cursor = db.query(TABLE_NOTES, new String[]{KEY_ID, KEY_TITLE, KEY_BODY, KEY_DATE, KEY_PRIOR},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Notatka notatka = new Notatka(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        // zwracamy notatk�
        return notatka;
    }

    // pobranie wszystkich notatek
    public List<Notatka> getAllNotes() {

        List<Notatka> noteList = new ArrayList<Notatka>();

        // zapytanie SQL
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;

        // inny spos�b wywo�ania zapytania
        Cursor cursor = db.rawQuery(selectQuery, null);

        // p�tla przez wszystkie elementy z dodzwaniem ich do listy
        if (cursor.moveToFirst()) {
            do {
                Notatka notatka = new Notatka();
                notatka.setId(Integer.parseInt(cursor.getString(0)));
                notatka.setTytul(cursor.getString(1));
                notatka.setTresc(cursor.getString(2));

                // Adding contact to list
                noteList.add(notatka);

            } while (cursor.moveToNext());
        }

        // return contact list
        return noteList;
    }

    public Cursor fetchAllNotes() {
        // wszystkie notatki w formie obiektu klasy Cursor
        return db.query(TABLE_NOTES, new String[]{KEY_ID, KEY_TITLE, KEY_BODY, KEY_PRIOR}, null, null, null, null, null); //hmmm +null?
    }

    // pobranie liczby notatek w bazie
    public int getNotesCount() {

        String countQuery = "SELECT  * FROM " + TABLE_NOTES;
        Cursor cursor = db.rawQuery(countQuery, null);

        // zwracamy liczb� wierszy
        return cursor.getCount();
    }

    // aktualizacja notatki
    public int updateNote(Notatka notatka) {

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, notatka.getTytul());
        values.put(KEY_BODY, notatka.getTresc());
        values.put(KEY_DATE, notatka.getDate());
        values.put(KEY_PRIOR, notatka.getPrior());

        // aktualizacja wiersza
        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(notatka.getId())});
    }

    // usuni�cie pojedynczej notatki
    public void deleteNote(Notatka notatka) {

        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[]{String.valueOf(notatka.getId())});

    }

    // usuni�cie pojedynczej notatki po ID
    public void deleteNote(long id) {

        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});

    }

    public void deleteAll(){
        db.delete(TABLE_NOTES, null, null);
    }



}