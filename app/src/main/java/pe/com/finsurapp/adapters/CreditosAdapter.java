package pe.com.finsurapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.finsurapp.R;
import pe.com.finsurapp.model.Credito;

public class CreditosAdapter extends RecyclerView.Adapter<CreditosAdapter.ViewHolder> implements View.OnClickListener {


    private View.OnClickListener listener;
    List<Credito> listaCreditos;
    Context context;

    public CreditosAdapter(List<Credito> listaCreditos, Context context) {
        this.listaCreditos = listaCreditos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_credito, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //viewHolder.imgCredito.setImageResource(listaCreditos.get(i).getImgCredito());
        Picasso.get()
                .load(listaCreditos.get(i).getImgCredito())
                //.placeholder(R.drawable.img_cred_multiproposito)
                .noPlaceholder()
                .fit()
                .into(viewHolder.imgCredito);
        viewHolder.txtTituloCredito.setText(listaCreditos.get(i).getNombreCredito());
        viewHolder.txtDescripcionCredito.setText(listaCreditos.get(i).getDescripcionCredito());
    }

    @Override
    public int getItemCount() {
        return listaCreditos.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgCredito)
        ImageView imgCredito;
        @BindView(R.id.txtTituloCredito)
        TextView txtTituloCredito;
        @BindView(R.id.txtDescripcionCredito)
        TextView txtDescripcionCredito;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
    }
}
