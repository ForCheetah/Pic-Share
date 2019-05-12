package test;

import redis.clients.jedis.Jedis;
import utils.JedisPoolUtils;

public class RedisTest {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = JedisPoolUtils.getJedis();
        System.out.println("连接成功");
        jedis.hset("ceshi","haha","shichi");
        System.out.println(jedis.hget("ceshi","haha"));
        jedis.hset("ceshi","haha","xindezhi");
        System.out.println(jedis.hget("ceshi","haha"));
        JedisPoolUtils.closeJedis(jedis);

    }

}
