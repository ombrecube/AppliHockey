package prog.teampoule.applitest.Utilities;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import prog.teampoule.applitest.classAdapter.User;

import static prog.teampoule.applitest.Utilities.InputStreamOperations.InputStreamToString;

/**
 * Created by Julien on 06/04/2017.
 */

public class HttpRequestTask_User extends AsyncTask<User, String, JSONObject> {

    private boolean CoDown;
    private String URL;
    public String getURL() {return URL;}
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
            user = new User("ombrecube","123Soleil");

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
}
