import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	
	private ArrayList<WebTree> treeList = new ArrayList<WebTree>();
	private ArrayList<WebNode> newResult = new ArrayList<WebNode>();
	private ArrayList<Keyword> keywords = new ArrayList<Keyword>();
	
	
	
	public Main()  throws IOException {
		File file = new File("C:/Users/User/109_11/108306087_hw11/input.txt");
		Scanner scanner = new Scanner(file);

		
		while (scanner.hasNextLine()) {
			int numOfKeywords = scanner.nextInt();// 2

			for (int i = 0; i < numOfKeywords; i++) {
				String name = scanner.next();// Yu
				double weight = scanner.nextDouble();// 1.2
				Keyword k = new Keyword(name, weight);// store key
				keywords.add(k);
			}
		}
		scanner.close();
	}
	
	private String fetchContent(String uu) throws IOException

	{
		String retVal = uu;

		URL u = new URL(uu);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal; 
	}
	
	
	
	public void add(String url, String title) throws IOException{
		WebTree www = new WebTree(new WebPage(url, title));
		treeList.add(www);
		
		String c = fetchContent(url);
		Document doc = Jsoup.parse(c);
		Elements lis = doc.select("a");
		String citeUrl = lis.attr("href");
		www.root.addChild(new WebNode(new WebPage(citeUrl, null)));
		
	}
	
	public void sort() {
		for (WebTree tree: treeList) {
			try {
				tree.setPostOrderScore(keywords);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		WebHeap heap = new WebHeap();
				for (WebTree tree: treeList) {
					heap.add(tree.root);
				}
				
				
				for (int i = 0; i < treeList.size(); i++) {
					newResult.add(heap.pop());
				}

	}
	
	public ArrayList<WebNode> getNode(){
		return this.newResult;
	}
	

}