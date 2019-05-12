package whs.picture.action;
/**
 * 注意这里有3个\\ 被改成了/   .为了linux
 */

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import utils.CommonUtils;
import whs.picture.domain.PageBean;
import whs.picture.domain.Picture;
import whs.picture.service.PictureService;
import whs.user.domain.User;
import whs.user.service.UserService;

import java.io.File;
import java.io.IOException;

public class PictureAction extends ActionSupport implements ModelDriven<Picture> {
    private Picture picture=new Picture();
    private PictureService pictureService;
    private UserService userService;
    private PageBean<Picture> picturePageBean;
    //接收第几页的参数
    private Integer page;
    /**
     * 传递总页数信息
     */

    private Integer total;
    /**
     * 接收上传者的信息ID
     */
    private String uid;
    /**
     * 是否为私密照片
     */
    private Boolean persional;
    /**
     * 用来接收来自寻找单个图片的图片ID
     */
    private String pictureId;





    /**
     * 这里都是为了文件上传时使用的
     * 文件上传使用的是百度的 webUploader
     * 参数获取 是通过火狐的浏览器找到的，通过控制台的抓包看到的名字
     *
     */

    /**
     * name是上传文件的名称，比如 404txt.jpg
     */
    private String name;

    /**
     *type是文件的mime类型
     * 如：image/jpeg
     */
    private String type;

    /**
     * 文件的大小
     */
    private String size;
    /**
     * 上传的文件
     */
    private File file;

    @Override
    public Picture getModel() {
        return picture;
    }

    //通过Spring注入PictureService
    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    //还需要通过Spring注入UserService,用于后面的用户查询
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //为picturePageBean提供一个外面获取的方法
    public PageBean<Picture> getPicturePageBean() {
        return picturePageBean;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPersional(Boolean persional) {
        this.persional = persional;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFile(File file) {
        this.file = file;
    }


    //这里用来接收传过来的一些用不到的东西
    private String fileContentType;
    private String fileFileName;
    private String lastModifiedDate;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * 这个函数是查询所有的不是私密的照片图片，用于浏览，每个人都能看，游客也能看
     * @return
     */
    public String showPool(){
        picturePageBean=pictureService.findAll(page);
        System.out.println(picturePageBean.toString());
        total=picturePageBean.getTotalPage();
        return "showPoolSuccess";
    }
    public String showFlow(){
        picturePageBean=pictureService.findAll(page);
        total=picturePageBean.getTotalPage();
        return "showFlowSuccess";
    }

    /**
     * 保存图片的功能
     * @return
     */
    public String savePicture(){
        //设置图片的主人
        User currentUser=new User();
        System.out.println("这里是PictureAction..........图片主人是"+uid);
        currentUser.setUid(uid);
        picture.setUser(currentUser);


        String pid= CommonUtils.uuid();
        picture.setPid(pid);

        //设置图片的路径
        String path= ServletActionContext.getServletContext().getRealPath("/pictures");
        if(name.getBytes().length!=name.length()){
            name=pid.substring(0,20)+name.substring(name.lastIndexOf('.'));
        }
        name=name.toLowerCase();

        String realPath=path+"\\"+pid.charAt(0)+"\\"+pid.charAt(1)+"\\"+name;   //就是这里的三个
        System.out.println("这里是PcitureAction的197行"+realPath);
        File diskFile=new File(realPath);
        try {
            FileUtils.copyFile(file,diskFile);
        } catch (IOException e) {
            throw new RuntimeException("图片未能保存成功");
        }
        //保存成功之后，打算同时保存一张压缩图片到相同的位置

            int index=name.lastIndexOf('.');
            String realPathSmall=path+"\\"+pid.charAt(0)+"\\"+pid.charAt(1)+"\\"+name.substring(0,index)+"-small"+name.substring(index);
            new Thread(new ThumBnailsThread(realPath,realPathSmall)).start();

        //保存图片信息到数据库
        picture.setPname(name);
        picture.setPisprivate(persional);
        picture.setPurl("pictures/"+pid.charAt(0)+"/"+pid.charAt(1)+"/"+name);
        pictureService.savePicture(picture);

       // picture.set
        return null;
    }

    public String showPersionalPage(){
        picturePageBean=pictureService.findPictureByUid(uid,page);
        total=picturePageBean.getTotalPage();
        return "showPersionalPageSuccess";
    }


    public String pictureHome(){


        System.out.println("这里是PictureAction 图片的id"+pictureId);
        picture =pictureService.findPictureByPid(pictureId);
        ActionContext.getContext().getValueStack().set("picture",picture);
        //本页显示的应该是图片的主人

        User owner=userService.findUserByUid(picture.getUser().getUid());

//        将Owner做压栈处理
        ActionContext.getContext().getValueStack().set("owner",owner);

        //将时间压入栈
        Long day=(System.currentTimeMillis()-owner.getUlogintime().getTime())/(1000*60*60*12);
        ActionContext.getContext().getValueStack().set("day",day);
        return "getPictureSuccess";
    }

    //转到PictureUodate图片修改页面
    public String updatePicPage(){
        picture =pictureService.findPictureByPid(pictureId);
        ActionContext.getContext().getValueStack().set("picture",picture);
        return "goToUpdatePage";
    }

    //保存图片添加信息的页面
    public String updateData(){
        Picture existPicture=pictureService.findPictureByPid(picture.getPid());
        picture.setPisprivate(existPicture.getPisprivate());
        picture.setPname(existPicture.getPname());
        picture.setPurl(existPicture.getPurl());
        picture.setUser(existPicture.getUser());
        pictureService.updatePicture(picture);
        System.out.println("这里是PictureAction"+picture.toString());
        ActionContext.getContext().getValueStack().set("picture",picture);

        User owner=userService.findUserByUid(picture.getUser().getUid());

//        将Owner做压栈处理
        ActionContext.getContext().getValueStack().set("owner",owner);

        //将时间压入栈
        Long day=(System.currentTimeMillis()-owner.getUlogintime().getTime())/(1000*60*60*12);
        ActionContext.getContext().getValueStack().set("day",day);
        return "updatePictureSuccess";
    }

}
