package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ResponseGeneral extends CommandResponse{

	public ResponseGeneral(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException {
		super(processId, commandCode, responseCode, is, os);
	}

	@Override
	protected void parseResponseStream() {

	}

}
