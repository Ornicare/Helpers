package com.ornilabs.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class StoppableThread extends Thread implements Runnable {
	private AtomicBoolean stopWork = new AtomicBoolean();
	private AtomicBoolean done = new AtomicBoolean();
	public static List<StoppableThread> runningThreads = new ArrayList<StoppableThread>();
	private static Object mutext = new Object();

	public final void run() {
		setup();
		while (!stopWork.get() && !done.get()) {
			doUnitOfWork();
		}
		cleanup();
	}

	/**
	 * Safely instructs this thread to stop working, letting it finish it's
	 * current unit of work, then doing any necessary cleanup and terminating
	 * the thread. Notice that this does not guarentee the thread will stop, as
	 * doUnitOfWork() could block if not properly implemented.
	 */
	public void requireStop() {
		System.out.println("Stop require...");
		stopWork.set(true);
	}

	public void done() {
		done.set(true);
	}

	protected void setup() {
		System.out.println("Add "+this.getClass().getName()+" to running threads.");
		add(this);
	}

	protected void cleanup() {
		System.out.println("Thread correctly stopped.");
		remove(this);
		done();
		System.out.println(this.getClass().getName());
	}
	
	public boolean isDone() {
		return done.get();
	}
	
	public boolean isStop() {
		return stopWork.get();
	}
	
	private static void remove(StoppableThread s) {
		synchronized (mutext ) {
			runningThreads.remove(s);
		}
	}
	
	private static void add(StoppableThread s) {
		synchronized (mutext ) {
			runningThreads.add(s);
		}
	}

	/**
	 * Does as small a unit of work as can be defined for this thread. Once
	 * there is no more work to be done, done() should be called.
	 */
	protected abstract void doUnitOfWork();
}