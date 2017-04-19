package prog.teampoule.applitest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import prog.teampoule.applitest.BDD.ConseilsBDD;
import prog.teampoule.applitest.BDD.DetailsConseils;
import prog.teampoule.applitest.BDD.MySQLiteHelper;
import prog.teampoule.applitest.R;

/**
 * Created by Noemie on 22/03/2017.
 */

public class activity_Conseils_Item_Details extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseils_details);

        Intent intent = getIntent();
        String item = intent.getExtras().getString("Item");

        TextView viewTitre = (TextView) findViewById(R.id.titreDetails);
        viewTitre.setText(item);

        TextView viewTitreContenu1 = (TextView) findViewById(R.id.titreContenu1);
        TextView viewTitreContenu2 = (TextView) findViewById(R.id.titreContenu2);
        TextView viewTitreContenu3 = (TextView) findViewById(R.id.titreContenu3);
        TextView viewTitreContenu4 = (TextView) findViewById(R.id.titreContenu4);
        TextView viewTitreContenu5 = (TextView) findViewById(R.id.titreContenu5);
        TextView viewTitreContenu6 = (TextView) findViewById(R.id.titreContenu6);
        TextView viewTitreContenu7 = (TextView) findViewById(R.id.titreContenu7);
        TextView viewTitreContenu8 = (TextView) findViewById(R.id.titreContenu8);
        final TextView viewContenu1 = (TextView) findViewById(R.id.textContenu1);
        final TextView viewContenu2 = (TextView) findViewById(R.id.textContenu2);
        final TextView viewContenu3 = (TextView) findViewById(R.id.textContenu3);
        final TextView viewContenu4 = (TextView) findViewById(R.id.textContenu4);
        final TextView viewContenu5 = (TextView) findViewById(R.id.textContenu5);
        final TextView viewContenu6 = (TextView) findViewById(R.id.textContenu6);
        final TextView viewContenu7 = (TextView) findViewById(R.id.textContenu7);
        final TextView viewContenu8 = (TextView) findViewById(R.id.textContenu8);

        MySQLiteHelper db = new MySQLiteHelper(this);
        ConseilsBDD conseils = new ConseilsBDD(db);

        List<DetailsConseils> listDetails = new ArrayList<DetailsConseils>();
        if (item.equals("Patins")) {
            listDetails = conseils.getAllDetailsConseils(1);
        } else if (item.equals("Bâtons")) {
            listDetails = conseils.getAllDetailsConseils(2);
        } else if (item.equals("Protections")) {
            listDetails = conseils.getAllDetailsConseils(3);
        }


        String nom_details = new String();
        String contenu_details = new String();

        if (listDetails.size() > 0) {
            nom_details = listDetails.get(0).getNom_details();
            contenu_details = listDetails.get(0).getContenu_details();
            viewTitreContenu1.setText(nom_details);
            viewContenu1.setText(contenu_details);

            viewTitreContenu1.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewContenu1.getVisibility() == view.GONE) {
                        viewContenu1.setVisibility(view.VISIBLE);
                    } else {
                        viewContenu1.setVisibility(view.GONE);
                    }
                }
            });

        } else {
            viewTitreContenu1.setText("");
            viewContenu1.setText("");
        }


        if (listDetails.size() > 1) {
            nom_details = listDetails.get(1).getNom_details();
            contenu_details = listDetails.get(1).getContenu_details();
            viewTitreContenu2.setText(nom_details);
            viewContenu2.setText(contenu_details);

            viewTitreContenu2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewContenu2.getVisibility() == view.GONE) {
                        viewContenu2.setVisibility(view.VISIBLE);
                    } else {
                        viewContenu2.setVisibility(view.GONE);
                    }
                }
            });

        } else {
            viewTitreContenu2.setText("");
            viewContenu2.setText("");
        }

        if (listDetails.size() > 2) {
            nom_details = listDetails.get(2).getNom_details();
            contenu_details = listDetails.get(2).getContenu_details();
            viewTitreContenu3.setText(nom_details);
            viewContenu3.setText(contenu_details);

            viewTitreContenu3.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewContenu3.getVisibility() == view.GONE) {
                        viewContenu3.setVisibility(view.VISIBLE);
                    } else {
                        viewContenu3.setVisibility(view.GONE);
                    }
                }
            });

        } else {
            viewTitreContenu3.setText("");
            viewContenu3.setText("");
        }

        if (listDetails.size() > 3) {
            nom_details = listDetails.get(3).getNom_details();
            contenu_details = listDetails.get(3).getContenu_details();
            viewTitreContenu4.setText(nom_details);
            viewContenu4.setText(contenu_details);

            viewTitreContenu4.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewContenu4.getVisibility() == view.GONE) {
                        viewContenu4.setVisibility(view.VISIBLE);
                    } else {
                        viewContenu4.setVisibility(view.GONE);
                    }
                }
            });

        } else {
            viewTitreContenu4.setText("");
            viewContenu4.setText("");
        }

        if (listDetails.size() > 4) {
            nom_details = listDetails.get(4).getNom_details();
            contenu_details = listDetails.get(4).getContenu_details();
            viewTitreContenu5.setText(nom_details);
            viewContenu5.setText(contenu_details);

            viewTitreContenu5.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewContenu5.getVisibility() == view.GONE) {
                        viewContenu5.setVisibility(view.VISIBLE);
                    } else {
                        viewContenu5.setVisibility(view.GONE);
                    }
                }
            });

        } else {
            viewTitreContenu5.setText("");
            viewContenu5.setText("");
        }

        if (listDetails.size() > 5) {
            nom_details = listDetails.get(5).getNom_details();
            contenu_details = listDetails.get(5).getContenu_details();
            viewTitreContenu6.setText(nom_details);
            viewContenu6.setText(contenu_details);

            viewTitreContenu6.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewContenu6.getVisibility() == view.GONE) {
                        viewContenu6.setVisibility(view.VISIBLE);
                    } else {
                        viewContenu6.setVisibility(view.GONE);
                    }
                }
            });

        } else {
            viewTitreContenu6.setText("");
            viewContenu6.setText("");
        }

        if (listDetails.size() > 6) {
            nom_details = listDetails.get(6).getNom_details();
            contenu_details = listDetails.get(6).getContenu_details();
            viewTitreContenu7.setText(nom_details);
            viewContenu7.setText(contenu_details);

            viewTitreContenu7.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewContenu7.getVisibility() == view.GONE) {
                        viewContenu7.setVisibility(view.VISIBLE);
                    } else {
                        viewContenu7.setVisibility(view.GONE);
                    }
                }
            });

        } else {
            viewTitreContenu7.setText("");
            viewContenu7.setText("");
        }

        if (listDetails.size() > 7) {
            nom_details = listDetails.get(7).getNom_details();
            contenu_details = listDetails.get(7).getContenu_details();
            viewTitreContenu8.setText(nom_details);
            viewContenu8.setText(contenu_details);

            viewTitreContenu8.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (viewContenu8.getVisibility() == view.GONE) {
                        viewContenu8.setVisibility(view.VISIBLE);
                    } else {
                        viewContenu8.setVisibility(view.GONE);
                    }
                }
            });

        } else {
            viewTitreContenu8.setText("");
            viewContenu8.setText("");
        }


        //Toast.makeText(getApplicationContext(), "problème", Toast.LENGTH_LONG).show();
        //Log.d("salut", item.getTitre().toString());
    }
}
