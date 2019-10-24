package com.example.crudtable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecordListActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<Model> mList;
    RecordListAdapter mAdapter = null;

    ImageView imageViewIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this, R.layout.row, mList);
        mListView.setAdapter(mAdapter);

        //get all data from sqlite
        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM RECORD");
        mList.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String phone = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            //add to list
            mList.add(new Model(id, name, age, phone, image));
        }

        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            //When there is no records in the table, list is empty
            Toast.makeText(this,"Database is empty !", Toast.LENGTH_SHORT).show();
        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l){
                //alert dialog to display options updtae - delete
                final CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(RecordListActivity.this);
                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(i == 0){
                            //update
                            Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show update dialog

                        }
                        if(i == 1){
                            //delete
                        }
                    }
                });
                return false;
            }
        });

    }

    private void showDialogUpdate(Activity activity, int position){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update");

        imageViewIcon = dialog.findViewById(R.id.imageViewRecord);
        EditText edtName = dialog.findViewById(R.id.edtName);
        EditText edtAge = dialog.findViewById(R.id.edtAge);
        EditText edtPhone = dialog.findViewById(R.id.edtPhone);
        EditText btnUpdate = dialog.findViewById(R.id.btnUpdate);

        //set width of dialog
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        //set height of dialog
        int height = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        //in update dialog click image view to update image

    }
}

/*
* Design row for the listview
* Create class model
* Create custom adapter for listView
*Create an alert dialog to update and delete records
* design update dialog
* */