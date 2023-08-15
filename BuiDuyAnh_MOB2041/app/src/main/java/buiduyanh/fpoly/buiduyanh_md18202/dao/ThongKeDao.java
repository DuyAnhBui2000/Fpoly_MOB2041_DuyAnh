package buiduyanh.fpoly.buiduyanh_md18202.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import buiduyanh.fpoly.buiduyanh_md18202.database.MySQLite;
import buiduyanh.fpoly.buiduyanh_md18202.model.Sach;
import buiduyanh.fpoly.buiduyanh_md18202.model.Top;

public class ThongKeDao {
    private MySQLite mySQLite;
    private SQLiteDatabase db;
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDao(Context context) {
        this.context = context;
        mySQLite = new MySQLite(context);
        db = mySQLite.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "select maSach, count(maSach) as soLuong from PhieuMuon group by maSach order by soLuong desc limit 10";
        List<Top> list = new ArrayList<Top>();
        SachDao sachDao = new SachDao(context);
        Cursor c = db.rawQuery(sqlTop, null);
        if (c.getCount() > 0) {
            c.moveToNext();
            while (!c.isAfterLast()) {
                Top top = new Top();
                @SuppressLint("Range") Sach sach = sachDao.getID(c.getString(c.getColumnIndex("maSach")));
                top.tenSach = sach.getTenSach();
                top.soLuong=Integer.parseInt(c.getString(c.getColumnIndex("soLuong")));
                list.add(top);
                c.moveToNext();
            }
            c.close();
            db.close();
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
         String sqlDoanThu="select sum(tienThue) as doanhThu from PhieuMuon where ngay between ? and ?";
         List<Integer> list=new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanThu, new String[]{tuNgay,denNgay});
        if (c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                try{
                    list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
                }catch (Exception e){
                    list.add(0);
                }
                c.moveToNext();
            }
            c.close();
            db.close();
        }
        return list.get(0);
    }
}
