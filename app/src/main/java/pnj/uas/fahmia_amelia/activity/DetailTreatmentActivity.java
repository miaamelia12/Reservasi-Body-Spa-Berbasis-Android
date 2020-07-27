package pnj.uas.fahmia_amelia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pnj.uas.fahmia_amelia.R;

public class DetailTreatmentActivity extends AppCompatActivity {

    ImageView imgTreatment;
    TextView txtTitle, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_treatment);
        imgTreatment = findViewById(R.id.imgTreatment);
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();

        if (extras!=null) {
            getSupportActionBar().setTitle(extras.getString("title", ""));
            txtTitle.setText(extras.getString("title", ""));
            txtDescription.setText(extras.getString("description", ""));
            Picasso.get().load(extras.getString("image", "")).into(imgTreatment);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
