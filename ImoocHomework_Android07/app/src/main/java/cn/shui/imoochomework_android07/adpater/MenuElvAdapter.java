package cn.shui.imoochomework_android07.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import cn.shui.imoochomework_android07.R;
import cn.shui.imoochomework_android07.bean.Menu;

public class MenuElvAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<Menu> mMenuList;

    public MenuElvAdapter(Context mContext, List<Menu> mMenuList) {
        this.mContext = mContext;
        this.mMenuList = mMenuList;
    }

    @Override
    public int getGroupCount() {
        return mMenuList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mMenuList.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mMenuList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mMenuList.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.parent_exapnd_list_view,  parent, false);

            holder = new ParentViewHolder();
            holder.mMenuTv = convertView.findViewById(R.id.menu_tv);
            convertView.setTag(holder);
        } else {
            holder = (ParentViewHolder) convertView.getTag();
        }
        holder.mMenuTv.setText(mMenuList.get(groupPosition).getMenuName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.child_expand_list_view, parent, false);
            holder = new ChildViewHolder();
            holder.mMenuItemTv = convertView.findViewById(R.id.child_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        holder.mMenuItemTv.setText(mMenuList.get(groupPosition).getChildren().get(childPosition).getmMenuItemName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ParentViewHolder {
        TextView mMenuTv;
    }

    private static class ChildViewHolder {
        TextView mMenuItemTv;
    }
}
