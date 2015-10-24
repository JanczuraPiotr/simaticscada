package pjpl.s7.var;

import pjpl.s7.common.TypeCode;

/**
 * Informacja o mapowaniu zmiennej procesowej na sterownik i obszar pamięci.
 * Podczas konstrukcji należy wstawiać zmienne w takiej kolejności w jakiej są umieszczone w pamięci plc wraz z
 * zachowaniem kolejności sterowników.
 */
public class MemCell {

	/**
	 * Wstawia definicję zmiennej procesowej do mapy pamięci.
	 * @param name Nazwa zmiennej
	 * @param description Opis zmiennej, np w celu wyświetlania podpowiedzi w interfejsie graficznym
	 * @param pos pozycja w buforze gdzie umieszczony jest pierwszy bajt tej zmiennej.
	 *         Należy pobierać z memoryMap.pos(TypeCode.size[TypeCode.xxx])
	 * @param typ identyfikator typu zmiennej z TypeCode.xxx
	 * @param plcId identyfikator sterownika
	 * @param plc  wskaźnik do sterownika
	 */
	public MemCell(String name, String description, int pos, int typ){
		this.pos = pos;
		this.typ = typ;
		this.name = name;
		this.description = description;
		this.size = TypeCode.size[typ];
	}
	/**
	 * Wstawia definicję zmiennej procesowej do mapy pamięci.
	 * @param name Nazwa zmiennej
	 * @param pos pozycja w buforze gdzie umieszczony jest pierwszy bajt tej zmiennej. Należy pobierać z MemoryMap.pos()
	 * @param typ identyfikator typu zmiennej z TypeCode.xxx
	 * @param plcId identyfikator sterownika
	 * @param plc  wskaźnik do sterownika
	 *
	 */
	public MemCell(String name, int pos, int typ){
		this(name, "", pos, typ);
	}

	/**
	 * Pozycja pierwszego bajtu zmiennej w tablicy pamięci.
	 * @return
	 */
	public int getPos(){
		return pos;
	}
	/**
	 * Typ zmiennej według S7
	 * @return
	 */
	public int getTyp(){
		return typ;
	}
	/**
	 * Nazwa zmiennej, taka jak zapisana w sterowniku
	 * @return
	 */
	public String getName(){
		return name;
	}
	/**
	 * Dodatkowy opis zmiennej do użycia w interfejsie graficznym.
	 * @return
	 */
	public String getDescription(){
		return description;
	}
	/**
	 * Ilość bajtów zajmowanych przez zmienną.
	 * @return
	 */
	public int getSize(){
		return TypeCode.size[getTyp()];
	}

	//------------------------------------------------------------------------------
	// pozycja pierwszego bitu w obszarze pamięci. Nie jest pozycją w wewnętrznej pamięci PLC
	private int pos;
	// typ danych
	private int typ;
	private int size;
	// nazwa zmiennej
	private String name;
	// opis zmiennej
	private String description;
}
