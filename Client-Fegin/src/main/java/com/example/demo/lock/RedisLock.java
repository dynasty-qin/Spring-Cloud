package com.example.demo.lock;

import com.example.demo.exception.CacheLockException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author : Harry
 * @Date :  2018-06-27 17:27
 * @Description : Redis 加锁/释放锁
 */
public class RedisLock {

	// 纳秒和毫秒之间的转换率
	public static final long MILLI_NANO_TIME = 1000 * 1000L;
	
	public static final String LOCKED = "TRUE";
	
	public static final Random RANDOM = new Random();

	private String key;

	// 封装的操作redis的工具
	private RedisTemplate<String,Object> redisTemplate;
	
	private boolean lock = true;

	/**
	 * @param purpose 锁前缀
     * @param key 锁定的ID等东西
	 * @param redisTemplate
	 */
    public RedisLock(String purpose, String key, RedisTemplate redisTemplate){
    	 this.key = purpose + "_" + key + "_lock";
    	 this.redisTemplate = redisTemplate;
    }

	/**
	 * 加锁
	 * @param timeout timeout的时间范围内轮询锁
	 * @param expire 设置锁超时时间
	 * @return 成功 or 失败
	 */
	public boolean lock(long timeout,int expire){

		System.out.println(this.key);
		long nanoTime = System.nanoTime();
		timeout *= MILLI_NANO_TIME;
		try {
			// 在timeout的时间范围内不断轮询锁
			while (System.nanoTime() - nanoTime < timeout) {
				// 锁不存在的话，设置锁并设置锁过期时间，即加锁
				if (redisTemplate.opsForValue().setIfAbsent(this.key,LOCKED)) {
					redisTemplate.expire(key,expire, TimeUnit.SECONDS);// 设置锁过期时间是为了在没有释放锁的情况下锁过期后消失，不会造成永久阻塞
					this.lock = true;
					return this.lock;
				}
				System.out.println("=========== 锁等待中 ===========");
				// 短暂休眠，避免可能的活锁
				Thread.sleep(3, RANDOM.nextInt(30));
			} 
		} catch (Exception e) {
			throw new RuntimeException("locking error",e);
		}
		return false;
	}
	
	public  void unlock() throws CacheLockException {
		try {
			if(this.lock){
				// 直接删除
				redisTemplate.delete(key);
				System.out.println("释放锁成功 !");
			}
		} catch (Throwable e) {
			throw new CacheLockException("释放锁失败 !");
		}
	}

}
