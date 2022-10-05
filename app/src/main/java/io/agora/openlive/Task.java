package io.agora.openlive;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class Task {

    private String _id , cleaners_assigned , cleaning_tasks, task_head,room,floor,start_time,end_time;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Task(String cleaning_tasks, String room, String floor,
                String start_time, String end_time, String _id){

        String starttimeLocal =
                Instant.parse (start_time)
                        .atZone( ZoneId.of ( "Asia/Manila" ) )
                        .format(
                                DateTimeFormatter.ofLocalizedDateTime ( FormatStyle.FULL )
                                        .withLocale ( Locale.US ).ofPattern("h:mm a"));

        String endtimeLocal =
                Instant.parse (end_time)
                        .atZone( ZoneId.of ( "Asia/Manila" ) )
                        .format(
                                DateTimeFormatter.ofLocalizedDateTime ( FormatStyle.FULL )
                                        .withLocale ( Locale.US ).ofPattern("h:mm a"));

         this._id = _id;
       // this.cleaners_assigned = cleaners_assigned;
        this.cleaning_tasks = cleaning_tasks;
       // this.task_head = task_head;
        this.room = room;
        this.floor = floor;
        this.start_time = starttimeLocal;
        this.end_time = endtimeLocal;




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


}
