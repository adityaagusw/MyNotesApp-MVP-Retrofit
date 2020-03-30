package com.example.mynotesapp.Activity.editor;

import android.content.Context;

import com.example.mynotesapp.Api.ApiClient;
import com.example.mynotesapp.Api.ApiInterface;
import com.example.mynotesapp.Model.Note;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EditorPresenter {

    private EditorView view;
    private ApiInterface apiInterface;
    private CompositeDisposable disposable;

    public EditorPresenter(EditorView view) {
        this.view = view;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        disposable = new CompositeDisposable();
    }

    void saveNote(final String title, final String note, final int color) {
        view.showProgress();
        disposable.add(
                apiInterface.saveNote(title, note, color)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<Note>() {

                            @Override
                            public void onNext(Note note) {
                                view.hideProgress();
                                if (note.getMessage().equals("success")) {
                                    view.onRequestSuccess(note.getMessage());
                                } else {
                                    view.onRequestError(note.getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                            }

                            @Override
                            public void onComplete() {
                                view.hideProgress();
                            }
                        })
        );
        /*ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title, note, color);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();

                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });*/

    }

    void updateNote(int id, String title, String note, int color) {
        view.showProgress();
        disposable.add(
                apiInterface.updateNote(id, title, note, color)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() { //penggunakan DisposableComplete sesuai dengan updateNote()

                            @Override
                            public void onComplete() {
                                view.hideProgress();
                                view.onRequestSuccess("Berhasil");
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                                view.onRequestError("Gagal update");
                            }
                        })
        );
        /*ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.updateNote(id, title, note, color);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();

                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });*/
    }

    void deleteNote(int id) {
        view.showProgress();
        disposable.add(
                apiInterface.deleteNote(id)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<Note>() {

                            @Override
                            public void onNext(Note note) {
                                view.hideProgress();
                                if (note.getMessage().equals("success")) {
                                    view.onRequestSuccess(note.getMessage());
                                } else {
                                    view.onRequestError(note.getMessage());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.hideProgress();
                            }

                            @Override
                            public void onComplete() {
                                view.hideProgress();
                            }
                        })
        );
        /*ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.deleteNote(id);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call,@NonNull Response<Note> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();

                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });*/
    }

}
