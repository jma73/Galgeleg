package dk.jpeace.jan.galgeleg;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOrdliste extends Fragment {


    public FragmentOrdliste() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rod = inflater.inflate(R.layout.fragment_ordliste, container, false);


        String[] lande = { "Danmark", "Norge", "Sverige",
                "Finland", "Holland", "Italien", "Nepal" };



        ListView listView = (ListView) rod.findViewById(R.id.listViewOrdliste);
        listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, ForsideActivity.galgelogik.muligeOrd()));




        return rod;
    }


}
