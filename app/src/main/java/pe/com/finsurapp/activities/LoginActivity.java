package pe.com.finsurapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.finsurapp.R;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btnIngresar)
    public void ingresarClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void validarUsuario(){

    }
}
