package pjpl.s7.var;

import Moka7.S7;
import java.util.ArrayList;
import pjpl.s7.command.ResponseRaportFull;
import pjpl.s7.common.TypeCode;

/**
 * Zarządza kopiami zmiennych z SimaticServer.
 *
 * Mapuje zmienne procesowe na bloki danych sterowników obsługujących proces.
 * Jako klasa abstrakcyjna nie rozróżnia typu zbiorów danych, pamięć danych, wejścia, wyjścia. W celu specjalizowania
 * na te obszary utworzone są klasy pochodne MemoryD, MemoryI, MemoryQ
 */
abstract public class Variables {

	protected static final int PLC_MEM_ARRANGE_START = 0;
	protected static final int PLC_MEM_ARRANGE_SIZE = 1;
	protected static final int PLC_MEM_ARRANGE_ = 2;

	// PLC_MEM_MODIFIED_?? wyznacza obszar przeznaczony do wysyłki do PLC.
	// W jego skład wchodzi obszar przechowujący zmodyfikowane zmienne ale jeżeli zmiennych jest więcej a pomiędzy nimi
	// jest obszar przechowujący zmienne nie zmodyfikowane to ten obszar też zostanie wysłany. W innym przypadku należało
	// by każdą zmienną wysyłać osobno.
	// Pierwszy bajt przechowujący pierwszy bajt zmodyfikowanej zmiennej o najmniejszym kodzie.
	protected static final int PLC_MEM_MODIFIED_FIRST = 0;
	// ostatni bajt przechowujący ostatni bajt zmodyfikowanej zmiennej o największym kodzie.
	protected static final int PLC_MEM_MODIFIED_LAST = 1;
	protected static final int PLC_MEM_MODIFIED_ = 2;


	public Variables(){
		currentPos = 0;
		init();
	}

	//------------------------------------------------------------------------------
	// interfejs

	public void parseResponseRaportFull( ResponseRaportFull raport){

	}

	/**
	 * Zwraca referencję do bufora przechowującego wszystkie zmienne procesu.
	 * @return
	 */
	public byte[] getMem(){
		return mem;
	}
	/**
	 * Zwraca kopię bufora przechowującego wszystkie zmienne procesu
	 * @return
	 */
	public byte[] getMemCopy(){
		byte[] memCopy = new byte[memSize];
		System.arraycopy(mem, 0, memCopy, 0, memSize);
		return memCopy;
	}
	/**
	 * Zwraca kopię obszaru pamięci dla procesora o wskazanym identyfikatorze
	 * @param plcId
	 * @return
	 */
	public byte[] getMemCopy(int plcId){
 		byte[] memCopy = new byte[memArrange[plcId][PLC_MEM_ARRANGE_SIZE]];
		System.arraycopy(
				mem
				, memArrange[plcId][PLC_MEM_ARRANGE_START]
				, memCopy
				, 0
				, memArrange[plcId][PLC_MEM_ARRANGE_SIZE]
		);
		return memCopy;
	}
	/**
	 * Zwraca zbiór zmodyfikowanych komórek.
	 * Jeżeli nie zmodyfikowano żadnej zwraca null.
	 * @return
	 */
	public ArrayList<MemCell> getModifiedCells(){
		if( modifiedCells.size() > 0){
			return modifiedCells;
		}else{
			return null;
		}
	}
	/**
	 * Zapisuje do pamięci wewnętrznej podany buff jako nową zawartość całej pamięci.
	 * @param buff
	 */
	public void writeMem(byte[] buff){
		System.arraycopy(buff, 0, mem, 0, memSize);
	}
	/**
	 * Zapisuje do pamięci nową zawartość pamięci procesora plcId
	 * @param buff
	 * @param plcId
	 */
	public void writeMem(byte[] buff, int plcId){
		System.arraycopy(buff, 0, mem, memArrange[plcId][PLC_MEM_ARRANGE_START], memArrange[plcId][PLC_MEM_ARRANGE_SIZE]);
	}
	// @todo poprawić nazwy metod zapisu i odczytu zmiennych z mem.
	public void write(int cellCode, byte val){
		writeByte(cellCode, val);
		onUpdateCell(cells[cellCode]);
	}
	public void write(int cellCode, short val){
		writeInt(cellCode, val);
		onUpdateCell(cells[cellCode]);
	}
	public void write(int cellCode, int val){
		switch(cells[cellCode].getTyp()){
			case TypeCode.BYTE:
				writeByte(cellCode, (byte)val);
				break;
			case TypeCode.INT:
				writeInt(cellCode, (short)val);
				break;
			case TypeCode.DINT:
				writeDInt(cellCode, val);
				break;
			default:
				// @todo rzuć wyjątek
		}
		onUpdateCell(cells[cellCode]);
	}
	public void writeReal(int cellCode, float val){
		S7.SetFloatAt(mem, cells[cellCode].getPos(), val);
		onUpdateCell(cells[cellCode]);
	}
	public void write(int cellCode, double val){
		switch(cells[cellCode].getTyp()){
			case TypeCode.LREAL:
				break;
			default:
				// @todo rzuć wyjątek
		}
		onUpdateCell(cells[cellCode]);
	}
	public byte readByte(int cellCode){
		// @todo sprawdź rzeczywisty typ zmiennej i ewentualnie rzuć wyjątek
		return (byte)mem[cells[cellCode].getPos()];
	}
	public short readInt(int cellCode){
		// @todo sprawdź rzeczywisty typ zmiennej i ewentualnie rzuć wyjątek
		return (short)S7.GetShortAt(mem, cells[cellCode].getPos());
	}
	public int readDInt(int cellCode){
		// @todo sprawdź rzeczywisty typ zmiennej i ewentualnie rzuć wyjątek
		return (int)S7.GetDIntAt(mem, cells[cellCode].getPos());
	}
	public float readReal(int cellCode){
		// @todo sprawdź rzeczywisty typ zmiennej i ewentualnie rzuć wyjątek
		return (float)S7.GetFloatAt(mem, cells[cellCode].getPos());
	}
	/**
	 * Tablica bajtów opisująca zmienną o podanym kodzie
	 * @param varCode
	 * @return
	 */
	public byte[] varBuff(int varCode){
		byte[] buff = new byte[cells[varCode].getSize()];
		System.arraycopy(mem, cells[varCode].getPos(), buff, 0, cells[varCode].getSize());
		return buff;
	}
	/**
	 * Zmienia wartość zmiennej urzywając tablicy bajtów obrazującej zmienną
	 * @param varCode kod zmiennej
	 * @param varBuff buforowa postać zmiennej
	 */
	public void varBuff(int varCode, byte[] varBuff){
		System.arraycopy(varBuff, 0, mem, cells[varCode].getPos(), cells[varCode].getSize());
		onUpdateCell(cells[varCode]);
	}

	public int getSize(){
		return memSize;
	}
	public int getPlcMemSize(int plcId){
		return memArrange[plcId][PLC_MEM_ARRANGE_SIZE];
	}

	// interfejs - koniec
	//------------------------------------------------------------------------------

	//------------------------------------------------------------------------------
	// metody chronione

	/**
	 * Modyfikuje zmienną PLC bajtową 8 bitów
 	 * @param cellCode
	 * @param val
	 */
	private void writeByte(int cellCode, byte val){
		mem[cells[cellCode].getPos()] = val;
	}
	/**
	 * Modyfikuje zmienną PLC Short 16 bitów ze znakiem
	 * @param cellCode
	 * @param val
	 */
	private void writeInt(int cellCode, short val){
		S7.SetIntAt(mem, cells[cellCode].getPos(), val);
	}
	/**
	 * Modyfikuje zmienną PLC DInt 32 bity ze znakiem
	 * @param cellCode
	 * @param val
	 */
	private void writeDInt(int cellCode, int val){
		S7.SetDIntAt(mem, cells[cellCode].getPos(), val);
	}

	protected void addCell(int cellCode, MemCell cell){
		memSize += cell.getSize();
	}
	/**
	 * Kod obszaru obsługiwanego przez obiekt.
	 * Typ tego obszaru "zakodowany" jest klasą obiektu ale może być konieczne podanie wartości liczbowej oznaczającej
	 * obszar pamięci obowiązujący w bibliotece Moka7
	 * @return
	 */
	abstract protected int areaCode();
	/**
	 * Kod obszaru wewnątrz D.
	 * Na razie nie wiem jak się tym posłużyć i wszędzie zwracam 0;
	 * @return
	 */
	abstract protected int dbNumber();
	/**
	 * Musi być napisane tak by do memmoryMap wstawiać kolejne obiekty opisujące zmienne procesu.
	 * Wypełnić za pomocą wywołania metod addCell dla każdej dodawanej komórki
	 */
	abstract protected void addCells();
	/**
	 * Zwraca pozycję w bloku pamięci na którym umieszczona będzie zmienna, domyślnie wypełniają tablice w sposób ciągły.
	 * @param size Rozmiar obecnie wstawianej zmiennej
	 * @return
	 */
	final protected int pos(int size){
		int pos = currentPos;
		currentPos += size;
		return pos;
	}

	//------------------------------------------------------------------------------
	// private

	/**
	 * Czyści informację o rozmieszczeniu pamięci sterowników plcs w buforze danych procesowych.
	 */
	private void clearMemArrange(){
		for( int[] plci : memArrange ){
			plci[PLC_MEM_ARRANGE_START] = 0;
			plci[PLC_MEM_ARRANGE_SIZE] = 0;
		}
	}
	/**
	 * Wszystkie operacje wymagane do poprawnego uruchomienia obiektu
	 */
	private void init(){
		modifiedCells = new ArrayList<>();
		addCells();
		initArrCells();
	}
	/**
	 * Na podstawie tymczasowej mapy w której zostały zebrane zmienne podczas tworzenia obiektu, tworzona jest
	 * tablica cells przechowująca zmienne w postaci tablicy dla szybszego działania. W czasie normalnej pracy
	 * obiektu dostęp do zmiennych odbywa się tylko za pomocą tablicy cells.
	 */
	private void initArrCells(){
		cells = new MemCell[cellsCount];
	}
	/**
	 * Wywoływana gdy na zmiennej wykonano write.
	 * Metoda sprawdza czy wartość zmiennej uległa rzeczywistej zmianie i jeżeli tak jest to dodaje ją do listy zmiennych
	 * które trzeba wysłać do PLC.
	 * @param cell
	 */
	private void onUpdateCell(MemCell cell){
		// @todo Do modifiedCell wstawić tylko te zmienne których wartość jest inna niż zmiennej zapisanej w mem
		modifiedCells.add(cell);
	}

	//------------------------------------------------------------------------------
	// atrybuty chronione

	// obszar danych procesowych
	protected byte[] mem;
	// Licznik ilości bajtów potrzebnych do obsługi zmiennych mapowanych w tym obiekcie.
	protected int memSize = 0;
	// Rozmieszczenie pamięci sterowników "plcs" w buforze danych procesowych "mem"
	// memArrange[plcId][PLC_MEM_ARRANGE_START] index pierwszej komórki pamięci plcId w mem
	// memArrange[plcId][PLC_MEM_ARRANGE_SIZE] rozmiar pamięci w mem przynależnej procesorowi plcId
	protected int[][] memArrange;


	//------------------------------------------------------------------------------
	// atrybuty prywatne

	// Ilość zmiennych zebranych przez metodę addCell()
	private int cellsCount = 0;
	// Tabela przechowująca wskaźniki do zmiennych procesowych.
	private MemCell[] cells;
	private ArrayList<MemCell> modifiedCells;

	// Aktualna pozycja pierwszego bajtu dla następnej zmiennej.
	private int currentPos;

}
/**
 *
 */