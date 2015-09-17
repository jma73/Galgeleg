package dk.jpeace.jan.galgeleg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SpilSlutActivity extends AppCompatActivity {

    private TextView textViewSpilSlut;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spilslut);

        Intent intent = getIntent();
        String message = intent.getStringExtra(GalgelejActivity.EXTRA_MESSAGE);
        String spilStatus = intent.getStringExtra(GalgelejActivity.ER_SPIL_VUNDET);

        imageView1 =  (ImageView)findViewById(R.id.imageViewSpilSlut    );
        if(spilStatus.equals("false"))
            imageView1.setImageResource(R.drawable.forkert6);
        else
            imageView1.setImageResource(R.drawable.galge);

        textViewSpilSlut = (TextView) findViewById(R.id.textViewSpilSlut);
        textViewSpilSlut.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spilslut, menu);
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
}