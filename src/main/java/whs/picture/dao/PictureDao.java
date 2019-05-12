package whs.picture.dao;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import redis.clients.jedis.Jedis;
import utils.JedisPoolUtils;
import utils.PageHibernateCallback;
import whs.picture.domain.Picture;
import whs.user.dao.SaveRedisThread;
import whs.user.domain.User;

import java.util.List;


public class PictureDao extends HibernateDaoSupport {

    public Integer findAllCount() {

        List list=this.getHibernateTemplate().find("select count(*) " +
                "from Picture where pisprivate=?",false);
        if(list.isEmpty()){
            return null;
        }
        return ((Long)list.get(0)).intValue();
    }

    public List<Picture> findAll(int begin, int limit) {
        Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query hqlQuery =session.createQuery("from Picture where pisprivate=?").setParameter(0,false);
        hqlQuery.setFirstResult(begin);
        hqlQuery.setMaxResults(limit);
        List<Picture> list=hqlQuery.list();




//        String sql="from Picture where pisprivate=?";
//        List<Picture> list= this.getHibernateTemplate().executeFind(
//                new PageHibernateCallback<Picture>(sql,new Object[]{false},begin,limit));
////        List<Picture> list =this.getHibernateTemplate().executeFind(new PageHibernateCallback<Picture>(
//                "from Picture",null,begin,limit
//        ));
        return list;
    }

    public void savePicture(Picture picture) {
        this.getHibernateTemplate().save(picture);
        String uid=picture.getUser().getUid();
        picture.setUser(null);
        JSONObject pictureJson=JSONObject.fromObject(picture);
        new Thread(new SaveRedisThread("findPictureByPid",picture.getPid(),pictureJson.toString(),JedisPoolUtils.getJedis())).start();
        new Thread(new SaveRedisThread("findPictureUidByPid",picture.getPid(),uid,JedisPoolUtils.getJedis())).start();
        //同样，设置user  null之后，又更新到数据库里面去了,还需要设置回来
        User yuan=new User();
        yuan.setUid(uid);
        picture.setUser(yuan);
    }

    /**
     * 查询个人上传的照片有多少张
     * @param userId
     * @return
     */
    public Integer findPersionalCount(String userId) {
        //注意，类似的地方都修改了
        List list=this.getHibernateTemplate().find("select count(*) from Picture p join p.user u where u.uid=?",userId);
        if(list.isEmpty()){
            return null;
        }
        return ((Long)list.get(0)).intValue();
    }

    public List<Picture> findPersionalPicture(String userId, int begin, int limit) {
//        String sql="select p from Product p join p.categorySecond cs where cs.csid=?";
//        List<Product> list=this.getHibernateTemplate().
//                executeFind(new PageHibernateCallback<Product>(sql,new Object[]{csid},begin,limit));
//        return list;


//        String sql="select p from Picture p join p.user u where u.uid=?";
//        List<Picture> list =this.getHibernateTemplate().executeFind(
//                new PageHibernateCallback<Picture>(sql,new Object[]{userId},begin,limit));
//        return list;


        Session session=this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query hqlQuery =session.createQuery("from Picture where uid=? and pisprivate=?").setParameter(1,false).setParameter(0,userId);
        hqlQuery.setFirstResult(begin);
        hqlQuery.setMaxResults(limit);
        List<Picture> list=hqlQuery.list();
        return list;
    }

    public Picture findPictureByPid(String pictureId) {
        Jedis jedis= JedisPoolUtils.getJedis();
        String strInRedis=jedis.hget("findPictureByPid",pictureId);
        if(strInRedis!=null) {
            //分两步查询，把图片查出来，但是图片的用户信息，是空的，所以需要通过图片ID来查询图片的主人
            JSONObject jsonObject = JSONObject.fromObject(strInRedis);
            Picture pictureFromRedis = (Picture) JSONObject.toBean(jsonObject, Picture.class);
            User owner=new User();
            owner.setUid(jedis.hget("findPictureUidByPid",pictureId));
            pictureFromRedis.setUser(owner);
            JedisPoolUtils.closeJedis(jedis);
            return pictureFromRedis;
        }else {
            //同样，在redis中没有的话，需要将两个信息分别存放至redis中
            List list = this.getHibernateTemplate().find("from Picture where pid=?", pictureId);
            if (list.isEmpty()) {
                return null;
            }

            Picture pictureInMysql= (Picture) list.get(0);
            System.out.println("这里是PcitureDao104行"+pictureInMysql.toString());
            String uid=pictureInMysql.getUser().getUid();
            pictureInMysql.setUser(null);
            JSONObject pictureJson=JSONObject.fromObject(pictureInMysql);
            new Thread(new SaveRedisThread("findPictureByPid",pictureId,pictureJson.toString(),jedis)).start();
            new Thread(new SaveRedisThread("findPictureUidByPid",pictureId,uid,JedisPoolUtils.getJedis())).start();
            User owner =new User();
            owner.setUid(uid);
            pictureInMysql.setUser(owner);
            return pictureInMysql;
        }
    }

    public void updatePicture(Picture picture) {
        this.getHibernateTemplate().update(picture);
        String uid=picture.getUser().getUid();
        picture.setUser(null);
        JSONObject pictureJson=JSONObject.fromObject(picture);
        new Thread(new SaveRedisThread("findPictureByPid",picture.getPid(),pictureJson.toString(),JedisPoolUtils.getJedis())).start();
        User yuan=new User();
        yuan.setUid(uid);
        picture.setUser(yuan);
    }
}
