import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Pan extends JPanel{
	int ArcLength = 0;
	int ArcColor = 1;	
	
	static int Shape = 0;
	static int backColorChange=0;
	static int backColor = 0;
	static int Block=0;
	static int Move=0;
	static int TimeLoop=0;
	static int TimeLoopLock=0;
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));

		JButton gris = new JButton();
		Clavier touche = new Clavier();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(backColor, backColor, backColor));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		switch (ArcColor){
			case 0: g.setColor(Color.red); break;
			case 1: g.setColor(Color.blue); break;
			case 2: g.setColor(Color.green); break;
			case 3: g.setColor(Color.pink); break;
			case 4: g.setColor(Color.yellow); break;
			case 5: g.setColor(Color.magenta); break;
			case 6: g.setColor(Color.orange); break;
			case 7: ArcColor=0;
			}
		if (Shape==3) Shape=0;
		if (Shape==0){
			for(int i=0; i<10; i++) g.drawArc(360-i*2, 360-i*2, 80+i*4, 80+i*4, 270, ArcLength);
			for(int i=0; i<10; i++) g.drawArc(300-i*2, 300-i*2, 200+i*4, 200+i*4, 0, ArcLength);
			for(int i=0; i<10; i++) g.drawArc(200-i*2, 200-i*2, 400+i*4, 400+i*4, 90, ArcLength);
			for(int i=0; i<10; i++) g.drawArc(100-i*2, 100-i*2, 600+i*4, 600+i*4, 180, ArcLength);
			}
		else if (Shape==1){
			for(int i=0; i<5; i++) g.drawArc(360-i*2, 360-i*2, 80+i*4, 80+i*4, 270, ArcLength);
			for(int i=0; i<10; i++) g.drawArc(300-i*2, 300-i*2, 200+i*4, 200+i*4, 0, ArcLength);
			for(int i=0; i<15; i++) g.drawArc(200-i*2, 200-i*2, 400+i*4, 400+i*4, 90, ArcLength);
			for(int i=0; i<20; i++) g.drawArc(100-i*2, 100-i*2, 600+i*4, 600+i*4, 180, ArcLength);
			}
		else if (Shape==2){
			for(int i=0; i<20; i++) g.drawArc(360-i*2, 360-i*2, 80+i*4, 80+i*4, 270, ArcLength);
			for(int i=0; i<15; i++) g.drawArc(300-i*2, 300-i*2, 200+i*4, 200+i*4, 0, ArcLength);
			for(int i=0; i<10; i++) g.drawArc(200-i*2, 200-i*2, 400+i*4, 400+i*4, 90, ArcLength);
			for(int i=0; i<5; i++) g.drawArc(100-i*2, 100-i*2, 600+i*4, 600+i*4, 180, ArcLength);
			}
	
		if (ArcLength<360) ArcLength+=1;
		if (ArcLength==360 && Block!=1){
			ArcLength=0;
			ArcColor+=1;
			}
		if (Move==1 && TimeLoop==0){
			new Time().start();
			TimeLoop=1;
			}
		g.drawString("s", 20, 750);
		g.drawString("b", 20, 760);
		g.drawString("c", 20, 770);
		g.drawString("m", 20, 780);
			
		try{
			Thread.sleep(1);
			} catch (InterruptedException e) {}
		}
	}
class Clavier implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		char k = event.getKeyChar();
		int c = event.getKeyCode();
		if (k=='s') Pan.Shape+=1;
		if (k=='b') Pan.backColorChange=1;
		if (Pan.backColorChange==1 && c==0x26 && Pan.backColor<255) Pan.backColor+=1;
		if (Pan.backColorChange==1 && c==0x28 && Pan.backColor>0) Pan.backColor-=1;

		if (k=='c' && Pan.Block==0) Pan.Block=1;
		else if (k=='c' && Pan.Block==1) Pan.Block=0;

		if (k=='m' && Pan.Move==0) Pan.Move=1;
		else if (k=='m' && Pan.Move==1) Pan.Move=0;
		}
	public void keyReleased(KeyEvent event){
		char k = event.getKeyChar();
		int c = event.getKeyCode();
		if (k=='b') Pan.backColorChange=0;
		}
	}
class Time extends Thread{
	public void run(){
		try{
			sleep(200);
			} catch (InterruptedException e) {}
		Pan.TimeLoop=0;
		Pan.Shape+=1;
		}
	}
public class Prog{
	public static void main(String[] args){
		JFrame fen = new JFrame();
		fen.setSize(816, 840);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		fen.setLocationRelativeTo(null);
		Pan pan = new Pan(800, 800);
		fen.setContentPane(pan);
		while (true) pan.repaint();
		}
}
