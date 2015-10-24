package pjpl.s7.common;

public class ResponseCode {

	public static final short NO = (short)0x0000;
	public static final short OK = (short)0xFFFF;

	public static final short GENERAL     = (short)0xC000;

	public static final short RETURN_BUFF    = (short)0xC100;
	public static final short RETURN_BYTE    = (short)0xC101;
	public static final short RETURN_INT     = (short)0xC102;
	public static final short RETURN_DINT    = (short)0xC103;
	public static final short RETURN_LONG    = (short)0xC104;
	public static final short RETURN_REAL    = (short)0xC105;
	public static final short RETURN_LREAL   = (short)0xC106;
	public static final short RAPORT_FULL    = (short)0xC107;
}
