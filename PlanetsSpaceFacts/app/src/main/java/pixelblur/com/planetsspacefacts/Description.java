package pixelblur.com.planetsspacefacts;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sai on 22-06-2016.
 */
public class Description extends Fragment {
    TextView textView;
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
        return inflator.inflate(R.layout.description,container,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        textView = (TextView)getActivity().findViewById(R.id.text_description);
        imageView = (ImageView)getActivity().findViewById(R.id.imageview);
        Bundle bundle = this.getArguments();
        int myint = bundle.getInt("key",1);
        changeData(myint);
    }
    public void changeData(final int i){
        Resources res = getResources();
        String[] descriptions = res.getStringArray(R.array.descriptions);
        textView.setText(descriptions[i]);
        getActivity().runOnUiThread(new Runnable(){
           @Override
            public void run(){
               switch(i){
                   case 0:imageView.setImageResource(R.drawable.mercury);
                       break;
                   case 1:imageView.setImageResource(R.drawable.venus);
                       break;
                   case 2:imageView.setImageResource(R.drawable.earth);
                       break;
                   case 3:imageView.setImageResource(R.drawable.mars);
                       break;
                   case 4:imageView.setImageResource(R.drawable.jupiter);
                       break;
                   case 5:imageView.setImageResource(R.drawable.saturn);
                       break;
                   case 6:imageView.setImageResource(R.drawable.uranus);
                       break;
                   case 7:imageView.setImageResource(R.drawable.neptune);
                       break;
                   case 8:imageView.setImageResource(R.drawable.pluto);
                       break;
                   default:break;
               }
           }
        });

    }
}
