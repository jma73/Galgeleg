package dk.jpeace.jan.galgeleg;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSpillet extends Fragment implements View.OnClickListener {

    Date startTime;
    private Button buttonGætBogstav;
    private TextView TextViewTest1;



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

        // TextViewTest1
        TextViewTest1 = (TextView) rod.findViewById(R.id.TextViewFragSpilTest1);


        StartSpil();        // pt. bare til test...

        return rod;
    }


    @Override
    public void onClick(View view) {
        Log.d("FragmentSpillet", "in onClick!");

        if(view == buttonGætBogstav)
        {
            ForsideActivity.galgelogik.getOrdet();

            TextViewTest1.setText("så vi igang - with Fragmentzzzz");
        }
    }

    private void StartSpil() {

        startTime = new Date();

        ForsideActivity.galgelogik.nulstil();   // når spillet starter
        String ordet = ForsideActivity.galgelogik.getOrdet();
        TextViewTest1.setText(TextViewTest1.getText() + "--> " + ordet);
        //Toast.makeText(this, "Spillet er startet!!!", Toast.LENGTH_LONG).show();

//        imageView1.setImageResource(R.drawable.galge);
//        buttonSpil.setText("Nyt spil");
//        buttonGæt.setEnabled(true);
//        textViewFinishedStatus.setText("Spillet er i gang.");
//
//        textViewBrugteBogst.setText("");
//        textViewSynligtOrd.setText(ForsideActivity.galgelogik.getSynligtOrd());
    }
}
