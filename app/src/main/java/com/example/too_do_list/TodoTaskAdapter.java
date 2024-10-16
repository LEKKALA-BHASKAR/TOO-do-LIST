package com.example.too_do_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TodoTaskAdapter extends ArrayAdapter<TodoTask> {
    private final Context context;
    private final List<TodoTask> tasks;

    public TodoTaskAdapter(@NonNull Context context, @NonNull List<TodoTask> tasks) {
        super(context, R.layout.item_task, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    private static class ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            // Inflate the layout
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_task, parent, false);

            // Set up the ViewHolder
            holder = new ViewHolder();
            holder.textViewName = convertView.findViewById(R.id.text_task_name);
            holder.textViewDescription = convertView.findViewById(R.id.text_task_description);

            convertView.setTag(holder);
        } else {
            // Use the existing ViewHolder
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the current task
        TodoTask task = tasks.get(position);

        // Populate the data into the template view
        holder.textViewName.setText(task.getName());
        holder.textViewDescription.setText(task.getDescription());

        return convertView;
    }
}
