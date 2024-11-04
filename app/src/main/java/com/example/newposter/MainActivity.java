package com.example.newposter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersListener {

    private Button buttonAddToWatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecycleView = findViewById(R.id.posterRecyclerView);
        buttonAddToWatchList = findViewById(R.id.buttonAddToWatchList);

        //Sample Movies
        List<Poster> posterList = new ArrayList<>();

        Poster the100 = new Poster();
        the100.image = R.drawable.intersteller;
        the100.name ="123";
        the100.createdBy = "ABC";
        the100.rating = 5f;
        the100.story = "test test Hello Space";
        posterList.add(the100);

        Poster random1 = new Poster();
        random1.image = R.drawable.wolf;
        random1.name ="Wolf of Wall Street";
        random1.createdBy = "Some Guy";
        random1.rating = 3f;
        random1.story = "test test Hello Wolf";
        posterList.add(random1);

        Poster idk = new Poster();
        idk.image = R.drawable.idk;
        idk.name ="Aviator";
        idk.createdBy = "Some Guy";
        idk.rating = 3f;
        idk.story = "test test Hello Aviator";
        posterList.add(idk);

        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecycleView.setAdapter(posterAdapter);

        //Suppose to create button with selected elements
        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for (int i = 0;i<selectPosters.size();i++){
                    if(i==0){
                        posterNames.append(selectPosters.get(i).name);
                    }else{
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this,posterNames.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPosterAction(boolean isSelected) {
        if(isSelected){
            buttonAddToWatchList.setVisibility(View.VISIBLE);
        }else{
            buttonAddToWatchList.setVisibility(View.GONE);
        }
    }
}