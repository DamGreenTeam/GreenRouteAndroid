package com.svalero.greenroute.feature.road_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.svalero.greenroute.R;
import com.svalero.greenroute.data.Road;

import java.util.ArrayList;

public class RoadListActivity extends AppCompatActivity implements RoadListContract.View{

    private RoadListPresenter presenter;

    private RecyclerView recyclerView;
    private LinearLayout layoutError;
    private RecyclerView.LayoutManager lmanager;
    private Button retry;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_list);

        initComponents();
        presenter = new RoadListPresenter(this);
        presenter.getRoads(this);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                layoutError.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                presenter.getRoads(getBaseContext());
            }
        });

    }

    @Override
    public void succes(ArrayList<Road> roads) {
        loading.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        lmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lmanager);
        RoadListAdapter adapter = new RoadListAdapter(roads);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        loading.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    public void initComponents(){
        recyclerView = findViewById(R.id.rvRoad);
        layoutError = findViewById(R.id.activity_road_list_layout_error);
        retry = findViewById(R.id.bRetry);
        loading = findViewById(R.id.pbLoading);
    }
}