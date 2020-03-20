package com.example.application1.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.application1.R;
import com.example.application1.model.HomeTimeLineModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: Administrator
 * @date: 2020-03-20
 */
public class HomeLineAdapter extends RecyclerView.Adapter<HomeLineAdapter.ViewHolder> {


    private List<HomeTimeLineModel.StatusesBean> homeList;
    private Context context;

    public HomeLineAdapter(List<HomeTimeLineModel.StatusesBean> homeList, Context context) {
        this.homeList = homeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(homeList.get(position).getUser().getProfile_image_url()).into(holder.imgItemHead);
        holder.tvItemName.setText(homeList.get(position).getUser().getName());
        holder.tvItemContent.setText(homeList.get(position).getText());
        holder.tvItemDate.setText(homeList.get(position).getCreated_at());

        if(TextUtils.isEmpty(homeList.get(position).getBmiddle_pic())){
            holder.imgItemContent.setVisibility(View.GONE);
        }else{
            Glide.with(context).load(homeList.get(position).getBmiddle_pic()).into(holder.imgItemContent);
        }

        holder.tvItemFoot1.setText(homeList.get(position).getReposts_count()+"");
        holder.tvItemFoot2.setText(homeList.get(position).getAttitudes_count()+"");
        holder.tvItemFoot3.setText(homeList.get(position).getComments_count()+"");

    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public void addList(List<HomeTimeLineModel.StatusesBean> list){
        homeList.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItemHead;
        TextView tvItemName;
        TextView tvItemDate;
        Button btnItemAtten;
        TextView tvItemContent;
        ImageView imgItemContent;
        TextView tvItemFoot1;
        TextView tvItemFoot2;
        TextView tvItemFoot3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItemHead = itemView.findViewById(R.id.image_item_head);
            tvItemName = itemView.findViewById(R.id.tv_item_name);
            tvItemDate = itemView.findViewById(R.id.tv_item_date);
            btnItemAtten = itemView.findViewById(R.id.btn_item_atten);
            tvItemContent = itemView.findViewById(R.id.tv_item_content);
            imgItemContent = itemView.findViewById(R.id.img_item_content);
            tvItemFoot1 = itemView.findViewById(R.id.tv_item_foot1);
            tvItemFoot2 = itemView.findViewById(R.id.tv_item_foot2);
            tvItemFoot3 = itemView.findViewById(R.id.tv_item_foot3);
        }
    }
}
