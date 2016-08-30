package com.efithealth.app.javabean;

/**
 * Created by 马小布 on 2016/8/29.
 */
public class BeanModifyOrderAddress {

    /**
     * msgFlag : 1
     * msgContent : 修改地址成功
     * res : 725
     */

    private String msgFlag;
    private String msgContent;
    private int res;

    public String getMsgFlag() {
        return msgFlag;
    }

    public void setMsgFlag(String msgFlag) {
        this.msgFlag = msgFlag;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
