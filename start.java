import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.*;
public class start
{
	public static void main(String[] args)throws IOException,InterruptedException
	{
             Runtime.getRuntime().exec("cmd /c \"javac input.java\"");
	     Runtime.getRuntime().exec("cmd /c \"javac carfunc.java\"");
	     Runtime.getRuntime().exec("cmd /c \"javac output.java\"");
	     Runtime.getRuntime().exec("cmd /c \"javac obstacle.java\"");
	     Runtime.getRuntime().exec("cmd /c \"javac mainbasecomp.java\"");
	     Runtime.getRuntime().exec("cmd /c \"javac light.java\"");
	     Runtime.getRuntime().exec("cmd /c start cmd.exe /k \"java output\"");
	     Thread.sleep(3000);
	     String Data="Start"; 
	     Socket client=new Socket("localhost",1372);
	     DataOutputStream out=new DataOutputStream(client.getOutputStream());
	     
	     out.writeBytes(Data);
	     client.close();
	     
	     Runtime.getRuntime().exec("cmd /c start cmd.exe /k \"java input\"");

	     //Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"java input\"");
	}
}
