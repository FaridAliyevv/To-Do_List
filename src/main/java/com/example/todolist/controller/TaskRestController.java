package com.example.todolist.controller;

import com.example.todolist.entity.Task;
import com.example.todolist.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskRestController {

    private TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/todoList")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String task, @RequestParam String priority) {
        taskService.addTask(new Task(task, priority));
        return "redirect:/todoList";
    }

    @PostMapping("/todoList/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        System.out.println("Deleting task with ID: " + id);
        taskService.deleteTask(id);
        return "redirect:/todoList";
    }


}
