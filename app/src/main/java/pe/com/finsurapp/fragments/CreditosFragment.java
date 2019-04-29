package pe.com.finsurapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pe.com.finsurapp.R;
import pe.com.finsurapp.activities.ComunicateFragments;
import pe.com.finsurapp.adapters.CreditosAdapter;
import pe.com.finsurapp.model.Credito;
import pe.com.finsurapp.network.NetworkApi;
import pe.com.finsurapp.network.RetrofitApiClient;
import pe.com.finsurapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreditosFragment extends Fragment {

    @BindView(R.id.rvCreditos)
    RecyclerView rvCreditos;
    CreditosAdapter adapter;
    List<Credito> creditos = new ArrayList<>();
    ComunicateFragments comunicateFragments;
    Activity activity;
    Retrofit retrofit;
    NetworkApi networkApi;
    List<Credito> listCredito = new ArrayList<>();
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreditosFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CreditosFragment newInstance(String param1, String param2) {
        CreditosFragment fragment = new CreditosFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_creditos, container, false);
        unbinder = ButterKnife.bind(this, view);
        cargarCreditos();
        adapter = new CreditosAdapter(listCredito,getContext());
        navigateToCreditoDetail();





        return view;
    }

   /* public void cargarCreditos(){
        creditos.add(new Credito("01",R.drawable.educativo_credito,"Crédito educativo","Estudia lo que siempre quisiste, en la universidad de tu elección."));
        creditos.add(new Credito("02",R.drawable.ecologico_credito,"Crédito ecológico","Dale vida a tus propósitos con sostenibilidad ambiental."));
        creditos.add(new Credito("03",R.drawable.hipotecario_credito,"Crédito hipotecario","Tener casa propia es una de las decisiones más importantes de tu vida. Tómala."));
        creditos.add(new Credito("04",R.drawable.vehicular_credito,"Crédito Vehicular", "Conduce tu vida sobre ruedas. Elige el vehículo que se ajusta a tus necesidades."));
        creditos.add(new Credito("05",R.drawable.linea_abierta_credito,"Linea Abierta", "Nos interesa tu crecimiento, completa aquel proyecto personal que tienes en mente."));
        creditos.add(new Credito("06",R.drawable.img_cred_multiproposito,"Crédito Multipropósito","Llegó el momento de hacer realidad tus propósitos. Todos tenemos algo pendiente por cumplir."));
    }*/

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public void navigateToCreditoDetail(){




    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if(context instanceof Activity){
            this.activity = (Activity) context;
            comunicateFragments= (ComunicateFragments) this.activity;
        }



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

    private void cargarCreditos(){
        retrofit = RetrofitApiClient.getClient();
        networkApi = retrofit.create(NetworkApi.class);

        networkApi.getCreditos().enqueue(new Callback<List<Credito>>() {
            @Override
            public void onResponse(Call<List<Credito>> call, Response<List<Credito>> response) {
                listCredito.clear();
                if(response.isSuccessful()){
                    List<Credito> creditos = response.body();

                    for (Credito cre: creditos ) {
                        listCredito.add(cre);
                        //Toast.makeText(getContext(), ""+cre.getNombreCredito(), Toast.LENGTH_SHORT).show();

                    }

                    adapter=new CreditosAdapter(creditos,getContext());
                    try {
                        rvCreditos.setLayoutManager(new GridLayoutManager(getContext(), 2));
                        rvCreditos.setAdapter(adapter);

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String credito = creditos.get(rvCreditos.getChildAdapterPosition(v)).getCodCredito();
                            String nombreCredito = creditos.get(rvCreditos.getChildAdapterPosition(v)).getNombreCredito();
                            comunicateFragments.enviarCredito(credito, nombreCredito);
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<List<Credito>> call, Throwable t) {
                Toast.makeText(getActivity(), Constantes.ERROR_NETWORK, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });


    }
}
