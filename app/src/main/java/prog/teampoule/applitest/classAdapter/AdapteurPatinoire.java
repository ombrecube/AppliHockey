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
 * Created by Julien on 05/04/2017.
 */

public class AdapteurPatinoire extends ArrayAdapter<Patinoire> {

    public AdapteurPatinoire(Context context, ArrayList<Patinoire> arrayList){
        super(context, 0, arrayList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_patinoire,parent,false);
        }

        TextView idPatinoire = (TextView)convertView.findViewById(R.id.lv_idPatinoire);
        TextView nomPatinoire = (TextView) convertView.findViewById(R.id.lv_NomPatinoire);
        TextView adressePatinoire = (TextView) convertView.findViewById(R.id.lv_AdressePatinoire);

        Patinoire patinoire = getItem(position);
        if(String.valueOf(patinoire.getId_patinoire()) != "")
            idPatinoire.setText(String.valueOf(patinoire.getId_patinoire()));
        if(patinoire.getNom() != "")
            nomPatinoire.setText(patinoire.getNom());
        if(String.valueOf(patinoire.getId_patinoire()) != "")
            adressePatinoire.setText(patinoire.getAdresse());


       return  convertView;
    }

}