package prog.teampoule.applitest.classAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import prog.teampoule.applitest.R;

/**
 * Created by Julien on 22/03/2017.
 */

public class AdapteurJoueur extends ArrayAdapter<String> {
private ArrayList<String> myItems;



public AdapteurJoueur(Context context, ArrayList<String> arrayList){
        super(context, 0, arrayList);
        myItems = arrayList;
        }


@Override
public View getView(int position, View convertView, ViewGroup parent){
        final int nb = position;

        if(convertView==null){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_joueur,parent,false);
        }

        TextView idJoueur = (TextView)convertView.findViewById(R.id.id_matchJoueur_alea);
        EditText nomJoueur = (EditText) convertView.findViewById(R.id.nom_joueur);


        if(myItems.get(nb) != "")
            nomJoueur.setText(myItems.get(nb).toString());
        position ++;
        idJoueur.setText("Joueur "+position+": ");
        nomJoueur.setHint("Joueur "+position);


        nomJoueur.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    final int pos = v.getId();
                    final EditText Caption = (EditText) v;
                    myItems.set(nb,Caption.getText().toString());
                }
                Log.d("Test",myItems.toString());
            }
        });
        return convertView;
        }

    public ArrayList<String> ListeJoueurs()
    {
        return myItems;
    }

}

