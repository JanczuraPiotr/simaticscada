package pjpl.s7.command;

import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class D_SetReal extends Command{
	private final short varCode;
	private final float varVal;

	public D_SetReal(byte processId, short varCode, float varVal, Observer observer) throws IOException {
		super(processId, observer);
		this.varCode = varCode;
		this.varVal = varVal;
		init();
	}
	public short getVarCode(){
		return varCode;
	}
	public float getVarVal(){
		return varVal;
	}

	@Override
	public short getCommandCode() {
		return CommandCode.D_SET_REAL;
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
		BigEndianInArray._float(getVarVal(), buff, 5);
	}

}
