package pe.com.finsurapp.network;

import java.util.List;

import pe.com.finsurapp.model.Agencia;
import pe.com.finsurapp.model.Credito;
import pe.com.finsurapp.model.DetalleCredito;
import pe.com.finsurapp.model.LoginResponse;
import pe.com.finsurapp.model.request.LoginRequest;
import pe.com.finsurapp.model.request.PhotoRequest;
import pe.com.finsurapp.model.response.PhotoResponse;
import pe.com.finsurapp.model.response.PhotoUserResponse;
import pe.com.finsurapp.utils.Constantes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkApi {
    @GET(Constantes.GET_CREDITOS)
    Call<List<Credito>> getCreditos();

    @GET(Constantes.GET_DETALLE_CREDITO)
    Call<DetalleCredito> getDetalleCredito(@Path("codCredito") int codCredito);

    @GET(Constantes.GET_AGENCIAS)
    Call<List<Agencia>> getAgencias();

    @POST(Constantes.POST_LOGIN)
    Call<LoginResponse> loginValidate(@Body LoginRequest loginRequest);

    @POST(Constantes.POST_UPLOADIMG)
    Call<PhotoResponse> savePhotoUser(@Body PhotoRequest photoRequest);

}
