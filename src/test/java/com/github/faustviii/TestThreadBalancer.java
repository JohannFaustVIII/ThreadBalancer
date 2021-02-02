package com.github.faustviii;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestThreadBalancer {

    private ThreadBalancer testedThreadBalancer;

    @Test
    public void testAddingRunnable(){
        Runnable runnable1 = createEmptyRunnable();
        Runnable runnable2 = createEmptyRunnable();

        testedThreadBalancer = new ThreadBalancer(runnable1, runnable2);

        //check if threadBalancer have thread with runnables
    }

    @Test
    public void testAddingRunnableMore() {
        Runnable runnable1 = createEmptyRunnable();
        Runnable runnable2 = createEmptyRunnable();
        Runnable runnable3 = createEmptyRunnable();
        Runnable runnable4 = createEmptyRunnable();
        Runnable runnable5 = createEmptyRunnable();

        testedThreadBalancer = new ThreadBalancer(runnable1, runnable2, runnable3, runnable4, runnable5);

        List<Thread> threads = testedThreadBalancer.threads;
        //check if threadBalancer have thread with runnables
        Assert.assertEquals(5, threads.size());
        //TODO: to think how get Thread.target variable from Thread class, not to try with reflection
        Assert.assertEquals(runnable1, threads.get(0));
    }

    private Runnable createEmptyRunnable() {
        return () -> {};
    }

}
