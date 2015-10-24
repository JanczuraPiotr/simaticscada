package pjpl.scada.process;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.s7.command.Command;
import pjpl.s7.command.CommandRaportFull;
import pjpl.s7.command.Q_SetByte;
import pjpl.s7.command.ResponseBuff;
import pjpl.s7.command.ResponseByte;
import pjpl.s7.command.ResponseDInt;
import pjpl.s7.command.ResponseGeneral;
import pjpl.s7.command.ResponseInt;
import pjpl.s7.command.ResponseNo;
import pjpl.s7.command.ResponseOk;
import pjpl.s7.command.ResponseRaportFull;
import pjpl.s7.command.ResponseReal;
import pjpl.s7.common.ConstProcess;
import pjpl.s7.process.Process;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class Process1 extends Process{
	private byte bit = 3;
	private long prevStep;
	private long thisStep;
	protected final DateFormat datePCFormat = new SimpleDateFormat(pjpl.scada.run.SimaticScada.config.getProperty("format_dateMS"));

	public Process1(byte processId, LinkedBlockingQueue<Command> commandQueue) {
		super(processId, commandQueue);
		prevStep = System.currentTimeMillis();
	}

	@Override
	protected void steep() {
		thisStep = System.currentTimeMillis();
		try {
			System.out.println( datePCFormat.format(thisStep) +" przerwa od poprzedniego wywołania : "+ String.format("%4d", thisStep - prevStep) + " [ms]");
			sendCommand( new CommandRaportFull(ConstProcess.PROCESS1_ID, this));
			sendCommand( new Q_SetByte(ConstProcess.PROCESS1_ID, (short)0, bit++, this) );
			if(bit > 32){
				bit = 0;
			}
		} catch (IOException ex) {
			Logger.getLogger(Process1.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
			prevStep = thisStep;
		}
	}

	@Override
	public void onResponseRaportFull(ResponseRaportFull response) {
		System.out.println(String.format("I = 0x%02X Q = 0x%02X", response.getI_0(), response.getQ_0()));
	}

	@Override
	public void onResponseGeneral(ResponseGeneral response) {
	}

	@Override
	public void onResponseOk(ResponseOk response) {
	}

	@Override
	public void onResponseNo(ResponseNo response) {
	}

	@Override
	public void onResponseByte(ResponseByte response) {
	}

	@Override
	public void onResponseInt(ResponseInt response) {
	}

	@Override
	public void onResponseDInt(ResponseDInt response) {
	}

	@Override
	public void onResponseReal(ResponseReal response) {
	}

	@Override
	public void onResponseBuff(ResponseBuff response) {
	}


}
