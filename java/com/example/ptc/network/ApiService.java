package com.example.ptc.network;

import com.example.ptc.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("get_data.php")  // Path relatif ke API PHP
    Call<List<User>> getUsers(); // Mendapatkan semua data user
}
