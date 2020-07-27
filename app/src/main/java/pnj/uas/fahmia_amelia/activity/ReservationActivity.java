package pnj.uas.fahmia_amelia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import pnj.uas.fahmia_amelia.R;
import pnj.uas.fahmia_amelia.model.Requests;

public class ReservationActivity extends AppCompatActivity {

    private static final String TAG = "BellaMiaBodySpa";
    private DatabaseReference database;

    private EditText edtName, edtTreatment, edtDate, edtAddress, edtTlp, edtEmail;
    private ProgressDialog loading;
    private Button actionCancel, actionSimpan;

    private String sPid, sPname, sPtreatment, sPdate, sPaddress, sPtlp, sPemail;

    private DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        database = FirebaseDatabase.getInstance().getReference();

        sPid = getIntent().getStringExtra("id");
        sPname = getIntent().getStringExtra("name");
        sPtreatment = getIntent().getStringExtra("treatment");
        sPdate = getIntent().getStringExtra("date");
        sPaddress = getIntent().getStringExtra("address");
        sPtlp = getIntent().getStringExtra("tlp");
        sPemail = getIntent().getStringExtra("email");

        edtName = findViewById(R.id.edtName);
        edtTreatment = findViewById(R.id.edtTreatment);
        edtDate = findViewById(R.id.edtDate);
        edtAddress = findViewById(R.id.edtAddress);
        edtTlp = findViewById(R.id.edtTlp);
        edtEmail = findViewById(R.id.edtEmail);
        actionSimpan = findViewById(R.id.actionSimpan);
        actionCancel = findViewById(R.id.actionCancel);

        edtName.setText(sPname);
        edtTreatment.setText(sPtreatment);
        edtDate.setText(sPdate);
        edtAddress.setText(sPaddress);
        edtTlp.setText(sPtlp);
        edtEmail.setText(sPemail);

        if (sPid.equals("")) {
            actionSimpan.setText("Save");
            actionCancel.setText("Cancel");
        } else {
            actionSimpan.setText("Update");
            actionCancel.setText("Delete");
        }

        edtDate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                Calendar myCalendar = Calendar.getInstance();
                int day = myCalendar.get(Calendar.DAY_OF_MONTH);
                int month = myCalendar.get(Calendar.MONTH);
                int year = myCalendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(ReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtDate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, day, month, year);
                dpd.show();
            }
        });

        actionSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sname = edtName.getText().toString();
                String Streatment = edtTreatment.getText().toString();
                String Sdate = edtDate.getText().toString();
                String Saddress = edtAddress.getText().toString();
                String Stlp = edtTlp.getText().toString();
                String Semail = edtEmail.getText().toString();

                if (actionSimpan.getText().equals("Save")) {
                    if (Sname.equals("")) {
                        edtName.setError("Enter Your Name");
                        edtName.requestFocus();
                    } else if (Streatment.equals("")) {
                        edtTreatment.setError("Enter Your Treatment");
                        edtTreatment.requestFocus();
                    } else if (Sdate.equals("")) {
                        edtDate.setError("Enter Your Date Book");
                        edtDate.requestFocus();
                    } else if (Saddress.equals("")) {
                        edtAddress.setError("Enter Your Address");
                        edtAddress.requestFocus();
                    } else if (Stlp.equals("")) {
                        edtTlp.setError("Enter Your Phone Number");
                        edtTlp.requestFocus();
                    } else if (Semail.equals("")) {
                        edtEmail.setError("Enter Your Treatment");
                        edtEmail.requestFocus();
                    } else {
                        loading = ProgressDialog.show(ReservationActivity.this,
                                null,
                                "Please wait...",
                                true,
                                false);
                        submitUser(new Requests(
                                Sname.toLowerCase(),
                                Streatment.toLowerCase(),
                                Sdate.toLowerCase(),
                                Saddress.toLowerCase(),
                                Stlp.toLowerCase(),
                                Semail.toLowerCase()));
                    }
                } else {
                    if (Sname.equals("")) {
                        edtName.setError("Enter Your Name");
                        edtName.requestFocus();
                    } else if (Streatment.equals("")) {
                        edtTreatment.setError("Enter Your Treatment");
                        edtTreatment.requestFocus();
                    } else if (Sdate.equals("")) {
                        edtDate.setError("Enter Your Date Book");
                        edtDate.requestFocus();
                    } else if (Saddress.equals("")) {
                        edtAddress.setError("Enter Your Address");
                        edtAddress.requestFocus();
                    } else if (Stlp.equals("")) {
                        edtTlp.setError("Enter Your Phone Number");
                        edtTlp.requestFocus();
                    } else if (Semail.equals("")) {
                        edtEmail.setError("Enter Your Treatment");
                        edtEmail.requestFocus();
                    } else {
                        loading = ProgressDialog.show(ReservationActivity.this,
                                null,
                                "Please wait...",
                                true,
                                false);
                        editUser(new Requests(
                                Sname.toLowerCase(),
                                Streatment.toLowerCase(),
                                Sdate.toLowerCase(),
                                Saddress.toLowerCase(),
                                Stlp.toLowerCase(),
                                Semail.toLowerCase()), sPid);
                    }
                }
            }
        });

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionCancel.getText().equals("Cancel")) {
                    finish();
                } else {
                    database.child("Request")
                            .child(sPid)
                            .removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(ReservationActivity.this,
                                            "Data Successfully Deleted",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }

    private void submitUser(Requests requests) {
        database.child("Request")
                .push()
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();

                        edtName.setText("");
                        edtTreatment.setText("");
                        edtDate.setText("");
                        edtAddress.setText("");
                        edtTlp.setText("");
                        edtEmail.setText("");

                        Toast.makeText(ReservationActivity.this,
                                "Data Successsfully Added",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void editUser (Requests requests, String id) {
        database.child("Request")
                .child(id)
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();

                        edtName.setText("");
                        edtTreatment.setText("");
                        edtDate.setText("");
                        edtAddress.setText("");
                        edtTlp.setText("");
                        edtEmail.setText("");

                        Toast.makeText(ReservationActivity.this,
                                "Data Successsfully Updated",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
