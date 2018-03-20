import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class UDPClient 
{
    DatagramSocket Socket;

    public UDPClient() 
    {

    }

    public void createAndListenSocket() throws ClassNotFoundException 
    {
        try 
        {
        	
            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("150.243.201.96");
            byte[] incomingData = new byte[1024];
            String sentence = "Client 1 is up";
            byte[] data = sentence.getBytes();
            
            while(true)
        	{
            	DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 9876);
                Socket.send(sendPacket);
                System.out.println("Message sent from client");
                System.out.println();
                
                //receive
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                Socket.receive(incomingPacket);
          
                String response = new String(incomingPacket.getData());
                System.out.println("Response from server:" + response);
               
        	}
            
        }
        catch (UnknownHostException e) 
        {
            e.printStackTrace();
        } 
        catch (SocketException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException 
    {
        UDPClient client = new UDPClient();
        client.createAndListenSocket();
    }
}
