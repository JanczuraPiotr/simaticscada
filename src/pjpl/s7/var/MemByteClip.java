package pjpl.s7.var;

/**
 * Spinacz na wszystkie bloki pamięci występujące w jednym urządzeniu zebrane w jednej chwili.
 */
public class MemByteClip{
	public byte[] buffD;
	public byte[] buffI;
	public byte[] buffQ;
	/**
	 * Moment wykonania zrzutu.
	 * Jeżeli < 0 to dane utworzono wewnątrz SimaticServer
	 */
	public long timeStamp;
	/**
	 * PLC w którym wykonano zrzut.
	 * Jeżeli < 0 to dane powstały wewnątrz SimaticServer
	 */
	public int plcId;;

	public MemByteClip(byte[] buffD, byte[] buffI, byte[] buffQ, long timeStamp, int plcId){
		this.buffD = buffD;
		this.buffI = buffI;
		this.buffQ = buffQ;
		this.timeStamp = timeStamp;
		this.plcId = plcId;
	}
	public MemByteClip(byte[] buffD, byte[] buffI, byte[] buffQ, long timeStamp){
		this.buffD = buffD;
		this.buffI = buffI;
		this.buffQ = buffQ;
		this.timeStamp = timeStamp;
		this.plcId = -1;
	}
	public MemByteClip(byte[] buffD, byte[] buffI, byte[] buffQ){
		this.buffD = buffD;
		this.buffI = buffI;
		this.buffQ = buffQ;
		this.timeStamp = -1;
		this.plcId = -1;
	}

	private MemByteClip(){}
}
