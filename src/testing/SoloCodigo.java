package testing;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoloCodigo {

	String texto;

	public SoloCodigo(String texto) {
		this.texto = texto;
	}

	public int cantLineasSoloCodigo() {
		texto = texto.replaceAll("\t","");
				
		String[] totalLineas = texto.split("\n");

		int lineasComentadas = 0;
		int bandera = 0;
		int banderaMismaLinea = 0;
		for (String string : totalLineas) {
			if (matchPattern(string) == true)
				lineasComentadas++;

			if (matchPattern2(string) == true && bandera == 0) {
				bandera = 1;
				banderaMismaLinea = 1;
				lineasComentadas++;
			}

			if (banderaMismaLinea == 1) {

				if (matchPattern3(string) == true) {
					bandera = 0;
				}
				banderaMismaLinea = 0;

			} else if (bandera == 1) {
				if (matchPattern3(string) == true) {
					bandera = 0;
				}
				lineasComentadas++;
			}
		}
		
		return totalLineas.length - lineasComentadas;
	}

	public boolean matchPattern(String string) {
		Pattern regex = Pattern.compile("^(\\/\\/{1,})");

		Matcher mat = regex.matcher(string);

		if (mat.find())
			return true;

		return false;
	}

	public boolean matchPattern2(String string) {
		Pattern regex = Pattern.compile("(\\/\\*)");

		Matcher mat = regex.matcher(string);

		if (mat.find())
			return true;

		return false;
	}

	public boolean matchPattern3(String string) {
		Pattern regex = Pattern.compile("(\\*\\/)$");

		Matcher mat = regex.matcher(string);

		if (mat.find())
			return true;

		return false;
	}

}
