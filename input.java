import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.*;
class input
{
	static int fuelmain=100;
	//This is the fucntion for fuel sensor//
	public static void fueldata(String fuel)throws IOException,InterruptedException	
	{
		System.out.println("----------------------------------------");
		System.out.println("\nFuel sensor is active");
		ServerSocket inserver=new ServerSocket(5555);

		DataOutputStream tocarfueldat;
		System.out.println("\ngetting fuel percentage from fuel sensor");

		int half;
		half=fuelmain-10;

		Random rand=new Random();
		int fueldat=rand.nextInt((fuelmain-half)+1)+half;
		String fueldatin=Integer.toString(fueldat);
		fuelmain=fueldat;
		if(fuelmain<10)
		{
			fuelmain=100;
		}
		System.out.println("\nFuel currently "+fueldatin+" percentage");
		Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"java carfunc\"");
		Socket client=inserver.accept();

		tocarfueldat=new DataOutputStream(client.getOutputStream());
		tocarfueldat.writeBytes(fueldatin);

		client.close();
		inserver.close();

		System.out.println("-----------------------------------------");

	}
	//This is the function for ultrasonic sensor//

	public static void ultrasonicdata(String waves)throws IOException,InterruptedException
	{
		if(fuelmain<20)
		{

			System.out.println("----------------------------------------");
			System.out.println("Low fuel Switched to Manual mode");
			System.out.println("----------------------------------------");
		}
		else
		{
			System.out.println("----------------------------------------");
			System.out.println("\nUltrsonic sensor is active");
			ServerSocket inserver=new ServerSocket(5556);

			DataOutputStream tocarultradat;
			System.out.println("\ngetting Obstacle data from ultrasonic sensor");

			Random rand=new Random();
			int ultradat=rand.nextInt(2) + 0;
			String ultradatin=Integer.toString(ultradat);

			if(ultradatin.equals("0"))
				System.out.println("\nSensor detected "+ultradatin);
			else
				System.out.println("\nSensor detected 1");
			Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"java obstacle\"");
			Socket client=inserver.accept();

			tocarultradat=new DataOutputStream(client.getOutputStream());
			tocarultradat.writeBytes(ultradatin);

			client.close();
			inserver.close();

			System.out.println("-----------------------------------------");
		}
	}
	//This is the fucntion for temperature sensor//
	public static void tempdata(String temp)throws IOException,InterruptedException
	{
		if(fuelmain<20)
		{
			System.out.println("----------------------------------------");	
			System.out.println("Fuel low Switched to manual mode");
			System.out.println("----------------------------------------");
		}
		System.out.println("----------------------------------------");
		System.out.println("\nTemperature sensor is active");
		System.out.println("\nAdjusting the Airconditioner accordingly");
		Socket client=new Socket("localhost",1372);
		OutputStream outtoout=client.getOutputStream();
		String edat1="temp";

		PrintWriter p1=new PrintWriter(outtoout,true);
		p1.println(edat1);

		p1.println(edat1);

		client.close();
		System.out.println("----------------------------------------");

	}
	//This is the fucntion for light sensor//

	public static void lightdata(String lux)throws IOException,InterruptedException
	{
		if(fuelmain<20)
		{
			System.out.println("----------------------------------------");	
			System.out.println("Fuel low Switched to manual mode");
			System.out.println("----------------------------------------");
		}
		else
		{
			System.out.println("----------------------------------------");
			System.out.println("\nlight sensor is active");
			ServerSocket inserver=new ServerSocket(5557);

			DataOutputStream tocarlightdat;
			System.out.println("\ngetting light data from light sensor");

			Random rand=new Random();
			int lightdat=rand.nextInt(100) + 0;
			String lightdatin=Integer.toString(lightdat);


			System.out.println("\nLight currently at "+lightdatin+" lux");
			Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"java light\"");
			Socket client=inserver.accept();

			tocarlightdat=new DataOutputStream(client.getOutputStream());
			tocarlightdat.writeBytes(lightdatin);

			client.close();
			inserver.close();

			System.out.println("-----------------------------------------");
		}
	}

	//This is the main function//
	public static void main(String[] args)throws IOException,InterruptedException
	{
		System.out.println("Fuel currenltly in "+fuelmain+" Percentage");
		int counter=0,breakv=0;
		Date date=new Date();
		while(true)
		{
			BufferedWriter tofile=new BufferedWriter(new FileWriter("log.txt",true));
			System.out.println("Please Provide Biometric ");
			BufferedReader bioin=new BufferedReader(new InputStreamReader(System.in));
			String bio=bioin.readLine();
			if (bio.equals("0"))
			{	
				System.out.println("FYI 1=fuel 2=ultrasonic 3=light 4=temperature");
				breakv=1;
				tofile.write("- "+date+"\n"+"-Biometric Ok,Car Started"+System.getProperty("line.separator")+"\n");
				tofile.close();
				int count=0;
				while(true)
				{
					if (count==1)
						break;
					else
					{
						String data;
						System.out.println("Waiting for sensor");
						Scanner in=new Scanner(System.in);
						data=in.nextLine();

						switch(data)
						{
							case "1":
								fueldata(data);
								break;
							case "2":
								ultrasonicdata(data);
								break;
							case "3":
								lightdata(data);
								break;
							case "4":
								tempdata(data);
								break;		
							default:
								System.out.println("Unknown Source detected.access Denied");
								BufferedWriter tofile1=new BufferedWriter(new FileWriter("log.txt",true));
								tofile1.write(date+"\nUnknown source "+data+" Tried to access the car.access denied\n");
								tofile1.close();
								count=count+1;
								Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"java mainbasecontroller\"");
								break;
						}

					}
				}
break;

			}
			else
			{
				if(counter==0||counter==1||counter==2)
				{
					System.out.println("Try Again");
					tofile.write("- "+date+"\n"+"- Wrong Biometric attempt"+counter+System.getProperty("line.separator")+"\n");
					tofile.close();
					counter++;
				}
				else
				{
					System.out.println("\nYou have messed with kevcracks security System youre gonna pay for this\n");
					String Data="Unverified biometric access.accessdenied";
					tofile.write("- "+date+"\n- "+Data+"\n- attempt"+counter+System.getProperty("line.separator")+"\n");
					tofile.close();
					Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"java mainbasecontroller\"");

					Socket client=new Socket("localhost",1372);
					OutputStream outtoout=client.getOutputStream();
					String edat1="exit";

					PrintWriter p1=new PrintWriter(outtoout,true);
					p1.println(edat1);

					p1.println(edat1);

					client.close();
					break;
				}
			}
		
		}

	}
}
