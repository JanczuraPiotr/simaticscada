package pjpl.s7.common;

/**
 * Typy zmiennych występujących w sterowniku PLC.S7
 */
public class TypeCode {

	public static final int BIT   = 0x80000000;
	public static final int BYTE  = 0x00000001; // 8 bit
	public static final int INT   = 0x00000002; // 16 bit
	public static final int DINT  = 0x00000003; // 32 bit
	public static final int REAL  = 0x00000004; // 32 bit float
	public static final int LREAL = 0x00000005; // 64 bit double

	public static final int[] size = new int[6];
	static{
		size[BYTE]  = 0x0000000001;
		size[INT]   = 0x0000000002;
		size[DINT]  = 0x0000000004;
		size[REAL]  = 0x0000000004;
		size[LREAL] = 0x0000000008;
	}

	public static final String[] name = new String[6];
	static{
		name[BYTE]  = "Byte";
		name[INT]   = "Int";
		name[DINT]  = "DInt";
		name[REAL]  = "Real";
		name[LREAL] = "LReal";
	}
}
/*
 *
 * Podział kodów.
 * 0x0000
 *   ||||
 *   ||  dwa mniej znaczące bajty kodują zmienną
 *   | trzeci bajt koduje typ zmiennej
 *    czwarty bajt koduje obszar pamięci.
 *
 * Znaczenia bajtów:
 * bajt nr 0
 * bajt nr 1
 *		kod zmiennej
 *
 * bajt nr 2
 *		0x0 bit
 *		0x1 bajt
 *		0x2 ...
 * bajt nr 3
 *		0x0 db
 *		0x1 in
 *		0x2 out
 *		0x3 ...
 *
 * 0x0000 db jako bajt
 * 0x1000 in jako bajt
 * 0x2000 out jako bajt
 */