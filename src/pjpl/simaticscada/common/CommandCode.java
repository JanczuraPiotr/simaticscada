package pjpl.simaticscada.common;

/**
 * Kody sterujące pracą programu
 */
abstract public class CommandCode{
	public static final short MINIMAL_COMMAND_SIZE = 3;

	public static final short NO = (short)0x0000;
	public static final short OK = (short)0xFFFF;

	// Stałe ogólne
	// 000000000000000B -> 0000111111111111B => 0x0000 -> 0x0FFF => 0 ->  4095

	// Stałe wewnętrzne biblioteki pjpl.s7
	// 000100000000000B -> 0001111111111111B => 0x1000 -> 0x1FFF => 4096 ->  8191

	// nieprzydzielone:
	// 001000000000000B -> 0011111111111111B => 0x2000 -> 0x3FFF =>   8192 -> 16383


	// Kody od 0x8000 wydzielone są dla klas definiujących algorytmy do wykonania w ramach procesu do którego są skierowane.
	// Kod może wymagać parametrów do pracy i te umieszczone są w tym samym buforze zaraz za nim. Obsługą kody wraz z
	// odczytaniem parametrów i wykonaniem algorytmu w ramach procesu zajmują się obiekty klas pochodnych po Command.

	// Kody komend
	// 100000000000000B -> 1011111111111111B => 0x8000 -> 0xBFFF =>  32768 -> 49151

	public static final short I_GET_BYTE  = (short)0x8001;
	public static final short Q_GET_BYTE  = (short)0x8002;
	public static final short Q_SET_BYTE  = (short)0x8003;
	public static final short D_GET_BYTE  = (short)0x8004;
	public static final short D_SET_BYTE  = (short)0x8005;
	public static final short D_GET_INT   = (short)0x8006;
	public static final short D_SET_INT   = (short)0x8007;
	public static final short D_GET_DINT  = (short)0x8008;
	public static final short D_SET_DINT  = (short)0x8009;
	public static final short D_GET_REAL  = (short)0x800A;
	public static final short D_SET_REAL  = (short)0x800B;
	public static final short RAPORT_FULL = (short)0x800C;
	public static final short BIT_ON      = (short)0x800D;
	public static final short BIT_OFF     = (short)0x800E;
	public static final short BIT_SWITCH  = (short)0x800F;

	// Kody odpowiedzi na komendy. Definicje w ResponseCode
	// 110000000000000B -> 1111111111111111B => 0xC000 -> 0xCFFF =>  49152 -> 65535

}
