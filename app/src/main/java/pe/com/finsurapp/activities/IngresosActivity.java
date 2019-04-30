package pe.com.finsurapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.finsurapp.R;
import pe.com.finsurapp.adapters.IngresosAdapter;
import pe.com.finsurapp.model.FinSurRoomDatabase;
import pe.com.finsurapp.model.IngresoEntity;

public class IngresosActivity extends AppCompatActivity {

    @BindView(R.id.rvIngresos)
    RecyclerView rvIngresos;
    List<IngresoEntity> ingresoEntities = new ArrayList<>();
    IngresosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos3);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getActionBar().setTitle("Ingresos Monetarios");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                Intent intent = new Intent(IngresosActivity.this,RegistroIngresoActivity.class);
                startActivity(intent);
            }
        });


        //getTasks();
        //adapter = new IngresosAdapter(ingresoEntities);

        //rvIngresos.setAdapter(adapter);
        setTitle("Ingresos");





    }

    @Override
    protected void onStart() {
        super.onStart();
        rvIngresos.setLayoutManager(new LinearLayoutManager(this));
        getTasks();


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<IngresoEntity>> {

            @Override
            protected List<IngresoEntity> doInBackground(Void... voids) {
                List<IngresoEntity> ingresoList = FinSurRoomDatabase.getDatabase(getApplicationContext())
                        .getIngresoDao()
                        .getAll();

                return  ingresoList;



            }

            @Override
            protected void onPostExecute(List<IngresoEntity> tasks) {
                super.onPostExecute(tasks);
                 adapter = new IngresosAdapter( tasks);

                rvIngresos.setAdapter(adapter);

                adapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IngresoEntity task = tasks.get(rvIngresos.getChildAdapterPosition(v));
                        Intent intent = new Intent(IngresosActivity.this, IngresosCrudActivity.class);
                        intent.putExtra("ingreso", task);

                        startActivity(intent);
                    }
                });
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();



    }




}
