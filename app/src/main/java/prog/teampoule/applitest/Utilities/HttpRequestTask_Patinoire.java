package prog.teampoule.applitest.Utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import prog.teampoule.applitest.Activity.activity_Patinoire;
import prog.teampoule.applitest.classAdapter.AdapteurPatinoire;
import prog.teampoule.applitest.classAdapter.Patinoire;
import static prog.teampoule.applitest.Utilities.InputStreamOperations.InputStreamToString;

/**
 * Created by massj on 28/01/2016.
 */
public class HttpRequestTask_Patinoire extends AsyncTask<Patinoire, String, JSONArray> {

    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";

    public String URL;
    public String getURL() {return URL;}
    public void setURL(String URL) {this.URL = URL;}

    public Context context;
    public void setContext(Context context){this.context = context;}

    public ArrayList<Patinoire> List;
    public ArrayList<Patinoire> getList() {return List;}
    public void setList(ArrayList<Patinoire> list) {List = list;}

    public void setResultat(TextView resultat) {
        Resultat = resultat;
    }

    public void setList(ListView list) {
        this.listView = list;
    }

    private ListView listView;
    private TextView Resultat;
    private Boolean CoDown;


    @Override
    protected JSONArray doInBackground(Patinoire... params) {
        JSONArray jsonResponse = new JSONArray();
        try {
            //Recupere l'adresse url du site
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            final String basicAuth = "Basic " + Base64.encodeToString("Admin:TeamPoule".getBytes(), Base64.NO_WRAP);
            connection.setRequestProperty ("Authorization", basicAuth);
            //String urlParameters = "username=" + credential.username + "&password=" + credential.password;
            //Recupere les donn�es en paraletre
           // byte[] postData = urlParameters.getBytes();
            //connection.setRequestProperty("Content-Length", "" + postData.length);
            //try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            //    wr.write(postData);
            //}
            // envoie des donn�es
            Log.d("HttpRequestTaskBackgr", "ready to send request.");
            connection.connect();
            // decode response
            CoDown=false;
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String StringJson = InputStreamToString(in);
            Log.d("Json",StringJson);
            jsonResponse = new JSONArray (StringJson);

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
    protected void onPostExecute( JSONArray Json){

        try{
            if(CoDown==false) {
                ArrayList<Patinoire> patinoires = new ArrayList<Patinoire>();
                Log.d("JSON2", Json.toString());
                // Pour tous les objets on récupère les infos


                for (int i = 0; i < Json.length(); i++) {
                    // On récupère un objet JSON du tableau
                    JSONObject obj = new JSONObject(Json.get(i).toString());
                    // On fait le lien Personne - Objet JSON
                    Patinoire patinoire = new Patinoire();
                    patinoire.setId_patinoire(obj.getInt("id_patinoire"));
                    Log.d("LIST", String.valueOf(patinoire.getId_patinoire()));
                    patinoire.setNom(obj.getString("nom"));
                    patinoire.setAdresse(obj.getString("adresse"));
                    patinoire.setCp(obj.getString("cp"));
                    patinoire.setTelephone(obj.getString("telephone"));
                    // On ajoute la personne à la liste
                    patinoires.add(patinoire);
                }
                List = patinoires;

                Resultat.setText("Patinoire :");
                AdapteurPatinoire adpt = new AdapteurPatinoire(context, List);
                listView.setAdapter(adpt);
                Log.d("LIST", patinoires.toString());
            }else{
                Resultat.setText("La Connection avec le serveur à échouer");
            }

        }  catch(JSONException e){
            Log.e("JSONException", "Error");
        }  catch (NetworkOnMainThreadException e){
            Log.e("ThreadException", "android > 3.0!!");
        }
    }

    private static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}

