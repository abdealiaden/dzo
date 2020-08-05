package dunzo;

import java.util.ArrayList;

public class Machine {
	private ArrayList<Slot> slots;
	private Outlet outlets[];
	private ArrayList<Recipe> recipes;
	Machine(ArrayList<Slot> slots, ArrayList<Recipe> recipes, int numOutlets){
		this.slots = slots;
		this.recipes = recipes;
		this.outlets = new Outlet[numOutlets];
		for(int i=0;i<numOutlets;i++) {
			this.outlets[i] = new Outlet();
		}
	}
	public void brew(Recipe recipe) {
			Outlet availableOutlet = null;
			Object thisReference = this;
			for(int i=0;i<outlets.length;i++) {
				if(outlets[i].isAvailable()) {
					availableOutlet = outlets[i];
					break;
				}
			}
			if(availableOutlet != null) {
				final Outlet outletToBeUsed = availableOutlet;
				new Thread(new Runnable() {
					public void run() {
						try {
							outletToBeUsed.brew(slots, recipe, thisReference);
							
						} catch(Exception e) {
							System.out.println(e.toString());
						}
					}
				}).start();
			} else {
				new Thread(new Runnable() { //Starting a new thread so that main thread is not blocked
					public void run() {
						synchronized(thisReference) {
							try {
								thisReference.wait();
								brew(null);
							} catch(Exception e) {
								
							}
							
						}
					}
				}).start();
//				synchronized(this) { //Acquires monitor on this
//					try {
//						wait();  //wait will release the monitor but wait until notify is called which is done through Outlet
//					} catch(Exception e) {
//						
//					}
//					
//					brew(null);
//				}
			}
		
	}
}
