package pjpl.s7.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;
import java.util.Observer;

/**
 * Komenda wysyłana do procesu.
 * Na podstawie atrybutów komend pochodnych tworzy strumień bajtowy rzutowany na string.
 *
 * Domyślny format bufora - stała zawartość dla każdego pochodnego Command
 * buff[0]
 * jeżeli 0x00 < buff[0] < 0xFF identyfikator procesu do którego skierowana jest konenda
 *		buff[1..2] kod komendy
 * jeżeli 0x00 = buff[0] - komenda dotyczy SimaticServer
 * jeżeli 0xff = buff[0] - komenda dotyczy Klienta. Nie jest tym samym co odpowiedź na komendę.
 *
 * W zależności od klasy pochodnej mogą być dołączane kolejne parametry definiowane w klasach pochgodnych
 *
 * @author Piotr Janczura <piotr@janczura.pl>
 */
public abstract class Command extends Observable{
	/**
	 * Spakowane atrybuty komendy
	 */
	protected byte[] buff;
	protected int buffSize;
	private CommandResponse commandResponse = null;

	/**
	 * Identyfikator procesu, który powinien wykonać komendę
	 * Wartość zmiennej przekazywana jest w parametrach komendy w buforze za kodem Komendy.
	 */
	private final byte processId;
	/**
	 * Gniazdo do SimaticServer
	 */
	private OutputStream os;
	private InputStream is;


	public Command(byte processId, Observer observer) throws IOException{
		this.processId = processId;
		this.addObserver(observer);
		init();
	}

	public void action(InputStream is, OutputStream os, CommandResponseBuilder responseBuilder) throws IOException{
		if( commandResponse == null ){
			os.write(buff, 0, buff.length);
			commandResponse = responseBuilder.build();
			setChanged();
			notifyObservers(commandResponse);
		}else{
			// @todo co zrobić w przypadku próby ponownego wykonania komendy
		}
	}
	public byte getProcessId(){
		return processId;
	}
	/**
	 * Zwraca kod komendy obsługiwanej przez obiekt
	 * @return
	 */
	public abstract short getCommandCode();
	public byte[] getCommandBuff(){
		return buff;
	}
	public int getCommandBuffSize(){
		return buffSize;
	}
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
	protected void init(){
		calculateBuffSize();
		buff = new byte[buffSize];
		buildCommandBuff();
	}
}
