package com.jcx.military.project.task.data;

public class SyncTask {

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

}
