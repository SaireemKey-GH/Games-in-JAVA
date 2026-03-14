import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.sound.sampled.*;
import java.io.*;

class Fen extends JFrame{
	static int x=380;
	static int y=400;
	static int sens=0;
	static int Motion=0;
	static int Lifey=-180;
	static int AttackHitBoxX=0;
	static int AttackHitBoxY=0;
	static int arcStart=60;
	static int Attack=0;
	static int AttackUp=0;
	static int AttackLeft=0;
	static int AttackRight=0;
	static int AttackDown=0;
	static int AttackOn=0;
	static int AttackTour=0;
	static int Room=0;
	static char Orientation;
	static int ChangeRoom=0;
	static int Interaction=0;
	static int Emeraude=0;
	static int Rubis=0;
	static int Saphir=0;
	static int TextTime=0;
	static int TextTimeOn=0;
	static int PassCinematic=0;
	static int GameOver=0;
	static int GameOverColor=0;
	static int Color255=255;
	static int Color128=128;
	static int Game=0;
	static int keyflag=0;
	static JButton gris = new JButton();
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);
		

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public static boolean Colision(int X, int Y, int width, int height, int Sens){
		int Cx=0;
		int Cy=0;
		for(int i=x; i<x+40; i++){
			if (X<i && i<X+width){
				Cx=1;
				break;
				}
			}
		for(int i=y; i<y+40; i++){
			if (Y<i && i<Y+height){
				Cy=1;
				break;
				}
			}
		return Cx==1 && Cy==1 && Sens==sens;
		}
	public static boolean ColisionMob(int ObsX, int ObsY, int ObsWidth, int ObsHeight, int X, int Y, int width, int height){
		int Cx=0;
		int Cy=0;
		for(int i=X; i<X+width; i++){
			if (ObsX<i && i<ObsX+ObsWidth){
				Cx=1;
				break;
				}
			}
		for(int i=Y; i<Y+height; i++){
			if (ObsY<i && i<ObsY+ObsHeight){
				Cy=1;
				break;
				}
			}
		return Cx==1 && Cy==1;
		}
	public static boolean Presence(int X, int Y, int width, int height){
		int Cx=0;
		int Cy=0;
		for(int i=x; i<x+40; i++){
			if (X<=i && i<=X+width){
				Cx=1;
				break;
				}
			}
		for(int i=y; i<y+40; i++){
			if (Y<=i && i<=Y+height){
				Cy=1;
				break;
				}
			}
		return Cx==1 && Cy==1;
		}
	public static boolean MobHitBox(int X, int Y, int width, int height){
		int Hx=0;
		int Hy=0;
		if (AttackLeft==1){
			for(int i=X; i<X+width; i++){
				if (Fen.x-40<=i && i<=x-25){
					Hx=1;
					break;
					}
				}
			for(int i=Y; i<Y+height; i++){
				if (Fen.y+45-Fen.AttackHitBoxY<=i && i<=Fen.y+60-Fen.AttackHitBoxY){
					Hy=1;
					break;
					}
				}
			}
		if (AttackRight==1){
			for(int i=X; i<X+width; i++){
				if (Fen.x+60<=i && i<=x+75){
					Hx=1;
					break;
					}
				}
			for(int i=Y; i<Y+height; i++){
				if (Fen.y-10+Fen.AttackHitBoxY<=i && i<=Fen.y+5+Fen.AttackHitBoxY){
					Hy=1;
					break;
					}
				}
			}
		if (AttackUp==1){
			for(int i=X; i<X+width; i++){
				if (Fen.x-15+Fen.AttackHitBoxX<=i && i<=Fen.x+Fen.AttackHitBoxX){
					Hx=1;
					break;
					}
				}
			for(int i=Y; i<Y+height; i++){
				if (Fen.y-40<=i && i<=y-25){
					Hy=1;
					break;
					}
				}
			}
		if (AttackDown==1){
			for(int i=X; i<X+width; i++){
				if (Fen.x+45-Fen.AttackHitBoxX<=i && i<=Fen.x+60-Fen.AttackHitBoxX){
					Hx=1;
					break;
					}
				}
			for(int i=Y; i<Y+height; i++){
				if (Fen.y+60<=i && i<=y+75){
					Hy=1;
					break;
					}
				}
			}
		return Hx==1 && Hy==1 && AttackOn==1;
		}
	public static boolean MobVision(int X, int Y, int width, int height, int Sens){
		int Vx=0;
		int Vy=0;
		if (Sens==0){
			for(int i=x; i<x+40; i++){
				if (X-400<i && i<X){
					Vx=1;
					break;
					}
				}
			for(int i=y; i<y+40; i++){
				if (Y<i && i<Y+height){
					Vy=1;
					break;
					}
				}
			}
		if (Sens==2){
			for(int i=x; i<x+40; i++){
				if (X+width<i && i<X+width+400){
					Vx=1;
					break;
					}
				}
			for(int i=y; i<y+40; i++){
				if (Y<i && i<Y+height){
					Vy=1;
					break;
					}
				}
			}
		if (Sens==1){
			for(int i=x; i<x+40; i++){
				if (X<i && i<X+width){
					Vx=1;
					break;
					}
				}
			for(int i=y; i<y+40; i++){
				if (Y-400<i && i<Y){
					Vy=1;
					break;
					}
				}
			}
		if (Sens==3){
			for(int i=x; i<x+40; i++){
				if (X<i && i<X+width){
					Vx=1;
					break;
					}
				}
			for(int i=y; i<y+40; i++){
				if (Y+height<i && i<Y+height+400){
					Vy=1;
					break;
					}
				}
			}
		return Vx==1 && Vy==1;
		}
	public static void AttackTour(){
		Fen.Attack=0;
		Fen.AttackHitBoxX+=6;
		Fen.AttackHitBoxY+=6;
		Fen.arcStart-=14;
		Fen.AttackTour+=1;
		if (Fen.AttackTour==10){
			Fen.AttackOn=0;
			Fen.AttackTour=0;
			Fen.arcStart=60;
			Fen.AttackHitBoxX=0;
			Fen.AttackHitBoxY=0;
			Fen.AttackDown=0;
			Fen.AttackLeft=0;
			Fen.AttackRight=0;
			Fen.AttackUp=0;
			}
		}
		
	}
class PanTitle extends JPanel{
	public PanTitle(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		try{
			Image Title = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Ecran Titre.png"));
			g.drawImage(Title, 0,0, 800, 800, this);
			} catch (IOException e) {}
		g.setFont(new Font("Miriam Libre", Font.BOLD, 30));
		if (Fen.Game==0) g.setColor(Color.black);
		else if (Fen.Game==1) g.setColor(Color.red);
		g.drawString("Jouer", 363, 455);
		}
	}			
class Pan0 extends JPanel{
	int rouge=0;
	public Pan0(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		}
	public void paintComponent(Graphics g){
		Fen.PassCinematic=1;
		g.setColor(Color.black);
		g.fillRect(0,0, 800, 800);
		g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
		g.setColor(new Color(rouge, 0, 0));
		g.fillRect(Fen.x, Fen.y, 40, 40);
		if (Fen.TextTimeOn==0){
			Fen.TextTimeOn=1;
			new TextTime().start();
			}
		g.setColor(Color.white);
		if (Fen.TextTime<17) g.drawString("ESCP pour passer", 50, 750);
		if (3<=Fen.TextTime && Fen.TextTime<10) g.drawString("Tu te trouves dans le Labyrinthe", 220, 250);
		if (6<=Fen.TextTime && Fen.TextTime<10) g.drawString("Pour t'echapper, tu dois trouver la Poussiere de Lumiere", 50, 280);
		if (10<=Fen.TextTime && Fen.TextTime<17) g.drawString("A cause de la magie du Labyrinthe,", 200, 250);
		if (12<=Fen.TextTime && Fen.TextTime<17){
			g.drawString("tu as pris cette apparence", 240, 280);
			if (rouge<255) rouge+=1;
			}
		if (Fen.TextTime>=17){
			Fen.TextTime=0;
			Fen.Room=1;
			Fen.Motion=1;
			Fen.PassCinematic=0;
			}
		try{
			Thread.sleep(7);
			} catch (InterruptedException e) {}
		}
	}
class Pan1 extends JPanel{
	int MobR1x=350;
	int MobR1y=201;
	static int MobR1Sens=0;
	static int MobR1TimeOn=0;
	static int MobR1Afraid=0;
	int MobR1Hit=0;
	int MobR1HitOn=0;
	static int MobR1Life=2;

	int MobT1x=700;
	int MobT1y=200;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	static int MobP1x=500;
	static int MobP1y=201;
	static int MobP1Sens=0;
	static int MobP1TimeOn=0;
	int MobP1Hit=0;
	int MobP1HitOn=0;
	static int MobP1Life=4;
	JButton gris = new JButton();
	public Pan1(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
	
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(0, Fen.Color255, Fen.Color255));
		g.fillRect(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, this.getWidth(), 2*this.getHeight()/8);

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.keyflag==0){
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			g.setColor(Color.black);
			g.drawString("Par ici", 340, 50);
			}
if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobR1 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRond.png"));
			Image MobR1A = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondAfraid.png"));
			Image MobR1D = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondDamaged.png"));
			if (MobR1Life==2 && Fen.keyflag>0) g.drawImage(MobR1, MobR1x, MobR1y, 40, 40, this);
			if (MobR1Life==1 && Fen.keyflag>0) g.drawImage(MobR1A, MobR1x, MobR1y, 40, 40, this);
			if (MobR1Hit==1 && MobR1Life>0 && Fen.keyflag>0){
				g.drawImage(MobR1D, MobR1x, MobR1y, 40, 40, this);
				if (MobR1HitOn==0){
					MobR1Life-=1;
					MobR1HitOn=1;
					}
				MobR1Hit=0;
				}
			Image MobT1 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobT1X = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobT1D = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (MobT1Life>0 && Fen.keyflag>0) g.drawImage(MobT1, MobT1x, MobT1y, 40, 40, this);
			if (MobT1TrapOn==1 && MobT1Life>0 && Fen.keyflag>0) g.drawImage(MobT1X, MobT1x, MobT1y, 40, 40, this);
			if (MobT1Hit==1 && MobT1Life>0 && Fen.keyflag>0){
				g.drawImage(MobT1D, MobT1x, MobT1y, 40, 40, this);
				if (MobT1HitOn==0){
					MobT1Life-=1;
					MobT1HitOn=1;
					}
				MobT1Hit=0;
				}
			Image MobP1 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPenta.png"));
			Image MobP1X = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaX.png"));
			Image MobP1D = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaDamaged.png"));
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, MobP1Sens) && MobP1Life>0 && Fen.keyflag>2) g.drawImage(MobP1X, MobP1x, MobP1y, 40, 40, this);
			else if (MobP1Life>0 && Fen.keyflag>2) g.drawImage(MobP1, MobP1x, MobP1y, 40, 40, this);
			if (MobP1Hit==1 && MobP1Life>0 && Fen.keyflag>2){
				g.drawImage(MobP1D, MobP1x, MobP1y, 40, 40, this);
				if (MobP1HitOn==0){
					MobP1Life-=1;
					MobP1HitOn=1;
					}
				MobP1Hit=0;
				}
			if (Fen.keyflag==0){
				g.drawImage(MurH, 0, 200, 100, 400, this);
				g.drawImage(MurH, 700, 200, 100, 400, this);
				if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
				if (Fen.Colision(700, 200, 100, 400, 2)) Fen.x=660;
				}
			if (Fen.keyflag==1){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				}
			if (Fen.AttackOn==0){
				MobR1HitOn=0;
				MobT1HitOn=0;
				MobP1HitOn=0;
				}
			} catch (IOException e) {}  
//****R1 Mob1
	if (Fen.keyflag>0){
		if (MobR1Life==1) MobR1Afraid=1;
		else MobR1Afraid=0;
		if (Fen.MobHitBox(MobR1x, MobR1y, 40, 40) && MobR1Hit==0){
			MobR1Hit=1;
			if (Fen.sens==0) MobR1x-=10;
			if (Fen.sens==1) MobR1y-=10;
			if (Fen.sens==2) MobR1x+=10;
			if (Fen.sens==3) MobR1y+=10;
			}
		if (MobR1TimeOn==0){
			MobR1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobR1Sens==0) MobR1x-=1+2*MobR1Afraid;
		if (MobR1Sens==1) MobR1y-=1+2*MobR1Afraid;
		if (MobR1Sens==2) MobR1x+=1+2*MobR1Afraid;
		if (MobR1Sens==3) MobR1y+=1+2*MobR1Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) || (Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40))) && MobR1Sens==1) MobR1y=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==0) MobR1x=200;
		if (Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==2) MobR1x=560;
		if (Fen.ColisionMob(0, 600, 800, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=560;
		if ((MobR1x<0 && MobR1Sens==0) || (MobR1x+40>800 && MobR1Sens==2) || (MobR1y<0 && MobR1Sens==1) || (MobR1y>800 && MobR1Sens==3)) MobR1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR1x, MobR1y, 40, 40) && MobR1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1) if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
		}
//*****T1
	if (Fen.keyflag>0){
		if (MobT1TrapOn==0) MobT1y=200;
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 3) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1y+40<600) MobT1y+=4;
		if ((MobT1y+40==600 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1y-=1;
		if (MobT1y==200) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
//******P1 Mob2
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP1x, MobP1y, 40, 40) && MobP1Hit==0){
			MobP1Hit=1;
			if (Fen.sens==0) MobP1x-=10;
			if (Fen.sens==1) MobP1y-=10;
			if (Fen.sens==2) MobP1x+=10;
			if (Fen.sens==3) MobP1y+=10;
			}
		if (MobP1TimeOn==0){
			MobP1TimeOn=1;
			new Mob2Time().start();
			}
		if (MobP1Sens==0){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 0)) MobP1x-=2;
			else MobP1x-=1;
			}
		if (MobP1Sens==1){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 1)) MobP1y-=2;
			else MobP1y-=1;
			}
		if (MobP1Sens==2){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 2)) MobP1x+=2;
			else MobP1x+=1;
			}
		if (MobP1Sens==3){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 3)) MobP1y+=2;
			else MobP1y+=1;
			}
		if ((Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) || (Fen.ColisionMob(600, 0, 200, 200, MobP1x, MobP1y, 40, 40))) && MobP1Sens==1) MobP1y=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==0) MobP1x=200;
		if (Fen.ColisionMob(600, 0, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==2) MobP1x=560;
		if (Fen.ColisionMob(0, 600, 800, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==3) MobP1y=560;
		if ((MobP1x<0 && MobP1Sens==0) || (MobP1x+40>800 && MobP1Sens==2) || (MobP1y<0 && MobP1Sens==1) || (MobP1y>800 && MobP1Sens==3)) MobP1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP1x, MobP1y, 40, 40) && MobP1Life>0) Fen.Lifey+=1;
		}
}
//*****Fin Mob 
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Orientation='E';
			Fen.Room=2; 
			Fen.ChangeRoom=0;
			}
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=3;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=5;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			}
		if (Fen.Colision(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8, 1)) Fen.y=200;
		else if (Fen.Colision(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8, 1)) Fen.y=200;
		else if (Fen.Colision(0, 600, this.getWidth(), 2*this.getHeight()/8, 3)) Fen.y=560;
		else if (Fen.Colision(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8, 0)) Fen.x=200;
		else if (Fen.Colision(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8, 2)) Fen.x=560;

		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1){
				g.setColor(new Color(100, 100, 100));
				g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
				g.setColor(Color.red);
				//g.fillRect(Fen.x+60, Fen.y-10+Fen.AttackHitBoxY, 15, 15);
				}
			if (Fen.AttackUp==1){
				g.setColor(new Color(100, 100, 100));
				g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);
				g.setColor(Color.red);
				//g.fillRect(Fen.x-15+Fen.AttackHitBoxX, Fen.y-40, 15, 15);
				}
			if (Fen.AttackLeft==1){
				g.setColor(new Color(100, 100, 100));
				g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);
				g.setColor(Color.red);
				//g.fillRect(Fen.x-40, Fen.y+45-Fen.AttackHitBoxY, 15, 15);
				}
			if (Fen.AttackDown==1){
				g.setColor(new Color(100, 100, 100));
				g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);
				g.setColor(Color.red);
				//g.fillRect(Fen.x+45-Fen.AttackHitBoxX, Fen.y+60, 15, 15);
				}
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);
		
		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170); g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan2 extends JPanel{
	int MobT1x=200;
	int MobT1y=50;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	static int MobP1x=250;
	static int MobP1y=200;
	static int MobP1Sens=0;
	static int MobP1TimeOn=0;
	int MobP1Hit=0;
	int MobP1HitOn=0;
	static int MobP1Life=4;

	static int MobP2x=400;
	static int MobP2y=200;
	static int MobP2Sens=0;
	static int MobP2TimeOn=0;
	int MobP2Hit=0;
	int MobP2HitOn=0;
	static int MobP2Life=4;

	static int MobP3x=300;
	static int MobP3y=300;
	static int MobP3Sens=0;
	static int MobP3TimeOn=0;
	int MobP3Hit=0;
	int MobP3HitOn=0;
	static int MobP3Life=4;

	static int MobP4x=350;
	static int MobP4y=400;
	static int MobP4Sens=0;
	static int MobP4TimeOn=0;
	int MobP4Hit=0;
	int MobP4HitOn=0;
	static int MobP4Life=4;

	static int Boss=2;
	public Pan2(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(0, Fen.Color255, 0));
		g.fillRect(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(0, 6*this.getHeight()/8, this.getWidth(), 2*this.getHeight()/8);

if (Fen.GameOver==0){   try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>1){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if ((MobT1TrapOn==1 || MobT1TrapOn==3) && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				}
			Image MobP = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPenta.png"));
			Image MobPX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaX.png"));
			Image MobPD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaDamaged.png"));
			if (Fen.keyflag>2){
				if (Fen.MobVision(MobP1x, MobP1y, 40, 40, MobP1Sens) && MobP1Life>0) g.drawImage(MobPX, MobP1x, MobP1y, 40, 40, this);
				else if (MobP1Life>0) g.drawImage(MobP, MobP1x, MobP1y, 40, 40, this);
				if (MobP1Hit==1 && MobP1Life>0){
					g.drawImage(MobPD, MobP1x, MobP1y, 40, 40, this);
					if (MobP1HitOn==0){
						MobP1Life-=1;
						MobP1HitOn=1;
						}
					MobP1Hit=0;
					}
				if (Fen.MobVision(MobP2x, MobP2y, 40, 40, MobP2Sens) && MobP2Life>0) g.drawImage(MobPX, MobP2x, MobP2y, 40, 40, this);
				else if (MobP2Life>0) g.drawImage(MobP, MobP2x, MobP2y, 40, 40, this);
				if (MobP2Hit==1 && MobP2Life>0){
					g.drawImage(MobPD, MobP2x, MobP2y, 40, 40, this);
					if (MobP2HitOn==0){
						MobP2Life-=1;
						MobP2HitOn=1;
						}
					MobP2Hit=0;
					}
				if (Fen.MobVision(MobP3x, MobP3y, 40, 40, MobP3Sens) && MobP3Life>0) g.drawImage(MobPX, MobP3x, MobP3y, 40, 40, this);
				else if (MobP3Life>0) g.drawImage(MobP, MobP3x, MobP3y, 40, 40, this);
				if (MobP3Hit==1 && MobP3Life>0){
					g.drawImage(MobPD, MobP3x, MobP3y, 40, 40, this);
					if (MobP3HitOn==0){
						MobP3Life-=1;
						MobP3HitOn=1;
						}
					MobP3Hit=0;
					}
				if (Fen.MobVision(MobP4x, MobP4y, 40, 40, MobP4Sens) && MobP4Life>0) g.drawImage(MobPX, MobP4x, MobP4y, 40, 40, this);
				else if (MobP4Life>0) g.drawImage(MobP, MobP4x, MobP4y, 40, 40, this);
				if (MobP4Hit==1 && MobP4Life>0){
					g.drawImage(MobPD, MobP4x, MobP4y, 40, 40, this);
					if (MobP4HitOn==0){
						MobP4Life-=1;
						MobP4HitOn=1;
						}
					MobP4Hit=0;
					}
				}
			if (Boss==1){
				g.drawImage(MurH, 0, 200, 100, 400, this);
				g.drawImage(MurW, 200, 0, 400, 100, this);
				if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				}
			if (Fen.AttackOn==0){
				MobT1HitOn=0;
				MobP1HitOn=0;
				MobP2HitOn=0;
				MobP3HitOn=0;
				MobP4HitOn=0;
				}
			} catch (IOException e) {}   
//*****T1
	if (Fen.keyflag>1){
		if (MobT1TrapOn==0){ 
			MobT1x=200;
			MobT1y=50;
			}
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 2) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1x+40<600) MobT1x+=4;
		if ((MobT1x+40==600 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1x-=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 3) && MobT1TrapOn==0) MobT1TrapOn=3;
		if (MobT1TrapOn==3 && MobT1y+40<600) MobT1y+=4;
		if ((MobT1y+40>=600 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==3) MobT1TrapOn=4;
		if (MobT1TrapOn==4) MobT1y-=1;
		if (MobT1x==200 && MobT1y==50) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
		if (Fen.Presence(150, 200, 0, 400) && Boss==2 && Fen.keyflag==3) Boss=1;
//******P1 Mob1
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP1x, MobP1y, 40, 40) && MobP1Hit==0){
			MobP1Hit=1;
			if (Fen.sens==0) MobP1x-=10;
			if (Fen.sens==1) MobP1y-=10;
			if (Fen.sens==2) MobP1x+=10;
			if (Fen.sens==3) MobP1y+=10;
			}
		if (MobP1TimeOn==0){
			MobP1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobP1Sens==0){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 0)) MobP1x-=2;
			else MobP1x-=1;
			}
		if (MobP1Sens==1){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 1)) MobP1y-=2;
			else MobP1y-=1;
			}
		if (MobP1Sens==2){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 2)) MobP1x+=2;
			else MobP1x+=1;
			}
		if (MobP1Sens==3){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 3)) MobP1y+=2;
			else MobP1y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==1) MobP1y=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==0) MobP1x=200;
		if (Fen.ColisionMob(600, 0, 200, 600, MobP1x, MobP1y, 40, 40) && MobP1Sens==2) MobP1x=560;
		if (Fen.ColisionMob(0, 600, 800, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==3) MobP1y=560;
		if ((MobP1x<0 && MobP1Sens==0) || (MobP1x+40>800 && MobP1Sens==2) || (MobP1y<0 && MobP1Sens==1) || (MobP1y>800 && MobP1Sens==3)) MobP1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP1x, MobP1y, 40, 40) && MobP1Life>0) Fen.Lifey+=1;
		if (Fen.ColisionMob(200, 0, 400, 100, MobP1x, MobP1y, 40, 40) && MobP1Sens==1) MobP1y=100;
		if (Fen.ColisionMob(0, 200, 100, 400, MobP1x, MobP1y, 40, 40) && MobP1Sens==0) MobP1x=100;
		}
//******P2 Mob2
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP2x, MobP2y, 40, 40) && MobP2Hit==0){
			MobP2Hit=1;
			if (Fen.sens==0) MobP2x-=10;
			if (Fen.sens==1) MobP2y-=10;
			if (Fen.sens==2) MobP2x+=10;
			if (Fen.sens==3) MobP2y+=10;
			}
		if (MobP2TimeOn==0){
			MobP2TimeOn=1;
			new Mob2Time().start();
			}
		if (MobP2Sens==0){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 0)) MobP2x-=2;
			else MobP2x-=1;
			}
		if (MobP2Sens==1){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 1)) MobP2y-=2;
			else MobP2y-=1;
			}
		if (MobP2Sens==2){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 2)) MobP2x+=2;
			else MobP2x+=1;
			}
		if (MobP2Sens==3){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 3)) MobP2y+=2;
			else MobP2y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 200, MobP2x, MobP2y, 40, 40) && MobP2Sens==1) MobP2y=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobP2x, MobP2y, 40, 40) && MobP2Sens==0) MobP2x=200;
		if (Fen.ColisionMob(600, 0, 200, 600, MobP2x, MobP2y, 40, 40) && MobP2Sens==2) MobP2x=560;
		if (Fen.ColisionMob(0, 600, 800, 200, MobP2x, MobP2y, 40, 40) && MobP2Sens==3) MobP2y=560;
		if ((MobP2x<0 && MobP2Sens==0) || (MobP2x+40>800 && MobP2Sens==2) || (MobP2y<0 && MobP2Sens==1) || (MobP2y>800 && MobP2Sens==3)) MobP2Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP2x, MobP2y, 40, 40) && MobP2Life>0) Fen.Lifey+=1;
		if (Fen.ColisionMob(200, 0, 400, 100, MobP2x, MobP2y, 40, 40) && MobP2Sens==1) MobP2y=100;
		if (Fen.ColisionMob(0, 200, 100, 400, MobP2x, MobP2y, 40, 40) && MobP2Sens==0) MobP2x=100;
		}
//******P3 Mob3
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP3x, MobP3y, 40, 40) && MobP3Hit==0){
			MobP3Hit=1;
			if (Fen.sens==0) MobP3x-=10;
			if (Fen.sens==1) MobP3y-=10;
			if (Fen.sens==2) MobP3x+=10;
			if (Fen.sens==3) MobP3y+=10;
			}
		if (MobP3TimeOn==0){
			MobP3TimeOn=1;
			new Mob3Time().start();
			}
		if (MobP3Sens==0){
			if (Fen.MobVision(MobP3x, MobP3y, 40, 40, 0)) MobP3x-=2;
			else MobP3x-=1;
			}
		if (MobP3Sens==1){
			if (Fen.MobVision(MobP3x, MobP3y, 40, 40, 1)) MobP3y-=2;
			else MobP3y-=1;
			}
		if (MobP3Sens==2){
			if (Fen.MobVision(MobP3x, MobP3y, 40, 40, 2)) MobP3x+=2;
			else MobP3x+=1;
			}
		if (MobP3Sens==3){
			if (Fen.MobVision(MobP3x, MobP3y, 40, 40, 3)) MobP3y+=2;
			else MobP3y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 200, MobP3x, MobP3y, 40, 40) && MobP3Sens==1) MobP3y=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobP3x, MobP3y, 40, 40) && MobP3Sens==0) MobP3x=200;
		if (Fen.ColisionMob(600, 0, 200, 600, MobP3x, MobP3y, 40, 40) && MobP3Sens==2) MobP3x=560;
		if (Fen.ColisionMob(0, 600, 800, 200, MobP3x, MobP3y, 40, 40) && MobP3Sens==3) MobP3y=560;
		if ((MobP3x<0 && MobP3Sens==0) || (MobP3x+40>800 && MobP3Sens==2) || (MobP3y<0 && MobP3Sens==1) || (MobP3y>800 && MobP3Sens==3)) MobP3Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP3x, MobP3y, 40, 40) && MobP3Life>0) Fen.Lifey+=1;
		if (Fen.ColisionMob(200, 0, 400, 100, MobP3x, MobP3y, 40, 40) && MobP3Sens==1) MobP3y=100;
		if (Fen.ColisionMob(0, 200, 100, 400, MobP3x, MobP3y, 40, 40) && MobP3Sens==0) MobP3x=100;
		}
//******P4 Mob4
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP4x, MobP4y, 40, 40) && MobP4Hit==0){
			MobP4Hit=1;
			if (Fen.sens==0) MobP4x-=10;
			if (Fen.sens==1) MobP4y-=10;
			if (Fen.sens==2) MobP4x+=10;
			if (Fen.sens==3) MobP4y+=10;
			}
		if (MobP4TimeOn==0){
			MobP4TimeOn=1;
			new Mob4Time().start();
			}
		if (MobP4Sens==0){
			if (Fen.MobVision(MobP4x, MobP4y, 40, 40, 0)) MobP4x-=2;
			else MobP4x-=1;
			}
		if (MobP4Sens==1){
			if (Fen.MobVision(MobP4x, MobP4y, 40, 40, 1)) MobP4y-=2;
			else MobP4y-=1;
			}
		if (MobP4Sens==2){
			if (Fen.MobVision(MobP4x, MobP4y, 40, 40, 2)) MobP4x+=2;
			else MobP4x+=1;
			}
		if (MobP4Sens==3){
			if (Fen.MobVision(MobP4x, MobP4y, 40, 40, 3)) MobP4y+=2;
			else MobP4y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 200, MobP4x, MobP4y, 40, 40) && MobP4Sens==1) MobP4y=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobP4x, MobP4y, 40, 40) && MobP4Sens==0) MobP4x=200;
		if (Fen.ColisionMob(600, 0, 200, 600, MobP4x, MobP4y, 40, 40) && MobP4Sens==2) MobP4x=560;
		if (Fen.ColisionMob(0, 600, 800, 200, MobP4x, MobP4y, 40, 40) && MobP4Sens==3) MobP4y=560;
		if ((MobP4x<0 && MobP4Sens==0) || (MobP4x+40>800 && MobP4Sens==2) || (MobP4y<0 && MobP4Sens==1) || (MobP4y>800 && MobP4Sens==3)) MobP4Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP4x, MobP4y, 40, 40) && MobP4Life>0) Fen.Lifey+=1;
		if (Fen.ColisionMob(200, 0, 400, 100, MobP4x, MobP4y, 40, 40) && MobP4Sens==1) MobP4y=100;
		if (Fen.ColisionMob(0, 200, 100, 400, MobP4x, MobP4y, 40, 40) && MobP4Sens==0) MobP4x=100;
		}
}
//*****Fin Mob 
		if (MobP1Life==0 && MobP2Life==0 && MobP3Life==0 && MobP4Life==0) Boss=0;

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=1;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			}
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=6;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			}
		if (Fen.Colision(0, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(0, 0, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(600, 0, 200, 600, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 600, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan3 extends JPanel{
	int MobGCx=200;
	int MobGCy=250;
	int MobGCcubex=302;
	int MobGCcubey=376;
	int MobGCcubeLockx=0;
	int MobGCcubeLocky=0;
	int MobGCangry=0;
	static int MobGCTime=0;
	static int MobGCTimeOn=0;
	int MobGCHit=0;
	int MobGCHitOn=0;
	static int MobGCLife=30;
	static int Boss=0;
	public Pan3(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(0, 0, Fen.Color255));
		g.fillRect(0, 0, this.getWidth(), 2*this.getHeight()/8);
		g.fillRect(0, 0, 1*this.getWidth()/8, this.getHeight());
		g.fillRect(0, 6*this.getHeight()/8, this.getWidth(), 2*this.getHeight()/8);

if (Fen.GameOver==0){   try{
			Image Saphir = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Saphir.png"));
			if (Fen.Saphir==0) g.drawImage(Saphir, 120, 380, 40, 40, this);
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobGC = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCube.png"));
			Image MobGCX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeX.png"));
			Image MobGCD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeDamaged.png"));
			Image MobGCa = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngry.png"));
			Image MobGCaX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngryX.png"));
			Image MobGCaD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngryDamaged.png"));
			if (Boss==1 && MobGCLife>0){
				if (MobGCangry==0) g.drawImage(MobGC, MobGCx, MobGCy, 300, 300, this);
				else g.drawImage(MobGCa, MobGCx, MobGCy, 300, 300, this);
				if (MobGCangry==0 && MobGCTime>5) g.drawImage(MobGCX, MobGCx, MobGCy, 300, 300, this);
				else if (MobGCTime>5) g.drawImage(MobGCaX, MobGCx, MobGCy, 300, 300, this);
				if (MobGCHit==1 && MobGCLife>0){
					if (MobGCangry==0) g.drawImage(MobGCD, MobGCx, MobGCy, 300, 300, this);
					else g.drawImage(MobGCaD, MobGCx, MobGCy, 300, 300, this);
					if (MobGCHitOn==0){
						MobGCLife-=1;
						MobGCHitOn=1;
						}
					MobGCHit=0;
					}
				if (Fen.AttackOn==0) MobGCHitOn=0;
				}
			if (MobGCLife>0){
				g.drawImage(MurH, 170, 200, 25, 400, this);
				if (Boss==1) g.drawImage(MurH, 775, 200, 100, 400, this);
				}
			} catch (IOException e) {}   
//*****GC
		if (Fen.Presence(600, 200, 0, 400) && Boss==0 && Fen.Saphir==0 && Fen.Emeraude!=1 && Fen.Rubis!=1 && Pan20.Boss==0 && Pan16.Boss==0){
			Boss=1;
			MobGCcubex=302;
			MobGCcubey=376;
			MobGCangry=0;
			}
	if (MobGCLife>0 && Boss==1){
		if (MobGCLife<=15) MobGCangry=1;
		if (MobGCTimeOn==0){
			MobGCTimeOn=1;
			new Mob1Time().start();
			}
		g.setColor(Color.magenta);
		if (MobGCTime<5){
			g.drawLine(MobGCx+125, MobGCy+30, Fen.x+(int)(Math.random()*40), Fen.y+(int)(Math.random()*40));
			g.drawLine(MobGCx+175, MobGCy+30, Fen.x+(int)(Math.random()*40), Fen.y+(int)(Math.random()*40));
			}
		if (MobGCTime==5){
			g.drawLine(MobGCx+125, MobGCy+30, Fen.x+20, Fen.y+20);
			g.drawLine(MobGCx+175, MobGCy+30, Fen.x+20, Fen.y+20);
			MobGCcubeLockx=Fen.x+20;
			MobGCcubeLocky=Fen.y+20;
			//MobGCcubeLocky=(int)(((Fen.x+20-350)*1000-c)/(Fen.y+20-416));
			}
		if (MobGCTime>5){
			if (MobGCcubeLockx==350) MobGCcubeLockx+=1;
			if (MobGCcubeLocky==416) MobGCcubeLocky+=1;
			int Y = (int)((MobGCcubeLocky-416)*(MobGCcubex+48-350)/(MobGCcubeLockx-350))+416;
			int X = (int)((MobGCcubeLockx-350)*(MobGCcubey+40-416)/(MobGCcubeLocky-416))+350;
			//System.out.println(Y);
			g.setColor(Color.gray);
			//g.drawLine(302+48, 376+40, MobGCcubeLockx, Y);
			g.fillRect(MobGCcubex, MobGCcubey, 96, 81);
			if (MobGCcubeLockx>MobGCx+250){
				MobGCcubex+=3+2*MobGCangry;
				if (MobGCcubey+40<Y) MobGCcubey+=2+2*MobGCangry;
				else if (MobGCcubey+40>Y) MobGCcubey-=2+2*MobGCangry;
				}
			if (MobGCcubeLockx<MobGCx+50){
				MobGCcubex-=3+2*MobGCangry;
				if (MobGCcubey+40<Y) MobGCcubey+=2+2*MobGCangry;
				else if (MobGCcubey+40>Y) MobGCcubey-=2+2*MobGCangry;
				}
			if (MobGCx+50<MobGCcubeLockx && MobGCcubeLockx<MobGCx+250){
				if (MobGCcubeLocky>MobGCy+150){
					MobGCcubey+=3+2*MobGCangry;
					if (MobGCcubex+48<X) MobGCcubex+=2+2*MobGCangry;
					else if (MobGCcubex+48>X) MobGCcubex-=2+2*MobGCangry;
					}
				if (MobGCcubeLocky<MobGCy+150){
					MobGCcubey-=3+2*MobGCangry;
					if (MobGCcubex+48<X) MobGCcubex+=2+2*MobGCangry;
					else if (MobGCcubex+48>X) MobGCcubex-=2+2*MobGCangry;
					}
				}
			}
		if (MobGCangry==1 && MobGCTime==8) MobGCTime=10;
		if (MobGCTime==10){
			if (MobGCangry==0) MobGCTime=0;
			else MobGCTime=4;
			MobGCcubex=302;
			MobGCcubey=376;
			}
		if ((Fen.MobHitBox(MobGCx+90, MobGCy, 120, 75) || Fen.MobHitBox(MobGCx+6, MobGCy+75, 33, 162) || Fen.MobHitBox(MobGCx+261, MobGCy+75, 33, 162) || Fen.MobHitBox(MobGCx+45, MobGCy+75, 210, 183) || Fen.MobHitBox(MobGCx+90, MobGCy+258, 120, 42)) && MobGCHit==0 && MobGCTime>5){
			MobGCHit=1;
			}
		if (Fen.Presence(MobGCx+90, MobGCy, 120, 75) || Fen.Presence(MobGCx+6, MobGCy+75, 33, 162) || Fen.Presence(MobGCx+261, MobGCy+75, 33, 162) || Fen.Presence(MobGCx+45, MobGCy+75, 210, 183) || Fen.Presence(MobGCx+90, MobGCy+258, 120, 42) || Fen.Presence(MobGCcubex, MobGCcubey, 96, 81)){
			Fen.Lifey+=1;
			}
		if (Fen.Colision(775, 200, 100, 400, 2)) Fen.x=735;
		}
}
//*****Fin Mob 
		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=1;
			Fen.Orientation='E';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(100, 0, 700, 200, 1)) Fen.y=200;
		else if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
		else if (Fen.Colision(100, 600, 700, 200, 3)) Fen.y=560;
		else if (Fen.Colision(170, 200, 25, 400, 0) && MobGCLife>0) Fen.x=195;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(3);
				} catch (InterruptedException e) {}
			}
		g.setFont(new Font("Arial", Font.BOLD, 18));
		if (Fen.Presence(120, 380, 40, 40) && Fen.Saphir==0 && Fen.Rubis!=1 && Fen.Emeraude!=1){
			if (Fen.Interaction==2){
				Fen.Saphir=1;
				Boss=0;
				}
			Fen.Interaction=1;
			g.setColor(Color.green);
			g.fillOval(130, 390, 20, 20);
			g.setColor(Color.black);
			g.drawString("A", 134, 406);
			}
		else Fen.Interaction=0;
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.white);
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(3);
			} catch (InterruptedException e) {}
		}
	}
class Pan4 extends JPanel{
	public Pan4(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(128, 0, 255));
		g.fillRect(0, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, this.getWidth(), 2*this.getHeight()/8);

		if (Fen.keyflag==3 && Fen.Presence(0, 0, 800, 800) && Fen.Emeraude==1 && Pan8.BossLife>0){
			g.setFont(new Font("Miriam Libre", Font.BOLD, 16));
			g.setColor(Color.black);
			g.drawString("Une puissante aura malefique s'est liberee...", 210, 50);
			}

		try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			
			} catch (IOException e) {}

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=5;
			Fen.Orientation='E';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=8;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(600, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(0, 0, 200, 600, 0)) Fen.x=200;
		else if (Fen.Colision(200, 600, 600, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan5 extends JPanel{
	int MobR1x=350;
	int MobR1y=201;
	static int MobR1Sens=0;
	static int MobR1TimeOn=0;
	static int MobR1Afraid=0;
	int MobR1Hit=0;
	int MobR1HitOn=0;
	static int MobR1Life=2;

	int MobR2x=350;
	int MobR2y=300;
	static int MobR2Sens=0;
	static int MobR2TimeOn=0;
	static int MobR2Afraid=0;
	int MobR2Hit=0;
	int MobR2HitOn=0;
	static int MobR2Life=2;

	int MobT1x=50;
	int MobT1y=200;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	static int MobP1x=500;
	static int MobP1y=201;
	static int MobP1Sens=0;
	static int MobP1TimeOn=0;
	int MobP1Hit=0;
	int MobP1HitOn=0;
	static int MobP1Life=4;
	public Pan5(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color255, 0, Fen.Color255));
		g.fillRect(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);

if (Fen.GameOver==0){   try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRond.png"));
			Image MobRA = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondAfraid.png"));
			Image MobRD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondDamaged.png"));
			if (Fen.keyflag>0){
				if (MobR1Life==2) g.drawImage(MobR, MobR1x, MobR1y, 40, 40, this);
				else if (MobR1Life==1) g.drawImage(MobRA, MobR1x, MobR1y, 40, 40, this);
				if (MobR1Hit==1 && MobR1Life>0){
					g.drawImage(MobRD, MobR1x, MobR1y, 40, 40, this);
					if (MobR1HitOn==0){
						MobR1Life-=1;
						MobR1HitOn=1;
						}
					MobR1Hit=0;
					}
				if (MobR2Life==2) g.drawImage(MobR, MobR2x, MobR2y, 40, 40, this);
				else if (MobR2Life==1) g.drawImage(MobRA, MobR2x, MobR2y, 40, 40, this);
				if (MobR2Hit==1 && MobR2Life>0){
					g.drawImage(MobRD, MobR2x, MobR2y, 40, 40, this);
					if (MobR2HitOn==0){
						MobR2Life-=1;
						MobR2HitOn=1;
						}
					MobR2Hit=0;
					}
				}
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>1){
				if (MobT1Life>0 && Fen.keyflag>1) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if (MobT1TrapOn==1 && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				}
			Image MobP = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPenta.png"));
			Image MobPX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaX.png"));
			Image MobPD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaDamaged.png"));
			if (Fen.keyflag>2){
				if (Fen.MobVision(MobP1x, MobP1y, 40, 40, MobP1Sens) && MobP1Life>0) g.drawImage(MobPX, MobP1x, MobP1y, 40, 40, this);
				else if (MobP1Life>0) g.drawImage(MobP, MobP1x, MobP1y, 40, 40, this);
				if (MobP1Hit==1 && MobP1Life>0){
					g.drawImage(MobPD, MobP1x, MobP1y, 40, 40, this);
					if (MobP1HitOn==0){
						MobP1Life-=1;
						MobP1HitOn=1;
						}
					MobP1Hit=0;
					}
				}
			if (Fen.keyflag==0 || Fen.keyflag==3){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				g.drawImage(MurH, 700, 200, 100, 400, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				if (Fen.Colision(700, 200, 100, 400, 2)) Fen.x=660;
				}
			if (Fen.keyflag==1){
				g.drawImage(MurW, 200, 700, 400, 100, this);
				g.drawImage(MurH, 700, 200, 100, 400, this);
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				if (Fen.Colision(700, 200, 100, 400, 2)) Fen.x=660;
				}
			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 700, 400, 100, this);
				g.drawImage(MurW, 200, 0, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.AttackOn==0){
				MobR1HitOn=0;
				MobR2HitOn=0;
				MobT1HitOn=0;
				MobP1HitOn=0;
				}
			} catch (IOException e) {}   
//****R1 Mob1
	if (Fen.keyflag>0){
		if (MobR1Life==1) MobR1Afraid=1;
		else MobR1Afraid=0;
		if (Fen.MobHitBox(MobR1x, MobR1y, 40, 40) && MobR1Hit==0){
			MobR1Hit=1;
			if (Fen.sens==0) MobR1x-=10;
			if (Fen.sens==1) MobR1y-=10;
			if (Fen.sens==2) MobR1x+=10;
			if (Fen.sens==3) MobR1y+=10;
			}
		if (MobR1TimeOn==0){
			MobR1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobR1Sens==0) MobR1x-=1+2*MobR1Afraid;
		if (MobR1Sens==1) MobR1y-=1+2*MobR1Afraid;
		if (MobR1Sens==2) MobR1x+=1+2*MobR1Afraid;
		if (MobR1Sens==3) MobR1y+=1+2*MobR1Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==1) MobR1y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==0) MobR1x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==3) MobR1y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==2) MobR1x=560;
		if ((MobR1x<0 && MobR1Sens==0) || (MobR1x+40>800 && MobR1Sens==2) || (MobR1y<0 && MobR1Sens==1) || (MobR1y>800 && MobR1Sens==3)) MobR1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR1x, MobR1y, 40, 40) && MobR1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1){
			if (Fen.ColisionMob(700, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==2) MobR1x=660;
			if (Fen.ColisionMob(200, 700, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=660;
			}
		if (Fen.keyflag==2){
			if (Fen.ColisionMob(200, 700, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=660;
			if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
			}
		if (Fen.keyflag==3){
			if (Fen.ColisionMob(700, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==2) MobR1x=660;
			if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
			}
		}
//*****R2 Mob2
	if (Fen.keyflag>0){
		if (MobR2Life==1) MobR2Afraid=1;
		else MobR2Afraid=0;
		if (Fen.MobHitBox(MobR2x, MobR2y, 40, 40) && MobR2Hit==0){
			MobR2Hit=1;
			if (Fen.sens==0) MobR2x-=10;
			if (Fen.sens==1) MobR2y-=10;
			if (Fen.sens==2) MobR2x+=10;
			if (Fen.sens==3) MobR2y+=10;
			}
		if (MobR2TimeOn==0){
			MobR2TimeOn=1;
			new Mob2Time().start();
			}
		if (MobR2Sens==0) MobR2x-=1+2*MobR2Afraid;
		if (MobR2Sens==1) MobR2y-=1+2*MobR2Afraid;
		if (MobR2Sens==2) MobR2x+=1+2*MobR2Afraid;
		if (MobR2Sens==3) MobR2y+=1+2*MobR2Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==1) MobR2y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==0) MobR2x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==3) MobR2y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==2) MobR2x=560;
		if ((MobR2x<0 && MobR2Sens==0) || (MobR2x+40>800 && MobR2Sens==2) || (MobR2y<0 && MobR2Sens==1) || (MobR2y>800 && MobR2Sens==3)) MobR2Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR2x, MobR2y, 40, 40) && MobR2Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1){
			if (Fen.ColisionMob(700, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==2) MobR2x=660;
			if (Fen.ColisionMob(200, 700, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==3) MobR2y=660;
			}
		if (Fen.keyflag==2){
			if (Fen.ColisionMob(200, 700, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==3) MobR2y=660;
			if (Fen.ColisionMob(200, 0, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==1) MobR2y=100;
			}
		if (Fen.keyflag==3){
			if (Fen.ColisionMob(700, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==2) MobR2x=660;
			if (Fen.ColisionMob(200, 0, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==1) MobR2y=100;
			}
		}
//*****T1
	if (Fen.keyflag>1){
		if (MobT1TrapOn==0) MobT1y=200;
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 3) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1y+40<600) MobT1y+=4;
		if ((MobT1y+40==600 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1y-=1;
		if (MobT1y==200) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
//******P1 Mob3
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP1x, MobP1y, 40, 40) && MobP1Hit==0){
			MobP1Hit=1;
			if (Fen.sens==0) MobP1x-=10;
			if (Fen.sens==1) MobP1y-=10;
			if (Fen.sens==2) MobP1x+=10;
			if (Fen.sens==3) MobP1y+=10;
			}
		if (MobP1TimeOn==0){
			MobP1TimeOn=1;
			new Mob3Time().start();
			}
		if (MobP1Sens==0){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 0)) MobP1x-=2;
			else MobP1x-=1;
			}
		if (MobP1Sens==1){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 1)) MobP1y-=2;
			else MobP1y-=1;
			}
		if (MobP1Sens==2){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 2)) MobP1x+=2;
			else MobP1x+=1;
			}
		if (MobP1Sens==3){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 3)) MobP1y+=2;
			else MobP1y+=1;
			}
		if ((Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==1) MobP1y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==0) MobP1x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==3) MobP1y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==2) MobP1x=560;
		if ((MobP1x<0 && MobP1Sens==0) || (MobP1x+40>800 && MobP1Sens==2) || (MobP1y<0 && MobP1Sens==1) || (MobP1y>800 && MobP1Sens==3)) MobP1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP1x, MobP1y, 40, 40) && MobP1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==3){
			if (Fen.ColisionMob(700, 200, 100, 400, MobP1x, MobP1y, 40, 40) && MobP1Sens==2) MobP1x=660;
			if (Fen.ColisionMob(200, 0, 400, 100, MobP1x, MobP1y, 40, 40) && MobP1Sens==1) MobP1y=100;
			}
		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=6;
			Fen.Orientation='E';
			Fen.ChangeRoom=0;
			} 
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=4;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=1;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=9;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(600, 600, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;
		else if (Fen.Colision(600, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan6 extends JPanel{
	int MobR1x=200;
	int MobR1y=300;
	static int MobR1Sens=0;
	static int MobR1TimeOn=0;
	static int MobR1Afraid=0;
	int MobR1Hit=0;
	int MobR1HitOn=0;
	static int MobR1Life=2;

	int MobR2x=300;
	int MobR2y=200;
	static int MobR2Sens=0;
	static int MobR2TimeOn=0;
	static int MobR2Afraid=0;
	int MobR2Hit=0;
	int MobR2HitOn=0;
	static int MobR2Life=2;

	int MobR3x=500;
	int MobR3y=300;
	static int MobR3Sens=0;
	static int MobR3TimeOn=0;
	static int MobR3Afraid=0;
	int MobR3Hit=0;
	int MobR3HitOn=0;
	static int MobR3Life=2;

	int MobR4x=400;
	int MobR4y=500;
	static int MobR4Sens=0;
	static int MobR4TimeOn=0;
	static int MobR4Afraid=0;
	int MobR4Hit=0;
	int MobR4HitOn=0;
	static int MobR4Life=2;

	int MobT1x=280;
	int MobT1y=160;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	int MobT2x=480;
	int MobT2y=600;
	int MobT2TrapOn=0;
	int MobT2Hit=0;
	int MobT2HitOn=0;
	static int MobT2Life=3;

	static int Boss=2;
	public Pan6(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color255, Fen.Color255, 0));
		g.fillRect(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRond.png"));
			Image MobRA = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondAfraid.png"));
			Image MobRD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondDamaged.png"));
			if (Fen.keyflag>0){
				if (MobR1Life==2) g.drawImage(MobR, MobR1x, MobR1y, 40, 40, this);
				else if (MobR1Life==1) g.drawImage(MobRA, MobR1x, MobR1y, 40, 40, this);
				if (MobR1Hit==1 && MobR1Life>0){
					g.drawImage(MobRD, MobR1x, MobR1y, 40, 40, this);
					if (MobR1HitOn==0){
						MobR1Life-=1;
						MobR1HitOn=1;
						}
					MobR1Hit=0;
					}
				if (MobR2Life==2) g.drawImage(MobR, MobR2x, MobR2y, 40, 40, this);
				else if (MobR2Life==1) g.drawImage(MobRA, MobR2x, MobR2y, 40, 40, this);
				if (MobR2Hit==1 && MobR2Life>0){
					g.drawImage(MobRD, MobR2x, MobR2y, 40, 40, this);
					if (MobR2HitOn==0){
						MobR2Life-=1;
						MobR2HitOn=1;
						}
					MobR2Hit=0;
					}
				if (MobR3Life==2) g.drawImage(MobR, MobR3x, MobR3y, 40, 40, this);
				else if (MobR3Life==1) g.drawImage(MobRA, MobR3x, MobR3y, 40, 40, this);
				if (MobR3Hit==1 && MobR3Life>0){
					g.drawImage(MobRD, MobR3x, MobR3y, 40, 40, this);
					if (MobR3HitOn==0){
						MobR3Life-=1;
						MobR3HitOn=1;
						}
					MobR3Hit=0;
					}
				if (MobR4Life==2) g.drawImage(MobR, MobR4x, MobR4y, 40, 40, this);
				else if (MobR4Life==1) g.drawImage(MobRA, MobR4x, MobR4y, 40, 40, this);
				if (MobR4Hit==1 && MobR4Life>0){
					g.drawImage(MobRD, MobR4x, MobR4y, 40, 40, this);
					if (MobR4HitOn==0){
						MobR4Life-=1;
						MobR4HitOn=1;
						}
					MobR4Hit=0;
					}
				}
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>1){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if (MobT1TrapOn==1 && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				if (MobT2Life>0) g.drawImage(MobT, MobT2x, MobT2y, 40, 40, this);
				if (MobT2TrapOn==1 && MobT2Life>0) g.drawImage(MobTX, MobT2x, MobT2y, 40, 40, this);
				if (MobT2Hit==1 && MobT2Life>0){
					g.drawImage(MobTD, MobT2x, MobT2y, 40, 40, this);
					if (MobT2HitOn==0){
						MobT2Life-=1;
						MobT2HitOn=1;
						}
					MobT2Hit=0;
					}
				}
			if (Fen.keyflag==1 || Fen.keyflag==3){
				g.drawImage(MurH, 0, 200, 100, 400, this);
				g.drawImage(MurH, 700, 200, 100, 400, this);
				if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
				if (Fen.Colision(700, 200, 100, 400, 2)) Fen.x=660;
				if (Boss==1){
					g.drawImage(MurW, 200, 0, 400, 100, this);
					g.drawImage(MurW, 200, 700, 400, 100, this);
					if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
					if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
					}
				}
			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.AttackOn==0){
				MobR1HitOn=0;
				MobR2HitOn=0;
				MobR3HitOn=0;
				MobR4HitOn=0;
				MobT1HitOn=0;
				MobT2HitOn=0;
				}
			} catch (IOException e) {}   
		if (Fen.Presence(200, 140, 400, 0) && Boss==2) Boss=1;
//****R1 Mob1
	if (Fen.keyflag>0){
		if (MobR1Life==1) MobR1Afraid=1;
		else MobR1Afraid=0;
		if (Fen.MobHitBox(MobR1x, MobR1y, 40, 40) && MobR1Hit==0){
			MobR1Hit=1;
			if (Fen.sens==0) MobR1x-=10;
			if (Fen.sens==1) MobR1y-=10;
			if (Fen.sens==2) MobR1x+=10;
			if (Fen.sens==3) MobR1y+=10;
			}
		if (MobR1TimeOn==0){
			MobR1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobR1Sens==0) MobR1x-=1+2*MobR1Afraid;
		if (MobR1Sens==1) MobR1y-=1+2*MobR1Afraid;
		if (MobR1Sens==2) MobR1x+=1+2*MobR1Afraid;
		if (MobR1Sens==3) MobR1y+=1+2*MobR1Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==1) MobR1y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==0) MobR1x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==3) MobR1y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==2) MobR1x=560;
		if ((MobR1x<0 && MobR1Sens==0) || (MobR1x+40>800 && MobR1Sens==2) || (MobR1y<0 && MobR1Sens==1) || (MobR1y>800 && MobR1Sens==3)) MobR1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR1x, MobR1y, 40, 40) && MobR1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1 || Fen.keyflag==3){
			if (Fen.ColisionMob(0, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==0) MobR1x=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==2) MobR1x=660;
			if (Boss==1){
				if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
				if (Fen.ColisionMob(200, 700, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=660;
				}
			}
		if (Fen.keyflag==2){
			if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=660;
			}
		}
//*****R2 Mob2
	if (Fen.keyflag>0){
		if (MobR2Life==1) MobR2Afraid=1;
		else MobR2Afraid=0;
		if (Fen.MobHitBox(MobR2x, MobR2y, 40, 40) && MobR2Hit==0){
			MobR2Hit=1;
			if (Fen.sens==0) MobR2x-=10;
			if (Fen.sens==1) MobR2y-=10;
			if (Fen.sens==2) MobR2x+=10;
			if (Fen.sens==3) MobR2y+=10;
			}
		if (MobR2TimeOn==0){
			MobR2TimeOn=1;
			new Mob2Time().start();
			}
		if (MobR2Sens==0) MobR2x-=1+2*MobR2Afraid;
		if (MobR2Sens==1) MobR2y-=1+2*MobR2Afraid;
		if (MobR2Sens==2) MobR2x+=1+2*MobR2Afraid;
		if (MobR2Sens==3) MobR2y+=1+2*MobR2Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==1) MobR2y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==0) MobR2x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==3) MobR2y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==2) MobR2x=560;
		if ((MobR2x<0 && MobR2Sens==0) || (MobR2x+40>800 && MobR2Sens==2) || (MobR2y<0 && MobR2Sens==1) || (MobR2y>800 && MobR2Sens==3)) MobR2Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR2x, MobR2y, 40, 40) && MobR2Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1 || Fen.keyflag==3){
			if (Fen.ColisionMob(0, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==0) MobR2x=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==2) MobR2x=660;
			if (Boss==1){
				if (Fen.ColisionMob(200, 0, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==1) MobR2y=100;
				if (Fen.ColisionMob(200, 700, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==3) MobR2y=660;
				}
			}
		if (Fen.keyflag==2){
			if (Fen.ColisionMob(200, 0, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==1) MobR2y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==3) MobR2y=660;
			}

		}
//****R3 Mob3
	if (Fen.keyflag>0){
		if (MobR3Life==1) MobR3Afraid=1;
		else MobR3Afraid=0;
		if (Fen.MobHitBox(MobR3x, MobR3y, 40, 40) && MobR3Hit==0){
			MobR3Hit=1;
			if (Fen.sens==0) MobR3x-=10;
			if (Fen.sens==1) MobR3y-=10;
			if (Fen.sens==2) MobR3x+=10;
			if (Fen.sens==3) MobR3y+=10;
			}
		if (MobR3TimeOn==0){
			MobR3TimeOn=1;
			new Mob3Time().start();
			}
		if (MobR3Sens==0) MobR3x-=1+2*MobR3Afraid;
		if (MobR3Sens==1) MobR3y-=1+2*MobR3Afraid;
		if (MobR3Sens==2) MobR3x+=1+2*MobR3Afraid;
		if (MobR3Sens==3) MobR3y+=1+2*MobR3Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR3x, MobR3y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobR3x, MobR3y, 40, 40)) && MobR3Sens==1) MobR3y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR3x, MobR3y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR3x, MobR3y, 40, 40)) && MobR3Sens==0) MobR3x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobR3x, MobR3y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR3x, MobR3y, 40, 40)) && MobR3Sens==3) MobR3y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR3x, MobR3y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR3x, MobR3y, 40, 40)) && MobR3Sens==2) MobR3x=560;
		if ((MobR3x<0 && MobR3Sens==0) || (MobR3x+40>800 && MobR3Sens==2) || (MobR3y<0 && MobR3Sens==1) || (MobR3y>800 && MobR3Sens==3)) MobR3Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR3x, MobR3y, 40, 40) && MobR3Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1 || Fen.keyflag==3){
			if (Fen.ColisionMob(0, 200, 100, 400, MobR3x, MobR3y, 40, 40) && MobR3Sens==0) MobR3x=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobR3x, MobR3y, 40, 40) && MobR3Sens==2) MobR3x=660;
			if (Boss==1){
				if (Fen.ColisionMob(200, 0, 400, 100, MobR3x, MobR3y, 40, 40) && MobR3Sens==1) MobR3y=100;
				if (Fen.ColisionMob(200, 700, 400, 100, MobR3x, MobR3y, 40, 40) && MobR3Sens==3) MobR3y=660;
				}
			}
		if (Fen.keyflag==2){
			if (Fen.ColisionMob(200, 0, 400, 100, MobR3x, MobR3y, 40, 40) && MobR3Sens==1) MobR3y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobR3x, MobR3y, 40, 40) && MobR3Sens==3) MobR3y=660;
			}
		}
//*****R4 Mob4
	if (Fen.keyflag>0){
		if (MobR4Life==1) MobR4Afraid=1;
		else MobR4Afraid=0;
		if (Fen.MobHitBox(MobR4x, MobR4y, 40, 40) && MobR4Hit==0){
			MobR4Hit=1;
			if (Fen.sens==0) MobR4x-=10;
			if (Fen.sens==1) MobR4y-=10;
			if (Fen.sens==2) MobR4x+=10;
			if (Fen.sens==3) MobR4y+=10;
			}
		if (MobR4TimeOn==0){
			MobR4TimeOn=1;
			new Mob4Time().start();
			}
		if (MobR4Sens==0) MobR4x-=1+2*MobR4Afraid;
		if (MobR4Sens==1) MobR4y-=1+2*MobR4Afraid;
		if (MobR4Sens==2) MobR4x+=1+2*MobR4Afraid;
		if (MobR4Sens==3) MobR4y+=1+2*MobR4Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR4x, MobR4y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobR4x, MobR4y, 40, 40)) && MobR4Sens==1) MobR4y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR4x, MobR4y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR4x, MobR4y, 40, 40)) && MobR4Sens==0) MobR4x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobR4x, MobR4y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR4x, MobR4y, 40, 40)) && MobR4Sens==3) MobR4y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR4x, MobR4y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR4x, MobR4y, 40, 40)) && MobR4Sens==2) MobR4x=560;
		if ((MobR4x<0 && MobR4Sens==0) || (MobR4x+40>800 && MobR4Sens==2) || (MobR4y<0 && MobR4Sens==1) || (MobR4y>800 && MobR4Sens==3)) MobR4Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR4x, MobR4y, 40, 40) && MobR4Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1 || Fen.keyflag==3){
			if (Fen.ColisionMob(0, 200, 100, 400, MobR4x, MobR4y, 40, 40) && MobR4Sens==0) MobR4x=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobR4x, MobR4y, 40, 40) && MobR4Sens==2) MobR4x=660;
			if (Boss==1){
				if (Fen.ColisionMob(200, 0, 400, 100, MobR4x, MobR4y, 40, 40) && MobR4Sens==1) MobR4y=100;
				if (Fen.ColisionMob(200, 700, 400, 100, MobR4x, MobR4y, 40, 40) && MobR4Sens==3) MobR4y=660;
				}
			}
		if (Fen.keyflag==2){
			if (Fen.ColisionMob(200, 0, 400, 100, MobR4x, MobR4y, 40, 40) && MobR4Sens==1) MobR4y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobR4x, MobR4y, 40, 40) && MobR4Sens==3) MobR4y=660;
			}

		}
//*****T1
	if (Fen.keyflag>1){
		if (MobT1TrapOn==0) MobT1y=200;
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 3) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1y+40<600) MobT1y+=4;
		if ((MobT1y+40==600 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1y-=1;
		if (MobT1y==200) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
//*****T2
	if (Fen.keyflag>1){
		if (MobT2TrapOn==0) MobT2y=600;
		if (Fen.MobHitBox(MobT2x, MobT2y, 40, 40) && MobT2Hit==0) MobT2Hit=1;
		if (Fen.MobVision(MobT2x, MobT2y, 40, 40, 1) && MobT2TrapOn==0) MobT2TrapOn=1;
		if (MobT2TrapOn==1 && MobT2y>200) MobT2y-=4;
		if ((MobT2y==200 || Fen.Presence(MobT2x, MobT2y, 37, 37)) && MobT2TrapOn==1) MobT2TrapOn=2;
		if (MobT2TrapOn==2) MobT2y+=1;
		if (MobT2y==600) MobT2TrapOn=0;
		if (Fen.Presence(MobT2x, MobT2y, 40, 40) && MobT2Life>0) Fen.Lifey+=1;
		}
}
//*****Fin Mob 
		if (MobR1Life==0 && MobR2Life==0 && MobR3Life==0 && MobR4Life==0) Boss=0;

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=7;
			Fen.Orientation='E';
			Fen.ChangeRoom=0;
			} 
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=5;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=2;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=10;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(600, 600, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;
		else if (Fen.Colision(600, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan7 extends JPanel{
	int MobT1x=50;
	int MobT1y=200;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;
	public Pan7(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(0, Fen.Color128, 0));
		g.fillRect(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());

if (Fen.GameOver==0){   try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>1){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if (MobT1TrapOn==1 && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				}
			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.keyflag==3){
				g.drawImage(MurH, 0, 200, 100, 400, this);
				if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
				}
			if (Fen.AttackOn==0) MobT1HitOn=0;
			} catch (IOException e) {}   
//*****T1
	if (Fen.keyflag>1){
		if (MobT1TrapOn==0) MobT1y=200;
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 3) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1y+40<600) MobT1y+=4;
		if ((MobT1y+40==600 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1y-=1;
		if (MobT1y==200) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=6;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=20;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=11;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 800, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color128>0) Fen.Color128-=1;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan8 extends JPanel{
	int MurFinx=200;

	static int blanc=255;//255
	static int Gris=128;//128

	int Bossx=200;
	int Bossy=0;

	int Arm1x=180; //180
	int Arm1y=200; //200
	int Arm2x=565; //565
	int Arm2y=200; //200
	int ArmMove=0;
	
	int Arm0Attackx=800;
	int Arm0Attacky=0;
	int Arm2Attackx=-200;
	int Arm2Attacky=0;

	int Sens=0;
	int LaserFireOvalLoop=0;
	int LaserFireOval=0;
	int LaserLockx=100;
	int LaserLocky=450;
	int LaserCircleLockx=0;
	int LaserCircleLocky=0;
	int LaserMove=0;

	int BossX=0;
	static int BossLife=780; //780
	int BossHit=0;
	int Vulnerable=0;
	int Aura=0;
	int BossAttack=0;
	static int BossTime=0;
	static int BossTimeOn=0;
	int Phase=0;
	int AuraFinx=50+(int)(Math.random()*151);
	int AuraFiny=50+(int)(Math.random()*151);
	int FinArm1=0;
	int FinArm2=0;
	static int Boss=0;

	int WallOpen=0;
//**ONWORK
	PlayBossFinalMusic playMusicBoss = new PlayBossFinalMusic();
	int MusicOn=0;
	public Pan8(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(blanc, blanc, blanc));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Gris, Gris, Gris));
		g.fillRect(0, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());
		
		if (Fen.keyflag==0 && Fen.Presence(200, 650, 400, 0)){
			Fen.PassCinematic=1;
			Fen.Motion=0;
			if (Fen.TextTimeOn==0){
				Fen.TextTimeOn=1;
				new TextTime().start();
				}
			g.setFont(new Font("Miriam Libre", Font.BOLD, 16));
			g.setColor(Color.black);
			if (Fen.TextTime<30) g.drawString("ESCP pour passer", 20, 780);
			if (1<=Fen.TextTime && Fen.TextTime<5) g.drawString("La Poussiere de Lumiere se trouve juste derriere", 200, 200);
			if (5<=Fen.TextTime && Fen.TextTime<17) g.drawString("Pour liberer le passage,", 310, 200);
			if (7<=Fen.TextTime && Fen.TextTime<17) g.drawString("il te faut trouver les trois Precieux", 254, 230);
			if (10<=Fen.TextTime && Fen.TextTime<17) g.drawString("Le Rubis,", 270, 260);
			if (12<=Fen.TextTime && Fen.TextTime<17) g.drawString("l'Emeraude,", 350, 260);
			if (14<=Fen.TextTime && Fen.TextTime<17) g.drawString("et le Saphir", 460, 260);

			if (17<=Fen.TextTime && Fen.TextTime<25) g.drawString("Prends garde", 350, 200);
			if (19<=Fen.TextTime && Fen.TextTime<25) g.drawString("le Labyrinthe regorge de creatures malfaisantes", 202, 230);
			if (22<=Fen.TextTime && Fen.TextTime<25) g.drawString("et ses murs sont malicieux", 300, 260);
			if (25<=Fen.TextTime && Fen.TextTime<30) g.drawString("Pour te defendre, appuie sur Z, Q, D ou X", 245, 230);
			if (Fen.TextTime>=30){
				Fen.keyflag=1;
				Fen.Motion=1;
				Fen.PassCinematic=0;
				}
			}
		try{
			Image MurFin = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurFin empty all.png"));
			//Image MurFinR00 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurFin R-0-0.png"));
			//Image MurFinRE0 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurFin R-E-0.png"));
			Image MurFinR0S = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurFin R-0-S.png"));
			//Image MurFin0E0 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurFin 0-E-0.png"));
			//Image MurFin0ES = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurFin 0-E-S.png"));
			Image MurFin00S = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurFin 0-0-S.png"));
			Image MurFinRES = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurFin R-E-S.png"));
			if (Fen.Rubis!=2 && Fen.Emeraude!=2 && Fen.Saphir!=2) g.drawImage(MurFin, 200, 50, 400, 100, this);
			//if (Fen.Rubis==2 && Fen.Emeraude!=2 && Fen.Saphir!=2) g.drawImage(MurFinR00, 200, 50, 400, 100, this);
			//if (Fen.Rubis==2 && Fen.Emeraude==2 && Fen.Saphir!=2) g.drawImage(MurFinRE0, 200, 50, 400, 100, this);
			if (Fen.Rubis==2 && Fen.Emeraude!=2 && Fen.Saphir==2 && (Boss==0 || Boss==4)) g.drawImage(MurFinR0S, 200, 50, 400, 100, this);
			//if (Fen.Rubis!=2 && Fen.Emeraude==2 && Fen.Saphir!=2) g.drawImage(MurFin0E0, 200, 50, 400, 100, this);
			//if (Fen.Rubis!=2 && Fen.Emeraude==2 && Fen.Saphir==2) g.drawImage(MurFin0ES, 200, 50, 400, 100, this);
			if (Fen.Rubis!=2 && Fen.Emeraude!=2 && Fen.Saphir==2) g.drawImage(MurFin00S, 200, 50, 400, 100, this);
			if (Fen.Rubis==2 && Fen.Emeraude==2 && Fen.Saphir==2) g.drawImage(MurFinRES, MurFinx, 50, 400, 100, this);

			Image BF = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss Final - Corps.png"));
			Image BFX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss FinalX - Corps.png"));
			//Image BFv = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss Final - Vulnerable.png"));
			//Image BFXv = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss FinalX - Vulnerable.png"));
			//Image BFd = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss Final - Damaged.png"));
			//Image BFXd = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss FinalX - Damaged.png"));
			Image H = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/BF Heart.png"));
			Image HX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/BFX Heart.png"));
			Image Hd = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/BF Heart Damaged.png"));
			Image HXd = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/BFX Heart Damaged.png"));
			Image BFarm3 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss Final - Arm3.png"));
			Image BFarm0 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss Final - Arm0.png"));
			Image BFarm2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss Final - Arm2.png"));
			Image BFXarm3 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss FinalX - Arm3.png"));
			Image BFXarm0 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss FinalX - Arm0.png"));
			Image BFXarm2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Boss FinalX - Arm2.png"));
			if (Boss==1 || Boss==6 || Boss==3){
				if (Boss==1) g.drawImage(BF, Bossx, Bossy, 400, 400, this);
				if (Vulnerable==1 && Boss==1) g.drawImage(H, Bossx+184, Bossy+226, 32, 28, this);
				if (Boss==6 || Boss==3) g.drawImage(BFX, Bossx, Bossy, 400, 400, this);
				if (Vulnerable==1 && Boss==6) g.drawImage(HX, Bossx+169, Bossy+207, 63, 55, this);
				if (BossHit==1 && Vulnerable==1){
					if (Boss==1) g.drawImage(Hd, Bossx+120, Bossy+153, 160, 160, this);
					else if (Boss==6) g.drawImage(HXd, Bossx+120, Bossy+153, 161, 160, this);
					BossLife-=1;
					BossHit=0;
					}
				if (Boss==1){
					g.drawImage(BFarm3, Arm1x, Arm1y, 55, 200, this);
					g.drawImage(BFarm3, Arm2x, Arm2y, 55, 200, this);
					g.drawImage(BFarm0, Arm0Attackx, Arm0Attacky, 200, 55, this);
					g.drawImage(BFarm2, Arm2Attackx, Arm2Attacky, 200, 55, this);
					}
				if (Boss==6 || Boss==3){
					if (FinArm1==0) g.drawImage(BFXarm3, Arm1x, Arm1y, 55, 200, this);
					if (FinArm2==0) g.drawImage(BFXarm3, Arm2x, Arm2y, 55, 200, this);
					g.drawImage(BFXarm0, Arm0Attackx, Arm0Attacky, 200, 55, this);
					g.drawImage(BFXarm2, Arm2Attackx, Arm2Attacky, 200, 55, this);
					}
				if (Fen.Colision(200, 750, 400, 50, 3)) Fen.y=710;
				}


			} catch (IOException e) {}   
if (Fen.GameOver==0){
//*****Boss Final
		if (BossX==1 && MusicOn==0){
			System.out.println("BON MUSIQUE");
			playMusicBoss.resume();
			MusicOn=1;
			}
		
		
		


		//if (MusicOn==1 || MusicOn==3){
			//MusicOn=0;
			//try{
				//File BossFinalMusicFile = new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Musiques/BossFinal.wav");
				//if (BossX==2) BossFinalMusicFile = new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Musiques/BossFinalAccelerated.wav");
				
				//AudioInputStream BossFinalMusic = AudioSystem.getAudioInputStream(BossFinalMusicFile);
				//Clip clip = AudioSystem.getClip();
				//clip.open(BossFinalMusic);
//
				//if ((Boss==2 && blanc==0 && Gris==0) || BossX==2){
				//	clip.start();
				//	clip.loop(Clip.LOOP_CONTINUOUSLY);
				//	}
				//if (BossX==1) clip.stop();
				//if (Fen.Lifey==0) clip.stop();
				//} catch (Exception e){System.out.println(e);}
			//}


		if (Fen.Presence(200, 500, 400, 0) && Boss==0 && BossLife>0 && Fen.Emeraude==1) Boss=2;
		if (Boss==2){
			blanc-=1;
			Gris-=1;
			if (blanc<0) blanc=0;
			if (Gris<0) Gris=0;
			Fen.Motion=0;
			if (blanc==0 && Gris==0){
				Boss=1;
				Vulnerable=0;
				BossX=0;
				Aura=0;
				Phase=0;
				Fen.Motion=1;

				playMusicBoss.start();
				}
			}
		if (Boss==1 || Boss==6){
			//Vulnerable=1;	
			if (BossLife<=390 && BossX==0){ //******Transition X Aura violette, boss + puissant
				BossX=1;
				Aura=0;
				Arm0Attackx=800;
				Arm2Attackx=-200;
				Arm1x=165;
				Arm2x=580;
				Phase=1;
				}
			if (BossX==1){
				Fen.Motion=0;
				if (Fen.y+40<750){
					Fen.y+=15;
					Fen.sens=3;
					}
				if (Phase<4){
					if (Phase==3) Boss=6;
					for(int i=0; i<50*Phase; i++){
						g.setColor(new Color(128, 0, 255));
						g.drawOval(Bossx+125-i-Aura, Bossy+150-i-Aura, 150+2*(i+Aura), 150+2*(i+Aura));
						}
					Aura+=10*Phase;
					if (Aura>600){
						Aura=0;
						Phase+=1;
						}
					}
				if (Phase==4){
					Phase=0;
					Fen.Motion=1;
					BossX=2;
					}
				}
			if ((Fen.MobHitBox(Bossx+120, Bossy+195, 160, 78) || Fen.MobHitBox(Bossx+131, Bossy+273, 137, 13) || Fen.MobHitBox(Bossx+156, Bossy+286, 87, 16) || Fen.MobHitBox(Bossx+183, Bossy+302, 34, 13)) && BossHit==0 && Vulnerable==1){
				BossHit=1;
				}
			if (BossTimeOn==0){
				BossTimeOn=1;
				new Mob4Time().start();
				}
	//****1 11 22 44
			if ((BossAttack==1 || BossAttack==11 || BossAttack==22 || BossAttack==44) && BossX!=1){
				//if (Fen.Colision(200, 400, 400, 10, 1)) Fen.y=410;
				if (Arm1x+55>0) Arm1x-=2+BossX;
				if (Arm2x<800) Arm2x+=2+BossX;
				//if (Fen.Presence(Bossx, Bossy, 400, 400)) Phase=1;
				if (Phase==1){
					for(int i=0; i<50; i++){
						g.setColor(Color.red);
						g.drawOval(Bossx+125-i-Aura, Bossy+150-i-Aura, 150+2*(i+Aura), 150+2*(i+Aura));
						}
					Fen.y+=20;
					Fen.sens=3;
					Aura+=20;
					if (Aura>600){
						Aura=0;
						Phase=0;
						BossHit=0;
						}
					}
				if (Arm1x+55<=0 && Arm2x>=800 && (Phase==0 || Phase==1)){
					Phase=2;
					BossTime=0;
					}
				if (Phase==2){
					if (BossTime<4-BossX/2){
						Arm0Attackx-=1;
						Arm0Attacky=Fen.y-7;
						}
					if (2-BossX/2<BossTime && BossTime<6-BossX/2){
						Arm2Attackx+=1;
						Arm2Attacky=Fen.y-7;
						}
					if (BossTime>=4-BossX/2) Arm0Attackx-=12+4*BossX;
					if (BossTime>=6-BossX/2) Arm2Attackx+=12+4*BossX;
						
					if (Arm0Attackx+200<0 && Arm2Attackx>800){
						Arm0Attackx=800;
						Arm2Attackx=-200;
						BossTime=0;
						BossAttack=0;
						Arm1x=165;
						Arm2x=580;
						Phase=0;
						}
					if (Fen.Presence(Arm0Attackx, Arm0Attacky+26, 11, 3)) Fen.Lifey+=2;
					if (Fen.Presence(Arm0Attackx+40, Arm0Attacky, 22, 55) || Fen.Presence(Arm0Attackx+30, Arm0Attacky+3, 10, 49) || Fen.Presence(Arm0Attackx+19, Arm0Attacky+12, 11, 30)) Fen.Lifey+=1;
					if (Fen.Presence(Arm2Attackx+189, Arm2Attacky+26, 11, 3)) Fen.Lifey+=2;  
					if (Fen.Presence(Arm2Attackx+138, Arm2Attacky, 22, 55) || Fen.Presence(Arm2Attackx+160, Arm2Attacky+3, 10, 49) || Fen.Presence(Arm2Attackx+170, Arm2Attacky+12, 11, 30)) Fen.Lifey+=1;
					}
					
				}
	//***2  20 40 60 80
			else if ((BossAttack==2 || BossAttack==20 || BossAttack==40 || BossAttack==60 || BossAttack==80) && BossX!=1){
				if (BossX==0) g.setColor(Color.red);
				else if (BossX==2) g.setColor(new Color(128, 0, 255));
				if (Phase==0){
					Phase=1;
					BossTime=0;
					Sens=(int)(Math.random()*2);
					if (Sens==0) LaserLockx=150;
					else if (Sens==1) LaserLockx=650;
					}
				if (BossTime<4){
					Vulnerable=1;
					for(int i=1; i<=12; i++){
						g.drawLine(Bossx+163+i, Bossy+35+i, LaserLockx, LaserLocky); 
						g.drawLine(Bossx+225+i, Bossy+35+i, LaserLockx, LaserLocky); 
						}
					g.fillOval(LaserLockx-15-LaserFireOval, LaserLocky-15-LaserFireOval, 30+2*LaserFireOval, 30+2*LaserFireOval);
					if (Fen.Presence(LaserLockx-15-LaserFireOval, LaserLocky-15-LaserFireOval, 30+2*LaserFireOval, 30+2*LaserFireOval)) Fen.Lifey+=1;
					if (LaserFireOvalLoop<5) LaserFireOval+=1+BossX;
					if (LaserFireOvalLoop>=5) LaserFireOval-=1;
					if (LaserFireOvalLoop==9){
						LaserFireOvalLoop=0;
						LaserFireOval=0;
						}
					LaserFireOvalLoop+=1;
					if (Sens==0) LaserLockx+=3;
					if (Sens==1) LaserLockx-=3;
					if (LaserLocky<Fen.y+20) LaserLocky+=3;
					else if (LaserLocky>Fen.y+20) LaserLocky-=3;
					}
				if (BossTime==4){
					Phase=0;
					BossTime=0;
					BossAttack=0;
					}
				}
	//****3 33 66
			else if ((BossAttack==3 || BossAttack==33 || BossAttack==66) && BossX!=1){   	
				if (BossX==0) g.setColor(Color.red);
				else if (BossX==2) g.setColor(new Color(128, 0, 255));
				if (Phase==0){
					g.drawOval(Bossx+128+Aura, Bossy+Aura, 80-2*Aura, 80-2*Aura); // (168, 39)
					g.drawOval(Bossx+190+Aura, Bossy+Aura, 80-2*Aura, 80-2*Aura);
					BossTime=0;
					Aura+=1;
					if (Aura==60){
						Phase=1;
						LaserCircleLockx=Fen.x+20;
						LaserCircleLocky=Fen.y+20;
						}
					}
				if (BossTime<5 && Phase==1){
					Vulnerable=1;
					for(int i=1; i<=12; i++){
						g.drawLine(Bossx+163+i, Bossy+35+i, LaserLockx, LaserLocky); 
						g.drawLine(Bossx+225+i, Bossy+35+i, LaserLockx, LaserLocky); 
						}
					g.fillOval(LaserLockx-15-LaserFireOval, LaserLocky-15-LaserFireOval, 30+2*LaserFireOval, 30+2*LaserFireOval);
					if (Fen.Presence(LaserLockx-15-LaserFireOval, LaserLocky-15-LaserFireOval, 30+2*LaserFireOval, 30+2*LaserFireOval)) Fen.Lifey+=1;
					if (LaserFireOvalLoop<5) LaserFireOval+=1+BossX;
					if (LaserFireOvalLoop>=5) LaserFireOval-=1;
					if (LaserFireOvalLoop==9){
						LaserFireOvalLoop=0;
						LaserFireOval=0;
						}
					LaserFireOvalLoop+=1;
					LaserLockx=LaserCircleLockx+(int)(80*Math.cos((LaserMove*3.1416)/(30/(BossX+1))));
					LaserLocky=LaserCircleLocky+(int)(80*Math.sin((LaserMove*3.1416)/(30/(BossX+1))));
					LaserMove+=1;
					}
				if (BossTime==5){
					Aura=0;
					Phase=0;
					LaserMove=0;
					BossTime=0;
					BossAttack=0;
					}
				}
	//****4
			else if (BossAttack==4 && BossX!=1){		
				if (BossX==0) g.setColor(Color.red);
				else if (BossX==2) g.setColor(new Color(128, 0, 255));
				if (Phase==0){
					g.drawOval(Bossx+119+Aura, Bossy+156+Aura, 160-2*Aura, 160-2*Aura); // (199, 236)
					BossTime=0;
					Aura+=1;
					LaserLockx=188; //188
					if (BossX==0) LaserLocky=300; //300		
					else if (BossX==2) LaserLocky=280;
					if (Aura==80) Phase=1;
					}
				if (Phase==1 && BossTime<9){
					Vulnerable=1;
					int A=-1;
					int Touched=0;
					if (LaserLocky<750){
						if (BossX==0){
							for(int j=0; j<24; j++){
									if (j%2==0) A+=1;
									//System.out.println(j+"\t"+A+"\t"+(Bossy+228+j));
								for(int i=A; i<24-A; i++){
									g.drawLine(Bossx+188+i, Bossy+228+j, LaserLockx+i, LaserLocky+j);
									if (Fen.Presence(LaserLockx+i, LaserLocky+j, 1, 1)) Touched=1;
									else Touched=0;
									//g.setColor(Color.white);
									//g.fillRect(LaserLockx+i, LaserLocky+j, 1, 1);
									}
								}
							}
						else if (BossX==2){
							for(int j=0; j<53; j++){
									if (j%2==0) A+=1;
									//System.out.println(j+"\t"+A+"\t"+(Bossy+228+j));
								for(int i=A; i<53-A; i++){
									g.drawLine(Bossx+174+i, Bossy+208+j, LaserLockx+i, LaserLocky+j);
									if (Fen.Presence(LaserLockx+i, LaserLocky+j, 1, 1)) Touched=1;
									//else Touched=0;
									}
								}
							}
						LaserLockx+=10+5*BossX;
						if (LaserLockx>=600 && BossX==0){
							LaserLockx=188;
							LaserLocky+=100;
							}
						else if (LaserLockx>=600 && BossX==2){
							LaserLockx=147;
							LaserLocky+=50;
							}
						}
					if (Touched==1) Fen.Lifey+=3+BossX;
					}
				if (BossTime==9){
					Aura=0;
					BossTime=0;
					Phase=0;
					BossAttack=0;
					}
				}
	//****5 55
			else if ((BossAttack==5 || BossAttack==55) && BossX!=1){
				if (BossX==0) g.setColor(Color.red);
				else if (BossX==2) g.setColor(new Color(128, 0, 255));
				Vulnerable=1;
				if (Phase==0){
					Sens=(int)(Math.random()*2);
					Phase=1;
					BossTime=0;
					if (Sens==0) LaserLockx=200;
					else if (Sens==1) LaserLockx=600;
					}
				if (BossTime<2) LaserLocky=Fen.y+20;
				for(int i=1; i<=12; i++){
					g.drawLine(Bossx+163+i, Bossy+35+i, LaserLockx, LaserLocky); 
					g.drawLine(Bossx+225+i, Bossy+35+i, LaserLockx, LaserLocky); 
					}
				g.fillOval(LaserLockx-15-LaserFireOval, LaserLocky-15-LaserFireOval, 30+2*LaserFireOval, 30+2*LaserFireOval);
				if (Fen.Presence(LaserLockx-15-LaserFireOval, LaserLocky-15-LaserFireOval, 30+2*LaserFireOval, 30+2*LaserFireOval)) Fen.Lifey+=1;
				if (LaserFireOvalLoop<5) LaserFireOval+=1+BossX;
				if (LaserFireOvalLoop>=5) LaserFireOval-=1;
				if (LaserFireOvalLoop==9){
					LaserFireOvalLoop=0;
					LaserFireOval=0;
					}
				LaserFireOvalLoop+=1;
				if (1<BossTime && BossTime<4){
					if (Sens==0 && LaserLockx<600) LaserLockx+=10+5*BossX;
					else if (Sens==1 && LaserLockx>200) LaserLockx-=10+5*BossX;
					}
				if (BossTime==4){
					Phase=0;
					BossTime=0;
					BossAttack=0;
					}
				}
		//****7  Attaque "DJAYAN"
			else if (BossAttack==7 && BossX!=1){
				if (BossX==0) g.setColor(Color.red);
				else if (BossX==2) g.setColor(new Color(128, 0, 255));
				//Vulnerable=1;
				for(int i=1; i<=12; i++){
					g.drawLine(Bossx+163+i, Bossy+35+i, LaserLockx, LaserLocky); 
					g.drawLine(Bossx+225+i, Bossy+35+i, LaserLockx, LaserLocky); 
					}
				g.fillOval(LaserLockx-15-LaserFireOval, LaserLocky-15-LaserFireOval, 30+2*LaserFireOval, 30+2*LaserFireOval);
				if (Fen.Presence(LaserLockx-15-LaserFireOval, LaserLocky-15-LaserFireOval, 30+2*LaserFireOval, 30+2*LaserFireOval)) Fen.Lifey+=1;
				if (LaserFireOvalLoop<5) LaserFireOval+=1+BossX;
				if (LaserFireOvalLoop>=5) LaserFireOval-=1;
				if (LaserFireOvalLoop==9){
					LaserFireOvalLoop=0;
					LaserFireOval=0;
					}
				LaserFireOvalLoop+=1;
				//System.out.println(Phase+"\t"+LaserLockx+"\t"+LaserLocky);
				if (Phase==0){
					LaserLockx=200;
					LaserLocky=750;
					LaserMove=0;
					Phase=1;
					}
				else if (Phase==1){   //***D
					if (LaserLocky>400) LaserLocky-=10;
					if (LaserLocky<=400) Phase=2;
					}
				else if (Phase==2){
					LaserLocky+=5;
					LaserMove+=1;
					LaserLockx=200+(int)(400*Math.sin((LaserMove*3.14)/68));
					if (LaserLocky==750) Phase=3;
					}
				else if (Phase==3){     //****J
					LaserLockx=200;
					LaserLocky=400;
					LaserMove=0;
					Phase=4;
					}
				else if (Phase==4){
					LaserLockx+=10;
					if (LaserLockx>=600){
						Phase=5;
						LaserLockx=400;
						}
					}
				else if (Phase==5){
					LaserLocky=400+(int)(350*Math.sin((LaserMove*3.14)/50));
					LaserMove+=1;
					if (LaserLocky>=600) LaserLockx-=5;
					if (LaserLockx<=245 && LaserLocky<=600){
						Phase=6;
						LaserLockx=200;
						LaserLocky=750;
						}
					}
				else if (Phase==6){     //****A
					LaserLockx+=10;
					if (LaserLockx<400) LaserLocky-=17;
					else if (LaserLockx>400) LaserLocky+=17;
					if (LaserLockx==600 && LaserLocky>=750){
						Phase=7;
						LaserLockx=300;
						LaserLocky=575;
						}
					}
				else if (Phase==7){
					LaserLockx+=10;
					if (LaserLockx==500){
						LaserLockx=200;
						LaserLocky=400;
						Phase=8;
						}
					}
				else if (Phase==8){	//****Y
					LaserLockx+=10;
					if (LaserLockx<400) LaserLocky+=7;
					else if (LaserLockx>400) LaserLocky-=7;
					if (LaserLockx>=600 && LaserLocky<=400){
						Phase=9;
						LaserLockx=400;
						LaserLocky=575;
						}
					}
				else if (Phase==9){
					LaserLocky+=7;
					if (LaserLocky>=750){
						LaserLockx=600;
						LaserLocky=750;
						Phase=10;
						}
					}
				else if (Phase==10){     //****A
					LaserLockx-=10;
					if (LaserLockx>400) LaserLocky-=17;
					else if (LaserLockx<400) LaserLocky+=17;
					if (LaserLockx<=200 && LaserLocky>=750){
						Phase=11;
						LaserLockx=500;
						LaserLocky=575;
						}
					}
				else if (Phase==11){
					LaserLockx-=10;
					if (LaserLockx==300){
						LaserLockx=200;
						LaserLocky=750;
						Phase=12;
						}
					}
				else if (Phase==12){	//***N
					LaserLocky-=15;
					if (LaserLocky==405){
						LaserLocky=400;
						Phase=13;
						}
					}
				else if (Phase==13){
					if (LaserLockx<600){
						LaserLocky+=17;
						LaserLockx+=20;
						}
					if (LaserLockx==600) LaserLocky-=15;
					if (LaserLockx==600 && LaserLocky<=400){
						Phase=0;
						BossAttack=0;
						}
					}
				}	
		//****0
			//if (BossAttack!=1 && BossAttack!=11 && BossAttack!=22 && BossAttack!=2 && BossAttack!=20 && BossAttack!=40 && BossAttack!=60 && BossAttack!=80 && BossAttack!=3 && BossAttack!=33 && BossAttack!=4 && BossX!=1){
			else if (BossX!=1){
				if ((BossHit==1 || Fen.Presence(Bossx, Bossy, 400, 270)) && Phase==0){//****Aura Repousse
					Phase=1;
					Fen.Lifey+=1;
					}
				Vulnerable=0;
				if (Phase==1){
					for(int i=0; i<50; i++){
						g.setColor(Color.red);
						g.drawOval(Bossx+125-i-Aura, Bossy+150-i-Aura, 150+2*(i+Aura), 150+2*(i+Aura));
						}
					Fen.y+=20;
					Fen.sens=3;
					Aura+=20;
					if (Aura>600){
						Aura=0;
						Phase=0;
						BossHit=0;
						}
					}
				if (Phase==0){
					Arm1y=Arm2y+=(int)(4*Math.cos((ArmMove*3.1416)/30));
					ArmMove+=1;
					BossAttack=(int)(Math.random()*101);
					}
				}
			if (BossLife<=0 && Boss==6){
				Boss=3;
				Vulnerable=0;
				Phase=0;
				Aura=0;
				Arm0Attackx=900;
				Arm2Attackx=900;
				Arm1x=165;
				Arm2x=580;
				BossTime=0;
				Fen.Motion=0;

				playMusicBoss.resume();
				}
			}
		if (Boss==3 || Boss==4){
			int color=(int)(Math.random()*2);
			if (color==0) g.setColor(Color.red);
			else if (color==1) g.setColor(new Color(128, 0, 255));
			if (Phase<12){
				for(int i=0; i<20; i++){
					g.drawOval(Bossx+AuraFinx-i-Aura, Bossy+AuraFiny-i-Aura, 50+2*(i+Aura), 50+2*(i+Aura));
					}
				Aura+=5;
				if (Aura>100){
					Aura=0;
					AuraFinx=50+(int)(Math.random()*151);
					AuraFiny=50+(int)(Math.random()*151);
					Phase+=1;
					}
				}
			if (12<=Phase && Phase<20){
				Bossx=199+(int)(Math.random()*3);
				Bossy=(int)(Math.random()*3);
				//g.setColor(new Color(128, 0, 255));
				for(int i=0; i<50; i++){
					g.drawOval(Bossx+AuraFinx-i-Aura, Bossy+AuraFiny-i-Aura, 100+2*(i+Aura), 100+2*(i+Aura));
					}
				Aura+=10;
				if (Aura>250){
					Aura=0;
					AuraFinx=50+(int)(Math.random()*351);
					AuraFiny=50+(int)(Math.random()*351);
					Phase+=1;
					}
				}
			if (Phase>=20){ //**** Explosions finales
				//g.setColor(new Color(128, 0, 255));
				if (Phase==20){
					for(int i=0; i<120; i++){
						g.drawOval(Arm1x-i-Aura, Arm1y+77-i-Aura, 54+2*(i+Aura), 54+2*(i+Aura));
						}
					}
				if (Phase==21){
					for(int i=0; i<120; i++){
						g.drawOval(Arm2x-i-Aura, Arm2y+77-i-Aura, 54+2*(i+Aura), 54+2*(i+Aura));
						}
					}
				if (Phase==22){
					for(int i=0; i<150; i++){
						g.drawOval(Bossx+125-i-Aura, Bossy+150-i-Aura, 150+2*(i+Aura), 150+2*(i+Aura));
						}
					}
				if (Phase==20) FinArm1=1;
				if (Phase==21) FinArm2=1;
				if (Phase==22) Boss=4;
				Aura+=40;
				if (Aura>700){
					Aura=0;
					Phase+=1;
					}
				}
			}
		if (Boss==4){ //**Retour écran normal
			if (Gris<128) Gris+=1;
			if (blanc<255) blanc+=1;
			if (Gris==128 && blanc==255){
				Boss=0;
				Fen.Motion=1;
				}
			}
	
	//int Arm1x=165;
	//int Arm1y=190;
	//int Arm2x=580;
	//int Arm2y=190;
}
if (Fen.GameOver==1) playMusicBoss.resume();
//***** Fin Boss Final  (530 Lignes)  

		//System.out.println(WallOpen);
		if (Fen.Rubis==2 && Fen.Saphir==2 && Fen.Emeraude==2 && WallOpen==0){
			WallOpen=1;
			Aura=0;
			Phase=0;
			Fen.Motion=0;
			}
		if (WallOpen>=1 && WallOpen<=14){
			if (4<=WallOpen && WallOpen<13){
				g.setColor(Color.red);
				for(int i=0; i<Aura; i++){
					g.drawOval(MurFinx+35-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
					}
				g.setColor(Color.green);
				for(int i=0; i<Aura; i++){
					g.drawOval(MurFinx+169-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
					}
				g.setColor(Color.blue);
				for(int i=0; i<Aura; i++){
					g.drawOval(MurFinx+303-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
					}
				}
			if (WallOpen==13){
				g.setColor(Color.red);
				for(int i=0; i<10; i++){
					g.drawOval(MurFinx+35-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
					}
				g.setColor(Color.green);
				for(int i=0; i<10; i++){
					g.drawOval(MurFinx+169-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
					}
				g.setColor(Color.blue);
				for(int i=0; i<10; i++){
					g.drawOval(MurFinx+303-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
					}
				}
			if (Phase<10) Aura+=1;
			if (Phase>=10) Aura-=1;
			if (WallOpen==13) Aura-=3;
			if (Phase==19){
				Phase=0;
				Aura=0;
				WallOpen+=1;
				}
			Phase+=1;
			}
		if (WallOpen==15){
			Fen.Motion=1;
			g.setColor(Color.white);
			g.fillRect(MurFinx+96, 50, 74, 100);
			g.fillRect(MurFinx+230, 50, 74, 100);
			if (Fen.Colision(MurFinx, 50, 96, 100, 1)) Fen.y=150;
			if (Fen.Colision(MurFinx, 50, 96, 100, 3)) Fen.y=10;
			if (Fen.Colision(MurFinx, 50, 96, 100, 0)) Fen.x=296;
			if (Fen.Colision(MurFinx+170, 50, 60, 100, 1)) Fen.y=150;
			if (Fen.Colision(MurFinx+170, 50, 60, 100, 3)) Fen.y=10;
			if (Fen.Colision(MurFinx+170, 50, 60, 100, 0)) Fen.x=430;
			if (Fen.Colision(MurFinx+170, 50, 60, 100, 2)) Fen.x=330;
			if (Fen.Colision(MurFinx+304, 50, 96, 100, 1)) Fen.y=150;
			if (Fen.Colision(MurFinx+304, 50, 96, 100, 3)) Fen.y=10;
			if (Fen.Colision(MurFinx+304, 50, 96, 100, 2)) Fen.x=464;
			}

			
/*		if (WallOpen>=1 && WallOpen<18){
*			if (WallOpen>=4){
*				g.setColor(Color.red);
*				for(int i=0; i<Aura; i++){
*					g.drawOval(MurFinx+35-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
*					}
*				g.setColor(Color.green);
*				for(int i=0; i<Aura; i++){
*					g.drawOval(MurFinx+169-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
*					}
*				g.setColor(Color.blue);
*				for(int i=0; i<Aura; i++){
*					g.drawOval(MurFinx+303-i-Aura, 70-i-Aura, 60+2*(i+Aura), 60+2*(i+Aura));
*					}
*				}
*			if (Phase<10) Aura+=1;
*			if (Phase>=10) Aura-=1;
*			if (Phase==19){
*				Phase=0;
*				Aura=0;
*				WallOpen+=1;
*				if (WallOpen==11) MurFinx+=50;
*				if (WallOpen==12) MurFinx+=50;
*				if (WallOpen==13) MurFinx+=50;
*				if (WallOpen==14) MurFinx+=50;
*				if (WallOpen==15) MurFinx+=50;
*				if (WallOpen==16) MurFinx+=50;
*				if (WallOpen==17) MurFinx+=50;
*				if (WallOpen==18){
*					MurFinx+=50;
*					Fen.Motion=1;
*					}
*				}
*			Phase+=1;
*			}
*/			

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=4;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=12;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 

		if (Fen.Colision(0, 0, 200, 800, 0)) Fen.x=200;
		else if (Fen.Colision(600, 0, 200, 800, 2)) Fen.x=560;
		else if (Fen.Rubis!=2 || Fen.Emeraude!=2 || Fen.Saphir!=2) if (Fen.Colision(200, 50, 400, 100, 1)) Fen.y=150;

		g.setFont(new Font("Arial", Font.BOLD, 18));
		if (Fen.Presence(240, 151, 45, 0) && Fen.Rubis==1){
			if (Fen.Interaction==2){
				Fen.Rubis=2;
				Fen.keyflag+=1;
				}
			Fen.Interaction=1;
			g.setColor(Color.green);
			g.fillOval(255, 120, 20, 20);
			g.setColor(Color.black);
			g.drawString("A", 259, 136);
			}
		else if (Fen.Presence(375, 151, 45, 0) && Fen.Emeraude==1 && Boss==0){
			if (Fen.Interaction==2){
				Fen.Emeraude=2;
				Fen.keyflag+=1;
				}
			Fen.Interaction=1;
			g.setColor(Color.green);
			g.fillOval(390, 120, 20, 20);
			g.setColor(Color.black);
			g.drawString("A", 394, 136);
			}
		else if (Fen.Presence(506, 151, 44, 0) && Fen.Saphir==1){
			if (Fen.Interaction==2){
				Fen.Saphir=2;
				Fen.keyflag+=1;
				}
			Fen.Interaction=1;
			g.setColor(Color.green);
			g.fillOval(523, 120, 20, 20);
			g.setColor(Color.black);
			g.drawString("A", 527, 136);
			}
		else Fen.Interaction=0;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			//try{
				//Thread.sleep(0);
				//} catch (InterruptedException e) {}
			}

		if (Boss==1 || Boss==6){
			g.setColor(Color.darkGray);
			g.fillRect(791, 769, -782, 22);
			g.setColor(Color.red);
			g.fillRect(790, 770, -BossLife, 20);
			}

		if (Boss==0) g.setColor(Color.black);
		else g.setColor(Color.darkGray);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.GameOverColor<255) Fen.GameOverColor+=1;
			g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		//try{
			//Thread.sleep(0);
			//} catch (InterruptedException e) {}
		}
	}
class Pan9 extends JPanel{
	static int pink=175;

	int MobR1x=350;
	int MobR1y=201;
	static int MobR1Sens=0;
	static int MobR1TimeOn=0;
	static int MobR1Afraid=0;
	int MobR1Hit=0;
	int MobR1HitOn=0;
	static int MobR1Life=2;
	public Pan9(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color255, pink, pink));
		g.fillRect(0, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRond.png"));
			Image MobRA = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondAfraid.png"));
			Image MobRD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondDamaged.png"));
			if (Fen.keyflag>0){
				if (MobR1Life==2) g.drawImage(MobR, MobR1x, MobR1y, 40, 40, this);
				else if (MobR1Life==1) g.drawImage(MobRA, MobR1x, MobR1y, 40, 40, this);
				if (MobR1Hit==1 && MobR1Life>0){
					g.drawImage(MobRD, MobR1x, MobR1y, 40, 40, this);
					if (MobR1HitOn==0){
						MobR1Life-=1;
						MobR1HitOn=1;
						}
					MobR1Hit=0;
					}
				}
			if (Fen.keyflag==1){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				}
			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.AttackOn==0) MobR1HitOn=0;
			} catch (IOException e) {}   
//****R1 Mob1
	if (Fen.keyflag>0){
		if (MobR1Life==1) MobR1Afraid=1;
		else MobR1Afraid=0;
		if (Fen.MobHitBox(MobR1x, MobR1y, 40, 40) && MobR1Hit==0){
			MobR1Hit=1;
			if (Fen.sens==0) MobR1x-=10;
			if (Fen.sens==1) MobR1y-=10;
			if (Fen.sens==2) MobR1x+=10;
			if (Fen.sens==3) MobR1y+=10;
			}
		if (MobR1TimeOn==0){
			MobR1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobR1Sens==0) MobR1x-=1+2*MobR1Afraid;
		if (MobR1Sens==1) MobR1y-=1+2*MobR1Afraid;
		if (MobR1Sens==2) MobR1x+=1+2*MobR1Afraid;
		if (MobR1Sens==3) MobR1y+=1+2*MobR1Afraid;
		if (Fen.ColisionMob(0, 0, 200, 800, MobR1x, MobR1y, 40, 40) && MobR1Sens==0) MobR1x=200;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==2) MobR1x=560;
		if (Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=200;
		if (Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=560;
		if ((MobR1x<0 && MobR1Sens==0) || (MobR1x+40>800 && MobR1Sens==2) || (MobR1y<0 && MobR1Sens==1) || (MobR1y>800 && MobR1Sens==3)) MobR1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR1x, MobR1y, 40, 40) && MobR1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1) if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
		if (Fen.keyflag==2) if (Fen.ColisionMob(200, 700, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=660;

		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=10;
			Fen.Orientation='E';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=5;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=13;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 800, 0)) Fen.x=200;
		else if (Fen.Colision(600, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(600, 600, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(600, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (pink>0) pink-=1;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan10 extends JPanel{
	int MobR1x=350;
	int MobR1y=201;
	static int MobR1Sens=0;
	static int MobR1TimeOn=0;
	static int MobR1Afraid=0;
	int MobR1Hit=0;
	int MobR1HitOn=0;
	static int MobR1Life=2;

	int MobR2x=550;
	int MobR2y=401;
	static int MobR2Sens=0;
	static int MobR2TimeOn=0;
	static int MobR2Afraid=0;
	int MobR2Hit=0;
	int MobR2HitOn=0;
	static int MobR2Life=2;

	int MobT1x=290;
	int MobT1y=160;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	int MobT2x=380;
	int MobT2y=600;
	int MobT2TrapOn=0;
	int MobT2Hit=0;
	int MobT2HitOn=0;
	static int MobT2Life=3;

	int MobT3x=470;
	int MobT3y=160;
	int MobT3TrapOn=0;
	int MobT3Hit=0;
	int MobT3HitOn=0;
	static int MobT3Life=3;

	int MobT4x=560;
	int MobT4y=600;
	int MobT4TrapOn=0;
	int MobT4Hit=0;
	int MobT4HitOn=0;
	static int MobT4Life=3;

	int MobT5x=200;
	int MobT5y=380;
	int MobT5TrapOn=0;
	int MobT5Hit=0;
	int MobT5HitOn=0;
	static int MobT5Life=3;

	static int MobP1x=400;
	static int MobP1y=600;
	static int MobP1Sens=0;
	static int MobP1TimeOn=0;
	int MobP1Hit=0;
	int MobP1HitOn=0;
	static int MobP1Life=4;

	static int Boss=2;
	public Pan10(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color255, Fen.Color128, 0));
		g.fillRect(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRond.png"));
			Image MobRA = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondAfraid.png"));
			Image MobRD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondDamaged.png"));
			if (Fen.keyflag>0){
				if (MobR1Life==2) g.drawImage(MobR, MobR1x, MobR1y, 40, 40, this);
				else if (MobR1Life==1) g.drawImage(MobRA, MobR1x, MobR1y, 40, 40, this);
				if (MobR1Hit==1 && MobR1Life>0){
					g.drawImage(MobRD, MobR1x, MobR1y, 40, 40, this);
					if (MobR1HitOn==0){
						MobR1Life-=1;
						MobR1HitOn=1;
						}
					MobR1Hit=0;
					}
				if (MobR2Life==2) g.drawImage(MobR, MobR2x, MobR2y, 40, 40, this);
				else if (MobR2Life==1) g.drawImage(MobRA, MobR2x, MobR2y, 40, 40, this);
				if (MobR2Hit==1 && MobR2Life>0){
					g.drawImage(MobRD, MobR2x, MobR2y, 40, 40, this);
					if (MobR2HitOn==0){
						MobR2Life-=1;
						MobR2HitOn=1;
						}
					MobR2Hit=0;
					}
				}
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>1){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if (MobT1TrapOn==1 && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				if (MobT2Life>0) g.drawImage(MobT, MobT2x, MobT2y, 40, 40, this);
				if (MobT2TrapOn==1 && MobT2Life>0) g.drawImage(MobTX, MobT2x, MobT2y, 40, 40, this);
				if (MobT2Hit==1 && MobT2Life>0){
					g.drawImage(MobTD, MobT2x, MobT2y, 40, 40, this);
					if (MobT2HitOn==0){
						MobT2Life-=1;
						MobT2HitOn=1;
						}
					MobT2Hit=0;
					}
				if (MobT3Life>0) g.drawImage(MobT, MobT3x, MobT3y, 40, 40, this);
				if (MobT3TrapOn==1 && MobT3Life>0) g.drawImage(MobTX, MobT3x, MobT3y, 40, 40, this);
				if (MobT3Hit==1 && MobT3Life>0){
					g.drawImage(MobTD, MobT3x, MobT3y, 40, 40, this);
					if (MobT3HitOn==0){
						MobT3Life-=1;
						MobT3HitOn=1;
						}
					MobT3Hit=0;
					}
				if (MobT4Life>0) g.drawImage(MobT, MobT4x, MobT4y, 40, 40, this);
				if (MobT4TrapOn==1 && MobT4Life>0) g.drawImage(MobTX, MobT4x, MobT4y, 40, 40, this);
				if (MobT4Hit==1 && MobT4Life>0){
					g.drawImage(MobTD, MobT4x, MobT4y, 40, 40, this);
					if (MobT4HitOn==0){
						MobT4Life-=1;
						MobT4HitOn=1;
						}
					MobT4Hit=0;
					}
				if (MobT5Life>0) g.drawImage(MobT, MobT5x, MobT5y, 40, 40, this);
				if (MobT5TrapOn==1 && MobT5Life>0) g.drawImage(MobTX, MobT5x, MobT5y, 40, 40, this);
				if (MobT5Hit==1 && MobT5Life>0){
					g.drawImage(MobTD, MobT5x, MobT5y, 40, 40, this);
					if (MobT5HitOn==0){
						MobT5Life-=1;
						MobT5HitOn=1;
						}
					MobT5Hit=0;
					}
				}
			Image MobP = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPenta.png"));
			Image MobPX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaX.png"));
			Image MobPD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaDamaged.png"));
			if (Fen.keyflag>2){
				if (Fen.MobVision(MobP1x, MobP1y, 40, 40, MobP1Sens) && MobP1Life>0) g.drawImage(MobPX, MobP1x, MobP1y, 40, 40, this);
				else if (MobP1Life>0) g.drawImage(MobP, MobP1x, MobP1y, 40, 40, this);
				if (MobP1Hit==1 && MobP1Life>0){
					g.drawImage(MobPD, MobP1x, MobP1y, 40, 40, this);
					if (MobP1HitOn==0){
						MobP1Life-=1;
						MobP1HitOn=1;
						}
					MobP1Hit=0;
					}
				}
			if (Fen.keyflag==1){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				g.drawImage(MurH, 700, 200, 100, 400, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				if (Fen.Colision(700, 200, 100, 400, 2)) Fen.x=660;
				}
			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				if (Boss==1){
					g.drawImage(MurH, 0, 200, 100, 400, this);
					g.drawImage(MurH, 700, 200, 100, 400, this);
					if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
					if (Fen.Colision(700, 200, 100, 400, 2)) Fen.x=660;
					}
				}
			if (Fen.keyflag==3){
				g.drawImage(MurH, 0, 200, 100, 400, this);
				g.drawImage(MurH, 700, 200, 100, 400, this);
				if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
				if (Fen.Colision(700, 200, 100, 400, 2)) Fen.x=660;
				}
			if (Fen.AttackOn==0){
				MobR1HitOn=0;
				MobR2HitOn=0;
				MobT1HitOn=0;
				MobT2HitOn=0;
				MobT3HitOn=0;
				MobT4HitOn=0;
				MobT5HitOn=0;
				MobP1HitOn=0;
				}
			} catch (IOException e) {}   
//****R1 Mob1
	if (Fen.keyflag>0){
		if (MobR1Life==1) MobR1Afraid=1;
		else MobR1Afraid=0;
		if (Fen.MobHitBox(MobR1x, MobR1y, 40, 40) && MobR1Hit==0){
			MobR1Hit=1;
			if (Fen.sens==0) MobR1x-=10;
			if (Fen.sens==1) MobR1y-=10;
			if (Fen.sens==2) MobR1x+=10;
			if (Fen.sens==3) MobR1y+=10;
			}
		if (MobR1TimeOn==0){
			MobR1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobR1Sens==0) MobR1x-=1+2*MobR1Afraid;
		if (MobR1Sens==1) MobR1y-=1+2*MobR1Afraid;
		if (MobR1Sens==2) MobR1x+=1+2*MobR1Afraid;
		if (MobR1Sens==3) MobR1y+=1+2*MobR1Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==1) MobR1y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==0) MobR1x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==3) MobR1y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==2) MobR1x=560;
		if ((MobR1x<0 && MobR1Sens==0) || (MobR1x+40>800 && MobR1Sens==2) || (MobR1y<0 && MobR1Sens==1) || (MobR1y>800 && MobR1Sens==3)) MobR1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR1x, MobR1y, 40, 40) && MobR1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1){
			if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==2) MobR1x=660;
			}
		if (Fen.keyflag==2){
			if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=660;
			if (Boss==1){
				if (Fen.ColisionMob(0, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==0) MobR1x=100;
				if (Fen.ColisionMob(700, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==2) MobR1x=660;
				}
			}
		if (Fen.keyflag==3){
			if (Fen.ColisionMob(0, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==0) MobR1x=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobR1x, MobR1y, 40, 40) && MobR1Sens==2) MobR1x=660;
			}
		}
//****R2 Mob2
	if (Fen.keyflag>0){
		if (MobR2Life==1) MobR2Afraid=1;
		else MobR2Afraid=0;
		if (Fen.MobHitBox(MobR2x, MobR2y, 40, 40) && MobR2Hit==0){
			MobR2Hit=1;
			if (Fen.sens==0) MobR2x-=10;
			if (Fen.sens==1) MobR2y-=10;
			if (Fen.sens==2) MobR2x+=10;
			if (Fen.sens==3) MobR2y+=10;
			}
		if (MobR2TimeOn==0){
			MobR2TimeOn=1;
			new Mob2Time().start();
			}
		if (MobR2Sens==0) MobR2x-=1+2*MobR2Afraid;
		if (MobR2Sens==1) MobR2y-=1+2*MobR2Afraid;
		if (MobR2Sens==2) MobR2x+=1+2*MobR2Afraid;
		if (MobR2Sens==3) MobR2y+=1+2*MobR2Afraid;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==1) MobR2y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==0) MobR2x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==3) MobR2y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==2) MobR2x=560;
		if ((MobR2x<0 && MobR2Sens==0) || (MobR2x+40>800 && MobR2Sens==2) || (MobR2y<0 && MobR2Sens==1) || (MobR2y>800 && MobR2Sens==3)) MobR2Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR2x, MobR2y, 40, 40) && MobR2Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==1){
			if (Fen.ColisionMob(200, 0, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==1) MobR2y=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==2) MobR2x=660;
			}
		if (Fen.keyflag==2){
			if (Fen.ColisionMob(200, 0, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==1) MobR2y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==3) MobR2y=660;
			if (Boss==1){
				if (Fen.ColisionMob(0, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==0) MobR2x=100;
				if (Fen.ColisionMob(700, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==2) MobR2x=660;
				}
			}
		if (Fen.keyflag==3){
			if (Fen.ColisionMob(0, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==0) MobR2x=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobR2x, MobR2y, 40, 40) && MobR2Sens==2) MobR2x=660;
			}
		}
		if (Fen.Presence(650, 200, 0, 400) && Boss==2 && Fen.keyflag==2) Boss=1;
//*****T1
	if (Fen.keyflag>1){
		if (MobT1TrapOn==0) MobT1y=200;
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 3) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1y+40<600) MobT1y+=4;
		if ((MobT1y+40==600 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1y-=1;
		if (MobT1y==200) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
//*****T2
	if (Fen.keyflag>1){
		if (Fen.MobHitBox(MobT2x, MobT2y, 40, 40) && MobT2Hit==0) MobT2Hit=1;
		if (Fen.MobVision(MobT2x, MobT2y, 40, 40, 1) && MobT2TrapOn==0) MobT2TrapOn=1;
		if (MobT2TrapOn==1 && MobT2y>200) MobT2y-=4;
		if ((MobT2y==200 || Fen.Presence(MobT2x, MobT2y, 37, 37)) && MobT2TrapOn==1) MobT2TrapOn=2;
		if (MobT2TrapOn==2) MobT2y+=1;
		if (MobT2y==600) MobT2TrapOn=0;
		if (Fen.Presence(MobT2x, MobT2y, 40, 40) && MobT2Life>0) Fen.Lifey+=1;
		}
//*****T3
	if (Fen.keyflag>1){
		if (Fen.MobHitBox(MobT3x, MobT3y, 40, 40) && MobT3Hit==0) MobT3Hit=1;
		if (Fen.MobVision(MobT3x, MobT3y, 40, 40, 3) && MobT3TrapOn==0) MobT3TrapOn=1;
		if (MobT3TrapOn==1 && MobT3y+40<600) MobT3y+=4;
		if ((MobT3y+40==600 || Fen.Presence(MobT3x, MobT3y, 37, 37)) && MobT3TrapOn==1) MobT3TrapOn=2;
		if (MobT3TrapOn==2) MobT3y-=1;
		if (MobT3y==200) MobT3TrapOn=0;
		if (Fen.Presence(MobT3x, MobT3y, 40, 40) && MobT3Life>0) Fen.Lifey+=1;
		}
//*****T4
	if (Fen.keyflag>1){
		if (MobT4TrapOn==0) MobT4y=600;
		if (Fen.MobHitBox(MobT4x, MobT4y, 40, 40) && MobT4Hit==0) MobT4Hit=1;
		if (Fen.MobVision(MobT4x, MobT4y, 40, 40, 1) && MobT4TrapOn==0) MobT4TrapOn=1;
		if (MobT4TrapOn==1 && MobT4y>200) MobT4y-=4;
		if ((MobT4y==200 || Fen.Presence(MobT4x, MobT4y, 37, 37)) && MobT4TrapOn==1) MobT4TrapOn=2;
		if (MobT4TrapOn==2) MobT4y+=1;
		if (MobT4y==600) MobT4TrapOn=0;
		if (Fen.Presence(MobT4x, MobT4y, 40, 40) && MobT4Life>0) Fen.Lifey+=1;
		}
//*****T5
	if (Fen.keyflag>1){
		if (MobT5TrapOn==0) MobT5x=200;
		if (Fen.MobHitBox(MobT5x, MobT5y, 40, 40) && MobT5Hit==0) MobT5Hit=1;
		if (Fen.MobVision(MobT5x, MobT5y, 40, 40, 2) && MobT5TrapOn==0) MobT5TrapOn=1;
		if (MobT5TrapOn==1 && MobT5x+40<600) MobT5x+=4;
		if ((MobT5x+40==600 || Fen.Presence(MobT5x, MobT5y, 37, 37)) && MobT5TrapOn==1) MobT5TrapOn=2;
		if (MobT5TrapOn==2) MobT5x-=1;
		if (MobT5x==200) MobT5TrapOn=0;
		if (Fen.Presence(MobT5x, MobT5y, 40, 40) && MobT5Life>0) Fen.Lifey+=1;
		}
//******P1 Mob3
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP1x, MobP1y, 40, 40) && MobP1Hit==0){
			MobP1Hit=1;
			if (Fen.sens==0) MobP1x-=10;
			if (Fen.sens==1) MobP1y-=10;
			if (Fen.sens==2) MobP1x+=10;
			if (Fen.sens==3) MobP1y+=10;
			}
		if (MobP1TimeOn==0){
			MobP1TimeOn=1;
			new Mob3Time().start();
			}
		if (MobP1Sens==0){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 0)) MobP1x-=2;
			else MobP1x-=1;
			}
		if (MobP1Sens==1){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 1)) MobP1y-=2;
			else MobP1y-=1;
			}
		if (MobP1Sens==2){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 2)) MobP1x+=2;
			else MobP1x+=1;
			}
		if (MobP1Sens==3){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 3)) MobP1y+=2;
			else MobP1y+=1;
			}
		if ((Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(600, 0, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==1) MobP1y=200;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==0) MobP1x=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==3) MobP1y=560;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==2) MobP1x=560;
		if ((MobP1x<0 && MobP1Sens==0) || (MobP1x+40>800 && MobP1Sens==2) || (MobP1y<0 && MobP1Sens==1) || (MobP1y>800 && MobP1Sens==3)) MobP1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP1x, MobP1y, 40, 40) && MobP1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==3){
			if (Fen.ColisionMob(0, 200, 100, 400, MobP1x, MobP1y, 40, 40) && MobP1Sens==0) MobP1x=100;
			if (Fen.ColisionMob(700, 200, 100, 400, MobP1x, MobP1y, 40, 40) && MobP1Sens==2) MobP1x=660;
			}
		}
}
//*****Fin Mob 
		if (MobT1Life==0 && MobT2Life==0 && MobT3Life==0 && MobT4Life==0 && MobT5Life==0) Boss=0;

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=11;
			Fen.Orientation='E';
			Fen.ChangeRoom=0;
			} 
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=9;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=6;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=14;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(600, 600, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;
		else if (Fen.Colision(600, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color128>0) Fen.Color128-=1;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan11 extends JPanel{
	public Pan11(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.red);
		g.fillRect(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));

			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				}
			if (Fen.keyflag==3){
				g.drawImage(MurH, 0, 200, 100, 400, this);
				if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
				}
			} catch (IOException e) {}   }
 
		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=10;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=7;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=15;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			}
		if (Fen.Colision(0, 0, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 800, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(10, 191, 21, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan12 extends JPanel{
	int ColorEnding=255;

	//int losaX[] = {400+x1Inv-40, 400+x2Inv-40, 400+x3Inv-40, 400+x4Inv-40};
	//int losaY[] = {300+y1Inv-40, 300+y2Inv-40, 300+y3Inv-40, 300+y4Inv-40};
	static int x1Inv=20;
	static int x2Inv=60;
	static int x3Inv=60;
	static int x4Inv=20;
	static int y1Inv=20;
	static int y2Inv=20;
	static int y3Inv=60;
	static int y4Inv=60;
	static int losaTour=0;
	static int losaTourk=0;

	int motFiny=400;
	public Pan12(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		Font font = new Font("New Times Roman", Font.BOLD, 30);
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, this.getWidth(), 2*this.getHeight()/8);
		g.fillRect(0, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=8;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 200, 200, 600, 0)) Fen.x=200;
		else if (Fen.Colision(200, 0, 600, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 200, 200, 600, 2)) Fen.x=560;

		g.setColor(new Color(ColorEnding, ColorEnding, 0));

			  //{20, 60, 60, 20}
			  //{20, 20, 60, 60}
		
		int losaX[] = {(4*this.getWidth()/8)+x1Inv-40, (4*this.getWidth()/8)+x2Inv-40, (4*this.getWidth()/8)+x3Inv-40, (4*this.getWidth()/8)+x4Inv-40};
		int losaY[] = {(3*this.getHeight()/8)+y1Inv-40, (3*this.getHeight()/8)+y2Inv-40, (3*this.getHeight()/8)+y3Inv-40, (3*this.getHeight()/8)+y4Inv-40};
			
		g.fillPolygon(losaX, losaY, 4);
		if (losaTour==0 && x1Inv>-920){
			if (x2Inv<60); x1Inv+=1;
			if (x4Inv>20); x3Inv-=1;
			if (y1Inv>20); y2Inv+=1;
			if (y3Inv<60); y4Inv-=1;
			losaTourk+=1;
			}
		if (losaTourk==40) losaTour=1;
		if (losaTour==1 && x1Inv>-920){
			if (x2Inv>20); x1Inv-=1;
			if (x4Inv<60); x3Inv+=1;
			if (y1Inv<60); y2Inv-=1;
			if (y3Inv>20); y4Inv+=1;
			losaTourk+=1;
			}
		if (losaTourk==80 && Fen.keyflag<5){
			losaTourk=0;
			losaTour=0;
			}
		//System.out.println(x1Inv);
		if (Fin()) Fen.keyflag=5;
		
		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		if (Fen.keyflag<5){
			g.setColor(Color.black);
			g.setFont(new Font("Arial", 0, 15));
			g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
			g.fillRect(9, 191, 22, -182);
			g.setColor(Color.green);
			g.fillRect(10, 190, 20, Fen.Lifey);
			}

		if (x1Inv<=-920 && ColorEnding>0) ColorEnding-=1;
		if (ColorEnding==0){
			if (Fen.TextTimeOn==0){
				Fen.TextTimeOn=1;
				new TextTime().start();
				}
			//g.setColor(Color.red);
			//g.drawLine(0, 400, 800, 400);
			//g.drawLine(400, 0, 400, 800);
			g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
			g.setFont(new Font("Miriam Libre", Font.BOLD, 30));
			g.drawString("Fin", 380, motFiny);
			g.drawString("Thanks for playing my game o^w^o", 150, motFiny+3400);
			g.setColor(Color.white);
			if (Fen.TextTime>5 && motFiny>-3000) motFiny-=1;
			g.setFont(new Font("Miriam Libre", Font.BOLD, 25));
			g.drawString("Designer Graphique", 290, motFiny+800);
			g.drawString("Level Designer", 320, motFiny+1200);
			g.drawString("Game Testeur", 320, motFiny+1600);
			g.drawString("Game Designer", 320, motFiny+2000);
			g.drawString("Lead Programmeur", 290, motFiny+2400);
			g.drawString("Cree par", 350, motFiny+2800);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 22));
			g.drawString("Soubaya Djayan", 320, motFiny+840);
			g.drawString("Soubaya Djayan", 325, motFiny+1240);
			g.drawString("Soubaya Djayan", 322, motFiny+1640);
			g.drawString("Soubaya Djayan", 329, motFiny+2040);
			g.drawString("Soubaya Djayan", 328, motFiny+2440);
			g.drawString("Soubaya Djayan", 318, motFiny+2840);
			if (motFiny==-50) Fen.Color255=0;
			if (motFiny==-3000 && Fen.Color255<255) Fen.Color255+=1;
			
			}
		try{
			Thread.sleep(7);
			} catch (InterruptedException e) {}
		}
	public boolean Fin(){
		int Fx=0;
		int Fy=0;
		for(int i=Fen.x; i<Fen.x+40; i++){
			if (380<i && i<420){
				Fx=1;
				break;
				}
			}
		for(int i=Fen.y; i<Fen.y+40; i++){
			if (280<i && i<320){
				Fy=1;
				break;
				}
			}
		return Fx==1 && Fy==1;
		}
	}
class Pan13 extends JPanel{
	int MobR1x=350;
	int MobR1y=301;
	static int MobR1Sens=0;
	static int MobR1TimeOn=0;
	static int MobR1Afraid=0;
	int MobR1Hit=0;
	int MobR1HitOn=0;
	static int MobR1Life=2;

	int MobT1x=680;
	int MobT1y=560;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	static int MobP1x=250;
	static int MobP1y=300;
	static int MobP1Sens=0;
	static int MobP1TimeOn=0;
	int MobP1Hit=0;
	int MobP1HitOn=0;
	static int MobP1Life=4;

	static int MobP2x=500;
	static int MobP2y=400;
	static int MobP2Sens=0;
	static int MobP2TimeOn=0;
	int MobP2Hit=0;
	int MobP2HitOn=0;
	static int MobP2Life=4;
	public Pan13(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color255, 0, 0));
		g.fillRect(0, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MobR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRond.png"));
			Image MobRA = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondAfraid.png"));
			Image MobRD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondDamaged.png"));
			if (Fen.keyflag>0){
				if (MobR1Life==2) g.drawImage(MobR, MobR1x, MobR1y, 40, 40, this);
				else if (MobR1Life==1) g.drawImage(MobRA, MobR1x, MobR1y, 40, 40, this);
				if (MobR1Hit==1 && MobR1Life>0){
					g.drawImage(MobRD, MobR1x, MobR1y, 40, 40, this);
					if (MobR1HitOn==0){
						MobR1Life-=1;
						MobR1HitOn=1;
						}
					MobR1Hit=0;
					}
				}
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>1){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if (MobT1TrapOn==1 && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				}
			Image MobP = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPenta.png"));
			Image MobPX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaX.png"));
			Image MobPD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaDamaged.png"));
			if (Fen.keyflag>2){
				if (Fen.MobVision(MobP1x, MobP1y, 40, 40, MobP1Sens) && MobP1Life>0) g.drawImage(MobPX, MobP1x, MobP1y, 40, 40, this);
				else if (MobP1Life>0) g.drawImage(MobP, MobP1x, MobP1y, 40, 40, this);
				if (MobP1Hit==1 && MobP1Life>0){
					g.drawImage(MobPD, MobP1x, MobP1y, 40, 40, this);
					if (MobP1HitOn==0){
						MobP1Life-=1;
						MobP1HitOn=1;
						}
					MobP1Hit=0;
					}
				if (Fen.MobVision(MobP2x, MobP2y, 40, 40, MobP2Sens) && MobP2Life>0) g.drawImage(MobPX, MobP2x, MobP2y, 40, 40, this);
				else if (MobP2Life>0) g.drawImage(MobP, MobP2x, MobP2y, 40, 40, this);
				if (MobP2Hit==1 && MobP2Life>0){
					g.drawImage(MobPD, MobP2x, MobP2y, 40, 40, this);
					if (MobP2HitOn==0){
						MobP2Life-=1;
						MobP2HitOn=1;
						}
					MobP2Hit=0;
					}
				}
			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				}
			if (Fen.keyflag==3){
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.AttackOn==0){
				MobR1HitOn=0;
				MobT1HitOn=0;
				MobP1HitOn=0;
				MobP2HitOn=0;
				}
			} catch (IOException e) {}   
//****R1 Mob1
	if (Fen.keyflag>0){
		if (MobR1Life==1) MobR1Afraid=1;
		else MobR1Afraid=0;
		if (Fen.MobHitBox(MobR1x, MobR1y, 40, 40) && MobR1Hit==0){
			MobR1Hit=1;
			if (Fen.sens==0) MobR1x-=10;
			if (Fen.sens==1) MobR1y-=10;
			if (Fen.sens==2) MobR1x+=10;
			if (Fen.sens==3) MobR1y+=10;
			}
		if (MobR1TimeOn==0){
			MobR1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobR1Sens==0) MobR1x-=1+2*MobR1Afraid;
		if (MobR1Sens==1) MobR1y-=1+2*MobR1Afraid;
		if (MobR1Sens==2) MobR1x+=1+2*MobR1Afraid;
		if (MobR1Sens==3) MobR1y+=1+2*MobR1Afraid;
		if (Fen.ColisionMob(0, 0, 200, 800, MobR1x, MobR1y, 40, 40) && MobR1Sens==0) MobR1x=200;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==2) MobR1x=560;
		if (Fen.ColisionMob(600, 0, 200, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=200;
		if (Fen.ColisionMob(600, 600, 200, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=560;
		if ((MobR1x<0 && MobR1Sens==0) || (MobR1x+40>800 && MobR1Sens==2) || (MobR1y<0 && MobR1Sens==1) || (MobR1y>800 && MobR1Sens==3)) MobR1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR1x, MobR1y, 40, 40) && MobR1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==2) if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
		if (Fen.keyflag==3) if (Fen.ColisionMob(200, 700, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=660;

		}
//*****T1
	if (Fen.keyflag>1){
		if (MobT1TrapOn==0) MobT1y=560;
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 1) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1y>200) MobT1y-=4;
		if ((MobT1y==200 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1y+=1;
		if (MobT1y==560) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
//******P1 Mob2
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP1x, MobP1y, 40, 40) && MobP1Hit==0){
			MobP1Hit=1;
			if (Fen.sens==0) MobP1x-=10;
			if (Fen.sens==1) MobP1y-=10;
			if (Fen.sens==2) MobP1x+=10;
			if (Fen.sens==3) MobP1y+=10;
			}
		if (MobP1TimeOn==0){
			MobP1TimeOn=1;
			new Mob2Time().start();
			}
		if (MobP1Sens==0){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 0)) MobP1x-=2;
			else MobP1x-=1;
			}
		if (MobP1Sens==1){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 1)) MobP1y-=2;
			else MobP1y-=1;
			}
		if (MobP1Sens==2){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 2)) MobP1x+=2;
			else MobP1x+=1;
			}
		if (MobP1Sens==3){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 3)) MobP1y+=2;
			else MobP1y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 800, MobP1x, MobP1y, 40, 40) && MobP1Sens==0) MobP1x=200;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==2) MobP1x=560;
		if (Fen.ColisionMob(600, 0, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==1) MobP1y=200;
		if (Fen.ColisionMob(600, 600, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==3) MobP1y=560;
		if ((MobP1x<0 && MobP1Sens==0) || (MobP1x+40>800 && MobP1Sens==2) || (MobP1y<0 && MobP1Sens==1) || (MobP1y>800 && MobP1Sens==3)) MobP1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP1x, MobP1y, 40, 40) && MobP1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==3) if (Fen.ColisionMob(200, 700, 400, 100, MobP1x, MobP1y, 40, 40) && MobP1Sens==3) MobP1y=660;
		}
//******P2 Mob3
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP2x, MobP2y, 40, 40) && MobP2Hit==0){
			MobP2Hit=1;
			if (Fen.sens==0) MobP2x-=10;
			if (Fen.sens==1) MobP2y-=10;
			if (Fen.sens==2) MobP2x+=10;
			if (Fen.sens==3) MobP2y+=10;
			}
		if (MobP2TimeOn==0){
			MobP2TimeOn=1;
			new Mob3Time().start();
			}
		if (MobP2Sens==0){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 0)) MobP2x-=2;
			else MobP2x-=1;
			}
		if (MobP2Sens==1){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 1)) MobP2y-=2;
			else MobP2y-=1;
			}
		if (MobP2Sens==2){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 2)) MobP2x+=2;
			else MobP2x+=1;
			}
		if (MobP2Sens==3){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 3)) MobP2y+=2;
			else MobP2y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 800, MobP2x, MobP2y, 40, 40) && MobP2Sens==0) MobP2x=200;
		if ((Fen.ColisionMob(600, 0, 200, 200, MobP2x, MobP2y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobP2x, MobP2y, 40, 40)) && MobP2Sens==2) MobP2x=560;
		if (Fen.ColisionMob(600, 0, 200, 200, MobP2x, MobP2y, 40, 40) && MobP2Sens==1) MobP2y=200;
		if (Fen.ColisionMob(600, 600, 200, 200, MobP2x, MobP2y, 40, 40) && MobP2Sens==3) MobP2y=560;
		if ((MobP2x<0 && MobP2Sens==0) || (MobP2x+40>800 && MobP2Sens==2) || (MobP2y<0 && MobP2Sens==1) || (MobP2y>800 && MobP2Sens==3)) MobP2Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP2x, MobP2y, 40, 40) && MobP2Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==3) if (Fen.ColisionMob(200, 700, 400, 100, MobP2x, MobP2y, 40, 40) && MobP2Sens==3) MobP2y=660;
		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=14;
			Fen.Orientation='E';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=9;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=17;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 800, 0)) Fen.x=200;
		else if (Fen.Colision(600, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(600, 600, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(600, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan14 extends JPanel{
	static int brown=64;

	int MobR1x=550;
	int MobR1y=350;
	static int MobR1Sens=0;
	static int MobR1TimeOn=0;
	static int MobR1Afraid=0;
	int MobR1Hit=0;
	int MobR1HitOn=0;
	static int MobR1Life=2;

	int MobR2x=250;
	int MobR2y=401;
	static int MobR2Sens=0;
	static int MobR2TimeOn=0;
	static int MobR2Afraid=0;
	int MobR2Hit=0;
	int MobR2HitOn=0;
	static int MobR2Life=2;

	int MobT1x=560;
	int MobT1y=600;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	static int MobP1x=500;
	static int MobP1y=201;
	static int MobP1Sens=0;
	static int MobP1TimeOn=0;
	int MobP1Hit=0;
	int MobP1HitOn=0;
	static int MobP1Life=4;
	public Pan14(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color128, brown, brown));
		g.fillRect(0, 0, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MobR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRond.png"));
			Image MobRA = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondAfraid.png"));
			Image MobRD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobRondDamaged.png"));
			if (Fen.keyflag>0){
				if (MobR1Life==2) g.drawImage(MobR, MobR1x, MobR1y, 40, 40, this);
				else if (MobR1Life==1) g.drawImage(MobRA, MobR1x, MobR1y, 40, 40, this);
				if (MobR1Hit==1 && MobR1Life>0){
					g.drawImage(MobRD, MobR1x, MobR1y, 40, 40, this);
					if (MobR1HitOn==0){
						MobR1Life-=1;
						MobR1HitOn=1;
						}
					MobR1Hit=0;
					}
				if (MobR2Life==2) g.drawImage(MobR, MobR2x, MobR2y, 40, 40, this);
				else if (MobR2Life==1) g.drawImage(MobRA, MobR2x, MobR2y, 40, 40, this);
				if (MobR2Hit==1 && MobR2Life>0){
					g.drawImage(MobRD, MobR2x, MobR2y, 40, 40, this);
					if (MobR2HitOn==0){
						MobR2Life-=1;
						MobR2HitOn=1;
						}
					MobR2Hit=0;
					}
				}
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>1){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if (MobT1TrapOn==1 && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				}
			Image MobP = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPenta.png"));
			Image MobPX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaX.png"));
			Image MobPD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaDamaged.png"));
			if (Fen.keyflag>2){
				if (Fen.MobVision(MobP1x, MobP1y, 40, 40, MobP1Sens) && MobP1Life>0) g.drawImage(MobPX, MobP1x, MobP1y, 40, 40, this);
				else if (MobP1Life>0) g.drawImage(MobP, MobP1x, MobP1y, 40, 40, this);
				if (MobP1Hit==1 && MobP1Life>0){
					g.drawImage(MobPD, MobP1x, MobP1y, 40, 40, this);
					if (MobP1HitOn==0){
						MobP1Life-=1;
						MobP1HitOn=1;
						}
					MobP1Hit=0;
					}
				}
			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.keyflag==3){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				}
			if (Fen.AttackOn==0){
				MobR1HitOn=0;
				MobR2HitOn=0;
				MobT1HitOn=0;
				MobP1HitOn=0;
				}
			} catch (IOException e) {}   
//****R1 Mob1
	if (Fen.keyflag>0){
		if (MobR1Life==1) MobR1Afraid=1;
		else MobR1Afraid=0;
		if (Fen.MobHitBox(MobR1x, MobR1y, 40, 40) && MobR1Hit==0){
			MobR1Hit=1;
			if (Fen.sens==0) MobR1x-=10;
			if (Fen.sens==1) MobR1y-=10;
			if (Fen.sens==2) MobR1x+=10;
			if (Fen.sens==3) MobR1y+=10;
			}
		if (MobR1TimeOn==0){
			MobR1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobR1Sens==0) MobR1x-=1+2*MobR1Afraid;
		if (MobR1Sens==1) MobR1y-=1+2*MobR1Afraid;
		if (MobR1Sens==2) MobR1x+=1+2*MobR1Afraid;
		if (MobR1Sens==3) MobR1y+=1+2*MobR1Afraid;
		if (Fen.ColisionMob(600, 0, 200, 800, MobR1x, MobR1y, 40, 40) && MobR1Sens==2) MobR1x=560;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR1x, MobR1y, 40, 40)) && MobR1Sens==0) MobR1x=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=200;
		if (Fen.ColisionMob(0, 600, 200, 200, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=560;
		if ((MobR1x<0 && MobR1Sens==0) || (MobR1x+40>800 && MobR1Sens==2) || (MobR1y<0 && MobR1Sens==1) || (MobR1y>800 && MobR1Sens==3)) MobR1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR1x, MobR1y, 40, 40) && MobR1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==2) if (Fen.ColisionMob(200, 700, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==3) MobR1y=660;
		if (Fen.keyflag==3) if (Fen.ColisionMob(200, 0, 400, 100, MobR1x, MobR1y, 40, 40) && MobR1Sens==1) MobR1y=100;
		}
//****R2 Mob2
	if (Fen.keyflag>0){
		if (MobR2Life==1) MobR2Afraid=1;
		else MobR2Afraid=0;
		if (Fen.MobHitBox(MobR2x, MobR2y, 40, 40) && MobR2Hit==0){
			MobR2Hit=1;
			if (Fen.sens==0) MobR2x-=10;
			if (Fen.sens==1) MobR2y-=10;
			if (Fen.sens==2) MobR2x+=10;
			if (Fen.sens==3) MobR2y+=10;
			}
		if (MobR2TimeOn==0){
			MobR2TimeOn=1;
			new Mob2Time().start();
			}
		if (MobR2Sens==0) MobR2x-=1+2*MobR2Afraid;
		if (MobR2Sens==1) MobR2y-=1+2*MobR2Afraid;
		if (MobR2Sens==2) MobR2x+=1+2*MobR2Afraid;
		if (MobR2Sens==3) MobR2y+=1+2*MobR2Afraid;
		if (Fen.ColisionMob(600, 0, 200, 800, MobR2x, MobR2y, 40, 40) && MobR2Sens==2) MobR2x=560;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobR2x, MobR2y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobR2x, MobR2y, 40, 40)) && MobR2Sens==0) MobR2x=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobR2x, MobR2y, 40, 40) && MobR2Sens==1) MobR2y=200;
		if (Fen.ColisionMob(0, 600, 200, 200, MobR2x, MobR2y, 40, 40) && MobR2Sens==3) MobR2y=560;
		if ((MobR2x<0 && MobR2Sens==0) || (MobR2x+40>800 && MobR2Sens==2) || (MobR2y<0 && MobR2Sens==1) || (MobR2y>800 && MobR2Sens==3)) MobR2Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobR2x, MobR2y, 40, 40) && MobR2Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==2) if (Fen.ColisionMob(200, 700, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==3) MobR2y=660;
		if (Fen.keyflag==3) if (Fen.ColisionMob(200, 0, 400, 100, MobR2x, MobR2y, 40, 40) && MobR2Sens==1) MobR2y=100;
		}
//*****T1
	if (Fen.keyflag>1){
		if (MobT1TrapOn==0) MobT1x=560;
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 0) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1x>200) MobT1x-=4;
		if ((MobT1x==200 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1x+=1;
		if (MobT1x==560) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
//******P1 Mob3
	if (Fen.keyflag>2){
		if (Fen.MobHitBox(MobP1x, MobP1y, 40, 40) && MobP1Hit==0){
			MobP1Hit=1;
			if (Fen.sens==0) MobP1x-=10;
			if (Fen.sens==1) MobP1y-=10;
			if (Fen.sens==2) MobP1x+=10;
			if (Fen.sens==3) MobP1y+=10;
			}
		if (MobP1TimeOn==0){
			MobP1TimeOn=1;
			new Mob3Time().start();
			}
		if (MobP1Sens==0){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 0)) MobP1x-=2;
			else MobP1x-=1;
			}
		if (MobP1Sens==1){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 1)) MobP1y-=2;
			else MobP1y-=1;
			}
		if (MobP1Sens==2){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 2)) MobP1x+=2;
			else MobP1x+=1;
			}
		if (MobP1Sens==3){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 3)) MobP1y+=2;
			else MobP1y+=1;
			}
		if (Fen.ColisionMob(600, 0, 200, 800, MobP1x, MobP1y, 40, 40) && MobP1Sens==2) MobP1x=560;
		if ((Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(0, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==0) MobP1x=200;
		if (Fen.ColisionMob(0, 0, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==1) MobP1y=200;
		if (Fen.ColisionMob(0, 600, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==3) MobP1y=560;
		if ((MobP1x<0 && MobP1Sens==0) || (MobP1x+40>800 && MobP1Sens==2) || (MobP1y<0 && MobP1Sens==1) || (MobP1y>800 && MobP1Sens==3)) MobP1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP1x, MobP1y, 40, 40) && MobP1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==3) if (Fen.ColisionMob(200, 0, 400, 100, MobP1x, MobP1y, 40, 40) && MobP1Sens==1) MobP1y=100;
		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=13;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=10;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=18;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 200, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 0, 200, 800, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (brown>0) brown-=1;
			if (Fen.Color128>0) Fen.Color128-=1;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan15 extends JPanel{
	int MobT1x=200;
	int MobT1y=600;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	int MobT2x=240;
	int MobT2y=600;
	int MobT2TrapOn=0;
	int MobT2Hit=0;
	int MobT2HitOn=0;
	static int MobT2Life=3;

	int MobT3x=280;
	int MobT3y=600;
	int MobT3TrapOn=0;
	int MobT3Hit=0;
	int MobT3HitOn=0;
	static int MobT3Life=3;

	int MobT4x=320;
	int MobT4y=600;
	int MobT4TrapOn=0;
	int MobT4Hit=0;
	int MobT4HitOn=0;
	static int MobT4Life=3;

	int MobT5x=360;
	int MobT5y=600;
	int MobT5TrapOn=0;
	int MobT5Hit=0;
	int MobT5HitOn=0;
	static int MobT5Life=3;

	int MobT6x=400;
	int MobT6y=600;
	int MobT6TrapOn=0;
	int MobT6Hit=0;
	int MobT6HitOn=0;
	static int MobT6Life=3;

	int MobT7x=440;
	int MobT7y=600;
	int MobT7TrapOn=0;
	int MobT7Hit=0;
	int MobT7HitOn=0;
	static int MobT7Life=3;

	int MobT8x=480;
	int MobT8y=600;
	int MobT8TrapOn=0;
	int MobT8Hit=0;
	int MobT8HitOn=0;
	static int MobT8Life=3;

	int MobT9x=520;
	int MobT9y=600;
	int MobT9TrapOn=0;
	int MobT9Hit=0;
	int MobT9HitOn=0;
	static int MobT9Life=3;

	int MobT10x=560;
	int MobT10y=600;
	int MobT10TrapOn=0;
	int MobT10Hit=0;
	int MobT10HitOn=0;
	static int MobT10Life=3;

	static int MobP1x=300;
	static int MobP1y=250;
	static int MobP1Sens=0;
	static int MobP1TimeOn=0;
	int MobP1Hit=0;
	int MobP1HitOn=0;
	static int MobP1Life=4;

	static int MobP2x=350;
	static int MobP2y=300;
	static int MobP2Sens=0;
	static int MobP2TimeOn=0;
	int MobP2Hit=0;
	int MobP2HitOn=0;
	static int MobP2Life=4;

	static int MobP3x=400;
	static int MobP3y=350;
	static int MobP3Sens=0;
	static int MobP3TimeOn=0;
	int MobP3Hit=0;
	int MobP3HitOn=0;
	static int MobP3Life=4;

	static int MobP4x=500;
	static int MobP4y=400;
	static int MobP4Sens=0;
	static int MobP4TimeOn=0;
	int MobP4Hit=0;
	int MobP4HitOn=0;
	static int MobP4Life=4;

	static int Boss=3;
	public Pan15(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(0, 0, Fen.Color255));
		g.fillRect(0, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Boss==2){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if (MobT1TrapOn==1 && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				if (MobT2Life>0) g.drawImage(MobT, MobT2x, MobT2y, 40, 40, this);
				if (MobT2TrapOn==1 && MobT2Life>0) g.drawImage(MobTX, MobT2x, MobT2y, 40, 40, this);
				if (MobT2Hit==1 && MobT2Life>0){
					g.drawImage(MobTD, MobT2x, MobT2y, 40, 40, this);
					if (MobT2HitOn==0){
						MobT2Life-=1;
						MobT2HitOn=1;
						}
					MobT2Hit=0;
					}
				if (MobT3Life>0) g.drawImage(MobT, MobT3x, MobT3y, 40, 40, this);
				if (MobT3TrapOn==1 && MobT3Life>0) g.drawImage(MobTX, MobT3x, MobT3y, 40, 40, this);
				if (MobT3Hit==1 && MobT3Life>0){
					g.drawImage(MobTD, MobT3x, MobT3y, 40, 40, this);
					if (MobT3HitOn==0){
						MobT3Life-=1;
						MobT3HitOn=1;
						}
					MobT3Hit=0;
					}
				if (MobT4Life>0) g.drawImage(MobT, MobT4x, MobT4y, 40, 40, this);
				if (MobT4TrapOn==1 && MobT4Life>0) g.drawImage(MobTX, MobT4x, MobT4y, 40, 40, this);
				if (MobT4Hit==1 && MobT4Life>0){
					g.drawImage(MobTD, MobT4x, MobT4y, 40, 40, this);
					if (MobT4HitOn==0){
						MobT4Life-=1;
						MobT4HitOn=1;
						}
					MobT4Hit=0;
					}
				if (MobT5Life>0) g.drawImage(MobT, MobT5x, MobT5y, 40, 40, this);
				if (MobT5TrapOn==1 && MobT5Life>0) g.drawImage(MobTX, MobT5x, MobT5y, 40, 40, this);
				if (MobT5Hit==1 && MobT5Life>0){
					g.drawImage(MobTD, MobT5x, MobT5y, 40, 40, this);
					if (MobT5HitOn==0){
						MobT5Life-=1;
						MobT5HitOn=1;
						}
					MobT5Hit=0;
					}
				if (MobT6Life>0) g.drawImage(MobT, MobT6x, MobT6y, 40, 40, this);
				if (MobT6TrapOn==1 && MobT6Life>0) g.drawImage(MobTX, MobT6x, MobT6y, 40, 40, this);
				if (MobT6Hit==1 && MobT6Life>0){
					g.drawImage(MobTD, MobT6x, MobT6y, 40, 40, this);
					if (MobT6HitOn==0){
						MobT6Life-=1;
						MobT6HitOn=1;
						}
					MobT6Hit=0;
					}
				if (MobT7Life>0) g.drawImage(MobT, MobT7x, MobT7y, 40, 40, this);
				if (MobT7TrapOn==1 && MobT7Life>0) g.drawImage(MobTX, MobT7x, MobT7y, 40, 40, this);
				if (MobT7Hit==1 && MobT7Life>0){
					g.drawImage(MobTD, MobT7x, MobT7y, 40, 40, this);
					if (MobT7HitOn==0){
						MobT7Life-=1;
						MobT7HitOn=1;
						}
					MobT7Hit=0;
					}
				if (MobT8Life>0) g.drawImage(MobT, MobT8x, MobT8y, 40, 40, this);
				if (MobT8TrapOn==1 && MobT8Life>0) g.drawImage(MobTX, MobT8x, MobT8y, 40, 40, this);
				if (MobT8Hit==1 && MobT8Life>0){
					g.drawImage(MobTD, MobT8x, MobT8y, 40, 40, this);
					if (MobT8HitOn==0){
						MobT8Life-=1;
						MobT8HitOn=1;
						}
					MobT8Hit=0;
					}
				if (MobT9Life>0) g.drawImage(MobT, MobT9x, MobT9y, 40, 40, this);
				if (MobT9TrapOn==1 && MobT9Life>0) g.drawImage(MobTX, MobT9x, MobT9y, 40, 40, this);
				if (MobT9Hit==1 && MobT9Life>0){
					g.drawImage(MobTD, MobT9x, MobT9y, 40, 40, this);
					if (MobT9HitOn==0){
						MobT9Life-=1;
						MobT9HitOn=1;
						}
					MobT9Hit=0;
					}
				if (MobT10Life>0) g.drawImage(MobT, MobT10x, MobT10y, 40, 40, this);
				if (MobT10TrapOn==1 && MobT10Life>0) g.drawImage(MobTX, MobT10x, MobT10y, 40, 40, this);
				if (MobT10Hit==1 && MobT10Life>0){
					g.drawImage(MobTD, MobT10x, MobT10y, 40, 40, this);
					if (MobT10HitOn==0){
						MobT10Life-=1;
						MobT10HitOn=1;
						}
					MobT10Hit=0;
					}
				}
			Image MobP = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPenta.png"));
			Image MobPX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaX.png"));
			Image MobPD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaDamaged.png"));
			if (Boss==1){
				if (Fen.MobVision(MobP1x, MobP1y, 40, 40, MobP1Sens) && MobP1Life>0) g.drawImage(MobPX, MobP1x, MobP1y, 40, 40, this);
				else if (MobP1Life>0) g.drawImage(MobP, MobP1x, MobP1y, 40, 40, this);
				if (MobP1Hit==1 && MobP1Life>0){
					g.drawImage(MobPD, MobP1x, MobP1y, 40, 40, this);
					if (MobP1HitOn==0){
						MobP1Life-=1;
						MobP1HitOn=1;
						}
					MobP1Hit=0;
					}
				if (Fen.MobVision(MobP2x, MobP2y, 40, 40, MobP2Sens) && MobP2Life>0) g.drawImage(MobPX, MobP2x, MobP2y, 40, 40, this);
				else if (MobP2Life>0) g.drawImage(MobP, MobP2x, MobP2y, 40, 40, this);
				if (MobP2Hit==1 && MobP2Life>0){
					g.drawImage(MobPD, MobP2x, MobP2y, 40, 40, this);
					if (MobP2HitOn==0){
						MobP2Life-=1;
						MobP2HitOn=1;
						}
					MobP2Hit=0;
					}
				if (Fen.MobVision(MobP3x, MobP3y, 40, 40, MobP3Sens) && MobP3Life>0) g.drawImage(MobPX, MobP3x, MobP3y, 40, 40, this);
				else if (MobP3Life>0) g.drawImage(MobP, MobP3x, MobP3y, 40, 40, this);
				if (MobP3Hit==1 && MobP3Life>0){
					g.drawImage(MobPD, MobP3x, MobP3y, 40, 40, this);
					if (MobP3HitOn==0){
						MobP3Life-=1;
						MobP3HitOn=1;
						}
					MobP3Hit=0;
					}
				if (Fen.MobVision(MobP4x, MobP4y, 40, 40, MobP4Sens) && MobP4Life>0) g.drawImage(MobPX, MobP4x, MobP4y, 40, 40, this);
				else if (MobP4Life>0) g.drawImage(MobP, MobP4x, MobP4y, 40, 40, this);
				if (MobP4Hit==1 && MobP4Life>0){
					g.drawImage(MobPD, MobP4x, MobP4y, 40, 40, this);
					if (MobP4HitOn==0){
						MobP4Life-=1;
						MobP4HitOn=1;
						}
					MobP4Hit=0;
					}
				}
			if (Boss==1 || Boss==2){
				g.drawImage(MurW, 200, 0, 400, 100, this);
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 0, 400, 100, 1)) Fen.y=100;
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.AttackOn==0){
				MobT1HitOn=0;
				MobT2HitOn=0;
				MobT3HitOn=0;
				MobT4HitOn=0;
				MobT5HitOn=0;
				MobT6HitOn=0;
				MobT7HitOn=0;
				MobT8HitOn=0;
				MobT9HitOn=0;
				MobT10HitOn=0;
				MobP1HitOn=0;
				MobP2HitOn=0;
				MobP3HitOn=0;
				MobP4HitOn=0;
				}
			} catch (IOException e) {}   
		if (Fen.Presence(200, 160, 400, 0) && Boss==3) Boss=2;
//*****T1
	if (Boss==2){
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 1) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1y>200) MobT1y-=4;
		if ((MobT1y==200 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1y+=2;
		if (MobT1y==600) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
//*****T2
	if (Boss==2){
		if (Fen.MobHitBox(MobT2x, MobT2y, 40, 40) && MobT2Hit==0) MobT2Hit=1;
		if (Fen.MobVision(MobT2x, MobT2y, 40, 40, 1) && MobT2TrapOn==0) MobT2TrapOn=1;
		if (MobT2TrapOn==1 && MobT2y>200) MobT2y-=4;
		if ((MobT2y==200 || Fen.Presence(MobT2x, MobT2y, 37, 37)) && MobT2TrapOn==1) MobT2TrapOn=2;
		if (MobT2TrapOn==2) MobT2y+=2;
		if (MobT2y==600) MobT2TrapOn=0;
		if (Fen.Presence(MobT2x, MobT2y, 40, 40) && MobT2Life>0) Fen.Lifey+=1;
		}
//*****T3
	if (Boss==2){
		if (Fen.MobHitBox(MobT3x, MobT3y, 40, 40) && MobT3Hit==0) MobT3Hit=1;
		if (Fen.MobVision(MobT3x, MobT3y, 40, 40, 1) && MobT3TrapOn==0) MobT3TrapOn=1;
		if (MobT3TrapOn==1 && MobT3y>200) MobT3y-=4;
		if ((MobT3y==200 || Fen.Presence(MobT3x, MobT3y, 37, 37)) && MobT3TrapOn==1) MobT3TrapOn=2;
		if (MobT3TrapOn==2) MobT3y+=2;
		if (MobT3y==600) MobT3TrapOn=0;
		if (Fen.Presence(MobT3x, MobT3y, 40, 40) && MobT3Life>0) Fen.Lifey+=1;
		}
//*****T4
	if (Boss==2){
		if (Fen.MobHitBox(MobT4x, MobT4y, 40, 40) && MobT4Hit==0) MobT4Hit=1;
		if (Fen.MobVision(MobT4x, MobT4y, 40, 40, 1) && MobT4TrapOn==0) MobT4TrapOn=1;
		if (MobT4TrapOn==1 && MobT4y>200) MobT4y-=4;
		if ((MobT4y==200 || Fen.Presence(MobT4x, MobT4y, 37, 37)) && MobT4TrapOn==1) MobT4TrapOn=2;
		if (MobT4TrapOn==2) MobT4y+=2;
		if (MobT4y==600) MobT4TrapOn=0;
		if (Fen.Presence(MobT4x, MobT4y, 40, 40) && MobT4Life>0) Fen.Lifey+=1;
		}
//*****T5
	if (Boss==2){
		if (Fen.MobHitBox(MobT5x, MobT5y, 40, 40) && MobT5Hit==0) MobT5Hit=1;
		if (Fen.MobVision(MobT5x, MobT5y, 40, 40, 1) && MobT5TrapOn==0) MobT5TrapOn=1;
		if (MobT5TrapOn==1 && MobT5y>200) MobT5y-=4;
		if ((MobT5y==200 || Fen.Presence(MobT5x, MobT5y, 37, 37)) && MobT5TrapOn==1) MobT5TrapOn=2;
		if (MobT5TrapOn==2) MobT5y+=2;
		if (MobT5y==600) MobT5TrapOn=0;
		if (Fen.Presence(MobT5x, MobT5y, 40, 40) && MobT5Life>0) Fen.Lifey+=1;
		}
//*****T6
	if (Boss==2){
		if (Fen.MobHitBox(MobT6x, MobT6y, 40, 40) && MobT6Hit==0) MobT6Hit=1;
		if (Fen.MobVision(MobT6x, MobT6y, 40, 40, 1) && MobT6TrapOn==0) MobT6TrapOn=1;
		if (MobT6TrapOn==1 && MobT6y>200) MobT6y-=4;
		if ((MobT6y==200 || Fen.Presence(MobT6x, MobT6y, 37, 37)) && MobT6TrapOn==1) MobT6TrapOn=2;
		if (MobT6TrapOn==2) MobT6y+=2;
		if (MobT6y==600) MobT6TrapOn=0;
		if (Fen.Presence(MobT6x, MobT6y, 40, 40) && MobT6Life>0) Fen.Lifey+=1;
		}
//*****T7
	if (Boss==2){
		if (Fen.MobHitBox(MobT7x, MobT7y, 40, 40) && MobT7Hit==0) MobT7Hit=1;
		if (Fen.MobVision(MobT7x, MobT7y, 40, 40, 1) && MobT7TrapOn==0) MobT7TrapOn=1;
		if (MobT7TrapOn==1 && MobT7y>200) MobT7y-=4;
		if ((MobT7y==200 || Fen.Presence(MobT7x, MobT7y, 37, 37)) && MobT7TrapOn==1) MobT7TrapOn=2;
		if (MobT7TrapOn==2) MobT7y+=2;
		if (MobT7y==600) MobT7TrapOn=0;
		if (Fen.Presence(MobT7x, MobT7y, 40, 40) && MobT7Life>0) Fen.Lifey+=1;
		}
//*****T8
	if (Boss==2){
		if (Fen.MobHitBox(MobT8x, MobT8y, 40, 40) && MobT8Hit==0) MobT8Hit=1;
		if (Fen.MobVision(MobT8x, MobT8y, 40, 40, 1) && MobT8TrapOn==0) MobT8TrapOn=1;
		if (MobT8TrapOn==1 && MobT9y>200) MobT8y-=4;
		if ((MobT8y==200 || Fen.Presence(MobT8x, MobT8y, 37, 37)) && MobT8TrapOn==1) MobT8TrapOn=2;
		if (MobT8TrapOn==2) MobT8y+=2;
		if (MobT8y==600) MobT8TrapOn=0;
		if (Fen.Presence(MobT8x, MobT8y, 40, 40) && MobT8Life>0) Fen.Lifey+=1;
		}
//*****T9
	if (Boss==2){
		if (Fen.MobHitBox(MobT9x, MobT9y, 40, 40) && MobT9Hit==0) MobT9Hit=1;
		if (Fen.MobVision(MobT9x, MobT9y, 40, 40, 1) && MobT9TrapOn==0) MobT9TrapOn=1;
		if (MobT9TrapOn==1 && MobT9y>200) MobT9y-=4;
		if ((MobT9y==200 || Fen.Presence(MobT9x, MobT9y, 37, 37)) && MobT9TrapOn==1) MobT9TrapOn=2;
		if (MobT9TrapOn==2) MobT9y+=2;
		if (MobT9y==600) MobT9TrapOn=0;
		if (Fen.Presence(MobT9x, MobT9y, 40, 40) && MobT9Life>0) Fen.Lifey+=1;
		}
//*****T10
	if (Boss==2){
		if (Fen.MobHitBox(MobT10x, MobT10y, 40, 40) && MobT10Hit==0) MobT10Hit=1;
		if (Fen.MobVision(MobT10x, MobT10y, 40, 40, 1) && MobT10TrapOn==0) MobT10TrapOn=1;
		if (MobT10TrapOn==1 && MobT10y>200) MobT10y-=4;
		if ((MobT10y==200 || Fen.Presence(MobT10x, MobT10y, 37, 37)) && MobT10TrapOn==1) MobT10TrapOn=2;
		if (MobT10TrapOn==2) MobT10y+=2;
		if (MobT10y==600) MobT10TrapOn=0;
		if (Fen.Presence(MobT10x, MobT10y, 40, 40) && MobT10Life>0) Fen.Lifey+=1;
		}
		if (MobT1Life==0 && MobT2Life==0 && MobT3Life==0 && MobT4Life==0 && MobT5Life==0 && MobT6Life==0 && MobT7Life==0 && MobT8Life==0 && MobT9Life==0 && MobT10Life==0) Boss=1;
//******P1 Mob1
	if (Boss==1){
		if (Fen.MobHitBox(MobP1x, MobP1y, 40, 40) && MobP1Hit==0){
			MobP1Hit=1;
			if (Fen.sens==0) MobP1x-=10;
			if (Fen.sens==1) MobP1y-=10;
			if (Fen.sens==2) MobP1x+=10;
			if (Fen.sens==3) MobP1y+=10;
			}
		if (MobP1TimeOn==0){
			MobP1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobP1Sens==0){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 0)) MobP1x-=2;
			else MobP1x-=1;
			}
		if (MobP1Sens==1){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 1)) MobP1y-=2;
			else MobP1y-=1;
			}
		if (MobP1Sens==2){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 2)) MobP1x+=2;
			else MobP1x+=1;
			}
		if (MobP1Sens==3){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 3)) MobP1y+=2;
			else MobP1y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 800, MobP1x, MobP1y, 40, 40) && MobP1Sens==0) MobP1x=200;
		if (Fen.ColisionMob(600, 0, 200, 800, MobP1x, MobP1y, 40, 40) && MobP1Sens==2) MobP1x=560;
		if ((MobP1x<0 && MobP1Sens==0) || (MobP1x+40>800 && MobP1Sens==2) || (MobP1y<0 && MobP1Sens==1) || (MobP1y>800 && MobP1Sens==3)) MobP1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP1x, MobP1y, 40, 40) && MobP1Life>0) Fen.Lifey+=1;
		if (Boss==1){
			if (Fen.ColisionMob(200, 0, 400, 100, MobP1x, MobP1y, 40, 40) && MobP1Sens==1) MobP1y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobP1x, MobP1y, 40, 40) && MobP1Sens==3) MobP1y=660;
			}
		}
//******P2 Mob2
	if (Boss==1){
		if (Fen.MobHitBox(MobP2x, MobP2y, 40, 40) && MobP2Hit==0){
			MobP2Hit=1;
			if (Fen.sens==0) MobP2x-=10;
			if (Fen.sens==1) MobP2y-=10;
			if (Fen.sens==2) MobP2x+=10;
			if (Fen.sens==3) MobP2y+=10;
			}
		if (MobP2TimeOn==0){
			MobP2TimeOn=1;
			new Mob2Time().start();
			}
		if (MobP2Sens==0){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 0)) MobP2x-=2;
			else MobP2x-=1;
			}
		if (MobP2Sens==1){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 1)) MobP2y-=2;
			else MobP2y-=1;
			}
		if (MobP2Sens==2){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 2)) MobP2x+=2;
			else MobP2x+=1;
			}
		if (MobP2Sens==3){
			if (Fen.MobVision(MobP2x, MobP2y, 40, 40, 3)) MobP2y+=2;
			else MobP2y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 800, MobP2x, MobP2y, 40, 40) && MobP2Sens==0) MobP2x=200;
		if (Fen.ColisionMob(600, 0, 200, 800, MobP2x, MobP2y, 40, 40) && MobP2Sens==2) MobP2x=560;
		if ((MobP2x<0 && MobP2Sens==0) || (MobP2x+40>800 && MobP2Sens==2) || (MobP2y<0 && MobP2Sens==1) || (MobP2y>800 && MobP2Sens==3)) MobP2Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP2x, MobP2y, 40, 40) && MobP2Life>0) Fen.Lifey+=1;
		if (Boss==1){
			if (Fen.ColisionMob(200, 0, 400, 100, MobP2x, MobP2y, 40, 40) && MobP2Sens==1) MobP2y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobP2x, MobP2y, 40, 40) && MobP2Sens==3) MobP2y=660;
			}
		}
//******P3 Mob3
	if (Boss==1){
		if (Fen.MobHitBox(MobP3x, MobP3y, 40, 40) && MobP3Hit==0){
			MobP3Hit=1;
			if (Fen.sens==0) MobP3x-=10;
			if (Fen.sens==1) MobP3y-=10;
			if (Fen.sens==2) MobP3x+=10;
			if (Fen.sens==3) MobP3y+=10;
			}
		if (MobP3TimeOn==0){
			MobP3TimeOn=1;
			new Mob3Time().start();
			}
		if (MobP3Sens==0){
			if (Fen.MobVision(MobP3x, MobP3y, 40, 40, 0)) MobP3x-=2;
			else MobP3x-=1;
			}
		if (MobP3Sens==1){
			if (Fen.MobVision(MobP3x, MobP3y, 40, 40, 1)) MobP3y-=2;
			else MobP3y-=1;
			}
		if (MobP3Sens==2){
			if (Fen.MobVision(MobP3x, MobP3y, 40, 40, 2)) MobP3x+=2;
			else MobP3x+=1;
			}
		if (MobP3Sens==3){
			if (Fen.MobVision(MobP3x, MobP3y, 40, 40, 3)) MobP3y+=2;
			else MobP3y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 800, MobP3x, MobP3y, 40, 40) && MobP3Sens==0) MobP3x=200;
		if (Fen.ColisionMob(600, 0, 200, 800, MobP3x, MobP3y, 40, 40) && MobP3Sens==2) MobP3x=560;
		if ((MobP3x<0 && MobP3Sens==0) || (MobP3x+40>800 && MobP3Sens==2) || (MobP3y<0 && MobP3Sens==1) || (MobP3y>800 && MobP3Sens==3)) MobP3Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP3x, MobP3y, 40, 40) && MobP3Life>0) Fen.Lifey+=1;
		if (Boss==1){
			if (Fen.ColisionMob(200, 0, 400, 100, MobP3x, MobP3y, 40, 40) && MobP3Sens==1) MobP3y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobP3x, MobP3y, 40, 40) && MobP3Sens==3) MobP3y=660;
			}
		}
//******P4 Mob4
	if (Boss==1){
		if (Fen.MobHitBox(MobP4x, MobP4y, 40, 40) && MobP4Hit==0){
			MobP4Hit=1;
			if (Fen.sens==0) MobP4x-=10;
			if (Fen.sens==1) MobP4y-=10;
			if (Fen.sens==2) MobP4x+=10;
			if (Fen.sens==3) MobP4y+=10;
			}
		if (MobP4TimeOn==0){
			MobP4TimeOn=1;
			new Mob4Time().start();
			}
		if (MobP4Sens==0){
			if (Fen.MobVision(MobP4x, MobP4y, 40, 40, 0)) MobP4x-=2;
			else MobP4x-=1;
			}
		if (MobP4Sens==1){
			if (Fen.MobVision(MobP4x, MobP4y, 40, 40, 1)) MobP4y-=2;
			else MobP4y-=1;
			}
		if (MobP4Sens==2){
			if (Fen.MobVision(MobP4x, MobP4y, 40, 40, 2)) MobP4x+=2;
			else MobP4x+=1;
			}
		if (MobP4Sens==3){
			if (Fen.MobVision(MobP4x, MobP4y, 40, 40, 3)) MobP4y+=2;
			else MobP4y+=1;
			}
		if (Fen.ColisionMob(0, 0, 200, 800, MobP4x, MobP4y, 40, 40) && MobP4Sens==0) MobP4x=200;
		if (Fen.ColisionMob(600, 0, 200, 800, MobP4x, MobP4y, 40, 40) && MobP4Sens==2) MobP4x=560;
		if ((MobP4x<0 && MobP4Sens==0) || (MobP4x+40>800 && MobP4Sens==2) || (MobP4y<0 && MobP4Sens==1) || (MobP4y>800 && MobP4Sens==3)) MobP4Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP4x, MobP4y, 40, 40) && MobP4Life>0) Fen.Lifey+=1;
		if (Boss==1){
			if (Fen.ColisionMob(200, 0, 400, 100, MobP4x, MobP4y, 40, 40) && MobP4Sens==1) MobP4y=100;
			if (Fen.ColisionMob(200, 700, 400, 100, MobP4x, MobP4y, 40, 40) && MobP4Sens==3) MobP4y=660;
			}
		}
}
//*****Fin Mob 
		if (MobP1Life==0 && MobP2Life==0 && MobP3Life==0 && MobP4Life==0) Boss=0;

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=11;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=19;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 800, 0)) Fen.x=200;
		else if (Fen.Colision(600, 0, 200, 800, 2)) Fen.x=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.fillRect(9, 191, 21, -182);
		g.setColor(Color.white);
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color128>0) Fen.Color128-=1;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan16 extends JPanel{
	int MobGCx=200;
	int MobGCy=250;
	int MobGCcubex=302;
	int MobGCcubey=376;
	int MobGCcubeLockx=0;
	int MobGCcubeLocky=0;
	int MobGCangry=0;
	static int MobGCTime=0;
	static int MobGCTimeOn=0;
	int MobGCHit=0;
	int MobGCHitOn=0;
	static int MobGCLife=30;
	static int Boss=0;
	public Pan16(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color255, Fen.Color255, 0));
		g.fillRect(0, 0, this.getWidth(), 2*this.getHeight()/8);
		g.fillRect(0, 0, 1*this.getWidth()/8, this.getHeight());
		g.fillRect(0, 6*this.getHeight()/8, this.getWidth(), 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image Rubis = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Rubis.png"));
			if (Fen.Rubis==0) g.drawImage(Rubis, 120, 380, 40, 40, this);
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobGC = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCube.png"));
			Image MobGCX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeX.png"));
			Image MobGCD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeDamaged.png"));
			Image MobGCa = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngry.png"));
			Image MobGCaX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngryX.png"));
			Image MobGCaD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngryDamaged.png"));
			if (Boss==1 && MobGCLife>0){
				if (MobGCangry==0) g.drawImage(MobGC, MobGCx, MobGCy, 300, 300, this);
				else g.drawImage(MobGCa, MobGCx, MobGCy, 300, 300, this);
				if (MobGCangry==0 && MobGCTime>5) g.drawImage(MobGCX, MobGCx, MobGCy, 300, 300, this);
				else if (MobGCangry==1 && MobGCTime>5) g.drawImage(MobGCaX, MobGCx, MobGCy, 300, 300, this);
				if (MobGCHit==1 && MobGCLife>0){
					if (MobGCangry==0) g.drawImage(MobGCD, MobGCx, MobGCy, 300, 300, this);
					else g.drawImage(MobGCaD, MobGCx, MobGCy, 300, 300, this);
					if (MobGCHitOn==0){
						MobGCLife-=1;
						MobGCHitOn=1;
						}
					MobGCHit=0;
					}
				if (Fen.AttackOn==0) MobGCHitOn=0;
				}
			if (MobGCLife>0){
				g.drawImage(MurH, 170, 200, 25, 400, this);
				if (Boss==1) g.drawImage(MurH, 775, 200, 100, 400, this);
				}
			} catch (IOException e) {}   
//*****GC
		if (Fen.Presence(600, 200, 0, 400) && Boss==0 && Fen.Rubis==0 && Fen.Emeraude!=1 && Fen.Saphir!=1 && Pan3.Boss==0 && Pan20.Boss==0){
			Boss=1;
			MobGCcubex=302;
			MobGCcubey=376;
			MobGCangry=0;
			}
	if (MobGCLife>0 && Boss==1){
		if (MobGCLife<=15) MobGCangry=1;
		if (MobGCTimeOn==0){
			MobGCTimeOn=1;
			new Mob1Time().start();
			}
		g.setColor(Color.magenta);
		if (MobGCTime<5){
			g.drawLine(MobGCx+125, MobGCy+30, Fen.x+(int)(Math.random()*40), Fen.y+(int)(Math.random()*40));
			g.drawLine(MobGCx+175, MobGCy+30, Fen.x+(int)(Math.random()*40), Fen.y+(int)(Math.random()*40));
			}
		if (MobGCTime==5){
			g.drawLine(MobGCx+125, MobGCy+30, Fen.x+20, Fen.y+20);
			g.drawLine(MobGCx+175, MobGCy+30, Fen.x+20, Fen.y+20);
			MobGCcubeLockx=Fen.x+20;
			MobGCcubeLocky=Fen.y+20;
			//MobGCcubeLocky=(int)(((Fen.x+20-350)*1000-c)/(Fen.y+20-416));
			}
		if (MobGCTime>5){
			if (MobGCcubeLockx==350) MobGCcubeLockx+=1;
			if (MobGCcubeLocky==416) MobGCcubeLocky+=1;
			int Y = (int)((MobGCcubeLocky-416)*(MobGCcubex+48-350)/(MobGCcubeLockx-350))+416;
			int X = (int)((MobGCcubeLockx-350)*(MobGCcubey+40-416)/(MobGCcubeLocky-416))+350;
			//System.out.println(Y);
			g.setColor(Color.gray);
			//g.drawLine(302+48, 376+40, MobGCcubeLockx, Y);
			g.fillRect(MobGCcubex, MobGCcubey, 96, 81);
			if (MobGCcubeLockx>MobGCx+250){
				MobGCcubex+=3+2*MobGCangry;
				if (MobGCcubey+40<Y) MobGCcubey+=2+2*MobGCangry;
				else if (MobGCcubey+40>Y) MobGCcubey-=2+2*MobGCangry;
				}
			if (MobGCcubeLockx<MobGCx+50){
				MobGCcubex-=3+2*MobGCangry;
				if (MobGCcubey+40<Y) MobGCcubey+=2+2*MobGCangry;
				else if (MobGCcubey+40>Y) MobGCcubey-=2+2*MobGCangry;
				}
			if (MobGCx+50<MobGCcubeLockx && MobGCcubeLockx<MobGCx+250){
				if (MobGCcubeLocky>MobGCy+150){
					MobGCcubey+=3+2*MobGCangry;
					if (MobGCcubex+48<X) MobGCcubex+=2+2*MobGCangry;
					else if (MobGCcubex+48>X) MobGCcubex-=2+2*MobGCangry;
					}
				if (MobGCcubeLocky<MobGCy+150){
					MobGCcubey-=3+2*MobGCangry;
					if (MobGCcubex+48<X) MobGCcubex+=2+2*MobGCangry;
					else if (MobGCcubex+48>X) MobGCcubex-=2+2*MobGCangry;
					}
				}
			}
		if (MobGCangry==1 && MobGCTime==8) MobGCTime=10;
		if (MobGCTime==10){
			if (MobGCangry==0) MobGCTime=0;
			else MobGCTime=4;
			MobGCcubex=302;
			MobGCcubey=376;
			}
		if ((Fen.MobHitBox(MobGCx+90, MobGCy, 120, 75) || Fen.MobHitBox(MobGCx+6, MobGCy+75, 33, 162) || Fen.MobHitBox(MobGCx+261, MobGCy+75, 33, 162) || Fen.MobHitBox(MobGCx+45, MobGCy+75, 210, 183) || Fen.MobHitBox(MobGCx+90, MobGCy+258, 120, 42)) && MobGCHit==0 && MobGCTime>5){
			MobGCHit=1;
			}
		if (Fen.Presence(MobGCx+90, MobGCy, 120, 75) || Fen.Presence(MobGCx+6, MobGCy+75, 33, 162) || Fen.Presence(MobGCx+261, MobGCy+75, 33, 162) || Fen.Presence(MobGCx+45, MobGCy+75, 210, 183) || Fen.Presence(MobGCx+90, MobGCy+258, 120, 42) || Fen.Presence(MobGCcubex, MobGCcubey, 96, 81)){
			Fen.Lifey+=1;
			}
		if (Fen.Colision(775, 200, 100, 400, 2)) Fen.x=735;
		}
}
//*****Fin Mob 
		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=17;
			Fen.Orientation='E';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(100, 0, 700, 200, 1)) Fen.y=200;
		else if (Fen.Colision(0, 200, 100, 400, 0)) Fen.x=100;
		else if (Fen.Colision(100, 600, 700, 200, 3)) Fen.y=560;
		else if (Fen.Colision(170, 200, 25, 400, 0) && MobGCLife>0) Fen.x=195;

		g.setFont(new Font("Arial", Font.BOLD, 18));
		if (Fen.Presence(120, 380, 40, 40) && Fen.Rubis==0 && Fen.Saphir!=1 && Fen.Emeraude!=1){
			if (Fen.Interaction==2){
				Fen.Rubis=1;
				Boss=0;
				}
			Fen.Interaction=1;
			g.setColor(Color.green);
			g.fillOval(130, 390, 20, 20);
			g.setColor(Color.black);
			g.drawString("A", 134, 406);
			}
		else Fen.Interaction=0;
		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan17 extends JPanel{
	static int MobP1x=500;
	static int MobP1y=201;
	static int MobP1Sens=0;
	static int MobP1TimeOn=0;
	int MobP1Hit=0;
	int MobP1HitOn=0;
	static int MobP1Life=4;
	public Pan17(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(0, Fen.Color255, Fen.Color255));
		g.fillRect(0, 0, this.getWidth(), 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MobP = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPenta.png"));
			Image MobPX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaX.png"));
			Image MobPD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobPentaDamaged.png"));
			if (Fen.keyflag>1){
				if (Fen.MobVision(MobP1x, MobP1y, 40, 40, MobP1Sens) && MobP1Life>0) g.drawImage(MobPX, MobP1x, MobP1y, 40, 40, this);
				else if (MobP1Life>0) g.drawImage(MobP, MobP1x, MobP1y, 40, 40, this);
				if (MobP1Hit==1 && MobP1Life>0){
					g.drawImage(MobPD, MobP1x, MobP1y, 40, 40, this);
					if (MobP1HitOn==0){
						MobP1Life-=1;
						MobP1HitOn=1;
						}
					MobP1Hit=0;
					}
				}
			if (Fen.keyflag==2){
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.AttackOn==0) MobP1HitOn=0;
			} catch (IOException e) {}   
//******P1 Mob1
	if (Fen.keyflag>1){
		if (Fen.MobHitBox(MobP1x, MobP1y, 40, 40) && MobP1Hit==0){
			MobP1Hit=1;
			if (Fen.sens==0) MobP1x-=10;
			if (Fen.sens==1) MobP1y-=10;
			if (Fen.sens==2) MobP1x+=10;
			if (Fen.sens==3) MobP1y+=10;
			}
		if (MobP1TimeOn==0){
			MobP1TimeOn=1;
			new Mob1Time().start();
			}
		if (MobP1Sens==0){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 0)) MobP1x-=2;
			else MobP1x-=1;
			}
		if (MobP1Sens==1){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 1)) MobP1y-=2;
			else MobP1y-=1;
			}
		if (MobP1Sens==2){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 2)) MobP1x+=2;
			else MobP1x+=1;
			}
		if (MobP1Sens==3){
			if (Fen.MobVision(MobP1x, MobP1y, 40, 40, 3)) MobP1y+=2;
			else MobP1y+=1;
			}
		if (Fen.ColisionMob(0, 0, 800, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==1) MobP1y=200;
		if ((Fen.ColisionMob(0, 600, 200, 200, MobP1x, MobP1y, 40, 40) || Fen.ColisionMob(600, 600, 200, 200, MobP1x, MobP1y, 40, 40)) && MobP1Sens==3) MobP1y=560;
		if (Fen.ColisionMob(0, 600, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==0) MobP1x=200;
		if (Fen.ColisionMob(600, 600, 200, 200, MobP1x, MobP1y, 40, 40) && MobP1Sens==2) MobP1x=560;
		if ((MobP1x<0 && MobP1Sens==0) || (MobP1x+40>800 && MobP1Sens==2) || (MobP1y<0 && MobP1Sens==1) || (MobP1y>800 && MobP1Sens==3)) MobP1Sens=(int)(Math.random()*4);
		if (Fen.Presence(MobP1x, MobP1y, 40, 40) && MobP1Life>0) Fen.Lifey+=1;
		if (Fen.keyflag==2) if (Fen.ColisionMob(200, 700, 400, 100, MobP1x, MobP1y, 40, 40) && MobP1Sens==3) MobP1y=660;
		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Orientation='E';
			Fen.Room=18; 
			Fen.ChangeRoom=0;
			}
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=16;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=13;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 800, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 600, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;
		else if (Fen.Colision(600, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan18 extends JPanel{
	static int pink=128;

	int MobT1x=680;
	int MobT1y=560;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;
	public Pan18(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color255, pink, pink));
		g.fillRect(0, 0, this.getWidth(), 2*this.getHeight()/8);
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MurH = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurH.png"));
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>2){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if (MobT1TrapOn==1 && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				}
			if (Fen.keyflag==2){
				g.drawImage(MurH, 700, 200, 100, 400, this);
				if (Fen.Colision(700, 200, 100, 400, 2)) Fen.x=660;
				}
			if (Fen.keyflag==3){
				g.drawImage(MurW, 200, 700, 400, 100, this);
				if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
				}
			if (Fen.AttackOn==0) MobT1HitOn=0;
			} catch (IOException e) {}   
//*****T1
	if (Fen.keyflag>2){
		if (MobT1TrapOn==0) MobT1y=560;
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 1) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1y>200) MobT1y-=4;
		if ((MobT1y==200 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1y+=1;
		if (MobT1y==560) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x+40>=this.getWidth() && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Orientation='E';
			Fen.Room=19; 
			Fen.ChangeRoom=0;
			}
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=17;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=14;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 800, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 600, 200, 200, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;
		else if (Fen.Colision(600, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (pink>0) pink-=1;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan19 extends JPanel{
	int MobT1x=160;
	int MobT1y=560;
	int MobT1TrapOn=0;
	int MobT1Hit=0;
	int MobT1HitOn=0;
	static int MobT1Life=3;

	int MobT2x=200;
	int MobT2y=600;
	int MobT2TrapOn=0;
	int MobT2Hit=0;
	int MobT2HitOn=0;
	static int MobT2Life=3;
	public Pan19(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(Fen.Color255, 0, Fen.Color255));
		g.fillRect(0, 0, this.getWidth(), 2*this.getHeight()/8);
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(0, 6*this.getHeight()/8, 2*this.getWidth()/8, 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image MobT = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrap.png"));
			Image MobTX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapX.png"));
			Image MobTD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobTrapDamaged.png"));
			if (Fen.keyflag>1){
				if (MobT1Life>0) g.drawImage(MobT, MobT1x, MobT1y, 40, 40, this);
				if ((MobT1TrapOn==1 || MobT1TrapOn==3) && MobT1Life>0) g.drawImage(MobTX, MobT1x, MobT1y, 40, 40, this);
				if (MobT1Hit==1 && MobT1Life>0){
					g.drawImage(MobTD, MobT1x, MobT1y, 40, 40, this);
					if (MobT1HitOn==0){
						MobT1Life-=1;
						MobT1HitOn=1;
						}
					MobT1Hit=0;
					}
				if (MobT2Life>0) g.drawImage(MobT, MobT2x, MobT2y, 40, 40, this);
				if ((MobT2TrapOn==1 || MobT2TrapOn==3) && MobT2Life>0) g.drawImage(MobTX, MobT2x, MobT2y, 40, 40, this);
				if (MobT2Hit==1 && MobT2Life>0){
					g.drawImage(MobTD, MobT2x, MobT2y, 40, 40, this);
					if (MobT2HitOn==0){
						MobT2Life-=1;
						MobT2HitOn=1;
						}
					MobT2Hit=0;
					}
				}
			if (Fen.AttackOn==0){
				MobT1HitOn=0;
				MobT2HitOn=0;
				}
			} catch (IOException e) {}   
//*****T1
	if (Fen.keyflag>1){
		if (MobT1TrapOn==0){
			MobT1y=560;
			MobT1x=160;
			}
		if (Fen.MobHitBox(MobT1x, MobT1y, 40, 40) && MobT1Hit==0) MobT1Hit=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 2) && MobT1TrapOn==0) MobT1TrapOn=1;
		if (MobT1TrapOn==1 && MobT1x+40<600) MobT1x+=4;
		if ((MobT1x+40==600 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==1) MobT1TrapOn=2;
		if (MobT1TrapOn==2) MobT1x-=1;
		if (Fen.MobVision(MobT1x, MobT1y, 40, 40, 1) && MobT1TrapOn==0) MobT1TrapOn=3;
		if (MobT1TrapOn==3 && MobT1y>200) MobT1y-=4;
		if ((MobT1y==200 || Fen.Presence(MobT1x, MobT1y, 37, 37)) && MobT1TrapOn==3) MobT1TrapOn=4;
		if (MobT1TrapOn==4) MobT1y+=1;
		if (MobT1x==160 && MobT1y==560) MobT1TrapOn=0;
		if (Fen.Presence(MobT1x, MobT1y, 40, 40) && MobT1Life>0) Fen.Lifey+=1;
		}
//*****T2
	if (Fen.keyflag>1){
		if (MobT2TrapOn==0){
			MobT2y=600;
			MobT2x=200;
			}
		if (Fen.MobHitBox(MobT2x, MobT2y, 40, 40) && MobT2Hit==0) MobT2Hit=1;
		if (Fen.MobVision(MobT2x, MobT2y, 40, 40, 2) && MobT2TrapOn==0) MobT2TrapOn=1;
		if (MobT2TrapOn==1 && MobT2x+40<600) MobT2x+=4;
		if ((MobT2x+40==600 || Fen.Presence(MobT2x, MobT2y, 37, 37)) && MobT2TrapOn==1) MobT2TrapOn=2;
		if (MobT2TrapOn==2) MobT2x-=1;
		if (Fen.MobVision(MobT2x, MobT2y, 40, 40, 1) && MobT2TrapOn==0) MobT2TrapOn=3;
		if (MobT2TrapOn==3 && MobT2y>200) MobT2y-=4;
		if ((MobT2y==200 || Fen.Presence(MobT2x, MobT2y, 37, 37)) && MobT2TrapOn==3) MobT2TrapOn=4;
		if (MobT2TrapOn==4) MobT2y+=1;
		if (MobT2x==200 && MobT2y==600) MobT2TrapOn=0;
		if (Fen.Presence(MobT2x, MobT2y, 40, 40) && MobT2Life>0) Fen.Lifey+=1;
		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.x<=0 && 2*this.getHeight()/8<=Fen.y && Fen.y<=6*this.getHeight()/8){
			Fen.Room=18;
			Fen.Orientation='O';
			Fen.ChangeRoom=0;
			} 
		if (Fen.y+40>=this.getHeight() && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=15;
			Fen.Orientation='S';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 600, 200, 200, 0)) Fen.x=200;
		else if (Fen.Colision(0, 0, 600, 200, 1)) Fen.y=200;
		else if (Fen.Colision(600, 200, 200, 600, 2)) Fen.x=560;
		else if (Fen.Colision(0, 600, 200, 200, 3)) Fen.y=560;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Pan20 extends JPanel{
	int MobGCx=250;
	int MobGCy=250;
	int MobGCcubex=352;
	int MobGCcubey=376;
	int MobGCcubeLockx=0;
	int MobGCcubeLocky=0;
	int MobGCangry=0;
	static int MobGCTime=0;
	static int MobGCTimeOn=0;
	int MobGCHit=0;
	int MobGCHitOn=0;
	static int MobGCLife=30;
	static int Boss=0;
	public Pan20(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		JButton gris = new JButton();
		ToucheListener touche = new ToucheListener();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		SourisGame SG = new SourisGame();
		this.addMouseListener(SG);
		SourisGameMove SGm = new SourisGameMove();
		this.addMouseMotionListener(SGm);
		}
	public void paintComponent(Graphics g){
		g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.fillRect(0, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(6*this.getWidth()/8, 0, 2*this.getWidth()/8, this.getHeight());
		g.fillRect(0, 7*this.getHeight()/8, this.getWidth(), 2*this.getHeight()/8);

if (Fen.GameOver==0){	try{
			Image Emeraude = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/Emeraude.png"));
			if (Fen.Emeraude==0) g.drawImage(Emeraude, 380, 620, 40, 40, this);
			Image MurW = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MurW.png"));
			Image MobGC = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCube.png"));
			Image MobGCX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeX.png"));
			Image MobGCD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeDamaged.png"));
			Image MobGCa = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngry.png"));
			Image MobGCaX = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngryX.png"));
			Image MobGCaD = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Images/MobGiantCubeAngryDamaged.png"));
			if (Boss==1 && MobGCLife>0){
				if (MobGCangry==0) g.drawImage(MobGC, MobGCx, MobGCy, 300, 300, this);
				else g.drawImage(MobGCa, MobGCx, MobGCy, 300, 300, this);
				if (MobGCangry==0 && MobGCTime>5) g.drawImage(MobGCX, MobGCx, MobGCy, 300, 300, this);
				else if (MobGCangry==1 && MobGCTime>5) g.drawImage(MobGCaX, MobGCx, MobGCy, 300, 300, this);
				if (MobGCHit==1 && MobGCLife>0){
					if (MobGCangry==0) g.drawImage(MobGCD, MobGCx, MobGCy, 300, 300, this);
					else g.drawImage(MobGCaD, MobGCx, MobGCy, 300, 300, this);
					if (MobGCHitOn==0){
						MobGCLife-=1;
						MobGCHitOn=1;
						}
					MobGCHit=0;
					}
				if (Fen.AttackOn==0) MobGCHitOn=0;
				}
			if (MobGCLife>0){
				g.drawImage(MurW, 200, 575, 400, 25, this);
				if (Boss==1) g.drawImage(MurW, 200, -75, 400, 100, this);
				}
			} catch (IOException e) {}   
//*****GC
		if (Fen.Presence(200, 200, 400, 0) && Boss==0 && Fen.Emeraude==0 && Fen.Saphir!=1 && Fen.Rubis!=1 && Pan3.Boss==0 && Pan16.Boss==0){
			Boss=1;
			MobGCcubex=352;
			MobGCcubey=376;
			MobGCangry=0;
			}
	if (MobGCLife>0 && Boss==1){
		if (MobGCLife<=15) MobGCangry=1;
		if (MobGCTimeOn==0){
			MobGCTimeOn=1;
			new Mob1Time().start();
			}
		g.setColor(Color.magenta);
		if (MobGCTime<5){
			g.drawLine(MobGCx+125, MobGCy+30, Fen.x+(int)(Math.random()*40), Fen.y+(int)(Math.random()*40));
			g.drawLine(MobGCx+175, MobGCy+30, Fen.x+(int)(Math.random()*40), Fen.y+(int)(Math.random()*40));
			}
		if (MobGCTime==5){
			g.drawLine(MobGCx+125, MobGCy+30, Fen.x+20, Fen.y+20);
			g.drawLine(MobGCx+175, MobGCy+30, Fen.x+20, Fen.y+20);
			MobGCcubeLockx=Fen.x+20;
			MobGCcubeLocky=Fen.y+20;
			}
		if (MobGCTime>5){
			if (MobGCcubeLockx==400) MobGCcubeLockx+=1;
			if (MobGCcubeLocky==416) MobGCcubeLocky+=1;
			int Y = (int)((MobGCcubeLocky-416)*(MobGCcubex+48-400)/(MobGCcubeLockx-400))+416;
			int X = (int)((MobGCcubeLockx-400)*(MobGCcubey+40-416)/(MobGCcubeLocky-416))+400;
			g.setColor(Color.gray);
			g.fillRect(MobGCcubex, MobGCcubey, 96, 81);
			if (MobGCcubeLocky>=MobGCy+310){
				MobGCcubey+=3+2*MobGCangry;
				if (MobGCcubex+48<X) MobGCcubex+=2+2*MobGCangry;
				else if (MobGCcubex+48>X) MobGCcubex-=2+2*MobGCangry;
				}
			if (MobGCcubeLocky<=MobGCy+20){
				MobGCcubey-=3+2*MobGCangry;
				if (MobGCcubex+48<X) MobGCcubex+=2+2*MobGCangry;
				else if (MobGCcubex+48>X) MobGCcubex-=2+2*MobGCangry;
				}
			if (MobGCy+20<MobGCcubeLocky && MobGCcubeLocky<MobGCy+310){
				if (MobGCcubeLockx>MobGCx+150){
					MobGCcubex+=3+2*MobGCangry;
					if (MobGCcubey+40<Y) MobGCcubey+=2+2*MobGCangry;
					else if (MobGCcubey+40>Y) MobGCcubey-=2+2*MobGCangry;
					}
				if (MobGCcubeLockx<MobGCx+150){
					MobGCcubex-=3+2*MobGCangry;
					if (MobGCcubey+40<Y) MobGCcubey+=2+2*MobGCangry;
					else if (MobGCcubey+40>Y) MobGCcubey-=2+2*MobGCangry;
					}
				}
			}
		if (MobGCangry==1 && MobGCTime==8) MobGCTime=10;
		if (MobGCTime==10){
			if (MobGCangry==0) MobGCTime=0;
			else MobGCTime=4;
			MobGCcubex=352;
			MobGCcubey=376;
			}
		if ((Fen.MobHitBox(MobGCx+90, MobGCy, 120, 75) || Fen.MobHitBox(MobGCx+6, MobGCy+75, 33, 162) || Fen.MobHitBox(MobGCx+261, MobGCy+75, 33, 162) || Fen.MobHitBox(MobGCx+45, MobGCy+75, 210, 183) || Fen.MobHitBox(MobGCx+90, MobGCy+258, 120, 42)) && MobGCHit==0 && MobGCTime>5){
			MobGCHit=1;
			}
		if (Fen.Presence(MobGCx+90, MobGCy, 120, 75) || Fen.Presence(MobGCx+6, MobGCy+75, 33, 162) || Fen.Presence(MobGCx+261, MobGCy+75, 33, 162) || Fen.Presence(MobGCx+45, MobGCy+75, 210, 183) || Fen.Presence(MobGCx+90, MobGCy+258, 120, 42) || Fen.Presence(MobGCcubex, MobGCcubey, 96, 81)){
			Fen.Lifey+=1;
			}
		if (Fen.Colision(200, 0, 400, 25, 1)) Fen.y=25;
		}
}
//*****Fin Mob 

		g.setColor(Color.red);
		g.fillRect(Fen.x,Fen.y, 40, 40);
		if (Fen.y<=0 && 2*this.getHeight()/8<=Fen.x && Fen.x<=6*this.getHeight()/8){
			Fen.Room=7;
			Fen.Orientation='N';
			Fen.ChangeRoom=0;
			} 
		if (Fen.Colision(0, 0, 200, 700, 0)) Fen.x=200;
		else if (Fen.Colision(200, 700, 400, 100, 3)) Fen.y=660;
		else if (Fen.Colision(600, 0, 200, 700, 2)) Fen.x=560;
		else if (Fen.Colision(200, 575, 400, 25, 3) && MobGCLife>0) Fen.y=535;

		g.setColor(new Color(100, 100, 100));
		if (Fen.Attack==1) Fen.AttackOn=1;
		if (Fen.AttackOn==1){
			if (Fen.AttackRight==1) g.fillArc(Fen.x, Fen.y-20, 80, 80, Fen.arcStart, 20);
			if (Fen.AttackUp==1) g.fillArc(Fen.x-20, Fen.y-40, 80, 80, Fen.arcStart+90, 20);	
			if (Fen.AttackLeft==1) g.fillArc(Fen.x-40, Fen.y-20, 80, 80, Fen.arcStart+180, 20);	
			if (Fen.AttackDown==1) g.fillArc(Fen.x-20, Fen.y, 80, 80, Fen.arcStart-90, 20);	
			Fen.AttackTour();
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {}
			}

		g.setFont(new Font("Arial", Font.BOLD, 18));
		if (Fen.Presence(380, 620, 40, 40) && Fen.Emeraude==0 && Fen.Rubis!=1 && Fen.Saphir!=1){
			if (Fen.Interaction==2){
				Fen.Emeraude=1;
				Boss=0;
				}
			Fen.Interaction=1;
			g.setColor(Color.green);
			g.fillOval(390, 630, 20, 20);
			g.setColor(Color.black);
			g.drawString("A", 394, 646);
			}
		else Fen.Interaction=0;
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 15));
		g.drawString("HP:"+String.valueOf(-Fen.Lifey), 32, 20);
		g.fillRect(9, 191, 22, -182);
		g.setColor(Color.green);
		g.fillRect(10, 190, 20, Fen.Lifey);

		if (Fen.GameOver>0){
			Fen.Motion=0;
			if (Fen.Color255>0) Fen.Color255-=1;
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 40));
			g.setColor(new Color(Fen.GameOverColor, 0, 0));
			if (Fen.Color255<=170 && Fen.GameOverColor<255) Fen.GameOverColor+=1;
			if (Fen.Color255<=170) g.drawString("GAME OVER", 300, 380);
			g.setFont(new Font("Miriam Libre", Font.PLAIN, 25));
			if (Fen.GameOver==1) g.setColor(Color.white);
			else if (Fen.GameOver==2) g.setColor(Color.blue);
			if (Fen.GameOverColor==255) g.drawString("Recommencer ?", 310, 440);
			}
		try{
			Thread.sleep(4);
			} catch (InterruptedException e) {}
		}
	}
class Mob1Time extends Thread{
	public void run(){
		if (Pan3.MobGCTimeOn==1){
			try{
				sleep(1000);
				} catch (InterruptedException e) {}
			Pan3.MobGCTime+=1;
			Pan3.MobGCTimeOn=0;
			}
		if (Pan16.MobGCTimeOn==1){
			try{
				sleep(1000);
				} catch (InterruptedException e) {}
			Pan16.MobGCTime+=1;
			Pan16.MobGCTimeOn=0;
			}
		if (Pan20.MobGCTimeOn==1){
			try{
				sleep(1000);
				} catch (InterruptedException e) {}
			Pan20.MobGCTime+=1;
			Pan20.MobGCTimeOn=0;
			}
		if (Pan1.MobR1TimeOn==1){
			try{
				if (Pan1.MobR1Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan1.MobR1Sens=(int)(Math.random()*4);
			Pan1.MobR1TimeOn=0;
			}
		if (Pan5.MobR1TimeOn==1){
			try{
				if (Pan5.MobR1Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan5.MobR1Sens=(int)(Math.random()*4);
			Pan5.MobR1TimeOn=0;
			}
		if (Pan6.MobR1TimeOn==1){
			try{
				if (Pan6.MobR1Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan6.MobR1Sens=(int)(Math.random()*4);
			Pan6.MobR1TimeOn=0;
			}
		if (Pan9.MobR1TimeOn==1){
			try{
				if (Pan9.MobR1Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan9.MobR1Sens=(int)(Math.random()*4);
			Pan9.MobR1TimeOn=0;
			}
		if (Pan10.MobR1TimeOn==1){
			try{
				if (Pan10.MobR1Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan10.MobR1Sens=(int)(Math.random()*4);
			Pan10.MobR1TimeOn=0;
			}
		if (Pan13.MobR1TimeOn==1){
			try{
				if (Pan13.MobR1Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan13.MobR1Sens=(int)(Math.random()*4);
			Pan13.MobR1TimeOn=0;
			}
		if (Pan14.MobR1TimeOn==1){
			try{
				if (Pan14.MobR1Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan14.MobR1Sens=(int)(Math.random()*4);
			Pan14.MobR1TimeOn=0;
			}
		if (Pan2.MobP1TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan2.MobP1x, Pan2.MobP1y, 40, 40, Pan2.MobP1Sens)==false) Pan2.MobP1Sens=(int)(Math.random()*4);
			Pan2.MobP1TimeOn=0;
			}
		if (Pan15.MobP1TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan15.MobP1x, Pan15.MobP1y, 40, 40, Pan15.MobP1Sens)==false) Pan15.MobP1Sens=(int)(Math.random()*4);
			Pan15.MobP1TimeOn=0;
			}
		if (Pan17.MobP1TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan17.MobP1x, Pan17.MobP1y, 40, 40, Pan17.MobP1Sens)==false) Pan17.MobP1Sens=(int)(Math.random()*4);
			Pan17.MobP1TimeOn=0;
			}
		}
	}
class Mob2Time extends Thread{
	public void run(){
		if (Pan1.MobP1TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan1.MobP1x, Pan1.MobP1y, 40, 40, Pan1.MobP1Sens)==false) Pan1.MobP1Sens=(int)(Math.random()*4);
			Pan1.MobP1TimeOn=0;
			}
		if (Pan5.MobR2TimeOn==1){
			try{
				if (Pan5.MobR2Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan5.MobR2Sens=(int)(Math.random()*4);
			Pan5.MobR2TimeOn=0;
			}
		if (Pan6.MobR2TimeOn==1){
			try{
				if (Pan6.MobR2Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan6.MobR2Sens=(int)(Math.random()*4);
			Pan6.MobR2TimeOn=0;
			}
		if (Pan10.MobR2TimeOn==1){
			try{
				if (Pan10.MobR2Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan10.MobR2Sens=(int)(Math.random()*4);
			Pan10.MobR2TimeOn=0;
			}
		if (Pan14.MobR2TimeOn==1){
			try{
				if (Pan14.MobR2Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan14.MobR2Sens=(int)(Math.random()*4);
			Pan14.MobR2TimeOn=0;
			}
		if (Pan2.MobP2TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan2.MobP2x, Pan2.MobP2y, 40, 40, Pan2.MobP2Sens)==false) Pan2.MobP2Sens=(int)(Math.random()*4);
			Pan2.MobP2TimeOn=0;
			}
		if (Pan13.MobP1TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan13.MobP1x, Pan13.MobP1y, 40, 40, Pan13.MobP1Sens)==false) Pan13.MobP1Sens=(int)(Math.random()*4);
			Pan13.MobP1TimeOn=0;
			}
		if (Pan15.MobP2TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan15.MobP2x, Pan15.MobP2y, 40, 40, Pan15.MobP2Sens)==false) Pan15.MobP2Sens=(int)(Math.random()*4);
			Pan15.MobP2TimeOn=0;
			}
		}
	}
class Mob3Time extends Thread{
	public void run(){
		if (Pan5.MobR2TimeOn==1){
			try{
				if (Pan5.MobR2Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan5.MobR2Sens=(int)(Math.random()*4);
			Pan5.MobR2TimeOn=0;
			}
		if (Pan6.MobR3TimeOn==1){
			try{
				if (Pan6.MobR3Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan6.MobR3Sens=(int)(Math.random()*4);
			Pan6.MobR3TimeOn=0;
			}
		if (Pan2.MobP3TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan2.MobP3x, Pan2.MobP3y, 40, 40, Pan2.MobP3Sens)==false) Pan2.MobP3Sens=(int)(Math.random()*4);
			Pan2.MobP3TimeOn=0;
			}
		if (Pan5.MobP1TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan5.MobP1x, Pan5.MobP1y, 40, 40, Pan5.MobP1Sens)==false) Pan5.MobP1Sens=(int)(Math.random()*4);
			Pan5.MobP1TimeOn=0;
			}
		if (Pan10.MobP1TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan10.MobP1x, Pan10.MobP1y, 40, 40, Pan10.MobP1Sens)==false) Pan10.MobP1Sens=(int)(Math.random()*4);
			Pan10.MobP1TimeOn=0;
			}
		if (Pan13.MobP2TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan13.MobP2x, Pan13.MobP2y, 40, 40, Pan13.MobP2Sens)==false) Pan13.MobP2Sens=(int)(Math.random()*4);
			Pan13.MobP2TimeOn=0;
			}
		if (Pan14.MobP1TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan14.MobP1x, Pan14.MobP1y, 40, 40, Pan14.MobP1Sens)==false) Pan14.MobP1Sens=(int)(Math.random()*4);
			Pan14.MobP1TimeOn=0;
			}
		if (Pan15.MobP3TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan15.MobP3x, Pan15.MobP3y, 40, 40, Pan15.MobP3Sens)==false) Pan15.MobP3Sens=(int)(Math.random()*4);
			Pan15.MobP3TimeOn=0;
			}
		}
	}
class Mob4Time extends Thread{
	public void run(){
		if (Pan5.MobR2TimeOn==1){
			try{
				if (Pan5.MobR2Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan5.MobR2Sens=(int)(Math.random()*4);
			Pan5.MobR2TimeOn=0;
			}
		if (Pan6.MobR4TimeOn==1){
			try{
				if (Pan6.MobR4Afraid==0) sleep(1000*(1+(int)(Math.random()*4)));
				else sleep(500);
				} catch (InterruptedException e) {}
			Pan6.MobR4Sens=(int)(Math.random()*4);
			Pan6.MobR4TimeOn=0;
			}
		if (Pan2.MobP4TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan2.MobP4x, Pan2.MobP4y, 40, 40, Pan2.MobP4Sens)==false) Pan2.MobP4Sens=(int)(Math.random()*4);
			Pan2.MobP4TimeOn=0;
			}
		if (Pan15.MobP4TimeOn==1){
			try{
				sleep(1000*(1+(int)(Math.random()*2)));
				} catch (InterruptedException e) {}
			if (Fen.MobVision(Pan15.MobP4x, Pan15.MobP4y, 40, 40, Pan15.MobP4Sens)==false) Pan15.MobP4Sens=(int)(Math.random()*4);
			Pan15.MobP4TimeOn=0;
			}
		if (Pan8.BossTimeOn==1){
			try{
				sleep(1000);
				} catch (InterruptedException e) {}
			Pan8.BossTime+=1;
			Pan8.BossTimeOn=0;
			}
		}
	}
class TextTime extends Thread{
	public void run(){
		if (Fen.TextTimeOn==1){
			try{
				sleep(1000);
				} catch (InterruptedException e) {}
			Fen.TextTime+=1;
			Fen.TextTimeOn=0;
			}
		}
	}
//*ONWORK
class PlayBossFinalMusic extends Thread{
	public void run(){
			try{
				File BossFinalMusicFile = new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Musiques/BossFinal.wav");
				AudioInputStream BossFinalMusic = AudioSystem.getAudioInputStream(BossFinalMusicFile);
				Clip clip = AudioSystem.getClip();
				clip.open(BossFinalMusic);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				suspend();
				clip.stop();
				if (Fen.GameOver==1) stop();
				
				BossFinalMusicFile = new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Labyrinthe/Musiques/BossFinalAccelerated.wav");
				BossFinalMusic = AudioSystem.getAudioInputStream(BossFinalMusicFile);
				//Clip clip2 = AudioSystem.getClip();
				clip.close();
				clip.open(BossFinalMusic);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				suspend();
				clip.stop();
				if (Fen.GameOver==1) stop();

				} catch (Exception e){System.out.println(e);}
			}
		}
	
class ToucheListener implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (c==0x1B && Fen.PassCinematic==1) Fen.TextTime=50; //**Echap
		if (k=='l') Fen.Lifey=-180;

		if (Fen.Interaction==1 && k=='a') Fen.Interaction=2;
		if (k=='z'){
			Fen.AttackOn=0;
			Fen.Attack=1;
			Fen.AttackUp=1;
			}
		if (k=='q'){
			Fen.AttackOn=0;
			Fen.Attack=1;
			Fen.AttackLeft=1;
			}
		if (k=='d'){
			Fen.AttackOn=0;
			Fen.Attack=1;
			Fen.AttackRight=1;
			}
		if (k=='x'){
			Fen.AttackOn=0;
			Fen.Attack=1;
			Fen.AttackDown=1;
			}
		//if (k=='s') Fen.Saphir=1;
		//if (k=='r') Fen.Rubis=1;
		if (k=='e') Fen.Emeraude=1;
		//if (k=='g') Fen.Rubis=Fen.Emeraude=Fen.Saphir=1;
		if (k=='i'){
			//System.out.println(Pan1.MobR1Life+"\t"+Pan1.MobT1Life+"\t"+Pan1.MobP1Life);
			//System.out.println("Rubis: "+Fen.Rubis);
			//System.out.println("Emeraude: "+Fen.Emeraude);
			//System.out.println("Saphir: "+Fen.Saphir);
			}
		//if (k=='p') System.out.println(Fen.x+"\t"+Fen.y);
		if (Fen.keyflag!=5){
			if (c==0x25){
				Fen.sens=0;
				//Fen.Motion=1;
				if (Fen.Motion==1) Fen.x-=7;
				}
			if (c==0x26){
				Fen.sens=1;
				//Fen.Motion=1;
				if (Fen.Motion==1) Fen.y-=7;
				}
			if (c==0x27){
				Fen.sens=2;
				//Fen.Motion=1;
				if (Fen.Motion==1) Fen.x+=7;
				}
			if (c==0x28){
				Fen.sens=3;
				//Fen.Motion=1;
				if (Fen.Motion==1) Fen.y+=7;
				}
			}
		if (Fen.GameOver==1 && c=='\n') Labyrinthe.Recommencer();
 		//System.out.println(c);
		}
	public void keyReleased(KeyEvent event){
		//int c = event.getKeyCode();
		//if (c==0x25 || c==0x26 || c==0x27 || c==0x28) Fen.Motion=0;
		}
	}
class SourisGame implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int x = event.getX();
		int y = event.getY();
		if (c==1 && Fen.Game==1) Fen.Game=2;
		//if (c==3) System.out.println(x+"\t"+y);
		if (Fen.GameOver==2 && c==1){
			Labyrinthe.Recommencer();
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisGameMove implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){
		int x = event.getX();
		int y = event.getY();
		if (Fen.Game==0 && 363<=x && x<=443 && 437<=y && y<=455) Fen.Game=1;
		else if (Fen.Game==1 && ((363>x || x>443) || (437>y || y>455))) Fen.Game=0;


//** Position de "Recommencer ?" : 310, 440
		if (Fen.GameOver==1 && 310<=x && x<=500 && 422<=y && y<=440) Fen.GameOver=2;
		else if (Fen.GameOver==2 && ((310>x || x>500) || (422>y || y>440))) Fen.GameOver=1;
		}
	public void mouseDragged(MouseEvent event){}
	}
public class Labyrinthe{
	public static void Recommencer(){
			Fen.GameOver=0;
			Fen.GameOverColor=0;
			Fen.Color255=255;
			Fen.Color128=128;
			Fen.Room=8;
			Fen.Orientation='R';
			Fen.ChangeRoom=0;
			Fen.keyflag=1;
			Fen.Lifey=-180;
			Fen.Rubis=0;
			Fen.Saphir=0;
			Fen.Emeraude=0;
			Fen.Motion=1;
			Pan3.Boss=0;
			Pan3.MobGCLife=30;
			Pan3.MobGCTime=0;
			Pan16.Boss=0;
			Pan16.MobGCLife=30;
			Pan16.MobGCTime=0;
			Pan20.Boss=0;
			Pan20.MobGCLife=30;
			Pan20.MobGCTime=0;
			Pan2.Boss=2;
			Pan2.MobP1Life=4;
			Pan2.MobP2Life=4;
			Pan2.MobP3Life=4;
			Pan2.MobP4Life=4;
			Pan6.Boss=2;
			Pan9.pink=175;
			Pan10.Boss=2;
			Pan10.MobT2Life=3;
			Pan10.MobT3Life=3;
			Pan14.brown=64;
			Pan15.Boss=3;
			Pan15.MobT1Life=3;
			Pan15.MobT2Life=3;
			Pan15.MobT3Life=3;
			Pan15.MobT4Life=3;
			Pan15.MobT5Life=3;
			Pan15.MobT6Life=3;
			Pan15.MobT7Life=3;
			Pan15.MobT8Life=3;
			Pan15.MobT9Life=3;
			Pan15.MobT10Life=3;
			Pan15.MobP1Life=4;
			Pan15.MobP2Life=4;
			Pan15.MobP3Life=4;
			Pan15.MobP4Life=4;
			Pan18.pink=175;
			Pan8.blanc=255;
			Pan8.Gris=128;
			Pan8.Boss=0;
			Pan8.BossTime=0;
			Pan8.BossLife=780;
		}

	public static void main(String[] args){
		Fen fen = new Fen("Fenetre", 816, 840);
		//Fen fen1 = new Fen("Fenetre", 816, 840);
		//Fen fen2 = new Fen("Fenetre", 816, 840);
		PanTitle title = new PanTitle(800, 800);
		Pan0 pan0 = new Pan0(800, 800);
		Pan1 pan1 = new Pan1(800, 800);
		Pan2 pan2 = new Pan2(800, 800);
		Pan3 pan3 = new Pan3(800, 800);
		Pan4 pan4 = new Pan4(800, 800);
		Pan5 pan5 = new Pan5(800, 800);
		Pan6 pan6 = new Pan6(800, 800);
		Pan7 pan7 = new Pan7(800, 800);
		Pan8 pan8 = new Pan8(800, 800);
		Pan9 pan9 = new Pan9(800, 800);
		Pan10 pan10 = new Pan10(800, 800);
		Pan11 pan11 = new Pan11(800, 800);
		Pan12 pan12 = new Pan12(800, 800);
		Pan13 pan13 = new Pan13(800, 800);
		Pan14 pan14 = new Pan14(800, 800);
		Pan15 pan15 = new Pan15(800, 800);
		Pan16 pan16 = new Pan16(800, 800);
		Pan17 pan17 = new Pan17(800, 800);
		Pan18 pan18 = new Pan18(800, 800);
		Pan19 pan19 = new Pan19(800, 800);
		Pan20 pan20 = new Pan20(800, 800);
		title.setVisible(true);
		pan0.setVisible(false);
		pan1.setVisible(false);
		pan2.setVisible(false);
		pan3.setVisible(false);
		pan4.setVisible(false);
		pan5.setVisible(false);
		pan6.setVisible(false);
		pan7.setVisible(false);
		pan8.setVisible(false);
		pan9.setVisible(false);
		pan10.setVisible(false);
		pan11.setVisible(false);
		pan12.setVisible(false);
		pan13.setVisible(false);
		pan14.setVisible(false);
		pan15.setVisible(false);
		pan16.setVisible(false);
		pan17.setVisible(false);
		pan18.setVisible(false);
		pan19.setVisible(false);
		pan20.setVisible(false);
		fen.setLayout(new GridBagLayout());
		fen.add(title);
		fen.add(pan0);
		fen.add(pan1);
		fen.add(pan2);
		fen.add(pan3);
		fen.add(pan4);
		fen.add(pan5);
		fen.add(pan6);
		fen.add(pan7);
		fen.add(pan8);
		fen.add(pan9);
		fen.add(pan10);
		fen.add(pan11);
		fen.add(pan12);
		fen.add(pan13);
		fen.add(pan14);
		fen.add(pan15);
		fen.add(pan16);
		fen.add(pan17);
		fen.add(pan18);
		fen.add(pan19);
		fen.add(pan20);

		fen.Rubis=fen.Saphir=2;
		while (true){
			while (fen.Game!=2) title.repaint();
			if (fen.Lifey==0 && fen.GameOver==0) fen.GameOver=1;
			if (fen.Lifey>0) fen.Lifey=0;
			if (fen.Motion==5000){
				if (fen.sens==0) fen.x-=1;
				if (fen.sens==1) fen.y-=1;
				if (fen.sens==2) fen.x+=1;
				if (fen.sens==3) fen.y+=1;
				}
			if (fen.Room==0){
				title.setVisible(false);
				pan0.setVisible(true);
				pan0.repaint();
				}
			else if (fen.Room==1){
				if (fen.ChangeRoom==0){
					pan0.setVisible(false);
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					pan2.setVisible(false);
					pan3.setVisible(false);
					pan5.setVisible(false);
					pan1.MobT1y=200;
					pan1.MobT1TrapOn=0;
					pan1.MobR1HitOn=1;
					pan1.MobT1HitOn=1;
					pan1.MobP1HitOn=1;
					pan1.MobR1Life=2;
					pan1.MobT1Life=3;
					pan1.MobP1Life=4;
					pan1.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan1.repaint();
				}
			else if (fen.Room==2){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='E') fen.x=1;
					pan1.setVisible(false);
					pan6.setVisible(false);
					pan2.setVisible(true);
					pan2.MobT1HitOn=1;
					pan2.MobT1TrapOn=0;
					pan2.MobT1Life=3;
					fen.ChangeRoom=1;
					}
				pan2.repaint();
				}
			else if (fen.Room==3){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					pan1.setVisible(false);
					pan3.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan3.repaint();
				}
			else if (fen.Room==4){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					pan8.setVisible(false);
					pan5.setVisible(false);
					pan4.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan4.repaint();
				}
			else if (fen.Room==5){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan1.setVisible(false);
					pan4.setVisible(false);
					pan6.setVisible(false);
					pan9.setVisible(false);
					pan5.setVisible(true);
					pan5.MobT1TrapOn=0;
					pan5.MobR1HitOn=1;
					pan5.MobR2HitOn=1;
					pan5.MobT1HitOn=1;
					pan5.MobP1HitOn=1;
					Pan5.MobR1Life=2;
					Pan5.MobR2Life=2;
					Pan5.MobT1Life=3;
					Pan5.MobP1Life=4;
					fen.ChangeRoom=1;
					}
				pan5.repaint();
				}
			else if (fen.Room==6){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan5.setVisible(false);
					pan10.setVisible(false);
					pan7.setVisible(false);
					pan2.setVisible(false);
					pan6.setVisible(true);
					if (fen.keyflag>1){
						pan6.MobR1HitOn=1;
						pan6.MobR2HitOn=1;
						pan6.MobR3HitOn=1;
						pan6.MobR4HitOn=1;
						pan6.MobR1Life=2;
						pan6.MobR2Life=2;
						pan6.MobR3Life=2;
						pan6.MobR4Life=2;
						pan6.MobR1x=200+(int)(Math.random()*561);
						pan6.MobR1y=200+(int)(Math.random()*561);
						pan6.MobR2x=200+(int)(Math.random()*561);
						pan6.MobR2y=200+(int)(Math.random()*561);
						pan6.MobR3x=200+(int)(Math.random()*561);
						pan6.MobR3y=200+(int)(Math.random()*561);
						pan6.MobR4x=200+(int)(Math.random()*561);
						pan6.MobR4y=200+(int)(Math.random()*561);
						}
					pan6.MobT1TrapOn=0;
					pan6.MobT2TrapOn=0;
					pan6.MobT1HitOn=1;
					pan6.MobT2HitOn=1;
					pan6.MobT1Life=3;
					pan6.MobT2Life=3;
					fen.ChangeRoom=1;
					}
				pan6.repaint();
				}
			else if (fen.Room==7){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan6.setVisible(false);
					pan11.setVisible(false);
					pan20.setVisible(false);
					pan7.setVisible(true);
					pan7.MobT1TrapOn=0;
					pan7.MobT1HitOn=1;
					pan7.MobT1Life=3;
					fen.ChangeRoom=1;
					}
				pan7.repaint();
				}
			else if (fen.Room==8){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='R'){
						fen.x=380;
						fen.y=400;
						}
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan12.setVisible(false);
					pan4.setVisible(false);
					pan1.setVisible(false);
					pan2.setVisible(false);
					pan3.setVisible(false);
					pan5.setVisible(false);
					pan6.setVisible(false);
					pan7.setVisible(false);
					pan9.setVisible(false);
					pan10.setVisible(false);
					pan11.setVisible(false);
					pan13.setVisible(false);
					pan14.setVisible(false);
					pan15.setVisible(false);
					pan16.setVisible(false);
					pan17.setVisible(false);
					pan18.setVisible(false);
					pan19.setVisible(false);
					pan20.setVisible(false);
					pan8.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan8.repaint();
				}
			else if (fen.Room==9){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan13.setVisible(false);
					pan10.setVisible(false);
					pan5.setVisible(false);
					pan9.setVisible(true);
					pan9.MobR1HitOn=1;
					pan9.MobR1Life=2;
					fen.ChangeRoom=1;
					}
				pan9.repaint();
				}
			else if (fen.Room==10){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan9.setVisible(false);
					pan14.setVisible(false);
					pan11.setVisible(false);
					pan6.setVisible(false);
					pan10.setVisible(true);
					pan10.MobT1TrapOn=0;
					pan10.MobT5TrapOn=0;
					pan10.MobT4TrapOn=0;
					pan10.MobR1HitOn=1;
					pan10.MobR2HitOn=1;
					pan10.MobT5HitOn=1;
					pan10.MobT1HitOn=1;
					pan10.MobT4HitOn=1;
					pan10.MobP1HitOn=1;
					pan10.MobR1Life=2;
					pan10.MobR2Life=2;
					pan10.MobT5Life=3;
					pan10.MobT1Life=3;
					pan10.MobT4Life=3;
					pan10.MobP1Life=4;
					fen.ChangeRoom=1;
					}
				pan10.repaint();
				}
			else if (fen.Room==11){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan15.setVisible(false);
					pan10.setVisible(false);
					pan7.setVisible(false);
					pan11.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan11.repaint();
				}
			else if (fen.Room==12){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan8.setVisible(false);
					pan12.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan12.repaint();
				}
			else if (fen.Room==13){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan17.setVisible(false);
					pan14.setVisible(false);
					pan9.setVisible(false);
					pan13.setVisible(true);
					pan13.MobT1TrapOn=0;
					pan13.MobR1HitOn=1;
					pan13.MobT1HitOn=1;
					pan13.MobP1HitOn=1;
					pan13.MobP2HitOn=1;
					pan13.MobR1Life=2;
					pan13.MobT1Life=3;
					pan13.MobP1Life=4;
					pan13.MobP2Life=4;
					fen.ChangeRoom=1;
					}
				pan13.repaint();
				}
			else if (fen.Room==14){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan13.setVisible(false);
					pan18.setVisible(false);
					pan10.setVisible(false);
					pan14.setVisible(true);
					pan14.MobT1TrapOn=0;
					pan14.MobR1HitOn=1;
					pan14.MobR2HitOn=1;
					pan14.MobT1HitOn=1;
					pan14.MobP1HitOn=1;
					pan14.MobR1Life=2;
					pan14.MobR2Life=2;
					pan14.MobT1Life=3;
					pan14.MobP1Life=4;
					fen.ChangeRoom=1;
					}
				pan14.repaint();
				}
			else if (fen.Room==15){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan19.setVisible(false);
					pan11.setVisible(false);
					pan15.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan15.repaint();
				}
			else if (fen.Room==16){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					pan17.setVisible(false);
					pan16.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan16.repaint();
				}
			else if (fen.Room==17){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan16.setVisible(false);
					pan13.setVisible(false);
					pan18.setVisible(false);
					pan17.setVisible(true);
					pan17.MobP1HitOn=1;
					pan17.MobP1Life=4;
					fen.ChangeRoom=1;
					}
				pan17.repaint();
				}
			else if (fen.Room==18){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='O') fen.x=pan1.getWidth()-41;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan17.setVisible(false);
					pan19.setVisible(false);
					pan14.setVisible(false);
					pan18.setVisible(true);
					pan18.MobT1TrapOn=0;
					pan18.MobT1HitOn=1;
					pan18.MobT1Life=3;
					fen.ChangeRoom=1;
					}
				pan18.repaint();
				}
			else if (fen.Room==19){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='E') fen.x=1;
					if (fen.Orientation=='N') fen.y=pan1.getHeight()-41;
					pan18.setVisible(false);
					pan15.setVisible(false);
					pan19.setVisible(true);
					pan19.MobT1TrapOn=0;
					pan19.MobT2TrapOn=0;
					pan19.MobT1HitOn=1;
					pan19.MobT2HitOn=1;
					pan19.MobT1Life=3;
					pan19.MobT2Life=3;
					fen.ChangeRoom=1;
					}
				pan19.repaint();
				}
			else if (fen.Room==20){
				if (fen.ChangeRoom==0){
					if (fen.Orientation=='S') fen.y=1;
					pan7.setVisible(false);
					pan20.setVisible(true);
					fen.ChangeRoom=1;
					}
				pan20.repaint();
				}
			try{
				Thread.sleep(2);
				} catch (InterruptedException e) {}
			}
		}
}