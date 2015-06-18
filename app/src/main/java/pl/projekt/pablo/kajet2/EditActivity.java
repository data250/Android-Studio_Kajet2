package pl.projekt.pablo.kajet2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EditActivity extends ActionBarActivity {

    public long idEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);


        Intent i = getIntent();
        long id;
        id = i.getLongExtra("PID", 0);
        idEdit = id;
        DatabaseHandler db = new DatabaseHandler(this);
        Notatka note = new Notatka();
        note = db.getNote(id);
        EditText tytul = (EditText) findViewById(R.id.title);
        EditText tresc = (EditText) findViewById(R.id.body);
        tytul.setText(note.getTytul());
        tresc.setText(note.getTresc());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void insertNote(View v) {
        DatabaseHandler db = new DatabaseHandler(this);

        EditText editText = (EditText) findViewById(R.id.title);
        String title = editText.getText().toString();

        editText = (EditText) findViewById(R.id.body);
        String body = editText.getText().toString();

        Notatka note = new Notatka(title, body);
        db.addNote(note);
        finish();
    }


    public void upDate(View v) {
        DatabaseHandler db = new DatabaseHandler(this);

        EditText editText = (EditText) findViewById(R.id.title);
        String title = editText.getText().toString();

        editText = (EditText) findViewById(R.id.body);
        String body = editText.getText().toString();

        Notatka note = new Notatka(idEdit ,title, body);
        db.updateNote(note);
        db.close();
        finish();

    }}
