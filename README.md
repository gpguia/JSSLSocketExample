# JSSLSocketExample

This is a java SSL socket example, you can start the server, it will wait for clients. Can connect more then 1 client per time.

## Getting Started

First you must generate a certificate public/private, in this example there is already one generated by myself, where the password is "password".

First command to generate the certificate:

1 - On the frist command you can change the size, to put it a more secure like 4096 insted of 2048.

-validity 360 it will expire your key in 360 days. **You can remove it if you want to be endless**

```
keytool -genkey -keyalg RSA -keysize 2048 -validity 360 -alias MYKEY -keystore MYKEYSTORE.jks
```

2 - Export the certficate and the public key that should be send to the client:

```
keytool -export -alias mykey -keystore myKeyStore.jks -file mykey.cert
```

3 - Add the key at the client side to a TrustedStore to trust the server

```
keytool -import -file mykey.cert -alias mykey -keystore myTrustStore.jts
```

### Prerequisites

You must have JDK installed in your computer, if you don't have it, please use this link:

* [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

If you are using Windows (only - Optional):

```
* Go to Control Panel -> System -> Advanced system settings
* On tab Advanced -> Environment Variables... ( at the bottom)
* In the section System Variables select Path and click in Edit...
* Click in New and insert the Java path, in my case it is: C:\Program Files\Java\jdk1.8.0_161\bin
```

Now you can use javac and java directly from cmd.

### Compiling

Simply put all files together in a folder and use:

```
* javac Server.java
* javac Client.java
```

Open a new terminal to use as the client.

```
* run the server: java Server
In the other terminal:
* run the client: java Client
```

At this point you already have all the program set and running. To cancel it just press Ctrl + C.

This is a simple echo program so it will simply echo the messages...

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
