package whs.user.dao;

import redis.clients.jedis.Jedis;
import utils.JedisPoolUtils;

public class SaveRedisThread implements Runnable {
    private String KeyOfHash;
    private String Field;
    private String value;
    private Jedis jedis;
    private SaveRedisThread(){}
    public SaveRedisThread(String KeyOfHash, String Field, String value, Jedis jedis){
        this.KeyOfHash=KeyOfHash;
        this.Field=Field;
        this.value=value;
        this.jedis=jedis;
    }
    @Override
    public void run() {
        System.out.println("这里是SaveRedisThread，已经保存了");
        jedis.hset(KeyOfHash,Field,value);
        if(jedis!=null) {
            JedisPoolUtils.closeJedis(jedis);
        }
    }
}
