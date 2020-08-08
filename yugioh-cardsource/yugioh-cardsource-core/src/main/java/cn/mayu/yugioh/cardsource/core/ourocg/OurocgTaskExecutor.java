package cn.mayu.yugioh.cardsource.core.ourocg;

import java.util.concurrent.*;

public class OurocgTaskExecutor {

    private static final ThreadPoolExecutor EXECUTOR;

    static {

        EXECUTOR = new ThreadPoolExecutor(1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                runnable -> {
                  Thread thread = new Thread(runnable);
                  thread.setName("Ourocg-Task");
                  return thread;
                });
    }

    protected static void exec(Runnable r) {
        EXECUTOR.execute(r);
    }
}
