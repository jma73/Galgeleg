package dk.jpeace.jan.galgeleg;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GalgelejActivity extends Activity implements OnClickListener {

    Button buttonTest;
    private TextView editviewTest;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galgelej);

        editviewTest = (TextView)findViewById(R.id.textView);
        buttonTest = (Button) findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(this);

        imageView1 =  (ImageView)findViewById(R.id.imageViewet);
        imageView1.setImageResource(R.drawable.forkert6);

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
        if(v==buttonTest)
            TestGalgelegLogik();
    }

    private void TestGalgelegLogik() {


        Galgelogik gl = new Galgelogik();
        gl.nulstil();   // når spillet starter


        String ordet = gl.getOrdet();

        editviewTest.setText(ordet);

    }

}
