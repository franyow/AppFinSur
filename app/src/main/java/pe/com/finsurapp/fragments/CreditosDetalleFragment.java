package pe.com.finsurapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pe.com.finsurapp.R;
import pe.com.finsurapp.model.DetalleCredito;
import pe.com.finsurapp.network.NetworkApi;
import pe.com.finsurapp.network.RetrofitApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    @BindView(R.id.txtRequisitos)
    TextView txtRequisitos;
    @BindView(R.id.txtBeneficios)
    TextView txtBeneficios;
    @BindView(R.id.imgCall)
    ImageButton imgCall;
    private OnFragmentInteractionListener mListener;

    NetworkApi networkApi = RetrofitApiClient.getClient().create(NetworkApi.class);
    DetalleCredito detalleCredito;


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
        //Toast.makeText(getContext(), "Test- Codigo crédito: " + codCredForBundle, Toast.LENGTH_SHORT).show();
        cargarInfoCredito(Integer.valueOf(codCredForBundle));

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

    void cargarInfoCredito(int credito) {
        networkApi.getDetalleCredito(credito).enqueue(new Callback<DetalleCredito>() {
            @Override
            public void onResponse(Call<DetalleCredito> call, Response<DetalleCredito> response) {
                detalleCredito = response.body();
                txtBeneficios.setText(detalleCredito.getBeneficios());
                txtRequisitos.setText(detalleCredito.getRequisitos());
            }

            @Override
            public void onFailure(Call<DetalleCredito> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.button)
    public void callPhoneByCredito(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:942353343"));

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }


}
