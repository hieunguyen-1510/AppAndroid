package hieu.tlus.nguyenlehoaihieu.phdhtl.khoa63.didong;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import hieu.tlus.nguyenlehoaihieu.phdhtl.khoa63.didong.model.SinhVien;

public class MainActivity extends AppCompatActivity {
    ListView lvsv;
    ArrayList<SinhVien> arrsv;
    CustomAdapter adapter;

    Integer[] imgid = {
            R.drawable.sv01, R.drawable.sv02,
            R.drawable.sv03, R.drawable.sv04,
            R.drawable.sv05
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lvsv = findViewById(R.id.lvsv);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Danh sách sinh viên");
        toolbar.setBackgroundColor(Color.parseColor("#FFFF00"));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        arrsv = new ArrayList<>();
        arrsv.add(new SinhVien("sv01", "Nguyễn Hùng Dũng", "Nam", 1999, R.drawable.sv01));
        arrsv.add(new SinhVien("sv02", "Dương Tất Danh", "Nam", 2000, R.drawable.sv02));
        arrsv.add(new SinhVien("sv03", "Lê Trọng Phi", "Nam", 2001, R.drawable.sv03));
        arrsv.add(new SinhVien("sv04", "Cao Nguyen", "Nam", 2002, R.drawable.sv04));
        arrsv.add(new SinhVien("sv05", "Trần Chí Thanh", "Nam", 2003, R.drawable.sv05));

        Log.d("MainActivity", "Number of students: " + arrsv.size());
        adapter = new CustomAdapter(this, arrsv);
        lvsv.setAdapter(adapter);

        lvsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MainActivity", "Item clicked at position: " + position);
                // Show details activity
                showDetailsActivity(position);
            }
        });

        lvsv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MainActivity", "Item long clicked at position: " + position);
                // Show delete confirmation dialog
                return true;
            }
        });
    }

    private void showDetailsActivity(int position) {
        SinhVien sinhVien = arrsv.get(position);
        Log.d("MainActivity", "Starting details activity for: " + sinhVien.getHovaten());
        Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
        intent.putExtra("sinhVien", sinhVien);
        intent.putExtra("position", position); // Pass the position to StudentDetailActivity
        startActivityForResult(intent, 2); // Use a different request code for detail activity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.them) {
            // Open Add Student Activity
            startActivityForResult(new Intent(this, AddStudentActivity.class), 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Get new student data
            SinhVien newStudent = (SinhVien) data.getSerializableExtra("new_student");
            Log.d("MainActivity", "New student added: " + newStudent.getHovaten());
            arrsv.add(newStudent);
            adapter.notifyDataSetChanged();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            // Get the position to delete
            int position = data.getIntExtra("position", -1);
            if (position != -1) {
                Log.d("MainActivity", "Student deleted at position: " + position);
                arrsv.remove(position);
                adapter.notifyDataSetChanged();
            }
        }
    }
}