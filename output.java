import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
class output
{
	public static void main(String[] args)throws IOException,InterruptedException
	{
		BufferedReader in;
		BufferedReader indat;

		DataOutputStream outtoclient;

		String dat=new String();
		String datin=new String();

		ServerSocket opserver=new ServerSocket(1372);


		while(true)
		{

			Socket client=opserver.accept();

			in=new BufferedReader(new InputStreamReader(client.getInputStream()));
			dat=in.readLine();
			String Data=new String();
			Data=dat;

			dat=in.readLine();
			String value=new String();
			value=dat;

			//				System.out.println(Data+"\n"+value);

			client.close();
			if(Data.equals("Start"))
			{
				System.out.println("Car is Started \n");
			}
			else if(Data.equals("fuel"))
			{
				if(value.equals("180"))
					System.out.println("Speed is 180 kmph(max Speed)\n");
				else if (value.equals("0"))
				{
					System.out.println("Car is Stopped due to low fuel\n");
					Thread.sleep(2000);
					System.out.println("Car is started again after refuelng\n");
				}
				else
					System.out.println("Speed is "+value+" adjuster as per fuel percentage\n");

			}
			else if(Data.equals("ultrasonic"))
			{
				if(value.equals("0"))
					System.out.println("No Obstacle still car still running\n");
				else
					System.out.println("Obstacle detected car Stopped\n");
			}
			else if(Data.equals("light"))
			{
				System.out.println("Head lamp intensity is "+value+" lux.Adjusted as per Daylight");
			}
			continue;
		}


	}
}

