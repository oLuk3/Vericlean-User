package io.agora.openlive.api;

public interface API {

    void login(String email, String password, APIListener listener );

    void loadSchedule(APIListener listener);
}
