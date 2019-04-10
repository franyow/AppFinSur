package pe.com.finsurapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.com.finsurapp.R;


public class CreditosDetalleFragment extends Fragment {


    String codCredForBundle, nomCreForBundle;

    @BindView(R.id.nombreCredito)
    TextView nombreCredito;
    Unbinder unbinder;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    private OnFragmentInteractionListener mListener;


    public CreditosDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle storeObject = getArguments();
        if (storeObject != null) {
            codCredForBundle = storeObject.getString("codigoCredito");
            nomCreForBundle = storeObject.getString("nombreCredito");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creditos_detalle, container, false);
        unbinder = ButterKnife.bind(this, view);
        nombreCredito.setText(nomCreForBundle);
        Toast.makeText(getContext(), "Test- Codigo crédito: " + codCredForBundle, Toast.LENGTH_SHORT).show();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
