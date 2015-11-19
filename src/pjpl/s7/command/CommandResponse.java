package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Odpowiedź nadesłana z SimaticServer w odpowiedzi na komendę.
 *
 * Nagłówek bufora - stała zawartość dla każdego pochodnego CommandResponse
 * buff[0] identyfikator procesu który wygenerował odpowiedź
 *		jeżeli 0x00 < buff[0] < 0xFF identyfikator procesu do którego skierowana jest konenda
 *				buff[1..2] kod komendy
 *		jeżeli 0x00 = buff[0] - komenda dotyczy SimaticServer
 *		jeżeli 0xff = buff[0] - komenda dotyczy Klienta. Nie jest tym samym co odpowiedź na komendę.
 * buff[1..2] kod komendy która była obsługiwana przez process
 * buff[3..5] kod odpowiedzi z jaką zakończyła się praca komendy na którą wygenerowano tą odpowiedź
 * jeżeli buff[3..5] == 0x0000 odpowiedź negatywna na komendę wymagającą tylko potwierdzenia lub niepowodzenie komendy
 * jeżeli buff[3..5] == 0xFFFF odpowiedź tak na komendę wymagającą tylko potwierdzenia
 * jeżeli 0x0000 < buff[3..5] < 0xFFFF specyficzna odpowiedź od której mogą zależeć pozostałe parametry bufora.
 * W zależności od klasy pochodnej mogą być dołączane kolejne parametry definiowane w klasach pochodnych
 *
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public abstract class CommandResponse {
	public CommandResponse(byte processId, short commandCode, short responseCode, InputStream is, OutputStream os) throws IOException{
		this.processId = processId;
		this.commandCode = commandCode;
		this.responseCode = responseCode;
		this.is = is;
		this.os = os;
		this.init();
	}

	public byte getProcessId(){
		return processId;
	}
	public short getCommandCode(){
		return commandCode;
	}
	public short getResponseCode(){
		return responseCode;
	}

	protected abstract void parseResponseStream() throws IOException;
	private void init() throws IOException{
		parseResponseStream();
	}

	protected byte processId;
	protected short commandCode;
	protected short responseCode;
	protected InputStream is;
	protected OutputStream os;

}
