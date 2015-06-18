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
import android.widget.TextView;


public class MainActivity extends ListActivity {

    private DatabaseHandler db;
    private int noteTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // nowy obiekt - obsługa bazy danych
        db = new DatabaseHandler(this);

        // wywołanie metody uzupełniającej lisę
        fillData();
    }

    public void onResume() {
        super.onResume();
        fillData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void addNote(View v) {
        // wywołanie aktywności
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // informacja o danych zwiazanych z kliknieciem (id jest zwiazane z nasza baza)
        Log.i("BAZA", "Pozycja: " + position + " ID: " + id);

    }

    // metoda uzupelnia nasza liste danymi pobranymi z bazy
    private void fillData() {

        Cursor c = db.fetchAllNotes();
        startManagingCursor(c);

        String[] from = new String[] { DatabaseHandler.KEY_TITLE,  DatabaseHandler.KEY_BODY, "#32b0e5"};
       // holder.title.setTextColor(Color.parseColor(note.getColor()));
        int[] to = new int[] { R.id.noteTitle, R.id.noteInfo};
        findViewById(R.id.noteTitle).setBackgroundColor(0xFF00FF00);
       // ((TextView)row.findViewById(R.id.noteTitle)).setTextColor(Color.parseColor(getItem(position).getColor()));

        // Uzupelniamy liste wartosciami
        SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.list_item, c, from, to);
       // ((TextView) notes).setTextColor(0xFF00FF00);
        setListAdapter(notes);


    }
}
