package com.gy.smartbardemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<Movie> movies=new ArrayList<>();

    private CallBack callBack;

    Boolean isC;

    public MyAdapter(CallBack callBack){
        this.callBack=callBack;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        if (isC){
            holder.tvName.setText("电影名称:"+movies.get(position).getMovieName());
            if (movies.get(position).getMovieContent()!=null){
                holder.tvContent.setText("电影评论:"+movies.get(position).getMovieContent());
            }else {
                holder.tvContent.setText("电影评论:");
            }
        }else {
            holder.tvName.setText("MovieName:"+movies.get(position).getMovieName());
            if (movies.get(position).getEMovieContent()!=null){
                holder.tvContent.setText("MovieComment:"+movies.get(position).getMovieContent());
            }else {
                holder.tvContent.setText("MovieComment:");
            }
        }
        holder.code.setText(movies.get(position).getCode());
        holder.itemView.setTag(position);

        holder.imageView.setImageResource(R.drawable.ic_star_checked);

        if (callBack!=null){
            callBack.getView(holder.itemView,position);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setDatas(List<Movie> datas,Boolean isC){
        this.movies=datas;
        this.isC=isC;
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName;
        TextView tvContent;
        TextView code;
        public MyHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.move_img);
            tvContent=itemView.findViewById(R.id.tvContent);
            tvName=itemView.findViewById(R.id.tvName);
            code=itemView.findViewById(R.id.code);
        }
    }
   public interface CallBack{
        void getView(View view,int position);
        void onClick(View view,int position);
   }
}
