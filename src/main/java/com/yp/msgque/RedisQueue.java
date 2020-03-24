package com.yp.msgque;

//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.BoundListOperations;
//import org.springframework.data.redis.core.RedisConnectionUtils;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

public class RedisQueue<T> {
//    private BoundListOperations<String,T> listOperations;
//    private static Lock lock = new ReentrantLock();
//    private RedisTemplate redisTemplate;
//    private byte[] rawKey;
//
//    public RedisQueue(RedisTemplate redisTemplate, String key){
//        this.redisTemplate = redisTemplate;
//        rawKey = redisTemplate.getKeySerializer().serialize(key);
//        listOperations = redisTemplate.boundListOps(key);
//    }
//
//    public T takeFromTail(int timeout) throws InterruptedException{
//        lock.lockInterruptibly();
//        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
//        RedisConnection connection = connectionFactory.getConnection();
//        try{
//            List<byte[]> results = connection.bRPop(timeout,rawKey);
//            if(CollectionUtils.isEmpty(results)){
//                return null;
//            }
//            return (T)redisTemplate.getValueSerializer().deserialize(results.get(1));
//        } finally {
//            lock.unlock();
//            RedisConnectionUtils.releaseConnection(connection,connectionFactory);
//
//        }
//    }
//
//
//    public T takeFromTail() throws InterruptedException{
//        return takeFromTail(0);
//    }
//
//    public void pushFromHead(T value){
//        listOperations.leftPush(value);
//    }
//
//    public void pushFromTail(T value){
//        listOperations.rightPush(value);
//    }
//
//    public T removeFromHead(){
//        return listOperations.leftPop();
//    }
//
//    public T removeFromTail(){
//        return listOperations.rightPop();
//    }
//
//    public T takeFromHead(int timeout) throws InterruptedException{
//        lock.lockInterruptibly();
//        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
//        RedisConnection connection = connectionFactory.getConnection();
//        try{
//            List<byte[]> results = connection.bLPop(timeout, rawKey);
//            if(CollectionUtils.isEmpty(results)){
//                return null;
//            }
//            return (T)redisTemplate.getValueSerializer().deserialize(results.get(1));
//        }finally{
//            lock.unlock();
//            RedisConnectionUtils.releaseConnection(connection, connectionFactory);
//        }
//    }
//
//    public T takeFromHead() throws InterruptedException{
//        return takeFromHead(0);
//    }

}
