package sample.controllers;

import sample.entities.PageEntity;

import java.io.File;

public class SendFile implements Runnable {
    private File file;
    private Integer pageId;
    private Integer bookId;

    public SendFile(Integer bookId, int pageId, File file) {
        this.bookId = bookId;
        this.pageId = pageId;
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println("dane");
        System.out.println(pageId);
        System.out.println(file);
        PageEntity page = new PageEntity();
        page.createNewPage(this.bookId, this.pageId, this.file);
    }
}
