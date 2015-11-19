package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pjpl.s7.utils.ReadDataStream;

/**
 *
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class ResponseDInt extends CommandResponse{
	private int varVal;

	public ResponseDInt(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException {
		super(processId, commandCode, responseCode, is, os);
	}

	public int getVarVal(){
		return varVal;
	}
	@Override
	protected void parseResponseStream() throws IOException{
		varVal = ReadDataStream.readInt(is);
	}
}
