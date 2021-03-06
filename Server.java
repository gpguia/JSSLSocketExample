import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.security.Security;
import com.sun.net.ssl.internal.ssl.Provider;

public class Server {
    public static void main(String args[])
    {
        //The Port number through which this server will accept client connections
        int port = 35786;
        /*Adding the JSSE (Java Secure Socket Extension) provider which provides SSL and TLS protocols
        and includes functionality for data encryption, server authentication, message integrity, 
        and optional client authentication.*/
        Security.addProvider(new Provider());
        //specifing the keystore file which contains the certificate/public key and the private key
        System.setProperty("javax.net.ssl.keyStore","pguia.jks");
        //specifing the password of the keystore file
        System.setProperty("javax.net.ssl.keyStorePassword","password");
        //This optional and it is just to show the dump of the details of the handshake process 
        //System.setProperty("javax.net.debug","all");
        try
        {
            //SSLServerSocketFactory establishes the ssl context and and creates SSLServerSocket 
            SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
            //Create SSLServerSocket using SSLServerSocketFactory established ssl context
            SSLServerSocket sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(port);
            System.out.println("Echo Server Started & Ready to accept Client Connection");
            //Wait for the SSL client to connect to this server
            //Create new thread and a new socket.
            while (true) new socketThread((SSLSocket)sslServerSocket.accept(),sslServerSocket).start();
       }catch(Exception ex){
					System.err.println("Error Happened : "+ex.toString());
			 }
}
}
