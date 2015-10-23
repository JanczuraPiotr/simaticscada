package pjpl.simaticscada.common;

/**
 * Kody zmiennych procesowych
 * Dla każdego bloku danych indeksy muszą zaczynać się od 0
 */
abstract public class VarCode{
	// @todo Przenieść do tej klasy definicje dla mapy komórek w postaci tablicy a w MemMap.init() na podstawie tej tablicy budować mapę
	/**
	 * Byte
	 */
	public static final short ZMIENNA_BYTE = 0;
	/**
	 * Int
	 */
	public static final short ZMIENNA_INT = 1;
	/**
	 * DInt
	 */
	public static final short ZMIENNA_DINT = 2;
	/**
	 * Real
	 */
	public static final short ZMIENNA_REAL = 3;

	/**
	 * Byte
	 */
	public static final short IN_1  = 0;

	/**
	 * Byte
	 */
	public static final short OUT_1 = 0;
}
