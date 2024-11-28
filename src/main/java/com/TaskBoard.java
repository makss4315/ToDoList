package com;

import javax.swing.*;
import java.awt.*;

public class TaskBoard extends JFrame {
    private TaskManager taskManager;

    public TaskBoard() {
        // Используем Singleton метод для получения экземпляра TaskManager
        this.taskManager = TaskManager.getInstance();

        setTitle("Task Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(1, 5)); // 5 столбцов для статусов задач

        add(new TaskColumn("Unassigned", taskManager));
        add(new TaskColumn("To Do", taskManager));
        add(new TaskColumn("In Progress", taskManager));
        add(new TaskColumn("In Review", taskManager));
        add(new TaskColumn("Done", taskManager));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskBoard::new);
    }
}
