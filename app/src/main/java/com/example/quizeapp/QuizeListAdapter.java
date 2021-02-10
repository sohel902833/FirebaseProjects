package com.example.quizeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class QuizeListAdapter extends RecyclerView.Adapter<QuizeListAdapter.QuizeViewHolder> {

    private List<QuizeListModule> quizelistmodules;
  private   OnQuizeListItemClick onQuizeListItemClick;

  public QuizeListAdapter(OnQuizeListItemClick onQuizeListItemClick){
            this.onQuizeListItemClick=onQuizeListItemClick;
  }


    public void setQuizelistmodules(List<QuizeListModule> quizelistmodules) {
        this.quizelistmodules = quizelistmodules;
    }

    @NonNull
    @Override
    public QuizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent,false);


        return new QuizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizeViewHolder holder, int position) {
        holder.listTitle.setText(quizelistmodules.get(position).getName());
        String imageUrl=quizelistmodules.get(position).getImage();
        Glide
                .with(holder.itemView.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(holder.listImage);
        String listdesk=quizelistmodules.get(position).getDesc();
        if(listdesk.length() >  150){
            listdesk=listdesk.substring(0,150);
        }
        holder.listDesc.setText(listdesk+"............");
        holder.listLevel.setText(quizelistmodules.get(position).getLevel());




    }

    @Override
    public int getItemCount() {
        if (quizelistmodules == null){
           return  0;
        }else {
            return quizelistmodules.size();
        }
    }

    public class QuizeViewHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView listImage;
        private TextView listTitle;
        private TextView listDesc;
        private TextView listLevel;
        private Button listBtn;



        public QuizeViewHolder(@NonNull View itemView) {
            super(itemView);

            listImage = itemView.findViewById(R.id.list_image);
            listTitle = itemView.findViewById(R.id.list_title);
            listDesc = itemView.findViewById(R.id.list_desc);
            listLevel = itemView.findViewById(R.id.list_difficulty);
            listBtn = itemView.findViewById(R.id.list_btn);


            listBtn.setOnClickListener( this);



        }

        @Override
        public void onClick(View v) {
                onQuizeListItemClick.onItemClick(getAdapterPosition());
        }
    }

    public interface  OnQuizeListItemClick{
        void onItemClick(int position);
    }




}
