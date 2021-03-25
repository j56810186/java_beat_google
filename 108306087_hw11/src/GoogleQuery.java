import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;

import java.util.HashMap;



import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;
import java.util.ArrayList;


public class GoogleQuery

{

	public String searchKeyword;

	public String url;

	public String content;
	
	private Main m;

	public GoogleQuery(String searchKeyword) throws IOException
	{

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=10";
		
		m = new Main();
	}

	

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

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
		
		//System.out.println("!!!!!"+ retVal);
		
		return retVal;
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
	
	
	
	public String[][] query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		Elements lis = doc.select("div");
		// System.out.println(lis);
		lis = lis.select(".kCrYT");
		// System.out.println(lis.size());
		
		
		for(Element li : lis)
		{
			try 

			{
				String citeUrl = null;
				citeUrl = "http://google.com" +li.select("a").get(0).attr("href");
				if(citeUrl.equals(null))
					{citeUrl = "http://google.com" +li.select("A").get(0).attr("HREF");
					}
				String title = li.select("a").get(0).select(".vvjwJb").text();
				System.out.println(title + ","+citeUrl);
				
				m.add(citeUrl, title);
				
			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}
		}
		
		
		m.sort();
		
		String[][] qqq = new String[8][3];		
		int i=0;
		for(WebNode node : m.getNode()) {
			
			System.out.println(node.webPage.name + node.nodeScore);
			if(node.webPage.name.equals(null)||node.webPage.name.equals("")) {
				qqq[i][0] = "校務系統暨成績缺曠查詢(選課)";
				qqq[i][1] = node.webPage.url;
				qqq[i][2] = "Score : " + node.nodeScore;
			}
			else {
				qqq[i][0] = node.webPage.name;
				qqq[i][1] = node.webPage.url;
				qqq[i][2] = "Score : " + node.nodeScore;
			}
			retVal.put(node.webPage.name, node.webPage.url);
			i++;
		}
		
		System.out.println(qqq);
		
		//retVal.put(title, citeUrl);
		return qqq;
		//return retVal;

	}

	

	

}