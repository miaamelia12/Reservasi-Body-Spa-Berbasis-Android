package pnj.uas.fahmia_amelia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import pnj.uas.fahmia_amelia.R;
import pnj.uas.fahmia_amelia.fragment.HomeFragment;
import pnj.uas.fahmia_amelia.model.TreatmentModel;

public class AdapterTreatment extends ArrayAdapter<TreatmentModel> {

    Context context;
    int resource;

    public AdapterTreatment(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Holder holder;
        if (convertView==null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);

            holder.imgTreatment = convertView.findViewById(R.id.imgTreatment);
            holder.txtTitle = convertView.findViewById(R.id.txtTitle);
            holder.txtDescription = convertView.findViewById(R.id.txtDescription);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtTitle.setText(getItem(position).getTitle());
        holder.txtDescription.setText(getItem(position).getDescription());
        Picasso.get().load(getItem(position).getImage()).into(holder.imgTreatment);

        return convertView;
    }

    class Holder {
        ImageView imgTreatment;
        TextView txtTitle, txtDescription;
    }
}
