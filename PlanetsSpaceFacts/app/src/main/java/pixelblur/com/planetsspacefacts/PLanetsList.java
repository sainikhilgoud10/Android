package pixelblur.com.planetsspacefacts;


import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by sai on 22-06-2016.
 */
public class PLanetsList extends Fragment  {
    ListView listView;
    Communicator communicator;
    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
        return inflator.inflate(R.layout.planets_list,container,false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        communicator = (Communicator)getActivity();
        String[] web = {"Mercury","Venus","Earth","Mars","Jupiter","Saturn","Uranus","Neptune","Pluto"};
        Integer[] imageId = {R.drawable.mercury,R.drawable.venus,R.drawable.earth,R.drawable.mars,R.drawable.jupiter,R.drawable.saturn,R.drawable.uranus,R.drawable.neptune,R.drawable.pluto};
        CustomList adapter = new CustomList(getActivity(),web,imageId);
        listView = (ListView)getActivity().findViewById(R.id.list);
        listView.setAdapter(adapter);
        ColorDrawable sage = new ColorDrawable(this.getResources().getColor(R.color.sage));
        listView.setDivider(sage);
        listView.setDividerHeight(1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position,long id){
                communicator.respond(position);
            }
        });
    }


}
