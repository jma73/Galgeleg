package dk.jpeace.jan.galgeleg;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Calendar;
//import java.util.Date;

public class ForsideActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStartSpil;
    private Button buttonHentOrdWeb;
    public static final Galgelogik galgelogik = new Galgelogik();
    private TextView textViewDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forside);

        Log.d("ForsideActivity", "in onCreate! Så vi igen");

        setContentView(R.layout.activity_forside2);
        //setContentView(R.layout.fragment_forside);

        Log.d("ForsideActivity", "in onCreate! vi er videre...");

//        SimpleDateFormat  df = new SimpleDateFormat("dd MM yyyy, HH:mm");
//        String date = df.format(Calendar.getInstance().getTime());
//        textViewDateTime = (TextView)findViewById(R.id.textViewDateTime);
//        String version = "lek4.0";
//        textViewDateTime.setText(version + ", " + date);
//
//
            buttonStartSpil = (Button)findViewById(R.id.buttonStartSpil);
            buttonStartSpil.setOnClickListener(this);

//        buttonHentOrdWeb = (Button)findViewById(R.id.buttonHentOrdWeb);
//        buttonHentOrdWeb.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forside, menu);
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
        Log.d("ForsideActivity", "in onClick!");
        if(v == buttonStartSpil)
            startSpilFragmet();
            // startSpilAct();
        if(v == buttonHentOrdWeb)
        {
            //String muligeord = hentOrdFraWeb();
            hentOrdFraWeb();

        }
    }

    private void startSpilFragmet() {

        FragmentSpillet fragmentSpillet = new FragmentSpillet();
        Bundle argumenter = new Bundle(); // Overfør data til fragmentet
        argumenter.putString("velkomst", "\n\nGod fornøjelse med Galgeleg!");
        fragmentSpillet.setArguments(argumenter);

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentindhold, fragmentSpillet)
                // .addToBackStack(null)
                .commit();
    }

    private void hentOrdFraWeb() {

        //String titler;

        // Lint: Warning:(97, 29) Unchecked call to 'execute(Params...)' as a member of raw type 'android.os.AsyncTask'
        AsyncTask execute = new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    //String rssdata = hentUrl("http://www.version2.dk/it-nyheder/rss");
                    //String titler = findTitler(rssdata);
                    galgelogik.hentOrdFraDr();
                    String titler = galgelogik.muligeOrd().toString();

                    return titler;
                } catch (Exception e) {
                    e.printStackTrace();
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object titler) {
                Toast.makeText(ForsideActivity.this, "resultat: \n" + titler, Toast.LENGTH_SHORT).show();
                setProgressBarIndeterminateVisibility(false);
            }
        }.execute();


//     catch (Exception e) {
//        e.printStackTrace();
//        textView.setText("Der skete en fejl:\n" + e);
//        setProgressBarIndeterminateVisibility(false);
//        }

    }

    private void startSpilAct() {
        Intent startSpilGalgelegIntent = new Intent(getApplicationContext(), GalgelejActivity.class);
        //startSpilGalgelegIntent.setAction(Intent.ACTION_SEND);
        startActivity(startSpilGalgelegIntent);

    }
}
