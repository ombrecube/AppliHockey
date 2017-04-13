package prog.teampoule.applitest.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import prog.teampoule.applitest.R;
import prog.teampoule.applitest.Utilities.HttpRequestTask_Friend;
import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.classAdapter.Conversation;

/**
 * Created by Julien on 13/04/2017.
 */

public class activity_Allmessages  extends Menu {
    ListView lv;
    TextView resultat;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_patinoire);
        View inflated = stub.inflate();
        lv = (ListView) findViewById(R.id.ListViewPatinoire);
        resultat = (TextView) findViewById(R.id.id_chargementPatinoire);
        try{
            GetFriend();
        }
        catch(Exception e){

        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Conversation item = (Conversation) parent.getItemAtPosition(position);
                String login;
                int ID;
                SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
                if(item.getLoginEmetteur().equals(prefs.getString("login","Nada"))) {
                    login = item.getLoginReceveur();
                    ID = item.getId();
                }
                else {
                    login = item.getLoginEmetteur();
                    ID = item.getId_user();
                }
                Intent intent = new Intent(view.getContext(), activity_Message.class);
                intent.putExtra("ID", ID);
                intent.putExtra("Login", login);
                startActivity(intent);
            }
        });


    }

    public void GetFriend() {
        SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
        int id = prefs.getInt("id_user",-1);
        String myurl = "https://patinou-rest-api-ombrecube.c9users.io/message/All/"+ String.valueOf(id);
        HttpRequestTask_Friend http = new HttpRequestTask_Friend();
        http.setURL(myurl);
        http.setContext(getApplicationContext());
        http.SetTxt(resultat);
        http.setFLAG(2);
        http.setListView(lv);
        http.execute();
    }

}
