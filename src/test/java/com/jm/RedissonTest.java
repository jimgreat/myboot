package com.jm;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.RedisClient;
import org.redisson.client.RedisClientConfig;
import org.redisson.client.RedisConnection;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.config.Config;

import java.util.List;

public class RedissonTest {

    private final String redisAddr = "redis://tj1-mijia-onebox20.kscn:5555";

    @Test
    public void tc(){
        EventLoopGroup group = new NioEventLoopGroup();

        RedisClientConfig config = new RedisClientConfig();
        config.setAddress(redisAddr) // 或者用rediss://使用加密连接
                .setClientName("myClient")
                .setGroup(group);

        RedisClient client = RedisClient.create(config);
        RedisConnection conn = client.connect();

        conn.sync(StringCodec.INSTANCE,RedisCommands.SET,"test","AA");

        client.shutdown();
    }

    @Test
    public void t(){
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                //可以用"rediss://"来启用SSL连接
                .addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
                .addNodeAddress("redis://127.0.0.1:7002");

        RedissonClient redisson = Redisson.create(config);
        redisson.getLock("L");
    }

    @Test
    public void t1(){
        System.out.println("A");
        Config config = new Config();
        config.useSingleServer().setAddress("redis://tj1-mijia-onebox20.kscn:5555");
        config.setCodec(new org.redisson.codec.FstCodec());
        RedissonClient redisson = Redisson.create(config);

        List<String> list = redisson.getList("L1");
        list.add("AA");
        list.add("CBB");

        System.out.println(list.get(1));

//        RLock lock = redisson.getLock("myLock");
//        boolean locked = false;
//        try{
//            locked = lock.tryLock();
//            if(locked){
//                System.out.println("Locked");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if(locked){
//                lock.unlock();
//                System.out.println("unlock");
//            }
//        }

    }

    @Test
    public void t2() throws Exception{
        Config config = new Config();
        config.useSingleServer().setAddress("redis://tj1-mijia-onebox20.kscn:5555");
        RedissonClient redisson = Redisson.create(config);
        RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
        atomicLong.set(3);
//        RFuture<Long> rf = atomicLong.incrementAndGetAsync();
//        rf.whenComplete((res,ex)->{
//            System.out.println(Thread.currentThread().getName());
//            System.out.println(res);
//        });
//        System.out.println(Thread.currentThread().getName());
////        System.out.println(atomicLong.get());
//        try{
//            Thread.sleep(1000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        RMapCache<String,String> rmc = redisson.getMapCache("mc");
//        rmc.put("a","A",1,TimeUnit.SECONDS);
//        try{
//            Thread.sleep(2000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println(rmc.get("a"));
//

        redisson.getSemaphore("s");

        RLock lock = redisson.getLock("lock");

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println("T1");
                lock.unlock();
            }
        }).start();

        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                System.out.println("T2");
                lock.unlock();
            }
        }).start();

        System.in.read();
    }

}
