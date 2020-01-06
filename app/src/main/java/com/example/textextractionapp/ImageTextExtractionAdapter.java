package com.example.textextractionapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ImageTextExtractionAdapter extends RecyclerView.Adapter<ImageTextExtractionAdapter.ImageTextExtractionViewHolder> {


    private ArrayList<ImageTextExtraction> imageTextExtractions;
    private ImageTextExtractionListener imageTextExtractionListener;

    public ImageTextExtractionAdapter(ArrayList<ImageTextExtraction> imageTextExtractions, ImageTextExtractionListener imageTextExtractionListener) {
        this.imageTextExtractions = imageTextExtractions;
        this.imageTextExtractionListener = imageTextExtractionListener;
    }

    @NonNull
    @Override
    public ImageTextExtractionAdapter.ImageTextExtractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_image, parent, false);
        return new ImageTextExtractionViewHolder(view , imageTextExtractionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageTextExtractionViewHolder holder, int position) {
       holder.textView.setText(imageTextExtractions.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (imageTextExtractions != null) ? imageTextExtractions.size() : 0;
    }

    public class ImageTextExtractionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imageView;
        private TextView textView;
        private ImageTextExtractionListener imageTextExtractionListener;
        public ImageTextExtractionViewHolder(View view , ImageTextExtractionListener imageTextExtractionListener){
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.image);
            this.textView = (TextView) view.findViewById(R.id.name);
            this.imageTextExtractionListener = imageTextExtractionListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            imageTextExtractionListener.onClickImageTextExtraction(getAdapterPosition());
        }
    }
}
