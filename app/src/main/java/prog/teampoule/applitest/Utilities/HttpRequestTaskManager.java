package prog.teampoule.applitest.Utilities;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import prog.teampoule.applitest.classAdapter.Patinoire;

import static prog.teampoule.applitest.Utilities.InputStreamOperations.InputStreamToString;

/**
 * Created by massj on 28/01/2016.
 */
public class HttpRequestTaskManager extends AsyncTask<Patinoire, String, JSONArray> {

    private static final String FLAG_SUCCESS = "success";
    private static final String FLAG_MESSAGE = "message";

    public String URL;
    public String getURL() {return URL;}
    public void setURL(String URL) {this.URL = URL;}

    public ArrayList<Patinoire> List;
    public ArrayList<Patinoire> getList() {return List;}
    public void setList(ArrayList<Patinoire> list) {List = list;}


    @Override
    protected JSONArray doInBackground(Patinoire... params) {
        JSONArray jsonResponse = new JSONArray();
        try {
            //Recupere l'adresse url du site
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
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
            InputStream in = new BufferedInputStream(connection.getInputStream());
            Log.d("Http",InputStreamToString(in));
            jsonResponse = new JSONArray(InputStreamToString(in));

        } catch (IOException e) {
            Log.e("IOException", "Verifier si le serveur tourne");
        } catch (JSONException e) {
            Log.e("JSONException", "Error2");
        } catch (NetworkOnMainThreadException e) {
            Log.e("NetWorkException","Marche pas si android > 3.0!!");
        }
        return jsonResponse;
    }

    @Override
    protected void onPostExecute( JSONArray array){

        try{
            ArrayList<Patinoire> patinoires = new ArrayList<Patinoire>();
            Log.d("JSON",array.toString());
            // On récupère le tableau d'objets qui nous concernent
            //JSONArray array = new JSONArray(result);
            // Pour tous les objets on récupère les infos

            for (int i = 0; i < array.length(); i++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));
                // On fait le lien Personne - Objet JSON
                Patinoire patinoire = new Patinoire();
                patinoire.setName(obj.getString("author"));
                patinoire.setText(obj.getString("text"));
                // On ajoute la personne à la liste
                patinoires.add(patinoire);
            }
            List = patinoires;
            Log.d("LIST",patinoires.toString());

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

