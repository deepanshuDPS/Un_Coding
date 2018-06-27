package com.Rowdy.Apps.un_coding;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;

public class CexListView extends BaseExpandableListAdapter
{

    private Context context;
    private List<String> eListTitle;
    private HashMap<String, List<String>> eListDetail;

    public CexListView(Context context, List<String> eListTitle, HashMap<String, List<String>> eListDetail)
    {
        this.context = context;
        this.eListTitle = eListTitle;
        this.eListDetail = eListDetail;
    }

    @Override
    public Object getChild(int listPos, int elistPos)
    {
        return this.eListDetail.get(this.eListTitle.get(listPos)).get(elistPos);
    }
    @Override
    public long getChildId(int listPosition, int expandedListPosition)
    {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.eListItem);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "rewards.ttf");
        expandedListTextView.setTypeface(face);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition)
    {
        return this.eListDetail.get(this.eListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition)
    {
        return this.eListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this.eListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition)
    {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "reward.otf");
        listTitleTextView.setTypeface(face);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
