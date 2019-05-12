package whs.picture.service;


import org.springframework.transaction.annotation.Transactional;
import whs.picture.dao.PictureDao;
import whs.picture.domain.PageBean;
import whs.picture.domain.Picture;
import whs.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class PictureService  {
    private PictureDao pictureDao;

    public void setPictureDao(PictureDao pictureDao) {
        this.pictureDao = pictureDao;
    }

    /**
     * 随机浏览，查询所有照片
     * @param page
     * @return
     */
    public PageBean<Picture> findAll(Integer page) {
        PageBean<Picture> picturePageBean=new PageBean<Picture>();
        int limit=18;
        int totalPage=0;
        picturePageBean.setPage(page);
        picturePageBean.setLimit(limit);
        //在这里查询出来所有图片的记录数
        Integer totalCount=pictureDao.findAllCount();
        picturePageBean.setTotalCount(totalCount);
        if(totalCount%limit==0){
            totalPage=totalCount/limit;
        }
        else{
            totalPage=totalCount/limit+1;
        }
        picturePageBean.setTotalPage(totalPage);
        int begin=(page-1)*limit;
        //在这里查询出来所有的图片集合
        List<Picture> pictureList=pictureDao.findAll(begin,limit);

        List<Picture> pictureListCopy=new ArrayList<Picture>();
        for(Picture yuanshi:pictureList){
            Picture newPic=new Picture();
            newPic.setPid(yuanshi.getPid());
            newPic.setPurl(yuanshi.getPurl());
            newPic.setPname(yuanshi.getPname());
            newPic.setPisprivate(yuanshi.getPisprivate());
            newPic.setPexpress(yuanshi.getPexpress());
            User newUser=new User();
            newUser.setUid(yuanshi.getUser().getUid());
            newPic.setUser(newUser);
            pictureListCopy.add(newPic);
        }
        for(Picture change:pictureListCopy){
            int index=change.getPurl().lastIndexOf('.');
            String url=change.getPurl();
            String newurl=url.substring(0,index)+"-small"+url.substring(index);
            change.setPurl(newurl);
        }
        picturePageBean.setList(pictureListCopy);
        return picturePageBean;
    }

    /**
     * 保存图片
     * @param picture
     */
    public void savePicture(Picture picture) {
        pictureDao.savePicture(picture);
    }
    /**
     * 通过用户id查询个人照片
     * @param userId
     * @param page
     * @return
     */
    public PageBean<Picture> findPictureByUid(String userId, Integer page) {
        PageBean<Picture> picturePageBean=new PageBean<Picture>();
        int limit=18;
        int totalPage=0;
        picturePageBean.setPage(page);
        picturePageBean.setLimit(limit);
        //在这里查询出来所有图片的记录数
        Integer totalCount=pictureDao.findPersionalCount(userId);
        picturePageBean.setTotalCount(totalCount);
        if(totalCount%limit==0){
            totalPage=totalCount/limit;
        }
        else{
            totalPage=totalCount/limit+1;
        }
        picturePageBean.setTotalPage(totalPage);
        int begin=(page-1)*limit;
        //在这里查询出来所有的图片集合
        List<Picture> pictureList=pictureDao.findPersionalPicture(userId,begin,limit);
        //出现了一个重大失误，因为hibernate一级缓存快照区的存在，更改之后的图片地址在连接提交的时候，更新到了数据库，这还得了，每次都在后面添加一个字段，我的天
        List<Picture> pictureListCopy=new ArrayList<Picture>();
        for(Picture yuanshi:pictureList){
            Picture newPic=new Picture();
            newPic.setPid(yuanshi.getPid());
            newPic.setPurl(yuanshi.getPurl());
            newPic.setPname(yuanshi.getPname());
            newPic.setPisprivate(yuanshi.getPisprivate());
            newPic.setPexpress(yuanshi.getPexpress());
            User newUser=new User();
            newUser.setUid(yuanshi.getUser().getUid());
            newPic.setUser(newUser);
            pictureListCopy.add(newPic);
        }

        for(Picture change:pictureListCopy){
            int index=change.getPurl().lastIndexOf('.');
            String url=change.getPurl();
            String newurl=url.substring(0,index)+"-small"+url.substring(index);
            change.setPurl(newurl);
        }
        picturePageBean.setList(pictureListCopy);
        System.out.println("这里是PictureService"+picturePageBean.toString());
        return picturePageBean;

    }


    public Picture findPictureByPid(String pictureId) {
        return pictureDao.findPictureByPid(pictureId);
    }

    public void updatePicture(Picture picture) {
        pictureDao.updatePicture(picture);
    }
}
