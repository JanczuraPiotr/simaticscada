package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pjpl.s7.utils.ReadDataStream;

public class ResponseReal extends CommandResponse{
	private float varVal;

	public ResponseReal(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException {
		super(processId, commandCode, responseCode, is, os);
	}

	public float getVarVal(){
		return varVal;
	}
	@Override
	protected void parseResponseStream() throws IOException {
		varVal = ReadDataStream.readFloat(is);
	}

}
