package com.tiaa.factory.ProductAssembly;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class App 
{
	static final int NO_OF_WORKERS = 3;
	static ExecutorService executor;
	
    public static void main( String[] args )
    {
        System.out.println( "******Product Assembly Factory*******" );
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter no. of Machines : ");
        int x = scanner.nextInt();
        System.out.println("Enter no. of bolts");
        int y = scanner.nextInt();
        System.out.println("Enter Time (in seconds) to assemble a product");
        int n = scanner.nextInt();
        scanner.close();
        
        System.out.println("Input : " + x + ", " + y + ", " + n);
        int noOfRuns = (x <= y/2)?x:y/2;
        
        executor = Executors.newFixedThreadPool(NO_OF_WORKERS);
        Long startTime = System.currentTimeMillis();
        
        for (int i = 1; i <= noOfRuns; i++) {
            Runnable worker = new Worker("" + i, n);
            executor.execute(worker);
          }
        
        try {
			waitForTasksToComplete();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Long endTime = System.currentTimeMillis();
        System.out.println("OUTPUT: ");
        System.out.println("Total Products : " + noOfRuns);
        System.out.println("Total time Taken : " + (endTime - startTime)/1000);
    }
    
    private static void waitForTasksToComplete() throws InterruptedException {
        executor.shutdown();
        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
          // There is nothing that takes even near 1 second in this test. So if the executor does not terminate within
          // 30s, than there is surely an error.
          throw new Error("executor did not terminate within 60s.");
        }
      }
}
