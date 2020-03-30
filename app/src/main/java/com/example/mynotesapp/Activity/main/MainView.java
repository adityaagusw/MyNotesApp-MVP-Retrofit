package com.example.mynotesapp.Activity.main;

import com.example.mynotesapp.Model.Note;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);
}
