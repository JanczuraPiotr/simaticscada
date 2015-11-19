package pjpl.s7.command;

import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class Q_SetByte extends Command{
	private final short portGroup;
	private final byte varVal;

	public Q_SetByte(byte processId, short portGroup, byte varVal, Observer observer)throws IOException {
		super(processId, observer);
		this.portGroup = portGroup;
		this.varVal = varVal;
		this.init();
	}

	@Override
	public short getCommandCode() {
		return CommandCode.Q_SET_BYTE;
	}

	@Override
	protected void calculateBuffSize() {
		buffSize = 6;
	}

	@Override
	protected void buildCommandBuff() {
		BigEndianInArray._short(getCommandCode(), buff, 0);
		BigEndianInArray._byte(getProcessId(), buff, 2);
		BigEndianInArray._short(portGroup, buff, 3);
		BigEndianInArray._byte(varVal, buff, 5);

	}

}
