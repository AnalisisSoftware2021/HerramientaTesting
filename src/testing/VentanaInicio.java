package testing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class VentanaInicio extends JFrame {

	private JPanel contentPane;
	private final JTextArea textArea;
	private final JScrollPane scrollPane;
	private final JButton btnElegir;
	private static final int NUMERO_COLUMNAS = 80;
	private static final int NUMERO_FILAS = 25;
	private ArrayList<String> metodos;
	private String[] nombreMetodos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		this.setTitle("Inserte su codigo aqui");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textArea = new JTextArea(NUMERO_FILAS, NUMERO_COLUMNAS);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(5, 5, 875, 456);

		btnElegir = new JButton("Elegir Metodo");
		btnElegir.setBounds(400, 500, 130, 30);
		btnElegir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				separarMetodos();
				separarNombreMetodos();
				
				if(nombreMetodos.length == 0) {
					JOptionPane.showMessageDialog(null,"Debe ingresar un metodo de java valido");
					return;
				}
				
				Object metodoElegido = JOptionPane.showInputDialog(null, "Selecciona un metodo para analizar",
						"Metodos", JOptionPane.QUESTION_MESSAGE, null, nombreMetodos, nombreMetodos[0]);

				int numeroMetodo = devuelveNumeroMetodoElegido(metodoElegido);
				
				String metodoSeleccionado = metodos.get(numeroMetodo);
				
				McCabe mc = new McCabe(metodoSeleccionado);
				
				SoloCodigo sc = new SoloCodigo(metodoSeleccionado);
				
				FanIn fanIn = new FanIn(textArea.getText());
				
				FanOut fanOut = new FanOut(textArea.getText());
				
				TotalLineasCodigo totalLineasCodigo = new TotalLineasCodigo(metodoSeleccionado);
				
				LineasEnBlanco lineasEnBlanco = new LineasEnBlanco(metodoSeleccionado);
				
				SoloComentarios soloComentarios = new SoloComentarios(metodoSeleccionado);		

				Halstead halstead = new Halstead(metodoSeleccionado);
				
				int totalLineas = totalLineasCodigo.cantTotalLineasCodigo();
				
				String resultados = 
						"Complejidad Ciclomatica: " + (mc.matchPattern() + 1) +
						"\nSolo codigo: " + sc.cantLineasSoloCodigo() +
						"\nTotal de lineas: " + totalLineas +
						"\nLineas comentadas: " + soloComentarios.cantLineasComentadas() + 
						"\nLineas en blanco: " + lineasEnBlanco.calcular() + 
						"\nPorcentaje de lineas comentadas: " + (((float)soloComentarios.cantLineasComentadas()/(float)sc.cantLineasSoloCodigo())*100)+ "%" +
						"\nFanIn: " + fanIn.calcularFanIn((String)metodoElegido) +
						"\nFanOut: " + fanOut.calcularFanOut((String)metodoElegido) +
						"\nLongitud: " + halstead.getLongitud() +
						"\nVolumen: " + halstead.getVolumen()
						;
				
				JOptionPane.showMessageDialog(null,resultados, "ANALISIS DEL METODO \"" + metodoElegido + "\"", JOptionPane.INFORMATION_MESSAGE);

				
			}

		});

		contentPane.setLayout(null);

		this.getContentPane().add(scrollPane);
		this.add(btnElegir);
		this.pack();
		this.setSize(900, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void separarMetodos() {
		this.metodos = new ArrayList<String>();
		String codigo = textArea.getText();
		String metodo = "";
		int contador = 0;
		int inicio = 0;
		int posicion = 0;
		int finMetodo = 0;
		for (int i = 0; i < codigo.length(); i++) {
			if (codigo.charAt(i) == '{') {
				contador++;
			}
			if (codigo.charAt(i) == '}') {
				finMetodo = 1;
				contador--;
			}
			if (finMetodo == 1 && contador == 0) {
				posicion = i + 1;
				metodo = codigo.substring(inicio, posicion);
				metodo = metodo.replaceAll("^(\n{1,})","");
				this.metodos.add(metodo);
				inicio = posicion;
				finMetodo = 0;
			}
		}
	}

	private void separarNombreMetodos() {
		nombreMetodos = new String[metodos.size()];
		int contador = 0;
		int espacio = 0;
		for (String string : metodos) {
			int caracter = 0;
			while (string.charAt(caracter) != '(') {
				if(string.charAt(caracter) == ' ') {
					espacio = caracter;
				}
				caracter++;
			}
			nombreMetodos[contador++] = string.substring(espacio + 1, caracter);

		}
	}
	
	private int devuelveNumeroMetodoElegido(Object metodoElegido) {
		int numeroMetodo = 0;
		
		for (int i = 0; i < nombreMetodos.length; i++) {
			if(nombreMetodos[i] == metodoElegido)
				numeroMetodo = i;
		}
		
		return numeroMetodo;
	}
}
