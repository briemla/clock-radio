package de.briemla.clockradio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.briemla.clockradio.controls.Run;

public class InBackground implements Run {

    private final ExecutorService executor;

    public InBackground() {
        super();
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void run(Runnable runnable) {
        executor.submit(runnable);
    }

}
