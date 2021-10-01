package testing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class McCabe {

	String texto;
	
	public McCabe(String texto) {
		this.texto = texto;
	}
	
	public long matchPattern() {
//		Pattern regexIf = Pattern.compile("(?<!((\\/\\/)|(\\/\\*)))if\\(", Pattern.DOTALL);
//		Pattern regexWhile = Pattern.compile("(?<!((\\/\\/)|(\\/\\*)))while\\(", Pattern.DOTALL);
//		Pattern regexAndOr = Pattern.compile("((?!\\(.+)(&&|(\\|\\|))(?!<.+)(?!\\)))", Pattern.DOTALL);
//		Pattern regexFor = Pattern.compile("(?<!((\\/\\/)|(\\/\\*)))for\\(", Pattern.DOTALL);
		
		Pattern regex = Pattern.compile("(if|while|for)[ ]{0,}\\(|((&&|\\|\\|)(?=.*))");
		
		Matcher match = regex.matcher(texto);
		
		long coincidencias = 0;
		coincidencias = match.results().count();
		return coincidencias;
	}
//		Matcher matIf = regexIf.matcher(texto);
//		Matcher matWhile = regexWhile.matcher(texto);
//		Matcher matAndOr = regexAndOr.matcher(texto);
//		Matcher matFor = regexFor.matcher(texto);
		
		
		
//		coincidencias = matIf.results().count();
//		coincidencias += matWhile.results().count();
//		coincidencias += matAndOr.results().count();
//		coincidencias += matFor.results().count();
}

