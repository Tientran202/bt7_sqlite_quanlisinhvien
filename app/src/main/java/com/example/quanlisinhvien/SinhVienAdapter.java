package com.example.quanlisinhvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<SinhVien> congvieclist;

    @Override
    public int getCount() {
        return congvieclist.size();
    }

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> congvieclist) {
        this.context = context;
        this.layout = layout;
        this.congvieclist = congvieclist;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class Viewholder {
        TextView txtSinhVien;
        ImageView imgUpdate,imgDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        if(view == null){
            holder = new Viewholder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtSinhVien = (TextView)view.findViewById(R.id.tensv);
            holder.imgDelete=(ImageView)  view.findViewById(R.id.btnxoacp);
            holder.imgUpdate = (ImageView) view.findViewById(R.id.btnsuacp);
            view.setTag(holder);
        }else{
            holder =(Viewholder) view.getTag();
        }
        SinhVien sinhvien = congvieclist.get(i);
        holder.txtSinhVien.setText(sinhvien.getTenSV());

        //xoa sinh vien
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    context.dialogXoa(sinhvien.getTenSV(),sinhvien.getId());
            }
        });
        return view;
    }



}
