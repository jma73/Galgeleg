package dk.jpeace.jan.galgeleg;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GalgelejActivity extends Activity implements OnClickListener {

    Galgelogik gl;

    private Button buttonSpil;
    private TextView editviewTest;
    private ImageView imageView1;
    private Button buttonGæt;
    private EditText editTextBogstav;
    private TextView textView2Show;
    private TextView textViewDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgelej);

        editviewTest = (TextView)findViewById(R.id.textView);
        textView2Show = (TextView)findViewById(R.id.textView2);
        textViewDebug = (TextView)findViewById(R.id.textViewDebug);
        editTextBogstav = (EditText)findViewById(R.id.editTextBogstav);
        buttonGæt = (Button) findViewById(R.id.buttonGæt);
        buttonSpil = (Button) findViewById(R.id.buttonSpil);
        buttonGæt.setOnClickListener(this);
        buttonSpil.setOnClickListener(this);

        imageView1 =  (ImageView)findViewById(R.id.imageViewet);
        imageView1.setImageResource(R.drawable.forkert6);
        gl = new Galgelogik();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_galgelej, menu);
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

    @Override
    public void onClick(View v) {
        if(v== buttonSpil)
            TestGalgelegLogik();
        if(v==buttonGæt)
            BogstavTest(editTextBogstav.getText());
    }

    private void BogstavTest(Editable text) {
        String text1 = "Du tastede: " + text;
        Toast.makeText(this, text1, Toast.LENGTH_SHORT);

        textViewDebug.setText(text1);

        if(text.length() != 1)
        {
            Toast.makeText(this, "Du skal taste eet bogstav!", Toast.LENGTH_SHORT);

        }
        else
        {
            gl.gætBogstav(text.toString());
        }
    }

    private void TestGalgelegLogik() {


        gl.nulstil();   // når spillet starter


        String ordet = gl.getOrdet();

        textView2Show.setText(ordet);

    }

}
