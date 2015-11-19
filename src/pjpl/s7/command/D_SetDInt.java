package pjpl.s7.command;

import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class D_SetDInt extends Command{
	private short varCode;
	private int varVal;

	public D_SetDInt(byte processId, short varCode, int varVal, Observer observer) throws IOException {
		super(processId, observer);
		this.varCode = varCode;
		this.varVal = varVal;
		init();
	}
	public short getVarCode(){
		return varCode;
	}
	public int getVarVal(){
		return varVal;
	}

	@Override
	public short getCommandCode() {
		return CommandCode.D_SET_DINT;
	}

	@Override
	protected void calculateBuffSize() {
		buffSize = 9;
	}

	@Override
	protected void buildCommandBuff() {
		BigEndianInArray._short(getCommandCode(), buff, 0);
		BigEndianInArray._byte(getProcessId(), buff, 2);
		BigEndianInArray._short(getVarCode(), buff, 3);
		BigEndianInArray._int(getVarVal(), buff, 5);
	}

}
