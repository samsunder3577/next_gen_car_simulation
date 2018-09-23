import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;
public class light
{
        public static void main(String[] args) throws IOException,InterruptedException
        {

                Socket client=new Socket("localhost",5557);

                BufferedReader cardata=new BufferedReader(new InputStreamReader(client.getInputStream()));
                String cardatin=cardata.readLine();

                String Speed=new String();
//              System.out.println(cardatin);


                int fuelper=Integer.parseInt(cardatin);
//              System.out.println("Fuel capacity at "+fuelper);
                if(fuelper<=100 && fuelper>70)
                {
                        Speed="0";
                }
                else if(fuelper<=70 && fuelper>50)
                {
                        Speed="35";
                }
                else if(fuelper<=50 && fuelper>30)
                {
                        Speed="75";
                }
                else if(fuelper<=30 && fuelper>=10)
                {
                        Speed="95";
                }
                else
                {
                        Speed="100";
                        }
                //System.out.println("Speed "+Speed);

                /* THE FOLLOWING TRANSFERS THE DATA TO OUTPUT FUNCTION*/

                BufferedReader infromoutdat;
                client=new Socket("localhost",1372);

                OutputStream outtoout=client.getOutputStream();
                String Fuel="light";

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
