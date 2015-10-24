/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author piotr
 */
public class ResponseInt extends CommandResponse{

	public ResponseInt(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException {
		super(processId, commandCode, responseCode, is, os);
	}

	public short getVarVal(){
		return varVal;
	}
	@Override
	protected void parseResponseStream() throws IOException {
	}

	private short varVal;

}
