package com.varmin.demos.others.expandable;

import java.util.List;

/**
 * author：yang
 * created on：2020/9/21 15:10
 * description: 游戏论坛列表
 */
public class GamePostItem {

    /**
     * total :
     * pages :
     * size :
     * startRow :
     * pageSize :
     * endRow :
     * page :
     * list : [{"listGameEvaluate":[{"listGameEvaluate":"","issueId":"","createTime":"","updateTime":"","sort":"置顶","userId":"用户id","content":"回复内容","parentId":"消息主键id","evaluateId":"回复主键id","status":"0 正常 1删除"}],"issueId":"","createTime":"创建时间","updateTime":"更新时间","sort":"置顶","userId":"用户id","content":"消息内容","parentId":"父id","evaluateId":"消息主键id","status":"0正常 1删除"}]
     * pageNum :
     */
    public String total;
    public String pages;
    public String size;
    public String startRow;
    public String pageSize;
    public String endRow;
    public String page;
    public List<PostItem> list;
    public String pageNum;

    public static class PostItem {
        /**
         * listGameEvaluate : [{"listGameEvaluate":"","issueId":"","createTime":"","updateTime":"","sort":"置顶","userId":"用户id","content":"回复内容","parentId":"消息主键id","evaluateId":"回复主键id","status":"0 正常 1删除"}]
         * issueId :
         * createTime : 创建时间
         * updateTime : 更新时间
         * sort : 置顶
         * userId : 用户id
         * content : 消息内容
         * parentId : 父id
         * evaluateId : 消息主键id
         * status : 0正常 1删除
         */
        public List<Evaluate> listGameEvaluate;
        public String issueId;
        public String createTime;
        public String updateTime;
        public String sort;
        public String userId;
        public String content;
        public String parentId;
        public String evaluateId;
        public String status;

        public static class Evaluate {
            /**
             * listGameEvaluate :
             * issueId :
             * createTime :
             * updateTime :
             * sort : 置顶
             * userId : 用户id
             * content : 回复内容
             * parentId : 消息主键id
             * evaluateId : 回复主键id
             * status : 0 正常 1删除
             */
            public String listGameEvaluate;
            public String issueId;
            public String createTime;
            public String updateTime;
            public String sort;
            public String userId;
            public String content;
            public String parentId;
            public String evaluateId;
            public String status;
        }
    }
}
