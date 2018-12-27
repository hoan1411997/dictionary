package com.khanh.dictionary.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khanh.dictionary.R;
import com.khanh.dictionary.model.VocabularyType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemTypeAdapter extends RecyclerView.Adapter<ItemTypeAdapter.ItemView> {
    List<VocabularyType> vocabularyData = new ArrayList<>();
    private Context mContext;

    public ItemTypeAdapter(List<VocabularyType> vocabularyData, Context mContext, OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
        this.vocabularyData = vocabularyData;
        this.mContext = mContext;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_rscv, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(final ItemView holder, final int position) {

        holder.txtId.setText(vocabularyData.get(position).getId() + "");
        holder.txtContent.setText(vocabularyData.get(position).getName() + "");
        holder.txtTypeId.setText(vocabularyData.get(position).getCode() + "");


        holder.txtDesc.setText(vocabularyData.get(position).getDesc() + "");

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onDel(position);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onEdit(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onclick(position);
            }
        });
    }

    OnItemClick onItemClick;

    public interface OnItemClick {
        void onclick(int position);

        void onEdit(int position);

        void onDel(int position);
    }

    @Override
    public int getItemCount() {
        return vocabularyData.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_id)
        TextView txtId;
        @BindView(R.id.txt_content)
        TextView txtContent;
        @BindView(R.id.txt_type_id)
        TextView txtTypeId;
        @BindView(R.id.txt_type_content)
        TextView txtTypeContent;
        @BindView(R.id.txt_desc)
        TextView txtDesc;
        @BindView(R.id.ic_del)
        ImageView del;
        @BindView(R.id.ic_edit)
        ImageView edit;

        public ItemView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
