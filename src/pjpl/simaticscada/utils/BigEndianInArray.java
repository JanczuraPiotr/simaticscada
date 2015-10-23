package pjpl.simaticscada.utils;

/**
 * Obsługuje przechowywanie zmiennych w tablicy bajtowej w porządku big endian
 */
public abstract class BigEndianInArray {
	public static void _byte(byte val, byte[] buff, int start) throws ArrayIndexOutOfBoundsException {
		buff[start] = val;
	}
	public static byte _byte(byte[] buff, int start) throws ArrayIndexOutOfBoundsException {
		return buff[start];
	}
	public static void _short(short val, byte[] buff, int start) throws ArrayIndexOutOfBoundsException {
		buff[start] =  (byte)( ( val & 0xFF00 ) >> 8 );
		buff[start +1 ] =  (byte)( ( val & 0x00FF ));
	}
	public static short _short(byte[] buff, int start) throws ArrayIndexOutOfBoundsException {
		return (short) (
					(
						( (buff[start  ] << 8 ) & 0x0000FF00 )+
						(buff[start+1]        & 0x000000FF )
					)
				);
	}
	public static void _int(int val, byte[] buff, int start) throws ArrayIndexOutOfBoundsException {
		buff[start]   = (byte)((val & 0xFF000000) >> 24);
		buff[start+1] = (byte)((val & 0x00FF0000) >> 16);
		buff[start+2] = (byte)((val & 0x0000FF00) >> 8);
		buff[start+3] = (byte)((val & 0x000000FF));
	}
	public static int _int(byte[] buff, int start) throws ArrayIndexOutOfBoundsException {
		int i3 = (( buff[start]     & 0x000000FF ) << 24);
		int i2 = (( buff[start + 1] & 0x000000FF ) << 16);
		int i1 = (( buff[start + 2] & 0x000000FF ) <<  8);
		int i0 =    buff[start + 3] & 0x000000FF;
		return ( i3 | i2 | i1 | i0 );
	}
	public static void _float(float val, byte[] buff, int start) throws ArrayIndexOutOfBoundsException {
		int intFloat = Float.floatToIntBits(val);
		buff[start]   = (byte)(((int)intFloat & 0xFF000000) >> 24);
		buff[start+1] = (byte)(((int)intFloat & 0x00FF0000) >> 16);
		buff[start+2] = (byte)(((int)intFloat & 0x0000FF00) >> 8);
		buff[start+3] = (byte)(((int)intFloat & 0x000000FF));
	}
	public static float _float(byte[] buff, int start) throws ArrayIndexOutOfBoundsException {
		int i3 = (( buff[start]     & 0x000000FF ) << 24);
		int i2 = (( buff[start + 1] & 0x000000FF ) << 16);
		int i1 = (( buff[start + 2] & 0x000000FF ) <<  8);
		int i0 =    buff[start + 3] & 0x000000FF;
		return  Float.intBitsToFloat( i3 | i2 | i1 | i0 );
	}
}
