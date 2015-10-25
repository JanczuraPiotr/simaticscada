package pjpl.s7.common;

/**
 * Kody zmiennych procesowych
 * Dla każdego bloku danych indeksy muszą zaczynać się od 0
 */
abstract public class VarCode{
	// @todo Przenieść do tej klasy definicje dla mapy komórek w postaci tablicy a w MemMap.init() na podstawie tej tablicy budować mapę
	/**
	 * Byte
	 */
	public static final short ZMIENNA_1 = (short)0x2001;
	/**
	 * Int
	 */
	public static final short ZMIENNA_2 = (short)0x3002;
	/**
	 * DInt
	 */
	public static final short ZMIENNA_3 = (short)0x4003;
	/**
	 * Real
	 */
	public static final short ZMIENNA_4 = (short)0x5004;

	/**
	 * Byte
	 */
	public static final short I_0    = (short)0x1000;
	public static final short I_0_0  = (short)0x8000;
	public static final short I_0_1  = (short)0x8001;
	public static final short I_0_2  = (short)0x8002;
	public static final short I_0_3  = (short)0x8003;
	public static final short I_0_4  = (short)0x8004;
	public static final short I_0_5  = (short)0x8005;
	public static final short I_0_6  = (short)0x8006;
	public static final short I_0_7  = (short)0x8007;

	/**
	 * Byte
	 */
	public static final short Q_0    = (short)0x1010;
	public static final short Q_0_0  = (short)0x8010;
	public static final short Q_0_1  = (short)0x8011;
	public static final short Q_0_2  = (short)0x8012;
	public static final short Q_0_3  = (short)0x8013;
	public static final short Q_0_4  = (short)0x8014;
	public static final short Q_0_5  = (short)0x8015;
	public static final short Q_0_6  = (short)0x8016;
	public static final short Q_0_7  = (short)0x8017;

}
