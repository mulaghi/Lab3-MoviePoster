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

        Poster Inter = new Poster();
        Inter.image = R.drawable.intersteller;
        Inter.name ="123";
        Inter.createdBy = "ABC";
        Inter.rating = 5f;
        Inter.story = "test test Hello Space";
        posterList.add(Inter);

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

        Poster idk1 = new Poster();
        idk1.image = R.drawable.idk;
        idk1.name ="Aviator";
        idk1.createdBy = "Some Guy";
        idk1.rating = 3f;
        idk1.story = "test test Hello Aviator";
        posterList.add(idk1);

        Poster idk2 = new Poster();
        idk2.image = R.drawable.idk;
        idk2.name ="Aviator";
        idk2.createdBy = "Some Guy";
        idk2.rating = 3f;
        idk2.story = "test test Hello Aviator";
        posterList.add(idk2);

        Poster Inter1 = new Poster();
        Inter1.image = R.drawable.intersteller;
        Inter1.name ="123";
        Inter1.createdBy = "ABC";
        Inter1.rating = 5f;
        Inter1.story = "test test Hello Space";
        posterList.add(Inter1);

        Poster random2 = new Poster();
        random2.image = R.drawable.wolf;
        random2.name ="Wolf of Wall Street";
        random2.createdBy = "Some Guy";
        random2.rating = 3f;
        random2.story = "test test Hello Wolf";
        posterList.add(random2);

        Poster idk7 = new Poster();
        idk7.image = R.drawable.idk;
        idk7.name ="Aviator";
        idk7.createdBy = "Some Guy";
        idk7.rating = 3f;
        idk7.story = "test test Hello Aviator";
        posterList.add(idk7);

        Poster idk8 = new Poster();
        idk8.image = R.drawable.idk;
        idk8.name ="Aviator";
        idk8.createdBy = "Some Guy";
        idk8.rating = 3f;
        idk8.story = "test test Hello Aviator";
        posterList.add(idk8);

        Poster idk9 = new Poster();
        idk9.image = R.drawable.idk;
        idk9.name ="Aviator";
        idk9.createdBy = "Some Guy";
        idk9.rating = 3f;
        idk9.story = "test test Hello Aviator";
        posterList.add(idk9);

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