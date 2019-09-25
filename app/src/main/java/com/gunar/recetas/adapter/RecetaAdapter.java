package com.gunar.recetas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gunar.recetas.R;
import com.gunar.recetas.model.Receta;

import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.MyViewHolder> {

    private List<Receta> comenList;
    private static ClickListener clickListener;
    public static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public TextView titulo;

        public MyViewHolder(View view) {
            super(view);

            titulo = (TextView) view.findViewById(R.id.titulo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public RecetaAdapter(List<Receta> myDataset) {
        comenList = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comen_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Receta comen = comenList.get(position);

        holder.titulo.setText(comen.getTitulo());
    }

    @Override
    public int getItemCount() {
        return comenList.size();
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecetaAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

    }


}


