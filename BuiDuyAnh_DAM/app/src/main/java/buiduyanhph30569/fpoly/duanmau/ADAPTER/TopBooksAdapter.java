package buiduyanhph30569.fpoly.duanmau.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import buiduyanhph30569.fpoly.duanmau.DTO.TopBooks;
import buiduyanhph30569.fpoly.duanmau.R;

public class TopBooksAdapter extends RecyclerView.Adapter<TopBooksAdapter.MyViewHolderTopBook> {
    ArrayList<TopBooks> listTopBooks;
    Context context;

    public TopBooksAdapter(ArrayList<TopBooks> listTopBooks,  Context context) {
        this.listTopBooks = listTopBooks;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolderTopBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_one_item_top_book,parent,false);
        return new MyViewHolderTopBook(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderTopBook holder, int position) {

        TopBooks topBooks = listTopBooks.get(position);
        holder.tvTitleBook.setText(topBooks.getTitleBook());
        holder.tvCount.setText(topBooks.getCountBook()+"");


    }


    @Override
    public int getItemCount() {
        return listTopBooks.size();
    }


    public class MyViewHolderTopBook extends RecyclerView.ViewHolder{
        TextView tvTitleBook, tvCount;
        public MyViewHolderTopBook(@NonNull View view) {
            super(view);
            tvTitleBook = view.findViewById(R.id.tvNameBook);
            tvCount = view.findViewById(R.id.tvCountBorrowings);
        }

    }




}
