package pl.projekt.pablo.kajet2;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends ListActivity {

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // nowy obiekt - obs³uga bazy danych
        db = new DatabaseHandler(this);

        // wywo³anie metody uzupe³niaj¹cej listê
        fillData();
    }

    public void onResume() {
        super.onResume();
        fillData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void addNote(View v) {
        // wywo³anie aktywnoœci
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // informacja o danych zwi¹zanych z klikniêciem (id jest zwi¹zane z nasz¹ baz¹)
        Log.i("BAZA", "Pozycja: " + position + " ID: " + id);

    }

    // metoda uzupe³nia nasz¹ listê danymi pobranymi z bazy
    private void fillData() {

        Cursor c = db.fetchAllNotes();
        startManagingCursor(c);

        String[] from = new String[] { DatabaseHandler.KEY_TITLE,  DatabaseHandler.KEY_BODY};
        int[] to = new int[] { R.id.noteTitle, R.id.noteInfo };

        // Uzupe³niamy listê wartoœciami
        SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.list_item, c, from, to);
        setListAdapter(notes);

    }
}
