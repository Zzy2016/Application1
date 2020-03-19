package com.example.application1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.application1.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application1.model.RecommendModel;

/**
 * @author: Administrator
 * @date: 2020-03-18
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {


    private List<RecommendModel.ListBean> recommendList;
    private Context context;


    public RecommendAdapter(List<RecommendModel.ListBean> recommendList, Context context) {
        this.recommendList = recommendList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tvItem.setText(recommendList.get(position).getTitle());
        Glide.with(context).load(recommendList.get(position).getPic()).into(holder.imageItem);
        holder.tvNumLeft.setText(recommendList.get(position).getPlay() + "");
        holder.tvNumRight.setText(recommendList.get(position).getFavorites() + "");

//        holder.imageItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteItem(holder.getAdapterPosition());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return recommendList.size();
    }


    public void addList(List<RecommendModel.ListBean> list) {
        recommendList.addAll(list);
        notifyDataSetChanged();
    }


    /*
     * 不使用  recommendList.remove(position)
     *删除之后列表size不变，
     * 使用notifyItemRemoved(position)可以解决
     * */
    public void deleteItem(int position) {
        recommendList.remove(position);
        notifyItemRemoved(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem;
        TextView tvItem;
        ImageView imageItemLeft;
        ImageView imageItemRight;
        TextView tvNumLeft;
        TextView tvNumRight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.image_item);
            tvItem = itemView.findViewById(R.id.tv_item);
            imageItemLeft = itemView.findViewById(R.id.image_item_left);
            imageItemRight = itemView.findViewById(R.id.image_item_right);
            tvNumLeft = itemView.findViewById(R.id.tv_num_left);
            tvNumRight = itemView.findViewById(R.id.tv_num_right);
        }
    }


}
