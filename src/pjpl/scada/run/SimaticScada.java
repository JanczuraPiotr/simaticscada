/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjpl.scada.run;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import pjpl.s7.command.Command;
import pjpl.s7.utils.Properties;
import pjpl.s7.common.ConstProcess;
import pjpl.s7.net.ServerCommandThread;
import pjpl.scada.form.MainFrame;
import pjpl.scada.process.Process1;

/**
 *
 * @author piotr
 */
public class SimaticScada {
	public static final String configFile = "SimaticScada.ini";
	public static Properties config;
	public static long timeStart;
	public static long time_interval;

	public static void main(String[] args) throws InterruptedException {
		timeStart = System.currentTimeMillis();
		configInit();

		//------------------------------------------------------------------------------
		// inicjacja zmiennych

		time_interval = Long.parseLong(config.getProperty("time_interval"), 10);
		commandQueue = new LinkedBlockingQueue<>();
		processes = new HashMap<>();
		executor = Executors.newScheduledThreadPool(MANAGEMENT_THREADS);

		// inicjacja zmiennych
		//------------------------------------------------------------------------------

		//------------------------------------------------------------------------------
		// procesy

		process1 = new Process1(ConstProcess.PROCESS1_ID, commandQueue);
		processes.put(process1.getId(), process1 );

		// procesy
		//------------------------------------------------------------------------------

		try {

			//------------------------------------------------------------------------------
			// demony

			connectThread = new ServerCommandThread(
							config.getProperty("simatic_server_ip")
							, Integer.parseInt(config.getProperty("simatic_server_port"))
							, commandQueue

			);

			// demony
			//------------------------------------------------------------------------------

			//------------------------------------------------------------------------------
			// uruchamianie

			connectThread.start();

			executor.scheduleAtFixedRate(
							process1
							, ( time_interval * 2 ) - ( System.currentTimeMillis() % time_interval)
							, time_interval
							, TimeUnit.MILLISECONDS
			);

			// uruchamianie
			//------------------------------------------------------------------------------

		} catch (IOException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		//------------------------------------------------------------------------------
		// intrfejs

		JFrame mainFrame = new MainFrame();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

		// intrfejs
		//------------------------------------------------------------------------------

	}


	private static void configInit(){
		try {
			FileReader configReader = new FileReader(configFile);
			config = new pjpl.s7.utils.Properties(new ConfigDefault());
			config.load(configReader);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(SimaticScada.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private static HashMap<Byte, pjpl.s7.process.Process> processes;
	private static ServerCommandThread connectThread;
	private static Process1 process1;
	private static LinkedBlockingQueue<Command> commandQueue;
	private static ScheduledExecutorService executor;

	private static int MANAGEMENT_THREADS = 1;

}
