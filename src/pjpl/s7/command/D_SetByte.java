package pjpl.s7.command;

import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * @todo opis
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class D_SetByte extends Command{

	public D_SetByte(byte processId, short varCode, byte varVal, Observer observer) throws IOException {
		super(processId, observer);
		this.varCode = varCode;
		this.varVal = varVal;
		this.init();
	}
	public short getVarCode(){
		return varCode;
	}
	public byte getVarVal(){
		return varVal;
	}

	@Override
	public short getCommandCode() {
		return CommandCode.D_SET_BYTE;
	}

	@Override
	protected void calculateBuffSize() {
		buffSize = 6;
	}

	@Override
	protected void buildCommandBuff() {
		BigEndianInArray._short(getCommandCode(), buff, 0);
		BigEndianInArray._byte(getProcessId(), buff, 2);
		BigEndianInArray._short(getVarCode(), buff, 3);
		BigEndianInArray._byte(getVarVal(), buff, 5);
	}

	private short varCode;
	private byte varVal;
}
