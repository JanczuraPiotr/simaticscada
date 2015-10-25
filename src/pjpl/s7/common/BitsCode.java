package pjpl.s7.common;

import Moka7.S7;
import pjpl.s7.utils.Bits;

/**
 *
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class BitsCode {
	public static byte memType = 0;
	public static short varCode = 0;
	public static byte bitNr = 0;

	public static final int I_O_0 = prepare(S7.I, VarCode.I_0, Bits.BIT_0);
	public static final int I_O_1 = prepare(S7.I, VarCode.I_0, Bits.BIT_1);
	public static final int I_O_2 = prepare(S7.I, VarCode.I_0, Bits.BIT_2);
	public static final int I_O_3 = prepare(S7.I, VarCode.I_0, Bits.BIT_3);
	public static final int I_O_4 = prepare(S7.I, VarCode.I_0, Bits.BIT_4);
	public static final int I_O_5 = prepare(S7.I, VarCode.I_0, Bits.BIT_5);
	public static final int I_O_6 = prepare(S7.I, VarCode.I_0, Bits.BIT_6);
	public static final int I_O_7 = prepare(S7.I, VarCode.I_0, Bits.BIT_7);

	public static final int Q_0_0 = prepare(S7.Q, VarCode.Q_0,Bits.BIT_0);
	public static final int Q_0_1 = prepare(S7.Q, VarCode.Q_0,Bits.BIT_1);
	public static final int Q_0_2 = prepare(S7.Q, VarCode.Q_0,Bits.BIT_2);
	public static final int Q_0_3 = prepare(S7.Q, VarCode.Q_0,Bits.BIT_3);
	public static final int Q_0_4 = prepare(S7.Q, VarCode.Q_0,Bits.BIT_4);
	public static final int Q_0_5 = prepare(S7.Q, VarCode.Q_0,Bits.BIT_5);


	public static int prepare(byte memType, short varCode, byte bitNr){
		return  (
				( ( memType << 24 ) & 0xFF000000 ) +
				( ( varCode << 8 ) & 0x00FFFF00 ) +
				bitNr
		);
	}
	public static void parse(int bitCode){
		memType = (byte)(( bitCode & 0xFF000000 ) >> 24);
		varCode = (short)( (bitCode & 0x00FFFF00) >> 8 );
		bitNr = (byte)( (bitCode & 0x000000FF ));
	}
}
