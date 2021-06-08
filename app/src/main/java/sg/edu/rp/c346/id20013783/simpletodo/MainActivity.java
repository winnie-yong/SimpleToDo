package sg.edu.rp.c346.id20013783.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etlist;
    EditText etRemove;
    Button btnadd;
    Button btnclear;
    Button btndelete;
    ListView lvTask;
    TextView tv;
    Spinner spnAddRemove;

    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRemove = findViewById(R.id.editRemoveTask);
        etlist = findViewById(R.id.editListOfTask);
        btnadd = findViewById(R.id.btnAdd);
        btnclear = findViewById(R.id.btnClear);
        lvTask = findViewById(R.id.listViewTask);
        tv = findViewById(R.id.textView);
        spnAddRemove = findViewById(R.id.spinner);
        btndelete = findViewById(R.id.btnDelete);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTask);
        lvTask.setAdapter(aaTask);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etlist.setVisibility(View.VISIBLE);
                        etRemove.setVisibility(View.GONE);
                        btndelete.setClickable(false);
                        btndelete.setTextColor(Color.parseColor("#808080"));
                        btndelete.setBackgroundColor(Color.parseColor("#D3D3D3"));
                        btnadd.setBackgroundColor(Color.parseColor("#6200EE"));
                        btnadd.setTextColor(Color.parseColor("#FFFFFF"));

                        btnadd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String task = etlist.getText().toString();
                                alTask.add(task);
                                aaTask.notifyDataSetChanged();
                            }
                        });

                        btnclear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alTask.clear();
                                aaTask.notifyDataSetChanged();
                            }
                        });
                        break;

                    case 1:
                        etlist.setVisibility(View.GONE);
                        etRemove.setVisibility(View.VISIBLE);
                        btnadd.setClickable(false);
                        btnadd.setTextColor(Color.parseColor("#808080"));
                        btnadd.setBackgroundColor(Color.parseColor("#D3D3D3"));
                        btndelete.setBackgroundColor(Color.parseColor("#6200EE"));
                        btndelete.setTextColor(Color.parseColor("#FFFFFF"));
                        btndelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = Integer.parseInt(etRemove.getText().toString());
                                if(aaTask.getCount() >pos){
                                    alTask.remove(pos);
                                    aaTask.notifyDataSetChanged();
                                }
                                else if(alTask.isEmpty()){
                                    Toast.makeText(MainActivity.this,"You dont have any task to remove",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_LONG).show();
                                }


                            }
                        });
                        btnclear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alTask.clear();
                                aaTask.notifyDataSetChanged();
                            }
                        });
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}