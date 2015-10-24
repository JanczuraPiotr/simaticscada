package pjpl.s7.command;

import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class CommandRaportFull extends Command{

	public CommandRaportFull(byte processId, Observer observer) throws IOException {
		super(processId, observer);
	}

	@Override
	public short getCommandCode() {
		return CommandCode.RAPORT_FULL;
	}

	@Override
	protected void buildCommandBuff() {
		BigEndianInArray._short(getCommandCode(), buff, 0);
		BigEndianInArray._byte(getProcessId(), buff, 2);
	}

	@Override
	protected void calculateBuffSize() {
		buffSize = 3;
	}

}
