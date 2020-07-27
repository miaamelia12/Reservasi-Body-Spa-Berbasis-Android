package pnj.uas.fahmia_amelia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import pnj.uas.fahmia_amelia.activity.DetailTreatmentActivity;
import pnj.uas.fahmia_amelia.R;
import pnj.uas.fahmia_amelia.adapter.AdapterTreatment;
import pnj.uas.fahmia_amelia.model.TreatmentModel;

public class TreatmentFragment extends Fragment {

    ListView listView;
    AdapterTreatment adapterTreatment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_treatment_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        adapterTreatment = new AdapterTreatment(getActivity(), R.layout.item_list_treatment);
        listView.setAdapter(adapterTreatment);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TreatmentModel datas = (TreatmentModel) parent.getAdapter().getItem(position);

                Intent intent = new Intent(getActivity(), DetailTreatmentActivity.class);
                intent.putExtra("title", datas.getTitle());
                intent.putExtra("description", datas.getDescription());
                intent.putExtra("image", datas.getImage());

                startActivity(intent);
            }
        });
        buatDataTreatment();
    }

    void buatDataTreatment() {
        String[] title = {
                "Body Treatment",
                "Face Treatment",
                "Hair Treatment",
                "Hand and Foot Treatment",
                "Make Up",
                "Signature Treatment",
        };

        String[] description = {
                "Massage treatment is one of the most important aspects in achieving the total well-being. Massage is beneficial to calm the body & mind. When the muscles are relaxed, the joints and nervous system will also give a relaxed feeling and create a sense of freshness and new strength for the body.",
                "Facial treatments with Sariayu cosmetic series that can customized to your skin type and is very suitable because of natural ingredients for efficacious skin care",
                "This popular Indonesian head, neck and shoulder massage improves the blood circulation and revitalizes your hair & scalp, leaving them healthy and fresh. A special steamer session allows the rich hair-cream to penetrate your hair’s fibers, nourish and revitalize them prior to wash and blow dry Variants available to suit the scalp’s condition (carrot, ginseng, candle nut, aloe vera)",
                "Hand & Foot care uses fragrant of tapak wangi very beneficial to maintaining health and beauty nails and skin, eliminate odor, excess sweating in the palms of the hands and feet moreover give calm the mind and soul",
                "We can't get enough of this look. Winged liner, pale pink lips, and eye-catching highlights make for the perfect complement to this bride's berry-hued bouquet at her September wedding.",
                "A complete package of women beauty care. A distinguished massage toincrease the blood circulation, soothe nerves and muscles. Followed with a hotherbal bath the.A unique feminine smoking ceremony which aims to cleansewomen sexual organ and removes unpleasant odor, singles this out as aroyalaccasion for all spa goers",
        };

        String[] image = {
                "https://lh3.googleusercontent.com/g9cjOBAF2J95K4kgzD_b8quph3mkyX0C2tRpIFfGPNXMG7UCqKUQLgzC78lXu70W1mPYvJrUsZic-7AI1eBbL6yCEw=w1000",
                "https://media.allure.com/photos/5c2e8ec54325fe2d62c0943a/16:9/w_2560%2Cc_limit/how-often-should-you-get-a-facial-lede.jpg",
                "https://tradeclick.in/web/image/product.template/2244/image?unique=43648ae",
                "https://www.beautybymarysze.com.au/wp-content/uploads/2014/04/handfootpage.png",
                "https://www.north-westbrides.com/wp-content/uploads/2019/03/Wedding-Makeup-Artist-660x400.jpg",
                "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/7a/bc/60.jpg",
        };

        ArrayList<TreatmentModel> data = new ArrayList<>();

        for (int i=0; i < image.length; i++) {
            TreatmentModel model = new TreatmentModel();
            model.setImage(image[i]);
            model.setTitle(title[i]);
            model.setDescription(description[i]);
            data.add(model);
        }
        adapterTreatment.addAll(data);
        adapterTreatment.notifyDataSetChanged();
    }
}
