package pjpl.s7.process;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;
import pjpl.s7.command.Command;
import pjpl.s7.command.CommandResponse;
import pjpl.s7.command.ResponseBuff;
import pjpl.s7.command.ResponseByte;
import pjpl.s7.command.ResponseDInt;
import pjpl.s7.command.ResponseGeneral;
import pjpl.s7.command.ResponseInt;
import pjpl.s7.command.ResponseNo;
import pjpl.s7.command.ResponseOk;
import pjpl.s7.command.ResponseRaportFull;
import pjpl.s7.command.ResponseReal;
import pjpl.s7.common.ResponseCode;
import pjpl.s7.var.Variables;
import pjpl.scada.var.Variables1;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public abstract class Process implements Runnable, Observer{

	public Process(byte processId, LinkedBlockingQueue<Command> commandQueue){
		this.processId = processId;
		this.setCommandQueue(commandQueue);
	}

	//------------------------------------------------------------------------------
	// interface

	public byte getProcessId(){
		return processId;
	}
	public void sendCommand(Command command){
		commandQueue.add(command);
	}

	// interface
	//------------------------------------------------------------------------------

	public abstract void onVariableChange(Variables1 variables, short codeVar);

	@Override
	public void run() {
		steep();
	}

	@Override
	public void update(Observable object, Object attrybute) {
		if( object instanceof Command){
			CommandResponse response = (CommandResponse)attrybute;
			if( response instanceof ResponseRaportFull){
				switch(response.getResponseCode()){
					case ResponseCode.OK:
						onResponseOk((ResponseOk)response);
						break;
					case ResponseCode.NO:
						onResponseNo((ResponseNo)response);
						break;
					case ResponseCode.GENERAL:
						onResponseGeneral((ResponseGeneral)response);
						break;
					case ResponseCode.RETURN_BYTE:
						onResponseByte((ResponseByte)response);
						break;
					case ResponseCode.RETURN_INT:
						onResponseInt((ResponseInt)response);
						break;
					case ResponseCode.RETURN_DINT:
						onResponseDInt((ResponseDInt)response);
						break;
					case ResponseCode.RETURN_REAL:
						onResponseReal((ResponseReal)response);
						break;
					case ResponseCode.RETURN_BUFF:
						onResponseBuff((ResponseBuff)response);
						break;
					case ResponseCode.RAPORT_FULL:
						onResponseRaportFull((ResponseRaportFull)response);
						break;
				}
			}
		} else if( object instanceof Variables1){
			// Variables zawsze infrmuje o zmianie podając kod zmienionej zmiennej
			short code = Short.parseShort(attrybute.toString());
			onVariableChange( (Variables1)object, code);
		}
	}
	public abstract void onResponseRaportFull(ResponseRaportFull response);
	public abstract void onResponseGeneral(ResponseGeneral response);
	public abstract void onResponseOk(ResponseOk response);
	public abstract void onResponseNo(ResponseNo response);
	public abstract void onResponseByte(ResponseByte response);
	public abstract void onResponseInt(ResponseInt response);
	public abstract void onResponseDInt(ResponseDInt response);
	public abstract void onResponseReal(ResponseReal response);
	public abstract void onResponseBuff(ResponseBuff response);
	/**
	 * Główne operacje wątku. Tu można wykonanć obliczenia na zmniennych procesu.
	 */
	abstract protected void steep();

	protected void setCommandQueue( LinkedBlockingQueue<Command> commandQueue){
		this.commandQueue = commandQueue;
	}

	//------------------------------------------------------------------------------
	// atrybuty chronione

	/**
	 * Zarządza kopiami zmiennych z SimaticServer.
	 */
	protected Variables variables;

	//------------------------------------------------------------------------------
	// atrybutye prywatne

	private byte processId;
	private boolean doRun = true;

	private LinkedBlockingQueue<Command> commandQueue;
}
