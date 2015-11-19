package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pjpl.s7.common.ResponseCode;
import pjpl.s7.utils.ReadDataStream;


/**
 *
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class CommandResponseBuilder {
	protected byte processId;
	protected short commandCode;
	protected short responseCode;
	private final InputStream is;
	private final OutputStream os;

	public CommandResponseBuilder(InputStream is, OutputStream os){
		this.is = is;
		this.os = os;
	}

	public CommandResponse build() throws IOException{
		processId = ReadDataStream.readByte(is);
		commandCode = ReadDataStream.readShort(is);
		responseCode = ReadDataStream.readShort(is);

		switch(responseCode){
			case ResponseCode.OK:
				return new ResponseOk(processId, commandCode, responseCode, is, os);
			case ResponseCode.NO:
				return new ResponseNo(processId, commandCode, responseCode, is, os);
			case ResponseCode.GENERAL:
				return new ResponseGeneral(processId, commandCode, responseCode, is, os);
			case ResponseCode.RETURN_BYTE:
				return new ResponseByte(processId, commandCode, responseCode, is, os);
			case ResponseCode.RETURN_INT:
				return new ResponseInt(processId, commandCode, responseCode, is, os);
			case ResponseCode.RETURN_DINT:
				return new ResponseDInt(processId, commandCode, responseCode, is, os);
			case ResponseCode.RETURN_REAL:
				return new ResponseReal(processId, commandCode, responseCode, is, os);
			case ResponseCode.RETURN_BUFF:
				return new ResponseBuff(processId, commandCode, responseCode, is, os);
			case ResponseCode.RAPORT_FULL:
				return new ResponseRaportFull(processId, commandCode, responseCode, is, os);
			default:
		}

		return null;
	}

}
