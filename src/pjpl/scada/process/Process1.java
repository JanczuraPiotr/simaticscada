package pjpl.scada.process;

import Moka7.S7;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.s7.command.BitSwitch;
import pjpl.s7.command.Command;
import pjpl.s7.command.CommandRaportFull;
import pjpl.s7.command.ResponseBuff;
import pjpl.s7.command.ResponseByte;
import pjpl.s7.command.ResponseDInt;
import pjpl.s7.command.ResponseGeneral;
import pjpl.s7.command.ResponseInt;
import pjpl.s7.command.ResponseNo;
import pjpl.s7.command.ResponseOk;
import pjpl.s7.command.ResponseRaportFull;
import pjpl.s7.command.ResponseReal;
import pjpl.s7.common.VarCode;
import pjpl.s7.common.ConstProcess;
import pjpl.s7.process.Process;
import pjpl.s7.utils.Bits;
import pjpl.scada.var.Variables1;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class Process1 extends Process{
	private byte bit = 3;
	private long prevStep;
	private long thisStep;
//	protected final DateFormat datePCFormat = new SimpleDateFormat(pjpl.scada.run.MainFrame.config.getProperty("format_dateMS"));

	public Process1(byte processId, LinkedBlockingQueue<Command> commandQueue) {
		super(processId, commandQueue);
		prevStep = System.currentTimeMillis();
		variables = new Variables1(this);
	}

	public void setQ_0_0(boolean b) throws IOException{
		System.out.println(String.format("S7.Q = 0x%02X", S7.Q));
		variables.setQ_0_0(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_0, this));
	}
	public void setQ_0_1(boolean b) throws IOException{
		System.out.println(String.format("S7.Q = 0x%02X", S7.Q));
		variables.setQ_0_1(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_1, this));
	}
	public void setQ_0_2(boolean b) throws IOException{
		System.out.println(String.format("S7.Q = 0x%02X", S7.Q));
		variables.setQ_0_2(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_2, this));
	}
	public void setQ_0_3(boolean b) throws IOException{
		System.out.println(String.format("S7.Q = 0x%02X", S7.Q));
		variables.setQ_0_3(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_3, this));
	}
	public void setQ_0_4(boolean b) throws IOException{
		System.out.println(String.format("S7.Q = 0x%02X", S7.Q));
		variables.setQ_0_4(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_4, this));
	}
	public void setQ_0_5(boolean b) throws IOException{
		System.out.println(String.format("S7.Q = 0x%02X", S7.Q));
		variables.setQ_0_5(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_5, this));
	}

	@Override
	protected void steep() {
		thisStep = System.currentTimeMillis();
		try {
//			System.out.println( datePCFormat.format(thisStep) +" przerwa od poprzedniego wywołania : "+ String.format("%4d", thisStep - prevStep) + " [ms]");
			sendCommand( new CommandRaportFull(ConstProcess.PROCESS1_ID, this));
//			sendCommand( new Q_SetByte(ConstProcess.PROCESS1_ID, (short)0, bit++, this) );
//			if(bit > 32){
//				bit = 0;
//			}
		} catch (IOException ex) {
			Logger.getLogger(Process1.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
			prevStep = thisStep;
		}
	}

	@Override
	public void onResponseRaportFull(ResponseRaportFull response) {
		variables.parseResponseRaportFull(response);
//		System.out.println(String.format("I = 0x%02X Q = 0x%02X", response.getI_0(), response.getQ_0()));
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


	@Override
	public void onVariableChange(short codeVar) {
		System.out.println(String.format("kod zmienionej zmiennej = 0x%04X ", codeVar) );
//		switch(codeVar){
//			case VarCode.ZMIENNA_1:break;
//			case VarCode.ZMIENNA_2:break;
//			case VarCode.ZMIENNA_3:break;
//			case VarCode.ZMIENNA_4:break;
//			case VarCode.I_0   :   break;
//			case VarCode.I_0_0 :   break;
//			case VarCode.I_0_1 :   break;
//			case VarCode.I_0_2 :   break;
//			case VarCode.I_0_3 :   break;
//			case VarCode.I_0_4 :   break;
//			case VarCode.I_0_5 :   break;
//			case VarCode.I_0_6 :   break;
//			case VarCode.I_0_7 :   break;
//			case VarCode.Q_0   :   break;
//			case VarCode.Q_0_0 :   break;
//			case VarCode.Q_0_1 :   break;
//			case VarCode.Q_0_2 :   break;
//			case VarCode.Q_0_3 :   break;
//			case VarCode.Q_0_4 :   break;
//			case VarCode.Q_0_5 :   break;
//			case VarCode.Q_0_6 :   break;
//			case VarCode.Q_0_7 :   break;
//		}
	}

	private Variables1 variables;
}
