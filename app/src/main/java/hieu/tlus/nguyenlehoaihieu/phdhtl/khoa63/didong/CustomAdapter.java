package hieu.tlus.nguyenlehoaihieu.phdhtl.khoa63.didong;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.DialogInterface;
import android.app.AlertDialog;

import java.util.ArrayList;

import hieu.tlus.nguyenlehoaihieu.phdhtl.khoa63.didong.model.SinhVien;

public class CustomAdapter extends ArrayAdapter<SinhVien> {
    private ArrayList<SinhVien> arrsv;
    private final Activity context;
    private int lastPosition = -1;

    public CustomAdapter(Activity context, ArrayList<SinhVien> arrsv) {
        super(context, R.layout.item_sv, arrsv);
        this.context = context;
        this.arrsv = arrsv;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_sv, null, true);

        final SinhVien sv = arrsv.get(position);
        Log.d("masv", sv.getMasv());

        TextView txtmasv = rowView.findViewById(R.id.txtmasv);
        ImageView imganhsv = rowView.findViewById(R.id.imgsv);
        TextView txttensv = rowView.findViewById(R.id.txttensv);
        TextView txtgt = rowView.findViewById(R.id.txtgt);
        Button btndelete = rowView.findViewById(R.id.btnxoa);

        txtmasv.setText(sv.getMasv());
        txttensv.setText(sv.getHovaten());
        txtgt.setText(sv.getGioitinh());
        imganhsv.setImageResource(sv.getAnhsv());

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        rowView.startAnimation(animation);
        lastPosition = position;

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("POSITION", "Clicked on: " + arrsv.get(position).getHovaten());
                Intent in = new Intent(context, StudentDetailActivity.class);
                in.putExtra("sinhVien", sv); //
                context.startActivity(in);
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có đồng ý xóa " + sv.getHovaten() + " này không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrsv.remove(position);
                                notifyDataSetChanged();
                                Log.d("CustomAdapter", "Deleted student: " + sv.getMasv());
                            }
                        })
                        .setNegativeButton("Không", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        return rowView;
    }
}
