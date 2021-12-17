package org.zeorck.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ComparatorTest {
    public interface Callable<V> {
        V call();
    }

    ExecutorService executorService = Executors.newCachedThreadPool();
    /*Future<String> threadName = executorService.submit(new Callable<String>(){

        @Override
        public String call() throws Exception {
            return Thread.currentThread().getName();
        }
    });*/

    Future<String> threadName2 = executorService.submit(
            () -> Thread.currentThread().getName());
}

