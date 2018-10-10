import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
class password
{
	//THIS IS THE BIOMETRIC CHANGE FUNCTION
	public static void cbio()throws IOException
	{
		System.out.println("===================================================================");
		int count=0;
		for(int i=0;i<3;i++)
		{

			System.out.println("Enter your password");
			BufferedReader pass=new BufferedReader(new InputStreamReader(System.in));
			String passfrom=pass.readLine();
			System.out.println("Register the new Biometric ID");
			BufferedReader pass2=new BufferedReader(new InputStreamReader(System.in));
			String passfrom2=pass2.readLine();
			FileReader reader=new FileReader("password.txt");
			BufferedReader passfromfile=new BufferedReader(reader);
			String fileread=passfromfile.readLine();
			if(passfrom.equals(fileread))
			{
				BufferedWriter topassfile=new BufferedWriter(new FileWriter("bio.txt",false));
				topassfile.write(passfrom2);
				topassfile.close();
				System.out.println("Biometric changed Successfully");
				password();
				break;
			}
			else
			{
				if(count==0||count==1)
				{
					System.out.println("Password is Wrong.Try again\n");

					count++;
				}
				else
				{
					System.out.println("Too many wrong attempts this wil be reported");
				}
			}
		}
		System.out.println("===================================================================");

	}
	//THIS IS THE PASSWORD ENTRY FUNCTION
	public static void password()throws IOException
	{	

		System.out.println("====================================================================");
		int count=0;
		Socket client=new Socket("localhost",6566);
		for(int i=0;i<3;i++)
		{

			System.out.println("ENTER PASSWORD TO START THE CAR");
			BufferedReader pass=new BufferedReader(new InputStreamReader(System.in));

			String passfrom=pass.readLine();
			FileReader reader=new FileReader("password.txt");

			BufferedReader passfromfile=new BufferedReader(reader);
			String fileread=passfromfile.readLine();
			DataOutputStream outt;
			
			//Socket client=new Socket("localhost",6566);
			//BufferedReader innn=new BufferedReader(new InputStreamReader(client.getInputStream()));
			//String inn=innn.readLine();
			//System.out.println(fileread);
			
			if(passfrom.equals(fileread))
			{
				System.out.println("correct password");
				outt=new DataOutputStream(client.getOutputStream());
				outt.writeBytes("success");
				client.close();
				break;
			}
			else
			{
				if(count==0||count==1)
				{
					System.out.println("wrong password.Try again\n");
					count++;
					continue;
				}
				else
				{
					System.out.println("MANY WRONG ATTEMPTS THIS WILL BE REPORTED");
				}
				outt=new DataOutputStream(client.getOutputStream());
				System.out.println("\n");
				outt.writeBytes("failiure");
				client.close();
				break;
				
			}

		}
		System.out.println("===================================================================");
	}

	//THIS THE PASSWORD CHANGING FUCNTION
	public static void change()throws IOException
	{
		System.out.println("===================================================================");
		int count=0;
		for(int i=0;i<3;i++)
		{

			System.out.println("Enter the old password");
			BufferedReader pass=new BufferedReader(new InputStreamReader(System.in));
			String passfrom=pass.readLine();
			System.out.println("Enter the new password");
			BufferedReader pass2=new BufferedReader(new InputStreamReader(System.in));
			String passfrom2=pass2.readLine();
			FileReader reader=new FileReader("password.txt");
			BufferedReader passfromfile=new BufferedReader(reader);
			String fileread=passfromfile.readLine();
			if(passfrom.equals(fileread))
			{
				BufferedWriter topassfile=new BufferedWriter(new FileWriter("password.txt",false));
				topassfile.write(passfrom2);
				topassfile.close();
				System.out.println("Password changed Successfully");
				password();
				break;
			}
			else
			{
				if(count==0||count==1)
				{
					System.out.println("Old Password is Wrong.Try again\n");

					count++;
				}
				else
					System.out.println("Too many wrong attempts this wil be reported");
			}
		}
		System.out.println("===================================================================");

	}

	//THIS IS THE MAIN FUNCTION
	public static void main(String[] args) throws IOException
	{

		System.out.println("1:Enter the password\n2:Change Password\n3:Change Biometric");
		String flag;
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		flag=in.readLine();
		switch(flag)
		{
			case "1":
				password();
				break;
			case "2":
				change();
				break;
			case "3":
				cbio();
				break;

		}
	}
}
