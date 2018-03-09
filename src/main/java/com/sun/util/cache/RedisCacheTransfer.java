package com.sun.util.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * ��̬ע���м���
 * @author SunFly
 *
 */
public class RedisCacheTransfer {

	@Autowired
	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory){
		RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}
}
