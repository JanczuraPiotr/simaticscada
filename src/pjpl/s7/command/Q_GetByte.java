package pjpl.s7.command;

import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * @todo opis
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class Q_GetByte extends Command{

	public Q_GetByte(byte processId, short portGroup, Observer observer) throws IOException {
		super(processId, observer);
		this.portGroup = portGroup;
		init();
	}

	@Override
	public short getCommandCode() {
		return CommandCode.Q_GET_BYTE;
	}

	@Override
	protected void calculateBuffSize() {
		buffSize = 5;
	}

	@Override
	protected void buildCommandBuff() {
		BigEndianInArray._short(getCommandCode(), buff, 0);
		BigEndianInArray._byte(getProcessId(), buff, 2);
		BigEndianInArray._short(portGroup, buff, 4);
	}

	private short portGroup;
}
