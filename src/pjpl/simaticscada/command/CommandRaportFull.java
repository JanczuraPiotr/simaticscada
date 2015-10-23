package pjpl.simaticscada.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pjpl.simaticscada.common.CommandCode;
import pjpl.simaticscada.utils.BigEndianInArray;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class CommandRaportFull extends Command{

	public CommandRaportFull(byte processId) throws IOException {
		super(processId);
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
