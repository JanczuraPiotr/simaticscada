package pjpl.s7.var;

import pjpl.s7.command.ResponseRaportFull;

/**
 * Zarządza kopiami zmiennych z SimaticServer.
 *
 */
public interface Variables {
	public abstract void parseResponseRaportFull( ResponseRaportFull raport);
}