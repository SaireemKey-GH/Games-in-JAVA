import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class Fen extends JFrame{
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		//this.setResizable(false);
		this.setVisible(true);
		this.setLocation(145, 50);
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
//******NIVEAUX A CORRIGER
/* Sticky green wallstick qui bouge
* Level 25
* Level 26
* Level 27
* Level 36 (cube rouge-vert à gauche)
*/


/* IDEES NIVEAUX
* Chronometre
* Plateformes à tailles variantes
* Sticky walls / WallJump
* Bouttons qui déplacent les plateformes
* Portails de téléportation (style Portal)
* Chasing red line
* Inverted gravity
*/
//Level 1-10 random color and Pink to Purple
//Level 11-20 Blue to Navy
//Level 21-30 Green
//Level 31-40 Yellow to Orange
//Level 41-50 Red
//Screen Size =  900*900
//Ground is at y = 800;
//Distance max saut entre bloc en Size max = 150
class Pan extends JPanel{
	static int x=20;
	static int y=800;
	static int P1x=0;
	static int P1y=0;
	static int P1Tour=0;
	static int P1Trigger=0;
	static int P2x=0;
	static int P2y=0;
	static int P2Tour=0;
	static int P2Trigger=0;
	static int P3x=0;
	static int P3y=0;
	static int P3Tour=0;
	static int P3Trigger=0;
	static int Pmove=0;
	static int ButtonOn1=0;
	static int ButtonOn2=0;
	static int [][] Cube28 = new int [10][10];

	static int [] Tab = new int [50];
	static int info = 0;
	static int SLight=1000;

	static int initP=0;
	static int Time=0;
	static int Time2=0;
	static int TimeOn=0;
	static int TimeOn2=0;
	static int Motion=1;
	static int Size=50;
	static int Sens=2;
	static int MotionR=0;
	static int MotionL=0;
	static int Down=0;
	static int Saut=0;
	static int Sustained=0;
	static int WallStickedR=0;
	static int WallStickedL=0;
	static int ForceSustained=0;
	static int LineOn=0;
	static int SautLock=0;
	static int SautCooldown=0;
	static int Chute=0;
	static int ColorP=0;
	static int LevelColor=0;
	static int Level=50;
	JButton gris = new JButton();

	static int Measurement=0;
	static int MeasurementX1=0;
	static int MeasurementY1=0;
	static int MeasurementX2=0;
	static int MeasurementY2=0;
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));

		ToucheSaut Tsaut = new ToucheSaut();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(Tsaut);
		this.add(gris);

		SourisListener souris = new SourisListener();

		SourisMotionListener sourisMove = new SourisMotionListener();
		this.addMouseListener(souris);
		this.addMouseMotionListener(sourisMove);

		gris.addMouseMotionListener(sourisMove);
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		if (LevelColor==0) g.setColor(Color.black);
		else g.setColor(Color.red);
		//if (Level>40) g.setColor(Color.white);
		g.fillRect(0, 8*this.getHeight()/9, this.getWidth(), this.getHeight()/9);
		g.setFont(new Font("Times New Roman", Font.BOLD, 25));
		//if (LevelColor==0) g.setColor(Color.white);
		//else g.setColor(Color.black);
		if (Level<=400) g.setColor(Color.white);
		if (Level>40) g.drawLine(0, 800, 900, 800);
		//else if (Level>40) g.setColor(Color.black);
		g.drawString("level "+String.valueOf(Level), 410, 860);
		g.setColor(Color.blue);
		if (Level<=40){
			Size=50-Level;
			g.fillRect(x,y, Size, -Size);
			}
		else Size=10;
		if (x<0) x=0;
		else if (x+Size>900) x=850;
		if (MotionR==1) x+=1;
		if (MotionL==1) x-=1;
		if (Saut==1){
			//Sens=1;
			SautLock=1;
			y-=5;
			SautCooldown+=1;
			if (SautCooldown==20){
				Saut=0;
				SautCooldown=0;
				}
			}
		if (Sustained==1 || y==800 || ForceSustained==1) SautLock=0;
		if (y==800 && LevelColor==1) initP=0;
		if (Saut==0 && y<8*this.getHeight()/9 && Sustained==0 && ForceSustained==0){
			y+=1;
			SautLock=1;
			}
		//else Chute=0;
		if (Chute==1 && ForceSustained==0) y+=1;

		if (Level>40){
			g.setColor(Color.red);
			g.fillRect(0, 0, 10+10*(50-Level), 800);
			if (Colision(0, 0, 10+10*(50-Level), 800)) initP=0;

			g.fillRect(900-(10+10*(50-Level)), 0, 10+10*(50-Level), 800);
			if (Colision(900-(10+10*(50-Level)), 0, 10+10*(50-Level), 800)) initP=0;
			}

		
		if (Level==1){
			g.setColor(Color.green);
			g.fillRect(200, 760, 100, 40);
			if (Colision(200, 765, 100, 35)) x=150;
			//if (Colision(200, 760, 120, 5)) Sustained=1;
		
			g.fillRect(320, 720, 100, 40);
			if (Colision(320, 725, 100, 35)) x=270;
			//if (Colision(320, 720, 120, 5)) Sustained=1;
		
			g.fillRect(440, 680, 100, 40);
			if (Colision(440, 685, 100, 35)) x=390;
			//if (Colision(440, 680, 120, 5)) Sustained=1;
		
			g.fillRect(560, 640, 100, 40);
			if (Colision(560, 645, 100, 35)) x=510;
			//if (Colision(560, 640, 120, 5)) Sustained=1;
		
			g.fillRect(680, 600, 220, 40);
			if (Colision(680, 605, 220, 35)) x=630;
			//if (Colision(680, 600, 220, 5)) Sustained=1;
		
//*magenta
			g.setColor(Color.magenta);
			g.fillRect(580, 520, 80, 30);
			ColisionALL(580, 520, 80, 30);
			//if (Colision(580, 525, 80, 20) && Sens==0) x=660;
			//else if (Colision(580, 525, 80, 20) && Sens==2) x=530;
			//else if (Colision(580, 545, 80, 5)) Saut=SautCooldown=0;

			g.fillRect(460, 480, 80, 30);
			ColisionALL(460, 480, 80, 30);
			//if (Colision(460, 485, 80, 20) && Sens==0) x=540;
			//else if (Colision(460, 485, 80, 20) && Sens==2) x=410;
			//else if (Colision(460, 505, 80, 5)) Saut=SautCooldown=0;

			g.fillRect(340, 440, 80, 30);
			ColisionALL(340, 440, 80, 30);
			//if (Colision(340, 445, 80, 30) && Sens==0) x=420;
			//else if (Colision(340, 445, 80, 30) && Sens==2) x=290;
			//else if (Colision(340, 465, 80, 30)) Saut=SautCooldown=0;

			g.fillRect(220, 400, 80, 30);
			if (Colision(220, 405, 80, 20) && Sens==0) x=300;
			else if (Colision(220, 405, 80, 20) && Sens==2) x=170;
			else if (Colision(220, 425, 80, 5)) Saut=SautCooldown=0;

			g.fillRect(120, 360, 80, 30);
			if (Colision(120, 365, 80, 20) && Sens==0) x=200;
			else if (Colision(120, 365, 80, 20) && Sens==2) x=70;
			else if (Colision(120, 385, 80, 5)) Saut=SautCooldown=0;

			g.fillRect(0, 320, 70, 30);
			if (Colision(0, 325, 70, 20) && Sens==0) x=70;
			else if (Colision(0, 345, 70, 5)) Saut=SautCooldown=0;

//*Purple
			g.setColor(new Color(150, 0, 150));
			g.fillRect(80, 240, 50, 20);
			if (Colision(80, 245, 50, 10) && Sens==0) x=130;
			else if (Colision(80, 245, 50, 10) && Sens==2) x=30;
			if (Colision(80, 255, 50, 5)) Saut=SautCooldown=0;
		
			g.fillRect(230, 220, 50, 20);
			if (Colision(230, 225, 50, 10) && Sens==0) x=280;
			else if (Colision(230, 225, 50, 10) && Sens==2) x=180;
			if (Colision(230, 235, 50, 5)) Saut=SautCooldown=0;
	
			g.fillRect(380, 200, 50, 20);
			if (Colision(380, 205, 50, 10) && Sens==0) x=430;
			else if (Colision(380, 205, 50, 10) && Sens==2) x=330;

			g.fillRect(530, 180, 50, 20);
			if (Colision(530, 185, 50, 10) && Sens==0) x=580;
			else if (Colision(530, 185, 50, 10) && Sens==2) x=480;

			g.fillRect(680, 160, 50, 20);
			if (Colision(680, 165, 50, 10) && Sens==0) x=730;
			else if (Colision(680, 165, 50, 10) && Sens==2) x=630;

			g.fillRect(830, 140, 70, 20);
			if (Colision(830, 145, 70, 10) && Sens==2) x=780;
		
//**Suspension
			if (Sustain(200, 760, 100, 2)) Sustained=1;
			else if (Sustain(320, 720, 100, 2)) Sustained=1;
			else if (Sustain(440, 680, 100, 2)) Sustained=1;
			else if (Sustain(560, 640, 100, 2)) Sustained=1;
			else if (Sustain(680, 600, 220, 2)) Sustained=1;

			else if (Sustain(580, 520, 80, 2)) Sustained=1;
			else if (Sustain(460, 480, 80, 2)) Sustained=1;
			else if (Sustain(340, 440, 80, 2)) Sustained=1;
			else if (Sustain(220, 400, 80, 2)) Sustained=1;
			else if (Sustain(120, 360, 80, 2)) Sustained=1;
			else if (Sustain(0, 320, 80, 2)) Sustained=1;

			else if (Sustain(80, 240, 50, 2)) Sustained=1;
			else if (Sustain(230, 220, 50, 2)) Sustained=1;
			else if (Sustain(380, 200, 50, 2)) Sustained=1;
			else if (Sustain(530, 180, 50, 2)) Sustained=1;
			else if (Sustain(680, 160, 50, 2)) Sustained=1;
			else if (Sustain(830, 140, 50, 2)) Sustained=1;
			else Sustained=0;

//***Goal Level 1
			if (x+50==900 && y==140){
				Level=2;
				Sustained=0;
				}
			}
//***Level 2
		else if (Level==2){
			if (initP==0){
				x=900-49;
				y=140;
				P1x=20;
				P1y=790;
				P2x=150;
				P2y=620;
				P3x=100;
				P3y=130;
				initP=1;
				}
//*Orange
			g.setColor(Color.orange);
			g.fillRect(800, 0, 40, 740);
			if (Colision(800, 0, 40, 735) && Sens==0) x=840;
			else if (Colision(800, 0, 40, 735) && Sens==2) x=750;
			if (Colision(800, 735, 40, 5)) Saut=SautCooldown=0;

			g.setColor(Color.gray);
			g.fillRect(P1x, P1y, 80, 10);
			if (Colision(P1x, P1y+5, 80, 2) && Sens==2) x=P1x-50;
			else if (Colision(P1x, P1y+5, 80, 2) && Sens==0) x=P1x+80;
			if (Colision(P1x, P1y+8, 80, 2)) Saut=SautCooldown=0;
			if (P1Tour<200) P1y-=1;
			else if (P1Tour>200) P1y+=1;
			if (P1Tour==399) P1Tour=0;
			P1Tour+=1;

			g.fillRect(P2x, P2y, 80, 10);
			if (Colision(P2x, P2y+5, 80, 2) && Sens==2) x=P2x-50;
			else if (Colision(P2x, P2y+5, 80, 2) && Sens==0) x=P2x+80;
			if (Colision(P2x, P2y+8, 80, 2)) Saut=SautCooldown=0;
			if (P2Tour<500){
				P2x+=1;
				P2y-=1;
				}
			else if (P2Tour>500){
				P2x-=1;
				P2y+=1;
				}
			if (P2Tour==999) P2Tour=0;
			P2Tour+=1;

			g.fillRect(P3x, P3y, 80, 10);
			if (Colision(P3x, P3y+5, 80, 2) && Sens==2) x=P3x-50;
			else if (Colision(P3x, P3y+5, 80, 2) && Sens==0) x=P3x+80;
			if (Colision(P3x, P3y+8, 80, 2)) Saut=SautCooldown=0;
			if (P3Tour<450) P3x+=1;
			else if (P3Tour>450) P3x-=1;
			if (P3Tour==899) P3Tour=0;
			P3Tour+=1;
			
//*Yellow
			g.setColor(Color.yellow);
			g.fillRect(0, 400, 50, 10);
			if (Colision(0, 400, 50, 10) && Sens==0) x=50;

			g.fillRect(110, 400, 50, 10);
			if (Colision(110, 400, 50, 10) && Sens==0) x=160;
			else if (Colision(110, 400, 50, 10) && Sens==2) x=60;
//**Suspension
			if (Sustain(P1x, P1y, 80, 10)){
				Sustained=1;
				if (P1Tour<200) y-=1;
				else if (P1Tour>200) y+=1;
				}
			else if (Sustain(P2x, P2y, 80, 10)){
				Sustained=1;
				if (P2Tour<500){
					x+=1;
					y-=1;
					}
				else if (P2Tour>500){
					x-=1;
					y+=1;
					}
				}
			else if (Sustain(P3x, P3y, 80, 10)){
				Sustained=1;
				if (P3Tour<450) x+=1;
				else if (P3Tour>450) x-=1;
				}
			else if (Sustain(0, 400, 50, 10)) Sustained=1;
			else if (Sustain(110, 400, 50, 10)) Sustained=1;
			else Sustained=0;
//***Goal Level 2
			g.setColor(Color.black);
			g.fillRect(115, 300, 40, 100);
			if (x+25==135 && y-25==375){
				Level=3;
				Sustained=0;
				initP=0;
				}
			}
//*** Level 3
		else if (Level==3){
			if (initP==0){
				P1x=350;
				P1y=400;
				P2x=600;
				P2y=600;
				P2Tour=0;
				P3x=0;
				P3y=790;
				initP=1;
				}
//*Green
			g.setColor(Color.green);
			g.fillRect(100, 420, 200, 100);
			if (Colision(100, 425, 200, 95) && Sens==0) x=300;

			g.fillRect(50, 320, 50, 200);
			if (Colision(50, 325, 50, 195) && Sens==0) x=100;
			else if (Colision(50, 325, 50, 195) && Sens==2) x=0;
					
//*P
			g.setColor(Color.gray);
			g.fillRect(P1x, P1y, 60, 10);
			if (Colision(P1x+55, P1y+5, 5, 2) && Sens==0) x=P1x+60;
			else if (Colision(P1x, P1y+5, 5, 2) && Sens==2) x=P1x-Size;
			P1y=400+(int)(100*Math.sin((Pmove*3.14)/190));
			Pmove+=1;
			if (P1Trigger==0 && P1x>350) P1x-=1;
			if (P1Trigger==1 && P1x<800) P1x+=1;

			g.fillRect(P2x, P2y, 60, 10);
			if (Colision(P2x+55, P2y+5, 5, 2) && Sens==0) x=P2x+60;
			else if (Colision(P2x, P2y+5, 5, 2) && Sens==2) x=P2x-Size;
			P2x=600+(int)(50*Math.cos((Pmove*3.14)/200));
			P2y=600+P2Tour+(int)(50*Math.sin((Pmove*3.14)/200));
			if (P2Trigger==1 && P2y>200) P2Tour-=1;
			if (P2Trigger==0 && P2y<600) P2Tour+=1;

			g.fillRect(P3x, P3y, 50, 10);
			if (Colision(P3x+45, P3y+5, 5, 2) && (Sens==0 || Sens==2)) x=P3x+50;
			if (P3Trigger==1 && P3y>320) P3y-=1;
			if (P3Trigger==0 && P3y<790) P3y+=1;
			
			if (y==800) P1Trigger=P2Trigger=P3Trigger=0;
//*Dark Green
			g.setColor(new Color(0, 120, 0));
			g.fillRect(445, 100, 20, 20);
			ColisionALL(445, 100, 20, 20);
			//if (Colision(455, 105, 10, 5) && Sens==0) x=455;
			//else if (Colision(445, 105, 10, 5) && Sens==2) x=395;
			//if (Colision(462, 100, 10, 3)) Saut=SautCooldown=0;

			g.fillRect(350, 120, 40, 30);
			if (Colision(350, 125, 40, 5) && Sens==0) x=390;
			else if (Colision(350, 125, 40, 5) && Sens==2) x=300;
			
			g.fillRect(250, 120, 40, 30);
			if (Colision(250, 125, 40, 5) && Sens==0) x=290;
			else if (Colision(250, 125, 40, 5) && Sens==2) x=200;

			g.fillRect(150, 120, 40, 30);
			if (Colision(150, 125, 40, 5) && Sens==0) x=190;
			else if (Colision(150, 125, 40, 5) && Sens==2) x=100;

			g.fillRect(0, 120, 100, 30);
			if (Colision(0, 130, 100, 5) && Sens==0) x=100;
//**Suspension
			if (Sustain(100, 420, 200, 2)) Sustained=1;
			else if (Sustain(50, 320, 50, 2)) Sustained=1;

			else if (Sustain(P1x, P1y, 60, 10)){
				P1Trigger=1;
				if (P1x<800) x+=1;
				Sustained=1;
				y=400+(int)(100*Math.sin((Pmove*3.14)/190));
				}
			else if (Sustain(P2x, P2y, 60, 10)){
				int Xmove=(int)(50*Math.cos((Pmove*3.14)/200));
				int Xmove1=(int)(50*Math.cos(((Pmove+1)*3.14)/200));
				P2Trigger=1;
				Sustained=1;
				if (P2y>200) y-=1;
				if (Xmove>=0 && Xmove<Xmove1) x+=1;
				else if (Xmove<0 && Xmove>Xmove1) x-=1;
				//if (P2y>607 && Xmove>Xmove1) x-=1;
				//if (P2y<=607 && Xmove<Xmove1) x+=1;
				//System.out.println(P2y);
				//x=600+(int)(50*Math.cos((Pmove*3.14)/200));
				y=600+P2Tour+(int)(50*Math.sin((Pmove*3.14)/200));
				}
			else if (Sustain(P3x, P3y, 50, 10)){
				Sustained=1;
				P3Trigger=1;
				if (P3y>320) y-=1;
				}

			else if (Sustain(445, 100, 10, 2)) Sustained=1;
			else if (Sustain(350, 120, 40, 2)) Sustained=1;
			else if (Sustain(250, 120, 40, 2)) Sustained=1;
			else if (Sustain(150, 120, 40, 2)) Sustained=1;
			else if (Sustain(0, 120, 100, 2)) Sustained=1;
			else Sustained=0;
//***Goal Level 3
			g.setColor(Color.black);
			g.fillRect(10, 20, 40, 100);
			if (x==5 && y==120){
				Level=4;
				Sustained=0;
				initP=0;
				}
			}
//***Level 4
		else if (Level==4){
			if (initP==0){
				P1x=400;
				P1y=400;
				P1Tour=0;
				Pmove=0;
				initP=1;
				}
//*Pink
			g.setColor(Color.pink);
			g.fillRect(0, 125, 80, 20);
			if (Colision(0, 130, 80, 10) && Sens==0) x=205;

			g.fillRect(0, 375, 50, 50);
			if (Colision(0, 390, 50, 30)) x=50;
			if (Colision(0, 422, 50, 3)) Saut=SautCooldown=0;
			

//*P
			g.setColor(Color.gray);
			g.fillRect(P1x, P1y, 100, 20);
			if (Colision(P1x, P1y+10, 100, 3) && Sens==0) x=P1x+100;
			else if (Colision(P1x, P1y+10, 100, 3) && Sens==2) x=P1x-Size;
			P1x=400+(int)(350*Math.cos((Pmove*3.14)/400));
			P1y=400+(int)(350*Math.sin((Pmove*3.14)/400));
			Pmove+=1;


//**Suspension
			if (Sustain(0, 125, 80, 20)) Sustained=1;
			else if (Sustain(P1x, P1y, 100, 20)){
				if (Sustained==0) P1Tour=x-P1x;
				Sustained=1;
				if (MotionL==1) P1Tour-=1;
				if (MotionR==1) P1Tour+=1;
				x=P1x+P1Tour;
				//if (x<=P1x) x+=3;
				//if (x+49>=P1x+80) x-=3;
				//x=400+(int)(350*Math.cos((Pmove*3.14)/400));
				y=400+(int)(350*Math.sin((Pmove*3.14)/400));
				}
			else if (Sustain(0, 375, 50, 5)) Sustained=1;
			else Sustained=P1Tour=0;
//***Goal Level 4
			g.setColor(Color.black);
			g.fillRect(420, 420, 55, 55);
			if (420<x && x<475 && 420<y && y<475){
				Level=5;
				initP=0;
				Sustained=0;
				}
			}
//***Level 5
		else if (Level==5){
			if (y==800) initP=0;
			if (initP==0){
				LevelColor=1;
				x=420;
				y=400;
				P1x=850;
				P1y=400;
				P1Trigger=P3Trigger=0;
				Pmove=0;
				P2x=50;
				P2y=650;
				P2Tour=0;
				P3y=590;
				initP=1;
				}

//*Light Pink
			g.setColor(new Color(255, 128, 255));
			g.fillRect(400, 450, 100, 30);
			if (Colision(400, 455, 100, 15) && Sens==0) x=500;
			if (Colision(400, 477, 100, 3)) Saut=SautCooldown=0;

			g.fillRect(600, 450, 30, 20);
			if (Colision(600, 455, 30, 10) && Sens==0) x=630;
			else if (Colision(600, 455, 30, 10) && Sens==2) x=600-Size;
			if (Colision(600, 467, 30, 3)) Saut=SautCooldown=0;

			g.fillRect(700, 430, 30, 20);
			if (Colision(700, 435, 30, 10) && Sens==0) x=730;
			else if (Colision(700, 435, 30, 10) && Sens==2) x=700-Size;
			if (Colision(700, 447, 30, 3)) Saut=SautCooldown=0;

			g.fillRect(800, 410, 30, 20);
			if (Colision(800, 415, 30, 10) && Sens==0) x=830;
			else if (Colision(800, 415, 30, 10) && Sens==2) x=800-Size;
			if (Colision(800, 427, 30, 3)) Saut=SautCooldown=0;

			g.fillRect(390, 0, 10, 480);
			if (Colision(390, 0, 10, 475) && Sens==0) x=400;
			else if (Colision(390, 0, 10, 475) && Sens==2) x=390-Size;
			if (Colision(390, 475, 10, 5)) Saut=SautCooldown=0;

			g.fillRect(0, P3y, 10, 15);
			if (Colision(0, P3y+5, 10, 3) && Sens==0) x=10;
			if (Colision(0, P3y+13, 10, 2)) Saut=SautCooldown=0;
			if (P3Trigger==1 && P3y>450) P3y-=1;
			if (P3Trigger==0) P3y=590;

			g.fillRect(110, 420, 15, 10);
			if (Colision(110, 423, 15, 3) && Sens==0) x=125;
			else if (Colision(110, 423, 15, 3) && Sens==2) x=110-Size;
			if (Colision(110, 428, 15, 2)) Saut=SautCooldown=0;

			g.fillRect(271, 400, 119, 20);
			if (Colision(271, 405, 119, 3) && Sens==2) x=271-Size;
			 
//*P
			g.setColor(Color.gray);
			g.fillRect(P1x, P1y, 50, 10);
			if (Colision(P1x, P1y+3, 50, 2) && Sens==2) x=P1x-Size;
			if (P1Trigger==1 && P1y<750) P1y+=1;
			if (P1Trigger==0) P1y=400;

			g.fillRect(P2x, P2y, 60, 10);
			if (Colision(P2x, P2y+3, 60, 2) && Sens==0) x=P2x+60;
			else if (Colision(P2x, P2y+3, 60, 2) && Sens==2) x=P2x-Size;
			if (P1Trigger==1){
				if (P2Tour<700) P2x+=1;
				if (P2Tour>700) P2x-=1;
				if (P2Tour==1399) P2Tour=0;
				P2y=650+(int)(100*Math.sin((Pmove*3.14)/250));
				P2Tour+=1;
				Pmove+=1;
				}
			if (P1Trigger==0){
				P2x=50;
				P2y=650;
				}
//**Suspension
			if (Sustain(400, 450, 100, 2)) Sustained=1;
			else if (Sustain(600, 450, 30, 2)) Sustained=1;
			else if (Sustain(700, 430, 30, 2)) Sustained=1;
			else if (Sustain(800, 410, 30, 2)) Sustained=1;
			else if (Sustain(P1x, P1y, 50, 10)){
				Sustained=1;
				P1Trigger=1;
				if (P1x<750) y+=1;
				}
			else if (Sustain(P2x, P2y, 60, 10)){
				Sustained=1;
				if (P2Tour<700) x+=1;
				if (P2Tour>700) x-=1;
				y=650+(int)(100*Math.sin((Pmove*3.14)/250));
				}
			else if (Sustain(0, P3y, 10, 15)){
				Sustained=1;
				P3Trigger=1;
				if (P3y>450) y-=1;
				}
			else if (Sustain(110, 420, 15, 2)) Sustained=1;
			else if (Sustain(271, 400, 119, 2)) Sustained=1;
			else Sustained=0;
//***Goal Level 5
			g.setColor(Color.black);
			g.fillRect(320, 350, 50, 50);
			if (x==320 && y==400){
				Level=6;
				initP=0;
				Sustained=0;
				}

			}
//***Level 6
		else if (Level==6){
			if (initP==0){
				LevelColor=0;
				P1x=180;
				P1y=790;
				P1Trigger=0;
				P2x=470;
				P2y=440;
				ButtonOn1=0;
				initP=1;
				}
//*Pink
			g.setColor(new Color(255, 125, 192));
			g.fillRect(300, 610, 90, 30);
			if (Colision(300, 615, 90, 10) && Sens==0) x=390;
			else if (Colision(300, 615, 90, 10) && Sens==2) x=300-Size;
			if (Colision(300, 628, 90, 2)) Saut=SautCooldown=0;

			g.fillRect(0, 0, 50, 800);
			if (Colision(0, 0, 50, 800) && Sens==0) x=50;

			g.fillRect(850, 0, 50, 800);
			if (Colision(850, 0, 50, 800) && Sens==2) x=850-Size;
			
			if (ButtonOn1==1){
				g.fillRect(540-5, 600, 30+5, 20);
				ColisionALL(540-5, 600, 30+5, 20);
				//if (Colision(540, 600, 30, 20) && Sens==0) x=570;
				//else if (Colision(540, 600, 30, 20) && Sens==2) x=540-Size;

				g.fillRect(700-5, 590, 30+5, 20);
				ColisionALL(700-5, 590, 30+5, 20);
				//if (Colision(700-5, 590, 30+5, 20) && Sens==0) x=730;
				//else if (Colision(700, 590, 30, 20) && Sens==2) x=700-Size;

				g.fillRect(820, 570, 30, 20);
				if (Colision(820, 570, 30, 20) && Sens==2) x=820-Size;

				g.fillRect(750-5, 480, 25+5, 15);
				ColisionALL(750-5, 480, 25+5, 15);
				//if (Colision(750, 485, 25, 3) && Sens==0) x=775;
				//else if (Colision(750, 485, 25, 3) && Sens==2) x=750-Size;
				//if (Colision(750, 493, 25, 2)) Saut=SautCooldown=0;
	
				g.fillRect(590-5, 460, 25+5, 15);
				if (Colision(590, 465, 25, 3) && Sens==0) x=615;
				else if (Colision(590, 465, 25, 3) && Sens==2) x=590-Size;
				if (Colision(590, 473, 25, 2)) Saut=SautCooldown=0;

				g.fillRect(260, 20, 20, 20);
				if (Colision(260, 25, 20, 5) && Sens==0) x=280;
				else if (Colision(260, 25, 20, 5) && Sens==2) x=260-Size;

				g.fillRect(380, 20, 20, 20);
				if (Colision(380, 25, 20, 5) && Sens==0) x=400;
				else if (Colision(380, 25, 20, 5) && Sens==2) x=380-Size;

				g.fillRect(500, 20, 20, 20);
				if (Colision(500, 25, 20, 5) && Sens==0) x=520;
				else if (Colision(500, 25, 20, 5) && Sens==2) x=500-Size;

				g.fillRect(620, 20, 20, 20);
				if (Colision(620, 25, 20, 5) && Sens==0) x=640;
				else if (Colision(620, 25, 20, 5) && Sens==2) x=620-Size;

				g.fillRect(740, 20, 20, 20);
				if (Colision(740, 25, 20, 5) && Sens==0) x=760;
				else if (Colision(740, 25, 20, 5) && Sens==2) x=740-Size;
				
				}
				g.fillRect(810, 50, 40, 20);
				if (Colision(810, 53, 40, 15) && Sens==2) x=810-Size;

//*Red button
			g.setColor(Color.red);
			if (ButtonOn1==0) g.fillRect(840, 760, 30, 30);
			if (ButtonOn1==1) g.fillRect(850, 760, 30, 30);
			if (Colision(840, 760, 30, 30) && Sens==2 && ButtonOn1==0) ButtonOn1=1;
		
//*P
			g.setColor(Color.gray);
			g.fillRect(P1x, P1y, 60, 10);
			if (Colision(P1x, P1y+5, 60, 2) && Sens==0) x=P1x+60;
			else if (Colision(P1x, P1y+5, 60, 2) && Sens==2) x=P1x-Size;
			if (y==800 && P1y<790) P1y+=1;

			g.fillRect(P2x, P2y, 90, 10);
			if (Colision(P2x, P2y+5, 90, 2) && Sens==0) x=P2x+90;
			else if (Colision(P2x, P2y+5, 90, 2) && Sens==2) x=P2x-Size;
			if (P2Trigger==1 && P2x>50){
				P2x-=1;
				P2y-=1;
				}
			if (P2Trigger==0 && P2x<470){
				P2x+=1;
				P2y+=1;
				}
			if (y==800) P2Trigger=0;

//**Suspension
			if (Sustain(300, 610, 90, 2)) Sustained=1;
			else if (Sustain(P1x+1, P1y, 58, 10)){
				Sustained=1;
				if (P1y>610){
					P1y-=1;
					y-=1;
					}
				}
			else if (Sustain(540, 600, 30, 2)) Sustained=1;
			else if (Sustain(700, 590, 30, 2)) Sustained=1;
			else if (Sustain(820, 570, 30, 2)) Sustained=1;
			else if (Sustain(750, 480, 25, 2)) Sustained=1;
			else if (Sustain(590, 460, 25, 2)) Sustained=1;
			else if (Sustain(P2x+1, P2y, 88, 10)){
				Sustained=1;
				P2Trigger=1;
				if (P2x>50){
					x-=1;
					y-=1;
					}
				}
			else if (Sustain(260, 20, 20, 2)) Sustained=1;
			else if (Sustain(380, 20, 20, 2)) Sustained=1;
			else if (Sustain(500, 20, 20, 2)) Sustained=1;
			else if (Sustain(620, 20, 20, 2)) Sustained=1;
			else if (Sustain(740, 20, 20, 2)) Sustained=1;
			else if (Sustain(810, 50, 40, 2)) Sustained=1;
			else Sustained=0;
//***Goal Level 6
			g.setColor(Color.black);
			g.fillRect(825, 10, 20, 40);
			if (x+Size==850 && y==50){
				Level=7;
				initP=0;
				Sustained=0;
				}
			}
//*** Level 7
		else if (Level==7){
			if (initP==0){
				x=820;
				y=20;
				P1x=660;
				P1y=80;
				P2x=235;
				P2y=350;
				P3x=700;
				P3y=640;
				ButtonOn1=0;
				initP=1;
				}
//*Pink Magenta
			g.setColor(new Color(236, 62, 255));
			g.fillRect(0, 0, 40, 800);
			if (Colision(0, 0, 40, 800) && Sens==0) x=40;

			g.fillRect(860, 0, 40, 800); 
			if (Colision(860, 0, 40, 800) && Sens==2) x=860-Size;

			g.fillRect(810, 60, 50, 30);
			if (Colision(810, 65, 50, 20) && Sens==2) x=810-Size;

			if (ButtonOn1==1){
				g.fillRect(680, 660, 40, 30);
				if (Colision(680, 665, 40, 15) && Sens==0) x=720;
				if (Colision(680, 665, 40, 15) && Sens==2) x=680-Size;

				g.fillRect(580, 645, 40, 30);
				if (Colision(580, 650, 40, 15) && Sens==0) x=620;
				if (Colision(580, 650, 40, 15) && Sens==2) x=580-Size;
		
				g.fillRect(450, 590, 40, 30);
				if (Colision(450, 595, 40, 15) && Sens==0) x=490;
				if (Colision(450, 595, 40, 15) && Sens==2) x=450-Size;

				g.fillRect(320, 660, 40, 30);
				if (Colision(320, 665, 40, 15) && Sens==0) x=360;
				if (Colision(320, 665, 40, 15) && Sens==2) x=320-Size;

				g.fillRect(230, 690, 20, 110);
				if (Colision(230, 700, 20, 100) && Sens==2) x=230-Size;
				}

//*Dead Red
			g.setColor(Color.red);
			g.fillRect(250, 100, 610, 100);
			g.fillRect(40, 400, 610, 100);
			g.fillRect(250, 700, 610, 100);
			if (Colision(250, 100, 610, 100) || Colision(40, 400, 610, 100) || Colision(250, 700, 610, 100)) initP=0;
//*Button
			if (ButtonOn1==0) g.fillRect(850, 620, 30, 30);
			if (ButtonOn1==1) g.fillRect(860, 620, 30, 30);
			if (Colision(850, 620, 30, 30) && ButtonOn1==0) ButtonOn1=1;

//*P
			g.setColor(Color.gray);
			g.fillRect(P1x, P1y, 60, 10);
			if (Colision(P1x, P1y+3, 10, 2) && Sens==0) x=P1x+60; 

			g.fillRect(P2x, P2y, 60, 10);
			if (Colision(P2x, P2y+3, 10, 2) && Sens==2) x=P2x-Size; 

			g.fillRect(P3x, P3y, 60, 10);
			if (Colision(P3x, P3y+3, 10, 2) && Sens==2) x=P3x-Size; 
//**Suspension
			if (Sustain(810, 60, 50, 2)) Sustained=1;

			else if (Sustain(P1x, P1y, 60, 10)){
				Sustained=1;
				if (P1x>50){
					P1x-=1;
					x-=1;
					}
				if (P1x==50 && P1y<500){
					P1y+=1;
					y+=1;
					}
				}
			else if (Sustain(P2x, P2y, 60 , 10)){
				Sustained=1;
				if (P2y<450){
					P2x+=1;
					x+=1;
					if (P2x>=335){
						P2y+=1;
						y+=1;
						}
					}
				if (P2x<335){
					P2y-=1;
					y-=1;
					}
				}
			else if (Sustain(P3x, P3y, 60, 10)){
				Sustained=1;
				if (P3x+60<860){
					P3x+=1;
					x+=1;
					}
				}
			else if (Sustain(680, 660, 40, 2)) Sustained=1;
			else if (Sustain(580, 645, 40, 2)) Sustained=1;
			else if (Sustain(450, 590, 40, 2)) Sustained=1;
			else if (Sustain(320, 660, 40, 2)) Sustained=1;
			else if (Sustain(230, 690, 20, 2)) Sustained=1;
			else Sustained=0;
//****Goal Level 7
			g.setColor(Color.black);
			g.fillRect(100, 700, 40, 100);
			if (x==100 && y==800){
				Level=8;
				initP=0;
				Sustained=0;
				}
			}
//****Level 8
		else if (Level==8){
			if (initP==0){
				x=100;
				y=800;
				initP=1;
				}
//***Pink Purple Light
			g.setColor(new Color(183, 63, 254));
			g.fillRect(0,0, 30, 800);
			if (Colision(0, 0, 30, 800) && Sens==0) x=30;

			g.fillRect(870, 0, 30, 800);
			if (Colision(870, 0, 30, 800) && Sens==2) x=870-Size;

			g.fillRect(150, 760, 50, 40);
			if (Colision(150, 765, 50, 30) && Sens==0) x=200;
			if (Colision(150, 765, 50, 30) && Sens==2) x=150-Size;

			g.fillRect(300-10, 700, 50+10, 20);
			ColisionALL(300-10, 700, 50+10, 20);
			//if (Colision(300, 705, 50, 10) && Sens==0) x=350;
			//if (Colision(300, 705, 50, 10) && Sens==2) x=300-Size;
			//if (Colision(300, 717, 50, 3)) Saut=SautCooldown=0;

			g.fillRect(450-10, 640, 50+10, 20);
			ColisionALL(450-10, 640, 50+10, 20);
			//if (Colision(450, 645, 50, 10) && Sens==0) x=500;
			//if (Colision(450, 645, 50, 10) && Sens==2) x=450-Size;

			g.fillRect(600-10, 580, 50+10, 20);
			ColisionALL(600-10, 580, 50+10, 20);
			//if (Colision(600, 585, 50, 10) && Sens==0) x=650;
			//if (Colision(600, 585, 50, 10) && Sens==2) x=600-Size;

			g.fillRect(750-10, 520, 50+10, 20);
			ColisionALL(750-10, 520, 50+10, 20);
			//if (Colision(750, 525, 50, 10) && Sens==0) x=800;
			//if (Colision(750, 525, 50, 10) && Sens==2) x=750-Size;
			
			g.fillRect(600-10, 460, 50+10, 20);
			ColisionALL(600-10, 460, 50+10, 20);
			//if (Colision(600, 465, 50, 10) && Sens==0) x=650;
			//if (Colision(600, 465, 50, 10) && Sens==2) x=600-Size;
			//if (Colision(600, 477, 50, 3)) Saut=SautCooldown=0;

			g.fillRect(450-10, 400, 50+10, 20);
			ColisionALL(450-10, 400, 50+10, 20);
			//if (Colision(450, 405, 50, 10) && Sens==0) x=500;
			//if (Colision(450, 405, 50, 10) && Sens==2) x=450-Size;

			g.fillRect(300-10, 340, 50+10, 20);
			ColisionALL(300-10, 340, 50+10, 20);
			//if (Colision(300, 345, 50, 10) && Sens==0) x=350;
			//if (Colision(300, 345, 50, 10) && Sens==2) x=300-Size;

			g.fillRect(150-10, 280, 50+10, 20);
			ColisionALL(150-10, 280, 50+10, 20);
			//if (Colision(150, 285, 50, 10) && Sens==0) x=200;
			//if (Colision(150, 285, 50, 10) && Sens==2) x=150-Size;

			g.fillRect(30, 220, 50, 20);
			if (Colision(30, 225, 50, 10) && Sens==0) x=80;

//***Suspension
			if (Sustain(150, 760, 50, 2)) Sustained=1;
			else if (Sustain(300, 700, 50, 2)) Sustained=1;
			else if (Sustain(450, 640, 50, 2)) Sustained=1;
			else if (Sustain(600, 580, 50, 2)) Sustained=1;
			else if (Sustain(750, 520, 50, 2)) Sustained=1;
			else if (Sustain(600, 460, 50, 2)) Sustained=1;
			else if (Sustain(450, 400, 50, 2)) Sustained=1;
			else if (Sustain(300, 340, 50, 2)) Sustained=1;
			else if (Sustain(150, 280, 50, 2)) Sustained=1;
			else if (Sustain(30, 220, 50, 2)) Sustained=1;
			else Sustained=0;
//***Goal Level 8
			g.setColor(Color.black);
			g.fillRect(35, 150, 30, 70);
			if (x==30 && y==220){
				Level=9;
				initP=0;
				Sustained=0;
				}
			}
//****Level 9
		else if (Level==9){
			if (initP==0){
				x=30;
				y=220;
				P1x=400;
				P1y=580;
				P1Tour=0;
				P2x=630;
				P2y=340;
				P2Tour=0;
				P3x=230;
				P3y=100;
				P3Tour=0;
				Pmove=0;
				initP=1;
				}
//****Purple Light
			g.setColor(new Color(131, 64, 255));
			g.fillRect(0,0, 20, 800);
			if (Colision(0, 0, 20, 800) && Sens==0) x=20;

			g.fillRect(880, 0, 20, 800);
			if (Colision(880, 0, 20, 800) && Sens==2) x=880-Size;

			g.fillRect(20, 760, 40, 20);
			if (Colision(20, 765, 40, 10) && Sens==0) x=60;

			g.fillRect(20, 680, 40, 20);
			if (Colision(20, 685, 40, 10) && Sens==0) x=60;

			g.fillRect(20, 600, 40, 20);
			if (Colision(20, 605, 40, 10) && Sens==0) x=60;

			g.fillRect(110, 580, 90, 20);
			if (Colision(110, 585, 40, 10) && Sens==0) x=200;
			if (Colision(110, 585, 40, 10) && Sens==2) x=110-Size;

			g.fillRect(20, 150, 50, 30);
			if (Colision(20, 155, 40, 10) && Sens==0) x=70;

//*P
			g.setColor(Color.gray);
			g.fillRect(P1x, P1y, 100, 10);
			if (Colision(P1x, P1y+5, 100, 3) && Sens==0) x=P1x+100;
			if (Colision(P1x, P1y+5, 100, 3) && Sens==2) x=P1x-Size;
			P1x=400+(int)(150*Math.cos((Pmove*3.14)/200));
			P1y=530+(int)(-120*Math.sin((Pmove*3.14)/200));

			g.fillRect(P2x, P2y, 100, 10);
			if (Colision(P2x, P2y+5, 100, 3) && Sens==0) x=P2x+100;
			if (Colision(P2x, P2y+5, 100, 3) && Sens==2) x=P2x-Size;
			P2x=630+(int)(150*Math.cos((Pmove*3.14)/200));
			P2y=400+(int)(120*Math.sin((Pmove*3.14)/200));

			g.fillRect(P3x, P3y, 100, 10);
			if (Colision(P3x, P3y+5, 100, 3) && Sens==0) x=P3x+100;
			if (Colision(P3x, P3y+5, 100, 3) && Sens==2) x=P3x-Size;
			P3x=420+(int)(150*Math.cos((Pmove*3.14)/200));
			P3y=150+(int)(-150*Math.sin((Pmove*3.14)/200));
			Pmove+=1;

//***Suspension
			if (Sustain(20, 760, 40, 2)) Sustained=1;
			else if (Sustain(20, 680, 40, 2)) Sustained=1;
			else if (Sustain(20, 600, 40, 2)) Sustained=1;
			else if (Sustain(110, 580, 90, 2)) Sustained=1;
			
			else if (Sustain(P1x, P1y, 100, 10)){
				if (Sustained==0) P1Tour=x-P1x;
				y=530+(int)(-120*Math.sin((Pmove*3.14)/200));
				if (MotionL==1) P1Tour-=1;
				if (MotionR==1) P1Tour+=1;
				x=P1x+P1Tour;
				Sustained=1;
				}
			else if (Sustain(P2x, P2y, 100, 10)){
				if (Sustained==0) P2Tour=x-P2x;
				y=400+(int)(120*Math.sin((Pmove*3.14)/200));
				if (MotionL==1) P2Tour-=1;
				if (MotionR==1) P2Tour+=1;
				x=P2x+P2Tour;
				Sustained=1;
				}
			else if (Sustain(P3x, P3y, 100, 10)){
				if (Sustained==0) P3Tour=x-P3x;
				y=150+(int)(-150*Math.sin((Pmove*3.14)/200));
				if (MotionL==1) P3Tour-=1;
				if (MotionR==1) P3Tour+=1;
				x=P3x+P3Tour;
				Sustained=1;
				}
			else if (Sustain(20, 150, 50, 2)) Sustained=1;
			else Sustained=0;
//***Goal Level 9
			g.setColor(Color.black);
			g.fillRect(25, 100, 20, 50);
			if (x==20 && y==150){
				Level=10;
				initP=0;
				Sustained=0;
				}
			}
//***Level 10
		else if (Level==10){
			if (initP==0){
				x=20;
				y=150;
				LevelColor=1;
				P1y=820;
				ColorP=255;
				ButtonOn1=0;
				initP=1;
				}
//**Purple
//*P
			g.setColor(new Color(90, 0, 255));
			g.fillRect(0,0, 10, 800);
			if (Colision(0,0, 10, 800) && Sens==0) x=10;

			g.fillRect(890, 0, 10, 800);
			if (Colision(890, 0, 10, 800) && Sens==2) x=890-Size;

			g.fillRect(0, 160, 80, 20);
			if (Colision(890, 165, 80, 10) && Sens==0) x=240;

			g.fillRect(810, 350, 80, 20); 
			if (Colision(810, 355, 80, 10) && Sens==2) x=810-Size;

			g.fillRect(10, 500, 70, 20);
			if (Colision(10, 505, 70, 12) && Sens==0) x=80;

			g.fillRect(630, 770, 70, 30);
			if (Colision(630, 775, 70, 20) && Sens==2) x=630-Size;
			if (Colision(630, 775, 70, 20) && Sens==0) x=700;

			g.fillRect(700, 720, 40, 80);
			if (Colision(700, 725, 40, 70) && Sens==2) x=700-Size;
			if (Colision(700, 725, 40, 70) && Sens==0) x=740;

			//if (LineOn(80, 160, 890, 300)) LineOn=1;
			//else if (LineOn(810, 350, 10, 450)) LineOn=1;
			//else if (LineOn(10, 500, 890, 520)) LineOn=1;
			//else LineOn=0;

			g.drawLine(80, 160, 890, 300);
			if (LineOn(80, 160, 890, 300) && x<890-Size && Down==0){
				Slide(80, 160, 890, 300);
				x+=1;
				}
			g.drawLine(810, 350, 10, 450);
			if (LineOn(810, 350, 10, 450) && x>10 && Down==0){
				Slide(810, 350, 10, 450);
				x-=1;
				}
			g.drawLine(10, 500, 890, 520);
			if (LineOn(10, 500, 890, 520) && x<890-Size && Down==0){
				Slide(10, 500, 890, 520);
				x+=1;
				}
			if (ButtonOn1==1){
				g.fillRect(70, 750, 90, 25);
				if (Colision(70, 753, 90, 20) && Sens==0) x=160;

				g.setColor(new Color(ColorP, 0, 0));
				g.fillRect(500, P1y, 50, 15);
				if (Colision(500, P1y+4, 50, 11) && Sens==0) x=550;
				if (Colision(500, P1y+4, 50, 11) && Sens==2) x=500-Size;

				g.fillRect(370, P1y, 50, 15);
				if (Colision(370, P1y+4, 50, 11) && Sens==0) x=420;
				if (Colision(370, P1y+4, 50, 11) && Sens==2) x=370-Size;

				g.fillRect(240, P1y, 50, 15);
				if (Colision(240, P1y+4, 50, 11) && Sens==0) x=290;
				if (Colision(240, P1y+4, 50, 11) && Sens==2) x=240-Size;

				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (P1y>800 && ColorP<255) ColorP+=1;
				else if (ColorP>0) ColorP-=1;
				if (Time>=4 && P1y>780) P1y-=1;
				if (Time<4 && P1y<820) P1y+=1;
				if (Time==8) Time=0;

				g.setColor(Color.black);
				g.fillRect(105, 700, 50, 50);
				if (x==105 && y==750){
					Level=11;
					LevelColor=0;
					initP=0;
					Sustained=0;
					}

				}
			if (y==800) initP=0;
//*Button
			g.setColor(Color.red);
			if (ButtonOn1==0) g.fillRect(690, 735, 30, 30);
			if (ButtonOn1==1) g.fillRect(700, 735, 30, 30);
			if (Colision(690, 735, 30, 30)) ButtonOn1=1;

//**Suspension
			if (Sustain(0, 160, 80, 2)) Sustained=1;
			else if (Sustain(810, 350, 80, 2)) Sustained=1;
			else if (Sustain(10, 500, 70, 2)) Sustained=1;
			else if (Sustain(630, 770, 70, 2)) Sustained=1;
			else if (Sustain(700, 720, 40, 2)) Sustained=1;

			else if (Sustain(500, P1y, 50, 10)){
				Sustained=1;
				if (Time>=4 && P1y>780) y-=1;
				if (Time<4 && P1y<820) y+=1;
				}
			else if (Sustain(370, P1y, 50, 10)){
				Sustained=1;
				if (Time>=4 && P1y>780) y-=1;
				if (Time<4 && P1y<820) y+=1;
				}
			else if (Sustain(240, P1y, 50, 10)){
				Sustained=1;
				if (Time>=4 && P1y>780) y-=1;
				if (Time<4 && P1y<820) y+=1;
				}
			else if (Sustain(110, P1y, 50, 10)){
				Sustained=1;
				if (Time>=4 && P1y>780) y-=1;
				if (Time<4 && P1y<820) y+=1;
				}
			else if (Sustain(70, 750, 90, 2)) Sustained=1;
			else Sustained=0;
			}
//***Level 11
		else if (Level==11){
			if (initP==0){
				x=210;
				y=750;
				Sustained=0;
				P1y=790;
				P2y=790;
				P1Tour=0;
				P2Tour=0;
				P1x=402;
				P2x=402;
				P1Trigger=2;
				P2Trigger=2;
				ButtonOn1=0;
				ButtonOn2=0;
				P3x=500;
				P3Trigger=0;
				initP=1;
				}
//*Blue
			g.setColor(Color.blue);
			g.fillRect(0,0, 100, 800);
			if (Colision(0,0, 100, 800) && Sens==0) x=100;

			g.fillRect(800, 0, 100, 800);
			if (Colision(800,0, 100, 800) && Sens==2) x=800-Size;

			g.fillRect(350, 100, 50, 700);
			if (Colision(380, 105, 20, 695)) x=400;
			if (Colision(350, 105, 20, 695)) x=350-Size;

			g.setColor(new Color(255, 128, 0));
			g.drawLine(400, 100, 800, 130);
			if (LineOn(400, 100, 800, 130) && x<800-Size && Down==0){
				Slide(400, 100, 800, 130);
				x+=1;
				}
			if (ButtonOn1==1){
				g.drawLine(P1x, 250, 400, 280);
				if (LineOn(P1x, 250, 400, 280) && x>400 && Down==0){
					Slide(P1x, 250, 400, 280);
					x-=1;
					}
				}
			if (ButtonOn2==1){
				g.drawLine(P2x, 450, 400, 480);
				if (LineOn(P2x, 450, 400, 480) && x>400 && Down==0){
					Slide(P2x, 450, 400, 480);
					x-=1;
					}
				}
			g.setColor(Color.red);
			if (ButtonOn1==0){
				g.fillRect(400, 300, 400, 50);
				if (Colision(400, 300, 400, 50)) initP=0;
				}
			if (ButtonOn2==0){
				g.fillRect(400, 500, 400, 50);
				if (Colision(400, 500, 400, 50)) initP=0;
				}
			g.fillRect(P3x-20, 690, 20, 110);
			g.fillRect(P3x, 750, 180, 50);
			g.fillRect(P3x+180, 690, 20, 110);
			if (Colision(P3x-20, 690, 20, 110)) initP=0;
			if (Colision(P3x, 750, 180, 50)) initP=0;
			if (Colision(P3x+180, 690, 20, 110)) initP=0;

//*Button
			if (ButtonOn1==0) g.fillRect(790, 235, 30, 30);
			if (ButtonOn1==1) g.fillRect(800, 235, 30, 30);
			if (Colision(790, 235, 30, 30)) ButtonOn1=P1Trigger=1;


			if (ButtonOn2==0) g.fillRect(790, 435, 30, 30);
			if (ButtonOn2==1) g.fillRect(800, 435, 30, 30);
			if (Colision(790, 435, 30, 30)) ButtonOn2=P2Trigger=1;
			

			
//*P
			g.setColor(Color.gray);
			g.fillRect(280, P1y, 50, 10);
			if (Colision(280, P1y+3, 50, 5) && Sens==2) x=280-Size;

			g.fillRect(130, P2y, 50, 10);
			if (Colision(130, P2y+3, 50, 5) && Sens==0) x=180;

			g.fillRect(P1x, 250, 40, 20);
			if (Colision(P1x, 250, 43, 15) && Sens==0) x=P1x+40;
			if (Colision(P1x, 250, 43, 15) && Sens==2) x=P1x-Size;
			if (ButtonOn1==0){
				if (P1Trigger==0) P1x+=1;
				if (P1Trigger==2) P1x-=1;
				if (P1x+40==800) P1Trigger=2;
				if (P1x==400) P1Trigger=0;
				}
			g.fillRect(P2x, 450, 40, 20);
			if (Colision(P2x, 450, 43, 15) && Sens==0) x=P2x+40;
			if (Colision(P2x, 450, 43, 15) && Sens==2) x=P2x-Size;
			if (ButtonOn2==0){
				if (P2Trigger==0) P2x+=2;
				if (P2Trigger==2) P2x-=2;
				if (P2x+40==800) P2Trigger=2;
				if (P2x==400) P2Trigger=0;
				}
			if (P3Trigger==0) P3x+=1;
			if (P3Trigger==1) P3x-=1;
			if (P3x+200==800) P3Trigger=1;
			if (P3x-20==400) P3Trigger=0;


			if (y==800){
				if (P1y<790) P1y+=1;
				if (P2y<790) P2y+=1;
				P1Tour=P2Tour=0;
				}

//**Suspension
			if (Sustain(282, P1y, 48, 10)){
				Sustained=1;
				P2Tour=0;
				if (P1Tour<50 && P1y>100){
					P1y-=1;
					y-=1;
					P1Tour+=1;
					}
				}
			else if (Sustain(132, P2y, 48, 10)){
				Sustained=1;
				P1Tour=0;
				if (P2Tour<50 && P2y>100){
					P2y-=1;
					y-=1;
					P2Tour+=1;
					}
				}
			else if (Sustain(350, 100, 50, 2)) Sustained=1;
			else if (Sustain(P1x, 250, 40, 10)){
				Sustained=1;
				if (P1Trigger==0) x+=1;
				if (P1Trigger==2) x-=1;
				}
			else if (Sustain(P2x, 450, 40, 10)){
				Sustained=1;
				if (P2Trigger==0) x+=2;
				if (P2Trigger==2) x-=2;
				}
			else Sustained=0;
//**Goal Level 11
			g.setColor(Color.black);
			g.fillRect(P3x-1, 700, 182, 50);
			if (P3x<x && x<P3x+180 && y==740){
				Level=12;
				Sustained=0;
				initP=0;
				}
			}
//***Level 12
		else if (Level==12){
			if (initP==0){
				x=500;
				y=740;
				ButtonOn1=0;
				initP=1;
				}
//*Slight Light Blue
			g.setColor(new Color(0, 70, 255));
			g.fillRect(400, 750, 300, 50);
			if (Colision(400, 755, 20, 45)) x=400-Size;
			if (Colision(680, 755, 20, 45)) x=700;

			g.fillRect(0, 0, 90, 800);
			if (Colision(0,0, 90, 800)) x=90;

			g.fillRect(810, 0, 90, 800);
			if (Colision(810,0, 90, 800)) x=810-Size;

			g.fillRect(770, 0, 30, 20);
			if (Colision(770, 0, 30, 20)) y=20+Size;

			g.fillRect(670, 175, 70, 25);
			if (Colision(670, 180, 70, 15) && Sens==2) x=670-Size;
			if (Colision(670, 180, 70, 15) && Sens==0) x=740;

			g.fillRect(300, 0, 30, 20);
			if (Colision(300, 0, 30, 20)) y=20+Size;

			g.fillRect(280, 150, 70, 20);
			if (Colision(280, 155, 70, 10) && Sens==2) x=280-Size;
			if (Colision(280, 155, 70, 10) && Sens==0) x=350;

			g.fillRect(90, 450, 60, 20);
			if (Colision(90, 455, 60, 10) && Sens==0) x=150;

			g.fillRect(210, 430, 50, 20);
			if (Colision(210, 435, 50, 10) && Sens==2) x=210-Size;
			if (Colision(210, 435, 50, 10) && Sens==0) x=260;

			g.fillRect(320, 410, 50, 20);
			if (Colision(320, 415, 50, 10) && Sens==2) x=320-Size;
			if (Colision(320, 415, 50, 10) && Sens==0) x=370;

			g.fillRect(430, 390, 50, 20);
			if (Colision(430, 395, 50, 10) && Sens==2) x=430-Size;
			if (Colision(430, 395, 50, 10) && Sens==0) x=480;

			g.fillRect(540, 370, 50, 20);
			if (Colision(540, 375, 50, 10) && Sens==2) x=540-Size;
			if (Colision(540, 375, 50, 10) && Sens==0) x=590;


			if (ButtonOn1==1){
				g.setColor(Color.orange);
				g.drawLine(785, 20, 785, 800);
				if (x<=785 && 785<=x+Size && ButtonOn1==1){
					Sustained=1;
					y-=2;
					}
				g.drawLine(670, 175, 315, 20);
				if (LineOn(670, 175, 315, 20) && x>315 && Down==0){
					Slide(670, 175, 315, 20);
					x-=1;
					}
				}

//*Button
			g.setColor(Color.red);
			if (ButtonOn1==0) g.fillRect(390, 760, 30, 30);
			if (ButtonOn1==1) g.fillRect(400, 760, 30, 30);			
			if (Colision(390, 760, 30, 30) && ButtonOn1==0) ButtonOn1=1;


//**Suspension
			if (Sustain(400, 750, 300, 2)) Sustained=1;
			else if (Sustain(670, 175, 70, 2)) Sustained=1;
			else if (Sustain(280, 150, 70, 2)) Sustained=1;
			else if (Sustain(90, 450, 60, 2)) Sustained=1;
			else if (Sustain(210, 430, 50, 2)) Sustained=1;
			else if (Sustain(320, 410, 50, 2)) Sustained=1;
			else if (Sustain(430, 390, 50, 2)) Sustained=1;
			else if (Sustain(540, 370, 50, 2)) Sustained=1;
			else Sustained=0;
//**Goal Level 12
			g.setColor(Color.black);
			g.fillRect(560, 320, 10, 50);
			if (x==560 && y==370){
				Level=13;
				Sustained=0;
				initP=0;
				}
			}
//***Level 13
		else if (Level==13){
			if (initP==0){
				x=560;
				y=370;
				P1y=790;
				ButtonOn1=0;
				P1x=80+(int)(Math.random()*361);
				P2x=80+(int)(Math.random()*381);
				P3x=80+(int)(Math.random()*411);
				P2y=80+(int)(Math.random()*441);
				P1Trigger=0;
				P2Trigger=0;
				P3Trigger=0;
				P3y=0;
				ButtonOn2=0;
				P1Tour=80+(int)(Math.random()*411);
				P2Tour=0;
				initP=1;
				}
//*Light Blue
			g.setColor(new Color(0, 110, 240));
			g.fillRect(540, 375, 40, 345);
			if (Colision(540, 377, 5, 340)) x=540-Size;
			if (Colision(575, 377, 5, 340)) x=580;
			if (Colision(540, 717, 40, 3)) Saut=SautCooldown=0;

			g.fillRect(540, 780, 40, 20);
			if (Colision(540, 783, 40, 17) && Sens==2) x=540-Size;
			if (Colision(540, 783, 40, 17) && Sens==0) x=580;

			g.fillRect(0, 0, 80, 800);
			if (Colision(0,0, 80, 800)) x=80;

			g.fillRect(820, 0, 80, 800);
			if (Colision(820,0, 80, 800)) x=820-Size;

			if (ButtonOn1==1){
				g.fillRect(540, 90, 40, 285);
				if (Colision(540, 95, 5, 280)) x=540-Size;
				if (Colision(575, 95, 5, 280)) x=580;

				g.fillRect(300, 0, 20, 15);
				if (Colision(300, 0, 20, 15)) y=20;

				g.setColor(Color.orange);
				g.drawLine(540, 90, 310, 15);
				if (LineOn(540, 90, 310, 15) && x>310 && Down==0){
					Slide(540, 90, 310, 15);
					x-=1;
					}

				g.drawLine(80, 150, 540, 150);

				g.drawLine(80, 250, 540, 250);

				g.drawLine(80, 350, 540, 350);

				g.drawLine(80, 450, 540, 450);
				if (80<=x && x<=540 && Down==0){
					if (y-Size==150 || y-Size==250 || y-Size==350 || y-Size==450){
						ForceSustained=1;
						if (ButtonOn2==0) x+=1;
						if (ButtonOn2==1) x-=1;
						if (x<=80) ButtonOn2=0;
						if (x+Size>=540) ButtonOn2=1;
						}
					}
				else ForceSustained=0;

				g.fillRect(P1Tour, 650, 50, 30);
				if (Colision(P1Tour, 655, 5, 20)) x=P1Tour-Size;
				if (Colision(P1Tour+45, 655, 5, 20)) x=P1Tour+50;
				g.setColor(Color.black);
				g.fillRect(P1Tour+5, 600, 40, 50);
				if (P2Tour==0) P1Tour+=1;
				if (P2Tour==1) P1Tour-=1;
				if (P1Tour<=80) P2Tour=0;
				if (P1Tour+50>=540) P2Tour=1;
				if (x==P1Tour+5 && y==650){
					Level=14;
					Sustained=0;
					initP=0;
					}
				}

//*Button
			g.setColor(Color.red);
			if (ButtonOn1==0) g.fillRect(810, 40, 30, 30); 
			if (ButtonOn1==1) g.fillRect(820, 40, 30, 30);
			if (Colision(810, 40, 30, 30) && ButtonOn1==0) ButtonOn1=1;
//*Red Dead
			if (ButtonOn1==1){
				g.fillRect(P1x, 200, 100, 20);
				if (P1Trigger==0) P1x+=1;
				if (P1Trigger==1) P1x-=1;
				if (P1x<=80) P1Trigger=0;
				if (P1x+100>=540) P1Trigger=1;
				if (Colision(P1x, 200, 100, 20)) initP=0;

				g.fillRect(P2x, 300, 80, 20);
				if (P2Trigger==0) P2x+=1;
				if (P2Trigger==1) P2x-=1;
				if (P2x<=80) P2Trigger=0;
				if (P2x+80>=540) P2Trigger=1;
				if (Colision(P2x, 300, 80, 20)) initP=0;

				g.fillRect(P3x, 400, 50, 20);
				if (P3Trigger==0) P3x+=2;
				if (P3Trigger==1) P3x-=2;
				if (P3x<=80) P3Trigger=0;
				if (P3x+50>=540) P3Trigger=1;
				if (Colision(P3x, 400, 50, 20)) initP=0;

				g.fillRect(P2y, 500, 20, 20);
				if (P3y==0) P2y+=3;
				if (P3y==1) P2y-=3;
				if (P2y<=80) P3y=0;
				if (P2y+20>=540) P3y=1;
				if (Colision(P2y, 500, 20, 20)) initP=0;
				}

			 

//*P
			g.setColor(Color.gray);
			g.fillRect(580, P1y, 240, 10);
			if (y==800 && P1y<790) P1y+=1;
			if (Colision(580, P1y+2, 5, 8) && Sens==2) x=580-Size;

//**Suspension
			if (Sustain(540, 375, 40, 2)) Sustained=1;
			else if (Sustain(540, 780, 40, 2)) Sustained=1;
			else if (Sustain(580, P1y, 240, 5)){
				Sustained=1;
				if (P1y>80){
					P1y-=1;
					y-=1;
					}
				}
			else if (Sustain(540, 90, 40, 2) && ButtonOn1==1) Sustained=1;
			else if (Sustain(P1Tour, 650, 50, 2)){
				Sustained=1;
				if (P2Tour==0) x+=1;
				if (P2Tour==1) x-=1;
				}
			else Sustained=0;
			}

//***Level 14
		else if (Level==14){
			if (initP==0){
				Sustained=0;
				y=650;
				ButtonOn1=0;
				P1x=100;
				P1Trigger=0;
				initP=1;
				}
//**Suspension
			if (Sustain(70, 660, 480, 2) && ButtonOn1==0) Sustained=1;
			else Sustained=0;
//*Strong Light Blue
			g.setColor(new Color(0, 165, 255));
			g.fillRect(0, 0, 70, 800);
			if (Colision(0,0, 70, 800)) x=70;

			g.fillRect(830, 0, 70, 800);
			if (Colision(830,0, 70, 800)) x=830-Size;

			if (ButtonOn1==0){
				g.fillRect(70, 660, 480, 40);
				if (Colision(70, 665, 480, 30) && Sens==0) x=550;
				if (Colision(70, 698, 480, 2)) Saut=SautCooldown=0;
				}
			else{
				g.fillRect(P1x, 0, 30, 15);
				if (Colision(P1x, 0, 30, 15)) y=15+Size;
				if (P1Trigger==0) P1x+=1;
				if (P1Trigger==1) P1x-=1;
				if (P1x<=70) P1Trigger=0;
				if (P1x+30>=830) P1Trigger=1;


				g.setColor(Color.orange);
				g.drawLine(P1x+15, 15, P1x+15, 800);
				if (Colision(P1x+15, 15, 2, 785)){
					if (Down==0) y-=1;
					Sustained=1;
					}
				g.setColor(Color.red);
				g.fillRect(70, 500, 500, 100);
				if (Colision(70, 500, 500, 100)) initP=0;

				g.fillRect(330, 300, 500, 100);
				if (Colision(330, 300, 500, 100)) initP=0;

				g.fillRect(70, 100, 500, 100);
				if (Colision(70, 100, 500, 100)) initP=0;
				}

//*Button
			g.setColor(Color.red);
			if (ButtonOn1==0) g.fillRect(50 , 750, 30, 30);
			if (ButtonOn1==1) g.fillRect(40 , 750, 30, 30);
			if (Colision(50, 750, 30, 30) && ButtonOn1==0) ButtonOn1=1;
//**Goal Level 14
			g.setColor(Color.black);
			g.fillRect(80, 15, Size, Size);
			if (x==80 && y<=15+Size){
				Level=15;
				Sustained=0;
				initP=0;
				}
			}
//***Level 15
		else if (Level==15){
			if (initP==0){
				x=80;
				y=30+Size;
				LevelColor=1;
				ButtonOn1=0;
				P1x=200;
				P1y=250;
				P1Tour=0;
				Pmove=0;
				P2x=400;
				P1Trigger=0;
				P2y=710;
				ButtonOn2=0;
				initP=1;
				}
			if (y==800) initP=0;
//*Sky Blue
			g.setColor(new Color(65, 220, 255));
			g.fillRect(0, 0, 60, 800);
			if (Colision(0, 0, 60, 800)) x=60;

			g.fillRect(840, 0, 60, 800);
			if (Colision(840, 0, 60, 800)) x=840-Size;

			g.fillRect(60, 70, 365, 15);
			if (Colision(423, 72, 2, 12)) x=425;
			if (Colision(60, 83, 365, 2)) Saut=SautCooldown=0;

			g.fillRect(475, 70, 320, 15);
			if (Colision(475, 72, 2, 12)) x=475-Size;
			if (Colision(475, 83, 320, 2)) Saut=SautCooldown=0;

			g.fillRect(P1x, P1y, 10, 100);
			if (Colision(P1x, P1y+2, 2, 95)) x=P1x-Size;
			if (Colision(P1x+8, P1y+2, 2, 95) && ForceSustained==0) x=P1x+10;

			g.fillRect(P1x+300, P1y, 10, 100);
			if (Colision(P1x+308, P1y+2, 2, 95)) x=P1x+310;
			if (Colision(P1x+300, P1y+2, 2, 95) && ForceSustained==0) x=P1x+300-Size;

			P1x=280+(int)(150*Math.cos((Pmove*3.14)/230));
			P1y=280+(int)(150*Math.sin((Pmove*3.14)/230));
			Pmove+=1;

			g.fillRect(P2x, 700, 5, 100);
			if (Colision(P2x, 703, 2, 97)) x=P2x-Size;
			if (Colision(P2x+3, 703, 2, 97)) x=P2x+5;
			P2x=400+(int)(200*Math.cos((Pmove*3.14)/200));
			
			if (P1Trigger==1){
				g.fillRect(650, 710, 5, 90);
				if (Colision(650, 713, 2, 87)) x=650-Size;
				if (Colision(653, 713, 2, 87)) x=655;

				g.fillRect(700, 710, 5, 90);
				if (Colision(700, 713, 2, 87)) x=700-Size;
				if (Colision(703, 713, 2, 87)) x=705;

				g.fillRect(745, 710, 5, 90);
				if (Colision(745, 713, 2, 87)) x=745-Size;
				if (Colision(748, 713, 2, 87)) x=750;

				g.fillRect(790, 710, 5, 90);
				if (Colision(790, 713, 2, 87)) x=790-Size;
				if (Colision(793, 713, 2, 87)) x=795;

				g.fillRect(830, P2y, 5, 90);
				if (Colision(830, 713, 2, 87)) x=830-Size;
				}
			if (ButtonOn2==0){
				g.fillRect(475, 0, 40, 70);
				if (Colision(475, 2, 2, 65)) x=475-Size;
				if (Colision(513, 2, 2, 65)) x=515;
				}
			if (ButtonOn2==1){
				g.fillRect(60, 790, 40, 10);
				if (Colision(60, 792, 40, 8) && Sens==0) x=100;
				g.setColor(Color.black);
				g.fillRect(60, 760, 30, 30);
				if (x==60 && y==790){
					Level=16;
					Sustained=0;
					LevelColor=0;
					initP=0;
					}
				}
			g.setColor(Color.orange);
			g.drawLine(P1x+10, P1y+50, P1x+300, P1y+50);
			if (P1x<=x && x<=P1x+300 && P1y+48<=y-Size && y-Size<=P1y+50 && Down==0){
				y=280+50+Size+(int)(150*Math.sin((Pmove*3.14)/230));
				if (ForceSustained==0) P1Tour=x-P1x;
				x=P1x+P1Tour;
				if (ButtonOn1==0) P1Tour+=1;
				if (ButtonOn1==1) P1Tour-=1;
				if (x+Size>=P1x+300) ButtonOn1=1;
				if (x<=P1x+10) ButtonOn1=0;
				ForceSustained=1;
				}
			else ForceSustained=0;

//*Button
			g.setColor(Color.red);
			if (ButtonOn2==0) g.fillRect(495, 35, 30, 30);
			if (Colision(495, 35, 30, 30) && ButtonOn2==0) ButtonOn2=1;
			

//**Suspension
			if (Sustain(60, 70, 365, 2)) Sustained=1;
			else if (Sustain(475, 70, 365, 2)) Sustained=1;
			else if (Sustain(P1x, P1y, 10, 2) || Sustain(P1x+300, P1y, 10, 2)){
				y=280+(int)(150*Math.sin((Pmove*3.14)/230));
				if (Sustained==0) P1Tour=x-P1x;
				x=P1x+P1Tour;
				if (MotionL==1) P1Tour-=1;
				if (MotionR==1) P1Tour+=1;
				Sustained=1;
				}
			else if (Sustain(P2x, 700, 5, 2)){
				if (Sustained==0) P1Tour=x-P2x;
				x=P2x+P1Tour;
				if (MotionL==1) P1Tour-=1;
				if (MotionR==1) P1Tour+=1;
				Sustained=P1Trigger=1;
				}
			else if (Sustain(650, 710, 5, 2)) Sustained=1;
			else if (Sustain(700, 710, 5, 2)) Sustained=1;
			else if (Sustain(745, 710, 5, 2)) Sustained=1;
			else if (Sustain(790, 710, 5, 2)) Sustained=1;
			else if (Sustain(830, P2y, 5, 2)){
				Sustained=1;
				if (P2y>70){
					P2y-=1;
					y-=1;
					}
				}
			else if (Sustain(60, 790, 40, 2)) Sustained=1;
			else Sustained=0;
			}
//***Level 16
		else if (Level==16){
			if (initP==0){
				Sustained=0;
				x=60;
				y=790;
				ButtonOn1=0;
				P1x=60;
				P1Tour=0;
				P2Tour=0;
				P2Trigger=0;
				P2x=0;
				P1Trigger=0;
				initP=1;
				}

//**Suspension
			if (Sustain(250, 50, 5, 2)) Sustained=1;
			else if (Sustain(660, 65, 5, 2)) Sustained=1;
			else if (Sustain(255, 280, 50, 2)) Sustained=1;
			else if (Sustain(375, 280, 80, 2)) Sustained=1;
			else if (Sustain(255, 400, 110, 2)) Sustained=1;
			else if (Sustain(325, 510, 130, 2)) Sustained=1;
			else if (Sustain(460+P2x, 180, 20, 2)){
				Sustained=1;
				if (Time<5 && P2x<80) x+=1;
				if (Time>=5 && P2x>0) x-=1;
				}
			else if (Sustain(640-P2x, 500, 20, 2)){
				Sustained=1;
				if (Time<5 && P2x<80) x-=1;
				if (Time>=5 && P2x>0) x+=1;
				}
			else Sustained=0;
//*Bright Sky Blue
			g.setColor(new Color(50, 230, 255));
			g.fillRect(0, 0, 50, 800);
			if (Colision(0, 0, 50, 800)) x=50;

			g.fillRect(850, 0, 50, 800);
			if (Colision(850, 0, 50, 800)) x=850-Size;

			g.fillRect(250, 50, 5, 750);
			if (Colision(250, 53, 2, 747)) x=250-Size;
			if (Colision(253, 53, 2, 747)) x=255;

			g.fillRect(455, 0, 5, 750);
			if (Colision(455, 0, 2, 747)) x=455-Size;
			if (Colision(458, 0, 2, 747)) x=460;
			if (Colision(455, 748, 5, 2)) Saut=SautCooldown=0;

			g.fillRect(660, 65, 5, 735);
			if (Colision(660, 68, 2, 747)) x=660-Size;
			if (Colision(663, 68, 2, 747)) x=665;

			g.fillRect(P1x, 0, 10, 15);
			if (Colision(P1x, 0, 10, 15)) y=15+Size;
			if (P1Tour==0) P1x+=1;
			if (P1Tour==1) P1x-=1;
			if (P1x<=50) P1Tour=0;
			if (P1x+5>=250) P1Tour=1;

			g.fillRect(255, 90, P2Tour, 110);

			g.fillRect(405+P2Tour, 90, 50-P2Tour, 110);
			if (P2Trigger==0) P2Tour+=1;
			if (P2Trigger==1) P2Tour-=1;
			if (P2Tour<=0) P2Trigger=0;
			if (P2Tour>=50) P2Trigger=1;

			g.fillRect(255, 280, 50, 60);

			g.fillRect(375, 280, 80, 60);

			g.fillRect(255, 400, 110, 60);

			g.fillRect(435, 400, 20, 60);

			g.fillRect(255, 510, 10, 60);

			g.fillRect(325, 510, 130, 60);

			g.fillRect(255, 620, 20+P2Tour, 120);

			g.fillRect(385+P2Tour, 620, 70-P2Tour, 120);

			g.fillRect(640-P2x, 0, 20, 30);
			if (Colision(642-P2x, 28, 16, 2)) y=30+Size;
			if (Colision(640-P2x, 0, 2, 30)) x=640-P2x-Size;
			if (Colision(658-P2x, 0, 2, 30)) x=660-P2x;
			g.fillRect(640-P2x, 250, 20, 30);
			if (Colision(640-P2x, 250, 2, 30)) x=640-P2x-Size;
			if (Colision(658-P2x, 250, 2, 30)) x=660-P2x;
			g.fillRect(460+P2x, 180, 20, 30);
			if (Colision(462+P2x, 208, 16, 2)) y=210+Size;
			if (Colision(460+P2x, 180, 2, 30)) x=460+P2x-Size;
			if (Colision(478+P2x, 180, 2, 30)) x=480+P2x;
			g.fillRect(460+P2x, 570, 20, 30);
			if (Colision(460+P2x, 570, 2, 30)) x=460+P2x-Size;
			if (Colision(478+P2x, 570, 2, 30)) x=480+P2x;
			g.fillRect(640-P2x, 500, 20, 30);
			if (Colision(642-P2x, 528, 16, 2)) y=530+Size;
			if (Colision(640-P2x, 500, 2, 30)) x=640-P2x-Size;
			if (Colision(658-P2x, 500, 2, 30)) x=660-P2x;
			g.fillRect(640-P2x, 770, 20, 30);
			if (Colision(640-P2x, 770, 2, 30)) x=640-P2x-Size;
			if (Colision(658-P2x, 770, 2, 30)) x=660-P2x;
			if (Time<5 && P2x<80) P2x+=1;
			if (Time>=5 && P2x>0) P2x-=1;
			if (Time==10) Time=0;
			
			if (TimeOn==0){
				TimeOn=1;
				new Time().start();
				}

			if (ButtonOn2==1){
				g.setColor(Color.orange);
				g.drawLine(P1x+5, 15, P1x+5, 800);
				if (Colision(P1x+4, 15, 2, 785)){
					Sustained=1;
					y-=1;
					}
				g.drawLine(255, 50, 455, 50);
				g.drawLine(255, 240, 455, 240);
				g.drawLine(255, 350, 455, 350);
				g.drawLine(255, 470, 455, 470);
				g.drawLine(255, 580, 455, 580);
				if (255<=x && x<=455 && (y-Size==50 || y-Size==240 || y-Size==350 || y-Size==470 || y-Size==580) && Down==0){
					Saut=SautCooldown=0;
					Sustained=1;
					if (ButtonOn1==0) x+=1;
					if (ButtonOn1==1) x-=1;
					if (x<=255) ButtonOn1=0;
					if (x+Size>=450) ButtonOn1=1;
					}
				g.drawLine(640-P2x+10, 30, 640-P2x+10, 250);
				g.drawLine(460+P2x+10, 210, 460+P2x+10, 570);
				g.drawLine(640-P2x+10, 530, 640-P2x+10, 770);
				if (Colision(640-P2x+9, 30, 2, 220) || Colision(460+P2x+9, 210, 2, 350) || Colision(640-P2x+9, 530, 2, 220)){
					Sustained=1;
					y-=1;
					}
				for(int i=0; i<7; i++){
					g.drawLine(665, 65+i*100, 850, 100+i*100);
					if (LineOn(665, 65+i*100, 850, 100+i*100) && x<850-Size && Down==0){
						Slide(665, 65+i*100, 850, 100+i*100);
						x+=1;
						}
					if (i<6){
						g.drawLine(850, 105+i*100, 665, 160+i*100);
						if (LineOn(850, 105+i*100, 665, 160+i*100) && x>665 && Down==0){
							Slide(850, 105+i*100, 665, 160+i*100);
							x-=1;
							}
						}
					}		

				//g.drawLine(665, 65, 850, 100);
				//g.drawLine(850, 105, 665, 150);
				//g.drawLine(665, 155, 850, 200);
				//g.drawLine(850, 205, 665, 250);
				//g.drawLine(665, 255, 850, 300);
				//g.drawLine(850, 305, 665, 350);
				//g.drawLine(665, 355, 850, 400);
				//g.drawLine(850, 405, 665, 450);
				//g.drawLine(665, 455, 850, 500);
				//g.drawLine(850, 505, 665, 550);
				//g.drawLine(665, 555, 850, 600);
				//g.drawLine(850, 605, 665, 650);
				//g.drawLine(665, 655, 850, 700);
				}
//*Red Dead
			g.setColor(Color.red);
			g.fillRect(255+P2Tour, 90, 10, 110);
			g.fillRect(395+P2Tour, 90, 10, 110);
			if (Colision(255+P2Tour, 90, 10, 110) || Colision(395+P2Tour, 90, 10, 110)) initP=0;
			g.fillRect(305, 280, 10, 60);
			g.fillRect(365, 280, 10, 60);
			if (Colision(305, 280, 10, 60) || Colision(365, 280, 10, 60)) initP=0;
			g.fillRect(365, 400, 10, 60);
			g.fillRect(425, 400, 10, 60);
			if (Colision(365, 400, 10, 60) || Colision(425, 400, 10, 60)) initP=0;
			g.fillRect(265, 510, 10, 60);
			g.fillRect(315, 510, 10, 60);
			if (Colision(265, 510, 10, 60) || Colision(315, 510, 10, 60)) initP=0;	
			g.fillRect(275+P2Tour, 620, 10, 120);
			g.fillRect(375+P2Tour, 620, 10, 120);
			if (Colision(275+P2Tour, 620, 10, 120) || Colision(375+P2Tour, 620, 10, 120)) initP=0;		
			
//*Button
			if (ButtonOn2==0) g.fillRect(30, 750, 30, 30);
			if (ButtonOn2==1) g.fillRect(20, 750, 30, 30);
			if (Colision(30, 750, 30, 30) && ButtonOn2==0) ButtonOn2=1;
//**Goal Level 16
			g.setColor(Color.black);
			g.fillRect(670, 750, 30, 50);
			if (x==670 && y==800){
				Level=17;
				initP=0;
				}
			}
//***Level 17
		else if (Level==17){
			if (initP==0){
				x=685;
				y=800;
				P1x=640;
				P1Trigger=2;
				ButtonOn1=0;
				initP=1;
				}
//**Suspension
			if (Sustain(640, 700, 100, 2)) Sustained=1;
			else if (Sustain(680, 60, 20, 2)) Sustained=1;
			else if (Sustain(700, 60, 100, 2)) Sustained=1;
			else if (Sustain(40, 700, 600, 2) && ButtonOn1==0) Sustained=1;
			else if (Sustain(45, 795, 100, 2)) Sustained=1;
			else Sustained=0;
//*Turquoise
			g.setColor(new Color(50, 255, 230));
			g.fillRect(0, 0, 40, 800);
			if (Colision(0, 0, 40, 800)) x=40;

			g.fillRect(860, 0, 40, 800);
			if (Colision(860, 0, 40, 800)) x=860-Size;
			
			g.fillRect(640, 720, 20, 80);
			if (Colision(640, 723, 20, 75) && Sens==0) x=660;
			if (Colision(640, 723, 20, 75) && Sens==2) x=640-Size;
			
			g.fillRect(640, 700, 100, 20);
			if (Colision(640, 703, 20, 15) && Sens==0) x=740;
			if (Colision(640, 703, 20, 15) && Sens==2) x=640-Size;
			if (Colision(640, 718, 100, 2)) Saut=SautCooldown=0;

			g.fillRect(680, 60, 20, 640);
			if (Colision(680, 63, 20, 637) && Sens==0) x=700;
			if (Colision(680, 63, 20, 637) && Sens==2) x=680-Size;

			g.fillRect(700, 60, 100, 20);
			if (Colision(700, 63, 100, 15) && Sens==0) x=800;

			g.fillRect(820, 0, 20, 10);
			if (Colision(820, 0, 20, 10)) y=10+Size;

			g.fillRect(P1x, 0, Size, 10);
			if (P1Trigger==0) P1x+=1;
			if (P1Trigger==1) P1x-=1;
			if (P1x<=40) P1Trigger=0;
			if (P1x+Size>=680) P1Trigger=1;
			
			if (ButtonOn1==0){
				g.fillRect(40, 700, 600, 20);
				if (Colision(40, 700, 600, 20)) y=700;
				}
			else{
				for(int i=0; i<11; i++){
					g.fillRect(650-i*50, 700+i*9, 50, 12);
					if (Colision(650-i*50, 703+i*9, 50, 8) && Sens==2) x=650-i*50-Size;
					if (Colision(650-i*50, 700+i*9, 50, 2)) Sustained=1;
					}
				g.fillRect(45, 795, 105, 5);
				g.setColor(Color.black);
				g.fillRect(45, 755, 15, 40);
				if (x==45 && y==795){
					Level=18;
					Sustained=0;
					initP=0;
					}
				}

			g.setColor(Color.orange);
			g.drawLine(830, 10, 830, 800);
			if (Colision(829, 10, 2, 790)){
				Sustained=1;
				y-=1;
				}

			if (Colision(P1x+(int)(Size/2)-1, 10, 2, 790) && ButtonOn1==0){
				if (P1Trigger==2) P1Trigger=1;
				Sustained=1;
				x=P1x;
				g.drawLine(P1x+(int)(Size/2), 10, P1x+(int)(Size/2), y-Size);
				if (Down==1) y+=1;
				}
//*Dead Red
			g.setColor(Color.red);
			g.fillRect(40, 100, 160, 30);
			if (Colision(40, 100, 160, 30)) initP=0;
			
			g.fillRect(300, 100, 150, 50);
			if (Colision(300, 100, 150, 50)) initP=0;

			g.fillRect(600, 130, 80, 20);
			if (Colision(600, 130, 80, 20)) initP=0;

			g.fillRect(100, 220, 200, 40);
			if (Colision(100, 220, 200, 40)) initP=0;

			g.fillRect(500, 230, 110, 30);
			if (Colision(500, 230, 110, 30)) initP=0;

			for(int i=0; i<4; i++){
				g.fillRect(40+i*160, 320, 40, 20);
				if (Colision(40+i*160, 320, 40, 20)) initP=0;
				}

			for(int i=0; i<4; i++){
				g.fillRect(40+i*160, 380, 80, 40);
				if (Colision(40+i*160, 380, 80, 40)) initP=0;
				}

			g.fillRect(120, 500, 580, 50);
			if (Colision(120, 500, 580, 50)) initP=0;

			for(int i=0; i<8; i++){
				g.fillRect(40+i*80, 620, 20, 10);
				if (Colision(40+i*80, 620, 20, 10)) initP=0;
				}
//*Button
			if (ButtonOn1==0) g.fillRect(670, 660, 30, 30);
			if (ButtonOn1==1) g.fillRect(680, 660, 30, 30);
			if (Colision(670, 660, 30, 30)) ButtonOn1=1;
			}
//***Level 18 
//******PIEGES A AJOUTER: RETOUR RAPIDE********
		else if (Level==18){
			if (initP==0){
				x=45;
				y=800;
				Time=0;
				P1y=735;
				P1Tour=0;
				initP=1;
				}
//**Suspension
			if (Sustain(30, 50, 840, 2)) Sustained=1;
			else Sustained=0;
//*Blue Lagoon
			g.setColor(new Color(1, 220, 190));
			g.fillRect(0, 0, 30, 800);
			if (Colision(0, 0, 30, 800)) x=30;

			g.fillRect(870, 0, 30, 800);
			if (Colision(870, 0, 30, 800)) x=870-Size;

			g.fillRect(30, P1y, 20, 30);
			g.fillRect(850, P1y, 20, 30);
			if (Colision(30, P1y+28, 20, 2) || Colision(850, P1y+28, 20, 2)) Saut=SautCooldown=0;
			if (Colision(48, P1y, 2, 28)) x=50;
			if (Colision(848, P1y, 2, 28)) x=850-Size;

			g.fillRect(30, 50, 840, 20);
			g.setColor(Color.black);
			g.fillRect(40, 5, 40, 45);
			if (x==40 && y==50){
				Level=19;
				Sustained=0;
				initP=0;
				}
		
			g.setColor(Color.orange);
			g.drawLine(50, P1y+15, 850, P1y+15);
			if (Colision(50, P1y+15, 800, 2) && Down==0){
				Sustained=1;
				y=P1y+15+Size;
				}
			if (Time>3 && P1Tour<200){
				P1Tour+=1;
				P1y-=1;
				y-=1;
				}
			if (Time==5) Time=P1Tour=0;
				
			if (TimeOn==0 && P1y>-50 && Sustained==1){
				new Time().start();
				TimeOn=1;
				}
//*Dead Red
			g.setColor(Color.red);
			g.fillRect(50, 630, 370, 40);
			g.fillRect(480, 630, 370, 40);
			if (Colision(50, 630, 370, 40) || Colision(480, 630, 370, 40)) initP=0;
			for(int i=0; i<4; i++){
				g.fillRect(50+i*200, 430, 100, 40);
				if (Colision(50+i*200, 430, 100, 40)) initP=0;
				}
			g.fillRect(50, 230, 750, 40);
			if (Colision(50, 230, 750, 40)) initP=0;
			}
//***Level 19
		else if (Level==19){
			if (initP==0){
				x=40;
				y=50;
				Time=0;
				P1y=55;
				P1Trigger=1;
				P1Tour=0;
				Sustained=0;
				P2Trigger=0;
				initP=1;
				}
//*Turquoise Green Smith
			g.setColor(new Color(0, 220, 100));
			g.fillRect(0, 0, 20, 800);
			if (Colision(0, 0, 20, 800)) x=20;

			g.fillRect(880, 0, 20, 800);
			if (Colision(880, 0, 20, 800)) x=880-Size;

			g.fillRect(235, 0, 5, 750);
			if (Colision(235, 0, 5, 750) && Sens==0) x=240;
			if (Colision(235, 0, 5, 750) && Sens==2) x=235-Size;

			g.fillRect(455, 50, 5, 750);
			if (Colision(455, 50, 5, 750) && Sens==0) x=460;
			if (Colision(455, 50, 5, 750) && Sens==2) x=455-Size;


			g.setColor(Color.orange);
			g.drawLine(20, P1y, 880, P1y);
			if (Colision(20, P1y+Size, 860, 2) && Down==0){
				Sustained=1;
				y=P1y+Size;
				}
			else Sustained=0;
			if (P1Trigger==1){
				if (Time>6 && P1Tour<150){
					P1Tour+=1;
					P1y+=1;
					y+=1;
					}	
				if (Time==8) Time=P1Tour=0;
				if (P1y>750){
					P1Trigger=2;
					P1Tour=Time=0;
					}
				}
			if (P1Trigger==2){
				if (Time>4 && P1Tour<100){
					P1Tour+=1;
					P1y-=1;
					y-=1;
					}	
				if (Time==6) Time=P1Tour=0;
				if (P1y<12){
					P1Trigger=3;
					P1Tour=Time=0;
					}
				}
			if (P1Trigger==3 && P1y<700){
				if (Time>1 && P1Tour<=Size){
					P1Tour+=1;
					P1y+=1;
					y+=1;
					}	
				if (Time==3) Time=P1Tour=0;
				if (P1y>700) Sustained=0;
				}
			if (TimeOn==0){
				new Time().start();
				TimeOn=1;
				}
//*Red Dead
// 460 et 880
			g.setColor(Color.red);
			g.fillRect(20, 90, 85, 30);
			g.fillRect(150, 90, 85, 30);
			if (Colision(20, 90, 85, 30) || Colision(150, 90, 85, 30)) initP=0;
			g.fillRect(20, 240, 150, 30);
			if (Colision(20, 240, 150, 30)) initP=0;
			g.fillRect(70, 390, 165, 30);
			if (Colision(70, 390, 165, 30)) initP=0;
			g.fillRect(20, 540, 20, 30);
			g.fillRect(75, 540, 105, 30);
			g.fillRect(215, 540, 20, 30);
			if (Colision(20, 540, 20, 30) || Colision(75, 540, 105, 30) || Colision(215, 540, 20, 30)) initP=0;
			for(int i=0; i<3; i++){
				g.fillRect(20+i*86, 690, 43, 30);
				if (Colision(20+i*86, 690, 43, 30)) initP=0;
				}

			g.fillRect(300, 720, 155, 30);
			if (Colision(300, 720, 155, 30)) initP=0;
			g.fillRect(435, 620, 20, 30);
			g.fillRect(240, 620, 155, 30);
			if (Colision(435, 620, 20, 30) || Colision(240, 620, 155, 30)) initP=0;
			if (P1Trigger==2 && P1y>620){
				g.fillRect(435, 520, 20, 30);
				g.fillRect(240, 520, 155, 30);
				}
			else if (P1Trigger==2){
				g.fillRect(240, 520, 20, 30);
				g.fillRect(300, 520, 155, 30);
				}
			if (Colision(240, 520, 20, 30) || Colision(300, 520, 155, 30)) initP=0;
			if (P1Trigger==2 && P1y>520){
				g.fillRect(240, 420, 20, 30);
				g.fillRect(300, 420, 155, 30);
				}
			else if (P1Trigger==2){
				g.fillRect(435, 420, 20, 30);
				g.fillRect(240, 420, 155, 30);
				}
			if (Colision(435, 420, 20, 30) || Colision(240, 420, 155, 30)) initP=0;
			g.fillRect(240, 320, 20, 30);
			g.fillRect(435, 320, 20, 30);
			g.fillRect(300, 320, 95, 30);
			if (Colision(240, 320, 20, 30) || Colision(435, 320, 20, 30) || Colision(300, 320, 95, 30)) initP=0;
			if (P1Trigger==2 && P1y>320){
				g.fillRect(240, 220, 20, 30);
				g.fillRect(435, 220, 20, 30);
				g.fillRect(300, 220, 95, 30);
				}
			else if (P1Trigger==2){
				g.fillRect(240, 220, 87, 30);
				g.fillRect(368, 220, 87, 30);
				}
			if (Colision(240, 220, 87, 30) || Colision(368, 220, 87, 30)) initP=0;
			g.fillRect(240, 120, 215-Size, 30);
			if (Colision(240, 120, 215-Size, 30)) initP=0;
			
			g.fillRect(460, 77, 40, 30);
			g.fillRect(540, 77, 340, 30);
			if (Colision(460, 77, 40, 30) || Colision(540, 77, 340, 30)) initP=0;
			g.fillRect(460, 139, 140, 30);
			g.fillRect(640, 139, 240, 30);
			if (Colision(460, 139, 140, 30) || Colision(640, 139, 240, 30)) initP=0;
			g.fillRect(460, 203, 240, 30);
			g.fillRect(740, 203, 140, 30);
			if (Colision(460, 203, 240, 30) || Colision(740, 203, 140, 30)) initP=0;
			g.fillRect(460, 267, 350, 30);
			g.fillRect(850, 267, 30, 30);
			if (Colision(460, 267, 350, 30) || Colision(850, 267, 30, 30)) initP=0;
			g.fillRect(460, 331, 40, 30);
			g.fillRect(540, 331, 340, 30);
			if (Colision(460, 331, 40, 30) || Colision(540, 331, 340, 30)) initP=0;
			g.fillRect(460, 395, 380, 30);
			if (Colision(460, 395, 380, 30)) initP=0;
			g.fillRect(500, 459, 380, 30);
			if (Colision(500, 459, 380, 30)) initP=0;
			for(int i=0; i<4; i++){
				g.fillRect(460+120*i, 523, 60, 30);
				if (Colision(460+120*i, 523, 60, 30)) initP=0;
				}
			for(int i=0; i<5; i++){
				g.fillRect(460+84*i, 587, 42, 30);
				if (Colision(460+84*i, 587, 42, 30)) initP=0;
				}
			if (P1Trigger==3 && P1y<590) P2Trigger=Time;
			if (P2Trigger==0){
				g.fillRect(460, 652, 360, 30);
				g.fillRect(860, 652, 20, 30);
				if (Colision(460, 652, 360, 30) || Colision(860, 652, 20, 30)) initP=0;
				}
			else if (P2Trigger==1){
				g.fillRect(460, 652, 20, 30);
				g.fillRect(520, 652, 360, 30);
				if (Colision(460, 652, 20, 30) || Colision(520, 652, 360, 30)) initP=0;
				}
			else if (P2Trigger==2){
				g.fillRect(460, 652, 190, 30);
				g.fillRect(690, 652, 190, 30);
				if (Colision(460, 652, 190, 30) || Colision(690, 652, 190, 30)) initP=0;
				}
//**Goal Level 19
			g.setColor(Color.black);
			g.fillRect(620, 750, 100, 25);
			if (620<x && x+Size<720 && y==760){
				Level=20;
				Sustained=0;
				initP=0;
				}
			}
//***Level 20
		else if (Level==20){
			if (initP==0){
				x=680;
				y=760;
				LevelColor=1;
				P1x=650;
				P1Trigger=0;
				ButtonOn1=0;
				P1y=630;
				P2x=310;
				P3x=260;
				P3y=780;
				P3Trigger=1;
				P1Tour=0;
				P2Tour=0;
				initP=1;
				}
			if (y==800) initP=0;
//**Suspension
			if (Sustain(P1x, 780, 60, 2)){
				Sustained=1;
				if (x<P1x && P1x<x+Size && P1x>300){
					P1x-=1;
					x-=1;
					}
				if (x<P1x+60 && P1x+60<x+Size && P1x<890){
					P1x+=1;
					x+=1;
					}
				if (ButtonOn1==1){
					if (P3x+30<290) P3x+=1;
					if (P3y<780) P3y+=1;
					}
				}
			else if (Sustain(710, P1y, 170, 2)){
				Sustained=1;
				if (P1y>60 && ButtonOn1==1){
					P1y-=1;
					y-=1;
					}
				}
			else if (Sustain(690, 60, 10, 2)) Sustained=1;
			else if (Sustain(P3x, P3y, 30, 2)){
				Sustained=1;
				if (P3y>50){
					if (P3Trigger==0) P3x+=1;
					if (P3Trigger==1) P3x-=1;
					if (P3x+30>=290) P3Trigger=1;
					if (P3x<=10) P3Trigger=0;
					if (P3Trigger==0) x+=1;
					if (P3Trigger==1) x-=1;
					P3y-=1;
					y-=1;
					}
				}
			else if (Sustain(10, 50, 180, 2)) Sustained=1;
			else Sustained=0;

			g.setColor(Color.blue);
			g.fillRect(P1x, 780, 2, 20);
			g.fillRect(P1x+58, 780, 2, 20);
//*Jade			
			g.setColor(new Color(35, 255, 105));
			g.fillRect(0, 0, 10, 800);
			if (Colision(0, 0, 10, 800)) x=10;

			g.fillRect(890, 0, 10, 800);
			if (Colision(890, 0, 10, 800)) x=890-Size;

			g.fillRect(P1x+2, 780, 56, 20);
			if (Colision(P1x, 783, 60, 17) && Sens==0) x=P1x+60;
			if (Colision(P1x, 783, 60, 17) && Sens==2) x=P1x-Size;

			g.fillRect(690, 60, 10, 600);
			if (Colision(690, 60, 2, 600)) x=690-Size;
			if (Colision(698, 60, 2, 600)) x=700;
			
			g.fillRect(700, 640, 190, 20);

			g.fillRect(290, 0, 10, 700);
			if (Colision(290, 0, 2, 699)) x=290-Size;
			if (Colision(298, 0, 2, 699)) x=300;
			if (Colision(290, 699, 10, 2)) Saut=SautCooldown=0;

			if (ButtonOn1==0){
				g.fillRect(290, 700, 10, 100);
				if (Colision(290, 700, 10, 100)) x=300;
				}
			if (P3y==50) g.fillRect(10, 50, 180, 10);

			g.fillRect(P2x, 0, 30, 15);
			if (Colision(P2x, 0, 30, 15)) y=15+Size;
			if (ButtonOn1==0){
				g.setColor(Color.orange);
				g.drawLine(P2x+15, 15, P2x+15, 800);
				if (Colision(P2x+15, 15, 2, 785)){
					Sustained=1;
					y-=1;
					}
				if (P1Trigger==0) P2x+=1;
				if (P1Trigger==1) P2x-=1;
				if (P2x+30>=690) P1Trigger=1;
				if (P2x<=300) P1Trigger=0;

				for(int i=0; i<8; i++){
					g.drawLine(700, 65+i*65, 890, 65+i*65);
					if (y-Size==65+i*65 && 700<=x && x<=890 && Down==0){
						Sustained=1;
						if (P1Tour==0) x+=1;
						if (P1Tour==1) x-=1;
						if (x+Size>=890) P1Tour=1;
						if (x<=700) P1Tour=0;
						}
					}

				g.setColor(Color.red);
				if (700<=x && x<=890 && y<660){ 
					g.fillRect(700, 100, x-700, 25);
					g.fillRect(x+Size, 100, 890-(x+Size), 25);
					if (Colision(700, 100, x-700, 25) || Colision(x+Size, 100, 890-(x+Size), 25)) initP=0;

					g.fillRect(700, 165, 155, 25);
					if (Colision(700, 165, 155, 25)) initP=0;

					g.fillRect(750, 230, 140, 25);
					if (Colision(750, 230, 140, 25)) initP=0;
			
					g.fillRect(730, 295, 120, 25);
					if (Colision(730, 295, 120, 25)) initP=0;
				
					g.fillRect(700, 360, 30, 25);
					g.fillRect(765, 360, 125, 25);
					if (Colision(700, 360, 30, 25) || Colision(765, 360, 125, 25)) initP=0;

					g.fillRect(700, 425, 100, 25);
					g.fillRect(840, 425, 50, 25);
					if (Colision(700, 425, 100, 25) || Colision(840, 425, 50, 25)) initP=0;
				
					g.fillRect(700, 490, 25, 25);
					g.fillRect(760, 490, 130, 25);
					if (Colision(700, 490, 25, 25) || Colision(760, 490, 130, 25)) initP=0;

					if (y>490){
						if (P1Tour==0){
							g.fillRect(x-Size, 555, Size, 25);
							if (Colision(x-Size, 555, Size, 25)) initP=0;
							}
						if (P1Tour==1){
							g.fillRect(x+Size, 555, Size, 25);
							if (Colision(x+Size, 555, Size, 25)) initP=0;
							}
						}
					}
				}

//*P
			g.setColor(Color.gray);
			g.fillRect(710, P1y, 170, 10); 

			g.setColor(Color.green);
			g.fillRect(P3x, P3y, 30, 800-P3y);
			if (Colision(P3x, P3y+3, 2, 800-P3y+3)) x=P3x-Size;
			if (Colision(P3x+28, P3y+3, 2, 800-P3y+3)) x=P3x+30;

//*Button
			g.setColor(Color.red);
			if (ButtonOn1==0) g.fillRect(870, 590, 30, 30);
			if (ButtonOn1==1) g.fillRect(880, 590, 30, 30);
			if (Colision(870, 600, 30, 30)) ButtonOn1=1;
			
			
//**Goal Level 20
			g.setColor(Color.black);
			g.fillRect(120, 10, 30, 40);
			if (x==120 && y==50){
				Level=21;
				Sustained=LevelColor=0;
				initP=0;
				}
			}
//***Level 21
		else if (Level==21){
			if (initP==0){
				x=120;
				y=50;
				P1y=200;
				initP=1;
				}
//**Suspension
			if (WallStick(98, 0, 2, 800)) Sustained=WallStickedL=1;
			else if (WallStick(800, 0, 2, 800)) Sustained=WallStickedR=1;
			else if (WallStick(163, 0, 2, 729)) Sustained=WallStickedL=1;
			else if (WallStick(240, 0, 2, 800)) Sustained=WallStickedR=1;
			else if (Sustain(240, 50, 15, 2)) Sustained=1;
			else if (Sustain(400, 50, 35, 2)) Sustained=1;
			else if (Sustain(435, 350, 170, 2)) Sustained=1;
			else if (WallStick(530, 0, 2, 285)) Sustained=WallStickedR=1;
			else if (WallStick(533, 0, 2, 285)) Sustained=WallStickedL=1;
			else if (WallStick(603, 353, 2, 447)) Sustained=WallStickedL=1;
			else Sustained=WallStickedL=WallStickedR=0;
//*Green
			g.setColor(Color.green);
			g.fillRect(0, 0, 100, 800);
			if (Colision(0, 0, 100, 800)) x=100;

			g.fillRect(800, 0, 100, 800);
			if (Colision(800, 0, 100, 800)) x=800-Size;

			g.fillRect(160, 0, 5, 730);
			if (Colision(160, 0, 5, 727)) x=165;

			g.fillRect(240, 50, 5, 750);
			if (Colision(240, 53, 5, 747)) x=240-Size;

			g.fillRect(530, 0, 5, 285);
			if (Colision(530, 0, 2, 283)) x=530-Size; 
			if (Colision(533, 0, 2, 283)) x=535;
			if (Colision(530, 283, 5, 4)) Saut=SautCooldown=0;
			
			g.fillRect(600, 350, 5, 450);
			if (Colision(600, 353, 5, 447)) x=605;

			for(int i=0; i<15; i++){
				if (i%2==0) g.setColor(Color.lightGray);
				else {
					g.setColor(Color.green);
					if (WallStick(400, 53+i*50, 2, 47)) Sustained=WallStickedR=1;
					}
				g.fillRect(400, 50+i*50, 20, 50);
				if (Colision(400, 53+i*50, 20, 50)) x=400-Size;
				}

//*Wall gray
			g.setColor(Color.lightGray);
			g.fillRect(150, 0, 10, 730);
			if (Colision(150, 0, 2, 727)) x=150-Size;
			if (Colision(158, 0, 2, 727)) x=160;
			if (Colision(150, 727, 15, 3)) Saut=SautCooldown=0;

			g.fillRect(245, 50, 10, 750);
			if (Colision(245, 53, 10, 747)) x=255;

			g.fillRect(420, 50, 15, 750);
			if (Colision(420, 53, 15, 750)) x=435;

			g.fillRect(435, 350, 165, 450);
//*P
			g.setColor(Color.gray);
			g.fillRect(740, P1y, 60, 15);
			if (Colision(740, P1y+3, 2, 10)) x=740-Size;
			if (Colision(740, P1y+13, 60, 2)) Saut=SautCooldown=0;
			if (Sustain(740, P1y, 60, 2)){
				Sustained=1;
				if (P1y>35){
					P1y-=1;
					y-=1;
					}
				}
			else if (P1y<200) P1y+=1;
//**Goal Level 21
			g.setColor(Color.black);
			g.fillRect(780, 5, 5, 30);
			if (x+Size==795 && y==35){
				Level=22;
				initP=0;
				}
			}
//***Level 22
		else if (Level==22){
			if (initP==0){
				x=775;
				y=55;
				Sustained=1;
				P1Trigger=0;
				Pmove=0;
				P1y=800;
				ButtonOn1=0;
				ButtonOn2=0;
				initP=1;
				}

//**Suspension
			
			if (Sustain(680, P1y, 20, 2)){
				Sustained=1;
				y=800-(int)(450*Math.sin((3.14*Pmove)/1000));
				}
			else if (WallStick(770, 0, 2, -P1y+830)) Sustained=WallStickedR=1;
			else if (Sustain(640, 150, 40, 2)) Sustained=1;
			else if (Sustain(490, 750, 160, 2)) Sustained=1;
			else if (Sustain(450, 150, 40, 2)) Sustained=1;
			else Sustained=WallStickedL=WallStickedR=0;
//*Low Green
			g.setColor(new Color(0, 220, 0));
			g.fillRect(0, 0, 90, 800);
			if (Colision(0, 0, 90, 800)) x=90;

			g.fillRect(810, 0, 90, 800);
			if (Colision(810, 0, 90, 800)) x=810-Size;

			if (P1Trigger==0){
				MotionL=MotionR=0;
				g.fillRect(x, y, Size, 800-y);
				if (y<400){
					x-=2;
					y+=1;
					}
				else if (x<780){
					x+=2;
					y+=1;
					}
				else if (y<800) y+=1;
				else{
					Sustained=0;
					P1Trigger=1;
					}
				}
			if (P1Trigger==1){
				g.fillRect(680, P1y, 20, 800-P1y);
				if (Colision(680, P1y+3, 20, 800-P1y)) x=700;
				P1y=800-(int)(450*Math.sin((3.14*Pmove)/1000));
				if (P1y==800) Pmove=0;
				Pmove+=1;

				for(int i=0; i<15; i++){
					g.fillRect(0-i*20, 600-i*30, P1x, 30);
					if (Colision(0-i*20+P1x-2, 600-i*30, 2, 30)) x=P1x-i*20;
					if (Sustain(0-i*20, 600-i*30, P1x, 2)) Sustained=1;
					}
				if (ButtonOn2==0) P1x=(int)(410*Math.sin((3.14*Pmove)/1000));
				else if (P1x<410) P1x+=1;

				g.fillRect(90, 40, 50, 15);
				if (Colision(90, 43, 50, 12)) x=140;
				if (Sustain(90, 40, 50, 2)) Sustained=1;

			
//*Wall gray
				g.setColor(Color.lightGray);
				g.fillRect(640, 150, 40, 650);
				if (Colision(640, 153, 2, 647)) x=640-Size;
				if (Colision(678, 153, 2, 647)) x=680;

				g.fillRect(490, 750, 160, 50);

				g.fillRect(450, 150, 40, 650);
				if (Colision(450, 153, 2, 647)) x=450-Size;
				if (Colision(488, 153, 2, 647)) x=490;
//*Button
				g.setColor(Color.red);
				if (ButtonOn1==0) g.fillRect(470, 710, 30, 30);
				if (ButtonOn1==1) g.fillRect(460, 710, 30, 30);
				if (Colision(470, 710, 30, 30) && ButtonOn1==0) ButtonOn1=1;

				if (ButtonOn2==0) g.fillRect(70, 5, 30, 30);
				if (ButtonOn2==1) g.fillRect(60, 5, 30, 30);
				if (Colision(70, 5, 30, 30) && ButtonOn2==0) ButtonOn2=1;
//*Dead Red
				if (ButtonOn2==0){
					g.fillRect(90, 130, 330, 20);
					if (Colision(90, 130, 330, 20)) initP=0;
					}
				

//*Sticky green
				g.setColor(Color.green);
				g.fillRect(770, 0, 10, -P1y+820);
				if (Colision(770, 0, 2, -P1y+820)) x=770-Size;
				
				if (ButtonOn1==1){
					for(int i=0; i<4; i++){
						g.fillRect(640, 200+i*150, 5, 40);
						if (WallStick(640, 200+i*150, 5, 40)) Sustained=WallStickedR=1;
						}
					for(int i=0; i<1; i++){
						g.fillRect(485, 150+i*150, 5, 40);
						if (WallStick(485, 150+i*150, 5, 40)) Sustained=WallStickedL=1;
						}
					}
				g.fillRect(420, 50, 30, 100);
				if (Colision(420, 53, 2, 97)) x=420-Size;
				if (Colision(448, 53, 2, 97)) x=450;
				if (WallStick(420, 53, 2, 97)) Sustained=WallStickedR=1;
				if (WallStick(448, 53, 2, 97)) Sustained=WallStickedL=1;
				if (Sustain(420, 50, 30, 2)) Sustained=1;

				g.fillRect(340, 20, 20, 60);
				if (Colision(340, 23, 2, 57)) x=340-Size;
				if (Colision(358, 23, 2, 57)) x=360;
				if (WallStick(340, 23, 2, 57)) Sustained=WallStickedR=1;
				if (WallStick(358, 23, 2, 57)) Sustained=WallStickedL=1;
				if (Sustain(340, 20, 20, 2)) Sustained=1;

				g.fillRect(215, 20, 20, 60);
				if (Colision(215, 23, 2, 57)) x=215-Size;
				if (Colision(233, 23, 2, 57)) x=235;
				if (WallStick(215, 23, 2, 57)) Sustained=WallStickedR=1;
				if (WallStick(233, 23, 2, 57)) Sustained=WallStickedL=1;
				if (Sustain(215, 20, 20, 2)) Sustained=1;
//**Goal Level 22
				g.setColor(Color.black);
				g.fillRect(150, 750, 25, 50);
				if (x==150 && y==800){
					Level=23;
					initP=0;
					}						
				}
			}
//***Level 23
		else if (Level==23){
			if (initP==0){
				x=150;
				y=800;
				P1y=700;
				P1Tour=0;
				P2y=600;
				P2Tour=0;
				P3y=450;
				P3Tour=0;
				ButtonOn1=0;
				ButtonOn2=0;
				Pmove=785; //Py
				initP=1;
				}
//**Suspension
			if (Sustain(180, P1y, 20, 2)){
				Sustained=1;
				if (P1Tour==0) y-=1;
				if (P1Tour==1) y+=1;
				}
			else if (WallStick(190, P1y+2, 2, 98)){
				Sustained=WallStickedL=1;
				if (P1Tour==0) y-=1;
				if (P1Tour==1) y+=1;
				}
			else if (WallStick(208, P1y+2, 2, 98)){
				Sustained=WallStickedR=1;
				if (P1Tour==0) y-=1;
				if (P1Tour==1) y+=1;
				}
			else if (Sustain(350, P2y, 20, 2)){
				Sustained=1;
				if (P2Tour==0) y-=1;
				if (P2Tour==1) y+=1;
				}
			else if (WallStick(350, P2y+2, 2, 58)){
				Sustained=WallStickedL=1;
				if (P2Tour==0) y-=1;
				if (P2Tour==1) y+=1;
				}
			else if (WallStick(368, P2y+2, 2, 58)){
				Sustained=WallStickedR=1;
				if (P2Tour==0) y-=1;
				if (P2Tour==1) y+=1;
				}
			else if (Sustain(500, P3y, 20, 2)){
				Sustained=1;
				if (P3Tour==0) y-=1;
				if (P3Tour==1) y+=1;
				}
			else if (WallStick(500, P3y+2, 2, 28)){
				Sustained=WallStickedL=1;
				if (P3Tour==0) y-=1;
				if (P3Tour==1) y+=1;
				}
			else if (WallStick(518, P3y+2, 2, 28)){
				Sustained=WallStickedR=1;
				if (P3Tour==0) y-=1;
				if (P3Tour==1) y+=1;
				}
			else if (Sustain(680, 120, 100, 2)) Sustained=1;
			else if (Sustain(750, 70, 70, 2)) Sustained=1;
			else if (WallStick(668, 150, 2, 550)) Sustained=WallStickedL=1;
			else if (WallStick(770, 150, 2, 550)) Sustained=WallStickedR=1;
			else if (Sustain(780, Pmove, 40, 2) && ButtonOn2==1){
				Sustained=1;
				if (Pmove>120){
					Pmove-=1;
					y-=1;
					}
				}
			else Sustained=WallStickedR=WallStickedL=0;
//*Low Odd Green
			g.setColor(new Color(10, 180, 20));
			g.fillRect(0, 0, 80, 800);
			if (Colision(0, 0, 80, 800)) x=80;

			g.fillRect(820, 0, 80, 800);
			if (Colision(820, 0, 80, 800)) x=820-Size;

			g.fillRect(660, 0, 20, 150);
			if (Colision(660, 0, 2, 148)) x=660-Size;
			if (Colision(678, 0, 2, 148)) x=680;
			g.fillRect(680, 120, 100, 30);
			if (Colision(660, 148, 120, 2)) y=150+Size;
			if (Colision(680, 120, 2, 30) && Sens==2) x=680-Size;

//*Sticky green
			g.setColor(Color.green);
			g.fillRect(190, P1y, 20, 100);
			if (Colision(192, P1y+98, 16, 2)) y=P1y+100+Size;
			if (Colision(190, P1y+2, 2, 96)) x=190-Size;
			if (Colision(208, P1y+2, 2, 96)) x=210;
			if (P1Tour==0) P1y-=1;
			if (P1Tour==1) P1y+=1;
			if (P1y==700) P1Tour=0;
			if (P1y==0) P1Tour=1;

			g.fillRect(350, P2y, 20, 60);
			if (Colision(350, P2y+2, 2, 58) && Sens==2) x=350-Size;
			if (Colision(368, P2y+2, 2, 58) && Sens==0) x=370;
			if (P2Tour==0) P2y-=1;
			if (P2Tour==1) P2y+=1;
			if (P2y==600) P2Tour=0;
			if (P2y==0) P2Tour=1;

			g.fillRect(500, P3y, 20, 30);
			if (Colision(500, P3y+2, 2, 28) && Sens==2) x=500-Size;
			if (Colision(518, P3y+2, 2, 28) && Sens==0) x=520;
			if (P3Tour==0) P3y-=1;
			if (P3Tour==1) P3y+=1;
			if (P3y==450) P3Tour=0;
			if (P3y==0) P3Tour=1;

//*Dead Red
			g.setColor(Color.red);
			g.fillRect(270, 800-P1y-300, 20, 100);
			g.fillRect(270, 800-P1y-600, 20, 100);
			if (Colision(270, 800-P1y-300, 20, 100) || Colision(270, 800-P1y-600, 20, 100)) initP=0;

			g.fillRect(400, 800-P2y-175, 20, 60);
			g.fillRect(400, 800-P2y-350, 20, 60);
			g.fillRect(400, 800-P2y-500, 20, 60);
			if (Colision(400, 800-P2y-175, 20, 60) || Colision(400, 800-P2y-350, 20, 60) || Colision(400, 800-P2y-500, 20, 60)) initP=0;


			for (int i=1; i<6; i++){
				g.fillRect(600, 800-P3y-160*i, 20, 30);
				if (Colision(600, 800-P3y-160*i, 20, 30)) initP=0;
				}

//*Red Button
			if (ButtonOn2==0 && ButtonOn1==3) g.fillRect(712, 140, 15, 20);
			if (Colision(712, 140, 15, 20) && ButtonOn2==0 && ButtonOn1==3) ButtonOn2=1;
			if (ButtonOn2==1) g.fillRect(712, 130, 15, 20);

			if (ButtonOn1!=0) g.fillRect(660, 125, 15, 20);
			if (ButtonOn1==0){
				g.fillRect(655, 125, 15, 20);
				if (Colision(655, 125, 15, 20)){
					ButtonOn1=1;
					P1Tour=P2Tour=P3Tour=3;
					}
				g.fillRect(660, 150, 160, 650);
				if (Colision(660, 150, 160, 650)) initP=0;
				g.fillRect(210, 700, 450, 100);
				if (Colision(210, 700, 450, 100)) initP=0;
				}
			else if (ButtonOn1==1 && x>660) ButtonOn1=2;
			else if (ButtonOn1>=2){
				g.setColor(new Color(10, 180, 20));
				g.fillRect(660, 150, 10, 550);
				if (Colision(668, 150, 2, 546)) x=670;
				if (Colision(660, 698, 10, 2)) y=700+Size;

				g.fillRect(770, 150, 10, 550);
				if (Colision(770, 150, 2, 546) && Sens==2) x=770-Size;
				if (Colision(778, 150, 2, 546) && Sens==0) x=780;
				if (Colision(770, 698, 10, 2)) y=700+Size;

				g.fillRect(210, 690, 450, 10);
				if (Colision(210, 698, 450, 2)) y=700+Size;

				g.setColor(Color.green);
				g.fillRect(668, 150, 2, 550);
				g.fillRect(770, 150, 2, 550);

				if (y==800) ButtonOn1=3;
				if (ButtonOn1==3){
					g.setColor(Color.red);
					for(int i=0; i<5; i++){
						g.fillRect(670, 200+100*i, 5, 5);
						if (Colision(670, 200+100*i, 5, 5)) initP=0;
						if (i>0){
							g.fillRect(765, 250+100*i, 5, 5);
							if (Colision(765, 250+100*i, 5, 5)) initP=0;
							}
						}
					}
				}
//*P
			if (ButtonOn2==1){
				g.setColor(Color.gray);
				g.fillRect(780, Pmove, 40, 15);	
				if (Colision(780, Pmove+2, 2, 13) && Sens==2) x=780-Size;
				}

//**Goal Level 23
			g.setColor(new Color(10, 180, 20));
			g.fillRect(750, 70, 70, 20);
			if (Colision(752, 88, 68, 2)) y=90+Size;
			if (Colision(750, 70, 2, 20) && Sens==2) x=750-Size;
			g.setColor(Color.black);
			g.fillRect(790, 20, 20, 50);
			if (x==790 && y==70){
				Level=24;
				initP=0;
				}
			}
//***Level 24
		else if (Level==24){
			if (initP==0){
				initP=1;
				x=790;
				y=70;
				P1x=760;
				P1y=75;
				P2x=70;
				P2Tour=0;
				P3x=70;
				P3Tour=0;
				P2y=290;
				ButtonOn1=0;
				}

			if (P1x+60<170 && ButtonOn1==0) ButtonOn1=1;
//*Colourless Low Green
			g.setColor(new Color(25, 155, 10));
			g.fillRect(0, 0, 70, 800);
			if (Colision(65, 0, 5, 800)) x=70;		
	
			g.fillRect(830, 0, 70, 800);			
			if (Colision(830, 0, 2, 800)) x=830-Size;

			g.fillRect(170, 100, 660, 40);
			if (Colision(170, 100, 2, 40) && Sens==2) x=170-Size;

			g.fillRect(70, 200, 600, 30);
			if (Colision(798, 200, 2, 30) && Sens==0) x=800;
			ColisionAll(70, 200, 600, 30);

			g.fillRect(P1x, P1y, 60, 20);
			if (Colision(P1x, P1y+2, 2, 18) && Sens==2) x=P1x-Size;
			if (Colision(P1x+58, P1y+2, 2, 18) && Sens==0) x=P1x+60;
			g.setColor(Color.blue);
			g.fillRect(P1x, P1y, 2, 20);
			g.fillRect(P1x+58, P1y, 2, 20);

//*Sticky green
			g.setColor(Color.green);
			g.fillRect(70, 0, 760, 20);
			if (Colision(70, 18, 760, 2)) y=20+Size-2;

			g.fillRect(170, 140, 660, 10);
			if (Colision(170, 148, 660, 2)) y=150+Size-2;

//*Line
			if (ButtonOn1<3){
				g.setColor(Color.orange);
				g.drawLine(830, 230, 70, 750);
				if (LineOn(830, 230, 70, 750) && x>70){
					Slide(830, 230, 70, 750);
					x-=1;
					}
				}

//*Dead Red
			g.setColor(Color.red);
			g.fillRect(170, 100, 660, 5);
			if (Colision(170, 100, 660, 5)) initP=0;

			g.fillRect(P2x, 45, 100, 20);
			if (Colision(P2x, 45, 100, 20)) initP=0;
			if (P2Tour==0) P2x+=1;
			if (P2Tour==1) P2x-=1;
			if (P2x==70) P2Tour=0;
			if (P2x+100==830) P2Tour=1;

			g.fillRect(P3x, 178, 50, 15);
			if (Colision(P3x, 178, 50, 15)) initP=0;
			if (P3Tour==0) P3x+=2;
			if (P3Tour==1) P3x-=2;
			if (P3x==70) P3Tour=0;
			if (P3x+50==830) P3Tour=1;
			
			g.fillRect(70, 200, 600, 5);
			if (Colision(70, 200, 600, 5)) initP=0;

//*Red Button
			if (ButtonOn1<3) g.fillRect(825, 775, 15, 15);
			if (Colision(825, 775, 15, 15) && ButtonOn1==2) ButtonOn1=P2Tour=P3Tour=3;
			if (ButtonOn1>=3){
				if (ButtonOn1<5 || ButtonOn1>=6) g.fillRect(830, 775, 15, 15);
				if (ButtonOn1==5){
					g.fillRect(825, 775, 15, 15);
					if (Colision(825, 775, 15, 15)) ButtonOn1=6;
					}
				if (ButtonOn1==3) g.fillRect(605, 220, 15, 15);
				if (Colision(605, 220, 15, 15)) ButtonOn1=4;
				for(int i=0; i<4; i++){
					g.fillRect(575, 300+i*100, 5, 5);
					if (Colision(575, 300+i*100, 5, 5)) initP=0;
					g.fillRect(650, 250+i*100, 5, 5);
					if (Colision(650, 250+i*100, 5, 5)) initP=0;
					}

				g.setColor(new Color(25, 155, 10));
				g.fillRect(655, 230, 15, 500);
				ColisionAll(655, 230, 15, 500);
				g.fillRect(560, 230, 15, 500);
				ColisionAll(560, 230, 15, 500);

				g.fillRect(150, 750, 100, 20);
				ColisionAll(150, 750, 100, 20);

				if (ButtonOn1==3){
					g.fillRect(560, 730, 15, 70);
					ColisionAll(560, 730, 15, 70);
					}
				g.setColor(Color.green);
				g.fillRect(570, 230, 5, 500);
				g.fillRect(655, 230, 5, 500);

				if (ButtonOn1>=4){	
					g.setColor(Color.red);
					g.fillRect(605, 215, 15, 15);
					if (ButtonOn1==4) g.fillRect(345, 220, 15, 15);
					if (ButtonOn1>=5) g.fillRect(345, 215, 15, 15);
					if (Colision(345, 220, 15, 15)) ButtonOn1=5;

					g.fillRect(335, P2y, 20, 60);
					if (Colision(335, P2y, 20, 60)) initP=0;
					if (P2Tour==3) P2y+=1;
					else if (P2Tour==4) P2y-=1;
					if (P2y==730) P2Tour=4;
					else if (P2y==240) P2Tour=3;

					g.setColor(new Color(25, 155, 10));
					if (ButtonOn1<6){
						g.fillRect(280, 230, 15, 50);
						ColisionAll(280, 230, 15, 50);
						}
					g.fillRect(280, 280, 15, 520);
					ColisionAll(280, 280, 15, 520);
					g.fillRect(395, 230, 15, 500);
					ColisionAll(395, 230, 15, 500);
				
					g.setColor(Color.green);
					if (ButtonOn1<6) g.fillRect(290, 230, 5, 50);
					g.fillRect(290, 280, 5, 450);
					g.fillRect(395, 230, 5, 500);
					}
				}
//**Suspension
			if (Sustain(P1x, P1y, 60, 2)){
				Sustained=1;
				if (x<P1x && P1x+2<x+Size && 70<P1x && (ButtonOn1==0 || ButtonOn1==2)){
					P1x-=1;
					x-=1;
					}
				if (x<P1x+58 && P1x+60<x+Size && P1x<830 && (ButtonOn1==0 || ButtonOn1==2)){
					P1x+=1;
					x+=1;
					}
				if (ButtonOn1==1){
					P1y+=1;
					y+=1;
					if (P1y+20==200) ButtonOn1=2;
					}
				}
			else if (Colision(70, 18, 760, 2) && Down==0){
				Sustained=1;
				Motion=0;
				}
			else if (Colision(170, 148, 660, 2) && Down==0) Sustained=1;
			else if (WallStick(573, 230, 2, 500)) Sustained=WallStickedL=1;
			else if (WallStick(655, 230, 2, 500)) Sustained=WallStickedR=1;
			else if (WallStick(293, 230, 2, 50) && ButtonOn1<6) Sustained=WallStickedL=1;
			else if (WallStick(293, 280, 2, 450)) Sustained=WallStickedL=1;
			else if (WallStick(395, 230, 2, 500)) Sustained=WallStickedR=1;
			else if (Sustain(280, 280, 15, 2)) Sustained=1;
			else if (Sustain(150, 750, 100, 2) && ButtonOn1==6) Sustained=1;
			else{
				Sustained=WallStickedL=WallStickedR=0;
				Motion=1;
				}
//***Goal Level 24
			if (ButtonOn1==6){
				g.setColor(Color.black);
				g.fillRect(160, 700, 10, 50);
				if (x==160 && y==750){
					Level=25;
					initP=0;
					}
				}
			}
//***Level 25
		else if (Level==25){
			if (initP==0){
				x=160;
				y=750;
				P1x=780;
				P2x=140; //y35
				P3x=220;
				P1y=400; //x600
				P2y=500; //x410
				P3y=300;
				P1Tour=0; //Move on P
				P2Tour=0; //y de P3y
				P3Tour=0;
				Pmove=0;
				ButtonOn1=0;
				LevelColor=1;
				initP=1;
				}
//*low green slightly yellow
			g.setColor(new Color(45, 170, 10));
			g.fillRect(0, 0, 60, 800);
			ColisionAll(0,-50, 60, 850);

			g.fillRect(840, 0, 60, 800);
			ColisionAll(840, -50, 60, 850);

			if (ButtonOn1==0){
				g.fillRect(60, 750, 700, 20);
				ColisionAll(60, 750, 700, 20);
				}
//*P
			g.setColor(Color.gray);
			g.fillRect(P1x, 798, 40, 2);
			ColisionAll(P1x, 798, 40, 2);
			if (Colision(P1x, 798-1, 40, 2) && ButtonOn1==0 && P1x>60){
				P1x-=1;
				x-=1;
				}
			else if (Colision(P1x, 798-1, 40, 2) && ButtonOn1==1 && P1x<780){
				P1x+=1;
				x+=1;
				}
//*Red Button
			g.setColor(Color.red);
			if (ButtonOn1==0) g.fillRect(50, 780, 15, 15);
			if (Colision(50, 780, 15, 15)) ButtonOn1=1;
			if (ButtonOn1==1){
				g.fillRect(45, 780, 15, 15);

				g.setColor(new Color(45, 170, 10));
				g.fillRect(760, 50, 20, 700);
				ColisionAll(760, 50, 20, 700);

				g.fillRect(500, 500, 20, 20);
				ColisionAll(500, 500, 20, 20);

//*Sticky green
				g.setColor(Color.green);


				P2x=300+(int)(150*Math.sin((Pmove*3.14)/400));
				P1y=400+(int)(250*Math.sin((Pmove*3.14)/250));
				P2y=500+(int)(150*Math.sin((Pmove*3.14)/250));
				P3x=220+(int)(100*Math.cos((Pmove*3.14)/300));
				P3y=300+(int)(100*Math.sin((Pmove*3.14)/300));

				g.fillRect(55, 50, 5, 750);

				g.fillRect(840, 50, 5, 750);

				g.fillRect(775, 50, 5, 700);

				g.fillRect(P2x, 35, 80, 15);
				ColisionALL(P2x, 35, 80, 15);
				//WallStickAll(P2x, 35, 80, 15);

				g.fillRect(P3x, P3y, 50, 50);
				ColisionALL(P3x, P3y, 50, 50);

				g.fillRect(600, P1y, 30, 50);
				ColisionALL(600, P1y, 30, 50);

				g.fillRect(410, P2y, 30, 50);
				ColisionALL(410, P2y, 30, 50);
				}

//**Suspension
			if (Sustain(60, 750, 700, 2) && ButtonOn1==0) Sustained=1;
			else if (Sustain(P1x, 798, 40, 2)) Sustained=1;
			else if (Sustain(760, 50, 20, 2)) Sustained=1;
			else if (WallStick(58, 50, 2, 750)) Sustained=WallStickedL=1;
			else if (WallStick(840, 50, 2, 750)) Sustained=WallStickedR=1;
			else if (WallStick(778, 50, 2, 700)) Sustained=WallStickedL=1;

			else if (Sustain(P3x, P3y, 50, 3)){
				Sustained=1;
				if (P1Tour==0) P1Tour=x-P3x;
				x=220+(int)(100*Math.cos((Pmove*3.14)/300))+P1Tour;
				y=300+(int)(100*Math.sin((Pmove*3.14)/300));
				if (MotionL==1) P1Tour-=1;
				if (MotionR==1) P1Tour+=1;
				}
			else if (WallStick(P3x, P3y+3, 2, 47-3)){
				Sustained=WallStickedL=1;
				if (P1Tour==0) P1Tour=y-P3y;
				x=220+(int)(100*Math.cos((Pmove*3.14)/300))-Size;
				y=300+(int)(100*Math.sin((Pmove*3.14)/300))+P1Tour;
				}
			else if (WallStick(P3x+48, P3y+3, 2, 47-3)){
				Sustained=WallStickedR=1;
				if (P1Tour==0) P1Tour=y-P3y;
				x=220+(int)(100*Math.cos((Pmove*3.14)/300))+50;
				y=300+(int)(100*Math.sin((Pmove*3.14)/300))+P1Tour;
				}
			else if (Colision(P3x, P3y+47, 50, 3)){
				Sustained=1;
				if (P1Tour==0) P1Tour=x-P3x;
				x=220+(int)(100*Math.cos((Pmove*3.14)/300))+P1Tour;
				y=300+(int)(100*Math.sin((Pmove*3.14)/300))+50+Size-1;
				//y-=1;
				}

			else if (Sustain(600, P1y, 30, 2)){
				Sustained=1;
				y=400+(int)(250*Math.sin((Pmove*3.14)/250));
				}
			else if (WallStick(600, P1y+2, 2, 48-2)){
				Sustained=WallStickedR=1;
				if (P1Tour==0) P1Tour=y-P1y;
				y=P1Tour+400+(int)(250*Math.sin((Pmove*3.14)/250));
				}
			else if (WallStick(628, P1y+2, 2, 48-2)){
				Sustained=WallStickedL=1;
				if (P1Tour==0) P1Tour=y-P1y;
				y=P1Tour+400+(int)(250*Math.sin((Pmove*3.14)/250));
				}
			else if (Colision(600, P1y+48, 30, 2) && Down==0){
				Sustained=1;
				y=400+(int)(250*Math.sin((Pmove*3.14)/250))+50+Size;
				}

			else if (Sustain(410, P2y, 30, 2)){
				Sustained=1;
				y=500+(int)(150*Math.sin((Pmove*3.14)/250));
				}
			else if (WallStick(410, P2y, 2, 50)){
				Sustained=WallStickedR=1;
				if (P1Tour==0) P1Tour=y-P2y;
				y=P1Tour+500+(int)(150*Math.sin((Pmove*3.14)/250));
				}
			else if (WallStick(438, P2y, 2, 50)){
				Sustained=WallStickedL=1;
				if (P1Tour==0) P1Tour=y-P2y;
				y=P1Tour+500+(int)(150*Math.sin((Pmove*3.14)/250));
				}
			else if (Colision(410, P2y+48, 30, 2) && Down==0){
				Sustained=1;
				Motion=0;
				y=500+(int)(150*Math.sin((Pmove*3.14)/250))+48+Size;
				}
			else if (Sustain(P2x, 35, 80, 2)){
				if (P1Tour==0) P1Tour=x-P2x;
				x=300+(int)(150*Math.sin((Pmove*3.14)/400))+P1Tour;
				if (MotionL==1) P1Tour-=1;
				if (MotionR==1) P1Tour+=1;
				}
			else if (WallStick(P2x, 35, 2, 15-3)){
				//Sustained=WallStickedR=1;
				//if (P1Tour==0) P1Tour=y-35;
				x=300+(int)(150*Math.sin((Pmove*3.14)/400))-Size;
				//y=35+P1Tour;
				}
			else if (WallStick(P2x+78, 35, 2, 15-3)){
				//Sustained=WallStickedL=1;
				//if (P1Tour==0) P1Tour=y-35;
				x=300+(int)(150*Math.sin((Pmove*3.14)/400))+80;
				//y=35+P1Tour;
				}
			else if (Colision(P2x, 47, 80, 3) && Down==0){
				Sustained=1;
				//Motion=0;
				WallStickAll(P2x, 35, 80, 15);
				if (P1Tour==0) P1Tour=x-P2x;
				x=P1Tour+300+(int)(150*Math.sin((Pmove*3.14)/400));
				//y=50+Size;
				//y-=1;
				}
			else if (Sustain(500, 500, 20, 2)) Sustained=1;
			else{
				Sustained=WallStickedL=WallStickedR=P1Tour=0;
				Motion=1;
				}
				Pmove+=1;
				WallStickAll(P2x, 35, 80, 15);
				WallStickAll(P3x, P3y, 50, 50);
				WallStickAll(600, P1y, 30, 50);
				WallStickAll(410, P2y, 30, 50);


//***Goal Level 25
			g.setColor(Color.black);
			g.fillRect(500, 50, 10, 40);
			if (x==500 && y-Size==50){
				Level=26;
				initP=0;
				LevelColor=0;
				} 
			}
//***Level 26
		else if (Level==26){
			if (initP==0){
				x=500;
				y=50+Size;
				ButtonOn1=0; //phase
				P1x=0;
				P1Tour=0;
				P2x=75;
				P2y=810;//x red, y=345
				P2Tour=1;
				P1y=250; //y wave sinus, y de P3y
				P3y=300; //x red
				P3Tour=0;
				Pmove=0;
				initP=1;
				}
//*light green slightly yellow
			g.setColor(new Color(85, 180, 0));
			g.fillRect(0, 0, 50, 800);
			ColisionAll(0,-0, 50, 800);

			g.fillRect(850, 0, 50, 800);
			ColisionALL(850, -100, 50, 900);
	
			g.fillRect(550, 70, 250, 30);
			ColisionALL(550, 70+1, 250, 30);
//*Line
			if (ButtonOn1==0){
				g.setColor(Color.orange);
				g.drawLine(550, 70, 80, 600);
				if (LineOn(550, 70, 80, 600) && x>80){
					Slide(550, 70, 80, 600);
					x-=1;
					}
				if (x==80) ButtonOn1=1;
				}
			if (ButtonOn1==3){
				g.fillRect(800, 70, 50, 30);
				g.setColor(Color.orange);
				g.drawLine(550, 70, 80, 400);
				if (LineOn(550, 70, 80, 400) && x>80){
					Slide(550, 70, 80, 400);
					x-=1;
					}
				if (x==80) ButtonOn1=4;
				}
			if (ButtonOn1==6){
				g.fillRect(800, 70, 50, 30);
				//if (x+Size<550-1 && Sens==0) x+=1;
				g.setColor(Color.orange);
				g.drawLine(550, 70, 80, 0);
				if (LineOn(550, 70, 80, 0) && x>80){
					Slide(550, 70, 80, 0);
					x-=1;
					}
				if (x==80) ButtonOn1=7;
				}
			if (ButtonOn1==9){
				g.setColor(Color.orange);
				g.drawLine(585, 100, 585, 365);
				if (x<585 && 585<x+Size && y>100) y-=2;
				}
//*Phase 1
			if (ButtonOn1>=1){
				g.setColor(new Color(85, 180, 0));
				if (ButtonOn1==1){
					g.fillRect(55, 0, 10, 800);
					ColisionALL(55, 0, 10, 800);

					g.fillRect(110, 0, 10, 650);
					ColisionALL(110, 0, 10, 650);
					}
				if (ButtonOn1==1 && y==800) ButtonOn1=2;

				if (ButtonOn1>=2){
					g.fillRect(50, 650, 750, 10);
					ColisionALL(50, 650, 750, 10);
					}
				g.fillRect(120, 780, 20, 20);
				ColisionALL(120, 780, 20, 20);

				g.fillRect(320, 770, 15, 20);
				ColisionALL(320, 770, 15, 20);

				g.fillRect(430, 770, 20, 20);
				ColisionALL(430, 770, 20, 20);

				g.fillRect(540, 740, 15, 10);
				ColisionALL(540, 740, 15, 10);

				g.fillRect(620, 710, 15, 10);
				ColisionALL(620, 710, 15, 10);

				g.fillRect(700, 780, 5, 10);
				ColisionALL(700, 780, 5, 10);

				g.fillRect(800, 780, 10, 20);
				ColisionALL(800, 780, 10, 20);

				if (ButtonOn1==2){
					g.fillRect(780, 100, 20, 550);
					ColisionALL(780, 100, 20, 550);
					g.setColor(Color.green);
					g.fillRect(795, 100, 5, 550);
					//WallStickAll(795, 100, 5, 550);
					}

				g.setColor(Color.green);
				g.fillRect(160, 750, 20, 40);
				//WallStickAll(160, 750, 20, 40);
				ColisionALL(160, 750, 20, 40);

				g.fillRect(240, 700, 20, 30);
				//WallStickAll(240, 700, 20, 30);
				ColisionALL(240, 700, 20, 30);

				g.fillRect(400, 660, 10, 40);
				//WallStickAll(400, 660, 10, 40);
				ColisionALL(400, 660, 10, 40);
				
				g.fillRect(750, 750, 10, 5);
				//WallStickAll(750, 750, 10, 5);
				ColisionALL(750, 750, 10, 5);
	
				g.fillRect(850, 0, 5, 800);
				//WallStickAll(850, 0, 5, 800);

				g.setColor(Color.red);
				g.fillRect(140, 790, 660, 10);
				//if (Colision(140, 790, 660, 10)) initP=0;
				if (y==71 && ButtonOn1==2) ButtonOn1=3;
				else if (y==71 && ButtonOn1==5) ButtonOn1=6;
				}
			if (ButtonOn1==3 || ButtonOn1==6){
				g.setColor(new Color(85, 180, 0));
				g.fillRect(800, 70, 50, 30);
				ColisionALL(800, 70, 50, 30);
				}
//*Phase 4
			if (ButtonOn1>=4){
				g.setColor(new Color(85, 180, 0));
				g.fillRect(800, 650, 50, 10);
 				ColisionALL(800, 650, 50, 10);
				if (ButtonOn1==4){
					g.fillRect(55, 0, 10, 650);
					ColisionALL(55, 0, 10, 650);

					g.fillRect(110, 0, 10, 400);
					ColisionALL(110, 0, 10, 400);
					}
				if (y==650 && ButtonOn1==4) ButtonOn1=5;
				if (ButtonOn1>=5){
					g.fillRect(50, 400, 750, 10);
					ColisionALL(50, 400, 750, 10);
					}
				g.fillRect(120, 630, 20, 20);
				ColisionALL(120, 630, 20, 20);

				g.fillRect(800, 630, 20, 20);
				ColisionALL(800, 630, 20, 20);

				if (ButtonOn1==5){
					g.fillRect(780, 100, 20, 300);
					ColisionALL(780, 100, 20, 300);
					g.setColor(Color.green);
					g.fillRect(795, 100, 5, 300);
					//WallStickAll(795, 100, 5, 300);
					}
				g.setColor(Color.green);
				g.fillRect(150+P1x, 600, 15, 40);
				WallStickAll(150+P1x, 600, 15, 40);
				ColisionALL(150+P1x, 600, 15, 40);

				g.fillRect(250+P1x, 500, 15, 40);
				WallStickAll(250+P1x, 500, 15, 40);
				ColisionALL(250+P1x, 500, 15, 40);

				g.fillRect(350+P1x, 410, 15, 40);
				WallStickAll(350+P1x, 410, 15, 40);
				ColisionALL(350+P1x, 410, 15, 40);

				g.fillRect(550-P1x, 500, 15, 40);
				WallStickAll(550-P1x, 500, 15, 40);
				ColisionALL(550-P1x, 500, 15, 40);

				g.fillRect(650-P1x, 600, 15, 40);
				WallStickAll(650-P1x, 600, 15, 40);
				ColisionALL(650-P1x, 600, 15, 40);

				g.fillRect(690+P1x, 500, 10, 20);
				WallStickAll(690+P1x, 500, 10, 20);
				ColisionALL(690+P1x, 500, 10, 20);
				if (P1Tour==0) P1x+=1;
				if (P1Tour==1) P1x-=1;
				if (P1x==100) P1Tour=1;
				if (P1x==0) P1Tour=0;
				if (Sustain(150+P1x, 600, 15, 4) || Sustain(250+P1x, 500, 15, 4) || Sustain(690+P1x, 500, 10, 2)){
					if (P1Tour==0) x+=1;
					if (P1Tour==1) x-=1;
					}
				else if (Sustain(550-P1x, 500, 15, 4) || Sustain(650-P1x, 600, 15, 4)){
					if (P1Tour==0) x-=1;
					if (P1Tour==1) x+=1;
					}

				g.setColor(Color.red);
				g.fillRect(140, 640, 660, 10);
				//if (Colision(140, 640, 660, 10)) initP=0;
				if (y==71 && ButtonOn1==5) ButtonOn1=6;
				}

//*Phase 7
			if (ButtonOn1>=7){
				g.setColor(new Color(85, 180, 0));
				g.fillRect(800, 650, 50, 10);
				ColisionALL(800, 650, 50, 10);
				if (ButtonOn1==7){
					g.fillRect(55, 0, 10, 400);
					ColisionALL(55, 0, 10, 400);
					g.fillRect(110, 0, 10, 100);
					ColisionALL(110, 0, 10, 100);
					}
				if (ButtonOn1==7 && y==365) ButtonOn1=8;
				g.setColor(new Color(85, 180, 0));
				g.fillRect(800, 400, 50, 10);
				ColisionALL(800, 400, 50, 10);
				if (ButtonOn1>=8){
					g.fillRect(50, 160, 520, 40);
					ColisionALL(50, 160, 520, 40);

					g.fillRect(550, 0, 40, 70);
					ColisionAll(550, 0-100, 40, 70+100);	
					}
				if (ButtonOn1<9){
					g.fillRect(550, 160, 100, 40);
					ColisionALL(550, 160, 100, 40);
					}
				g.fillRect(P2x, 365, 60, 20);
				ColisionALL(P2x, 365, 60, 20);
				g.fillRect(P2x+60, 300, 15, 85);
				ColisionALL(P2x+60, 300, 15, 85);
				g.setColor(Color.blue);
				g.fillRect(P2x+55, 365, 5, 5);
				if (Sustain(P2x+55, 365, 5, 5) && P2x+75<850 && ButtonOn1==8){
					P2x+=1;
					x+=1;
					}
				else if (Sustain(P2x+55, 365, 5, 5) && P2x>570 && ButtonOn1==9){
					P2x-=1;
					x-=1;
					}
				if (ButtonOn1==8){
					g.setColor(Color.green);
					g.fillRect(50, 195, 600, 5);

					g.fillRect(P2x+60, 300, 5, 40);
					}
				
				g.setColor(Color.red);
				g.fillRect(50, 390, 800, 10);
				//if (Colision(50, 390, 800, 10)) initP=0;
				if (ButtonOn1==8){
					g.fillRect(585, 50, 15, 15);
			
					g.fillRect(P2y, 345, 40, 20);
					if (P2Tour==0) P2y+=1;
					if (P2Tour==1) P2y-=1;
					if (P2y+40==P2x+60) P2Tour=1;
					if (P2y==50) P2Tour=0;
					//if (Colision(P2y, 345, 40, 20)) initP=0;

					g.fillRect(P3y, P1y, 20, 10);
					if (P3Tour==0) P3y+=2;
					if (P3Tour==1) P3y-=2;
					if (P3y+20==850) P3Tour=1;
					if (P3y==50) P3Tour=0;
					P1y=250+(int)(20*Math.sin((Pmove*3.14)/100));
					Pmove+=1;
					//if (Colision(P3y, P1y, 20, 10)) initP=0;
					}
				if (Colision(585, 50, 15, 15)) ButtonOn1=9;
				if (ButtonOn1==9) g.fillRect(575, 50, 15, 15);

				}
//***Goal Level 26
			g.setColor(new Color(85, 180, 0));
			g.fillRect(550, 100, 20, 60);
			ColisionALL(550, 100, 20, 60);

			g.fillRect(600, 130, 30, 20);
			ColisionALL(600, 130, 30, 20);

			g.fillRect(630, 100, 20, 60);
			ColisionALL(630, 100, 20, 60);
			g.setColor(Color.black);
			g.fillRect(605, 105, 20, 25);
			if (x==605 && y==130){
				Level=27;
				initP=0;
				}
			if (ButtonOn1>=1){
				
				}
			if (ButtonOn1>=2){
				if (ButtonOn1==2) WallStickAll(795, 100, 5, 550);
				WallStickAll(160, 750, 20, 40);
				WallStickAll(240, 700, 20, 30);
				WallStickAll(400, 660, 10, 40);
				WallStickAll(750, 750, 10, 5);
				WallStickAll(850, 0, 5, 800);				
				}
			if (ButtonOn1>=3){
				
				}
			if (ButtonOn1>=4){
				WallStickAll(150+P1x, 600, 15, 40);
				WallStickAll(250+P1x, 500, 15, 40);
				WallStickAll(350+P1x, 410, 15, 40);
				WallStickAll(550-P1x, 500, 15, 40);
				WallStickAll(650-P1x, 600, 15, 40);
				WallStickAll(690+P1x, 500, 10, 20);
				if (WallStick(150+P1x, 600+2, 2, 38) || WallStick(250+P1x, 500+2, 2, 38)
				|| WallStick(350+P1x, 410+2, 2, 38) || WallStick(690+P1x, 500+2, 2, 38)){
					if (P1Tour==0) x+=1;
					}
				if (WallStick(150+P1x+13, 600+2, 2, 38) || WallStick(250+P1x+13, 500+2, 2, 38)
				|| WallStick(350+P1x+13, 410+2, 2, 38) || WallStick(690+P1x+8, 500+2, 2, 38)){
					if (P1Tour==1) x-=1;
					}
				if (WallStick(150+P1x, 600+2, 2, 38) || WallStick(150+P1x+13, 600+2, 2, 38)
				|| WallStick(250+P1x, 500+2, 2, 38) || WallStick(250+P1x+13, 500+2, 2, 38)
				|| WallStick(350+P1x, 410+2, 2, 38) || WallStick(350+P1x+13, 410+2, 2, 38)
				|| WallStick(690+P1x, 500+2, 2, 38) || WallStick(690+P1x+8, 500+2, 2, 38)){
					//System.out.println("Bon");
					//y-=1;
					//if (P1Tour==0) x+=1;
					//if (P1Tour==1) x-=1;
					}
				if (WallStick(550-P1x, 500+2, 2, 38) || WallStick(550-P1x+13, 500+2, 2, 38)
				|| WallStick(650-P1x, 600+2, 2, 38) || WallStick(650-P1x+13, 600+2, 2, 38)){
					//y-=1;
					//if (P1Tour==0) x-=1;
					//if (P1Tour==1) x+=1;
					}
				}
			if (ButtonOn1>=5){
				if (ButtonOn1==5) WallStickAll(795, 100, 5, 300);
				}
			if (ButtonOn1>=6){
				
				}
			if (ButtonOn1>=7){
				
				}
			if (ButtonOn1>=8){	
				if (ButtonOn1==8){
					WallStickAll(50, 195, 600, 5);
					WallStickAll(P2x+60, 300, 5, 40);
					}
				}
			if (ButtonOn1>=9){
				
				}

			}
//***Level 27
		else if (Level==27){
			if (initP==0){
				x=605;
				y=130;
				ButtonOn1=0; //phase
				P1y=200;
				P1Tour=0;
				initP=1;
				}
//*light green grass yellowish
			g.setColor(new Color(135, 180, 0));
			g.fillRect(0, 0, 40, 800);
			ColisionALL(0,0, 40, 800);

			g.fillRect(860, 0, 40, 800);
			ColisionALL(860, 0, 40, 800);

			g.setColor(Color.green);
			g.fillRect(35, 100, 5, 700);
			WallStickAll(35, 100, 5, 700);
			g.fillRect(860, 100, 5, 700);
			WallStickAll(860, 100, 5, 700);

			g.fillRect(420, 50, 5, 750);
			ColisionALL(420, 50, 5, 750);
			WallStickAll(420, 50+1, 5, 750);
			g.fillRect(475, 50, 5, 750);
			ColisionALL(475, 50, 5, 750);
			WallStickAll(475, 50+1, 5, 750);

			if (ButtonOn1==0){
				g.setColor(new Color(135, 180, 0));
				g.fillRect(250, 500, 60, 60);
				ColisionALL(250, 500, 60, 60);
				g.setColor(Color.red);
				g.fillRect(300, 530, 15, 15);
				if (Colision(300, 530, 15, 15)) ButtonOn1=1;
				}
			else if (ButtonOn1==1){
				g.setColor(new Color(135, 180, 0));
				g.fillRect(520, 600, 80, 30);
				ColisionALL(520, 600, 80, 30);

				g.fillRect(650, 500, 50, 100);
				ColisionALL(650, 500+1, 50, 100);

				g.fillRect(500, 500, 150, 30);
				ColisionALL(500, 500+1, 150, 30);
				g.setColor(Color.red);
				g.fillRect(645, 520, 15, 15);
				if (Colision(645, 520, 15, 15)) ButtonOn1=2;
				}
			else if (ButtonOn1==2){
				g.setColor(new Color(135, 180, 0));
				g.fillRect(180, 50, 70, 40);
				ColisionALL(180, 50, 70, 40);
				g.fillRect(120, 0, 20, 500);
				ColisionALL(120, 0, 20, 500);
				g.fillRect(270, 0, 20, 500);
				ColisionALL(270, 0, 20, 500);
				for(int i=0; i<5; i++){
					g.fillRect(150, 140+100*i, 50, 10);
					ColisionALL(150, 140+100*i, 50, 10);
					g.fillRect(210, 200+100*i, 50, 10);
					ColisionALL(210, 200+100*i, 50, 10);
					}
				g.setColor(Color.green);
				g.fillRect(180, 50, 5, 40);
				WallStickAll(180, 50, 5, 40);
				g.setColor(Color.red);
				g.fillRect(203, 40, 15, 15);
				if (Colision(203, 40, 15, 15)) ButtonOn1=3;
				}
			else if (ButtonOn1==3){
				g.setColor(new Color(135, 180, 0));
				for(int i=0; i<9; i++){
					g.fillRect(500+40*i, 790-i*60, 40, 10);
					ColisionALL(500+40*i, 790-i*60, 40, 10);
					}
				g.setColor(Color.red);
				g.fillRect(855, 280, 15, 15);
				if (Colision(855, 280, 15, 15)) ButtonOn1=4;
				}
			else if (ButtonOn1==4){
				g.setColor(new Color(135, 180, 0));
				g.fillRect(200, P1y, 50, 80);
				ColisionALL(200, P1y, 50, 80);
				if (P1Tour==0) P1y+=1;
				if (P1Tour==1) P1y-=1;
				if (P1y==50) P1Tour=0;
				if (P1y+80==720) P1Tour=1;
				if (Sustain(200, P1y, 50, 80) && P1Tour==1) y-=1;					
				g.setColor(Color.red);
				g.fillRect(240, P1y+30, 15, 15);
				if (Colision(240, P1y+30, 15, 15)) ButtonOn1=5;
				}
			else if (ButtonOn1==5){
				g.setColor(new Color(135, 180, 0));
				for(int i=0; i<4; i++){
					g.setColor(new Color(135, 180, 0));
					g.fillRect(550+i*80, 100, 30, 600-i*100); 
					ColisionALL(550+i*80, 100, 30, 600-i*100);
					g.setColor(new Color(225+i*10, 0 ,0));
					g.fillRect(570+i*80, 650-i*100, 15, 15);
					if (Colision(570+i*80, 650-i*100, 15, 15) && i==3){
						ButtonOn1=6;
						x=830;
						}
					}
				}
			else if (ButtonOn1==6){
				g.setColor(new Color(135, 180, 0));
				g.fillRect(80, 0, 20, 760);
				ColisionALL(80, 0, 20, 760);

				g.fillRect(370, 25, 20, 735);
				ColisionALL(370, 25+1, 20, 735);

				g.fillRect(100, 0, 710, 1);
				ColisionALL(100, 0, 710, 1);

				g.fillRect(370, 25, 140, 2);
				ColisionALL(370, 25+1, 140, 2);

				g.fillRect(510, 25, 20, 735);
				ColisionALL(510, 25+1, 20, 735);

				g.fillRect(810, 0, 20, 760);
				ColisionALL(810, 0, 20, 760);

				g.fillRect(530, 740, 280, 20);
				ColisionALL(530, 740, 280, 20);

				g.setColor(Color.green);
				g.fillRect(180, P1y, 20, 70);
				ColisionALL(180, P1y, 20, 70);
				g.fillRect(300, 650-P1y, 20, 70);
				ColisionALL(300, 650-P1y, 20, 70);
				if (P1Tour==0) P1y+=1;
				if (P1Tour==1) P1y-=1;
				if (P1y==50) P1Tour=0;
				if (P1y+70==700) P1Tour=1;
				if ((Sustain(180, P1y, 20, 7) || Sustain(300, 650-P1y, 20, 7)) && P1Tour==1) y-=1;
				g.fillRect(95, 680, 5, 80);
				WallStickAll(95, 680, 5, 80);
				g.fillRect(370, 600, 5, 80);
				WallStickAll(370, 600, 5, 80);
				g.fillRect(95, 520, 5, 80);
				WallStickAll(95, 520, 5, 80);
				g.fillRect(370, 440, 5, 80);
				WallStickAll(370, 440, 5, 80);
				g.fillRect(95, 360, 5, 80);
				WallStickAll(95, 360, 5, 80);
				g.fillRect(370, 280, 5, 80);
				WallStickAll(370, 280, 5, 80);
				g.fillRect(95, 200, 5, 80);
				WallStickAll(95, 200, 5, 80);
				g.fillRect(370, 120, 5, 80);
				WallStickAll(370, 120, 5, 80);
				g.fillRect(95, 40, 5, 80);
				WallStickAll(95, 40, 5, 80);
				g.setColor(Color.red);
				g.fillRect(805, 730, 15, 15);
				if (Colision(805, 730, 15, 15)) ButtonOn1=7;
				}
//***Goal Level 27
			g.setColor(new Color(135, 180, 0));
			g.fillRect(425, 780, 50, 20);
			ColisionALL(425, 780, 50, 20);
			for(int i=ButtonOn1; i<7; i++){
				g.fillRect(425, 50+i*100, 50, 10);
				ColisionALL(425, 50+i*100, 50, 10);
				}
			g.setColor(Color.black);
			g.fillRect(440, 740, 20, 40);
			if (x==440 && y==780){
				Level=28;
				initP=0;
				}
			//WallStickAll(35, 100, 5, 700);
			//WallStickAll(860, 100, 5, 700);
			}
//***Level 28
		else if (Level==28){
			if (initP==0){
				x=440;
				y=780;
				ButtonOn1=0;
				Time=0;
				TimeOn=0;
				Pmove=P2Tour=0;
				P1y=50;
				P1Tour=0;
				P1x=400;
				initP=1;
				}
//*light greenish yellowish
			for(int i=0; i<10 && Pmove<10; i++){
				for(int j=0; j<10; j++){
					g.setColor(new Color(0, 75+i*10+j*5, 0));
					if (Cube28[i][j]==0) g.fillRect(420+i*36, 420+j*31, 36, 31);
					}
				}
			if (ButtonOn1==5 && Pmove!=10){
				if (TimeOn2==0){
					new Time2().start();
					TimeOn2=1;
					if (Pmove<10) Motion=0;
					Cube28[Pmove][P2Tour]=1;
					P2Tour+=1;
					if (P2Tour==10){
						Pmove+=1;
						P2Tour=0;
						}
					}
				}
			else if (Pmove==10 && Motion==0) Motion=1;
			g.setColor(new Color(150, 200, 0));
			g.fillRect(0,0, 30, 800);
			ColisionAll(0,-50, 30, 870);

			g.fillRect(870, 0, 30, 800);
			ColisionAll(870, -50, 30, 850);
			
			for(int i=0; i<2; i++){
				g.fillRect(400, 400+i*330, 400, 20);
				if (ButtonOn1!=5 && Pmove!=10) g.fillRect(400+i*380, 420, 20, 310);
				else if (i==1) g.fillRect(400+i*380, 420, 20, 310);
				ColisionALL(400, 400+i*330, 400, 20);
				if (ButtonOn1!=5 && Pmove!=10) ColisionALL(400+i*380, 420, 20, 310);
				else if (i==1) ColisionALL(400+i*380, 420, 20, 310);
				}
			//for(int i=0; i<6; i++){
				//g.fillRect(300-i*50, 780-i*40, 30, 15);
				//ColisionALL(300-i*50, 780-i*40, 30, 15);
				//}
			g.setColor(Color.green);
			g.fillRect(795, 400, 5, 350);
			WallStickAll(795, 400, 5, 350);
			if (Pmove!=10){
				WallStickAll(400, 400, 5, 350);
				g.fillRect(400, 400, 5, 350);
				}
			if (ButtonOn1>=1){
				g.setColor(new Color(150, 200, 0));
				if (Pmove!=10){
					g.fillRect(220, 150, 20, 150);
					ColisionALL(220, 150, 20, 150); 
					}
				g.fillRect(240, 280, 630, 20);
				ColisionALL(240, 280, 630, 20);
				if (Sustain(240, 280, 620, 2) && ButtonOn1==1) ButtonOn1=2;

				g.setColor(Color.green);
				g.fillRect(25, 60, 5, 600);
				WallStickAll(25, 60, 5, 600);

				if (ButtonOn1==2){
					g.setColor(Color.green);
					g.fillRect(385, P1y, 15, 40);
					g.fillRect(P1x, 50, 40, 15);
					ColisionALL(385, P1y, 15, 40);
					ColisionALL(P1x, 50, 40, 15);
					WallStickAll(385, P1y, 15, 40);
					WallStickAll(P1x, 50, 40, 15);
					if (Sustain(385, P1y, 15, 2)){
						if (P1Tour==0) y+=1;
						if (P1Tour==1) y-=1;
						}
					if (WallStick(385, P1y, 2, 40) || WallStick(398, P1y, 2, 40)){
						if (P1Tour==0) y+=1;
						if (P1Tour==1) y-=1;
						}
					//if (Colision(385, P1y+38, 15, 3)){
						//if (P1Tour==0) y+=1;
						//if (P1Tour==1) y-=1;
						//}
					if (Sustain(P1x, 50, 40, 2)){
						if (P1Tour==0) x+=2;
						if (P1Tour==1) x-=2;
						}
					if (WallStick(P1x, 50, 2, 15) || WallStick(P1x+38, 50, 2, 15)){
						if (P1Tour==0) x+=2;
						if (P1Tour==1) x-=2;
						}
					if (Colision(P1x, 63, 40, 3)){
						if (P1Tour==0) x+=2;
						if (P1Tour==1) x-=2;
						}						
					if (P1Tour==0){
						P1y+=1;
						P1x+=2;
						}
					if (P1Tour==1){
						P1y-=1;
						P1x-=2;
						}
					if (P1y+40==250) P1Tour=1;
					if (P1y==50) P1Tour=0;
					
					if (Colision(865, 60, 15, 15)) ButtonOn1=3;
					}
				if (ButtonOn1==3 && y==280) ButtonOn1=4;
				if (ButtonOn1==4){
					g.setColor(Color.green);
					g.fillRect(235, 150, 5, 50);
					WallStickAll(235, 150, 5, 50);

					for(int i=0; i<5; i++){
						g.fillRect(300+i*120, 100-i*20, 20, 10);
						ColisionALL(300+i*120, 100-i*20, 20, 10);
						WallStickAll(300+i*120, 100-i*20, 20, 10);
						}
					if (Colision(865, 60, 15, 15)) ButtonOn1=5;
					}
//*Line
				if (ButtonOn1==1){
					g.setColor(Color.orange);
					g.drawLine(30, 50, 500, 200);
					if (LineOn(30, 50, 500, 200) && Down==0 && x<500){
						Slide(30, 50, 500, 200);
						x+=1;
						}
					}
				}

//*Red Button
			g.setColor(Color.red);
			if (ButtonOn1==0) g.fillRect(20, 660, 15, 15);
			if (Colision(20, 660, 15, 15) && ButtonOn1==0) ButtonOn1=1;
			if (ButtonOn1>=1) g.fillRect(15, 660, 15, 15);
			if (ButtonOn1==2) g.fillRect(865, 60, 15, 15);
			if (ButtonOn1==3) g.fillRect(870, 60, 15, 15);
			if (ButtonOn1==4) g.fillRect(865, 60, 15, 15);
			

//***Goal Level 28
			if (Pmove==10){
				g.setColor(Color.black);
				g.fillRect(750, 700, 25, 30);
				if (x==750 && y==730){
					Level=29;
					initP=0;
					}
				}
			}
		try{
			Thread.sleep(1);
			} catch (InterruptedException e) {}
		}
//------------------------------------------------------------------------------------------------
	public void paintChildren(Graphics g){
//***Level 29
		if (Level==29){
			if (initP==0){
				x=750;
				y=730;
				P1x=200;
				P1y=380;
				P2x=655;
				P2y=395;
				P1Tour=0;
				P2Tour=0;
				Pmove=0;
				TimeOn=0;
				Time=0;
				P3y=0; 
				P3Tour=0;
				ButtonOn1=0;
				initP=1;
				}
//*little bright light greeny yellow
			g.setColor(new Color(170, 220, 0));
			g.fillRect(0,0, 20, 800);
			ColisionAll(0,0, 20, 800);
	
			g.fillRect(880, 0, 20, 800);
			ColisionAll(880, 0, 20, 800);

			g.fillRect(655, 0+P3y, 20, 30);
			ColisionALL(655, 0+P3y, 20, 30);
			if (Sustain(655, 0+P3y, 20, 2)){
				if (P3Tour==0) y+=1;
				if (P3Tour==1) y-=1;
				}
			//System.out.println(P3y/5+"\t"+(int)(P3y/5)+"\t"+(int)(Math.floor((P3y/5)))+"\t"+(int)(Math.ceil((P3y/5))));
			g.fillRect(80, 300+(int)(P3y/5),50, 100);
			ColisionALL(80, 300+(int)(P3y/5),50, 100);
			if (Sustain(80, 300+(int)(P3y/5),50, 2)){
				if (P3Tour==0 && y<(int)(P3y/5)) y+=1;
				if (P3Tour==1) y-=1;
				}
			g.fillRect(800, 380-(int)(P3y/2), 40, 120);
			ColisionALL(800, 380-(int)(P3y/2), 40, 120);
			if (Sustain(800, 380-(int)(P3y/2), 40, 2)){
				if (P3Tour==0) y-=1;
				if (P3Tour==1 && y<(int)(P3y/2)) y+=1;
				}
			g.fillRect(280, 400-(int)(P3y/3), 25, 60);
			ColisionALL(280, 400-(int)(P3y/3), 25, 60);
			if (Sustain(280, 400-(int)(P3y/3), 25, 2)){
				if (P3Tour==0) y-=1;
				if (P3Tour==1 && y<(int)(P3y/3)) y+=1;
				}
			if (ButtonOn1==4){
				g.fillRect(900-x, 800-y, 20, 20);
				ColisionALL(900-x, 800-y, 20, 20);
				if (Sustain(900-x, 800-y, 20, 2)){
					//if (P3Tour==0) y-=1;
					//if (P3Tour==1) y+=1;
					}
				}
			if (P3Tour==0) P3y+=1;
			if (P3Tour==1) P3y-=1;
			if (P3y==750) P3Tour=1;
			if (P3y==0) P3Tour=0;

			g.setColor(Color.green);
			for(int i=0; i<6; i++){
				g.fillRect((435+10)-ButtonOn1*2, 40+i*130, (30-10)+ButtonOn1*2, 60);
				ColisionALL((435+10)-ButtonOn1*2, 40+i*130, (30-10)+ButtonOn1*2, 60);
				WallStickAll((435+10)-ButtonOn1*2, 40+i*130, (30-10)+ButtonOn1*2, 60);
				}
//*red dead
			g.setColor(Color.red);
			g.fillRect(P1x, P1y, 50, 40);
			if (Colision(P1x, P1y, 50, 40)) initP=0;
			g.fillRect(P2x, P2y, 20, 10);
			if (Colision(P2x, P2y, 20, 10)) initP=0;

			P1x=200+(int)(170*Math.cos((Pmove*3.14)/(400))) +P1Tour;
			P1y=380+(int)(370*Math.sin((Pmove*3.14)/(400)));
			P2x=655+(int)(150*Math.cos((-Pmove*3.14)/(100))) -P1Tour;
			P2y=395+(int)(100*Math.sin((-Pmove*3.14)/(100)));
			if (ButtonOn1<5) Pmove+=1;
			if (TimeOn==0 && Time<10){
				new Time().start();
				TimeOn=1;
				}
			else if (TimeOn==0 && 10<Time && Time<20 && P1Tour<=450){ 
				new Time().start();
				TimeOn=1;
				}
			if (Time==10 && P1Tour<450){
				P1Tour+=1;
				if (P1Tour==450) Time=11;
				}
			else if (Time==20 && P1Tour>0){
				P1Tour-=1;
				if (P1Tour==0) Time=0;
				}
			//System.out.println(Time);
//*red Button
			if (ButtonOn1==0){
				g.fillRect(650, 7+P3y, 15, 15);
				if (Colision(650, 7+P3y, 15, 15)) ButtonOn1=1;
				}
			else if (ButtonOn1==1){
				g.fillRect(120, 365+(int)(P3y/5), 15, 15);
				if (Colision(120, 365+(int)(P3y/5), 15, 15)) ButtonOn1=2;
				}
			else if (ButtonOn1==2){
				g.fillRect(795, 385-(int)(P3y/2), 15, 15);
				if (Colision(795, 385-(int)(P3y/2), 15, 15)) ButtonOn1=3;
				}
			else if (ButtonOn1==3){
				g.fillRect(275, 425-(int)(P3y/3), 15, 15);
				if (Colision(275, 425-(int)(P3y/3), 15, 15)) ButtonOn1=4;
				}
			else if (ButtonOn1==4){
				g.fillRect(902-x, 802-y, 15, 15);
				if (Colision(900-x-1, 800-y-1, 20+2, 20+2)){
					ButtonOn1=5;
					Time=3000;
					}
				}

//***Goal Level 29
			if (ButtonOn1==5){
				g.setColor(Color.black);
				g.fillRect(435, 5, 30, 35);
				if (x==435 && y==40){
					Level=30;
					initP=0;
					}
				}
			}


//***Level 30
		else if (Level==30){
			if (initP==0){
				x=435;
				y=40;
				ButtonOn1=0;
				ButtonOn2=0; //Trigger green Button
				P1x=10;
				P2x=200;
				P2y=450;
				P2Tour=0;
				Pmove=0;
				Time=0;
				TimeOn=0;
				P1Tour=0; //Suspension x
				P3Tour=0; //Suspension y
				P3y=450; //x 30 P
				P1y=30; //Move of y de last buttonB1 6
				P3x=785; //y 775
				LevelColor=1;
				initP=1;
				}
			if (y==800) initP=0;
//*shy bright sunny yellow
			g.setColor(new Color(210, 235, 0));
			g.fillRect(0,0, 10, 800);
			ColisionAll(0,-100, 10, 800+100);
	
			g.fillRect(890, 0, 10, 800);
			ColisionAll(890, -100, 10, 800+100);

			g.fillRect(390, 0, 25, 50);
			ColisionALL(390, -100, 25, 50+100);

			g.fillRect(485, 0, 25, 50);
			ColisionALL(485, -100, 25, 50+100);

			if (ButtonOn1==0 || ButtonOn2==0){
				g.fillRect(415, 40, 70, 10);
				ColisionALL(415, 40, 70, 10);
				}
			g.setColor(Color.green);
			for(int i=0; i<5; i++){
				if (P1x+i*176+126<890) g.fillRect(P1x+i*176, 80, 126, 20);
				ColisionALL(P1x+i*176, 80, 126, 20);
				WallStickAll(P1x+i*176, 80, 126, 20);
				if (Sustain(P1x+i*176, 80, 126, 2)) x+=1;
				if (WallStick(P1x+i*176+125, 80, 2, 20)) x+=1;
 				if (890<=P1x+i*176+126 && P1x+i*176+126<1016){
					g.fillRect(P1x+i*176, 80, 890-(P1x+i*176), 20);
					ColisionALL(P1x+i*176, 80, 890-(P1x+i*176), 20);
					WallStickAll(P1x+i*176, 80, 890-(P1x+i*176), 20);

					g.fillRect(10, 80, 0+(P1x+i*176+126-890), 20);
					ColisionALL(10, 80, 0+(P1x+i*176+126-890), 20);
					WallStickAll(10, 80, 0+(P1x+i*176+126-890), 20);
					if (Sustain(10, 80, 0+(P1x+i*176+126-890), 2)) x+=1;
					if (WallStick(10+(P1x+i*176+126-890)-2, 80, 2, 20)) x+=1;
					}
				if (P1x+i*176+126==1016-1) P1x=10;
				}
			P1x+=1;
			//if (Down==0) P1x+=1;
			//if (MotionL==1) P1x-=1;
			//if (MotionR==1) P1x+=1;

			g.setColor(new Color(210, 235, 0));
			for(int i=0; i<3; i++){
				g.fillRect(P3x-230*i, 785, 10, 15);
				ColisionALL(P3x-230*i, 785, 10, 15);
				if (Sustain(P3x-230*i, 785, 10, 2)) x-=1;
				
				if (95<P3x-230*i && P3x-230*i<=105){
					g.fillRect(105, 785, (P3x-230*i)+10-105, 15);
					ColisionALL((P3x-230*i)+10, 785, (P3x-230*i)+10-105, 15);
					//if (Sustain((P3x-230*i)+10, 785, (P3x-230*i)+10-105, 15)) x-=1;

					g.fillRect(785+((P3x-230*i)+10-105), 785, -((P3x-230*i)-105), 15);
					if (Sustain(785+((P3x-230*i)+10-105), 785, -((P3x-230*i)-105), 15)) x-=1;
					}
				if (95==P3x-230*i) P3x=785;
				}
			P3x-=1;
			//if (Down==1) P3x-=1;
			//if (MotionL==1) P3x-=1;
			//if (MotionR==1) P3x+=1;


			g.setColor(new Color(210, 235, 0));
			g.fillRect(425, 425, 50, 50);
			ColisionALL(425, 425, 50, 50);
			g.setColor(Color.orange);
			g.drawLine(450, 450, P2x, P2y);

			g.fillOval(P2x-5, P2y-5, 10, 10); 

			if (CircleColision(P2x, P2y, 100-20*P2Tour) && ButtonOn2<2) initP=0;
			else if (CircleColision(P2x, P2y, 100-20*P2Tour) && ButtonOn2==2){
				if (P1Tour==0) P1Tour=P2x-x;
				if (P3Tour==0) P3Tour=P2y-y;
				//Motion=0;
				//if (MotionL==1) P1Tour+=1;
				//if (MotionR==1) P1Tour-=1;
				Sustained=1;
				if (y-Size>P2y && ((x>P2x && MotionR==1) || (x+Size<P2x && MotionL==1) || Down==1)){
					 x=x;
					}
				//if ((y-Size>P2y && x>P2x && MotionR==1) x+=1;
				//else if ((y-Size>P2y && x+Size<P2x && MotionL==1) x-=1;
				else x=450-(int)(225*Math.cos((Pmove*3.14)/(400-40*P2Tour))) -P1Tour;
				y=450+(int)(225*Math.sin((Pmove*3.14)/(400-40*P2Tour))) -P3Tour;
				y-=1;
				}
			else{
				//Motion=1;
				P1Tour=P3Tour=Sustained=0;
				}

			if (ButtonOn2<=1) g.setColor(Color.red);
			else if (ButtonOn2==2) g.setColor(Color.green);
			g.fillOval(P2x-100+20*P2Tour, P2y-100+20*P2Tour, 200-40*P2Tour, 200-40*P2Tour);
			P2x=450-(int)(225*Math.cos((Pmove*3.14)/(400-40*P2Tour)));
			P2y=450+(int)(225*Math.sin((Pmove*3.14)/(400-40*P2Tour)));
			Pmove+=1;
			//if (Down==1) Pmove+=1;

			if (ButtonOn1>=5 && P1y>0) P1y-=1;
			for(int i=0; i<P1y; i++){
				for(int j=0; j<P1y; j++){
					g.setColor(new Color(225-i*6, i*5, j*4));
					int R=20;
					int A1=450-i;
					int A2=450+i;
					int B1=450-j;
					int B2=450+j;
					int k=2;
					if (i%k==0 && j%k==0 && (A1-450)*(A1-450) + (B1-450)*(B1-450) <= R*R){
						g.drawLine(450, 450, A1, B1);
						g.drawLine(450, 450, 900-A1, 900-B1);
						}
					if (i%k==0 && j%k==0 && (A2-450)*(A2-450) + (B1-450)*(B1-450) <= R*R){
						g.drawLine(450, 450, A2, B1);
						g.drawLine(450, 450, 900-A2, 900-B1);
						}
					}
				}
//*Red Button
			g.setColor(Color.red);

			if (ButtonOn1==0) g.fillRect(405, 2, 15, 15);
			if (Colision(415, 2, 15, 15)) ButtonOn1=1;
			if (ButtonOn2==0) g.fillRect(480, 2, 15, 15);
			if (Colision(480, 2, 15, 15)) ButtonOn2=1;

			if (ButtonOn1<=1){
				g.fillRect(885, 730, 15, 15); 
				if (Colision(885, 730, 15, 15)){
					ButtonOn1=2;
					P2Tour=1;
					}
				g.fillRect(-5, 730, 15, 15);
				}
			else if (ButtonOn1==2){
				g.fillRect(0, 730, 15, 15); 
				if (Colision(0, 730, 15, 15)){
					ButtonOn1=3;
					P2Tour=2;
					}
				g.fillRect(890, 730, 15, 15);
				}
			else if (ButtonOn1==3){
				g.fillRect(885, 730, 15, 15); 
				if (Colision(885, 730, 15, 15)){
					ButtonOn1=4;
					P2Tour=3;
					}
				g.fillRect(-5, 730, 15, 15);
				}
			else if (ButtonOn1==4){
				g.fillRect(0, 730, 15, 15); 
				if (Colision(0, 730, 15, 15)){
					ButtonOn1=5;
					P2Tour=4;
					}
				g.fillRect(890, 730, 15, 15);
				}
			else if (ButtonOn1>=5){
				g.fillRect(-5, 730, 15, 15);
				g.fillRect(890, 730, 15, 15);	

				if (ButtonOn1<6) g.fillRect(443, 418+P1y, 15, 15);
				if (Colision(443, 418+P1y, 15, 15)) ButtonOn1=6;
				}
//*green Button
			g.setColor(Color.green);
			if (ButtonOn2==1){
				g.fillRect(0, 500, 15, 15);
				g.fillRect(885, 500, 15, 15);
				if (Colision(0, 500, 15, 15) || Colision(885, 500, 15, 15)) ButtonOn2=2;
				}
			else if (ButtonOn2==2){
				g.fillRect(-5, 500, 15, 15);
				g.fillRect(890, 500, 15, 15);
				}
			if (ButtonOn2==2 && TimeOn==0){
				new Time().start();
				TimeOn=1;
				if (Time==10){
					ButtonOn2=1;
					Time=0;
					}
				}
//*Sticky green
			g.setColor(Color.green);
			//g.fillRect(10, 230, 5, 525);
			//ColisionALL(10, 230, 5, 525);
			//WallStickAll(10, 230, 5, 525);			

			g.fillRect(100, 230, 5, 570);
			ColisionALL(100, 230, 5, 570);
			WallStickAll(100, 230, 5, 570);

			//g.fillRect(885, 230, 5, 525);
			//ColisionALL(885, 230, 5, 525);
			//WallStickAll(885, 230, 5, 525);

			g.fillRect(795, 230, 5, 570);
			ColisionALL(795, 230, 5, 570);
			WallStickAll(795, 230, 5, 570);
			
//***Goal Level 30
			g.setColor(new Color(210, 235, 0));
			g.fillRect(90, 755, 10, 40);
			ColisionALL(90, 755, 10, 40);
			if (ButtonOn1<6){
				g.fillRect(10, 755, 80, 5);
				ColisionALL(10, 755, 80, 5);
				}
			g.fillRect(10, 795, 90, 5);
			ColisionALL(10, 795, 90, 5);

			g.setColor(Color.cyan);
			g.fillOval(90, 760, 5, 30);
			if (Colision(90-1, 760, 5, 30) && ButtonOn1==6) x=811;

			g.setColor(new Color(210, 235, 0));
			g.fillRect(800, 760, 10, 35);
			ColisionALL(800, 760, 10, 35);

			g.fillRect(800, 755, 90, 5);
			ColisionALL(800, 755, 90, 5);

			g.fillRect(800, 795, 90, 5);
			ColisionALL(800, 795, 90, 5);

			g.setColor(Color.magenta);
			g.fillOval(805, 760, 5, 30);

			g.setColor(Color.black);
			g.fillRect(870, 760, 10, 35);
			if (x==870 && y==795){
				Level=31;
				LevelColor=0;
				initP=0;
				}
			}
//***Level 31
		else if (Level==31){
			if (initP==0){
				x=780;
				y=795;
				ButtonOn1=0;
				P1x=550; //x P green
				P1y=770; //y red
				TimeOn=0;
				Time=0;
				initP=1;
				}
//*yellow
			g.setColor(Color.yellow);
			g.fillRect(0,0, 100, 800);
			ColisionAll(0,0, 100, 800);
	
			g.fillRect(800, 0, 100, 800);
			ColisionAll(800, 0, 100, 800);

			g.fillRect(680, 150, 20, 650);
			ColisionALL(680, 150+1, 20, 650);

			g.fillRect(680, 680, 120,  20);
			ColisionALL(680, 680, 120,  20);

			g.fillRect(330, 0, 20, 800);
			ColisionALL(330, 0, 20, 800);

			g.fillRect(350, 150, 330, 20);
			ColisionALL(350, 150, 330, 20);

			g.fillRect(100, 0, 700, 10);
			ColisionALL(100, 0, 700, 10);

			g.fillRect(130, 10, 10, 790);
			ColisionALL(130, 10, 10, 790);

			g.fillRect(140, 255, 190, 20);
			ColisionALL(140, 255, 190, 20);

			g.fillRect(140, 500, 190, 20);
			ColisionALL(140, 500, 190, 20);

			g.fillRect(107, 10, 16, 15);
			ColisionALL(107, 10, 16, 15);

			g.fillRect(350, 200, 300, 10);
			ColisionALL(350, 200, 300, 10);

			for(int i=0; i<2; i++){
				g.setColor(Color.yellow);
				g.fillRect(P1x, 500+i*150, 40, 20);
				ColisionALL(P1x, 500+i*150, 40, 20);
				if (Sustain(P1x, 500+i*150, 40, 20)){
					if (P1x+40<680 && Time<5) x+=1;
					if (550<P1x && 5<=Time && Time<10) x-=1;
					}
				g.fillRect(1030-P1x-40, 575+i*150, 40, 20);
				ColisionALL(1030-P1x-40, 575+i*150, 40, 20);
				if (Sustain(1030-P1x-40, 575+i*150, 40, 20)){
					if (P1x+40<680 && Time<5) x-=1;
					if (550<P1x && 5<=Time && Time<10) x+=1;
					}
				g.setColor(Color.green);
				g.fillRect(P1x, 500+i*150, 5, 20);
				g.fillRect(1030-P1x, 575+i*150, -5, 20);
				WallStickAll(P1x, 500+i*150, 5, 20);
				WallStickAll(1030-P1x-5, 575+i*150, 5, 20);
				if (WallStick(1030-P1x-2, 575+i*150, 2, 20)){
					if (P1x+40<680 && Time<5) x-=1;
					if (550<P1x && 5<=Time && Time<10) x+=1;
					}
				if (WallStick(P1x, 500+i*150, 2, 20)){
					if (P1x+40<680 && Time<5) x+=1;
					if (550<P1x && 5<=Time && Time<10) x-=1;
					}
				}
			if (TimeOn==0){
				new Time().start();
				TimeOn=1;
				}
			if (P1x+40<680 && Time<5) P1x+=1;
			if (550<P1x && 5<=Time && Time<10) P1x-=1;
			if (Time==10) Time=0;

			g.setColor(Color.yellow);
			for(int i=0; i<4; i++){
				g.fillRect(650-i*100, 455-i*38, 30, 15);
				ColisionALL(650-i*100, 455-i*38, 30, 15);
				}
			for(int i=1; i<4; i++){
				g.fillRect(350+i*100, 280, 30, 15);
				ColisionALL(350+i*100, 280, 30, 15);
				}
//*Red Dead
			g.setColor(Color.red);
			g.fillRect(140, 10, 20, 245);
			if (Colision(140, 10, 20, 245)) initP=0;

			g.fillRect(160, 235, 150, 20);
			if (Colision(160, 235, 150, 20)) initP=0;
			
			g.fillRect(310, 10, 20, 245);
			if (Colision(310, 10, 20, 245)) initP=0;

			g.fillRect(140, 520, 80, 280);
			if (Colision(140, 520, 80, 280)) initP=0;

			g.fillRect(250, 520, 80, 280);
			if (Colision(250, 520, 80, 280)) initP=0;

			g.fillRect(700, 660, 100, 20);
			if (Colision(700, 660, 100, 20)) initP=0;

			if (ButtonOn1==1 && 100<=x && x<=130){
				g.fillRect(100, P1y, 30, 30);
				if (Colision(100, P1y, 30, 30)) initP=0;
				P1y-=1;
				}
//*Red Button
			if (ButtonOn1==0) g.fillRect(90, 30, 15, 15);
			else if (ButtonOn1==1) g.fillRect(85, 30, 15, 15);
			if (Colision(90, 30, 15, 15)) ButtonOn1=1;
//*Line
			g.setColor(Color.orange);
			g.drawLine(700, 150, 800, 350);
			if (LineOn(700, 150, 800, 350) && Down==0){
				Slide(700, 150, 800, 350);
				x+=1;
				}
			g.drawLine(700, 380, 800, 580);
			if (LineOn(700, 380, 800, 580) && Down==0){
				Slide(700, 380, 800, 580);
				x+=1;
				}
			if (ButtonOn1==0){
				g.drawLine(115, 25, 115, 800);
				if (x<=115 && 115<=x+Size) y-=2;
				}
//*Portals
			g.setColor(Color.gray);
			if (ButtonOn1==0){
				g.fillOval(345, 460, 5, 30); //#7
				g.fillOval(95, 220, 5, 30); //#6
				}
			g.setColor(Color.cyan);
			g.fillOval(695, 765, 5, 30); //#1
			if (Colision(695+1, 765, 5, 30)) x=680-Size;

			g.fillOval(500, 165, 30, 5); //#2
			if (Colision(500, 165, 30, 5+2)) y=150;

			g.fillOval(800, 320, 5, 30); //#3
			if (Colision(800-1, 320, 5, 30)){
				x=225;
				y=10+Size;
				}
			g.fillOval(800, 550, 5, 30); //#4
			if (Colision(800-1, 550, 5, 30)){
				x=225;
				y=520+Size;
				}
			g.fillOval(220, 800, 30, 5); //#5
			if (Colision(220, 800-1, 30, 5)) x=105;

			if (ButtonOn1==1){
				g.fillOval(345, 460, 5, 30); //#7
				if (Colision(345, 460, 5+1, 30)) x=330-Size;

				g.fillOval(330, 460, 5, 30); //#7

				g.fillOval(95, 220, 5, 30); //#6
				if (Colision(95, 220, 5+1, 30)){
					x=740;
					y=700+Size;
					}
				}
			g.setColor(Color.magenta);
			g.fillOval(680, 765, 5, 30); //#1

			g.fillOval(500, 150, 30, 5); //#2

			g.fillOval(220, 5, 30, 5); //#3

			g.fillOval(220, 515, 30, 5); //#4

			g.fillOval(100, 800, 30, 5); //#5

			g.fillOval(735, 695, 30, 5); //#6

			if (ButtonOn1==1) g.fillOval(330, 460, 5, 30); //#7

//***Goal Level 31
			g.setColor(Color.black);
			g.fillRect(150, 450, 20, 50);
			if (x==150 && y==500){
				Level=32;
				initP=0;
				}
			}
//***Level 32        tube rouge au centre avec plusieurs portails aleatoir, carres autour
		else if (Level==32){
			if (initP==0){
				x=150;
				y=500;
				ResetTab();
				/* 0, B1
				*  1, B2
				*  2, x1= Red dead
				*  3, y1= Red dead
				*  4, x2 Red dead
				*  5, x2Tour Red dead
				*  6, x3 Red dead
				*  7, x3Tour Red dead
				*  8, y S1 blue P1 go up
				*  9, y S2 P2 go up
				*  10, y2 pillar 1
				*  11, y3 pillar 2
				*  12, xy1 Pmove
				*  13, y4 Red dead, x=660
				*/
				Tab[4]=500;
				Tab[6]=400;
				Tab[8]=785;
				Tab[9]=360;
				Tab[10]=710;
				Tab[11]=0;
				Tab[13]=600;
				initP=1;
				}
//*yellow a little orangy
			g.setColor(new Color(250, 220, 0));
			g.fillRect(0,-100, 90, 800+100);
			ColisionAll(0,-100, 90, 800+100);
	
			g.fillRect(810, 0, 90, 800);
			ColisionAll(810, 0, 90, 800);

			g.fillRect(235, 0, 20, Tab[10]+1);
			ColisionALL(235, 0, 20, Tab[10]+1);

			g.fillArc(195, Tab[10], 100, 30, 0, 180);
			ColisionALL(195, Tab[10]+10, 100, 5);

			g.fillRect(280, Tab[10]+15, 15, 30);
			ColisionALL(280, Tab[10]+15, 15, 30);
			
			g.fillArc(195, Tab[10]+30, 100, 30, 180, 180);
			ColisionALL(195, Tab[10]+45, 100, 5);
			if (Sustain(195, Tab[10]+45, 100, 5) && Tab[10]>0){
				Tab[10]-=1;
				y-=1;
				}
			else if (Tab[10]<710) Tab[10]+=1;

			g.fillRect(300, 0, 15, 800);
			ColisionALL(300, 0, 15, 800);

			g.fillRect(175, 0, 20, 40);
			ColisionALL(175, -100, 20, 40+100);

			g.fillRect(355, 0, 20, Tab[11]+1);
			ColisionALL(355, 0, 20, Tab[11]+1);

			g.fillArc(315, Tab[11], 100, 30, 0, 180);
			ColisionALL(315, Tab[11]+10, 100, 5);

			g.fillRect(400, Tab[11]+15, 15, 30);
			ColisionALL(400, Tab[11]+15, 15, 30);
			
			g.fillArc(315, Tab[11]+30, 100, 30, 180, 180);
			ColisionALL(315, Tab[11]+45, 100, 5);
			if (Tab[1]==1 && Tab[11]+60<500){
				Tab[11]+=1;
				y+=1;
				}
			g.fillRect(750, 40, 60, 15);
			ColisionALL(750, 40, 60, 15);

			g.fillRect(550, 0, 20, 250);
			ColisionALL(550, 0, 20, 250);

			g.fillRect(550, 250, 150, 20);
			ColisionALL(550, 250, 150, 20);

			g.fillRect(680, 270, 20, 70);
			ColisionALL(680, 270, 20, 70);

			g.fillRect(550, 340, 150, 20);
			ColisionALL(550, 340, 150, 20);

			g.fillRect(680, 360, 20, 390);
			ColisionALL(680, 360, 20, 390);

			g.fillRect(360, Tab[8], 30, 15);
			ColisionALL(360, Tab[8], 30, 15);
			if (Sustain(360, Tab[8], 5, 2) && Tab[8]>620){
				Tab[8]-=1;
				y-=1;
				}
			else if (y==800 && Tab[8]+15<800) Tab[8]+=1;
			g.fillRect(520, Tab[9], 30, 15);
			ColisionALL(520, Tab[9], 30, 15);
			if (Sustain(520, Tab[9], 30, 15) && Tab[9]>360){
				Tab[9]-=1;
				y-=1;
				}
			g.fillRect(490, 600, 20, 15);
			ColisionALL(490, 600, 20, 15);

			g.fillRect(560, 645, 20, 15);
			ColisionALL(560, 645, 20, 15);

			g.fillRect(660, Tab[13], 20, 15);
			ColisionALL(660, Tab[13], 20, 15);
			if (Sustain(675, Tab[13], 5, 2)){
				if (Tab[13]>500){
					Tab[13]-=1;
					y-=1;
					}
				if (Tab[13]==500 && Tab[9]<500) Tab[9]+=1;
				}
			else if (y==800 && Tab[13]+15<600) Tab[13]+=1;
			g.setColor(Color.blue);
			g.fillRect(360, Tab[8], 5, 3);
			g.fillRect(675, Tab[13], 5, 3);
//*Line
			g.setColor(Color.orange);
			g.drawLine(750, 40, 629, 96);
			if (LineOn(750, 40, 629, 96)){
				Slide(750, 40, 629, 96);
				x-=1;
				}
			g.drawLine(570, 60, 781, 189);
			if (LineOn(570, 60, 781, 189)){
				Slide(570, 60, 781, 189);
				x+=1;
				}
			g.drawLine(810, 150, 717, 272);
			if (LineOn(810, 150, 717, 272)){
				Slide(810, 150, 717, 272);
				x-=1;
				}
			g.drawLine(700, 250, 810, 400);
			if (LineOn(700, 250, 810, 400) && Down==0){
				Slide(700, 250, 810, 400);
				x+=1;
				}
//*Portals
			if (Tab[0]==0) g.setColor(Color.gray);
			if (Tab[0]==1){
				g.setColor(Color.cyan);
				if (Colision(280-1, Tab[10]+15, 5, 30)){
					x=365-9;
					y=Tab[11]+15+Size;
					}
				}
			g.fillOval(280, Tab[10]+15, 5, 30); //#1

			g.fillOval(310, 455, 5, 30); //#2
			if (Colision(310+1, 455, 5, 30)){
				x=810-Size;
				y=10+Size;
				}
			g.setColor(Color.magenta);
			g.fillOval(350, Tab[11]+10, 30, 5); //#1
			g.fillOval(810, 10, 5, 30); //#2
//*Sticky green
			g.setColor(Color.green);
			g.fillRect(85, 0, 5, 250);
			WallStickAll(85, 0, 5, 250);

			g.fillRect(695, 360, 5, 390);
			WallStickAll(695, 360, 5, 390);
//*Red Dead
			g.setColor(Color.red);
			g.fillRect(315, 500, 45, 300);
			if (Colision(315, 500, 45, 300)) initP=0;

			g.fillRect(795, 380, 15, 40);
			if (Colision(795, 380, 15, 40)) initP=0;

			g.fillRect(Tab[2], Tab[3], 30, 30);
			if (Colision(Tab[2], Tab[3], 30, 30)) initP=0;
			Tab[2]=740+(int)(40*Math.cos((Tab[12]*3.14)/250));
			Tab[3]=595+(int)(175*Math.sin((Tab[12]*3.14)/250));
			Tab[12]+=1;

			g.fillRect(Tab[4], 535, 40, 20);
			if (Colision(Tab[4], 535, 40, 20)) initP=0;
			if (Tab[5]==0) Tab[4]+=1;
			if (Tab[5]==1) Tab[4]-=1;
			if (Tab[4]==360) Tab[5]=0;
			if (Tab[4]+40==680) Tab[5]=1;

			g.fillRect(Tab[6], 660, 40, 20);
			if (Colision(Tab[6], 660, 40, 20)) initP=0;
			if (Tab[7]==0) Tab[6]+=1;
			if (Tab[7]==1) Tab[6]-=1;
			if (Tab[6]==360) Tab[7]=0;
			if (Tab[6]+40==680) Tab[7]=1;
//*Red Button
			if (Tab[0]==0) g.fillRect(170, 20, 15, 15);
			if (Colision(170, 20, 15, 15)) Tab[0]=1;
			if (Tab[0]==1) g.fillRect(175, 20, 15, 15);

			if (Tab[1]==0) g.fillRect(395, Tab[11]+22, 15, 15);
			if (Colision(395, Tab[11]+22, 15, 15)) Tab[1]=1;
			if (Tab[1]==1) g.fillRect(400, Tab[11]+22, 15, 15);
//***Goal Level 32
			g.setColor(Color.black);
			g.fillRect(640, 300, 15, 40);
			if (x+Size==655 && y==340){
				Level=33;
				initP=0;
				} 
			}
//***Level 33
		else if (Level==33){
			if (initP==0){
				x=655-Size;
				y=340;
				ResetTab();
				/* 0, Button cumulatif 1+2+3+4 
				*  1, B1 room 1
				*  2, B2 room 2
				*  3, B3 room 3
				*  4, B4 room 4
				*  5, x1 P moving around 
				*  6, y1 P moving around
				*  7, tour P moving around
				* 14, x5 red dead room 4
				* 15, x5Tour red dead room 4
				*  8, x2 double red dead room 4 add and subtract x2=650
				* 17, x2Tour double red dead room 4
				*  9, x3 circle red dead room 4
				* 13, y3 circle red dead room 4
				* 18, xy3 cirelce Pmove room 4
				* 16, y6 P with button and portal sustain room 4
				* 10, x4 red dead sustain pursue room 1
				* 12, TourLine room 2
				* 19, B5 room 5
				* 20, sustain colision point room 5 x=
				*/
				//Tab[1]=Tab[2]=Tab[3]=Tab[4]=1;
				Tab[5]=150;
				Tab[6]=300;
				Tab[8]=0;
				Tab[10]=240;
				Tab[14]=650;
				Tab[16]=200;
				TimeOn=0; //x2 double red dead room 4
				Time=0;
				initP=1;
				}
//*orangy yellowy
			g.setColor(new Color(250, 200, 0));
			g.fillRect(0,0, 80, 800);
			ColisionAll(0,0, 80, 800);
	
			g.fillRect(820, 0, 80, 800);
			ColisionAll(820, 0, 80, 800);

			g.fillRect(210, 250, 480, 20);
			ColisionALL(210, 250, 480, 20);

			g.fillRect(210, 270, 20, 150);
			ColisionALL(210, 270, 20, 150);

			g.fillRect(670, 270, 20, 150);
			ColisionALL(670, 270, 20, 150);

			g.fillRect(210, 420, 480, 20);
			ColisionALL(210, 420, 480, 20);

			g.fillRect(Tab[5], Tab[6], 30, 30);
			ColisionALL(Tab[5], Tab[6], 30, 30);
			if (230<=x && x+Size<=670 && 270<=y-Size && y<=420){
				if (Tab[5]==150 && Tab[6]<470) Tab[6]+=1;
				if (Tab[5]+30<750 && Tab[6]==470) Tab[5]+=1;
				if (Tab[5]+30==750 && Tab[6]>160) Tab[6]-=1;
				if (Tab[5]>150 && Tab[6]==160) Tab[5]-=1;
				}
			if (Tab[0]==4 && Tab[19]==0){
				g.setColor(Color.green);
				g.fillRect(Tab[5], Tab[6]+15, 3, 5);
				WallStickAll(Tab[5], Tab[6]+15, 3, 5);
				g.setColor(Color.blue);
				g.fillRect(Tab[5]+25, Tab[6], 5, 3);
				if (Sustain(Tab[5]+25, Tab[6], 5, 2)){
					if (Tab[5]==150 && Tab[6]<470){
						Tab[6]+=1;
						y+=1;
						}
					if (Tab[5]+30<750 && Tab[6]==470){
						Tab[5]+=1;
						x+=1;
						}
					if (Tab[5]+30==750 && Tab[6]>160){
						Tab[6]-=1;
						y-=1;
						}
					if (Tab[5]>150 && Tab[6]==160){
						Tab[5]-=1;
						x-=1;
						}
					}
				}	
//*room 1
			g.setColor(new Color(250, 200, 0));
			g.fillRect(100, 535, 165, 15);
			ColisionALL(100, 535, 165, 15);

			g.fillRect(80, 590, 140, 15);
			ColisionALL(80, 590, 140, 15);

			g.fillRect(250, 590, 15, 15);
			ColisionALL(250, 590, 15, 15);

			g.fillRect(110, 700, 155, 15);
			ColisionALL(110, 700, 155, 15);

			g.fillRect(80, 740, 160, 15);
			ColisionALL(80, 740, 160, 15);

			g.fillRect(120, 760, 15, 40);
			ColisionALL(120, 760, 15, 40);

			for(int i=0; i<3; i++){
				g.fillRect(80+i*74, 650, 30, 15);
				ColisionALL(80+i*74, 650, 30, 15);
				}

			g.setColor(Color.red);
			g.fillRect(220, 595, 5, 10);
			if (Colision(220, 595, 5, 10)) initP=0;

			g.fillRect(184, 649, 5, 10);
			if (Colision(184, 649, 5, 10)) initP=0;

			for(int i=0; i<2; i++){
				g.fillRect(110+i*39, 655, 5, 10);
				if (Colision(110+i*39, 655, 5, 10)) initP=0;
				}
			if (y==700 && Tab[10]>80 && 80<=x && x+Size<=265){
				g.fillRect(Tab[10], 680, 20, 15);
				if (Colision(Tab[10], 680, 20, 15)) initP=0;
				Tab[10]-=1;
				}
			else Tab[10]=240;

			if (Tab[1]==0){
				g.fillRect(170, 751, 8, 8);
				if (Colision(170, 751, 8, 8)) Tab[1]=1;
				}

			g.setColor(Color.cyan);
			g.fillOval(130, 770, 5, 30); //#2
			if (Colision(130+1, 770, 5, 30)){
				x=615-8;
				y=270+Size;
				}
//*room 2
			g.setColor(new Color(250, 200, 0));
			for(int i=0; i<2; i++){
				g.fillRect(270+i*170, 605, 10, 10);
				ColisionALL(270+i*170, 605, 10, 10);
				}
			g.fillRect(430, 760, 20, 40);
			ColisionALL(430, 760, 20, 40);

			g.fillRect(290, 760, 20, 40);
			ColisionALL(290, 760, 20, 40);

			g.setColor(Color.orange);
			g.drawLine(280, 610, 440, 610);
			if (y-Size==611 && Down==0 && 270<=x && x+Size<=450){
				y-=1;
				if (Tab[12]==0) x+=1;
				if (Tab[12]==1) x-=1;
				if (x+Size==440) Tab[12]=1;
				if (x==280) Tab[12]=0;
				}
			g.drawLine(380, 645, 270, 715);
			if (LineOn(380, 645, 270, 715) && Down==0){
				Slide(380, 645, 270, 715);
				x-=1;
				}
			g.drawLine(270, 720, 450, 720);
			if (y-Size==720+1 && Down==0 && 270<=x && x+Size<=450){
				y-=1;
				x+=1;
				}
			g.setColor(Color.red);
			g.fillRect(300, 550, 150, 15);
			if (Colision(300, 550, 150, 15)) initP=0;

			g.fillRect(265, 630, 80, 15);
			if (Colision(265, 630, 80, 15)) initP=0;

			g.fillRect(380, 630, 70, 15);
			if (Colision(380, 630, 70, 15)) initP=0;

			g.fillRect(270, 745, 130, 15);
			if (Colision(270, 745, 130, 15)) initP=0;
		
			g.fillRect(430, 745, 20, 15);
			if (Colision(430, 745, 20, 15)) initP=0;

			if (Tab[2]==0){
				g.fillRect(426, 776, 8, 8);
				if (Colision(426, 776, 8, 8)) Tab[2]=1;
				}
			g.setColor(Color.cyan);
			g.fillOval(305, 770, 5, 30);
			if (Colision(305+1, 770, 5, 30)){
				x=615-8;
				y=270+Size;
				}
//*room 3
			g.setColor(new Color(250, 200, 0));
			g.fillRect(455, 520, 155, 15);
			ColisionALL(455, 520, 155, 15);

			g.fillRect(480, 600, 20, 15);
			ColisionALL(480, 600, 20, 15);

			g.fillRect(615, 600, 20, 15);
			ColisionALL(615, 600, 20, 15);

			g.fillRect(480, 615, 155, 5);
			ColisionALL(480, 615, 155, 5);

			g.fillRect(553, 600, 10, 5);
			ColisionALL(553, 600, 10, 5);

			g.fillRect(455, 650, 20, 150);
			ColisionALL(455, 650, 20, 150);

			g.fillRect(615, 650, 20, 150);
			ColisionALL(615, 650, 20, 150);

			g.fillRect(625, 620, 10, 30);
			ColisionALL(625, 620, 10, 30);

			g.fillRect(535, 775, 20, 15);
			ColisionALL(535, 775, 20, 15);

			g.setColor(Color.green);
			g.fillRect(500, 660, 10, 10);
			ColisionALL(500, 660, 10, 10);
			WallStickAll(500, 660, 10, 10);

			g.fillRect(475-3, 755, 3, 5);
			WallStickAll(475-3, 755, 3, 5);

			g.fillRect(540, 705, 10, 10);
			ColisionALL(540, 705, 10, 10);
			WallStickAll(540, 705, 10, 10);

			g.fillRect(560, 620, 10, 5);
			ColisionALL(560, 620, 10, 5);
			WallStickAll(560, 620, 10, 5);

			g.fillRect(605, 780, 10, 10);
			ColisionALL(605, 780, 10, 10);
			WallStickAll(605, 780, 10, 10);

			g.fillRect(615, 650, 3, 10);
			WallStickAll(615, 650, 3, 10);

			g.setColor(Color.red);
			g.fillRect(500, 605, 115, 10);
			if (Colision(500, 605, 115, 10)) initP=0;

			g.fillRect(475, 790, 140, 10);
			if (Colision(475, 790, 140, 10)) initP=0;

			if (Tab[3]==0){
				g.fillRect(541, 771, 8, 8);
				if (Colision (541, 771, 8, 8)) Tab[3]=1;
				}
			g.setColor(Color.cyan);
			g.fillOval(625, 620, 5, 30); //#2
			if (Colision(625-1, 620, 5, 30)){
				x=615-8;
				y=270+Size;
				}
//*room 4
			g.setColor(new Color(250, 200, 0));
			g.fillRect(640, 505, 5, 295);
			ColisionALL(640, 505, 5, 295);

			g.fillRect(645, 795, 155, 5);
			ColisionALL(645, 795, 155, 5);

			g.fillRect(685, 520, 15, 10);
			ColisionALL(685, 520, 15, 10);

			g.fillRect(740, 520, 15, 10);
			ColisionALL(740, 520, 15, 10);

			g.fillRect(711, 620, 22, 20);
			ColisionALL(711, 620, 22, 20);
			for(int i=0; i<2; i++){
				g.fillRect(645+i*145, 720, 10, 5);
				ColisionALL(645+i*145, 720, 10, 5);
				}
			g.fillRect(800, Tab[16], 20, 100); //*Tab[16]y = 240 
			ColisionALL(800, Tab[16], 20, 100);
			for(int i=0; i<2 && Tab[16]<390; i++){
				g.fillRect(800, Tab[16]+100+i*130, 5, 30);
				ColisionALL(800, Tab[16]+100+i*130, 5, 30);
				}
			g.fillRect(800, Tab[16]+130, 20, 100);
			ColisionALL(800, Tab[16]+130, 20, 100);

			g.fillRect(800, Tab[16]+260, 20, 800-(Tab[16]+260));
			ColisionALL(800, Tab[16]+260, 20, 800-(Tab[16]+260));
			
			g.setColor(Color.red);
				g.fillRect(Tab[14], 550, 20, 15);
				if (Colision(Tab[14], 550, 20, 15)) initP=0;

				g.fillRect(645+Tab[8], 650, 15, 10);
				if (Colision(645+Tab[8], 650, 15, 10)) initP=0;

				g.fillRect(785-Tab[8], 650, 15, 10);
				if (Colision(785-Tab[8], 650, 15, 10)) initP=0;

				g.fillRect(Tab[9]-5, Tab[13]-5, 10, 10);
				if (Colision(Tab[9]-5, Tab[13]-5, 10, 10)) initP=0;
			if (640<=x && x+Size<=820 && 500<=y-Size && y<=800){
				//g.fillRect(Tab[14], 550, 20, 15);
				if (Tab[15]==0) Tab[14]+=1;
				if (Tab[15]==1) Tab[14]-=1;
				if (Tab[14]+20==800) Tab[15]=1;
				if (Tab[14]==650) Tab[15]=0;

				//g.fillRect(645+Tab[8], 650, 15, 10);
				//g.fillRect(785-Tab[8], 650, 15, 10);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time<2 && Tab[8]<60) Tab[8]+=1;
				if (2<Time && Time<4 && Tab[8]>0) Tab[8]-=1;
				if (Time==4) Time=0;

				//g.fillRect(Tab[9]-5, Tab[13]-5, 10, 10);
				Tab[9]=722+(int)(50*Math.cos((Tab[18]*3.14)/300));
				Tab[13]=730+(int)(50*Math.sin((Tab[18]*3.14)/300));
				Tab[18]+=1;
				}
			if (Tab[4]==0){
				g.fillRect(816, Tab[16]+111, 8, 8);
				if (Colision(816, Tab[16]+111, 8, 8)) Tab[4]=1;
				}
			g.setColor(Color.green);
			for(int i=0; i<2; i++){
				g.fillRect(697+i*43, 520, 3, 10);
				WallStickAll(697+i*43, 520, 3, 10);
				g.fillRect(711+i*19, 620, 3, 20);
				WallStickAll(711+i*19, 620, 3, 20);
				}
			g.setColor(Color.blue);
			g.fillRect(648, 795, 5, 3);
			if (Sustain(648, 795, 5, 3) && Tab[16]<390) Tab[16]+=1;
			
			g.setColor(Color.cyan);
			g.fillOval(820, Tab[16]+230, 5, 30); //#2
			if (Colision(820-1, Tab[16]+230, 5, 30)){
				x=615-8;
				y=270+Size;
				}
//*room 5
			if (Tab[0]==4){
				g.setColor(new Color(250, 200, 0));
				g.fillRect(130, 25, 10, 45);
				ColisionALL(130, 25, 10, 45);
				if (Colision(130, 25-1, 10, 2)) Tab[20]=1;
				if (Tab[20]==1 && y>=160) Tab[20]=0;
				if (Tab[20]==1){
					if (Tab[6]>160) Tab[6]-=1;
					if (Tab[5]<x) Tab[5]+=1;
					if (Tab[5]>x) Tab[5]-=1;

					g.fillRect(200, 35, 9, 40);
					ColisionALL(200, 35, 9, 40);

					g.fillRect(270, 50, 8, 30);
					ColisionALL(270, 50, 8, 30);

					g.fillRect(320, 10, 7, 25);
					ColisionALL(320, 10, 7, 25);

					g.fillRect(500, 60, 6, 20);
					ColisionALL(500, 60, 6, 20);

					g.fillRect(550, 40, 5, 15);
					ColisionALL(550, 40, 5, 15);

					g.fillRect(610, 25, 4, 10);
					ColisionALL(610, 25, 4, 10);

					g.setColor(Color.red);
					if (Tab[19]==0){
						g.fillRect(696, 60, 8, 8);
						if (Colision(696, 60, 8, 8)) Tab[19]=1;
						}
					}
				g.setColor(Color.green);
				g.fillRect(75, 90, 5, 40);
				WallStickAll(75, 90, 5, 40);
				}
//*Red Dead
			g.setColor(Color.red);
			for(int i=1; i<4; i++){
				g.fillRect(80+i*185, 500, 5, 300);
				if (Colision(80+i*185, 500, 5, 300)) initP=0;
				}

//*Portals
			g.setColor(Color.magenta);
			g.fillOval(Tab[5], Tab[6]+25, 30, 5); //#1
			g.fillOval(600, 265, 30, 5); //#2

			g.setColor(Color.cyan);
			g.fillOval(670, 390, 5, 30); //#1
			if (Colision(670-1, 390, 5, 30)){
				x=Tab[5]+7;
				y=Tab[6]+30+Size;
				}

			if (Tab[0]==4 && Tab[19]==0) g.setColor(Color.gray);
			g.fillOval(225, 390, 5, 30); //#3
			if (Colision(225+1, 390, 5, 30) && Tab[19]==1){
				x=720;
				y=100;
				}
//***Goal Level 33
			g.setColor(new Color(250, 200, 0));
			Tab[0]=Tab[1]+Tab[2]+Tab[3]+Tab[4];
			for(int i=0; i<4-Tab[0]; i++){
				g.fillRect(280+i*50, 270, 20, 150);
				ColisionALL(280+i*50, 270, 20, 150);
				}
			g.fillRect(700, 100, 120, 20);
			ColisionALL(700, 100, 120, 20);

			g.fillRect(700, 0, 20, 100);
			ColisionALL(700, 0-100, 20, 100+100);
			
			g.setColor(Color.magenta);
			g.fillOval(715, 70, 5, 30); //#3

			g.setColor(Color.black);
			g.fillRect(790, 50, 20, 50);
			if (x+Size==810 && y==100){
				Level=34;
				initP=0;
				}
			}
//***Level 34
		else if (Level==34){
			if (initP==0){
				x=810-Size;
				y=100;
				ResetTab();
				/*
				* 0, x1 double red dead 1st floor add & subtract
				* 1, x2 double red dead 2nd floor add & subtract
				* 2, x3 double red dead 3rd floor add & subtract
				* 3, color r level faint,     rgb=(240,170,0)
				* 4, color g level faint,
				* 5, color r yellow lines faint, rgb=(255,255,0)
				* 6, color g yellow lines faint, rgb=(255,255,0)
				* 7, trigger time floor 1
				* 8, trigger time floor 2
				* 9, trigger time floor 3
				*/
				TimeOn=0; //* 1st floor 5s
					  //* 2nd floor 7s
					  //* 3rd floor 10s
				Time=0;
				Tab[3]=0;
				Tab[4]=0;
				Tab[5]=255;
				Tab[6]=255;
				initP=1;
				}
//*fat orange slightly yellowy
			g.setColor(new Color(240, 170, 0));
			g.fillRect(0,0, 70, 800);
			ColisionAll(0,0, 70, 800);
	
			g.fillRect(830, 0, 70, 800);
			ColisionAll(830, 0, 70, 800);

			g.fillRect(790, 100, 20, 10);
			ColisionALL(790, 100, 20, 10);

			g.setColor(Color.black);
			g.fillRect(70, 110, 760, 200);
			ColisionALL(70, 110, 760, 3);
			ColisionALL(70, 310-3, 760, 3);

			g.fillRect(70, 365, 760, 200);
			ColisionALL(70, 365, 760, 3);
			ColisionALL(70, 565-3, 760, 3);

			g.fillRect(70, 590, 760, 210);
			ColisionALL(70, 590, 760, 3);

			g.setColor(Color.magenta);
			g.fillOval(435, 305, 30, 5);
			g.fillOval(435, 560, 30, 5);

//*Floor 1
			if (y==110) Tab[7]=1;
			if (TimeOn==0 && Tab[7]==1 && Time<5 && y<720){
				new Time().start();
				TimeOn=1;
				}
			if (Tab[7]==1){
				if (Time<=5) Tab[5]=255-Time*51;

				g.setColor(new Color(Tab[5], Tab[5], Tab[5])); //white
				g.fillRect(300, 150, 50, 50);
				g.fillRect(620, 150, 50, 50);
	
				g.setColor(new Color(Tab[5],0,0)); //red
				g.fillRect(345, 150, 5, 50);
				g.fillRect(620, 150, 5, 50);

				g.setColor(new Color(Tab[5], 0, Tab[5]));
				g.fillOval(295, 160, 5, 30);
				g.fillOval(670, 160, 5, 30);
				
				g.setColor(new Color(Tab[5], Tab[5], 0));
				int x1[] = {250, 250, 600, 600, 390, 390, 570, 570, 360, 360, 450, 450};
				int y1[] = {115, 145, 145, 220, 220, 200, 200, 180, 180, 160, 160, 305};

				int x2[] = {450, 450, 100, 100, 750, 750, 675};
				int y2[] = {115, 140, 140, 250, 250, 175, 175};

				int x3[] = {650, 650, 380, 380, 800, 800, 270, 270, 295};
				int y3[] = {115, 125, 125, 290, 290, 225, 225, 175, 175};

				g.drawPolyline(x1, y1, 12);
				g.drawPolyline(x2, y2, 7);
				g.drawPolyline(x3, y3, 9);
				}
				ColisionALL(300, 150-2, 50, 2);
			if (300-1<=x && x+Size<=350 && 150<=y-Size && y<=200+1){
				//ColisionALL(300, 150-2, 50, 4);
				ColisionALL(300-2, 150, 2, 50);
				ColisionALL(300, 200, 50, 3);
				g.setColor(Color.white);
				g.fillRect(300, 150, 50, 50);
				g.setColor(Color.red);
				g.fillRect(345, 150, 5, 50);
				if (Colision(345, 150, 5, 50)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(295, 160, 5, 30);
				}
				ColisionALL(620, 150-2, 50, 2);
			if (620<=x && x+Size<=670+1 && 150<=y-Size && y<=200+1){
				//ColisionALL(620, 150-2, 50, 2);
				ColisionALL(670, 150, 2, 50);
				ColisionALL(620, 200, 50, 5);
				g.setColor(Color.white);
				g.fillRect(620, 150, 50, 50);
				g.setColor(Color.red);
				g.fillRect(620, 150, 5, 50);
				if (Colision(620, 150, 5, 50)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(670, 160, 5, 30);
				}
			if (Time<5) g.setColor(Color.gray);
			else if (Tab[7]==1) g.setColor(Color.cyan);
			for(int i=0; i<3; i++){
				g.fillOval(235+i*200, 110, 30, 5);
				if (Colision(235+i*200, 110-1, 30, 5) && Time>=5){
					if (i==0){
						x=450-8;
						y=310+Size;
						Tab[7]=Time=0;
						Tab[5]=255;
						}
					else if (i==1){
						x=670-Size;
						y=185;
						Tab[7]=Time=0;
						}
					else if (i==2){
						x=300;
						y=185;
						Tab[7]=Time=0;
						}
					}
				}
//*Floor 2			
			if (y==365) Tab[8]=1;
			if (TimeOn==0 && Tab[8]==1 && Time<7 && y<720){
				new Time().start();
				TimeOn=1;
				}
			if (Tab[8]==1){
				if (Time<=7) Tab[5]=255-Time*37;
				if (Tab[5]<0) Tab[5]=0;

				g.setColor(new Color(Tab[5], Tab[5], Tab[5])); //white
				g.fillRect(80, 400, 30, 70);
				g.fillRect(320, 480, 50, 30);
				g.fillRect(580, 390, 50, 50);
				g.fillRect(775, 510, 50, 50);

				g.setColor(new Color(Tab[5], 0, 0)); //red
				g.fillRect(80, 465, 30, 5);
				g.fillRect(320, 505, 50, 5);
				g.fillRect(580, 390, 5, 50);
				g.fillRect(820, 510, 5, 50);

				g.setColor(new Color(Tab[5], 0, Tab[5])); //magenta
				g.fillOval(80, 395, 30, 5); //#6
				g.fillOval(330, 475, 30, 5); //#8
				g.fillOval(630, 400, 5, 30); //#4
				g.fillOval(770, 520, 5, 30); //#5

				g.setColor(new Color(Tab[5], Tab[5], 0)); //yellow
				int x4[] = {150, 150, 400, 400, 710, 710, 635};
				int y4[] = {370, 460, 460, 520, 520, 415, 415};

				int x5[] = {300, 300, 815, 815, 500, 500, 770};
				int y5[] = {370, 470, 470, 375, 375, 535, 535};

				int x6[] = {450, 450,  95,  95};
				int y6[] = {370, 375, 375, 395};

				int x7[] = {600, 600,  75,  75, 450, 450};
				int y7[] = {370, 380, 380, 555, 555, 560};

				int x8[] = {750, 750, 820, 820, 380, 380, 560, 560, 345, 345};
				int y8[] = {370, 390, 390, 510, 510, 450, 450, 415, 415, 475};

				g.drawPolyline(x4, y4, 7);
				g.drawPolyline(x5, y5, 7);
				g.drawPolyline(x6, y6, 4);
				g.drawPolyline(x7, y7, 6);
				g.drawPolyline(x8, y8, 10);
				}
			if (80-1<=x && x+Size<=110+1 && 400<=y-Size && y<=470){
				ColisionALL(80-2, 400, 2, 70);
				ColisionALL(110, 400, 2, 70);

				g.setColor(Color.white);
				g.fillRect(80, 400, 30, 70);

				g.setColor(Color.red);
				g.fillRect(80, 465, 30, 5);
				if (Colision(80, 465, 30, 5)) initP=0;

				g.setColor(Color.magenta);
				g.fillOval(80, 395, 30, 5); //#6

				g.setColor(Color.orange);
				g.drawLine(80, 420, 110-1, 420);
				if (y-Size==420+1 && Down==0) y-=1;
				}
			if (320-1<=x && x+Size<=370+1 && 480<=y-Size && y<=510){
				ColisionALL(320-2, 480, 2, 30);
				ColisionALL(370, 480, 2, 30);
				g.setColor(Color.white);
				g.fillRect(320, 480, 50, 30);
				g.setColor(Color.red);
				g.fillRect(320, 505, 50, 5);
				if (Colision(320, 505, 50, 5)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(330, 475, 30, 5); //#8
				}
				ColisionALL(580, 390-2, 50, 2);
			if (580<=x && x+Size<=630+1 && 390-1<=y-Size && y<=440+1){
				//ColisionALL(580, 390-2, 50, 2);
				ColisionALL(580, 440, 50, 3);
				ColisionALL(630, 390, 2, 50);
				g.setColor(Color.white);
				g.fillRect(580, 390, 50, 50);
				g.setColor(Color.red);
				g.fillRect(580, 390, 5, 50);
				if (Colision(580, 390, 5, 50)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(630, 400, 5, 30); //#4
				}
				ColisionALL(775, 510-2, 50, 2);
			if (775-1<=x && x+Size<=825 && 510-1<=y-Size && y<=560+1){
				//ColisionALL(775, 510-2, 50, 2);
				ColisionALL(775, 560, 50, 3);
				ColisionALL(775-2, 510, 2, 50);
				g.setColor(Color.white);
				g.fillRect(775, 510, 50, 50);
				g.setColor(Color.red);
				g.fillRect(820, 510, 5, 50);
				if (Colision(820, 510, 5, 50)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(770, 520, 5, 30); //#5
				}
			if (Time<7) g.setColor(Color.gray);
			else if (Tab[8]==1) g.setColor(Color.cyan);
			for(int i=0; i<5; i++){
				g.fillOval(135+i*150, 365, 30, 5);
				if (Colision(135+i*150, 365-1, 30, 5) && Time>=7){
					if (i==0){ //#4
						x=630-Size;
						y=420;
						Tab[8]=Time=0;
						}
					else if (i==1){ //#5
						x=775;
						y=540;
						Tab[8]=Time=0;
						}
					else if (i==2){ //#6
						x=87;
						y=395+Size;
						Tab[8]=Time=0;
						}
					else if (i==3){  //#7
						x=450-8;
						y=565+Size;
						Tab[8]=Time=0;
						Tab[5]=255;
						}
					else if (i==4){ //#8
						x=337;
						y=475+Size;
						Tab[8]=Time=0;
						}
					}
				}

//*Floor 3
			if (y==590) Tab[9]=1;
			if (TimeOn==0 && Tab[9]==1 && Time<10 && y<720){
				new Time().start();
				TimeOn=1;
				}
			if (Tab[9]==1){
				if (Time<=10) Tab[5]=255-Time*26;
				if (Tab[5]<0) Tab[5]=0;

				g.setColor(new Color(Tab[5], Tab[5], Tab[5])); //white
				g.fillRect(80, 740, 50, 50);
				g.fillRect(280, 630, 30, 70);
				g.fillRect(410, 720, 80, 80);
				g.fillRect(630, 730, 80, 30);
				g.fillRect(750, 600, 50, 50);

				g.setColor(new Color(Tab[5], 0, 0)); //red
				g.fillRect(125, 740, 5, 50);
				g.fillRect(280, 695, 30, 5);
				g.fillRect(630, 755, 80, 5);
				g.fillRect(750, 600, 5, 50);

				g.setColor(new Color(Tab[5], 0, Tab[5])); //magenta
				g.fillOval(75, 750, 5, 30); //#13
				g.fillOval(275, 640, 5, 30); //#10
				g.fillOval(310, 640, 5, 30); //#12
				g.fillOval(435, 715, 30, 5); //#15
				g.fillOval(635, 725, 30, 5); //#14
				g.fillOval(675, 725, 30, 5); //#9
				g.fillOval(800, 610, 5, 30); //#11

				g.setColor(new Color(Tab[5], Tab[5], 0)); //yellow
				int x9[] = {150, 150, 420, 420, 690, 690};
				int y9[] = {595, 625, 625, 700, 700, 725};

				int x10[] = {250, 250, 430, 430, 350, 350, 250, 250, 275};
				int y10[] = {595, 615, 615, 680, 680, 750, 750, 655, 655};

				int x11[] = {350, 350, 440, 440, 360, 360, 810, 810, 805};
				int y11[] = {595, 605, 605, 660, 660, 655, 655, 625, 625};

				int x12[] = {450, 450, 460, 460, 540, 540, 330, 330, 315};
				int y12[] = {595, 605, 605, 660, 660, 670, 670, 655, 655};

				int x13[] = {550, 550, 470, 470, 495, 495, 405, 405, 550, 550,  72,  72,  75};
				int y13[] = {595, 615, 615, 680, 680, 865, 865, 835, 835, 810, 810, 765, 765};

				int x14[] = {650, 650, 480, 480, 650, 650};
				int y14[] = {595, 625, 625, 700, 700, 725};

				int x15[] = {750, 750, 510, 510, 320, 320, 450, 450};
				int y15[] = {595, 600, 600, 695, 695, 630, 630, 715};
				
				g.drawPolyline(x9, y9, 6);
				g.drawPolyline(x10, y10, 9);
				g.drawPolyline(x11, y11, 9);
				g.drawPolyline(x12, y12, 9);
				g.drawPolyline(x13, y13, 13);
				g.drawPolyline(x14, y14, 6);
				g.drawPolyline(x15, y15, 8);
				}
				ColisionALL(80, 740-2, 50, 2);
			if (80-1<=x && x+Size<=130 && 740<=y-Size && y<=790+1){
				//ColisionALL(80, 740-2, 50, 2);
				ColisionALL(80-2, 740, 2, 50);
				ColisionALL(80, 790, 50, 3);				
				g.setColor(Color.white);
				g.fillRect(80, 740, 50, 50);
				g.setColor(Color.red);
				g.fillRect(125, 740, 5, 50);
				if (Colision(125, 740, 5, 50)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(75, 750, 5, 30); //#13
				}
			if (280-1<=x && x+Size<=310+1 && 630<=y-Size && y<=700){
				ColisionALL(280-2, 630, 2, 70);
				ColisionALL(310, 630, 2, 70);
				g.setColor(Color.white);
				g.fillRect(280, 630, 30, 70);
				g.setColor(Color.red);
				g.fillRect(280, 695, 30, 5);
				if (Colision(280, 695, 30, 5)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(275, 640, 5, 30); //#10
				g.fillOval(310, 640, 5, 30); //#12
				}
				//ColisionALL(410, 720-2, 80, 2);
			if (410-1<=x && x+Size<=490+1 && 720-5<=y-Size && y<=800){
				ColisionALL(410, 720-5, 80, 5);
				ColisionALL(410-2, 720, 2, 80);
				ColisionALL(490, 720, 2, 80);
				g.setColor(Color.white);
				g.fillRect(410, 720, 80, 80);
				g.setColor(Color.magenta);
				g.fillOval(435, 715, 30, 5); //#15
				}
			if (630-1<=x && x+Size<=710+1 && 730<=y-Size && y<=760){
				ColisionALL(630-2, 730, 2, 30);
				ColisionALL(710, 730, 2, 30);
				g.setColor(Color.white);
				g.fillRect(630, 730, 80, 30);
				g.setColor(Color.red);
				g.fillRect(630, 755, 80, 5);
				if (Colision(630, 755, 80, 5)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(635, 725, 30, 5); //#14
				g.fillOval(675, 725, 30, 5); //#9
				}
				ColisionALL(750, 600-2, 50, 2);
			if (750<=x && x+Size<=800+1 && 600-1<=y-Size && y<=650+1){
				//ColisionALL(750, 600-2, 50, 2);
				ColisionALL(750, 650, 50, 3);
				ColisionALL(800, 600, 2, 50);
				g.setColor(Color.white);
				g.fillRect(750, 600, 50, 50);
				g.setColor(Color.red);
				g.fillRect(750, 600, 5, 50);
				if (Colision(750, 600, 5, 50)) initP=0;
				g.setColor(Color.magenta);
				g.fillOval(800, 610, 5, 30); //#11
				}
			if (Time<10) g.setColor(Color.gray);
			else g.setColor(Color.cyan);
			for(int i=0; i<7; i++){
				g.fillOval(135+i*100, 590, 30, 5);
				if (Colision(135+i*100, 590-1, 30, 5) && Time>=10){
					if (i==0){ //#9
						x=682;
						y=730+Size;
						Tab[9]=Time=0;
						}
					else if (i==1){ //#10
						x=280;
						y=663;
						Tab[9]=Time=0;
						}
					else if (i==2){ //#11
						x=800-Size;
						y=640;
						Tab[9]=Time=0;
						}
					else if (i==3){ //#12
						x=310-Size;
						y=663;
						Tab[9]=Time=0;
						}
					else if (i==4){ //#13
						x=80;
						y=773;
						Tab[9]=Time=0;
						}
					else if (i==5){ //#14
						x=642;
						y=730+Size;
						Tab[9]=Time=0;
						}
					else if (i==6){ //#15
						x=442;
						y=720+Size;
						Tab[9]=Time=0;
						Tab[5]=255;
						}
					}
				}
//*Red Dead
			g.setColor(Color.red);
			g.fillRect(70+Tab[0], 0, 20, 110);
			if (Colision(70+Tab[0], 0, 20, 110)) initP=0;
		
			g.fillRect(810-Tab[0], 0, 20, 110);
			if (Colision(810-Tab[0], 0, 20, 110)) initP=0;

			if (Tab[7]==1 && Time==5) Tab[0]+=1;

			g.fillRect(70+Tab[1], 310, 20, 55);
			if (Colision(70+Tab[1], 310, 20, 55)) initP=0;
		
			g.fillRect(810-Tab[1], 310, 20, 55);
			if (Colision(810-Tab[1], 310, 20, 55)) initP=0;

			if (Tab[8]==1 && Time==7) Tab[1]+=2;

			g.fillRect(70+Tab[2], 565, 20, 25);
			if (Colision(70+Tab[2], 565, 20, 25)) initP=0;
		
			g.fillRect(810-Tab[2], 565, 20, 25);
			if (Colision(810-Tab[2], 565, 20, 25)) initP=0;

			if (Tab[9]==1 && Time==10) Tab[2]+=3;

			g.setColor(Color.blue);
			g.fillRect(x,y, Size, -Size);
//***Goal Level 34
			if (y==795 || y==800) Tab[7]=Tab[8]=Tab[9]=1;
			g.setColor(new Color(240, 170, 0));
			g.fillRect(435, 795, 30, 5);
			ColisionALL(435, 795, 30, 5);

			g.setColor(Color.black);
			g.fillRect(440, 745, 20, 50);
			if (x==440 && y==795){
				Level=35;
				initP=0;
				}
			}

//***Level 35
		else if (Level==35){
			if (initP==0){
				x=440;
				y=795;
				ResetTab();
				/*
				* 0 is the movement of the sliced-circle
				* 1 is the x cos position of circled blocks
				* 2 is the y sin position of circled blocks
				* 3 is red button 0
				* 5 is red button 2
				* 7 is red button 4
				* 9 is red button 6
				* 11 is red button 1
				* 13 is red button 3
				* 15 is red button 5
				* 17 is red button 7
				* 4 is the sum red button
				* 22 is the x difference on cos on the block-circle
				* 6 is the fall down of red button 0's block
				* 8 is the fall down of red button 1's block
				* 10 is the fall down of red button 2's block
				* 12 is the fall down of red button 3's block
				* 14 is the fall down of red button 4's block
				* 16 is the fall down of red button 5's block
				* 18 is the fall down of red button 6's block
				* 20 is the fall down of red button 7's block
				* 25 is the reverse circle trigger
				* 23 is the coefficient of reverse 1 or -1
				* 21 is the minus length radius of 8 first blocks
				*/
				//Tab[3]=Tab[5]=Tab[7]=Tab[9]=Tab[11]=Tab[13]=Tab[15]=Tab[17]=1;
				Tab[23]=Tab[21]=1;
				LevelColor=1;
				initP=1;
				//## Problème boutons phase 2, glissement sur bloc, ajustement colision
				}
//*orange
			g.setColor(Color.orange);
			//g.setColor(new Color(255, 128, 0));
			g.fillRect(0,0, 60, 800);
			ColisionAll(0,0, 60, 800);
	
			g.fillRect(840, 0, 60, 800);
			ColisionAll(840, 0, 60, 800);

			g.fillRect(435, 795, 30, 5);
			ColisionALL(435, 795, 30, 5);

			g.fillRect(425, 400, 50, 25);
			ColisionALL(425, 400, 50, 25);

			g.fillRect(60, 0, 780, 10);
			ColisionAll(60, 0, 780, 10);
			
			Tab[4] = Tab[3]+Tab[5]+Tab[7]+Tab[9]+Tab[11]+Tab[13]+Tab[15]+Tab[17];
			int T=1;
			if (Tab[4]>=8) T=2;
		
			for(int i=0; i<8*T; i++){
				g.setColor(Color.orange);
				if (T==1){		
					Tab[1] = 450+(int)(200*Math.cos(((Tab[0]+i*200)*3.14)/800));
					Tab[2] = 400+(int)(200*Math.sin(((Tab[0]+i*200)*3.14)/800));
					g.fillRect(Tab[1]-10, Tab[2]-10, 20, 20);
					ColisionAll(Tab[1]-10, Tab[2]-10, 20, 20);
					}
				else if (T==2 && i%2==0){
					g.setColor(Color.red);
					Tab[1] = 450+Tab[23]*(int)((200-Tab[21])*Math.cos(((Tab[0]+i*100)*3.14)/800));
					Tab[2] = 400+(int)((200-Tab[21])*Math.sin(((Tab[0]+i*100)*3.14)/800));
					g.fillRect(Tab[1]-10, Tab[2]-10, 20, 20);
					//if (Tab[21]<90 && Tab[4]%2==0) Tab[21]+=1;
					//if (Tab[21]>0 && Tab[4]%2==1) Tab[21]-=1;
					if ((Tab[21]==90 || Tab[21]==0) && Colision(Tab[1]-11, Tab[2]-11, 22, 22)) initP=0;
					}
				else if (T==2 && i%2==1){
					Tab[1] = 450+Tab[23]*(int)((200+Tab[21])*Math.cos(((Tab[0]+i*100)*3.14)/800));
					Tab[2] = 400+(int)((200+Tab[21])*Math.sin(((Tab[0]+i*100)*3.14)/800));
					g.fillRect(Tab[1]-10, Tab[2]-10, 20, 20);
					ColisionAll(Tab[1]-10, Tab[2]-10, 20, 20);
					ColisionAll(Tab[1]-10, Tab[2]-10, 20, 20);
					}
				if (Sustain(Tab[1]-10, Tab[2]-10, 20, 10) && Saut==0){
					switch(i){
						case 0:Tab[6]=1;break;//
						case 1:Tab[8]=1;break;//
						case 2:Tab[10]=1;break;//
						case 3:Tab[12]=1;break;//
						case 4:Tab[14]=1;break;//
						case 5:Tab[16]=1;break;//
						case 6:Tab[18]=1;break;//
						case 7:Tab[20]=1;break;//problème
						}
					//Tab[6+i*2]=1;
					if (Tab[22]==0) Tab[22] = x-(Tab[1]-10);
					if (MotionR==1) Tab[22]+=1;
					if (MotionL==1) Tab[22]-=1;
					//if (info==1) System.out.println(i+"   "+Tab[6+i*2]+"   "+(6+i*2));
					//x = Tab[1]-10 + Tab[22];
					if (T==1){
						x = 450+(int)(200*Math.cos(((Tab[0]+i*200)*3.14)/800)) + Tab[22]-10;
						y = 400+(int)(200*Math.sin(((Tab[0]+i*200)*3.14)/800)) -10;
						}
					else if (T==2){
						x = 450+Tab[23]*(int)((200+Tab[21])*Math.cos(((Tab[0]+i*100)*3.14)/800)) + Tab[22]-10;
						y = 400+(int)((200+Tab[21])*Math.sin(((Tab[0]+i*100)*3.14)/800)) -10;
						}
					if (Saut==1){
						y-=4;
						break;
						}
					//y = Tab[2]-10;
					}
				else if (Tab[6+i*2]==1){
					Tab[6+i*2]=0;
					Tab[22]=0;
					//if (info==1) System.out.println("Pas bon");
					}
				g.setColor(Color.blue);
				g.drawLine(450, 400, Tab[1], Tab[2]);
				if (T==1){
					if (i%2==0){
						if (Tab[i+3]!=1) g.fillRect(Tab[1]+7, Tab[2]-3, 6, 6);
						if (Tab[i+3]!=1 && Colision(Tab[1]+7, Tab[2]-3, 6, 6)) Tab[i+3]=Tab[25]=1;
						}
					else{
						if (Tab[i+10]!=1) g.fillRect(Tab[1]-13, Tab[2]-3, 6, 6);
						if (Tab[i+10]!=1 && Colision(Tab[1]-13, Tab[2]-3, 6, 6)) Tab[i+10]=Tab[25]=1;
						}
					}
				else if (T==2 && i%2==1){
					if (Tab[i+2]!=2){
						g.fillRect(Tab[1]-3, Tab[2]-13, 6, 6);
						if (Colision(Tab[1]-3, Tab[2]-13, 6, 6)){
							Tab[i+2]=2;
							Tab[25]=1;
							}
						}
					}
				}
			if (T==2 && Tab[21]<90 && Tab[4]%2==0) Tab[21]+=1;
			if (T==2 && Tab[21]>0 && Tab[4]%2==1) Tab[21]-=1;
			//if (Tab[40]==1 && Tab[25]==0) Tab[0]+=1; //key T
			if (Tab[4]%2==0 && T==1){
				Tab[0]+=1;
				if (Tab[0]==1600) Tab[0]=0;
				}
			else if (T==1){
				Tab[0]-=1;
				if (Tab[0]==0) Tab[0]=1600;
				}
			else if (T==2){
				if (Tab[25]==0 && Tab[4]!=16) Tab[0]+=1;
				if (Tab[25]==1 && Tab[0]>0) Tab[0]-=2;
				else if (Tab[0]<=0){
					Tab[25]=Tab[0]=0;
					Tab[23]=-1*Tab[23];
					}
				if (Tab[0]==1600) Tab[0]=0;
				}

			for(int i=0; i>100; i++){
				for(int j=0; j<100; j++){
					g.setColor(new Color(i*2, 255-j, 255-i));
					int R=100;
					int A1=450-i;
					int A2=450+i;
					int B1=400-j;
					//int B2=400+j;
					int k=10;
					if (i%k==0 && j%k==0 && (A1-450)*(A1-450) + (B1-400)*(B1-400) <= R*R){
						g.drawLine(450, 400, A1, B1);
						g.drawLine(450, 400, 900-A1, 800-B1);
						}
					if (i%k==0 && j%k==0 && (A2-450)*(A2-450) + (B1-400)*(B1-400) <= R*R){
						g.drawLine(450, 400, A2, B1);
						g.drawLine(450, 400, 900-A2, 800-B1);
						}
					//break;
					}
				}
			for(int i=0; i>110; i++){
				for(int j=0; j<110; j++){
					int R=105;
					int R1=110;
					int A1=450-i;
					int A2=450+i;
					int B1=400-j;
					//int A1=450+(int)(150*Math.sin((Tab[0]*3.14)/500));
					//int A2=450+(int)(150*Math.sin((Tab[0]*3.14)/500));
					//int B1=400+(int)(100*Math.sin((Tab[0]*3.14)/500));
					g.setColor(Color.red);
					int A = (A1-450)*(A1-450) + (B1-400)*(B1-400);
					int B = (A2-450)*(A2-450) + (B1-400)*(B1-400);
					int k=2;
					if (i%k==0 && j%k==0 && R*R<=A && A<=R1*R1){
						g.drawLine(A1+5, B1+5, A1, B1);
						g.drawLine(900-A1-5, 800-B1-5, 900-A1, 800-B1);
						//g.drawLine(450, 400, A1, B1);
						//g.drawLine(450, 400, 900-A1, 800-B1);
						}
					if (i%k==0 && j%k==0 && R*R<=B && B<=R1*R1){
						g.drawLine(A2-5, B1+5, A2, B1);
						g.drawLine(900-A2+5, 800-B1-5, 900-A2, 800-B1);
						//g.drawLine(450+R/2, 400-R/2, A2, B1);
						//g.drawLine(450-R/2, 400+R/2, 900-A2, 800-B1);
						}
					//break;
					}
				}
//*Portals
			for (int i=0; i<7; i++){
				g.setColor(Color.cyan);
				g.fillOval(70+i*50, 800, 30, 5);
				g.fillOval(500+i*50, 800, 30, 5);
				if (Colision(70+i*50, 800-1, 30, 5)) y=30+Size;
				if (Colision(500+i*50, 800-1, 30, 5)) y=30+Size;
				g.setColor(Color.magenta);
				g.fillOval(70+i*50, 5, 30, 5);
				g.fillOval(500+i*50, 5, 30, 5);
				}
//*Red Dead
			g.setColor(Color.red);
			//for (int i=0; i<51; i++) g.drawOval(300-i, 250-i, 300+i*2, 300+i*2);
			//g.setColor(Color.red);
			//g.drawOval(300, 250, 300, 300);
			if (Tab[4]!=16){
				g.drawOval(340, 290, 220, 220);
				if (CircleColision(450, 400, 110)) initP=0;
				}
//***Goal Level 35
			g.setColor(Color.black);
			g.fillRect(440, 350, 20, 50);
			if (x==440 && y==400){
				Level=36;
				initP=0;
				LevelColor=0;
				}
			}
//***Level 36
		else if (Level==36){
			if (initP==0){
				x=440;
				y=400;
				ResetTab();
				/*
				* 0 is the random number teleportation
				* 1 is yellow portal 1
				* 2 is yellow portal 2
				* 3 is yellow portal 3
				* 4 is yellow portal 4
				* 5 is yellow portal 5
				* 6 is yellow portal 6
				* 7 is yellow portal 7
				* 8 is yellow portal 8
				* 9 is yellow portal 9
				* 10 is yellow portal 10
				* 11 is yellow portal 11
				* 12 is yellow portal 12
				* 13 is red button 1
				* 14 is red button 2
				* 15 is red button 3
				* 16 is green button 1
				* 17 is x cos position of red block left room
				* 18 is x cos difference of red block left room
				* 19 is y of block with blue step 
				* 20 is the x cos movement of red block left room
				* 21 is the x cos difference on sustained
				* 22 is the x position of move block 1 right room
				* 23 is the x trigger of move block 1 right room
				* 24 is the x position of move block 2 right room
				* 25 is the x trigger of move block 2 right room
				* 26 is the y position of red block right room
				* 27 is the y trigger of red block right room
				* 28 is green button 2
				*/
				Tab[19]=790;
				Tab[22]=800;
				Tab[24]=750;
				Tab[26]=300;
				Time=TimeOn=0;
				initP=1;
				}
//*orangy slightly red
			g.setColor(new Color(250, 100, 0));
			g.fillRect(0,0, 50, 800);
			ColisionAll(0,0, 50, 800);
	
			g.fillRect(850, 0, 50, 800);
			ColisionAll(850, 0, 50, 800);

			g.fillRect(325, 0, 25, 800);
			ColisionALL(325, 0, 25, 800);

			g.fillRect(550, 0, 25, 800);
			ColisionALL(550, 0, 25, 800);
	
			g.fillRect(50, 0, 800, 5);
			ColisionAll(50, 0, 800, 5);

			g.fillRect(425, 400, 50, 20);
			ColisionALL(425, 400, 50, 20);

			g.fillRect(430, 0, 40, 20);
			ColisionAll(430, 0, 40, 20);

			g.fillRect(350, 380, 200, 5);
			ColisionALL(350, 380, 200, 5);

			g.fillRect(350, 415, 75, 5);
			ColisionALL(350, 415, 75, 5);
			g.fillRect(475, 415, 75, 5);
			ColisionALL(475, 415, 75, 5);
		
			g.setColor(Color.red);
			g.fillRect(350, 370, 200, 10);
			if (Colision(350, 370, 200, 10)) initP=0;
//*Left room
			g.setColor(new Color(250, 100, 0));
			g.fillRect(50, 80, 80, 20);
			ColisionALL(50, 80, 80, 20);

			g.fillRect(130, 0, 20, 120);
			ColisionAll(130, 0, 20, 120);

			g.fillRect(150, 100, 125, 20);
			ColisionAll(150, 100, 125, 20);

			g.fillRect(275, 100, 20, 180);
			ColisionALL(275, 100, 20, 180);

			g.fillRect(305, 330, 20, 70);
			ColisionALL(305, 330, 20, 70);

			g.fillRect(130, 400, 195, 30);
			ColisionALL(130, 400, 195, 30);

			g.fillRect(50, 380, 20, 20);
			ColisionALL(50, 380, 20, 20);

			g.fillRect(50, 230, 80, 15);
			ColisionALL(50, 230, 80, 15);

			g.fillRect(90, Tab[19], 20, 10);
			ColisionALL(90, Tab[19], 20, 10);
			g.setColor(Color.blue);
			g.fillRect(90+15, Tab[19], 5, 2);
			if (Sustain(90+15, Tab[19], 5, 2) && Tab[19]>400){
				Tab[19]-=1;
				y-=1;
				}
			else if (Tab[19]<790 && Sustain(90+15, Tab[19], 5, 2)==false) Tab[19]+=1;

//*red left room
			g.setColor(Color.red);
			g.fillRect(150, 90, 125, 10);
			if (Colision(150, 90, 125, 10)) initP=0;

			if (Tab[16]==0 && Colision(Tab[17]-1, 480-1, 15+2, 30+2)) initP=0;
			else if (Tab[16]==1) g.setColor(Color.green);
			Tab[17]=200+(int)(90*Math.cos((Tab[20]*3.14)/800));
			g.fillRect(Tab[17], 480, 15, 30);
			ColisionALL(Tab[17], 480, 15, 30);
			//WallStickAll(Tab[17], 480, 15, 30);//*
			if (Sustain(Tab[17], 480, 15, 5)){
				if (Tab[21]==0) Tab[21]=x-Tab[17];
				x=200+(int)(90*Math.cos((Tab[20]*3.14)/800)) + Tab[21];
				if (MotionR==1) Tab[21]+=1;
				if (MotionL==1) Tab[21]-=1;
				}
			else Tab[21]=0;
			//if (WallStick(Tab[17], 480, 15, 5)
			Tab[20]+=1;
//*green left room
			//if (info==1) System.out.println("1:"+Time);
			g.setColor(Color.green); //16

//*yellow left room
			g.setColor(Color.yellow);
			g.fillOval(145, 5, 5, 30);
			if (Colision(145, 5, 5+1, 30)) Tab[0]=1+(int)(Math.random()*12);
			g.fillOval(305, 370, 5, 30);
			if (Colision(305-1, 370, 5, 30)) Tab[0]=1+(int)(Math.random()*12);
			g.fillOval(325, 750, 5, 30);
			if (Colision(325-1, 750, 5, 30)) Tab[0]=1+(int)(Math.random()*12);

			g.drawLine(150, 35, 325, 80);
			if (LineOn(150, 35, 325, 80) && x+Size<325){
				x+=1;
				Slide(150, 35, 325, 80);
				}
			g.drawLine(275, 120, 50, 310);
			if (LineOn(275, 120, 50, 310) && x+Size<275){
				x+=1;
				Slide(275, 120, 50, 310);
				}
			g.drawLine(275, 175, 50, 100);
			if (LineOn(275, 175, 50, 100) && x>50){
				x-=1;
				Slide(275, 175, 50, 100);
				}
//*Right room
			g.setColor(new Color(250, 100, 0));
			g.fillRect(780, 770, 20, 10);
			ColisionALL(780, 770, 20, 10);

			g.fillRect(Tab[22], 670, 20, 10);
			ColisionALL(Tab[22], 670, 20, 10);
			if (Tab[23]==1) Tab[22]+=1;
			if (Tab[23]==0) Tab[22]-=1;
			if (Tab[22]+20==830) Tab[23]=0;
			if (Tab[22]==650) Tab[23]=1;
			if (Sustain(Tab[22], 670, 20, 10)){
				if (Tab[23]==1) x+=1;
				if (Tab[23]==0) x-=1;
				}
			g.fillRect(Tab[24], 580, 20, 10);
			ColisionALL(Tab[24], 580, 20, 10);	
			if (Tab[25]==1) Tab[24]+=1;
			if (Tab[25]==0) Tab[24]-=1;
			if (Tab[24]+20==830) Tab[25]=0;
			if (Tab[24]==620) Tab[25]=1;		
			if (Sustain(Tab[24], 580, 20, 10)){
				if (Tab[25]==1) x+=1;
				if (Tab[25]==0) x-=1;
				}
			g.fillRect(565, 540, 192, 20);
			ColisionALL(565, 540, 192, 20);

			g.fillRect(565, 560, 35, 80);
			ColisionAll(565, 560, 35, 80);

			g.fillRect(687, 5, 30, 5);
			ColisionAll(687, 5, 30, 5);

			g.fillRect(600, 250, 87, 20);
			ColisionALL(600, 250, 87, 20);

			g.fillRect(600, 270, 20, 40);
			ColisionAll(600, 270, 20, 40);

			g.fillRect(600, 310, 87, 20);
			ColisionALL(600, 310, 87, 20);

			g.fillRect(670, 330, 17, 70);
			ColisionAll(670, 330, 17, 70);

			g.fillRect(580, 5, 10, 5);
			ColisionAll(580, 5, 10, 5);

//*red right room		
			g.setColor(Color.red);
			if (Tab[28]==1) g.setColor(Color.green);
			if (Tab[28]==0 && Colision(775-1, Tab[26]-1, 15+2, 30+2)) initP=0;
			g.fillRect(775, Tab[26], 15, 30);
			ColisionALL(775, Tab[26], 15, 30);
			//WallStickAll(775, Tab[26], 15, 30);//**
			if (Tab[27]==1) Tab[26]+=1;
			if (Tab[27]==0) Tab[26]-=1;
			if (Tab[26]==30) Tab[27]=1;
			if (Tab[26]==330) Tab[27]=0;
			if (Sustain(775, Tab[26], 15, 30) || WallStick(775, Tab[26], 15, 30)){
				if (Tab[27]==1) y+=1;
				if (Tab[27]==0) y-=1;
				}
				
//*yellow right room
			g.setColor(Color.yellow);
			g.fillOval(687, 5, 30, 5);
			if (Colision(687, 5+1, 30, 5+1)) Tab[0]=1+(int)(Math.random()*12);
			g.fillOval(615, 270, 5, 30);
			if (Colision(615, 270, 5+1, 30)) Tab[0]=1+(int)(Math.random()*12);
			g.fillOval(570, 750, 5, 30);
			if (Colision(570, 750, 5+1, 30)) Tab[0]=1+(int)(Math.random()*12);

			g.drawLine(585, 10, 585, 540);
			if (LineColision(585, 10, 585, 540) && y>10) y-=2;
//*green right room
			g.setColor(Color.green);
			for(int i=0; i<4; i++){
				g.fillRect(440, 64+84*i, 20, 40);
				ColisionALL(440, 64+84*i, 20, 40);
				WallStickAll(440, 64+84*i, 20, 40);
				g.fillRect(440, 464+84*i, 20, 40);
				ColisionALL(440, 464+84*i, 20, 40);
				WallStickAll(440, 464+84*i, 20, 40);
				}

			g.fillRect(850, 705, 5, 50);
			WallStickAll(850, 705, 5, 50);

			g.fillRect(595, 600, 5, 10);
			WallStickAll(595, 600, 5, 10);

			g.fillRect(655, 555, 10, 5);
			WallStickAll(655, 555, 10, 5);

///****
			if (Tab[16]==0){
				g.fillRect(60, 96, 8, 8);
				if (Colision(60, 96, 8, 8)) Tab[16]=1;
				}
			else if (Tab[16]==1 && Time<10){
				g.fillRect(60, 92, 8, 8);
				if (TimeOn==0){
					TimeOn=1;
					new Time().start();
					}
				}
			else if (Time==10) Time=TimeOn=Tab[16]=0;
			//if (info==1) System.out.println("2:"+Time);



			if (Tab[28]==0){
				g.fillRect(581, 6, 8, 8);
				if (Colision(581, 6, 8, 8)) Tab[28]=1;
				}
			else if (Tab[28]==1 && Time<10){
				g.fillRect(581, 2, 8, 8);
				if (TimeOn==0){
					TimeOn=1;
					new Time().start();
					}
				}
			else if (Time==10) Time=TimeOn=Tab[28]=0;



//*Portal
			for(int i=0; i<3; i++){
				g.setColor(new Color(250, 100, 0));
				g.fillRect(360, 55+i*330, 20, 30);
				g.fillRect(520, 55+i*330, 20, 30);
				ColisionALL(360, 55+i*330, 20, 30);
				ColisionALL(520, 55+i*330, 20, 30);
				g.setColor(Color.yellow);
				g.fillOval(375, 55+i*330, 5, 30);
				g.fillOval(520, 55+i*330, 5, 30);
				if (Colision(375+1, 55+i*330, 5, 30) || Colision(520-1, 55+i*330, 5, 30)){
					Tab[0]=1+(int)(Math.random()*12);
					}
				}
			if (Tab[0]!=0){
				switch(Tab[0]){
					case 1:x=151;y=35;break;
					case 2:x=305-Size;y=400;break;
					case 3:x=325-Size;y=750;break;
					case 4:x=380;y=85;break;
					case 5:x=380;y=415;break;
					case 6:x=380;y=745;break;
					case 7:x=520-Size;y=85;break;
					case 8:x=520-Size;y=415;break;
					case 9:x=520-Size;y=745;break;
					case 10:x=700-Size;y=10+Size;break;
					case 11:x=620;y=300;break;
					case 12:x=575;y=750;break;
					}
				Tab[0]=0;
				}

			if (Tab[13]!=1 || Tab[14]!=1 || Tab[15]!=1) g.setColor(Color.gray);
			else{
				g.setColor(Color.cyan);
				if (Colision(435, 415, 30, 5+2)){
					x=65-Size;
					y=0+Size;
					}
				}
			g.fillOval(435, 415, 30, 5);
			g.setColor(Color.magenta);
			g.fillOval(50, 0, 30, 5);
//*Red button
			g.setColor(Color.red);
			if (Tab[13]==0){
				g.fillRect(446, 16, 8, 8);
				if (Colision(446, 16, 8, 8)) Tab[13]=1;
				}
			else g.fillRect(446, 12, 8, 8);

			if (Tab[14]==0){
				g.fillRect(322, 426, 8, 8);
				if (Colision(322, 426, 8, 8)) Tab[14]=1;
				}
			else g.fillRect(322, 422, 8, 8);

			if (Tab[15]==0){
				g.fillRect(846, 30, 8, 8);
				if (Colision(846, 30, 8, 8)) Tab[15]=1;
				}
			else g.fillRect(850, 30, 8, 8);
//***Goal Level 36
			g.setColor(Color.black);
			g.fillRect(80, 30, 20, 50);
			if (x==80 && y==80){
				Level=37;
				initP=0;
				}

			WallStickAll(Tab[17], 480, 15, 30); //*
			WallStickAll(775, Tab[26], 15, 30);//**
			}
//***Level 37
		else if (Level==37){
			if (initP==0){
				x=80;
				y=80;
				ResetTab();
				/*
				* 1 y position of red block fifth room
				* 2 red button, trigger red block to vanish last room
				*/
				Tab[1]=800;
				initP=1;
				}
//*fading orange risingly red
			g.setColor(new Color(250, 80, 0));
			g.fillRect(0,0, 40, 800);
			ColisionAll(0,0, 40, 800);
	
			g.fillRect(860, 0, 40, 800);
			ColisionAll(860, 0, 40, 800);

			for(int i=0; i<5; i++){
				g.fillRect(170+146*i, 0, 16, 800);
				ColisionAll(170+146*i, 0, 16, 800);
				}
			for(int i=0; i<5; i++){
				g.setColor(Color.cyan);
				g.fillOval(90+i*146, 800, 30, 5);
				if (Colision(90+i*146, 800-1, 30, 5)){
					x+=146;
					y=0+Size;
					}
				g.setColor(Color.magenta);
				g.fillOval(90+(i+1)*146, 0, 30, 5);
				}
//*Red dead
			g.setColor(Color.red);
			for(int i=0; i<5; i++){
				g.fillRect(40+i*146, 795, 50, 5);	
				g.fillRect(120+i*146, 795, 50, 5);
				if (Colision(40+i*146, 795, 50, 5) || Colision(120+i*146, 795, 50, 5)) initP=0;
				}
//*room 1
			g.fillRect(40, 120, 20, 20);
			if (Colision(40, 120, 20, 20)) initP=0;
			
			g.fillRect(150, 200, 20, 20);
			if (Colision(150, 200, 20, 20)) initP=0;

			g.fillRect(90, 300, 30, 40);
			if (Colision(90, 300, 30, 40)) initP=0;

			g.fillRect(40, 450, 20, 20);
			g.fillRect(100, 450, 70, 20);
			if (Colision(40, 450, 20, 20) || Colision(100, 450, 70, 20)) initP=0;


			g.fillRect(40, 650, 70, 20);
			g.fillRect(150, 650, 20, 20);
			if (Colision(40, 650, 70, 20) || Colision(150, 450, 20, 20)) initP=0;
//*room 2
			for(int i=0; i<2; i++){
				g.fillRect(186, 80+i*60, 50, 30);
				g.fillRect(261, 80+i*60, 50, 30);
				if (Colision(186, 80+i*60, 50, 30) || Colision(261, 80+i*60, 50, 30)) initP=0;
				g.fillRect(186, 330+i*60, 20, 30);
				g.fillRect(231, 330+i*60, 80, 30);
				if (Colision(186, 330+i*60, 20, 30) || Colision(231, 330+i*60, 80, 30)) initP=0;
				g.fillRect(186, 580+i*60, 90, 30);
				g.fillRect(306, 580+i*60, 10, 30);
				if (Colision(186, 580+i*60, 90, 30) || Colision(306, 580+i*60, 10, 30)) initP=0;
				}
//*room 3
			for(int i=0; i<4; i++){
				g.fillRect(332+i*25, 90+i*75, 25, 100);
				if (Colision(332+i*25, 90+i*75, 25, 100)) initP=0;
				g.fillRect(462-40-20*i, 500+i*25, 40, 25);
				if (Colision(462-40-20*i, 500+i*25, 40, 25)) initP=0;
				}
			g.fillRect(332, 750, 50, 20);
			g.fillRect(412, 750, 50, 20);
			if (Colision(332, 750, 50, 20) || Colision(412, 750, 50, 20)) initP=0;
//*room 4

			int [] x1= {505, 535, 608, 578};
			int [] y1= {300, 330, 108, 78};
			g.setColor(Color.blue);
			g.drawPolygon(x1, y1, 4);
			g.setColor(Color.red);
			g.fillPolygon(x1, y1, 4);
			if (PolygonColision(x1,y1)) initP=0;
		
			int [] x2 = {478, 533, 588, 533};
			int [] y2 = {500, 555, 500, 445};
			g.fillPolygon(x2, y2, 4);
			if (PolygonColision(x2,y2)) initP=0;
//*room 5
			if (624<=x && x+Size<=738){
				g.fillRect(624+(738-x) , Tab[1], Size, 20);
				if (Colision(624+(738-x) , Tab[1], Size, 20)) initP=0;
				Tab[1]-=1;
				}
//*room 6
			if (Tab[2]==0){
				g.fillRect(856, 400, 8, 8);
				if (Colision(856, 400, 8, 8)) Tab[2]=1;
				g.fillRect(770, 430, 90, 30);
				if (Colision(770, 430, 90, 30)) initP=0;
				}
			if (Tab[2]==1) g.fillRect(860, 400, 8, 8);
//***Goal Level 37
			g.setColor(Color.black);
			g.fillRect(805, 750, 20, 50);
			if (x==805 && y==800){
				Level=38;
				initP=0;
				}
			}
//***Level 38
		else if (Level==38){
			if (initP==0){
				x=805;
				y=800;
				ButtonOn1=0; //Cumulatif
				ButtonOn2=0; //Colision point room #6 portal
				P1y=790; // y sustain room #2
				P1Trigger=0; //colision point room #2
				P2x=770;  //x circle room #5
				P2y=670;	//y circle room #5
				P3Trigger=0; //x difference room #5
				Pmove=0;
				P1x=190; //x P room #4
				P1Tour=0; //tour room #4
				P2Tour=0;
				P3Tour=0;
				P3x=0;
				P3y=0;
				P2Trigger=0;
				initP=1;
				}
//*falling orange great red
			g.setColor(new Color(250, 60, 0));
			g.fillRect(0,0, 30, 800);
			ColisionAll(0,0, 30, 800);
	
			g.fillRect(870, 0, 30, 800);
			ColisionAll(870, 0, 30, 800);

			g.fillRect(30, 0, 840, 15);
			ColisionALL(30, 0, 840, 15);

			g.fillRect(30, 100, 840, 10);
			ColisionALL(30, 100, 840, 10);

			g.setColor(new Color(250, 220, 0));
			for(int i=0; i<4; i++){
				g.fillRect(225+i*140, 110, 30, 10); //room #4-10-12-6
				ColisionALL(225+i*140, 110, 30, 10);
				}
//*room #3
			g.setColor(new Color(250, 220, 0));
			g.fillRect(30, 770, 10, 30);
			ColisionALL(30, 770, 10, 30);

			g.fillRect(30, 750, 30, 20);
			ColisionALL(30, 750, 30, 20);
			
			g.fillRect(60, 590, 20, 180);
			ColisionALL(60, 590, 20, 180);
			
			g.setColor(Color.green);
			for(int i=0; i<3; i++){
				g.fillRect(75, 610+i*70, 5, 15);
				WallStickAll(75, 610+i*70, 5, 15);
				}
			g.setColor(Color.magenta); 
			g.fillOval(35, 770, 5, 30); //#3
			g.setColor(Color.cyan); 
			g.fillOval(30, 750, 30, 5); //#25
			if (Colision(30, 750-1, 30, 5)){
				x=(135+0*60)-6;
				y=385+Size;
				}
//*room #11
			g.setColor(new Color(250, 220, 0));
			g.fillRect(200, 580, 110, 10);
			ColisionALL(200, 580, 110, 10);

			g.fillRect(300, 550, 10, 30);
			ColisionALL(300, 550, 10, 30);

			g.fillRect(240, 740, 16, 10);
			ColisionALL(240, 740, 16, 10);

			g.fillRect(294, 680, 16, 10);
			ColisionALL(294, 680, 16, 10);

			g.fillRect(240, 620, 16, 10);
			ColisionALL(240, 620, 16, 10);

			g.fillRect(170, 660, 16, 10);
			ColisionALL(170, 660, 16, 10);

			g.fillRect(300, 770, 10, 30);
			ColisionALL(300, 770, 10, 30);

			g.setColor(Color.magenta);
			g.fillOval(300, 770, 5, 30); //#11
			g.setColor(Color.cyan);
			g.fillOval(300, 550, 5, 30); //#24
			if (Colision(300-1, 550, 5, 30)){
				x=(135+1*60)-6;
				//x=195-6;
				y=385+Size;
				}
//*room 13
			g.setColor(new Color(250, 220, 0));
			g.fillRect(310, 770, 10, 30);
			ColisionALL(310, 770, 10, 30);

			g.fillRect(310, 660, 30, 15);
			ColisionALL(310, 660, 30, 15);

			g.fillRect(340, 580, 10, 95);
			ColisionALL(340, 580, 10, 95);

			g.fillRect(340, 570, 90, 10);
			ColisionALL(340, 570, 90, 10);

			g.fillRect(430, 695, 20, 10);
			ColisionALL(430, 695+1, 20, 10-1);

			g.fillRect(380, 695, 10, 50);
			ColisionALL(380, 695+1, 10, 50-1);

			g.fillRect(420, 695, 10, 50);
			ColisionALL(420, 695+1, 10, 50-1);

			g.fillRect(380, 745, 50, 10);
			ColisionALL(380, 745, 50, 10);

			if (P2Tour==0){
				g.fillRect(390, 695, 30, 10);
				ColisionALL(390, 695, 50, 10);
				g.setColor(Color.orange);
				g.drawLine(440, 550, 440, 695);
				if (x<=440 && 440<=x+Size && 550<=y-Size && y<=695) y-=2;
				}
			g.setColor(Color.green);
			g.fillRect(380, 720, 5, 15);
			WallStickAll(380, 720, 5, 15);

			g.setColor(Color.magenta);
			g.fillOval(315, 770, 5, 30);
			g.setColor(Color.cyan);
			g.fillOval(390, 745, 30, 5); //#23
			if (Colision(390, 745-1, 30, 5)){
				x=(135+2*60)-6;
				//x=255-6;
				y=385+Size;
				}
//*room #2
			g.setColor(new Color(250, 220, 0));
			g.fillRect(580, 770, 10, 30);
			ColisionALL(580, 770, 10, 30);

			g.fillRect(550, 730, 40, 10);
			ColisionALL(550, 730, 40, 10);

			g.fillRect(550, 690, 10, 40);
			ColisionALL(550, 690+1, 10, 40-1);

			g.fillRect(470, P1y, 12, 10);
			ColisionALL(470, P1y, 12, 10);
			if (Sustain(470, P1y, 12, 2)){
				if (P1y-Size-1>550){
					P1y-=2;
					y-=2;
					}
				}
			else if (P1y<790) P1y+=1;
			if (ColisionPoint(476, 555)) P1Trigger=1;

			if (P1Trigger==0){
				g.fillRect(560, 690, 30, 10);
				ColisionALL(560, 690, 30, 10);
				}
			g.setColor(Color.magenta);
			g.fillOval(580, 770, 5, 30);
			g.setColor(Color.cyan);
			g.fillOval(560, 730, 30, 5); //#22
			if (Colision(560, 730-1, 30, 5)){
				P1Trigger=0;
				x=(135+3*60)-6;
				//x=315-6;
				y=385+Size;
				}
//*room #8
			g.setColor(new Color(250, 220, 0));
			g.fillRect(590, 770, 10, 30);
			ColisionALL(590, 770, 10, 30);

			g.fillRect(720, 550, 10, 250);
			ColisionALL(720, 550, 10, 250);

			g.fillRect(670, 580, 50, 10);
			ColisionALL(670, 580, 50, 10);

			g.setColor(Color.orange);
			g.drawLine(660, 550, 660, 800-1);
			if (x<=660 && 660<=x+Size && 550<=y-Size && y<=800) y-=2;

			g.setColor(Color.magenta);
			g.fillOval(595, 770, 5, 30); //#8
			g.setColor(Color.cyan);
			g.fillOval(720, 550, 5, 30); //#21
			if (Colision(720-1, 550, 5, 30)){
				x=(135+4*60)-6;
				//x=375-6;
				y=385+Size;
				}
			
//*room #5
			g.setColor(new Color(250, 220, 0));
			g.fillRect(860, 770, 10, 30);
			ColisionALL(860, 770, 10, 30);

			g.fillRect(830, 570, 10, 200);
			ColisionALL(830, 570, 10, 200);

			g.fillRect(840, 760, 30, 10);
			ColisionALL(840, 760, 30, 10);

			g.fillRect(P2x, P2y, 20, 10);
			ColisionALL(P2x, P2y, 20, 10);

			P2x=770+(int)(40*Math.cos((Pmove*3.14)/300));
			P2y=670+(int)(100*Math.sin((Pmove*3.14)/300));
			if (730<=x && x+Size<=870 && 550<=y-Size && y<=800) Pmove+=1;
			if (Sustain(P2x, P2y, 20, 3)){
				if (P3Trigger==0) P3Trigger=x-P2x;
				x=770+(int)(40*Math.cos((Pmove*3.14)/300)) +P3Trigger;
				y=670+(int)(100*Math.sin((Pmove*3.14)/300));
				if (MotionR==1) P3Trigger+=1;
				if (MotionL==1) P3Trigger-=1;
	
				ColisionALL(830, 570, 10, 200);
				ColisionALL(840, 760, 30, 10);
				}
			else P3Trigger=0;

			g.setColor(Color.magenta);
			g.fillOval(860, 770, 5, 30); //#5
			g.setColor(Color.cyan);
			g.fillOval(835, 730, 5, 30); //#20
			if (Colision(835+1, 730, 5, 30)){
				x=(135+5*60)-6;
				y=385+Size;
				}
//*room #7
			g.setColor(new Color(250, 220, 0));
			g.fillRect(860, 110, 10, 30);
			ColisionALL(860, 110, 10, 30);

			g.fillRect(735, 350, 30, 10);
			ColisionALL(735, 350, 30, 10);

			g.fillRect(795, 355, 15, 5);
			ColisionALL(795, 355, 15, 5);

			g.setColor(Color.green);
			g.fillRect(820, 110, 5, 200);
			ColisionALL(820, 110, 5, 200);
			WallStickAll(820, 110, 5, 200);

			g.fillRect(770, 140, 5, 220);
			ColisionALL(770, 140, 5, 220);
			WallStickAll(770, 140, 5, 220);
			
			g.setColor(Color.magenta);
			g.fillOval(860, 110, 5, 30); //#7
			g.setColor(Color.cyan);
			g.fillOval(735, 350, 30, 5); //#19
			if (Colision(735, 350-1, 30, 5)){
				x=(135+6*60)-6;
				y=385+Size;
				}
//*room #6
			g.setColor(new Color(250, 220, 0));
			g.fillRect(635, 210, 50, 50);
			ColisionALL(635, 210, 50, 50);

			g.fillRect(605, 357, 3, 3);
			ColisionALL(605, 357, 3, 3);
			if (Sustain(605, 357, 3, 3)) ButtonOn2=1;

			if (ButtonOn2==1){
				g.fillRect(630, 345, 70, 10);
				ColisionALL(630, 345, 70, 10);

				g.fillRect(690, 280, 10, 65);
				ColisionALL(690, 280, 10, 65);

				g.setColor(Color.cyan);
				g.fillOval(695, 285, 5, 30); //#18
				if (Colision(695+1, 285, 5, 30)){
					x=(135+7*60)-6;
					y=385+Size;
					}
				}
			g.setColor(Color.magenta);
			g.fillOval(645, 115, 30, 5); //#6

//*room #12
			g.setColor(new Color(250, 220, 0));
			g.fillRect(450, 330, 30, 30);
			ColisionALL(450, 330, 30, 30);

			g.fillRect(480, 350, 30, 10);
			ColisionALL(480, 350, 30, 10);

			g.setColor(Color.orange);
			for(int i=0; i<3; i++){
				g.drawLine(450, 110+i*80, 590, 160+i*80);
				if (LineOn(450, 110+i*80, 590, 160+i*80) && Down==0){
					Slide(450, 110+i*80, 590, 160+i*80);
					x+=1;
					}
				}
			g.setColor(Color.magenta);
			g.fillOval(505, 115, 30, 5); //#12
			g.setColor(Color.cyan);
			g.fillOval(480, 350, 30, 5); //#17
			if (Colision(480, 350-1, 30, 5)){
				x=(135+8*60)-6;
				y=385+Size;
				}
//*room #10
			g.setColor(new Color(250, 220, 0));
			g.fillRect(420, 330, 30, 30);
			ColisionALL(420, 330, 30, 30);

			g.fillRect(417, 357, 3, 3);
			ColisionALL(417, 357, 3, 3);

			g.fillRect(320, 310, 10, 30);
			ColisionALL(320, 310, 10, 30);

			g.fillRect(320, 340, 50, 10);
			ColisionALL(320, 340, 50, 10);

			g.setColor(Color.magenta);
			g.fillOval(365, 115, 30, 5); //#10
			g.setColor(Color.cyan);
			g.fillOval(325, 310, 5, 30); //#16
			if (Colision(325+1, 310, 5, 30)){
				x=(135+9*60)-6;
				y=385+Size;
				}
//*room #4
			g.setColor(new Color(250, 220, 0));
			g.fillRect(P1x, 330, 30, 15);
			ColisionALL(P1x, 330, 30, 15);

			if (170<=x && x+Size<=310 && 110<=y-Size && y<=360){
				if (P1Tour==0) P1x+=1;
				if (P1Tour==1) P1x-=1;
				if (P1x+30==290) P1Tour=1;
				if (P1x==190) P1Tour=0;
				}
			if (Sustain(P1x, 330, 30, 2)){
				if (P1Tour==0) x+=1;
				if (P1Tour==1) x-=1;
				}
			g.setColor(Color.magenta);
			g.fillOval(225, 115, 30, 5); //#4
			g.setColor(Color.cyan);
			g.fillOval(P1x, 330, 30, 5); //#15
			if (Colision(P1x, 330-1, 30, 5)){
				x=(135+10*60)-6;
				y=385+Size;
				}
//*room #9
			g.setColor(new Color(250, 220, 0));
			g.fillRect(30, 110, 10, 30);
			ColisionALL(30, 110, 10, 30);

			g.fillRect(30, 150, 100, 10);
			ColisionALL(30, 150, 100, 10);

			g.setColor(Color.orange);
			g.drawLine(130, 160, 30, 310);
			if (LineOn(130, 160, 30, 310) && Down==0){
				Slide(130, 160, 30, 310);
				x-=1;
				}
			g.setColor(Color.magenta);
			g.fillOval(35, 110, 5, 30); //#9
			g.setColor(Color.cyan);
			g.fillOval(25, 310, 5, 30); //#14
			if (Colision(25+1, 310, 5, 30)){
				x=(135+11*60)-6;
				y=385+Size;
				}
//*Wall Gray Line
			g.setColor(Color.gray);
			for(int i=0; i<5; i++){
				g.drawLine(170+i*140, 110, 170+i*140, 360);
				ColisionALL(170+i*140, 110, 1, 250);
				g.drawLine(170+i*140, 550, 170+i*140, 799);
				ColisionALL(170+i*140, 550, 1, 250);
				}
//Red Button
			g.setColor(Color.red);
			if (P2Tour==0){
				g.fillRect(321, 655, 8, 8);
				if (Colision(321, 655, 8, 8)) P2Tour=1;
				}
			if (P3Tour==0){
				g.fillRect(866, 748, 8, 8);
				if (Colision(866, 748, 8, 8)) P3Tour=1;
				}
			if (P3x==0){
				g.fillRect(656, 256, 8, 8);
				if (Colision(656, 256, 8, 8)) P3x=1;
				}
			if (P2Trigger==0){
				g.fillRect(476, 341, 8, 8);
				if (Colision(476, 341, 8, 8)) P2Trigger=1;
				}
			if (P3y==0){
				g.fillRect(416, 341, 8, 8);
				if (Colision(416, 341, 8, 8)) P3y=1;
				}

//*Red Dead
			g.fillRect(30, 360, 840, 5);
			if (Colision(30, 360, 840, 5)) initP=0;

			g.fillRect(30, 545, 840, 5);
			if (Colision(30, 545, 840, 5)) initP=0;

//*Portals
			for(int i=0; i<12; i++){
				g.setColor(new Color(250, 220, 0));
				g.fillRect(90+i*60, 525, 30, 20);
				ColisionALL(90+i*60, 525, 30, 20);
				g.fillRect(120+i*60, 365, 30, 20);
				ColisionALL(120+i*60, 365, 30, 20);

				g.setColor(Color.cyan);
				g.fillOval(90+i*60, 525, 30, 5);

				if (ButtonOn1==5 && Colision(90+i*60, 525-1, 30, 5)){
					x=805-6;
					y=25+Size;
					}
				g.setColor(Color.magenta);
				g.fillOval(120+i*60, 380, 30, 5);
				}
			if (Colision(90+0*60, 525-1, 30, 5)){ // (i+2 *60)
				x=580-Size;
				y=800;
				}
			else if (Colision(90+1*60, 525-1, 30, 5)){
				x=40;
				y=800;
				}
			else if (Colision(90+2*60, 525-1, 30, 5)){
				x=240-6;
				y=120+Size;
				}
			else if (Colision(90+3*60, 525-1, 30, 5)){
				x=860-Size;
				y=800;
				}
			else if (Colision(90+4*60, 525-1, 30, 5)){
				x=660-6;
				y=120+Size;
				}
			else if (Colision(90+5*60, 525-1, 30, 5)){
				x=860-Size;
				y=110+Size;
				}
			else if (Colision(90+6*60, 525-1, 30, 5)){
				x=600;
				y=800;
				}
			else if (Colision(90+7*60, 525-1, 30, 5)){
				x=40;
				y=110+Size;
				}
			else if (Colision(90+8*60, 525-1, 30, 5)){
				x=380-6;
				y=120+Size;
				}
			else if (Colision(90+9*60, 525-1, 30, 5)){
				x=300-Size;
				y=800;
				}
			else if (Colision(90+10*60, 525-1, 30, 5)){
				x=520-6;
				y=120+Size;
				}
			else if (Colision(90+11*60, 525-1, 30, 5)){
				x=320;
				y=800;
				}
//***Goal Level 38
			ButtonOn1=P2Tour+P3Tour+P3x+P3y+P2Trigger;
			g.setColor(new Color(250, 220, 0));
			for(int i=0; i<5-ButtonOn1; i++){
				g.fillRect(200+i*50, 15, 20, 85);
				ColisionALL(200+i*50, 15, 20, 85);
				}
			if (ButtonOn1==5){
				g.fillRect(790, 15, 30, 10);
				ColisionALL(790, 15, 30, 10);
				g.setColor(Color.magenta);
				g.fillOval(790, 20, 30, 5); //# Final
				}
			g.setColor(Color.black);
			g.fillRect(120, 50, 20, 50);
			if (x==120 && y==100){
				Level=39;
				initP=0;
				}





			}
//***Level 39
		else if (Level==39){
			if (initP==0){
				x=120;
				y=100;
				ResetTab();
				/*
				* 0 x is blue step
				* 1 x cos position of closest red block to center
				* 2 y sin position of closest red block to center
				* 3 x cos position of farvest red block to center
				* 4 is yellow portal teleportation gate
				* 5 is cyan ball in center, trigger the lines
				* 6 is upper left red button trigger 
				* 7 is upper right red button trigger
				* 8 is down right red button trigger
				* 9 is down left red button trigger
				* 10 is red button triggered sum
				*/
				Tab[0]=170;
				initP=1;
				}
//*orange sky eated by greedy red
			g.setColor(new Color(250, 40, 0));
			g.fillRect(0,0, 20, 800);
			ColisionAll(0,0, 20, 800);
	
			g.fillRect(880, 0, 20, 800);
			ColisionAll(880, 0, 20, 800);

			g.fillRect(120-5, 120, 10, 5);
			ColisionALL(120-5, 120, 10, 5);

			for(int i=1; i<=3; i++){
				g.fillRect(880-20*i, (70-20)+40*i, 20*i, 15);
				ColisionALL(880-20*i, (70-20)+40*i, 20*i, 15);
				}

			if (Tab[4]==6){
				g.fillRect(75, 430, 10, 10);
				ColisionALL(75, 430, 10, 10);
				g.setColor(Color.green);
				g.fillRect(130, 470, 5, 10);
				ColisionALL(130, 470, 5, 10);
				WallStickAll(130, 470, 5, 10);
				g.fillRect(200, 420, 5, 10);
				ColisionALL(200, 420, 5, 10);
				WallStickAll(200, 420, 5, 10);
				g.fillRect(280, 360, 5, 10);
				ColisionALL(280, 360, 5, 10);
				WallStickAll(280, 360, 5, 10);
				g.setColor(new Color(250, 40, 0));
				}
			else if (Tab[4]==7){
				g.fillRect(420, 740, 10, 5);
				ColisionALL(420, 740, 10, 5);
				g.fillRect(505, 700, 10, 5);
				ColisionALL(505, 700, 10, 5);
				g.fillRect(425, 650, 10, 5);
				ColisionALL(425, 650, 10, 5);
				g.fillRect(505, 600, 10, 5);
				ColisionALL(505, 600, 10, 5);
				g.fillRect(440, 570, 10, 5);
				ColisionALL(440, 570, 10, 5);
				g.fillRect(460, 500, 10, 5);
				ColisionALL(460, 500, 10, 5);
				}
			else if (Tab[4]==8){
				g.setColor(Color.yellow);
				g.drawLine(815, 535, 815, 265);
				g.drawLine(760, 165, 760, 640);
				g.drawLine(580, 35, 580, 768);
				if (LineColision(815, 535, 815, 265) || LineColision(760, 165, 760, 640) || LineColision(580, 35, 580, 768)) y-=2;
				g.setColor(new Color(250, 40, 0));
				}
			g.fillRect(Tab[0]-1, 45, 20, 10);
			ColisionALL(Tab[0]-1, 45, 20, 10);
			g.fillRect(190, 20, 10, 35);
			ColisionALL(190, 20, 10, 35);
			g.setColor(Color.blue);
			g.fillRect(Tab[0]+17-1, 45, 3, 2);
			if (Sustain(Tab[0]+17-1, 45, 3, 2)){
				Tab[0]-=1;
				x-=1;
				}
			for(int i=0; i<2; i++){
				g.setColor(new Color(250, 40, 0));
				g.fillRect(420, 12+i*770, 60, 7);
				g.fillRect(62+i*770, 370, 7, 60);
				ColisionALL(420, 12+i*770, 60, 7);
				ColisionALL(62+i*770, 370, 7, 60);
				if (Tab[5]<=1) g.setColor(Color.yellow);
				else if (Tab[5]==2) g.setColor(Color.gray);
				g.fillOval(15+i*865, 25, 5, 30);
				if (Colision(15+i*865-1, 25, 5+2, 30)) Tab[4]=Tab[5]=2;
				g.fillOval(15+i*865, 675, 5, 30);
				if (Colision(15+i*865-1, 675, 5+2, 30)) Tab[4]=Tab[5]=2;
				}
			g.fillOval(420+15, 12+2, 30, 5);
			g.fillOval(420+15, 12+770, 30, 5);
			g.fillOval(62+2, 370+15, 5, 30);
			g.fillOval(62+770, 370+15, 5, 30);
			if ((Colision(420+15, 12+2, 30, 5+2) || Colision(420+15, 12+770-1, 30, 5) || Colision(62+2, 370+15, 5+1, 30) || Colision(62+770-1, 370+15, 5, 30)) && Tab[5]==1){
				Tab[4]=1; 
				Tab[5]=0;
				}
//*Lines
			g.setColor(Color.yellow);
			if (Sustain(Tab[0]+17, 45, 3, 2)==false){
				g.drawLine(20, 120, 160, 0);
				if (LineOn(20, 120, 160, 0) && x<160){
					Slide(20, 120, 160, 0);
					x+=1;
					}
				}		
			g.setColor(Color.cyan);
			if (Tab[5]==0 || Tab[5]==2){
				g.fillOval(450-10, 400-10, 20, 20);
				if (CircleColision(450, 400, 10)){
					Tab[5]=1;
					Tab[4]=0;
					}
				}
			else if (Tab[5]==1){
				g.drawLine(165, 134, 735, 666);
				g.drawLine(165, 666, 735, 134);
				if (LineOn(165, 134, 735, 666) && x>165){
					Slide(165, 134, 735, 666);
					x-=1;
					}
				if (LineOn(165, 666, 735, 134) && x<735){
					Slide(165, 666, 735, 134);
					x+=1;
					}
				g.drawLine(450, 19, 450, 780);
				g.drawLine(70, 400, 830, 400);
				if (LineColision(450, 19, 450, 780)) y-=2;
				if (LineOn(70, 400, 830, 400)){
					Slide(70, 400, 830, 400);
					if (x<450) x-=1;
					else if (x>450) x+=1;
					else if (x==450){
						if (MotionR==1) x+=1;
						if (MotionL==1) x-=1;
						}
					}
				}
//*Red dead
			g.setColor(Color.red);
			for(int i=0; i<10; i++){
				g.drawOval(60-i, 10-i, 780+i*2, 780+i*2);
				if (CircleColision(450, 400, 390+i)) initP=0;
				}
			g.fillRect(20, 372, 30, 60);
			g.fillRect(850, 372, 30, 60);
			if (Colision(20, 372, 30, 60) || Colision(850, 372, 30, 60)) initP=0;

			Tab[1]=450+(int)(100*Math.cos((Tab[3]*3.14)/700));
			Tab[2]=400+(int)(100*Math.sin((Tab[3]*3.14)/700));
			g.fillRect(Tab[1]-15, Tab[2]-15, 30, 30);
			if (Colision(Tab[1]-15, Tab[2]-15, 30, 30)) initP=0;

			Tab[1]=450+(int)(-300*Math.cos((Tab[3]*3.14)/700));
			Tab[2]=400+(int)(300*Math.sin((Tab[3]*3.14)/700));
			g.fillRect(Tab[1]-15, Tab[2]-15, 30, 30);
			Tab[3]+=1;
			if (Colision(Tab[1]-15, Tab[2]-15, 30, 30)) initP=0;
//*red button
			if (Tab[6]==0){
				g.fillRect(186, 26, 8, 8);
				if (Colision(186, 26, 8, 8)) Tab[6]=1;
				}
			if (Tab[7]==0){
				g.fillRect(880-4, 155, 8, 8);
				if (Colision(880-4, 155, 8, 8)) Tab[7]=1;
				}
			if (Tab[8]==0){
				g.fillRect(880-4, 775, 8, 8);
				if (Colision(880-4, 775, 8, 8)) Tab[8]=1;
				}
			if (Tab[9]==0){
				g.fillRect(100-4, 775, 8, 8);
				if (Colision(100-4, 775, 8, 8)) Tab[9]=1;
				}
//*Yellow Portals
			if (Tab[4]==1){
				Tab[4]=1+(int)(Math.random()*4);
				switch(Tab[4]){
					case 1:x=20+1;y=55;break;
					case 2:x=20+1;y=705;break;
					case 3:x=880-Size-1;y=55;break;
					case 4:x=880-Size-1;y=705;break;
					}
				Tab[4]=0;
				}
			else if (Tab[4]==2){
				Tab[4]=5+(int)(Math.random()*4);
				switch(Tab[4]){
					case 5:x=450-5;y=20+Size;break;
					case 6:x=70;y=400;break;
					case 7:x=450-5;y=782;break;
					case 8:x=832-Size;y=400;break;
					}
				Tab[0]=170;
				}
//***Goal Level 39
			Tab[10]=Tab[6]+Tab[7]+Tab[8]+Tab[9];
			g.setColor(new Color(250, 40, 0));
			if (Tab[10]!=4){
				g.fillRect(30, 720, 10, 70);
				g.fillRect(30, 710, 70, 10);
				g.fillRect(90, 720, 10, 70);
				ColisionALL(30, 720, 10, 70);
				ColisionALL(30, 710, 70, 10);
				ColisionALL(90, 720, 10, 70);
				}
			g.fillRect(30, 790, 70, 10);
			ColisionALL(30, 790, 70, 10);
			g.setColor(Color.black);
			g.fillRect(55, 740, 20, 50);
			if (x==55 && y==790){
				Level=40;
				initP=0;
				}
			}
//***Level 40
		else if (Level==40){
			if (initP==0){
				x=55;
				y=790;
				ResetTab();
				/*
				* 0 is the count of button pushed
				* 1 is y of central block
				* 2 is y trigger movement of central block 
				* 3 is x movement addition of horizontal red blocks
				* 4 is x trigger of horizontal red blocks
				* 5 is x cos of vertical red blocks
				* 6 is y sin of vertical red blocks
				* 7 cos sin movement
				* 8 is green room (down left) y plateform
				* 9 is x of blue first plateform  (up left)
				* 10 is x of blue second plateform (up left)
				* 11 is y of blue secon plateform (up left)
				* 12 is trigger yellow portal side (in or out)
				* 13 is blue-cyan button of wall in yellow room (up right)
				*/
				Tab[1]=445;
				Tab[8]=700;
				Tab[9]=20; //y=380
				Tab[10]=320;
				Tab[11]=300;
				Time=TimeOn=0;
				LevelColor=1;
				initP=1;
				}
//*orange ominously red
			g.setColor(new Color(250, 20, 0));
			g.fillRect(0,0, 10, 800);
			ColisionAll(0,0-100, 10, 800+100);
	
			g.fillRect(890, 0, 10, 800);
			ColisionAll(890, 0-100, 10, 800+100);

			g.fillRect(450-20, Tab[1]-20, 40, 40);
			//ColisionALL(450-20, Tab[1]-20, 40, 40);
			//
			//
			if (Tab[1]==262) Tab[2]=1;
			if (Tab[1]==647) Tab[2]=0;
			//if (Tab[1]==-180) Tab[2]=1;
			//if (Tab[1]==180) Tab[2]=0;
			//if (Sustain(450-20, 445+Tab[1]-20, 40, 5)){
				//if (Tab[2]==0) y-=1;
				//if (Tab[2]==1) y+=1;
				//}
			g.fillRect(10, 790, 300+40, 10);
			ColisionALL(10, 790, 300+40, 10);
			g.fillRect(10, 480, 310+40, 10);
			ColisionALL(10, 480, 310+40, 10);
			g.fillRect(310+40, 490, 10, 310);
			ColisionALL(310+40, 490, 10, 310);

			g.fillRect(580-40, 790, 310+40, 10);
			ColisionALL(580-40, 790, 310+40, 10);
			g.fillRect(580-40, 480, 310+40, 10);
			ColisionALL(580-40, 480, 310+40, 10);
			g.fillRect(580-40, 490, 10, 300);
			ColisionALL(580-40, 490, 10, 300);

			g.fillRect(10, 400, 310+40, 10);
			ColisionALL(10, 400, 310+40, 10);
			g.fillRect(310+40, 100, 10, 300);
			ColisionALL(310+40, 100, 10, 300);

			g.fillRect(580-40, 400, 310+40, 10);
			ColisionALL(580-40, 400, 310+40, 10);
			g.fillRect(580-40, 100, 10, 300);
			ColisionALL(580-40, 100, 10, 300);

//*Yellow portal
			g.setColor(Color.yellow);
			for(int i=0; i<2; i++){
				g.fillOval(5+i*885, 350, 5, 30);
				g.fillOval(5+i*885, 750, 5, 30);
				if (Colision(5, 350+i*400, 5+1, 30)) Tab[12]=1;
				if (Colision(890-1, 350+i*400, 5, 30)) Tab[12]=1;
				}
			g.fillOval(450-20, Tab[1]-15, 5, 30);
			g.fillOval(450+15, Tab[1]-15, 5, 30);
			g.fillOval(450-15, Tab[1]+15, 30, 5);
			//g.fillOval(450-15, Tab[1]-20, 30, 5); 
			if (Colision(450-20-1, Tab[1]-15, 5, 30) || Colision(450+15, Tab[1]-15, 5+1, 30)) Tab[12]=2;

			ColisionALL(450-20, Tab[1]-20, 40, 40);
			if (Tab[2]==0) Tab[1]-=1;
			if (Tab[2]==1) Tab[1]+=1;
			if (Sustain(450-20, Tab[1]-20, 40, 5)){
				if (Tab[2]==0) y-=1;
				if (Tab[2]==1) y+=1;
				}
			
			if (Tab[12]==1){
				Tab[12]=1+(int)(Math.random()*3);
				switch(Tab[12]){
					case 1:x=450-20-Size;y=Tab[1]+5;break;
					case 2:x=450+20;y=Tab[1]+5;break;
					case 3:x=450-5;y=Tab[1]+20+Size;break;
					}
				Tab[12]=0;
				}
			else if (Tab[12]==2){
				Tab[12]=1+(int)(Math.random()*4);
				switch(Tab[12]){
					case 1:x=10;y=375;break;
					case 2:x=890-Size;y=375;break;
					case 3:x=10;y=775;break;
					case 4:x=890-Size;y=775;break;
					}
				Tab[12]=0;
				}
//*Red dead
			g.setColor(Color.red);
			g.fillRect(30+Tab[3], 435, 20, 20);
			g.fillRect(870-Tab[3]-20, 435, 20, 20);
			if (Tab[4]==0) Tab[3]+=1;
			if (Tab[4]==1) Tab[3]-=1;
			if (Tab[3]==0) Tab[4]=0;
			if (Tab[3]==350) Tab[4]=1;
			if (Colision(30+Tab[3], 435, 20, 20) || Colision(870-Tab[3]-20, 435, 20, 20)) initP=0;

			Tab[5]=450+(int)(50*Math.cos((Tab[7]*3.14)/300));
			//Tab[6]=272+(int)(112*Math.sin((Tab[7]*3.14)/500));
			//Tab[6]=(int)(272+Tab[1]/2+((112+Tab[1]/2)*Math.sin((Tab[7]*3.14)/500)));
			//Tab[6]=(445+Tab[1]-86)+(int)(50*Math.sin((Tab[7]*3.14)/300));
			Tab[6]=(Tab[1]-86)+(int)(50*Math.sin((Tab[7]*3.14)/300));
			g.fillRect(Tab[5]-10, Tab[6]-10, 20, 20);
			if (Colision(Tab[5]-10, Tab[6]-10, 20, 20)) initP=0;

			Tab[5]=450+(int)(-50*Math.cos((Tab[7]*3.14)/300));
			Tab[6]=(Tab[1]+86)+(int)(50*Math.sin((Tab[7]*3.14)/300));
			g.fillRect(Tab[5]-10, Tab[6]-10, 20, 20);
			if (Colision(Tab[5]-10, Tab[6]-10, 20, 20)) initP=0;
			Tab[7]+=1;
//**Green Room (down left)
			g.setColor(Color.gray);
			g.fillRect(325, Tab[8], 20, 10);
			ColisionALL(325, Tab[8], 20, 10);
			if (Sustain(325, Tab[8], 20, 5)){
				if (Tab[0]==0 && Tab[8]+10<790){
					Tab[8]+=1;
					y+=1;
					}
				else if (Tab[0]>=5 && Tab[8]>540){
					Tab[8]-=1;
					y-=1;
					}
				}
			else if (y==790){
				if (Tab[8]>700) Tab[8]-=1;
				if (Tab[8]<700) Tab[8]+=1;
				}
			g.setColor(new Color(250, 20, 0));
			//g.fillRect(300, 715, 25, 75);
			//ColisionAll(300, 715, 25, 75);

			for(int i=0; i<5; i++){
				g.fillRect(300-25*i, 695+i*20, 25, 20+75-i*20);
				ColisionALL(300-25*i, 695+i*20, 25, 20+75-i*20);
				}
			g.fillRect(10, 620, 40, 10);
			ColisionALL(10, 620, 40, 10);

			g.fillRect(10, 645, 40, 10);
			ColisionALL(10, 645, 40, 10);
			
			if (Tab[0]<5){
				g.fillRect(50, 620, 10, 35);///
				ColisionALL(50, 620, 10, 35);
				}			
			if (Tab[0]<9){
				g.fillRect(160, 490, 10, 40);///
				ColisionALL(160, 490, 10, 40);
				}
			g.fillRect(185, 490, 10, 40);
			ColisionALL(185, 490, 10, 40);

			g.fillRect(160, 530, 35, 10);
			ColisionALL(160, 530, 35, 10);

			g.fillRect(50, 510, 5, 5);
			ColisionALL(50, 510, 5, 5);

			g.fillRect(85, 530, 5, 5);
			ColisionALL(85, 530, 5, 5);
//*
			g.setColor(Color.green);
			g.fillRect(260, 540, 10, 15);
			ColisionALL(260, 540, 10, 15);
			WallStickAll(260, 540, 10, 15);

			g.fillRect(185, 540, 10, 5);
			ColisionALL(185, 540, 10, 5);
			WallStickAll(185, 540, 10, 5);

			g.fillRect(90, 660, 15, 10);
			ColisionALL(90, 660, 15, 10);
			WallStickAll(90, 660, 15, 10);

			g.fillRect(5, 520, 5, 10);
			ColisionALL(5, 520, 5, 10);
			WallStickAll(5, 520, 5, 10);

			g.fillRect(160, 540, 10, 5);
			ColisionALL(160, 540, 10, 5);
			WallStickAll(160, 540, 10, 5);
//*Buttons
			g.setColor(Color.red);
			if (Tab[0]<1){
				g.fillRect(346, 775, 8, 8);//#1
				if (Colision(346, 775, 8, 8)) Tab[0]+=1;
				}
			if (Tab[0]<6){
				g.fillRect(23, 634, 8, 8);//#6
				if (Colision(23, 634, 8, 8)) Tab[0]+=1;
				}
			if (Tab[0]<10){
				g.fillRect(174, 506, 8, 8);//#10
				if (Colision(174, 506, 8, 8)) Tab[0]+=1;
				}
//**Magenta Room (down right)
			g.setColor(new Color(250, 20, 0));
			g.fillRect(860, 490, 10, 5);
			ColisionAll(860, 490, 10, 5);

			g.fillRect(550, 520, 40, 10);
			if (Tab[0]>=4) ColisionALL(550, 520, 40, 10);

			if (Tab[0]<4){
				g.fillRect(590, 520, 40, 10);//#5
				ColisionALL(550, 520, 40+220+40, 10);
				}
			if (Tab[0]<1){
				g.fillRect(580, 490, 10, 30);//#2
				ColisionALL(580, 490, 10, 30);
				}

			g.fillRect(630, 520, 220, 10);
			ColisionALL(630, 520, 220, 10);

			if (Tab[0]<7){
				g.fillRect(580, 530, 10, 50);//#8
				ColisionALL(580, 530, 10, 50);
				}
			g.fillRect(630, 530, 10, 50);
			ColisionALL(630, 530, 10, 50);

			if (Tab[0]<10){
				g.fillRect(670, 530, 10, 50);//#11
				ColisionALL(670, 530, 10, 50);
				}
			g.fillRect(550, 580, 40, 10);
			ColisionALL(550, 580, 40, 10);

			g.fillRect(630, 580, 200, 10);
			ColisionALL(630, 580, 200, 10);

			g.fillRect(630, 620, 200, 10);
			ColisionALL(630, 620, 200, 10);

			g.fillRect(840, 530, 10, 90);
			ColisionAll(840, 530, 10, 90);

			g.fillRect(820, 630, 10, 150);
			ColisionAll(820, 630, 10, 150);

			g.fillRect(817, 670, 3, 3);
			ColisionALL(817, 670, 3, 3);
//*
			g.setColor(Color.yellow);
			g.drawLine(865, 495, 865, 790-1);
			if (LineColision(865, 495+Size+3, 865, 790-1)) y-=2;

			g.drawLine(630, 590, 550, 630);
			if (LineOn(630, 590, 550, 630) && x>550){
				Slide(630, 590, 550, 630);
				x-=1;
				}
			g.drawLine(550, 650, 820-1, 650);
			if (LineOn(550, 650, 820, 650)){
				y=650+Size;
				}
//*Buttons
			g.setColor(Color.red);
			if (Tab[0]<2){
				g.fillRect(561, 501, 8, 8);//#2
				if (Colision(561, 501, 8, 8)) Tab[0]+=1;
				}
			if (Tab[0]<8){
				g.fillRect(561, 551, 8, 8);//#8
				if (Colision(561, 551, 8, 8)) Tab[0]+=1;
				}
			if (Tab[0]<11){
				g.fillRect(651, 551, 8, 8);//#11
				if (Colision(651, 551, 8, 8)) Tab[0]+=1;
				}
			if (Tab[0]<5){
				g.fillRect(816, 626, 8, 8);//#5
				if (Colision(816, 626, 8, 8)) Tab[0]+=1;
				}
//**Blue Room (up left)
			g.setColor(new Color(250, 20, 0));
			g.fillRect(Tab[9], 380, 30, 10);
			ColisionALL(Tab[9], 380, 30, 10);

			g.fillRect(Tab[10], Tab[11], 30, 10);
			ColisionALL(Tab[10], Tab[11], 30, 10);

			g.fillRect(10, 300, 30, 10);
			ColisionALL(10, 300, 30, 10);

			g.fillRect(10, 340, 30, 10);
			ColisionALL(10, 340, 30, 10);

			if (Tab[0]<8){			
				g.fillRect(40, 300, 10, 50);//#9
				ColisionAll(40, 300, 10, 50);
				}
			if (Tab[0]<3){
				g.fillRect(320, 125, 30, 10);//#4
				ColisionALL(320, 125, 30, 10);
				}
			g.fillRect(40, 100, 10, 35);
			ColisionAll(40, 100, 10, 35);

			g.fillRect(50, 125, 30, 10);//#12 ?
			ColisionALL(50, 125, 30, 10);
//*Blue
			g.setColor(Color.blue);
			g.fillRect(Tab[9]+25, 380, 5, 3);
			if (Sustain(Tab[9]+25, 380, 5, 3)){
				if (Tab[9]+30<350){
					Tab[9]+=1;
					x+=1;
					}
				}
			else if (Tab[9]>20 && y==400) Tab[9]-=1;

			g.fillRect(Tab[10]+25, Tab[11], 5, 3);
			if (Sustain(Tab[10]+25, Tab[11], 5, 3)){
				if (Tab[0]==8){
					 if (Tab[10]>100){
						Tab[10]-=1;
						x-=1;
						}
					}
				else if (Tab[0]==3){
					if (Tab[11]>150){
						Tab[11]-=1;
						y-=1;
						}						
					}
				else if (Tab[0]==11){
					if (Tab[10]>80){
						Tab[10]-=1;
						x-=1;
						}
					if (Tab[11]>135){
						Tab[11]-=1;
						y-=1;
						}
					}
				}
			else if (y==400){
				if (Tab[10]<320) Tab[10]+=1;
				if (Tab[11]<300) Tab[11]+=1;
				}
//*Buttons
			g.setColor(Color.red);
			if (Tab[0]<9){
				g.fillRect(21, 321, 8, 8);//#9
				if (Colision(21, 321, 8, 8)) Tab[0]+=1;
				}
			if (Tab[0]<4){
				g.fillRect(331, 111, 8, 8);//#4
				if (Colision(331, 111, 8, 8)) Tab[0]+=1;
				}
			if (Tab[0]<12){
				g.fillRect(61, 111, 8, 8);//#12
				if (Colision(61, 111, 8, 8)) Tab[0]+=1;
				}

//**Yellow Room (up right)
			g.setColor(new Color(250, 20, 0));
			g.fillRect(10, 95, 880, 5);
			ColisionALL(10, 95, 880, 5);

			g.fillRect(580, 100, 10, 240);
			ColisionAll(580, 100, 10, 240);

			if (Tab[0]<2){
				g.fillRect(580, 340, 10, 30);//#3
				ColisionAll(580, 340, 10, 30);
				}
			g.fillRect(590, 330, 40, 10);
			ColisionALL(590, 330, 40, 10);

			g.fillRect(590, 370, 40, 10);
			ColisionALL(590, 370, 40, 10);

			g.fillRect(620, 340, 10, 30);
			ColisionAll(620, 340, 10, 30);

			g.fillRect(580, 370, 10, 30);
			ColisionAll(580, 370, 10, 30);

			g.fillRect(650, 130, 240, 10);
			ColisionAll(650, 130, 240, 10);
			for(int i=1; i<7; i++){
				g.setColor(new Color(250, 20, 0));
				g.fillRect(580+i*40+30, 100, 10, 30);
				ColisionAll(580+i*40+30, 100, 10, 30);
				g.setColor(Color.cyan);
				g.fillOval(580+i*40+30+10, 130, 30, 5);
				if (i!=6){
					if (Colision(580+i*40+30+10, 130-1, 30, 5)){
						x=580+(i+1)*40+30+25;
						y=100+Size;
						}
					}
				g.setColor(Color.magenta);
				g.fillOval(580+i*40+30+10, 95, 30, 5);
				}
			g.setColor(new Color(250, 20, 0));
			if (Tab[0]<6){
				g.fillRect(850, 140, 10, 30);//#7
				ColisionAll(850, 140, 10, 30);
				}
			g.fillRect(860, 170, 30, 10);
			ColisionAll(860, 170, 30, 10);

			g.fillRect(850, 170, 10, 230);
			ColisionAll(850, 170, 10, 230);

			g.fillRect(550, 245, 300, 10);
			ColisionALL(550, 245, 300, 10);

			if (Tab[0]<12){
				g.fillRect(810, 140, 10, 25);//#goal portal
				ColisionAll(810, 140, 10, 25);
				}
			g.fillRect(780, 165, 40, 10);
			ColisionALL(780, 165, 40, 10);

			g.fillRect(780, 175, 10, 30);
			ColisionAll(780, 175, 10, 30);

			g.fillRect(780, 205, 40, 10);
			ColisionALL(780, 205, 40, 10);

			g.fillRect(810, 215, 10, 30);
			ColisionAll(810, 215, 10, 30);

			g.setColor(Color.blue);
			if (Tab[13]==0){			
				g.fillRect(780, 215, 15, 30);
				if (Colision(780, 215, 15, 30)) x=780-Size;

				g.fillRect(616, 116, 8, 8);
				if (Colision(616, 116, 8, 8)) Tab[13]=1;
				}
			else{
				g.setColor(Color.cyan);
				g.fillRect(780, 215, 15, 30);
				g.fillRect(616, 116, 8, 8);
				if (TimeOn==0){
					TimeOn=1;
					new Time().start();
					}
				if (Time==5) Time=Tab[13]=0;
				}
			g.setColor(Color.green);
			g.fillRect(650, 140, 2, 3);
			WallStickAll(650, 140, 2, 3);
//*Buttons
			g.setColor(Color.red);
			if (Tab[0]<3){
				g.fillRect(601, 351, 8, 8);//#3
				if (Colision(601, 351, 8, 8)) Tab[0]+=1;
				}
			if (Tab[0]<7){
				g.fillRect(801, 186, 8, 8);//#7
				if (Colision(801, 186, 8, 8)) Tab[0]+=1;
				}
//*Portals
			g.setColor(Color.cyan);
			g.fillOval(860, 400, 30, 5);//#P1
			if (Colision(860, 400-1, 30, 5)){
				x=560;
				y=250+Size;
				}
			g.fillOval(550, 400, 30, 5);//#P2
			if (Colision(550, 400-1, 30, 5)){
				x=610;
				y=250+Size;
				}
			g.fillOval(850, 350, 5, 30);//#P3
			if (Colision(850-1, 350, 5, 30)){
				x=560;
				y=245-Size;
				}
			g.fillOval(550, 95, 30, 5);//#P4
			if (Colision(550, 95+2, 30, 5)){
				x=670;
				y=100+Size;
				}
			//g.fillOval(860, 130, 30, 5);//#P5
			if (Colision(860, 130-1, 30, 5)){
				x=870;
				y=135+Size;
				}
			g.fillOval(860, 170, 30, 5);//#P6
			g.fillOval(820, 245, 30, 5);//#P6
			if (Colision(860, 170-1, 30, 5) || Colision(820, 245-1, 30, 5)){
				x=870;
				y=175+Size;
				}
			g.fillOval(810, 215, 5, 30);//#P7
			if (Colision(810-1, 215, 5, 30)){
				x=890-Size;
				y=65;
				}
			g.setColor(Color.magenta);
			g.fillOval(550, 250, 30, 5);//#P1
			g.fillOval(600, 250, 30, 5);//#P2
			g.fillOval(550, 245, 30, 5);//#P3
			//g.fillOval(660, 95, 30, 5);//#P4
			g.fillOval(860, 135, 30, 5);//#P5
			g.fillOval(860, 175, 30, 5);//#P6

			g.fillOval(890, 40, 5, 30);//#P7
//*Lines
			g.setColor(Color.yellow);
			g.drawLine(590, 255, 850, 365);
			if (LineOn(590, 255, 850, 365) && x+Size<850){
				Slide(590, 255, 850, 365);
				x+=2;
				}
			for(int i=0; i<3; i++){
				g.drawLine(555+i*10, 100, 555+i*10, 245-1);
				if (LineColision(555+i*10, 100, 555+i*10, 245)) y-=2;
				}
//*
			g.setColor(Color.green);
			for(int i=0; i<2; i++){
				g.fillRect(360-5+i*185, 135, 5, 70);
				g.fillRect(360-5+i*185, 305, 5, 70);
				WallStickAll(360-5+i*185, 135, 5, 70);
				WallStickAll(360-5+i*185, 305, 5, 70);
				g.fillRect(360-5+i*185, 515, 5, 70);
				g.fillRect(360-5+i*185, 685, 5, 70);
				WallStickAll(360-5+i*185, 515, 5, 70);
				WallStickAll(360-5+i*185, 685, 5, 70);
				}
//*Sticky wall of room down left
			WallStickAll(260, 540, 10, 15);
			WallStickAll(185, 540, 10, 5);
			WallStickAll(90, 660, 15, 10);
			WallStickAll(5, 520, 5, 10);
			WallStickAll(160, 540, 10, 5);
//***Goal Level 40
			g.setColor(new Color(250, 20, 0));
			for(int i=0; i<3; i++){
				g.fillRect(160-i*30, 80-10*i, 30, 10);
				ColisionALL(160-i*30, 80-10*i, 30, 10);
				}

			//g.fillRect(250, 35, 50, 50);
			//ColisionALL(250, 35, 50, 50);

			g.setColor(Color.black);
			g.fillRect(105, 10, 20, 50);
			if (x==105 && y==60){
				Level=41;
				LevelColor=0;
				initP=0;
				}

			//int SLight=50;
			SLight=50;
			if (10<=x && x+10<=890  && y<=96){
				if (10<x-SLight) g.fillRect(x-SLight, 0, 10-(x-SLight), 95);
				if (x+10+SLight<890) g.fillRect(x+10+SLight, 0, 890-(x+10+SLight), 95);

				if (10<x-SLight && x+10+SLight<890){
					g.fillRect(x-SLight, y-10-SLight, 2*SLight+10, 0-(y-10-SLight));
					if (y+SLight<95) g.fillRect(x-SLight, y+SLight, 2*SLight+10, 95-(y+SLight));
					}
				else if (10>=x-SLight){
					g.fillRect(10, y-10-SLight, 2*SLight+10, 0-(y-10-SLight));
					if (y+SLight<95) g.fillRect(10, y+SLight, 2*SLight+10, 95-(y+SLight));
					}
				else if (x+10+SLight>=890){
					g.fillRect(890-(2*SLight+10), y-10-SLight, 2*SLight+10, 0-(y-10-SLight));
					if (y+SLight<95) g.fillRect(890-(2*SLight+10), y+SLight, 2*SLight+10, 95-(y+SLight));
					}
				}
			else g.fillRect(10, 0, 880, 95);


			g.setColor(Color.blue);
			g.fillRect(x, y, 10, -10);
			}

//***Level 41 //(décalage gauche de 1, pas conséquent)
	else if (Level==41){
		if (initP==0){
			x=105;
			y=60;
			SLight=50;
			ResetTab();
			/* 
			* 0 is plateform in y=690, go down ro y=785 on sustain
			* 1 is red button 1, left
			* 2 is red button 2, right
			* 3 is blue button(1 trigger left button and right door, 2 trigger the other)
			* 4 is x move addition of upper plateforms //1x=450-30 2x=450
			* 5 is trigger of x movement //Tab[4]=200
			* 
			*/
			Tab[0]=690;
			initP=1;
			}
//*
		g.setColor(Color.magenta);
		g.fillRect(120, 0, 20, 780);
		ColisionAll(120, 0, 20, 780);

		g.fillRect(140, 630, 610, 20);
		ColisionALL(140, 630, 610, 20);

		g.fillRect(200, 785, 15, 15);
		ColisionALL(200, 785, 15, 15);

		g.fillRect(700, 785, 15, 15);
		ColisionALL(700, 785, 15, 15);

		g.fillRect(215, 795, 485, 5);
		g.setColor(Color.red);
		g.fillRect(215, 790, 485, 5);
		if (Colision(215, 790, 485, 5)) initP=0;

		g.setColor(Color.magenta);
		g.fillRect(240, 770, 15, 5);
		ColisionALL(240, 770, 15, 5);

		g.fillRect(300, 750, 15, 5);
		ColisionALL(300, 750, 15, 5);

		g.fillRect(325, 650, 15, 40);
		ColisionAll(325, 650, 15, 40);
		g.setColor(Color.green);


		g.setColor(Color.magenta);
		g.fillRect(360, 740, 15, 5);
		ColisionALL(360, 740, 15, 5);

		g.fillRect(420, 780, 15, 5);
		ColisionALL(420, 780, 15, 5);

		g.fillRect(480, 740, 15, 5);
		ColisionALL(480, 740, 15, 5);

		g.fillRect(540, Tab[0], 15, 5);
		ColisionALL(540, Tab[0], 15, 5);
		if (Sustain(540, Tab[0], 15, 5) && Tab[0]<785){
			Tab[0]+=1;
			y+=1;
			}
		g.fillRect(600, 785, 15, 5);
		ColisionALL(600, 785, 15, 5);

		g.fillRect(666, 750, 15, 5);
		ColisionALL(666, 750, 15, 5);
//*

		g.fillRect(785, 630, 15, 120);
		ColisionALL(785, 630, 15, 120);
		g.setColor(Color.green);


		g.setColor(Color.magenta);
		g.fillRect(190, 300, 20, 300-20);
		ColisionALL(190, 300, 20, 300-20);
		g.fillRect(190, 580, 250, 20);
		ColisionALL(190, 580, 250, 20);
		g.fillRect(230, 280, 210, 20);	
		ColisionALL(230, 280, 210, 20);	
		g.fillRect(420, 300, 20, 300-20);
		ColisionAll(420, 300, 20, 300-20);

		g.fillRect(210, 540, 100, 20);
		ColisionALL(210, 540, 100, 20);

		for(int i=0; i<6; i++){
			g.fillRect(210+i*30, 320+i*40, 20, 15);
			ColisionALL(210+i*30, 320+i*40, 20, 15);
			}

		g.fillRect(500, 300, 20, 300-20);
		ColisionAll(500, 300, 20, 300-20);
		g.fillRect(500, 280, 210, 20);
		ColisionALL(500, 280, 210, 20);
		g.fillRect(730, 300, 20, 300-20);
		ColisionALL(730, 300, 20, 300-20);
		g.fillRect(500, 580, 250, 20);
		ColisionALL(500, 580, 250, 20);

		g.fillRect(630, 540, 100, 20);
		ColisionALL(630, 540, 100, 20);

		for(int i=0; i<6; i++){
			g.fillRect(730-i*30-20, 320+i*40, 20, 15);
			ColisionALL(730-i*30-20, 320+i*40, 20, 15);
			}

		g.setColor(Color.red);
		if (Tab[1]==0 || Tab[2]==0){
			g.fillRect(440, 435, 60, 30);
			if (Colision(440, 435, 60, 30)) initP=0;
			}
		if (Tab[1]==0){
			g.fillRect(215, 566, 8, 8);
			if (Colision(215, 566, 8, 8)) Tab[1]=1;
			}
		if (Tab[2]==0){
			g.fillRect(717, 566, 8, 8);
			if (Colision(717, 566, 8, 8)) Tab[2]=1;
			}
		g.fillRect(250, 180, 10, 100);
		if (Colision(250, 180, 10, 100)) initP=0;

		g.setColor(Color.blue);
		if (Tab[3]==0 || Tab[3]==1){
			g.fillRect(525, 566, 8, 8);
			if (Colision(525, 566, 8, 8)){
				Tab[3]=2;
				Time=0;
				}
			if (Tab[1]==0){
				g.fillRect(290, 560, 20, 20);
				ColisionAll(290, 560, 20, 20);
				}
			}
		if (Tab[3]==0 || Tab[3]==2){
			g.fillRect(407, 566, 8, 8);
			if (Colision(407, 566, 8, 8)){
				Tab[3]=1;
				Time=0;
				}
			if (Tab[2]==0){
				g.fillRect(630, 560, 20, 20);
				ColisionAll(630, 560, 20, 20);
				}
			}
		g.setColor(Color.cyan);
		if (Tab[3]==2){
			g.fillRect(525, 566, 8, 8);
			g.fillRect(290, 560, 20, 20);
			}
		if (Tab[3]==1){
			g.fillRect(407, 566, 8, 8);
			g.fillRect(630, 560, 20, 20);
			}
		if (Tab[3]!=0 && TimeOn==0){
			new Time().start();
			TimeOn=1;
			}
		if (Time==10) Time=Tab[3]=0;

		g.setColor(Color.magenta);
		g.fillRect(140, 200, 110, 20);
		ColisionALL(140, 200, 110, 20);
		g.fillRect(230, 220, 20, 60);
		ColisionAll(230, 220, 20, 60);

		g.fillRect(690, 200, 110, 20);
		ColisionALL(690, 200, 110, 20);
		g.fillRect(690, 220, 20, 60);
		ColisionAll(690, 220, 20, 60);

		g.fillRect(470+Tab[4], 200, 30, 10);
		ColisionALL(470+Tab[4], 200, 30, 10);
		g.fillRect(440-Tab[4], 200, 30, 10);
		ColisionALL(440-Tab[4], 200, 30, 10);
		if (Tab[5]==0) Tab[4]+=1;
		if (Tab[5]==1) Tab[4]-=1;
		if (Tab[4]==0) Tab[5]=0;
		if (Tab[4]==180) Tab[5]=1;
		if (Sustain(440-Tab[4], 200, 30, 7)){
			if (Tab[5]==0) x-=1;
			if (Tab[5]==1) x+=1;
			}
		else if (Sustain(470+Tab[4], 200, 30, 7)){
			if (Tab[5]==0) x+=1;
			if (Tab[5]==1) x-=1;
			}
		for(int i=0; i<9; i++){
			g.fillRect(176+i*30, 60, 15, 10);
			ColisionALL(176+i*30, 60, 15, 10);
			}
		for(int i=0; i<5; i++){
			g.fillRect(446+i*45, 60, 15, 10);
			ColisionALL(446+i*45, 60, 15, 10);
			}

		g.fillRect(170, 0, 6, 6);
		ColisionAll(170, 0, 6, 6);
		g.setColor(Color.yellow);
		g.drawLine(173, 6, 173, 200);
		if (LineColision(173, 6, 173, 200)) y-=2;

//***Goal Level 41
		g.setColor(Color.magenta);
		g.fillRect(700, 60, 100, 10);
		ColisionALL(700, 60, 100, 10);
		g.setColor(Color.green);
		g.fillRect(700, 60, 5, 10);
		WallStickAll(700, 60, 5, 10);


		g.setColor(Color.green);
		g.fillRect(325, 685, 15, 5);
		WallStickAll(325, 685, 15, 5);

		g.fillRect(785, 630, 5, 120);
		WallStickAll(785, 630, 5, 120);

		g.fillRect(190, 300, 5, 300-20);
		WallStickAll(190, 300, 5, 300-20);
		g.fillRect(745, 300, 5, 300-20);
		WallStickAll(745, 300, 5, 300-20);
		for(int i=0; i<6; i++){
			g.fillRect(435, 300+i*50, 5, 30);
			WallStickAll(435, 300+i*50, 5, 30);
			g.fillRect(500, 300+i*50, 5, 30);
			WallStickAll(500, 300+i*50, 5, 30);
			}

		g.setColor(Color.black);
		g.fillRect(740, 10, 20, 50);
		if (x==740 && y==60){
			Level=42;
			initP=0;
			}

		//g.fillRect(100, 0, 700, 800);
		//SLight=1000;
		int LeftSide=0;
		int RightSide=900;
		int UpSide=0;
		int DownSide=800;
		g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
		if (y+SLight<=DownSide){
			g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
			g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
			g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
			}
		else if (y+SLight>DownSide){
			g.fillRect(LeftSide, UpSide, x-SLight, y);
			g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
			g.fillRect(LeftSide, y, x-SLight, DownSide-y);
			g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
			}

/*
*			//g.fillRect(100, 0, 700, 800);
*			//SLight=1000;
*			int LeftSide=0;
*			int RightSide=900;
*			int UpSide=0;
*			int DownSide=800;
*			g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
*			if (y+SLight<=DownSide){
*				g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
*				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
*				g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
*				}
*			else if (y+SLight>DownSide){
*				g.fillRect(LeftSide, UpSide, x-SLight, y);
*				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
*				g.fillRect(LeftSide, y, x-SLight, DownSide-y);
*				g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
*				}
*			if (Tab[0]==1){
*				g.setColor(Color.cyan);
*				g.fillRect(75, 47, 8, 8);
*				g.fillRect(110, 780, 20, 20);
*				g.setColor(Color.black);
*				g.setFont(new Font("Arial", Font.BOLD, 10));
*				g.drawString(String.valueOf(150-Time),111, 795);
*				}
*/



		if (Tab[3]==2){
			g.setColor(Color.white);
			g.fillRect(290-5, 560, 20+10, 20);
			g.setColor(Color.cyan);
			g.fillRect(525, 566, 8, 8);
			g.fillRect(290, 560, 20, 20);
			}
		if (Tab[3]==1){
			g.setColor(Color.white);
			g.fillRect(630-5, 560, 20+10, 20);
			g.setColor(Color.cyan);
			g.fillRect(407, 566, 8, 8);
			g.fillRect(630, 560, 20, 20);
			}
		}
//***Level 42
	else if (Level==42){
		if (initP==0){
			x=740;
			y=60;
			SLight=50;
			Time=TimeOn=0;
			ResetTab();
			/*
			* 0 is the y move of red block in 1st; x=700
			* 1 is the trigger move of red block in 1st
			* 2 is blue button 1
			* 3 is blue button 2
			* 4 is blue button 4
			* 5 is x move of block holding yellow line in 2nd
			* 6 is its trigger
			* 7 is y move of duo red blocks in 3rd
			* 8 is their trigger
			* 9 is x cos move of duo red blocks in 3rd
			* 10 is y sin move of duo red blocks in 3rd
			* 11 is x move of last trap red block
			* 12 is movement of cos sin (9 & 10)
			*/
			Tab[0]=200;
			Tab[5]=600;
			Tab[7]=250;
			Tab[11]=780;
			initP=1;
			}
		g.setColor(Color.pink);
		g.fillRect(730, 60, 80, 340);
		ColisionALL(730, 60, 80, 340);

		g.fillRect(650, 60, 35, 390);
		ColisionALL(650, 60, 35, 390);

		g.fillRect(685, 420, 125, 30);
		ColisionALL(685, 420, 125, 30);

		g.fillRect(465, 60, 35, 540);
		ColisionALL(465, 60, 35, 540);

		g.fillRect(500, 570, 310, 30);
		ColisionALL(500, 570, 310, 30);

		g.fillRect(130, 60, 35, 680);
		ColisionALL(130, 60, 35, 680);

		g.fillRect(165, 710, 335, 30);
		ColisionALL(165, 710, 335, 30);

		g.fillRect(790, 640, 20, 10);
		ColisionALL(790, 640, 20, 10);

		g.fillRect(490, 740, 310, 10);
		ColisionALL(490, 740, 310, 10);

		g.fillRect(790, 650, 10, 90);
		ColisionAll(790, 650, 10, 90);

		g.fillRect(490, 640, 10, 90);
		ColisionALL(490, 640, 10, 90);

//***1st
		for(int i=0; i<7; i++){
			if (i%2==0){
				g.fillRect(685, 65+i*50, 5, 5);
				ColisionALL(685, 65+i*50, 5, 5);
				}
			else{
				g.fillRect(725, 65+i*50, 5, 5);
				ColisionALL(725, 65+i*50, 5, 5);
				}
			}


		if (Tab[2]==0){
			g.setColor(Color.blue);
			g.fillRect(800, 406, 8, 8);
			if (Colision(800, 406, 8, 8)) Tab[2]=1;
			g.fillRect(665, 0, 15, 60);
			ColisionAll(665, 0-100, 15, 60+100);
			}
		else if (Tab[2]==1){
			g.setColor(Color.cyan);
			g.fillRect(800, 406, 8, 8);
			g.fillRect(665, 0, 15, 60);
			if (TimeOn==0){
				new Time().start();
				TimeOn=1;
				}
			if (Time==5) Time=Tab[2]=0;
			}
		g.setColor(Color.red);
		g.fillRect(700, Tab[0], 15, 15);
		if (Colision(700, Tab[0], 15, 15)) initP=0;
		if (Tab[1]==0) Tab[0]+=1;
		if (Tab[1]==1) Tab[0]-=1;
		if (Tab[0]==70) Tab[1]=0;
		if (Tab[0]==385) Tab[1]=1;
//***2nd
		if (Tab[3]==0){
			for(int i=0; i<2; i++){
				g.fillRect(500+i*85, 90, 65, 20);
				if (Colision(500+i*85, 90, 65, 20)) initP=0;
				g.fillRect(500+i*20, 210+i*120, 130, 20);
				if (Colision(500+i*20, 210+i*120, 130, 20)) initP=0;
				g.fillRect(530+i*60, 450, 30, 30);
				if (Colision(530+i*60, 450, 30, 30)) initP=0;
				}
			g.setColor(Color.yellow);
			for(int i=0; i<4; i++){
				g.drawLine(500, 75+i*120, 650, 75+i*120);
				if (LineOn(500, 75+i*120, 650, 75+i*120)) y=75+i*120+Size;
				}
			g.setColor(Color.blue);
			g.fillRect(800, 506, 8, 8);
			if (Colision(800, 506, 8, 8)) Tab[3]=1;
			g.fillRect(480, 0, 15, 60);
			ColisionAll(480, 0-100, 15, 60+100);
			}
		else if (Tab[3]==1){
			g.setColor(Color.pink);
			g.fillRect(Tab[5], 0, 6, 6);
			ColisionAll(Tab[5], 0-100, 6, 6+100);
			if (Tab[6]==0) Tab[5]+=1;
			if (Tab[6]==1) Tab[5]-=1;
			if (Tab[5]==644) Tab[6]=1;
			if (Tab[5]==500) Tab[6]=0;
			g.setColor(Color.yellow);
			g.fillRect(Tab[5], 6, 6, 564);
			//g.drawLine(Tab[5]+3, 6, Tab[5]+3, 570);
			for(int i=0; LineColision(Tab[5]+i,6,Tab[5]+i,570) && i<7; i++) y-=2;
			//if (LineColision(Tab[5]+3, 6, Tab[5]+3, 570)) y-=2;
			g.setColor(Color.cyan);
			g.fillRect(800, 506, 8, 8);
			g.fillRect(480, 0, 15, 60);
			if (TimeOn==0){
				new Time().start();
				TimeOn=1;
				}
			if (Time==7) Time=Tab[3]=0;
			}
		g.setColor(Color.pink);
		for(int i=0; i<5; i++){
			g.fillRect(650+i*30, 570-15*i-15, 10, 15+15*i);
			ColisionALL(650+i*30, 570-15*i-15, 10, 15+15*i);
			}
//***3rd
		for(int i=0; i<7; i++){
			g.fillRect(165+i*45, 100, 30, 10);
			ColisionALL(165+i*45, 100, 30, 10);
			}
		for(int i=0; i<4; i++){
			g.setColor(Color.pink);
			g.fillRect(305, 130+i*145, 20, 10);
			ColisionALL(305, 130+i*145, 20, 10);
			g.setColor(Color.yellow);
			g.fillRect(305, 130+i*145, 20, 3);
			}
		if (Sustain(305, 130+0*145, 20, 3) || Sustain(305, 130+1*145, 20, 3) || Sustain(305, 130+2*145, 20, 3) || Sustain(305, 130+3*145, 20, 3)){
			if (SLight<150) SLight+=1;
			}
		else if (SLight>50 && 165<x && x+Size<465 && y<710) SLight-=1;

		for(int i=0; i<2; i++){
			g.setColor(Color.pink);
			g.fillRect(196+i*40, 180, 20, 20);
			ColisionALL(196+i*40, 180, 20, 20);
			g.fillRect(374+i*40, 180, 20, 20); 
			ColisionALL(374+i*40, 180, 20, 20);

			g.fillRect(227+i*164, 320, 20, 20);
			ColisionALL(227+i*164, 320, 20, 20);
			g.fillRect(227+i*164, 470, 20, 20);
			ColisionALL(227+i*164, 470, 20, 20);
			
			g.setColor(Color.red);
			g.fillRect(216+i*178, Tab[7], 20, 20);
			if (Colision(216+i*178, Tab[7], 20, 20)) initP=0;
			//if (Tab[8]==0) Tab[7]+=1;
			//if (Tab[8]==1) Tab[7]-=1;
			//if (Tab[7]==140) Tab[8]=0;
			//if (Tab[7]==255) Tab[8]=1;

			g.fillRect(295+i*30, 280, 10, 10);
			if (Colision(295+i*30, 280, 10, 10)) initP=0;
			g.fillRect(295+i*30, 420, 10, 10);
			if (Colision(295+i*30, 420, 10, 10)) initP=0;
			g.fillRect(295+i*30, 565, 10, 20);
			if (Colision(295+i*30, 565, 10, 20)) initP=0;
			}
		if (Tab[8]==0) Tab[7]+=1;
		if (Tab[8]==1) Tab[7]-=1;
		if (Tab[7]==140) Tab[8]=0;
		if (Tab[7]==255) Tab[8]=1;

		Tab[9]=315+(int)(70*Math.cos((Tab[12]*3.14)/400)) -10;
		Tab[10]=570+(int)(70*Math.sin((Tab[12]*3.14)/400)) -10;
		g.fillRect(Tab[9], Tab[10], 20, 20);
		if (Colision(Tab[9], Tab[10], 20, 20)) initP=0;
		Tab[9]=315+(int)(-70*Math.cos((Tab[12]*3.14)/400)) -10;
		Tab[10]=570+(int)(-70*Math.sin((Tab[12]*3.14)/400)) -10;
		g.fillRect(Tab[9], Tab[10], 20, 20);
		if (Colision(Tab[9], Tab[10], 20, 20)) initP=0;
		Tab[12]+=1;

		for(int i=1; i<=16; i++){
			g.fillRect(460, 110+i*30, 5, 5);
			if (Colision(460, 110+i*30, 5, 5)) initP=0;
			}

		g.setColor(Color.pink);
		g.fillRect(530, 710, 10, 5);
		ColisionALL(530, 710, 10, 5);

		g.fillRect(590, 650, 10, 5);
		ColisionALL(590, 650, 10, 5); //green

		g.fillRect(640, 640, 10, 5);
		ColisionALL(640, 640, 10, 5);

		g.fillRect(700, 640, 10, 5);
		ColisionALL(700, 640, 10, 5);

		g.fillRect(750, 640, 10, 5);
		ColisionALL(750, 640, 10, 5);

		if (Tab[4]==0){
			g.setColor(Color.red);
			for(int i=1; i<=19; i++){
				g.fillRect(165, 110+i*30, 5, 5);
				if (Colision(165, 110+i*30, 5, 5)) initP=0;
				}
			g.setColor(Color.blue);
			g.fillRect(800, 630, 8, 8);
			if (Colision(800, 630, 8, 8)) Tab[4]=1;
			g.fillRect(145, 0, 15, 60);
			ColisionAll(145, 0-100, 15, 60+100);
			}
		else if (Tab[4]==1){
			g.setColor(Color.green);
			for(int i=1; i<=19; i++){
				g.fillRect(160, 110+i*30, 5, 5);
				WallStickAll(160, 110+i*30, 5, 5);
				}
			g.setColor(Color.cyan);
			g.fillRect(800, 630, 8, 8);
			g.fillRect(145, 0, 15, 60);
			if (TimeOn==0){
				new Time().start();
				TimeOn=1;
				}
			if (Time==10) Time=Tab[4]=0;
			}
//***Goal Level 42
		g.setColor(Color.red);
		g.fillRect(Tab[11], 780, 10, 10);
		if (Colision(Tab[11], 780, 10, 10)) initP=0;
		if (y==800) Tab[11]-=1;
		g.setColor(Color.pink);
		g.fillRect(250, 790, 10, 10);
		ColisionALL(250, 790, 10, 10);
		g.setColor(Color.yellow);
		g.fillRect(250, 790, 10, 3);
		if (Sustain(250, 790, 10, 3) && SLight<150) SLight+=1;
		else if (SLight>50 && y>710) SLight-=1;

		g.setColor(Color.black);
		g.fillRect(780, 750, 20, 50);
		if (x==780 && y==800){
			Level=43;
			initP=0;
			}

		//g.fillRect(100, 0, 700, 800);
		//SLight=1000;
		int LeftSide=0;
		int RightSide=900;
		int UpSide=0;
		int DownSide=800;
		g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
		if (y+SLight<=DownSide){
			g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
			g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
			g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
			}
		else if (y+SLight>DownSide){
			g.fillRect(LeftSide, UpSide, x-SLight, y);
			g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
			g.fillRect(LeftSide, y, x-SLight, DownSide-y);
			g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
			}

		}
//***Level 43
	else if (Level==43){
		if (initP==0){
			x=780;
			y=800;
			SLight=50;
			ResetTab();
			/*
			* 1 is blue button 1
			* 2 is blue button 2
			* 49 is glitch trigger
			*/
			Time=TimeOn=0;
			initP=1;
			}
		Color color = new Color(128,50,255);
		g.setFont(new Font("Arial", Font.BOLD, 10));

		g.setColor(color);
		g.fillRect(80, 60, 715, 15);
		ColisionALL(80, 60, 715, 15);

		g.fillRect(105, 90, 15, 685);
		ColisionALL(105, 90, 15, 685);

		g.fillRect(120, 760, 660, 15);
		ColisionALL(120, 760, 660, 15);

		g.fillRect(780, 105, 15, 670);
		ColisionAll(780, 105, 15, 670);

		g.fillRect(320, 90, 475, 15);
		ColisionALL(320, 90, 475, 15);

		g.fillRect(320, 105, 15, 450);
		ColisionAll(320, 105, 15, 450);

		g.fillRect(335, 540, 230, 15);
		ColisionALL(335, 540, 230, 15);

		g.fillRect(565, 230, 15, 325);
		ColisionAll(565, 230, 15, 325);

		g.fillRect(440, 215, 140, 15);
		ColisionALL(440, 215, 140, 15);

		g.fillRect(440, 230, 15, 210);
		ColisionAll(440, 230, 15, 210);

		g.fillRect(455, 425, 30, 15);
		ColisionALL(455, 425, 30, 15);

		g.fillRect(485, 295, 15, 145);
		ColisionALL(485, 295, 15, 145);


//***
		g.fillRect(140, 100, 10, 650);
		ColisionALL(140, 100, 10, 650);

		for(int i=0; i<3; i++){
			g.fillRect(280, 160+i*150, 10, 40);
			ColisionALL(280, 160+i*150, 10, 40);
			g.fillRect(290, 190+i*150, 30, 10);
			ColisionALL(290, 190+i*150, 30, 10);
			}
		for(int i=0; i<2; i++){
			g.fillRect(180, 225+i*300, 10, 40);
			ColisionALL(180, 225+i*300, 10, 40);
			g.fillRect(150, 255+i*300, 30, 10);
			ColisionALL(150, 255+i*300, 30, 10);

			g.fillRect(200, 350+i*200, 10, 40);
			ColisionALL(200, 350+i*200, 10, 40);
			g.fillRect(240, 350+i*200, 10, 40);
			ColisionALL(240, 350+i*200, 10, 40);
			g.fillRect(210, 380+i*200, 30, 10);
			ColisionALL(210, 380+i*200, 30, 10);
			}
		g.fillRect(180, 485, 10, 40);
		ColisionALL(180, 485, 10, 40);
		g.fillRect(220, 485, 10, 40);
		ColisionALL(220, 485, 10, 40);
		g.fillRect(190, 515, 30, 10);
		ColisionALL(190, 515, 30, 10);

		g.fillRect(190, 550, 10, 10);
		ColisionALL(190, 550, 10, 10);

		g.fillRect(320, 555, 10, 110);
		ColisionAll(320, 555, 10, 110);

		g.fillRect(200, 655, 120, 10);
		ColisionALL(200, 655, 120, 10);

		g.setColor(Color.yellow);
		g.drawLine(150, 100, 320, 155);
		if (LineOn(150, 100, 320, 155) && x+Size<320){
			Slide(150, 100, 320, 155);
			x+=1;
			}
		g.drawLine(240, 130, 150, 210);
		if (LineOn(240, 130, 150, 210) && x>150){
			Slide(240, 130, 150, 210);
			x-=1;
			}
		g.drawLine(195, 175, 320, 275);
		if (LineOn(195, 175, 320, 275) && x+Size<320){
			Slide(195, 175, 320, 275);
			x+=1;
			}
		g.drawLine(265, 235, 210, 365);
		if (LineOn(265, 235, 210, 365) && x>210){
			Slide(265, 235, 210, 365);
			x-=1;
			}
		g.drawLine(200, 390, 150, 450);
		if (LineOn(200, 390, 150, 450) && x>150){
			Slide(200, 390, 150, 450);
			x-=1;
			}
		g.drawLine(250, 390, 320, 450);
		if (LineOn(250, 390, 320, 450) && x+Size<320){
			Slide(250, 390, 320, 450);
			x+=1;
			}
		g.drawLine(250, 570, 320, 610);
		if (LineOn(250, 570, 320, 610) && x+Size<320){
			Slide(250, 570, 320, 610);
			x+=1;
			}
//*
		g.setColor(color);
		for(int i=0; i<3; i++){
			g.fillRect(200+i*60, 700, 15, 10);
			ColisionALL(200+i*60, 700, 15, 10);

			g.fillRect(360+i*40, 670-i*30, 15, 10);
			ColisionALL(360+i*40, 670-i*30, 15, 10);

			g.fillRect(470+i*40, 625-i*30, 15, 10);
			ColisionALL(470+i*40, 625-i*30, 15, 10);
			}
		g.fillRect(565, 660, 75, 10);
		ColisionALL(565, 660, 75, 10);
//*
		g.fillRect(630, 600, 16, 16);
		ColisionALL(630, 600, 16, 16); //green left

		g.fillRect(630, 545, 150, 10);
		ColisionALL(630, 545, 150, 10);

		g.fillRect(600, 440, 10, 60);
		ColisionALL(600, 440, 10, 60); //green left

		for(int i=0; i<3; i++){
			g.fillRect(650+i*50, 440, 10, 10);
			ColisionALL(650+i*50, 440, 10, 10);

			g.fillRect(600+i*50, 340, 10, 10);
			ColisionALL(600+i*50, 340, 10, 10);
			}

		g.fillRect(740, 300, 10, 40);
		ColisionALL(740, 300, 10, 40); // green right

		g.setColor(Color.green);
		g.fillRect(630, 600, 5, 16);
		WallStickAll(630, 600, 5, 16);

		g.fillRect(600, 440, 5, 60);
		WallStickAll(600, 440, 5, 60);

		g.fillRect(575, 540, 5, 15);
		WallStickAll(575, 540, 5, 15);

		g.fillRect(780, 360, 5, 60);
		WallStickAll(780, 360, 5, 60);

		g.fillRect(745, 300, 5, 40);
		WallStickAll(745, 300, 5, 40);

		g.fillRect(575, 260, 5, 60);
		WallStickAll(575, 260, 5, 60);


		g.fillRect(115, 115, 5, 635);

		g.fillRect(790, 75, 5, 675);
//*
		g.setColor(color);
		for(int i=0; i<4; i++){
			g.fillRect(455+i*34, 105, 10, 110);
			ColisionAll(455+i*34, 105, 10, 110);
			}
		g.fillRect(465, 155, 92, 10);
		ColisionALL(465, 155, 92, 10);
		
		if (Tab[2]==0){
			for(int i=0; i<4; i++){
				g.setColor(Color.cyan);
				g.fillOval(465-5+i*34, 185, 5, 30);
				if (Colision(465-5+i*34, 185, 5+1, 30)){
					if (i==0){
						x=455-Size;
						y=215;
						}
					else{
						x=465-10+i*34-Size;
						y=155;
						}
					}
				if (i<3){
					g.fillOval(465-5+i*34, 125, 5, 30);
					if (Colision(465-5+i*34, 125, 5+1, 30)){
						x=465-10+(i+1)*34-Size;
						y=215;
						}
					}
				g.setColor(Color.magenta);
				g.fillOval(465-10+i*34, 185, 5, 30);
				if (i>0){
					g.fillOval(465-10+i*34, 125, 5, 30);
					}
				}
			}
		else if (Tab[2]==1){
			for(int i=0; i<4; i++){
				g.setColor(Color.magenta);
				g.fillOval(465-5+i*34, 185, 5, 30);
				if (i<3){
					g.fillOval(465-5+i*34, 125, 5, 30);
					}

				g.setColor(Color.cyan);
				g.fillOval(465-10+i*34, 185, 5, 30);
				if (Colision(465-10+i*34-1, 185, 5, 30)){
					if (i==3){
						x=567;
						y=215;
						}
					else{
						x=465+i*34;
						y=155;
						}
					}
				if (i>0){
					g.fillOval(465-10+i*34, 125, 5, 30);
					if (Colision(465-10+i*34-1, 125, 5, 30)){
						x=465+(i-1)*34;
						y=215;
						}
					}
				}
			}
//*
		g.setColor(color);
		for(int i=0; i<3; i++){
			g.fillRect(335+i*35, 220, 20, 10);
			ColisionALL(335+i*35, 220, 20, 10);

			if (i<2){
				g.fillRect(355+i*35, 240, 15, 10);
				ColisionALL(355+i*35, 240, 15, 10);

				g.fillRect(335+i*65, 400, 40, 10);
				ColisionALL(335+i*65, 400, 40, 10);

				g.fillRect(350+i*50, 430, 25, 10);
				ColisionALL(350+i*50, 430, 25, 10);

				g.fillRect(365+i*35, 440, 10, 60);
				ColisionALL(365+i*35, 440, 10, 60);
				}
			}
		g.fillRect(355, 290, 65, 10);
		ColisionALL(355, 290, 65, 10);

		g.fillRect(335, 350, 85, 10);
		ColisionALL(335, 350, 85, 10);

		g.fillRect(375, 490, 190, 10);
		ColisionALL(375, 490, 190, 10);

		g.fillRect(440, 440, 10, 90);
		ColisionAll(440, 440, 10, 90);
//*
		g.fillRect(490, 440, 10, 100);
		ColisionAll(490, 440, 10, 100);

		g.fillRect(400, 500, 10, 40);
		ColisionAll(400, 500, 10, 40);

		if (Tab[2]==0){
			g.setColor(Color.cyan);
			g.fillOval(400, 510, 5, 30);
			if (Colision(400-1, 510, 5, 30)){
				x=410;
				y=540;
				}
			g.fillOval(440, 500, 5, 30);
			if (Colision(440-1, 500, 5, 30)){
				x=410;
				y=490;
				}
			for(int i=0; i<2; i++){
				g.fillOval(490+i*75, 510, 5, 30);
				if (Colision(490+i*75-1, 510, 5, 30)){
					if (i==0){
						x=450;
						y=490;
						}
					else{
						x=500;
						y=490;
						}
					}
				}
			g.fillOval(490, 460, 5, 30);
			if (Colision(490-1, 460, 5, 30)){
				x=500;
				y=540;
				}
			g.setColor(Color.magenta);
			g.fillOval(405, 510, 5, 30);
			g.fillOval(405, 460, 5, 30);
			for(int i=0; i<2; i++) g.fillOval(445+i*50, 460, 5, 30);
			g.fillOval(495, 510, 5, 30);
			}
		else if (Tab[2]==1){
			g.setColor(Color.cyan);
			g.fillOval(440, 500, 5, 30);
			if (Colision(440-1, 500, 5, 30)){
				x=410;
				y=490;
				}
			g.fillOval(405, 510, 5, 30);
			if (Colision(405, 510, 5+1, 30)){
				x=400-Size;
				y=540;
				}
			for(int i=0; i<2; i++){
				g.fillOval(445+i*50, 460, 5+1, 30);
				if (Colision(445+i*50, 460, 5+1, 30)){
					if (i==0){
						x=490-Size;
						y=540;
						}
					else{
						x=565-Size;
						y=540;
						}
					}
				}				
			g.fillOval(495, 510, 5, 30);
			if (Colision(495, 510, 5+1, 30)){
				x=490-Size;
				y=490;
				}
			g.setColor(Color.magenta);
			g.fillOval(405, 460, 5, 30);

			g.fillOval(400, 510, 5, 30);
			if (Colision(400, 510, 5+1, 30)){
				x=410;
				y=540;
				}

			for(int i=0; i<2; i++){
				g.fillOval(490+i*75, 510, 5, 30);
				if (Colision(490+i*75, 510, 5+1, 30)){
					if (i==0){
						x=450;
						y=490;
						}
					else{
						x=500;
						y=490;
						}
					}
				}
			g.fillOval(490, 460, 5, 30);
			if (Colision(490, 460, 5+1, 30)){
				x=500;
				y=540;
				}
			}
//*
		g.setColor(color);
		for(int i=0; i<2; i++){
			g.fillRect(500, 310+i*100, 5, 5);
			ColisionALL(500, 310+i*100, 5, 5);

			g.fillRect(560, 360+i*100, 5, 5);
			ColisionALL(560, 360+i*100, 5, 5);
			}
		g.setColor(Color.red);
		g.fillRect(760, 740, 20, 20);
		if (Colision(760, 740, 20, 20)) initP=0;
		g.fillRect(545, 470, 20, 20);
		if (Colision(545, 470, 20, 20)) initP=0;
//***Blue timer
		if (Tab[1]==0){
			g.setColor(Color.blue);
			g.fillRect(760, 47, 8, 8);
			if (Colision(760, 47, 8, 8)){
				Tab[1]=1;
				Tab[2]=Time=0;
				}
			if (Tab[2]==0){
				g.fillRect(455, 295, 30, 15);
				ColisionALL(455, 295, 30, 15);
				}
			}
		else if (Tab[1]==1){
			g.setColor(Color.cyan);
			g.fillRect(760, 47, 8, 8);
			g.fillRect(455, 295, 30, 15);
			if (TimeOn==0){
				new Time().start();
				TimeOn=1;
				}
			if (Time==180) Time=Tab[1]=0;
			//g.setColor(Color.black);
			//g.drawString(String.valueOf(180-Time), 468, 305);
			}
		if (Tab[2]==0){
			g.setColor(Color.blue);
			g.fillRect(466, 416, 8, 8);
			if (Colision(466, 416, 8, 8)){
				Tab[2]=1;
				Tab[1]=Time=0;
				}
			g.fillRect(735, 0, 15, 60);
			ColisionAll(735, 0-100, 15, 60+100);
			g.fillRect(120, 100, 20, 15);
			ColisionALL(120, 100, 20, 15);
			}
		else if (Tab[2]==1){
			g.setColor(Color.cyan);
			g.fillRect(466, 416, 8, 8);
			g.fillRect(735, 0, 15, 60);
			g.fillRect(120, 100, 20, 15);
			if (TimeOn==0){
				new Time().start();
				TimeOn=1;
				}
			if (Time==60) Time=Tab[2]=0;
			//g.setColor(Color.black);
			//g.drawString(String.valueOf(60-Time), 740, 32);
			}
//**Glitch	
		g.setColor(color);
		g.fillRect(320, 75, 15, 15);
		if (LineColision(335, 75, 335, 90)) Tab[49]=1;

		g.fillRect(460, 75, 15, 15);
		ColisionAll(460, 75, 15, 15);

		g.fillRect(780, 75, 15, 15);
		if (Tab[49]==0) ColisionAll(780, 75, 15, 15);

		if (Tab[49]==1){
			g.setColor(Color.cyan);
			g.fillOval(460, 75, 5, 15);
			if (Colision(460-1, 75, 5, 15)){
				x=450;
				y=425;
				}
			g.fillOval(485, 410, 5, 15);
			if (Colision(485-1, 410, 5, 15)){
				x=475;
				y=90;
				}
			g.setColor(Color.magenta);
			g.fillOval(450, 410, 5, 15);
			g.fillOval(470, 75, 5, 15);
			}

//***Goal Level 43
		g.setColor(Color.black);
		g.fillRect(90, 10, 20, 50);
		if (x==90 && y==60){
			Level=44;
			initP=0;
			}

		//g.fillRect(100, 0, 700, 800);
		//SLight=1000;
		int LeftSide=0;
		int RightSide=900;
		int UpSide=0;
		int DownSide=800;
		g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
		if (y+SLight<=DownSide){
			g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
			g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
			g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
				}
		else if (y+SLight>DownSide){
			g.fillRect(LeftSide, UpSide, x-SLight, y);
			g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
			g.fillRect(LeftSide, y, x-SLight, DownSide-y);
			g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
				}
		if (Tab[1]==1){
			g.setColor(Color.white);
			g.fillRect(450, 290, 40, 25);
			g.setColor(color);
			g.fillRect(450, 290, 5, 25);
			g.fillRect(485, 290, 5, 25);
			g.setColor(Color.cyan);
			g.fillRect(760, 47, 8, 8);
			g.fillRect(455, 295, 30, 15);
			g.setColor(Color.black);
			g.drawString(String.valueOf(180-Time), 462, 305);
			}
		if (Tab[2]==1){
			g.setColor(Color.cyan);
			g.fillRect(466, 416, 8, 8);
			g.fillRect(735, 0, 15, 60);
			g.setColor(Color.black);
			g.drawString(String.valueOf(60-Time), 737, 32);
			}
		//g.fillRect(630, 600, 5, 16);
		WallStickAll(630, 600, 5, 16);

		//g.fillRect(600, 440, 5, 60);
		WallStickAll(600, 440, 5, 60);

		//g.fillRect(575, 540, 5, 15);
		WallStickAll(575, 540, 5, 15);

		//g.fillRect(780, 360, 5, 60);
		WallStickAll(780, 360, 5, 60);

		//g.fillRect(745, 300, 5, 40);
		WallStickAll(745, 300, 5, 40);

		//g.fillRect(575, 260, 5, 60);
		WallStickAll(575, 260, 5, 60);

		WallStickAll(115, 115, 5, 635);
		WallStickAll(790, 75, 5, 675);
		}

	if (Measurement==1){
		g.setColor(Color.red);
		g.drawLine(MeasurementX1, MeasurementY1, MeasurementX2, MeasurementY2);
		int Measure = Norme(MeasurementX2-MeasurementX1, MeasurementY2-MeasurementY1);
		g.drawString(String.valueOf(Measure), MeasurementX2-20, MeasurementY2-10);
		}
	if (Level>40){
		g.setColor(Color.blue);
		g.fillRect(x, y, 10, -10);

		if (1==0){
			if (LevelColor==0) g.setColor(Color.black);
			else g.setColor(Color.red);
			g.fillRect(0, 8*this.getHeight()/9, this.getWidth(), this.getHeight()/9);
			g.setColor(Color.white);
			g.drawString("level "+String.valueOf(Level), 410, 860);
			g.setColor(Color.red);
			g.fillRect(0, 0, 10+10*(50-Level), 800);
			if (Colision(0, 0, 10+10*(50-Level), 800)) initP=0;
			g.fillRect(900-(10+10*(50-Level)), 0, 10+10*(50-Level), 800);
			if (Colision(900-(10+10*(50-Level)), 0, 10+10*(50-Level), 800)) initP=0;
			}
		}
	}
//-----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------
	//if (Level>=44){
	//public void paint(Graphics g){
	//public void update(Graphics g){
        //void lightweightPaint(Graphics g) {
        //void paintHeavyweightComponents(Graphics g) {
        //public void paintAll(Graphics g) {
        public void print(Graphics g) {

		}//##$$##
		
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
		
	public boolean Sustain(int X, int Y, int width, int height){
		int Sx=0;
		int Sy=0;
		for(int i=x; i<=x+Size; i++){
			if (X<=i && i<=X+width){
				Sx=1;
				break;
				}
			}
		if (Y<=y && y<=Y+height) Sy=1;
		return Sx==1 && Sy==1;
		}
	public boolean Colision(int X, int Y, int width, int height){
		int Cx=0;
		int Cy=0;
		for(int i=x; i<=x+Size; i++){
			if (X<i && i<X+width){
				Cx=1;
				break;
				}
			}
		for(int i=y; i>y-Size; i--){
			if (Y<i && i<Y+height){
				Cy=1;
				break;
				}
			}
		return Cx==1 && Cy==1;
		}
	public void Jump(int Y){
		SautLock=1;
		y-=5;
		SautCooldown+=1;
		if (SautCooldown==(int)(Y/5)){
			Saut=0;
			SautCooldown=0;
			}
		}

	public boolean WallStick(int X, int Y, int width, int height){
		int Sx=0;
		int Sy=0;
		for(int i=y; i>=y-Size; i--){
			if (Y<=i && i<=Y+height){
				Sy=1;
				break;
				}
			}
		if ((X<=x && x<=X+width) || (X<=x+Size && x+Size<=X+width)) Sx=1;
		return Sx==1 && Sy==1;
		}
	public boolean LineOn(int Ax, int Ay, int Bx, int By){
		int b = -(Bx-Ax);
		int a = By-Ay;
		int c = -a*Ax-b*Ay;
		int xmax=0;
		int xmin=0;
		int On=0;
		if (Bx>Ax){
			xmax = Bx;
			xmin = Ax;
			}
		else if (Ax>Bx){
			xmax = Ax;
			xmin = Bx;
			}
		for(int i=xmin; i<xmax && b!=0; i++){
			int Y = (int)((-a*i-c)/b);
			if (i-1<=x && x<=i+1 && Y-1<=y-Size && y-Size<=Y+1) On=1;
			}
		return On==1 && Down==0;
		}

	public void Slide(int Ax, int Ay, int Bx, int By){
		int b = -(Bx-Ax);
		int a = By-Ay;
		int c = -(a*Ax+b*Ay);
		y = (int)((-a*x-c)/b)+Size;
		}
	public boolean LineColision(int Ax, int Ay, int Bx, int By){
		int b = -(Bx-Ax);
		int a = By-Ay;
		int c = -a*Ax-b*Ay;
		int max=0;
		int min=0;
		int On=0;
		//int X,Y;
		int L=0;
		if (Bx>Ax){
			max = Bx;
			min = Ax;
			L=1;
			}
		else if (Ax>Bx){
			max = Ax;
			min = Bx;
			L=1;
			}
		else if (By>Ay){
			max = By;
			min = Ay;
			L=2;
			}
		else if (Ay>By){
			max = Ay;
			min = By;
			L=2;
			}
		for(int i=min; i<max && L==1; i++){
			for(int j=y; j>y-Size; j--){
				int Y = (int)((-a*i-c)/b);
				if (i-1<=x && x<=i+1 && Y-1<=j && j<=Y+1) On=1;
				}
			}
		for(int i=min; i<max && L==2; i++){
			for(int j=x; j<=x+Size; j++){
				int X = (int)((-c)/a);
				if (X<=j && j<=X && i-1<=y && y-Size<=i+1) On=1;
				}
			}
		return On==1;
		}

	public void ColisionAll(int X, int Y, int width, int height){
		for(int i=y; i>y-Size; i--){
			if (Y<i && i<=Y+height){
				if (X<=x+Size && x+Size<=X+1) x=X-Size;
				if (X+width-1<=x && x<=X+width) x=X+width;
				}
			}
		for(int i=y; i>y-Size; i--){
			for(int j=x; j<x+Size; j++){
				if (X+1<=j && j<=X+width-1){
					if (Y<=i && i<=Y+1){
						//SautLock=0;
						//y-=1;
						}
					//if (Y<=i && i<=Y+1) Chute=0;
					//if (Y<=y && y<=Y+1) Sustained+=1;
					//if (Y<=y && y<=Y+1) y-=1;
					//if (Y<=y && y<=Y+1) y=Y;
					if (Y<=y && y<=Y+1) Sustained=1;
					else Sustained=0;
					if (Y+height-1<=i && i<=Y+height) y=Y+height+Size;
					//if (Sustained>1) Sustained=1;
					break;
					}
				else Sustained=0;
				}
			}
		}
	public void ColisionALL(int X, int Y, int width, int height){
		for(int i=y; i>y-Size; i--){
			if (Y<i && i<=Y+height){
				if (X<=x+Size && x+Size<=X+1) x=X-Size;
				if (X+width-1<=x && x<=X+width) x=X+width;
				}
			}
		for(int i=y; i>y-Size; i--){
			for(int j=x; j<x+Size; j++){
				if (X+1<=j && j<=X+width-1){
					if (Y<i && i<=Y+1){
						SautLock=0;
						y-=1;
						}
					//if (Y<=y && y<=Y+1) Sustained=1;
					//else Sustained=0;
					if (Y+height-1<=i && i<=Y+height) y=Y+height+Size;
					//if (Sustained>1) Sustained=1;
					break;
					}
				//else Sustained=0;
				}
			}
		}
	public void WallStickAll(int X, int Y, int width, int height){
		int ML=0;
		int MR=0;
		for(int i=y; i>y-Size; i--){
			if (Y<=i && i<=Y+height){
				if (X==x+Size){
					Sustained=WallStickedR=1;
					//y-=1;
					//WallStickedR+=1;
					//Sustained+=1;
					//SautLock=0;
					}
				if (X+width==x){
					Sustained=WallStickedL=1;
					//y-=1;
					//WallStickedL+=1;
					//Sustained+=1;
					//SautLock=0;
					}
				//if (WallStickedR>1) WallStickedR=1;
				//if (WallStickedL>1) WallStickedL=1;
				//if (Sustained>1) Sustained=1;
				break;
				}
			//if ((X!=x+Size || X+width!=x) && (WallStickedR==1 || WallStickedL==1 || Sustained==1)) Sustained=WallStickedR=WallStickedL=0;
			//else Sustained=WallStickedL=WallStickedR=0;
			}
		for(int i=x; i<x+Size; i++){
			if (X<=i && i<=X+width){
				if (y-Size==Y+height && Down==0){
					Sustained=1;
					//y-=1;//###
					//Motion=0;
					if (MotionL==1) ML=1;
					if (MotionR==1) MR=1;
					}
				//else Motion=1;
				}
			}
		if (MR==1) x-=1;
		if (ML==1) x+=1;
		}
	public int Parabol(int X, int x1, int y1, int x0, int y0){
		int Y=0;
		int X2 = X/10;
		int X1 = x1;
		int Y1 = y1;
		int X0 = x0;
		int Y0 = y0;
		int a = (Y0-Y1)/((X1*(2*X0-X1)+X0*X0));
		int c = Y0 + a*X0*X0;
		int b = (Y1-a*X1*X1-c)/X1;
		System.out.println(a+"\t"+b+"\t"+c);
		//Y = a*X*X + b*X + c;
		Y = 1*X2*X2 + 0*X2 + 0;
		return Y;
		}

	public int Norme(int X, int Y){
		return (int)Math.sqrt(X*X + Y*Y);
		}

	public boolean ColisionPoint(int X, int Y){
		int CPx=0;
		int CPy=0;
		for(int i=x; i<=x+Size; i++){
			if (i==X){
				CPx=1;
				break;
				}
			}
		for(int i=y; i>=y-Size; i--){
			if (i==Y){
				CPy=1;
				break;
				}
			}
		return CPx==1 && CPy==1;
		}

	public boolean CircleColision(int a, int b, int r){
		for(int i=x; i<=x+Size; i++){
			for(int j=y; j>=y-Size; j--){
				int XY = (i-a)*(i-a) + (j-b)*(j-b);
				if ( (r-1)*(r-1) <= XY && XY <= r*r) return true;
				}
			}
		return false;
		}
	public void ResetTab(){
		for(int i=0; i<50; i++) Tab[i]=0;
		}
	public boolean PolygonColision(int [] X, int [] Y){
		if (LineColision(X[0], Y[0], X[X.length-1], Y[X.length-1])) return true;
		for(int i=0; i<X.length-1; i++){
			if (LineColision(X[i], Y[i], X[i+1], Y[i+1])) return true;
			}
		return false;
		}
	public void ColisionRed(int X, int Y, int width, int height){
		if (Colision(X, Y, width, height)) initP=0;
		}
	public boolean LineColisionLeft(int Ax, int Ay, int Bx, int By){
		return LineColision(Ax, Ay, Bx, By) && x+Size==Ax;
		}
	public boolean LineColisionRight(int Ax, int Ay, int Bx, int By){
		return LineColision(Ax, Ay, Bx, By) && x==Ax;
		}
		
	}
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
//*****************************************************************************************************
class Pan2 extends JPanel{
	static int x=20;
	static int y=800;
	static int P1x=0;
	static int P1y=0;
	static int P1Tour=0;
	static int P1Trigger=0;
	static int P2x=0;
	static int P2y=0;
	static int P2Tour=0;
	static int P2Trigger=0;
	static int P3x=0;
	static int P3y=0;
	static int P3Tour=0;
	static int P3Trigger=0;
	static int Pmove=0;
	static int ButtonOn1=0;
	static int ButtonOn2=0;

	static int [] Tab = new int [50];
	static int info = 0;
	static int SLight=1000;

	static int initP=0;
	static int Time=0;
	static int Time2=0;
	static int Time3=0;
	static int TimeOn=0;
	static int TimeOn2=0;
	static int TimeOn3=0;
	static int Motion=1;
	static int Size=10;
	static int Sens=2;
	static int MotionR=0;
	static int MotionL=0;
	static int Down=0;
	static int Saut=0;
	static int Sustained=0;
	static int WallStickedR=0;
	static int WallStickedL=0;
	static int ForceSustained=0;
	static int LineOn=0;
	static int SautLock=0;
	static int SautCooldown=0;
	static int Chute=0;
	static int ColorP=0;
	static int LevelColor=0;
	static int Level=50;
	JButton gris = new JButton();

	static int Measurement=0;
	static int MeasurementX1=0;
	static int MeasurementY1=0;
	static int MeasurementX2=0;
	static int MeasurementY2=0;
	public Pan2(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));

		ToucheSaut Tsaut = new ToucheSaut();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(Tsaut);
		this.add(gris);

		SourisListener souris = new SourisListener();
		SourisMotionListener sourisMove = new SourisMotionListener();
		this.addMouseListener(souris);
		//gris.addMouseListener(souris);
		this.addMouseMotionListener(sourisMove);

		gris.addMouseMotionListener(sourisMove);
		}
	public void paintComponent(Graphics g){
		//g.setColor(new Color(10,10,10));
		g.setColor(Color.white);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		if (LevelColor==0) g.setColor(Color.black);
		else g.setColor(Color.red);
		g.fillRect(0, 8*this.getHeight()/9, this.getWidth(), this.getHeight()/9);
		g.setFont(new Font("Times New Roman", Font.BOLD, 25));
		//if (LevelColor==0) g.setColor(Color.white);
		//else g.setColor(Color.black);
		g.setColor(Color.white);
		g.drawLine(0, 800, 900, 800);
		//else if (Level>40) g.setColor(Color.black);
		g.drawString("level "+String.valueOf(Level), 410, 860);
		g.setColor(Color.blue);
		if (Level!=50) Size=10;
		if (x<0) x=0;
		else if (x+Size>900) x=850;
		if (MotionR==1) x+=1;
		if (MotionL==1) x-=1;
		if (Saut==1){
			SautLock=1;
			y-=5;
			SautCooldown+=1;
			if (SautCooldown==20){
				Saut=0;
				SautCooldown=0;
				}
			}
		if (Sustained==1 || y==800 || ForceSustained==1) SautLock=0;
		if (y==800 && LevelColor==1) initP=0;
		if (Saut==0 && y<8*this.getHeight()/9 && Sustained==0 && ForceSustained==0){
			y+=1;
			SautLock=1;
			}
		//else Chute=0;
		if (Chute==1 && ForceSustained==0) y+=1;

		if ((40<Level && Level<50) || (Level==50 && Tab[0]<20)){
			g.setColor(Color.red);
			g.fillRect(0, 0, 10+10*(50-Level), 800);
			if (Colision(0, 0, 10+10*(50-Level), 800)) initP=0;

			g.fillRect(900-(10+10*(50-Level)), 0, 10+10*(50-Level), 800);
			if (Colision(900-(10+10*(50-Level)), 0, 10+10*(50-Level), 800)) initP=0;
			}
//***Level 44
		if (Level==44){
			if (initP==0){
				x=90;
				y=60;
				ResetTab();
				/*
				* 0 is blue button
				* 1 is x width of death red wall time out
				*/
				Tab[0]=2;// 2 is start position, 0 is time out
				Tab[1]=0;
				Time=TimeOn=0;
				initP=1;
				}
//*Start
			Color color = new Color(100, 150, 200);
			g.setColor(color);
			g.fillRect(70, 60, 35, 10);
			ColisionALL(70, 60, 35, 10);

			g.fillRect(795, 60, 35, 10);
			ColisionALL(795, 60, 35, 10);

			for(int i=0; i<9; i++){
				g.fillRect(105+15+i*75, 60, 60, 10);
				ColisionALL(105+15+i*75, 60, 60, 10);
				}
//*1st
			g.fillRect(85, 70, 10, 25);
			ColisionAll(85, 70, 10, 25);

			g.fillRect(95, 85, 60, 10);
			ColisionALL(95, 85, 60, 10);

			g.fillRect(170, 70, 10, 50);
			ColisionAll(170, 70, 10, 50);

			g.fillRect(85, 110, 85, 10);
			ColisionALL(85, 110, 85, 10);

			g.fillRect(85, 120, 10, 615);
			ColisionAll(85, 120, 10, 615);
//*2nd
			g.fillRect(220, 70, 10, 15);
			ColisionAll(220, 70, 10, 15);

			g.fillRect(195, 85, 80, 10);
			ColisionALL(195, 85, 80, 10);
		
			g.fillRect(180, 110, 70, 10);
			ColisionALL(180, 110, 70, 10);
		
			g.fillRect(265, 95, 10, 75);
			ColisionAll(265, 95, 10, 75);

			g.fillRect(200, 135, 75, 10);
			ColisionALL(200, 135, 75, 10);

			g.fillRect(185, 160, 80, 10);
			ColisionALL(185, 160, 80, 10);

			g.fillRect(110, 135, 75, 10);
			ColisionALL(110, 135, 75, 10);

			g.fillRect(175, 145, 10, 25);
			ColisionAll(175, 145, 10, 25);

			g.fillRect(95, 185, 25, 10);
			ColisionALL(95, 185, 25, 10);

			g.fillRect(110, 160, 50, 10);
			ColisionALL(110, 160, 50, 10);

			g.fillRect(110, 170, 10, 15);
			ColisionAll(110, 170, 10, 15);

			for(int j=0; j<2; j++){
				for(int i=0; i<8; i++){
					g.fillRect(110+i*75, 285+j*50, 60, 10);
					ColisionALL(110+i*75, 285+j*50, 60, 10);
					}
				}

			for(int i=0; i<3; i++){
				for(int j=0; j<6; j++){
					if (i==1){
						g.fillRect(95+j*50, 235, 35, 10);
						ColisionALL(95+j*50, 235, 35, 10);
						}
					else{
						g.fillRect(110+j*50, 210+i*25, 35, 10);
						ColisionALL(110+j*50, 210+i*25, 35, 10);
						}
					}
				}
			for(int i=0; i<7; i++){
				for(int j=0; j<2; j++){
					if (j==0){
						g.fillRect(140+i*75, 310+j*50, 60, 10);
						ColisionALL(140+i*75, 310+j*50, 60, 10);
						}
					if (j==1){
						if (i<3){
							g.fillRect(140+i*75, 310+j*50, 60, 10);
							ColisionALL(140+i*75, 310+j*50, 60, 10);
							}
						else if (i==3){
							g.fillRect(140+i*75, 310+j*50, 135, 10);
							ColisionALL(140+i*75, 310+j*50, 135, 10);
							}
						else if (i==5){
							g.fillRect(140+i*75, 310+j*50, 135, 10);
							ColisionALL(140+i*75, 310+j*50, 135, 10);
							}
						}
					if (i==0){
						g.fillRect(95, 310+j*50, 30, 10);
						ColisionALL(95, 310+j*50, 30, 10);
						g.fillRect(665, 310+j*50, 30, 10);
						ColisionALL(665, 310+j*50, 30, 10);
						}
					}
				}
			for(int i=0; i<5; i++){
				g.fillRect(110+i*65, 385, 50, 10);
				ColisionALL(110+i*65, 385, 50, 10);			
				if (i<4){
					g.fillRect(140+i*70, 410, 55, 10);
					ColisionALL(140+i*70, 410, 55, 10);
					}
				}
			g.fillRect(280, 395, 10, 15);
			ColisionAll(280, 395, 10, 15);

			g.fillRect(95, 410, 30, 10);
			ColisionALL(95, 410, 30, 10);
			for(int i=0; i<3; i++){
				for(int j=0; j<5; j++){
					if (i%2==0){
						if (i==0 && j==2){
							g.fillRect(110+j*73, 435+i*25, 57, 10);
							ColisionALL(110+j*73, 435+i*25, 57, 10);
							}
						else if (i==0 && j>2){
							g.fillRect(109+j*73, 435+i*25, 58, 10);
							ColisionALL(109+j*73, 435+i*25, 58, 10);
							}
						else{
							g.fillRect(110+j*73, 435+i*25, 58, 10);
							ColisionALL(110+j*73, 435+i*25, 58, 10);
							}
						}
					else if (j<4){
						g.fillRect(153+j*75, 460, 60, 10);
						ColisionALL(153+j*75, 460, 60, 10);
						}
					}
				}
			g.fillRect(95, 460, 43, 10);	
			ColisionALL(95, 460, 43, 10);
			g.fillRect(109+4*73+58, 435, 1, 10);
			g.fillRect(453, 460, 7, 10);
			ColisionALL(453, 460, 7, 10);

			g.fillRect(303, 445, 10, 15);
			ColisionAll(303, 445, 10, 15);

			for(int i=0; i<5; i++){
				g.fillRect(95+i*103, 510, 88, 10);
				ColisionALL(95+i*103, 510, 88, 10);

				if (i<4){
					g.fillRect(110+i*120, 535, 105, 10);
					ColisionALL(110+i*120, 535, 105, 10);
					}
				else if (i==4){
					g.fillRect(110+i*120, 535, 19, 10);
					ColisionALL(110+i*120, 535, 19, 10);
					}

				g.fillRect(95+i*103, 560, 88, 10);
				ColisionALL(95+i*103, 560, 88, 10);
				}
			for(int i=0; i<3; i++){
				g.fillRect(247+i*115, 585, 100, 10);
				ColisionALL(247+i*115, 585, 100, 10);
				}
			g.fillRect(110, 585, 122, 10);
			ColisionALL(110, 585, 122, 10);
			g.fillRect(592, 585, 66, 10);
			ColisionALL(592, 585, 66, 10);

		
			g.fillRect(507, 520, 10, 15);
			ColisionAll(507, 520, 10, 15);
			g.fillRect(530, 545, 10, 15);
			ColisionAll(530, 545, 10, 15);
			g.fillRect(567, 570, 10, 15);
			ColisionAll(567, 570, 10, 15);
			g.fillRect(477, 595, 10, 15);
			ColisionAll(477, 595, 10, 15);
			g.fillRect(595+24-5, 620, 10, 15);
			ColisionAll(595+24-5, 620, 10, 15);
			g.fillRect(620, 645, 10, 15);
			ColisionAll(620, 645, 10, 15);
			g.fillRect(645, 670, 10, 15);
			ColisionAll(645, 670, 10, 15);
			g.fillRect(670, 695, 10, 15);
			ColisionAll(670, 695, 10, 15);
			g.fillRect(670, 720, 10, 15);
			ColisionAll(670, 720, 10, 15);
			g.fillRect(650, 745, 10, 15);
			ColisionAll(650, 745, 10, 15);
			for(int i=0; i<4; i++){
				g.fillRect(145, 645+i*25, 10, 15);
				ColisionAll(145, 645+i*25, 10, 15);
				}
			g.fillRect(95, 610, 300, 10);
			ColisionALL(95, 610, 300, 10);
			g.fillRect(410, 610, 248-15, 10);
			ColisionALL(410, 610, 248-15, 10);
//**			
			for(int i=0; i<12; i++){
				if (i<10){
					g.fillRect(95+i*50, 635, 35, 10);
					ColisionALL(95+i*50, 635, 35, 10);
					}
				if (i<11){
					g.fillRect(120+i*50, 660, 35, 10);
					ColisionALL(120+i*50, 660, 35, 10);

					g.fillRect(120+i*50, 710, 35, 10);
					ColisionALL(120+i*50, 710, 35, 10);
					}
				if (i==11){
					g.fillRect(120+i*50, 660, 37, 10);
					ColisionALL(120+i*50, 660, 37, 10);

					g.fillRect(95+i*50+50, 685, 12, 10);
					ColisionALL(95+i*50+50, 685, 12, 10);

					g.fillRect(120+i*50, 710, 37-15, 10);
					ColisionALL(120+i*50, 710, 37-15, 10);
					}
				g.fillRect(95+i*50, 685, 35, 10);
				ColisionALL(95+i*50, 685, 35, 10);

				g.fillRect(145+i*50, 735, 35, 10);
				ColisionALL(145+i*50, 735, 35, 10);
				}
			g.fillRect(595, 635, 48, 10);
			ColisionALL(595, 635, 48, 10);

			g.fillRect(95, 660, 10, 10);
			ColisionALL(95, 660, 10, 10);
			g.fillRect(95, 710, 10, 10);
			ColisionALL(95, 710, 10, 10);
			g.fillRect(745, 735, 10, 10);
			ColisionALL(745, 735, 10, 10);

			g.fillRect(545, 720, 10, 15);
			ColisionAll(545, 720, 10, 15);
			g.fillRect(570, 720, 10, 15);
			ColisionAll(570, 720, 10, 15);
			g.fillRect(645, 720, 10, 15);
			ColisionAll(645, 720, 10, 15);
			g.fillRect(295, 745, 10, 15);
			ColisionAll(295, 745, 10, 15);
			for(int i=0; i<2; i++){
				g.fillRect(220+i*75, 670, 10, 15);
				ColisionAll(220+i*75, 670, 10, 15);
				g.fillRect(245+i*25, 645, 10, 15);
				ColisionAll(245+i*25, 645, 10, 15);
				g.fillRect(345+i*100, 695, 10, 15);
				ColisionAll(345+i*100, 695, 10, 15);
				g.fillRect(370+i*125, 670, 10, 15);
				ColisionAll(370+i*125, 670, 10, 15);
				g.fillRect(395+i*25, 645, 10, 15);
				ColisionAll(395+i*25, 645, 10, 15);
				g.fillRect(445+i*25, 645, 10, 15);
				ColisionAll(445+i*25, 645, 10, 15);
				}
			g.fillRect(445, 720, 10, 15);
			ColisionAll(445, 720, 10, 15);
			g.fillRect(470, 745, 10, 15);
			ColisionAll(470, 745, 10, 15);


			g.fillRect(170, 720, 10, 15);
			ColisionAll(170, 720, 10, 15);
			g.fillRect(220, 745, 10, 15);
			ColisionAll(220, 745, 10, 15);
			for(int i=0; i<9; i+=2){
				g.fillRect(195+i*50, 695, 10, 15);
				ColisionAll(195+i*50, 695, 10, 15);
				g.fillRect(220+i*50, 695, 10, 15);
				ColisionAll(220+i*50, 695, 10, 15);
				}
			for(int i=0; i<5; i++){
				g.setColor(color);
				g.fillRect(135+i*127, 760, 112, 10);
				ColisionALL(135+i*127, 760, 112, 10);
				if (i<4){
					g.setColor(Color.red);
					g.fillRect(120+i*127, 760, 15, 10);
					if (Colision(120+i*127, 760, 15, 10)) initP=0;
					}
				}				
//*3rd & 4th
			g.setColor(color);
			for(int i=0; i<2; i++){
				g.fillRect(275+i*60+15, 85, 45, 10);
				ColisionALL(275+i*60+15, 85, 45, 10);

				g.fillRect(315+i*40, 135, 25, 10);
				ColisionALL(315+i*40, 135, 25, 10);

				g.fillRect(300+i*40, 160, 25, 10);
				ColisionALL(300+i*40, 160, 25, 10);
				}
			g.fillRect(365, 110, 30, 10);
			ColisionALL(365, 110, 30, 10);

			g.fillRect(290, 110, 60, 10);
			ColisionALL(290, 110, 60, 10);

			g.fillRect(290, 120, 10, 50);
			ColisionAll(290, 120, 10, 50);

			g.fillRect(395, 160, 10, 35+75); //##
			ColisionAll(395, 160, 10, 35+75);

			g.fillRect(380, 160, 15, 10);
			ColisionALL(380, 160, 15, 10);
			for(int i=0; i<5; i++){
				g.fillRect(120+i*55, 185, 40, 10);
				ColisionALL(120+i*55, 185, 40, 10);
				}
			g.fillRect(175, 170, 10, 15);
			ColisionAll(175, 170, 10, 15);
			g.fillRect(185, 195, 10, 15);
			ColisionAll(185, 195, 10, 15);
			g.fillRect(170, 220, 10, 15);
			ColisionAll(170, 220, 10, 15);
			g.fillRect(160, 245, 10, 15);
			ColisionAll(160, 245, 10, 15);
			g.fillRect(185, 270, 10, 15);
			ColisionAll(185, 270, 10, 15);
			g.fillRect(185, 295, 10, 15);
			ColisionAll(185, 295, 10, 15);

			g.fillRect(300, 170, 10, 15);
			ColisionAll(300, 170, 10, 15);
			g.fillRect(235, 195, 10, 15);
			ColisionAll(235, 195, 10, 15);
			g.fillRect(220, 220, 10, 15);
			ColisionAll(220, 220, 10, 15);
			g.fillRect(210, 245, 10, 15);
			ColisionAll(210, 245, 10, 15);
			g.fillRect(275, 270, 10, 15);
			ColisionAll(275, 270, 10, 15);
			g.fillRect(260, 295, 10, 15);
			ColisionAll(260, 295, 10, 15);

			g.fillRect(310, 195, 10, 15);
			ColisionAll(310, 195, 10, 15);
			g.fillRect(320, 220, 10, 15);
			ColisionAll(320, 220, 10, 15);
			g.fillRect(310, 245, 10, 15);
			ColisionAll(310, 245, 10, 15);
			g.fillRect(335, 270, 10, 15);
			ColisionAll(335, 270, 10, 15);
			g.fillRect(335, 295, 10, 15);
			ColisionAll(335, 295, 10, 15);
			g.fillRect(410, 270, 10, 15);
			ColisionAll(410, 270, 10, 15);
			g.fillRect(410, 295, 10, 15);
			ColisionAll(410, 295, 10, 15);
			g.fillRect(385, 320, 10, 15);
			ColisionAll(385, 320, 10, 15);
			g.fillRect(385, 345, 10, 15);
			ColisionAll(385, 345, 10, 15);
			for(int i=0; i<2; i++){
				g.fillRect(160+i*150, 345, 10, 15);
				g.fillRect(185, 345, 10, 15);
				ColisionAll(160+i*150, 345, 10, 15);
				ColisionAll(185, 345, 10, 15);
				}
			g.fillRect(158, 420, 10, 15);
			ColisionAll(158, 420, 10, 15);
			g.fillRect(231, 420, 10, 15);
			ColisionAll(231, 420, 10, 15);
			g.fillRect(376, 420, 10, 15);
			ColisionAll(376, 420, 10, 15);
			g.fillRect(183, 445, 10, 15);
			ColisionAll(183, 445, 10, 15);
			g.fillRect(203, 470, 10, 15);
			ColisionAll(203, 470, 10, 15);
			g.fillRect(256, 470, 10, 15);
			ColisionAll(256, 470, 10, 15);

			g.fillRect(355, 495, 10, 15);
			ColisionAll(355, 495, 10, 15);

			g.fillRect(158, 495, 10, 15);
			ColisionAll(158, 495, 10, 15);
			g.fillRect(198, 495, 10, 15);
			ColisionAll(198, 495, 10, 15);
			g.fillRect(198, 520, 10, 15);
			ColisionAll(198, 520, 10, 15);

			g.fillRect(173, 545, 10, 15);
			for(int i=0; i<2; i++){
				g.fillRect(445+i*25, 520, 10, 15);
				ColisionAll(445+i*25, 520, 10, 15);
				}		
			g.fillRect(379, 545, 10, 15);
			ColisionAll(379, 545, 10, 15);
			
			g.fillRect(404, 570, 10, 15);
			ColisionAll(404, 570, 10, 15);

			g.fillRect(222, 595, 10, 15);
			ColisionAll(222, 595, 10, 15);
			g.fillRect(385, 595, 10, 15);
			ColisionAll(385, 595, 10, 15);
//*5th
			g.fillRect(395, 70, 10, 90);
			ColisionAll(395, 70, 10, 90);

			g.fillRect(420, 70, 10, 90);
			ColisionAll(420, 70, 10, 90);

			g.fillRect(405, 150, 15, 10);
			ColisionALL(405, 150, 15, 10);
//*6th
			g.fillRect(545, 70, 10, 75);
			ColisionAll(545, 70, 10, 75);

			g.fillRect(445, 85, 85, 10);
			ColisionALL(445, 85, 85, 10);

			g.fillRect(520, 95, 10, 75);
			ColisionAll(520, 95, 10, 75);

			g.fillRect(555, 135, 75, 10);
			ColisionALL(555, 135, 75, 10);

			g.fillRect(530, 160, 100, 10);
			ColisionALL(530, 160, 100, 10);

			g.fillRect(620, 145, 10, 15);
			ColisionAll(620, 145, 10, 15);
			for(int i=0; i<2; i++){
				g.fillRect(430+i*15, 110+i*25, 75, 10);
				ColisionALL(430+i*15, 110+i*25, 75, 10);
				}
			g.fillRect(445, 160, 60, 10);
			ColisionALL(445, 160, 60, 10);

			g.fillRect(420, 160, 10, 10);//
			ColisionALL(420, 160, 10, 10);

			g.fillRect(405, 235, 25, 10);
			ColisionALL(405, 235, 25, 10);

			g.fillRect(445, 235, 100, 10);
			ColisionALL(445, 235, 100, 10);

			g.fillRect(560, 235, 110, 10);
			ColisionALL(560, 235, 110, 10);

			g.fillRect(405, 260, 100, 10);
			ColisionALL(405, 260, 100, 10);

			g.fillRect(520, 260, 135, 10);
			ColisionALL(520, 260, 135, 10);
//*7th & 8th
			g.fillRect(570, 85, 50, 10);
			ColisionALL(570, 85, 50, 10);

			g.fillRect(635, 85, 60, 10);
			ColisionALL(635, 85, 60, 10);

			g.fillRect(570, 95, 10, 25);
			ColisionAll(570, 95, 10, 25);

			g.fillRect(590, 70, 10, 15);
			ColisionAll(590, 70, 10, 15);

			g.fillRect(620, 120, 10, 15);
			ColisionAll(620, 120, 10, 15);

			g.fillRect(595, 110, 85, 10);
			ColisionALL(595, 110, 85, 10);

			g.fillRect(645, 135, 50, 10);
			ColisionALL(645, 135, 50, 10);

			g.fillRect(645, 160, 35, 10);
			ColisionALL(645, 160, 35, 10);

			g.fillRect(670, 170, 10, 100);
			ColisionAll(670, 170, 10, 100);

			g.fillRect(620, 185, 50, 10);
			ColisionALL(620, 185, 50, 10);

			g.fillRect(420, 185, 185, 10);
			ColisionALL(420, 185, 185, 10);

			g.fillRect(475, 210, 180, 10);
			ColisionALL(475, 210, 180, 10);

			g.fillRect(405, 210, 55, 10);
			ColisionALL(405, 210, 55, 10);


			g.fillRect(520, 170, 10, 15);
			ColisionAll(520, 170, 10, 15);
			g.fillRect(595, 195, 10, 15);
			ColisionAll(595, 195, 10, 15);
			g.fillRect(495, 245, 10, 15);
			ColisionAll(495, 245, 10, 15);
			g.fillRect(645, 270, 10, 15);
			ColisionAll(645, 270, 10, 15);
			for(int i=0; i<2; i++){
				g.fillRect(535+i*25, 295, 10, 15);
				ColisionAll(535+i*25, 295, 10, 15);

				g.fillRect(535+i*25, 320, 10, 15);
				ColisionAll(535+i*25, 320, 10, 15);
				}
			g.fillRect(460, 320, 10, 15);
			ColisionAll(460, 320, 10, 15);
			g.fillRect(635, 320, 10, 15);
			ColisionAll(635, 320, 10, 15);
			g.fillRect(410, 345, 10, 15);
			ColisionAll(410, 345, 10, 15);
			g.fillRect(515, 345, 10, 15);
			ColisionAll(515, 345, 10, 15);

			g.fillRect(670, 410+15, 10, 40-15);
			ColisionALL(670, 410+15, 10, 40-15);

			g.fillRect(610, 385, 60, 10);
			ColisionALL(610, 385, 60, 10);

			g.fillRect(685, 385, 10, 25);
			ColisionALL(685, 385, 10, 25);

			g.fillRect(635, 395, 10, 15);
			ColisionAll(635, 395, 10, 15);

			g.fillRect(610, 410, 10, 20);
			ColisionAll(610, 410, 10, 20);
			g.fillRect(610, 430, 10, 20);
			ColisionAll(610, 430, 10, 20);

			g.fillRect(610, 425, 45, 10);
			ColisionALL(610, 425, 45, 10);

			g.fillRect(660, 395, 10, 15);
			ColisionAll(660, 395, 10, 15);

			g.fillRect(659, 450, 21, 10);
			ColisionALL(659, 450, 21, 10);

			g.fillRect(670, 460, 10, 15);
			ColisionAll(670, 460, 10, 15);

			g.fillRect(670, 475, 25, 10);
			ColisionALL(670, 475, 25, 10);

			g.fillRect(644, 475, 11, 10);
			ColisionALL(644, 475, 11, 10);

			g.fillRect(644, 500, 36, 10);
			ColisionALL(644, 500, 36, 10);
//*9th
			for(int i=0; i<2; i++){
				g.fillRect(695+i*25, 70, 10, 440);
				ColisionAll(695+i*25, 70, 10, 440);
				}
			g.fillRect(708, 525, 97, 10);
			ColisionALL(708, 525, 97, 10);

			g.fillRect(730, 110, 45, 10);
			ColisionALL(730, 110, 45, 10);

			g.fillRect(790, 110, 15, 10);
			ColisionALL(790, 110, 15, 10);

			g.fillRect(750, 120, 10, 390);
			ColisionAll(750, 120, 10, 390);

			for(int i=0; i<15; i++){
				if (i%2==0){
					g.fillRect(730, 135+i*25, 5, 10);
					ColisionALL(730, 135+i*25, 5, 10);
					}
				else{
					g.fillRect(745, 135+i*25, 5, 10);
					ColisionALL(745, 135+i*25, 5, 10);
					}
				g.fillRect(775, 135+i*25, 15, 10);
				ColisionALL(775, 135+i*25, 15, 10);	
				}
			g.fillRect(775, 510, 15, 10);
			ColisionALL(775, 510, 15, 10);

			g.fillRect(708, 535, 10, 50);
			ColisionAll(708, 535, 10, 50);

			g.fillRect(718, 575, 52, 10);
			ColisionALL(718, 575, 52, 10);

			g.fillRect(760, 585, 10, 75);
			ColisionAll(760, 585, 10, 75);
			for(int i=0; i<4; i++){
				if (i%2==0){
					g.fillRect(770, 575+i*25, 20, 10);
					ColisionALL(770, 575+i*25, 20, 10);
					}
				else{
					g.fillRect(785, 575+i*25, 20, 10);
					ColisionALL(785, 575+i*25, 20, 10);
					}
				}
			g.fillRect(733, 550, 72, 10);
			ColisionALL(733, 550, 72, 10);
//10th last, lux
			g.fillRect(770, 70, 10, 25);
			ColisionAll(770, 70, 10, 25);

			g.fillRect(745, 85, 35+35, 10);
			ColisionALL(745, 85, 35+35, 10);

			g.fillRect(805, 95 , 10, 665);
			ColisionAll(805, 95 , 10, 665);
			for(int i=0; i<5; i++){
				if (i==0){
					g.fillRect(585+i*44, 385+i*75, 10, 75);
					ColisionAll(585+i*44, 385+i*75, 10, 75);
					g.fillRect(595+i*44, 450+i*75, 44+5, 10);
					ColisionALL(595+i*44, 450+i*75, 44+5, 10);

					g.fillRect(560+i*44, 420+i*75, 10, 75-10);
					ColisionAll(560+i*44, 420+i*75, 10, 75-10);
					g.fillRect(570+i*44, 475+i*75, 44+5, 10);
					ColisionALL(570+i*44, 475+i*75, 44+5, 10);
					}
				else if (i<4){
					g.fillRect(585+i*49, 385+i*75, 10, 75);
					ColisionAll(585+i*49, 385+i*75, 10, 75);

					g.fillRect(560+i*49, 410+i*75, 10, 75);
					ColisionAll(560+i*49, 410+i*75, 10, 75);
					if (i==3){
						g.fillRect(595+i*49, 450+i*75, 44+4, 10);
						ColisionALL(595+i*49, 450+i*75, 44+4, 10);

						g.fillRect(570+i*49, 475+i*75, 44+4, 10);
						ColisionALL(570+i*49, 475+i*75, 44+4, 10);
						}
					else{
						g.fillRect(595+i*49, 450+i*75, 44+5, 10);
						ColisionALL(595+i*49, 450+i*75, 44+5, 10);

						g.fillRect(570+i*49, 475+i*75, 44+5, 10);
						ColisionALL(570+i*49, 475+i*75, 44+5, 10);
						}
					}
				else{
					g.fillRect(585+i*48+3, 385+i*75, 10, 75);
					ColisionAll(585+i*48+3, 385+i*75, 10, 75);
					g.fillRect(595+i*48+3, 450+i*75, 15, 10);
					ColisionALL(595+i*48+3, 450+i*75, 15, 10);

					g.fillRect(560+i*48+3, 410+i*75, 10, 75);
					ColisionAll(560+i*48+3, 410+i*75, 10, 75);
					g.fillRect(570+i*48+3, 475+i*75, 65, 10);
					ColisionALL(570+i*48+3, 475+i*75, 65, 10);
					}
				}
//560 bas vers 780 ; 585 haut vers 805
//220 en x
//410 en bas vers 775 ; 385 haut vers 760
//365 en y
			g.setColor(Color.yellow);
			g.fillRect(445, 405, 10, 5);
			ColisionALL(445, 405, 10, 5);
			if (Sustain(445, 405, 10, 5) && SLight<450) SLight+=1;
			else if (SLight>50) SLight-=1;
			g.setColor(color);
			g.drawRect(445, 405, 10, 5);
			g.fillRect(420, 395, 10, 25);
			ColisionAll(420, 395, 10, 25);

			g.fillRect(420, 385, 75, 10);
			ColisionALL(420, 385, 75, 10);

			g.fillRect(430, 410, 40, 10);
			ColisionALL(430, 410, 40, 10);

			g.fillRect(460, 420, 10, 75);
			ColisionAll(460, 420, 10, 75);

			g.fillRect(470, 485, 65, 10);
			ColisionALL(470, 485, 65, 10);
			for(int i=0; i<2; i++){
				g.fillRect(485+i*25, 385, 10, 85);
				ColisionAll(485+i*25, 385, 10, 85);
				}
			g.fillRect(495, 460, 15, 10);
			ColisionALL(495, 460, 15, 10);

			g.fillRect(510, 385, 85, 10);
			ColisionALL(510, 385, 85, 10);

			g.fillRect(535, 420, 10, 75);
			ColisionAll(535, 420, 10, 75);

			g.fillRect(535, 410, 35, 10);
			ColisionALL(535, 410, 35, 10);

			g.fillRect(70, 735, 60, 10);
			ColisionALL(70, 735, 60, 10);
			g.fillRect(110, 745, 10, 35);
			ColisionAll(110, 745, 10, 35);
			if (Tab[0]==2){
				g.setColor(Color.blue);
				g.fillRect(75, 47, 8, 8);
				if (Colision(75, 47, 8, 8)) Tab[0]=1;
				g.fillRect(110, 780, 20, 20);
				ColisionAll(110, 780, 20, 20);
				}
			else if (Tab[0]==1){
				g.setColor(Color.cyan);
				g.fillRect(75, 47, 8, 8);
				g.fillRect(110, 780, 20, 20);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==150) Time=Tab[0]=0;
				//g.setColor(Color.black);
				//g.setFont(new Font("Arial", Font.BOLD, 10));
				//g.drawString(String.valueOf(300-Time),111, 795);
				}
			if (Tab[0]==0){
				g.setColor(Color.red);
				g.fillRect(70, 0, Tab[1], 800);
				if (Colision(70, 0, Tab[1], 800)) initP=0;
				Tab[1]+=1;
				}
//***Goal Level 44
			g.setColor(color);
			g.fillRect(75, 795, 30, 5);
			ColisionALL(75, 795, 30, 5);
			g.setColor(Color.black);
			g.fillRect(80, 745, 20, 50);
			if (x==80 && y==795){
				Level=Pan.Level=45;
				initP=0;
				}

			//g.fillRect(100, 0, 700, 800);
			//SLight=1000;
			int LeftSide=0;
			int RightSide=900;
			int UpSide=0;
			int DownSide=800;
			g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
			if (y+SLight<=DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
				g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
				}
			else if (y+SLight>DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
				g.fillRect(LeftSide, y, x-SLight, DownSide-y);
				g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
				}
			if (Tab[0]==1){
				g.setColor(Color.cyan);
				g.fillRect(75, 47, 8, 8);
				g.fillRect(110, 780, 20, 20);
				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 10));
				g.drawString(String.valueOf(150-Time),111, 795);
				}
			}

//***Level 45
		else if (Level==45){
			if (initP==0){
				x=80;
				y=790;
				SLight=50;
				ResetTab();
				/*
				* 0 red button count
				* 1 is first blue button
				* 2 is second blue button
				* 3 is third blue button
				* 4 is fourth blue button
				* 5 is x cos move of block in third level
				* 6 is y sin move of block in third level
				* 7 is movement of circle block in third level
				* 8 is x move of block in third level
				* 9 is its trigger
				* 10 is x move of block in third level with sustained
				* 11 is its trigger
				* 12 is x move of block in fourth level
				* 13 is its trigger
				* 14 is portal trigger side (-x)
				* 15 is x of boat
				* 16 is y of boat
				* 17 is y of red wave height
				* 18 is x position of duo block with yellow line in second level
				* 19 is their trigger by incrementation
				*/
				Tab[0]=0;
				Tab[15]=65;
				Tab[16]=790;
				Tab[17]=800;
				//Tab[18]=485;
				Tab[8]=120;
				Tab[10]=320;
				LevelColor=1;
				initP=1;
				}
			Color color = new Color(50, 150, 100);
			//int c1 = (int)(Math.random()*256);
			//int c2 = (int)(Math.random()*256);
			//int c3 = (int)(Math.random()*256);
			//g.setColor(new Color(c1,c2,c3));
//*Boat & red wave move
			g.setColor(color);
			g.fillRect(Tab[15], Tab[16], 30, 10);
			ColisionALL(Tab[15], Tab[16], 30, 10);
			for(int i=0; i<2; i++){
				g.fillRect(Tab[15]-2+i*32, Tab[16]-2, 2, 12);
				ColisionALL(Tab[15]-2+i*32, Tab[16]-2, 2, 12);
				}
			g.setColor(Color.blue);
			g.fillRect(Tab[15], Tab[16], 5, 3);
			g.fillRect(Tab[15]+25, Tab[16], 5, 3);
			if (Sustain(Tab[15], Tab[16], 5, 3) && Tab[15]-2>60){
				Tab[15]-=1;
				x-=1;
				}
			if (Sustain(Tab[15]+25, Tab[16], 5, 3) && Tab[15]+30+2<840){
				Tab[15]+=1;
				x+=1;
				}
			if (y<Tab[16] && Tab[0]<3){
				if (x>Tab[15]+30) Tab[15]+=1;
				else if (x+Size<Tab[15]) Tab[15]-=1;
				}
			else if (y<165){
				if (900-x>Tab[15]+30) Tab[15]+=1;
				else if (900-(x+Size)<Tab[15]) Tab[15]-=1;
				}

			if (Tab[0]==1 && Tab[17]>600 && Sustain(Tab[15], Tab[16], 30, 2)){
				if (MotionL==1) x+=1;
				if (MotionR==1) x-=1;
				//Motion=0;
				Tab[17]-=1;
				Tab[16]-=1;
				y-=1;
				}
			else if (Tab[0]==2 && Tab[17]>400 && Sustain(Tab[15], Tab[16], 30, 2)){
				if (MotionL==1) x+=1;
				if (MotionR==1) x-=1;
				//Motion=0;
				Tab[17]-=1;
				Tab[16]-=1;
				y-=1;
				}
			else if (Tab[0]==3 && Tab[17]>200 && Sustain(Tab[15], Tab[16], 30, 2)){
				if (MotionL==1) x+=1;
				if (MotionR==1) x-=1;
				//Motion=0;
				Tab[17]-=1;
				Tab[16]-=1;
				y-=1;
				}
//**First level
			g.setColor(color);
			g.fillRect(60, 750, 40, 10);
			if  (Tab[0]==0) ColisionALL(60, 750, 40, 10);

			g.fillRect(140, 710, 40, 10);
			if  (Tab[0]==0) ColisionALL(140, 710, 40, 10);

			g.fillRect(220, 650, 95, 10);
			if  (Tab[0]==0) ColisionALL(220, 650, 95, 10);

			for(int i=0; i<18; i+=2){
				g.fillRect(340+i*25, 710, 25, 10);
				if  (Tab[0]==0) ColisionALL(340+i*25, 710, 25, 10);
				}


			g.fillRect(790, 600, 10, 70);
			if  (Tab[0]==0) ColisionAll(790, 600, 10, 70);
			g.fillRect(800, 616, 20, 10);
			if  (Tab[0]==0) ColisionALL(800, 616, 20, 10);

			g.fillRect(790, 710, 50, 10);
			if  (Tab[0]==0) ColisionALL(790, 710, 50, 10);
			g.fillRect(790, 720, 10, 10);
			if  (Tab[0]==0) ColisionAll(790, 720, 10, 10);
			g.fillRect(820, 740, 20, 10);
			if  (Tab[0]==0) ColisionALL(820, 740, 20, 10);
//*1st buttons
			g.setColor(Color.red);
			if (Tab[0]==0){
				g.fillRect(806, 602, 8, 8);
				if (Colision(806, 602, 8, 8)){
					Tab[0]=1;
					Time=0;
					}
				}
			if (Tab[1]==0){
				g.setColor(Color.blue);
				g.fillRect(826, 726, 8, 8);
				if (Colision(826, 726, 8, 8)) Tab[1]=1;
				g.fillRect(250, 600, 15, 50);
				ColisionAll(250, 600, 15, 50);
				}
			else if (Tab[1]==1){
				g.setColor(Color.cyan);
				g.fillRect(826, 726, 8, 8);
				g.fillRect(250, 600, 15, 50);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==20) Time=Tab[1]=0;
				}
//**2nd level
			g.setColor(color);
			g.fillRect(800, 510, 10, 60);
			if (Tab[0]==1) ColisionAll(800, 510, 10, 60);
			g.fillRect(826, 530, 14, 10);
			if (Tab[0]==1) ColisionALL(826, 530, 14, 10);

			g.fillRect(760, 480, 10, 90);
			if (Tab[0]==1) ColisionALL(760, 480, 10, 90);

			g.fillRect(800, 500, 40, 10);
			if (Tab[0]==1) ColisionALL(800, 500, 40, 10);

			g.fillRect(760, 420, 60, 10);
			if (Tab[0]==1) ColisionALL(760, 420, 60, 10);

			g.fillRect(620, 470, 10, 10);
			if (Tab[0]==1) ColisionALL(620, 470, 10, 10);
			g.fillRect(630, 490, 20, 10);
			if (Tab[0]==1) ColisionALL(630, 490, 20, 10);
			g.fillRect(620, 400, 15, 5);
			if (Tab[0]==1) ColisionAll(620, 400, 15, 5);

			g.fillRect(300-10, 490, 50+10, 10);
			if (Tab[0]==1) ColisionALL(300-10, 490, 50+10, 10);

			g.fillRect(230, 420, 10, 70);
			if (Tab[0]==1) ColisionALL(230, 420, 10, 70);

			for(int i=0; i<2; i++){
				g.fillRect(485-Tab[18]-10, 400+i*90, 10, 10); 
				g.fillRect(485+Tab[18], 400+i*90, 10, 10);
				if (Tab[0]==1) ColisionALL(485-Tab[18]-10, 400+i*90, 10, 10); 
				if (Tab[0]==1) ColisionALL(485+Tab[18], 400+i*90, 10, 10);
				}
			if (Tab[19]==0) Tab[18]+=1;
			if (Tab[19]==1) Tab[18]-=1;
			if (Tab[18]==125) Tab[19]=1;
			if (Tab[18]==0) Tab[19]=0;
			for(int i=0; i<2; i++){
				g.fillRect(150+i*40, 490, 10, 10);
				if (Tab[0]==1) ColisionALL(150+i*40, 490, 10, 10);
				}
			g.fillRect(100, 400, 10, 60);
			if (Tab[0]==1) ColisionAll(100, 400, 10, 60);
			g.fillRect(110, 450, 10, 10);
			if (Tab[0]==1) ColisionALL(110, 450, 10, 10);
			g.fillRect(60, 500, 60, 10);
			if (Tab[0]==1) ColisionALL(60, 500, 60, 10);

			g.fillRect(230, 400, 10, 5);
			if (Tab[0]==1) ColisionAll(230, 400, 10, 5);

			g.fillRect(150, 400, 10, 5);
			if (Tab[0]==1) ColisionAll(150, 400, 10, 5);
			g.fillRect(190, 400, 10, 5);
			if (Tab[0]==1) ColisionAll(190, 400, 10, 5);
//*yellow lines
			g.setColor(Color.orange);
			g.drawLine(830, 400, 830, 500);
			if (LineColision(830, 400, 830, 500)) y-=2;
			g.drawLine(760, 420, 630, 470);
			if (LineOn(760, 420, 630, 470)){
				Slide(760, 420, 630, 470);
				x-=1;
				}
			g.drawLine(485-5-Tab[18], 410, 485-5-Tab[18], 490);
			if (Tab[0]==1) if (LineColision(485-5-Tab[18], 410, 485-5-Tab[18], 490)) y-=2;
			g.drawLine(485+5+Tab[18], 410, 485+5+Tab[18], 490);
			if (Tab[0]==1) if (LineColision(485+5+Tab[18], 410, 485+5+Tab[18], 490)) y-=2;

			g.drawLine(300, 490, 235, 405);
			if (Tab[0]==1) if (LineOn(300, 490, 235, 405)){
						Slide(300, 490, 235, 405);
						x-=1;
						}
			g.drawLine(155, 405, 155, 490);
			if (Tab[0]==1) if (LineColision(155, 405, 155, 490)) y-=2;
			g.drawLine(195, 405, 195, 490);
			if (Tab[0]==1) if (LineColision(195, 405, 195, 490)) y-=2;

			g.drawLine(80, 400, 80, 500);
			if (Tab[0]==1) if (LineColision(80, 400, 80, 500)) y-=2;
				

//*2nd buttons
			if (Tab[2]==0){
				g.setColor(Color.blue);
				g.fillRect(829, 516, 8, 8);
				if (Colision(829, 516, 8, 8)) Tab[2]=1;
				g.fillRect(770, 555, 30, 15);
				ColisionAll(770, 555, 30, 15);
				
				if (Tab[0]==1){
					g.fillRect(105, 460, 15, 40);
					ColisionAll(105, 460, 15, 40);
					}
				}
			else if (Tab[2]==1){
				g.setColor(Color.cyan);
				g.fillRect(829, 516, 8, 8);
				g.fillRect(770, 555, 30, 15);

				g.fillRect(105, 460, 15, 40);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==20) Time=Tab[2]=0;
				}
			if (Tab[0]==1){
				g.setColor(Color.red);
				g.fillRect(86, 406, 8, 8);
				if (Colision(86, 406, 8, 8)){
					Tab[0]=2;
					Time=0;
					}
				}

//*3rd level
			g.setColor(color);
			g.fillRect(80, 340, 20, 10);
			if (Tab[0]==2) ColisionALL(80, 340, 20, 10);

			g.fillRect(60, 270, 20, 10);
			if (Tab[0]==2) ColisionALL(60, 270, 20, 10);

			g.fillRect(60, 240, 20, 10);
			if (Tab[0]==2) ColisionALL(60, 240, 20, 10);

			g.fillRect(120, 270, 20, 10);
			if (Tab[0]==2) ColisionALL(120, 270, 20, 10);

			g.fillRect(Tab[8], 215, 20, 10);//green down move on sustain
			if (Tab[0]==2) ColisionALL(Tab[8], 215, 20, 10);
			//WallStickAll(Tab[8]+1, 225-5, 18, 5+2);
			if (WallStick(Tab[8]+1, 225-5, 18, 5+5)){
				//y=225+Size-1;
				if (Tab[9]==0){
					Tab[8]+=1;
					x+=1;
					}
				if (Tab[9]==1){
					Tab[8]-=1;
					x-=1;
					}
				if (Tab[8]==300) Tab[9]=1;
				if (Tab[8]==120) Tab[9]=0;
				}
			else if (Tab[8]>121) Tab[8]-=1;
			g.fillRect(Tab[10], 240, 20, 10);
			if (Tab[0]==2) ColisionALL(Tab[10], 240, 20, 10);
			if (Tab[11]==1) Tab[10]+=1;
			if (Tab[11]==0) Tab[10]-=1;
			if (Tab[10]==230) Tab[11]=1;
			if (Tab[10]==360) Tab[11]=0;
			if (Sustain(Tab[10], 240, 20, 5)){
				if (Tab[11]==1) x+=1;
				if (Tab[11]==0) x-=1;
				}
			g.fillRect(380, 240, 20, 10);
			if (Tab[0]==2) ColisionALL(380, 240, 20, 10);

			Tab[5]=450+(int)(50*Math.cos((Tab[7]*3.14)/500));
			Tab[6]=250+(int)(40*Math.sin((Tab[7]*3.14)/500));
			g.fillRect(Tab[5], Tab[6], 20, 10);
			if (Tab[0]==2) ColisionALL(Tab[5], Tab[6], 20, 10);
			if (Sustain(Tab[5], Tab[6], 20, 10)){
				if (Tab[49]==0) Tab[49]=x-Tab[5];
				x=450+(int)(50*Math.cos((Tab[7]*3.14)/500)) +Tab[49];
				y=250+(int)(40*Math.sin((Tab[7]*3.14)/500));
				if (MotionL==1) Tab[49]-=1;
				if (MotionR==1) Tab[49]+=1;
				}
			else Tab[49]=0;
			Tab[7]+=1;

			g.fillRect(520, 220, 10, 60);
			if (Tab[0]==2) ColisionALL(520, 220, 10, 60);
			g.fillRect(510, 200, 10, 5);
			if (Tab[0]==2) ColisionAll(510, 200, 10, 5);
			g.fillRect(530, 200, 10, 5);
			if (Tab[0]==2) ColisionAll(530, 200, 10, 5);

			g.fillRect(560, 200, 10, 40);
			if (Tab[0]==2) ColisionAll(560, 200, 10, 40);
			g.fillRect(570, 230, 200, 10);
			if (Tab[0]==2) ColisionALL(570, 230, 200, 10);

			g.fillRect(560, 260, 140, 10);
			if (Tab[0]==2) ColisionALL(560, 260, 140, 10);
			g.fillRect(630, 240, 15, 20);
			if (Tab[0]==2) ColisionAll(630, 240, 15, 20);

			g.fillRect(560, 290, 140, 10);
			if (Tab[0]==2) ColisionALL(560, 290, 140, 10);


			g.fillRect(720, 290, 15, 10);
			if (Tab[0]==2) ColisionALL(720, 290, 15, 10);

			g.fillRect(775, 290, 15, 10);
			if (Tab[0]==2) ColisionALL(775, 290, 15, 10);

			g.fillRect(775, 200, 15, 5);
			if (Tab[0]==2) ColisionAll(775, 200, 15, 5);
//*green
			g.setColor(Color.green);
			g.fillRect(80-5, 240, 5, 10);
			//WallStickAll(80-5, 240, 5, 10);

			g.fillRect(Tab[8]+1, 225-5, 18, 5);
			//WallStickAll(Tab[8]+1, 225-5, 18, 5);

			for(int i=0; i<2; i++){
				g.fillRect(520+i*7, 220, 3, 60);
				//WallStickAll(520+i*7, 220, 3, 60);
				}
			//WallStickAll(Tab[8]+1, 225-5, 18, 5);
//*Buttons
			g.setColor(Color.red);
			if (Tab[0]==2){
				g.fillRect(580, 214, 8, 8);
				if (Colision(580, 214, 8, 8)){
					Tab[0]=3;
					Time=0;
					}
				}
			if (Tab[3]==0){
				g.setColor(Color.blue);
				g.fillRect(66, 256, 8, 8);
				if (Colision(66, 256, 8, 8)) Tab[3]=1;
				g.fillRect(685, 270, 15, 20);
				ColisionAll(685, 270, 15, 20);
				}
			else if (Tab[3]==1){
				g.setColor(Color.cyan);
				g.fillRect(66, 256, 8, 8);
				g.fillRect(685, 270, 15, 20);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==20) Time=Tab[3]=0;
				}
//*4th level
			g.setColor(color);
			g.fillRect(710, 20, 10, 145);
			ColisionALL(710, 20, 10, 145);

			g.fillRect(790, 160, 30, 5);
			ColisionALL(790, 160, 30, 5);

			g.fillRect(680, 130-1, 10, 35+1);
			ColisionALL(680, 130-1, 10, 35+1);
			for(int i=0; i<2; i++){
				g.setColor(color);
				g.fillRect(60, 0+i*170, 780, 5);
				ColisionAll(60, 0+i*170, 780, 5);
				for(int j=0; j<26; j++){
					if (y<160){
						if (i==0) g.setColor(Color.cyan);
						else if (i==1) g.setColor(Color.magenta);
						}
					else if (y>170){
						if (i==0) g.setColor(Color.magenta);
						else if (i==1){
							g.setColor(Color.cyan);
							if (Colision(60, 170, 780, 5+2)){
							//if (Colision(60+j*30, 0+i*170, 30, 5+2)){
								//x=900-x;
								//y=5+Size+2;
								//break;
								}
							}
						}

					g.fillOval(60+j*30, 0+i*170, 30, 5);
					}
				if (i==1) if (Colision(60+30, 0+i*170, 780-60, 5+2) && Tab[14]==0){
					Tab[14]=1;
					x=900-x;
					y=5+Size;
					}
				if (i==0) if (Colision(60+30, 0+i*170, 780-60, 5+2) && Tab[14]==2){
					Tab[14]=1;
					x=900-x;
					y=170+Size;
					}
				}
			//if (info==1) System.out.println(Tab[14]+"\t"+Tab[16]);
			if (y==Tab[16] && Tab[14]==2) Tab[14]=0;
			if (y>=20 && Tab[14]==1) Tab[14]=2;
			for(int i=0; i<2; i++){
				g.setColor(color);
				g.fillRect(100+i*60, 20, 10, 145);
				ColisionALL(100+i*60, 20, 10, 145);
				g.fillRect(170, 25+i*70, 45, 10);
				ColisionALL(170, 25+i*70, 45, 10);

				g.fillRect(440+i*210, 20, 10, 145);
				ColisionALL(440+i*210, 20, 10, 145);

				g.fillRect(830, 50+i*65, 10, 15);
				ColisionALL(830, 50+i*65, 10, 15);
				for(int j=0; j<5; j++){
					g.setColor(color);
					g.fillRect(250+j*40, 20+i*80, 10, 30-i*20);
					ColisionALL(250+j*40, 20+i*80, 10, 30-i*20);
					g.setColor(Color.green);
					g.fillRect(250+j*40, 20+i*80, 3, 30-i*20);
					g.fillRect(257+j*40, 20+i*80, 3, 30-i*20);
					}
				g.fillRect(107, 50+i*90, 3, 20);
				g.fillRect(160, 50+i*90, 3, 20);

				g.fillRect(212, 95, 3, 10);

				g.fillRect(447+i*203, 75, 3, 20);

				g.fillRect(830, 50+i*65, 3, 15);
				}
				g.fillRect(160, 50+0*90, 3, 20);
			g.fillRect(100, 20, 3, 145);
	
			g.fillRect(710, 100, 3, 20);

			g.fillRect(687, 130-1, 3, 35+1);

			g.setColor(color);
			g.fillRect(Tab[12], 155, 50, 10);
			ColisionALL(Tab[12], 155, 50, 10);
			if (Tab[13]==0) Tab[12]+=1;
			if (Tab[13]==1) Tab[12]-=1;
			if (Tab[12]==250) Tab[13]=0;
			if (Tab[12]+50==440) Tab[13]=1;
			if (Sustain(Tab[12], 155, 50, 10)){
				if (Tab[13]==0) x+=1;
				if (Tab[13]==1) x-=1;
				}

//*Buttons

			if (Tab[4]==0){
				g.setColor(Color.blue);
				g.fillRect(696, 143, 8, 8);
				if (Colision(696, 143, 8, 8)) Tab[4]=1;
				g.fillRect(200, 35, 15, 60);
				ColisionAll(200, 35, 15, 60);
				}
			else if (Tab[4]==1){
				g.setColor(Color.cyan);
				g.fillRect(696, 143, 8, 8);
				g.fillRect(200, 35, 15, 60);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==20) Time=Tab[4]=0;
				}
//*Lines
			g.setColor(Color.orange);
			g.drawLine(450, 20, 650, 20);
			if (LineOn(450, 20, 650, 20)){
				y=20+Size;
				//if (MotionR==1) x+=1;
				//if (MotionL==1) x-=1;
				}
			g.drawLine(720, 20, 830, 115);
			if (LineOn(720, 20, 830, 115)){
				Slide(720, 20, 830, 115);
				x+=1;
				}
			g.setColor(Color.red);
			g.fillRect(60, 165, 780, 5);
			if (Colision(60, 165, 780, 5)) initP=0;


//*Red dead & red wave
			g.setColor(Color.red);
			g.fillRect(60, Tab[17], 780, 800-Tab[17]);
			if (Colision(60, Tab[17], 780, 800-Tab[17])) initP=0;
			if (Tab[0]==0){
				g.fillRect(60, 580, 780, 20);
				if (Colision(60, 580, 780, 20)) initP=0;
				}
			else if (Tab[0]==1){
				g.fillRect(60, 380, 780, 20);
				if (Colision(60, 380, 780, 20)) initP=0;
				}
			else if (Tab[0]==2){
				g.fillRect(60, 180, 780, 20);
				if (Colision(60, 180, 780, 20)) initP=0;
				}

				
				
				



//***Goal Level 45
			g.setColor(Color.black);
			g.fillRect(175, 45, 20, 50);
			if (x==175 && y==95){
				Level=Pan.Level=46;
				initP=0;
				LevelColor=0;
				}

			//g.fillRect(100, 0, 700, 800);
			//SLight=1000;
			int LeftSide=0;
			int RightSide=900;
			int UpSide=0;
			int DownSide=800;
			g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
			if (y+SLight<=DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
				g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
				}
			else if (y+SLight>DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
				g.fillRect(LeftSide, y, x-SLight, DownSide-y);
				g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
				}


			WallStickAll(80-5, 240, 5, 10);
			for(int i=0; i<2; i++) WallStickAll(520+i*7, 220, 3, 60);
			WallStickAll(Tab[8]+1, 225-5, 18, 5);
			for(int i=0; i<2; i++){
				for(int j=0; j<5; j++){
					WallStickAll(250+j*40, 20+i*80, 3, 30-i*20);
					WallStickAll(257+j*40, 20+i*80, 3, 30-i*20);
					}
				WallStickAll(107, 50+i*90, 3, 20);
				WallStickAll(160, 50+i*90, 3, 20);
				WallStickAll(212, 95, 3, 10);
				WallStickAll(447+i*203, 75, 3, 20);
				WallStickAll(830, 50+i*65, 3, 15);
				}
			WallStickAll(100, 20, 3, 145);
			WallStickAll(710, 100, 3, 20);
			WallStickAll(687, 130-1, 3, 35+1);
			}
//***Level 46
		else if (Level==46){
			if (initP==0){
				x=175;
				y=95;
				SLight=50;
				ResetTab();
				Time=TimeOn=Time2=TimeOn2=0;
				/*
				* 0 is sum of red buttons 
				* 1 is red button 1
				* 2 is red button 2
				* 3 is red button 3
				* 4 is red button 4
				* 5 is red button 5
				* 6 is red button 6
				* 7 is red button 7
				* 8 is red button 8
				* 9 is red button 9
				* 10 is x,y addition to block in R1
				* 11 is y addition of block in R1 to blue button 3
				* 12 is blue button 3 in R1 (to R7)
				* 13 is blue button 5 in R2 (to R5)
				* 14 is y additionnal to blocks in R3
				* 15 is x position of red 20 block in R4
				* 16 is y position of red 20 block in R4
				* 17 is its trigger
				* 18 is x,y addition of 4 red blocks diagonal in R4
				* 19 is their trigger
				* 20 is blue button of R4
				* 21 is blue button 2 in R5 (to R3 last gate)
				* 22 is y position of block sustain in R7
				* 23 is x cos position of first meet block in R7
				* 24 is y sin position of first meet block in R7
				* 25 is x cos position of second meet block in R7
				* 26 is y sin position of second meet block in R7
				* 27  is x cos position of red block in R7
				* 28 is y sin position of red block in R7
				* 29 is their move
				* 30 is x addition on sustain
				* 31 is x position of block in R6
				* 32 is its trigger
				* 33 is y position of block 10 width in R6 on sustain
				* 34 is y position of block 25 width in R6 on sustain
				* 35 is x position of block 15 width in R6 on sustain
				* 36 is x position of first meet block in R6
				* 
				* 39 is blue button 4 in R9 (to R6)
				* 40 is blue button 1 in R3 (to R5)
				* 41 is blue button 2 in R5 (to R3)
				* 47 is trigger to teleportation
				* 48 is number of room to teleport displayed
				* 49 is light switch in each room
				*/
				Tab[48]=3;
				Tab[15]=76;
				Tab[16]=380;
				Tab[22]=620;
				Tab[31]=715;
				Tab[33]=480-1;
				Tab[34]=480;
				Tab[35]=785;
				Tab[36]=794;
				initP=1;
				}
			g.setFont(new Font("Arial", Font.BOLD, 10));
//*Start
			//Color color = new Color(238, 30, 238);
			Color color = Color.pink;
			g.setColor(color);
			for(int i=0; i<2; i++){
				g.fillRect(300+i*275, 0, 25, 800);
				ColisionAll(300+i*275, 0-100, 25, 800+100);
				for(int j=0; j<3; j++){
					g.fillRect(50+j*275, 250+i*275, 250, 25);
					ColisionALL(50+j*275, 250+i*275, 250, 25);
					}
				}
			for(int i=0; i<3; i++){
				g.setColor(color);
				g.fillRect(600, 240+i*275, 7, 5);
				ColisionALL(600, 240+i*275, 7, 5);
				g.fillRect(325, 220+i*275-16, 16, 16);
				ColisionALL(325, 220+i*275-16, 16, 16);

				g.setColor(Color.magenta);
				g.fillRect(325, 220+i*275-16, 16, 3);
				g.setColor(Color.cyan);
				g.fillRect(325, 220+i*275-16+13, 16, 3);
				g.setColor(Color.yellow);
				g.fillRect(600+5, 240+i*275, 2, 5);
				if (Colision(600+5, 240+i*275, 3, 5)){
					if (i==0) Tab[49]=3;
					else if (i==1) Tab[49]=6;
					else if (i==2) Tab[49]=9;
					}
				g.setColor(new Color(255, 100, 0));
				g.fillOval(320, 220+i*275, 5, 30);
				if (Colision(320, 220+i*275, 5+1, 30)) Tab[47]=1;
				g.fillRect(325, 220+i*275-16+3, 16, 10);

				g.setColor(Color.black);
				g.drawString(String.valueOf(Tab[48]), 331, 220+i*275-16+12);
				if (Colision(325, 220+i*275-16+15, 16, 3)) Tab[48]+=1;
				for(int j=0; j<2; j++){
					g.setColor(color);
					g.fillRect(50+j*790, 220+i*275, 10, 30);
					ColisionALL(50+j*790, 220+i*275, 10, 30);
					g.fillRect(60+j*764, 210+i*275-6, 16, 16);
					ColisionALL(60+j*764, 210+i*275-6, 16, 16);

					g.fillRect(300-7+j*275, 250-10+i*275, 7, 5);
					ColisionALL(300-7+j*275, 250-10+i*275, 7, 5);
					g.setColor(Color.yellow);
					g.fillRect(300-7+j*275, 250-10+i*275, 2, 5);
					if (Colision(300-7+j*275-1, 250-10+i*275, 2, 5)){
						if (j==0){
							if (i==0) Tab[49]=1;
							else if (i==1) Tab[49]=4;
							else if (i==2) Tab[49]=7;
							}
						else if (j==1){
							if (i==0) Tab[49]=2;
							else if (i==1) Tab[49]=5;
							else if (i==2) Tab[49]=8;
							}
						}
					g.setColor(new Color(255, 100, 0));
					g.fillOval(55+j*785, 220+i*275, 5, 30);
					if (j==0) if (Colision(55+j*785, 220+i*275, 5+1, 30)) Tab[47]=1;
					if (j==1) if (Colision(55+j*785-1, 220+i*275, 5, 30)) Tab[47]=1;

					g.fillRect(60+j*764, 210+i*275-6+3, 16, 10);

					g.setColor(Color.black);
					g.drawString(String.valueOf(Tab[48]), 60+j*764+6, 210+i*275-6+12);
					if (Colision(60+j*764, 210+i*275-6+15, 16, 3)) Tab[48]+=1;

					g.setColor(Color.magenta);
					g.fillRect(60+j*764, 210+i*275-6, 16, 3);
					g.setColor(Color.cyan);
					g.fillRect(60+j*764, 210+i*275-6+13, 16, 3);
					}
				}
			if (Tab[48]==10) Tab[48]=1;
//***Orange Portal
			if (Tab[47]==1){
				switch(Tab[48]){
					case 1:x=60;y=250;break;
					case 2:x=325;y=250;break;
					case 3:x=840-Size;y=250;break;
					case 4:x=60;y=250+275;break;
					case 5:x=325;y=250+275;break;
					case 6:x=840-Size;y=250+275;break;
					case 7:x=60;y=250+275*2;break;
					case 8:x=325;y=250+275*2;break;
					case 9:x=840-Size;y=250+275*2;break;
					}
				Tab[47]=Tab[49]=0;
				}
//*Room 1
			g.setColor(color);
			g.fillRect(170, 100, 50, 10);
			ColisionALL(170, 100, 50, 10);

			g.fillRect(160, 70, 10, 40);
			ColisionAll(160, 70, 10, 40);

			g.fillRect(160, 60, 100, 10);
			ColisionALL(160, 60, 100, 10);

			g.fillRect(250, 70, 10, 160);
			ColisionAll(250, 70, 10, 160);

			g.fillRect(260, 220, 40, 10);
			ColisionALL(260, 220, 40, 10);

			g.fillRect(50, 20, 30, 10);
			ColisionALL(50, 20, 30, 10);

			g.fillRect(80, 50, 20, 10);
			ColisionALL(80, 50, 20, 10);

			g.fillRect(120, 0, 10, 100);
			ColisionAll(120, 0-100, 10, 100+100);

			g.fillRect(100, 90, 20, 10);
			ColisionALL(100, 90, 20, 10);

			g.fillRect(50+Tab[10], 194-Tab[10], 15, 10);
			ColisionALL(50+Tab[10], 194-Tab[10], 15, 10);
			if (Sustain(50+Tab[10], 194-Tab[10], 15, 10)){
				if (Tab[10]<95){
					Tab[10]+=1;
					x+=1;
					y-=1;
					}
				}
			else if (Tab[10]>0) Tab[10]-=1;
			g.fillRect(50, 150-Tab[11], 15, 10);
			ColisionALL(50, 150-Tab[11], 15, 10);
			if (Sustain(50, 150-Tab[11], 15, 10) && Tab[11]<100){
				Tab[11]+=1;
				y-=1;
				}
			else if (Tab[11]>0) Tab[11]-=1;

			g.setColor(Color.green);
			g.fillRect(260-3, 150, 3, 10);
			//WallStickAll(260-3, 150, 3, 10);
			g.setColor(Color.red);
			if (Tab[1]==0){
				g.fillRect(276, 207, 8, 8);
				if (Colision(276, 207, 8, 8)) Tab[1]=1;
				}
			if (Tab[12]==0){
				g.setColor(Color.blue);
				g.fillRect(56, 6, 8, 8);
				if (Colision(56, 6, 8, 8)) Tab[12]=1;
				}
			else if (Tab[12]==1){
				g.setColor(Color.cyan);
				g.fillRect(56, 6, 8, 8);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==10) Time=Tab[12]=0;
				}
//**Room 2
			g.setColor(color);
			g.fillRect(340, 225, 60, 10);
			ColisionALL(340, 225, 60, 10);

			g.fillRect(415, 225, 85, 10);
			ColisionALL(415, 225, 85, 10);

			g.fillRect(515, 225, 60, 10);
			ColisionALL(515, 225, 60, 10);

			g.fillRect(400, 200, 40, 10);
			ColisionALL(400, 200, 40, 10);

			for(int i=0; i<2; i++){
				g.fillRect(455+i*60, 200, 45, 10);
				ColisionALL(455+i*60, 200, 45, 10);
				}
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					if (i%2==0){
						g.fillRect(325+j*50, 90+i*22, 35, 10);
						ColisionALL(325+j*50, 90+i*22, 35, 10);
						}
					else if (j<4){
						g.fillRect(350+j*50, 90+i*22, 35, 10);
						ColisionALL(350+j*50, 90+i*22, 35, 10);

						g.fillRect(325, 90+i*22, 10, 10);
						g.fillRect(550, 90+i*22, 10, 10);
						}
					}
				}
			g.fillRect(550, 100, 10, 100);
			ColisionAll(550, 100, 10, 100);

			g.fillRect(560, 65, 15, 10);
			ColisionALL(560, 65, 15, 10);

			for(int i=0; i<2; i++){
				g.fillRect(550-i*25, 20-i*20, 10, 55+i*20);
				ColisionALL(550-i*25, 20-i*120, 10, 55+i*120);

				//g.fillRect(325, 30+i*35, 35, 10);
				//ColisionALL(325, 30+i*35, 35, 10);

				g.fillRect(375+i*75, 65, 60+i*25, 10);
				ColisionALL(375+i*75, 65, 60+i*25, 10);

				g.fillRect(450+i*35, 50-i*10, 10+i*5, 15-i*5);
				ColisionALL(450+i*35, 50-i*10, 10+i*5, 15-i*5);

				g.fillRect(475+i*25, 25-i*10, 10, 25+i*10);
				ColisionALL(475+i*25, 25-i*10, 10, 25+i*10);


				g.fillRect(375+i*25, 120, 10, 15);
				ColisionAll(375+i*25, 120, 10, 15);

				g.fillRect(375+i*50, 75, 10, 15);
				ColisionAll(375+i*50, 75, 10, 15);

				g.fillRect(425+i*25, 165, 10, 15);
				ColisionAll(425+i*25, 165, 10, 15);

				g.fillRect(475+i*25, 144, 10, 15);
				ColisionAll(475+i*25, 144, 10, 15);

				g.fillRect(425+i*25, 100, 10, 15);
				ColisionAll(425+i*25, 100, 10, 15);

				g.fillRect(500, 100+i*21, 10, 15);
				ColisionAll(500, 100+i*21, 10, 15);
				}
			g.fillRect(475, 75, 10, 15);
			ColisionAll(475, 75, 10, 15);

			g.fillRect(400, 40, 60, 10);
			ColisionALL(400, 40, 60, 10);

			g.fillRect(385, 15, 100, 10);
			ColisionALL(385, 15, 100, 10);

			g.fillRect(325, 40, 35, 10);
			ColisionALL(325, 40, 35, 10);


			if (Tab[0]<7){
				g.fillRect(450, 0, 10, 15);
				ColisionAll(450, 0-100, 10, 15+100);
				}
			g.setColor(Color.red);
			g.fillRect(340, 200, 60, 10);
			if (Colision(340, 200, 60, 10)) initP=0;

			g.fillRect(500, 200, 15, 10);
			if (Colision(500, 200, 15, 10)) initP=0;

			g.fillRect(375, 0, 10, 65);
			if (Colision(375, 0-100, 10, 65+100)) initP=0;
			
			g.fillRect(485, 25, 15, 15);
			if (Colision(485, 25, 15, 15)) initP=0;

			//int x1 [] = {445, 418, 418, 392, 392, 417, 417, 492, 492, 468, 468};
			//int y1 [] = {195, 195, 170, 170, 150, 150, 125, 125, 105, 105, 90};
			//g.drawPolyline(x1,y1,11);
			//int x2 [] = {445, 468, 468, 540, 540, 517, 517, 540, 540, 517, 517};
			//int y2 [] = {195, 195, 170, 170, 150, 150, 125, 125, 105, 105, 90};
			//g.drawPolyline(x2,y2,11);
			//int x3 [] = {445, 418, 418, 392, 392, 366, 366, 342, 342, 366, 366};
			//int y3 [] = {195, 195, 170, 170, 150, 150, 125, 125, 105, 105, 90};
			//g.drawPolyline(x3,y3,11);

			if (Tab[8]==0){
				g.fillRect(390, 3, 8, 8);
				if (Colision(390, 3, 8, 8)) Tab[8]=1;
				}
			if (Tab[13]==0){
				g.setColor(Color.blue);
				g.fillRect(563, 52, 8, 8);
				if (Colision(563, 52, 8, 8)) Tab[13]=1;
				}
			else if (Tab[13]==1){
				g.setColor(Color.cyan);
				g.fillRect(563, 52, 8, 8);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==10) Time=Tab[13]=0;				
				}
			g.setColor(Color.cyan);
			g.fillOval(325-5, 60, 5, 30);//#3
			if (Colision(325-5, 60, 5+1, 30)){
				x=160;
				y=765+Size;
				}
			g.fillOval(325-5, 10, 5, 30);//#5
			if (Colision(325-5, 10, 5+1, 30)){
				x=170;
				y=550+Size;
				}

//*Room 3
			g.setColor(color);
			for(int i=0; i<2; i++){
				g.fillRect(620, 60+i*145, 230-i*26, 10);
				ColisionALL(620, 60+i*145, 230-i*26, 10);

				g.fillRect(620, 70+i*85, 10, 50);
				ColisionALL(620, 70+i*85, 10, 50);


				g.fillRect(832, 123+i*23, 18, 5);
				ColisionALL(832, 123+i*23, 18, 5);
				}
			for(int i=0; i<5; i++){
				if (195-Tab[14]-i*20>75){
					g.fillRect(630+i*38+19, 195-Tab[14]-i*20, 15, 5);
					ColisionALL(630+i*38+19, 195-Tab[14]-i*20, 15, 5);
					if (Sustain(630+i*38+19, 195-Tab[14]-i*20, 15, 5)) y-=2;
					}
				else{
					g.fillRect(630+i*38+19, 195-Tab[14]-i*20+125, 15, 5);
					ColisionALL(630+i*38+19, 195-Tab[14]-i*20+125, 15, 5);
					if (Sustain(630+i*38+19, 195-Tab[14]-i*20+125, 15, 5)) y-=2;
					}
				}
			if (TimeOn2==0 && Tab[49]==3){
				new Time2().start();
				TimeOn2=1;
				Tab[14]+=1;
				}
			if (Tab[14]==125) Tab[14]=0;


			if (Tab[0]<1){
				g.fillRect(827, 128, 5, 18);
				ColisionALL(827, 128, 5, 18);
				}
			g.setColor(Color.red);
			if (Tab[2]==0){
				g.fillRect(837, 133, 8, 8);
				if (Colision(837, 133, 8, 8)) Tab[2]=1;
				}

			for(int i=0; i<2; i++){
				g.fillRect(630, 70+i*125, 220-i*26, 10);
				if (Colision(630, 70+i*125, 220-i*26, 10)) initP=0;
				}
			g.setColor(Color.orange);
			g.drawLine(630, 85, 850, 85);
			if (LineOn(630, 85, 850, 85)){
				y=85+Size;
				x+=2;
				}
			if (Tab[40]==0 && Tab[0]==9){
				g.setColor(Color.blue);
				g.fillRect(792, 47, 8, 8);
				if (Colision(792, 47, 8, 8)) Tab[40]=1;
				}
			else if (Tab[40]==1 && Tab[0]==9){
				g.setColor(Color.cyan);
				g.fillRect(792, 47, 8, 8);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==7) Time=Tab[40]=0;					
				}
			if (Tab[21]==0){
				g.setColor(Color.blue);
				g.fillRect(805, 0, 15, 60);
				ColisionAll(805, 0-100, 15, 60+100);
				}
			else if (Tab[21]==1){
				g.setColor(Color.cyan);
				g.fillRect(805, 0, 15, 60);
				}
//***Goal Level 46
			Tab[0]=Tab[1]+Tab[2]+Tab[3]+Tab[4]+Tab[5]+Tab[6]+Tab[7]+Tab[8]+Tab[9];
			g.setColor(Color.black);
			g.fillRect(825, 10, 20, 50);
			if (x==825 && y==60){
				Level=Pan.Level=47;
				initP=0;
				}
			g.setColor(color);
			for(int i=0; i<9-Tab[0]; i++){
				//g.fillRect(620+i*20+5, 0, 10, 60);
				//ColisionAll(620+i*20+5, 0-100, 10, 60+100);
				g.fillRect(785-i*20, 0, 10, 60);
				ColisionAll(785-i*20, 0-100, 10, 60+100);
				}
//**Room 4
			g.fillRect(66, 295, 10, 183-15);
			ColisionAll(66, 295, 10, 183-15);

			g.fillRect(66, 285, 214, 10);
			ColisionALL(66, 285, 214, 10);
			for(int i=0; i<2; i++){
				g.fillRect(96+i*84, 315, 70, 10);
				ColisionALL(96+i*84, 315, 70, 10);

				g.fillRect(96+i*144, 325, 10, 170);
				ColisionAll(96+i*144, 325, 10, 170);

				g.fillRect(106+i*74, 485, 60, 10);
				ColisionALL(106+i*74, 485, 60, 10);

				g.fillRect(270, 295+i*140, 10, 100-i*10);
				ColisionALL(270, 295+i*140, 10, 100-i*10);
				}
			g.fillRect(106, 400, 10, 10);
			ColisionALL(106, 400, 10, 10);

			g.fillRect(180, 445, 10, 40);
			ColisionALL(180, 445, 10, 40);
			g.fillRect(170, 424, 10, 21);
			ColisionALL(170, 424, 10, 21);
			g.fillRect(150, 385, 45, 10);
			ColisionALL(150, 385, 45, 10);
			g.fillRect(150, 395, 10, 29);
			ColisionAll(150, 395, 10, 29);
			g.fillRect(160, 414, 35, 10);
			ColisionALL(160, 414, 35, 10);

			if (Tab[0]<6){
				g.fillRect(195, 395, 10, 18);
				ColisionALL(195, 395, 10, 18);
				}

			g.setColor(Color.red);
			if (Tab[7]==0){
				g.fillRect(171, 396+5, 8, 8);
				if (Colision(171, 396+5, 8, 8)) Tab[7]=1;
				}

			g.fillRect(Tab[15], Tab[16], 20, 20);
			if (Colision(Tab[15], Tab[16], 20, 20)) initP=0;
			if (Tab[17]==0) Tab[16]+=1;
			if (Tab[16]==495) Tab[17]=1;
			if (Tab[17]==1) Tab[15]+=1;
			if (Tab[15]==250) Tab[17]=2;
			if (Tab[17]==2) Tab[16]-=1;
			if (Tab[16]==295) Tab[17]=3;
			if (Tab[17]==3) Tab[15]-=1;
			if (Tab[15]==76) Tab[17]=0;
			for(int i=0; i<2; i++){
				int I = (int)(Math.pow(-1,i));
				g.fillRect(106+i*124+Tab[18]*I, 325+i*150+Tab[18]*I, 10, 10);
				g.fillRect(106+i*124+Tab[18]*I, 475-i*150-Tab[18]*I, 10, 10);
				if (Colision(106+i*124+Tab[18]*I, 325+i*150+Tab[18]*I, 10, 10)) initP=0;
				if (Colision(106+i*124+Tab[18]*I, 475+i*150-Tab[18]*I, 10, 10)) initP=0;
				}
			//if (Tab[19]==0) Tab[18]+=1;
			//if (Tab[19]==1) Tab[18]-=1;
			if (Tab[18]==0) Tab[19]=0;
			if (Tab[18]==50) Tab[19]=1;
			//Tab[18]=50;
			if (TimeOn2==0 && 50<=x && x+Size<=300 && 525>=y && y-Size>=275){
				new Time2().start();
				TimeOn2=1;
				if (Tab[19]==0) Tab[18]+=1;
				if (Tab[19]==1) Tab[18]-=1;
				}

			g.setColor(Color.green);
			g.fillRect(280-3, 360, 3, 10);
			//WallStickAll(280-3, 360, 3, 10);

			if (Tab[20]==0){
				g.setColor(Color.blue);
				g.fillRect(55, 466, 8, 8);
				if (Colision(55, 466, 8, 8)) Tab[20]=1;
				g.fillRect(166, 485, 14, 10);
				ColisionAll(166, 485, 14, 10);
				g.fillRect(66, 463, 10, 15);
				ColisionAll(66, 463, 10, 15);
				}
			else if (Tab[20]==1){
				g.setColor(Color.cyan);
				g.fillRect(55, 466, 8, 8);
				g.fillRect(166, 485, 14, 10);
				g.fillRect(66, 463, 10, 15);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==5) Time=Tab[20]=0;
				}
//**Room 5
			g.setColor(color);
			g.fillRect(341, 485, 14, 10);
			ColisionALL(341, 485, 14, 10);

			g.fillRect(431, 402, 10, 93);
			ColisionAll(431, 402, 10, 93);

			g.fillRect(390, 485, 41, 10);
			ColisionALL(390, 485, 41, 10);

			g.fillRect(325, 425+15, 75, 10);
			ColisionALL(325, 425+15, 75, 10);
			g.fillRect(390, 390+15, 10, 35);
			ColisionALL(390, 390+15, 10, 35);
			g.fillRect(390, 380+15, 20, 10);
			ColisionALL(390, 380+15, 20, 10);

			g.fillRect(340, 345+1, 90+1, 10);
			ColisionALL(340, 345+1, 90+1, 10);

			g.fillRect(459, 295, 10, 50+1);
			ColisionAll(459, 295, 10, 50+1);

			g.fillRect(459, 285, 91, 10);
			ColisionALL(459, 285, 91, 10);

			g.fillRect(540, 295, 10, 200);
			ColisionAll(540, 295, 10, 200);

			g.fillRect(515, 305, 10, 190);
			ColisionALL(515, 305, 10, 190);

			g.fillRect(469, 485, 46, 10);
			ColisionALL(469, 485, 46, 10);

			g.fillRect(459, 425, 10, 70);
			ColisionALL(459, 425, 10, 70);

			if (Tab[0]<8){
				g.fillRect(441, 371-35, 18, 10);
				ColisionALL(441, 371-35, 18, 10);
				}
			for(int i=0; i<2; i++){
				g.fillRect(431+i*28, 381-35, 10, 56);
				ColisionALL(431+i*28, 381-35, 10, 56);
				}
			g.fillRect(441, 409-35, 18, 10);
			ColisionALL(441, 409-35, 18, 10);

			g.setColor(Color.orange);
			g.drawLine(532, 295, 532, 525);
			if (LineColision(532, 295, 532, 525)) y-=2;
			for(int i=0; i<4; i++){
				if (i%2==0){
					g.drawLine(469, 305+i*24, 515, 305+(i+1)*24);
					if (LineOn(469, 305+i*24, 515, 305+(i+1)*24) && x+Size<515){
						Slide(469, 305+i*24, 515, 305+(i+1)*24);
						x+=1;
						}
					}
				else{
					g.drawLine(515, 305+i*24, 469, 305+(i+1)*24);
					if (LineOn(515, 305+i*24, 469, 305+(i+1)*24) && x>469){
						Slide(515, 305+i*24, 469, 305+(i+1)*24);
						x-=1;
						}
					}
				}
			g.setColor(Color.red);
			if (Tab[9]==0){
				g.fillRect(450-4, 400-4-35, 8, 8);
				if (Colision(450-4, 400-4-35, 8, 8)) Tab[9]=1;
				}
			if (Tab[13]==0){
				g.setColor(Color.blue);
				g.fillRect(355, 485, 35, 10);
				ColisionAll(355, 485, 35, 10);
				}
			else if (Tab[13]==1){
				g.setColor(Color.cyan);
				g.fillRect(355, 485, 35, 10);
				}
			if (Tab[21]==0){
				g.setColor(Color.blue);
				g.fillRect(450-4, 400-4+28-35, 8, 8);
				if (Colision(450-4, 400-4+28-35, 8, 8)) Tab[21]=1;
				}
			else if (Tab[21]==1){
				g.setColor(Color.cyan);
				g.fillRect(450-4, 400-4+28-35, 8, 8);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==10) Time=Tab[21]=0;
				}
			if (Tab[40]==0){
				g.setColor(Color.blue);
				g.fillRect(441, 400-4+28+8+5-35, 18, 10);
				ColisionAll(441, 400-4+28+8+5-35, 18, 10);
				}
			else if (Tab[40]==1){
				g.setColor(Color.cyan);
				g.fillRect(441, 400-4+28+8+5-35, 18, 10);
				}
//**Room 6

			g.setColor(color);
			g.fillRect(Tab[36], 510, 16, 5);
			ColisionALL(Tab[36], 510, 16, 5);
			g.fillRect(805, 495, 10, 2);
			ColisionAll(805, 495, 10, 2);
			
			g.fillRect(626, 300, 10, 5);
			ColisionALL(626, 300, 10, 5);

			g.fillRect(640, 310, 15, 10);
			ColisionALL(640, 310, 15, 10);

			g.fillRect(665, 340, 15, 10);
			ColisionALL(665, 340, 15, 10);

			g.fillRect(685, 310, 15, 5);
			ColisionALL(685, 310, 15, 5);

			g.fillRect(750, 310, 15, 10);
			ColisionALL(750, 310, 15, 10);

			g.fillRect(775-1, 350, 15+1, 10);
			ColisionALL(775-1, 350, 15+1, 10);
			for(int i=0; i<2; i++){
				g.fillRect(790+i*30, 300, 10, 5);
				ColisionALL(790+i*30, 300, 10, 5);
				}
			g.fillRect(805, 285, 10, 5);
			ColisionAll(805, 285, 10, 5);
			g.fillRect(815, 320, 5, 5);
			ColisionALL(815, 320, 5, 5);
			g.fillRect(800, 350, 5, 5);
			ColisionALL(800, 350, 5, 5);
			
			g.fillRect(640, 470, 165, 10);
			ColisionALL(640, 470, 165, 10);

			g.fillRect(660, 430, 16, 10);
			ColisionALL(660, 430, 16, 10);

			g.fillRect(700, 360, 15, 5);
			ColisionAll(700, 360, 15, 5);

			g.fillRect(660, 360, 10, 2);
			ColisionAll(660, 360, 10, 2);
			g.fillRect(650, 370+2, 10, 3);
			ColisionALL(650, 370+2, 10, 3);


			g.fillRect(Tab[31], 340, 15, 10);
			ColisionALL(Tab[31], 340, 15, 10);
			//if (Tab[32]==0) Tab[31]+=1;
			//if (Tab[32]==1) Tab[31]-=1;
			if (Tab[31]==685) Tab[32]=0;
			if (Tab[31]+15==774) Tab[32]=1;
			if (TimeOn2==0){
				new Time2().start();
				TimeOn2=1;
				if (Tab[32]==0) Tab[31]+=1;
				if (Tab[32]==1) Tab[31]-=1;
				if (Sustain(Tab[31], 340, 15, 10)){
					if (Tab[32]==0) x+=1;
					if (Tab[32]==1) x-=1;
					}
				}

			//g.fillRect(840, Tab[33], 10, 5);
			//ColisionALL(840, Tab[33], 10, 5);
			//if (Sustain(840, Tab[33], 10, 5)){ if (Tab[33]>395){
				//Tab[33]-=1;
				//y-=1;
				//}}
			//else if (Tab[33]<479) Tab[33]+=1;

			g.fillRect(Tab[35], 430, 15, 10);
			ColisionALL(Tab[35], 430, 15, 10);
			if (Sustain(Tab[35], 430, 15, 10)){ if (Tab[35]>715){
				Tab[35]-=1;
				x-=1;
				}}
			else if (Tab[35]<785) Tab[35]+=1;

			for(int i=0; i<2; i++){
				g.fillRect(614+i*166, 285, 5, 3);
				ColisionAll(614+i*166, 285, 5, 3);
				}
//*Red
			g.setColor(Color.red);
			g.fillRect(636, 485, 188, 10);
			if (Colision(636, 485, 188, 10)) initP=0;
			g.fillRect(626, 305, 10, 145);
			ColisionRed(626, 305, 10, 145);	
			if (Tab[5]==0){
				g.fillRect(626, 305+145, 10, 45);
				ColisionRed(626, 305+145, 10, 45);	
				}

			g.fillRect(600, 275, 10, 250);
			ColisionRed(600, 275, 10, 250);
			g.fillRect(610, 515, 200, 10);
			ColisionRed(610, 515, 200, 10);
			g.fillRect(610, 275, 240, 10);
			ColisionRed(610, 275, 240, 10);

			g.fillRect(636, 310, 4, 10);
			ColisionRed(636, 310, 4, 10);

			g.fillRect(645, 320, 10, 40);
			ColisionRed(645, 320, 10, 40);
			g.fillRect(655, 350, 120-1, 10);
			ColisionRed(655, 350, 120-1, 10);

			g.fillRect(700, 285, 10, 35);
			ColisionRed(700, 285, 10, 35);
			g.fillRect(710, 310, 40, 10);
			ColisionRed(710, 310, 40, 10);
			g.fillRect(765, 300, 10-1, 20);
			ColisionRed(765, 300, 10-1, 20);

			for(int i=0; i<2; i++){
				g.fillRect(790+i*30, 305, 10, 55);
				ColisionRed(790+i*30, 305, 10, 55);
				}


			g.fillRect(650, 375, 10, 65);
			ColisionRed(650, 375, 10, 65);
			g.fillRect(650, 440, 150, 10);
			ColisionRed(650, 440, 150, 10);

			if (Tab[5]==0){
				g.fillRect(664, 417, 8, 8);
				if (Colision(664, 417, 8, 8)) Tab[5]=1;
				}
			g.setColor(Color.orange);
			if (Tab[5]==0){
				g.drawLine(616, 285+3, 616, 510-1);
				if (LineColision(616, 285+3, 616, 510-1)) y-=2;
				}
			g.drawLine(782, 285+3, 782, 350-1);
			if (LineColision(782, 285+3, 782, 350-1)) y-=2;

			g.setColor(Color.blue);
			g.fillRect(Tab[36]+6, 510, 4, 2);
			if (Sustain(Tab[36]+6, 510, 4, 2)){
				if (Tab[5]==0 && Tab[36]>610){
					Tab[36]-=1;
					x-=1;
					}
				if (Tab[5]==1 && Tab[36]+16<810){
					Tab[36]+=1;
					x+=1;
					}
				}
			if (Tab[39]==0 && Tab[5]==0){
				g.fillRect(820, 495, 7, 30);
				ColisionAll(820, 495, 7, 30);
				}
			if (Tab[39]==1){
				g.setColor(Color.cyan);
				g.fillRect(820, 495, 7, 30);
				}

//**Room 7
			g.setColor(color);
			g.fillRect(76, 754, 224, 10);
			ColisionALL(76, 754, 224, 10);
			for(int i=0; i<2; i++){
				g.fillRect(150+i*40, 550, 10, 60);
				ColisionAll(150+i*40, 550, 10, 60);

				g.fillRect(76+i*184, 550, 10, 204);
				ColisionAll(76+i*184, 550, 10, 204);
				}
			g.fillRect(160, 600, 30, 10);
			ColisionALL(160, 600, 30, 10);

			g.fillRect(86, 570, 24, 10);
			ColisionALL(86, 570, 24, 10);

			g.fillRect(200, 580, 60, 10);
			ColisionALL(200, 580, 60, 10);

			g.fillRect(255, 764, 15, 36);
			ColisionAll(255, 764, 15, 36);

			g.fillRect(220, 610, 20, 10);
			ColisionALL(220, 610, 20, 10);  //***********************


			g.fillRect(86, Tab[22], 14, 10);//[22]=620 default
			ColisionALL(86, Tab[22], 14, 10);
			if (Sustain(86, Tab[22], 14, 10)){ if (Tab[22]<734){
				Tab[22]+=1;
				//y+=1;
				}}
			else if (Tab[22]>620) Tab[22]-=1;

			int Speed=350;
			Tab[23]=120+(int)(20*Math.cos((Tab[29]*3.14)/Speed));
			Tab[24]=704+(int)(40*Math.sin((Tab[29]*3.14)/Speed));
			g.fillRect(Tab[23], Tab[24], 15, 10);
			ColisionALL(Tab[23], Tab[24], 15, 10);

			Tab[27]=195+(int)(-50*Math.cos((Tab[29]*3.14)/Speed));
			Tab[28]=670+(int)(20*Math.sin((Tab[29]*3.14)/Speed));
			g.fillRect(Tab[27], Tab[28], 15, 10);
			ColisionALL(Tab[27], Tab[28], 15, 10);

			if (Sustain(Tab[23], Tab[24], 15, 10)){
				if (Tab[30]==0) Tab[30]=x-Tab[23];
				x=120+(int)(20*Math.cos((Tab[29]*3.14)/Speed))+Tab[30];
				y=704+(int)(40*Math.sin((Tab[29]*3.14)/Speed));
				if (MotionL==1) Tab[30]-=1;
				if (MotionR==1) Tab[30]+=1;
				}
			else if (Sustain(Tab[27], Tab[28], 15, 10)){
				if (Tab[30]==0) Tab[30]=x-Tab[27];
				x=195+(int)(-50*Math.cos((Tab[29]*3.14)/Speed))+Tab[30];
				y=670+(int)(20*Math.sin((Tab[29]*3.14)/Speed));
				if (MotionL==1) Tab[30]-=1;
				if (MotionR==1) Tab[30]+=1;
				}
			else Tab[30]=0;
			Tab[29]+=1;

			if (Tab[0]<2){
				g.fillRect(220, 550, 6, 30);
				ColisionAll(220, 550, 6, 30);
				}
			g.setColor(Color.orange);
			g.drawLine(150, 565, 86, 605);
			if (LineOn(150, 565, 86, 605) && x>86){
				Slide(150, 565, 86, 605);
				x-=1;
				}
			g.setColor(Color.red);
			if (Tab[3]==0){
				g.fillRect(206, 561, 8, 8);
				if (Colision(206, 561, 8, 8)) Tab[3]=1;
				}
			g.fillRect(86, 744, 174, 10);
			if (Colision(86, 744, 174, 10)) initP=0;
			g.fillRect(120, 600, 30, 10);
			if (Colision(120, 600, 30, 10)) initP=0;
			g.fillRect(270, 600, 20, 10);
			if (Colision(270, 600, 20, 10)) initP=0;

			Tab[25]=155+(int)(10*Math.cos((Tab[29]*3.14)/Speed));
			Tab[26]=675+(int)(-60*Math.sin((Tab[29]*3.14)/Speed));
			g.fillRect(Tab[25], Tab[26], 15, 10);
			if (Colision(Tab[25], Tab[26], 15, 10)) initP=0;

			g.fillRect(270, 745, 30, 10);
			ColisionRed(270, 745, 30, 10);
//*Portal
			g.setColor(Color.cyan);
			g.fillOval(255, 770, 5, 30);//#1
			if (Colision(255-1, 770, 5, 30)){
				x=60;
				y=550+Size;
				}
			g.fillOval(76, 565, 5, 30);//#2
			if (Colision(76-1, 565, 5, 30)){
				x=270;
				y=800;
				}
			g.fillOval(76, 724, 5, 30);//#4
			if (Colision(76-1, 724, 5, 30)){
				x=280;
				y=550+Size;
				}
			g.fillOval(270-5, 620, 5, 30);//#5
			if (Colision(270-5, 620, 5+1, 30)){
				x=170;
				y=550+Size;
				}
			g.fillOval(190, 550, 5, 30);//#3
			if (Colision(190-1, 550, 5, 30)){
				x=160;
				y=765+Size;
				}
			g.fillOval(270-5, 715, 5, 30);//#3
			if (Colision(270-5, 715, 5+1, 30)){
				x=160;
				y=765+Size;
				}
			g.fillOval(160, 600, 30, 5);//#6
			if (Colision(160, 600-1, 30, 5)){
				x=86;
				y=550+Size;
				}
			g.fillOval(215, 590-5, 30, 5);//#7
			if (Colision(215, 590-5, 30, 5+2)){
				x=237;
				y=550+Size;
				}
			g.fillOval(260, 550, 5, 30);//#3
			if (Colision(260, 550-1, 5, 30)){
				x=160;
				y=765+Size;
				}
			g.fillOval(270, 765-5, 30, 5);//#3
			if (Colision(270, 765-5, 30, 5+1)){
				x=160;
				y=765+Size;
				}
			g.setColor(Color.magenta);
			g.fillOval(50, 550-5, 30, 5);//#1
			g.fillOval(270, 550-5, 30, 5);//#4
			g.fillOval(270-5, 770, 5, 30);//#2
			g.fillOval(160, 550-5, 30, 5);//#5
			g.fillOval(150, 765-5, 30, 5);//#3
			g.fillOval(86, 550-5, 30, 5);//#6
			g.fillOval(227, 550-5, 30, 5);//#7

			if (Tab[12]==0){
				g.setColor(Color.blue);
				g.fillRect(160, 580, 30, 10);
				ColisionALL(160, 580, 30, 10);
				}
			else if (Tab[12]==1){
				g.setColor(Color.cyan);
				g.fillRect(160, 580, 30, 10);
				}
			

			g.setColor(Color.magenta);
			g.fillRect(230, 625, 10, 9);
			
//**Room 8
			g.setColor(color);
			g.fillRect(360, 570, 10, 200);
			ColisionALL(360, 570, 10, 200);

			g.fillRect(540, 570, 10, 50);
			ColisionALL(540, 570, 10, 50);

			g.fillRect(540, 640, 10, 160);
			ColisionALL(540, 640, 10, 160);

			if (Tab[0]<5){
				g.fillRect(439, 600, 36, 10);
				ColisionALL(439, 600, 36, 10);
				}
			g.setColor(Color.green);
			g.fillRect(575, 680, 3, 10);

			g.setColor(Color.orange);
			g.drawLine(350, 550, 350, 800-1);
			if (LineColision(350, 550, 350, 800-1)) y-=2;
			g.drawLine(370, 575, 540, 575);
			if (LineOn(370, 575, 540, 575)) y=575+Size;
				

			g.setColor(Color.red);
			for(int i=0; i<2; i++){
				g.fillRect(385+i*40-1, 600, 15, 100);
				if (Colision(385+i*40-1, 600, 15, 100)) initP=0;
				g.fillRect(475+i*40, 600, 15, 100);
				if (Colision(475+i*40, 600, 15, 100)) initP=0;	
				g.fillRect(400+i*91-1, 680-i*80, 25, 20);			
				if (Colision(400+i*91-1, 680-i*80, 25, 20)) initP=0;
				}
			if (Tab[6]==0){
				g.fillRect(448+5, 646, 8, 8);
				if (Colision(448+5, 646, 8, 8)) Tab[6]=1;
				}
//**Room 9
			g.setColor(color);
			for(int i=0; i<5; i++){
				g.fillRect(650+i*20, 750-i*40, 10, 10);
				ColisionALL(650+i*20, 750-i*40, 10, 10);

				if (i<3){
					g.fillRect(740+i*20, 720-i*40, 10, 10);
					ColisionALL(740+i*20, 720-i*40, 10, 10);

					g.fillRect(620-i*5, 695-i*45, 10, 10);
					ColisionALL(620-i*5, 695-i*45, 10, 10);
					}
				}

			g.fillRect(670, 580, 10, 10);
			ColisionALL(670, 580, 10, 10);


			for(int i=0; i<2; i++){
				g.fillRect(775+i*23, 550, 5, 18);
				ColisionAll(775+i*23, 550, 5, 18);
				}
			if (Tab[0]<3){
				g.fillRect(780, 568, 18, 5);
				ColisionAll(780, 568, 18, 5);
				}
			g.setColor(Color.red);
			if (Tab[4]==0){
				g.fillRect(785, 555, 8, 8);
				if (Colision(785, 555, 8, 8)) Tab[4]=1;
				}

			for(int i=0; i<5; i++){
				g.fillRect(750+i*20, 750-i*40, 10, 10);
				if (Colision(750+i*20, 750-i*40, 10, 10)) initP=0;

				if (i<3){
					g.fillRect(720+i*40, 630-i*20, 10, 10);
					if (Colision(720+i*40, 630-i*20, 10, 10)) initP=0;

					g.fillRect(615+i*15, 750-i*45, 10, 10);
					if (Colision(615+i*15, 750-i*45, 10, 10)) initP=0;
					}
				}
			g.fillRect(710, 550, 10, 10);
			if (Colision(710, 550, 10, 10)) initP=0;

			if (Tab[39]==0){
				g.setColor(Color.blue);
				g.fillRect(671, 555, 8, 8);
				if (Colision(671, 555, 8, 8)) Tab[39]=1;
				}
			else if (Tab[39]==1){
				g.setColor(Color.cyan);
				g.fillRect(671, 555, 8, 8);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==10) Time=Tab[39]=0;
				}
//**ending

			g.setColor(Color.black);
			//g.fillRect(100, 0, 700, 800);
			//SLight=1000;
			if (Tab[49]==0){
				int LeftSide=0;
				int RightSide=900;
				int UpSide=0;
				int DownSide=800;
				g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
				if (y+SLight<=DownSide){
					g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
					g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
					g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
					}
				else if (y+SLight>DownSide){
					g.fillRect(LeftSide, UpSide, x-SLight, y);
					g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
					g.fillRect(LeftSide, y, x-SLight, DownSide-y);
					g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
					}
				}
			else{
				//g.setColor(Color.black);
				if (Tab[49]==1){
					g.fillRect(0, 275, 900, 525);
					g.fillRect(325, 0, 575, 275);
					}
				else if (Tab[49]==2){
					g.fillRect(0, 0, 300, 800);
					g.fillRect(600, 0, 300, 800);
					g.fillRect(300, 275, 300, 525);
					}
				else if (Tab[49]==3){
					g.fillRect(0, 0, 575, 800);
					g.fillRect(575, 275, 325, 525);
					}
				else if (Tab[49]==4){
					g.fillRect(0, 0, 900, 250);
					g.fillRect(0, 550, 900, 250);
					g.fillRect(325, 250, 575, 300);
					}
				else if (Tab[49]==5){
					g.fillRect(0, 0, 900, 250);
					g.fillRect(0, 550, 900, 250);
					g.fillRect(0, 250, 300, 300);
					g.fillRect(600, 250, 300, 300);
					}
				else if (Tab[49]==6){
					g.fillRect(0, 0, 900, 250);
					g.fillRect(0, 550, 900, 250);
					g.fillRect(0, 250, 575, 300);
					}
				else if (Tab[49]==7){
					g.fillRect(0, 0, 900, 525);
					g.fillRect(325, 525, 575, 275);
					}
				else if (Tab[49]==8){
					g.fillRect(0, 0, 300, 800);
					g.fillRect(600, 0, 300, 800);
					g.fillRect(300, 0, 300, 525);
					}
				else if (Tab[49]==9){
					g.fillRect(0, 0, 900, 525);
					g.fillRect(0, 525, 575, 275);
					}
				}

			WallStickAll(260-3, 150, 3, 10);//R1
			WallStickAll(280-3, 360, 3, 10);//R4	
			WallStickAll(575, 680, 3, 10);//R8		


			}
//***Level 47
		else if (Level==47){
			if (initP==0){
				x=825;
				y=60;
				SLight=50;
				Time=TimeOn=Time2=TimeOn2=0;
				ResetTab();
				/*
				* 0 is blue button
				* 1 is red button 1
				* 2 is red button 2
				* 3 is red button 3
				* 4 is red button 4
				* 5 is red button 5
				* 6 is red button 6
				* 8 is x cos of red blocks central
				* 9 is y sin of red blocks central
				* 10 is their movement
				* 11 is reversal variable of their movement half
				* 12 is x addition to thre three red blocks swift
				* 13 is their trigger
				* 14 is x position of red block pursuit in 2nd floor
				* 15 is x position of red block pursuit in 3rd floor
				* 16 is x position of red block pursuit in 4th floor
				* 
				* 
				* 30 is black shutting trigger
				* 
				* 
				* 40 is x position of last trap red block pursuit
				* 45 is y position of block with blue step2
				*/
				Tab[11]=1;
				Tab[40]=150;
				Tab[45]=655;
				Tab[15]=900;
				initP=1;
				}
			Color color = new Color(100, 0, 200);
			g.setColor(color);
//*Start
			for(int i=0; i<4; i++){
				for(int j=0; j<2; j++){
					g.fillRect(40+j*460, 100+i*160, 360, 10);
					ColisionALL(40+j*460, 100+i*160, 360, 10);
					if (i==0){
						g.fillRect(40+j*460, 690, 360, 10);
						ColisionALL(40+j*460, 690, 360, 10);
						}
					}
				}
			g.setColor(Color.orange);
			for(int i=0; i<3; i++){
				for(int j=0; j<2; j++){
					g.drawLine(400+j*100, 100+i*160, 500-j*100, 260+i*160);
					if (LineOn(400+j*100, 100+i*160, 500-j*100, 260+i*160)){
						Slide(400+j*100, 100+i*160, 500-j*100, 260+i*160);
						if (j==0 && x+Size<500) x+=1;
						else if (j==1 && x>400) x-=1;
						}
					}
				}
			g.setColor(Color.red);
			Tab[8]=400+(int)(Tab[11]*20*Math.sin((Tab[10]*3.14)/200));
			Tab[9]=105+(int)(20*Math.cos((Tab[10]*3.14)/200));
			for(int i=0; i<4; i++){
				g.fillRect(Tab[8]-10, Tab[9]-5+i*160, 20, 10);
				ColisionRed(Tab[8]-10, Tab[9]-5+i*160, 20, 10);
				}
			Tab[8]=500+(int)(-Tab[11]*20*Math.sin((Tab[10]*3.14)/200));
			Tab[9]=105+(int)(20*Math.cos((Tab[10]*3.14)/200));
			for(int i=0; i<4; i++){
				g.fillRect(Tab[8]-10, Tab[9]-5+i*160, 20, 10);
				ColisionRed(Tab[8]-10, Tab[9]-5+i*160, 20, 10);
				}
			Tab[10]+=1;
			if (Tab[10]%200==0) Tab[11]=Tab[11]*(-1);
			if (Tab[10]==10000) Tab[10]=0;
			//if (info==1) System.out.println(Tab[10]);

			for(int i=0; i<3; i++){
				if (i!=1){
					g.fillRect(40+Tab[12], 180+i*160, 30, 20);
					ColisionRed(40+Tab[12], 180+i*160, 30, 20);
					}
				else{
					g.fillRect(830-Tab[12], 180+i*160, 30, 20);
					ColisionRed(830-Tab[12], 180+i*160, 30, 20);
					}
				}
			if (Tab[13]==0) Tab[12]+=2;
			if (Tab[13]==1) Tab[12]-=2;
			if (Tab[12]==790) Tab[13]=1;
			if (Tab[12]==0) Tab[13]=0;
//*First floor
			g.setColor(color);
			for(int i=0; i<2; i++){
				g.fillRect(815-i*765, 0, 30, 10);
				ColisionAll(815-i*760, 0-100, 30, 10+100);
				g.fillRect(550-i*210, 90, 10, 10);
				ColisionALL(550-i*210, 90, 10, 10);
				for(int j=0; j<6; j++){
					if (j%2==0){
						g.fillRect(820-j*50 - i*(760-j*100), 85, 20, 10);
						ColisionALL(820-j*50 - i*(760-j*100), 85, 20, 10);
						}
					else{
						g.fillRect(820-j*50 - i*(760-j*100), 40, 20, 10);
						ColisionALL(820-j*50 - i*(760-j*100), 40, 20, 10);
						}
					}
				}
			g.setColor(Color.red);
			for(int i=0; i<2; i++){
				g.fillRect(40+i*520, 95, 300, 5);
				ColisionRed(40+i*520, 95, 300, 5);
				}
			g.setColor(Color.magenta);
			g.fillOval(815, 5, 30, 5);//#2
			g.fillOval(50, 5, 30, 5);//#1
//**Second floor
//*Left
			g.setColor(color);
			for(int i=0; i<3; i++){
				g.fillRect(350, 240-i*40, 10, 10);
				ColisionALL(350, 240-i*40, 10, 10);

				for(int j=0; j<2; j++){
					g.fillRect(310-i*40, 140+j*60, 10, 40);
					ColisionALL(310-i*40, 140+j*60, 10, 40);
					}
				g.fillRect(190-i*40, 210, 10, 40);
				ColisionALL(190-i*40, 210, 10, 40);

				g.fillRect(150-i*40, 160, 10, 20);
				ColisionALL(150-i*40, 160, 10, 20);
				}

			g.fillRect(50, 110, 10, 30);
			ColisionAll(50, 110, 10, 30);
			g.fillRect(60, 130, 30, 10);
			ColisionALL(60, 130, 30, 10);



//*Right
			for(int i=0; i<4; i++){
				g.fillRect(540+i*85, 220, 10, 40);
				ColisionALL(540+i*85, 220, 10, 40);
				
				if (i==3) break;
				if (i!=1){
					g.fillRect(555+i*70, 160, 70, 10);
					ColisionALL(555+i*70, 160, 70, 10);
					}
				else{
					g.fillRect(555+i*70, 210, 70, 10);
					ColisionALL(555+i*70, 210, 70, 10);
					}
				}

			g.fillRect(840, 240, 10, 20);
			ColisionALL(840, 240, 10, 20);

			g.setColor(Color.red);
			g.fillRect(Tab[14], 250, 10, 10);
			ColisionRed(Tab[14], 250, 10, 10);
			if (y==260 && x+Size<400){
				if (x>Tab[14]+10) Tab[14]+=1;
				if (x+Size<Tab[14]) Tab[14]-=1;
				}

			if (Tab[1]==0){
				g.fillRect(60-4, 117, 8, 8);
				if (Colision(60-4, 117, 8, 8)) Tab[1]=1;
				}
			else g.fillRect(60-8, 117, 8, 8);
			if (Tab[2]==0){
				g.fillRect(840-4, 245, 8, 8);
				if (Colision(840-4, 245, 8, 8)) Tab[2]=1;
				}
			else g.fillRect(840, 245, 8, 8);

//**Third floor
//*Left
			g.setColor(color);
			g.fillRect(320, 270, 10, 60);
			ColisionAll(320, 270, 10, 60);
			g.fillRect(320, 330, 40, 10);
			ColisionALL(320, 330, 40, 10);

			g.fillRect(265, 360, 95, 10);
			ColisionALL(265, 360, 95, 10);

			g.fillRect(265, 370, 10, 50);
			ColisionAll(265, 370, 10, 50);

			g.fillRect(185, 330, 70, 10);
			ColisionALL(185, 330, 70, 10);	
			g.fillRect(165, 270, 10, 60);
			ColisionAll(165, 270, 10, 60);
			for(int i=0; i<2; i++){
				g.fillRect(75+i*100, 370, 10, 50);
				ColisionAll(75+i*100, 370, 10, 50);

				g.fillRect(75, 360-i*30, 110-i*10, 10);
				ColisionALL(75, 360-i*30, 110-i*10, 10);
				}
//*Right
			g.fillRect(500, 360, 10, 10);
			ColisionALL(500, 360, 10, 10);
			
			for(int i=0; i<12; i++){
				g.fillRect(510+i*30, 370, 10, 40);
				ColisionALL(510+i*30, 370, 10, 40);

				if (i%2==0){
					g.fillRect(510+i*30, 330, 10, 10);
					ColisionALL(510+i*30, 330, 10, 10);
					}
				else if (i!=11){
					g.fillRect(510+i*30, 300, 10, 10);
					ColisionALL(510+i*30, 300, 10, 10);
					}
				}

			g.fillRect(55, 400, 10, 20);
			ColisionALL(55, 400, 10, 20);
			
			g.fillRect(810-5, 270, 10, 30);
			ColisionAll(810-5, 270, 10, 30);
			g.fillRect(820-5, 290, 30, 10);
			ColisionALL(820-5, 290, 30, 10);
			g.setColor(Color.red);
			g.fillRect(185, 410, 80, 10);
			ColisionRed(185, 410, 80, 10);

			g.fillRect(Tab[15], 410, 10, 10);
			ColisionRed(Tab[15], 410, 10, 10);
			if (y==420 && x>500){
				if (x+Size<Tab[15]) Tab[15]-=1;
				if (x>Tab[15]+10) Tab[15]+=1;
				}

			if (Tab[3]==0){
				g.fillRect(55-4, 407, 8, 8);
				if (Colision(55-4, 407, 8, 8)) Tab[3]=1;
				}
			else g.fillRect(55, 407, 8, 8);
			if (Tab[4]==0){
				g.fillRect(820-4-5, 277, 8, 8);
				if (Colision(820-4-5, 277, 8, 8)) Tab[4]=1;
				}
			else g.fillRect(820-8-5, 277, 8, 8);
//**Fourth floor
			g.setColor(color);
//*Left
			for(int i=0; i<12; i++){
				if (i%2==0){
					g.fillRect(50+i*30, 490, 10, 10);
					ColisionALL(50+i*30, 490, 10, 10);
					}
				else{
					g.fillRect(50+i*30, 520, 10, 10);
					ColisionALL(50+i*30, 520, 10, 10);
					}
				g.fillRect(50+i*30, 540, 10, 30);
				ColisionALL(50+i*30, 540, 10, 30);
				}
//*Right

			g.fillRect(500, 455, 90, 10);
			ColisionALL(500, 455, 90, 10);

			for(int i=0; i<2; i++){
				g.fillRect(510+i*70, 520, 10, 70);
				ColisionALL(510+i*70, 520, 10, 70);

				g.fillRect(630+i*100, 520, 10, 70);
				ColisionALL(630+i*100, 520, 10, 70);
				}
			g.fillRect(620, 430, 10, 50);
			ColisionAll(620, 430, 10, 50);

			g.fillRect(630, 490, 10, 10);
			ColisionALL(630, 490, 10, 10);

			g.fillRect(640, 440, 90, 10);
			ColisionALL(640, 440, 90, 10);
			
			g.fillRect(740, 430, 10, 70);
			ColisionAll(740, 430, 10, 70);

			g.fillRect(750, 550, 10, 10);
			ColisionALL(750, 550, 10, 10);

			g.fillRect(775, 470, 10, 30);
			ColisionALL(775, 470, 10, 30); 

			
			g.fillRect(55, 450, 30, 10);
			ColisionALL(55, 450, 30, 10);
			g.fillRect(85, 430, 10, 30);
			ColisionALL(85, 430, 10, 30);

			g.fillRect(835, 560, 10, 20);
			ColisionALL(835, 560, 10, 20);
			g.setColor(Color.red);
			for(int i=0; i<2; i++){
				g.fillRect(520+i*120, 570, 60+i*30, 10);
				ColisionRed(520+i*120, 570, 60+i*30, 10);
				}
			g.fillRect(740, 570, 95, 10);
			ColisionRed(740, 570, 95, 10);

			g.fillRect(Tab[16], 570, 10, 10);
			ColisionRed(Tab[16], 570, 10, 10);
			if (y==580 && x+Size<400){
				if (x>Tab[16]+10) Tab[16]+=1;
				if (x+Size<Tab[16]) Tab[16]-=1;
				}

			if (Tab[5]==0){
				g.fillRect(85-4, 437, 8, 8);
				if (Colision(85-4, 437, 8, 8)) Tab[5]=1;
				}
			else g.fillRect(85, 437, 8, 8);
			if (Tab[6]==0){
				g.fillRect(845-4, 567, 8, 8);
				if (Colision(845-4, 567, 8, 8)) Tab[6]=1;
				}
			else g.fillRect(845-8, 567, 8, 8);
			
//*Blue button
			g.setColor(color);
			g.fillRect(440, Tab[45], 20, 10);//Tab[45]=655
			ColisionALL(440, Tab[45], 20, 10);


			for(int i=0; i<2; i++){
				g.fillRect(431+i*28, 0, 10, 18);
				ColisionAll(431+i*28, 0-100, 10, 18+100);
				}
			if (Tab[1]==0 || Tab[2]==0 || Tab[3]==0 || Tab[4]==0 || Tab[5]==0 || Tab[6]==0){
				g.fillRect(441, 18, 18, 10);
				ColisionAll(441, 18, 18, 10);
				for(int i=0; i<2; i++){
					g.fillRect(425, 640+i*30, 50, 10);
					ColisionALL(425, 640+i*30, 50, 10);
					g.fillRect(425+i*40, 650, 10, 20);
					ColisionAll(425+i*40, 650, 10, 20);
					}
				}
			if (Tab[0]==0){
				g.setColor(Color.blue);
				g.fillRect(446, 5, 8, 8);
				if (Colision(446, 5, 8, 8)) Tab[0]=1;
				g.fillRect(400, 690, 100, 10);
				ColisionALL(400, 690, 100, 10);

				g.fillRect(447, Tab[45], 6, 3);
				if (Sustain(447, Tab[45], 6, 3) && Tab[45]>40){
					Tab[45]-=1;
					y-=1;
					}
				}
			else if (Tab[0]==1){
				g.setColor(Color.cyan);
				g.fillRect(446, 5, 8, 8);
				g.fillRect(400, 690, 100, 10);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==10) Time=Tab[0]=0;
				}
//*5th floor

			g.setColor(Color.orange);
			for(int i=0; i<2; i++){
				g.drawLine(290+i*220, 605, 390+i*220, 605);
				if (LineOn(290+i*220, 605, 390+i*220, 605)){
					y=605+Size;
					if (i==0) x-=1;
					if (i==1) x+=1;
					}
				g.drawLine(290+i*230, 635, 380+i*230, 635);
				if (LineOn(290+i*230, 635, 380+i*230, 635)){
					y=635+Size;
					if (i==0) x+=1;
					if (i==1) x-=1;
					}
				g.drawLine(250+i*270, 665, 380+i*270, 665);
				if (LineOn(250+i*270, 665, 380+i*270, 665)){
					y=665+Size;
					if (i==0) x-=1;
					if (i==1) x+=1;
					}
				g.drawLine(260+i*380, 600, 260+i*380, 680-1); 
				if (LineColision(260+i*380, 600, 260+i*380, 680)) y-=2;
				g.drawLine(40+i*580, 605, 280+i*580, 605);
				if (LineOn(40+i*580, 605, 280+i*580, 605)){
					y=605+Size;
					if (i==0) x-=1;
					if (i==1) x+=1;
					}
				g.drawLine(50+i*610, 630, 240+i*610, 630);
				if (LineOn(50+i*610, 630, 240+i*610, 630)){
					y=630+Size;
					if (i==0) x-=1;
					if (i==1) x+=1;
					}
				g.drawLine(40+i*620, 665, 240+i*620, 665);
				if (LineOn(40+i*620, 665, 240+i*620, 665)){
					y=665+Size;
					if (i==0) x-=1;
					if (i==1) x+=1;
					}
				}
			g.setColor(Color.red);
			for(int i=0; i<2; i++){
				g.fillRect(40+i*490, 590, 330, 10);
				ColisionRed(40+i*490, 590, 330, 10);

				g.fillRect(40+i*470, 680, 350, 10);
				ColisionRed(40+i*470, 680, 350, 10);

				g.fillRect(320+i*210, 620, 50, 10);
				ColisionRed(320+i*210, 620, 50, 10);

				g.fillRect(280+i*330, 600, 10, 60);
				ColisionRed(280+i*330, 600, 10, 60);

				g.fillRect(380+i*130, 630, 10, 50);
				ColisionRed(380+i*130, 630, 10, 50);

				g.fillRect(290+i*260, 650, 60, 10);
				ColisionRed(290+i*260, 650, 60, 10);

				g.fillRect(240+i*410, 620, 10, 60);
				ColisionRed(240+i*410, 620, 10, 60);
				}

			g.setColor(color);
			for(int i=0; i<2; i++){
				g.fillRect(390+i*110, 630, 10, 60);
				ColisionAll(390+i*110, 630, 10, 60);
				g.fillRect(370+i*130, 620, 30, 10);
				ColisionALL(370+i*130, 620, 30, 10);
				g.fillRect(390+i*110, 590, 10, 20);
				ColisionAll(390+i*110, 590, 10, 20);

				g.fillRect(290+i*317, 602, 3, 5);
				ColisionAll(290+i*317, 602, 3, 5);
				g.fillRect(377+i*143, 632, 3, 5);
				ColisionAll(377+i*143, 632, 3, 5);
				g.fillRect(250+i*397, 663, 3, 5);
				ColisionAll(250+i*397, 663, 3, 5);
				}


			if (Colision(45, 625, 5, 30)){//#1
				x=55;
				y=10+Size;
				}

			if (Colision(850, 625, 5, 30)){//#2
				x=820;
				y=10+Size;
				}
			for(int i=0; i<2; i++){
				g.fillRect(40+i*810, 625, 10, 30);
				ColisionALL(40+i*810, 625, 10, 30);
				}
			g.setColor(Color.cyan);
			g.fillOval(45, 625, 5, 30);//#1
			g.fillOval(850, 625, 5, 30);//#2

			
//*Last floor
			g.setColor(color);
			g.fillRect(140, 700, 10, 60);
			ColisionAll(140, 700, 10, 60);
			g.fillRect(150, 750, 690, 10);
			ColisionALL(150, 750, 690, 10);

			g.fillRect(100, 800-22, 5, 22);
			ColisionALL(100, 800-22, 5, 22);
			g.setColor(Color.orange);
			g.drawLine(105, 780, 860, 780);
			if (LineOn(105, 780, 860, 780) && x>105){
				Slide(105, 780, 860, 780);
				x-=3;
				}
			
			g.setColor(Color.red);
			g.fillRect(Tab[40], 700, 20, 50);
			ColisionRed(Tab[40], 700, 20, 50);
			if (y>710 && Tab[40]<1000) Tab[40]+=1;

			g.fillRect(115, 790, 745, 10);
			ColisionRed(115, 790, 745, 10);
//***Goal Level 47
			g.setColor(color);
			g.fillRect(50, 795, 30, 5);
			ColisionALL(50, 795, 30, 5);
			g.setColor(Color.black);
			g.fillRect(55, 745, 20, 50);
			if (x==55 && y==795){
				Level=Pan.Level=48;
				SLight=50;
				initP=0;
				}
//*
			if (x>105 || y<700){ if (Tab[30]==0){
				if (TimeOn2==0){
					new Time2().start();
					TimeOn2=1;
					if (SLight>0) SLight-=1;
					else Tab[30]=1;
					}
				}
			else if (Tab[30]==1){
				if (TimeOn2==0){
					new Time2().start();
					TimeOn2=1;
					}
				if (Time2>=30 && SLight<900) SLight+=5;
				else if (SLight==900) Time2=Tab[30]=0;
				}
				}


			
			//g.fillRect(100, 0, 700, 800);
			//SLight=1000;
			int LeftSide=0;
			int RightSide=900;
			int UpSide=0;
			int DownSide=800;
			g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
			if (y+SLight<=DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
				g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
				}
			else if (y+SLight>DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
				g.fillRect(LeftSide, y, x-SLight, DownSide-y);
				g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
				}
				


			}
//***Level 48
		else if (Level==48){
			if (initP==0){
				x=55;
				y=795;
				SLight=50;
				ResetTab();
				/*
				* 0 is light switch:
					* 0 is default light
					* 1 is light in first passage
					* 2 is light in second passage
					* 3 is light in third passage
					* 4 is light in last passage
				* 1 is blue button 1
				* 2 is blue button 2
				* 3 is y position of first meet block/red in 3rdPassage
				* 4 is its trigger
				* 5 is x position of second meet block on sustain
				* 6 is x position of third meet block on sustain
				* 7 is x position of fourth meet block on sustain
				* 8 is x position of fifth meet block moving
				* 9 is its trigger
				* 10 is x position of sixth meet block moving
				* 11 is its trigger
				* 12 is x position of seventh meet block moving
				* 13 is its trigger
				* 14 is x cos position of duo block moving
				* 15 is y sin position of duo block moving
				* 16 is their movement addition
				* 17 is x position of eigth meet block moving on sustain
				* 18 is y position of eigth meet block moving on sustain
				* 19 is x or/and y addition of x or y on sustain in last passage
				* 20 is x position of nineth meet block moving on sustain
				* 21 is y addition of rolling up blocks in right center
				* 22 is x or/and y addition of blocks in last passage
				* 23 is their trigger
				* 24 is x cos difference
				*
				* 25 is light addition in 1st passage
				* 26 is its trigger
				* 27 is light addition in 2nd passage
				* 28 is its trigger
				* 29 is light addition in 3rd passage
				* 30 is its trigger
				* 31 is light addition in last passage
				* 32 is its trigger
				* 
				* 
				* 34 is x difference of duo block moving (2nd)
				*/
				Tab[3]=520;
				Tab[5]=140;
				Tab[6]=200;
				Tab[7]=265;
				Tab[8]=330;
				Tab[10]=250;
				Tab[12]=360;
				Tab[17]=405;
				Tab[18]=545;
				Tab[20]=210;
				initP=1;
				}
			Color color = new Color(50, 65, 110);
			g.setColor(color);

//*First passage
			g.fillRect(30, 690, 770, 10);
			ColisionALL(30, 690, 770, 10);

			for(int i=0; i<2; i++){
				g.setColor(color);
				g.fillRect(80+i*700, 700, 20, 60);
				ColisionAll(80+i*700, 700, 20, 60);
				g.setColor(Color.orange);
				g.fillRect(80+i*700, 760, 20, 10);
				ColisionAll(80+i*700, 760, 20, 10);
				g.setColor(Color.black);
				g.fillRect(80+i*700, 770, 20, 30);
				if (LineColisionRight(100+i*680, 770, 100+i*680, 800)) Tab[0]=1;
				if (LineColisionLeft(80+i*720, 770, 80+i*720, 800)) Tab[0]=0;
				}

			g.setColor(color);
			for(int i=0; i<2; i++){
				g.fillRect(120+i*630, 785, 10, 15);
				ColisionALL(120+i*630, 785, 10, 15);
				}
			for(int i=0; i<4; i++){
				g.fillRect(140+i*40, 790-i*15, 15, 5);
				ColisionALL(140+i*40, 790-i*15, 15, 5);
				if (i%2==0){
					g.fillRect(300+i*50, 750, 15, 5);
					ColisionALL(300+i*50, 750, 15, 5);
					}
				else{
					g.fillRect(300+i*50, 790, 15, 5);
					ColisionALL(300+i*50, 790, 15, 5);
					}
				g.fillRect(510+i*65, 750, 15, 5);
				ColisionALL(510+i*65, 750, 15, 5);
				}
			g.setColor(Color.red);
			g.fillRect(130, 795, 620, 5);
			ColisionRed(130, 795, 620, 5);

			g.setColor(Color.green);
			//g.fillRect(480, 700-3, 20, 3);
			//WallStickAll(480, 700-3, 20, 3);

			g.fillRect(800-3, 690, 3, 10);
			WallStickAll(800-3, 690, 3, 10);
//*Second Passage
			g.setColor(color);
			g.fillRect(60, 580, 810, 10);
			ColisionALL(60, 580, 810, 10);
			for(int i=0; i<2; i++){
				g.setColor(Color.orange);
				g.fillRect(80+i*680, 650, 20, 10);
				ColisionAll(80+i*680, 650, 20, 10);
				g.setColor(Color.black);
				g.fillRect(80+i*680, 660, 20, 30);
				if (LineColisionRight(100+i*660, 660, 100+i*660, 690)) Tab[0]=2;
				if (LineColisionLeft(80+i*700, 660, 80+i*700, 690)) Tab[0]=0;
				g.setColor(color);
				g.fillRect(80+i*680, 590, 20, 60);
				ColisionAll(80+i*680, 590, 20, 60);

				g.fillRect(200, 625+i*25, 160, 5);
				ColisionALL(200, 625+i*25, 160, 5);
				}
			g.fillRect(620, 685, 20, 5);
			ColisionALL(620, 685, 20, 5);

			g.fillRect(470, 640, 140, 5);
			ColisionALL(470, 640, 140, 5);

			g.fillRect(375, 685, 20, 5);
			ColisionALL(375, 685, 20, 5);

			for(int i=0; i<4; i++){
				g.fillRect(185-i*25, 660, 15, 30);
				ColisionALL(185-i*25, 660, 15, 30);
				}

			g.setColor(Color.red);
			g.fillRect(720, 640, 10, 50);
			ColisionRed(720, 640, 10, 50);

			g.fillRect(690, 590, 10, 50);
			ColisionALL(690, 590, 10, 50);

			g.fillRect(640, 670, 20, 20);
			ColisionRed(640, 670, 20, 20);

			for(int i=0; i<2; i++){
				g.fillRect(470+i*5, 645-i*55, 140, 45-i*35);
				ColisionRed(470+i*5, 645-i*55, 140, 45-i*35);

				g.fillRect(425-i*30, 590+i*90, 30, 10);
				ColisionRed(425-i*30, 590+i*90, 30, 10);

				g.fillRect(200, 590+i*65, 160, 35);
				ColisionRed(200, 590+i*65, 160, 35);
				}
			for(int i=0; i<3; i++){
				g.fillRect(175-i*25, 660, 10, 30);
				ColisionRed(175-i*25, 660, 10, 30);
				}
			
			g.setColor(Color.green);
			g.fillRect(395, 590-3, 30, 3);
			g.fillRect(60, 580, 3, 10);

//*Third Passage
			g.setColor(color);
			g.fillRect(30, 190, 820, 10);
			ColisionALL(30, 190, 820, 10);

			g.fillRect(30, 233, 20, 10);
			ColisionALL(30, 233, 20, 10);

			for(int i=0; i<2; i++){
				g.setColor(Color.orange);
				g.fillRect(80+i*750, 540, 20, 10);
				ColisionAll(80+i*750, 540, 20, 10);
				g.setColor(Color.black);
				g.fillRect(80+i*750, 550, 20, 30);
				if (LineColisionRight(100+i*730, 550, 100+i*730, 580)) Tab[0]=3;
				if (LineColisionLeft(80+i*770, 550, 80+i*770, 580)) Tab[0]=0;
				g.setColor(color);
				g.fillRect(80+i*750, 190, 20, 350);
				ColisionAll(80+i*750, 190, 20, 350);

				//g.fillRect(455, 200+i*205, 20, 175);
				//ColisionALL(455, 200+i*205, 20, 175);
				}

			g.fillRect(100, Tab[3], 40, 10);
			ColisionALL(100, Tab[3], 40, 10);
			//if (Tab[4]==0) Tab[3]-=1;
			//if (Tab[4]==1) Tab[3]+=1;
			if (Tab[3]==230) Tab[4]=1;
			if (Tab[3]==520) Tab[4]=0;

			g.fillRect(Tab[5], 225, 15, 5);
			ColisionALL(Tab[5], 225, 15, 5);
			if (Sustain(Tab[5], 225, 15, 5) && Tab[5]+15<215){
				Tab[5]+=1;
				x+=1;
				}
			g.fillRect(Tab[6], 200, 15, 8);
			ColisionAll(Tab[6], 200, 15, 8);
			if (WallStick(Tab[6], 200, 15, 8) && Tab[6]+15<280){
				Tab[6]+=1;
				x+=1;
				}
			g.fillRect(Tab[7], 225, 15, 5);
			ColisionALL(Tab[7], 225, 15, 5);
			if (Sustain(Tab[7], 225, 15, 5) && Tab[7]+15<340){
				Tab[7]+=1;
				x+=1;
				}

			//g.fillRect(390, 345, 10, 20);
			//ColisionALL(390, 345, 10, 20);
			g.fillRect(400-10, 355, 20+10, 10);
			ColisionALL(400-10, 355, 20+10, 10);


			g.fillRect(Tab[8], 335, 20, 5);
			ColisionALL(Tab[8], 335, 20, 5);
			//if (Tab[9]==0) Tab[8]+=1;
			//if (Tab[9]==1) Tab[8]-=1;
			if (Tab[8]==220) Tab[9]=0;
			if (Tab[8]+20==390) Tab[9]=1;

			g.fillRect(Tab[10], 360, 20, 5);
			ColisionALL(Tab[10], 360, 20, 5);
			//if (Tab[11]==0) Tab[10]+=1;
			//if (Tab[11]==1) Tab[10]-=1;
			if (Tab[10]==200) Tab[11]=0;
			if (Tab[10]+20==380) Tab[11]=1;

			g.fillRect(165, Tab[12], 20, 5);
			ColisionALL(165, Tab[12], 20, 5);
			//if (Tab[13]==0) Tab[12]+=1;
			//if (Tab[13]==1) Tab[12]-=1;
			if (Tab[12]==260) Tab[13]=0;
			if (Tab[12]+5==450) Tab[13]=1;

			
			Tab[14]=225+(int)(50*Math.cos((Tab[16]*3.14)/300));
			Tab[15]=485+(int)(50*Math.sin((Tab[16]*3.14)/300));
			g.fillRect(Tab[14], Tab[15], 15, 5);
			ColisionALL(Tab[14], Tab[15], 15, 5);
			if (Sustain(Tab[14], Tab[15], 15, 5)){
				if (Tab[24]==0) Tab[24]=x-Tab[14];
				x=225+(int)(50*Math.cos((Tab[16]*3.14)/300))+Tab[24];
				y=485+(int)(50*Math.sin((Tab[16]*3.14)/300));
				if (MotionL==1) Tab[24]-=1;
				if (MotionR==1) Tab[24]+=1;
				}
			else Tab[24]=0;
			Tab[14]=335+(int)(-50*Math.cos((Tab[16]*3.14)/300));
			Tab[15]=485+(int)(50*Math.sin((Tab[16]*3.14)/300));
			g.fillRect(Tab[14], Tab[15], 15, 5);
			ColisionALL(Tab[14], Tab[15], 15, 5);
			if (Sustain(Tab[14], Tab[15], 15, 5)){
				if (Tab[34]==0) Tab[34]=x-Tab[14];
				x=335+(int)(-50*Math.cos((Tab[16]*3.14)/300))+Tab[34];
				y=485+(int)(50*Math.sin((Tab[16]*3.14)/300));
				if (MotionL==1) Tab[34]-=1;
				if (MotionR==1) Tab[34]+=1;
				}
			else Tab[34]=0;
			Tab[16]+=1;

			g.fillRect(Tab[17], Tab[18], 20, 5);
			ColisionALL(Tab[17], Tab[18], 20, 5);
			if (Sustain(Tab[17], Tab[18], 20, 5)){
				if (Tab[18]>385){
					Tab[18]-=1;
					y-=1;
					//if (Tab[18]==385) Tab[19]=1;
					}
				else if (Tab[17]>230){
					Tab[17]-=1;
					x-=1;
					if (Tab[17]==190) Tab[19]=2;
					}
				}
			g.fillRect(Tab[20], 400, 20, 5);
			ColisionALL(Tab[20], 400, 20, 5);
			if (Sustain(Tab[20], 400, 20, 5) && Tab[20]<475){
				Tab[20]+=1;
				x+=1;
				}


			g.setColor(Color.red);
			g.fillRect(100, Tab[3]+10, 40, 10);
			ColisionRed(100, Tab[3]+10, 40, 10);
			for(int i=0; i<2; i++){
				g.fillRect(455, 200+i*205, 20, 175);
				ColisionRed(455, 200+i*205, 20, 175);
				}
			g.fillRect(140, 230, 280, 10);
			ColisionRed(140, 230, 280, 10);
			g.fillRect(140, 240, 10, 230);
			ColisionRed(140, 240, 10, 230);
			g.fillRect(150, 460, 25, 10);
			ColisionRed(150, 460, 25, 10);
			g.fillRect(165, 470, 10, 90);
			ColisionRed(165, 470, 10, 90);
			g.fillRect(175, 550, 260, 10);
			ColisionRed(175, 550, 260, 10);
			g.fillRect(425, 405, 10, 145);
			ColisionRed(425, 405, 10, 145);

			g.fillRect(200, 365, 255, 10);
			ColisionRed(200, 365, 255, 10);
			g.fillRect(200, 375, 10, 40);
			ColisionRed(200, 375, 10, 40);
			g.fillRect(200, 405, 205, 10);
			ColisionRed(200, 405, 205, 10);

			g.fillRect(100, 200, 30, 10);
			ColisionRed(100, 200, 30, 10);
			g.fillRect(150, 200, 50, 10);
			ColisionRed(150, 200, 50, 10);
			g.fillRect(215, 220, 50, 10);
			ColisionRed(215, 220, 50, 10);
			g.fillRect(280, 200, 50, 10);
			ColisionRed(280, 200, 50, 10);
			g.fillRect(340, 220, 30, 10);
			ColisionRed(340, 220, 30, 10);

			g.fillRect(360, 340, 30, 10);
			ColisionRed(360, 340, 30, 10);
			g.fillRect(380, 350, 10, 15);
			ColisionRed(380, 350, 10, 15);

			
			if (y==580){
				if (Tab[5]>140) Tab[5]-=1;
				if (Tab[6]>200) Tab[6]-=1;
				if (Tab[7]>260) Tab[7]-=1;
				if (Tab[17]<405) Tab[17]+=1;
				else if (Tab[18]<545) Tab[18]+=1;
				//if (Tab[9]==2 && Tab[17]+20<405) Tab[17]+=1;
				//else if (Tab[9]==2 && Tab[18]+5<550) Tab[18]+=1;
				//else Tab[9]=0;
				if (Tab[20]>210) Tab[20]-=1;
				}
//*Right center
			if (TimeOn2==0){
				new Time2().start();
				TimeOn2=1;
				if (Tab[4]==0) Tab[3]-=1;
				if (Tab[4]==1) Tab[3]+=1;
				if (Sustain(100, Tab[3], 40, 10) && Tab[4]==0) y-=1;
				if (Tab[9]==0) Tab[8]+=1;
				if (Tab[9]==1) Tab[8]-=1;
				if (Sustain(Tab[8], 335, 20, 5)){
					if (Tab[9]==0) x+=1;
					if (Tab[9]==1) x-=1;
					}
				if (Tab[11]==0) Tab[10]+=1;
				if (Tab[11]==1) Tab[10]-=1;
				if (Sustain(Tab[10], 360, 20, 5)){					
					if (Tab[11]==0) x+=1;
					if (Tab[11]==1) x-=1;
					}
				if (Tab[13]==0) Tab[12]+=1;
				if (Tab[13]==1) Tab[12]-=1;
				if (Sustain(165, Tab[12], 20, 5) && Tab[13]==1) y-=1;

		
				Tab[21]+=1;
				if (Tab[21]==370) Tab[21]=0;


				if (Tab[23]==0) Tab[22]+=1;
				if (Tab[23]==1) Tab[22]-=1;
				if (Tab[19]==1){
					if (Tab[23]==0) x-=1;
					if (Tab[23]==1) x+=1;
					}
				else if (Tab[19]==3 && Tab[23]==0) y-=1;
				else if (Tab[19]==2){
					if (Tab[23]==0) x+=1;
					if (Tab[23]==1) x-=1;
					}
				else if (Tab[19]==4){
					if (Tab[23]==0){
						x-=1;
						y-=1;
						}
					if (Tab[23]==1) x+=1;
					}
				else if (Tab[19]==5){
					if (Tab[23]==0){
						x+=1;
						y-=1;
						}
					if (Tab[23]==1) x-=1;
					}
				else if (Tab[19]==22){
					if (Tab[23]==0) x+=7;
					if (Tab[23]==1) x-=7;
					}
				}

			g.setColor(color);
			for(int i=0; i<5; i++){
				if (210+i*74-Tab[21]>200){
					g.fillRect(500, 210+i*74-Tab[21], 30, 10);
					ColisionALL(500, 210+i*74-Tab[21], 30, 10);
					if (Sustain(500, 210+i*74-Tab[21], 30, 10)) y=210+i*74-Tab[21];

					if (i%2==0){
						g.fillRect(620, 210+i*74-Tab[21], 25, 10);
						ColisionALL(620, 210+i*74-Tab[21], 25, 10);
						if (Sustain(620, 210+i*74-Tab[21], 25, 10)) y=210+i*74-Tab[21];
						}
					if (i%2==1){
						g.fillRect(720, 210+i*74-Tab[21], 20, 10);
						ColisionALL(720, 210+i*74-Tab[21], 20, 10);
						if (Sustain(720, 210+i*74-Tab[21], 20, 10)) y=210+i*74-Tab[21];
						}
					}
				else{
					g.fillRect(500, 210+i*74-Tab[21]+370, 30, 10);
					ColisionALL(500, 210+i*74-Tab[21]+370, 30, 10);
					if (Sustain(500, 210+i*74-Tab[21]+370, 30, 10)) y=210+i*74-Tab[21]+370;

					if (i%2==0){
						g.fillRect(620, 210+i*74-Tab[21]+370, 25, 10);
						ColisionALL(620, 210+i*74-Tab[21]+370, 25, 10);
						if (Sustain(620, 210+i*74-Tab[21]+370, 25, 10)) y=210+i*75-Tab[21]+370;
						}
					if (i%2==1){
						g.fillRect(720, 210+i*74-Tab[21]+370, 20, 10);
						ColisionALL(720, 210+i*74-Tab[21]+370, 20, 10);
						if (Sustain(720, 210+i*74-Tab[21]+370, 20, 10)) y=210+i*75-Tab[21]+370;
						}
					}
				}
			


			g.setColor(Color.red);
			for(int i=0; i<2; i++){
				g.fillRect(475, 200+i*370, 340, 10);
				ColisionRed(475, 200+i*370, 340, 10);
				}

			for(int i=0; i<5; i++){
				if (200+i*74-Tab[21]+37>200){
					g.fillRect(675, 200+i*74-Tab[21]+37, 15, 10);
					ColisionRed(675, 200+i*74-Tab[21]+37, 15, 10);

					if (i%2==1){
						g.fillRect(770, 200+i*74-Tab[21]+37, 20, 10);
						ColisionRed(770, 200+i*74-Tab[21]+37, 20, 10);
						}
					}
				else{
					g.fillRect(675, 200+i*74-Tab[21]+37+370, 15, 10);
					ColisionRed(675, 200+i*74-Tab[21]+37+370, 15, 10);

					if (i%2==1){
						g.fillRect(770, 200+i*74-Tab[21]+37+370, 20, 10);
						ColisionRed(770, 200+i*74-Tab[21]+37+370, 20, 10);
						}
					}
				}

			g.fillRect(550, 570-Tab[21], 20, 10);
			ColisionRed(550, 570-Tab[21], 20, 10);
			g.fillRect(580, 200+Tab[21], 20, 10);
			ColisionRed(580, 200+Tab[21], 20, 10);


			if (Tab[1]==0){
				g.setColor(Color.blue);
				g.fillRect(35, 220, 8, 8);
				if (Colision(35, 220, 8, 8)) Tab[1]=1;
				g.fillRect(455, 375, 20, 30);
				ColisionAll(455, 375, 20, 30);
				}
			else if (Tab[1]==900){
				g.setColor(Color.cyan);
				g.fillRect(35, 220, 8, 8);
				g.fillRect(455, 375, 20, 30);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==30) Time=Tab[1]=0;
				}

			g.setColor(Color.orange);
			g.drawLine(330, 205, 455-1, 205);
			if (LineOn(330, 205, 455-1, 205)){
				y=205+Size;
				x+=1;
				}
			g.setColor(Color.green);
			for(int i=0; i<5; i++){
				for(int j=0; j<2; j++) g.fillRect(850-3-j*767, 200+i*80, 3, 15);
				}
			g.fillRect(130, 200-3, 20, 3);
			g.fillRect(Tab[6], 208-3, 15, 3);
//*Last Passage
			g.setColor(color);
			g.fillRect(60, 60, 770, 10);
			ColisionALL(60, 60, 770, 10);

			g.fillRect(480, 0, 10, 60);
			ColisionAll(480, 0-100, 10, 60+100);
			g.fillRect(30, 125, 15, 10);
			ColisionALL(30, 125, 15, 10);

			g.fillRect(770-Tab[22], 170, 20, 10);
			ColisionALL(770-Tab[22], 170, 20, 10);

			g.fillRect(580+Tab[22], 130, 20, 10);
			ColisionALL(580+Tab[22], 130, 20, 10);

			g.fillRect(530, 170-Tab[22], 20, 10);
			ColisionALL(530, 170-Tab[22], 20, 10);

			g.fillRect(60+Tab[22]*7, 80, 20, 10);
			ColisionALL(60+Tab[22]*7, 80, 20, 10);

			g.fillRect(480-Tab[22], 170-Tab[22], 20, 10);
			ColisionALL(480-Tab[22], 170-Tab[22], 20, 10);
			g.fillRect(280+Tab[22], 170-Tab[22], 20, 10);
			ColisionALL(280+Tab[22], 170-Tab[22], 20, 10);

			g.fillRect(240-Tab[22], 120, 20, 10);
			ColisionALL(240-Tab[22], 120, 20, 10);

			g.fillRect(130-Tab[22], 170, 20, 10);
			ColisionALL(130-Tab[22], 170, 20, 10);

			if (Sustain(770-Tab[22], 170, 20, 10)) Tab[19]=1;
			else if (Sustain(580+Tab[22], 130, 20, 10)) Tab[19]=2;
			else if (Sustain(530, 170-Tab[22], 20, 10)) Tab[19]=3;
			else if (Sustain(60+Tab[22]*7, 80, 20, 10)) Tab[19]=22;
			else if (Sustain(480-Tab[22], 170-Tab[22], 20, 10)) Tab[19]=4;
			else if (Sustain(280+Tab[22], 170-Tab[22], 20, 10)) Tab[19]=5;
			else if (Sustain(240-Tab[22], 120, 20, 10)) Tab[19]=1;
			else if (Sustain(130-Tab[22], 170, 20, 10)) Tab[19]=1;
			else Tab[19]=0;

			//g.fillRect(50, 170, 10, 10);
			//ColisionALL(50, 170, 10, 10);

			//if (Tab[23]==0) Tab[22]+=1;
			//if (Tab[23]==1) Tab[22]-=1;
			if (Tab[22]==90) Tab[23]=1;
			if (Tab[22]==0) Tab[23]=0;


			g.fillRect(810, 70, 20, 80);
			ColisionAll(810, 70, 20, 80);
			g.fillRect(80, 0, 20, 20);
			ColisionAll(80, 0-100, 20, 20+100);
			g.setColor(Color.orange);
			g.fillRect(810, 150, 20, 10);
			ColisionAll(810, 150, 20, 10);
			g.fillRect(80, 20, 20, 10);
			ColisionAll(80, 20, 20, 10);
			g.setColor(Color.black);
			g.fillRect(810, 160, 20, 30);
			if (LineColisionLeft(810, 160, 810, 190)) Tab[0]=4;
			if (LineColisionRight(830, 160, 830, 190)) Tab[0]=0;
			g.fillRect(80, 30, 20, 30);
			if (LineColisionRight(100, 30, 100, 60)) Tab[0]=0;

			g.setColor(Color.red);
			g.fillRect(30, 180, 760, 10);
			ColisionRed(30, 180, 760, 10);

			g.setColor(Color.green);
			g.fillRect(830-3, 100, 3, 10);
			g.fillRect(45-3, 125, 3, 10);
			g.fillRect(60, 60, 3, 10);

			if (Tab[2]==0){
				g.setColor(Color.blue);
				g.fillRect(495, 47, 8, 8);
				if (Colision(495, 47, 8, 8)) Tab[2]=1;
				g.fillRect(420, 0, 20, 60);
				if (Colision(420, 0, 20, 60)) x=420-Size;
				ColisionAll(420, 0-100, 20, 60+100);
				}
			else if (Tab[2]==10){
				g.setColor(Color.cyan);
				g.fillRect(495, 47, 8, 8);
				g.fillRect(420, 0, 20, 60);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==60) Time=Tab[2]=0;
				}
			
//***Goal Level 48
			g.setColor(Color.black);
			g.fillRect(450, 10, 20, 50);
			if (x==450 && y==60){
				Level=Pan.Level=49;
				initP=0;
				}
			if (Tab[0]!=0){
				if (Tab[26]==0) Tab[25]+=1;
				if (Tab[26]==1) Tab[25]-=1;
				if (Tab[25]==0) Tab[26]=0;
				}
			if (Tab[0]==0){
			//g.fillRect(100, 0, 700, 800);
			//SLight=1000;
			//SLight=50;
			int LeftSide=0;
			int RightSide=900;
			int UpSide=0;
			int DownSide=800;
			g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
			if (y+SLight<=DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
				g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
				}
			else if (y+SLight>DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
				g.fillRect(LeftSide, y, x-SLight, DownSide-y);
				g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
				}
				Tab[25]=Tab[26]=0;
				}

			else if (Tab[0]==1){
				g.fillRect(0,0, 900, 690);
				g.fillRect(0, 690, 100+Tab[25], 110);
				g.fillRect(100+Tab[25]+110, 690, 900-(100+Tab[25]+110), 110);
				if (Tab[25]==570) Tab[26]=1;
				}
			else if (Tab[0]==2){
				for(int i=0; i<2; i++){
					//g.fillRect(0+i*780,0, 80+i*40, 800);
					g.fillRect(0, 0+i*700, 900, 580-i*480);
					int I = (int)Math.pow(-1,i);
					//g.fillRect(80+Tab[25]+i*(120+Tab[25]), 580, i*(800)+I*(i*80+i*(120+Tab[25])), 120);
					g.fillRect(0+i*(80+Tab[25]+120), 580, i*(800)+I*(80+Tab[25]), 120);
					}
				if (Tab[25]==580) Tab[26]=1;
				}
			else if (Tab[0]==3){
				for(int i=0; i<2; i++){
					g.fillRect(0+i*850,0, 80-i*30, 800);
					//g.fillRect(80, 0+i*590, 770, 190+i*20);
					
					g.fillRect(75+i*390, 0, 195, 190+Tab[25]);
					g.fillRect(75+i*390, 190+Tab[25]+100, 195, 800-(190+Tab[25]+100));
					g.fillRect(270+i*390, 0, 195, 490-Tab[25]);
					g.fillRect(270+i*390, 490-Tab[25]+100, 195, 800-(490-Tab[25]+100));
					if (Tab[25]==290) Tab[26]=1;
					}
				}
			else if (Tab[0]==4){
				g.fillRect(830, 0, 70, 800);
				g.fillRect(0, 0, 5, 800);
				for(int i=0; i<3; i++){
					if (i!=1){
						g.fillRect(5+i*275, 0, 275, 70+60-Tab[25]);
						g.fillRect(5+i*275, 70+60-Tab[25]+70, 275, 800-(70+60-Tab[25]+70));
						}
					else{
						g.fillRect(5+i*275, 0, 275, 60+Tab[25]);
						g.fillRect(5+i*275, 60+Tab[25]+70, 275, 800-(60+Tab[25]+70));
						}
					}
				if (Tab[25]==70) Tab[26]=1;
				}




			if (Tab[1]==1){
				g.setColor(Color.cyan);
				g.fillRect(35, 220, 8, 8);
				g.fillRect(455, 375, 20, 30);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==30) Time=Tab[1]=0;
				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 10));
				g.drawString(String.valueOf(30-Time), 460, 392);
				}
			else if (Tab[2]==1){
				g.setColor(Color.cyan);
				g.fillRect(495, 47, 8, 8);
				g.fillRect(420, 0, 20, 60);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==60) Time=Tab[2]=0;
				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 10));
				g.drawString(String.valueOf(60-Time), 425, 32);
				}



			//WallStickAll(480, 700-3, 20, 3);//#F1
			WallStickAll(800-3, 690, 3, 10);//#F1
			WallStickAll(395, 590-3, 30, 3);//#F2
			WallStickAll(60, 580, 3, 10);//#F2
			WallStickAll(Tab[6], 208-3, 15, 3);//F3
			for(int i=0; i<5; i++){
				for(int j=0; j<2; j++) WallStickAll(850-3-j*767, 200+i*80, 3, 15);
				}
			WallStickAll(130, 200-3, 20, 3);
			WallStickAll(830-3, 100, 3, 10);
			WallStickAll(45-3, 125, 3, 10);
			WallStickAll(60, 60, 3, 10);

			}
//***Level 49
		else if (Level==49){
			if (initP==0){
				x=450;
				y=60;
				SLight=50;
				Time=TimeOn=Time2=TimeOn2=0;
				ResetTab();
				/*
				* 0 is red button accumulation //(even for red button & odd for portal)
				* 1 is blue button 1
				* 2 is blue button 2
				* 3 is red button 1
				* 4 is red button 2
				**ULC
				* 5 is x addition on red block rolling
				* 6 is x cos position for circle block & red
				* 7 is y sin position for circle block & red
				* 8 is its x cos addition 
				* 9 is x difference on sustain
			* * * * * 10 is their move variable
				* 11 is x position of block moving
				* 12 is its trigger
				**ULC
				* 13 is y position of block with red on sides on left
				* 14 is its trigger
				**LLC
				* 15 is x position of first meet block moving
				* 16 is its trigger
				* 17 is x addition for second meet duo block
				* 18 is their trigger
				* 19 is trigger of LineOn in CROSS Center
				* 20 is trigger of red button in central CROSS
				* 21 is x difference of trio block 2nd in LRC
				* 22 is y of block to goal in center CROSS
				* 23 is x cos position of fourth meet block half
				* 24 is y sin  position of fourth meet block half
				* 25 is its x difference on sustain 
				* 26 is x cos position of 1st trio blocks & red
				* 27 is y sin position of 1st trio blocks & red
				* 28 is its x difference
				* 29 is x difference of 2nd trio blocks
				* 30 is x position of moving block on sustain next to blue button
				* 31 is y position of moving block on sustain
				**LLC 
				**URC 
				* 32 is y position of red block
				* 33 is its trigger
				* 34 is y position of block moving on sustain next to it
				* 35 is x cos position of red blocks
				* 36 is y sin position of red blocks
				* 37 is x position of block moving on sustain
				* 38 is x,y addition of rolling blocks/red
				**URC 
				* 39 is y addition of block & red
				* 40 is their trigger
				**LRC
				* 41 is x position of first meet block moving
				* 42 is its trigger
				* 43 is x cos position of trio blocks/red
				* 44 is y sin position of trio blocks/red
				* 45 is x difference on sustain of trio blocks/red
				* 46 is x addition of trio blocks/red
				* 47 is its trigger
				* 48 is x position of moving block on sustain next to blue button 2
				* 49 is y position of moving block on sustain
				**LRC
				*/
				Tab[11]=300;
				Tab[13]=400;
				Tab[15]=300;
				Tab[19]=0;
				Tab[30]=375;//y=784
				Tab[31]=789;//x=35
				Tab[32]=200;
				Tab[34]=320;
				Tab[37]=610;
				Tab[41]=650;
				Tab[48]=510;
				Tab[49]=789;
				Tab[22]=350;
				P1Tour=1;
				Pmove=0;
				initP=1;
				}
//*Start
			//Color color = new Color(128, 128, 192);
			Color color = new Color(64, 0, 128);
			g.setColor(color);
			g.fillRect(415, 60, 70, 10);
			ColisionALL(415, 60, 70, 10);
			for(int i=0; i<2; i++){
				//g.fillRect(390, 60+i*670, 120, 10);
				//ColisionALL(390, 60+i*670, 120, 10);
				g.fillRect(110+i*670, 350, 10, 100);
				ColisionAll(110+i*670, 350, 10, 100);

				g.fillRect(400+i*55, 730, 45, 10);
				ColisionALL(400+i*55, 730, 45, 10);

				for(int j=0; j<2; j++){
					g.fillRect(390+i*110, 60+j*400, 10, 290-j*10);
					ColisionALL(390+i*110, 60+j*400, 10, 290-j*10);

					g.fillRect(110+i*390, 340+j*110, 290, 10);
					ColisionALL(110+i*390, 340+j*110, 290, 10);
					}
				}
			for(int i=0; i<2-Tab[3]-Tab[4]; i++){
				g.fillRect(445, 720+i*40, 10, 40);
				ColisionAll(445, 720+i*40, 10, 40);
				}

			for(int i=0; i<2; i++){
				g.fillRect(400+i*85, 130, 15, 10);
				ColisionALL(400+i*85, 130, 15, 10);
				g.fillRect(415+i*60, 60, 10, 80);
				ColisionAll(415+i*60, 60, 10, 80);
				}
			g.setColor(Color.red);
			if (Tab[3]==0){
				g.fillRect(415-4, 117, 8, 8);
				if (Colision(415-4, 117, 8, 8)) Tab[3]=1;
				}
			else g.fillRect(415, 117, 8, 8);
			if (Tab[4]==0){
				g.fillRect(485-4, 117, 8, 8);
				if (Colision(485-4, 117, 8, 8)) Tab[4]=1;
				}
			else g.fillRect(485-8, 117, 8, 8);
			if (Tab[1]==0 && Tab[3]==0){
				g.setColor(Color.blue);
				g.fillRect(432, 787, 8, 8);
				if (Colision(432, 787, 8, 8)) Tab[1]=1;
				g.fillRect(400, 60, 15, 10);
				ColisionALL(400, 60, 15, 10);
				}
			//else if (Tab[1]==1){//###
				
			if (Tab[2]==0 && Tab[4]==0){
				g.setColor(Color.blue);
				g.fillRect(460, 787, 8, 8);
				if (Colision(460, 787, 8, 8)) Tab[2]=1;
				g.fillRect(485, 60, 15, 10);
				ColisionALL(485, 60, 15, 10);
				}
			//else if (Tab[2]==1){//###
			g.setColor(Color.red);

			
//**Left
//*Upper Left Corner
			for(int i=0; i<9; i++){
				g.setColor(color);
				if (i%2==0){
					g.fillRect(370-i*40, 60, 10, 10);
					ColisionALL(370-i*40, 60, 10, 10);
					}
				else{					
					g.fillRect(370-i*40, 90, 10, 10);
					ColisionALL(370-i*40, 90, 10, 10);

					g.fillRect(320-i*40, 110, 10, 10);
					ColisionALL(320-i*40, 110, 10, 10);

					g.setColor(Color.orange);
					g.fillRect(320-i*40, 110, 10, 3);
					}
				}
			g.setColor(color);

			Tab[8]=(int)(100*Math.cos((Tab[10]*3.14)/600));
			Tab[6]=200+(int)(50*Math.cos((Tab[10]*3.14)/400))+Tab[8];
			Tab[7]=200+(int)(50*Math.sin((Tab[10]*3.14)/400));
			g.fillRect(Tab[6], Tab[7], 30, 10);
			ColisionALL(Tab[6], Tab[7], 30, 10);
			if (Sustain(Tab[6], Tab[7], 30, 10)){
				if (Tab[9]==0) Tab[9]=x-Tab[6];
				x=200+(int)(50*Math.cos((Tab[10]*3.14)/400))+Tab[8]+Tab[9];
				y=200+(int)(50*Math.sin((Tab[10]*3.14)/400));
				if (MotionR==1) Tab[9]+=1;
				if (MotionL==1) Tab[9]-=1;
				}
			else Tab[9]=0;
				
			
			g.fillRect(370, 270, 10, 10);
			ColisionALL(370, 270, 10, 10);
			g.fillRect(Tab[11], 310, 20, 10);
			ColisionALL(Tab[11], 310, 20, 10);
			if (Tab[1]==0){
				if (Tab[12]==0) Tab[11]+=1;
				if (Tab[12]==1) Tab[11]-=1;
				if (Tab[11]+20==380) Tab[12]=1;
				if (Tab[11]==110) Tab[12]=0;
				if (Sustain(Tab[11], 310, 20, 10)){
					if (Tab[12]==0) x+=1;
					if (Tab[12]==1) x-=1;
					}
				}
			else if (Tab[11]>110) Tab[11]-=1;
				
			g.fillRect(60, Tab[13], 10, 10);
			ColisionALL(60, Tab[13], 10, 10);
			if (Tab[14]==0) Tab[13]+=1;
			if (Tab[14]==1) Tab[13]-=1;
			if (Tab[13]==330) Tab[14]=0;
			if (Tab[13]==460) Tab[14]=1;
			if (Sustain(60, Tab[13], 10, 10) && Tab[14]==1) y-=1;

			g.setColor(Color.red);
			for(int i=0; i<2; i++){
				g.fillRect(60-5+i*10, Tab[13]+10, 10, 10);
				ColisionRed(60-5+i*10, Tab[13]+10, 10, 10);
				}
			for(int i=0; i<10; i++){
				if (i%2==0){
				if (10+i*37+Tab[5]<380){
					g.fillRect(10+i*37+Tab[5], 130, 10, 10);
					ColisionRed(10+i*37+Tab[5], 130, 10, 10);
					}
				else{
					g.fillRect(10+i*37+Tab[5]-370, 130, 10, 10);
					ColisionRed(10+i*37+Tab[5]-370, 130, 10, 10);
					}}
				}
			//Tab[5]+=1;## In time2
			if (Tab[5]==370) Tab[5]=0;
			for(int i=1; i<4 &&  Tab[1]==0; i++){
				Tab[6]=200+(int)(50*Math.cos(((Tab[10]+200*i)*3.14)/400))+Tab[8];
				Tab[7]=200+(int)(50*Math.sin(((Tab[10]+200*i)*3.14)/400));
				g.fillRect(Tab[6], Tab[7], 30, 10);
				ColisionRed(Tab[6], Tab[7], 30, 10);
				}
//**ULC end

//**LLC
			g.setColor(color);
			g.fillRect(120, 500, 10, 20);
			ColisionALL(120, 500, 10, 20);
			
			g.fillRect(360, 510, 10, 20);
			ColisionAll(360, 510, 10, 20);

			g.fillRect(Tab[15], 485, 20, 5);
			ColisionALL(Tab[15], 485, 20, 5);
			if (Tab[16]==0) Tab[15]+=1;
			if (Tab[16]==1) Tab[15]-=1;
			if (Tab[15]==130) Tab[16]=0;
			if (Tab[15]+20==380) Tab[16]=1;
			if (Sustain(Tab[15], 485, 20, 5)){
				if (Tab[16]==0) x+=1;
				if (Tab[16]==1) x-=1;
				}

			g.fillRect(250, 520, 10, 10);
			ColisionALL(250, 520, 10, 10);

			int OnDuo=0;
			for(int i=0; i<2; i++){
				int I=(int)Math.pow(-1,i);
				g.fillRect(235-I*Tab[17]+i*25, 560, 15, 5);
				ColisionALL(235-I*Tab[17]+i*25, 560, 15, 5);
				if (Sustain(235-I*Tab[17]+i*25, 560, 15, 5)){
					if (i==0) OnDuo=1;
					else if (i==1) OnDuo=2;
					//if (Tab[18]==0) x=x-1*I;
					//if (Tab[18]==1) x=x+1*I;
					}
				}
			//g.fillRect(260+Tab[17], 560, 15, 5);
			//ColisionALL(260+Tab[17], 560, 15, 5);
			//if (Tab[18]==0) Tab[17]+=1;
			//if (Tab[18]==1) Tab[17]-=1;
			if (Tab[17]==115) Tab[18]=1;
			if (Tab[17]==0) Tab[18]=0;
			

			Tab[26]=340+(int)(35*Math.cos((Tab[10]*3.14)/350));
			Tab[27]=625+(int)(35*Math.sin((Tab[10]*3.14)/350))+5;
			g.fillRect(Tab[26], Tab[27], 15, 5);
			ColisionALL(Tab[26], Tab[27], 15, 5);
			if (Sustain(Tab[26], Tab[27], 15, 5)){
				if (Tab[28]==0) Tab[28]=x-Tab[26];
				x=340+(int)(35*Math.cos((Tab[10]*3.14)/350))+Tab[28];
				y=625+(int)(35*Math.sin((Tab[10]*3.14)/350))+5;
				if (MotionL==1) Tab[28]-=1;
				if (MotionR==1) Tab[28]+=1;
				}
			else Tab[28]=0;
			Tab[26]=340+(int)(-35*Math.cos((Tab[10]*3.14)/350));
			Tab[27]=695+(int)(35*Math.sin((Tab[10]*3.14)/350))+5;
			g.fillRect(Tab[26], Tab[27], 15, 5);
			ColisionALL(Tab[26], Tab[27], 15, 5);
			if (Sustain(Tab[26], Tab[27], 15, 5)){
				if (Tab[29]==0) Tab[29]=x-Tab[26];
				x=340+(int)(-35*Math.cos((Tab[10]*3.14)/350))+Tab[29];
				y=695+(int)(35*Math.sin((Tab[10]*3.14)/350))+5;
				if (MotionL==1) Tab[29]-=1;
				if (MotionR==1) Tab[29]+=1;
				}
			else Tab[29]=0;

			for(int i=0; i<2; i++){
				g.fillRect(235+i*30, 580, 10, 15);
				ColisionAll(235+i*30, 580, 10, 15);
				}

			if (Pmove%400==0) P1Tour=P1Tour*(-1);
			Tab[23]=250+(int)(30*Math.cos((Pmove*3.14)/400));
			Tab[24]=610+(int)(-P1Tour*100*Math.sin((Pmove*3.14)/400));
			g.fillRect(Tab[23], Tab[24], 15, 5);
			ColisionALL(Tab[23], Tab[24], 15, 5);
			if (Sustain(Tab[23], Tab[24], 15, 5)){
				if (Tab[25]==0) Tab[25]=x-Tab[23];
				x=250+(int)(30*Math.cos((Pmove*3.14)/400))+Tab[25];
				y=610+(int)(-P1Tour*100*Math.sin((Pmove*3.14)/400));
				if (MotionR==1) Tab[25]+=1;
				if (MotionL==1) Tab[25]-=1;
				}
			else Tab[25]=0;
			Pmove+=1;
			if (Pmove==800) Pmove=0;

			g.fillRect(290, 580, 15, 3);
			ColisionAll(290, 580, 15, 3);

			g.fillRect(Tab[30], 784, 15, 5);
			ColisionALL(Tab[30], 784, 15, 5);
			if (Sustain(Tab[30], 784, 15, 5)){ if( Tab[30]>20){
				Tab[30]-=1;
				x-=1;
				}}
			else if (Tab[30]<375) Tab[30]+=1;
			g.fillRect(35, Tab[31], 15, 5);
			ColisionALL(35, Tab[31], 15, 5);
			if (Sustain(35, Tab[31], 15, 5)){ if (Tab[31]>460){
				Tab[31]-=1;
				y-=1;
				}}
			else if (Tab[31]<789) Tab[31]+=1;



			g.setColor(Color.red);
			for(int i=0; i<2; i++){
				int I=(int)(Math.pow(-1,i));
				g.fillRect(130+i*145, 500, 105-i*10, 10);
				ColisionRed(130+i*145, 500, 105-i*10, 10);

				g.fillRect(130+i*135, 570, 115+i*10, 10);
				ColisionRed(130+i*135, 570, 115+i*10, 10);


				Tab[26]=340+(int)(I*35*Math.sin(((Tab[10]+0*i)*3.14)/350));
				Tab[27]=660+(int)(-35*Math.cos(((Tab[10]+0*i)*3.14)/350))+i*70;
				g.fillRect(Tab[26], Tab[27], 15, 5);
				ColisionRed(Tab[26], Tab[27], 15, 5);
				}
			g.fillRect(295, 620, 10, 120);
			ColisionRed(295, 620, 10, 120);
			g.fillRect(110, 730, 185, 10);
			ColisionRed(110, 730, 185, 10);
//**LLC end
//**URC
			g.setColor(color);
			for(int i=0; i<6; i++){
				g.fillRect(530, 60+i*45, 10, 10);
				ColisionALL(530, 60+i*45, 10, 10);
				}
				
			g.fillRect(580, Tab[34], 20, 10);
			ColisionALL(580, Tab[34], 20, 10);
			if (Sustain(580, Tab[34], 20, 10)){ if (Tab[34]>60){
				Tab[34]-=1;
				y-=1;
				}}
			else if (Tab[34]+10<330) Tab[34]+=1;

			g.fillRect(Tab[37], 320, 20, 10);
			ColisionALL(Tab[37], 320, 20, 10);
			if (Tab[2]==0){
				if (Sustain(Tab[37], 320, 20, 10)){ if (Tab[37]+20<770){
					Tab[37]+=1;
					x+=1;
					}}
				else if (Tab[37]>610) Tab[37]-=1;
				}
			else if (Tab[2]==1){
				if (Sustain(Tab[37], 320, 20, 10)){ if (Tab[37]>695){
					Tab[37]-=1;
					x-=1;
					}}
				else if (Tab[37]+20<770) Tab[37]+=1;
				}

			int w=105;
			int h=105;
			int d=21;
			boolean On = false;
			for(int i=0; i<5; i++){
				if (i%2==0){
					g.setColor(color);
					if (775+i*d+Tab[38]<880){
						g.fillRect(775+i*d+Tab[38], 330-i*d*2-Tab[38]*2, 15, 10);
						ColisionALL(775+i*d+Tab[38], 330-i*d*2-Tab[38]*2, 15, 10);
						if (Sustain(775+i*d+Tab[38], 330-i*d*2-Tab[38]*2, 15, 5)){
							On=true;
							//x+=1;
							//y-=2;
							}
						}
					else{
						g.fillRect(775+i*d+Tab[38]-w, 330-i*d*2-Tab[38]*2+h*2, 15, 10);
						ColisionALL(775+i*d+Tab[38]-w, 330-i*d*2-Tab[38]*2+h*2, 15, 10);
						if (Sustain(775+i*d+Tab[38]-w, 330-i*d*2-Tab[38]*2+h*2, 15, 5)){
							On=true;
							//x+=1;
							//y-=2;
							}
						}
					}
				else if (Tab[2]==0){
					g.setColor(Color.red);
					if (775+i*d+Tab[38]<880){
						g.fillRect(775+i*d+Tab[38], 330-i*d*2-Tab[38]*2, 15, 10);
						ColisionRed(775+i*d+Tab[38], 330-i*d*2-Tab[38]*2, 15, 10);
						}
					else{
						g.fillRect(775+i*d+Tab[38]-w, 330-i*d*2-Tab[38]*2+h*2, 15, 10);
						ColisionRed(775+i*d+Tab[38]-w, 330-i*d*2-Tab[38]*2+h*2, 15, 10);
						}
					}
				}

			g.fillRect(620, 100, 10, 10);//SLight
			ColisionALL(620, 100, 10, 10);

			g.fillRect(685, 100, 10, 10);
			ColisionALL(685, 100, 10, 10);

			g.fillRect(645, 180, 10, 10);
			ColisionALL(645, 180, 10, 10);

			g.fillRect(735, 180, 10, 10);
			ColisionALL(735, 180, 10, 10);

			g.fillRect(685, 250, 10, 10);
			ColisionALL(685, 250, 10, 10);

			g.fillRect(690, 180, 10, 10);//SLight
			ColisionALL(690, 180, 10, 10);

			g.fillRect(630, 250, 10, 10);//SLight
			ColisionALL(630, 250, 10, 10);

			g.fillRect(800, 135, 10, 10);//Slight
			ColisionALL(800, 135, 10, 10);
			g.fillRect(750, 75, 10, 10);//SLight
			ColisionALL(750, 75, 10, 10);

			g.setColor(Color.orange);
			g.fillRect(620, 100, 10, 3);
			g.fillRect(690, 180, 10, 3);
			g.fillRect(630, 250, 10, 3);
			g.fillRect(800, 135, 10, 3);
			g.fillRect(750, 75, 10, 3);


			g.setColor(Color.red);
			g.fillRect(570, Tab[32], 10, 30);
			ColisionRed(570, Tab[32], 10, 30);
			if (Tab[33]==0) Tab[32]+=1;
			if (Tab[33]==1) Tab[32]-=1;
			if (Tab[32]+30==330) Tab[33]=1;
			if (Tab[32]==60) Tab[33]=0;

			if (Tab[2]==0){
				g.fillRect(600, 60, 10, 270);
				ColisionRed(600, 60, 10, 270);
				}

			Tab[35]=680+(int)(30*Math.cos((Tab[10]*3.14)/500));
			Tab[36]=100+(int)(30*Math.sin((Tab[10]*3.14)/500));
			g.fillRect(Tab[35], Tab[36], 20, 10);
			ColisionRed(Tab[35], Tab[36], 20, 10);
			Tab[35]=640+(int)(30*Math.cos((Tab[10]*3.14)/500));
			Tab[36]=180+(int)(-30*Math.sin((Tab[10]*3.14)/500));
			g.fillRect(Tab[35], Tab[36], 20, 10);
			ColisionRed(Tab[35], Tab[36], 20, 10);
			Tab[35]=730+(int)(-30*Math.cos((Tab[10]*3.14)/500));
			Tab[36]=180+(int)(30*Math.sin((Tab[10]*3.14)/500));
			g.fillRect(Tab[35], Tab[36], 20, 10);
			ColisionRed(Tab[35], Tab[36], 20, 10);
			Tab[35]=680+(int)(-30*Math.cos((Tab[10]*3.14)/500));
			Tab[36]=250+(int)(-30*Math.sin((Tab[10]*3.14)/500));
			g.fillRect(Tab[35], Tab[36], 20, 10);
			ColisionRed(Tab[35], Tab[36], 20, 10);
			
//**URC end
			g.fillRect(810, 455-Tab[39], 15, 5);
			ColisionRed(810, 455-Tab[39], 15, 5);
			g.setColor(color);
			g.fillRect(845, 330+Tab[39], 15, 5);
			ColisionALL(845, 330+Tab[39], 15, 5);
			if (Tab[40]==0) Tab[39]+=1;
			if (Tab[40]==1) Tab[39]-=1;
			if (Tab[39]==0) Tab[40]=0;
			if (Tab[39]==125) Tab[40]=1;
			if (Sustain(845, 330+Tab[39], 15, 5) && Tab[40]==1) y-=1;
//**LRC

			for(int i=1; i<=5; i++){
				g.fillRect(530+i*52-15, 500, 15, 5);
				ColisionALL(530+i*52-15, 500, 15, 5);
				}
			g.fillRect(530, 520, 10, 20);
			ColisionALL(530, 520, 10, 20);


			g.fillRect(Tab[41], 560, 15, 5);
			ColisionALL(Tab[41], 560, 15, 5);
			if (Tab[42]==0) Tab[41]+=1;
			if (Tab[42]==1) Tab[41]-=1;
			if (Tab[41]==510) Tab[42]=0;
			if (Tab[41]+15==780) Tab[42]=1;
			if (Sustain(Tab[41], 560, 15, 5)){
				if (Tab[42]==0) x+=1;
				if (Tab[42]==1) x-=1;
				}
			for(int i=0; i<2; i++){
				g.fillRect(625+i*30, 600, 10, 10);
				ColisionALL(625+i*30, 600, 10, 10);

				g.fillRect(625+i*30, 620, 10, 30);
				ColisionAll(625+i*30, 620, 10, 30);
				}
			int s=420;
			int s2=(int)(s/2);
			boolean On2 = false;
			Tab[43]=565+(int)(35*Math.cos((Tab[10]*3.14)/s))+Tab[46];
			Tab[44]=695+(int)(35*Math.sin((Tab[10]*3.14)/s));
			g.fillRect(Tab[43], Tab[44], 15, 5);
			ColisionALL(Tab[43], Tab[44], 15, 5);
			if (Sustain(Tab[43], Tab[44], 15, 5)){
				if (Tab[45]==0) Tab[45]=x-Tab[43];
				x=565+(int)(35*Math.cos((Tab[10]*3.14)/s))+Tab[46]+Tab[45];
				y=695+(int)(35*Math.sin((Tab[10]*3.14)/s));
				if (MotionL==1) Tab[45]-=1;
				if (MotionR==1) Tab[45]+=1;
				On2=true;
				}
			else Tab[45]=0;

			Tab[43]=615+(int)(-35*Math.cos(((Tab[10]-s2)*3.14)/s))+Tab[46];
			Tab[44]=695+(int)(35*Math.sin(((Tab[10]-s2)*3.14)/s));
			g.fillRect(Tab[43], Tab[44], 15, 5);
			ColisionALL(Tab[43], Tab[44], 15, 5);
			if (Sustain(Tab[43], Tab[44], 15, 5)){
				if (Tab[21]==0) Tab[21]=x-Tab[43];
				x=615+(int)(-35*Math.cos(((Tab[10]-s2)*3.14)/s))+Tab[46]+Tab[21];
				y=695+(int)(35*Math.sin(((Tab[10]-s2)*3.14)/s));
				if (MotionL==1) Tab[21]-=1;
				if (MotionR==1) Tab[21]+=1;
				On2=true;
				}
			else Tab[21]=0;
			//if (Tab[47]==0) Tab[46]+=1;
			//if (Tab[47]==1) Tab[46]-=1;
			if (Tab[46]==115) Tab[47]=1;
			if (Tab[46]==0) Tab[47]=0;


			g.fillRect(Tab[48], 784, 15, 5);
			ColisionALL(Tab[48], 784, 15, 5);
			if (Sustain(Tab[48], 784, 15, 5)){ if (Tab[48]+15<880){
				Tab[48]+=1;
				x+=1;
				}}
			else if (Tab[48]>510) Tab[48]-=1;
			g.fillRect(850, Tab[49], 15, 5);
			ColisionALL(850, Tab[49], 15, 5);
			if (Sustain(850, Tab[49], 15, 5)){ if (Tab[49]>470){
				Tab[49]-=1;
				y-=1;
				}}
			else if (Tab[49]<789) Tab[49]+=1;


			g.setColor(Color.red);
			g.fillRect(780, 530, 10, 200);
			ColisionRed(780, 530, 10, 200);
			for(int i=0; i<2; i++){
				g.fillRect(510+i*145, 610, 125, 10);
				ColisionRed(510+i*145, 610, 125, 10);
				g.fillRect(530+i*10, 730-i*210, 260-i*10, 10);
				ColisionRed(530+i*10, 730-i*210, 260-i*10, 10);
				}
			Tab[43]=565+(int)(35*Math.cos(((Tab[10]+s)*3.14)/s))+Tab[46];
			Tab[44]=695+(int)(35*Math.sin(((Tab[10]+s)*3.14)/s));
			g.fillRect(Tab[43], Tab[44], 15, 5);
			ColisionRed(Tab[43], Tab[44], 15, 5);
			Tab[43]=615+(int)(-35*Math.cos(((Tab[10]+s-s2)*3.14)/s))+Tab[46];
			Tab[44]=695+(int)(35*Math.sin(((Tab[10]+s-s2)*3.14)/s));
			g.fillRect(Tab[43], Tab[44], 15, 5);
			ColisionRed(Tab[43], Tab[44], 15, 5);
//**LRC end
//**Time2
			if (TimeOn2==0){
				new Time2().start();
				TimeOn2=1;
				if (Tab[18]==0) Tab[17]+=1;
				if (Tab[18]==1) Tab[17]-=1;
				if (OnDuo==1){
					if (Tab[18]==0) x-=1;
					if (Tab[18]==1) x+=1;
					}
				else if (OnDuo==2){
					if (Tab[18]==0) x+=1;
					if (Tab[18]==1) x-=1;
					}
				if (On){
					x+=1;
					y-=2;
					}
				Tab[38]+=1;//#R
				if (Tab[38]==105) Tab[38]=0;
				if (Tab[47]==0) Tab[46]+=1;
				if (Tab[47]==1) Tab[46]-=1;
				if (On2){
					if (Tab[47]==0) x+=1;
					if (Tab[47]==1) x-=1;
					}
				Tab[5]+=1;
				}
				
//*green
			g.setColor(Color.green);
			for(int i=0; i<2; i++){
				for(int j=0; j<2; j++) g.fillRect(110+j*677, 340+i*110, 3, 10);
				}
			g.fillRect(120, 510, 3, 10);
			g.fillRect(370-3, 510, 3, 20);
			for(int i=0; i<2; i++) g.fillRect(245+i*23-3, 580, 3, 10);
			g.fillRect(290, 580, 15, 3);
			g.fillRect(530, 530, 3, 10);
			for(int i=0; i<2; i++) g.fillRect(635-3+i*23, 620, 3, 10);

			Tab[10]+=1;
			//if (Tab[10]==10000) Tab[10]=0;
			g.setColor(Color.red);
			for(int i=0; i<2; i++){
				g.fillRect(380+i*130, 60, 10, 280);
				ColisionRed(380+i*130, 60, 10, 280);
				g.fillRect(110+i*410, 330, 270, 10);
				ColisionRed(110+i*410, 330, 270, 10);

				g.fillRect(0+i*880,0, 20, 800);
				ColisionRed(0+i*880,0, 20, 800);

				g.fillRect(20+i*490, 790, 370, 10);
				ColisionRed(20+i*490, 790, 370, 10);
				}
//*SLight

			if (Sustain(40, 110, 10, 10) || Sustain(120, 110, 10, 10) || Sustain(200, 110, 10, 10) || Sustain(280, 110, 10, 10) || Sustain(620, 100, 10, 10) || Sustain(690, 180, 10, 10) || Sustain(630, 250, 10, 10) || Sustain(800, 135, 10, 10) || Sustain(750, 75, 10, 10)){
				if (SLight<100) SLight+=1;
				}
			else if (SLight>50) SLight-=1;
//**CROSS
//*Below
			for(int i=0; i<2; i++){
				g.setColor(color);
				g.fillRect(445, 500+i*150, 10, 10);
				ColisionALL(445, 500+i*150, 10, 10);
				g.fillRect(400+i*90, 555, 10, 10);
				ColisionALL(400+i*90, 555, 10, 10);
				


				g.fillRect(435+i*20, 460, 10, 20);
				ColisionAll(435+i*20, 460, 10, 20);
				

				g.setColor(Color.red);
				for(int j=0; j<2; j++){
					g.fillRect(424+j*45, 520+i*150, 7, 7);
					ColisionRed(424+j*45, 520+i*150, 7, 7);
					}
				}
//*Center
			g.setColor(color);
			for(int i=0; i<2; i++){
				g.fillRect(400+i*55, 440+10, 45, 10);
				ColisionALL(400+i*55, 440+10, 45, 10);

				g.fillRect(400+i*90, 385, 10, 30);
				ColisionALL(400+i*90, 385, 10, 30);

				g.fillRect(390+i*110, 350, 10, 35);
				ColisionAll(390+i*110, 350, 10, 35);

				g.fillRect(400+i*65, 340, 35, 10);
				ColisionALL(400+i*65, 340, 35, 10);
				if (Tab[20]!=2){
					//g.fillRect(390+i*110, 415, 10, 25+10);
					//ColisionAll(390+i*110, 415, 10, 25+10);
					}
				else if (Tab[20]==2){
					//g.fillRect(410+i*70, 415, 10, 25+10);
					//ColisionALL(410+i*70, 415, 10, 25+10);
					}
				g.fillRect(390+i*110, 415, 10, 25);
				ColisionAll(390+i*110, 415, 10, 25);				
				}
			if (LineColisionRight(400, 415, 400, 440) && Tab[20]==2) Tab[20]=0;
			if (LineColisionLeft(500, 415, 500, 440) && Tab[20]==2) Tab[20]=0;
			g.fillRect(450-15, Tab[22], 30, 30);
			ColisionALL(450-15, Tab[22], 30, 30);
			

			if (Tab[20]==1 && Tab[0]<13){
			if (Tab[0]%2==0){
				g.setColor(Color.cyan);
				g.fillOval(410-5, 385, 5, 30);
				if (Colision(410-5, 385, 5+1, 30)){//#1
					x=120;
					y=350+Size;
					Tab[20]=2;
					Tab[19]=0;
					}
				g.setColor(Color.gray);
				g.fillOval(490, 385, 5, 30);
				}
			else{
				g.setColor(Color.cyan);
				g.fillOval(490, 385, 5, 30);
				if (Colision(490-1, 385, 5, 30)){//#2
					x=780-Size;
					y=350+Size;
					Tab[20]=2;
					Tab[19]=1;
					}
				g.setColor(Color.gray);
				g.fillOval(410-5, 385, 5, 30);
				}
				}
			else if (Tab[20]==0){
				g.setColor(Color.gray);
				g.fillOval(490, 385, 5, 30);
				g.fillOval(410-5, 385, 5, 30);
				}
			g.setColor(Color.magenta);
			g.fillOval(120-5, 350, 5, 10);//#1
			g.fillOval(780, 350, 5, 10);//#2
			g.fillOval(445, 660-5, 10, 5);//#Portal center

			if (Tab[0]==13 && Tab[22]<400) Tab[22]+=1;
			g.setColor(Color.red);
			if (Tab[20]==0 && Tab[0]<13){
				g.fillRect(450-4, 400-4, 8, 8);
				if (Colision(450-4, 400-4, 8, 8)){
					Tab[20]=1;
					Tab[0]+=1;
					}
				}

			if (Tab[20]==2) SLight=50-Tab[0]*3;
			else if (Tab[20]==0 && SLight<50) SLight+=1;
//*Portal colision

			if (Colision(160, 370, 5, 10) || Colision(180-5, 370, 5+1, 10) || 
			Colision(265, 390, 5, 10) || Colision(220, 430, 5, 10) || 
			Colision(280, 430, 5, 10) || Colision(620-1, 370, 5, 10) || 
			Colision(690-1, 370, 5, 10) || Colision(740-5, 390, 5+1, 10) || 
			Colision(610-1, 410, 5, 10) || Colision(780-1, 430, 5, 10)){
				x=445;
				y=660+Size;
				Tab[20]=0;
				Motion=1;
				}
//*Center Left
			g.setColor(color);
			g.fillRect(160, 370, 20, 10);
			ColisionAll(160, 370, 20, 10);
			g.fillRect(220, 370, 10, 10);
			ColisionAll(220, 370, 10, 10);

			g.fillRect(260, 390, 10, 10);
			ColisionAll(260, 390, 10, 10);

			g.fillRect(220, 430, 10, 10);
			ColisionAll(220, 430, 10, 10);
			g.fillRect(280, 430, 10, 10);
			ColisionAll(280, 430, 10, 10);

			g.setColor(Color.orange);
			for(int i=0; i<2; i++){
				g.drawLine(120+i*390, 350, 390+i*390-1, 350);
				g.drawLine(120+i*390, 370, 390+i*390-1, 370);
				g.drawLine(120+i*380, 390, 400+i*380-1, 390);
				g.drawLine(120+i*380, 410, 400+i*380-1, 410);
				g.drawLine(120+i*390, 430, 390+i*390-1, 430);
				
				if (LineOn(120+i*390, 350, 390+i*390, 350)){
					y=350+Size;
					if (Tab[19]==0) x+=1;
					if (Tab[19]==1) x-=1;
					if (i==0){
						if (x==120) Tab[19]=0;
						if (x==380) Tab[19]=1;
						}
					else{
						if (x==510) Tab[19]=0;
						if (x==770) Tab[19]=1;
						}
					Motion=0;
					}
				else if (LineOn(120+i*390, 370, 390+i*390, 370)){
					y=370+Size;
					if (i==0){
						if (x<160) x+=1;
						if (180<x && x<220) x-=1;
						if (x>=230){
							if (Tab[19]==0) x+=1;
							if (Tab[19]==1) x-=1;
							if (x==230) Tab[19]=0;
							if (x==380) Tab[19]=1;
							}
						}
					else{
						if (x<700) x+=1;
						if (x>=700){
							if (Tab[19]==0) x+=1;
							if (Tab[19]==1) x-=1;
							if (x==700) Tab[19]=0;
							if (x==770) Tab[19]=1;
							}
						}
					Motion=0;
					}
				else if (LineOn(120+i*390, 390, 390+i*390, 390)){
					y=390+Size;
					if (i==0){
						if (x>250) x-=1;
						if (x<=250){
							if (Tab[19]==0) x+=1;
							if (Tab[19]==1) x-=1;
							if (x==120) Tab[19]=0;
							if (x==250) Tab[19]=1;	
							}
						}
					else{
						if (x>730) x-=1;
						if (x<=730){
							if (Tab[19]==0) x+=1;
							if (Tab[19]==1) x-=1;
							if (x==510) Tab[19]=0;
							if (x==720) Tab[19]=1;	
							}
						}
						
					Motion=0;
					}
				else if (LineOn(120+i*390, 410, 390+i*390, 410)){
					y=410+Size;
					if (i==0){
						if (Tab[19]==0) x+=1;
						if (Tab[19]==1) x-=1;
						if (x==120) Tab[19]=0;
						if (x==310) Tab[19]=1;
						}
					else{
						if (x<620) x+=1;
						if (x>=620){
							if (Tab[19]==0) x+=1;
							if (Tab[19]==1) x-=1;
							if (x==620) Tab[19]=0;
							if (x==770) Tab[19]=1;
							}
						}
					Motion=0;
					}
				else if (LineOn(120+i*390, 430, 390+i*390, 430)){
					y=430+Size;
					if (i==0){
						if (x<290) x+=1;
						if (x>=290){
							if (Tab[19]==0) x+=1;
							if (Tab[19]==1) x-=1;
							if (x==290) Tab[19]=0;
							if (x==380) Tab[19]=1;
							}
						}
					else{
						if (x>670) x+=1;
						if (x<=670){
							if (Tab[19]==0) x+=1;
							if (Tab[19]==1) x-=1;
							if (x==510) Tab[19]=0;
							if (x==660) Tab[19]=1;
							}
						}
					Motion=0;
					}
				else if (y==450) Motion=1;
				}

			g.setColor(Color.red);
			g.fillRect(120, 360, 10, 10);
			ColisionRed(120, 360, 10, 10);
			for(int i=0; i<3; i++){
				g.fillRect(160+i*60, 360, 30, 10);
				ColisionRed(160+i*60, 360, 30, 10);
				}
			g.fillRect(310, 360, 80, 10);
			ColisionRed(310, 360, 80, 10);

			g.fillRect(120, 380, 110, 10);
			ColisionRed(120, 380, 110, 10);
			g.fillRect(260, 380, 30, 10);
			ColisionRed(260, 380, 30, 10);
			g.fillRect(320, 380, 40, 10);
			ColisionRed(320, 380, 40, 10);

			for(int i=0; i<2; i++){
				g.fillRect(150+i*110, 400, 80+i*60, 10);
				ColisionRed(150+i*110, 400, 80+i*60, 10);
				}
			g.fillRect(150, 420, 80, 10);
			ColisionRed(150, 420, 80, 10);
			g.fillRect(260, 420, 30, 10);
			ColisionRed(260, 420, 30, 10);
			g.fillRect(320, 420, 70, 10);
			ColisionRed(320, 420, 70, 10);
//*Center Right
			g.fillRect(540, 360, 90, 10);
			ColisionRed(540, 360, 90, 10);
			g.fillRect(660, 360, 40, 10);
			ColisionRed(660, 360, 40, 10);
			g.fillRect(730, 360, 20, 10);
			ColisionRed(730, 360, 20, 10);

			g.fillRect(510, 380, 190, 10);
			ColisionRed(510, 380, 190, 10);
			g.fillRect(730, 380, 20, 10);
			ColisionRed(730, 380, 20, 10);

			g.fillRect(530, 400, 100, 10);
			ColisionRed(530, 400, 100, 10);
			//g.fillRect(580, 400, 50, 10);
			//ColisionRed(580, 400, 50, 10);
			g.fillRect(660, 400, 20, 10);
			ColisionRed(660, 400, 20, 10);
			g.fillRect(710, 400, 70, 10);
			ColisionRed(710, 400, 70, 10);

			g.fillRect(710, 420, 70, 10);
			ColisionRed(710, 420, 70, 10);
			g.fillRect(650, 420, 30, 10);
			ColisionRed(650, 420, 30, 10);
			//g.fillRect(580, 420, 40, 10);
			//ColisionRed(580, 420, 40, 10);
			g.fillRect(510, 420, 110, 10);
			ColisionRed(510, 420, 110, 10);


			for(int i=0; i<2; i++){
				g.fillRect(120+i*420, 440, 240, 10);
				ColisionRed(120+i*420, 440, 240, 10);
				}
			g.setColor(color);
			g.fillRect(620, 370, 10, 10);
			ColisionAll(620, 370, 10, 10);
			g.fillRect(690, 370, 10, 10);
			ColisionAll(690, 370, 10, 10);

			g.fillRect(730, 390, 10, 10);
			ColisionAll(730, 390, 10, 10);

			g.fillRect(610, 410, 10, 10);
			ColisionAll(610, 410, 10, 10);
			//g.fillRect(690, 410, 10, 10);
			//ColisionAll(690, 410, 10, 10);
			//g.fillRect(580, 410, 10, 10);
			//ColisionAll(580, 410, 10, 10);

			g.fillRect(670, 430, 10, 10);
			ColisionAll(670, 430, 10, 10);
//*Portal
			g.setColor(Color.cyan);
			g.fillOval(160, 370, 5, 10);
			g.fillOval(180-5, 370, 5, 10);
			g.fillOval(265, 390, 5, 10);
			g.fillOval(220, 430, 5, 10);
			g.fillOval(280, 430, 5, 10);

			g.fillOval(620, 370, 5, 10);
			g.fillOval(690, 370, 5, 10);
			g.fillOval(740-5, 390, 5, 10);
			g.fillOval(610, 410, 5, 10);
			g.fillOval(780, 430, 5, 10);
			
			
//***Goal Level 49
			g.setColor(color);
			g.fillRect(410, 160, 80, 10);
			ColisionALL(410, 160, 80, 10);
			g.setColor(Color.yellow);
			g.fillRect(435, 160, 30, 3);
			if (Sustain(435, 160, 30, 3) && SLight<1500) SLight+=2;
			g.setColor(color);
			for(int i=0; i<2; i++){
				g.fillRect(400+i*55, 180, 45, 10);
				ColisionALL(400+i*55, 180, 45, 10);
				}
			for(int i=0; i<7; i++){
				if (i%2==0){
					g.fillRect(400, 200+i*20, 90, 10);
					ColisionALL(400, 200+i*20, 90, 10);
					}
				else{
					g.fillRect(410, 200+i*20, 90, 10);
					ColisionALL(410, 200+i*20, 90, 10);
					}
				}

			g.fillRect(435, 130, 30, 10);
			ColisionALL(435, 130, 30, 10);
			g.setColor(Color.black);
			if (y==130 && SLight<1000 && Tab[0]==13) SLight+=2;
			g.fillRect(440, 80, 20, 50);
			if (x==440 && y==130){
				Level=Pan.Level=50;
				initP=0;
				LevelColor=1;
				}
			//g.fillRect(100, 0, 700, 800);
			//SLight=1000;
			int LeftSide=0;
			int RightSide=900;
			int UpSide=0;
			int DownSide=800;
			g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
			if (y+SLight<=DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
				g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
				}
			else if (y+SLight>DownSide){
				g.fillRect(LeftSide, UpSide, x-SLight, y);
				g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
				g.fillRect(LeftSide, y, x-SLight, DownSide-y);
				g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
				}

			if (Tab[1]==1){
				g.setColor(Color.cyan);
				g.fillRect(432, 787, 8, 8);
				g.fillRect(400, 60, 15, 10);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==30) Time=Tab[1]=0;
				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 10));
				if (Time<=20) g.drawString(String.valueOf(30-Time), 402, 68);
				else g.drawString(String.valueOf(30-Time), 405, 68);
				}
			if (Tab[2]==1){
				g.setColor(Color.cyan);
				g.fillRect(460, 787, 8, 8);
				g.fillRect(485, 60, 15, 10);
				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==30) Time=Tab[2]=0;
				g.setColor(Color.black);
				g.setFont(new Font("Arial", Font.BOLD, 10));
				if (Time<=20) g.drawString(String.valueOf(30-Time), 487, 68);
				else g.drawString(String.valueOf(30-Time), 490, 68);
				}

			for(int i=0; i<2; i++){
				for(int j=0; j<2; j++) WallStickAll(110+j*677, 340+i*110, 3, 10);
				}
			for(int i=0; i<2; i++) WallStickAll(245+i*23-3, 580, 3, 10);
			WallStickAll(120, 510, 3, 10);
			WallStickAll(370-3, 510, 3, 20);
			WallStickAll(290, 580, 15, 3);
			WallStickAll(530, 530, 3, 10);
			for(int i=0; i<2; i++) WallStickAll(635-3+i*23, 620, 3, 10);

			}
		}
//**************************************************************************************************	
	public void paintChildren(Graphics g){
	
//***## Niveau 50, refaire les niveaux finaux (10, 20, 30, 40) + niveau 50 ?
//**Plateforme apparaissant l'une après l'autre
//**##Niveau 50, BOSS de fin ? Abilité à grossir et rétrécir
//SI REDDEAD RECOMMENCE JUSTE LA PHASE, PAS TOUT LE NIVEAU ?
//ONWORK
		if (Level==50){
			if (initP==0){
				x=440;
				y=130;
				Time=TimeOn=Time2=TimeOn2=Time3=TimeOn3=0;
				//LevelColor=1;
				ResetTab();
				/*
				* 0 is phase of level
				* 1 is button 1
				* 2 is button 2
				* 3 is button 3
				* 4 is gray button 4
				* 5 is gray button 5
				* 6 is gray button 6
				* 7 is gray button 7
				* 8 is color of upper goal wall resistance (black to white by 50)
				* -----
				* 9 
				* 10 is gray button counter
				* 11 is y move of rainbow vertical line blocks(Phase 0)
				* 12 is x move of rainbow horizontal line blocks(Phase 0)
				* 13 is is x difference on raibow horizontal line blocks (Phase 0)
				* 14 is no colision rainbow blocks counter (Phase 0)
				* 
				* 
				* 
				*/
				Tab[0]=0;
				Tab[8]=0;
				Size=10;
				initP=1;

							//Tab[10]=0;//Addition to blocks
							//Tab[11]=0;//Trigger to move blocks
							//Tab[12]=650;//y
							//Tab[13]=370;//y
							//Tab[14]=440;//x
							//Tab[15]=245;//y
							//Tab[16]=305;//y
							//Tab[17]=440;//x
				}
			ColisionAll(0, -100, 10, 100);
			ColisionAll(890, -100, 10, 100);
			Color TabColor [] = new Color[7];
			TabColor[0]=Color.magenta;
			TabColor[1]=Color.blue;
			TabColor[2]=Color.cyan;
			TabColor[3]=Color.green;
			TabColor[4]=Color.yellow;
			TabColor[5]=Color.pink;
			TabColor[6]=Color.red;
			Tab[10]=Tab[1]+Tab[2]+Tab[3]+Tab[4]+Tab[5]+Tab[6]+Tab[7];
			//Tab[10]=7;
//start
//Phase 0
			if (Tab[0]==0){
				g.setColor(Color.gray);
				g.fillRect(420, 0, 10, 375);
				ColisionAll(420, 0, 10, 375);
				g.fillRect(470, 0, 10, 375);
				ColisionAll(470, 0, 10, 375);

				g.fillRect(440, 200, 20, 10);
				ColisionALL(440, 200, 20, 10);

				if (y==375) Tab[0]=1;
				}
			else if (Tab[0]==1){
				for(int i=Tab[10]; i<7; i++){
					g.setColor(TabColor[i]);
					for(int j=0; j<2; j++){
						if (-2+800-i*118-Tab[11]>-12){
							if (i==6) ColisionRed(55+j*780, -2+800-i*118-Tab[11], 10, 10);
							g.fillRect(55+j*780, -2+800-i*118-Tab[11], 10, 10);
							ColisionALL(55+j*780, -2+800-i*118-Tab[11], 10, 10);
							if (Colision(55+j*780, -2+800-i*118-Tab[11], 10, 10)) y-=1;
							if (i==3) WallStickAll(55+j*780-1, -2+800-i*118-Tab[11], 10+1, 10);
							}
						else{
							if (i==6) ColisionRed(55+j*780, -2+800-i*118-Tab[11]+810, 10, 10);
							g.fillRect(55+j*780, -2+800-i*118-Tab[11]+810, 10, 10);
							ColisionALL(55+j*780, -2+800-i*118-Tab[11]+810, 10, 10);
							if (Colision(55+j*780, -2+800-i*118-Tab[11]+810, 10, 10)) y-=1;
							if (i==3) WallStickAll(55+j*780-1, -2+800-i*118-Tab[11]+810, 10+1, 10);
							}

						if (i*127+Tab[12]<890){
							if (i==6) ColisionRed(i*127+Tab[12], 600-1-j*450, 10, 10);
							g.fillRect(i*127+Tab[12], 600-j*450, 10, 10);
							ColisionALL(i*127+Tab[12], 600-j*450, 10, 10);
							//if (Colision(i*127+Tab[12], 600-1-j*450, 10, 10) && MotionR==0 && MotionL==0) x=i*127+Tab[12];
							if (i==3) WallStickAll(i*127+Tab[12], 600-1-j*450, 10, 10);
							if (Colision(i*127+Tab[12], 600-1-j*450, 10, 10)){
								if (Tab[13]==0) Tab[13]=x-(i*127+Tab[12]);
								x=i*127+Tab[12]+Tab[13];
								if (MotionR==1) Tab[13]+=2;
								if (MotionL==1) Tab[13]-=1;
								}
							}
						else{
							if (i==6) ColisionRed(i*127+Tab[12]-890, 600-1-j*450, 10, 10);
							g.fillRect(i*127+Tab[12]-890, 600-j*450, 10, 10);
							ColisionALL(i*127+Tab[12]-890, 600-j*450, 10, 10);
							//if (Colision(i*127+Tab[12]-890, 600-1-j*450, 10, 3) && MotionR==0 && MotionL==0) x=i*127+Tab[12]-890;
							if (i==3) WallStickAll(i*127+Tab[12]-890, 600-1-j*450, 10, 10);
							if (Colision(i*127+Tab[12]-890, 600-1-j*450, 10, 10)){
								if (Tab[13]==0) Tab[13]=x-(i*127+Tab[12]-890);
								x=i*127+Tab[12]-890+Tab[13];
								if (MotionR==1) Tab[13]+=2;
								if (MotionL==1) Tab[13]-=1;
								}
							}
						if (i*127+Tab[12]<890     && 1==0){
							g.fillRect(i*127+Tab[12], 150, 10, 10);
							ColisionALL(i*127+Tab[12], 150, 10, 10);
							//if (Colision(i*127+Tab[12], 150-1, 10, 3) && MotionR==0 && MotionL==0) x=i*127+Tab[12];
							if (Colision(i*127+Tab[12], 150-1, 10, 10)){
								if (Tab[13]==0) Tab[13]=i*127+Tab[12]-x;
								x=i*127+Tab[12]+Tab[13];
								if (MotionR==1) Tab[13]+=1;
								if (MotionL==1) Tab[13]-=1;
								}
							}
						else if (      2==0) {
							g.fillRect(i*127+Tab[12]-890, 150, 10, 10);
							ColisionALL(i*127+Tab[12]-890, 150, 10, 10);
							//if (Colision(i*127+Tab[12]-890, 150-1, 10, 3) && MotionR==0 && MotionL==0) x=i*127+Tab[12]-890;
							if (Colision(i*127+Tab[12]-890, 150-1, 10, 10)){
								if (Tab[13]==0) Tab[13]=i*127+Tab[12]-890-x;
								x=i*127+Tab[12]-890+Tab[13];
								if (MotionR==1) Tab[13]+=1;
								if (MotionL==1) Tab[13]-=1;
								}
							}
						}
					}
				for(int i=0; i<7; i++){
					for(int j=0; j<2; j++){
						if (i*127+Tab[12]<890){
							if (Colision(i*127+Tab[12], 150-1+j*450, 10, 10)==false) Tab[14]+=1;
							}
						else{
							if (Colision(i*127+Tab[12]-890, 150-1+j*450, 10, 10)==false) Tab[14]+=1;
							}
						if (i*127+Tab[12]<890){
							//if (Colision(i*127+Tab[12], 600-1, 10, 10)==false) Tab[14]+=1;
							}
						else{
							//if (Colision(i*127+Tab[12]-890, 600-1, 10, 10)==false) Tab[14]+=1;
							}
						}
					}
				if (Tab[14]>=14) Tab[13]=0;
				Tab[11]+=1;
				if (Tab[11]==810) Tab[11]=0;
				Tab[12]+=1;
				if (Tab[12]==890) Tab[12]=0;
				Tab[14]=0;

				g.setColor(Color.gray);
				if (Tab[1]==0){
					g.fillRect(447, 497+5, 6, 6);
					if (Colision(447, 497+5, 6, 6)) Tab[1]=1;
					}
				if (Tab[2]==0){
					g.fillRect(707, 377, 6, 6);
					if (Colision(707, 377, 6, 6)) Tab[2]=1;
					}
				if (Tab[3]==0){
					g.fillRect(197, 252, 6, 6);
					if (Colision(197, 252, 6, 6)) Tab[3]=1;
					}
				if (Tab[4]==0){
					g.fillRect(22, 312, 6, 6);
					if (Colision(22, 312, 6, 6)) Tab[4]=1;
					}
				if (Tab[5]==0){
					g.fillRect(867, 657, 6, 6);
					if (Colision(867, 657, 6, 6)) Tab[5]=1;
					}
				if (Tab[6]==0){
					g.fillRect(447, 57, 6, 6);
					if (Colision(447, 57, 6, 6)) Tab[6]=1;
					}
				if (Tab[7]==0){
					g.fillRect(447, 687, 6, 6);
					if (Colision(447, 687, 6, 6)) Tab[7]=1;
					}

				g.setColor(Color.black);
				g.fillRect(440, 495, 20, 10);
				ColisionALL(440, 495, 20, 10);
				
				g.fillRect(200, 245, 10, 20);
				ColisionALL(200, 245, 10, 20);

				g.fillRect(700, 370, 10, 20);
				ColisionALL(700, 370, 10, 20);

				g.fillRect(440, 50, 20, 10);
				ColisionALL(440, 50, 20, 10);

				g.fillRect(15, 305, 10, 20);
				ColisionALL(15, 305, 10, 20);
			
				g.fillRect(870, 650, 10, 20);
				ColisionALL(870, 650, 10, 20);

				g.fillRect(440, 690, 20, 10);
				ColisionALL(440, 690, 20, 10);


				g.fillRect(400, 790, 100, 10);
				ColisionALL(400, 790, 100, 10);
				g.fillRect(110, 700, 680, 10);
				ColisionALL(110, 700, 680, 10);
				for(int i=0; i<7; i++){
					g.fillRect(412+i*11, 710, 10, 10);
					ColisionAll(412+i*11, 710, 10, 10);
					g.fillRect(350-i*40, 790, 10, 10);
					ColisionALL(350-i*40, 790, 10, 10);
					g.fillRect(540+i*40, 790, 10, 10);
					ColisionALL(540+i*40, 790, 10, 10);
					}
				//Tab[10]=Tab[1]+Tab[2]+Tab[3]+Tab[4]+Tab[5]+Tab[6]+Tab[7];
				for(int i=Tab[10]; i<7; i++){
					//if (Tab[1+i]==1) g.setColor(TabColor[i]);
					g.setColor(Color.gray);
					g.fillArc(411+i*11, 717, 11, 5, 180, 180);
					ColisionAll(412+i*11, 717, 11, 5);
					}
				for(int i=0; i<Tab[10]; i++){
					g.setColor(TabColor[i]);
					g.fillArc(411+i*11, 717, 11, 5, 180, 180);
					ColisionAll(412+i*11, 717, 11, 5);
					g.drawLine(417+i*11, 720, 450, 790);
					}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(TabColor[i]);
					g.fillRect(110+i*40, 710, 10, 80);
					ColisionAll(110+i*40, 710, 10, 80);
					g.fillRect(780-i*40, 710, 10, 80);
					ColisionAll(780-i*40, 710, 10, 80);
					}
				if (Tab[10]==7){
					g.setColor(new Color(255-5*Size, 255-5*Size, 255-5*Size));
					g.fillRect(445, 790, 10, 10);
					if (((445<=x && x<=450) || (450<=x+Size && x+Size<=455)) && y==790) Tab[21]=1;
					if (Tab[21]==1){
						x=445;
						Motion=0;
						if (TimeOn3==0){
							TimeOn3=1;
							new Time3().start();
							Size+=1;							
							}
						if (Size==50){
							Tab[0]=2;
							Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
							Motion=1;
							Time3=0;

							Tab[9]=0;//Accumulation of sin moving block
							Tab[11]=0;//Trigger to move blocks
							Tab[12]=650;//y
							Tab[13]=370;//y
							Tab[14]=440;//x
							Tab[15]=245;//y
							Tab[16]=305;//y
							Tab[17]=440;//x
							Tab[18]=0;//x difference on Tab[14] sin moving block
							Tab[19]=0;//Addition to blocks
							Tab[0]=3;
							Tab[10]=0;
							}
						}
					}	
				}
//****Phase 1 End
			else if (Tab[0]==2){
				Tab[9]=0;//Accumulation of sin moving block
				Tab[10]=0;
				Tab[11]=0;//Trigger to move blocks
				Tab[12]=650;//y
				Tab[13]=370;//y
				Tab[14]=440;//x
				Tab[15]=245;//y
				Tab[16]=90;//y
				Tab[17]=440;//x
				Tab[18]=0;//x difference on Tab[14] sin moving block
				Tab[19]=0;//Addition to blocks
				Tab[20]=0;//transformation button lock
				Tab[0]=3;
				}
//****Phase 3
			else if (Tab[0]==3){
				g.setColor(Color.black);
				g.fillRect(Tab[14], 490, 20, 10);
				ColisionALL(Tab[14], 490, 20, 10);
				Tab[14]=440+(int)(200*Math.sin((3.14*Tab[9])/400));
				Tab[17]=175+60+(int)(175*Math.cos((3.14*Tab[9])/400));
				if (Tab[11]!=2) Tab[9]+=1;
				if (Sustain(Tab[14], 490, 20, 10)){
					if (Tab[18]==0) Tab[18]=x-Tab[14];
					x=440+(int)(200*Math.sin((3.14*Tab[9])/400))+Tab[18];
					if (MotionL==1) Tab[18]-=1;
					if (MotionR==1) Tab[18]+=1;
					}
				else if (Sustain(Tab[17], 50, 20, 10)){
					//if (Tab[11]==0) x+=1;
					//if (Tab[11]==1) x-=1;
					if (Tab[18]==0) Tab[18]=x-Tab[17];
					x=175+60+(int)(175*Math.cos((3.14*Tab[9])/400))+Tab[18];
					if (MotionL==1) Tab[18]-=1;
					if (MotionR==1) Tab[18]+=1;
					}
				else Tab[18]=0;
				
				g.fillRect(200, Tab[15], 10, 20);
				ColisionALL(200, Tab[15], 10, 20);
				if (Tab[19]<=200){
					Tab[15]=245+Tab[19];
					if (Sustain(200, Tab[15], 10, 20)) y-=1;
					}

				g.fillRect(700, Tab[13], 10, 20);
				ColisionALL(700, Tab[13], 10, 20);
				if (Tab[19]<=300){
					Tab[13]=370+Tab[19];
					if (Sustain(700, Tab[13], 10, 20)) y-=1;
					}

				g.fillRect(Tab[17], 50, 20, 10);
				ColisionALL(Tab[17], 50, 20, 10);
				//Tab[17]=60+Tab[19];

				g.fillRect(15, Tab[16], 10, 20);
				ColisionALL(15, Tab[16], 10, 20);
				if (Tab[19]<=340){
					Tab[16]=395-Tab[19];
					if (Sustain(15, Tab[16], 10, 20)) y-=1;
					}
			
				g.fillRect(870, Tab[12], 10, 20);
				ColisionALL(870, Tab[12], 10, 20);
				if (Tab[12]<780) Tab[12]+=1;

				if (Tab[11]==0) Tab[19]+=1;
				if (Tab[11]==1) Tab[19]-=1;
				if (Tab[19]==0) Tab[11]=0;
				if (Tab[19]==340) Tab[11]=1;
				//if (info==1) System.out.println(Tab[15]);
				

				g.fillRect(440, 690, 20, 10);
				ColisionALL(440, 690, 20, 10);
				g.fillRect(400, 790, 100, 10);
				ColisionALL(400, 790, 100, 10);
				g.fillRect(110, 700, 680, 10);
				ColisionALL(110, 700, 680, 10);
				for(int i=0; i<7; i++){
					g.fillRect(412+i*11, 710, 10, 10);
					ColisionAll(412+i*11, 710, 10, 10);
					g.fillRect(350-i*40, 790, 10, 10);
					ColisionALL(350-i*40, 790, 10, 10);
					g.fillRect(540+i*40, 790, 10, 10);
					ColisionALL(540+i*40, 790, 10, 10);
					}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(Color.gray);
					g.fillArc(411+i*11, 717, 11, 5, 180, 180);
					ColisionAll(412+i*11, 717, 11, 5);
					}

				g.fillRect(420, 110, 5, 265);
				ColisionALL(420, 110, 5, 265);
				g.fillRect(475, 110, 5, 265);
				ColisionALL(475+1, 110, 5-1, 265);

				//if (425<=x && x+Size<=475 && 110<=y && y<375) y-=2;
				if ((425<=x && x<=476 && y==375) || Size<=49) Tab[20]=1;
				if (Tab[20]==1){
					Tab[8]=50;
					Tab[11]=2;
					Motion=0;
					if (TimeOn3==0){
						TimeOn3=1;
						new Time3().start();
						Size-=1;							
						}
					if (Size==10){
						Tab[0]=4;
						Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
						Motion=1;
						Time3=0;
						}
					//System.out.println("b");
					}
				}
//****Phase 3 End
			else if (Tab[0]==4){
				Tab[20]=0; //up x circle
				Tab[21]=0; //up y circle
				Tab[22]=0; //up x circle entraînant
				Tab[23]=0; //up y circle entraînant
				Tab[24]=0; //up circle up move
				Tab[25]=0; //up circle up no touch counter
				Tab[26]=0; //up left block trigger
				Tab[27]=10; //up left block x move
				Tab[28]=0;//transformation button lock
				
				Tab[0]=5;
				}
//****Phase 5	
			else if (Tab[0]==5){
				
				g.setColor(Color.black);
				g.fillRect(865, 20, 10, 770);
				ColisionALL(865, 20, 10, 770);
				g.fillRect(878, 0, 10, 10);
				ColisionALL(878, 0, 10, 10);

				g.fillRect(120, 435, 300, 10);
				ColisionALL(120, 435, 300, 10);

				g.fillRect(420, 0, 10, 100);
				ColisionALL(420, 0, 10, 100);
				g.fillRect(420, 200, 10, 105);
				ColisionALL(420, 200, 10, 105);

				if (10<=x && x+Size<=420 && 285<=y-Size && y<=306){
					for(int i=0; i<10; i++){
						g.fillRect(10+i*40, 285, 30, 10);
						}
					}
				else{
					g.fillRect(10, 285, 10, 10);
					ColisionALL(10, 285, 10, 10);
					g.fillRect(30, 285, 390, 10);
					ColisionALL(30, 285, 390, 10);
					}

				g.fillRect(Tab[27], -10, 10, 10);
				ColisionAll(Tab[27], -10, 10, 10);
				
				if (Tab[26]==0) Tab[27]+=1;
				if (Tab[26]==1) Tab[27]-=1;
				if (Tab[27]+10==420) Tab[26]=1;
				if (Tab[27]==10) Tab[26]=0;

				g.setColor(Color.orange);
				if (10<=x && x+Size<=420 && 285<=y-Size && y<=306){
					g.setColor(Color.red);
					for(int i=0; i<10; i++){
						if (10+i*40<=Tab[27]+5 && Tab[27]+5<=10+i*40+30) g.drawLine(Tab[27]+5, 0, Tab[27]+5, 285);
						else if (40+i*40<=Tab[27]+5 && Tab[27]+5<=40+i*40+10){
							g.drawLine(Tab[27]+5, 0, Tab[27]+5, 305);
							if (LineColision(Tab[27]+5, 0, Tab[27]+5, 305)) initP=0;
							}
						}
					}
				else{
					if ((10<=Tab[27]+5 && Tab[27]+5<=20) || Tab[27]+5>=30){
						g.drawLine(Tab[27]+5, 0, Tab[27]+5, 285);
						if (LineColision(Tab[27]+5, 0, Tab[27]+5, 285)) y-=2;
						}
					else{
						g.drawLine(Tab[27]+5, 0, Tab[27]+5, 305);
						if (LineColision(Tab[27]+5, 0, Tab[27]+5, 305)) y-=2;
						}
					}						
				g.setColor(Color.orange);
				g.drawLine(883, 10, 883, 799);
				if (LineColision(883, 10, 883, 799)) y-=2;

				g.drawLine(430, 305, 865-1, 305);
				if (LineColision(430, 305, 863, 305) && Down==0) y=305+Size-1;
		

				//for(int i=Tab[10]; i<7; i++){
				//for(int i=6-Tab[10]; i>-1; i--){
				for(int i=6; i>=Tab[10]; i--){
					//if (i==6 && Colision(Tab[20], Tab[21], 20, 10)) initP=0;
					g.setColor(TabColor[i]);
					Tab[22]=637+(int)(140*Math.cos((3.14*Tab[24])/1000));
					Tab[23]=150+(int)(70*Math.sin((3.14*Tab[24])/300));
					Tab[20]=Tab[22]+(int)(70*Math.cos((3.14*(Tab[24]+200*i))/700));
					Tab[21]=Tab[23]+(int)(70*Math.sin((3.14*(Tab[24]+200*i))/700));
					//Tab[20]=775+(int)(70*Math.cos((3.14*(Tab[24]+200*i))/700));
					//Tab[21]=90+(int)(70*Math.sin((3.14*(Tab[24]+200*i))/700));

					g.fillRect(Tab[20], Tab[21], 20, 10);
					//ColisionALL(Tab[20], Tab[21], 20, 10);
					if (Colision(Tab[20], Tab[21], 20, 10)){
						if (Tab[18]==0) Tab[18]=x-Tab[20];
						x=Tab[22]+(int)(70*Math.cos((3.14*(Tab[24]+200*i))/700))+Tab[18];
						y=Tab[23]+(int)(70*Math.sin((3.14*(Tab[24]+200*i))/700));
						if (MotionR==1) Tab[18]+=1;
						if (MotionL==1) Tab[18]-=1;
						ColisionALL(865, 20, 10, 770);
						}
					}
				//for(int i=Tab[10]; i<7; i++){
				//for(int i=6-Tab[10]; i>-1; i--){
				for(int i=6; i>=Tab[10]; i--){
					//Tab[20]=775+(int)(70*Math.cos((3.14*(Tab[24]+200*i))/700));
					//Tab[21]=90+(int)(70*Math.sin((3.14*(Tab[24]+200*i))/700));
					if (Colision(Tab[20]-1, Tab[21]-1, 20+1, 10+1)==false) Tab[25]+=1;
					else break;
					}
				if (Tab[25]==7-Tab[10]){
					Tab[18]=0;
					if (info==1) System.out.println("b"+"\t"+Tab[25]);
					}
				Tab[25]=0;
				Tab[24]+=1;

//**down level
				g.setColor(Color.black);
				g.fillRect(845, 315, 10, 475);
				ColisionALL(845, 315, 10, 475);

				g.fillRect(230, 660, 10, 10);
				ColisionALL(230, 660, 10, 10);

				g.fillRect(470, 445, 10, 155);
				ColisionAll(470, 445, 10, 155);

				g.setColor(Color.orange);
				g.drawLine(10, 790, 890, 790);
				if (LineColision(10, 790, 890, 790) && Down==0){
					y=790+Size-1;
					if (x+Size+2<890) x+=1;
					}

				for(int i=0; i<5; i++){
					g.setColor(TabColor[i]);
					if (i==4) g.setColor(Color.orange);
					if (Tab[10]>i) continue;
					g.drawLine(480, 445+i*35, 845-1, 480+i*35);
					if (LineOn(480, 445+i*35, 845-1, 480+i*35) && Down==0 && !LineOn(455, 640, 865, 270)){
						Slide(480, 445+i*35, 845-1, 480+i*35);
						x+=1;
						}
					}


				if (Tab[10]==0){
					g.setColor(Color.magenta);
					//g.drawLine(480, 375, 865, 20);
					//if (LineOn(480, 375, 865, 20) && x+Size<865 && Down==0){
						//Slide(480, 375, 865, 20);
						//x+=1;
						//}
					}
				if (Tab[10]<=1){
					g.setColor(Color.blue);
					}
				if (Tab[10]<=2){
					g.setColor(Color.cyan);
					g.drawLine(455, 640, 865, 270);
					if (LineOn(455, 640, 865, 270) && Down==0){
						Slide(455, 640, 865, 270);
						x+=1;
						}
					g.drawLine(450, 630, 845-1, 630);
					if (LineOn(450, 630, 845-1, 630) && !LineOn(455, 640, 865, 270)){
						x-=1;
						y=630+Size;
						}
					}
				if (Tab[10]<=3){
					g.setColor(Color.green);
					g.drawLine(10, 670, 835, 760);
					if (LineOn(10, 670, 835, 760) && Down==0 && !LineOn(10, 700, 845, 645) && !LineOn(10, 790, 420, 445)){
					//if (LineOn(10, 670, 835, 760) && Down==0 && !LineOn(10, 790, 420, 445)){
						Slide(10, 670, 835, 760);
						x+=2;
						}
					}
				if (Tab[10]<=4){
					g.setColor(Color.orange);
					g.drawLine(10, 700, 845, 645);
					//if (LineOn(10, 700, 845, 645) && Down==0 && !LineOn(10, 790, 420, 445) && !LineOn(10, 670, 835, 760)){
					if (LineOn(10, 700, 845, 645) && Down==0 && !LineOn(10, 790, 420, 445)){
						Slide(10, 700, 845, 645);
						x-=1;
						}
					}
				if (Tab[10]<=5){
					g.setColor(Color.pink);
					g.drawLine(10, 790, 420, 445);
					//if (LineOn(10, 790, 420, 445) && Down==0 && !LineOn(10, 670, 835, 760) && !LineOn(10, 700, 845, 645) && !LineOn(10, 645, 450, 445)){
					if (LineOn(10, 790, 420, 445) && Down==0){
						Slide(10, 790, 420, 445);
						x+=1;
						}
					g.drawLine(120, 445, 120, 645);
					if (LineColision(120, 445, 120, 650)) y-=2;
					}
				if (Tab[10]==6){
					g.setColor(Color.red);

					}



				if (Tab[10]<7) g.setColor(TabColor[Tab[10]]);
				else g.setColor(Color.red);
				g.drawLine(10, 645, 445, 645);
				if (Tab[10]<7) g.setColor(TabColor[6-Tab[10]]);
				else g.setColor(Color.magenta);
				if (6-Tab[10]==4) g.setColor(Color.orange);
				g.drawLine(455, 645, 845, 645);
				if (LineOn(10, 645, 445, 645) && x<445 && Down==0) y=645+Size;
				if (LineOn(455, 645, 845, 645) && x<845 && Down==0) y=645+Size;
				if (Tab[10]>=6) RedLineColision(10, 645, 445, 645);
				if (Tab[10]==0) RedLineColision(455, 645, 845, 645);

			
				g.fillRect(445, 640, 10, 10);
				ColisionALL(445, 640, 10, 10);
				//g.setColor(TabColor[6-Tab[10]]);
				//if (6-Tab[10]==4) g.setColor(Color.orange);
				g.drawLine(450, 445, 450, 640-1);
				if (LineColision(450, 445, 450, 640)){
					if (Tab[10]==0) RedLineColision(450, 445, 450, 640);
					else if (y-Size>445) y-=2;
					}

				for(int i=Tab[10]; i<7; i++){
					if (i==6 && Colision(110-i*10, 445+i*10, 10, 10)) initP=0;
					g.setColor(TabColor[i]);
					g.fillRect(110-i*10, 445+i*10, 10, 10);
					ColisionALL(110-i*10, 445+i*10, 10, 10);
					}

				g.setColor(Color.red);
				g.fillRect(835, 515, 10, 10);
				ColisionRed(835, 515, 10, 10);
				g.fillRect(835, 755, 10, 10);
				ColisionRed(835, 755, 10, 10);

				g.setColor(Color.gray);
				if (Tab[1]==0){
					g.fillRect(410, 417, 6, 6);
					if (Colision(410, 417, 6, 6)) Tab[1]=1;
					}
				if (Tab[2]==0){
					g.fillRect(435, 5, 6, 6);
					if (Colision(435, 5, 6, 6)) Tab[2]=1;
					}
				if (Tab[3]==0){
					g.fillRect(408, 297, 6, 6);
					if (Colision(408, 297, 6, 6)) Tab[3]=1;
					}
				if (Tab[4]==0){
					g.fillRect(15, 5, 6, 6);
					if (Colision(15, 5, 6, 6)) Tab[4]=1;
					}
				if (Tab[5]==0){
					g.fillRect(833, 775, 6, 6);
					if (Colision(833, 775, 6, 6)) Tab[5]=1;
					}
				if (Tab[6]==0){
					g.fillRect(15, 652, 6, 6);
					if (Colision(15, 652, 6, 6)) Tab[6]=1;
					}
				if (Tab[7]==0){
					g.fillRect(447, 451, 6, 6);
					if (Colision(447, 451, 6, 6)) Tab[7]=1;
					}

				g.setColor(Color.black);
				g.fillRect(80, 395, 340, 10);
				ColisionALL(80, 395, 340, 10);
				g.fillRect(10, 305, 420, 10);
				ColisionALL(10, 305, 420, 10);
				for(int i=0; i<7; i++){
					g.fillRect(20, 359+i*11, 10, 10);
					ColisionALL(20, 359+i*11, 10, 10);
					}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(Color.gray);
					g.fillArc(27, 359+i*11, 5, 10, 280, 180);
					ColisionALL(27, 359+i*11, 5, 10);
					}
				for(int i=0; i<Tab[10]; i++){
					g.setColor(TabColor[i]);
					g.fillArc(27, 359+i*11, 5, 10, 280, 180);
					ColisionALL(27, 359+i*11, 5, 10);
					g.drawLine(30, 362+i*11, 105, 395);
					}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(TabColor[i]);
					g.fillRect(410-i*40, 315, 10, 80);
					ColisionAll(410-i*40, 315, 10, 80);
					}
//*Size Up phase 5
				if (Tab[10]==7){
					g.setColor(new Color(255-5*Size, 255-5*Size, 255-5*Size));
					g.fillRect(100, 395, 10, 10);
					ColisionALL(100, 395, 10, 10);
					if (((100<=x && x<=105) || (105<=x+Size && x+Size<=110)) && y==395) Tab[28]=1;
					if (Tab[28]==1){
						x=100;
						Motion=0;
						if (TimeOn3==0){
							TimeOn3=1;
							new Time3().start();
							Size+=1;							
							}
						if (Size==50){
							Tab[0]=6;
							Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
							Motion=1;
							Time3=0;
							}
						}
					}
				}
//**Phase 6
			else if (Tab[0]==6){
				Tab[20]=0;//transformation size button lock
				Tab[0]=7;
				}
//**Phase 7

			else if (Tab[0]==7){
				g.setColor(Color.black);
				g.fillRect(865, 20, 10, 770);
				ColisionALL(865, 20, 10, 770);
				g.fillRect(878, 0, 10, 10);
				ColisionALL(878, 0, 10, 10);

				g.fillRect(120, 435, 300, 10);
				ColisionALL(120, 435, 300, 10);

				g.fillRect(420, 0, 5, 375);
				ColisionAll(420, 0-100, 5, 375+100);
				g.fillRect(475, 50, 5, 325);
				ColisionALL(475, 50, 5, 325);

				g.fillRect(845, 315, 10, 475);
				ColisionALL(845, 315, 10, 475);

				g.fillRect(230, 660, 10, 10);
				ColisionALL(230, 660, 10, 10);

				g.fillRect(470, 445, 10, 155);
				ColisionAll(470, 445, 10, 155);

				g.fillRect(355, 690, 10, 10);
				ColisionALL(355, 690, 10, 10);

				g.fillRect(480, 650, 10, 10);
				ColisionALL(480, 650, 10, 10);

				g.fillRect(680, 770, 10, 10);
				ColisionALL(680, 770, 10, 10);

				g.fillRect(485, 500, 10, 10);
				ColisionALL(485, 500, 10, 10);

				g.fillRect(590, 480, 10, 10);
				ColisionALL(590, 480, 10, 10);

				g.fillRect(855, 130, 10, 10);
				ColisionALL(855, 130, 10, 10);

				g.fillRect(715, 115, 10, 10);
				ColisionALL(715, 115, 10, 10);

				g.fillRect(560, 140, 10, 10);
				ColisionALL(560, 140, 10, 10);

				g.fillRect(485, 100, 10, 10);
				ColisionALL(485, 100, 10, 10);

				g.setColor(Color.orange);
				g.drawLine(475, 600, 475, 800);
				if (LineColision(475, 600, 475, 800)) y-=2;

				g.drawLine(845, 790, 480, 435);
				if (LineOn(845, 790, 480, 435) && Down==0){
					Slide(845, 790, 480, 435);
					x-=1;
					}
				g.drawLine(480, 380, 865-1, 20);
				if (LineOn(480, 380, 865, 20) && Down==0 && x+Size<865){
					Slide(480, 380, 865, 20);
					x+=1;
					}
				if (y==375 && 420<=x && x<=475) Tab[20]=1;
				if (Tab[20]==1){
					Tab[8]=100;
					Motion=0;
					if (TimeOn3==0){
						TimeOn3=1;
						new Time3().start();
						Size-=1;							
						}
					if (Size==10){
						Tab[0]=8;
						Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
						Motion=1;
						Time3=0;
						}
					}
				}
//**Phase 8
			else if (Tab[0]==8){

				Tab[0]=9;
				}
//**Phase 9
			else if (Tab[0]==9){
				g.setColor(Color.black);
				g.fillRect(480, 395, 340, 10);
				ColisionALL(480, 395, 340, 10);
				g.fillRect(480, 305, 410, 10);
				ColisionALL(480, 305, 410, 10);

				g.fillRect(10, 260, 240, 10);
				ColisionALL(10, 260, 240, 10);

				g.fillRect(10, 530, 240, 10);
				ColisionALL(10, 530, 240, 10);


				for(int i=0; i<13; i++){
					g.setColor(Color.black);
					g.fillRect(300, 40+i*60, 10, 10);
					ColisionALL(300, 40+i*60, 10, 10);
					//WallStickAll(300, 40+i*60, 5, 10);

					g.fillRect(370, 70+i*60, 10, 10);
					ColisionALL(370, 70+i*60, 10, 10);
					//WallStickAll(370-5, 70+i*60, 5, 10);
					g.setColor(Color.green);
					g.fillRect(300+8, 40+i*60, 2, 10);
					g.fillRect(370-2, 70+i*60, 2, 10);
					}


//*down right
				g.fillRect(625, 750, 5, 50);
				ColisionALL(625, 750, 5, 50);
				WallStickAll(625, 750, 5, 50);
				g.fillRect(660, 750, 5, 50);
				ColisionALL(660, 750, 5, 50);
				WallStickAll(660, 750, 5, 50);


				g.setColor(Color.black);
				for(int i=0; i<7; i++){
					g.fillRect(440+i*100, 590, 10, 10);
					ColisionALL(440+i*100, 590, 10, 10);
					if (i==4) break;
					g.fillRect(490+i*100, 500, 10, 10);
					ColisionALL(490+i*100, 500, 10, 10);
					}
					
				g.fillRect(490, 780, 10, 10);
				ColisionALL(490, 780, 10, 10);
				g.fillRect(790, 780, 10, 10);
				ColisionALL(790, 780, 10, 10);
				for(int i=Tab[10]; i<7; i++){
					g.setColor(TabColor[i]);
					if (i==0){
						g.fillRect(610, 405, 10, 10);
						ColisionALL(610, 405, 10, 10);
						}
					if (i==1){
						g.fillRect(620, 415, 10, 10);
						ColisionALL(620, 415, 10, 10);
						}
					if (i==2){
						g.fillRect(630, 425, 10, 10);
						ColisionALL(630, 425, 10, 10);
						}
					if (i==3){
						g.fillRect(640, 435, 10, 10);
						ColisionALL(640, 435, 10, 10);
						}
					if (i==4){
						g.fillRect(650, 425, 10, 10);
						ColisionALL(650, 425, 10, 10);
						}
					if (i==5){
						g.fillRect(660, 415, 10, 10);
						ColisionALL(660, 415, 10, 10);
						}
					if (i==6){
						g.fillRect(670, 405, 10, 10);
						ColisionALL(670, 405, 10, 10);
						}
					}
//*Up Right
				g.setColor(Color.black);
				g.fillRect(480, 2, 10, 303);
				ColisionALL(480, 2, 10, 303);
				
				g.fillRect(570, 230, 10, 10);
				ColisionALL(570, 230, 10, 10);

				g.fillRect(580, 130, 10, 10);
				ColisionALL(580, 130, 10, 10);

				g.fillRect(570, 30, 10, 10);
				ColisionALL(570, 30, 10, 10);

				g.fillRect(840, 130, 10, 10);
				ColisionALL(840, 130, 10, 10);

				g.fillRect(850, 230, 10, 10);
				ColisionALL(850, 230, 10, 10);

				g.fillRect(700, 260, 10, 10);
				ColisionALL(700, 260, 10, 10);

				g.setColor(Color.green);
				g.fillRect(480, 50, 2, 10);
				WallStickAll(480, 50, 2, 10);

				g.setColor(Color.red);
				g.fillRect(510, 0, 10, 290);
				ColisionRed(510, 0, 10, 290);
				g.fillRect(550, 295, 300, 10);
				ColisionRed(550, 295, 300, 10);

//*Left up box
				g.setColor(Color.black);
				//g.fillRect(26, 40, 10, 220);
				//ColisionALL(26, 40, 10, 220);

				for(int i=0; i<4; i++){
					g.fillRect(35+i*60, 50+i*50, 10, 210-i*50);
					ColisionALL(35+i*60, 50+i*50, 10, 210-i*50);
					}

//*Center box
				for(int i=0; i<4; i++){
					g.fillRect(35+i*60, 420, 10, 10);
					ColisionALL(35+i*60, 420, 10, 10);
					}				

//*Left down box
				for(int i=0; i<4; i++){
					g.setColor(Color.black);
					g.fillRect(35+i*60, 550, 10, 200-i*50);
					ColisionALL(35+i*60, 550, 10, 200-i*50);
					g.setColor(Color.green);
					g.fillRect(35+i*60, 745-i*50, 2, 5);
					g.fillRect(45+i*60-2, 745-i*50, 2, 5);
					if (i==3) break;
					g.fillRect(35+i*60, 665-i*50, 2, 5);
					g.fillRect(45+i*60-2, 665-i*50, 2, 5);
					}
				g.setColor(Color.green);
				g.fillRect(35, 205, 2, 5);
				g.fillRect(35, 105, 2, 5);
				
				g.fillRect(95, 190, 2, 5);
				g.fillRect(105-2, 190, 2, 5);

				g.fillRect(155, 190, 2, 5);
				g.fillRect(165-2, 190, 2, 5);

//*Buttons
				g.setColor(Color.gray);
				if (Tab[1]==0){
					g.fillRect(15, 545, 6, 6);
					if (Colision(15, 545, 6, 6)) Tab[1]=1;
					}
				if (Tab[2]==0){
					g.fillRect(15, 397, 6, 6);
					if (Colision(15, 397, 6, 6)) Tab[2]=1;
					}
				if (Tab[3]==0){
					g.fillRect(15, 249, 6, 6);
					if (Colision(15, 249, 6, 6)) Tab[3]=1;
					}
				if (Tab[4]==0){
					g.fillRect(642, 410, 6, 6);
					if (Colision(642, 410, 6, 6)) Tab[4]=1;
					}
				if (Tab[5]==0){
					g.fillRect(642, 780, 6, 6);
					if (Colision(642, 780, 6, 6)) Tab[5]=1;
					}
				if (Tab[6]==0){
					g.fillRect(495, 293, 6, 6);
					if (Colision(495, 293, 6, 6)) Tab[6]=1;
					}
				if (Tab[7]==0){
					g.fillRect(879, 293, 6, 6);
					if (Colision(879, 293, 6, 6)) Tab[7]=1;
					}
//*Size Up
				g.setColor(Color.black);
				for(int i=0; i<7; i++){
					g.fillRect(870, 359+i*11, 10, 10);
					ColisionALL(870, 359+i*11, 10, 10);
					}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(Color.gray);
					g.fillArc(870-2, 359+i*11, 5, 10, 90, 180);
					ColisionALL(870-2, 359+i*11, 5, 10);
					}
				for(int i=0; i<Tab[10]; i++){
					g.setColor(TabColor[i]);
					g.fillArc(870-2, 359+i*11, 5, 10, 90, 180);
					ColisionALL(870-2, 359+i*11, 5, 10);
					g.drawLine(870-2, 362+i*11, 795, 395);
					}
				//for(int i=1; i<=7; i++){
					//if (Tab[10]>=i) g.setColor(TabColor[i-1]);
					//g.fillArc(870-2, 359+(i-1)*11, 5, 10, 90, 180);
					//ColisionALL(870-2, 359+(i-1)*11, 5, 10);
					//g.drawLine(870-2, 362+(i-1)*11, 795, 395);
					//}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(TabColor[i]);
					g.fillRect(480+i*40, 315, 10, 80);
					ColisionAll(480+i*40, 315, 10, 80);
					}
				if (Tab[10]==7){
					g.setColor(new Color(255-5*Size, 255-5*Size, 255-5*Size));
					g.fillRect(790, 395, 10, 10);
					if (x==790 && y==395) Tab[40]=1;
					if (Tab[40]==1){
						x=790;
						Motion=0;
						if (TimeOn3==0){
							TimeOn3=1;
							new Time3().start();
							Size+=1;							
							}
						if (Size==50){
							Tab[0]=10;
							Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
							Motion=1;
							Time3=0;
							}
						}
					}
				}

//**Phase 10
			else if (Tab[0]==10){
				Tab[20]=0;//transformation lock
				Tab[0]=11;
				}
//**Phase 11
			else if (Tab[0]==11){
				g.setColor(Color.black);
				g.fillRect(480, 395, 340, 10);
				ColisionALL(480, 395, 340, 10);
				g.fillRect(480, 305, 410, 10);
				ColisionALL(480, 305, 410, 10);

				g.fillRect(10, 260, 240, 10);
				ColisionALL(10, 260, 240, 10);

				g.fillRect(10, 530, 240, 10);
				ColisionALL(10, 530, 240, 10);

				g.fillRect(475, 0, 5, 375);
				ColisionAll(475, 0-100, 5, 375+100);
				g.fillRect(420, 20, 5, 355);
				ColisionALL(420, 20, 5, 355);

				g.fillRect(520, 780, 10, 10);
				ColisionALL(520, 780, 10, 10);

				g.fillRect(420, 760, 10, 10);
				ColisionALL(420, 760, 10, 10);

				g.fillRect(370, 680, 10, 10);
				ColisionALL(370, 680, 10, 10);

				g.fillRect(340, 600, 10, 10);
				ColisionALL(340, 600, 10, 10);

				g.fillRect(130, 450, 10, 10);
				ColisionALL(130, 450, 10, 10);

				g.fillRect(210, 365, 10, 10);
				ColisionALL(210, 365, 10, 10);

				g.fillRect(385, 475, 10, 10);
				ColisionALL(385, 475, 10, 10);

				g.fillRect(130, 105, 10, 10);
				ColisionALL(130, 105, 10, 10);

				g.fillRect(210, 190, 10, 10);
				ColisionALL(210, 190, 10, 10);

				g.fillRect(160, 30, 10, 10);
				ColisionALL(160, 30, 10, 10);

				g.fillRect(400, 100, 10, 10);
				ColisionALL(400, 100, 10, 10);

				g.setColor(Color.green);
				g.fillRect(250-2, 530, 2, 10);
				WallStickAll(250-2, 530, 2, 10);
				g.fillRect(250-2, 260, 2, 10);
				WallStickAll(250-2, 260, 2, 10);

				g.fillRect(420, 370, 2, 5);
				g.fillRect(420, 270, 2, 5);
				g.fillRect(420, 220, 2, 5);

				if (y==375 && 420<=x && x<=475) Tab[20]=1;
				if (Tab[20]==1){
					Tab[8]=150;
					Motion=0;
					if (TimeOn3==0){
						TimeOn3=1;
						new Time3().start();
						Size-=1;							
						}
					if (Size==10){
						Tab[0]=12;
						Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
						Motion=1;
						Time3=0;
						}
					}

				}
//**Phase 12
			else if (Tab[0]==12){

				Tab[0]=13;
				}
//**Phase 13
			else if (Tab[0]==13){


//*Size Up phase 13
				g.setColor(Color.black);
				g.fillRect(390, 60, 120, 10);
				ColisionALL(390, 60, 120, 10);
				g.fillRect(390, 70, 10, 30);
				ColisionAll(390, 70, 10, 30);
				g.fillRect(500, 70, 10, 30);
				ColisionAll(500, 70, 10, 30);
				g.fillRect(10, 100, 880, 10);
				ColisionALL(10, 100, 880, 10);
				g.fillRect(10, 0, 880, 10);
				ColisionAll(10, 0, 880, 10);
				for(int i=0; i<7; i++){
					g.fillRect(412+i*11, 90, 10, 10);
					ColisionAll(412+i*11, 90, 10, 10);
					//g.fillRect(350-i*40, 10, 10, 10);
					//ColisionAll(350-i*40, 10, 10-100, 10+100);
					//g.fillRect(540+i*40, 10, 10, 10);
					//ColisionAll(540+i*40, 10, 10-100, 10+100);
					}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(Color.gray);
					//if (Tab[10]>=1) g.setColor(TabColor[Tab[10-1]]);
					g.fillArc(411+i*11, 805-717, 11, 5, 0, 180);
					ColisionAll(412+i*11, 805-717, 11, 5);
					}
				for(int i=0; i<Tab[10]; i++){
					g.setColor(TabColor[i]);
					g.fillArc(411+i*11, 805-717, 11, 5, 0, 180);
					ColisionAll(412+i*11, 805-717, 11, 5);
					g.drawLine(417+i*11, 808-720, 450, 60);
					}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(TabColor[i]);
					g.fillRect(110+i*40, 10, 10, 90);
					ColisionAll(110+i*40, 10, 10, 90);
					g.fillRect(780-i*40, 10, 10, 90);
					ColisionAll(780-i*40, 10, 10, 90);
					}

				if (Tab[10]==7){
					g.setColor(new Color(255-5*Size, 255-5*Size, 255-5*Size));
					g.fillRect(445, 60, 10, 10);
					ColisionALL(445, 60, 10, 10);
					if (((445<=x && x<=450) || (450<=x+Size && x+Size<=455)) && y==60) Tab[42]=1;
					if (Tab[42]==1){
						x=445;
						Motion=0;
						if (TimeOn3==0){
							TimeOn3=1;
							new Time3().start();
							Size+=1;							
							}
						if (Size==50){
							Tab[0]=14;
							Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
							Motion=1;
							Time3=0;
							}
						}
					}
//*level blocks
				g.setColor(Color.black);
				g.fillRect(420, 305, 60, 10);
				ColisionALL(420, 305, 60, 10);	

				g.fillRect(420, 210, 60, 10);
				ColisionALL(420, 210, 60, 10);
				for(int i=0; i<2; i++){
					g.fillRect(420+i*50, 110, 10, 130);
					ColisionAll(420+i*50, 110, 10, 130);
					g.fillRect(10, 375+i*60, 880, 10);
					ColisionALL(10, 375+i*60, 880, 10);
					}
				g.fillRect(430, 230, 40, 10);
				ColisionALL(430, 230, 40, 10);


				for(int i=1; i<10; i++){
					g.fillRect(45+i*80, 790, 10, 10);
					ColisionALL(45+i*80, 790, 10, 10);
					}
				g.fillRect(45, 750, 10, 50);
				ColisionALL(45, 750, 10, 50);
				g.fillRect(845, 750, 10, 50);
				ColisionALL(845, 750, 10, 50);

				g.setColor(Color.red);
				for(int i=0; i<11; i++){
					g.fillRect(45+i*80, 365, 10, 10);
					ColisionRed(45+i*80, 365, 10, 10);
					}
//*Portals
				if (Tab[10]>0){
					g.setColor(new Color(30+30*Tab[10], 30+30*Tab[10], 30+30*Tab[10]));
					g.fillOval(20, 380, 30, 5);
					if (Colision(20, 380, 30, 5+2) && Tab[10]==7) y=10+Size;
					g.fillOval(20, 5, 30, 5);

					g.fillOval(850, 380, 30, 5);
					if (Colision(850, 380, 30, 5+2) && Tab[10]==7) y=10+Size;
					g.fillOval(850, 5, 30, 5);
					}

					
				if (Tab[10]<1){
					g.setColor(Color.magenta);
					
					}
				if (Tab[10]<2){
					g.setColor(Color.blue);
					}
				if (Tab[10]<3){
					g.setColor(Color.cyan);
					}
				if (Tab[10]<4){
					g.setColor(Color.green);
					g.fillOval(435, 305, 30, 5);//#G1
					if (Colision(435, 305-1, 30, 5)) y=110+Size;
					g.fillOval(435, 105, 30, 5);//#G1
					}
				if (Tab[10]<5){
					g.setColor(Color.yellow);
					}
				if (Tab[10]<6){
					g.setColor(Color.pink);


					}
				g.setColor(Color.red);
					
				g.setColor(Color.orange);
				g.fillOval(430, 310, 10, 5);//#O1
				if (Colision(430, 310, 10, 5+2)){
					x=405;
					y=385+Size;
					}
				g.fillOval(460, 310, 10, 5);//#O2			
				if (Colision(460, 310, 10, 5+2)){
					x=490;
					y=385+Size;
					}
				g.fillOval(425, 220, 5, 10);//#O1
				if (Colision(425, 220, 5+1, 10)){
					x=405;
					y=385+Size;
					}
				g.fillOval(470, 220, 5, 10);//#O2
				if (Colision(470-1, 220, 5, 10)){
					x=490;
					y=385+Size;
					}

				g.fillOval(490, 380, 10, 5);//#O1
				g.fillOval(400, 380, 10, 5);//#O2

				g.fillOval(425, 125, 5, 30);
				if (Colision(425, 125, 5+1, 30)){
					x=405;
					y=385+Size;
					}				
				g.fillOval(470, 125, 5, 30);
				if (Colision(470-1, 125, 5, 30)){
					x=490;
					y=385+Size;
					}
				g.fillOval(50, 760, 5, 10);
				if (Colision(50, 760, 5+1, 10)){
					x=405;
					y=385+Size;
					}
				g.fillOval(845, 760, 5, 10);
				if (Colision(845-1, 760, 5, 10)){
					x=490;
					y=385+Size;
					}
				g.fillOval(445, 790, 10, 5);//#O3
				if (Colision(445, 790-1, 10, 5)) y=230;
				g.fillOval(445, 215, 10, 5);//#O3

				g.setColor(Color.black);
				for(int i=0; i<7; i++){
					g.fillRect(530+i*50, 425, 10, 10);
					ColisionALL(530+i*50, 425, 10, 10);
					g.fillRect(540+i*50, 385, 10, 10);
					ColisionAll(540+i*50, 385, 10, 10);

					g.fillRect(360-i*50, 425, 10, 10);
					ColisionALL(360-i*50, 425, 10, 10);
					g.fillRect(350-i*50, 385, 10, 10);
					ColisionAll(350-i*50, 385, 10, 10);
					}
				for(int i=Tab[10]; i<7; i++){
					g.setColor(TabColor[i]);
					g.fillOval(530+i*50, 425, 5, 10);
					if (Colision(530+i*50-1, 425, 5, 10) && i!=6){
						x=530+i*50;
						y=445+Size;
						}
					ColisionRed(830-1, 425, 5, 10);
					g.fillOval(545+i*50, 385, 5, 10);
					if (Colision(545+i*50, 385, 5+1, 10)){
						x=780-i*40;
						y=110+Size;
						if (i==6) initP=0;
						}
					g.fillOval(780-i*40, 105, 10, 5);
					g.fillOval(530+i*50, 440, 10, 5);

					g.fillOval(365-i*50, 425, 5, 10);
					if (Colision(365-i*50, 425, 5+1, 10) && i!=6){
						x=360-i*50;
						y=445+Size;
						}
					ColisionRed(70-5, 425, 5+1, 10);
					g.fillOval(350-i*50, 385, 5, 10);
					if (Colision(350-i*50-1, 385, 5, 10)){
						x=110+i*40;
						y=110+Size;
						if (i==6) initP=0;
						}
					g.fillOval(110+i*40, 105, 10, 5);
					g.fillOval(360-i*50, 440, 10, 5);
					}
//*Buttons
				g.setColor(Color.gray);
				if (Tab[1]==0){
					g.fillRect(447, 200, 6, 6);
					if (Colision(447, 200, 6, 6)) Tab[1]=1;
					}
				if (Tab[2]==0){
					g.fillRect(140, 210, 6, 6);
					if (Colision(140, 210, 6, 6)) Tab[2]=1;
					}
				if (Tab[3]==0){
					g.fillRect(447, 222, 6, 6);
					if (Colision(447, 222, 6, 6)) Tab[3]=1;
					}
				if (Tab[4]==0){
					g.fillRect(710, 335, 6, 6);
					if (Colision(710, 335, 6, 6)) Tab[4]=1;
					}
				if (Tab[5]==0){
					g.fillRect(85, 480, 6, 6);
					if (Colision(85, 480, 6, 6)) Tab[5]=1;
					}
				if (Tab[6]==0){
					g.fillRect(447, 750, 6, 6);
					if (Colision(447, 750, 6, 6)) Tab[6]=1;
					}
				if (Tab[7]==0){
					g.fillRect(690, 665, 6, 6);
					if (Colision(690, 665, 6, 6)) Tab[7]=1;
					}
				}
//*Phase 14
			else if (Tab[0]==14){
				Tab[0]=15;

				}

//*Phase 15
			else if (Tab[0]==15){
				g.setColor(Color.black);
				if (Tab[45]==0){
					g.fillRect(390, 60, 120, 10);
					ColisionALL(390, 60, 120, 10);
					g.fillRect(390, 70, 10, 30);
					ColisionAll(390, 70, 10, 30);
					g.fillRect(500, 70, 10, 30);
					ColisionAll(500, 70, 10, 30);
					g.fillRect(420, 210, 60, 10);
					ColisionALL(420, 210, 60, 10);
					g.fillRect(10, 100, 880, 10);
					ColisionALL(10, 100, 880, 10);
					}
				for(int i=0; i<2; i++){
					if (Tab[45]==0){
						g.fillRect(420+i*50, 110, 10, 100);
						ColisionAll(420+i*50, 110, 10, 100);
						}
					g.fillRect(10, 375+i*60, 880, 10); 
					ColisionALL(10, 375+i*60, 880, 10); 
					}
				g.fillRect(10, 0, 880, 10);
				ColisionALL(10, 0, 880, 10);


				for(int i=1; i<10; i++){
					g.fillRect(45+i*80, 790, 10, 10);
					ColisionALL(45+i*80, 790, 10, 10);
					}
				g.fillRect(45, 750, 10, 50);
				ColisionALL(45, 750, 10, 50);
				g.fillRect(845, 750, 10, 50);
				ColisionALL(845, 750, 10, 50);

				g.setColor(Color.cyan);
				g.fillOval(20, 5, 50, 5+2);
				if (Colision(20, 5, 50, 5)) y=445+Size;

				g.fillOval(20, 105, 50, 5);
				g.fillOval(20, 440, 50, 5);

				g.fillOval(830, 5, 50, 5);
				if (Colision(830, 5, 50, 5+2)) y=445+Size;

				g.fillOval(830, 105, 50, 5);
				g.fillOval(830, 440, 50, 5);

				if (Tab[45]==0){
					g.setColor(Color.orange);
					g.fillRect(447, 710, 6, 6);
					if (Colision(447, 710, 6, 6)) Tab[45]=1;
					}
				else{
					g.setColor(Color.black);
					g.fillRect(390, 10, 10, 90);
					ColisionAll(390, 10, 10, 90);
					g.fillRect(500, 10, 10, 90);
					ColisionAll(500, 10, 10, 90);
					for(int i=0; i<2; i++){
						ColisionALL(10, 375+i*60, 880, 10);
						g.fillRect(10+i*490, 100, 390, 10);
						ColisionALL(10+i*490, 100, 390, 10);
						}
					g.fillRect(420, 10, 5, 365);
					ColisionAll(420, 10, 5, 365);
					g.fillRect(480-5, 10, 5, 365);
					ColisionAll(480-5, 10, 5, 365);
					}
					g.setColor(Color.cyan);
					g.fillOval(55-5, 750, 5, 50);
					if (Colision(55-5, 750, 5+1, 50)){
						x=830;
						y=110+Size;
						}
					g.fillOval(845, 750, 5, 50);
					if (Colision(845-1, 750, 5, 50)){
						x=20;
						y=110+Size;
						}
					g.fillOval(365, 375, 50, 5);
					g.fillOval(485, 375, 50, 5);
					if (Colision(365, 375-1, 50, 5) || Colision(485, 375-1, 50, 5)){
						x=425;
						y=10+Size;
						}
					g.fillOval(425, 5, 50, 5);

				if (y==375 && 420<=x && x<=475) Tab[20]=1;
				if (Tab[20]==1){
					Tab[8]=200;
					Motion=0;
					if (TimeOn3==0){
						TimeOn3=1;
						new Time3().start();
						Size-=1;							
						}
					if (Size==10){
						Tab[0]=16;
						Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
						Motion=1;
						Time3=0;
						}
					}
				}
//*Phase 16
			else if (Tab[0]==16){
				x=430;
				y=375;
				ResetTab();
				Tab[8]=200;
				Tab[0]=17;
				Tab[19]=1;//
				Tab[20]=0;//type SLight
				
				}

//*Phase 17
			else if (Tab[0]==17){
				if (Tab[19]==0){
					if (SLight>50) SLight-=1;
					if (SLight==50) Tab[19]=1;
					}
				g.setColor(Color.black);
				if (Tab[19]==0){
					g.fillRect(420, 355, 60, 10);
					ColisionALL(420, 355, 60, 10);
					g.fillRect(420, 365, 10, 10);
					ColisionAll(420, 365, 10, 10);
					g.fillRect(470, 365, 10, 10);
					ColisionAll(470, 365, 10, 10);
					}




				for(int i=0; i<4; i++){
					for(int j=0; j<2; j++){
						g.fillRect(480+i*100, 380+j*60, 50, 10);
						ColisionALL(480+i*100, 380+j*60, 50, 10);
						g.fillRect(370-i*100, 380+j*60, 50, 10);
						ColisionALL(370-i*100, 380+j*60, 50, 10);
						}
					}
				for(int i=0; i<8; i++){
					for(int j=0; j<3; j++){
						if (i%5==3){
							g.setColor(Color.yellow);
							g.drawRect(10+i*110-1, 15+j*110-1, 50+1, 10+1);
							g.drawRect(10+i*110-1, 790-j*110-1, 50+1, 10+1);
							g.drawRect(65+i*110-1, 75+j*110-1, 50+1, 10+1);
							g.drawRect(65+i*110-1, 730-j*110-1, 50+1, 10+1);
							if (Colision(10+i*110, 15+j*110, 50, 10) && SLight<150) SLight+=1;
							else if (Colision(10+i*110, 790-j*110, 50, 10) && SLight<150) SLight+=1;
							else if (Colision(65+i*110, 75+j*110, 50, 10) && SLight<150) SLight+=1;
							else if (Colision(65+i*110, 730-j*110, 50, 10) && SLight<150) SLight+=1;
							//else Tab[20]=0;
							}
						g.setColor(Color.black);
						g.fillRect(10+i*110, 15+j*110, 50, 10);
						ColisionALL(10+i*110, 15+j*110, 50, 10);

						g.fillRect(10+i*110, 790-j*110, 50, 10);
						ColisionALL(10+i*110, 790-j*110, 50, 10);

						g.fillRect(65+i*110, 75+j*110, 50, 10);
						ColisionALL(65+i*110, 75+j*110, 50, 10);
						g.fillRect(65+i*110, 730-j*110, 50, 10);
						ColisionALL(65+i*110, 730-j*110, 50, 10);
						if (i%5==0){
							g.setColor(Color.red);
							g.drawRect(10+i*110-1, 15+j*110-1, 50+1, 10+1);
							g.drawRect(10+i*110-1, 790-j*110-1, 50+1, 10+1);
							g.drawRect(65+i*110-1, 75+j*110-1, 50+1, 10+1);
							g.drawRect(65+i*110-1, 730-j*110-1, 50+1, 10+1);
							if (Colision(10+i*110, 15+j*110-1, 50, 10)) SLight-=1;
							if (Colision(10+i*110, 790-j*110-1, 50, 10)) SLight-=1;
							if (Colision(65+i*110, 75+j*110-1, 50, 10)) SLight-=1;
							if (Colision(65+i*110, 730-j*110-1, 50, 10)) SLight-=1;
							//else Tab[20]=0;
							}
							
						}
					
					} 

//*Buttons
				if (Tab[1]==0){
					g.setColor(Color.magenta);
					g.fillRect(390, 415, 6, 6);
					if (Colision(390, 415, 6, 6)) Tab[1]=1;
					}
				if (Tab[2]==0){
					g.setColor(Color.blue);
					g.fillRect(740, 165, 6, 6);
					if (Colision(740, 165, 6, 6)) Tab[2]=1;
					}
				if (Tab[3]==0){
					g.setColor(Color.cyan);
					g.fillRect(195, 10, 6, 6);
					if (Colision(195, 10, 6, 6)) Tab[3]=1;
					}
				if (Tab[4]==0){
					g.setColor(Color.green);
					g.fillRect(30, 200, 6, 6);
					if (Colision(30, 200, 6, 6)) Tab[4]=1;
					}
				if (Tab[5]==0){
					g.setColor(Color.yellow);
					g.fillRect(795, 780, 6, 6);
					if (Colision(795, 780, 6, 6)) Tab[5]=1;
					}
				if (Tab[6]==0){
					g.setColor(Color.pink);
					g.fillRect(25, 415, 6, 6);
					if (Colision(25, 415, 6, 6)) Tab[6]=1;
					}
				if (Tab[7]==0){
					g.setColor(Color.red);
					g.fillRect(810, 415, 6, 6);
					if (Colision(810, 415, 6, 6)) Tab[7]=1;
					}





//*Size Up
				if (Tab[10]==7) Tab[49]=1;
				if (Tab[49]==1){
					Motion=0;
					if (TimeOn3==0){
						TimeOn3=1;
						new Time3().start();
						Size+=1;							
						}
					if (Size==50){
						Tab[0]=18;
						Tab[1]=Tab[2]=Tab[3]=Tab[4]=Tab[5]=Tab[6]=Tab[7]=0;
						Motion=1;
						Time3=0;
						}
						
					}


				//SLight=1000;
				int LeftSide=0;
				int RightSide=900;
				int UpSide=0;
				int DownSide=800;
				//g.setColor(Color.black);
				
				if (Tab[20]==0){
					//if (SLight<50) SLight+=1;
					//else if (SLight>50) SLight-=1;
					}
				else if (Tab[20]==1){
					SLight-=1;
					//g.setColor(Color.red);
					if (SLight==0) initP=0;
					}
				else if (Tab[20]==2 && SLight<150) SLight+=1;
				if (SLight==0) initP=0;
				g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
				if (y+SLight<=DownSide){
					g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
					g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
					g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
					}
				else if (y+SLight>DownSide){
					g.fillRect(LeftSide, UpSide, x-SLight, y);
					g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
					g.fillRect(LeftSide, y, x-SLight, DownSide-y);
					g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
					}

				}
//*Phase 18

			else if (Tab[0]==18){
				ResetTab();
				Tab[8]=200;
				Tab[0]=19;
				}
//*Phase 19
			else if (Tab[0]==19){
				g.setColor(Color.black);
				g.fillRect(420, 30, 5, 345);
				ColisionALL(420, 30, 5, 345);
				g.fillRect(475, 30, 5, 345);
				ColisionALL(475, 30, 5, 345);
				for(int i=0; i<4; i++){
					for(int j=0; j<2; j++){
						//g.fillRect(480+i*100+j*10, 380+j*60, 50, 10);
						//ColisionALL(480+i*100, 380+j*60, 50, 10);
						//g.fillRect(370-i*100-j*10, 380+j*60, 50, 10);
						//ColisionALL(370-i*100, 380+j*60, 50, 10);
						}
					g.fillRect(480+i*100, 410, 50, 10);
					ColisionALL(480+i*100, 410, 50, 10);
					g.fillRect(370-i*100, 410, 50, 10);
					ColisionALL(370-i*100, 410, 50, 10);
					}
				for(int i=0; i<8; i++){
					for(int j=0; j<3; j++){
						g.setColor(Color.black);
						if (i<3 || i>4){
							g.fillRect(10+i*110, 15+j*120, 50, 10);
							ColisionALL(10+i*110, 15+j*120, 50, 10);
							g.fillRect(65+i*110, 75+j*120, 50, 10);
							ColisionALL(65+i*110, 75+j*120, 50, 10);
							}
						g.fillRect(10+i*110, 790-j*120, 50, 10);
						ColisionALL(10+i*110, 790-j*120, 50, 10);
						g.fillRect(65+i*110, 730-j*120, 50, 10);
						ColisionALL(65+i*110, 730-j*120, 50, 10);
						}
					
					}
				//SLight=1000;
				int LeftSide=0;
				int RightSide=900;
				int UpSide=0;
				int DownSide=800;
				g.setColor(Color.black);
				//if (SLight>50) SLight-=1;
				//if (SLight<50) SLight+=1;
				g.fillRect(x-SLight, 0, 2*SLight+Size, y-Size-SLight);//#Up
				if (y+SLight<=DownSide){
					g.fillRect(LeftSide, UpSide, x-SLight, y+SLight);
					g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y+SLight);
					g.fillRect(LeftSide, y+SLight, RightSide, DownSide-(y+SLight));//#Down
					}
				else if (y+SLight>DownSide){
					g.fillRect(LeftSide, UpSide, x-SLight, y);
					g.fillRect(x+Size+SLight, UpSide, RightSide-(x+Size+SLight), y);
					g.fillRect(LeftSide, y, x-SLight, DownSide-y);
					g.fillRect(x+Size+SLight, y, RightSide-(x+Size+SLight), DownSide-y);
					}


//*PHASE 20
				if (420<=x && x<=470 && y==375){
					ResetTab();
					SLight=1000;
					Tab[0]=20;
					Tab[8]=250;
					Tab[14]=254;
					Tab[15]=254;
					Tab[16]=128;
					Tab[42]=5;
					Motion=0;
					ForceSustained=1;
					}
				}
			else if (Tab[0]==20){
				for(int i=0; i<49; i++){
					int I=i%7;
					g.setColor(TabColor[I]);
					g.drawOval(430-i*2-Tab[42], 385-i*2-Tab[42], 40+i*4+Tab[42]*2, 60+i*4+Tab[42]*2);
					}
				if (Tab[42]<1000) Tab[42]+=(int)(Tab[42]/5);
				else{
					Motion=1;
					ForceSustained=0;
					}
				g.setColor(Color.black);
				g.fillRect(420, 380, 60, 5);
				ColisionALL(420, 380, 60, 5);
				g.fillRect(0, 435, 900, 5);
				ColisionALL(0, 435, 900, 5);
				}

			if (Tab[8]<250){
				g.setColor(new Color(Tab[8], Tab[8], Tab[8]));
				for(int i=0; i<2; i++){
					g.fillRect(420, 375+i*60, 60, 10);
					ColisionALL(420, 375+i*60, 60, 10);
					g.fillRect(420+i*50, 385, 10, 50);
					ColisionAll(420+i*50, 385, 10, 50);
					}
				}
			g.setColor(Color.black);
			g.drawRect(440-1, 385-1, 20+1, 50+1);
			if (Tab[0]==20) g.setColor(new Color(Tab[15], Tab[16], 0));//orange
			else g.setColor(Color.orange);
			g.fillRect(440, 385, 20, 50);
			if (Tab[0]==20){
				if (Colision(440, 385, 20, 50)){
					Motion=0;
					g.fillRect(440-Time2, 385-Time2, 20+Time2*2, 50+Time2*2);
					if (TimeOn2==0){
						TimeOn2=1;
						new Time2().start();
						}
					}
				}
			
			g.setColor(Color.blue);
			if (Tab[0]==17 && Tab[10]>0) g.setColor(TabColor[Tab[10]-1]);
			else if (Tab[0]==20 && Time2>800){
				g.setColor(new Color(0,0,Tab[14]));
				if (Tab[14]>0) Tab[14]-=1;
				if (Tab[14]==0){
					if (Tab[15]>0) Tab[15]-=2;
					if (Tab[16]>0) Tab[16]-=1;
					}
				if (Tab[15]==0 && Tab[16]==0) Level=51;
				}
			g.fillRect(x, y, Size, -Size);




// DERNIERE PHASE, PHASE NOIR, PASSAGES DE COULEURS (Labyrinthe ?)

			}//FIN LEVEL 50 *************************************************

		//if (info==1) System.out.println(Measurement);
		if (Measurement==1){
			g.setColor(Color.red);
			g.drawLine(MeasurementX1, MeasurementY1, MeasurementX2, MeasurementY2);
			int Measure = Norme(MeasurementX2-MeasurementX1, MeasurementY2-MeasurementY1);
			if (MeasurementY2-10<=0) g.drawString(String.valueOf(Measure), MeasurementX2-20, MeasurementY2+20);
			else g.drawString(String.valueOf(Measure), MeasurementX2-20, MeasurementY2-10);
			}
		//if (Level>40 && Level!=50){
		if (40<Level && Level<50){
			g.setColor(Color.blue);
			g.fillRect(x, y, 10, -10);

			if (1==0){
				if (LevelColor==0) g.setColor(Color.black);
				else g.setColor(Color.red);
				g.fillRect(0, 8*this.getHeight()/9, this.getWidth(), this.getHeight()/9);
				g.setColor(Color.white);
				g.drawString("level "+String.valueOf(Level), 410, 860);
				g.setColor(Color.red);
				g.fillRect(0, 0, 10+10*(50-Level), 800);
				if (Colision(0, 0, 10+10*(50-Level), 800)) initP=0;
				g.fillRect(900-(10+10*(50-Level)), 0, 10+10*(50-Level), 800);
				if (Colision(900-(10+10*(50-Level)), 0, 10+10*(50-Level), 800)) initP=0;
				}
			}
		try{
			Thread.sleep(1);
			} catch (InterruptedException e) {}
		}
//############################*********************************************************************
	public boolean Sustain(int X, int Y, int width, int height){	
		int Sx=0;
		int Sy=0;
		for(int i=x; i<=x+Size; i++){
			if (X<=i && i<=X+width){
				Sx=1;
				break;
				}
			}
		if (Y<=y && y<=Y+height) Sy=1;
		return Sx==1 && Sy==1;
		}
	public boolean Colision(int X, int Y, int width, int height){
		int Cx=0;
		int Cy=0;
		for(int i=x; i<=x+Size; i++){
			if (X<i && i<X+width){
				Cx=1;
				break;
				}
			}
		for(int i=y; i>y-Size; i--){
			if (Y<i && i<Y+height){
				Cy=1;
				break;
				}
			}
		return Cx==1 && Cy==1;
		}
	public void RedColision(int X, int Y, int width, int height){
		if (Colision(X,Y,width,height)) Pan.initP=Pan2.initP=0;
		}
	public void Jump(int Y){
		SautLock=1;
		y-=5;
		SautCooldown+=1;
		if (SautCooldown==(int)(Y/5)){
			Saut=0;
			SautCooldown=0;
			}
		}

	public boolean WallStick(int X, int Y, int width, int height){
		int Sx=0;
		int Sy=0;
		for(int i=y; i>=y-Size; i--){
			if (Y<=i && i<=Y+height){
				Sy=1;
				break;
				}
			}
		if ((X<=x && x<=X+width) || (X<=x+Size && x+Size<=X+width)) Sx=1;
		return Sx==1 && Sy==1;
		}
	public boolean LineOn(int Ax, int Ay, int Bx, int By){
		int b = -(Bx-Ax);
		int a = By-Ay;
		int c = -a*Ax-b*Ay;
		int xmax=0;
		int xmin=0;
		int On=0;
		if (Bx>Ax){
			xmax = Bx;
			xmin = Ax;
			}
		else if (Ax>Bx){
			xmax = Ax;
			xmin = Bx;
			}
		for(int i=xmin; i<xmax && b!=0; i++){
			int Y = (int)((-a*i-c)/b);
			if (i-1<=x && x<=i+1 && Y-1<=y-Size && y-Size<=Y+1) On=1;
			}
		return On==1 && Down==0;
		}

	public void Slide(int Ax, int Ay, int Bx, int By){
		int b = -(Bx-Ax);
		int a = By-Ay;
		int c = -(a*Ax+b*Ay);
		y = (int)((-a*x-c)/b)+Size;
		}
	public void RedLineColision(int Ax, int Ay, int Bx, int By){
		if (LineColision(Ax,Ay,Bx,By)) Pan.initP=Pan2.initP=0;
		}
	public boolean LineColision(int Ax, int Ay, int Bx, int By){
		int b = -(Bx-Ax);
		int a = By-Ay;
		int c = -a*Ax-b*Ay;
		int max=0;
		int min=0;
		int On=0;
		//int X,Y;
		int L=0;
		if (Bx>Ax){
			max = Bx;
			min = Ax;
			L=1;
			}
		else if (Ax>Bx){
			max = Ax;
			min = Bx;
			L=1;
			}
		else if (By>Ay){
			max = By;
			min = Ay;
			L=2;
			}
		else if (Ay>By){
			max = Ay;
			min = By;
			L=2;
			}
		for(int i=min; i<max && L==1; i++){
			for(int j=y; j>y-Size; j--){
				int Y = (int)((-a*i-c)/b);
				if (i-1<=x && x<=i+1 && Y-1<=j && j<=Y+1) On=1;
				}
			}
		for(int i=min; i<max && L==2; i++){
			for(int j=x; j<=x+Size; j++){
				int X = (int)((-c)/a);
				if (X<=j && j<=X && i-1<=y && y-Size<=i+1) On=1;
				}
			}
		return On==1;
		}

	public void ColisionAll(int X, int Y, int width, int height){
		for(int i=y; i>y-Size; i--){
			if (Y<i && i<=Y+height){
				if (X<=x+Size && x+Size<=X+1) x=X-Size;
				if (X+width-1<=x && x<=X+width) x=X+width;
				}
			}
		for(int i=y; i>y-Size; i--){
			for(int j=x; j<x+Size; j++){
				if (X+1<=j && j<=X+width-1){
					if (Y<=i && i<=Y+1){
						//SautLock=0;
						//y-=1;
						}
					//if (Y<=i && i<=Y+1) Chute=0;
					//if (Y<=y && y<=Y+1) Sustained+=1;
					//if (Y<=y && y<=Y+1) y-=1;
					//if (Y<=y && y<=Y+1) y=Y;
					if (Y<=y && y<=Y+1) Sustained=1;
					else Sustained=0;
					if (Y+height-1<=i && i<=Y+height) y=Y+height+Size;
					//if (Sustained>1) Sustained=1;
					break;
					}
				else Sustained=0;
				}
			}
		}
	public void ColisionALL(int X, int Y, int width, int height){
		for(int i=y; i>y-Size; i--){
			if (Y<i && i<=Y+height){
				if (X<=x+Size && x+Size<=X+1) x=X-Size;
				if (X+width-1<=x && x<=X+width) x=X+width;
				}
			}
		for(int i=y; i>y-Size; i--){
			for(int j=x; j<x+Size; j++){
				if (X+1<=j && j<=X+width-1){
					if (Y<i && i<=Y+1){
						SautLock=0;
						y-=1;
						}
					//if (Y<=y && y<=Y+1) Sustained=1;
					//else Sustained=0;
					if (Y+height-1<=i && i<=Y+height) y=Y+height+Size;
					//if (Sustained>1) Sustained=1;
					break;
					}
				//else Sustained=0;
				}
			}
		}
	public void WallStickAll(int X, int Y, int width, int height){
		int ML=0;
		int MR=0;
		for(int i=y; i>y-Size; i--){
			if (Y<=i && i<=Y+height){
				if (X==x+Size){
					Sustained=WallStickedR=1;
					//x=X-Size;
					//y-=1;
					//WallStickedR+=1;
					//Sustained+=1;
					//SautLock=0;
					}
				if (X+width==x){
					Sustained=WallStickedL=1;
					//x=X+width;
					//y-=1;
					//WallStickedL+=1;
					//Sustained+=1;
					//SautLock=0;
					}
				//if (WallStickedR>1) WallStickedR=1;
				//if (WallStickedL>1) WallStickedL=1;
				//if (Sustained>1) Sustained=1;
				break;
				}
			//if ((X!=x+Size || X+width!=x) && (WallStickedR==1 || WallStickedL==1 || Sustained==1)) Sustained=WallStickedR=WallStickedL=0;
			//else Sustained=WallStickedL=WallStickedR=0;
			}
		for(int i=x; i<x+Size; i++){
			if (X<=i && i<=X+width){
				if (y-Size==Y+height && Down==0){
					Sustained=1;
					//y-=1;//###
					//Motion=0;
					if (MotionL==1) ML=1;
					if (MotionR==1) MR=1;
					}
				//else Motion=1;
				}
			}
		if (MR==1) x-=1;
		if (ML==1) x+=1;
		}
	public int Parabol(int X, int x1, int y1, int x0, int y0){
		int Y=0;
		int X2 = X/10;
		int X1 = x1;
		int Y1 = y1;
		int X0 = x0;
		int Y0 = y0;
		int a = (Y0-Y1)/((X1*(2*X0-X1)+X0*X0));
		int c = Y0 + a*X0*X0;
		int b = (Y1-a*X1*X1-c)/X1;
		System.out.println(a+"\t"+b+"\t"+c);
		//Y = a*X*X + b*X + c;
		Y = 1*X2*X2 + 0*X2 + 0;
		return Y;
		}

	public int Norme(int X, int Y){
		return (int)Math.sqrt(X*X + Y*Y);
		}

	public boolean ColisionPoint(int X, int Y){
		int CPx=0;
		int CPy=0;
		for(int i=x; i<=x+Size; i++){
			if (i==X){
				CPx=1;
				break;
				}
			}
		for(int i=y; i>=y-Size; i--){
			if (i==Y){
				CPy=1;
				break;
				}
			}
		return CPx==1 && CPy==1;
		}

	public boolean CircleColision(int a, int b, int r){
		for(int i=x; i<=x+Size; i++){
			for(int j=y; j>=y-Size; j--){
				int XY = (i-a)*(i-a) + (j-b)*(j-b);
				if ( (r-1)*(r-1) <= XY && XY <= r*r) return true;
				}
			}
		return false;
		}
	public void ResetTab(){
		for(int i=0; i<50; i++) Tab[i]=0;
		}
	public boolean PolygonColision(int [] X, int [] Y){
		if (LineColision(X[0], Y[0], X[X.length-1], Y[X.length-1])) return true;
		for(int i=0; i<X.length-1; i++){
			if (LineColision(X[i], Y[i], X[i+1], Y[i+1])) return true;
			}
		return false;
		}
	public void ColisionRed(int X, int Y, int width, int height){
		if (Colision(X, Y, width, height)) initP=0;
		}
	public boolean LineColisionLeft(int Ax, int Ay, int Bx, int By){
		return LineColision(Ax, Ay, Bx, By) && x+Size==Ax;
		}
	public boolean LineColisionRight(int Ax, int Ay, int Bx, int By){
		return LineColision(Ax, Ay, Bx, By) && x==Ax;
		}
	public static void PanRefresh(){
		//x=Pan.x;
		//y=Pan.y;
		//Level=Pan.Level;
		//Down=Pan.Down;
		//info=Pan.info;
		//SLight=Pan.SLight;
		//Motion=Pan.Motion;
		//ForceSustained=Pan.ForceSustained;
		//initP=Pan.initP;
		//Sens=Pan.Sens;
		//MotionL=Pan.MotionL;
		//MotionR=Pan.MotionR;
		//Saut=Pan.Saut;
		//WallStickedR=Pan.WallStickedR;
		//WallStickedL=Pan.WallStickedL;
		//Sustained=Pan.Sustained;
		Measurement=Pan.Measurement;
		MeasurementX1=Pan.MeasurementX1;
		MeasurementX2=Pan.MeasurementX2;
		MeasurementY1=Pan.MeasurementY1;
		MeasurementY2=Pan.MeasurementY2;
		//LevelColor=Pan.LevelColor;
		}
	}//###$$****************************************************************************


class PanCredits extends JPanel{
	static int motFiny=0;
	public PanCredits(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0,0,900,900);
		if (Pan2.TimeOn==0){
			Pan2.TimeOn=1;
			new Time().start();
			}
		//g.setColor(new Color(Fen.Color255, Fen.Color255, Fen.Color255));
		g.setColor(Color.yellow);
		g.setFont(new Font("Miriam Libre", Font.BOLD, 30));
		g.drawString("Fin", 380, motFiny);
		g.drawString("Thanks for playing my game o^w^o", 150, motFiny+3400);
		g.setColor(Color.white);
		if (Pan2.Time>3 && motFiny>-3000) motFiny-=1;
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
		//if (motFiny==-50) Fen.Color255=0;
		//if (motFiny==-3000 && Fen.Color255<255) Fen.Color255+=1;

		try{
			Thread.sleep(7);
			} catch (InterruptedException e) {}
		}

	}


class Time extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e) {}
		Pan.Time+=1;
		Pan2.Time+=1;
		Pan.TimeOn=Pan2.TimeOn=0;
		}
	}
class Time2 extends Thread{
	public void run(){
		try{
			sleep(10);
			} catch (InterruptedException e) {}
		Pan.Time2+=1;
		Pan2.Time2+=1;
		Pan.TimeOn2=Pan2.TimeOn2=0;
		}
	}
class Time3 extends Thread{
	public void run(){
		try{
			sleep(100);
			} catch (InterruptedException e) {}
		//Pan.Time2+=1;
		Pan2.Time3+=1;
		Pan2.TimeOn3=0;
		}
	}
class SourisListener implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1) System.out.println(X+"\t"+Y);
		if (c==3 && 1==0){ //#################### SOURIS MODE EDITEUR
			Pan.x=Pan2.x=X;
			Pan.y=Pan2.y=Y;
			}
		//Pan2.PanRefresh();
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){
		int c = event.getButton();
		if (Pan.Measurement==1 || Pan2.Measurement==1) Pan.Measurement=Pan2.Measurement=0;
		//Pan2.PanRefresh();
			}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class ToucheSaut implements KeyListener{
	static String line;

	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		//if (Pan.Level<44){
//*Normal Keys
		if (Pan.Motion==1 && Pan2.Motion==1){
			if (c==0x25){
				Pan.MotionL=1;
				Pan.Sens=0;
				}
			else if (c==0x27){
				Pan.MotionR=1;
				Pan.Sens=2;
				}
			//if (c==0x20 && Pan.SautLock==0 && Pan.Chute==0){
			if (c==0x20 && Pan.SautLock==0){
				Pan.Saut=1;
				if (Pan.WallStickedR==1) Pan.x-=10;
				if (Pan.WallStickedL==1) Pan.x+=10;
				Pan.Sustained=0;
				Pan.WallStickedR=Pan.WallStickedL=0;
				}
			}
		else if (Pan.Motion==2){
			if (c==0x25) Pan.x-=1;
			else if (c==0x26) Pan.y-=1;
			else if (c==0x27) Pan.x+=1;
			else if (c==0x28) Pan.y+=1;
			//if (c==0x20) Pan.y-=2;
			}
		else if (Pan.Motion==3){
			if (c==0x25) Pan.x-=1;
			else if (c==0x27) Pan.x+=1;
			}
		if (Pan2.Motion==1){
			if (c==0x25){
				Pan2.MotionL=1;
				Pan2.Sens=0;
				}
			else if (c==0x27){
				Pan2.MotionR=1;
				Pan2.Sens=2;
				}
			if (c==0x20 && Pan2.SautLock==0){
				Pan2.Saut=1;
				if (Pan2.WallStickedR==1) Pan2.x-=10;
				if (Pan2.WallStickedL==1) Pan2.x+=10;
				Pan2.Sustained=0;
				Pan2.WallStickedR=Pan2.WallStickedL=0;
				}
			}
		else if (Pan2.Motion==2){
			if (c==0x25) Pan2.x-=1;
			else if (c==0x26) Pan2.y-=1;
			else if (c==0x27) Pan2.x+=1;
			else if (c==0x28) Pan2.y+=1;
			//if (c==0x20) Pan2.y-=2;
			}
		else if (Pan2.Motion==3){
			if (c==0x25) Pan2.x-=1;
			else if (c==0x27) Pan2.x+=1;
			}
		if (c==0x28) Pan.Down=Pan2.Down=1;
//**Super Keys
		if (1==0){ //######### MODE EDITEUR

		if (k=='p'){
			if (Pan.Level<44) System.out.println("\n"+Pan.x+"\t"+Pan.y+"\t"+"Sustained="+Pan.Sustained);
			else System.out.println("\n"+Pan2.x+"\t"+Pan2.y+"\t"+"Sustained="+Pan2.Sustained);
			}
		if (k=='P' && Pan.Motion==1){
			Pan.Motion=2;
			Pan.ForceSustained=1;
			}
		else if (k=='P' && Pan.Motion==2) Pan.Motion=1;
		if (k=='P' && Pan2.Motion==1){
			Pan2.Motion=2;
			Pan2.ForceSustained=1;
			}
		else if (k=='P' && Pan2.Motion==2) Pan2.Motion=1;
		if (k=='s' || k=='S') Pan.ForceSustained=Pan.Motion=Pan2.ForceSustained=Pan2.Motion=1;
		if (k=='r') Pan.initP=Pan2.initP=0;


		if (c==0x54 && Pan.Tab[40]==1) Pan.Tab[40]=0;
		else if (c==0x54 && Pan.Tab[40]==0) Pan.Tab[40]=1;
		if (k=='i') Pan.info=Pan2.info=1;
		if (k=='I'){
			System.out.println("Pan1 = " + Pan.SLight);
			System.out.println("Pan2 = " + Pan2.SLight);
			}
		if (k=='z' && c==0x26){
			Pan.SLight+=1;
			Pan2.SLight+=1;
			}
		else if (k=='z' && c==0x28 && Pan.SLight>0){
			Pan.SLight-=1;
			Pan2.SLight-=1;
			}
		if (k=='Z' && (Pan.SLight!=50 || Pan.SLight!=50)) Pan.SLight=Pan2.SLight=50;
		else if (k=='Z' && (Pan.SLight==50 || Pan.SLight==50)) Pan.SLight=Pan2.SLight=1000;

		if (c==0x10) Pan.Motion=Pan2.Motion=3;
		if (k=='l' && Pan.Level>1){
			Pan.initP=Pan2.initP=0;
			Pan.Level-=1;
			//Pan2.Level-=1;
			if (Pan.Level%5!=0) Pan.LevelColor=Pan2.LevelColor=0;
			}
		if (k=='L' && Pan.Level<50){
			Pan.initP=Pan2.initP=0;
			Pan.Level+=1;
			//Pan2.Level+=1;
			if (Pan.Level%5!=0) Pan.LevelColor=Pan2.LevelColor=0;
			}
		Pan2.Level=Pan.Level;
		//Pan2.PanRefresh();

		if (k=='m') System.out.println(line); //***FIN MODE EDITEUR TOUCHES */
		}
		}
	public void keyReleased(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		//if (Pan.Level<44){
			if (c==0x25) Pan.MotionL=Pan2.MotionL=0;
			else if (c==0x27) Pan.MotionR=Pan2.MotionR=0;
			//if (c==0x25 || c==0x27) Pan.MotionL=Pan.MotionR=0;
			if (c==0x28) Pan.Down=Pan2.Down=0;
			if (k=='s') Pan.ForceSustained=Pan2.ForceSustained=0;
			if (k=='i') Pan.info=Pan2.info=0;
			if (c==0x10) Pan.Motion=Pan2.Motion=1;
			//}
		//Pan2.PanRefresh();
		}
	}
class SourisMotionListener implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){}
	public void mouseDragged(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		//if (c==1){
			//System.out.println("Bon");
			if (Pan.Measurement==0){
				Pan.MeasurementX1=X;
				Pan.MeasurementY1=Y;
				}
			if (Pan2.Measurement==0){
				Pan2.MeasurementX1=X;
				Pan2.MeasurementY1=Y;
				}
			Pan.Measurement=1;
			Pan.MeasurementX2=X;
			Pan.MeasurementY2=Y;
			Pan2.Measurement=1;
			Pan2.MeasurementX2=X;
			Pan2.MeasurementY2=Y;
			//}
		//Pan2.PanRefresh();
		}
	}

class EcranTitre extends JPanel{
	class ActionCommencer implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Partie = 1;
			}
		}
	class ActionContinuer implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Partie = 2;
			}
		}
	public static boolean IsInTabString(String A, String [] B){
		for(int i=0; i<B.length; i++){
			if (B[i]==A) return true;
			}
		return false;
		}
	public static int StringToInt(String sNum){
		//String [] num = {"0","1","2","3","4","5","6","7","8","9"};
		char [] num = {'0','1','2','3','4','5','6','7','8','9'};
		String [] SNUM = new String [2];
		int Num=0;
		if (sNum.length()==1){
			for(int i=0; i<10; i++){
				if (num[i]==sNum.charAt(0)) Num=i;
				}
			}
		else if (sNum.length()==2){
			//SNUM[0]=s
			for(int i=0; i<10; i++){
				if (num[i]==sNum.charAt(1)) Num+=i;
				if (num[i]==sNum.charAt(0)) Num+=10*i;
				}
			}
		return Num;
		}
		

	JButton gris = new JButton();
	JButton Commencer = new JButton("Commencer une nouvelle partie");
	JButton Continuer = new JButton("Continuer la partie en cours");
	static int Partie=0;
	EcranTitre(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		

		ActionCommencer CommencerAction = new ActionCommencer();
		ActionContinuer ContinuerAction = new ActionContinuer();
		Commencer.addActionListener(CommencerAction);
		Commencer.setPreferredSize(new Dimension(250, 120));
		Continuer.addActionListener(ContinuerAction);
		Continuer.setVisible(false);
		Continuer.setPreferredSize(new Dimension(250, 120));

		this.add(Commencer);
		this.add(Continuer);

		ToucheSaut Tsaut = new ToucheSaut();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(Tsaut);
		this.add(gris);

		}
	public void paintComponent(Graphics g){
		try{
			Image Titre = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jump Games/Jump/Images/EcranTitre.png"));
			//g.drawImage(Titre, 0,0,this.getWidth(),this.getHeight(),this);
			g.drawImage(Titre, 0,0,900,900,this);
			} catch (IOException e) {e.printStackTrace();}
		}
	}


public class Jump{
	public static void main(String[] args){
		int y=900;
		Fen FenTitre = new Fen("Jump Ecran Titre", 916, y+40);
		//FenTitre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try{
			Image Titre = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Jump Games/Jump/Images/IconeJump.png"));
			FenTitre.setIconImage(Titre);
			} catch (IOException e) {e.printStackTrace();}
		EcranTitre Titre = new EcranTitre(900,y);
		FenTitre.setContentPane(Titre);
		Pan pan = new Pan(900, y);
		Pan2 pan2 = new Pan2(900, y);
		PanCredits panCredits = new PanCredits(900, y);


		String SavePath = "C:/Users/Utilisateur/Desktop/Programmes/Creations/Jump Games/Jump/JumpSave.txt";
		String JumpSaveText="";
		try{
			FileReader SaveReader = new FileReader(SavePath);
			BufferedReader bR = new BufferedReader(SaveReader);
			if (bR.readLine() != null) Titre.Continuer.setVisible(true);
			} catch (IOException e){}

		while (EcranTitre.Partie==0 && 1==1){
			Titre.setVisible(true);
			pan2.setVisible(false);
			pan.setVisible(false);
			panCredits.setVisible(false);
			
			//System.out.println("attente");
			}
		if (EcranTitre.Partie==1){
			try{
			FileWriter writer = new FileWriter("JumpSave.txt");
			//writer.write();
			//writer.flush();
			writer.close();
			pan.Level=pan2.Level = 1;			
				} catch (IOException e){}
			}
		
		else if (EcranTitre.Partie==2){
			try{
			FileReader SaveReader = new FileReader(SavePath);
			BufferedReader bR = new BufferedReader(SaveReader);
			String T = bR.readLine();
			//for(int i=T.length()-1; i>=0; i--){
				//for(int j=0; j<10; j++){
					//if (T[i]==num[j]){


			//if (T.length()==1) 
			pan.Level=pan2.Level = EcranTitre.StringToInt(T);
			pan.initP=pan2.initP=0;
				} catch (IOException e){}
			}

		Titre.setVisible(false);
		FenTitre.setVisible(false);
		FenTitre.removeNotify();
		//fen.remove(Titre);
		//fen.setLayout(new GridBagLayout());
		//fen.add(pan);
		//fen.add(pan2);




		Fen fen = new Fen("Jump", 916, y+40);
		try{
			Image Titre2 = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jump Games/Jump/Images/IconeJump.png"));
			fen.setIconImage(Titre2);
			} catch (IOException e) {e.printStackTrace();}

		//EcranTitre Titre = new EcranTitre(900,y);
		//pan.setVisible(false);
		//pan2.setVisible(false);
		//Titre.setVisible(true);
		fen.setLayout(new GridBagLayout());
		//fen.setContentPane(Titre);
		//fen.add(Titre);
		fen.add(pan);
		fen.add(pan2);
		fen.add(panCredits);

		JPanel gris = new JPanel();
		ToucheSaut Tsaut = new ToucheSaut();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(Tsaut);
		pan.add(gris);
		pan2.add(gris);
		//Titre.add(gris);
		fen.addKeyListener(Tsaut);
		pan.addKeyListener(Tsaut);
		pan2.addKeyListener(Tsaut);
		//Titre.addKeyListener(Tsaut);


		while (true){
/*
*			gris.setPreferredSize(new Dimension(0,0));
*			gris.setVisible(true);
*			gris.addKeyListener(Tsaut);
*			pan.add(gris);
*			pan2.add(gris);
*			fen.addKeyListener(Tsaut);
*			pan.addKeyListener(Tsaut);
*			pan2.addKeyListener(Tsaut);
*/			if (y<900){
				//y+=1;
				//fen.setSize(916, y+40);
				//fen.setLocationRelativeTo(null);
				try{
					Thread.sleep(5);
					} catch (InterruptedException e) {}
				 }
			if (pan.Level<44){
				//fen.remove(pan2);
				//Titre.setVisible(false);
				pan2.setVisible(false);
				pan.setVisible(true);
				}
			else if (pan2.Level<=50){
				//fen.remove(pan);
				//Titre.setVisible(false);
				pan.setVisible(false);
				pan2.setVisible(true);
				}
			else{
				pan.setVisible(false);
				pan2.setVisible(false);
				panCredits.setVisible(true);
				panCredits.repaint();
				}
				
			pan.repaint();
			pan2.repaint();

			//ToucheSaut.line = "bon";
			//try{
				//FileReader SaveReader = new FileReader(SavePath);
				//BufferedReader bR = new BufferedReader(SaveReader);
				//while((ToucheSaut.line = bR.readLine()) != null);
				//ToucheSaut.line = bR.readLine();
				//ToucheSaut.line = bR.readLine();
				//ToucheSaut.line = SaveReader.read();
				//System.out.println("fichier lignes = "+bR.prefixLength());
				//int AQ = bR.read();
				//int AQ2 = bR.read();
				//int AQ3 = bR.read();
				//System.out.println("fichier lignes = "+ AQ);
				//System.out.println("fichier lignes = "+ AQ2);
				//System.out.println("fichier lignes = "+ AQ3);
				//} catch (IOException e){ System.out.println("Error reading file: " + e.getMessage());}


			if ((pan.Level<44 && pan.initP==0) || (pan.Level>=44 && pan2.initP==0)){
				if (pan.Level<44) JumpSaveText = String.valueOf(pan.Level);
				else JumpSaveText = String.valueOf(pan2.Level);
/*
*	x=20;
*	y=800;
*	P1x=0;
*	P1y=0;
*	P1Tour=0;
*	P1Trigger=0;
*	P2x=0;
*	P2y=0;
*	P2Tour=0;
*	P2Trigger=0;
*	P3x=0;
*	P3y=0;
*	P3Tour=0;
*	P3Trigger=0;
*	Pmove=0;
*	ButtonOn1=0;
*	ButtonOn2=0;
*
*	info = 0;
*	SLight=1000;
*
*	initP=0;
*	Time=0;
*	Time2=0;
*	TimeOn=0;
*	TimeOn2=0;
*	Motion=1;
*	Size=50;
*	Sens=2;
*	MotionR=0;
*	MotionL=0;
*	Down=0;
*	Saut=0;
*	Sustained=0;
*	WallStickedR=0;
*	WallStickedL=0;
*	ForceSustained=0;
*	LineOn=0;
*	SautLock=0;
*	SautCooldown=0;
*	Chute=0;
*	ColorP=0;
*	LevelColor=0;
*	Level=50;
*
*
*	Measurement=0;
*	MeasurementX1=0;
*	MeasurementY1=0;
*	MeasurementX2=0;
*	MeasurementY2=0;
*
*/
				try{
					FileWriter writer = new FileWriter("JumpSave.txt");
					writer.write(JumpSaveText);
					writer.close();
					} catch (IOException e){ System.out.println("Saving JumpSave Error"); e.printStackTrace();}
				}
				
			}
		}
}