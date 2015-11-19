package pjpl.s7.utils;

import java.io.InputStream;
import java.io.IOException;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public abstract class ReadDataStream {
	private static final int BYTE = 1;
	private static final int SHORT = 2;
	private static final int INT = 4;
	private static final int LONG = 8;
	private static final int FLOAT = 4;
	private static final int DOUBLE = 8;

	public static byte readByte(InputStream bis) throws IOException{
		return BigEndianInArray._byte(scan(bis, BYTE), 0);
	}
	public static short readShort(InputStream bis) throws IOException{
		return BigEndianInArray._short(scan(bis, SHORT), 0);
	}
	public static int readInt(InputStream bis) throws IOException{
		return BigEndianInArray._int(scan(bis, INT), 0);
	}
	public static float readFloat(InputStream bis) throws IOException{
		return BigEndianInArray._float(scan(bis, FLOAT), 0);
	}

	private static byte[] scan(InputStream bis, int varSize) throws IOException{
		int inputVar;
		byte[] inputBuff = new byte[varSize];
		for( int i = 0 ; i < varSize ; i++ ){
			if ( ( inputVar = bis.read() ) >= 0 ){
				inputBuff[i] = (byte)( inputVar & 0x000000FF );
			}else{
				throw new IOException("błąd przy odczycie bajtu ze strumienia");
			}
		}
		return inputBuff;
	}
}
