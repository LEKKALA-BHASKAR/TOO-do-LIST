package com.example.too_do_list;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText editName, editDescription;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        databaseHelper = new DatabaseHelper(this);
        editName = findViewById(R.id.et_task_name);
        editDescription = findViewById(R.id.et_task_description);
        buttonAdd = findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }

    private void addTask() {
        String name = editName.getText().toString().trim();
        String description = editDescription.getText().toString().trim();

        if (name.isEmpty()) {
            editName.setError("Task name is required");
            editName.requestFocus();
            return;
        }

        TodoTask task = new TodoTask();
        task.setName(name);
        task.setDescription(description);
        // Set default values or gather from user inputs if available
        task.setDeadline(0); // Replace with actual deadline if implemented
        task.setReminderTime(0); // Replace with actual reminder time if implemented
        task.setPriority(TodoTask.Priority.MEDIUM); // Default priority
        task.setProgress(0); // Default progress

        long id = databaseHelper.insertTodoTask(task);
        if (id != -1) {
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
            finish(); // Close activity and return to MainActivity
        } else {
            Toast.makeText(this, "Failed to add task", Toast.LENGTH_SHORT).show();
        }
    }
}
