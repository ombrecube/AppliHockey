package prog.teampoule.applitest.classAdapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import prog.teampoule.applitest.R;
import prog.teampoule.applitest.activity_Conseils_Item;

/**
 * Created by Noemie on 22/03/2017.
 */

public class AdapteurConseils extends ArrayAdapter<activity_Conseils_Item> {

    public AdapteurConseils(Context context, ArrayList<activity_Conseils_Item> arrayList){
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_conseils_element,parent,false);
        }

        TextView viewTitre = (TextView) convertView.findViewById(R.id.titreElement);

        activity_Conseils_Item item = getItem(position);
        viewTitre.setText(item.getTitre());

        return convertView;
    }

}
