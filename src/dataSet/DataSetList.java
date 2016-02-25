package dataSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class DataSetList {
	
	LinkedList<DataSet> list = new LinkedList<DataSet>();
	
	public DataSetList(String f) throws IOException{
//		System.out.println("Sub Check point 0");
		File tmpFile = new File("./");
//		System.out.println(tmpFile.getAbsolutePath());
		BufferedReader br = new BufferedReader(new FileReader(f));
//		System.out.println("Sub Check point 0.1");
		String line = br.readLine();
		line = br.readLine();
//		System.out.println("Sub Check point 1");
		while(line != null){
			String[] items = line.split("\t");
			int id = Integer.valueOf(items[0]);
			String name = items[1];
			String type = items[2];
			String link = items[3];
			String goal = items[4];
			float size = Float.valueOf(items[5]);
			DataSet tmp = new DataSet(id, name, goal, type, link, size);
			list.add(tmp);
			line = br.readLine();
		}
//		System.out.println("Sub Check point 2");
		br.close();
	}
	
	public LinkedList<DataSet> getList(){return list;}
	
	
}
