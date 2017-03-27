package prog.teampoule.applitest.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ListView;
import android.widget.AdapterView;
import android.content.Intent;
import java.util.ArrayList;

import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.R;
import prog.teampoule.applitest.classAdapter.AdapteurConseils;

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

        String[] values = new String[]{"Patins", "Bâtons", "Protections"};


        ArrayList<String> listItem = new ArrayList<String>();
        for(int i = 0; i < values.length; i++) {
            listItem.add(values[i]);
        }

        AdapteurConseils adapter = new AdapteurConseils(this, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), activity_Conseils_Item_Details.class);
                intent.putExtra("Item", item);
                startActivity(intent);
            }
        });

    }
}
