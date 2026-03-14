import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.lang.Math;

class Fen extends JFrame{
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
class Pan extends JPanel{
	static int S;
	static int P=0;
	static int r=0;

	JButton gris = new JButton();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg,haut));

		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		Touche touche = new Touche();
		gris.addKeyListener(touche);
		this.add(gris);
		}
	
	public void paintComponent(Graphics g){
		if (r==0){
			g.setColor(Color.white);
			g.fillRect(0,0, this.getWidth(), this.getHeight());
			}
		int [][] L = new int [S][2];
		int [][] l = new int [S][2];
		int M = S*1000;
		for(int i=0; i<S; i++){
			if (P==0){
				L[i][0]=300+(int)(250*Math.sin(((i*2000)*3.14)/M));
				L[i][1]=300-(int)(250*Math.cos(((i*2000)*3.14)/M));
				l[i][0]=300+(int)(235*Math.sin(((i*2000)*3.14)/M));
				l[i][1]=300-(int)(235*Math.cos(((i*2000)*3.14)/M));
				}
			else if (P==1){
				L[i][0]=300+(int)(250*Math.cos(((i*2000)*3.14)/M));
				L[i][1]=300+(int)(250*Math.sin(((i*2000)*3.14)/M));
				l[i][0]=300+(int)(235*Math.cos(((i*2000)*3.14)/M));
				l[i][1]=300+(int)(235*Math.sin(((i*2000)*3.14)/M));
				}
			else if (P==2){
				L[i][0]=300+(int)(250*Math.sin(((i*2000)*3.14)/M));
				L[i][1]=300+(int)(250*Math.cos(((i*2000)*3.14)/M));
				l[i][0]=300+(int)(235*Math.sin(((i*2000)*3.14)/M));
				l[i][1]=300+(int)(235*Math.cos(((i*2000)*3.14)/M));
				}
			else if (P==3){
				L[i][0]=300-(int)(250*Math.cos(((i*2000)*3.14)/M));
				L[i][1]=300-(int)(250*Math.sin(((i*2000)*3.14)/M));
				l[i][0]=300-(int)(235*Math.cos(((i*2000)*3.14)/M));
				l[i][1]=300-(int)(235*Math.sin(((i*2000)*3.14)/M));
				}
			}
		g.setColor(Color.red);
		g.fillOval(295, 295, 10, 10);
		g.setColor(Color.black);
		g.drawOval(50, 50, 500, 500);
		
		for (int i=0; i<L.length; i++){
			g.setColor(Color.white);
			g.fillOval(L[i][0]-15, L[i][1]-15, 30, 30);
			g.setColor(Color.black);
			g.drawOval(L[i][0]-15, L[i][1]-15, 30, 30);
			g.drawString("e"+(i+1),L[i][0]-5, L[i][1]+5);
			}
		g.setColor(Color.red);
		for(int i=0; i<l.length; i++){
			for(int j=0; j<l.length; j++){
				if (i!=j) g.drawLine(l[i][0], l[i][1], l[j][0], l[j][1]);
				}
			} 
		g.drawString("r",0,8);
		}

	}
class Touche implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyReleased(KeyEvent event){}
	public void keyPressed(KeyEvent event){
			int c = event.getKeyCode();
			char k = event.getKeyChar();
			if (c==0x26) Pan.S+=1;
			if (c==0x28 && Pan.S>2) Pan.S-=1;
			if (c==0x25 && Pan.P>-1) Pan.P-=1;
			if (c==0x25 && Pan.P==-1) Pan.P=3;
			if (c==0x27 && Pan.P<4) Pan.P+=1;
			if (c==0x27 && Pan.P==4) Pan.P=0;
			if (k=='r'){
				if (Pan.r==0) Pan.r=1;
				else if (Pan.r==1) Pan.r=0;
				}
			if (k=='i') System.out.println("P = "+Pan.P);
			}
	}

public class Graphe{
	public static void main(String[] args){
		//Scanner sc = new Scanner(System.in);
		int S=2;
		//while (S<=1 || S>1000){
			//System.out.print("Nombre de sommets S = ");
			//S = sc.nextInt();
			//}
		Pan.S = S;
		Fen fen = new Fen("Fenetre", 616, 640);
		Pan pan = new Pan(600,600);
		fen.setContentPane(pan);
		while (true) pan.repaint();
		}
}