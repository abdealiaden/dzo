package dunzo;

import java.util.ArrayList;
public class Outlet {
	private boolean isFree;
	Outlet(){
		this.isFree = true;
	}
	public synchronized void brew(ArrayList<Slot> slots, Recipe recipe, Object thisReference) throws Exception {
			this.isFree = false;
			System.out.println("Coffee started brewing");
			Thread.sleep(1000);
			System.out.println("Coffee brewed");
			this.isFree = true;
			synchronized(thisReference) {
				thisReference.notify();
			}
	}
	public boolean isAvailable() {
		System.out.println(isFree?"true": "false");
		return isFree;
	}
}
