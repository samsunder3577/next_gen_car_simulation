//package mypackage;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.*;
public class mainbasecomp
{
	public static void main(String[] args)throws IOException
	{
		ServerSocket server=new ServerSocket(1472);
		Date date=new Date();
		Socket client=server.accept();
		BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));

		String inread=in.readLine();
		String Data=new String();
		Data=inread;

		inread=in.readLine();
		String value=new String();
		value=inread;

		BufferedWriter tofile=new BufferedWriter(new FileWriter("log.txt",true));
		if(Data.equals("fuel"))
		{
			tofile.write(Data+" :Data\n"+date+"\nSpeed is: "+value+"Kmph \n Adjusted according to fuel percentage\n"+System.getProperty("line.separator")+"\n");
			tofile.close();
		}
		else if(Data.equals("ultrasonic"))
		{

			if(value.equals("0"))
			{
				tofile.write(Data+" :Data\n"+date+"\nSensor Detected :"+value+System.getProperty("line.separator")+"\n");
				tofile.close();
			}
			else
			{
				tofile.write(Data+" :Data\n"+date+"\nSensor Detected :1"+System.getProperty("line.separator")+"\n");
				tofile.close();
			}

		}
		else if(Data.equals("light"))
		{
			tofile.write(Data+" :Data\n"+date+"\nHeadlamp intensity: "+value+"lux\n Adjusted according to day light"+System.getProperty("line.separator")+"\n");
			tofile.close();
		}
	}
}

