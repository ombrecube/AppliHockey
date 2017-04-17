package prog.teampoule.applitest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import prog.teampoule.applitest.R;
import prog.teampoule.applitest.Utilities.HttpRequestTask_Friend;

/**
 * Created by Julien on 13/04/2017.
 */

public class activity_Message extends AppCompatActivity {
    ListView lv;
    public int ID;
    boolean bool = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent = getIntent();
        ID = intent.getExtras().getInt("ID");
        String Login = intent.getExtras().getString("Login");
        Log.d("ID",String.valueOf(ID));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        lv = (ListView) findViewById(R.id.lv_messages);
        getMessage(ID);

        Button btn = (Button) findViewById(R.id.btn_envoyerMsg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt = (EditText) findViewById(R.id.Edtxt_message);
                setMessage(ID,txt.getText().toString());
            }
        });

    }

    private void getMessage(int id) {
        SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
        int idUser = prefs.getInt("id_user",-1);
        String myurl = "https://patinou-rest-api-ombrecube.c9users.io/message/get/"+ String.valueOf(idUser)+"/"+ String.valueOf(id);
        HttpRequestTask_Friend http = new HttpRequestTask_Friend();
        http.setURL(myurl);
        http.setContext(getApplicationContext());
        http.setFLAG(3);
        http.setListView(lv);
        http.execute();

    }

    private void setMessage(int id,String message){
        SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
        int idUser = prefs.getInt("id_user",-1);
        String myurl = "https://patinou-rest-api-ombrecube.c9users.io/message/add/"+ String.valueOf(idUser)+"/"+ String.valueOf(id);
        HttpRequestTask_Friend http = new HttpRequestTask_Friend();
        http.setURL(myurl);
        http.setMessage(message);
        http.setContext(getApplicationContext());
        http.setFLAG(4);
        http.execute();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        new Thread(new Runnable() {
            public void run() {

                while (bool) {
                    SystemClock.sleep(4000);
                    getMessage(ID);
                }
            }
        }).start();
        super.onResume();
    }

    @Override
    protected  void onPause(){
        bool = false;
        super.onPause();
    }


}
