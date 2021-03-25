import java.util.PriorityQueue;

public class WebHeap {
	private PriorityQueue<WebNode> heap;
	
	public WebHeap(){
		this.heap = new PriorityQueue<WebNode>(10, new WebComparator());
	}
	
	public void add(WebNode k){
		heap.offer(k);
	}
	
	public void peek(){
		if(heap.peek() == null){
			System.out.println("InvalidOperation");
			return;
		}
		
		WebNode k = heap.peek();		
		System.out.println(k.toString());
	}
	
	public void removeTop(){
		//TODO: write remove minimal element logic here...
		if (heap.isEmpty()) {
			System.out.println("InvalidOperation");
			return;
		}
		System.out.println(heap.poll().toString());		
	}
	
	public void output(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
		WebNode k;
		
		if (heap.isEmpty()) {
			System.out.println("InvalidOperation");
			return;
		}
		while((k = heap.poll()) != null){
			sb.append(k.toString());
			if(heap.peek() != null) sb.append("");
		}
		
		System.out.println(sb.toString());	
	}
	
	public WebNode pop() {
		//TODO: write remove minimal element logic here...
		if (heap.isEmpty()) {
			System.out.println("InvalidOperation");
			return null;
		}
		return heap.poll();
	}
}