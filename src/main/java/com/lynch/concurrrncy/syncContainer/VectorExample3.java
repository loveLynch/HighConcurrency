package com.lynch.concurrrncy.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by lynch on 2019-11-30. <br>
 **/
@Slf4j
public class VectorExample3 {
    public static void test1(Vector<Integer> v) {
        for (Integer i : v) { //foreach
            if (i.equals(3)) {
                v.remove(i);
            }
        }

    }

    public static void test2(Vector<Integer> v) {
        Iterator<Integer> iterator = v.iterator();//iterator
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v.remove(i);
            }
        }

    }

    public static void test3(Vector<Integer> v) {
        for (int i = 0; i < v.size(); i++) {//for
            if (v.get(i).equals(3)) {
                v.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
//        test1(vector);//Exception in thread "main" java.util.ConcurrentModificationException
//        test2(vector);//Exception in thread "main" java.util.ConcurrentModificationException
        test3(vector);//success

    }
}
