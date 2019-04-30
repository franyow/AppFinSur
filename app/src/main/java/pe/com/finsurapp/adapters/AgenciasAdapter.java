package pe.com.finsurapp.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.finsurapp.R;
import pe.com.finsurapp.model.Agencia;

public class AgenciasAdapter extends RecyclerView.Adapter<AgenciasAdapter.ViewHolder> implements View.OnClickListener {

    List<Agencia> listAgencias;
    private View.OnClickListener listener;

    public AgenciasAdapter(List<Agencia> listAgencias) {
        this.listAgencias = listAgencias;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_agencia,null,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtNombreAgencia.setText(listAgencias.get(i).getNombreAgencia());
        viewHolder.txtNombreAgencia.setTextColor(Color.parseColor("#1e88e5"));
        viewHolder.txtDireccion.setText(listAgencias.get(i).getDireccionAgencia());
        viewHolder.txtTelefono.setText(listAgencias.get(i).getTelefonoAgencia());
    }

    @Override
    public int getItemCount() {
        return listAgencias.size();
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
        @BindView(R.id.imgAgencia)
        ImageView imgAgencia;
        @BindView(R.id.txtMontoIngreso)
        TextView txtNombreAgencia;
        @BindView(R.id.txtDescripcion)
        TextView txtTelefono;
        @BindView(R.id.txtDireccion)
        TextView txtDireccion;
        @BindView(R.id.cardviewid)
        CardView cardviewid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
