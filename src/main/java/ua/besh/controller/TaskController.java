package ua.besh.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.besh.domain.SharedTask;
import ua.besh.domain.Task;
import ua.besh.repos.SharedTaskRepo;
import ua.besh.repos.TaskRepo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class TaskController {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private SharedTaskRepo sharedTaskRepo;

    @PostMapping(path = "/logout")
    public String logOut() {
        return "redirect:/login";
    }

    @PostMapping(path="/task")
    public String addNewTask(String taskBody, Authentication authentication,
                             Map<String, Object> model) {

        String userEmail = authentication.getName();
        Task task = new Task(taskBody, userEmail);
        taskRepo.save(task);

        Iterable<Task> tasks = taskRepo.findByMessageAuthor(userEmail);
        model.put("task", tasks);

        return "redirect:/task";
    }

    @GetMapping(path="/task")
    public String getTasks(Authentication authentication, Map<String, Object> model) {

        String userEmail = authentication.getName();
        Iterable<SharedTask> sharedTasksFromDb = sharedTaskRepo.findByCooperatorEmail(userEmail);

        Set<Long> sharedTasksIds = StreamSupport.stream(sharedTasksFromDb.spliterator(), false)
                .map(task -> task.getTaskId())
                .collect(Collectors.toSet());

        Iterable<Task> sharedTask = taskRepo.findAllById(sharedTasksIds);

        Iterable<Task> task = taskRepo.findByMessageAuthor(userEmail);

        List<Task> target = new ArrayList<>();
        task.forEach(target::add);

        model.put("task", target);
        if (!sharedTasksIds.isEmpty()) {
            model.put("taskType", true);
        }
        model.put("sharedTask", sharedTask);

        return "task";
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.POST)
    public String updateTask(@PathVariable(value="id") Long id, String messageBody) {
        Optional<Task> updatedTask = taskRepo.findById(id);
        updatedTask.ifPresent(task -> {
            task.setMessageBody(messageBody);
            taskRepo.save(task);
        });

        return "redirect:/task";
    }

    @RequestMapping(value = "/task/delete/{id}", method = RequestMethod.POST)
    public String deleteTask(@PathVariable(value = "id") Long id) {
        taskRepo.deleteById(id);
        return "redirect:/task";
    }

    @RequestMapping(value = "/task/share/{id}", method = RequestMethod.POST)
    public String shareTask(@PathVariable(value = "id") Long id, String sharedEmail) {
        SharedTask sharedTask = new SharedTask(id, sharedEmail);
        sharedTaskRepo.save(sharedTask);
        return "redirect:/task";
    }



}