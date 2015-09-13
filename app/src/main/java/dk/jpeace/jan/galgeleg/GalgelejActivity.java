package dk.jpeace.jan.galgeleg;

import android.app.Activity;
import android.graphics.Color;
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

    Galgelogik galgelogik;

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
    private TextView textViewFinishedStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgelej);

        editviewTest = (TextView)findViewById(R.id.textView);
        textView2Show = (TextView)findViewById(R.id.textViewOrdet);
        textViewDebug = (TextView)findViewById(R.id.textViewDebug);
        textViewSynligtOrd = (TextView)findViewById(R.id.textViewSynligtOrd);
        textViewBrugteBogst = (TextView)findViewById(R.id.textViewBrugteBogst);
        textViewFinishedStatus = (TextView)findViewById(R.id.textViewFinishedStatus);

        editTextBogstav = (EditText)findViewById(R.id.editTextBogstav);
        editTextGætOrdet = (EditText)findViewById(R.id.editTextGætOrdet);

        buttonGæt = (Button) findViewById(R.id.buttonGæt);
        buttonSpil = (Button) findViewById(R.id.buttonSpil);
        buttonGætOrdet = (Button) findViewById(R.id.buttonGætOrdet);
        buttonGæt.setOnClickListener(this);
        buttonSpil.setOnClickListener(this);
        buttonGætOrdet.setOnClickListener(this);
        buttonGæt.setEnabled(false);

        imageView1 =  (ImageView)findViewById(R.id.imageViewet);
        imageView1.setImageResource(R.drawable.forkert6);
        galgelogik = new Galgelogik();
        textViewFinishedStatus.setText("Vælg start spil.");
        textViewFinishedStatus.setTextColor(Color.DKGRAY);

        // disse skal egentlig slet ikke være med...
        buttonGætOrdet.setVisibility(View.INVISIBLE);
        editTextGætOrdet.setVisibility(View.INVISIBLE);
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
        if(gæt.toString().equals(galgelogik.getOrdet()))
            Toast.makeText(this, "Juhuuu - du gættede rigtigt!!!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Neeej det er forkert!!! Ready to hang??? '" + gæt.toString() + "'", Toast.LENGTH_SHORT).show();

    }

    private void BogstavTest(Editable text) {
        String text1 = "Du tastede: " + text;
        //Toast.makeText(this, text1, Toast.LENGTH_SHORT).show();

        textViewDebug.setText(text1);

        if(text.length() != 1)
        {
            imageView1.setImageResource(R.drawable.forkert3);

            Log.d("jj", "det indtastede passer ikke. " + text1);
            Toast.makeText(this, "Fejl: Du skal taste præcis eet bogstav!", Toast.LENGTH_SHORT).show();

        }
        else
        {

            galgelogik.gætBogstav(text.toString());
            String syngligtOrd = galgelogik.getSynligtOrd();
            textViewSynligtOrd.setText(syngligtOrd);

            VisBrugteBogstaver();

            editTextBogstav.setText("");
        }

        OpdaterGalgeBillede();
        SpilStatus();

    }

    private void SpilStatus() {
        if(galgelogik.erSpilletSlut()) {
            if(galgelogik.erSpilletTabt())
            {
                String statusText = "Spillet er tabt!!!";
                textViewFinishedStatus.setText(statusText);
                textViewFinishedStatus.setTextColor(Color.RED);

                Toast.makeText(this, statusText, Toast.LENGTH_LONG).show();
                Log.d("jj", "spillet er tabt!");
                buttonGæt.setEnabled(false);
            }
            else if(galgelogik.erSpilletVundet())
            {
                String statusText = "Spillet er vundet!!!";
                textViewFinishedStatus.setText(statusText);
                textViewFinishedStatus.setTextColor(Color.GREEN);
                Toast.makeText(this, statusText, Toast.LENGTH_LONG).show();
                Log.d("jj", statusText);
                buttonGæt.setEnabled(false);

            }
        }
    }


    private void OpdaterGalgeBillede()
    {
        int antalForkerte = galgelogik.getAntalForkerteBogstaver();
        Log.v("jj", (String.valueOf(antalForkerte)));
        switch (antalForkerte)
        {
            case 0:
                imageView1.setImageResource(R.drawable.galge);
                break;
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
                imageView1.setImageResource(R.drawable.forkert6);
        }
    }

    private void VisBrugteBogstaver() {
        String display = "";
        ArrayList<String> bb = galgelogik.getBrugteBogstaver();
        for (String ii:bb  ) {
           display += ii;
        }
        textViewBrugteBogst.setText(display);
    }

    private void StartSpil() {
        galgelogik.nulstil();   // når spillet starter
        String ordet = galgelogik.getOrdet();
        textView2Show.setText(ordet);
        Toast.makeText(this, "Spillet er startet!!!", Toast.LENGTH_LONG).show();

        imageView1.setImageResource(R.drawable.galge);
        buttonSpil.setText("Nyt spil");
        buttonGæt.setEnabled(true);
        textViewFinishedStatus.setText("Spillet er i gang.");

        textViewBrugteBogst.setText("");
        textViewSynligtOrd.setText(galgelogik.getSynligtOrd());
    }


}