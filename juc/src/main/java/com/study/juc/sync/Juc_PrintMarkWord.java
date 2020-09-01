/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author study
 * @version : Juc_PrintMarkWord.java, v 0.1 2020年09月01日 20:22 study Exp $
 */
public class Juc_PrintMarkWord {

    public static void main(String[] args) throws InterruptedException {
        // 需要sleep一段时间，因为java对于偏向锁的启动是在启动几秒之后才激活。
        // 因为jvm启动的过程中会有大量的同步块，且这些同步块都有竞争，如果一启动就启动
        // 偏向锁，会出现很多没有必要的锁撤销
        Thread.sleep(5000);
        T t = new T();
        //未出现任何获取锁的时候
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        synchronized (t){
            // 获取一次锁之后
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
        // 输出hashcode
        System.out.println(t.hashCode());
        // 计算了hashcode之后，将导致锁的升级
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
        synchronized (t){
            // 再次获取锁
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(t).toPrintable());
    }
}

class T{
    int i = 0;
}