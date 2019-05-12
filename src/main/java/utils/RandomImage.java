package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class RandomImage {
    private int w = 80;
    private int h = 35;
    private Random r = new Random();

    private String[] fontNames = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    private String code = "234568790abcdefghijklmnopqrstuvwxyz";
    private Color bgColor = new Color(255, 255, 255);
    private String text;
    private Color randomColor()
    {
        int red=r.nextInt(150);
        int green=r.nextInt(150);
        int blue=r.nextInt(150);
        return new Color(red,green,blue);
    }

    private Font randomFont()
    {
        int index=r.nextInt(fontNames.length);
        String fontName=fontNames[index];
        int style=r.nextInt(4);
        int size=r.nextInt(5)+24;
        return new Font(fontName,style,size);

    }
    private char randChar()
    {
        int index=r.nextInt(code.length());
        return code.charAt(index);
    }
    private BufferedImage createImage()
    {
        BufferedImage image =new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g=(Graphics2D)image.getGraphics();
        g.setColor(this.bgColor);
        g.fillRect(0,0,w,h);
        return image;
    }
    public BufferedImage getImage()
    {
        //创建图片缓冲区
        BufferedImage image=createImage();
        Graphics2D g2=(Graphics2D)image.getGraphics();
        //用来装载生成的字符
        StringBuilder sb=new StringBuilder();
        //向图片中画4个字符
        for(int i=0;i<4;i++)
        {
            String s=randChar()+"";
            sb.append(s);
            //x设置的是写字符的x坐标
            float x=i*1.0F*w/4;
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s,x,h-5);
        }
        //将随机产生的字符串4个保存到整个对象的text成员中
        this.text=sb.toString();
        //添加干扰线
        drawLine(image);
        return image;

    }
    public String getText()
    {
        return text;
    }
    private void drawLine(BufferedImage image)
    {
        int num=3;
        Graphics2D g2=(Graphics2D)image.getGraphics();
        for(int i=0;i<num;i++)
        {
            int x1=r.nextInt(w);
            int y1=r.nextInt(h);
            int x2=r.nextInt(w);
            int y2=r.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(randomColor());
            g2.drawLine(x1,y1,x2,y2);
        }
    }
    //保存图片到指定的输出流
    public static void output(BufferedImage image, OutputStream out)throws IOException
    {
        ImageIO.write(image,"JPEG",out);
    }


}
