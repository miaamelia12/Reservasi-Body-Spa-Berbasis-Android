package pnj.uas.fahmia_amelia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pnj.uas.fahmia_amelia.R;
import pnj.uas.fahmia_amelia.adapter.RequestAdapterRecyclerView;
import pnj.uas.fahmia_amelia.model.Requests;

public class ListReservationActivity extends AppCompatActivity {

    private DatabaseReference database;

    private ArrayList<Requests> daftarReq;
    private RequestAdapterRecyclerView requestAdapterRecyclerView;

    private RecyclerView rc_list_request;
    private ProgressDialog loading;
    private FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reservation);

        database = FirebaseDatabase.getInstance().getReference();

        rc_list_request = findViewById(R.id.rc_list_request);
        fab_add = findViewById(R.id.fab_add);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc_list_request.setLayoutManager(mLayoutManager);
        rc_list_request.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(ListReservationActivity.this,
                null,
                "Please wait...",
                true,
                false);

        database.child("Request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Saat ada data baru, masukkan datanya ke ArrayList
                daftarReq = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /*
                    * Mapping data pada DataSnapshot ke dalam object
                    * Dan juga menyimpan primary key pada object
                    * untuk keperluan Edit dan Delete data
                     */

                    Requests requests = noteDataSnapshot.getValue(Requests.class);
                    requests.setKey(noteDataSnapshot.getKey());

                    daftarReq.add(requests);
                }
                requestAdapterRecyclerView = new RequestAdapterRecyclerView(daftarReq, ListReservationActivity.this);
                rc_list_request.setAdapter(requestAdapterRecyclerView);
                loading.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                    loading.dismiss();
            }
        });

        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListReservationActivity.this, ReservationActivity.class)
                        .putExtra("id","")
                        .putExtra("name","")
                        .putExtra("treatment","")
                        .putExtra("date","")
                        .putExtra("address","")
                        .putExtra("tlp","")
                        .putExtra("email",""));
            }
        });
    }
}
