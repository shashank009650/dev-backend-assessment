package com.flxpoint.troubleshoting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class TroubleshootingTest {
	private static final ExecutorService executor = Executors.newFixedThreadPool(2);
	private static final Map<String, Integer> sharedData = new ConcurrentHashMap<>();
	private static final Logger logger = Logger.getLogger(TroubleshootingTest.class.getName());

	public static void main(String[] args) {
		TroubleshootingTest test = new TroubleshootingTest();
		test.runTest();
		logger.info("Executor: " + executor);
		logger.info("SharedData: " + sharedData);
	}

	public void runTest() {
		List<Future<Integer>> results = new ArrayList<>();

		results.add(executor.submit(() -> updateSharedData("key1")));
		results.add(executor.submit(() -> updateSharedData("key1")));

		results.add(executor.submit(this::causeNullPointer));

		results.add(executor.submit(() -> parseInteger("ABC")));

		Object lock1 = new Object();
		Object lock2 = new Object();
		executor.submit(() -> deadlockMethod(lock1, lock2));
		executor.submit(() -> deadlockMethod(lock2, lock1));

		// Removed missingMethod() call since it doesn't exist

		@SuppressWarnings("unused")
		String num = "100"; // Fixed incorrect type assignment

		executor.submit(this::methodThrowsException);

		// Removed infinite loop to prevent thread hanging

		executor.submit(this::unclosedScanner);

		executor.shutdown();
	}

	private Integer updateSharedData(String key) {
		synchronized (sharedData) {
			int currentValue = sharedData.getOrDefault(key, 0);
			sharedData.put(key, currentValue + 1);
			return currentValue + 1;
		}
	}

	@SuppressWarnings("null")
	private Integer causeNullPointer() {
		try {
			String str = null;
			return str.length();
		} catch (NullPointerException e) {
			logger.warning("NullPointerException caught: " + e.getMessage());
			return -1;
		}
	}

	private Integer parseInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			logger.warning("NumberFormatException caught: " + e.getMessage());
			return -1;
		}
	}

	private void deadlockMethod(Object lock1, Object lock2) {
		synchronized (lock1) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ignored) {
			}
			synchronized (lock2) {
				logger.warning("Acquired both locks safely");
			}
		}
	}

	@SuppressWarnings("unused")
	private void infiniteLoop() {
		while (true) {
		}
	}

	private void unclosedScanner() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter something: ");
		String input = scanner.nextLine();
		System.out.println("You entered: " + input);
	}

	private void methodThrowsException() {
		try {
			throw new Exception("Test Exception");
		} catch (Exception e) {
			logger.warning("Exception caught: " + e.getMessage());
		}
	}

}
