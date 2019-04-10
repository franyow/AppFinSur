package pe.com.finsurapp.network;

import java.util.List;

import pe.com.finsurapp.model.Credito;
import pe.com.finsurapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkApi {
    @GET(Constantes.GET_CREDITOS)
    Call<List<Credito>> getCreditos();

}
