package io.agora.openlive.api;

import java.util.List;

import io.agora.openlive.Schedule;
import io.agora.openlive.User;

public interface APIListener {
    void onLogin(User user);

   // void onScheduleLoaded(List<Schedule> schedules);
}
