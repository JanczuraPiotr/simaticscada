package pjpl.simaticscada.net;

import Moka7.S7;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.simaticscada.command.Command;
import pjpl.simaticscada.command.CommandResponse;
import pjpl.simaticscada.command.CommandResponseBuilder;
import pjpl.simaticscada.command.ResponseRaportFull;
import pjpl.simaticscada.common.CommandCode;
import pjpl.simaticscada.common.ResponseCode;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class ServerConnectThread extends Thread{
	public ServerConnectThread(String serverIp, int serverPort) throws IOException{
		super();
		this.ip = serverIp;
		this.port = serverPort;
		this.commandQueue = new LinkedBlockingQueue<>();
		initConnection();
		this.commandResponseBuilder = new CommandResponseBuilder(this.socketSimaticServer.getInputStream(), this.socketSimaticServer.getOutputStream());
	}

	public void sendCommand(Command command){
		commandQueue.add(command);
	}

	public void run(){
		Command command;
		CommandResponse response;
		while(true){
			try {

				command = commandQueue.take();
				response = command.action(sis,sos,commandResponseBuilder);
				switch(response.getResponseCode()){
					case ResponseCode.RAPORT_FULL:
						ResponseRaportFull rrf = (ResponseRaportFull)response;
						System.out.println(String.format("I = 0x%02X Q = 0x%02X", rrf.getI_0(), rrf.getQ_0()));
						break;
				}

//				program1();
			} catch (SocketException ex){
				System.err.println("Utracono połączenie z serverem");
				try {
					System.err.println("Próba odzyskania połączena z serverem");
					initConnection();
					System.err.println("Odzyskano połączenie z serverem");
				} catch (IOException ex1) {
					System.err.println("Nie udana próba odzyskania połączena z serverem");
				}
			} catch (IOException ex) {
				Logger.getLogger(ServerConnectThread.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(ServerConnectThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private LinkedBlockingQueue<Command> commandQueue;
	private CommandResponseBuilder commandResponseBuilder;

	private void initConnection() throws IOException{
		socketSimaticServer = new Socket(ip, port);
		sis = socketSimaticServer.getInputStream();
		sos = socketSimaticServer.getOutputStream();
	}
	private Socket socketSimaticServer;
	private InputStream sis;
	private OutputStream sos;
	private String ip;
	private int port;


	//------------------------------------------------------------------------------

	private void program1() throws InterruptedException, IOException{
		byte[] buff = {
			(byte)0x80,(byte)0x0F, // kod komendy
			(byte)0x01,            // identyfikator procesu
			(byte)S7.Q,            // kod obszaru danych
			(byte)0x00,(byte)0x00, // kod zmiennej
			(byte)0x03             // numer bitu  w zmiennej
		};

		Thread.sleep(1000);
		buff[6] = 0;
		sos.write(buff, 0, buff.length);
		Thread.sleep(1000);
		buff[6] = 1;
		sos.write(buff, 0, buff.length);
		Thread.sleep(1000);
		buff[6] = 2;
		sos.write(buff, 0, buff.length);
		Thread.sleep(1000);
		buff[6] = 3;
		sos.write(buff, 0, buff.length);
		Thread.sleep(1000);
		buff[6] = 4;
		sos.write(buff, 0, buff.length);
		Thread.sleep(1000);
		buff[6] = 5;
		sos.write(buff, 0, buff.length);

	}
}
