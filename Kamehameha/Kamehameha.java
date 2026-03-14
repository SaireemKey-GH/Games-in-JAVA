import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;


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
	int p1x=100;
	int p1y=410;
	static int TouchedExplosion=0;
	static int Time=0;
	static int TimeOn=0;
	int Aura=10;
	int AuraLoop=0;
	static int KFight=0;
	static int K1x=0;
	static int K2x=0;
	static int K1oval=0;
	static int K1ovalLoop=0;
	static int keyflag=0;
	static JButton again = new JButton("Recommencer ?");
	static JLabel win = new JLabel("You won !");
	static JLabel lost = new JLabel("You lost !");
	JButton gris = new JButton();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		Font font = new Font("Arial", Font.BOLD, 30);
		
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisAgain Sagain = new SourisAgain();
		again.setPreferredSize(new Dimension(200, 120));
		again.setVisible(false);
		again.addMouseListener(Sagain);
		this.add(again);

		win.setPreferredSize(new Dimension(250, 120));
		win.setVisible(false);
		win.setFont(font);
		this.add(win);

		lost.setPreferredSize(new Dimension(250, 120));
		lost.setVisible(false);
		lost.setFont(font);
		this.add(lost);
		
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(34, 145, 255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		for(int i=0; i<20; i++){
			g.setColor(new Color(160-i*5, 100-i*5, 0));
			g.fillRect(0, i*10+6*this.getHeight()/8, this.getWidth(), 10);
			}
		//g.drawString(String.valueOf(Time), 600, 20);
		if (TimeOn==0){
			TimeOn=1;
			new TimeLoop().start();
			}
		if (Time<=3){
			try{
				Image p1Sol = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 1 - sol.png"));
				g.drawImage(p1Sol, p1x, p1y, 120, 190, this);
				Image p2Sol = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 2 - sol.png"));
				g.drawImage(p2Sol, this.getWidth()-200, p1y, 120, 190, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (3<Time && Time<=7){
			try{
				Image p1SolFocus = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 1 - sol focus.png"));
				g.drawImage(p1SolFocus, p1x, p1y, 120, 190, this);
				Image p2SolFocus = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 2 - sol focus.png"));
				g.drawImage(p2SolFocus, this.getWidth()-200, p1y, 120, 190, this);
				} catch (IOException e) {e.printStackTrace();}
			g.setColor(Color.blue);
			for(int i=0; i<Aura; i++) g.drawOval(p1x-Aura-i, p1y-Aura-i, 120+2*Aura+2*i, 190+2*Aura+2*i);
			g.setColor(Color.magenta);
			for(int i=0; i<Aura; i++) g.drawOval(this.getWidth()-200-Aura-i, p1y-Aura-i, 120+2*Aura+2*i, 190+2*Aura+2*i);
			if (AuraLoop<20) Aura+=1;
			if (AuraLoop>20) Aura-=1;
			if (AuraLoop==39) AuraLoop=0;
			AuraLoop+=1;
			//if (Time==7) Time=4;
			}
		if (7<Time && Time<=9){
			try{
				Image p1SolSaiyan = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 1 - sol Saiyan.png"));
				g.drawImage(p1SolSaiyan, p1x, p1y-30, 120, 220, this);
				Image p2SolSaiyan = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 2 - sol Saiyan.png"));
				g.drawImage(p2SolSaiyan, this.getWidth()-200, p1y-30, 120, 220, this);
				} catch (IOException e) {e.printStackTrace();}
			g.setColor(Color.blue);
			for(int i=0; i<Aura; i++) g.drawOval(p1x-Aura-i, p1y-Aura-i, 120+2*Aura+2*i, 190+2*Aura+2*i);
			g.setColor(Color.magenta);
			for(int i=0; i<Aura; i++) g.drawOval(this.getWidth()-200-Aura-i, p1y-Aura-i, 120+2*Aura+2*i, 190+2*Aura+2*i);
			Aura+=(int)Aura/3;
			}
		if (9<Time && Time<=13){
			try{
				Image p1SaiyanFly = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 1 - Saiyan fly.png"));
				g.drawImage(p1SaiyanFly, p1x, p1y-30, 120, 220, this);
				Image p2SaiyanFly = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 2 - Saiyan fly.png"));
				g.drawImage(p2SaiyanFly, this.getWidth()-200, p1y-30, 120, 220, this);
				} catch (IOException e) {e.printStackTrace();}
			 if (p1y>150) p1y-=1;
			}
		if (13<=Time && Time<=14){
			try{
				Image p1SaiyanFlyStand = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 1 - Saiyan fly standby.png"));
				g.drawImage(p1SaiyanFlyStand, p1x, p1y-30, 120, 220, this);
				Image p2SaiyanFlyStand = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 2 - Saiyan fly standby.png"));
				g.drawImage(p2SaiyanFlyStand, this.getWidth()-200, p1y-30, 120, 220, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (14<Time && Time<=23){
			try{
				Image p1SaiyanFlyFocus = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 1 - Saiyan fly focus.png"));
				g.drawImage(p1SaiyanFlyFocus, p1x, p1y-30, 120, 220, this);
				Image p2SaiyanFlyFocus = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 2 - Saiyan fly focus.png"));
				g.drawImage(p2SaiyanFlyFocus, this.getWidth()-200, p1y-30, 120, 220, this);
				} catch (IOException e) {e.printStackTrace();}
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			if (Time==19) g.drawString("KA", (this.getWidth()/2)-100, 200);
			if (Time==20) g.drawString("KAME", (this.getWidth()/2)-100, 200);
			if (Time==21) g.drawString("KAMEHA", (this.getWidth()/2)-100, 200);
			if (Time==22) g.drawString("KAMEHAME", (this.getWidth()/2)-100, 200);
			if (Time==23) g.drawString("KAMEHAMEHA", (this.getWidth()/2)-100, 200);
			g.setColor(Color.cyan);
			g.fillOval(p1x-K1oval+5, p1y+90-K1oval, 22+K1oval, 22+K1oval);
			g.setColor(Color.magenta);
			g.fillOval(this.getWidth()-77-K1oval, p1y+90-K1oval, 22+K1oval, 22+K1oval);
			if (K1ovalLoop<=20) K1oval+=1;
			if (K1ovalLoop>20) K1oval-=1;
			if (K1ovalLoop==39) K1ovalLoop=0;
			K1ovalLoop+=1;
			}
		if (Time>23){
			try{
				Image p1SaiyanFlyK = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 1 - Saiyan fly kamehameha.png"));
				if (keyflag!=2) g.drawImage(p1SaiyanFlyK, p1x, p1y-30, 120, 220, this);
				Image p2SaiyanFlyK = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Kamehameha/Images/Perso 2 - Saiyan fly kamehameha.png"));
				if (keyflag!=1) g.drawImage(p2SaiyanFlyK, this.getWidth()-200, p1y-30, 120, 220, this);
				} catch (IOException e) {e.printStackTrace();}
			g.setColor(Color.cyan);
			g.fillRoundRect(p1x+120, p1y-30+80, K1x, 60, 60, 60);
			
			g.setColor(Color.magenta);
			g.fillRoundRect(this.getWidth()-200-K2x, p1y-30+80, K2x, 60, 60, 60);
			if (keyflag==0){
				K1x+=2;
				K2x+=2;
				}
			int K2touche=this.getWidth()-200-K2x;
			int K1touche=p1x+120+K1x;
			if (K1touche==K2touche){
				K1x-=4;
				KFight=1;
				}
			else KFight=0;
			if (K1touche>=this.getWidth()-190) keyflag=1;
			if (K2touche<=p1x+110) keyflag=2;


			if (keyflag==1){
				K1x+=3;
				for(int i=0; i<50; i++){
					if (i%4==0) g.setColor(Color.cyan);
					else g.setColor(Color.magenta);
					g.drawOval(this.getWidth()-200+60-i-TouchedExplosion, p1y-30+110-i-TouchedExplosion, TouchedExplosion*2+i*2, TouchedExplosion*2+i*2);
					}
				}
			
			if (keyflag==2){
				K2x+=2;
				for(int i=0; i<50; i++){
					if (i%4==0) g.setColor(Color.cyan);
					else g.setColor(Color.magenta);
					g.drawOval(p1x+60-i-TouchedExplosion, p1y-30+110-i-TouchedExplosion, TouchedExplosion*2+i*2, TouchedExplosion*2+i*2);
					}
				}
			
			if (keyflag!=0) TouchedExplosion+=3;
			}
		try{
			Thread.sleep(1);
			} catch (InterruptedException e) {}
		g.drawString("Touche 'k' and 'm'", 20, 20);
		}
	}
class ToucheListener implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){}
	public void keyReleased(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (Pan.keyflag==0){
			if ((c==0x20 || k=='k' || k=='m') && Pan.KFight==1){
				Pan.K1x+=26;
				Pan.K2x-=26;
				}
			}
		}
	}
class TimeLoop extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e) {}
		Pan.Time+=1;
		Pan.TimeOn=0;
		}
	}
class SourisAgain implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1){
			Pan.K1x=0;
			Pan.K2x=0;
			Pan.K1oval=0;
			Pan.K1ovalLoop=0;
			Pan.Time=14;
			Pan.keyflag=0;
			Pan.TouchedExplosion=0;
			Pan.win.setVisible(false);
			Pan.lost.setVisible(false);
			Pan.again.setVisible(false);
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}


public class Kamehameha{
	public static void main(String[] args){
		Fen fen = new Fen("Fenetre", 1416, 840);
		Pan pan = new Pan(1400, 800);
		fen.setContentPane(pan);
		while (true){
			pan.repaint();
			if (pan.keyflag==1){
				pan.win.setVisible(true);
				pan.again.setVisible(true);
				}
			if (pan.keyflag==2){
				pan.lost.setVisible(true);
				pan.again.setVisible(true);
				}
			}
		}
}