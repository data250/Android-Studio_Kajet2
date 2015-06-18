package pl.projekt.pablo.kajet2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }

    public void insertNote(View v) {
        DatabaseHandler db = new DatabaseHandler(this);

        EditText editText = (EditText) findViewById(R.id.title);
        String title = editText.getText().toString();

        editText = (EditText) findViewById(R.id.body);
        String body = editText.getText().toString();

        SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        String date = simpleDateHere.format(new Date());



        Notatka note = new Notatka(title, body, date);
        db.addNote(note);
        finish();
    }

}
