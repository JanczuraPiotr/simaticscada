package pjpl.s7.utils;

/**
 *
 * @author pjanczura
 */
public class Bits {
	public static final byte BIT_0 = 0;
	public static final byte BIT_1 = 1;
	public static final byte BIT_2 = 2;
	public static final byte BIT_3 = 3;
	public static final byte BIT_4 = 4;
	public static final byte BIT_5 = 5;
	public static final byte BIT_6 = 6;
	public static final byte BIT_7 = 7;
	public static final byte BIT_8 = 8;
	public static final byte BIT_9 = 9;
	public static final byte BIT_10 = 10;
	public static final byte BIT_11 = 11;
	public static final byte BIT_12 = 12;
	public static final byte BIT_13 = 13;
	public static final byte BIT_14 = 14;
	public static final byte BIT_15 = 15;
	public static final byte BIT_16 = 16;
	public static final byte BIT_17 = 17;
	public static final byte BIT_18 = 18;
	public static final byte BIT_19 = 19;
	public static final byte BIT_20 = 20;
	public static final byte BIT_21 = 21;
	public static final byte BIT_22 = 22;
	public static final byte BIT_23 = 23;
	public static final byte BIT_24 = 24;
	public static final byte BIT_25 = 25;
	public static final byte BIT_26 = 26;
	public static final byte BIT_27 = 27;
	public static final byte BIT_28 = 28;
	public static final byte BIT_29 = 29;
	public static final byte BIT_30 = 30;
	public static final byte BIT_31 = 31;
	public static final byte BIT_32 = 32;
	public static final byte BIT_33 = 33;
	public static final byte BIT_34 = 34;
	public static final byte BIT_35 = 35;
	public static final byte BIT_36 = 36;
	public static final byte BIT_37 = 37;
	public static final byte BIT_38 = 38;
	public static final byte BIT_39 = 39;

	public static final byte BIT_40 = 40;
	public static final byte BIT_41 = 41;
	public static final byte BIT_42 = 42;
	public static final byte BIT_43 = 43;
	public static final byte BIT_44 = 44;
	public static final byte BIT_45 = 45;
	public static final byte BIT_46 = 46;
	public static final byte BIT_47 = 47;
	public static final byte BIT_48 = 48;
	public static final byte BIT_49 = 49;
	public static final byte BIT_50 = 50;

	public static final byte BIT_51 = 51;
	public static final byte BIT_52 = 52;
	public static final byte BIT_53 = 53;
	public static final byte BIT_54 = 54;
	public static final byte BIT_55 = 55;
	public static final byte BIT_56 = 56;
	public static final byte BIT_57 = 57;
	public static final byte BIT_58 = 58;
	public static final byte BIT_59 = 59;
	public static final byte BIT_60 = 60;
	public static final byte BIT_61 = 61;
	public static final byte BIT_62 = 62;
	public static final byte BIT_63 = 63;

	public static final byte BIT_A = BIT_10;
	public static final byte BIT_B = BIT_11;
	public static final byte BIT_C = BIT_12;
	public static final byte BIT_D = BIT_13;
	public static final byte BIT_E = BIT_14;
	public static final byte BIT_F = BIT_15;

	public static final byte[] maskByte = {
			(byte)0x01,
			(byte)0x02,
			(byte)0x04,
			(byte)0x08,
			(byte)0x10,
			(byte)0x20,
			(byte)0x40,
			(byte)0x80
	};
	public static final short[] maskShort = {
			(short)0x0001,
			(short)0x0002,
			(short)0x0004,
			(short)0x0008,
			(short)0x0010,
			(short)0x0020,
			(short)0x0040,
			(short)0x0080,
			(short)0x0100,
			(short)0x0200,
			(short)0x0400,
			(short)0x0800,
			(short)0x1000,
			(short)0x2000,
			(short)0x4000,
			(short)0x8000
	};
	public static final int[] maskInt = {
			0x00000001,
			0x00000002,
			0x00000004,
			0x00000008,
			0x00000010,
			0x00000020,
			0x00000040,
			0x00000080,
			0x00000100,
			0x00000200,
			0x00000400,
			0x00000800,
			0x00001000,
			0x00002000,
			0x00004000,
			0x00008000,
			0x00010000,
			0x00020000,
			0x00040000,
			0x00080000,
			0x00100000,
			0x00200000,
			0x00400000,
			0x00800000,
			0x01000000,
			0x02000000,
			0x04000000,
			0x08000000,
			0x10000000,
			0x20000000,
			0x40000000,
			0x80000000
	};
	public static final long[] maskLong = {
			0x0000000000000001L,
			0x0000000000000002L,
			0x0000000000000004L,
			0x0000000000000008L,
			0x0000000000000010L,
			0x0000000000000020L,
			0x0000000000000040L,
			0x0000000000000080L,
			0x0000000000000100L,
			0x0000000000000200L,
			0x0000000000000400L,
			0x0000000000000800L,
			0x0000000000001000L,
			0x0000000000002000L,
			0x0000000000004000L,
			0x0000000000008000L,
			0x0000000000010000L,
			0x0000000000020000L,
			0x0000000000040000L,
			0x0000000000080000L,
			0x0000000000100000L,
			0x0000000000200000L,
			0x0000000000400000L,
			0x0000000000800000L,
			0x0000000001000000L,
			0x0000000002000000L,
			0x0000000004000000L,
			0x0000000008000000L,
			0x0000000010000000L,
			0x0000000020000000L,
			0x0000000040000000L,
			0x0000000080000000L,
			0x0000000010000000L,
			0x0000000020000000L,
			0x0000000040000000L,
			0x0000000080000000L,
			0x0000000100000000L,
			0x0000002000000000L,
			0x0000004000000000L,
			0x0000008000000000L,
			0x0000010000000000L,
			0x0000020000000000L,
			0x0000040000000000L,
			0x0000080000000000L,
			0x0000100000000000L,
			0x0000200000000000L,
			0x0000400000000000L,
			0x0000800000000000L,
			0x0001000000000000L,
			0x0002000000000000L,
			0x0004000000000000L,
			0x0008000000000000L,
			0x0010000000000000L,
			0x0020000000000000L,
			0x0040000000000000L,
			0x0080000000000000L,
			0x0100000000000000L,
			0x0200000000000000L,
			0x0400000000000000L,
			0x0800000000000000L,
			0x1000000000000000L,
			0x2000000000000000L,
			0x4000000000000000L,
			0x8000000000000000L

	};

	public static int get(byte var, byte bitNr){
		return ( ( var & maskByte[bitNr] ) > 0 ? 1 : 0 ) ;
	}
	public static int get(short var, byte bitNr){
		return ( ( var & maskShort[bitNr] ) > 0 ? 1 : 0 ) ;
	}
	public static int get(int var, byte bitNr){
		return ( ( var & maskInt[bitNr] ) > 0 ? 1 : 0 ) ;
	}
	public static int get(long var, byte bitNr){
		return ( ( var & maskLong[bitNr] ) > 0 ? 1 : 0 ) ;
	}
	public static int get(byte[] var, byte bitNr){
		// @todo rzucić wyjątek gdy bitNie nie mieści się w buforze

		int buffSize = var.length;
		// ilość bitów w całym buforze
		int bitsVar = buffSize * 8;
		// w którym bajcie zmiennej znaduje się bit
		int bitInVarByte =  bitNr / 8  ;
		// w którym bajcie bufora znajduje się bit. Bajty w zmiennej mają odwrotną kolejność do bajtów w buforze.
		int bitInBuffByte = ( buffSize - 1 ) - bitInVarByte;
		// którym bitem jest w bajcie
		int bitInByte = bitNr % 8;

		return ( ( var[bitInBuffByte] & maskByte[bitInByte] ) > 0 ? 1 : 0 ) ;
	}
	public static byte on(byte var, byte bitNr){
		return (byte) (var | maskByte[bitNr]);
	}
	public static short on(short var, byte bitNr){
		return (short) (var | maskShort[bitNr]);
	}
	public static int on(int var, byte bitNr){
		return  (var | maskInt[bitNr]);
	}
	public static long on(long var, byte bitNr){
		return  (var | maskLong[bitNr]);
	}
	public static void on(byte[] var, byte bitNr){

		int buffSize = var.length;
		// ilość bitów w całym buforze
		int bitsVar = buffSize * 8;
		// w którym bajcie zmiennej znaduje się bit
		int bitInVarByte =  bitNr / 8  ;
		// w którym bajcie bufora znajduje się bit. Bajty w zmiennej mają odwrotną kolejność do bajtów w buforze.
		int bitInBuffByte = ( buffSize - 1 ) - bitInVarByte;
		// którym bitem jest w bajcie
		int bitInByte = bitNr % 8;

		var[bitInBuffByte] = (byte)( var[bitInBuffByte] | maskByte[bitInByte] ) ;
	}
	public static byte off(byte var, byte bitNr){
		return (byte) (var & ~maskByte[bitNr]);
	}
	public static short off(short var, byte bitNr){
		return (short) (var & ~maskShort[bitNr]);
	}
	public static int off(int var, byte bitNr){
	 return (int) (var & ~maskInt[bitNr]);
	}
	public static long off(long var, byte bitNr){
	 return (long) (var & ~maskLong[bitNr]);
	}
	public static void off(byte[] var, byte bitNr){

		int buffSize = var.length;
		// ilość bitów w całym buforze
		int bitsVar = buffSize * 8;
		// w którym bajcie zmiennej znaduje się bit
		int bitInVarByte =  bitNr / 8  ;
		// w którym bajcie bufora znajduje się bit. Bajty w zmiennej mają odwrotną kolejność do bajtów w buforze.
		int bitInBuffByte = ( buffSize - 1 ) - bitInVarByte;
		// którym bitem jest w bajcie
		int bitInByte = bitNr % 8;

		var[bitInBuffByte] = (byte)( var[bitInBuffByte] & ~maskByte[bitInByte] ) ;

	}
	public static byte sw(byte var, byte bitNr){
		if( get(var, bitNr) == 1){
			return off(var, bitNr);
		}else{
			return on(var,bitNr);
		}
	}
	public static int sw(short var, byte bitNr){
		if( get(var, bitNr) == 1){
			return off(var, bitNr);
		}else{
			return on(var,bitNr);
		}
	}
	public static int sw(int var, byte bitNr){
		if( get(var, bitNr) == 1){
			return off(var, bitNr);
		}else{
			return on(var,bitNr);
		}
	}
	public static long sw(long var, byte bitNr){
		if( get(var, bitNr) == 1){
			return off(var, bitNr);
		}else{
			return on(var,bitNr);
		}
	}
	public static void sw(byte[] var, byte bitNr){
		if( get(var, bitNr) == 1){
			off(var, bitNr);
		}else{
			on(var,bitNr);
		}
	}

}
