package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoloComentarios {

	String texto;

	public SoloComentarios(String texto) {
		this.texto = texto;
	}

	public int cantLineasComentadas() {

		String[] lineas = texto.split("\n");
		int contador = 0;
		boolean esLineaComentada = false;

		for (String string : lineas) {
			if (matchPattern(string) == true)
				contador++;
		}

		for (String string : lineas) {
			if (esLineaComentada) {
				contador++;
				if (string.contains("*/")) {
					esLineaComentada = false;
				}
			} else {
				if (string.contains("/*")) {
					esLineaComentada = true;
					contador++;
				}
				if (string.contains("*/")) {
					esLineaComentada = false;
				}
			}
		}

		return contador;
	}

	public static boolean matchPattern(String string) {
		Pattern commentsPattern = Pattern.compile("(//.*?$)");
		Matcher commentsMatcher = commentsPattern.matcher(string);

		if (commentsMatcher.find())
			return true;

		return false;
	}
}
