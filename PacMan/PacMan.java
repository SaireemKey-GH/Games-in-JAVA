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
//Pac est un rond de rayon 15
/// dark fairy inverser les touches !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
class Pan extends JPanel{
	static int larg=0;
	static int haut=0;
	static int keyflag=0;
	static int pacx=(800/2)-15;
	static int pacy=800-30;
	static int pacArcStart=225;
	static int pacArcEnd=135;
	static int pacArcTour=0;
	static int pacSens=0;
	static int pacMotion=1;
	static int RedSens=0+(int)(Math.random()*4);
	static int RedMotion=1;
	static int RedChoixTimerOn=0;
	static int Redx=0+(int)(Math.random()*771);
	static int Redy=200;
	static int RedDead=0;

	static int BlueSens=0+(int)(Math.random()*4);
	static int BlueMotion=1;
	static int BlueChoixTimerOn=0;
	static int Bluex=0+(int)(Math.random()*771);
	static int Bluey=300;
	static int BlueDead=0;

	static int GreenSens=0+(int)(Math.random()*4);
	static int GreenMotion=1;
	static int GreenChoixTimerOn=0;
	static int Greenx=0+(int)(Math.random()*771);
	static int Greeny=500;
	static int GreenDead=0;

	static int PurpleSens=0+(int)(Math.random()*4);
	static int PurpleMotion=1;
	static int PurpleChoixTimerOn=0;
	static int Purplex=0+(int)(Math.random()*771);
	static int Purpley=400;

	static int BlueFairyx=0;
	static int BlueFairyy=0;
	static int BlueFairyMagic=0;
	static int BlueFairyTimeOn=0;
	static int BlueFairyM=0;
	static int BlueFairyAppearY=100+(int)(Math.random()*501);

	static int YellowFairyx=0;
	static int YellowFairyy=0;
	static int YellowFairyMagic=0;
	static int YellowFairyTimeOn=0;
	static int YellowFairyM=0;
	static int YellowFairyAppearY=100+(int)(Math.random()*501);
	static int YellowFairyMoveX=816;

	static int DarkFairyx=0;
	static int DarkFairyy=0;
	static int DarkFairyMagic=0;
	static int DarkFairyTimeOn=0;
	static int DarkFairyM=0;
	static int DarkFairyAppearY=100+(int)(Math.random()*501);
	static int DarkFairyMoveX=0;
	static int DarkFairyMoveY=0;

	static int FFairyx=0;
	static int FFairyy=0;
	static int FFairyMagic=0;
	static int FFairyTimeOn=0;
	static int FFairyTime=0;
	static int FFairyM=0;
	static int FFairyAppear=1+(int)(Math.random()*4);
	static int FFairyMoveX=0;
	static int FFairyMoveY=0;

	static int Endx=0;
	static int Endy=0;
	int lin1x[] = new int[3];
	int lin1y[] = new int[3];
	int lin2x[] = new int[3];
	int lin2y[] = new int[3];
	int lin3x[] = new int[3];
	int lin3y[] = new int[3];
	int lin4x[] = new int[3];
	int lin4y[] = new int[3];
	static int[] Bubblex = new int[15];
	static int[] Bubbley = new int[15];
	static int[][] AteBubble = new int[15][15];
	static int AteSum=0;
	static int Score=0;
	static int ScoreTimeS=0;
	static int ScoreTimeM=0;
	static int ScoreTimeOn=0;
	static int TimeBonus=0;
	static int BestScore=0;
	static String ScoreMinus="";
	static int ScoreMinusCooldown=0;
	static int ScoreMinusY=40;
	static int ScoreMinusColor=255;
	static String ScoreBonus="";
	static int ScoreBonusCooldown=0;
	static int ScoreBonusX=40;
	static int ScoreBonusY=40;
	static int ScoreBonusColor=255;
	static JLabel lost = new JLabel("Vous avez perdu !");
	static JLabel win = new JLabel("Vous avez gagne !");
	static JButton again = new JButton("Recommencer ?");
	static JButton continuer = new JButton("Continuer ?");
	static JTextArea FinalScore = new JTextArea();
	JButton gris = new JButton();
	ToucheListener touche = new ToucheListener();
	SourisAgain Sagain = new SourisAgain();
	SourisContinuer Scontinuer = new SourisContinuer();
	SourisTestStop SSTOP = new SourisTestStop();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		this.setVisible(true);
		this.larg=this.getWidth();
		this.haut=this.getHeight();
		this.addMouseListener(SSTOP);
		Font font = new Font("Arial", Font.BOLD, 21);

		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.add(gris);

		lost.setPreferredSize(new Dimension(200, 100));
		lost.setVisible(false);
		lost.setFont(font);
		lost.setForeground(Color.WHITE);
		this.add(lost);

		win.setPreferredSize(new Dimension(600, 100));
		win.setVisible(false);
		win.setFont(font);
		win.setForeground(Color.WHITE);
		this.add(win);

		again.setPreferredSize(new Dimension(200, 100));
		again.setVisible(false);
		again.setFont(font);
		again.addMouseListener(Sagain);
		this.add(again);

		continuer.setPreferredSize(new Dimension(200, 100));
		continuer.setVisible(false);
		continuer.setFont(font);
		continuer.addMouseListener(Scontinuer);
		this.add(continuer);

		FinalScore.setPreferredSize(new Dimension(220, 250));
		FinalScore.setVisible(false);
		FinalScore.setFont(font);
		this.add(FinalScore);
		}
	
	public void paintComponent(Graphics g){
		for(int i=0; i<15; i++){
			Bubblex[i] = ((i+1)*30+this.getWidth()/5)+6;
			}
		for(int i=0; i<15; i++){
			Bubbley[i] = ((i+1)*30+this.getHeight()/5)+6;
			}
		
		int Sum=0;
		for(int k=0; k<15; k++){
			for(int l=0; l<15; l++){
				Sum+=AteBubble[k][l];
				}
			}
		AteSum=Sum;
		if (Score<0) Score=0;
		if (ScoreTimeOn==0 && keyflag==0){
			new ScoreTime().start();
			ScoreTimeOn=1;
			}
		if (ScoreTimeS==60){
			ScoreTimeS=0;
			ScoreTimeM+=1;
			}
		g.setColor(Color.black);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 21));
		g.drawString(String.valueOf(AteSum), 20, 20);
		g.drawString(String.valueOf(ScoreTimeM)+":"+String.valueOf(ScoreTimeS), this.getWidth()-50, 20);
		g.drawString(String.valueOf(Score), this.getWidth()-50, 40);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("Meilleur Score: "+String.valueOf(BestScore), this.getWidth()-150, this.getHeight()-145);
		//if (RedSens==0) g.fillRect(Redx, Redy, -400, 27);
		//if (RedSens==1) g.fillRect(Redx, Redy, 30, -400);	
		//if (RedSens==2) g.fillRect(Redx, Redy, 400, 27);	
		//if (RedSens==3) g.fillRect(Redx, Redy, 30, 400);

		//g.fillRect(Purplex-66, Purpley-60, 165, 147);
		g.setColor(Color.red);
		lin1x[0]=0;
		lin1x[1]=this.getWidth()/5;
		lin1x[2]=this.getWidth()/5;
		lin1y[0]=this.getHeight()/5;
		lin1y[1]=this.getHeight()/5;
		lin1y[2]=0;
		lin2x[0]=4*this.getWidth()/5;
		lin2x[1]=4*this.getWidth()/5;
		lin2x[2]=this.getWidth();
		lin2y[0]=0;
		lin2y[1]=this.getHeight()/5;
		lin2y[2]=this.getHeight()/5;
		lin3x[0]=0;
		lin3x[1]=this.getWidth()/5;
		lin3x[2]=this.getWidth()/5;
		lin3y[0]=4*this.getHeight()/5;
		lin3y[1]=4*this.getHeight()/5;
		lin3y[2]=this.getHeight();
		lin4x[0]=4*this.getWidth()/5;
		lin4x[1]=4*this.getWidth()/5;
		lin4x[2]=this.getWidth();
		lin4y[0]=this.getHeight();
		lin4y[1]=4*this.getHeight()/5;
		lin4y[2]=4*this.getHeight()/5;
		g.drawPolyline(lin1x, lin1y, 3);
		g.drawPolyline(lin2x, lin2y, 3);
		g.drawPolyline(lin3x, lin3y, 3);
		g.drawPolyline(lin4x, lin4y, 3);
		
		g.setColor(Color.yellow);
		for(int i=0; i<15; i++){
			for(int j=0; j<15; j++){
				if (AteBubble[i][j]==0){
					g.fillOval(Bubblex[i]-6, Bubbley[j]-6, 12, 12);
					}
				}
			}
		//System.out.println(BubbleEat()[0]+"\t"+ BubbleEat()[1]+"\t"+ BubbleEat()[2]+"\t"+ BubbleEat()[3]);
		if (BubbleEat()[0]!=0 && BubbleEat()[1]!=0){
			if (AteBubble[BubbleEat()[2]][BubbleEat()[3]]==0) Score+=10;
			AteBubble[BubbleEat()[2]][BubbleEat()[3]] = 1;
			//g.clearRect(Bubblex[BubbleEat()[2]]-6, Bubbley[BubbleEat()[3]]-6, 12, 12);
			}
		if (RedSens==0 && FFairyMagic==0){
			try{
				Image RedLeft=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FRedLeft.png"));
				g.drawImage(RedLeft, Redx, Redy, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (RedSens==1 && FFairyMagic==0){
			try{
				Image RedUp=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FRedUp.png"));
				g.drawImage(RedUp, Redx, Redy, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (RedSens==2 && FFairyMagic==0){
			try{
				Image RedRight=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FRedRight.png"));
				g.drawImage(RedRight, Redx, Redy, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (RedSens==3 && FFairyMagic==0){
			try{
				Image RedDown=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FRedDown.png"));
				g.drawImage(RedDown, Redx, Redy, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (BlueSens==0 && FFairyMagic==0){
			try{
				Image BlueLeft=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FBlueLeft.png"));
				g.drawImage(BlueLeft, Bluex, Bluey, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (BlueSens==1 && FFairyMagic==0){
			try{
				Image BlueUp=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FBlueUp.png"));
				g.drawImage(BlueUp, Bluex, Bluey, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (BlueSens==2 && FFairyMagic==0){
			try{
				Image BlueRight=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FBlueRight.png"));
				g.drawImage(BlueRight, Bluex, Bluey, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (BlueSens==3 && FFairyMagic==0){
			try{
				Image BlueDown=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FBlueDown.png"));
				g.drawImage(BlueDown, Bluex, Bluey, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (GreenSens==0 && FFairyMagic==0){
			try{
				Image GreenLeft=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FGreenLeft.png"));
				g.drawImage(GreenLeft, Greenx, Greeny, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (GreenSens==1 && FFairyMagic==0){
			try{
				Image GreenUp=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FGreenUp.png"));
				g.drawImage(GreenUp, Greenx, Greeny, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (GreenSens==2 && FFairyMagic==0){
			try{
				Image GreenRight=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FGreenRight.png"));
				g.drawImage(GreenRight, Greenx, Greeny, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (GreenSens==3 && FFairyMagic==0){
			try{
				Image GreenDown=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FGreenDown.png"));
				g.drawImage(GreenDown, Greenx, Greeny, 30, 27, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		try{
			if (FFairyMagic==1){
				Image Fantom=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FantomEatable.png"));
				Image FDeadEyes=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FantomDeadEyes.png"));
				if (RedDead==0) g.drawImage(Fantom, Redx, Redy, 30, 27, this);
				else if (RedDead==1) g.drawImage(FDeadEyes, Redx, Redy, 30, 27, this);
				if (BlueDead==0) g.drawImage(Fantom, Bluex, Bluey, 30, 27, this);
				else if (BlueDead==1) g.drawImage(FDeadEyes, Bluex, Bluey, 30, 27, this);
				if (GreenDead==0) g.drawImage(Fantom, Greenx, Greeny, 30, 27, this);
				else if (GreenDead==1) g.drawImage(FDeadEyes, Greenx, Greeny, 30, 27, this);
				}
			} catch (IOException e) {e.printStackTrace();}
		if (PurpleSens==0 && DarkFairyMagic==1){
			try{
				Image PurpleLeft=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FKPurpleLeft.png"));
				Image PurpleLeftTarget=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FKPurpleLeftTarget.png"));
				if (FVision(Purplex, Purpley, PurpleSens)){
					g.drawImage(PurpleLeftTarget, Purplex, Purpley, 33, 30, this);
					}
				else g.drawImage(PurpleLeft, Purplex, Purpley, 33, 30, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (PurpleSens==1 && DarkFairyMagic==1){
			try{
				Image PurpleUp=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FKPurpleUp.png"));
				Image PurpleUpTarget=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FKPurpleUpTarget.png"));
				if (FVision(Purplex, Purpley, PurpleSens)){
					g.drawImage(PurpleUpTarget, Purplex, Purpley, 33, 30, this);
					}
				else g.drawImage(PurpleUp, Purplex, Purpley, 33, 30, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (PurpleSens==2 && DarkFairyMagic==1){
			try{
				Image PurpleRight=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FKPurpleRight.png"));
				Image PurpleRightTarget=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FKPurpleRightTarget.png"));
				if (FVision(Purplex, Purpley, PurpleSens)){
					g.drawImage(PurpleRightTarget, Purplex, Purpley, 33, 30, this);
					}
				else g.drawImage(PurpleRight, Purplex, Purpley, 33, 30, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		if (PurpleSens==3 && DarkFairyMagic==1){
			try{
				Image PurpleDown=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FKPurpleDown.png"));
				Image PurpleDownTarget=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FKPurpleDownTarget.png"));
				if (FVision(Purplex, Purpley, PurpleSens)){
					g.drawImage(PurpleDownTarget, Purplex, Purpley, 33, 30, this);
					}
				else g.drawImage(PurpleDown, Purplex, Purpley, 33, 30, this);
				} catch (IOException e) {e.printStackTrace();}
			}
		try{
			Image BlueFairy=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/BlueFairy.png"));
			g.drawImage(BlueFairy, BlueFairyx, BlueFairyy, 19, 16, this);
			} catch (IOException e) {e.printStackTrace();}
		try{
			Image YellowFairy=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/YellowFairy.png"));
			g.drawImage(YellowFairy, YellowFairyx, YellowFairyy, 19, 16, this);
			} catch (IOException e) {e.printStackTrace();}
		try{
			Image DarkFairy=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/DarkFairy.png"));
			g.drawImage(DarkFairy, DarkFairyx, DarkFairyy, 19, 16, this);
			} catch (IOException e) {e.printStackTrace();}
		try{
			Image FFairy=ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/PacMan/Images/FantomFairy.png"));
			g.drawImage(FFairy, FFairyx, FFairyy, 19, 16, this);
			} catch (IOException e) {e.printStackTrace();}

		//System.out.println(BlueFairyTimeOn+"\t"+YellowFairyTimeOn+"\t"+DarkFairyTimeOn);

		if (keyflag==0){
			if (BlueFairyMagic==0 && BlueFairyTimeOn==0){
				BlueFairyx+=1;
				BlueFairyy = BlueFairyAppearY+(int) (50* Math.sin((BlueFairyx*3.1416)/90));
				if (BlueFairyx>=this.getWidth()){
					new BlueFairyTime().start();
					BlueFairyTimeOn=1;
					}
				}
			if (BlueFairyMagic==1){
				if (BlueFairyTimeOn==0){
					new BlueFairyTime().start();
					BlueFairyTimeOn=1;
					}
				BlueFairyM+=2;
				BlueFairyx=pacx+3 + (int) (35* Math.cos((BlueFairyM*3.1416)/90));
				BlueFairyy=pacy+2 + (int) (35* Math.sin((BlueFairyM*3.1416)/90));
				}
			if (FairyMagic(BlueFairyx, BlueFairyy) && DarkFairyMagic==0) BlueFairyMagic=1;
			}

		if (keyflag==0){
			if (YellowFairyMagic==0 && YellowFairyTimeOn==0){
				YellowFairyMoveX-=1;
				YellowFairyM+=4;
				YellowFairyx = YellowFairyMoveX+(int) (50* Math.cos((-YellowFairyM*3.1416)/90));;
				YellowFairyy = YellowFairyAppearY+(int) (50* Math.sin((YellowFairyM*3.1416)/90));
				if (YellowFairyx+19<=0){
					new YellowFairyTime().start();
					YellowFairyTimeOn=1;
					}
				}
			if (YellowFairyMagic==1){
				if (YellowFairyTimeOn==0){
					new YellowFairyTime().start();
					YellowFairyTimeOn=1;
					}
				YellowFairyM+=2;
				YellowFairyx=pacx+3 + (int) (35* Math.cos((YellowFairyM*3.1416)/90));
				YellowFairyy=pacy+2 + (int) (35* Math.sin((YellowFairyM*3.1416)/90));
				}
			if (FairyMagic(YellowFairyx, YellowFairyy) && DarkFairyMagic==0) YellowFairyMagic=1;
			}

		if (keyflag==0){
			if (DarkFairyMagic==0 && DarkFairyTimeOn==0){
				DarkFairyMoveX+=1;
				DarkFairyMoveY+=1;
				DarkFairyM+=1;
				DarkFairyx = DarkFairyMoveX+(int) (75* Math.cos((DarkFairyM*3.1416)/90));
				DarkFairyy = DarkFairyMoveY+(int) (75* Math.sin((DarkFairyM*3.1416)/90));
				if (DarkFairyx>this.getWidth() && DarkFairyy>this.getHeight()){
					new DarkFairyTime().start();
					DarkFairyTimeOn=1;
					}
				}
			if (DarkFairyMagic==1){
				if (DarkFairyTimeOn==0){
					new DarkFairyTime().start();
					DarkFairyTimeOn=1;
					}
				DarkFairyM+=2;
				DarkFairyx=pacx+3 + (int) (35* Math.cos((-DarkFairyM*3.1416)/90));
				DarkFairyy=pacy+2 + (int) (35* Math.sin((DarkFairyM*3.1416)/90));
				}
			if (FairyMagic(DarkFairyx, DarkFairyy)) DarkFairyMagic=1;
			}
		if (keyflag==0){
			if (FFairyMagic==0 && FFairyTime<15){
				if (FFairyAppear==1){
					FFairyMoveX=50;
					FFairyMoveY=400;
					FFairyAppear=0;
					}
				else if (FFairyAppear==2){
					FFairyMoveX=400;
					FFairyMoveY=50;
					FFairyAppear=0;
					}
				else if (FFairyAppear==3){
					FFairyMoveX=750;
					FFairyMoveY=400;
					FFairyAppear=0;
					}
				else if (FFairyAppear==4){
					FFairyMoveX=400;
					FFairyMoveY=750;
					FFairyAppear=0;
					}
				FFairyM+=1;
				FFairyx = FFairyMoveX+(int)(30*Math.cos((FFairyM*3.1416)/90));
				FFairyy = FFairyMoveY+(int)(30*Math.sin((FFairyM*3.1416)/90));
				if (FFairyTimeOn==0){
					new FantomFairyTime().start();
					FFairyTimeOn=1;
					}
				}
			if (FFairyMagic==1){
				if (FFairyTimeOn==0){
					new FantomFairyTime().start();
					FFairyTimeOn=1;
					}
				FFairyM+=2;
				FFairyx=pacx+3 + (int) (35*Math.cos((FFairyM*3.1416)/90));
				FFairyy=pacy+2 + (int) (35*Math.sin((FFairyM*3.1416)/90));
				}
			if (FairyMagic(FFairyx, FFairyy)) FFairyMagic=1;
			}
		
		if (pacSens==0){
			g.fillArc(pacx, pacy, 30, 30, pacArcStart, 180);
			g.fillArc(pacx, pacy, 30, 30, 0, pacArcEnd);
			}
		if (pacSens==1){
			g.fillArc(pacx, pacy, 30, 30, pacArcStart-90, 180);
			g.fillArc(pacx, pacy, 30, 30, 0-90, pacArcEnd);
			}
		if (pacSens==2){
			g.fillArc(pacx, pacy, 30, 30, pacArcStart-180, 180);
			g.fillArc(pacx, pacy, 30, 30, 0-180, pacArcEnd);
			}
		if (pacSens==3){
			g.fillArc(pacx, pacy, 30, 30, pacArcStart+90, 180);
			g.fillArc(pacx, pacy, 30, 30, 90, pacArcEnd);
			}
		
		
		if (pacArcTour<45){
			pacArcStart-=1;
			pacArcEnd+=1;
			pacArcTour+=1;
			}
		if (pacArcTour==45){
			pacArcStart+=45;
			pacArcEnd-=45;
			pacArcTour=0;
			}
		if (pacMotion==1 && keyflag==0){
			if (YellowFairyMagic==0){
				if (pacSens==0) pacx-=1;
				if (pacSens==1) pacy-=1;
				if (pacSens==2) pacx+=1;
				if (pacSens==3) pacy+=1;
				}
			if (YellowFairyMagic==1){
				if (pacSens==0) pacx-=3;
				if (pacSens==1) pacy-=3;
				if (pacSens==2) pacx+=3;
				if (pacSens==3) pacy+=3;
				}
			}
		
		if (Colision(Redx, Redy, 30, 27, RedSens) && RedSens==2){
			while(RedSens==2) RedSens=0+(int)(Math.random()*4);
			}
		if (Colision(Redx, Redy, 30, 27, RedSens) && RedSens==0){
			while(RedSens==0) RedSens=0+(int)(Math.random()*4);
			}
		if (Colision(Redx, Redy, 30, 27, RedSens) && RedSens==1){
			while(RedSens==1) RedSens=0+(int)(Math.random()*4);
			}
		if (Colision(Redx, Redy, 30, 27, RedSens) && RedSens==3){
			while(RedSens==3) RedSens=0+(int)(Math.random()*4);
			}
		if (RedMotion==1 && keyflag==0){
			if (RedChoixTimerOn==0 && (FVision(Redx, Redy, RedSens))==false){
				RedChoixTimerOn=1; 
				new RedChoixTimer().start();
				}
			if (FVision(Redx, Redy, RedSens) && FFairyMagic==1) RedSens=(int)(Math.random()*4);
			if (BlueFairyMagic==1) RedSens=(int)(Math.random()*4);
			if (RedSens==0){
				if (FVision(Redx, Redy, RedSens) && BlueFairyMagic==0) Redx-=2;
				else Redx-=1+FFairyMagic;
				}
			if (RedSens==1){
				if (FVision(Redx, Redy, RedSens) && BlueFairyMagic==0) Redy-=2;
				else Redy-=1+FFairyMagic;
				}
			if (RedSens==2){
				if (FVision(Redx, Redy, RedSens) && BlueFairyMagic==0) Redx+=2;
				else Redx+=1+FFairyMagic;
				}
			if (RedSens==3){
				if (FVision(Redx, Redy, RedSens) && BlueFairyMagic==0) Redy+=2;
				else Redy+=1+FFairyMagic;
				}
			}
		if (Colision(Bluex, Bluey, 30, 27, BlueSens) && BlueSens==2){
			while(BlueSens==2) BlueSens=0+(int)(Math.random()*4);
			}
		if (Colision(Bluex, Bluey, 30, 27, BlueSens) && BlueSens==0){
			while(BlueSens==0) BlueSens=0+(int)(Math.random()*4);
			}
		if (Colision(Bluex, Bluey, 30, 27, BlueSens) && BlueSens==1){
			while(BlueSens==1) BlueSens=0+(int)(Math.random()*4);
			}
		if (Colision(Bluex, Bluey, 30, 27, BlueSens) && BlueSens==3){
			while(BlueSens==3) BlueSens=0+(int)(Math.random()*4);
			}
		if (BlueMotion==1 && keyflag==0){
			if (BlueChoixTimerOn==0 && (FVision(Bluex, Bluey, BlueSens))==false){
				BlueChoixTimerOn=1; 
				new BlueChoixTimer().start();
				}
			if (FVision(Bluex, Bluey, BlueSens) && FFairyMagic==1) BlueSens=(int)(Math.random()*4);
			if (BlueFairyMagic==1) BlueSens=(int)(Math.random()*4);
			if (BlueSens==0){
				if (FVision(Bluex, Bluey, BlueSens) && BlueFairyMagic==0) Bluex-=2;
				else Bluex-=1+FFairyMagic;
				}
			if (BlueSens==1){
				if (FVision(Bluex, Bluey, BlueSens) && BlueFairyMagic==0) Bluey-=2;
				else Bluey-=1+FFairyMagic;
				}
			if (BlueSens==2){
				if (FVision(Bluex, Bluey, BlueSens) && BlueFairyMagic==0) Bluex+=2;
				else Bluex+=1+FFairyMagic;
				}
			if (BlueSens==3){
				if (FVision(Bluex, Bluey, BlueSens) && BlueFairyMagic==0) Bluey+=2;
				else Bluey+=1+FFairyMagic;
				}
			}
		if (Colision(Greenx, Greeny, 30, 27, GreenSens) && GreenSens==2){
			while(GreenSens==2) GreenSens=0+(int)(Math.random()*4);
			}
		if (Colision(Greenx, Greeny, 30, 27, GreenSens) && GreenSens==0){
			while(GreenSens==0) GreenSens=0+(int)(Math.random()*4);
			}
		if (Colision(Greenx, Greeny, 30, 27, GreenSens) && GreenSens==1){
			while(GreenSens==1) GreenSens=0+(int)(Math.random()*4);
			}
		if (Colision(Greenx, Greeny, 30, 27, GreenSens) && GreenSens==3){
			while(GreenSens==3) GreenSens=0+(int)(Math.random()*4);
			}
		if (GreenMotion==1 && keyflag==0){
			if (GreenChoixTimerOn==0 && (FVision(Greenx, Greeny, GreenSens))==false){
				GreenChoixTimerOn=1; 
				new GreenChoixTimer().start();
				}
			if (FVision(Greenx, Greeny, GreenSens) && FFairyMagic==1) GreenSens=(int)(Math.random()*4);
			if (BlueFairyMagic==1) GreenSens=(int)(Math.random()*4);
			if (GreenSens==0){
				if (FVision(Greenx, Greeny, GreenSens) && BlueFairyMagic==0) Greenx-=2;
				else Greenx-=1+FFairyMagic;
				}
			if (GreenSens==1){
				if (FVision(Greenx, Greeny, GreenSens) && BlueFairyMagic==0) Greeny-=2;
				else Greeny-=1+FFairyMagic;
				}
			if (GreenSens==2){
				if (FVision(Greenx, Greeny, GreenSens) && BlueFairyMagic==0) Greenx+=2;
				else Greenx+=1+FFairyMagic;
				}
			if (GreenSens==3){
				if (FVision(Greenx, Greeny, GreenSens) && BlueFairyMagic==0) Greeny+=2;
				else Greeny+=1+FFairyMagic;
				}
			}
		if (Colision(Purplex, Purpley, 33, 30, PurpleSens) && PurpleSens==2){
			while(PurpleSens==2) PurpleSens=0+(int)(Math.random()*4);
			}
		if (Colision(Purplex, Purpley, 33, 30, PurpleSens) && PurpleSens==0){
			while(PurpleSens==0) PurpleSens=0+(int)(Math.random()*4);
			}
		if (Colision(Purplex, Purpley, 33, 30, PurpleSens) && PurpleSens==1){
			while(PurpleSens==1) PurpleSens=0+(int)(Math.random()*4);
			}
		if (Colision(Purplex, Purpley, 33, 30, PurpleSens) && PurpleSens==3){
			while(PurpleSens==3) PurpleSens=0+(int)(Math.random()*4);
			}
		if (PurpleMotion==1 && keyflag==0 && DarkFairyMagic==1){
			if (PurpleChoixTimerOn==0 && (FVision(Purplex, Purpley, PurpleSens))==false){
				PurpleChoixTimerOn=1; 
				new PurpleChoixTimer().start();
				}
			if (PurpleSens==0){
				if (FVision(Purplex, Purpley, PurpleSens) && BlueFairyMagic==0) Purplex-=3;
				if (BlueFairyMagic==1) PurpleSens=PurpleSens=(int)(Math.random()*4);
				else Purplex-=1;
				}
			if (PurpleSens==1){
				if (FVision(Purplex, Purpley, PurpleSens) && BlueFairyMagic==0) Purpley-=3;
				if (BlueFairyMagic==1) PurpleSens=PurpleSens=(int)(Math.random()*4);
				else Purpley-=1;
				}
			if (PurpleSens==2){
				if (FVision(Purplex, Purpley, PurpleSens) && BlueFairyMagic==0) Purplex+=3;
				if (BlueFairyMagic==1) PurpleSens=PurpleSens=(int)(Math.random()*4);
				else Purplex+=1;
				}
			if (PurpleSens==3){
				if (FVision(Purplex, Purpley, PurpleSens) && BlueFairyMagic==0) Purpley+=3;
				if (BlueFairyMagic==1) PurpleSens=PurpleSens=(int)(Math.random()*4);
				else Purpley+=1;
				}
			}
		if (FKVision()!=5) PurpleSens=FKVision();


		if (FantomEat(Redx, Redy, 30, 27) && FFairyMagic==1 && RedDead==0){
			ScoreBonusX=Redx+12;
			ScoreBonusY=Redy+12;
			ScoreBonus="+200";
			ScoreBonusCooldown=1;
			Score+=200;
			RedDead=1;
			RedMotion=0;
			try{
				Thread.sleep(500);
				} catch(InterruptedException e) {}
			}
		if (FantomEat(Bluex, Bluey, 30, 27) && FFairyMagic==1 && BlueDead==0){
			ScoreBonusX=Bluex+12;
			ScoreBonusY=Bluey+12;
			ScoreBonus="+200";
			ScoreBonusCooldown=1;
			Score+=200;
			BlueDead=1;
			BlueMotion=0;
			try{
				Thread.sleep(500);
				} catch(InterruptedException e) {}
			}
		if (FantomEat(Greenx, Greeny, 30, 27) && FFairyMagic==1 && GreenDead==0){
			ScoreBonusX=Greenx+12;
			ScoreBonusY=Greeny+12;
			ScoreBonus="+200";
			ScoreBonusCooldown=1;
			Score+=200;
			GreenDead=1;
			GreenMotion=0;
			try{
				Thread.sleep(500);
				} catch(InterruptedException e) {}
			}

		//if (AteSum>=0){
			//g.setColor(Color.white);
			//g.fillRect((this.getWidth()/2)-15, 15, 30, 90);
			//}
		//g.setColor(Color.red); 
		//g.fillRect(pacx+12, pacy+12, 6, 6);
		
		if (ScoreMinusCooldown==1){
			g.setColor(new Color(ScoreMinusColor, ScoreMinusColor, 0));
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString(ScoreMinus, this.getWidth()-50, ScoreMinusY);
			ScoreMinusY+=1;
			ScoreMinusColor-=5;
			if (ScoreMinusColor==0){
				ScoreMinusY=40;
				ScoreMinusCooldown=0;
				ScoreMinusColor=255;
				}
			}
		if (ScoreBonusCooldown==1){
			g.setColor(new Color(ScoreBonusColor, ScoreBonusColor, 0));
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString(ScoreBonus, ScoreBonusX, ScoreBonusY);
			ScoreBonusY-=1;
			ScoreBonusColor-=17;
			if (ScoreBonusColor==0){
				ScoreBonusCooldown=0;
				ScoreBonusColor=255;
				}
			}
		if (AteSum==225){
			double DivTime = Math.log((double)(ScoreTimeS+60*ScoreTimeM));
			TimeBonus = Score/(int)Math.ceil(DivTime);
			if (BestScore<Score+TimeBonus) BestScore=Score+TimeBonus;

			//System.out.print(DivTime+"\t"+TimeBonus);
			}

		try{
			Thread.sleep(7);
			} catch (InterruptedException e) {}

		}
	public boolean Colision(int x, int y, int Width, int Height, int Sens){
		int Cx=0;
		int Cy=0;
		if (Sens==1){
			for(int i=x; i<x+Width; i++){
				if (lin1x[0]<=i && i<=lin1x[1]){
					Cx=1;
					break;
					}
				}
			if (y==lin1y[0]) Cy=1;
			
			for(int i=x; i<x+Width; i++){
				if (lin2x[1]<=i && i<=lin2x[2]){
					Cx=1;
					break;
					}
				}
			if (y<=0){
				Cx=1;
				Cy=1;
				}
			}
		if (Sens==3){
			for(int i=x; i<x+Width; i++){
				if (lin3x[0]<=i && i<=lin3x[1]){
					Cx=1;
					break;
					}
				}
			if (y+Height==lin3y[0]) Cy=1;
			for(int i=x; i<x+Width; i++){
				if (lin4x[1]<=i && i<=lin4x[2]){
					Cx=1;
					break;
					}
				}
			if (y+Height>=this.getHeight()){
				Cx=1;
				Cy=1;
				}
			}
		if (Sens==2){
			for(int i=y; i<y+Height; i++){
				if (lin2y[0]<=i && i<=lin2y[1]){
					Cy=1;
					break;
					}
				}
			if (x+Width==lin2x[0]) Cx=1;
			
			for(int i=y; i<y+Height; i++){
				if (lin4y[1]<=i && i<=lin4y[0]){
					Cy=1;
					break;
					}
				}
			if (x+Width>=this.getWidth()){
				Cx=1;
				Cy=1;
				}
			}
		if (Sens==0){
			for(int i=y; i<y+Height; i++){
				if (lin1y[2]<i && i<lin1y[1]){
					Cy=1;
					break;
					}
				}
			if (x==lin1x[1]) Cx=1;
			
			for(int i=y; i<y+Height; i++){
				if (lin3y[1]<i && i<lin3y[2]){
					Cy=1;
					break;
					}
				}
			if (x<=0){
				Cx=1;
				Cy=1;
				}
			}
		return Cx==1 && Cy==1;
		}
	public boolean Eat(){
		int Fx=0;
		int Fy=0;
		int FKx=0;
		int FKy=0;
		for(int i=pacx; i<pacx+30; i++){
			if (Redx<i && i<Redx+30){
				Fx=1;
				break;
				}
			else if (Bluex<i && i<Bluex+30){
				Fx=2;
				break;
				}
			else if (Greenx<i && i<Greenx+30){
				Fx=3;
				break;
				}
			else if (Purplex<i && i<Purplex+33 && DarkFairyMagic==1){
				FKx=4;
				break;
				}
			}
		for(int i=pacy; i<pacy+30; i++){
			if (Redy<i && i<Redy+27){
				Fy=1;
				break;
				}
			else if (Bluey<i && i<Bluey+27){
				Fy=2;
				break;
				}
			else if (Greeny<i && i<Greeny+27){
				Fy=3;
				break;
				}
			else if (Purpley<i && i<Purpley+30 && DarkFairyMagic==1){
				FKy=4;
				break;
				}
			}
		return (Fx==1 && Fy==1) || (Fx==2 && Fy==2) || (Fx==3 && Fy==3) || (FKx==4 && FKy==4);
		}
	public static boolean FVision(int x, int y, int Sens){
		int Vx=0;
		int Vy=0;
		if (Sens==0){
			for(int i=pacx; i<pacx+30; i++){
				if (x-400<i && i<x){
					Vx=1;
					break;
					}
				}
			for(int i=pacy; i<pacy+30; i++){
				if (y<i && i<y+27){
					Vy=1;
					break;
					}
				}
			}
		if (Sens==1){
			for(int i=pacx; i<pacx+30; i++){
				if (x<i && i<x+30){
					Vx=1;
					break;
					}
				}
			for(int i=pacy; i<pacy+30; i++){
				if (y-400<i && i<y){
					Vy=1;
					break;
					}
				}
			}
		if (Sens==2){
			for(int i=pacx; i<pacx+30; i++){
				if (x<i && i<x+400){
					Vx=1;
					break;
					}
				}
			for(int i=pacy; i<pacy+30; i++){
				if (y<i && i<y+27){
					Vy=1;
					break;
					}
				}
			}
		if (Sens==3){
			for(int i=pacx; i<pacx+30; i++){
				if (x<i && i<x+30){
					Vx=1;
					break;
					}
				}
			for(int i=pacy; i<pacy+30; i++){
				if (y<i && i<y+400){
					Vy=1;
					break;
					}
				}
			}
		return Vx==1 && Vy==1;
		}
	public int FKVision(){
		int Vx=8;
		int Vy=8;
		int Sens=5;

		if ((Purplex-66<pacx+30 && pacx+30<Purplex) && (Purpley<pacy+15 && pacy+15<Purpley+30)){
			Vx=0;
			Vy=20;
			}
		
		if (((Purplex-66<pacx && pacx<Purplex+99) || (Purplex-66<pacx+30 && pacx+30<Purplex+99)) && Purpley-60<pacy+30 && pacy+30<Purpley){
			Vx=13;
			Vy=1;
			}

		if ((Purplex+33<pacx && pacx<Purplex+99) && (Purpley<pacy+15 && pacy+15<Purpley+30)){
			Vx=2;
			Vy=20;
			}

		if (((Purplex-66<pacx && pacx<Purplex+99) || (Purplex-66<pacx+30 && pacx+30<Purplex+99)) && Purpley+30<pacy && pacy<Purpley+90){
			Vx=13;
			Vy=3;
			}

		if (Vx==0 && Vy==20) Sens=0;
		else if (Vx==2 && Vy==20) Sens=2;
		else if (Vx==13 && Vy==1) Sens=1;
		else if (Vx==13 && Vy==3) Sens=3;
		return Sens;
		}
		
	public int[] BubbleEat(){
		int Eat[] = new int[4];
		if (YellowFairyMagic==0){
			for(int i=0; i<15; i++){
				if (pacx+12<=Bubblex[i] && Bubblex[i]<=pacx+18){
					Eat[0]=Bubblex[i];
					Eat[2]=i;
					break;
					}
				}
			for(int i=0; i<15; i++){
				if (pacy+12<=Bubbley[i] && Bubbley[i]<=pacy+18){
					Eat[1]=Bubbley[i];
					Eat[3]=i;
					break;
					}
				}
			}
		if (YellowFairyMagic==1){
			for(int i=0; i<15; i++){
				if (pacx+7<=Bubblex[i] && Bubblex[i]<=pacx+23){
					Eat[0]=Bubblex[i];
					Eat[2]=i;
					break;
					}
				}
			for(int i=0; i<15; i++){
				if (pacy+7<=Bubbley[i] && Bubbley[i]<=pacy+23){
					Eat[1]=Bubbley[i];
					Eat[3]=i;
					break;
					}
				}
			}
		return Eat;
		}
	public boolean FairyMagic(int x, int y){
		int Fx=0;
		int Fy=0;
		for(int i=pacx; i<pacx+30; i++){
			if (x<i && i<x+19){
				Fx=1;
				break;
				}
			}
		for(int i=pacy; i<pacy+30; i++){
			if (y<i && i<y+16){
				Fy=1;
				break;
				}
			}
		return Fx==1 && Fy==1;
		}
	public boolean FantomEat(int x, int y, int width, int height){
		int Ex=0;
		int Ey=0;
		if (x<pacx+15 && pacx+15<x+width) Ex=1;
		if (y<pacy+15 && pacy+15<y+height) Ey=1;
		return Ex==1 && Ey==1;
		}
			
	}
class DarkFairyTime extends Thread{
	public void run(){
		if (Pan.DarkFairyMagic==1){
			if (Pan.BlueFairyMagic==1){
				Pan.BlueFairyMagic=0;
				Pan.BlueFairyx=900;
				}
			if (Pan.YellowFairyMagic==1){
				Pan.YellowFairyMagic=0;
				Pan.YellowFairyx=-100;
				}
			if (Pan.FFairyMagic==1){
				Pan.FFairyMagic=0;
				Pan.FFairyx=-100;
				}
			for(int i=0; i<13 && Pan.DarkFairyMagic==1; i++){
				Pan.ScoreMinus="-10";
				try{
					sleep(1000);
					} catch (InterruptedException e) {}
				if (Pan.keyflag==0){
					Pan.ScoreMinusCooldown=1;
					Pan.Score-=10;
					}
				}
			Pan.DarkFairyMoveX=900;
			Pan.DarkFairyMoveY=900;
			Pan.DarkFairyMagic=0;
			Pan.DarkFairyTimeOn=0;
			}
		if (Pan.DarkFairyMagic==0){
			try{
				sleep(1000*(13+(int)(Math.random()*6)));
				} catch (InterruptedException e) {}
			Pan.DarkFairyTimeOn=0;
			Pan.DarkFairyMoveX=0+(int)(Math.random()*250);
			Pan.DarkFairyMoveY=0+(int)(Math.random()*250);
			}
		}
	}
class YellowFairyTime extends Thread{
	public void run(){
		if (Pan.YellowFairyMagic==1){
			try{
				sleep(12000);
				} catch (InterruptedException e) {}
			Pan.YellowFairyMoveX=-100;
			Pan.YellowFairyMagic=0;
			Pan.YellowFairyTimeOn=0;
			}
		if (Pan.YellowFairyMagic==0){
			try{
				sleep(1000*(15+(int)(Math.random()*6)));
				} catch (InterruptedException e) {}
			Pan.YellowFairyM=0;
			Pan.YellowFairyMoveX=819;
			Pan.YellowFairyAppearY=100+(int)(Math.random()*501);
			Pan.YellowFairyTimeOn=0;
			}
		}
	}
class BlueFairyTime extends Thread{
	public void run(){
		if (Pan.BlueFairyMagic==1){
			try{
				sleep(20000);
				} catch (InterruptedException e) {}
			Pan.BlueFairyx=900;
			Pan.BlueFairyMagic=0;
			Pan.BlueFairyTimeOn=0;
			}
		if (Pan.BlueFairyMagic==0){
			try{
				sleep(1000*(35+(int)(Math.random()*6)));
				} catch (InterruptedException e) {}
			Pan.BlueFairyx=-19;
			Pan.BlueFairyAppearY=100+(int)(Math.random()*501);
			Pan.BlueFairyTimeOn=0;
			}
		}
	}
class FantomFairyTime extends Thread{
	public void run(){
		if (Pan.FFairyMagic==0 && Pan.FFairyTime<15){
			try{
				sleep(1000);
				} catch (InterruptedException e) {}
			Pan.FFairyTime+=1;
			Pan.FFairyTimeOn=0;
			}
		if (Pan.FFairyMagic==1){
			try{
				sleep(20000);
				} catch (InterruptedException e) {}
			Pan.RedDead=0;
			Pan.BlueDead=0;
			Pan.GreenDead=0;
			Pan.RedMotion=1;
			Pan.GreenMotion=1;
			Pan.BlueMotion=1;
			Pan.FFairyx=1000;
			Pan.FFairyMagic=0;
			Pan.FFairyTimeOn=0;
			Pan.FFairyTime=15;
			}
		if (Pan.FFairyTime==15){
			Pan.FFairyx=1000;
			try{
				sleep(30000);
				} catch (InterruptedException e) {}
			Pan.FFairyAppear=1+(int)(Math.random()*4);
			Pan.FFairyTime=0;
			Pan.FFairyTimeOn=0;
			}
		}
	}
class SourisTestStop implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1) Pan.pacMotion=1;
		if (c==3) Pan.pacMotion=0;
		}
	public void mousePressed(MouseEvent event){
		int c = event.getButton();
		if (c==2){
			if (Pan.pacSens==0) Pan.pacx-=1;
			if (Pan.pacSens==1) Pan.pacy-=1;
			if (Pan.pacSens==2) Pan.pacx+=1;
			if (Pan.pacSens==3) Pan.pacy+=1;
			}
		}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class ScoreTime extends Thread{
	public void run(){
		try{
			sleep(1000);
			} catch (InterruptedException e) {}
		if (Pan.keyflag==0){
			Pan.ScoreTimeS+=1;
			Pan.ScoreTimeOn=0;
			}
		}
	}
class PurpleChoixTimer extends Thread{
	public void run(){
		try{
			if (Pan.FFairyMagic==0) sleep(1000*(1+(int)(Math.random()*5)));
			else sleep(500*(1+(int)(Math.random()*5)));
			} catch (InterruptedException e) {}
		if (Pan.FVision(Pan.Purplex, Pan.Purpley, Pan.PurpleSens)==false){
			Pan.PurpleSens=0+(int)(Math.random()*4);
			}
		Pan.PurpleChoixTimerOn=0;
		}
	}
class RedChoixTimer extends Thread{
	public void run(){
		try{			
			if (Pan.FFairyMagic==0) sleep(1000*(1+(int)(Math.random()*5)));
			else sleep(500*(1+(int)(Math.random()*5)));
			} catch (InterruptedException e) {}
		if (Pan.FVision(Pan.Redx, Pan.Redy, Pan.RedSens)==false){
			Pan.RedSens=0+(int)(Math.random()*4);
			}
		Pan.RedChoixTimerOn=0;
		}
	}
class BlueChoixTimer extends Thread{
	public void run(){
		try{
			if (Pan.FFairyMagic==0) sleep(1000*(1+(int)(Math.random()*5)));
			else sleep(500*(1+(int)(Math.random()*5)));
			} catch (InterruptedException e) {}
		if (Pan.FVision(Pan.Bluex, Pan.Bluey, Pan.BlueSens)==false){
			Pan.BlueSens=0+(int)(Math.random()*4);
			}
		Pan.BlueChoixTimerOn=0;
		}
	}
class GreenChoixTimer extends Thread{
	public void run(){
		try{
			if (Pan.FFairyMagic==0) sleep(1000*(1+(int)(Math.random()*5)));
			else sleep(500*(1+(int)(Math.random()*5)));
			} catch (InterruptedException e) {}
		if (Pan.FVision(Pan.Greenx, Pan.Greeny, Pan.GreenSens)==false){
			Pan.GreenSens=0+(int)(Math.random()*4);
			}
		Pan.GreenChoixTimerOn=0;	
		}
	}
class SourisContinuer implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1){
			Pan.BlueFairyMagic=0;
			Pan.YellowFairyMagic=0;
			Pan.DarkFairyMagic=0;
			Pan.FFairyMagic=0;
			Pan.pacx=(800/2)-15;
			Pan.pacy=800-30;
			Pan.Redx=0+(int)(Math.random()*771);
			Pan.Redy=200;
			Pan.Bluex=0+(int)(Math.random()*771);
			Pan.Bluey=300;
			Pan.Greenx=0+(int)(Math.random()*771);
			Pan.Greeny=500;
			Pan.Purplex=0+(int)(Math.random()*771);
			Pan.Purpley=400;
			Pan.ScoreTimeOn=0;
			Pan.ScoreMinusColor=255;
			Pan.ScoreMinusY=40;
			Pan.keyflag=0;
			Pan.win.setVisible(false);
			Pan.continuer.setVisible(false);
			Pan.lost.setVisible(false);
			Pan.again.setVisible(false);
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisAgain implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1){
			Pan.BlueFairyMagic=0;
			Pan.YellowFairyMagic=0;
			Pan.DarkFairyMagic=0;
			Pan.FFairyMagic=0;
			Pan.pacx=(800/2)-15;
			Pan.pacy=800-30;
			Pan.AteSum=0;
			for(int k=0; k<15; k++){
				for(int l=0; l<15; l++){
					Pan.AteBubble[k][l]=0;
					}
				}
			Pan.Redx=0+(int)(Math.random()*771);
			Pan.Redy=200;
			Pan.Bluex=0+(int)(Math.random()*771);
			Pan.Bluey=300;
			Pan.Greenx=0+(int)(Math.random()*771);
			Pan.Greeny=500;
			Pan.BlueFairyx=900;
			Pan.YellowFairyx=-50;
			Pan.DarkFairyx=900;
			Pan.DarkFairyy=900;
			Pan.keyflag=0;
			Pan.ScoreTimeS=0;
			Pan.ScoreTimeM=0;
			Pan.ScoreTimeOn=0;
			Pan.ScoreMinusColor=255;
			Pan.ScoreMinusY=40;
			Pan.Score=0;
			Pan.win.setVisible(false);
			Pan.lost.setVisible(false);
			Pan.again.setVisible(false);
			Pan.continuer.setVisible(false);
			Pan.FinalScore.setVisible(false);
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class ToucheListener implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		if (Pan.DarkFairyMagic==0){
			if (c==0x25) Pan.pacSens=0;
			if (c==0x26) Pan.pacSens=1;
			if (c==0x27) Pan.pacSens=2;
			if (c==0x28) Pan.pacSens=3;
			}
		if (Pan.DarkFairyMagic==1){
			if (c==0x25) Pan.pacSens=2;
			if (c==0x26) Pan.pacSens=3;
			if (c==0x27) Pan.pacSens=0;
			if (c==0x28) Pan.pacSens=1;
			}
		}
	public void keyReleased(KeyEvent event){}
	}

public class PacMan{
	public static void main(String[] args){
		Fen fen = new Fen("Fenetre", 817, 840);
		Pan pan = new Pan(800, 800);
		fen.setContentPane(pan);
		while (true){
			pan.repaint();
			if (pan.Colision(pan.pacx, pan.pacy, 30, 30, pan.pacSens)) pan.pacMotion=0;
			else pan.pacMotion=1;
			if (pan.Eat() && pan.BlueFairyMagic==0 && pan.FFairyMagic==0){
				if (pan.keyflag==0){
					pan.Score-=100;
					pan.ScoreMinus="-100";
					pan.ScoreMinusCooldown=1;
					}
				pan.keyflag=1;
				pan.lost.setVisible(true);
				pan.continuer.setVisible(true);
				pan.again.setVisible(true);
				}
			if (pan.AteSum==225){
				pan.keyflag=1;
				pan.win.setVisible(true);
				pan.again.setVisible(true);
				pan.FinalScore.setText("Score:\nScore Bubble = "+pan.Score+"\nBonus Temps = "+pan.TimeBonus+"\nScore Final = "+(pan.Score+pan.TimeBonus)+"\n\nMeilleur Score: "+pan.BestScore);
				pan.FinalScore.setVisible(true);
				}
			}
		}
}
		