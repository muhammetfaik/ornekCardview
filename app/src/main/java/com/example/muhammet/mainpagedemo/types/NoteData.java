package com.example.muhammet.mainpagedemo.types;

import android.util.Log;

import com.example.muhammet.mainpagedemo.helpers.HttpGetAsyncTask;
import com.google.gson.reflect.TypeToken;
import com.example.muhammet.mainpagedemo.types.OnTaskCompleted;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by MUHAMMET on 12.07.2016.
 */
public  class NoteData {
   public static   ArrayList<Note> noteListClient,notelistPosition;






   /* public static String[] placeNameArray = {"Bora Bora", "Canada", "Dubai", "Hong Kong", "Iceland", "India", "Kenya", "London", "Switzerland", "Sydney"};

    public static ArrayList<Note> placeList() {
        ArrayList<Note> list = new ArrayList<>();
        for (int i = 0; i < placeNameArray.length; i++) {
            Note note = new Note();
            note.name = placeNameArray[i];
            note.pdf = placeNameArray[i].replaceAll("\\s+", "").toLowerCase();
            if (i == 2 || i == 5) {
                note.isFav = true;
            }
            list.add(note);

        }
        return (list);
    }*/


    public static   void httpclient(final DataListener dataListener) {
        Type stringType = new TypeToken<GeneralResponse<List<Note>>>() {
        }.getType();
        HttpGetAsyncTask task2 = new HttpGetAsyncTask(new OnTaskCompleted<GeneralResponse<List<Note>>>() {
            @Override
            public void onTaskCompleted(GeneralResponse<List<Note>> object) {
                noteListClient = new ArrayList<>(object.getData());
                dataListener.onDataFetched(noteListClient);
            }
        }, stringType);
        task2.execute(Methods.getLatestNotes);


    }




    public static ArrayList<Note> fooNote()
    {

        httpclient(new DataListener() {
            @Override
            public void onDataFetched(ArrayList<Note> noteList) {
                notelistPosition = noteList;
            }
        });
     return notelistPosition;
    }


}
