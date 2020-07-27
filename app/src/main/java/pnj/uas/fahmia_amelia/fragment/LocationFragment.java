package pnj.uas.fahmia_amelia.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pnj.uas.fahmia_amelia.R;

public class LocationFragment extends Fragment implements OnMapReadyCallback {

        GoogleMap map;

        public LocationFragment(){

        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v =  inflater.inflate(R.layout.layout_location_fragment, container, false);

            return v;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            SupportMapFragment mapFragment = (SupportMapFragment)
                    getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;

            LatLng spa = new LatLng(-29.789127,31.038447);
            MarkerOptions options = new MarkerOptions();
            options.position(spa).title("Bella Mia Body Spa");
            map.addMarker(options);
            map.moveCamera(CameraUpdateFactory.newLatLng(spa));

        }
}
