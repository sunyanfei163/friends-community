package com.sun.util.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisCache implements Cache {

	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

	private static JedisConnectionFactory jedisConnectionFactory;

	private final String id;

	private final ReadWriteLock rwl = new ReentrantReadWriteLock();

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.debug("MybatisRedisCache:id=" + id);
		this.id = id;
	}

	/**
	 * 清空缓存
	 */
	public void clear() {
		rwl.readLock().lock();
		JedisConnection connection = null;
			try {
				connection = jedisConnectionFactory.getConnection();
				connection.flushDb();
				connection.flushAll();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(connection != null) {
					connection.close();
				}
				rwl.readLock().unlock();
			}
	}

	public String getId() {
		return this.id;
	}

	public Object getObject(Object key) {
		//先从缓存中去取数据，先加锁
		rwl.readLock().lock();
		Object result = null;
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = serializer.deserialize(connection.get(serializer.serialize(key)));
			logger.info("命中mybatis二级缓存，value=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connection.close();
			}
			rwl.readLock().unlock();
		}
		return result;
	}

	public ReadWriteLock getReadWriteLock() {
		return rwl;
	}

	/**
	 * 获取缓存总量
	 */
	public int getSize() {
		int result = 0;
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.parseInt(connection.dbSize().toString());
		} catch (Exception e) {
			if(connection != null) {
				connection.close();
			}
		}
		return result;
	}

	/**
	 * 
	 */
	public void putObject(Object key, Object value) {
		rwl.readLock().lock();
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			connection.set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
			logger.info("添加mybatis二级缓存key=" + key + ",value=" + value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connection.close();
			}
			rwl.readLock().unlock();
		}

	}

	public Object removeObject(Object key) {
		rwl.readLock().lock();
		Object result = null;
		JedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expire(serializer.serialize(key), 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				connection.close();
			}
			rwl.readLock().unlock();
		}
		return result;
	}
	
	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}

}
