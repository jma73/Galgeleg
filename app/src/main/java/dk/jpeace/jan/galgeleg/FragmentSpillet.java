package dk.jpeace.jan.galgeleg;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSpillet extends Fragment implements View.OnClickListener {

    Date startTime;
    private Button buttonGætBogstav;
    private TextView textViewTest1;
    private TextView TextViewFSordVisning;
    private ImageView imageViewetFS;
    private EditText editTextBogstav;
    private TextView textViewFSheader;
    private TextView textViewBrugteBogst;
    private TextView textViewFSbottom;


    public FragmentSpillet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FragmentSpillet", "in onCreateView! Så vi igang...");

        // Inflate the layout for this fragment
        View rod =  inflater.inflate(R.layout.fragment_spillet_copy, container, false);

        buttonGætBogstav = (Button) rod.findViewById(R.id.buttonGætBogstav);
        buttonGætBogstav.setOnClickListener(this);

        // textViewTest1
        textViewTest1 = (TextView) rod.findViewById(R.id.TextViewFragSpilTest1);
        TextViewFSordVisning = (TextView) rod.findViewById(R.id.TextViewFSordVisning);
        textViewFSheader = (TextView) rod.findViewById(R.id.textViewFSheader);
        textViewBrugteBogst = (TextView) rod.findViewById(R.id.textViewFSbrugteBogstaver);
        textViewFSbottom = (TextView) rod.findViewById(R.id.textViewFSbottom);
        editTextBogstav = (EditText) rod.findViewById(R.id.editTextBogstav);

        imageViewetFS = (ImageView) rod.findViewById(R.id.imageViewetFS);


        StartSpil();        // pt. bare til test...

        return rod;
    }


    @Override
    public void onClick(View view) {
        Log.d("FragmentSpillet", "in onClick!");

        if(view == buttonGætBogstav)
        {
            ForsideActivity.galgelogik.getOrdet();
            textViewTest1.setText("så vi igang - with Fragmentzzzz");

            BogstavTest(editTextBogstav.getText());
        }

    }

    private void BogstavTest(Editable text) {
        String text1 = "Du tastede: " + text;
        //Toast.makeText(this, text1, Toast.LENGTH_SHORT).show();

        //textViewDebug.setText(text1);

        if(text.length() != 1)
        {
            imageViewetFS.setImageResource(R.drawable.forkert3);

            Log.d("jj", "det indtastede passer ikke. " + text1);
//            Toast.makeText(FragmentSpillet.this, "Fejl: Du skal taste præcis eet bogstav!", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

        }
        else
        {

            ForsideActivity.galgelogik.gætBogstav(text.toString());
            String syngligtOrd = ForsideActivity.galgelogik.getSynligtOrd();
            TextViewFSordVisning.setText(syngligtOrd);

            VisBrugteBogstaver();

            editTextBogstav.setText("");
        }

        OpdaterGalgeBillede();
        SpilStatus();

    }

    private void VisBrugteBogstaver() {
        String display = "";
        ArrayList<String> bb = ForsideActivity.galgelogik.getBrugteBogstaver();
        for (String ii:bb  ) {
            display += ii;
        }
        textViewBrugteBogst.setText(display);
    }

    private void SpilStatus() {

        Date spilSlut = new Date();
        long spilleTid = GalgelejActivity.getDateDiff(startTime, spilSlut, TimeUnit.SECONDS);


        if(ForsideActivity.galgelogik.erSpilletSlut()) {


//            Intent spilletErSlutIntent = new Intent(getApplicationContext(), SpilSlutActivity.class);

            if(ForsideActivity.galgelogik.erSpilletTabt())
            {
                String statusText = "Spillet er tabt!!!";
                textViewTest1.setText(statusText);
                textViewTest1.setTextColor(Color.RED);

                Toast.makeText(getActivity(), statusText, Toast.LENGTH_LONG).show();
                Log.d("jj", "spillet er tabt!");
                buttonGætBogstav.setEnabled(false);

//                spilletErSlutIntent.putExtra(EXTRA_MESSAGE, statusText);
//                spilletErSlutIntent.putExtra(ER_SPIL_VUNDET, "false");
//                spilletErSlutIntent.putExtra(SPILLE_TID, spilleTid);
//                startActivity(spilletErSlutIntent);
            }
            else if(ForsideActivity.galgelogik.erSpilletVundet())
            {
                String statusText = "Spillet er vundet!!!";
                textViewTest1.setText(statusText);
                textViewTest1.setTextColor(Color.GREEN);
                Toast.makeText(getActivity(), statusText, Toast.LENGTH_LONG).show();
                Log.d("jj", statusText);
                buttonGætBogstav.setEnabled(false);

//                spilletErSlutIntent.putExtra(EXTRA_MESSAGE, statusText);
//                spilletErSlutIntent.putExtra(ER_SPIL_VUNDET, "true");
//                spilletErSlutIntent.putExtra(SPILLE_TID, spilleTid);

//                startActivity(spilletErSlutIntent);
//                this.finish();


            }
        }
        else
        {
            textViewFSbottom.setText("Tid: " + spilleTid + " sekunder");
        }
    }

    private void StartSpil() {

        startTime = new Date();

        ForsideActivity.galgelogik.nulstil();   // når spillet starter
        String ordet = ForsideActivity.galgelogik.getOrdet();
        textViewTest1.setText(textViewTest1.getText() + "--> " + ordet);
        //Toast.makeText(this, "Spillet er startet!!!", Toast.LENGTH_LONG).show();

        TextViewFSordVisning.setText(ForsideActivity.galgelogik.getSynligtOrd());


//        imageViewetFS.setImageResource(R.drawable.galge);
//        buttonSpil.setText("Nyt spil");
//        buttonGæt.setEnabled(true);
//        textViewFinishedStatus.setText("Spillet er i gang.");
//
//        textViewBrugteBogst.setText("");
//        textViewSynligtOrd.setText(ForsideActivity.galgelogik.getSynligtOrd());
    }

    private void OpdaterGalgeBillede()
    {
        int antalForkerte = ForsideActivity.galgelogik.getAntalForkerteBogstaver();
        Log.v("jj", (String.valueOf(antalForkerte)));
        switch (antalForkerte)
        {
            case 0:
                imageViewetFS.setImageResource(R.drawable.galge);
                break;
            case 1:
                imageViewetFS.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                imageViewetFS.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                imageViewetFS.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                imageViewetFS.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                imageViewetFS.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                imageViewetFS.setImageResource(R.drawable.forkert6);
                break;
            default:
                imageViewetFS.setImageResource(R.drawable.forkert6);
        }
    }
}
