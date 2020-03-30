package com.example.mynotesapp.Api;

import com.example.mynotesapp.Model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import io.reactivex.Observable;
import io.reactivex.Completable;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("save.php")
    Observable<Note> saveNote(
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @GET("notes.php")
    Observable<List<Note>> getNotes();

    @FormUrlEncoded
    @POST("update.php")
    Completable updateNote(
            @Field("id") int id,
            @Field("title") String title,
            @Field("note") String note,
            @Field("color") int color
    );

    @FormUrlEncoded
    @POST("delete.php")
    Observable<Note> deleteNote(
            @Field("id") int id
    );

}
