package com.jcx.military.project.task.data;


import java.util.List;


public class TaskInfo {

    private long id;

    private String taskId;

    private String appCode;

    private String reason;

    /**
     * status    remain_count
     *  1          totalCount-n      处理中
     *  2           0                打包中
     *  3           0                完成
     *  4          totalCount-n      失败
     */
    private int status;

    private int remain_count;

    List<SyncTaskFile> files;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRemain_count() {
        return remain_count;
    }

    public void setRemain_count(int remain_count) {
        this.remain_count = remain_count;
    }

    public List<SyncTaskFile> getFiles() {
        return files;
    }

    public void setFiles(List<SyncTaskFile> files) {
        this.files = files;
    }
}
