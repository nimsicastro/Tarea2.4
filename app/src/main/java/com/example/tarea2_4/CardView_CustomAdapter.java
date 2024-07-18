package com.example.tarea2_4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea2_4.Modelo.signaturess;

import java.util.List;

public class CardView_CustomAdapter extends RecyclerView.Adapter<CardView_CustomAdapter.SignatureViewHolder> {
    private Context context;
    private List<signaturess> listaFirmas;


    public CardView_CustomAdapter(Context context, List<signaturess> listaFirmas) {
        this.context = context;
        this.listaFirmas = listaFirmas;
    }

    @NonNull
    @Override
    public SignatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_cardview, parent, false);
        return new SignatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SignatureViewHolder holder, int position) {
        signaturess item = listaFirmas.get(position);
        holder.signatureDescription.setText(item.getDescripcion());

        // Convertir byte[] a Bitmap
        byte[] signatureBytes = item.getFirma();
        if (signatureBytes != null) {
            Bitmap signatureBitmap = BitmapFactory.decodeByteArray(signatureBytes, 0, signatureBytes.length);
            holder.signatureImage.setImageBitmap(signatureBitmap);
        }
    }

    @Override
    public int getItemCount() {
        return listaFirmas.size();
    }

    // Clase ViewHolder interna
    public static class SignatureViewHolder extends RecyclerView.ViewHolder {
        ImageView signatureImage;
        TextView signatureDescription;

        public SignatureViewHolder(View itemView) {
            super(itemView);
            signatureImage = itemView.findViewById(R.id.signatureImage);
            signatureDescription = itemView.findViewById(R.id.signatureDescription);
        }
    }
}
