package com.jcx.military.project.task.controller;

import com.jcx.military.project.task.data.TaskInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task/")
public class TaskController {


    public String newTask() {



        return "taskId";
    }

    public List<TaskInfo> query() {





        return new ArrayList<>();
    }


    public void delete() {



    }





}
