package io.agora.openlive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context , List<Task> tasks){
        this.context = context;
        taskList = tasks;
    }
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new TaskHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        Task task = taskList.get(position);
        holder._id.setText(task.get_id());
        holder.cleaners_assigned.setText(task.getCleaners_assigned());
        holder.cleaning_tasks.setText(task.getCleaning_tasks());
        holder.task_head.setText(task.getTask_head());
        holder.room.setText(task.getRoom());
        holder.floor.setText(task.getFloor());
        holder.start_time.setText(task.getStart_time());
        holder.end_time.setText(task.getEnd_time());
        holder.createdAt.setText(task.getCreatedAt());



        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("_id" , task.get_id());
                bundle.putString("cleaners_assigned" , task.getCleaners_assigned());
                bundle.putString("cleaning_tasks" , task.getCleaning_tasks());
                bundle.putString("task_head" , task.getTask_head());
                bundle.putString("room" , task.getRoom());
                bundle.putString("floor" , task.getFloor());
                bundle.putString("start_time" , task.getStart_time());
                bundle.putString("end_time" , task.getEnd_time());
                bundle.putString("createdAt" , task.getCreatedAt());


                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder{


        TextView _id , cleaners_assigned , cleaning_tasks, task_head,room,floor,start_time,end_time,createdAt;
        ConstraintLayout constraintLayout;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            _id = itemView.findViewById(R.id._idTV);
            cleaners_assigned = itemView.findViewById(R.id.cleaners_assignedTV);
            cleaning_tasks = itemView.findViewById(R.id.cleaning_tasksTV);
            task_head = itemView.findViewById(R.id.task_headTV);
            room = itemView.findViewById(R.id.roomTV);
            floor = itemView.findViewById(R.id.floorTV);
            start_time = itemView.findViewById(R.id.start_timeTV);
            end_time = itemView.findViewById(R.id.end_timeTV);
            createdAt = itemView.findViewById(R.id.createdAtTV);

            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}