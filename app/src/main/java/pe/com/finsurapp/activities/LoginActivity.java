package pe.com.finsurapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.finsurapp.R;
import pe.com.finsurapp.model.LoginResponse;
import pe.com.finsurapp.model.request.LoginRequest;
import pe.com.finsurapp.network.NetworkApi;
import pe.com.finsurapp.network.RetrofitApiClient;
import pe.com.finsurapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.logoImg)
    ImageView logoImg;
    @BindView(R.id.name_text_log)
    TextView nameTextLog;
    @BindView(R.id.etUsuario)
    EditText etUsuario;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnIngresar)
    Button btnIngresar;
    @BindView(R.id.txtRecoveryPass)
    TextView txtRecoveryPass;
    @BindView(R.id.txtCreateAcount)
    TextView txtCreateAcount;


    private final int REQUEST_ACCES_FINE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        comprobarPermisos();



    }


    @OnClick(R.id.btnIngresar)
    public void ingresarClick() {
        String username = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(!username.matches("") && !password.matches("")){
            validarUsuario(username,password);
        }else
            Toast.makeText(this, "Ingrese los datos correctamente", Toast.LENGTH_SHORT).show();


    }

    public void validarUsuario(String username,String password){
        NetworkApi networkApi = RetrofitApiClient.getClient().create(NetworkApi.class);
        LoginRequest loginRequest = new LoginRequest(username,password);
        networkApi.loginValidate(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    guardarPreferencias(loginResponse.getNombre());

                    if(loginResponse.getSuccess()){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else
                        Toast.makeText(LoginActivity.this,"El usuario o la contraseña son incorrectas",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void comprobarPermisos(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_ACCES_FINE);
    }

    private void guardarPreferencias(String nombre) {
        SharedPreferences preferences = getSharedPreferences(Constantes.PREFERENCES, Context.MODE_PRIVATE);



        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre",nombre);



        //Toast.makeText(this, "cod: " + Constantes.CODIGO_AUX, Toast.LENGTH_SHORT).show();
        editor.commit();
    }


}
