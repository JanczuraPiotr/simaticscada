package pjpl.simaticscada.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pjpl.simaticscada.common.ResponseCode;
import pjpl.simaticscada.utils.ReadDataStream;


/**
 *
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public class CommandResponseBuilder {
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
				break;
			case ResponseCode.NO:
				break;
			case ResponseCode.GENERAL:
				break;
			case ResponseCode.RETURN_BYTE:
				break;
			case ResponseCode.RETURN_INT:
				break;
			case ResponseCode.RETURN_DINT:
				break;
			case ResponseCode.RETURN_REAL:
				break;
			case ResponseCode.RETURN_BUFF:
				break;
			case ResponseCode.RAPORT_FULL:
				return new ResponseRaportFull(processId, commandCode, responseCode, is, os);
			default:
		}

		return null;
	}


	protected byte processId;
	protected short commandCode;
	protected short responseCode;

	private InputStream is;
	private OutputStream os;
}
