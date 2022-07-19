import java.util.concurrent.locks.ReentrantLock;


public class TrashTruck extends Thread {
	private static Queue<TrashType> collection;
	private Queue<PrivateTruckData> outputCollection = new Queue<PrivateTruckData>();
	private ReentrantLock collectionLock = new ReentrantLock();
	private ReentrantLock outputCollectionLock = new ReentrantLock();
	
	
	public TrashTruck(Queue<TrashType> list) {
		TrashTruck.collection = list;
	}
	
	class PrivateTruckData{
		 int[] trashTypeArray = new int[TrashType.values().length];
		
		public void print() {
			for(int i=0; i < trashTypeArray.length; i++) {
				
				System.out.println(trashTypeArray[i]);
			}
		}
		
		
		public void collectTrash(TrashType t) {
			trashTypeArray[t.ordinal()]++;
		}
		
		public int[] getCounts() {
			return trashTypeArray;
		}
	}
	static TrashTruck truck = new TrashTruck(collection);


	public static class PrivateDataInstance {
 public final static ThreadLocal<PrivateTruckData> collection =
     new ThreadLocal<PrivateTruckData>() {
         @Override 
         protected PrivateTruckData initialValue() {
        	
             return truck.new PrivateTruckData();
         };
 };

/**
 * Returns one instance of the TransactionCollection class
 * @return
 */
 public static PrivateTruckData get() {
     return collection.get();
 }
}

	
	
	@Override
	public void run() {
		
		int i = 0;
		do {
			
			i++;
			collectionLock.lock();
			try {
				PrivateDataInstance.get().collectTrash(collection.dequeue());
				collectionLock.unlock();
			}
			catch(Exception e) {
				System.out.println("oops");
			}
		} while(i<1000);
		
		outputCollectionLock.lock(); 
		try {
			outputCollection.enqueue(PrivateDataInstance.get());
		} finally {
			outputCollectionLock.unlock();
		}
		
	
}
	public void printAll(){
		for(int i=1; i<5;i++) {
			 
			System.out.println("Driver " + i + ":");
			int[] trash = new int[TrashType.values().length];
			 trash = outputCollection.dequeue().trashTypeArray;
			for(int a=0; a<5;a++) {
				
				String t = null ;
				switch(a) {
				case 0: t = "Holey socks: ";
					break;
				case 1: t = "Banana Peels: ";
					break;
				case 2: t = "BioHazard Vial: ";
					break;
				case 3: t = "Plastic Bottle: ";
					break;
				case 4: t = "Broken Toys: ";
				}
				
			System.out.println(t + trash[a]);
		}
			
	}
	
}
}
