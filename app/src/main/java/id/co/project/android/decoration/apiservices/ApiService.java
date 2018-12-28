package id.co.project.android.decoration.apiservices;

import id.co.project.android.decoration.models.InsertFoodResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiService {
    @FormUrlEncoded
    @POST("data/InsertClient.php")
    Call<InsertFoodResponseModel> insertFood(@Field("customername") String customername, @Field("telp") String telp,@Field("email") String email,@Field("ktp") String ktp,@Field("alamat") String alamat,@Field("paket") String paket);
}
