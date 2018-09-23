import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.*;
public class input
{
	//This is the fucntion for fuel sensor//
	public static void fueldata(String fuel)throws IOException,InterruptedException	
	{
		System.out.println("----------------------------------------");
		System.out.println("\nFuel sensor is active");
		ServerSocket inserver=new ServerSocket(5555);

		DataOutputStream tocarfueldat;
		System.out.println("\ngetting fuel percentage from fuel sensor");

		Random rand=new Random();
		int fueldat=rand.nextInt(100)+0;
		String fueldatin=Integer.toString(fueldat);

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
	//This is the fucntion for light sensor//
	 
	public static void lightdata(String lux)throws IOException,InterruptedException
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

	//This is the main function//
	public static void main(String[] args)throws IOException,InterruptedException
	{
		Date date=new Date();
		System.out.println("Please Provide Biometric ");
		BufferedReader bioin=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter tofile=new BufferedWriter(new FileWriter("log.txt",true));
		String bio=bioin.readLine();
		if(bio.equals("0"))
		{
			System.out.println("\nYou have messed with kevcracks security System youre gonna pay for this\n");
			String Data="Unverified biometric access.accessdenied";
			tofile.write("\r\n"+date+"\r\n"+Data+System.getProperty("line.separator"));
			tofile.close();
		}

		else
		{

			tofile.write("\n"+date+"\n"+"Biometric Ok,Car Started"+System.getProperty("line.separator"));
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
						case "fuel":
							fueldata(data);
							break;
						case "ultrasonic":
							ultrasonicdata(data);
							break;
						case "light":
							lightdata(data);
							break;
						default:
							System.out.println("Unknown Source detected.access Denied");
							BufferedWriter tofile1=new BufferedWriter(new FileWriter("log.txt",true));
							tofile1.write(date+"\nUnknown source "+data+" Tried to access the car.access denied\n");
							tofile1.close();
							count=count+1;
							break;
					}

				}
			}

		}
	}
}
