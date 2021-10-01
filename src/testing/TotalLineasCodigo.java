package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TotalLineasCodigo {
	
	String texto;

	public TotalLineasCodigo(String texto) {
		this.texto = texto;
	}
	
	public int cantTotalLineasCodigo() {
		String[] lineas = texto.split("\n");
		return lineas.length;
	}
}