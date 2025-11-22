package com.seoul.myai;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seoul.myai.db.DataDatabase;
import com.seoul.myai.db.DataItemDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataItem> list = new ArrayList<>();

    private DataItem[] item = new DataItem[3];

    private RecyclerView recyclerView;

    private EditText editText;

    private Button addButton;

    private Button removeButton;

    private DataItemDao Dao;

    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Dao = DataDatabase.getINSTANCE(MainActivity.this).dataItemDao();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.list);

        editText = findViewById(R.id.editview);
        addButton = findViewById(R.id.add_button);
        removeButton = findViewById(R.id.remove_button);


        DataItem item1 = new DataItem();
        item1.setSrc(R.drawable.a);
        item1.setName("연예인 1");
        list.add(item1);

        DataItem item2 = new DataItem();
        item2.setSrc(R.drawable.b);
        item2.setName("연예인 2");
        list.add(item2);

        DataItem item3 = new DataItem();
        item3.setSrc(R.drawable.c);
        item3.setName("연예인 3");
        list.add(item3);

        /*item[0].setSrc(R.drawable.a);
        item[1].setSrc(R.drawable.b);
        item[2].setSrc(R.drawable.c);

        for (int i = 0; i < 3; i++) {
            item[i].setName("연예인 " + i);
            list.add(item[i]);
        }*/

        GeminiAdapter geminiAdapter = new GeminiAdapter();


        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(item.length);
                //geminiAdapter.RemoveItem(item);
            }


        });

        //geminiAdapter.RemoveItem(item);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                Log.d("test", "name" + name);

                /*i.setSrc(item[list.size() % 3].getSrc());
                i.setName(name);
                list.add(i);*/
                DataItem i = new DataItem();


                switch (list.size() % 3) {
                    case 0:
                        i.setSrc(R.drawable.a);
                        break;

                    case 1:
                        i.setSrc(R.drawable.b);
                        break;

                    case 2:
                        i.setSrc(R.drawable.c);
                        break;
                }
                i.setName(name);
                list.add(i);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Dao.Insert(i);
                    }
                }).start();

                geminiAdapter.addItem(list);
                recyclerView.scrollToPosition(list.size() - 1);
            }
        });

        recyclerView.setAdapter(
                geminiAdapter
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<DataItem> getAllItem = Dao.getAll();
                list.addAll(getAllItem);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        geminiAdapter.setList(list);
                    }
                });
            }
        }).start();
        //geminiAdapter.setList(list);

    }
}