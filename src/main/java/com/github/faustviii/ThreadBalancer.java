package com.github.faustviii;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadBalancer {

    //TODO: to think?
    List<Thread> threads;

    public ThreadBalancer(Runnable... runnables) {
        threads = Arrays.stream(runnables).map(Thread::new).collect(Collectors.toList());
    }

}
