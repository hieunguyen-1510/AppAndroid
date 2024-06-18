package hieu.tlus.nguyenlehoaihieu.phdhtl.khoa63.didong;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hieu.tlus.nguyenlehoaihieu.phdhtl.khoa63.didong.model.SinhVien;

public class AddStudentActivity extends AppCompatActivity {
    EditText txtmasv, txttensv, txtnamsinh;
    RadioButton rdnam, rdnu;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Thêm sinh viên mới");
        toolbar.setBackgroundColor(Color.parseColor("#0FFF0F"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        txtmasv = findViewById(R.id.edtmasv);
        txttensv = findViewById(R.id.edttensv);
        txtnamsinh = findViewById(R.id.edtnamsinh);
        rdnam = findViewById(R.id.rdnam);
        rdnu = findViewById(R.id.rdnu);
        btnadd = findViewById(R.id.btnluu);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masv = txtmasv.getText().toString();
                String tensv = txttensv.getText().toString();
                String namsinhStr = txtnamsinh.getText().toString();
                String gioitinh = rdnam.isChecked() ? "Nam" : "Nữ";
                int namsinh = Integer.parseInt(namsinhStr);

                int anhsv = R.drawable.sv01;

                SinhVien sv = new SinhVien(masv, tensv, gioitinh, namsinh, anhsv);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("new_student", sv);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
