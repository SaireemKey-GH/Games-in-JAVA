import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class Fen extends JFrame{
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLocationRelativeTo();
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		}
	}

class Pan extends JPanel{
	static int L =0;
	static int H =0;
	static int Y1 =0;
	static int X1 =0;
	static int UpY1 = 0;
	static int DownY1 = 0;
	static int UpY2 = 0;
	static int DownY2 = 0;
	static int X2 =0;
	static int Y2 = 0;
	static int Cx = 0;
	static int Cy = (int)H/2;
	static int InverX = 1;
	static int InverY = 0;
	static int RandomReturn=3;
	static int ReturnJ1=0;
	static int ReturnJ2=0;
	static int Game = 0;
	static int ScoreJ1 = 0;
	static int ScoreJ2 = 0;
	JButton Solo = new JButton("Solo");
	JButton Duo = new JButton("Duo");
	JButton gris = new JButton();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));

		Touche touche = new Touche();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);
		
		Solo.setPreferredSize(new Dimension(100, 100));
		Solo.setVisible(true);
		}
	class SoloAction implements ActionListener{
		public void actionPerformed(ActionEvent event){ 
			Pan.Game=1;
			}
		}
	class DuoAction implements ActionListener{
		public void actionPerformed(ActionEvent event){ 
			Pan.Game=2;
			}
		}
	public void paintComponent(Graphics g){
		if (Game==0){
			L = this.getWidth();
			H = this.getHeight();
			try{
				Image Titre = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Jeux Retro/Pong/Image/Title2.png"));
				g.drawImage(Titre,0,0,L,H, this);
				} catch (IOException e) {}
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("J1: touches z et x, a et q", (int)(L/8), (int)(H/5)*3);
			g.drawString("J2: fleches haut et bas, p et m", (int)(L/8)*5, (int)(H/5)*3);
			}
		else if (Game==1){
			Y1 = (int)H/2;
			X1 = (int)(L/20)*2;
			Y2 = (int)H/2;
			X2 = (int)(L/20)*18;
			Cx = (int)L/2;
			Cy = (int)H/2;
			RandomReturn=3;
			Game=2;
			InverY=0;
			}
		else if (Game==2){
			g.setColor(Color.black);
			g.fillRect(0,0,L,H);


			g.setColor(Color.white);
			g.fillRect(X1, Y1, 15, 40);
			if (UpY1==1) Y1-=2;
			if (DownY1==1) Y1+=2;
			if (UpY2==1) Y2-=2;
			if (DownY2==1) Y2+=2;
			g.fillRect(X2, Y2, 15, 40);
			g.fillRect(Cx, Cy, 10, 10);

			for(int i=0; i<31; i++){
				g.fillRect((int)(L/2)-3, (int)(H/31)+i*(int)(H/31), 6, (int)(H/62));
				}

			if (RandomReturn!=0) Cy=Cy+InverY;
			Cx=Cx+(-3)*InverX;
				
			
			if (Cx<(int)(L/2)){
				if (Cx<0 || Cy<0 || Cy>H){
					ScoreJ2+=1;
					Game=1;
					}
				for(int i=Y1; i<Y1+40; i++){
					if (Cy<=i && i<=Cy+10){
						if (X1+10==Cx || X1+11==Cx || X1+12==Cx){
							InverX=InverX*(-1);
							if (Y1<=i && i<=Y1+10 && Y1<(int)(H/2)){	
								//RandomReturn=(int)(Math.random()*2);
								//if (RandomReturn==0) InverY=0;
								//else if (RandomReturn==1) InverY=-1;
								}
							else if (Y1+30<=i && i<=Y1+40 && Y1+40>(int)(H/2)){
								//RandomReturn=(int)(Math.random()*2);
								//if (RandomReturn==0) InverY=0;
								//else if (RandomReturn==1) InverY=1;
								}
							else{
								RandomReturn=(int)(Math.random()*3);
								//if (RandomReturn==0) InverY=0;
								//else if (RandomReturn==1) InverY=1;
								//else if (RandomReturn==2) InverY=-1;
								}								
							//if (RandomReturn==1) InverY=InverY*(-1);
							if (ReturnJ1==1){
								RandomReturn=1;
								InverY=-1;
								}
							else if (ReturnJ1==2){
								RandomReturn=1;
								InverY=1;
								}
							else RandomReturn=0;
							break;
							}
						}
					}
				}
			if (Cx>(int)(L/2)){
				if (Cx>L || Cy<0 || Cy>H){
					ScoreJ1+=1;
					Game=1;
					}
				for(int i=Y2; i<Y2+40; i++){
					if (Cy<=i && i<=Cy+10){
						//if (X2==Cx+10){
						if (X2==Cx+10 || X2==Cx+11 || X2==Cx+12){
							InverX=InverX*(-1);
							if (Y2<=i && i<=Y2+10 && Y2<(int)(H/2)){
								//RandomReturn=(int)(Math.random()*2);
								//if (RandomReturn==0) InverY=0;
								//else if (RandomReturn==1) InverY=-1;
								}
							else if (Y2+30<=i && i<=Y2+40 && Y2+40>(int)(H/2)){	
								//RandomReturn=(int)(Math.random()*2);
								//if (RandomReturn==0) InverY=0;
								//else if (RandomReturn==1) InverY=1;
								}
							else{
								RandomReturn=(int)(Math.random()*3);
								//if (RandomReturn==0) InverY=0;
								//else if (RandomReturn==1) InverY=1;
								//else if (RandomReturn==2) InverY=-1;
								}

							if (ReturnJ2==1){
								RandomReturn=1;
								InverY=-1;
								}
							else if (ReturnJ2==2){
								RandomReturn=1;
								InverY=1;
								}
							else RandomReturn=0;
							break;
							}
						}
					}
				}
			g.setFont(new Font("Rubik", Font.BOLD, 100));
			g.drawString(String.valueOf(ScoreJ1), (int)(L/7)*2, (int)(H/10)*3);
			g.drawString(String.valueOf(ScoreJ2), (int)(L/7)*5, (int)(H/10)*3);
			}



		try{
			Thread.sleep(1);
			} catch (InterruptedException e){}
		}
	}
class Touche implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (c==0x26) Pan.UpY2=1;
		if (c==0x28) Pan.DownY2=1;
		if (k=='z') Pan.UpY1=1;
		if (k=='x') Pan.DownY1=1;
		
		if (k=='p') Pan.ReturnJ2=1;
		if (k=='m') Pan.ReturnJ2=2;
		if (k=='a') Pan.ReturnJ1=1;
		if (k=='q') Pan.ReturnJ1=2;

		if (Pan.Game==0) Pan.Game=1;
		}
	public void keyReleased(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (c==0x26) Pan.UpY2=0;
		if (c==0x28) Pan.DownY2=0;
		if (k=='z') Pan.UpY1=0;
		if (k=='x') Pan.DownY1=0;

		if (k=='a' || k=='q') Pan.ReturnJ1=0;
		if (k=='p' || k=='m') Pan.ReturnJ2=0;
		}
	}

public class Pong{
	public static void main(String[] args){
		//Fen fen = new Fen("Pong", 1000+16, 1000+40);
		Fen fen = new Fen("Pong", 1920+16, 1080+40);
		//Pan pan = new Pan(1000, 1000);
		Pan pan = new Pan(1920, 1080);
		fen.setContentPane(pan);
		//while (pan.Game==0);
		while (true) pan.repaint();
		}
}