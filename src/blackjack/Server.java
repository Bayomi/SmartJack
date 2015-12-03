package blackjack;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private String ip = "localhost";
	private int port = 22222;

	private Socket socket;
	private ObjectOutputStream dos;
	private ObjectInputStream dis;

	private ServerSocket serverSocket;
	
	public Server(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new ObjectOutputStream(socket.getOutputStream());
			dis = new ObjectInputStream(socket.getInputStream());
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean connect() {
		try {
			socket = new Socket(ip, port);
			dos = new ObjectOutputStream(socket.getOutputStream());
			dis = new ObjectInputStream(socket.getInputStream());
			System.out.println("Successfully connected to the server.");
		} catch (IOException e) {
			System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
			return false;
		}
		return true;
	}

	public void initializeServer() {
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ObjectInputStream getDis() {
		return this.dis;
	}
	
	public ObjectOutputStream getDos() {
		return this.dos;
	}
}
