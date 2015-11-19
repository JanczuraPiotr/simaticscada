package pjpl.scada.process;

import Moka7.S7;
import java.io.IOException;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.s7.command.BitSwitch;
import pjpl.s7.command.Command;
import pjpl.s7.command.CommandRaportFull;
import pjpl.s7.command.D_SetByte;
import pjpl.s7.command.D_SetDInt;
import pjpl.s7.command.D_SetInt;
import pjpl.s7.command.D_SetReal;
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
	private Variables1 variables;

	public Process1(byte processId, LinkedBlockingQueue<Command> commandQueue) {
		super(processId, commandQueue);
		prevStep = System.currentTimeMillis();
		variables = new Variables1(this);
	}

	public void addVariablesObserver(Observer observer){
		variables.addObserver(observer);
	}
	public void setZmienna_1(byte var) throws IOException{
		variables.setZmienna_1(var);
		sendCommand(new D_SetByte(getProcessId(), VarCode.ZMIENNA_1, var, this));
	}
	public void setZmienna_2(short var) throws IOException{
		variables.setZmienna_2(var);
		sendCommand(new D_SetInt(getProcessId(), VarCode.ZMIENNA_2, var, this));
	}
	public void setZmienna_3(int var) throws IOException{
		variables.setZmienna_3(var);
		sendCommand(new D_SetDInt(getProcessId(), VarCode.ZMIENNA_3, var, this));
	}
	public void setZmienna_4(float var) throws IOException{
		variables.setZmienna_4(var);
		sendCommand(new D_SetReal(getProcessId(), VarCode.ZMIENNA_4, var, this));
	}

	public byte getZmienna_1(){
		return variables.getZmienna_1();
	}
	public short getZmienna_2(){
		return variables.getZmienna_2();
	}
	public int getZmienna_3(){
		return variables.getZmienna_3();
	}
	public float getZmienna_4(){
		return variables.getZmienna_4();
	}

	public boolean getI_0_0(){return variables.getI_0_0();}
	public boolean getI_0_1(){return variables.getI_0_1();}
	public boolean getI_0_2(){return variables.getI_0_2();}
	public boolean getI_0_3(){return variables.getI_0_3();}
	public boolean getI_0_4(){return variables.getI_0_4();}
	public boolean getI_0_5(){return variables.getI_0_5();}
	public boolean getI_0_6(){return variables.getI_0_6();}
	public boolean getI_0_7(){return variables.getI_0_7();}

	public void setQ_0_0(boolean b) throws IOException{
		variables.setQ_0_0(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_0, this));
	}
	public void setQ_0_1(boolean b) throws IOException{
		variables.setQ_0_1(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_1, this));
	}
	public void setQ_0_2(boolean b) throws IOException{
		variables.setQ_0_2(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_2, this));
	}
	public void setQ_0_3(boolean b) throws IOException{
		variables.setQ_0_3(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_3, this));
	}
	public void setQ_0_4(boolean b) throws IOException{
		variables.setQ_0_4(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_4, this));
	}
	public void setQ_0_5(boolean b) throws IOException{
		variables.setQ_0_5(b);
		sendCommand(new BitSwitch(getProcessId(),S7.Q, VarCode.Q_0, Bits.BIT_5, this));
	}

	public boolean getQ_0_0(){return variables.getQ_0_0();}
	public boolean getQ_0_1(){return variables.getQ_0_1();}
	public boolean getQ_0_2(){return variables.getQ_0_2();}
	public boolean getQ_0_3(){return variables.getQ_0_3();}
	public boolean getQ_0_4(){return variables.getQ_0_4();}
	public boolean getQ_0_5(){return variables.getQ_0_5();}

	@Override
	protected void steep() {
		thisStep = System.currentTimeMillis();
		try {
			sendCommand( new CommandRaportFull(ConstProcess.PROCESS1_ID, this));
		} catch (IOException ex) {
			Logger.getLogger(Process1.class.getName()).log(Level.SEVERE, null, ex);
		}finally{
			prevStep = thisStep;
		}
	}

	@Override
	public void onResponseRaportFull(ResponseRaportFull response) {
		variables.parseResponseRaportFull(response);
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
	public void onVariableChange(Variables1 variables, short codeVar) {
//		switch(codeVar){
//			case VarCode.ZMIENNA_1:break;
//			case VarCode.ZMIENNA_2:break;
//			case VarCode.ZMIENNA_3:break;
//			case VarCode.ZMIENNA_4:break;
//			case VarCode.I_0_0 :   break;
//			case VarCode.I_0_1 :   break;
//			case VarCode.I_0_2 :   break;
//			case VarCode.I_0_3 :   break;
//			case VarCode.I_0_4 :   break;
//			case VarCode.I_0_5 :   break;
//			case VarCode.I_0_6 :   break;
//			case VarCode.I_0_7 :   break;
//			case VarCode.Q_0_0 :   break;
//			case VarCode.Q_0_1 :   break;
//			case VarCode.Q_0_2 :   break;
//			case VarCode.Q_0_3 :   break;
//			case VarCode.Q_0_4 :   break;
//			case VarCode.Q_0_5 :   break;
//		}
	}

}
