package dunzo;

import java.util.ArrayList;

public class Machine {
	private ArrayList<Slot> slots;
	private Outlet outlets[];
	Machine(ArrayList<Slot> slots, int numOutlets){
		this.slots = slots;
		this.outlets = new Outlet[numOutlets];
		for(int i=0;i<numOutlets;i++) {
			this.outlets[i] = new Outlet();
		}
	}
	public synchronized void brew(Recipe recipe){
			Outlet availableOutlet = null;
			Object thisReference = this;
			for(int i=0;i<outlets.length;i++) {
				if(outlets[i].isAvailable()) {
					availableOutlet = outlets[i];
					break;
				}
			}
			if(availableOutlet != null) {
				availableOutlet.setBusy();
				final Outlet outletToBeUsed = availableOutlet;
				new Thread(new Runnable() {
					public void run(){
						try {
							outletToBeUsed.brew(slots, recipe, thisReference);
							
						} catch(IngredientNotAvailableException e) {
							//If any exception is thrown here we need to resume execution of other pending requests
							synchronized(thisReference) {
								thisReference.notify();
							}
							System.out.println(recipe.getName()+" can't be brewed because "+e.getIngredient().getName()+" is not available");
							
						}
						catch(IngredientNotSufficientException e) {
							//If any exception is thrown here we need to resume execution of other pending requests
							synchronized(thisReference) {
								thisReference.notify();
							}
							System.out.println(recipe.getName()+" can't be brewed because "+e.getIngredient().getName()+" is not sufficient");
							
						}
					}
				}).start();		
			} else {
				new Thread(new Runnable() { //Starting a new thread so that main thread is not blocked
					public void run() {
						synchronized(thisReference) {
							try {
								thisReference.wait();
								brew(recipe);
							} catch(Exception e) {
								System.out.println("Idhar");
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
