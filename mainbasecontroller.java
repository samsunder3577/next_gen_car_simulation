import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
public class mainbasecontroller
{
	public static void main(String[] args)throws IOException,InterruptedException
	{
		File file = new File("C:\\Theftlog.txt");
		System.out.println("imhere1");
		file.getParentFile().mkdirs();
		System.out.println("imhere2");
		//FileWriter writer = new FileWriter(file);
		BufferedReader fromfile=new BufferedReader(new FileReader("log.txt"));
		String fileread=fromfile.readLine();
		System.out.println("imhere3"+fileread+System.getProperty("line.separator"));
		while(!fileread.isEmpty())
		{
			System.out.println(fileread);
			BufferedWriter tofile=new BufferedWriter(new FileWriter(file,true));
			tofile.write(fileread+System.getProperty("line.separator"));
			fileread=fromfile.readLine();
		}
	}
}
