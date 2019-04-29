package pe.com.finsurapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.com.finsurapp.R;
import pe.com.finsurapp.activities.MapsActivity;
import pe.com.finsurapp.adapters.AgenciasAdapter;
import pe.com.finsurapp.model.Agencia;
import pe.com.finsurapp.model.Credito;
import pe.com.finsurapp.model.DetalleCredito;
import pe.com.finsurapp.network.NetworkApi;
import pe.com.finsurapp.network.RetrofitApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AgenciasFragment extends Fragment {

    List<Agencia> agencias = new ArrayList<>();
    @BindView(R.id.rvAgencias)
    RecyclerView rvAgencias;
    AgenciasAdapter adapter;
    Unbinder unbinder;
    String lat="0.0";
    String lng="0.0";
    String nombreAgencia = null;



    NetworkApi networkApi = RetrofitApiClient.getClient().create(NetworkApi.class);

    private OnFragmentInteractionListener mListener;

    public AgenciasFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AgenciasFragment newInstance(String param1, String param2) {
        AgenciasFragment fragment = new AgenciasFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agencias, container, false);
        unbinder = ButterKnife.bind(this, view);
        //cargarAgencias();
        cargarAgenciasFromApi();
        adapter = new AgenciasAdapter(agencias);
        rvAgencias.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAgencias.setAdapter(adapter);
        rvAgencias.hasFixedSize();



        return view;
    }



     void cargarAgenciasFromApi(){
        networkApi.getAgencias().enqueue(new Callback<List<Agencia>>() {
            @Override
            public void onResponse(Call<List<Agencia>> call, Response<List<Agencia>> response) {
                if(response.isSuccessful()){
                    List<Agencia> listAgenciaResponse = response.body();

                    for(Agencia agencia: listAgenciaResponse ){
                        agencias.add(agencia);
                    }
                    adapter.notifyDataSetChanged();

                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //lat = agencias.get((Integer) view.getTag()).getLatAgencia();
                            //lng = agencias.get((Integer) view.getTag()).getLongAgencia();
                            lat = agencias.get(rvAgencias.getChildAdapterPosition(v)).getLatAgencia();
                            lng = agencias.get(rvAgencias.getChildAdapterPosition(v)).getLongAgencia();
                            nombreAgencia = agencias.get(rvAgencias.getChildAdapterPosition(v)).getNombreAgencia();

                            //Toast.makeText(getActivity(), "map", Toast.LENGTH_SHORT).show();
                            //lat = agencias.get((Integer) view.getTag()).getLatAgencia();
                            //lng = agencias.get((Integer) view.getTag()).getLongAgencia();
                            goingMapsFromAgencia(lat,lng,nombreAgencia);

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Agencia>> call, Throwable t) {

            }
        });


     }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    void goingMapsFromAgencia(String latitud, String longitud,String nombreAgencia){
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putExtra("latitud", latitud);
        intent.putExtra("longitud", longitud);
        intent.putExtra("nombreAgencia",nombreAgencia);

        startActivity(intent);

    }
}
