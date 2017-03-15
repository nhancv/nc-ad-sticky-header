package com.nhancv.sample;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.nhancv.stickyheader.StickyHeaderAdapter;
import com.nhancv.stickyheader.StickyHeaderDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by nhancao on 3/15/17.
 */

public class StyleNonKeepAdapter extends RecyclerView.Adapter<StyleNonKeepAdapter.ContentViewHolder> implements Filterable, StickyHeaderAdapter<StyleNonKeepAdapter.HeaderHolder> {

    private List<String> listsItems;
    private List<String> listFiltered;
    private String searchText;
    private List<Section> listSection;
    private StickyHeaderDecoration sectionDecoration;

    public StyleNonKeepAdapter() {
        this.listsItems = new ArrayList<>();
        this.listFiltered = new ArrayList<>();
        this.listSection = new ArrayList<>();
        this.sectionDecoration = new StickyHeaderDecoration(this, false);
    }

    public StickyHeaderDecoration getSectionDecoration() {
        return sectionDecoration;
    }

    /**
     * Add item to list
     *
     * @param item
     */
    public void addItem(String item) {
        add(item, -1);
    }

    /**
     * Generate Section
     */
    public void generateSection() {
        listSection.clear();
        Section index = new Section(-1, "-1");
        for (int i = 0; i < listFiltered.size(); i++) {
            Object item = listFiltered.get(i);
            String category = (String) item;
            if (!TextUtils.isEmpty(category) && !index.getTitle().equals(category)) {
                index.setHeaderPosition(index.getHeaderPosition() + 1);
                index.setTitle(category);
            }
            listSection.add(new Section(index.getHeaderPosition(), index.getTitle()));
        }
        if (sectionDecoration != null) {
            sectionDecoration.clearHeaderCache();
        }
    }

    /**
     * Add item to list by position
     *
     * @param item
     * @param position
     */
    public void add(String item, int position) {
        position = position == -1 ? getItemCount() : position;
        listFiltered.add(position, item);
        sortListsItems();
    }

    /**
     * Remove item by position
     *
     * @param position
     */
    public void remove(int position) {
        if (position < getItemCount()) {
            listFiltered.remove(position);
            sortListsItems();
        }
    }

    /**
     * Set list item
     *
     * @param listsItems
     */
    public void setListsItems(List<String> listsItems) {
        this.listsItems = listsItems;
        this.listFiltered = listsItems;
        sortListsItems();
    }

    /**
     * Sort list item
     */
    public void sortListsItems() {
        Collections.sort(listFiltered, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                if (lhs == null) {
                    return -1;
                }
                if (rhs == null) {
                    return 1;
                }
                if (lhs.equals(rhs)) {
                    return 0;
                }
                return 1;
            }
        });
        generateSection();
        notifyDataSetChanged();
    }

    @Override
    public StyleNonKeepAdapter.ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_view_rv_item, parent, false);
        return new ContentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StyleNonKeepAdapter.ContentViewHolder holder, int position) {
        Object item = listFiltered.get(position);
        holder.tvName.setText(Utils.highlightText(searchText, (String) item));
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    /**
     * Update search text
     *
     * @param searchText
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText;
        getFilter().filter(searchText);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.toString().length() == 0) {
                    results.count = listsItems.size();
                    results.values = listsItems;
                } else {
                    List<String> resultsData = new ArrayList<>();
                    String searchStr = constraint.toString();
                    for (String item : listsItems) {
                        if (Utils.isContainText(searchStr, item)) {
                            resultsData.add(item);
                        }
                    }
                    results.count = resultsData.size();
                    results.values = resultsData;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (searchText == null || searchText.length() == 0) {
                    listFiltered = listsItems;
                } else {
                    listFiltered = (List<String>) results.values;
                }
                sortListsItems();
            }
        };
    }

    @Override
    public long getHeaderId(int position) {
        return listSection.get(position).getHeaderPosition();
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_view_rvsession_header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewHolder, int position) {
        viewHolder.tvHeader.setText("Pos: " + getHeaderId(position) + " - Header " + listSection.get(position).getTitle());
    }

    public static final class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;

        public HeaderHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView) itemView.findViewById(R.id.tvHeader);
        }
    }

    /**
     * Define section
     */
    static class Section {
        int headerPosition;
        String title;

        public Section(int headerPosition, String title) {
            this.headerPosition = headerPosition;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getHeaderPosition() {
            return headerPosition;
        }

        public void setHeaderPosition(int headerPosition) {
            this.headerPosition = headerPosition;
        }
    }

}
