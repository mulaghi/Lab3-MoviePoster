package com.example.newposter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    /**
     * Creates a new ViewHolder for the poster item view.
     *
     * @param parent the ViewGroup into which the new View will be added
     * @param viewType the view type of the new View
     * @return a new PosterViewHolder that holds the view for the poster item
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster ,parent,false));
    }
    /**
     * Binds the data for a poster to the provided ViewHolder.
     *
     * @param holder the ViewHolder to which the poster data will be bound
     * @param position the position of the poster in the list
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPoster(posterList.get(position));

    }
    /**
     * Returns the total number of posters in the list.
     *
     * @return the size of the poster list
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    private List<Poster> posterList;

    private PostersListener postersListener;

    /**
     * Returns a list of currently selected posters.
     *
     * @return a List of Poster objects selected
     */
    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for(Poster poster : this.posterList){
            if(poster.isSelected){
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    class PosterViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layoutPosters;
        View viewBackground;

        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;


        /**
         * Constructs a new PosterViewHolder with the specified item view.
         *
         * @param itemView the view for the poster item
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutposters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster= itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }
        /**
         * Binds the poster data to the views in the ViewHolder.
         *
         * @param poster the Poster object containing data to bind
         */
        void bindPoster(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textStory.setText(poster.story);
            textCreatedBy.setText(poster.createdBy);
            ratingBar.setRating(poster.rating);
            if(poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            }else{
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            //Suppose to handle Poster Selection
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if(getSelectedPosters().isEmpty()){
                            postersListener.onPosterAction(false);
                        }else{
                            viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                            imageSelected.setVisibility(View.VISIBLE);
                            poster.isSelected=true;
                            postersListener.onPosterAction(true);
                        }
                    }
                }
            });
        }
    }

}
