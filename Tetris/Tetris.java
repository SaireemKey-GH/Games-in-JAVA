import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class Fen extends JFrame{
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		}
	}
/*
* Game=0 is reset
* Game=1 is play
* Game=2 is GameOver
* Game=3 is LevelCompleted ; Top achieved
*
*
*/
class Pan extends JPanel{
	static int LIGHT=0;

	static int grid [][] = new int [21][10];
	static char CurrentType='A';
	static int Score=0;
	static int Top=10000;
	static int Level=0;
	static char Next=' ';
	static int NextOn=0;
	static int Lines=0;
	static int StatA=0;
	static int StatB=0;
	static int StatC=0;
	static int StatD=0;
	static int StatE=0;
	static int StatF=0;
	static int StatG=0;
	static int TimeOn=0;
	static int TIMEON=0;
	static int Rotation=0;
	static int MovePositionRightOn=0;
	static int MovePositionLeftOn=0;
	static int MovePositionDownOn=0;	
	static char [] Type = {'A','B','C','D','E','F','G'};
	static int LineBreakI=0;
	static int BreakOn=0;
	static int BreakTourX=0;
	static int BreakTourY=0;
	static int Game=-1;
	static int BestScore=0;
	static int BestLevel=0;
	static int StartGame=0;
	JButton gris = new JButton();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		

		Souris souris = new Souris();
		Touche touche = new Touche();
		this.addMouseListener(souris);
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);
		}
	public void paintComponent(Graphics g){
		if (Game!=-1){//#

		if (LIGHT==0) g.setColor(Color.white);
		else g.setColor(Color.black);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.red);
		g.drawRect(0, 0, 1000, 800);
			
		if (LIGHT==0) g.setColor(Color.black);
		else g.setColor(Color.white);
		g.drawRect(43, 216, 280, 518);//Stats
		g.drawRoundRect(43-17, 216-17, 280+34, 518+34, 20, 20);

		g.drawRect(77, 88, 215, 50);//Type
		g.drawRoundRect(77-17, 88-17, 215+34, 50+34, 20, 20);

		g.drawRect(374, 150, 270, 567);//Centre
		g.drawRoundRect(374-17, 150-17, 270+34, 567+34, 5, 5);
		g.drawRect(374-17*2, 150-17*2, 270+34*2, 567+34*2);

		g.drawRect(357, 49, 304, 50);//Lines number
		g.drawRoundRect(357-17, 49-17, 304+34, 50+34, 20, 20);

		g.drawRect(695, 49, 215, 215);//Score
		g.drawRoundRect(695-17, 49-17, 215+34, 215+34, 20, 20);

		g.drawRect(712, 315, 126, 140);//Next
		g.drawRoundRect(712-17, 315-17, 126+34, 140+34, 10, 10);
		g.drawRect(712-17*2, 315-17*2, 126+34*2, 140+34*2);

		g.drawRect(695, 506, 190, 76);//Level
		g.drawRoundRect(695-17, 506-17, 190+34, 76+34, 20, 20);


		g.drawRect(695, 616, 190, 117);//**BEST 
		g.drawRoundRect(695-17, 616-17, 190+34, 151, 20, 20);
//*****StartGame
		if (NextOn==0){
			int RandomType = (int)(Math.random()*7);
			if (Game==0){
				CurrentType = Type[RandomType];
				for(int i=0; i<21; i++){
					for(int j=0; j<10; j++) grid[i][j]=0;
					}
				}
			else CurrentType=Next;
			Rotation=0;
			if (CurrentType=='A'){
				if (grid[0][3]!=0 || grid[0][4]!=0 || grid[0][5]!=0 || grid[1][4]!=0) Game=2;
				grid[0][3]=grid[0][4]=grid[0][5]=grid[1][4]=1;
				StatA+=1;
				}
			if (CurrentType=='B'){
				if (grid[0][3]!=0 || grid[0][4]!=0 || grid[0][5]!=0 || grid[1][5]!=0) Game=2;
				grid[0][3]=grid[0][4]=grid[0][5]=grid[1][5]=1;
				StatB+=1;
				}
			if (CurrentType=='C'){
				if (grid[0][3]!=0 || grid[0][4]!=0 || grid[1][4]!=0 || grid[1][5]!=0) Game=2;
				grid[0][3]=grid[0][4]=grid[1][4]=grid[1][5]=1;
				StatC+=1;
				}
			if (CurrentType=='D'){
				if (grid[0][4]!=0 || grid[0][5]!=0 || grid[1][4]!=0 || grid[1][5]!=0) Game=2;
				grid[0][4]=grid[0][5]=grid[1][4]=grid[1][5]=1;
				StatD+=1;
				}
			if (CurrentType=='E'){
				if (grid[1][3]!=0 || grid[1][4]!=0 || grid[0][4]!=0 || grid[0][5]!=0) Game=2;
				grid[1][3]=grid[1][4]=grid[0][4]=grid[0][5]=1;
				StatE+=1;
				}
			if (CurrentType=='F'){
				if (grid[0][3]!=0 || grid[0][4]!=0 || grid[0][5]!=0 || grid[1][3]!=0) Game=2;
				grid[0][3]=grid[0][4]=grid[0][5]=grid[1][3]=1;
				StatF+=1;
				}
			if (CurrentType=='G'){
				if (grid[0][3]!=0 || grid[0][4]!=0 || grid[0][5]!=0 || grid[0][6]!=0) Game=2;
				grid[0][3]=grid[0][4]=grid[0][5]=grid[0][6]=1;
				StatG+=1;
				}

			RandomType = (int)(Math.random()*7);
			Next = Type[RandomType];
				
			if (Game!=2) Game=NextOn=1;
			}

//**End StartGame
		int size=27;
		//for(int j=0; j<10; j++) grid[1][j]=1;
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1){
					if (CurrentType=='A' || CurrentType=='D' || CurrentType=='G'){
						g.setColor(Color.black);
						g.fillRect(374+j*size, 150+i*size, size, size);
						g.setColor(Color.blue);
						g.drawRect(374+j*size+1, 150+i*size+1, size-2, size-2);
						g.setColor(Color.white);
						g.fillRect(374+j*size+2, 150+i*size+2, size-4, size-4);
						}
					if (CurrentType=='B' || CurrentType=='E'){
						g.setColor(Color.black);
						g.fillRect(374+j*size, 150+i*size, size, size);
						g.setColor(Color.blue);
						g.fill3DRect(374+j*size+1, 150+i*size+1, size-2, size-2, true);
						}
					if (CurrentType=='C' || CurrentType=='F'){
						g.setColor(Color.black);
						g.fillRect(374+j*size, 150+i*size, size, size);
						g.setColor(Color.cyan);
						g.fill3DRect(374+j*size+1, 150+i*size+1, size-2, size-2, true);
						}
					}
				if (grid[i][j]==2){
					g.setColor(Color.black);
					g.fillRect(374+j*size, 150+i*size, size, size);
					g.setColor(Color.blue);
					g.drawRect(374+j*size+1, 150+i*size+1, size-2, size-2);
					g.setColor(Color.white);
					g.fillRect(374+j*size+2, 150+i*size+2, size-4, size-4);
					}
				if (grid[i][j]==3){
					g.setColor(Color.black);
					g.fillRect(374+j*size, 150+i*size, size, size);
					g.setColor(Color.blue);
					g.fill3DRect(374+j*size+1, 150+i*size+1, size-2, size-2, true);
					}
				if (grid[i][j]==4){
					g.setColor(Color.black);
					g.fillRect(374+j*size, 150+i*size, size, size);
					g.setColor(Color.cyan);
					g.fill3DRect(374+j*size+1, 150+i*size+1, size-2, size-2, true);
					}
				}
			}
//*******************
//******MovePosition


			if (LineBreakI()!=-1 && BreakOn==0){
				if (IsLineFullColor(LineBreakI())) BreakOn=3;
				else BreakOn=1;
				BreakTourX=130;
				BreakTourY=13;
				LineBreakI=LineBreakI();
				Lines+=1;
				}
		if (TimeOn==0 && BreakOn==0 && Game==1){
			new Time().start();
			TimeOn=1;
			//System.out.println("bon");
			if (MovePositionDownPossible()) MovePositionDown();
			//if (MovePositionDownOn==1) MovePositionDown();
			else{
				FreezePosition();
				NextOn=0;
				}

			//if (MovePositionRightOn==1) MovePositionRight();
			//else if (MovePositionLeftOn==1) MovePositionLeft();
			}
		if (BreakOn>0){
			int I = LineBreakI;
			g.setColor(Color.yellow);
			g.fillRect(374+BreakTourX, 150+I*size+BreakTourY, 270-BreakTourX*2, size-BreakTourY*2);
			if (BreakOn==1){
				if (BreakTourX%10==0) BreakTourY-=1;
				BreakTourX-=1;
				if (BreakTourX==0){
					BreakOn=2;
					ScoringLine(I);
					LineBreak(I);
					}
				}
			else if (BreakOn==2){
				if (BreakTourX%10==0) BreakTourY+=1;
				BreakTourX+=1;
				if (BreakTourX==135) BreakOn=0;
				}
			}
		if (BreakOn==3){
			int I = LineBreakI;
			int c1 = 150+(int)(Math.random()*105);
			int c2 = 150+(int)(Math.random()*105);
			int c3 = 150+(int)(Math.random()*105);
			g.setColor(new Color(c1, c2, c3));
			g.fillRect(374, 150+I*size, 270, size);
			if (I-1>-1) g.fillRect(374, 150+(I-1)*size, 270, size);
			if (I+1<21) g.fillRect(374, 150+(I+1)*size, 270, size);
			BreakTourX-=2;
			if (BreakTourX==0){
				BreakOn=0;
				if (I+1==21){
					SuperScoring(I-1);
					LineBreak(I-1);
					LineBreak(I);
					}
				else if (I-1==-1){
					SuperScoring(I);
					LineBreak(I);
					LineBreak(I+1);
					}
				else{
					UltraScoring(I-1);
					LineBreak(I-1);
					LineBreak(I);
					LineBreak(I+1);
					}

				if (I-1>-1){
					//ScoringLine(I-1);
					//LineBreak(I-1);
					}
				//ScoringLine(I);
				//LineBreak(I);
				if (I+1<21){
					//ScoringLine(I+1);
					//LineBreak(I+1);
					}
				}	
			}
		try{
			Thread.sleep(5);
			} catch (InterruptedException e) {}
//*****End MovePosition
//*******************
//**Affichages
		int size2 = (int)(size/2);
		if (LIGHT==0) g.setColor(Color.black);
		else g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("NEXT", 720, 348);
		if (Next=='A'){
			for(int i=0; i<3; i++){
				g.setColor(Color.black);			
				g.fillRect(735+size*i, 370, size, size);
				g.setColor(Color.blue);
				g.drawRect(735+size*i+1, 370+1, size-2, size-2);
				g.setColor(Color.white);
				g.fillRect(735+size*i+2, 370+2, size-4, size-4);
				}
			g.setColor(Color.black);			
			g.fillRect(762, 370+size, size, size);
			g.setColor(Color.blue);
			g.drawRect(762+1, 370+size+1, size-2, size-2);
			g.setColor(Color.white);
			g.fillRect(762+2, 370+size+2, size-4, size-4);
			}
		else if (Next=='B'){
			for(int i=0; i<3; i++){
				g.setColor(Color.black);
				g.fillRect(735+size*i, 370, size, size);
				g.setColor(Color.blue);
				g.fillRect(735+size*i+1, 370+1, size-2, size-2);
				}
			g.setColor(Color.black);
			g.fillRect(789, 397, size, size);
			g.setColor(Color.blue);
			g.fillRect(789+1, 397+1, size-2, size-2);
			}
		else if (Next=='C'){
			for(int i=0; i<2; i++){
				g.setColor(Color.black);
				g.fillRect(735+size*i, 370, size, size);
				g.fillRect(762+size*i, 397, size, size);
				g.setColor(Color.cyan);
				g.fillRect(735+size*i+1, 370+1, size-2, size-2);
				g.fillRect(762+size*i+1, 397+1, size-2, size-2);
				}
			}
		else if (Next=='D'){
			for(int i=0; i<2; i++){
				for(int j=0; j<2; j++){
					g.setColor(Color.black);
					g.fillRect(748+i*size, 370+j*size, size, size);
					g.setColor(Color.blue);
					g.drawRect(748+i*size+1, 370+j*size+1, size-2, size-2);
					g.setColor(Color.white);
					g.fillRect(748+i*size+2, 370+j*size+2, size-4, size-4);
					}
				}
			}
		else if (Next=='E'){
			for(int i=0; i<2; i++){
				g.setColor(Color.black);
				g.fillRect(735+size*i, 397, size, size);
				g.fillRect(762+size*i, 370, size, size);
				g.setColor(Color.blue);
				g.fillRect(735+size*i+1, 397+1, size-2, size-2);
				g.fillRect(762+size*i+1, 370+1, size-2, size-2);
				}
			}
		else if (Next=='F'){
			for(int i=0; i<3; i++){
				g.setColor(Color.black);
				g.fillRect(735+size*i, 370, size, size);
				g.setColor(Color.cyan);
				g.fillRect(735+size*i+1, 370+1, size-2, size-2);
				}
			g.setColor(Color.black);
			g.fillRect(735, 397, size, size);
			g.setColor(Color.cyan);
			g.fillRect(735+1, 397+1, size-2, size-2);
			}
		else if (Next=='G'){
			for(int i=0; i<4; i++){
				g.setColor(Color.black);
				g.fillRect(721+i*size, 370, size, size);
				g.setColor(Color.blue);
				g.fillRect(721+i*size+1, 370+1, size-2, size-2);
				g.setColor(Color.white);
				g.fillRect(721+i*size+2, 370+2, size-4, size-4);
				}
			}

//*******Score
		if (LIGHT==0) g.setColor(Color.black);
		else g.setColor(Color.white);
		g.drawString("TOP", 705, 90);
		if (Top<100000){
			g.drawString("0",705, 125);
			g.drawString(String.valueOf(Top),725, 125);
			}
		else g.drawString(String.valueOf(Top),705, 125);
		g.drawString("SCORE",705, 180);
		if (Score<10){
			g.drawString("00000",705, 215);
			g.drawString(String.valueOf(Score),815, 215);
			}
		else if (Score<100){
			g.drawString("0000",705, 215);
			g.drawString(String.valueOf(Score),792, 215);
			}
		else if (Score<1000){
			g.drawString("000",705, 215);
			g.drawString(String.valueOf(Score),770, 215);
			}
		else if (Score<10000){
			g.drawString("00",705, 215);
			g.drawString(String.valueOf(Score),748, 215);
			}
		else if (Score<100000){
			g.drawString("0",705, 215);
			g.drawString(String.valueOf(Score),726, 215);
			}
		else g.drawString(String.valueOf(Score),705, 215);


		if (Score>=Top) Game=3;

//*******End Score

//**Level
		g.drawString("LEVEL",720, 540);
		if (Level<10){
			g.drawString("0",775, 575);
			g.drawString(String.valueOf(Level),800, 575);
			}
		else g.drawString(String.valueOf(Level),775, 575);
//**End Level

//**Type and Line
		g.drawString(String.valueOf(CurrentType), 85, 128);
		g.drawString("_", 132, 107);
		g.drawString("TYPE",175, 128);
		
		g.drawString("LINES", 405, 92);
		g.drawString("_",530, 70);
		if (Lines<10){
			g.drawString("00",565,92);
			g.drawString(String.valueOf(Lines),610,92);
			}
		else if (Lines<100){
			g.drawString("0",565,92);
			g.drawString(String.valueOf(Lines),585,92);
			}
		else g.drawString(String.valueOf(Lines),565,92);
		

//**End Type and Line
			
//**Statistiques
		size=22;
		size2=(int)(size/2);
		g.drawString("STATISTICS",70, 250);
		if (StatA<10){
			g.drawString("00",185, 313);
			g.drawString(String.valueOf(StatA),229,313);
			}
		else if (StatA<100){
			g.drawString("0",185, 313);
			g.drawString(String.valueOf(StatA),207,313);
			}
		else g.drawString(String.valueOf(StatA),185,313);

		if (StatB<10){
			g.drawString("00",185, 379);
			g.drawString(String.valueOf(StatB),229,379);
			}
		else if (StatB<100){
			g.drawString("0",185, 379);
			g.drawString(String.valueOf(StatB),207,379);
			}
		else g.drawString(String.valueOf(StatB),185,379);

		if (StatC<10){
			g.drawString("00",185, 445);
			g.drawString(String.valueOf(StatC),229,445);
			}
		else if (StatC<100){
			g.drawString("0",185, 445);
			g.drawString(String.valueOf(StatC),207,445);
			}
		else g.drawString(String.valueOf(StatC),185,445);

		if (StatD<10){
			g.drawString("00",185, 511);
			g.drawString(String.valueOf(StatD),229,511);
			}
		else if (StatD<100){
			g.drawString("0",185, 511);
			g.drawString(String.valueOf(StatD),207,511);
			}
		else g.drawString(String.valueOf(StatD),185,511);

		if (StatE<10){
			g.drawString("00",185, 577);
			g.drawString(String.valueOf(StatE),229,577);
			}
		else if (StatE<100){
			g.drawString("0",185, 577);
			g.drawString(String.valueOf(StatE),207,577);
			}
		else g.drawString(String.valueOf(StatE),185,577);

		if (StatF<10){
			g.drawString("00",185, 643);
			g.drawString(String.valueOf(StatF),229,643);
			}
		else if (StatF<100){
			g.drawString("0",185, 643);
			g.drawString(String.valueOf(StatF),207,643);
			}
		else g.drawString(String.valueOf(StatF),185,643);

		if (StatG<10){
			g.drawString("00",185, 709);
			g.drawString(String.valueOf(StatG),229,709);
			}
		else if (StatG<100){
			g.drawString("0",185, 709);
			g.drawString(String.valueOf(StatG),207,709);
			}
		else g.drawString(String.valueOf(StatG),185,709);
//MilieuX=130
//MilieuX-size*2=86
//MilieuX-size2-size=97
//StartY=280
//*A
		for(int i=0; i<3; i++){
			g.setColor(Color.black);			
			g.fillRect(97+size*i, 280, size, size);
			g.setColor(Color.blue);
			g.drawRect(97+size*i+1, 280+1, size-2, size-2);
			g.setColor(Color.white);
			g.fillRect(97+size*i+2, 280+2, size-4, size-4);
			}
		g.setColor(Color.black);			
		g.fillRect(119, 280+size, size, size);
		g.setColor(Color.blue);
		g.drawRect(119+1, 280+size+1, size-2, size-2);
		g.setColor(Color.white);
		g.fillRect(119+2, 280+size+2, size-4, size-4);
//*B

		for(int i=0; i<3; i++){
			g.setColor(Color.black);
			g.fillRect(97+size*i, 346, size, size);
			g.setColor(Color.blue);
			g.fillRect(97+size*i+1, 346+1, size-2, size-2);
			}
		g.setColor(Color.black);
		g.fillRect(141, 368, size, size);
		g.setColor(Color.blue);
		g.fillRect(141+1, 368+1, size-2, size-2);
//*C
		for(int i=0; i<2; i++){
			g.setColor(Color.black);
			g.fillRect(97+size*i, 412, size, size);
			g.fillRect(119+size*i, 434, size, size);
			g.setColor(Color.cyan);
			g.fillRect(97+size*i+1, 412+1, size-2, size-2);
			g.fillRect(119+size*i+1, 434+1, size-2, size-2);	
			}
//*D
		for(int i=0; i<2; i++){
			for(int j=0; j<2; j++){
				g.setColor(Color.black);
				g.fillRect(108+i*size, 478+j*size, size, size);
				g.setColor(Color.blue);
				g.drawRect(108+i*size+1, 478+j*size+1, size-2, size-2);
				g.setColor(Color.white);
				g.fillRect(108+i*size+2, 478+j*size+2, size-4, size-4);
				}
			}
//*E
		for(int i=0; i<2; i++){
			g.setColor(Color.black);
			g.fillRect(97+size*i, 566, size, size);
			g.fillRect(119+size*i, 544, size, size);
			g.setColor(Color.blue);
			g.fillRect(97+size*i+1, 566+1, size-2, size-2);
			g.fillRect(119+size*i+1, 544+1, size-2, size-2);
			}
//*F
		for(int i=0; i<3; i++){
			g.setColor(Color.black);
			g.fillRect(97+size*i, 610, size, size);
			g.setColor(Color.cyan);
			g.fillRect(97+size*i+1, 610+1, size-2, size-2);
			}
		g.setColor(Color.black);
		g.fillRect(97, 632, size, size);
		g.setColor(Color.cyan);
		g.fillRect(97+1, 632+1, size-2, size-2);
//*G
		for(int i=0; i<4; i++){
			g.setColor(Color.black);
			g.fillRect(86+i*size, 676, size, size);
			g.setColor(Color.blue);
			g.fillRect(86+i*size+1, 676+1, size-2, size-2);
			g.setColor(Color.white);
			g.fillRect(86+i*size+2, 676+2, size-4, size-4);	
			}		

//**End Statistiques
//**End Affichages

//*****Game 2 or 3
		if (BestScore!=0 || BestLevel!=0){
			if (LIGHT==0) g.setColor(Color.black);
			else g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.drawString("BESTLEVEL",698, 645);
			g.drawString("BESTSCORE",698, 700);

			if (BestLevel<10){
				g.drawString("0"+String.valueOf(BestLevel), 795, 672);
				}
			else g.drawString(String.valueOf(BestLevel),795, 672);

			if (BestScore<10){
				g.drawString("00000"+String.valueOf(BestScore),765, 726);
				}
			else if (BestScore<100){
				g.drawString("0000"+String.valueOf(BestScore),765, 726);
				}
			else if (BestScore<1000){
				g.drawString("000"+String.valueOf(BestScore),765, 726);
				}
			else if (BestScore<10000){
				g.drawString("00"+String.valueOf(BestScore),765, 726);
				}
			else if (BestScore<100000){
				g.drawString("0"+String.valueOf(BestScore),765, 726);
				}
			else g.drawString(String.valueOf(BestScore),765, 726);
			}
		if (Game==2){
			NextOn=3;
			g.setColor(Color.red);
			for(int i=0; i<5; i++) g.drawRect(509-130-i, 434-25-i, 130*2+i*2, 25*2+i*2);
			g.setColor(Color.black);
			g.fillRect(509-130, 434-25, 130*2, 25*2);
			//g.fillRoundRect(679, 600, 224, 151, 20, 20);
			g.fillRoundRect(26, 752, 878, 40, 20, 20);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("GAME OVER",386, 448);

			g.setFont(new Font("Arial", Font.BOLD, 22));
			//g.drawString("APPUYEZ SUR UNE",680, 625);
			//g.drawString("TOUCHE POUR",680, 650);
			//g.drawString("RECOMMENCER",680, 675);

			g.setFont(new Font("Arial", Font.BOLD, 34));
			g.drawString("APPUYEZ SUR UNE TOUCHE POUR RECOMMENCER", 29, 785);
			}

		if (Game==3){
			NextOn=3;
			g.setColor(Color.yellow);
			for(int i=0; i<5; i++) g.drawRect(509-130-i, 434-25-i, 130*2+i*2, 25*2+i*2);
			g.setColor(Color.black);
			g.fillRect(509-130, 434-25, 130*2, 25*2);
			//g.fillRoundRect(679, 600, 224, 151, 20, 20);
			g.fillRoundRect(26, 752, 878, 40, 20, 20);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.drawString("LEVEL COMPLETED",386, 445);

			//g.drawString("APPUYEZ SUR UNE",680, 625);
			//g.drawString("TOUCHE POUR",680, 650);
			//g.drawString("CONTINUER",680, 675);

			g.setFont(new Font("Arial", Font.BOLD, 34));
			g.drawString("APPUYEZ SUR UNE TOUCHE POUR CONTINUER", 65, 785);
			}
//*****End Game 2 or 3

			}//#Game!=-1
		else if (Game==-1){
			g.setColor(Color.black);
			g.fillRect(0,0,this.getWidth(), this.getHeight());
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 230));
			g.drawString("TETRIS", 500-400, 300);


			g.setFont(new Font("Arial", Font.BOLD, 50));			
			g.drawString("Appuyez sur 'x' pour pivoter", 500-330, 500);
			}

		}
//****************************************************************************************
//****************************************************************************************
//****************************************************************************************
//****************************************************************************************
//****************************************************************************************
//****************************************************************************************
	public static void afficherGrid(){
		for(int i=0; i<21; i++){
			System.out.print("[");
			for(int j=0; j<10; j++){
				if (j!=9) System.out.print(grid[i][j]+", ");
				else System.out.println(grid[i][j]+"]");
				}
			}
		}
	public static void RefreshGrid(){
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1) grid[i][j]=0;
				}
			}
		}
	public static void ResetGrid(){
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				grid[i][j]=0;
				}
			}
		}
	public static void MovePositionDown(){
		int [][] Shape = new int [4][2];
		int S=0;
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1 && S<4){
					Shape[S][0]=i+1;
					Shape[S][1]=j;
					if (S<4) S+=1;
					}
				}
			}
		RefreshGrid();
		for(int i=0; i<4; i++) grid[Shape[i][0]][Shape[i][1]]=1;
		}
		
	public static void MovePositionLeft(){
		int [][] Shape = new int [4][2];
		int S=0;
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1 && S<4){
					Shape[S][0]=i;
					Shape[S][1]=j-1;
					if (S<4) S+=1;
					}
				}
			}
		RefreshGrid();
		for(int i=0; i<4; i++) grid[Shape[i][0]][Shape[i][1]]=1;
		}
	public static void MovePositionRight(){
		int [][] Shape = new int [4][2];
		int S=0;
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1 && S<4){
					Shape[S][0]=i;
					Shape[S][1]=j+1;
					if (S<4) S+=1;
					}
				}
			}
		RefreshGrid();
		for(int i=0; i<4; i++) grid[Shape[i][0]][Shape[i][1]]=1;
		}
	public static boolean MovePositionDownPossible(){
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1){
					if (i+1>=21) return false;
					else if (grid[i+1][j]>1) return false;
					}
				}
			}
		return true;
		}
	public static boolean MovePositionLeftPossible(){
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1){
					if (j-1<=-1) return false;
					else if (grid[i][j-1]>1) return false;
					}
				}
			}
		return true;
		}
	public static boolean MovePositionRightPossible(){
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1){
					if (j+1>=10) return false;
					else if (grid[i][j+1]>1) return false;
					}
				}
			}
		return true;
		}


	public static void FreezePosition(){
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1){
					if (CurrentType=='A' || CurrentType=='D' || CurrentType=='G'){
						grid[i][j]=2;
						}
					if (CurrentType=='B' || CurrentType=='E'){
						grid[i][j]=3;
						}
					if (CurrentType=='C' || CurrentType=='F'){
						grid[i][j]=4;
						}
					}
				}
			}
		}
	public boolean IsThereLineBreak(){
		int C=0;
		for(int i=0; i<21; i++){
			C=0;
			for(int j=0; j<10; j++){
				if (grid[i][j]>1) C+=1;
				if (C==10) return true;
				}
			}
		return false;
		}
	public int LineBreakI(){
		int C=0;
		int I=-1;
		for(int i=0; i<21; i++){
			C=0;
			for(int j=0; j<10; j++){
				if (grid[i][j]>1) C+=1;
				if (C==10) I=i;
				}
			}
		return I;
		}
	public void LineBreak(int I){
		for(int j=0; j<10; j++) grid[I][j]=0;
		if (I>0){
			//int [][] NewGrid = [21][10];
			for(int i=I-1; i>-1; i--){
				for(int j=0; j<10; j++){
					if (grid[i][j]==2) grid[i+1][j]=2;
					if (grid[i][j]==3) grid[i+1][j]=3;
					if (grid[i][j]==4) grid[i+1][j]=4;
					if (i-1>-1){
						if (grid[i][j]>1 && grid[i-1][j]==0) grid[i][j]=0;
						}
					}
				}
			}
		}
	public static  void Rotation(int R){
		int [][] Shape = new int [4][2];
		int S=0;
		int I=0;
		int J=0;
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){			
				if (grid[i][j]==1 && S<4){
					Shape[S][0]=i;
					Shape[S][1]=j;
					S+=1;
					}
				}
			}
		//if (CurrentType!='D') RefreshGrid();
		if (CurrentType=='A'){
			if (Rotation==0){
				if (R==1 && RotationPossible(Shape[1][0],Shape[1][1],'A',0)){
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]][Shape[1][1]-1]=1;
					grid[Shape[1][0]][Shape[1][1]+1]=1;
					grid[Shape[1][0]+1][Shape[1][1]]=1;
					}
				if (R==3 && RotationPossible(Shape[2][0],Shape[2][1],'A',0)){
					RefreshGrid();
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]][Shape[2][1]-1]=1;
					grid[Shape[2][0]][Shape[2][1]+1]=1;
					grid[Shape[2][0]+1][Shape[2][1]]=1;
					}
				else Rotation=R;
				}
			else if (Rotation==1){
				if (R==0 && RotationPossible(Shape[1][0],Shape[1][1],'A',1)){
					RefreshGrid();
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]-1][Shape[1][1]]=1;
					grid[Shape[1][0]+1][Shape[1][1]]=1;
					grid[Shape[1][0]][Shape[1][1]+1]=1;
					}
				else Rotation=R;
				if (R==2 && RotationPossible(Shape[2][0],Shape[2][1],'A',1)){
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]-1][Shape[2][1]]=1;
					grid[Shape[2][0]+1][Shape[2][1]]=1;
					grid[Shape[2][0]][Shape[2][1]+1]=1;
					}
				}
			else if (Rotation==2){
				if (R==3 && RotationPossible(Shape[2][0],Shape[2][1],'A',2)){
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]][Shape[2][1]-1]=1;
					grid[Shape[2][0]][Shape[2][1]+1]=1;
					grid[Shape[2][0]-1][Shape[2][1]]=1;
					}
				if (R==1 && RotationPossible(Shape[1][0],Shape[1][1],'A',2)){
					RefreshGrid();
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]][Shape[1][1]-1]=1;
					grid[Shape[1][0]][Shape[1][1]+1]=1;
					grid[Shape[1][0]-1][Shape[1][1]]=1;
					}
				else Rotation=R;
				}
			else if (Rotation==3){
				if (R==0 && RotationPossible(Shape[1][0],Shape[1][1],'A',3)){
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]-1][Shape[1][1]]=1;
					grid[Shape[1][0]+1][Shape[1][1]]=1;
					grid[Shape[1][0]][Shape[1][1]-1]=1;
					}
				if (R==2 && RotationPossible(Shape[2][0],Shape[2][1],'A',3)){
					RefreshGrid();
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]-1][Shape[2][1]]=1;
					grid[Shape[2][0]+1][Shape[2][1]]=1;
					grid[Shape[2][0]][Shape[2][1]-1]=1;
					}
				else Rotation=R;
				}				
			}
		else if (CurrentType=='B'){
			if (Rotation==0){
				if (R==1 && RotationPossible(Shape[2][0],Shape[2][1],'B',0)){
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]][Shape[2][1]-1]=1;
					grid[Shape[2][0]][Shape[2][1]+1]=1;
					grid[Shape[2][0]+1][Shape[2][1]+1]=1;
					}
				if (R==3 && RotationPossible(Shape[1][0],Shape[1][1],'B',0)){
					RefreshGrid();
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]][Shape[1][1]-1]=1;
					grid[Shape[1][0]][Shape[1][1]+1]=1;
					grid[Shape[1][0]+1][Shape[1][1]+1]=1;
					}
				else Rotation=R;
				}
			else if (Rotation==1){
				if (R==0 && RotationPossible(Shape[1][0],Shape[1][1],'B',1)){
					RefreshGrid();
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]-1][Shape[1][1]]=1;
					grid[Shape[1][0]+1][Shape[1][1]]=1;
					grid[Shape[1][0]-1][Shape[1][1]+1]=1;
					}
				else Rotation=R;
				if (R==2 && RotationPossible(Shape[2][0],Shape[2][1],'B',1)){
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]-1][Shape[2][1]]=1;
					grid[Shape[2][0]+1][Shape[2][1]]=1;
					grid[Shape[2][0]-1][Shape[2][1]+1]=1;
					}
				}
			else if (Rotation==2){
				if (R==1 && RotationPossible(Shape[2][0],Shape[2][1],'B',2)){
					RefreshGrid();
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]][Shape[2][1]-1]=1;
					grid[Shape[2][0]][Shape[2][1]+1]=1;
					grid[Shape[2][0]-1][Shape[2][1]-1]=1;
					}
				else Rotation=R;
				if (R==3 && RotationPossible(Shape[1][0],Shape[1][1],'B',2)){
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]][Shape[1][1]-1]=1;
					grid[Shape[1][0]][Shape[1][1]+1]=1;
					grid[Shape[1][0]-1][Shape[1][1]-1]=1;
					}
				}
			else if (Rotation==3){
				if (R==2 && RotationPossible(Shape[2][0],Shape[2][1],'B',3)){
					RefreshGrid();
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]-1][Shape[2][1]]=1;
					grid[Shape[2][0]+1][Shape[2][1]]=1;
					grid[Shape[2][0]+1][Shape[2][1]-1]=1;
					}
				else Rotation=R;
				if (R==0 && RotationPossible(Shape[1][0],Shape[1][1],'B',3)){
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]-1][Shape[1][1]]=1;
					grid[Shape[1][0]+1][Shape[1][1]]=1;
					grid[Shape[1][0]+1][Shape[1][1]-1]=1;
					}
				}			
			}
		else if (CurrentType=='C'){
			if ((Rotation==0 || Rotation==2) && RotationPossible(Shape[1][0],Shape[1][1],'C',Rotation)){
				RefreshGrid();
				grid[Shape[1][0]][Shape[1][1]]=1;
				grid[Shape[1][0]][Shape[1][1]-1]=1;
				grid[Shape[1][0]+1][Shape[1][1]]=1;
				grid[Shape[1][0]+1][Shape[1][1]+1]=1;
				}
			//else Rotation=R;
			else if ((Rotation==1 || Rotation==3) && RotationPossible(Shape[1][0],Shape[1][1],'C',Rotation)){
				RefreshGrid();
				grid[Shape[1][0]][Shape[1][1]]=1;
				grid[Shape[1][0]+1][Shape[1][1]]=1;
				grid[Shape[1][0]][Shape[1][1]+1]=1;
				grid[Shape[1][0]-1][Shape[1][1]+1]=1;
				}
			else Rotation=R;
			}

		else if (CurrentType=='E'){
			if ((Rotation==0 || Rotation==2) && RotationPossible(Shape[1][0],Shape[1][1],'E',Rotation)){
				RefreshGrid();
				grid[Shape[1][0]][Shape[1][1]]=1;
				grid[Shape[1][0]][Shape[1][1]+1]=1;
				grid[Shape[1][0]+1][Shape[1][1]]=1;
				grid[Shape[1][0]+1][Shape[1][1]-1]=1;
				}
			//else Rotation=R;
			else if ((Rotation==1 || Rotation==3) && RotationPossible(Shape[0][0],Shape[0][1],'E',Rotation)){
				RefreshGrid();
				grid[Shape[0][0]][Shape[0][1]]=1;
				grid[Shape[0][0]-1][Shape[0][1]]=1;
				grid[Shape[0][0]][Shape[0][1]+1]=1;
				grid[Shape[0][0]+1][Shape[0][1]+1]=1;
				}
			else Rotation=R;
			}
		else if (CurrentType=='F'){
			if (Rotation==0){
				if (R==3 && RotationPossible(Shape[2][0],Shape[2][1],'F',Rotation)){
					RefreshGrid();
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]][Shape[2][1]-1]=1;
					grid[Shape[2][0]][Shape[2][1]+1]=1;
					grid[Shape[2][0]-1][Shape[2][1]+1]=1;
					}
				else Rotation=R;
				if (R==1 && RotationPossible(Shape[1][0],Shape[1][1],'F',Rotation)){
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]][Shape[1][1]-1]=1;
					grid[Shape[1][0]][Shape[1][1]+1]=1;
					grid[Shape[1][0]-1][Shape[1][1]+1]=1;
					}
				}
			else if (Rotation==1){
				if (R==0 && RotationPossible(Shape[1][0],Shape[1][1],'F',Rotation)){
					RefreshGrid();
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]-1][Shape[1][1]]=1;
					grid[Shape[1][0]+1][Shape[1][1]]=1;
					grid[Shape[1][0]+1][Shape[1][1]+1]=1;
					}
				else Rotation=R;
				if (R==2 && RotationPossible(Shape[2][0],Shape[2][1],'F',Rotation)){
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]-1][Shape[2][1]]=1;
					grid[Shape[2][0]+1][Shape[2][1]]=1;
					grid[Shape[2][0]+1][Shape[2][1]+1]=1;
					}
				}
			else if (Rotation==2){
				if (R==3 && RotationPossible(Shape[2][0],Shape[2][1],'F',Rotation)){
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]][Shape[2][1]-1]=1;
					grid[Shape[2][0]][Shape[2][1]+1]=1;
					grid[Shape[2][0]+1][Shape[2][1]-1]=1;
					}
				if (R==1 && RotationPossible(Shape[1][0],Shape[1][1],'F',Rotation)){
					RefreshGrid();
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]][Shape[1][1]-1]=1;
					grid[Shape[1][0]][Shape[1][1]+1]=1;
					grid[Shape[1][0]+1][Shape[1][1]-1]=1;
					}
				else Rotation=R;
				}
			else if (Rotation==3){
				if (R==2 && RotationPossible(Shape[2][0],Shape[2][1],'F',Rotation)){
					RefreshGrid();
					grid[Shape[2][0]][Shape[2][1]]=1;
					grid[Shape[2][0]-1][Shape[2][1]]=1;
					grid[Shape[2][0]+1][Shape[2][1]]=1;
					grid[Shape[2][0]-1][Shape[2][1]-1]=1;
					}
				else Rotation=R;
				if (R==0 && RotationPossible(Shape[1][0],Shape[1][1],'F',Rotation)){
					grid[Shape[1][0]][Shape[1][1]]=1;
					grid[Shape[1][0]-1][Shape[1][1]]=1;
					grid[Shape[1][0]+1][Shape[1][1]]=1;
					grid[Shape[1][0]-1][Shape[1][1]-1]=1;
					}
				}			
			}
		else if (CurrentType=='G'){
			if (Rotation==0 && RotationPossible(Shape[2][0],Shape[2][1],'G',Rotation)){
				RefreshGrid();
				for(int i=0; i<4; i++) grid[Shape[2][0]][Shape[2][1]-2+i]=1;
				}
			else if (Rotation==1 && RotationPossible(Shape[2][0],Shape[2][1],'G',Rotation)){
				RefreshGrid();
				for(int i=0; i<4; i++) grid[Shape[2][0]-2+i][Shape[2][1]]=1;
				}
			else if (Rotation==2 && RotationPossible(Shape[1][0],Shape[1][1],'G',Rotation)){
				RefreshGrid();
				for(int i=0; i<4; i++) grid[Shape[1][0]][Shape[1][1]-2+i]=1;
				}
			else if (Rotation==3 && RotationPossible(Shape[1][0],Shape[1][1],'G',Rotation)){
				RefreshGrid();
				for(int i=0; i<4; i++) grid[Shape[1][0]-1+i][Shape[1][1]]=1;
				}
			else Rotation=R;
			}
		//if (Rotation==R) System.out.println("Bon");
		}
	public static boolean RotationPossible(int i, int j, char type, int NewRotation){
		//int I = Shape[S][0];
		//int J = Shape[S][1];
		int I = i;
		int J = j;
		if (type=='A'){
			if (NewRotation==0){
				if (J-1==-1 || J+1==10 || I+1==21) return false;
				else if (grid[I][J-1]>1 || grid[I][J+1]>1 || grid[I+1][J]>1) return false;
				}
			if (NewRotation==1){
				if (I-1==-1 || I+1==21 || J+1==10) return false;
				else if (grid[I-1][J]>1 || grid[I+1][J]>1 || grid[I][J+1]>1) return false;
				}
			if (NewRotation==2){
				if (J-1==-1 || J+1==10 || I-1==-1) return false;
				else if (grid[I][J-1]>1 || grid[I][J+1]>1 || grid[I-1][J]>1) return false;
				}
			if (NewRotation==3){
				if (I-1==-1 || I+1==21 || J-1==-1) return false;
				else if (grid[I-1][J]>1 || grid[I+1][J]>1 || grid[I][J-1]>1) return false;
				}
			}
		if (type=='B'){
			if (NewRotation==0){
				if (J-1==-1 || J+1==10 || I+1==21) return false;
				else if (grid[I][J-1]>1 || grid[I][J+1]>1 || grid[I+1][J+1]>1) return false;
				}
			if (NewRotation==1){
				if (I-1==-1 || I+1==21 || J+1==10) return false;
				else if (grid[I-1][J]>1 || grid[I+1][J]>1 || grid[I-1][J+1]>1) return false;
				}
			if (NewRotation==2){
				if (J-1==-1 || J+1==10 || I-1==-1) return false;
				else if (grid[I][J-1]>1 || grid[I][J+1]>1 || grid[I-1][J-1]>1) return false;
				}
			if (NewRotation==3){
				if (I-1==-1 || I+1==21 || J-1==-1) return false;
				else if (grid[I-1][J]>1 || grid[I+1][J]>1 || grid[I+1][J-1]>1) return false;
				}
			}
		if (type=='C'){
			if (NewRotation==0 || NewRotation==2){
				if (J-1==-1 || J+1==10 || I+1==21) return false;
				else if (grid[I][J-1]>1 || grid[I+1][J]>1 || grid[I+1][J+1]>1) return false;
				}
			if (NewRotation==1 || NewRotation==3){
				if (I-1==-1 || I+1==21 || J+1==10) return false;
				else if (grid[I+1][J]>1 || grid[I][J+1]>1 || grid[I-1][J+1]>1) return false;
				}
			}
		if (type=='E'){
			if (NewRotation==0 || NewRotation==2){
				if (J-1==-1 || J+1==10 || I+1==21) return false;
				else if (grid[I][J+1]>1 || grid[I+1][J]>1 || grid[I+1][J-1]>1) return false;
				}
			if (NewRotation==1 || NewRotation==3){
				if (I-1==-1 || I+1==21 || J+1==10) return false;
				else if (grid[I-1][J]>1 || grid[I][J+1]>1 || grid[I+1][J+1]>1) return false;
				}
			}
		if (type=='F'){
			if (NewRotation==0){
				if (J-1==-1 || J+1==10 || I+1==21) return false;
				else if (grid[I][J-1]>1 || grid[I][J+1]>1 || grid[I+1][J-1]>1) return false;
				}
			if (NewRotation==1){
				if (I-1==-1 || I+1==21 || J+1==10) return false;
				else if (grid[I-1][J]>1 || grid[I+1][J]>1 || grid[I+1][J+1]>1) return false;
				}
			if (NewRotation==2){
				if (J-1==-1 || J+1==10 || I-1==-1) return false;
				else if (grid[I][J-1]>1 || grid[I][J+1]>1 || grid[I-1][J+1]>1) return false;
				}
			if (NewRotation==3){
				if (I-1==-1 || I+1==21 || J-1==-1) return false;
				else if (grid[I-1][J]>1 || grid[I+1][J]>1 || grid[I-1][J-1]>1) return false;
				}
			}
		if (type=='G'){
			if (NewRotation==0 || NewRotation==2){
				if (J-2<=-1 || J+1==10) return false;
				else if (grid[I][J-2]>1 || grid[I][J-1]>1 || grid[I][J+1]>1) return false;
				}
			if (NewRotation==1){
				if (I-2<=-1 || I+1==21) return false;
				else if (grid[I-2][J]>1 || grid[I-1][J]>1 || grid[I+1][J]>1) return false;
				}
			if (NewRotation==3){
				if (I-1==-1 || I+2>=21) return false;
				else if (grid[I-1][J]>1 || grid[I+1][J]>1 || grid[I+2][J]>1) return false;
				}
			}
		return true;
		}
					
	public static boolean IsGridCurrentEmpty(){
		for(int i=0; i<21; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==1) return false;
				}
			}
		return true;
		}
	public static void ScoringLine(int I){
		//int I = LineBreakI();
		int adgScore=0;
		int bfScore=0;
		int ceScore=0;
		for(int j=0; j<10; j++){
			if (grid[I][j]==2){
				adgScore+=1;
				//Score+=10*adgScore;
				switch(adgScore){
					case 1:Score+=1;break;
					case 2:Score+=10;break;
					case 3:Score+=20;break;
					case 4:Score+=50;break;
					case 5:Score+=100;break;
					case 6:Score+=200;break;
					case 7:Score+=500;break;
					case 8:Score+=1000;break;
					case 9:Score+=2000;break;
					case 10:Score+=5000;break;
					}
				}
			if (grid[I][j]==3){
				bfScore+=1;
				//Score+=10*bfScore;
				switch(bfScore){
					case 1:Score+=1;break;
					case 2:Score+=10;break;
					case 3:Score+=20;break;
					case 4:Score+=50;break;
					case 5:Score+=100;break;
					case 6:Score+=200;break;
					case 7:Score+=500;break;
					case 8:Score+=1000;break;
					case 9:Score+=2000;break;
					case 10:Score+=5000;break;
					}
				}
			if (grid[I][j]==4){
				ceScore+=1;
				//Score+=10*ceScore;
				switch(ceScore){
					case 1:Score+=1;break;
					case 2:Score+=10;break;
					case 3:Score+=20;break;
					case 4:Score+=50;break;
					case 5:Score+=100;break;
					case 6:Score+=200;break;
					case 7:Score+=500;break;
					case 8:Score+=1000;break;
					case 9:Score+=2000;break;
					case 10:Score+=5000;break;
					}
				}
			}
		}
	public static void SuperScoring(int I){
		int adgScore=0;
		int bfScore=0;
		int ceScore=0;
		for(int i=I; i<I+2; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==2){
					adgScore+=1;
					switch(adgScore){
						case 1:Score+=1;break;
						case 2:Score+=10;break;
						case 3:Score+=20;break;
						case 4:Score+=50;break;
						case 5:Score+=100;break;
						case 6:Score+=200;break;
						case 7:Score+=500;break;
						case 8:Score+=1000;break;
						case 9:Score+=2000;break;
						case 10:Score+=5000;break;
						case 11:Score+=6000;break;
						case 12:Score+=7000;break;
						case 13:Score+=8000;break;
						case 14:Score+=9000;break;
						case 15:Score+=10000;break;
						case 16:Score+=20000;break;
						case 17:Score+=30000;break;
						case 18:Score+=40000;break;
						case 19:Score+=50000;break;
						case 20:Score+=100000;break;
						}
					}
				if (grid[i][j]==3){
					bfScore+=1;
					switch(bfScore){
						case 1:Score+=1;break;
						case 2:Score+=10;break;
						case 3:Score+=20;break;
						case 4:Score+=50;break;
						case 5:Score+=100;break;
						case 6:Score+=200;break;
						case 7:Score+=500;break;
						case 8:Score+=1000;break;
						case 9:Score+=2000;break;
						case 10:Score+=5000;break;
						case 11:Score+=6000;break;
						case 12:Score+=7000;break;
						case 13:Score+=8000;break;
						case 14:Score+=9000;break;
						case 15:Score+=10000;break;
						case 16:Score+=20000;break;
						case 17:Score+=30000;break;
						case 18:Score+=40000;break;
						case 19:Score+=50000;break;
						case 20:Score+=100000;break;
						}
					}
				if (grid[i][j]==4){
					ceScore+=1;
					switch(ceScore){
						case 1:Score+=1;break;
						case 2:Score+=10;break;
						case 3:Score+=20;break;
						case 4:Score+=50;break;
						case 5:Score+=100;break;
						case 6:Score+=200;break;
						case 7:Score+=500;break;
						case 8:Score+=1000;break;
						case 9:Score+=2000;break;
						case 10:Score+=5000;break;
						case 11:Score+=6000;break;
						case 12:Score+=7000;break;
						case 13:Score+=8000;break;
						case 14:Score+=9000;break;
						case 15:Score+=10000;break;
						case 16:Score+=20000;break;
						case 17:Score+=30000;break;
						case 18:Score+=40000;break;
						case 19:Score+=50000;break;
						case 20:Score+=100000;break;
						}
					}
				}
			}						
		}
		

	public static void UltraScoring(int I){
		int adgScore=0;
		int bfScore=0;
		int ceScore=0;
		for(int i=I; i<I+3; i++){
			for(int j=0; j<10; j++){
				if (grid[i][j]==2){
					adgScore+=1;
					switch(adgScore){
						case 1:Score+=1;break;
						case 2:Score+=10;break;
						case 3:Score+=20;break;
						case 4:Score+=50;break;
						case 5:Score+=100;break;
						case 6:Score+=200;break;
						case 7:Score+=500;break;
						case 8:Score+=1000;break;
						case 9:Score+=2000;break;
						case 10:Score+=5000;break;
						case 11:Score+=6000;break;
						case 12:Score+=7000;break;
						case 13:Score+=8000;break;
						case 14:Score+=9000;break;
						case 15:Score+=10000;break;
						case 16:Score+=20000;break;
						case 17:Score+=30000;break;
						case 18:Score+=40000;break;
						case 19:Score+=50000;break;
						case 20:Score+=100000;break;
						case 21:Score+=150000;break;
						case 22:Score+=200000;break;
						case 23:Score+=250000;break;
						case 24:Score+=300000;break;
						case 25:Score+=350000;break;
						case 26:Score+=400000;break;
						case 27:Score+=450000;break;
						case 28:Score+=500000;break;
						case 29:Score+=777777;break;
						case 30:Score=Top;break;
						}
					}
				if (grid[i][j]==3){
					bfScore+=1;
					switch(bfScore){
						case 1:Score+=1;break;
						case 2:Score+=10;break;
						case 3:Score+=20;break;
						case 4:Score+=50;break;
						case 5:Score+=100;break;
						case 6:Score+=200;break;
						case 7:Score+=500;break;
						case 8:Score+=1000;break;
						case 9:Score+=2000;break;
						case 10:Score+=5000;break;
						case 11:Score+=6000;break;
						case 12:Score+=7000;break;
						case 13:Score+=8000;break;
						case 14:Score+=9000;break;
						case 15:Score+=10000;break;
						case 16:Score+=20000;break;
						case 17:Score+=30000;break;
						case 18:Score+=40000;break;
						case 19:Score+=50000;break;
						case 20:Score+=100000;break;
						case 21:Score+=150000;break;
						case 22:Score+=200000;break;
						case 23:Score+=250000;break;
						case 24:Score+=300000;break;
						case 25:Score+=350000;break;
						case 26:Score+=400000;break;
						case 27:Score+=450000;break;
						case 28:Score+=500000;break;
						case 29:Score+=777777;break;
						case 30:Score=Top;break;
						}
					}
				if (grid[i][j]==4){
					ceScore+=1;
					switch(ceScore){
						case 1:Score+=1;break;
						case 2:Score+=10;break;
						case 3:Score+=20;break;
						case 4:Score+=50;break;
						case 5:Score+=100;break;
						case 6:Score+=200;break;
						case 7:Score+=500;break;
						case 8:Score+=1000;break;
						case 9:Score+=2000;break;
						case 10:Score+=5000;break;
						case 11:Score+=6000;break;
						case 12:Score+=7000;break;
						case 13:Score+=8000;break;
						case 14:Score+=9000;break;
						case 15:Score+=10000;break;
						case 16:Score+=20000;break;
						case 17:Score+=30000;break;
						case 18:Score+=40000;break;
						case 19:Score+=50000;break;
						case 20:Score+=100000;break;
						case 21:Score+=150000;break;
						case 22:Score+=200000;break;
						case 23:Score+=250000;break;
						case 24:Score+=300000;break;
						case 25:Score+=350000;break;
						case 26:Score+=400000;break;
						case 27:Score+=450000;break;
						case 28:Score+=500000;break;
						case 29:Score+=777777;break;
						case 30:Score=Top;break;
						}
					}
				}
			}						
		}

	public static boolean IsLineFullColor(int I){
		int C = grid[I][0];
		for(int j=0; j<10; j++){
			if (grid[I][j]!=C) return false;
			}
		return true;
		}		
	}
//****************************************************************************************
//****************************************************************************************
//****************************************************************************************
class Time extends Thread{
	public void run(){
		try{
			sleep(1000-10*Pan.Level);
			} catch (InterruptedException e){}
		Pan.TimeOn=0;
		}
	}
class Touche implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (k=='g') Pan.afficherGrid();

		if (c==0x25 && Pan.MovePositionLeftPossible()) Pan.MovePositionLeft();
		if (c==0x27 && Pan.MovePositionRightPossible()) Pan.MovePositionRight();
		//if (c==0x25) Pan.MovePositionLeftOn=1;
		//if (c==0x27) Pan.MovePositionRightOn=1;
		//if (c==0x28) Pan.MovePositionDownOn=1;
		if (c==0x28 && Pan.MovePositionDownPossible()){
			if (Pan.IsGridCurrentEmpty()==false) Pan.MovePositionDown();
			}

		if (k=='t') Pan.TIMEON=1;

		if (k=='x'){
			int R=Pan.Rotation;
			Pan.Rotation+=1;
			if (Pan.Rotation==4) Pan.Rotation=0;
			Pan.Rotation(R);
			if (Pan.Rotation==-1) Pan.Rotation=3;
			}
		if (k=='s') Pan.Score+=10;
		if (k=='S') Pan.Score+=1000;

		if (k=='b') Pan.BestScore+=10;
		if (k=='B') Pan.BestScore+=1000;
		if (k=='L') Pan.BestLevel+=1;



		if (Pan.Game==2){
			Pan.ResetGrid();
			Pan.BestScore=Pan.Score;
			Pan.BestLevel=Pan.Level;
			Pan.Level=Pan.Score=Pan.StatA=Pan.StatB=Pan.StatC=Pan.StatD=Pan.StatE=Pan.StatF=Pan.StatG=Pan.Lines=Pan.Game=Pan.NextOn=0;
			Pan.Top=10000;
			}
		if (Pan.Game==3){
			Pan.Game=Pan.NextOn=0;
			Pan.Score=0;
			Pan.Level+=1;
			Pan.Top=10000 + Pan.Level*5000;
			}

		if (Pan.Game==-1) Pan.Game=0;
		}
	public void keyReleased(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (c==0x25) Pan.MovePositionLeftOn=0;
		if (c==0x27) Pan.MovePositionRightOn=0;
		if (c==0x28) Pan.MovePositionDownOn=0;

		if (k=='t') Pan.TIMEON=0;
		if (k=='T') Pan.Score=Pan.Top;

		if (k=='r') Pan.ResetGrid();

		if (k=='g') Pan.Game=2;
		if (k=='G') Pan.Game=3;
		}
	}
class Souris implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1) System.out.println(X+"\t"+Y);
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}

public class Tetris{
	public static void main(String[] args){
		Fen fen = new Fen("Tetris",1000+16, 800+40);
		Pan pan = new Pan(1000, 800);
		fen.setContentPane(pan);
		while (true) pan.repaint();
		}
}