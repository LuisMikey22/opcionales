import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DibujoFlor {
	
	public JFrame frame;
	public int xP, yP;
	public ArrayList<Flor> arregloDeFlores = new ArrayList<Flor>(); 
	public Random rand = new Random();
	
	public DibujoFlor(String titulo) {
		frame = new JFrame(titulo);
		frame.setVisible(true); //hacer visible la ventana
		frame.setResizable(true); //redimensionar la ventana
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cerrar ventana si se presiona la X
		frame.setSize(950, 820); //colocar tamaño predeterminado
		frame.setLocationRelativeTo(null); //colocar la ventana en el centro de la pantalla
		frame.setMinimumSize(new Dimension(500, 500));
	}
	
	public void crearFlor() {
		JPanel backgroundPnl = new JPanel();
		backgroundPnl.setBackground(Color.black);
		backgroundPnl.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60)); 
		backgroundPnl.setLayout(new BorderLayout(0, 20));
		frame.setContentPane(backgroundPnl);
		
		PaintPanel paintPnl = new PaintPanel();
		backgroundPnl.add(paintPnl, BorderLayout.CENTER);
		
		JPanel footerPnl = new JPanel();
		footerPnl.setBackground(Color.white);
		footerPnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		footerPnl.setLayout(new GridLayout(2, 4, 10, 20));
		backgroundPnl.add(footerPnl, BorderLayout.SOUTH);
		
		JLabel xPositionLbl = new JLabel("Coordenadas en X: ");
		xPositionLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		xPositionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		xPositionLbl.setOpaque(false);
		footerPnl.add(xPositionLbl);
		
		JTextField xPositionTxtFld = new JTextField("");
		xPositionTxtFld.setBackground(Color.white);
		xPositionTxtFld.setFont(new Font("Tahoma", Font.BOLD, 15));
		footerPnl.add(xPositionTxtFld);
		
		JLabel yPositionLbl = new JLabel("Coordenadas en Y: ");
		yPositionLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		yPositionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		yPositionLbl.setOpaque(false);
		footerPnl.add(yPositionLbl);
		
		JTextField yPositionTxtFld = new JTextField("");
		yPositionTxtFld.setBackground(Color.white);
		yPositionTxtFld.setFont(new Font("Tahoma", Font.BOLD, 15));
		footerPnl.add(yPositionTxtFld);
		
		footerPnl.add(Box.createHorizontalStrut(0));
		
		JButton clearBttn = new JButton("Clear");
	    clearBttn.setBackground(Color.decode("#A81B0C"));
	    clearBttn.setForeground(Color.white);
	    clearBttn.setFont(new Font("Tahoma", Font.BOLD, 15));
	    footerPnl.add(clearBttn);
	    
	    clearBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				arregloDeFlores.clear();
				paintPnl.repaint();
			}
	    });
		
		JButton drawBttn = new JButton("Draw");
	    drawBttn.setBackground(Color.decode("#2A6B01"));
	    drawBttn.setForeground(Color.white);
	    drawBttn.setFont(new Font("Tahoma", Font.BOLD, 15));
	    footerPnl.add(drawBttn);
	    
	    drawBttn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String xValue = xPositionTxtFld.getText();
				String yValue = yPositionTxtFld.getText();
				if(number(xValue) && number(yValue) && !xValue.equals("") && !yValue.equals("")){
					
					xP = Integer.parseInt(xValue);
					yP = Integer.parseInt(yValue);
					
					if(xP>=0 && yP>=0) {
						paintPnl.repaint();
						Flor flor = new Flor(xP, yP);
			        	arregloDeFlores.add(flor);
					}
				}
			}
	    });
		
		footerPnl.add(Box.createHorizontalStrut(0));
		
	    frame.setVisible(true);
	}
	
	//main que ejecuta la ventana
	public static void main(String[] args) {
		DibujoFlor ventana = new DibujoFlor("Flor en coordenadas");
		ventana.crearFlor();
	}
 	
 	//métodos para validar carácteres en textos
 	public boolean number(String text) {
        for(char character:text.toCharArray()) {
            if(!Character.isDigit(character)) { //si es diferente a un número
                return false;
            }
        }
        
        return true;
    }
 	
	//clase PaintPanel
	class PaintPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1278175958603073892L;

		public PaintPanel() {
			this.setBackground(Color.white);
		}
		
		@Override
 	    public void paintComponent(Graphics g) {
			System.out.println("Pongan sza");
			super.paintComponent(g);
	       
			Graphics2D g2 = (Graphics2D)g; 
	        
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suavizar bordes
	       
	        if(arregloDeFlores!=null) {
	    	    for(int i=0; i<arregloDeFlores.size(); i++) {  
	    	    	System.out.println(arregloDeFlores.size());
	    	    	Flor f = arregloDeFlores.get(i);
	    	    	
	    	    	f.DibujarFlor(f.getX(), f.getY(), g2);
	    	    }
	        }
		}
	}
	
	//clase flor
	class Flor{

		private int y;
		private int x;
		private Color tallo, petalos, centro;
		
		public Flor(int x, int y) {
			this.x = x;
			this.y = y;
			
			tallo = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
			petalos = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
			centro = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getX() {
			return x;  
		}

		public void setX(int x) {
			this.x = x;
		}

		public void DibujarFlor(int x, int y, Graphics2D g2) {
			//FLORES (DECORACIÓN SIN COLISIÓN)
			
			
			g2.setColor(tallo);//tallos
			g2.setStroke(new BasicStroke(10));
			g2.drawLine(x+60, y+70, x+60, y+140); 
			
			g2.setColor(petalos);//pétalos
			g2.fillOval(x+5, y, 50, 50);
			g2.fillOval(x+35, y-25, 50, 50);
			g2.fillOval(x+65, y, 50, 50);
			g2.fillOval(x+15, y+35, 50, 50);
			g2.fillOval(x+55, y+35, 50, 50);
			
			g2.setColor(centro); //centros
			g2.fillOval(x+36, y+8, 45, 45);
		}
	}
	
}
