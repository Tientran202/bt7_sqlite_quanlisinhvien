package com.example.quanlisinhvien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button them;
    Database database;
    ListView lvSinhVien;
    ArrayList<SinhVien> arraySinhVien;
    SinhVienAdapter adapter;
    EditText edtNhapTen,edtNhapLop;
    String id;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa
        lvSinhVien = (ListView)findViewById(R.id.lvComputer);
        arraySinhVien = new ArrayList<>();
        adapter  = new SinhVienAdapter(this,R.layout.dong_sinhvien,arraySinhVien);
        lvSinhVien.setAdapter(adapter);
        them = (Button)findViewById(R.id.btthemm);
        edtNhapTen = (EditText) findViewById(R.id.edtNhapTen);
        edtNhapLop = (EditText) findViewById(R.id.edtNhapLop);

        //tao database
        database = new Database(this,"quanLiSinhVien.sqlite",null,1);

        //tao bang computer
        database.QueryData("create table if not exists sinhvien (id integer primary key AUTOINCREMENT," +
                "tensv varchar(200)," +
                "lopsv varchar(200))");

        //them data
        //database.QueryData("insert into sinhvien values(null,' Ngọc Tiến','Lớp 20T3')");

        //select data
        Cursor dataCP =database.getdata("select * from sinhvien");
        while(dataCP.moveToNext()){
            String tensv = dataCP.getString(1);
             id = dataCP.getString(1);
            arraySinhVien.add(new SinhVien(id,tensv));

        }
        adapter.notifyDataSetChanged();

        //click button them
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten,lop;
                ten=edtNhapTen.getText().toString();
                lop= edtNhapLop.getText().toString();
                if(ten.equals("") || lop.equals("")){
                    Toast.makeText(MainActivity.this,"Nhập đầy đủ thông tin trước khi thêm", Toast.LENGTH_SHORT).show();
                }
                else{
                    database.QueryData("insert into sinhvien values(null,'"+ten+"','"+lop+"')");
                    Toast.makeText(MainActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        //long click button
        lvSinhVien.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                database.QueryData("delete from sinhvien where id = '"+ id + "'");
                Toast.makeText(MainActivity.this, "xoa", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
    //dialog xoa sinh vien
    public void dialogXoa(String tensv,final int  id ){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Bạn muốn xóa sinh viên"+ tensv+" này không");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    database.QueryData("delete from sinhvien where id = '"+id+"'");
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }
}
