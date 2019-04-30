package pe.com.finsurapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.finsurapp.R;
import pe.com.finsurapp.model.FinSurRoomDatabase;
import pe.com.finsurapp.model.IngresoEntity;

public class RegistroIngresoActivity extends AppCompatActivity {

    @BindView(R.id.etMontoIngreso)
    EditText etMontoIngreso;
    @BindView(R.id.spinnerTipoIngreso)
    Spinner spinnerTipoIngreso;
    @BindView(R.id.button5)
    Button button5;

    String[] letra = {"Alquiler de bien","Ingreso laboral","Ingreso privado","otros"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ingreso);
        ButterKnife.bind(this);
        inicializarSpinner();
    }


    @OnClick(R.id.button5)
    @Nullable
    public void saveTask() {
        final String monto = etMontoIngreso.getText().toString().trim();
        final String tipoIngreso = spinnerTipoIngreso.getSelectedItem().toString();

        if (monto.isEmpty()) {
            etMontoIngreso.setError("Monto requiredo");
            etMontoIngreso.requestFocus();
            return;
        }




        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                IngresoEntity ingreso = new IngresoEntity();

                ingreso.setDescripcion(tipoIngreso);
                ingreso.setIngreso(monto);

                FinSurRoomDatabase.getDatabase(getApplicationContext()).getIngresoDao().insertIngreso(ingreso);
                //adding to database

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    void inicializarSpinner(){

        spinnerTipoIngreso.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
    }
}
