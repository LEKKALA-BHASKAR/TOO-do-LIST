package com.example.too_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText editName, editDescription;
    private Button buttonSave, buttonDelete;
    private int taskId;
    private TodoTask currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        databaseHelper = new DatabaseHelper(this);
        editName = findViewById(R.id.edit_name);
        editDescription = findViewById(R.id.edit_description);
        buttonSave = findViewById(R.id.button_save);
        buttonDelete = findViewById(R.id.button_delete);

        Intent intent = getIntent();
        taskId = intent.getIntExtra("TASK_ID", -1);
        if (taskId == -1) {
            Toast.makeText(this, "Invalid Task ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadTaskDetails();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask();
            }
        });
    }

    private void loadTaskDetails() {
        currentTask = databaseHelper.getTodoTaskById(taskId);
        if (currentTask != null) {
            editName.setText(currentTask.getName());
            editDescription.setText(currentTask.getDescription());
            // You can load other fields if you have UI components for them
        } else {
            Toast.makeText(this, "Task not found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void updateTask() {
        String name = editName.getText().toString().trim();
        String description = editDescription.getText().toString().trim();

        if (name.isEmpty()) {
            editName.setError("Task name is required");
            editName.requestFocus();
            return;
        }

        // Update only the fields that can be edited
        currentTask.setName(name);
        currentTask.setDescription(description);
        // If you have fields like deadline, reminderTime, etc., handle them here

        databaseHelper.updateTodoTask(currentTask);
        Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void deleteTask() {
        databaseHelper.deleteTodoTask(taskId);
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
