package pjpl.scada.run;

import java.util.Properties;

/**
 */
public class ConfigDefault extends Properties{

	public ConfigDefault(){

		// Sieć.
		setProperty("simatic_server_ip"    , "192.168.1.104");
		setProperty("simatic_server_port"  , "9001");

		// Procesy
//		setProperty("process-1-id"         , "1");
//		setProperty("process-1-name"       , "Process 1");


		// katalogi
//		setProperty("dir_work"             , "d:\\tmp\\simaticserver");
//		setProperty("dir_log"              , "d:\\tmp\\simaticserver\\log");
//		setProperty("dir_dump"             , "d:\\tmp\\simaticserver\\dump");

		// format daty
		setProperty("format_date"          , "YYYY-MM-dd HH:mm:ss");
		setProperty("format_dateMS"        , "YYYY-MM-dd HH:mm:ss:SSS");
		setProperty("format_datePacked"    , "YYYYMMddHHmmss");
		setProperty("format_datePackedMS"  , "YYYYMMddHHmmssSSS");
		setProperty("format_fileName"      , "YYYYMMddHHmmssSSS");

		// czasy.
		// Jeżeli string opisujący czas nie ma przyrostka to wyraża milisekundy
		// Przyrostki określające jednostkę czasu to : d - dzień, h - godzina, m - minuta, s - sekunda
		// Jeden string może zawierać jedną jednostkę czasu czyli 1d albo 2s.
		// Nie można łączyć np : 5d12h3m
		setProperty("time_interval"        , "1000"); // Czas pomiędzy wywołaniami głównej pętli ( pętli "sterowania" )
		setProperty("time_storage"         , "5s");   // Czas przechowywania plików zanim zostaną usunięte
		setProperty("time_storageClean"    , "2s");   // Czas uruchamiania procedury czyszczenia bufora przechowującego zrzuty z PLC
	}
}
