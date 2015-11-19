package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ResponseInt extends CommandResponse{
	private short varVal;

	public ResponseInt(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException {
		super(processId, commandCode, responseCode, is, os);
	}

	public short getVarVal(){
		return varVal;
	}
	@Override
	protected void parseResponseStream() throws IOException {
	}

}
