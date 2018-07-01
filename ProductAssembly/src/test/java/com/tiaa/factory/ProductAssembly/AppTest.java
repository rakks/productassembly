package com.tiaa.factory.ProductAssembly;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
	  private ExecutorService executor;
	  static final int NO_OF_WORKERS = 3;
	  
	  @Before
	  public void setUp() throws Exception {
	    executor = Executors.newFixedThreadPool(NO_OF_WORKERS);
	  }

	  @Test
	  public void checkCorrectCalculation1() throws Exception {
	    // setup
		  
	    // execution
	    final long startTime = System.currentTimeMillis();
	    int noOfRuns = 5;
        for (int i = 1; i <= noOfRuns; i++) {
            Runnable worker = new Worker("" + i, 4);
            executor.execute(worker);
          }
        
	    waitForTasksToComplete();
	    final long endTime = System.currentTimeMillis();

	    long timeTaken = endTime - startTime;
	    // evaluation
	    assertEquals((int)timeTaken/1000, 8);
	  }
	  
	  @Test
	  public void checkCorrectCalculation2() throws Exception {
	    // setup
		  
	    // execution
	    final long startTime = System.currentTimeMillis();
	    int noOfRuns = 7;
        for (int i = 1; i <= noOfRuns; i++) {
            Runnable worker = new Worker("" + i, 3);
            executor.execute(worker);
          }
        
	    waitForTasksToComplete();
	    final long endTime = System.currentTimeMillis();

	    long timeTaken = endTime - startTime;
	    // evaluation
	    assertEquals((int)timeTaken/1000, 9);
	  }
	  
	  private void waitForTasksToComplete() throws InterruptedException {
	        executor.shutdown();
	        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
	          // There is nothing that takes even near 1 second in this test. So if the executor does not terminate within
	          // 30s, than there is surely an error.
	          throw new Error("executor did not terminate within 60s.");
	        }
	      }
}
