package prog.teampoule.applitest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.Intent;
import java.util.ArrayList;

/**
 * Created by Julien on 20/03/2017.
 */

public class activity_Conseils extends Menu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_conseils);
        View inflated = stub.inflate();


        ListView listView = (ListView) findViewById(R.id.listViewConseils);

        String[] valuesTitre = new String[]{"Patins", "Bâtons", "Protections"};

        String[] valuesTitreContenuPatins = new String[]{"Choisir ses patins", "Entretenir"};
        String[] valuesContenuPatins = new String[]{"Les pointures", "Les lames"};

        String[] valuesTitreContenuBatons = new String[]{"Choisir son bâton", "Entretenir"};
        String[] valuesContenuBatons = new String[]{"La hauteur", "Mettre du tape"};


        ArrayList<activity_Conseils_Item> listItem = new ArrayList<activity_Conseils_Item>();
        listItem.add(new activity_Conseils_Item(valuesTitre[0], valuesTitreContenuPatins, valuesContenuPatins));
        listItem.add(new activity_Conseils_Item(valuesTitre[1], valuesTitreContenuBatons, valuesContenuBatons));

        //normal pour l'instant
        activity_Conseils_Adapter adapter = new activity_Conseils_Adapter(this, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                activity_Conseils_Item item = (activity_Conseils_Item) parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), activity_Conseils_Item_Details.class);
                intent.putExtra("Item", item);
                startActivity(intent);
            }
        });

    }
}
