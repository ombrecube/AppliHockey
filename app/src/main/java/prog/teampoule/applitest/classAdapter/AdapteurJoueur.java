package prog.teampoule.applitest.classAdapter;

import android.content.Context;
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


public AdapteurJoueur(Context context, ArrayList<String> arrayList){
        super(context, 0, arrayList);
        }


@Override
public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_joueur,parent,false);
        }

        TextView idJoueur = (TextView)convertView.findViewById(R.id.id_matchJoueur_alea);
        EditText nomJoueur = (EditText) convertView.findViewById(R.id.nom_joueur);
        String noms = getItem(position);
        position ++;
        //nomJoueur.setId(1000+position);
        idJoueur.setText("Joueur "+position+": ");
        nomJoueur.setHint("Joueur "+position);

        return convertView;
        }

}