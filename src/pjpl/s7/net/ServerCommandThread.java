package pjpl.s7.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.s7.command.Command;
import pjpl.s7.command.CommandResponse;
import pjpl.s7.command.CommandResponseBuilder;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class ServerCommandThread extends Thread{
	public ServerCommandThread(String serverIp, int serverPort, LinkedBlockingQueue<Command> commandQueue) throws IOException{
		super();
		this.ip = serverIp;
		this.port = serverPort;
		this.commandQueue = commandQueue;
		initConnection();
		this.commandResponseBuilder = new CommandResponseBuilder(this.socketSimaticServer.getInputStream(), this.socketSimaticServer.getOutputStream());
	}

	@Override
	public void run(){

		while(true){
			try {

				command = commandQueue.take();
				command.action(sis,sos,commandResponseBuilder);

			} catch (SocketException ex){
				System.err.println("Utracono połączenie z serverem");
				// @todo próba odsyskania kończy się niepowodzeniem przez commandQueue. komendy mają referencje do utraconych strumieni.
				try {
					System.err.println("Próba odzyskania połączena z serverem");
					initConnection();
					System.err.println("Odzyskano połączenie z serverem");
				} catch (IOException ex1) {
					System.err.println("Nie udana próba odzyskania połączena z serverem");
				}
			} catch (IOException ex) {
				Logger.getLogger(ServerCommandThread.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(ServerCommandThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private void initConnection() throws IOException{
		socketSimaticServer = new Socket(ip, port);
		sis = socketSimaticServer.getInputStream();
		sos = socketSimaticServer.getOutputStream();
	}

	private	Command command;
	private	CommandResponse response;
	private final LinkedBlockingQueue<Command> commandQueue;
	private final CommandResponseBuilder commandResponseBuilder;
	private Socket socketSimaticServer;
	private InputStream sis;
	private OutputStream sos;
	private final String ip;
	private final int port;


	//------------------------------------------------------------------------------

}
