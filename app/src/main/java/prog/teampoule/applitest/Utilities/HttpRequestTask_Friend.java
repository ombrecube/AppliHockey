package prog.teampoule.applitest.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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

import prog.teampoule.applitest.R;
import prog.teampoule.applitest.classAdapter.AdapteurAllMessage;
import prog.teampoule.applitest.classAdapter.AdapteurMessage;
import prog.teampoule.applitest.classAdapter.AdapteurUser;
import prog.teampoule.applitest.classAdapter.Conversation;
import prog.teampoule.applitest.classAdapter.Message;
import prog.teampoule.applitest.classAdapter.User;

import static android.content.Context.MODE_PRIVATE;
import static prog.teampoule.applitest.Activity.activity_Friend.lv;
import static prog.teampoule.applitest.Activity.activity_Friend.lv2;
import static prog.teampoule.applitest.Utilities.InputStreamOperations.InputStreamToString;

/**
 * Created by Julien on 09/04/2017.
 */

public class HttpRequestTask_Friend extends AsyncTask<User, String, JSONObject> {

    private boolean CoDown;
    private String URL;
    public void setURL(String URL) {this.URL = URL;}

    //Savoir quel fonctionalié est demande
    //1: Get all friend
    //2: Get all Message
    //3: Get Message from an id_friend
    //4: Add a message
    private int FLAG;
    public int getFLAG() {
        return FLAG;
    }
    public void setFLAG(int FLAG) {
        this.FLAG = FLAG;
    }

    public Context context;
    public void setContext(Context context){this.context = context;}

    private ListView list;
    public void setListView(ListView l){this.list = l;}

    private TextView txt;
    public void SetTxt(TextView txt){this.txt=txt;}

    private String message;
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected JSONObject doInBackground(User... params) {
        JSONObject jsonResponse = new JSONObject();
        try {
            //Recupere l'adresse url du site
            java.net.URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            final String basicAuth = "Basic " + Base64.encodeToString("Admin:TeamPoule".getBytes(), Base64.NO_WRAP);
            connection.setRequestProperty ("Authorization", basicAuth);

            String urlParameters;
            byte [] postData;


            switch (FLAG){
                //Get All friend
                case 1:
                    connection.setRequestMethod("GET");
                    break;
                case 2:
                    connection.setRequestMethod("GET");
                    break;
                case 3:
                    connection.setRequestMethod("GET");
                    break;
                case 4:
                    connection.setRequestMethod("POST");
                    urlParameters = "message=" + message;
                    postData = urlParameters.getBytes();
                    connection.setRequestProperty("Content-Length", "" + postData.length);
                    try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())){
                        wr.write(postData);
                    }
                    break;
                default:
                    break;
            }
            Log.d("HttpRequestTaskBackgr", "ready to send request.");
            connection.connect();
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
        SharedPreferences prefs = context.getSharedPreferences("MYPREF", MODE_PRIVATE);
        try{
            if(CoDown==false) {
                    switch (FLAG){
                        case 1:
                            ArrayList<User> List = new ArrayList<User>();
                            JSONArray users = new JSONArray(Json.getString("Friend"));
                            for (int i = 0; i < users.length(); i++) {
                                JSONObject friend = new JSONObject(users.get(i).toString());
                                User user = new User();
                                user.setId_user(friend.getInt("id_user"));
                                user.setLogin(friend.getString("login"));
                                List.add(user);
                            }
                            adapteur = new AdapteurUser(context,List);
                            adapteur.notifyDataSetChanged();
                            lv.setAdapter(adapteur);
                            ArrayList<User> Friends = new ArrayList<User>();
                            JSONArray Friend = new JSONArray(Json.getString("RequestFriend"));
                            for (int i = 0; i < Friend.length(); i++) {
                                JSONObject friend = new JSONObject(Friend.get(i).toString());
                                User user = new User();
                                user.setId_user(friend.getInt("id_user"));
                                user.setLogin(friend.getString("login"));
                                Friends.add(user);
                            }
                            lv2.setAdapter(new AdapteurUser(context,Friends));
                            break;
                        case 2:
                            ArrayList<Conversation> Conversations = new ArrayList<Conversation>();
                            JSONArray conversation = new JSONArray(Json.getString("Reussi"));
                            for (int i = 0; i < conversation.length(); i++) {
                                JSONObject conv = new JSONObject(conversation.get(i).toString());
                                Conversation conversation1 = new Conversation();
                                conversation1.setLoginReceveur(conv.getString("log"));
                                conversation1.setLoginEmetteur(conv.getString("login"));
                                conversation1.setMessage(conv.getString("message"));
                                conversation1.setDate(conv.getString("date"));
                                conversation1.setId_user(conv.getInt("id_user"));
                                conversation1.setId(conv.getInt("id"));
                                Conversations.add(conversation1);
                            }
                            txt.setText("Mes Messages:");
                            String CurrentLogin = prefs.getString("login","Nada");
                            Log.d("CurrentLogin",CurrentLogin);
                            AdapteurAllMessage adptM = new AdapteurAllMessage(context,Conversations,CurrentLogin);
                            list.setAdapter(adptM);
                            break;
                        case 3:
                            ArrayList<Message> ListMessage = new ArrayList<Message>();
                            JSONArray messages = new JSONArray(Json.getString("Messages"));
                            for (int i = 0; i < messages.length(); i++) {
                                JSONObject mess = new JSONObject(messages.get(i).toString());
                                Message message = new Message();
                                message.setMessage(mess.getString("message"));
                                message.setDate(mess.getString("date"));
                                if(String.valueOf(mess.getString("id_emeteur")).equals(String.valueOf(prefs.getInt("id_user",0)))){
                                    message.setOther(false);
                                }else{
                                    message.setOther(true);
                                }
                                ListMessage.add(message);
                            }
                            AdapteurMessage adptMessage = new AdapteurMessage(context,ListMessage);
                            list.setAdapter(adptMessage);
                            break;
                        case 4:
                            break;
                        default:
                            break;
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
    private AdapteurUser adapteur;
    public void setAdapteur(AdapteurUser adapteur) {
        this.adapteur = adapteur;
    }
}
