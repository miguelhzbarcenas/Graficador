package Funcionamiento;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Ventana extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private int escala = 10; //Cuantos pixeles representan un punto en cada eje del plano dibujado
	
	private JTextField cajaX1, cajaY1, cajaX2, cajaY2;
	
	private JPanel panelPrincipal = new JPanel();
	private JPanel panelPlano = new JPanel();
	
	private JTextArea area = new JTextArea();
	
	private JButton botonGraficar = new JButton("Graficar");
	
	private Grafico grafico = new Grafico(area, panelPlano, escala);
	
	//Genera la ventana con todos sus componentes
	public Ventana() {
		this.setSize(700,700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Graficador");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		Componentes();

		this.setVisible(true);
		
		grafico.DibujarPlano();
		area.setText("");
		area.append("Solo números enteros\nentre -25 y 25");
	}
	
	private void Componentes() {
		ColocarPaneles();
		ColocarEtiquetas();
		ColocarCajasDeTexto();
		ColocarBotones();
		ColocarCoordenadas();
	}
	
	private void ColocarPaneles() {
		panelPrincipal.setLayout(null);
		this.getContentPane().add(panelPrincipal);	
		
		panelPlano.setLayout(null);
		panelPlano.setBounds(140,10,540,645);
		panelPlano.setBackground(Color.white);
		panelPrincipal.add(panelPlano);
	}
	
	private void ColocarEtiquetas() {
		JLabel etiquetaX1 = new JLabel("x1:");
		etiquetaX1.setBounds(10,10,25,20);
		etiquetaX1.setForeground(Color.blue);
		etiquetaX1.setFont(new Font("Default", Font.PLAIN, 15));
		panelPrincipal.add(etiquetaX1);
		
		JLabel etiquetaY1 = new JLabel("y1:");
		etiquetaY1.setBounds(75,10,25,20);
		etiquetaY1.setForeground(Color.blue);
		etiquetaY1.setFont(new Font("Default", Font.PLAIN, 15));
		panelPrincipal.add(etiquetaY1);
		
		JLabel etiquetaX2 = new JLabel("x2:");
		etiquetaX2.setBounds(10,35,25,20);
		etiquetaX2.setForeground(Color.blue);
		etiquetaX2.setFont(new Font("Default", Font.PLAIN, 15));
		panelPrincipal.add(etiquetaX2);
		
		JLabel etiquetaY2 = new JLabel("y2:");
		etiquetaY2.setBounds(75,35,25,20);
		etiquetaY2.setForeground(Color.blue);
		etiquetaY2.setFont(new Font("Default", Font.PLAIN, 15));
		panelPrincipal.add(etiquetaY2);
	}
	
	private void ColocarCajasDeTexto() {
		cajaX1 = new JTextField();
		cajaX1.setBounds(35, 10, 30, 25);
		panelPrincipal.add(cajaX1);
		
		cajaY1 = new JTextField();
		cajaY1.setBounds(100, 10, 30, 25);
		panelPrincipal.add(cajaY1);
		
		
		cajaX2 = new JTextField();
		cajaX2.setBounds(35, 35, 30, 25);
		panelPrincipal.add(cajaX2);
		
		cajaY2 = new JTextField();
		cajaY2.setBounds(100, 35, 30, 25);
//		cajaY2.setText("y2");
		panelPrincipal.add(cajaY2);
	}
	
	private void ColocarBotones() {
		botonGraficar.setBounds(10,60,120,25);
		panelPrincipal.add(botonGraficar);
	}
	
	private void ColocarCoordenadas() {
		JScrollPane scroll = new JScrollPane(area);
		scroll.setBounds(10,90,120,565);
		area.setBackground(null);
		area.setEditable(false);
		area.setFont(new Font("Default", Font.PLAIN, 11));
//		GraficarLineaBresenham();
		panelPrincipal.add(scroll);
	}
	
	public void ClicBotonGraficar() {
		MouseListener clic = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				try {
					
					int x1 = Integer.parseInt(cajaX1.getText());
					int y1 = Integer.parseInt(cajaY1.getText());
					int x2 = Integer.parseInt(cajaX2.getText());
					int y2 = Integer.parseInt(cajaY2.getText());
					
					boolean menor_que_25 = x1 >= -25 && x1 <= 25 &&
							               x2 >= -25 && x2 <= 25 &&
							               y1 >= -25 && y1 <= 25 &&
							               y2 >= -25 && y2 <= 25;
					
					if(menor_que_25) {
						
						grafico.DibujarPlano();
						grafico.DibujarLineaBresenham(x1, y1, x2, y2);
						
					} else {
						
						throw new NumberFormatException();
					}
					
				} catch(Exception NumberFormatException){
					
					area.setText(" ");
					area.append("Solo números enteros\nentre -25 y 25");
					
				}

			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		botonGraficar.addMouseListener(clic);
	}
	
}
