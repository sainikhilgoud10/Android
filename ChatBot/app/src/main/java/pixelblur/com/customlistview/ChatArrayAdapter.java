package pixelblur.com.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sai on 15-10-2016.
 */

class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {
    private ImageView imageView;
    private TextView Datetxt , content;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private Context context;
    @Override
    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }
    public ChatArrayAdapter(Context context, int layoutResourceId) {

        super(context, layoutResourceId);


        this.context = context;
    }
    public int getCount() {
        return this.chatMessageList.size();
    }
    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (chatMessageObj.isMe) {
            row = inflater.inflate(R.layout.right, parent, false);
        }else{
            row = inflater.inflate(R.layout.left, parent, false);
        }
        Datetxt= (TextView)row.findViewById(R.id.txtInfo);
        content = (TextView)row.findViewById(R.id.txtMessage);
        Datetxt.setText(chatMessageObj.date);
        content.setText(chatMessageObj.message);
        //Animation animation = AnimationUtils.loadAnimation(context , R.anim.move);
        //chatMessageObj.imageView.startAnimation(animation);
        //chatText = (TextView) row.findViewById(R.id.msgr);
        //chatText.setText(chatMessageObj.message);
        return row;
    }
}
