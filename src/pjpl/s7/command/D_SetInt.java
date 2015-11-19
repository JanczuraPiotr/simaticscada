package pjpl.s7.command;

import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class D_SetInt extends Command{
	private short varCode;
	private short varVal;

	public D_SetInt(byte processId, short varCode, short varVal, Observer observer) throws IOException {
		super(processId, observer);
		this.varCode = varCode;
		this.varVal = varVal;
		this.init();
	}
	public short getVarCode(){
		return varCode;
	}
	public short getVarVal(){
		return varVal;
	}

	@Override
	public short getCommandCode() {
		return CommandCode.D_SET_INT;
	}

	@Override
	protected void calculateBuffSize() {
		buffSize = 7;
	}

	@Override
	protected void buildCommandBuff() {
		BigEndianInArray._short(getCommandCode(), buff, 0);
		BigEndianInArray._byte(getProcessId(), buff, 2);
		BigEndianInArray._short(getVarCode(), buff, 3);
		BigEndianInArray._short(getVarVal(), buff, 5);
	}
}
