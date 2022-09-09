package io.agora.openlive;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import io.agora.openlive.api.APIListener;
import io.agora.openlive.api.AbstractAPIListener;
import io.agora.openlive.api.WebAPI;
import io.agora.openlive.api.API;

public class Model {

    private static Model sInstance = null;

    private final API mApi;
    private User mUser;
    private List<Schedule> mSchedule;

    public static Model getInstance(Application application){
        //change on line 12
        if (sInstance == null ) {
            sInstance = new Model(application);
        }
        return sInstance;
    }

    private final Application mApplication;

    private Model(Application application) {
        mApplication = application;
        mApi = new WebAPI(mApplication);
        mSchedule = new ArrayList<>();
    }

    public Application getApplication(){return mApplication;}

    public void login(String email, String password, APIListener listener){
        mApi.login(email, password, listener);

    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }

    public List<Schedule> getSchedule(){return mSchedule; }

    public void loadSchedule(APIListener listener) {
        mApi.loadSchedule(listener);
    }
}
