package com.svalero.greenroute.feature.road_list;

import android.content.Context;

import com.svalero.greenroute.data.Road;

import java.util.ArrayList;

public class RoadListPresenter implements RoadListContract.Presenter{

    private RoadListContract.View view;
    private RoadListModel model;

    public RoadListPresenter(RoadListContract.View view){
        this.view = view;
        this.model = new RoadListModel();
    }

    @Override
    public void getRoads(Context context) {
        model.getRoadsWS(context, new RoadListContract.Model.OnListRoadsListener() {
            @Override
            public void resolve(ArrayList<Road> roads) {
                view.succes(roads);
            }

            @Override
            public void reject(String message) {
                view.error(message);
            }
        });
    }

}
