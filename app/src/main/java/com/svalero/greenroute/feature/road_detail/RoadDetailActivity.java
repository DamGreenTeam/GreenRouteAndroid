package com.svalero.greenroute.feature.road_detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.svalero.greenroute.R;
import com.svalero.greenroute.data.Road;

import org.w3c.dom.Text;

public class RoadDetailActivity extends AppCompatActivity {

    private ImageView imageDetail;
    private TextView nameDetail;
    private TextView dateDetail;
    private TextView lengthDetail;
    private TextView tollDetail;
    private Road road;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_detail);
        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            road = (Road) getIntent().getSerializableExtra("myRoad");
        }

        showRoad();

    }

    public void initComponents(){
        imageDetail = findViewById(R.id.ivRoadDetailImage);
        nameDetail = findViewById(R.id.tvRoadDetailName);
        dateDetail = findViewById(R.id.tvRoadDetailDate);
        lengthDetail = findViewById(R.id.tvRoadDetailLength);
        tollDetail = findViewById(R.id.tvRoadDetailToll);
    }

    public void showRoad(){
        Picasso.get().load(road.getImage()).into(imageDetail);
        nameDetail.setText(road.getName());
        dateDetail.setText(road.getBuildDate());
        lengthDetail.setText(String.valueOf(road.getLength()));

        String option = "";
        option = (road.isToll()) ? "SI" : "NO";
        tollDetail.setText(option);

    }
}