
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EstrellaRotacion extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1870655270854140847L;

	PaintPanel cuadroDibujo;
	
	Random rand = new Random();
	
	public EstrellaRotacion(String title) {
		this.setTitle(title);
		this.setVisible(true); //hacer visible la ventana
		this.setResizable(true); //redimensionar la ventana
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar ventana si se presiona la X
		this.setSize(750, 750); //colocar tamaño predeterminado
		this.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		this.setMinimumSize(new Dimension(300, 400));
		this.setMaximumSize(new Dimension(1000, 750)); 
		this.setResizable(isMaximumSizeSet());
		this.add(this.dibujoMickey(), BorderLayout.CENTER);
		
		this.validate();
		this.revalidate();
		this.repaint();
	}
	
	//main que ejecuta la ventana
	public static void main(String[] args) {
		new EstrellaRotacion("Estrellas rotadas");
	}
	
	public JPanel dibujoMickey() {
		cuadroDibujo = new PaintPanel();
		
		return cuadroDibujo;
	}
	
	//clase PaintPanel
	class PaintPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		
		public PaintPanel() {
			 this.setBackground(Color.white);
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			Graphics2D g2 = (Graphics2D) g; //convertir la variable g de tipo Graphics a Graphics2D
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suavizar bordes
			
			//coordenadas de la estrella inicial
			int x = 350;
			int y = 350;
			
			//espacio en el centro de la corona
			int esp = 55; 
			
			//arreglo de coordenadas para dibujar polígono inicial
			int xs[] = {x, x-23, x-99, x-38, x-61, x, x+61, x+38, x+99, x+23};
			int ys[] = {y+esp, y+76+esp, y+76+esp, y+123+esp, y+199+esp, y+152+esp, y+199+esp, y+123+esp, y+76+esp, y+76+esp};
			
			//cantidad de estrellas a dibujar
			int cant = 65;
			
			//iteraciones
			for (int i=1; i<=cant; i++) {
				//rotar coordenadas
				g2.rotate(360/i, x, y); 

				//estrella con rotación
				g2.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))); //color random
				g2.fillPolygon(xs, ys, 10);
			}
			
		}
	}
}