package dk.jpeace.jan.galgeleg;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GalgelejActivity extends Activity implements OnClickListener {

    Galgelogik gl;
    private int guessCounter;
    private int guessMax = 6;

    private Button buttonSpil;
    private TextView editviewTest;
    private ImageView imageView1;
    private Button buttonGæt;
    private EditText editTextBogstav;
    private TextView textView2Show;
    private TextView textViewDebug;
    private TextView textViewSynligtOrd;
    private TextView textViewBrugteBogst;
    private Button buttonGætOrdet;
    private EditText editTextGætOrdet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgelej);

        guessCounter = 0;
        editviewTest = (TextView)findViewById(R.id.textView);
        textView2Show = (TextView)findViewById(R.id.textViewOrdet);
        textViewDebug = (TextView)findViewById(R.id.textViewDebug);
        textViewSynligtOrd = (TextView)findViewById(R.id.textViewSynligtOrd);
        textViewBrugteBogst = (TextView)findViewById(R.id.textViewBrugteBogst);

        editTextBogstav = (EditText)findViewById(R.id.editTextBogstav);
        editTextGætOrdet = (EditText)findViewById(R.id.editTextGætOrdet);

        buttonGæt = (Button) findViewById(R.id.buttonGæt);
        buttonSpil = (Button) findViewById(R.id.buttonSpil);
        buttonGætOrdet = (Button) findViewById(R.id.buttonGætOrdet);
        buttonGæt.setOnClickListener(this);
        buttonSpil.setOnClickListener(this);
        buttonGætOrdet.setOnClickListener(this);

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
            StartSpil();
        if(v==buttonGæt)
            BogstavTest(editTextBogstav.getText());
        if(v==buttonGætOrdet)
            GætOrdet();

    }

    private void GætOrdet() {
        // denne skal egentlig ikke med i den endelige løsning...
        Editable gæt = editTextGætOrdet.getText();
        if(gæt.toString().equals(gl.getOrdet()))
            Toast.makeText(this, "Juhuuu - du gættede rigtigt!!!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Neeej det er forkert!!! Ready to hang??? '" + gæt.toString() + "'", Toast.LENGTH_SHORT).show();

    }

    private void BogstavTest(Editable text) {
        String text1 = "Du tastede: " + text;
        Toast.makeText(this, text1, Toast.LENGTH_SHORT).show();

        textViewDebug.setText(text1);

        if(text.length() != 1)
        {
            imageView1.setImageResource(R.drawable.forkert3);

            Log.d("jj", "det indtastede passer ikke. " + text1);
            Toast.makeText(this, "Fejl: Du skal taste præcis eet bogstav!", Toast.LENGTH_SHORT).show();

        }
        else
        {
            imageView1.setImageResource(R.drawable.forkert5);

            gl.gætBogstav(text.toString());
            String syngligtOrd = gl.getSynligtOrd();
            textViewSynligtOrd.setText(syngligtOrd);

            VisBrugteBogstaver();

            editTextBogstav.setText("");
        }

        OpdaterGalgeBillede();
        SpilStatus();

    }

    private void SpilStatus() {
        if(gl.erSpilletSlut()) {
            if(gl.erSpilletTabt())
            {
                Toast.makeText(this, "Spillet er tabt!!!", Toast.LENGTH_LONG).show();

            }
            else if(gl.erSpilletVundet())
            {
                Toast.makeText(this, "Spillet er vundet!!!", Toast.LENGTH_LONG).show();

            }
        }
    }


    private void OpdaterGalgeBillede()
    {
        int antalForkerte = gl.getAntalForkerteBogstaver();
        Log.v("jj", (String.valueOf(antalForkerte)));
        switch (antalForkerte)
        {
            case 1:
                imageView1.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                imageView1.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                imageView1.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                imageView1.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                imageView1.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                imageView1.setImageResource(R.drawable.forkert6);
                break;
            default:
                imageView1.setImageResource(R.drawable.galge);

        }
        // imageView1.setImageResource(R.drawable.forkert6);

    }

    private void VisBrugteBogstaver() {
        String display = "";
        ArrayList<String> bb = gl.getBrugteBogstaver();
        for (String ii:bb  ) {
           display += ii;
        }
        textViewBrugteBogst.setText(display);
    }

    private void StartSpil() {

        gl.nulstil();   // når spillet starter
        String ordet = gl.getOrdet();
        textView2Show.setText(ordet);
        Toast.makeText(this, "Spillet er startet!!!", Toast.LENGTH_LONG).show();

        buttonSpil.setText("Ny spil");
    }

}
