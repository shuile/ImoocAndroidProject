package cn.imooc.imoochomework_android05.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.imooc.imoochomework_android05.R;
import cn.imooc.imoochomework_android05.entity.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, null));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
        News news = newsList.get(i);
        newsViewHolder.newsContentTv.setText(news.getContent());
        newsViewHolder.newsFromTv.setText(news.getNewsFrom());
        newsViewHolder.seeNumberTv.setText("" + news.getSeeNumber());
        newsViewHolder.dianNumberTv.setText("" + news.getDianNumber());
        newsViewHolder.newsPicIv.setImageResource(news.getNewsPic());
    }

    @Override
    public int getItemCount() {
        return null != newsList ? newsList.size() : 0;
    }
}

class NewsViewHolder extends RecyclerView.ViewHolder {

    public TextView newsContentTv;
    public TextView newsFromTv;
    public TextView seeNumberTv;
    public TextView dianNumberTv;
    public ImageView newsPicIv;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        newsContentTv = (TextView) itemView.findViewById(R.id.news_content_tv);
        newsFromTv = (TextView) itemView.findViewById(R.id.news_from_tv);
        seeNumberTv = (TextView) itemView.findViewById(R.id.see_number_tv);
        dianNumberTv = (TextView) itemView.findViewById(R.id.dian_number_tv);
        newsPicIv = (ImageView) itemView.findViewById(R.id.news_pic_iv);
    }
}