package pnj.uas.fahmia_amelia.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pnj.uas.fahmia_amelia.R;
import pnj.uas.fahmia_amelia.activity.ReservationActivity;
import pnj.uas.fahmia_amelia.model.Requests;

public class RequestAdapterRecyclerView extends RecyclerView.Adapter<RequestAdapterRecyclerView.MyViewHolder> {

    private List<Requests> reservationList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView txt_name, txt_treatment, txt_date;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rl_layout);
            txt_name = view.findViewById(R.id.txt_name);
            txt_treatment = view.findViewById(R.id.txt_treatment);
            txt_date = view.findViewById(R.id.txt_date);
        }
    }

    public RequestAdapterRecyclerView(List<Requests> reservationList, Activity activity) {
        this.reservationList = reservationList;
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapterRecyclerView.MyViewHolder holder, int position) {
        final Requests reservation = reservationList.get(position);

        holder.txt_name.setText(reservation.getName());
        holder.txt_treatment.setText(reservation.getTreatment());
        holder.txt_date.setText(reservation.getDate_book());

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDetail = new Intent(mActivity, ReservationActivity.class);

                goDetail.putExtra("id", reservation.getKey());
                goDetail.putExtra("name", reservation.getName());
                goDetail.putExtra("treatment", reservation.getTreatment());
                goDetail.putExtra("date", reservation.getDate_book());
                goDetail.putExtra("address", reservation.getAddress());
                goDetail.putExtra("tlp", reservation.getTlp());
                goDetail.putExtra("email", reservation.getEmail());

                mActivity.startActivity(goDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }
}
