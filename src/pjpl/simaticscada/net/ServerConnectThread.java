package pjpl.simaticscada.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class ServerConnectThread extends Thread{
	public ServerConnectThread(String serverIp, int serverPort) throws IOException{
		super();
		this.ip = serverIp;
		this.port = serverPort;
		socketSimaticServer = new Socket(this.ip, this.port);
		socketInputStream = socketSimaticServer.getInputStream();
		socketOutputStream = socketSimaticServer.getOutputStream();
	}

	public void run(){
		byte[] buff = {(byte)0xAA,(byte)0xFF};
		try {
			socketOutputStream.write(buff, 0, 2);
		} catch (IOException ex) {
			Logger.getLogger(ServerConnectThread.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private Socket socketSimaticServer;
	private InputStream socketInputStream;
	private OutputStream socketOutputStream;
	private String ip;
	private int port;
}
