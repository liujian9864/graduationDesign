package com.ecut.frozenpearassistant.orm.entity;


import java.util.List;

/**
 * Created by Administrator on 2017/12/31.
 */
public class PageMessage {
    private List<MessageEntity> messages;
    private  int totalPage;
    private  int currentPage;

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public PageMessage() {
    }

    @Override
    public String toString() {
        return "PageLaw{" +
                "messages=" + messages +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                '}';
    }
}
