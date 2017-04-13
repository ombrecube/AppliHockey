package prog.teampoule.applitest.classAdapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import prog.teampoule.applitest.R;

/**
 * Created by Julien on 13/04/2017.
 */

public class AdapteurMessage extends ArrayAdapter<Message> {

    private String Login;

    public AdapteurMessage(Context context, ArrayList<Message> arrayList){
        super(context, 0, arrayList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_message,parent,false);
        }
        RelativeLayout linear = (RelativeLayout) convertView.findViewById(R.id.Frame_Message);
        TextView Message = (TextView) convertView.findViewById(R.id.messages);
        TextView Date = (TextView) convertView.findViewById(R.id.date);

        Message message = getItem(position);
        if(!message.isOther())
            linear.setGravity(Gravity.RIGHT);
        if(message.getMessage() != "")
            Message.setText(message.getMessage());
        if(String.valueOf(message.getDate()) != "")
            Date.setText(message.getDate());


        return  convertView;
    }

}