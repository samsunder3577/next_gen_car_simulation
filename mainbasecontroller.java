import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class mainbasecontroller
{
	public static void main(String[] args)throws IOException,InterruptedException
	{
		FileReader reader=new FileReader("log.txt");
		BufferedReader fromfile=new BufferedReader(reader);
		
		String fileread=fromfile.readLine();
		BufferedWriter tofile=new BufferedWriter(new FileWriter("C://Theftlog.txt",true));
		
		while(fileread!=null)
		{
			System.out.println(fileread);
			tofile.write(fileread+System.getProperty("line.separator"));
			fileread=fromfile.readLine();
		}
		
		reader.close();
		tofile.close();
	}
}
