package pjpl.s7.command;

import Moka7.S7;
import java.io.IOException;
import java.util.Observer;
import pjpl.s7.common.CommandCode;
import pjpl.s7.utils.BigEndianInArray;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class BitSwitch extends Command{

	public BitSwitch(byte processId, byte memType, short varCode, byte bitNr, Observer observer) throws IOException {
		super(processId, observer);
		this.memType = memType;
		this.varCode = varCode;
		this.bitNr = bitNr;
		init();
		System.out.println(String.format("memType = 0x%02X", memType));
		System.out.println(String.format("this.memType = 0x%02X", this.memType));
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

		for( int i = 0 ;i < buffSize ; i++ ){
			System.out.println(String.format("buff[%d] = 0x%02X", i , buff[i]));
		}
	}

	protected byte memType;
	protected short varCode;
	protected byte bitNr;
}
