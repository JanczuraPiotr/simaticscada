package pjpl.s7.var;

/**
 * Spinacz na wszystkie mapy pamięci występujące w jednym urządzeniu zebrane w jednej chwili.
 */
public class MemClip {
	public Variables memD;
	public Variables memI;
	public Variables memQ;
	/**
	 * Moment wykonania zrzutu.
	 * Jeżeli < 0 to dane utworzono wewnątrz SimaticServer
	 */
	public long timeStamp;
	/**
	 * PLC w którym wykonano zrzut.
	 * Jeżeli < 0 to dane powstały wewnątrz SimaticServer
	 */
	public int plcId;

	public MemClip(Variables memD, Variables memI, Variables memQ, long timeStamp, int plcId){
		this.memD = memD;
		this.memI = memI;
		this.memQ = memQ;
		this.timeStamp = timeStamp;
		this.plcId = plcId;
	}
	public MemClip(Variables memD, Variables memI, Variables memQ, long timeStamp){
		this.memD = memD;
		this.memI = memI;
		this.memQ = memQ;
		this.timeStamp = timeStamp;
		this.plcId = -1;
	}
	public MemClip(Variables memD, Variables memI, Variables memQ){
		this.memD = memD;
		this.memI = memI;
		this.memQ = memQ;
		this.timeStamp = -1;
		this.plcId = -1;
	}

	//------------------------------------------------------------------------------
	// interfejs

	public MemByteClip toMemByteClip(){
		return new MemByteClip(memD.getMemCopy(), memI.getMemCopy(), memQ.getMemCopy(), timeStamp, plcId);
	}

	// interfejs
	//------------------------------------------------------------------------------

	//------------------------------------------------------------------------------
	// metody prywatne


	private MemClip(){}
}
