import java.util.Comparator;

public class WebComparator implements Comparator<WebNode>{
	@Override
	public int compare(WebNode o1, WebNode o2){
		if(o1==null || o2==null) throw new NullPointerException();
		//1. compare
		if (o1.nodeScore > o2.nodeScore) {
			return -1;
		} else if (o1.nodeScore < o2.nodeScore) {
			return 1;
		}
		return 0;
	}
}