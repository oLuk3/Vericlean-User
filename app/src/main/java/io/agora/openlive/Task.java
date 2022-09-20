package io.agora.openlive;

public class Task {
    private String _id , cleaners_assigned , cleaning_tasks, task_head,room,floor,start_time,end_time,createdAt;



    public Task(String _id , String cleaners_assigned , String cleaning_tasks, String task_head, String room, String floor,
                String start_time, String end_time, String createdAt){

        this._id = _id;
        this.cleaners_assigned = cleaners_assigned;
        this.cleaning_tasks = cleaning_tasks;
        this.task_head = task_head;
        this.room = room;
        this.floor = floor;
        this.start_time = start_time;
        this.end_time = end_time;
        this.createdAt = createdAt;

    }

    public String get_id() {
        return _id;
    }

    public String getCleaners_assigned() {
        return cleaners_assigned;
    }

    public String getCleaning_tasks() {
        return cleaning_tasks;
    }

    public String getTask_head() {
        return task_head;
    }

    public String getRoom() {
        return room;
    }

    public String getFloor() {
        return floor;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getCreatedAt() {
        return createdAt;
    }

}
