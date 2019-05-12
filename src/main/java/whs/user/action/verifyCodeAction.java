package whs.user.action;



import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import utils.RandomImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class verifyCodeAction extends ActionSupport {
    public String getVerifyCode() throws IOException {


        RandomImage image=new RandomImage();
        BufferedImage bufferedImage=image.getImage();
        String code=image.getText();
        ServletActionContext.getRequest().getSession().setAttribute("verifyCode",code);
        RandomImage.output(bufferedImage, ServletActionContext.getResponse().getOutputStream());

        return NONE;
    }
}
