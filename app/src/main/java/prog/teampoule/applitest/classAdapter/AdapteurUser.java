package prog.teampoule.applitest.classAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import prog.teampoule.applitest.R;

/**
 * Created by Julien on 09/04/2017.
 */

public class AdapteurUser extends ArrayAdapter<User>{

        public AdapteurUser(Context context, ArrayList<User> arrayList){
        super(context, 0, arrayList);
    }


        @Override
        public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_user,parent,false);
        }

        TextView idUser = (TextView)convertView.findViewById(R.id.lv_id_user);
        TextView login = (TextView) convertView.findViewById(R.id.lv_loginUser);

        User user = getItem(position);
        if(String.valueOf(user.getId_user()) != "")
            idUser.setText(String.valueOf(user.getId_user()));
        if(user.getLogin() != "")
            login.setText(user.getLogin());
        return  convertView;
    }

    }
