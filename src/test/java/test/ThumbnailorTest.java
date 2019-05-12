package test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ThumbnailorTest {
    public static void main(String[] args) throws IOException {

        //实现图片的缩放
        Thumbnails.of(new File("D:\\java work\\ImageShareWithRedisNew\\src\\test\\java\\test\\rome-441767.jpg"))
                .scale(0.2).toFile("D:\\java work\\ImageShareWithRedisNew\\src\\test\\java\\test\\rome-small.jpg");
        //为图片添加水印
        Thumbnails.of(new File("D:\\java work\\ImageShareWithRedisNew\\src\\test\\java\\test\\rome-441767.jpg"))
                .size(1028,1024).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("D:\\java work\\ImageShareWithRedisNew\\src\\test\\java\\test\\maps.png")),0.6f)
                .outputQuality(0.8).toFile("D:\\java work\\ImageShareWithRedisNew\\src\\test\\java\\test\\rome-water.jpg");

    }
}
