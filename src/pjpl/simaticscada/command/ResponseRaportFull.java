package pjpl.simaticscada.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.simaticscada.utils.ReadDataStream;

/**
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class ResponseRaportFull extends CommandResponse{

	public ResponseRaportFull(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException {
		super(processId, commandCode, responseCode, is, os);
	}

	@Override
	protected void parseResponseStream() {
		try {
			startD = DATA_START;
			buffDLength = ReadDataStream.readInt(is);
			startI = startD + buffDLength;
			buffILength = ReadDataStream.readInt(is);
			startQ = startI + buffILength;
			buffQLength = ReadDataStream.readInt(is);
			zmienna_1 = ReadDataStream.readByte(is);
			zmienna_2 = ReadDataStream.readShort(is);
			zmienna_3 = ReadDataStream.readInt(is);
			zmienna_4 = ReadDataStream.readFloat(is);
			I_0 = ReadDataStream.readByte(is);
			Q_0 = ReadDataStream.readByte(is);
		} catch (IOException ex) {
			Logger.getLogger(ResponseRaportFull.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public byte getZmienna1(){
		return zmienna_1;
	}
	public short getZmienna2(){
		return zmienna_2;
	}
	public int getZmienna3(){
		return zmienna_3;
	}
	public float getZmienna4(){
		return zmienna_4;
	}
	public byte getI_0(){
		return I_0;
	}
	public byte getQ_0(){
		return Q_0;
	}


	private byte zmienna_1;
	private short zmienna_2;
	private int zmienna_3;
	private float zmienna_4;
	private byte I_0;
	private byte Q_0;

	private int startD;
	private int buffDLength;
	private int startI;
	private int buffILength;
	private int startQ;
	private int buffQLength;

	private byte[] buff;


	protected static final int SYGNATURA_LENGHT = 5;
	protected static final int WYMIARY_LENGTH = 12;
	protected static final int NAGLOWEK_LENGTH = 17;
	protected static final int DATA_START = 17;
	protected static final int D_LENGTH_POS =  5;
	protected static final int I_LENGTH_POS =  9;
	protected static final int Q_LENGTH_POS =  13;
}
