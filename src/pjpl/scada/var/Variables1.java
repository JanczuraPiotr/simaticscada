package pjpl.scada.var;

import java.util.Observer;
import pjpl.s7.command.ResponseRaportFull;
import pjpl.s7.common.VarCode;
import pjpl.s7.utils.Bits;

/**
 * Wszystkie zmienne procesu 1
 *
 * @author Piotr Janczua <piotr@janczura.pl>
 */
public class Variables1 extends java.util.Observable implements pjpl.s7.var.Variables{
	private byte zmienna_1 = 0;
	private short zmienna_2 = 0;
	private int zmienna_3 = 0;
	private float zmienna_4 = 0;
	private byte I_0 = 0;
	private byte Q_0 = 0;

	private boolean I_0_0 = false;
	private boolean I_0_1 = false;
	private boolean I_0_2 = false;
	private boolean I_0_3 = false;
	private boolean I_0_4 = false;
	private boolean I_0_5 = false;
	private boolean I_0_6 = false;
	private boolean I_0_7 = false;
	private boolean Q_0_0 = false;
	private boolean Q_0_1 = false;
	private boolean Q_0_2 = false;
	private boolean Q_0_3 = false;
	private boolean Q_0_4 = false;
	private boolean Q_0_5 = false;

	public Variables1(Observer observer){
		addObserver(observer);
	}


	@Override
	public void parseResponseRaportFull(ResponseRaportFull raport) {
		setZmienna_1(raport.getZmienna1());
		setZmienna_2(raport.getZmienna2());
		setZmienna_3(raport.getZmienna3());
		setZmienna_4(raport.getZmienna4());
		setI_0(raport.getI_0());
		setQ_0(raport.getQ_0());
	}

	public void setZmienna_1(byte var){
		if( zmienna_1 != var){
			zmienna_1 = var;
			setChanged();
			notifyObservers(VarCode.ZMIENNA_1);
		}
	}
	public void setZmienna_2(short var){
		if( zmienna_2 != var){
			zmienna_2 = var;
			setChanged();
			notifyObservers(VarCode.ZMIENNA_2);
		}
	}
	public void setZmienna_3(int var){
		if( zmienna_3 != var){
			zmienna_3 = var;
			setChanged();
			notifyObservers(VarCode.ZMIENNA_3);
		}
	}
	public void setZmienna_4(float var){
		if( zmienna_4 != var){
			zmienna_4 = var;
			setChanged();
			notifyObservers(VarCode.ZMIENNA_4);
		}
	}

	public byte getZmienna_1(){
			return zmienna_1;
	}
	public short getZmienna_2(){
		return zmienna_2;
	}
	public int getZmienna_3(){
		return zmienna_3;
	}
	public float getZmienna_4(){
		return zmienna_4;
	}

	public byte getQ_0(){
		return Q_0;
	}
	public byte getI_0(){
		return I_0;
	}

	public boolean getI_0_0(){return I_0_0;}
	public boolean getI_0_1(){return I_0_1;}
	public boolean getI_0_2(){return I_0_2;}
	public boolean getI_0_3(){return I_0_3;}
	public boolean getI_0_4(){return I_0_4;}
	public boolean getI_0_5(){return I_0_5;}
	public boolean getI_0_6(){return I_0_6;}
	public boolean getI_0_7(){return I_0_7;}

	public boolean getQ_0_0(){return Q_0_0;}
	public boolean getQ_0_1(){return Q_0_1;}
	public boolean getQ_0_2(){return Q_0_2;}
	public boolean getQ_0_3(){return Q_0_3;}
	public boolean getQ_0_4(){return Q_0_4;}
	public boolean getQ_0_5(){return Q_0_5;}

	public void setQ_0_0(boolean b){
		Q_0_0 = b;
		Q_0 = Bits.on(Q_0, Bits.BIT_0);
	}
	public void setQ_0_1(boolean b){
		Q_0_1 = b;
		Q_0 = Bits.on(Q_0, Bits.BIT_1);
	}
	public void setQ_0_2(boolean b){
		Q_0_2 = b;
		Q_0 = Bits.on(Q_0, Bits.BIT_2);
	}
	public void setQ_0_3(boolean b){
		Q_0_3 = b;
		Q_0 = Bits.on(Q_0, Bits.BIT_3);
	}
	public void setQ_0_4(boolean b){
		Q_0_4 = b;
		Q_0 = Bits.on(Q_0, Bits.BIT_4);
	}
	public void setQ_0_5(boolean b){
		Q_0_5 = b;
		Q_0 = Bits.on(Q_0, Bits.BIT_5);
	}


	protected void setI_0(byte var){
		if( I_0 != var){
			setI_0_0(var);
			setI_0_1(var);
			setI_0_2(var);
			setI_0_3(var);
			setI_0_4(var);
			setI_0_5(var);
			setI_0_6(var);
			setI_0_7(var);
			I_0 = var;
		}
	}
	protected void setI_0_0(byte var ){
		if( ! Bits.compare(var, Bits.BIT_0, I_0, Bits.BIT_0) ){
			I_0_0 = Bits.get(var, Bits.BIT_0);
			setChanged();
			notifyObservers(VarCode.I_0_0);
		}
	}
	protected void setI_0_1(byte var ){
		if( ! Bits.compare(var, Bits.BIT_1, I_0, Bits.BIT_1) ){
			I_0_1 = Bits.get(var, Bits.BIT_1);
			setChanged();
			notifyObservers(VarCode.I_0_1);
		}
	}
	protected void setI_0_2(byte var ){
		if( ! Bits.compare(var, Bits.BIT_2, I_0, Bits.BIT_2) ){
			I_0_2 = Bits.get(var, Bits.BIT_2);
			setChanged();
			notifyObservers(VarCode.I_0_2);
		}
	}
	protected void setI_0_3(byte var ){
		if( ! Bits.compare(var, Bits.BIT_3, I_0, Bits.BIT_3) ){
			I_0_3 = Bits.get(var, Bits.BIT_3);
			setChanged();
			notifyObservers(VarCode.I_0_3);
		}
	}
	protected void setI_0_4(byte var ){
		if( ! Bits.compare(var, Bits.BIT_4, I_0, Bits.BIT_4) ){
			I_0_4 = Bits.get(var, Bits.BIT_4);
			setChanged();
			notifyObservers(VarCode.I_0_4);
		}
	}
	protected void setI_0_5(byte var ){
		if( ! Bits.compare(var, Bits.BIT_5, I_0, Bits.BIT_5) ){
			I_0_5 = Bits.get(var, Bits.BIT_5);
			setChanged();
			notifyObservers(VarCode.I_0_5);
		}
	}
	protected void setI_0_6(byte var ){
		if( ! Bits.compare(var, Bits.BIT_6, I_0, Bits.BIT_6) ){
			I_0_6 = Bits.get(var, Bits.BIT_6);
			setChanged();
			notifyObservers(VarCode.I_0_6);
		}
	}
	protected void setI_0_7(byte var ){
		if( ! Bits.compare(var, Bits.BIT_7, I_0, Bits.BIT_7) ){
			I_0_7 = Bits.get(var, Bits.BIT_7);
			setChanged();
			notifyObservers(VarCode.I_0_7);
		}

	}
	protected void setQ_0(byte var){
		if( Q_0 != var){
			setQ_0_0(var);
			setQ_0_1(var);
			setQ_0_2(var);
			setQ_0_3(var);
			setQ_0_4(var);
			setQ_0_5(var);
			Q_0 = var;
		}
	}
	protected void setQ_0_0(byte var ){
		if( ! Bits.compare(var, Bits.BIT_0, Q_0, Bits.BIT_0) ){
			Q_0_0 = Bits.get(var, Bits.BIT_0);
			setChanged();
			notifyObservers(VarCode.Q_0_0);
		}
	}
	protected void setQ_0_1(byte var ){
		if( ! Bits.compare(var, Bits.BIT_1, Q_0, Bits.BIT_1) ){
			Q_0_1 = Bits.get(var, Bits.BIT_1);
			setChanged();
			notifyObservers(VarCode.Q_0_1);
		}
	}
	protected void setQ_0_2(byte var ){
		if( ! Bits.compare(var, Bits.BIT_2, Q_0, Bits.BIT_2) ){
			Q_0_2 = Bits.get(var, Bits.BIT_2);
			setChanged();
			notifyObservers(VarCode.Q_0_2);
		}

	}
	protected void setQ_0_3(byte var ){
		if( ! Bits.compare(var, Bits.BIT_3, Q_0, Bits.BIT_3) ){
			Q_0_3 = Bits.get(var, Bits.BIT_3);
			setChanged();
			notifyObservers(VarCode.Q_0_3);
		}
	}
	protected void setQ_0_4(byte var ){
		if( ! Bits.compare(var, Bits.BIT_4, Q_0, Bits.BIT_4) ){
			Q_0_4 = Bits.get(var, Bits.BIT_4);
			setChanged();
			notifyObservers(VarCode.Q_0_4);
		}
	}
	protected void setQ_0_5(byte var ){
		if( ! Bits.compare(var, Bits.BIT_5, Q_0, Bits.BIT_5) ){
			Q_0_5 = Bits.get(var, Bits.BIT_5);
			setChanged();
			notifyObservers(VarCode.Q_0_5);
		}
	}



}
