/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjpl.simaticscada.run;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.simaticscada.utils.Properties;
import javax.swing.JFrame;
import pjpl.simaticscada.command.CommandRaportFull;
import pjpl.simaticscada.common.ConstProcess;
import pjpl.simaticscada.net.ServerConnectThread;

/**
 *
 * @author piotr
 */
public class SimaticScada {
	public static final String configFile = "SimaticScada.ini";
	public static Properties config;

	public static void main(String[] args) throws InterruptedException {
		configInit();

		try {
			connectThread = new ServerConnectThread(
							config.getProperty("simatic_server_ip")
							, Integer.parseInt(
											config.getProperty("simatic_server_port")
							)
			);
			connectThread.start();


			while(true){
				connectThread.sendCommand( new CommandRaportFull(ConstProcess.PROCESS1_ID));
				Thread.sleep(1000);
			}



//		JFrame mainFrame = new MainFrame();
//		mainFrame.setLocationRelativeTo(null);
//		mainFrame.setVisible(true);
		} catch (IOException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	private static void configInit(){
		try {
			FileReader configReader = new FileReader(configFile);
			config = new pjpl.simaticscada.utils.Properties(new ConfigDefault());
			config.load(configReader);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private static ServerConnectThread connectThread;
//	private static Queue<pjpl.s7.command.Command> commandQueue;

}
