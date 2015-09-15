package dk.jpeace.jan.galgeleg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ForsideActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStartSpil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forside);

        buttonStartSpil = (Button)findViewById(R.id.buttonStartSpil);
        buttonStartSpil.setOnClickListener(this);
        //buttonGæt.setOnClickListener(this);

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
        if(v == buttonStartSpil)
            startSpil();
    }

    private void startSpil() {
        Intent startSpilGalgelegIntent = new Intent(getApplicationContext(), GalgelejActivity.class);
        //startSpilGalgelegIntent.setAction(Intent.ACTION_SEND);
        startActivity(startSpilGalgelegIntent);

    }
}
