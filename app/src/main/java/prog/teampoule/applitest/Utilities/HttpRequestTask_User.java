package prog.teampoule.applitest.Utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import prog.teampoule.applitest.Activity.activity_Conseils;
import prog.teampoule.applitest.Activity.activity_HomePage;
import prog.teampoule.applitest.classAdapter.User;

import static android.content.Context.MODE_PRIVATE;
import static prog.teampoule.applitest.Utilities.InputStreamOperations.InputStreamToString;

/**
 * Created by Julien on 06/04/2017.
 */

public class HttpRequestTask_User extends AsyncTask<User, String, JSONObject> {

    private boolean CoDown;
    private String URL;
    public void setURL(String URL) {this.URL = URL;}

    //Savoir quel fonctionalié est demande
    //1: Login
    //2: Inscription
    //3: Modification du profil
    //4: Chercher des amis
    private int FLAG;
    public int getFLAG() {
        return FLAG;
    }
    public void setFLAG(int FLAG) {
        this.FLAG = FLAG;
    }

    public Context context;
    public void setContext(Context context){this.context = context;}

    public LinearLayout log;
    public LinearLayout ins;
    public LinearLayout pro;

    public TextView login;
    public TextView nom;
    public TextView prenom;

    public void setPrenom(TextView prenom) {
        this.prenom = prenom;
    }
    public void setLogin(TextView login) {
        this.login = login;
    }
    public void setNom(TextView nom) {
        this.nom = nom;
    }

    public void setPro(LinearLayout pro) {this.pro = pro;}
    public void setLog(LinearLayout log) {
        this.log = log;
    }
    public void setIns(LinearLayout ins) {
        this.ins = ins;
    }

    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected JSONObject doInBackground(User... params) {
        JSONObject jsonResponse = new JSONObject();
        try {
            //Recupere l'adresse url du site
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            final String basicAuth = "Basic " + Base64.encodeToString("Admin:TeamPoule".getBytes(), Base64.NO_WRAP);
            connection.setRequestProperty ("Authorization", basicAuth);

            String urlParameters;
            byte [] postData;


            switch (FLAG){
                //Login
                case 1:
                    connection.setRequestMethod("POST");
                    urlParameters = "login=" + user.getLogin() + "&mdp=" + user.getMdp();
                    postData = urlParameters.getBytes();
                    connection.setRequestProperty("Content-Length", "" + postData.length);
                    try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())){
                        wr.write(postData);
                    }
                    break;
                //Inscription
                case 2:
                    connection.setRequestMethod("POST");
                    urlParameters = "login=" + user.getLogin() + "&mdp=" + user.getMdp()+"&nom="+user.getNom()+"&prenom="+user.getPrenom();
                    postData = urlParameters.getBytes();
                    connection.setRequestProperty("Content-Length", "" + postData.length);
                    try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())){
                        wr.write(postData);
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
            Log.d("HttpRequestTaskBackgr", "ready to send request.");
            connection.connect();
            // decode response
            CoDown=false;
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String StringJson = InputStreamToString(in);
            Log.d("Json",StringJson);
            jsonResponse = new JSONObject (StringJson);
            Log.d("Json","Convertion ok");
        } catch (IOException e) {
            Log.e("IOException", "Verifier si le serveur tourne");
            CoDown = true;
        } catch (JSONException e) {
            Log.e("JSONException", e.toString());
        } catch (NetworkOnMainThreadException e) {
            Log.e("NetWorkException","Marche pas si android > 3.0!!");
        }
        return jsonResponse;
    }

    @Override
    protected void onPostExecute( JSONObject Json){

        try{
            if(CoDown==false) {
                if(Json.getBoolean("Reussi")== true){
                    switch (FLAG){
                        case 1:
                            JSONArray users = new JSONArray(Json.getString("user"));
                            JSONObject user = new JSONObject(users.get(0).toString());
                            Log.d("Login user",Json.getString("user"));
                            SharedPreferences prefs = context.getSharedPreferences("MYPREF", MODE_PRIVATE);
                            SharedPreferences.Editor editor = context.getSharedPreferences("MYPREF", MODE_PRIVATE).edit();
                            if(prefs.getBoolean("is_Connected",false)==false) {
                                editor.putBoolean("is_Connected", true);
                                editor.commit();
                            }
                            editor.putInt("id_user", user.getInt("id_user"));
                            editor.putString("login", user.getString("login"));
                            editor.putString("nom", user.getString("nom"));
                            editor.putString("prenom", user.getString("prenom"));
                            editor.commit();
                            log.setVisibility(View.INVISIBLE);
                            pro.setVisibility(View.VISIBLE);

                            login.setText(user.getString("login"));
                            if(user.getString("nom") != "null")
                                nom.setText(user.getString("nom"));
                            if(user.getString("prenom") != "null")
                                prenom.setText(user.getString("prenom"));
                            Toast.makeText(context,"Heureux de vous voir "+user.getString("login"),Toast.LENGTH_LONG).show();//*/
                            break;
                        case 2:
                            log.setVisibility(View.VISIBLE);
                            ins.setVisibility(View.INVISIBLE);
                            Toast.makeText(context,"Inscription reussi",Toast.LENGTH_LONG).show();
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        default:
                            break;
                    }

                }else{
                    switch (FLAG) {
                        case 1:
                            Toast.makeText(context, "Mot de passe incorrect", Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(context, "Login déjà utilisé", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(context, "Erreur dans le systeme", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
                Log.d("JSON2", Json.toString());



            }else{
                Toast.makeText(context,"La connection au serveur a échoué",Toast.LENGTH_LONG).show();
            }

        }  catch(JSONException e){
            Log.e("JSONException", "Error");
        }  catch (NetworkOnMainThreadException e){
            Log.e("ThreadException", "android > 3.0!!");
        }
    }
}
