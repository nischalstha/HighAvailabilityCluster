import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;


public class UDPServer implements Serializable
{
    public DatagramSocket socket = null;
    
    //public static ArrayList <DatagramPacket> clients = new ArrayList<DatagramPacket>();
    public static ArrayList <CreatePacket> clients = new ArrayList<CreatePacket>();
    public static ArrayList <InetAddress> IPs = new ArrayList <InetAddress>();
    public static ArrayList <Integer> ports = new ArrayList <Integer>();
    
//  public static HashMap <InetAddress, Integer> ipports = new HashMap <InetAddress, Integer>();
    public UDPServer() 
    {

    }
    public void createAndListenSocket() throws IOException, InterruptedException 
    {
        try 
        {
            socket = new DatagramSocket(9876);
            byte[] incomingData = new byte[1024];
            System.out.println(clients.size());

        	// When to break the loop?
            while(true)
            {      
            	//10 seconds wait
            	long begin = System.currentTimeMillis(); 
            	long end = begin + 10*1000; 
            	
            	clients.removeAll(clients);
            	IPs.removeAll(IPs);
            	ports.removeAll(ports);
            	
            	while(begin < end)
            	{
            		DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            		socket.receive(incomingPacket);
            		CreatePacket newPacket = new CreatePacket(incomingData, incomingData.length); 
            		InetAddress IPAddress = incomingPacket.getAddress();
            		int port = incomingPacket.getPort();  
		                
            		clients.add(newPacket);
            		IPs.add(IPAddress);
            		ports.add(port);
            		
            		//ipports.put(IPAddress, port);	
            		
            		System.out.println(clients.size());
            		Thread.sleep(3000);
                	begin = System.currentTimeMillis();
                	System.out.println(begin < end);
            	}
            	
                ByteArrayOutputStream out = new ByteArrayOutputStream();
           		ObjectOutputStream os = new ObjectOutputStream(out);
           		
           		os.writeObject(clients);           		          	
           		//byte[] data = out.toByteArray();
           		System.out.println("Checkpoint");
           		for (int j = 0; j < IPs.size(); j++) 
           		{
           			//Packet send garna lai IP Address?
           			if (IPs.get(j).isReachable(5000))
           			{
               			DatagramPacket replyPacket = new DatagramPacket(incomingData, incomingData.length, IPs.get(j), ports.get(j));
               			socket.send(replyPacket);
           			}
           			else
           			{
           	            byte[] notIncomingData = new byte[1024];
           	            String notData = "Client " + j + 1 +" is down.";
           	            notIncomingData = notData.getBytes();
           				DatagramPacket replyPacket = new DatagramPacket(notIncomingData, notIncomingData.length, IPs.get(j), ports.get(j));
						socket.send(replyPacket);
           			}
       
           		} 
            }
        }
        catch (SocketTimeoutException e)
        {
        	e.printStackTrace();
        }
        catch (SocketException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException i) 
        {
            i.printStackTrace();
        }
    }
    
//    public void sendPacket() throws IOException, InterruptedException
//    {
//    	
//    }

    public static void main(String[] args) throws IOException, InterruptedException 
    {
        UDPServer server = new UDPServer();
        server.createAndListenSocket();
//      server.sendPacket();        
    }
}
