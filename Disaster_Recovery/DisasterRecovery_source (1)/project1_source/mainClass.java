package com.vmware.vim25.mo.samples;

import java.io.IOException;

/**
 * Write a description of class MyVM here.
 * 
 * @author Kuntal Shah
 * @version 4.3 Date Modified: 4/3/2013 Time: 4:33:40
 */

import java.io.IOException;


public class mainClass {
	
	 public static void main(String args[])
	    {
		 			 	
		 	
		final VMotion myvm = new VMotion("Team12_Ullas_VM");
		myvm.setAlarm();
		Thread t1 = new Thread() {
			public void run() {
				while (true) {
					try {
						boolean result = myvm.pingVM();
						if (result == true) {
					
							System.out.println("VM is pinging so the VM is up and running");
						} else {

							String state = myvm.getVMState();
							
							
							if (state.equalsIgnoreCase("poweredoff")) {
								System.out.println("VM is Powered off by user so no need for cloning and migration");
							} 
							else {
								System.out.println("State is Powered On, so VM has failed it must be cloned and migrated");
								boolean hresult = myvm.pingSecondHost();
								if (hresult == true) {
									System.out.println("Yahoo!!!Another Host is still working");
									System.out.println("Now Cloning and Migrating will be start!!! Have patience ");
									try {
											myvm.cloneFromSnapshot("", "");
						

									} catch (Exception e) {
										e.printStackTrace();
									}
								} else {
									System.out.println("Second Host is not working..Wait till it resumes");
								}

							}

						}

					} catch (IOException e) {

						e.printStackTrace();
					}
					try {
						Thread.sleep(80000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}
		 	};
		 	
		 	Thread t2= new Thread(){
		 		public void run(){
		 			while(true)
		 			{
		 				myvm.snapShot("", "create");
		 				try {
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
		 			}
		 		}
		 	};
		 myvm.getAlarm();
		 t1.start();
		 t2.start();
	    }
}
