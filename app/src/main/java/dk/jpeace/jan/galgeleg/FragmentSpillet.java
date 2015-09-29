package dk.jpeace.jan.galgeleg;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSpillet extends Fragment implements View.OnClickListener {


    private Button buttonGætBogstav;
    private TextView TextViewTest1;

    public FragmentSpillet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FragmentSpillet", "in onCreateView! Så vi igang...");
        View rod =  inflater.inflate(R.layout.fragment_spillet_copy, container, false);

        buttonGætBogstav = (Button) rod.findViewById(R.id.buttonGætBogstav);
        buttonGætBogstav.setOnClickListener(this);

        // TextViewTest1
        TextViewTest1 = (TextView) rod.findViewById(R.id.TextViewFragSpilTest1);
        // Inflate the layout for this fragment
        return rod;
    }


    @Override
    public void onClick(View view) {
        Log.d("FragmentSpillet", "in onClick!");

        if(view == buttonGætBogstav)
        {
            TextViewTest1.setText("så vi igang - with Fragmentzzzz");
        }
    }
}
