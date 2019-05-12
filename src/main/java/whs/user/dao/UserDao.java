package whs.user.dao;


import net.sf.json.JSONObject;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import redis.clients.jedis.Jedis;
import utils.JedisPoolUtils;
import whs.user.domain.User;

import java.util.List;

public class UserDao extends HibernateDaoSupport {
    //添加用户
    public void saveUser(User user){
        this.getHibernateTemplate().save(user);
        user.setPictureSet(null);
        JSONObject jsonObject=JSONObject.fromObject(user);
        Thread thread=new Thread(new SaveRedisThread("findByUid",user.getUid(),jsonObject.toString(),JedisPoolUtils.getJedis()));
        thread.start();

    }


    //根据昵称来查询用户
    public User findByUname(String uname) {
        Jedis jedis=JedisPoolUtils.getJedis();
        String strInRedis=jedis.hget("findByname",uname);
        if(strInRedis!=null) {
            System.out.println("这里是UserDAo找到User了"+strInRedis);
            JSONObject jsonObject = JSONObject.fromObject(strInRedis);
            User userFromRedis = (User) JSONObject.toBean(jsonObject, User.class);
            JedisPoolUtils.closeJedis(jedis);
            return userFromRedis;
        }else {

            List list = this.getHibernateTemplate().find("from User where uname=?", uname);
//        this.getHibernateTemplate().find("from User where uname=?",uname);
            if (list.isEmpty()) {
                System.out.println("UserDao中空");
                return null;
            }
            User userNotExistRedis= (User) list.get(0);
            userNotExistRedis.setPictureSet(null);
            JSONObject jsonObject=JSONObject.fromObject(userNotExistRedis);
            //接下来在使用线程池
            Thread thread=new Thread(new SaveRedisThread("findByName",uname,jsonObject.toString(),jedis));
            thread.start();
            return (User) list.get(0);
        }
    }

    public User findByUid(String uid) {


        Jedis jedis=JedisPoolUtils.getJedis();
        String strInRedis=jedis.hget("findByUid",uid);
        if(strInRedis!=null) {
            JSONObject jsonObject = JSONObject.fromObject(strInRedis);
            User userFromRedis = (User) JSONObject.toBean(jsonObject, User.class);
            JedisPoolUtils.closeJedis(jedis);
            return userFromRedis;
        }else {
            List list = this.getHibernateTemplate().find("from User where uid=?", uid);
            if (list.isEmpty()) {
                return null;
            }
            User userNotExistRedis= (User) list.get(0);
            userNotExistRedis.setPictureSet(null);
            JSONObject jsonObject=JSONObject.fromObject(userNotExistRedis);
            //接下来在使用线程池
            Thread thread=new Thread(new SaveRedisThread("findByUid",uid,jsonObject.toString(),jedis));
            thread.start();
            return (User) list.get(0);
        }
    }

    public void updateUser(User user) {
        this.getHibernateTemplate().update(user);
        user.setPictureSet(null);
        JSONObject jsonObject=JSONObject.fromObject(user);
        Thread thread=new Thread(new SaveRedisThread("findByUid",user.getUid(),jsonObject.toString(),JedisPoolUtils.getJedis()));
        thread.start();

    }
}
