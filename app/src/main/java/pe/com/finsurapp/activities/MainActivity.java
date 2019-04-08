package pe.com.finsurapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import pe.com.finsurapp.R;
import pe.com.finsurapp.fragments.AgenciasFragment;
import pe.com.finsurapp.fragments.CreditosFragment;
import pe.com.finsurapp.fragments.PerfilFragment;

public class MainActivity extends AppCompatActivity implements AgenciasFragment.OnFragmentInteractionListener, CreditosFragment.OnFragmentInteractionListener, PerfilFragment.OnFragmentInteractionListener {

    private Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_creditos:
                    fragment = new CreditosFragment();
                    cargarFragment(fragment);
                    return true;
                case R.id.navigation_agencias:
                    fragment = new AgenciasFragment();
                    cargarFragment(fragment);
                    return true;
                case R.id.navigation_perfil:
                    fragment = new PerfilFragment();
                    cargarFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(fragment==null){
            fragment = new CreditosFragment();
            cargarFragment(fragment);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void cargarFragment(Fragment fragment){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment).commit();



    }
}
