import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.security.Security;
import com.sun.net.ssl.internal.ssl.Provider;
import java.io.IOException;

public class socketThread extends Thread{
	SSLSocket sslSocket;
	SSLServerSocket sslServerSocket;
	
	public socketThread(SSLSocket sslSocket,SSLServerSocket sslServerSocket){
		this.sslSocket = sslSocket;
		this.sslServerSocket = sslServerSocket;
	}
	
public void run(){
			try{
				//Create InputStream to recive messages send by the client
			DataInputStream inputStream = new DataInputStream(sslSocket.getInputStream());
			//Create OutputStream to send message to client
			DataOutputStream outputStream = new DataOutputStream(sslSocket.getOutputStream());
			outputStream.writeUTF("Hello Client, Say Something!");
			//Keep sending the client the message you recive unless he sends the word "close"
			while(true)
			{
			  String recivedMessage = inputStream.readUTF();
			  System.out.println("Client Said : " + recivedMessage);
			  if(recivedMessage.equals("close"))
			  {
			      outputStream.writeUTF("Bye");
			      outputStream.close();
			      inputStream.close();
			      this.sslSocket.close();
			      //this.sslServerSocket.close();
			      break;
			  }
			  else
			  {
			      outputStream.writeUTF("You Said : "+recivedMessage);
			  }
			}
		} catch (IOException ex) {
            System.out.println("Erro on printWriter: " + ex.toString());
            //Logger.getLogger(socketThread.class.getName()).log(Level.SEVERE, null, ex);
   	}
    }
}