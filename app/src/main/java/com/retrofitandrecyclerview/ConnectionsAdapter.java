package com.retrofitandrecyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hari Prasad on 10/21/16.
 */

public class ConnectionsAdapter extends RecyclerView.Adapter<ConnectionsAdapter.HubViewHolder> {
    class HubViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;

        public HubViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }


        public void populate(ContactModel sikkaUserModel) {
            tv_name.setText(sikkaUserModel.getName());
        }
    }

    private Context context;
    private List<ContactModel> contactModels;

    public ConnectionsAdapter(Context context, List<ContactModel> contactModels) {
        this.context = context;
        this.contactModels = contactModels;
    }

    public static void build(Context context, RecyclerView recyclerView, List<ContactModel> sikkaUserModels) {
        if (recyclerView.getAdapter() == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
            ConnectionsAdapter messageModels = new ConnectionsAdapter(context, sikkaUserModels);
            recyclerView.setAdapter(messageModels);
        } else {
            ((ConnectionsAdapter) recyclerView.getAdapter()).rebuild(sikkaUserModels);
        }
    }

    private void rebuild(List<ContactModel> sikkaUserModels) {
        this.contactModels = sikkaUserModels;
        notifyDataSetChanged();
    }

    @Override
    public HubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.row_contact, parent, false);
        return new HubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HubViewHolder holder, int position) {
        holder.populate(contactModels.get(position));
    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }
}
