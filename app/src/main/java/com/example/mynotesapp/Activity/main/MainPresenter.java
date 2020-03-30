package com.example.mynotesapp.Activity.main;

import androidx.annotation.NonNull;

import com.example.mynotesapp.Api.ApiClient;
import com.example.mynotesapp.Api.ApiInterface;
import com.example.mynotesapp.Model.Note;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private MainView view;
    private CompositeDisposable disposable;
    private ApiInterface apiInterface;

    public MainPresenter(MainView view) {
        this.view = view;
        disposable = new CompositeDisposable();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    void getData() {
        view.showLoading();
        disposable.add(
                apiInterface.getNotes()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<Note>>(){

                            @Override
                            public void onNext(List<Note> notes) {
                                view.hideLoading();
                                view.onGetResult(notes);
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideLoading();
                            }

                            @Override
                            public void onComplete() {
                                view.hideLoading();
                            }
                        })
        );
        /*ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getNotes();
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(@NonNull Call<List<Note>> call, @NonNull Response<List<Note>> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Note>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getMessage());
            }
        });*/
    }


}
