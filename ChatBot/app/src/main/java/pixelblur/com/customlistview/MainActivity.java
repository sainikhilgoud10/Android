package pixelblur.com.customlistview;

import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private Button buttonSend;
    private Animation animation , animation1 , animation2;

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;
        String url = "https://en.wikipedia.org/wiki/" + chatText.getText().toString();

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect(url).get();
                // Using Elements to get the Meta data
                Elements description = document
                        .select("div.mw-content-ltr");
                Elements e = description.select("p");
                Element e1 = e.first();
                // Locate the content attribute
                desc = e1.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView
            ChatMessage replyMessage = new ChatMessage(desc, false , DateFormat.getDateTimeInstance().format(new Date()));
            chatArrayAdapter.add(replyMessage);
            runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    chatText.setVisibility(View.VISIBLE);
                    buttonSend.setVisibility(View.VISIBLE);
                    findViewById(R.id.circle).clearAnimation();
                    findViewById(R.id.circle1).clearAnimation();
                    findViewById(R.id.circle2).clearAnimation();
                    animation.setAnimationListener(null);
                    findViewById(R.id.circle).setAnimation(null);
                    findViewById(R.id.circle1).setAnimation(null);
                    findViewById(R.id.circle2).setAnimation(null);
                    findViewById(R.id.circle).setVisibility(View.GONE);
                    findViewById(R.id.circle1).setVisibility(View.GONE);
                    findViewById(R.id.circle2).setVisibility(View.GONE);
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animation = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.move);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.move);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.move);
        buttonSend = (Button)findViewById(R.id.send);
        chatText = (EditText)findViewById(R.id.msg);
        listView = (ListView)findViewById(R.id.msgview);
        //imageViewLeft = (ImageView)findViewById(R.id.imageview);
        //imageViewRight = (ImageView)findViewById(R.id.imageview1);
        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext() , R.layout.right);
        listView.setAdapter(chatArrayAdapter);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
    }

    private boolean sendChatMessage() {
        //chatMessage = new ChatMessage(true , imageView);

        chatArrayAdapter.add(new ChatMessage(chatText.getText().toString() ,true , DateFormat.getDateTimeInstance().format(new Date())));
        new Description().execute();
        chatText.setText("");
        chatText.setVisibility(View.GONE);
        buttonSend.setVisibility(View.GONE);

        findViewById(R.id.circle).setVisibility(View.VISIBLE);
        findViewById(R.id.circle1).setVisibility(View.VISIBLE);
        findViewById(R.id.circle2).setVisibility(View.VISIBLE);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //im.startAnimation(animation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //animation.restrictDuration(5000);
        findViewById(R.id.circle).startAnimation(animation);
        findViewById(R.id.circle1).startAnimation(animation1);
        findViewById(R.id.circle2).startAnimation(animation2);
        /*new Handler().postDelayed(new Runnable(){
           @Override
            public void run(){

               chatArrayAdapter.add(new ChatMessage(chatText.getText().toString() , false , DateFormat.getDateTimeInstance().format(new Date())));
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        chatText.setVisibility(View.VISIBLE);
                        buttonSend.setVisibility(View.VISIBLE);
                        findViewById(R.id.circle).clearAnimation();
                        findViewById(R.id.circle1).clearAnimation();
                        findViewById(R.id.circle2).clearAnimation();
                        animation.setAnimationListener(null);
                        findViewById(R.id.circle).setAnimation(null);
                        findViewById(R.id.circle1).setAnimation(null);
                        findViewById(R.id.circle2).setAnimation(null);
                        findViewById(R.id.circle).setVisibility(View.GONE);
                        findViewById(R.id.circle1).setVisibility(View.GONE);
                        findViewById(R.id.circle2).setVisibility(View.GONE);
                    }
                });



           }
        } , 5000);*/
        return true;
    }

}
