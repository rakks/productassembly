package com.tiaa.factory.ProductAssembly;

import com.tiaa.factory.ProductAssembly.model.Bolt;
import com.tiaa.factory.ProductAssembly.model.Machine;
import com.tiaa.factory.ProductAssembly.model.UnfinishedGood;

public class Worker implements Runnable {

	private String command;
	private int duration;
	
	public Worker(String s, int d) {
		command = s;
		duration = d*1000;
	}
	
	private UnfinishedGood getUnfinishedGood(String request) { 
		if(request.equals("bolt"))
			return new Bolt();
		else if(request.equals("machine"))
			return new Machine();
		else 
			return null;
	}
	
	private void createProduct(UnfinishedGood bolt1, UnfinishedGood bolt2, UnfinishedGood machine){
		System.out.println("Creating Product " + command);

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Product" + command + " Created!!!");
	}
	
	public synchronized void run() {
		UnfinishedGood bolt1 = getUnfinishedGood("bolt");
		UnfinishedGood bolt2 = getUnfinishedGood("bolt");
		UnfinishedGood machine = getUnfinishedGood("machine");
		createProduct(bolt1, bolt2, machine);
	}
	
}
