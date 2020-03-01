package cn.shui.testrecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shui on 2020-02-11
 *
 * 1、集成RecyclerView.Adapter
 * 2、绑定ViewHolder
 * 3、实现Adapter的相关方法
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> dataSource;
    private RecyclerView mRv;
    private OnItemClickListener onItemClickListener;
    private int addDataPosition = -1;

    public MyRecyclerViewAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRv = recyclerView;
        dataSource = new ArrayList<>();
    }

    public void setDataSource(List<String> dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 创建并且返回ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.my_recycler_view_item, parent, false));
    }

    /**
     * ViewHolder绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mIv.setImageResource(getIcon(position));
        holder.mTv.setText(dataSource.get(position));

        // 只在瀑布流布局中使用随机高度
        LinearLayout.LayoutParams params = null;
        if (mRv.getLayoutManager().getClass() == StaggeredGridLayoutManager.class) {
            params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getRandomHeight());
        } else {
            params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        holder.mTv.setLayoutParams(params);

        // 改变ItemView背景颜色
        Log.d("question", "onBindViewHolder: addDataPosition is " + addDataPosition + "  position is " + position);
        if (addDataPosition == position) {
            Log.d("question", "onBindViewHolder: ?????");
            holder.mItemView.setBackgroundColor(Color.RED);
        } else {
            holder.mItemView.setBackgroundColor(Color.parseColor("#A4D3EE"));
        }

        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用接口的回调方法
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    /**
     * 返回数据数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    private int getIcon(int position) {
        switch (position % 5) {
            case 0:
                return R.mipmap.a;
            case 1:
                return R.mipmap.b;
            case 2:
                return R.mipmap.c;
            case 3:
                return R.mipmap.d;
            case 4:
                return R.mipmap.e;
            default:
                break;
        }
        return 0;
    }

    /**
     * 返回不同的ItemView高度
     * @return
     */
    private int getRandomHeight() {
        return (int) (Math.random() * 1000);
    }

    /**
     * 添加一条数据
     * @param position
     */
    public void addData(int position) {
        addDataPosition = position;
        dataSource.add(position, "插入的数据");
        notifyItemInserted(position);

        // 刷新ItemView
        notifyItemRangeChanged(position, dataSource.size() - position);
    }

    /**
     * 删除一条数据
     * @param position
     */
    public void removeData(int position) {
        addDataPosition = -1;
        dataSource.remove(position);
        notifyItemRemoved(position);

        // 刷新ItemView
        notifyItemRangeChanged(position, dataSource.size() - position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View mItemView;
        ImageView mIv;
        TextView mTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mIv = itemView.findViewById(R.id.iv);
            mTv = itemView.findViewById(R.id.tv);
        }
    }

    /**
     * ItemView点击事件回调接口
     */
    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
