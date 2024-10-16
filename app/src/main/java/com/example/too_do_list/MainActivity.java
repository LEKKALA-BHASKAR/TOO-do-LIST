package com.example.too_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper databaseHelper;
    private TodoTaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.list_view);

        loadTasks();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TodoTask selectedTask = (TodoTask) adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                intent.putExtra("TASK_ID", selectedTask.getId()); // Pass the task ID
                startActivity(intent);
            }
        });

        // Handle FloatingActionButton click to add a new task
        findViewById(R.id.fab_add_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(addIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks(); // Refresh the task list when returning to this activity
    }

    private void loadTasks() {
        List<TodoTask> tasks = databaseHelper.getAllTodoTasks();
        adapter = new TodoTaskAdapter(this, tasks);
        listView.setAdapter(adapter);
    }
}
