package com.jcx.military.project.task.registry.service;

import com.jcx.military.project.redis.RedisService;
import com.jcx.military.project.task.data.SyncTask;
import com.jcx.military.project.task.data.SyncTaskFile;
import com.jcx.military.project.util.StringUtil;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    RedisService redisService;

    public TaskServiceImpl() {
    }

    public List<SyncTaskFile> selectTaskFile(int fileStatus, int limitCount) {
        String cache = fileStatus == 0 ? "SYNC::TASK_PRODUCER_DOWNLOAD" : "SYNC::TASK_PRODUCER_RETRY";
        cache = cache + StringUtil.getUUid();

        List var4;
        try {
            if (!this.redisService.getLock(cache)) {
                return null;
            }

            var4 = Arrays.asList(new SyncTaskFile(), new SyncTaskFile());
        } finally {
            this.redisService.releaseLock(cache);
        }

        return var4;
    }

    public List<SyncTask> selectTask(int limitCount) {
        String cache = "SYNC::TASK_PRODUCER_PACK" + StringUtil.getUUid();

        try {
            if (this.redisService.getLock(cache)) {
                List var3 = Arrays.asList(new SyncTask(), new SyncTask());
                return var3;
            }
        } finally {
            this.redisService.releaseLock(cache);
        }

        return null;
    }
}
