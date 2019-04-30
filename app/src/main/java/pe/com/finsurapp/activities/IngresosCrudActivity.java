package pe.com.finsurapp.activities;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.finsurapp.R;
import pe.com.finsurapp.model.FinSurRoomDatabase;
import pe.com.finsurapp.model.IngresoEntity;

public class IngresosCrudActivity extends AppCompatActivity {

    @BindView(R.id.etMontoIngreso)
    EditText etMontoIngreso;
    @BindView(R.id.spinnerDescrip)
    Spinner spinnerDescrip;
    @BindView(R.id.button_update)
    Button buttonUpdate;
    @BindView(R.id.button_delete)
    Button buttonDelete;

    String[] letra = {"Alquiler de bien","Ingreso laboral","Ingreso privado","otros"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos_crud);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Actualizar Ingreso");
        final IngresoEntity task = (IngresoEntity) getIntent().getSerializableExtra("ingreso");
        inicializarSpinner();
        loadTask(task);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(task);
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IngresosCrudActivity.this);
                builder.setTitle("Estás seguro?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(task);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });

    }

    private void loadTask(IngresoEntity ingresoEntity) {
        etMontoIngreso.setText(ingresoEntity.getIngreso());
        spinnerDescrip.setSelection(0);
    }

        private void updateTask(final IngresoEntity ingresoEntity1){
            final String monto = etMontoIngreso.getText().toString().trim();
            final String sDesc = spinnerDescrip.getSelectedItem().toString().trim();


       if (monto.isEmpty()) {
            //editTextTask.setError("Task required");
            //editTextTask.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            //editTextDesc.setError("Desc required");
            //editTextDesc.requestFocus();
            return;
        }



            class UpdateTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    ingresoEntity1.setIngreso(monto);
                    ingresoEntity1.setDescripcion(sDesc);

                    FinSurRoomDatabase.getDatabase(getApplicationContext()).getIngresoDao().updateIngreso(ingresoEntity1);


                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(), "Actualizado", Toast.LENGTH_LONG).show();
                    finish();
                    //startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
                }
            }

            UpdateTask ut = new UpdateTask();
            ut.execute();
        }


        private void deleteTask(final IngresoEntity task){
            class DeleteTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    FinSurRoomDatabase.getDatabase(getApplicationContext()).getIngresoDao().deleteIngreso(task);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_LONG).show();
                    finish();
                    //startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
                }
            }

            DeleteTask dt = new DeleteTask();
            dt.execute();

        }


        void deleteIngreso(){

        }

    void inicializarSpinner(){

        spinnerDescrip.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
    }

    }
