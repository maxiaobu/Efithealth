package com.efithealth.app.javabean;

import java.util.List;

/**
 * Created by 马小布 on 2016/8/25.
 */
public class BeanSaveOrderInfo {

    /**
     * msgFlag : 1
     * msgContent :
     * ordno : ["FO-20160825-305","FO-20160825-306"]
     */

    private String msgFlag;
    private String msgContent;
    private List<String> ordno;

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

    public List<String> getOrdno() {
        return ordno;
    }

    public void setOrdno(List<String> ordno) {
        this.ordno = ordno;
    }
}
