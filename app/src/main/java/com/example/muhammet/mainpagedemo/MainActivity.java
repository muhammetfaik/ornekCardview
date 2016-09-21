package com.example.muhammet.mainpagedemo;

import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.muhammet.mainpagedemo.adapters.NoteListAdapter;
import com.example.muhammet.mainpagedemo.helpers.HttpGetAsyncTask;
import com.example.muhammet.mainpagedemo.types.DataListener;
import com.example.muhammet.mainpagedemo.types.GeneralResponse;
import com.example.muhammet.mainpagedemo.types.Methods;
import com.example.muhammet.mainpagedemo.types.Note;
import com.example.muhammet.mainpagedemo.types.OnTaskCompleted;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Toolbar toolbar;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private NoteListAdapter mListAdapter;
    private Menu menu;
    private boolean isListView;
    private ArrayList<Note> noteListClient,notelistPosition = new ArrayList<Note>();
    private  int i = 0;
    private List<Note> noteDataset =  new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

     //   toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setUpActionBar();
        mRecyclerView = (RecyclerView)findViewById(R.id.notelist);
        mListAdapter = new NoteListAdapter(noteDataset);
        mRecyclerView.setHasFixedSize(true);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

           mRecyclerView.setAdapter(mListAdapter);



        praperNoteData();


    }
    public   void  httpclient(final DataListener dataListener) {
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

 private void  praperNoteData()
 {
     httpclient(new DataListener() {
         @Override
         public void onDataFetched(ArrayList<Note> noteList) {
             notelistPosition = noteList;
             for (Note note : notelistPosition) {
               try {
                   note = new Note(notelistPosition.get(i).id
                           , notelistPosition.get(i).price, notelistPosition.get(i).university_id);
                   noteDataset.add(note);
                   i++;
               }
               catch (Exception e)
               {
                   Log.e("Error",e.toString());
               }
             }
         }
     });
     try {
         mListAdapter.notifyDataSetChanged();
     }
     catch (Exception e)
     {
         Log.e("Error",e.toString());
     }
 }




    private void setUpActionBar() {
        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
           // toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   /* private void toggle() {
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (isListView) {
            mStaggeredLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_action_list);
            item.setTitle("Show as list");
            isListView = false;
        } else {
            mStaggeredLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_action_grid);
            item.setTitle("Show as grid");
            isListView = true;
        }
    }*/
}
