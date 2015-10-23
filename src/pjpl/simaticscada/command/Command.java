package pjpl.simaticscada.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Komenda wysyłana do procesu.
 * Na podstawie atrybutów komend pochodnych tworzy strumień bajtowy zrzutowany na string.
 *
 * Domyślny format bufora - stała zawartość dla każdego pochodnego Command
 * buff[0]
 * jeżeli 0x00 < buff[0] < 0xFF identyfikator procesu do którego skierowana jest konenda
 *		buff[1..2] kod komendy
 * jeżeli 0x00 = buff[0] - komenda dotyczy SimaticServer
 * jeżeli 0xff = buff[0] - komenda dotyczy Klienta. Nie jest tym samym co odpowiedź na komendę.
 *
 * W zależności od klasy pochodnej mogą być dołączane kolejne parametry definiowane w klasach pochgodnych
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public abstract class Command {
	public static final int MAX_SOCKET_RECEIVE = 100000; //!?

	public Command(byte processId) throws IOException{
		this.processId = processId;

		this.init();
	}

	//------------------------------------------------------------------------------
	// interfejs

	public CommandResponse action(InputStream is, OutputStream os, CommandResponseBuilder responseBuilder) throws IOException{
		if( commandResponse == null ){
			os.write(buff, 0, buff.length);
			commandResponse = responseBuilder.build();
		}
		return commandResponse;
	}
	public byte getProcessId(){
		return processId;
	}
	/**
	 * Zwraca kod komendy obsługiwanej przez obiekt
	 */
	public abstract short getCommandCode();


	// interfejs
	//------------------------------------------------------------------------------

	//------------------------------------------------------------------------------
	// metody chronione

	/**
	 * Nadaje wartość zmiennej commandBuffSize.
	 */
	protected abstract void calculateBuffSize();
	/**
	 * Z atrybutów obiektu pochodnego i processId oraz getCommandCode() buduje commandBuff
	 * 		$this->commandStream
	 *			= BigEndian::shortToPack($this->getCommandCode())
	 *			. BigEndian::byteToPack($this->getProcessId())
	 *			. BigEndian::shortToPack($this->getJakasWartosc())
	 *			. BigEndian::byteToPack($this->getJakasKolejnaWartosc());
	 */
	protected abstract void buildCommandBuff();

	// metody chronione
	//------------------------------------------------------------------------------

	//------------------------------------------------------------------------------
	// metody prywatne

	private void init(){
		calculateBuffSize();
		buff = new byte[buffSize];
		buildCommandBuff();
	}

	// metody prywatne
	//------------------------------------------------------------------------------

	//------------------------------------------------------------------------------
	// atrybuty chronone

	/**
	 * Spakowane atrybuty komendy
	 */
	protected byte[] buff;
	protected int buffSize;

	//------------------------------------------------------------------------------
	// atrybuty prywatne


	private CommandResponse commandResponse = null;

	/**
	 * Identyfikator procesu, który powinien wykonać komendę
	 * Wartość zmiennej przekazywana jest w parametrach komendy w buforze za kodem Komendy.
	 */
	private byte processId;
	/**
	 * Gniazdo do SimaticServer
	 */
	private OutputStream os;
	private InputStream is;
}
