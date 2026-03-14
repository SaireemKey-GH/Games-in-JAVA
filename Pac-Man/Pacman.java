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
//243 bubbles
//game=2 is defeat
//game=3 is win, bubble all eated
class Pan extends JPanel{
	static int L=0;
	static int H=0;
	static int Pacx=386;
	static int Pacy=748;
	static int Pactour=0;
	static int Sens=0;
	static int SensAim=0;
	static int Redx=386;
	static int Redy=412;
	static int RedSens=0;
	static int RedTime=0;
	static int RedTimeOn=0;
	static int RedEaten=0;
	static int RedEatenNextPoint=0;
	static int RedScoreEaten=0;
	static int RedBeenEaten=0;
	static int RedScoreEatenx=0;
	static int RedScoreEateny=0;
	static int RedScoreEatenyMax=0;
	static int RedExitHome=0;
	static int [] RedHomePath = new int [1];
	static int Pinkx=386;
	static int Pinky=500;
	static int PinkTime=0;
	static int PinkTimeOn=0;
	static int PinkSens=3;
	static int PinkEaten=0;
	static int PinkEatenNextPoint=0;
	static int PinkScoreEaten=0;
	static int PinkBeenEaten=0;
	static int PinkScoreEatenx=0;
	static int PinkScoreEateny=0;
	static int PinkScoreEatenyMax=0;
	static int PinkExitHome=0;
	static int [] PinkHomePath = new int [1];
	static int Bluex=326;
	static int Bluey=500;
	static int BlueTime=0;
	static int BlueTimeOn=0;
	static int BlueSens=1;
	static int BlueEaten=0;
	static int BlueEatenNextPoint=0;
	static int BlueScoreEaten=0;
	static int BlueBeenEaten=0;
	static int BlueScoreEatenx=0;
	static int BlueScoreEateny=0;
	static int BlueScoreEatenyMax=0;
	static int BlueExitHome=0;
	static int [] BlueHomePath = new int [1];
	static int Orangex=446;
	static int Orangey=500;
	static int OrangeTime=0;
	static int OrangeTimeOn=0;
	static int OrangeSens=1;
	static int OrangeEaten=0;
	static int OrangeBeenEaten=0;
	static int OrangeEatenNextPoint=0;
	static int OrangeScoreEaten=0;
	static int OrangeScoreEatenx=0;
	static int OrangeScoreEateny=0;
	static int OrangeScoreEatenyMax=0;
	static int OrangeExitHome=0;
	static int [] OrangeHomePath = new int [1];
	static int FantomInOn=0;//1 for blue; 3 for pink and 5 for orange
	static int Time=0;
	static int TimeOn=0;
	static int Bubble=0;
	static int Bubblexy[][] = new int [244][3];
	static int TurnPointxy[][] = new int [64][2];
	static int MapPoint[][] = new int [66+2][2];
	static int [][] FHomeShortestPath = {{0,6,7,8,16,17,57,64,65},{1,7,8,16,17,57,64,65},{2,9,8,16,17,57,64,65},{3,10,11,19,18,58,64,65},{4,12,11,19,18,58,64,65},{5,13,12,11,19,18,58,64,65},
						{6,7,8,16,17,57,64,65},{7,8,16,17,57,64,65},{8,16,17,57,64,65},{9,8,16,17,57,64,65},{10,11,19,18,58,64,65},{11,19,18,58,64,65},{12,11,19,18,58,64,65},{13,12,11,19,18,58,64,65},
						{14,15,22,60,56,57,64,65},{15,22,60,56,57,64,65},{16,17,57,64,65},{17,57,64,65},{18,58,64,65},{19,18,58,64,65},{20,23,61,59,58,64,65},{21,20,23,61,59,58,64,65},
						{22,60,56,57,64,65},{23,61,59,58,64,65},{24,25,22,60,56,57,64,65},{25,22,60,56,57,64,65},{26,62,60,56,57,64,65},{27,26,62,60,56,57,64,65},{28,29,63,61,59,58,64,65},{29,63,61,59,58,64,65},{30,23,61,59,58,64,65},{31,30,23,61,59,58,64,65},
						{32,24,25,22,60,56,57,64,65},{33,32,24,25,22,60,56,57,64,65},{34,25,22,60,56,57,64,65},{35,34,25,22,60,56,57,64,65},{36,27,26,62,60,56,57,64,65},{37,28,29,63,61,59,58,64,65},{38,39,30,23,61,59,58,64,65},{39,30,23,61,59,58,64,65},{40,41,31,30,23,61,59,58,64,65},{41,31,30,23,61,59,58,64,65},
						{42,43,44,34,25,22,60,56,57,64,65},{43,44,34,25,22,60,56,57,64,65},{44,34,25,22,60,56,57,64,65},{45,35,34,25,22,60,56,57,64,65},{46,45,35,34,25,22,60,56,57,64,65},{47,48,38,39,30,23,61,59,58,64,65},{48,38,39,30,23,61,59,58,64,65},{49,39,30,23,61,59,58,64,65},{50,49,39,30,23,61,59,58,64,65},{51,50,49,39,30,23,61,59,58,64,65},
						{52,42,43,44,34,25,22,60,56,57,64,65},{53,46,45,35,34,25,22,60,56,57,64,65},{54,47,48,38,39,30,23,61,59,58,64,65},{55,51,50,49,39,30,23,61,59,58,64,65},
						{56,57,64,65},{57,64,65},{58,64,65},{59,58,64,65},
						{60,56,57,64,65},{61,59,58,64,65},
						{62,60,56,57,64,65},{63,61,59,58,64,65},
						{64,65},
						{65},
						{66,62,60,56,57,64,65},
						{67,63,61,59,58,64,65}};
	static int SUPER=0;
	static int SUPERTimeOn=0;
	static int SUPERTime=0;
	static int EatenRow=0;
	static int ScoreEatenFantom=0;
	static int ScoreBubble=0;
	static int Score=0;
	static int HighScore=0;
	static int Life=2;
	static int UP1=0;
	static int Cherry=0;
	static int StartGame=-1;
	static int Game=0;
	JButton gris = new JButton();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));

		Touche touche = new Touche();		

		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		Souris souris = new Souris();
		this.addMouseListener(souris);
		}
	public void paintComponent(Graphics g){
		g.setFont(new Font("MV Boli", Font.BOLD, 30));
		if (Game==0){
			Pacx=386;
			Pacy=748;
			Sens=0;
			Redx=386;
			Redy=412;
			Bluex=320;
			Bluey=500;
			Pinkx=386;
			Pinky=500;
			Orangex=450;
			Orangey=500;
			RedSens=0;		
			BlueSens=1;
			PinkSens=3;
			OrangeSens=1;
			RedEaten=RedBeenEaten=0;
			BlueEaten=BlueBeenEaten=0;
			PinkEaten=PinkBeenEaten=0;
			OrangeEaten=OrangeBeenEaten=0;
			RedTime=BlueTime=PinkTime=OrangeTime=0;
			SUPER=SUPERTime=SUPERTimeOn=0;
			if (Score>=610) BlueExitHome=1;
			if (Score>=1220) PinkExitHome=1;
			if (Score>=1830) OrangeExitHome=1;
			Bubble=0;
			Score=0;
			EatenRow=0;
			int [] Bx = { 40,  66,  92, 118, 144, 172, 201, 230, 259, 288, 317, 346, 426, 455, 484, 513, 542, 571, 600, 628, 654, 680, 706, 732,  
				    40,	172, 346, 426, 600, 732,
                                    40, 172, 346, 426, 600, 732,
                                    40, 172, 346, 426, 600, 732,
				    40,  66,  92, 118, 144, 172, 198, 224, 251, 283, 315, 346, 373, 399, 426, 457, 489, 521, 548, 574, 600, 628, 654, 680, 706, 732,
				    40, 172, 251, 521, 600, 732,
				    40, 172, 251, 521, 600, 732,
				    40,  66,  92, 118, 144, 172, 251, 283, 315, 346, 426, 457, 489, 521, 600, 628, 654, 680, 706, 732,
				   172, 600,
				   172, 600,
				   172, 600,
				   172, 600,
				   172, 600,
				   172, 600,
				   172, 600,
				   172, 600,
				   172, 600,
				   172, 600,
				   172, 600,
				    40,  66,  92, 118, 144, 172, 198, 224, 251, 283, 315, 346, 426, 457, 489, 521, 548, 574, 600, 628, 654, 680, 706, 732,
				    40, 172, 346, 426, 600, 732,
				    40, 172, 346, 426, 600, 732,
				    40,  67,  94, 172, 198, 224, 251, 283, 315, 346, 426, 457, 489, 521, 548, 574, 600, 678, 705, 732,
				    94, 172, 251, 521, 600, 678,
				    94, 172, 251, 521, 600, 678,
				    40,  67,  94, 120, 146, 172, 251, 283, 315, 346, 426, 457, 489, 521, 600, 626, 652, 678, 705, 732,
				    40, 346, 426, 732,
				    40, 346, 426, 732,
				    40,  66,  92, 118, 144, 172, 201, 230, 259, 288, 317, 346, 373, 399, 426, 455, 484, 513, 542, 571, 600, 628, 654, 680, 706, 732};
 
			int [] By = {120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120,                                       
				   153, 153, 153, 153, 153, 153,
				   186, 186, 186, 186, 186, 186,
				   219, 219, 219, 219, 219, 219,
				   252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252, 252,
				   278, 278, 278, 278, 278, 278,
				   304, 304, 304, 304, 304, 304,
				   331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331, 331,
				   360, 360,
				   388, 388,
				   416, 416,
				   444, 444,
				   472, 472,
				   500, 500,
				   529, 529,
				   557, 557,
				   585, 585,
				   613, 613,
				   641, 641,
				   669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 669, 
				   695, 695, 695, 695, 695, 695,
				   721, 721, 721, 721, 721, 721,
				   748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748,
				   774, 774, 774, 774, 774, 774,
				   800, 800, 800, 800, 800, 800,
				   827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827,
				   853, 853, 853, 853,
				   879, 879, 879, 879,
				   906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906, 906};

			int [] TPx = { 40, 172, 346, 426, 600, 732,  40, 172, 251, 346, 426, 521, 600, 732,  40, 172, 251, 346, 426, 521, 600, 732, 172, 600,  40, 172, 251, 346, 426, 521, 600, 732,  40,  94, 172, 251, 346, 426, 521, 600, 678, 732,  40,  94, 172, 251, 346, 426, 521, 600, 678, 732,  40, 346, 426, 732, 251, 346, 426, 521, 251, 521, 251, 521};
			int [] TPy = {120, 120, 120, 120, 120, 120, 252, 252, 252, 252, 252, 252, 252, 252, 331, 331, 331, 331, 331, 331, 331, 331, 500, 500, 669, 669, 669, 669, 669, 669, 669, 669, 748, 748, 748, 748, 748, 748, 748, 748, 748, 748, 827, 827, 827, 827, 827, 827, 827, 827, 827, 827, 906, 906, 906, 906, 412, 412, 412, 412, 500, 500, 591, 591};

			//System.out.print(Bx.length+"\t"+By.length+"\t"+TPx.length+"\t"+TPy.length);
			for(int i=0; i<244; i++){
				Bubblexy[i][0]=Bx[i];
				Bubblexy[i][1]=By[i];
				}
			for(int i=0; i<64; i++){
				TurnPointxy[i][0]=TPx[i];
				TurnPointxy[i][1]=TPy[i];
				MapPoint[i][0]=TPx[i];
				MapPoint[i][1]=TPy[i];
				}
			MapPoint[64][0]=386;
			MapPoint[64][1]=412;
			MapPoint[65][0]=386;
			MapPoint[65][1]=500;

			MapPoint[66][0]=346;
			MapPoint[66][1]=591;
			MapPoint[67][0]=426;
			MapPoint[67][1]=591;
			Time=TimeOn=0;
			Game=1;
			if (StartGame==-1) Game=5;
			}
		g.setColor(Color.black);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
//*Bubbles

		for(int i=0; i<244; i++){
			if (Bubblexy[i][2]==0){
				if (i==30 || i==35 || i==158 || i==177){
					g.setColor(new Color(250, 155, 130));
					g.fillOval(Bubblexy[i][0]-15, Bubblexy[i][1]-15, 31, 31);
					//if (Pacx==Bubblexy[i][0] && Pacy==Bubblexy[i][1]) SUPER=1;
					}
				else{
					g.setColor(Color.white);
					g.fillRect(Bubblexy[i][0]-1, Bubblexy[i][1]-1, 3, 3);
					}
				}
			//if (Pacx==Bubblexy[i][0] && Pacy==Bubblexy[i][1]) Bubblexy[i][2]=1;
			}
		EatBubble();

//*End Bubbles
		try{
			//Image BG = ImageIO.read(new File("C:\Users\djaya\Desktop\Programmes\Creations\Jeux Retro\Pac-Man\Images\Background.png"));
			Image BG = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/Background.png"));
			Image RedRight = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/RedRight.png"));
			Image RedLeft = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/RedLeft.png"));
			Image RedUp = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/RedUp.png"));
			Image RedDown = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/RedDown.png"));
			Image BlueRight = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/BlueRight.png"));
			Image BlueLeft = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/BlueLeft.png"));
			Image BlueUp = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/BlueUp.png"));
			Image BlueDown = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/BlueDown.png"));
			Image PinkRight = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/PinkRight.png"));
			Image PinkLeft = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/PinkLeft.png"));
			Image PinkUp = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/PinkUp.png"));
			Image PinkDown = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/PinkDown.png"));
			Image OrangeRight = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/OrangeRight.png"));
			Image OrangeLeft = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/OrangeLeft.png"));
			Image OrangeUp = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/OrangeUp.png"));
			Image OrangeDown = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/OrangeDown.png"));
			Image FantomEatable = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/FantomEatable.png"));
			Image FEatenRight = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/FantomEatenRight.png"));
			Image FEatenLeft = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/FantomEatenLeft.png"));
			Image FEatenUp = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/FantomEatenUp.png"));
			Image FEatenDown = ImageIO.read(new File("C:/Users/djaya/Desktop/Programmes/Creations/Jeux Retro/Pac-Man/Images/FantomEatenDown.png"));
			g.drawImage(BG,0,0,773, 1000, this);
			if ((SUPER==0 || RedBeenEaten==1) && RedEaten==0){
				if (RedSens==0) g.drawImage(RedLeft, Redx-23, Redy-23, 47, 47, this);
				else if (RedSens==1) g.drawImage(RedUp, Redx-23, Redy-23, 47, 47, this);
				else if (RedSens==2) g.drawImage(RedRight, Redx-23, Redy-23, 47, 47, this);
				else if (RedSens==3) g.drawImage(RedDown, Redx-23, Redy-23, 47, 47, this);
				}
			if (SUPER==1 && RedEaten==0 && RedBeenEaten==0) g.drawImage(FantomEatable, Redx-23, Redy-23, 47, 47, this);
			if (RedEaten==1){
				if (RedSens==0) g.drawImage(FEatenLeft, Redx-23, Redy-23, 47, 47, this);
				else if (RedSens==1) g.drawImage(FEatenUp, Redx-23, Redy-23, 47, 47, this);
				else if (RedSens==2) g.drawImage(FEatenRight, Redx-23, Redy-23, 47, 47, this);
				else if (RedSens==3) g.drawImage(FEatenDown, Redx-23, Redy-23, 47, 47, this);
				}
			if ((SUPER==0 || BlueBeenEaten==1) && BlueEaten==0){
				if (BlueSens==0) g.drawImage(BlueLeft, Bluex-23, Bluey-23, 47, 47, this);
				else if (BlueSens==1) g.drawImage(BlueUp, Bluex-23, Bluey-23, 47, 47, this);
				else if (BlueSens==2) g.drawImage(BlueRight, Bluex-23, Bluey-23, 47, 47, this);
				else if (BlueSens==3) g.drawImage(BlueDown, Bluex-23, Bluey-23, 47, 47, this);
				}
			if (SUPER==1 && BlueEaten==0 && BlueBeenEaten==0) g.drawImage(FantomEatable, Bluex-23, Bluey-23, 47, 47, this);
			if (BlueEaten==1){
				if (BlueSens==0) g.drawImage(FEatenLeft, Bluex-23, Bluey-23, 47, 47, this);
				else if (BlueSens==1) g.drawImage(FEatenUp, Bluex-23, Bluey-23, 47, 47, this);
				else if (BlueSens==2) g.drawImage(FEatenRight, Bluex-23, Bluey-23, 47, 47, this);
				else if (BlueSens==3) g.drawImage(FEatenDown, Bluex-23, Bluey-23, 47, 47, this);
				}
			if ((SUPER==0 || PinkBeenEaten==1) && PinkEaten==0){
				if (PinkSens==0) g.drawImage(PinkLeft, Pinkx-23, Pinky-23, 47, 47, this);
				else if (PinkSens==1) g.drawImage(PinkUp, Pinkx-23, Pinky-23, 47, 47, this);
				else if (PinkSens==2) g.drawImage(PinkRight, Pinkx-23, Pinky-23, 47, 47, this);
				else if (PinkSens==3) g.drawImage(PinkDown, Pinkx-23, Pinky-23, 47, 47, this);
				}
			if (SUPER==1 && PinkEaten==0 && PinkBeenEaten==0) g.drawImage(FantomEatable, Pinkx-23, Pinky-23, 47, 47, this);
			if (PinkEaten==1){
				if (PinkSens==0) g.drawImage(FEatenLeft, Pinkx-23, Pinky-23, 47, 47, this);
				else if (PinkSens==1) g.drawImage(FEatenUp, Pinkx-23, Pinky-23, 47, 47, this);
				else if (PinkSens==2) g.drawImage(FEatenRight, Pinkx-23, Pinky-23, 47, 47, this);
				else if (PinkSens==3) g.drawImage(FEatenDown, Pinkx-23, Pinky-23, 47, 47, this);
				}
			if ((SUPER==0 || OrangeBeenEaten==1) && OrangeEaten==0){
				if (OrangeSens==0) g.drawImage(OrangeLeft, Orangex-23, Orangey-23, 47, 47, this);
				else if (OrangeSens==1) g.drawImage(OrangeUp, Orangex-23, Orangey-23, 47, 47, this);
				else if (OrangeSens==2) g.drawImage(OrangeRight, Orangex-23, Orangey-23, 47, 47, this);
				else if (OrangeSens==3) g.drawImage(OrangeDown, Orangex-23, Orangey-23, 47, 47, this);
				}
			if (SUPER==1 && OrangeEaten==0 && OrangeBeenEaten==0) g.drawImage(FantomEatable, Orangex-23, Orangey-23, 47, 47, this);
			if (OrangeEaten==1){
				if (OrangeSens==0) g.drawImage(FEatenLeft, Orangex-23, Orangey-23, 47, 47, this);
				else if (OrangeSens==1) g.drawImage(FEatenUp, Orangex-23, Orangey-23, 47, 47, this);
				else if (OrangeSens==2) g.drawImage(FEatenRight, Orangex-23, Orangey-23, 47, 47, this);
				else if (OrangeSens==3) g.drawImage(FEatenDown, Orangex-23, Orangey-23, 47, 47, this);
				}	
			} catch (IOException e) {}

//*Score
		Bubble=0;
		for(int i=0; i<244; i++) if (Bubblexy[i][2]==1) Bubble+=1;
		ScoreBubble=10*Bubble;
		g.setColor(Color.white);
		g.drawString("1UP", 93, 30-8);
		
		g.drawString("HIGH", 253, 30-8);
		if (HighScore!=0) g.drawString(String.valueOf(HighScore), 253, 60-8);
		g.drawString("SCORE", 386, 30-8);
		if (Score==0) g.drawString("00", 412, 60-8);
		else g.drawString(String.valueOf(Score), 412, 60-8);

		if (Bubble==244) Game=4;
		if (Game==4){
			//SensAim=5;
			HighScore=Score;
			g.setColor(Color.yellow);
			g.drawString("YOU WON !", 310, 600);
			g.drawString("APPUYEZ SUR UNE TOUCHE POUR REJOUER", 10,980);			
			}
		Score=ScoreBubble+ScoreEatenFantom;


		if (UP1<10) g.drawString("0"+String.valueOf(UP1), 130, 60-8);
		else g.drawString(String.valueOf(UP1), 130, 60-8);
//*End Score


		
//*Pac

		if (Pacy==120 || Pacy==252 || Pacy==331 || ((Pacy==412 || Pacy==591) && 251<=Pacx && Pacx<=521) || Pacy==500 || Pacy==669 || Pacy==748 || Pacy==827 || Pacy==906){
			if (SensAim==0) Sens=0;
			if (SensAim==2) Sens=2;
			}
		if (Pacx==40 || (Pacx==94 && 722<Pacy && Pacy<853) || Pacx==172 || Pacx==251 || Pacx==346 || Pacx==426 || Pacx==521 || Pacx==600 || (Pacx==678 && 722<Pacy && Pacy<853) || Pacx==732){
			if (SensAim==1) Sens=1;
			if (SensAim==3) Sens=3;
			}

		//if (PacIsOnTurnPoint()){
		if (IsOnTurnPoint(Pacx, Pacy)){
			//int I=PacOnTurnPoint();
			int I=OnTurnPoint(Pacx, Pacy);
			//System.out.println("BON");
			//Pacx=TurnPointxy[I][0];
			//Pacy=TurnPointxy[I][1];
			}
		for(int i=0; i<64; i++){
			if (Pacx==TurnPointxy[i][0] && Pacy==TurnPointxy[i][1]){
				switch(i){
					case 0: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 1: if (SensAim==1) SensAim=5;break;
					case 2: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 3: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 4: if (SensAim==1) SensAim=5;break;
					case 5: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 6: if (SensAim==0) SensAim=5;break;
					case 8: if (SensAim==1) SensAim=5;break;
					case 9: if (SensAim==3) SensAim=5;break;
					case 10: if (SensAim==3) SensAim=5;break;
					case 11: if (SensAim==1) SensAim=5;break;
					case 13: if (SensAim==2) SensAim=5;break;
					case 14: if (SensAim==0 || SensAim==3) SensAim=5;break;
					case 15: if (SensAim==2) SensAim=5;break;
					case 16: if (SensAim==0 || SensAim==3) SensAim=5;break;
					case 17: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 18: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 19: if (SensAim==2 || SensAim==3) SensAim=5;break;
					case 20: if (SensAim==0) SensAim=5;break;
					case 21: if (SensAim==2 || SensAim==3) SensAim=5;break;
					case 22: if (SensAim==0) SensAim=5;break;
					case 23: if (SensAim==2) SensAim=5;break;
					case 24: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 26: if (SensAim==3) SensAim=5;break;
					case 27: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 28: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 29: if (SensAim==3) SensAim=5;break;
					case 31: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 32: if (SensAim==0 || SensAim==3) SensAim=5;break;
					case 33: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 34: if (SensAim==0) SensAim=5;break;
					case 35: if (SensAim==1) SensAim=5;break;
					case 36: if (SensAim==3) SensAim=5;break;
					case 37: if (SensAim==3) SensAim=5;break;
					case 38: if (SensAim==1) SensAim=5;break;
					case 39: if (SensAim==2) SensAim=5;break;
					case 40: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 41: if (SensAim==2 || SensAim==3) SensAim=5;break;
					case 42: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 43: if (SensAim==3) SensAim=5;break;
					case 44: if (SensAim==2 || SensAim==3) SensAim=5;break;
					case 45: if (SensAim==0 || SensAim==3) SensAim=5;break;
					case 46: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 47: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 48: if (SensAim==2 || SensAim==3) SensAim=5;break;
					case 49: if (SensAim==0 || SensAim==3) SensAim=5;break;
					case 50: if (SensAim==3) SensAim=5;break;
					case 51: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 52: if (SensAim==0 || SensAim==3) SensAim=5;break;
					case 53: if (SensAim==3) SensAim=5;break;
					case 54: if (SensAim==3) SensAim=5;break;
					case 55: if (SensAim==2 || SensAim==3) SensAim=5;break;
					case 56: if (SensAim==0 || SensAim==1) SensAim=5;break;
					case 57: if (SensAim==3) SensAim=5;break;
					case 58: if (SensAim==3) SensAim=5;break;
					case 59: if (SensAim==2 || SensAim==1) SensAim=5;break;
					case 60: if (SensAim==2) SensAim=5;break;
					case 61: if (SensAim==0) SensAim=5;break;
					case 62: if (SensAim==0) SensAim=5;break;
					case 63: if (SensAim==2) SensAim=5;break;
					//default:Sens=5; break;
					}
				}
			}
		g.setColor(Color.yellow);
		int size=46;
		int size2=(int)(size/2);
		if (Sens==0){
			g.fillArc(Pacx-size2, Pacy-size2, size, size, 0, 120+Pactour);
			g.fillArc(Pacx-size2, Pacy-size2, size, size, 240-Pactour, 120+Pactour);
			if (Game<2){ 
				if (SensAim!=5){
					if (IsOnTurnPoint(Pacx, Pacy)) Pacx-=1;
					else Pacx-=2;
					}
				}
			}
		else if (Sens==1){
			g.fillArc(Pacx-size2, Pacy-size2, size, size, 150-Pactour, 120+Pactour);
			g.fillArc(Pacx-size2, Pacy-size2, size, size, 270, 120+Pactour);
			if (Game<2){ 
				if (SensAim!=5){
					if (IsOnTurnPoint(Pacx, Pacy)) Pacy-=1;
					else Pacy-=2;
					}
				}
			}
		else if (Sens==2){
			g.fillArc(Pacx-size2, Pacy-size2, size, size, 60-Pactour, 120+Pactour);
			g.fillArc(Pacx-size2, Pacy-size2, size, size, 180, 120+Pactour);
			if (Game<2){ 
				if (SensAim!=5){
					if (IsOnTurnPoint(Pacx, Pacy)) Pacx+=1;
					else Pacx+=2;
					}
				}
			}
		else if (Sens==3){
			g.fillArc(Pacx-size2, Pacy-size2, size, size, 90, 120+Pactour);
			g.fillArc(Pacx-size2, Pacy-size2, size, size, 330-Pactour, 120+Pactour);
			if (Game<2){ 
				if (SensAim!=5){
					if (IsOnTurnPoint(Pacx, Pacy)) Pacy+=1;
					else Pacy+=2;
					}
				}
			}
		if (Game<2) Pactour+=3;
		if (Pactour==60) Pactour=0;

		if (SUPER==1){//##########
			if (SUPERTimeOn==0){
				new SUPERTime().start();
				SUPERTimeOn=1;
				}
			if (SUPERTime==15) SUPER=SUPERTime=RedBeenEaten=BlueBeenEaten=PinkBeenEaten=OrangeBeenEaten=EatenRow=0;
			}


		for(int i=0; i<Life; i++){
			int X=67;
			int Y=955;
			int Size=40;
			int Size2=(int)(Size/2);
			g.fillArc(X+i*60, Y, Size, Size, 0, 120+30);
			g.fillArc(X+i*60, Y, Size, Size, 240-30, 120+30);
			}
//****START GAME
			while (StartGame==1){

				if (TimeOn==0){
					new Time().start();
					TimeOn=1;
					}
				if (Time==5){
					Time=0;
					StartGame=2;
					Game=0;
					}
				}
			if (StartGame==0) StartGame=1;
			if (StartGame==1){
				g.setColor(Color.yellow);
				g.fillOval(Pacx-23, Pacy-23, 47, 47);
				g.drawString("READY !", 320, 600);
				}
		
//*End Pac

//***Fantom
		int [] ChoiceRD = {2,3};
		int [] ChoiceUR = {1,2};
		int [] ChoiceLU = {0,1};
		int [] ChoiceLD = {0,3};
		int [] ChoiceLRD = {0,2,3};
		int [] ChoiceURD = {1,2,3};
		int [] ChoiceLUD = {0,1,3};
		int [] ChoiceLUR = {0,1,2};
		int [] ChoiceLURD = {0,1,2,3};
//*Red
		//if (RedEaten==0) g.setColor(Color.red);
		//else g.setColor(Color.green);
		//g.fillOval(Redx-23, Redy-23, 47, 47);
		//g.setColor(Color.white);
		//if (SUPER==1 && RedBeenEaten==0) g.fillOval(Redx-10, Redy-10, 21, 21);




		if (Game<2 && RedEaten==1 && IsOnMapPoint5(Redx, Redy)==false){
			//if (RedSens==0) Redx-=3;
			//if (RedSens==1) Redy-=3;
			//if (RedSens==2) Redx+=3;
			//if (RedSens==3) Redy+=3;
			}
		else if (Game<2 && RedEaten==1 && IsOnMapPoint5(Redx, Redy)){
			//if (RedSens==0) Redx-=1;
			//if (RedSens==1) Redy-=1;
			//if (RedSens==2) Redx+=1;
			//if (RedSens==3) Redy+=1;
			}

		if (IsOnTurnPoint(Redx, Redy) && SUPER==0 && RedEaten==0){
			int I=OnTurnPoint(Redx, Redy);
			Redx=TurnPointxy[I][0];
			Redy=TurnPointxy[I][1];
			}
		else if (1==0 && RedEaten==1 && IsOnMapPoint5(Redx, Redy)){//##
			int I=OnTurnPoint5(Redx, Redy);
			Redx=TurnPointxy[I][0];
			Redy=TurnPointxy[I][1];
			}

		if (RedEaten==0 && Game<2) RedSens=RandomFantomMoveChoice(Redx, Redy, RedSens);

		if (FantomColision(Redx, Redy) && SUPER==0 && RedBeenEaten==0 && RedEaten==0) Game=2;
		else if (FantomColision(Redx, Redy) && SUPER==1 && RedBeenEaten==1) Game=2;
		if (FantomColision(Redx, Redy) && SUPER==1 && RedEaten==0 && RedBeenEaten==0){
			RedEaten=1;
			EatenRow+=1;
			while (RedTime==0){
				if (RedTimeOn==0){
					new RedTime().start();
					RedTimeOn=1;
					}
				}
			//RedTime=0;
			RedScoreEaten=100*EatenRow;
			ScoreEatenFantom+=RedScoreEaten;
			RedScoreEatenx=Redx;
			RedScoreEateny=Redy;
			RedScoreEatenyMax=Redy-100;
			int I=NearestTurnPoint(Redx,Redy);
			//System.out.println("Nearest Point:"+I);
			for(int i=0; i<68; i++){
				if (FHomeShortestPath[i][0]==I){
					int n = FHomeShortestPath[i].length;
					int PathR [] = new int [n];
					for(int j=0; j<n; j++){
						PathR[j]=FHomeShortestPath[i][j];
						}
					RedHomePath=PathR;
					RedEatenNextPoint=RedHomePath[0];
					System.out.println("RedEatenNextPoint="+RedEatenNextPoint+"\t"+"X="+MapPoint[RedEatenNextPoint][0]+"\t"+"Y="+MapPoint[RedEatenNextPoint][1]);
					System.out.println("X="+Redx+"\t"+"Y="+Redy);
					afficher("RedHomePath",RedHomePath);
					}
				}
			if ((MapPoint[I][0]-2<=Redx && Redx<=MapPoint[I][0]+2) || (MapPoint[I][1]-2<=Redy && Redy<=MapPoint[I][1]+2)){
				Redx=MapPoint[I][0];
				Redy=MapPoint[I][1];
				}
			}

		if (Game<2 && RedEaten==0 && RedBeenEaten==0){
			if (RedSens==0) Redx-=2-SUPER;
			if (RedSens==1) Redy-=2-SUPER;
			if (RedSens==2) Redx+=2-SUPER;
			if (RedSens==3) Redy+=2-SUPER;
			}
		else if (Game<2 && RedEaten==0 && RedBeenEaten==1){
			if (RedSens==0) Redx-=2;
			if (RedSens==1) Redy-=2;
			if (RedSens==2) Redx+=2;
			if (RedSens==3) Redy+=2;
			}


		if (RedEaten==1 && (Redx!=386 || Redy!=500)){
			int R = RedEatenNextPoint;
			if (Redx>MapPoint[R][0]) RedSens=0;
			else if (Redx<MapPoint[R][0]) RedSens=2;
			if (Redy>MapPoint[R][1]) RedSens=1;
			else if (Redy<MapPoint[R][1]) RedSens=3;
			if (Game<2 && IsOnMapPoint5(Redx, Redy)==false){
				if (RedSens==0) Redx-=3;
				if (RedSens==1) Redy-=3;
				if (RedSens==2) Redx+=3;
				if (RedSens==3) Redy+=3;
				}
			else if (Game<2 && IsOnMapPoint5(Redx, Redy)){
				if (RedSens==0) Redx-=1;
				if (RedSens==1) Redy-=1;
				if (RedSens==2) Redx+=1;
				if (RedSens==3) Redy+=1;
				}
			if (Redx==MapPoint[R][0] && Redy==MapPoint[R][1]){
				for(int i=0; i<RedHomePath.length-1; i++){
					if (RedHomePath[i]==RedEatenNextPoint){
						RedEatenNextPoint=RedHomePath[i+1];
						break;
						}
					}
				}
			}
		else if (RedEaten==1 && Redx==386 && Redy==500){
			RedEaten=RedTime=0;
			RedExitHome=RedBeenEaten=1;
			//RedSens=5;
			}
		if (RedExitHome==1){
			if (Redy>412) RedSens=1;
			else if (Redy==412){
				RedExitHome=0;
				int RS2=(int)(Math.random()*2);
				if (RS2==1) RS2=2;
				RedSens=RS2;
				}
			}
		if (RedScoreEaten!=0){
			g.drawString("+"+String.valueOf(RedScoreEaten),RedScoreEatenx,RedScoreEateny);
			RedScoreEateny-=1;
			if (RedScoreEateny==RedScoreEatenyMax) RedScoreEaten=0;
			}
		
//*Blue
		if (Score>=610 && FantomInOn==0) FantomInOn=1;

		//if (BlueEaten==0) g.setColor(Color.blue);
		//else g.setColor(Color.green);
		//g.fillOval(Bluex-23, Bluey-23, 47, 47);
		//g.setColor(Color.white);
		//if (SUPER==1 && BlueBeenEaten==0) g.fillOval(Bluex-10, Bluey-10, 21, 21);




		else if (Game<2 && BlueEaten==1 && IsOnMapPoint5(Bluex, Bluey)==false){
			//if (BlueSens==0) Bluex-=3;
			//if (BlueSens==1) Bluey-=3;
			//if (BlueSens==2) Bluex+=3;
			//if (BlueSens==3) Bluey+=3;
			}
		else if (Game<2 && BlueEaten==1 && IsOnMapPoint5(Bluex, Bluey)){
			//if (BlueSens==0) Bluex-=1;
			//if (BlueSens==1) Bluey-=1;
			//if (BlueSens==2) Bluex+=1;
			//if (BlueSens==3) Bluey+=1;
			}
		if (FantomInOn==0 && Game<2){
			if (Bluex-23<291){
				int RS3=(int)(Math.random()*3);
				BlueSens=ChoiceURD[RS3];
				}
			if (Bluex+23>481){
				int RS3=(int)(Math.random()*3);
				BlueSens=ChoiceLUD[RS3];
				}
			if (Bluey-23<452){
				int RS3=(int)(Math.random()*3);
				BlueSens=ChoiceLRD[RS3];
				}
			if (Bluey+23>551){
				int RS3=(int)(Math.random()*3);
				BlueSens=ChoiceLUR[RS3];
				}
			}
		else if (FantomInOn==1){
			//if (Bluex<386) Bluex+=1;
			//else if (Bluex>386) Bluex-=1;
			//else if (Bluey>412) Bluey-=1;
			if (Bluex==385 || Bluex==387) Bluex=386;
			if (Bluex<386) BlueSens=2;
			else if (Bluex>386) BlueSens=0;
			else if (Bluey>412) BlueSens=1;
			else if (Bluey==411) Bluey=412;
			if (Bluex==386 && Bluey==412){
				int RS2=(int)(Math.random()*2);
				if (RS2==1) RS2=2;
				BlueSens=RS2;
				FantomInOn=2;
				}
			}
		else{
			if (IsOnTurnPoint(Bluex, Bluey) && SUPER==0){
				int I=OnTurnPoint(Bluex, Bluey);
				Bluex=TurnPointxy[I][0];
				Bluey=TurnPointxy[I][1];
				}
			if (BlueEaten==0 && Game<2) BlueSens=RandomFantomMoveChoice(Bluex, Bluey, BlueSens);
			}
		if (FantomColision(Bluex, Bluey) && SUPER==0 && BlueBeenEaten==0 && BlueEaten==0) Game=2;
		else if (FantomColision(Bluex, Bluey) && SUPER==1 && BlueBeenEaten==1) Game=2;
		if (FantomColision(Bluex, Bluey) && SUPER==1 && BlueEaten==0 && BlueBeenEaten==0){
			BlueEaten=1;
			EatenRow+=1;
			while (BlueTime==0){
				if (BlueTimeOn==0){
					new BlueTime().start();
					BlueTimeOn=1;
					}
				}
			//BlueTime=0;
			BlueScoreEaten=100*EatenRow;
			ScoreEatenFantom+=BlueScoreEaten;
			BlueScoreEatenx=Bluex;
			BlueScoreEateny=Bluey;
			BlueScoreEatenyMax=Bluey-100;
			int I=NearestTurnPoint(Bluex,Bluey);
			//System.out.println("Nearest Point:"+I);
			for(int i=0; i<68; i++){
				if (FHomeShortestPath[i][0]==I){
					int n = FHomeShortestPath[i].length;
					int Path [] = new int [n];
					for(int j=0; j<n; j++){
						Path[j]=FHomeShortestPath[i][j];
						}
					BlueHomePath=Path;
					BlueEatenNextPoint=BlueHomePath[0];
					System.out.println("BlueEatenNextPoint="+BlueEatenNextPoint+"\t"+"X="+MapPoint[BlueEatenNextPoint][0]+"\t"+"Y="+MapPoint[BlueEatenNextPoint][1]);
					System.out.println("X="+Bluex+"\t"+"Y="+Bluey);
					afficher("BlueHomePath",BlueHomePath);
					}
				}
			if ((MapPoint[I][0]-2<=Bluex && Bluex<=MapPoint[I][0]+2) || (MapPoint[I][1]-2<=Bluey && Bluey<=MapPoint[I][1]+2)){
				Bluex=MapPoint[I][0];
				Bluey=MapPoint[I][1];
				}
			}

		if (Game<2 && BlueEaten==0 && BlueBeenEaten==0){
			if (BlueSens==0) Bluex-=2-SUPER;
			if (BlueSens==1) Bluey-=2-SUPER;
			if (BlueSens==2) Bluex+=2-SUPER;
			if (BlueSens==3) Bluey+=2-SUPER;
			}
		else if (Game<2 && BlueEaten==0 && BlueBeenEaten==1){
			if (BlueSens==0) Bluex-=2;
			if (BlueSens==1) Bluey-=2;
			if (BlueSens==2) Bluex+=2;
			if (BlueSens==3) Bluey+=2;
			}


		if (BlueEaten==1 && (Bluex!=386 || Bluey!=500)){
			int R = BlueEatenNextPoint;
			if (Bluex>MapPoint[R][0]) BlueSens=0;
			else if (Bluex<MapPoint[R][0]) BlueSens=2;
			if (Bluey>MapPoint[R][1]) BlueSens=1;
			else if (Bluey<MapPoint[R][1]) BlueSens=3;
			if (Game<2 && IsOnMapPoint5(Bluex, Bluey)==false){
				if (BlueSens==0) Bluex-=3;
				if (BlueSens==1) Bluey-=3;
				if (BlueSens==2) Bluex+=3;
				if (BlueSens==3) Bluey+=3;
				}
			else if (Game<2 && IsOnMapPoint5(Bluex, Bluey)){
				if (BlueSens==0) Bluex-=1;
				if (BlueSens==1) Bluey-=1;
				if (BlueSens==2) Bluex+=1;
				if (BlueSens==3) Bluey+=1;
				}
			if (Bluex==MapPoint[R][0] && Bluey==MapPoint[R][1]){
				for(int i=0; i<BlueHomePath.length-1; i++){
					if (BlueHomePath[i]==BlueEatenNextPoint){
						BlueEatenNextPoint=BlueHomePath[i+1];
						break;
						}
					}
				}
			}
		else if (BlueEaten==1 && Bluex==386 && Bluey==500){
			BlueEaten=BlueTime=0;
			BlueExitHome=BlueBeenEaten=1;
			//BlueSens=5;
			}
		if (BlueExitHome==1){
			//if (Bluex<386) Bluex+=1;
			//else if (Bluex>386) Bluex-=1;
			//else if (Bluey>412) Bluey-=1;
			if (Bluex<386) BlueSens=2;
			else if (Bluex>386) BlueSens=0;
			else if (Bluey>412) BlueSens=1;
			else if (Bluex==386 && Bluey==412){
				int RS2=(int)(Math.random()*2);
				if (RS2==1) RS2=2;
				BlueSens=RS2;
				BlueExitHome=0;
				}
			}
			
		if (BlueScoreEaten!=0){
			g.drawString("+"+String.valueOf(BlueScoreEaten),BlueScoreEatenx,BlueScoreEateny);
			BlueScoreEateny-=1;
			if (BlueScoreEateny==BlueScoreEatenyMax) BlueScoreEaten=0;
			}
//*Pink
		if (Score>=1220 && FantomInOn==2) FantomInOn=3;

		//if (PinkEaten==0) g.setColor(Color.pink);
		//else g.setColor(Color.green);
		//g.fillOval(Pinkx-23, Pinky-23, 47, 47);
		//g.setColor(Color.white);
		//if (SUPER==1 && PinkBeenEaten==0) g.fillOval(Pinkx-10, Pinky-10, 21, 21);



		else if (Game<2 && PinkEaten==1 && IsOnMapPoint5(Pinkx, Pinky)==false){
			//if (PinkSens==0) Pinkx-=3;
			//if (PinkSens==1) Pinky-=3;
			//if (PinkSens==2) Pinkx+=3;
			//if (PinkSens==3) Pinky+=3;
			}
		else if (Game<2 && PinkEaten==1 && IsOnMapPoint5(Pinkx, Pinky)){
			//if (PinkSens==0) Pinkx-=1;
			//if (PinkSens==1) Pinky-=1;
			//if (PinkSens==2) Pinkx+=1;
			//if (PinkSens==3) Pinky+=1;
			}
		if (FantomInOn<3 && Game<2){
			if (Pinkx-23<291){
				int RS3=(int)(Math.random()*3);
				PinkSens=ChoiceURD[RS3];
				}
			if (Pinkx+23>481){
				int RS3=(int)(Math.random()*3);
				PinkSens=ChoiceLUD[RS3];
				}
			if (Pinky-23<452){
				int RS3=(int)(Math.random()*3);
				PinkSens=ChoiceLRD[RS3];
				}
			if (Pinky+23>551){
				int RS3=(int)(Math.random()*3);
				PinkSens=ChoiceLUR[RS3];
				}
			}
		else if (FantomInOn==3){
			//if (Pinkx<386) Pinkx+=1;
			//else if (Pinkx>386) Pinkx-=1;
			//else if (Pinky>412) Pinky-=1;
			if (Pinkx==385 || Pinkx==387) Pinkx=386;
			if (Pinkx<386) PinkSens=2;
			else if (Pinkx>386) PinkSens=0;
			else if (Pinky>412) PinkSens=1;
			else if (Pinky==411) Pinky=412;
			if (Pinkx==386 && Pinky==412){
				int RS2=(int)(Math.random()*2);
				if (RS2==1) RS2=2;
				PinkSens=RS2;
				FantomInOn=4;
				}
			}
		else{
			if (IsOnTurnPoint(Pinkx, Pinky) && SUPER==0){
				//int I=OnTurnPoint(Pinkx, Pinky);
				//Pinkx=TurnPointxy[I][0];
				//Pinky=TurnPointxy[I][1];
				}
			if (PinkEaten==0 && Game<2) PinkSens=RandomFantomMoveChoice(Pinkx, Pinky, PinkSens);
			}
		if (FantomColision(Pinkx, Pinky) && SUPER==0 && PinkBeenEaten==0 && PinkEaten==0) Game=2;
		else if (FantomColision(Pinkx, Pinky) && SUPER==1 && PinkBeenEaten==1) Game=2;
		if (FantomColision(Pinkx, Pinky) && SUPER==1 && PinkEaten==0 && PinkBeenEaten==0){
			PinkEaten=1;
			EatenRow+=1;
			while (PinkTime==0){
				if (PinkTimeOn==0){
					new PinkTime().start();
					PinkTimeOn=1;
					}
				}
			//PinkTime=0;
			PinkScoreEaten=100*EatenRow;
			ScoreEatenFantom+=PinkScoreEaten;
			PinkScoreEatenx=Pinkx;
			PinkScoreEateny=Pinky;
			PinkScoreEatenyMax=Pinky-100;
			int I=NearestTurnPoint(Pinkx,Pinky);
			//System.out.println("Nearest Point:"+I);
			for(int i=0; i<68; i++){
				if (FHomeShortestPath[i][0]==I){
					int n = FHomeShortestPath[i].length;
					int Path [] = new int [n];
					for(int j=0; j<n; j++){
						Path[j]=FHomeShortestPath[i][j];
						}
					PinkHomePath=Path;
					PinkEatenNextPoint=PinkHomePath[0];
					System.out.println("PinkEatenNextPoint="+PinkEatenNextPoint+"\t"+"X="+MapPoint[PinkEatenNextPoint][0]+"\t"+"Y="+MapPoint[PinkEatenNextPoint][1]);
					System.out.println("X="+Pinkx+"\t"+"Y="+Pinky);
					afficher("PinkHomePath",PinkHomePath);
					}
				}
			if ((MapPoint[I][0]-2<=Pinkx && Pinkx<=MapPoint[I][0]+2) || (MapPoint[I][1]-2<=Pinky && Pinky<=MapPoint[I][1]+2)){
				Pinkx=MapPoint[I][0];
				Pinky=MapPoint[I][1];
				}
			}

		if (Game<2 && PinkEaten==0 && PinkBeenEaten==0){
			if (PinkSens==0) Pinkx-=2-SUPER;
			if (PinkSens==1) Pinky-=2-SUPER;
			if (PinkSens==2) Pinkx+=2-SUPER;
			if (PinkSens==3) Pinky+=2-SUPER;
			}
		else if (Game<2 && PinkEaten==0 && PinkBeenEaten==1){
			if (PinkSens==0) Pinkx-=2;
			if (PinkSens==1) Pinky-=2;
			if (PinkSens==2) Pinkx+=2;
			if (PinkSens==3) Pinky+=2;
			}

		if (PinkEaten==1 && (Pinkx!=386 || Pinky!=500)){
			int R = PinkEatenNextPoint;
			if (Pinkx>MapPoint[R][0]) PinkSens=0;
			else if (Pinkx<MapPoint[R][0]) PinkSens=2;
			if (Pinky>MapPoint[R][1]) PinkSens=1;
			else if (Pinky<MapPoint[R][1]) PinkSens=3;
			if (Game<2 && IsOnMapPoint5(Pinkx, Pinky)==false){
				if (PinkSens==0) Pinkx-=3;
				if (PinkSens==1) Pinky-=3;
				if (PinkSens==2) Pinkx+=3;
				if (PinkSens==3) Pinky+=3;
				}
			else if (Game<2 && IsOnMapPoint5(Pinkx, Pinky)){
				if (PinkSens==0) Pinkx-=1;
				if (PinkSens==1) Pinky-=1;
				if (PinkSens==2) Pinkx+=1;
				if (PinkSens==3) Pinky+=1;
				}
			if (Pinkx==MapPoint[R][0] && Pinky==MapPoint[R][1]){
				for(int i=0; i<PinkHomePath.length-1; i++){
					if (PinkHomePath[i]==PinkEatenNextPoint){
						PinkEatenNextPoint=PinkHomePath[i+1];
						break;
						}
					}
				}
			}
		else if (PinkEaten==1 && Pinkx==386 && Pinky==500){
			PinkEaten=PinkTime=0;
			PinkExitHome=PinkBeenEaten=1;
			//PinkSens=5;
			}
		if (PinkExitHome==1){
			//if (Pinkx<386) Pinkx+=1;
			//else if (Pinkx>386) Pinkx-=1;
			//else if (Pinky>412) Pinky-=1;
			if (Pinkx<386) PinkSens=2;
			else if (Pinkx>386) PinkSens=0;
			else if (Pinky>412) PinkSens=1;
			else if (Pinkx==386 && Pinky==412){
				int RS2=(int)(Math.random()*2);
				if (RS2==1) RS2=2;
				PinkSens=RS2;
				PinkExitHome=0;
				}
			}
		if (PinkScoreEaten!=0){
			g.drawString("+"+String.valueOf(PinkScoreEaten),PinkScoreEatenx,PinkScoreEateny);
			PinkScoreEateny-=1;
			if (PinkScoreEateny==PinkScoreEatenyMax) PinkScoreEaten=0;
			}			
		
//*Orange
		if (Score>=1830 && FantomInOn==4) FantomInOn=5;

		//if (OrangeEaten==0) g.setColor(Color.orange);
		//else g.setColor(Color.green);//#
		//g.fillOval(Orangex-23, Orangey-23, 47, 47);
		//g.setColor(Color.white);
		//if (SUPER==1 && OrangeBeenEaten==0) g.fillOval(Orangex-10, Orangey-10, 21, 21);
/*
*
*
*
*
*
*/
		boolean O = IsOnTurnPoint(Orangex, Orangey);
		if (Game<2 && ((O==false && SUPER==0 && OrangeEaten==0) || OrangeBeenEaten==1)){
			//if (OrangeSens==0) Orangex-=2;
			//if (OrangeSens==1) Orangey-=2;
			//if (OrangeSens==2) Orangex+=2;
			//if (OrangeSens==3) Orangey+=2;
			}
		else if (Game<2 && (O || SUPER==1) && OrangeEaten==0){
			//if (OrangeSens==0) Orangex-=1;
			//if (OrangeSens==1) Orangey-=1;
			//if (OrangeSens==2) Orangex+=1;
			//if (OrangeSens==3) Orangey+=1;
			}

		else if (Game<2 && OrangeEaten==1 && IsOnMapPoint5(Orangex, Orangey)==false){
			//if (OrangeSens==0) Orangex-=3;
			//if (OrangeSens==1) Orangey-=3;
			//if (OrangeSens==2) Orangex+=3;
			//if (OrangeSens==3) Orangey+=3;
			}
		else if (Game<2 && OrangeEaten==1 && IsOnMapPoint5(Orangex, Orangey)){
			//if (OrangeSens==0) Orangex-=1;
			//if (OrangeSens==1) Orangey-=1;
			//if (OrangeSens==2) Orangex+=1;
			//if (OrangeSens==3) Orangey+=1;
			}
		if (FantomInOn<5 && Game<2){
			if (Orangex-23<291){
				int RS3=(int)(Math.random()*3);
				OrangeSens=ChoiceURD[RS3];
				}
			if (Orangex+23>481){
				int RS3=(int)(Math.random()*3);
				OrangeSens=ChoiceLUD[RS3];
				}
			if (Orangey-23<452){
				int RS3=(int)(Math.random()*3);
				OrangeSens=ChoiceLRD[RS3];
				}
			if (Orangey+23>551){
				int RS3=(int)(Math.random()*3);
				OrangeSens=ChoiceLUR[RS3];
				}
			}
		else if (FantomInOn==5){
			//if (Orangex<386) Orangex+=1;
			//else if (Orangex>386) Orangex-=1;
			//else if (Orangey>412) Orangey-=1;
			if (Orangex==385 || Orangex==387) Orangex=386;
			if (Orangex<386) OrangeSens=2;
			else if (Orangex>386) OrangeSens=0;
			else if (Orangey>412) OrangeSens=1;
			else if (Orangey==411) Orangey=412;
			if (Orangex==386 && Orangey==412){
				int RS2=(int)(Math.random()*2);
				if (RS2==1) RS2=2;
				OrangeSens=RS2;
				FantomInOn=6;
				}
			}
		else{
			if (IsOnTurnPoint(Orangex, Orangey) && SUPER==0){
				//int I=OnTurnPoint(Orangex, Orangey);
				//Orangex=TurnPointxy[I][0];
				//Orangey=TurnPointxy[I][1];
				}
			if (OrangeEaten==0 && Game<2) OrangeSens=RandomFantomMoveChoice(Orangex, Orangey, OrangeSens);
			}
		
		if (FantomColision(Orangex, Orangey) && SUPER==0 && OrangeBeenEaten==0 && OrangeEaten==0) Game=2;
		else if (FantomColision(Orangex, Orangey) && SUPER==1 && OrangeBeenEaten==1) Game=2;
		if (FantomColision(Orangex, Orangey) && SUPER==1 && OrangeEaten==0 && OrangeBeenEaten==0){
			OrangeEaten=1;
			EatenRow+=1;
			while (OrangeTime==0){
				if (OrangeTimeOn==0){
					new OrangeTime().start();
					OrangeTimeOn=1;
					}
				}
			OrangeScoreEaten=100*EatenRow;
			ScoreEatenFantom+=OrangeScoreEaten;
			OrangeScoreEatenx=Orangex;
			OrangeScoreEateny=Orangey;
			OrangeScoreEatenyMax=Orangey-100;
			int I=NearestTurnPoint(Orangex,Orangey);
			//System.out.println(I);
			for(int i=0; i<68; i++){
				if (FHomeShortestPath[i][0]==I){
					int n = FHomeShortestPath[i].length;
					int Path [] = new int [n];
					for(int j=0; j<n; j++){
						Path[j]=FHomeShortestPath[i][j];
						}
					OrangeHomePath=Path;
					OrangeEatenNextPoint=OrangeHomePath[0];
					System.out.println("OrangeEatenNextPoint="+OrangeEatenNextPoint+"\t"+"X="+MapPoint[OrangeEatenNextPoint][0]+"\t"+"Y="+MapPoint[OrangeEatenNextPoint][1]);
					System.out.println("X="+Orangex+"\t"+"Y="+Orangey);
					afficher("OrangeHomePath",OrangeHomePath);
					}
				}
			if ((MapPoint[I][0]-2<=Orangex && Orangex<=MapPoint[I][0]+2) || (MapPoint[I][1]-2<=Orangey && Orangey<=MapPoint[I][1]+2)){
				Orangex=MapPoint[I][0];
				Orangey=MapPoint[I][1];
				}
			}
		if (Game<2 && OrangeEaten==0 && OrangeBeenEaten==0){
			if (OrangeSens==0) Orangex-=2-SUPER;
			if (OrangeSens==1) Orangey-=2-SUPER;
			if (OrangeSens==2) Orangex+=2-SUPER;
			if (OrangeSens==3) Orangey+=2-SUPER;
			}
		else if (Game<2 && OrangeEaten==0 && OrangeBeenEaten==1){
			if (OrangeSens==0) Orangex-=2;
			if (OrangeSens==1) Orangey-=2;
			if (OrangeSens==2) Orangex+=2;
			if (OrangeSens==3) Orangey+=2;
			}


		if (OrangeEaten==1 && (Orangex!=386 || Orangey!=500)){
			int R = OrangeEatenNextPoint;
			if (Orangex>MapPoint[R][0]) OrangeSens=0;
			else if (Orangex<MapPoint[R][0]) OrangeSens=2;
			if (Orangey>MapPoint[R][1]) OrangeSens=1;
			else if (Orangey<MapPoint[R][1]) OrangeSens=3;
			if (Game<2 && IsOnMapPoint5(Orangex, Orangey)==false){
				if (OrangeSens==0) Orangex-=3;
				if (OrangeSens==1) Orangey-=3;
				if (OrangeSens==2) Orangex+=3;
				if (OrangeSens==3) Orangey+=3;
				}
			else if (Game<2 && IsOnMapPoint5(Orangex, Orangey)){
				if (OrangeSens==0) Orangex-=1;
				if (OrangeSens==1) Orangey-=1;
				if (OrangeSens==2) Orangex+=1;
				if (OrangeSens==3) Orangey+=1;
				}
			if (Orangex==MapPoint[R][0] && Orangey==MapPoint[R][1]){
				for(int i=0; i<OrangeHomePath.length-1; i++){
					if (OrangeHomePath[i]==OrangeEatenNextPoint){
						OrangeEatenNextPoint=OrangeHomePath[i+1];
						break;
						}
					}
				}
			}
		else if (OrangeEaten==1 && Orangex==386 && Orangey==500){
			OrangeEaten=OrangeTime=0;
			OrangeExitHome=OrangeBeenEaten=1;
			//OrangeSens=5;
			}
		if (OrangeExitHome==1){
			//if (Orangex<386) Orangex+=1;
			//else if (Orangex>386) Orangex-=1;
			//else if (Orangey>412) Orangey-=1;
			if (Orangex<386) OrangeSens=2;
			else if (Orangex>386) OrangeSens=0;
			else if (Orangey>412) OrangeSens=1;
			else if (Orangex==386 && Orangey==412){
				int RS2=(int)(Math.random()*2);
				if (RS2==1) RS2=2;
				OrangeSens=RS2;
				OrangeExitHome=0;
				}
			}
		if (OrangeScoreEaten!=0){
			g.drawString("+"+String.valueOf(OrangeScoreEaten),OrangeScoreEatenx,OrangeScoreEateny);
			OrangeScoreEateny-=1;
			if (OrangeScoreEateny==OrangeScoreEatenyMax) OrangeScoreEaten=0;
			}
//***End Fantom

		if (Game==2 && Life>-1){
			if (TimeOn==0){
				new Time().start();
				TimeOn=1;
				}
			if (Time==3){
				Time=Game=0;
				Life-=1;
				}
			}
		if (Life==-1) Game=3;
		if (Game==3){
			g.setColor(Color.yellow);
			g.drawString("APPUYEZ SUR UNE TOUCHE POUR REJOUER", 10,980);
			g.drawString("GAME OVER", 285, 600);
			}
		try{
			Thread.sleep(0);
			} catch (InterruptedException e) {}

		if (StartGame==-1){
			g.setColor(Color.black);
			g.fillRect(0,0,this.getWidth(),this.getHeight());
			g.setColor(Color.yellow);
			g.drawString("PAC-MAN", 386-125, 100);
			g.fillArc(386-150, 500-150, 300, 300, 0, 120+30);
			g.fillArc(386-150, 500-150, 300, 300, 240-30, 120+30);
			}
			
		}
//***************************************************************************************
//***************************************************************************************
//#######################################################################################
//***************************************************************************************
//***************************************************************************************
	public int RandomFantomMoveChoice(int Fantomx, int Fantomy, int FSens){
		int FantomSens=FSens;
		int [] ChoiceRD = {2,3};
		int [] ChoiceUR = {1,2};
		int [] ChoiceLU = {0,1};
		int [] ChoiceLD = {0,3};
		int [] ChoiceLRD = {0,2,3};
		int [] ChoiceURD = {1,2,3};
		int [] ChoiceLUD = {0,1,3};
		int [] ChoiceLUR = {0,1,2};
		int [] ChoiceLURD = {0,1,2,3};
		int RS2=(int)(Math.random()*2);
		int RS3=(int)(Math.random()*3);
		int RS4=(int)(Math.random()*4);
		for(int i=0; i<64; i++){
			for(int j=0; j<3; j++){
			for(int k=0; k<3; k++){
			if (Fantomx-1+j==TurnPointxy[i][0] && Fantomy-1+k==TurnPointxy[i][1]){
				switch(i){
					case 0:FantomSens=ChoiceRD[RS2];break;
					case 1:FantomSens=ChoiceLRD[RS3];break;
					case 2:FantomSens=ChoiceLD[RS2];break;
					case 3:FantomSens=ChoiceRD[RS2];break;
					case 4:FantomSens=ChoiceLRD[RS3];break;
					case 5:FantomSens=ChoiceLD[RS2];break;
					case 6:FantomSens=ChoiceURD[RS3];break;
					case 7:FantomSens=ChoiceLURD[RS4];break;
					case 8:FantomSens=ChoiceLRD[RS3];break;
					case 9:FantomSens=ChoiceLUR[RS3];break;
					case 10:FantomSens=ChoiceLUR[RS3];break;
					case 11:FantomSens=ChoiceLRD[RS3];break;
					case 12:FantomSens=ChoiceLURD[RS4];break;
					case 13:FantomSens=ChoiceLUD[RS3];break;
					case 14:FantomSens=ChoiceUR[RS2];break;
					case 15:FantomSens=ChoiceLUD[RS3];break;
					case 16:FantomSens=ChoiceUR[RS2];break;
					case 17:FantomSens=ChoiceLD[RS2];break;
					case 18:FantomSens=ChoiceRD[RS2];break;
					case 19:FantomSens=ChoiceLU[RS2];break;
					case 20:FantomSens=ChoiceURD[RS3];break;
					case 21:FantomSens=ChoiceLU[RS2];break;
					case 22:FantomSens=ChoiceURD[RS3];break;
					case 23:FantomSens=ChoiceLUD[RS3];break;
					case 24:FantomSens=ChoiceRD[RS2];break;
					case 25:FantomSens=ChoiceLURD[RS4];break;
					case 26:FantomSens=ChoiceLUR[RS3];break;
					case 27:FantomSens=ChoiceLD[RS2];break;
					case 28:FantomSens=ChoiceRD[RS2];break;
					case 29:FantomSens=ChoiceLUR[RS3];break;
					case 30:FantomSens=ChoiceLURD[RS4];break;
					case 31:FantomSens=ChoiceLD[RS2];break;
					case 32:FantomSens=ChoiceUR[RS2];break;
					case 33:FantomSens=ChoiceLD[RS2];break;
					case 34:FantomSens=ChoiceURD[RS3];break;
					case 35:FantomSens=ChoiceLRD[RS3];break;
					case 36:FantomSens=ChoiceLUR[RS3];break;
					case 37:FantomSens=ChoiceLUR[RS3];break;
					case 38:FantomSens=ChoiceLRD[RS3];break;
					case 39:FantomSens=ChoiceLUD[RS3];break;
					case 40:FantomSens=ChoiceRD[RS2];break;
					case 41:FantomSens=ChoiceLU[RS2];break;
					case 42:FantomSens=ChoiceRD[RS2];break;
					case 43:FantomSens=ChoiceLUR[RS3];break;
					case 44:FantomSens=ChoiceLU[RS2];break;
					case 45:FantomSens=ChoiceUR[RS2];break;
					case 46:FantomSens=ChoiceLD[RS2];break;
					case 47:FantomSens=ChoiceRD[RS2];break;
					case 48:FantomSens=ChoiceLU[RS2];break;
					case 49:FantomSens=ChoiceUR[RS2];break;
					case 50:FantomSens=ChoiceLUR[RS3];break;
					case 51:FantomSens=ChoiceLD[RS2];break;
					case 52:FantomSens=ChoiceUR[RS2];break;
					case 53:FantomSens=ChoiceLUR[RS3];break;
					case 54:FantomSens=ChoiceLUR[RS3];break;
					case 55:FantomSens=ChoiceLU[RS2];break;
					case 56:FantomSens=ChoiceRD[RS2];break;
					case 57:FantomSens=ChoiceLUR[RS3];break;
					case 58:FantomSens=ChoiceLUR[RS3];break;
					case 59:FantomSens=ChoiceLD[RS2];break;
					case 60:FantomSens=ChoiceLUD[RS3];break;
					case 61:FantomSens=ChoiceURD[RS3];break;
					case 62:FantomSens=ChoiceURD[RS3];break;
					case 63:FantomSens=ChoiceLUD[RS3];break;
					}
				}
				}
				}
			}
		return FantomSens;
		}
	public boolean IsOnMapPoint5(int X, int Y){
		for(int i=0; i<66; i++){
			for(int j=0; j<5; j++){
				for(int k=0; k<5; k++){
					if (X-2+j==MapPoint[i][0] && Y-2+k==MapPoint[i][1]) return true;
					}
				}
			}
		return false;
		}
	public int OnTurnPoint5(int X, int Y){
		int I=0;
		for(int i=0; i<64; i++){
			for(int j=0; j<5; j++){
				for(int k=0; k<5; k++){
					if (X-2+j==TurnPointxy[i][0] && Y-2+k==TurnPointxy[i][1]) I=i;
					}
				}
			}
		return I;
		}
	public boolean IsOnTurnPoint(int X, int Y){
		for(int i=0; i<64; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					if (X-1+j==TurnPointxy[i][0] && Y-1+k==TurnPointxy[i][1]) return true;
					}
				}
			}
		return false;
		}
	public int OnTurnPoint(int X, int Y){
		int I=0;
		for(int i=0; i<64; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					if (X-1+j==TurnPointxy[i][0] && Y-1+k==TurnPointxy[i][1]) I=i;
					}
				}
			}
		return I;
		}
	public void EatBubble(){
		for(int i=0; i<244; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					if (Pacx-1+j==Bubblexy[i][0] && Pacy-1+k==Bubblexy[i][1] && Bubblexy[i][2]==0){
						Bubblexy[i][2]=1;
						if (i==30 || i==35 || i==158 || i==177) SUPER=1;
						}
					}
				}
			}
		}
	public boolean FantomColision(int Fx, int Fy){
		for(int i=Fx-20; i<Fx+20; i++){
			for(int j=Fy-20; j<Fy+20; j++){
				if (Pacx-23<i && i<Pacx+23 && Pacy-23<j && j<Pacy+23) return true;
				}
			}
		return false;
		}
	public int Norme(int x1, int y1, int x2, int y2){
		return (int)(Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
		}
	public int NearestTurnPoint(int X, int Y){
		int Dmin = Norme(X,Y,MapPoint[0][0], MapPoint[0][1]);
		int Imin = 0;
		for(int i=0; i<68; i++){
			int D = Norme(X,Y,MapPoint[i][0], MapPoint[i][1]);
			if (D<Dmin){
				Dmin=D;
				Imin=i;
				}
			}
		return Imin;
		}
	public void afficher(int [] L){
		for(int i=0; i<L.length; i++){
			System.out.println(i+":"+L[i]);
			}
		}
	public void afficher(String nom, int [] L){
		System.out.println(nom);
		for(int i=0; i<L.length; i++){
			System.out.println(i+":"+L[i]);
			}
		}
	}
class Touche implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		//System.out.println("b");
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (Pan.StartGame==-1 && c!=0x25 && c!=0x26 && c!=0x27 && c!=0x28) Pan.StartGame=0;

		if (c==0x25) Pan.SensAim=0;
		else if (c==0x26) Pan.SensAim=1;
		else if (c==0x27) Pan.SensAim=2;
		else if (c==0x28) Pan.SensAim=3;
		//if (c==0x25) Pan.Sens=0;
		//else if (c==0x26) Pan.Sens=1;
		//else if (c==0x27) Pan.Sens=2;
		//else if (c==0x28) Pan.Sens=3;
		if (k=='t'){
			for(int i=0; i<64; i++){
				System.out.println(i+": "+Pan.TurnPointxy[i][0]+"\t"+Pan.TurnPointxy[i][1]);
				}	
			}
		if (k=='P') System.out.println(Pan.Pacx+"\t"+Pan.Pacy);
		if (k=='r') System.out.println(Pan.Redx+"\t"+Pan.Redy);
		if (k=='b') System.out.println(Pan.Bluex+"\t"+Pan.Bluey);
		if (k=='p') System.out.println(Pan.Pinkx+"\t"+Pan.Pinky);
		if (k=='o') System.out.println(Pan.Orangex+"\t"+Pan.Orangey);

		if (k=='s') Pan.SUPER=1;
		if (k=='d') Pan.ScoreEatenFantom+=1000;

		if (k=='g') Pan.Game=2;
		if (k=='l') Pan.Life+=1;
		if (k=='i'){
			System.out.println("Game:"+Pan.Game);
			System.out.println("Time:"+Pan.Time);
			System.out.println();
			}
		int A=0;
		if (k=='a'){
			if (c==0x25) Pan.Sens=0;
			else if (c==0x26) Pan.Sens=1;
			else if (c==0x27) Pan.Sens=2;
			else if (c==0x28) Pan.Sens=3;
			}
		if ((Pan.Game==3 || Pan.Game==4) && k!='i'){
			Pan.HighScore=Pan.Score;
			Pan.Game=Pan.Score=Pan.ScoreBubble=Pan.ScoreEatenFantom=Pan.FantomInOn=Pan.StartGame=0;
			for(int i=0; i<244; i++) Pan.Bubblexy[i][2]=0;
			Pan.Life=2;
			}
		}
	public void keyReleased(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		//if (k=='s') Pan.SUPER=Pan.RedBeenEaten=Pan.BlueBeenEaten=Pan.PinkBeenEaten=Pan.OrangeBeenEaten=0;
		}
	}
class Time extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e){}
		Pan.TimeOn=0;
		Pan.Time+=1;
		}
	}
class RedTime extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e){}
		Pan.RedTimeOn=0;
		Pan.RedTime+=1;
		}
	}
class PinkTime extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e){}
		Pan.PinkTimeOn=0;
		Pan.PinkTime+=1;
		}
	}
class BlueTime extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e){}
		Pan.BlueTimeOn=0;
		Pan.BlueTime+=1;
		}
	}
class OrangeTime extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e){}
		Pan.OrangeTimeOn=0;
		Pan.OrangeTime+=1;
		}
	}
class SUPERTime extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e){}
		Pan.SUPERTimeOn=0;
		Pan.SUPERTime+=1;
		}
	}
class Souris implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1){
			if (Pan.StartGame==-1) Pan.StartGame=0;

			System.out.println(X+"\t"+Y);
			for(int i=0; i<244; i++){
				for(int j=0; j<12; j++){
					for(int k=0; k<12; k++){				
						if (Pan.Bubblexy[i][0]==X-6+j && Pan.Bubblexy[i][1]==Y-6+k) System.out.println(i);
						}	
					}
				}
			for(int i=0; i<64; i++){
				for(int j=0; j<12; j++){
					for(int k=0; k<12; k++){				
						if (Pan.TurnPointxy[i][0]==X-6+j && Pan.TurnPointxy[i][1]==Y-6+k) System.out.println(i);
						}	
					}
				}
			}
		if (c==3){
			for(int i=0; i<244; i++){
				for(int j=0; j<12; j++){
					for(int k=0; k<12; k++){				
						if (Pan.Bubblexy[i][0]==X-6+j && Pan.Bubblexy[i][1]==Y-6+k){
							Pan.Pacx=Pan.Bubblexy[i][0];
							Pan.Pacy=Pan.Bubblexy[i][1];
							}
						}	
					}
				}
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
public class Pacman{
	public static void main(String[] args){
		Fen fen = new Fen("PacMan",773+16, 1000+40);
		//Fen fen = new Fen("PacMan",773, 1000);
		Pan pan = new Pan(773, 1000);
		fen.setContentPane(pan);
		while (true) pan.repaint();
		}
}