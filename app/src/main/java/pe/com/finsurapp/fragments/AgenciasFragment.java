package pe.com.finsurapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.com.finsurapp.R;
import pe.com.finsurapp.adapters.AgenciasAdapter;
import pe.com.finsurapp.model.Agencia;


public class AgenciasFragment extends Fragment {

    List<Agencia> agencias = new ArrayList<>();
    @BindView(R.id.rvAgencias)
    RecyclerView rvAgencias;
    AgenciasAdapter adapter;
    Unbinder unbinder;
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
        cargarAgencias();
        adapter = new AgenciasAdapter(agencias);
        rvAgencias.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAgencias.setAdapter(adapter);
        rvAgencias.hasFixedSize();


        return view;
    }

    public void cargarAgencias(){
        agencias.add(new Agencia("Agencia Los Olivos","Av. Angélica Gamarra 131, Los Olivos 15302","(01) 5330814","","","LIMA"));
        agencias.add(new Agencia("Agencia José Galvez","Lima 1119, Lima","(01) 2931043","","","LIMA"));
        agencias.add(new Agencia("Agencia Manchay","Plaza De Armas, S/N, Lima","(01) 3574645","","","LIMA"));
        agencias.add(new Agencia("Agencia Bellavista","Av. Oscar R. Benavides 4742, Bellavista 07006","","","","LIMA"));
        agencias.add(new Agencia("Agencia Los Olivos","Av. Angélica Gamarra 131, Los Olivos 15302","(01) 5330814","","","LIMA"));
        agencias.add(new Agencia("Agencia José Galvez","Lima 1119, Lima","(01) 2931043","","","LIMA"));
        agencias.add(new Agencia("Agencia Manchay","Plaza De Armas, S/N, Lima","(01) 3574645","","","LIMA"));
        agencias.add(new Agencia("Agencia Bellavista","Av. Oscar R. Benavides 4742, Bellavista 07006","","","","LIMA"));
        agencias.add(new Agencia("Agencia Los Olivos","Av. Angélica Gamarra 131, Los Olivos 15302","(01) 5330814","","","LIMA"));
        agencias.add(new Agencia("Agencia José Galvez","Lima 1119, Lima","(01) 2931043","","","LIMA"));
        agencias.add(new Agencia("Agencia Manchay","Plaza De Armas, S/N, Lima","(01) 3574645","","","LIMA"));
        agencias.add(new Agencia("Agencia Bellavista","Av. Oscar R. Benavides 4742, Bellavista 07006","","","","LIMA"));


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
}
