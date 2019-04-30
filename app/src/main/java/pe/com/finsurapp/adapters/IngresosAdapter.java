package pe.com.finsurapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.finsurapp.R;
import pe.com.finsurapp.model.IngresoEntity;

public class IngresosAdapter extends RecyclerView.Adapter<IngresosAdapter.ViewHolder> implements View.OnClickListener {
    List<IngresoEntity> ingresoEntities;

    public IngresosAdapter(List<IngresoEntity> ingresoEntities) {
        this.ingresoEntities = ingresoEntities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ingreso, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtDescripcion.setText(ingresoEntities.get(i).getDescripcion());
        viewHolder.txtMontoIngreso.setText(ingresoEntities.get(i).getIngreso());
    }

    @Override
    public int getItemCount() {
        return ingresoEntities.size();
    }

    @Override
    public void onClick(View v) {

    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtMontoIngreso)
        TextView txtMontoIngreso;
        @BindView(R.id.txtDescripcion)
        TextView txtDescripcion;
        @BindView(R.id.cardviewid)
        CardView cardviewid;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
