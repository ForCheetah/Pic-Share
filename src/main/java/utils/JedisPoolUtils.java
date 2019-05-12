package utils;
import java.util.ResourceBundle;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {

    //私有构造方法
    private JedisPoolUtils(){}
    //保证内存中只有一个连接池对象
    private static JedisPool pool = null;
    //静态代码块
    static{
        //读取资源文件
//        ResourceBundle bundle = ResourceBundle.getBundle("jedisPool");
        //读取相应的值
        JedisPoolConfig config = new JedisPoolConfig();
        //最小空闲连接数
//        config.setMinIdle(Integer.parseInt(bundle.getString("minIdle")));
        config.setMaxIdle(20);
        //最大空闲连接数
//        config.setMaxIdle(Integer.parseInt(bundle.getString("maxIdle")));
        config.setMaxIdle(30);
        //最大连接数
//        config.setMaxTotal(Integer.parseInt(bundle.getString("maxTotal")));
        config.setMaxTotal(50);
        //最大等待超时时间
//        config.setMaxWaitMillis(Integer.parseInt(bundle.getString("maxWaitMillis")));
        config.setMaxWaitMillis(10000);

//        pool = new JedisPool(config, bundle.getString("host"), Integer.parseInt(bundle.getString("port")));
        pool=new JedisPool(config,"127.0.0.1",6379);
    }

    //获取连接

    public static Jedis getJedis(){

        return pool.getResource();

    }
    //关闭连接
    public static void closeJedis(Jedis jedis) {
        jedis.close();
    }
}
