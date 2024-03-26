package com.jcx.military.project.task.registry.service;

import com.jcx.military.project.task.data.SyncTask;
import com.jcx.military.project.task.data.SyncTaskFile;

import java.util.List;

public interface TaskService {

    List<SyncTaskFile> selectTaskFile(int fileStatus, int limitCount);


    List<SyncTask> selectTask(int limitCount);
}
