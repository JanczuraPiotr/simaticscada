package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pjpl.s7.utils.ReadDataStream;

/**
 *
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class ResponseBuff extends CommandResponse{
	private int buffSize;
	private byte[] buff;

	public ResponseBuff(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException {
		super(processId, commandCode, responseCode, is, os);
	}

	public int getBuffSize(){
		return buffSize;
	}
	public byte[] getBuff(){
		return buff;
	}
	@Override
	protected void parseResponseStream() throws IOException {
		buffSize = ReadDataStream.readInt(is);
		buff = new byte[buffSize];
		is.read(buff, 0, buffSize);
	}

}
