import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
public class temperature
{
        public static void main(String[] args) throws IOException,InterruptedException
        {

                Socket client=new Socket("localhost",5555);

                BufferedReader cardata=new BufferedReader(new InputStreamReader(client.getInputStream()));
                String cardatin=cardata.readLine();

                String Speed=new String();
//              System.out.println(cardatin);


                int fuelper=Integer.parseInt(cardatin);
//              System.out.println("Fuel capacity at "+fuelper);
                if(fuelper<=100 && fuelper>70)
                {
                        Speed="180";
                }
                else if(fuelper<=70 && fuelper>50)
                {
                        Speed="130";
                }
                else if(fuelper<=50 && fuelper>30)
                {
                        Speed="60";
                }
                else if(fuelper<=30 && fuelper>=10)
                {
                        Speed="20";
                }
                else
                {
                        Speed="0";
                        }
                //System.out.println("Speed "+Speed);

                /* THE FOLLOWING TRANSFERS THE DATA TO OUTPUT FUNCTION*/
 /* THE FOLLOWING TRANSFERS THE DATA TO OUTPUT FUNCTION*/

                BufferedReader infromoutdat;
                client=new Socket("localhost",1372);

                OutputStream outtoout=client.getOutputStream();
                String Fuel="fuel";

                PrintWriter p1=new PrintWriter(outtoout,true);
                p1.println(Fuel);

                p1.println(Speed);

                client.close();

                /*THE FOLLOWING WILL TRANSFER DATA TO MAINBASE COMPUTER*/
                Runtime.getRuntime().exec("cmd /c start cmd.exe /c \"java mainbasecomp\"");
                client=new Socket("localhost",1472);

                OutputStream outtomaincomp=client.getOutputStream();
                PrintWriter p2=new PrintWriter(outtomaincomp,true);
                p2.println(Fuel);
                p2.println(Speed);

        }
}

