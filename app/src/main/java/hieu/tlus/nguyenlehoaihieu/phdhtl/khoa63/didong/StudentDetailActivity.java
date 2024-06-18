package hieu.tlus.nguyenlehoaihieu.phdhtl.khoa63.didong;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hieu.tlus.nguyenlehoaihieu.phdhtl.khoa63.didong.model.SinhVien;

public class StudentDetailActivity extends AppCompatActivity {

    TextView txtMasv, txtTensv, txtGioitinh, txtNamsinh;
    ImageView imgAnhsv;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        toolbar.setTitle("Thông tin chi tiết");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        txtMasv = findViewById(R.id.txtmasv);
        txtTensv = findViewById(R.id.txttensv);
        txtGioitinh = findViewById(R.id.txtgt);
        txtNamsinh = findViewById(R.id.txtnamsinh);
        imgAnhsv = findViewById(R.id.imgsv);

        // Get the position from the Intent
        position = getIntent().getIntExtra("position", -1);
        SinhVien sinhVien = (SinhVien) getIntent().getSerializableExtra("sinhVien");

        if (sinhVien != null) {
            Log.d("StudentDetailActivity", "Displaying details for: " + sinhVien.getHovaten());
            txtMasv.setText(sinhVien.getMasv());
            txtTensv.setText(sinhVien.getHovaten());
            txtGioitinh.setText(sinhVien.getGioitinh());
            txtNamsinh.setText(String.valueOf(sinhVien.getNamsinh()));
            imgAnhsv.setImageResource(sinhVien.getAnhsv());
        } else {
            Log.e("StudentDetailActivity", "SinhVien data is missing!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chitiet, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.camera) {
            Log.d("StudentDetailActivity", "Camera clicked");
        } else if (item.getItemId() == R.id.delete) {
            Log.d("StudentDetailActivity", "Delete clicked");

            // Pass the position back to MainActivity
            Intent intent = new Intent();
            intent.putExtra("position", position);
            setResult(RESULT_OK, intent);
            finish(); // Close the activity and return to MainActivity
        }
        return super.onOptionsItemSelected(item);
    }
}