package com.example.fourthassigment.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fourthassigment.Model.DashModel;
import com.example.fourthassigment.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashHolder> {
    private Context context;
    private List<DashModel> dashModelList;

    public DashboardAdapter(Context context, List<DashModel> dashModelList) {
        this.context = context;
        this.dashModelList = dashModelList;
    }

    @NonNull
    @Override
    public DashHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.dashboard_layout,viewGroup,false);

        return new DashHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashHolder dashHolder, int i) {
        DashModel dashModel=dashModelList.get(i);
        dashHolder.bindData(dashModel);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DashHolder extends RecyclerView.ViewHolder {
        private ImageView imagedash;
        private TextView imgname;
        private TextView imgprice;
        private TextView imgdesc;


        public DashHolder(@NonNull View itemView) {
            super(itemView);
            imagedash=itemView.findViewById(R.id.iv_img_dash);
            imgname=itemView.findViewById(R.id.tv_img_name_dash);
            imgprice=itemView.findViewById(R.id.tv_img_price_dash);
            imgdesc=itemView.findViewById(R.id.tv_img_desc_dash);
        }

        public void bindData(DashModel dashModel) {
            imgname.setText(dashModel.getName());
            imgprice.setText(dashModel.getPrice());
            imgdesc.setText(dashModel.getDesc());


        }
    }
}
