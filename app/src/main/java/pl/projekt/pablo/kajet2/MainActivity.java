package pl.projekt.pablo.kajet2;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    private DatabaseHandler db;
    private static final int DIALOG_ALERT = 10;

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

    public void deleteAll(View v) {
        Intent intent = new Intent(this, DeleteAllActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // informacja o danych zwiazanych z kliknieciem (id jest zwiazane z nasza baza)
        Log.i("BAZA", "Pozycja: " + position + " ID: " + id);

        Intent in = new Intent(this, EditActivity.class);
        in.putExtra("PID", id);
        startActivity(in);

    }

    // metoda uzupelnia nasza liste danymi pobranymi z bazy
    private void fillData() {

        Cursor c = db.fetchAllNotes();
        startManagingCursor(c);

        String[] from = new String[] { DatabaseHandler.KEY_TITLE,  DatabaseHandler.KEY_BODY};
        int[] to = new int[] { R.id.noteTitle, R.id.noteInfo };

        // Uzupelniamy liste wartosciami ttttttttttttttttetst
        SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.list_item, c, from, to);
        setListAdapter(notes);

    }


}
