import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;
class obstacle
{
  public static void main(String[] args) throws IOException,InterruptedException
        {

                Socket client=new Socket("localhost",5556);

                BufferedReader cardata=new BufferedReader(new InputStreamReader(client.getInputStream()));
                String cardatin=cardata.readLine();
		System.out.println(cardatin);
                
		/* THE FOLLOWING TRANSFERS THE DATA TO OUTPUT FUNCTION*/

                BufferedReader infromoutdat;
                client=new Socket("localhost",1372);

                OutputStream outtoout=client.getOutputStream();
                String Data="ultrasonic";

                PrintWriter p1=new PrintWriter(outtoout,true);
                p1.println(Data);

                p1.println(cardatin);

                client.close();

                /*THE FOLLOWING WILL TRANSFER DATA TO MAINBASE COMPUTER*/
                Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"java mainbasecomp\"");
                client=new Socket("localhost",1472);

                OutputStream outtomaincomp=client.getOutputStream();
                PrintWriter p2=new PrintWriter(outtomaincomp,true);
                p2.println(Data);
                p2.println(cardatin);

        }

}
