import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

class Fen extends JFrame{
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

class Pan extends JPanel{
	static int dinox=50;
	static int dinoy=150;
	static int Down=0;
	static int SautOn=0;
	static int SautWhile=0;
	static int Clo1x=500;
	static int Clo1y=30+(int)(Math.random()*71);
	static int Clo2x=550;
	static int Clo2y=30+(int)(Math.random()*71);
	static int Clo3x=700;
	static int Clo3y=30+(int)(Math.random()*71);
	static int Clo4x=200;
	static int Clo4y=30+(int)(Math.random()*71);
	static int Sol1x=0;
	static int Sol2x=600;
	static int star1x=500;
	static int star1y=20;
	static int star2x=300;
	static int star2y=50;
	static int moonx=200;
	static int moony=50;
	static int MoonFace=0;
	static int Obst1=(int)(Math.random()*7);
	static int Obst2=(int)(Math.random()*7);
	static int Obst3=(int)(Math.random()*7);
	static int By=50;
	static int Ob1x=2000;
	static int Ob2x=2600;
	static int Ob3x=3000;
	static int R=0;
	static int speed=6;
	static int Night=0;
	static int NightColor=255;
	static int NightLong=0;
	static int keyflag=0;
	static int ScoreU=1;
	static int ScoreD=0;
	static int ScoreC=0;
	static int ScoreM=0;
	static int ScoreDM=0;
	static int ScoreTimeOn=0;
	static int ScoringTic=0;
	static int ScoringTimeOn=0;

	static int Begin=0;
	static int BestScoreU=0;
	static int BestScoreD=0;
	static int BestScoreC=0;
	static int BestScoreM=0;
	static int BestScoreDM=0;
	JButton gris = new JButton();
	SautListener sautTouche = new SautListener();
	SourisAgain Sagain = new SourisAgain();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));

		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(sautTouche);
		this.add(gris);

		this.addMouseListener(Sagain);
		}
	public void paintComponent(Graphics g){
		if (Night==0 && NightColor<255) NightColor+=5;
		else if (Night==1  && NightColor>0) NightColor-=5;
		g.setColor(new Color(NightColor, NightColor, NightColor));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(new Color(255-NightColor, 255-NightColor, 255-NightColor));
		g.setFont(new Font("New Times Roman", Font.BOLD, 15));
		if (ScoringTimeOn==1) g.drawString(String.valueOf(ScoreDM)+String.valueOf(ScoreM)+String.valueOf(ScoreC)+"00" ,this.getWidth()-50, 13);
		if (Begin==2 && ScoringTimeOn==0) g.drawString(String.valueOf(ScoreDM)+String.valueOf(ScoreM)+String.valueOf(ScoreC)+String.valueOf(ScoreD)+String.valueOf(ScoreU) ,this.getWidth()-50, 13);
		if (BestScoreU!=0) g.drawString("HI  "+String.valueOf(BestScoreDM)+String.valueOf(BestScoreM)+String.valueOf(BestScoreC)+String.valueOf(BestScoreD)+String.valueOf(BestScoreU) , 480, 13);

		//g.drawString(String.valueOf(speed), 40, 20);
		//g.drawString(String.valueOf(ScoringTic), 20, 20);

		if (Down==1) dinoy+=10;
		if (dinoy>150){
			dinoy=150;
			SautOn=0;
			}
		int Results=ScoreU+10*ScoreD+100*ScoreC+1000*ScoreM+10000*ScoreDM;
		if (Results%700==0){
			Night=1;
			NightLong=Results;
			}
		if (Results==NightLong+250) Night=0;

		if (Results%100==0 && ScoringTimeOn==0){
			new ScoringTime().start();
			ScoringTimeOn=1;
			}
		if (ScoringTimeOn==1){
			if (Night==0) g.setColor(Color.white);
			else g.setColor(Color.black);
			if (ScoringTic%2==0) g.fillRect(550, 0, 50, 13);
			}
		if (ScoringTic==30 && speed<10){
			speed+=1;
			ScoringTic=0;
			}
			

if (Night==0){	try{
			Image cloud = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Cloud.png"));
			Image Sol1 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Ground 1.png"));
			Image Sol2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Ground 2.png"));
			Image Sc = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/SCactus.png"));
			Image MSc2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/MSCactus2.png"));
			Image MSc3 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/MSCactus3.png"));
			Image Tc = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/TCactus.png"));
			Image MTc = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/MTCactus2.png"));
			Image Fc = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/FamilyCactus.png"));
			Image DstandR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Dinosaure Stand Reverse.png"));
			Image Dstand1R = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Dinosaure Stand 1 Reverse.png"));
			Image Dstand2R = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Dinosaure Stand 2 Reverse.png"));
			Image Ddown1R = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Dinosaure Down 1 Reverse.png"));
			Image Ddown2R = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Dinosaure Down 2 Reverse.png"));
			Image Bird1 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Bird 1.png"));
			Image Bird2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Bird 2.png"));
			Image DfR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Dinosaure Fail Reverse.png"));
			Image Rbutton = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Reset Button.png"));
			Image GO = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Game Over.png"));

			g.drawImage(cloud, Clo1x, Clo1y, 46, 13, this);
			g.drawImage(cloud, Clo2x, Clo2y, 46, 13, this);
			g.drawImage(cloud, Clo3x, Clo3y, 46, 13, this);
			g.drawImage(cloud, Clo4x, Clo4y, 46, 13, this);
			if (Clo1x<-50){
				Clo1x=600+(int)(Math.random()*50);
				Clo1y=30+(int)(Math.random()*71);
				}
			if (Clo2x<-50){
				Clo2x=600+(int)(Math.random()*50);
				Clo2y=30+(int)(Math.random()*71);
				}
			if (Clo3x<-50){
				Clo3x=600+(int)(Math.random()*50);
				Clo3y=30+(int)(Math.random()*71);
				}
			if (Clo4x<-50){
				Clo4x=600+(int)(Math.random()*50);
				Clo4y=30+(int)(Math.random()*71);
				}
			g.drawImage(Sol1, Sol1x, 140, 600, 20, this);
			g.drawImage(Sol2, Sol2x, 140, 600, 20, this);
			if (Sol1x<=-600) Sol1x=600;
			if (Sol2x<=-600) Sol2x=Sol1x+600;
		
			if (Obst1==0) g.drawImage(Sc, Ob1x, 150-33, 15, 33, this);
			else if (Obst1==1) g.drawImage(MSc2, Ob1x, 150-33, 32, 33, this);
			else if (Obst1==2) g.drawImage(MSc3, Ob1x, 150-33, 49, 33, this);
			else if (Obst1==3) g.drawImage(Tc, Ob1x, 150-46, 23, 46, this);
			else if (Obst1==4) g.drawImage(MTc, Ob1x, 150-46, 48, 46, this);
			else if (Obst1==5) g.drawImage(Fc, Ob1x, 150-47, 73, 47, this);
			else if (Obst1==6){
				if (R%20<10) g.drawImage(Bird1, Ob1x, By, 42, 26, this);
				else if (R%20>=10) g.drawImage(Bird2, Ob1x, By+5, 42, 30, this);
				}
			if (Ob1x<=-80){
				if (Obst1==6){
					By=45+40*(int)(Math.random()*3);
					if (Obst2==6) while (Obst1==6) Obst1=(int)(Math.random()*7);
					}
				Ob1x=Ob3x+300+100*(int)(Math.random()*5);
				Obst1=(int)(Math.random()*7);
				} 

			if (Obst2==0) g.drawImage(Sc, Ob2x, 150-33, 15, 33, this);
			else if (Obst2==1) g.drawImage(MSc2, Ob2x, 150-33, 32, 33, this);
			else if (Obst2==2) g.drawImage(MSc3, Ob2x, 150-33, 49, 33, this);
			else if (Obst2==3) g.drawImage(Tc, Ob2x, 150-46, 23, 46, this);
			else if (Obst2==4) g.drawImage(MTc, Ob2x, 150-46, 48, 46, this);
			else if (Obst2==5) g.drawImage(Fc, Ob2x, 150-47, 73, 47, this);
			else if (Obst2==6){
				if (R%20<10) g.drawImage(Bird1, Ob2x, By, 42, 26, this);
				else if (R%20>=10) g.drawImage(Bird2, Ob2x, By+5, 42, 30, this);
				}
			if (Ob2x<=-80){
				if (Obst2==6){
					By=45+40*(int)(Math.random()*3);
					if (Obst3==6) while (Obst2==6) Obst2=(int)(Math.random()*7);
					}
				Ob2x=Ob1x+300+100*(int)(Math.random()*5);
				Obst2=(int)(Math.random()*7);
				}

			if (Obst3==0) g.drawImage(Sc, Ob3x, 150-33, 15, 33, this);
			else if (Obst3==1) g.drawImage(MSc2, Ob3x, 150-33, 32, 33, this);
			else if (Obst3==2) g.drawImage(MSc3, Ob3x, 150-33, 49, 33, this);
			else if (Obst3==3) g.drawImage(Tc, Ob3x, 150-46, 23, 46, this);
			else if (Obst3==4) g.drawImage(MTc, Ob3x, 150-46, 48, 46, this);
			else if (Obst3==5) g.drawImage(Fc, Ob3x, 150-47, 73, 47, this);
			else if (Obst3==6){
				if (R%20<10) g.drawImage(Bird1, Ob3x, By, 42, 26, this);
				else if (R%20>=10) g.drawImage(Bird2, Ob3x, By+5, 42, 30, this);
				}
			if (Ob3x<=-80){
				if (Obst3==6){
					By=45+40*(int)(Math.random()*3);
					if (Obst1==6) while (Obst3==6) Obst3=(int)(Math.random()*7);
					}
				Ob3x=Ob2x+300+100*(int)(Math.random()*5);
				Obst3=(int)(Math.random()*7);
				}
			
			if (dinoy==150 && Down==0){
				if (R%16<8) g.drawImage(Dstand1R, dinox, dinoy, 40, -43, this);
				else if (R%16>=8) g.drawImage(Dstand2R, dinox, dinoy, 40, -43, this);
				}
			else if (dinoy==150 && Down==1){
				if (R%16<8) g.drawImage(Ddown1R, dinox, dinoy, 55, -26, this);
				else if (R%16>=8) g.drawImage(Ddown2R, dinox, dinoy, 55, -26, this);
				}
			else g.drawImage(DstandR, dinox, dinoy, 40, -43, this);

			if (keyflag==0 && Begin==2){
				R+=1;
				Clo1x-=(int)(speed/4);
				Clo2x-=(int)(speed/4);
				Clo3x-=(int)(speed/4);
				Clo4x-=(int)(speed/4);
				Sol1x-=speed;
				Sol2x-=speed;
				Ob1x-=speed;
				Ob2x-=speed;
				Ob3x-=speed;
				}
			
			if (keyflag==1){
				g.drawImage(DfR, dinox, dinoy, 40, -43, this);
				g.drawImage(Rbutton, 283, 95, 34, 30, this);
				g.drawImage(GO, 205, 55, 191, 11, this);
				Down=0;
				}
			MoonFace=(int)(Math.random()*2);
			} catch (IOException e) {} }

if (Night==1){    try{
			Image cloud = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Cloud.png"));
			Image Sol1 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Ground 1.png"));
			Image Sol2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Ground 2.png"));
			Image Sc = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night SCactus.png"));
			Image MSc2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night MSCactus2.png"));
			Image MSc3 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night MSCactus3.png"));
			Image Tc = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night TCactus.png"));
			Image MTc = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night MTCactus2.png"));
			Image Fc = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night FamilyCactus.png"));
			Image DstandR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Dinosaure Stand Reverse.png"));
			Image Dstand1R = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Dinosaure Stand 1 Reverse.png"));
			Image Dstand2R = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Dinosaure Stand 2 Reverse.png"));
			Image Ddown1R = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Dinosaure Down 1 Reverse.png"));
			Image Ddown2R = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Dinosaure Down 2 Reverse.png"));
			Image Bird1 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Bird 1.png"));
			Image Bird2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Bird 2.png"));
			Image DfR = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Dinosaure Fail Reverse.png"));
			Image Rbutton = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Reset Button.png"));
			Image GO = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Night Game Over.png"));
			Image star1 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Star 1.png"));
			Image star2 = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Star 2.png"));
			Image moon = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/Moon.png"));
			Image Fmoon = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Dinosaure/Images/FullMoon.png"));

			g.drawImage(cloud, Clo1x, Clo1y, 46, 13, this);
			g.drawImage(cloud, Clo2x, Clo2y, 46, 13, this);
			g.drawImage(cloud, Clo3x, Clo3y, 46, 13, this);
			g.drawImage(cloud, Clo4x, Clo4y, 46, 13, this);
			g.drawImage(star1, star1x, star1y, 8, 9, this);
			g.drawImage(star2, star2x, star2y, 8, 9, this);
			if (MoonFace==0) g.drawImage(moon, moonx, moony, 20, 40, this);
			if (MoonFace==1) g.drawImage(Fmoon, moonx, moony, 44, 40, this);
			if (Clo1x<-50){
				Clo1x=600+(int)(Math.random()*50);
				Clo1y=30+(int)(Math.random()*71);
				}
			if (Clo2x<-50){
				Clo2x=600+(int)(Math.random()*50);
				Clo2y=30+(int)(Math.random()*71);
				}
			if (Clo3x<-50){
				Clo3x=600+(int)(Math.random()*50);
				Clo3y=30+(int)(Math.random()*71);
				}
			if (Clo4x<-50){
				Clo4x=600+(int)(Math.random()*50);
				Clo4y=30+(int)(Math.random()*71);
				}
			if (star1x<-10){
				star1x=600+(int)(Math.random()*50);
				star1y=10+(int)(Math.random()*20);
				}
			if (star2x<-10){
				star2x=600+(int)(Math.random()*50);
				star2y=10+(int)(Math.random()*20);
				}
			if (moonx<-50){
				moonx=600+(int)(Math.random()*50);
				moony=20+(int)(Math.random()*21);
				}
			g.drawImage(Sol1, Sol1x, 140, 600, 20, this);
			g.drawImage(Sol2, Sol2x, 140, 600, 20, this);
			if (Sol1x<=-600) Sol1x=600;
			if (Sol2x<=-600) Sol2x=Sol1x+600;
		
			if (Obst1==0) g.drawImage(Sc, Ob1x, 150-33, 15, 33, this);
			else if (Obst1==1) g.drawImage(MSc2, Ob1x, 150-33, 32, 33, this);
			else if (Obst1==2) g.drawImage(MSc3, Ob1x, 150-33, 49, 33, this);
			else if (Obst1==3) g.drawImage(Tc, Ob1x, 150-46, 23, 46, this);
			else if (Obst1==4) g.drawImage(MTc, Ob1x, 150-46, 48, 46, this);
			else if (Obst1==5) g.drawImage(Fc, Ob1x, 150-47, 73, 47, this);
			else if (Obst1==6){
				if (R%20<10) g.drawImage(Bird1, Ob1x, By, 42, 26, this);
				else if (R%20>=10) g.drawImage(Bird2, Ob1x, By+5, 42, 30, this);
				}
			if (Ob1x<=-80){
				Ob1x=Ob3x+300+100*(int)(Math.random()*5);
				Obst1=(int)(Math.random()*7);
				if (Obst1==6) By=45+40*(int)(Math.random()*3);
				} 

			if (Obst2==0) g.drawImage(Sc, Ob2x, 150-33, 15, 33, this);
			else if (Obst2==1) g.drawImage(MSc2, Ob2x, 150-33, 32, 33, this);
			else if (Obst2==2) g.drawImage(MSc3, Ob2x, 150-33, 49, 33, this);
			else if (Obst2==3) g.drawImage(Tc, Ob2x, 150-46, 23, 46, this);
			else if (Obst2==4) g.drawImage(MTc, Ob2x, 150-46, 48, 46, this);
			else if (Obst2==5) g.drawImage(Fc, Ob2x, 150-47, 73, 47, this);
			else if (Obst2==6){
				if (R%20<10) g.drawImage(Bird1, Ob2x, By, 42, 26, this);
				else if (R%20>=10) g.drawImage(Bird2, Ob2x, By+5, 42, 30, this);
				}
			if (Ob2x<=-80){
				Ob2x=Ob1x+300+100*(int)(Math.random()*5);
				Obst2=(int)(Math.random()*7);
				if (Obst2==6) By=45+40*(int)(Math.random()*3);
				}

			if (Obst3==0) g.drawImage(Sc, Ob3x, 150-33, 15, 33, this);
			else if (Obst3==1) g.drawImage(MSc2, Ob3x, 150-33, 32, 33, this);
			else if (Obst3==2) g.drawImage(MSc3, Ob3x, 150-33, 49, 33, this);
			else if (Obst3==3) g.drawImage(Tc, Ob3x, 150-46, 23, 46, this);
			else if (Obst3==4) g.drawImage(MTc, Ob3x, 150-46, 48, 46, this);
			else if (Obst3==5) g.drawImage(Fc, Ob3x, 150-47, 73, 47, this);
			else if (Obst3==6){
				if (R%20<10) g.drawImage(Bird1, Ob3x, By, 42, 26, this);
				else if (R%20>=10) g.drawImage(Bird2, Ob3x, By+5, 42, 30, this);
				}
			if (Ob3x<=-80){
				Ob3x=Ob2x+300+100*(int)(Math.random()*5);
				Obst3=(int)(Math.random()*7);
				if (Obst3==6) By=45+40*(int)(Math.random()*3);
				}
			
			if (dinoy==150 && Down==0){
				if (R%16<8) g.drawImage(Dstand1R, dinox, dinoy, 40, -43, this);
				else if (R%16>=8) g.drawImage(Dstand2R, dinox, dinoy, 40, -43, this);
				}
			else if (dinoy==150 && Down==1){
				if (R%16<8) g.drawImage(Ddown1R, dinox, dinoy, 55, -26, this);
				else if (R%16>=8) g.drawImage(Ddown2R, dinox, dinoy, 55, -26, this);
				}
			else g.drawImage(DstandR, dinox, dinoy, 40, -43, this);

			if (keyflag==0){
				R+=1;
				Clo1x-=(int)(speed/4);
				Clo2x-=(int)(speed/4);
				Clo3x-=(int)(speed/4);
				Clo4x-=(int)(speed/4);
				star1x-=(int)(speed/3);
				star2x-=(int)(speed/3);
				moonx-=(int)(speed/3);
				Sol1x-=speed;
				Sol2x-=speed;
				Ob1x-=speed;
				Ob2x-=speed;
				Ob3x-=speed;
				}
			
			if (keyflag==1){
				g.drawImage(DfR, dinox, dinoy, 40, -43, this);
				g.drawImage(Rbutton, 283, 95, 34, 30, this);
				g.drawImage(GO, 205, 55, 191, 11, this);
				Down=0;
				}
			} catch (IOException e) {} }

		ObstacleColision(Obst1, Ob1x);
		ObstacleColision(Obst2, Ob2x);
		ObstacleColision(Obst3, Ob3x);

		
		if (keyflag==0){
			if (SautOn==1 && SautWhile==0){
				new Saut().start();
				SautWhile=1;
				}

			if (ScoreTimeOn==0 && Begin==2){
				new ScoreTime().start();
				ScoreTimeOn=1;
				}
			if (ScoreU==10){
				ScoreU=0;
				ScoreD+=1;
				}
			if (ScoreD==10){
				ScoreD=0;
				ScoreC+=1;
				}
			if (ScoreC==10){
				ScoreC=0;
				ScoreM+=1;
				}
			if (ScoreM==10){
				ScoreM=0;
				ScoreDM+=1;
				}
			}
		}
	public boolean Colision(int x, int y, int width, int height){
		int Cx=0;
		int Cy=0;
		if (Down==0){
			for(int i=dinox; i<dinox+7; i++){
				if (x<i && i<x+width){
					Cx=1;
					break;
					}
				}
			for(int i=dinox+7; i<dinox+29; i++){
				if (x<i && i<x+width){
					Cx=2;
					break;
					}
				}
			for(int i=dinox+21; i<dinox+40; i++){
				if (x<i && i<x+width){
					Cx=3;
					break;
					}
				}
			for(int i=dinoy-13; i>dinoy-28; i--){
				if (y<i && i<y+height){
					Cy=1;
					break;
					}
				}
			for(int i=dinoy-11; i>dinoy-20; i--){
				if (y<i && i<y+height){
					Cy=2;
					break;
					}
				}
			for(int i=dinoy-33; i>dinoy-43; i--){
				if (y<i && i<y+height){
					Cy=3;
					break;
					}
				}
			}
		if (Down==1){
			for(int i=dinox+38; i<dinox+55; i++){
				if (x<i && i<x+width){
					Cx=1;
					break;
					}
				}
			for(int i=dinoy-15; i>dinoy-25; i--){
				if (y<i && i<y+height){
					Cy=1;
					break;
					}
				}
			}
		return (Cx==1 && Cy==1) || (Cx==2 && Cy==2) || (Cx==3 && Cy==3);
		}
	public static void Reset(){
		int Results=ScoreU+10*ScoreD+100*ScoreC+1000*ScoreM+10000*ScoreDM;
		int BestResults=BestScoreU+10*BestScoreD+100*BestScoreC+1000*BestScoreM+10000*BestScoreDM;
		Pan.Ob1x=2000;
		Pan.Ob2x=2600;
		Pan.Ob3x=3000;
		Pan.R=0;
		Pan.dinoy=150;
		speed=6;
		ScoringTic=0;
		Pan.keyflag=0;
		if (BestResults<Results){
			Pan.BestScoreU=Pan.ScoreU;
			Pan.BestScoreD=Pan.ScoreD;
			Pan.BestScoreC=Pan.ScoreC;
			Pan.BestScoreM=Pan.ScoreM;
			Pan.BestScoreDM=Pan.ScoreDM;
			}
		Pan.ScoreU=1;
		Pan.ScoreD=0;
		Pan.ScoreC=0;
		Pan.ScoreM=0;
		Pan.ScoreDM=0;
		}
	public void ObstacleColision(int obst, int obstX){
		if (obst==0){
			if (Colision(obstX, 150-25, 3, 14)) keyflag=1;
			if (Colision(obstX+6, 150-33, 5, 33)) keyflag=1;
			if (Colision(obstX+13, 150-29, 3, 14)) keyflag=1;
			}
		if (obst==1){
			if (Colision(obstX, 150-25, 3, 14)) keyflag=1;
			if (Colision(obstX+6, 150-33, 5, 33)) keyflag=1;
			if (Colision(obstX+13, 150-29, 9, 14)) keyflag=1;
			if (Colision(obstX+23, 150-33, 5, 33)) keyflag=1;
			if (Colision(obstX+30, 150-29, 3, 16)) keyflag=1;
			}
		if (obst==2){
			if (Colision(obstX, 150-25, 3, 14)) keyflag=1;
			if (Colision(obstX+6, 150-33, 5, 33)) keyflag=1;
			if (Colision(obstX+13, 150-30, 8, 16)) keyflag=1;
			if (Colision(obstX+23, 150-33, 3, 33)) keyflag=1;
			if (Colision(obstX+30, 150-30, 8, 15)) keyflag=1;
			if (Colision(obstX+40, 150-33, 5, 33)) keyflag=1;
			if (Colision(obstX+47, 150-29, 3, 16)) keyflag=1;
			}
		if (obst==3){
			if (Colision(obstX, 150-34, 5, 19)) keyflag=1;
			if (Colision(obstX+9, 150-46, 7, 46)) keyflag=1;
			if (Colision(obstX+19, 150-36, 5, 20)) keyflag=1;
			}
		if (obst==4){			
			if (Colision(obstX, 150-34, 5, 19)) keyflag=1;
			if (Colision(obstX+9, 150-46, 7, 46)) keyflag=1;
			if (Colision(obstX+19, 150-41, 5, 16)) keyflag=1;
			if (Colision(obstX+34, 150-46, 7, 46)) keyflag=1;
			if (Colision(obstX+44, 150-36, 5, 20)) keyflag=1;
			}
		if (obst==5){
			if (Colision(obstX, 150-35, 5, 20)) keyflag=1;
			if (Colision(obstX+9, 150-47, 7, 47)) keyflag=1;
			if (Colision(obstX+19, 150-36, 10, 16)) keyflag=1;
			if (Colision(obstX+31, 150-45, 5, 45)) keyflag=1;
			if (Colision(obstX+39, 150-40, 3, 10)) keyflag=1;
			if (Colision(obstX+51, 150-41, 5, 16)) keyflag=1;
			if (Colision(obstX+59, 150-47, 7, 47)) keyflag=1;
			if (Colision(obstX+69, 150-36, 5, 19)) keyflag=1;
			}
		if (obst==6){
			if (R%20<10){
				if (Colision(obstX, By+7, 12, 10)) keyflag=1;
				if (Colision(obstX+17, By+14, 12, 13)) keyflag=1;
				if (Colision(obstX+29, By+17, 14, 10)) keyflag=1;
				}
			if (R%20>=10){
				if (Colision(obstX, By+5, 14, 10)) keyflag=1;
				if (Colision(obstX+17, By+5+15, 4, 16)) keyflag=1;
				if (Colision(obstX+25, By+5+13, 8, 18)) keyflag=1;
				}
			}
		}
	}
class ScoreTime extends Thread{
	public void run(){
		try{
			sleep((int)(500/Pan.speed));
			} catch (InterruptedException e) {}
		Pan.ScoreU+=1;
		Pan.ScoreTimeOn=0;
		}
	}
class ScoringTime extends Thread{
	public void run(){
		for(int i=0; i<5; i++){
			try{
				sleep(500);
				} catch (InterruptedException e) {}
			Pan.ScoringTic+=1;
			}
		Pan.ScoringTimeOn=0;
		}
	}
			
class Saut extends Thread{
	public void run(){
		Pan.Down=0;
		for(int i=15; i>-16 && Pan.keyflag==0 && Pan.SautOn==1; i--){
			try{
				sleep(15-i);
				//else if (i<0) sleep(15+i);
				} catch (InterruptedException e) {}
			Pan.dinoy-=i;
			}
		Pan.SautWhile=0;
		Pan.SautOn=0;
		}
	}
class SautListener implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (c==0x20 || c==0x26){
			Pan.SautOn=1;
			if (Pan.Begin==0) Pan.Begin=1;
			}
		if (c==0x28 && Pan.keyflag==0) Pan.Down=1;
		if (Pan.keyflag==1 && (c==0x20 || c==0x26)) Pan.Reset();
		}
	public void keyReleased(KeyEvent event){
		int c = event.getKeyCode();
		if (c==0x28) Pan.Down=0;
		}
	}
class SourisAgain implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1) System.out.println(X+"\t"+Y);
		if ((c==1 || c==2) && Pan.keyflag==1) Pan.Reset();
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
public class Dinosaure{
	public static void main(String[] args){
		int SizeX=40;
		Fen fen = new Fen("Fenetre", SizeX+16, 160+40);
		Pan pan = new Pan(600, 160);
		fen.setContentPane(pan);
		while (true){
			if (SizeX<600){
				if (pan.Begin==1 && pan.SautOn==0) SizeX+=2;
				fen.setSize(SizeX+16, 160+40);
				fen.setLocationRelativeTo(null);
				if (SizeX>=600) pan.Begin=2;
				}
			pan.repaint();
			}
		}
}