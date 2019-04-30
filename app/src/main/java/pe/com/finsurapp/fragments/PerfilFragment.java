package pe.com.finsurapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import pe.com.finsurapp.R;
import pe.com.finsurapp.activities.IngresosActivity;
import pe.com.finsurapp.model.request.PhotoRequest;
import pe.com.finsurapp.model.response.PhotoResponse;
import pe.com.finsurapp.network.NetworkApi;
import pe.com.finsurapp.network.RetrofitApiClient;
import pe.com.finsurapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.txtNombreClient)
    TextView txtNombreClient;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvPhone)
    LinearLayout tvPhone;
    Unbinder unbinder;
    @BindView(R.id.imgPerfilUser)
    CircleImageView imgPerfilUser;

    Bitmap bitmap;
    byte[] imageByte;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    @BindView(R.id.btnRegistrarIngresos)
    Button btnRegistrarIngresos;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        unbinder = ButterKnife.bind(this, view);
        leerPreferencias(view);
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

    public void leerPreferencias(View view) {
        SharedPreferences preferences = getContext().getSharedPreferences(Constantes.PREFERENCES, Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombre", "");
        txtNombreClient.setText(nombre);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // convert byte array to Bitmap

                bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);

                imgPerfilUser.setImageBitmap(bitmap);
                Log.e("THIS CONTEXT BITMAP", bitmap.toString());
                //bitMapToByte();
                //savePhotoService();
            }
        }
    }


    @OnClick(R.id.imgPerfilUser)
    void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,
                CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);


    }

    void bitMapToByte() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        imageByte = stream.toByteArray();
        bitmap.recycle();

        Log.e("THIS CONTEXT BITMAP", imageByte.toString());
    }

    void savePhotoService() {
        PhotoRequest photoRequest = new PhotoRequest("1", imageByte.toString());

        NetworkApi networkApi = RetrofitApiClient.getClient().create(NetworkApi.class);

        networkApi.savePhotoUser(photoRequest).enqueue(new Callback<PhotoResponse>() {
            @Override
            public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {
                if (response.isSuccessful()) {
                    PhotoResponse photoResponse = response.body();
                    Toast.makeText(getActivity(), photoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PhotoResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.btnRegistrarIngresos)
    public void navigateToRegistrarIngresosActivity(){
        Intent intent = new Intent(getActivity(), IngresosActivity.class);
        startActivity(intent);

    }


}
