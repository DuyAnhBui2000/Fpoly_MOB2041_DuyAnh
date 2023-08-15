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


import java.util.Collection;
import java.util.List;

import buiduyanh.fpoly.buiduyanh_md18202.R;
import buiduyanh.fpoly.buiduyanh_md18202.click.SachClick;
import buiduyanh.fpoly.buiduyanh_md18202.dao.LoaiSachDao;
import buiduyanh.fpoly.buiduyanh_md18202.dao.SachDao;
import buiduyanh.fpoly.buiduyanh_md18202.model.LoaiSach;
import buiduyanh.fpoly.buiduyanh_md18202.model.Sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    private Context context;
    private List<Sach> list;
    private SachDao sachDao;
    private LoaiSachDao loaiSachDao;
    private SachClick sachClick;
    private LoaiSach loaiSach;
    public SachAdapter(Context context, List<Sach> list) {
        this.context = context;
        this.list = list;
        sachDao=new SachDao(context);
        loaiSachDao=new LoaiSachDao(context);
    }

    public void setSachClick(SachClick sachClick) {
        this.sachClick = sachClick;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_sach,parent,false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Sach sach=list.get(position);
        loaiSach=loaiSachDao.getID(String.valueOf(sach.getMaLoai()));
        holder.ma.setText("Mã sách: "+sach.getMaSach());
        holder.ten.setText("Tên sách: "+sach.getTenSach());
        holder.gia.setText("Giá thuê: "+sach.getGiaThue());
        holder.loai.setText("Loại sách: "+loaiSach.getTenLoai());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachClick.onClick(list.get(position));
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
                                if (sachDao.delete(sach.getMaLoai()) > 0) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    list.clear();
                                    list.addAll((Collection<? extends Sach>) sachDao.getAll());
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

    public class SachViewHolder extends RecyclerView.ViewHolder{
        public TextView ma,ten,gia,loai;
        public ImageView delete;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            ma=itemView.findViewById(R.id.tv_masach);
            ten=itemView.findViewById(R.id.tv_tensach);
            gia=itemView.findViewById(R.id.tv_giathuesach);
            loai=itemView.findViewById(R.id.tv_loaisach_sach);
            delete=itemView.findViewById(R.id.img_delete_sach);
        }
    }
}
