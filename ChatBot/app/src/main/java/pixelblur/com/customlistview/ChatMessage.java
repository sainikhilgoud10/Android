package pixelblur.com.customlistview;

import android.widget.ImageView;

import java.util.Date;

/**
 * Created by sai on 15-10-2016.
 */

public class ChatMessage {

    public boolean isMe;
    public String date;
    public String message;
    public ChatMessage(String message , boolean isMe , String date) {
        super();
        this.message = message;
        this.date = date;
        this.isMe = isMe;

    }
}
