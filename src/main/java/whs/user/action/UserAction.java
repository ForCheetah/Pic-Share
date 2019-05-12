package whs.user.action;
/**
 * 此文件中有两个“\\”被改成了/
 */

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import utils.CommonUtils;
import whs.user.domain.User;
import whs.user.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user=new User();

    private UserService userService;


    private String verifyCode;
    //用于获取传来的用户id
    private String userId;



    //文件上传
    private File upload;//和页面上表单的名称相同
    private String uploadContentType;//上传文件的Mime类型
    private String uploadFileName;//上传文件名称
    private File uploadphoto;
    private String uploadphotoContentType;//上传文件的Mime类型
    private String uploadphotoFileName;//上传文件名称
    private String updatename;
    private String updatemotto;
    private String updateexpress;
    private String updateuid;

    public void setUpdateuid(String updateuid) {
        this.updateuid = updateuid;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadphoto(File uploadphoto) {
        this.uploadphoto = uploadphoto;
    }

    public void setUploadphotoContentType(String uploadphotoContentType) {
        this.uploadphotoContentType = uploadphotoContentType;
    }

    public void setUploadphotoFileName(String uploadphotoFileName) {
        this.uploadphotoFileName = uploadphotoFileName;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }

    public void setUpdatemotto(String updatemotto) {
        this.updatemotto = updatemotto;
    }

    public void setUpdateexpress(String updateexpress) {
        this.updateexpress = updateexpress;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }


    @Override
    public User getModel() {
        return user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public String loginPage(){
        return "goToLoginPage";
    }


    public String goToIndex(){
        return SUCCESS;
    }
    //第二个方法，将用户的信息保存起来，也就是用户注册
    public String saveUser(){
        //先判断验证码
        String code= (String) ActionContext.getContext().getSession().get("verifyCode");
        if(!verifyCode.equalsIgnoreCase(code)){
            System.out.println("验证码输入错误！");
            this.addActionMessage("验证码输入错误！");
            return "saveFiled";
        }
        User existUser=userService.findUserByUname(user.getUname());
        if(existUser!=null){
            this.addActionMessage("昵称已存在！");
            return "saveFiled";
        }
        //没有重复的昵称，则可以注册
        user.setUid(CommonUtils.uuid());
        user.setUlogintime(new Date(System.currentTimeMillis()));
        user.setUphoto("photo/defaultPhoto.jpg");
        user.setUimg("ChatHead/superman.png");

        userService.saveUser(user);

        this.addActionMessage("注册成功，请登录");
        ServletActionContext.getRequest().setAttribute("flag","true");
        System.out.println("保存用户成功");
        return "saveUserSuccess";
    }


    /**
     * 登录函数
     */
    public String tologin(){

        System.out.println("UserAction"+user.toString());
        User existUser=userService.findUserByUname(user.getUname());

        if(existUser==null){
            this.addFieldError("nameDontExist","昵称不存在");
            return "loginFiled";
        }
        if(!existUser.getUpassword().equals(user.getUpassword())){

            this.addFieldError("passwordError","密码不正确");
            return "loginFiled";
        }
        ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);

        return "loginSuccess";
    }


    //到达个人主页面
    public String findPersionalPage(){

        //不知道为什么，在连接里面穿uid 通过set方法 获取不到？？？？？？？
        userId= ServletActionContext.getRequest().getParameter("userId");
        user=userService.findUserByUid(userId);
        if(user!=null){
            System.out.println("这里是Action"+user.toString());
        }
        Long day=(System.currentTimeMillis()-user.getUlogintime().getTime())/(1000*60*60*12);
        ActionContext.getContext().getValueStack().set("day",day);

        return "findPersionalPageSuccess";
    }


    /**
     * 联系我们
     */
    public String contactUs(){
        return "contactUs";
    }

    //转入到资料修改页面
    public String updateUser(){
        return "goToUpdatePage";
    }

    //修改用户资料
    public String updateData(){

        User existUser=userService.findUserByUid(updateuid);
        user.setUid(updateuid);
        user.setUpassword(existUser.getUpassword());
        user.setUname(updatename);

        String path= ServletActionContext.getServletContext().getRealPath("/ChatHead");
        String path2= ServletActionContext.getServletContext().getRealPath("/photo");
        if(uploadFileName.getBytes().length!=uploadFileName.length()){
            uploadFileName= CommonUtils.uuid()+uploadFileName.substring(uploadFileName.lastIndexOf('.'));
        }
        uploadFileName=uploadFileName.toLowerCase();
        if(uploadphotoFileName.getBytes().length!=uploadphotoFileName.length()){
            uploadphotoFileName= CommonUtils.uuid()+uploadphotoFileName.substring(uploadphotoFileName.lastIndexOf('.'));
        }
        uploadphotoFileName=uploadphotoFileName.toLowerCase();

        String realPath=path+"\\"+uploadFileName;
        File diskFile=new File(realPath);
        System.out.println("这里是userAction的224行"+realPath);
        try {
            FileUtils.copyFile(upload,diskFile);
        } catch (IOException e) {
            throw new RuntimeException("图片1未能保存成功");
        }
        String realPath2=path2+"\\"+uploadphotoFileName;
        System.out.println("这里是UserAction的231行"+realPath2);
        File diskFile2=new File(realPath2);
        try {
            FileUtils.copyFile(uploadphoto,diskFile2);
        } catch (IOException e) {
            throw new RuntimeException("图片2未能保存成功");
        }

        user.setUimg("ChatHead/"+uploadFileName);
        user.setUphoto("photo/"+uploadphotoFileName);
        user.setUlogintime(existUser.getUlogintime());
        user.setMotto(updatemotto);
        user.setUexpress(updateexpress);
        System.out.println(user.toString());
        userService.updateUser(user);
        ServletActionContext.getRequest().getSession().setAttribute("existUser",user);

        Long day=(System.currentTimeMillis()-user.getUlogintime().getTime())/(1000*60*60*12);
        ActionContext.getContext().getValueStack().set("day",day);
        return "updateSuccess";
    }
}
