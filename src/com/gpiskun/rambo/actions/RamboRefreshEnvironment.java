package com.gpiskun.rambo.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class RamboRefreshEnvironment implements Runnable {

	@Override
	public void run() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		List<Runnable> tasks = new ArrayList<Runnable>();
		tasks.add(() -> justWait());
		tasks.add(() -> justWait());
				
		List<Callable<Object>> callableTasks = tasks.stream().map(r -> Executors.callable(r)).collect(Collectors.toList());
		
		try {
			executorService.invokeAll(callableTasks);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
	
	private void justWait() {
		try {
			System.out.println("Task started by " + Thread.currentThread().getId());
			Thread.sleep(10000);
			System.out.println("Task stopped by " + Thread.currentThread().getId());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
