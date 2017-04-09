package prog.teampoule.applitest.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import prog.teampoule.applitest.R;
import prog.teampoule.applitest.Utilities.HttpRequestTask_User;
import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.classAdapter.User;

/**
 * Created by Julien on 07/04/2017.
 */

public class activity_login extends Menu{

    private  LinearLayout inscription;
    private LinearLayout logins;
    private LinearLayout profil;
    private TextView nom;
    private TextView login;
    private TextView prenom ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_login);
        View inflated = stub.inflate();

        logins = (LinearLayout) findViewById(R.id.LinearLayoutLogin);
        inscription = (LinearLayout) findViewById(R.id.LinearLayoutInscription);
        profil = (LinearLayout) findViewById(R.id.LinearLayoutProfil);

        nom = (TextView) findViewById(R.id.txt_nom);
        login = (TextView) findViewById(R.id.txt_login);
        prenom = (TextView) findViewById(R.id.txt_prenom);



        if(is_Connected){
            logins.setVisibility(View.INVISIBLE);
            profil.setVisibility(View.VISIBLE);
            SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
            if(prefs.getBoolean("is_Connected",false)) {
                if(prefs.getString("nom",null)!=null){
                    nom.setText(prefs.getString("nom",null));
                }
                if(prefs.getString("prenom",null)!=null){
                    prenom.setText(prefs.getString("prenom",null));
                }
                if(prefs.getString("login",null)!=null){
                    login.setText(prefs.getString("login",null));
                }
            }
        }
        //Bouton pour le login
        Button connection = (Button) findViewById(R.id.btn_Connection);
        connection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Connection", Toast.LENGTH_SHORT).show();
                EditText login = (EditText) findViewById(R.id.edtxt_Login);
                EditText mdp = (EditText) findViewById(R.id.edtxt_Mdp);
                Login(login.getText().toString(),mdp.getText().toString());

            }
        });

        //Button pour l'inscription
        final Button Inscription = (Button) findViewById(R.id.btn_Inscription);
        Inscription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logins.setVisibility(View.INVISIBLE);
                inscription.setVisibility(View.VISIBLE);
            }
        });

        //Button pour la deconnection
        Button deconnection = (Button) findViewById(R.id.btn_deconnection);
        deconnection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
                if (prefs.getBoolean("is_Connected", false)) {
                    SharedPreferences.Editor editor = getSharedPreferences("MYPREF", MODE_PRIVATE).edit();
                    editor.putBoolean("is_Connected", false);
                    editor.putInt("id_user", -1);
                    editor.putString("login", null);
                    editor.putString("nom", null);
                    editor.putString("prenom",null);
                    editor.commit();
                }
                logins.setVisibility(View.VISIBLE);
                profil.setVisibility(View.INVISIBLE);
                login.setText("Qui etes vous?????");
                nom.setText("Inconnue");
                prenom.setText("Inconnue");
            }
        });


        Button inscrit = (Button) findViewById(R.id.btn_inscrit);
        inscrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText login = (EditText) findViewById( R.id.edtxt_loginInscription);
                EditText nom = (EditText) findViewById( R.id.edtxt_nom);
                EditText prenom = (EditText) findViewById( R.id.edtxt_prenom);
                EditText mdp = (EditText) findViewById( R.id.edtxt_mdpInscription);
                Log.d("Login",login.getText().toString());
                User user = new User(nom.getText().toString(),prenom.getText().toString(),login.getText().toString(),mdp.getText().toString());
                if(user.getLogin().length() !=  0 && user.getMdp().length() != 0 && user.getLogin() != null && user.getMdp() != null) {
                    Inscription(user);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login et mot de passe obligatoire", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //rempli les champs si l'utilisateur est connecté et que les champs sont pas vide


    }

    private void Inscription(User user){
        String myurl = "https://patinou-rest-api-ombrecube.c9users.io/user/inscription";
        HttpRequestTask_User http = new HttpRequestTask_User();
        http.setURL(myurl);
        http.setContext(getApplicationContext());
        http.setUser(user);
        http.setFLAG(2);//Flag Inscription
        http.setLog(logins);
        http.setPro(profil);
        http.setIns(inscription);
        http.execute();

    }
    private void Login(String Login,String mdp) {
        //Login("https://patinou-rest-api-ombrecube.c9users.io/user/login");
        try {
            String myurl = "https://patinou-rest-api-ombrecube.c9users.io/user/login";
            HttpRequestTask_User http = new HttpRequestTask_User();
            User user = new User(Login,mdp);
            http.setURL(myurl);
            http.setContext(getApplicationContext());
            http.setUser(user);
            http.setFLAG(1);
            http.setLog(logins);
            http.setPro(profil);
            http.setIns(inscription);
            http.setLogin(login);
            http.setNom(nom);
            http.setPrenom(prenom);
            http.execute();

        }catch(Exception e) {
            e.printStackTrace();
            Log.d("Test", e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(inscription.getVisibility()==View.VISIBLE){
                logins.setVisibility(View.VISIBLE);
                inscription.setVisibility(View.INVISIBLE);
            }else {
                Intent myIntent = new Intent(getApplicationContext(), activity_HomePage.class);
                startActivity(myIntent);
                finish();
                //super.onBackPressed();
            }
        }
    }


}
