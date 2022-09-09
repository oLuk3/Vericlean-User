package io.agora.openlive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

 class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mIdView;
        public final TextView mCleaningView;
        public final TextView mCreatedView;

        public Schedule mSchedule;

        public ViewHolder(View view){
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.schedule_id);
            mCleaningView = view.findViewById(R.id.cleaning_tasks);
            mCreatedView = view.findViewById(R.id.createdAt);

        }

    }


    private final ScheduleFragment mFragment;
    private final List<Schedule> mSchedule;

    public ScheduleAdapter(ScheduleFragment scheduleFragment, List<Schedule> schedule) {
        mFragment = scheduleFragment;
        mSchedule = schedule;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_schedule_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
//changes here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Schedule schedule = mSchedule.get(position);
        viewHolder.mIdView.setText(schedule.getId());
        viewHolder.mCleaningView.setText(schedule.getCleaning());
        viewHolder.mCreatedView.setText(schedule.getCreated());
        viewHolder.mSchedule = schedule;

        viewHolder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mFragment.onItemSelected(schedule);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSchedule.size();
    }
}
