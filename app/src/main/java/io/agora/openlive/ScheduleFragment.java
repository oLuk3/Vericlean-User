package io.agora.openlive;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.agora.openlive.api.AbstractAPIListener;


public class ScheduleFragment extends Fragment {

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    private OnFragmentInteractionListener mListener;

    public ScheduleFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        if (view instanceof RecyclerView){
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(decoration);

            Application application = this.getActivity().getApplication();
            Model model = Model.getInstance(application);
            final List<Schedule> schedule = model.getSchedule();

            final ScheduleAdapter adapter = new ScheduleAdapter(this, schedule);
            recyclerView.setAdapter(adapter);

            model.loadSchedule(new AbstractAPIListener(){

            });
        }

    }


    public void onItemSelected(Schedule schedule){
        if (mListener != null){
            mListener.onItemSelected(schedule);
        }

    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }


    public void onDetach(){
        super.onDetach();
        mListener = null;
    }

    public interface  OnFragmentInteractionListener{
        void onItemSelected(Schedule schedule);
    }


}