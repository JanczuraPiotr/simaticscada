/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import pjpl.s7.utils.ReadDataStream;

/**
 *
 * @author piotr
 */
public class ResponseByte extends CommandResponse{

	public ResponseByte(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException {
		super(processId, commandCode, responseCode, is, os);
	}

	public byte getVarVal(){
		return varVal;
	}

	@Override
	protected void parseResponseStream() throws IOException {
		varVal = ReadDataStream.readByte(is);
	}
	private byte varVal;
}
