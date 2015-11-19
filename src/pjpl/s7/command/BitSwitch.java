package pjpl.s7.command;

import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * Komenda przełącza wskazany bit wskazanego bajtu.
 *
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class BitSwitch extends Command{
	protected byte memType;
	protected short varCode;
	protected byte bitNr;

	/**
	 *
	 * @param processId numer procesu
	 * @param memType typ pamięci
	 * @param varCode kod zmiennej
	 * @param bitNr numer bitu w zmiennej
	 * @param observer
	 * @throws IOException
	 */
	public BitSwitch(byte processId, byte memType, short varCode, byte bitNr, Observer observer) throws IOException {
		super(processId, observer);
		this.memType = memType;
		this.varCode = varCode;
		this.bitNr = bitNr;
		init();
	}

	@Override
	public short getCommandCode() {
		return CommandCode.BIT_SWITCH;
	}

	@Override
	protected void calculateBuffSize() {
		buffSize = 7;
	}

	@Override
	protected void buildCommandBuff() {
		BigEndianInArray._short(getCommandCode(), buff, 0);
		BigEndianInArray._byte(getProcessId(), buff, 2);
		BigEndianInArray._byte(memType, buff, 3);
		BigEndianInArray._short(varCode, buff, 4);
		BigEndianInArray._byte(bitNr, buff, 6);
	}

}
