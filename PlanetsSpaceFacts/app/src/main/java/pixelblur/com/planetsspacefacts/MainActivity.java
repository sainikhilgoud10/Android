package pixelblur.com.planetsspacefacts;


import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toolbar my_toolbar = (Toolbar)findViewById(R.id.my_toolbar);
                setSupportActionBar(my_toolbar);
                try {
                    getSupportActionBar().setTitle(R.string.my_tb_title);
                    getSupportActionBar().setIcon(R.mipmap.icon);
                }catch(NullPointerException e){
                    //do nothing mostly this exception will not occur
                }
            }
        });

        addFragmentBeginning();
    }
    private void addFragmentBeginning(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PLanetsList fraga = new PLanetsList();
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.mainframe,fraga,"fraga");
                transaction.commit();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.action_love){
            final String uri = getPackageName();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+uri)));
        }else if(item.getItemId()==R.id.action_share){
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "https://play.google.com/store/apps/details?id=com.planetfactsreloaded.apw";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"subject here");
            sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(sharingIntent,"share via"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void respond(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Description fragb = new Description();
                Bundle bundle = new Bundle();
                bundle.putInt("key",i);
                fragb.setArguments(bundle);
                android.app.FragmentManager manager = getFragmentManager();
                android.app.FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.animator.slide_up,R.animator.slide_down,R.animator.slide_up,R.animator.slide_down).replace(R.id.mainframe,fragb,"Vivz");
                transaction.addToBackStack("fraga");
                transaction.commit();
            }
        });

    }
    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount()==0){
            super.onBackPressed();
        }else{
            getFragmentManager().popBackStack();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

    }

}
