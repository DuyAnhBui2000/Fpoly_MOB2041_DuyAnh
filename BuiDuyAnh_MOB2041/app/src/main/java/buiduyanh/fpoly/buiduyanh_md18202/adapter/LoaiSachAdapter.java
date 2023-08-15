package buiduyanh.fpoly.buiduyanh_md18202.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import buiduyanh.fpoly.buiduyanh_md18202.R;
import buiduyanh.fpoly.buiduyanh_md18202.click.LoaiSachClick;
import buiduyanh.fpoly.buiduyanh_md18202.dao.LoaiSachDao;
import buiduyanh.fpoly.buiduyanh_md18202.model.LoaiSach;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachViewHolder> {
    private Context context;
    private List<LoaiSach> list;
    private LoaiSachDao loaiSachDao;
    private LoaiSachClick loaiSachClick;

    public LoaiSachAdapter(Context context, List<LoaiSach> list) {
        this.context = context;
        this.list = list;
        loaiSachDao = new LoaiSachDao(context);
    }

    public void setLoaiSachClick(LoaiSachClick loaiSachClick) {
        this.loaiSachClick = loaiSachClick;
    }

    @NonNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loaisach, parent, false);
        return new LoaiSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LoaiSach sach = list.get(position);
        holder.ma.setText("Mã loại: " + sach.getMaLoai());
        holder.tenloai.setText("Tên loại sách: " + sach.getTenLoai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSachClick.onClick(list.get(position));
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setTitle("Bạn có muốn xóa không")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (loaiSachDao.delete(sach.getMaLoai()) > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll(loaiSachDao.getAll());
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("CANNEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        public TextView ma, tenloai;
        public ImageView delete;

        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);
            ma = itemView.findViewById(R.id.tv_maloaisach);
            tenloai = itemView.findViewById(R.id.tv_tenloaisach);
            delete = itemView.findViewById(R.id.img_delete_loaisach);
        }
    }
}
