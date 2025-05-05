
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MickeyMouse extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1870655270854140847L;

	PaintPanel cuadroDibujo;
	
	public MickeyMouse(String title) {
		this.setTitle(title);
		this.setVisible(true); //hacer visible la ventana
		this.setResizable(true); //redimensionar la ventana
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar ventana si se presiona la X
		this.setSize(500, 500); //colocar tamaño predeterminado
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
		new MickeyMouse("Mickey Mouse");
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
	
			//manos
			g2.setColor(Color.black); //borde
			g2.fillOval(168, 298, 44, 64);
			g2.fillOval(288, 298, 44, 64);
			g2.setColor(Color.white);
			g2.fillOval(170, 300, 40, 60);
			g2.fillOval(290, 300, 40, 60);
			
			g2.setColor(Color.black);	
			
			//orejas
			g2.fillOval(120, 80, 100, 100);
			g2.fillOval(280, 80, 100, 100);
			
			//cabeza
			g2.fillOval(140, 150, 220, 180);
			g2.fillOval(135, 172, 230, 180);
			
			g2.setColor(Color.decode("#FACDB0"));
		
			//frente 
			g2.fillOval(160, 180, 110, 140);
			g2.fillOval(230, 180, 110, 140);
			
			//barbilla
			g2.fillOval(196, 316, 110, 30);
			
			//mejillas
			g2.rotate(-45, 135, 300);
			g2.fillOval(135, 300, 80, 120);
			g2.rotate(90, 290, 423);
			g2.fillOval(290, 423, 80, 120);

			g2.setColor(Color.black);
			
			//ojos
			g2.rotate(10, 280, 490);
			g2.fillOval(260, 500, 30, 20);
			g2.fillOval(260, 440, 30, 20);
			
			//nariz
			g2.rotate(0, 240, 460);
			g2.fillOval(240, 465, 20, 30);
			
		}
	}
}
