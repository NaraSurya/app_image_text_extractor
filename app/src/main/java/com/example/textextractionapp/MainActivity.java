package com.example.textextractionapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageTextExtractionListener{

    private ArrayList<ImageTextExtraction> imageTextExtractions = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageTextExtractionAdapter adapter;
    private static final int REQUEST_CODE_PICK_IMAGE_FROM_GALLERY = 1;
    private static final int REQUEST_CODE_PICK_IMAGE_FROM_CAMERA = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(view);
            }
        });

        addData();
        initRecyclerview();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectImage(final View view) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    onclickCamera();
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                   onclickGallery();
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE_FROM_GALLERY){
            if(resultCode == RESULT_OK){
                if (data.getData() != null){
                    System.out.println(data.getData().getPath());
//                    mImageUri = data.getData();
//                    Picasso.get().load(mImageUri).into(profile);
                }
            }
        }
    }

    private void addData(){
        imageTextExtractions.add(new ImageTextExtraction("path","name","textnya"));
    }

    private void initRecyclerview(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_image);

        adapter = new ImageTextExtractionAdapter(imageTextExtractions,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickImageTextExtraction(int position) {
        System.out.println("ketekan");
        Toast.makeText(getApplicationContext(),"ketekan",Toast.LENGTH_SHORT).show();
    }

    public void onclickGallery(){
        Intent pickImageFromGallery = new Intent();
        pickImageFromGallery.setType("image/*");
        pickImageFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pickImageFromGallery , REQUEST_CODE_PICK_IMAGE_FROM_GALLERY);
    }

    public void onclickCamera(){
        Intent pickImageFromCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(pickImageFromCamera,REQUEST_CODE_PICK_IMAGE_FROM_CAMERA);

    }


}
