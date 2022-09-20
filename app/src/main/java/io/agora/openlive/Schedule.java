package io.agora.openlive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;


public class Schedule extends AppCompatActivity implements ScheduleFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        FrameLayout container = findViewById(R.id.schedule_container);
        if(container != null) {
            Fragment fragment = ScheduleFragment.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.schedule_container, fragment);
            fragmentTransaction.commit();
        }

    }


    @Override
    public void onItemSelected(Schedule schedule) {
        Toast.makeText(this, "Schedule Stelected: "+schedule,Toast.LENGTH_LONG).show();
    }
}


