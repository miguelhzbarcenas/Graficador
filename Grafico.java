package Funcionamiento;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Grafico{

	private JTextArea textAreaCoordenadas;
	private JPanel panelPlano;
	private int escala;
	
	public Grafico(JTextArea textAreaCoordenadas, JPanel panelPlano, int escala){
		this.textAreaCoordenadas = textAreaCoordenadas;
		this.panelPlano = panelPlano;
		this.escala = escala;
	}

	public void DibujarLineaBresenham(int x1, int y1, int x2, int y2) {
		
		textAreaCoordenadas.setText(" ");
		textAreaCoordenadas.append("PUNTOS OBTENIDOS:\n");
		
		int pk;
		
		int dy = y2 - y1;
		int dx = x2 - x1;	
		
		int inc_x = 1; 
		int inc_y = 1;
		
		if(dx < 0) {
			dx = -dx;
		}
		
		if(dy < 0) {
			dy = -dy;
		}
		
		if(x2 < x1) {
			inc_x = -1;
		}
		
		if(y2 < y1) {
			inc_y = -1;
		}
		
		if(dx > dy) {
			pk = 2*dy - dx;
			
			for(int i = 0; i<=dx; i++) {
				textAreaCoordenadas.append("pk:" + pk + "    (" + x1 + (",") + y1 + ")\n");
				dibujarPunto(x1,y1);
				
				if(pk < 0) {
					x1 += inc_x;
					pk += 2*dy;
	
				} 
				else {
					x1 += inc_x;
					y1 += inc_y;
					pk += (2*dy) - (2*dx);
				}
				
			}
			
		} 
		else {
			
			pk = 2*dx-dy;
			for(int i = 0; i<=dy; i++) {
				textAreaCoordenadas.append("pk:" + pk + "    (" + x1 + (",") + y1 + ")\n");
				dibujarPunto(x1,y1);
				
				if(pk < 0) {
					y1 += inc_y;
					pk += 2*dx;
					
				} 
				else {
					y1 += inc_y;
					x1 += inc_x;
					pk += (2*dx) - (2*dy);
				}
				
			}
		 }
	}
	
	public void dibujarPunto(int coordenada_x,  int coordenada_y) {
		
		Graphics2D g=(Graphics2D) panelPlano.getGraphics(); 
        int xDigital=(int) (panelPlano.getWidth()/2+coordenada_x*escala);
        int yDigital=(int) (panelPlano.getHeight()/2-coordenada_y*escala);
        g.fillOval(xDigital-3, yDigital-3, 6, 6);
	}
	
	public void DibujarPlano() {
		
		Graphics2D g = (Graphics2D) panelPlano.getGraphics();
		int eje_cero_X=panelPlano.getWidth()/2;
		int eje_cero_Y=panelPlano.getHeight()/2;
		
		g.clearRect(0, 0, panelPlano.getWidth(), panelPlano.getHeight());
        
		g.setColor(Color.LIGHT_GRAY);
		
        for(int x = eje_cero_X; x < panelPlano.getWidth(); x += escala){
            g.drawLine(x, 0, x,panelPlano.getHeight());
            g.drawLine(eje_cero_X, 0, eje_cero_X, panelPlano.getHeight());
            eje_cero_X = eje_cero_X - escala;
        }
        
        for(int y=panelPlano.getHeight()/2;y<panelPlano.getHeight();y+=escala){
            g.drawLine(0, y, panelPlano.getWidth(),y);
            g.drawLine(0, eje_cero_Y, panelPlano.getWidth(),eje_cero_Y);
            eje_cero_Y = eje_cero_Y - escala;
        }
        
        g.setColor(Color.red);
        g.drawLine(panelPlano.getWidth()/2, 0, panelPlano.getWidth()/2,panelPlano.getHeight());
        g.drawLine(0, panelPlano.getHeight()/2, panelPlano.getWidth(),panelPlano.getHeight()/2);
        
        eje_cero_X=panelPlano.getWidth()/2;
		eje_cero_Y=panelPlano.getHeight()/2;
        
        for(int x = eje_cero_X; x < panelPlano.getWidth(); x += escala*5){
            g.drawLine(x, (panelPlano.getHeight()/2)-escala, x, (panelPlano.getHeight()/2)+escala);
            g.drawLine(eje_cero_X, (panelPlano.getHeight()/2)-escala, eje_cero_X, (panelPlano.getHeight()/2)+escala);
            eje_cero_X = eje_cero_X - escala*5;
        }
        
        for(int y = panelPlano.getHeight()/2 ; y<panelPlano.getHeight(); y += escala*5){
            g.drawLine((panelPlano.getWidth()/2)-escala, y, (panelPlano.getWidth()/2)+escala,y);
            g.drawLine((panelPlano.getWidth()/2)-escala, eje_cero_Y, (panelPlano.getWidth()/2)+escala,eje_cero_Y);
            eje_cero_Y = eje_cero_Y - escala*5;
        }

	}
}
