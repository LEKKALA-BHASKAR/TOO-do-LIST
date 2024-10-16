package com.example.too_do_list;

public class TodoTask {

    public static final String TABLE_NAME = "tasks";

    private int id;
    private String name;
    private String description;
    private long deadline; // Stores the deadline in milliseconds since epoch
    private long reminderTime; // Stores the reminder time in milliseconds since epoch
    private Priority priority;
    private int progress; // Stores the task's progress percentage (0 to 100)

    // Default constructor
    public TodoTask() {}

    // Full constructor
    public TodoTask(int id, String name, String description, long deadline, long reminderTime, Priority priority, int progress) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.reminderTime = reminderTime;
        this.priority = priority;
        this.progress = progress;
    }

    // Partial constructor for adding/editing tasks
    public TodoTask(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        // Initialize other fields with default values or handle appropriately
        this.deadline = 0; // 0 can represent no deadline set
        this.reminderTime = 0; // No reminder set
        this.priority = Priority.MEDIUM; // Default priority
        this.progress = 0; // Default progress
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public long getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(long reminderTime) {
        this.reminderTime = reminderTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (progress < 0) {
            this.progress = 0; // Ensure progress can't be below 0
        } else if (progress > 100) {
            this.progress = 100; // Ensure progress doesn't exceed 100
        } else {
            this.progress = progress;
        }
    }

    // Priority Enum for task importance
    public enum Priority {
        LOW(0), MEDIUM(1), HIGH(2);

        private final int value;

        Priority(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Priority fromValue(int value) {
            for (Priority p : Priority.values()) {
                if (p.getValue() == value) {
                    return p;
                }
            }
            return MEDIUM; // Default value if not found
        }
    }
}
