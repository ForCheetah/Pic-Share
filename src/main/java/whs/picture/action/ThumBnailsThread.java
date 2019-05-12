package whs.picture.action;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

public class ThumBnailsThread implements Runnable{
    private String realPath;
    private String realPathSmall;
    private ThumBnailsThread(){}
    public ThumBnailsThread(String realPath,String realPathSmall){
        this.realPath=realPath;
        this.realPathSmall=realPathSmall;
    }
    @Override
    public void run() {
        try {
            Thumbnails.of(new File(realPath)).size(600,400).toFile(realPathSmall);
        } catch (IOException e) {
            throw new RuntimeException("压缩图片失败"+e.getMessage());
        }
    }
}
