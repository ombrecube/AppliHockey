package prog.teampoule.applitest.classAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import prog.teampoule.applitest.R;

/**
 * Created by Julien on 13/04/2017.
 */

public class AdapteurAllMessage extends ArrayAdapter<Conversation> {

    private String Login;

    public AdapteurAllMessage(Context context, ArrayList<Conversation> arrayList, String login){
        super(context, 0, arrayList);
        Login=login;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_friendmessage,parent,false);
        }

        TextView login = (TextView)convertView.findViewById(R.id.friend_login);
        TextView Message = (TextView) convertView.findViewById(R.id.Extrainmsg);
        TextView Date = (TextView) convertView.findViewById(R.id.date);

        Conversation conv = getItem(position);
        if(conv.getLoginEmetteur() != "")
            if(conv.getLoginEmetteur().equals(Login))
                login.setText(conv.getLoginReceveur());
            else
                login.setText(conv.getLoginEmetteur());
        if(conv.getMessage() != "")
            Message.setText(conv.getMessage());
        if(String.valueOf(conv.getDate()) != "")
            Date.setText(conv.getDate());


        return  convertView;
    }

}
