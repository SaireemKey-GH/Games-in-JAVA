import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

class Fen extends JFrame{
	ToucheListener touche = new ToucheListener();
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setAlwaysOnTop(true);
		}
	}

class Pan extends JPanel{
	static int x=104;
	static int y=104;
	static int xP1=200+(int)(Math.random()*401);
	static int yP1=200+(int)(Math.random()*401);
	static int xP1Signe=1;
	static int yP1Signe=1;
	static int xP2=0;
	static int yP2=0;
	static int xP2Signe=1;
	static int yP2Signe=1;
	static int xP3=0;
	static int yP3=0;
	static int xP3Signe=1;
	static int yP3Signe=1;
	static int xDP1=400;
	static int yDP1=400;
	static int Invincible=0;
	static int InvAlea = 1+(int)(Math.random()*10);
	static int xCoefInv= 2+(int)(Math.random()*5);
	static int yCoefInv= 2+(int)(Math.random()*5);
	static int x1Inv=20;
	static int x2Inv=60;
	static int x3Inv=60;
	static int x4Inv=20;
	static int y1Inv=20;
	static int y2Inv=20;
	static int y3Inv=60;
	static int y4Inv=60;
	static int x1DP1=20;
	static int x2DP1=40;
	static int x3DP1=60;
	static int x4DP1=40;
	static int y1DP1=120;
	static int y2DP1=100;
	static int y3DP1=120;
	static int y4DP1=140;
	
	static int losaTourInv=0;
	static int losaTourInvk=0;
	static int losaTourDP1=0;
	static int losaTourDP1k=0;
	static int nombreP=1;
	static int nombreWin=0;
	static int nombreLost=0;
	static int nombreWinOn=0;
	static int nombreLostOn=0;
	static int arcStart=0;
	static int arcEnd=45;
	static int Case=0;
	static int Stop=0;
	static int upDifficultOn=0;
	static int C=0;
	static int gameplay=0;
	static int difficult=0;
	static int vieY=300;
	static int Expli=0;
	static int OFinC = 0+(int)(Math.random()*6);
	static int RFinC = 0+(int)(Math.random()*6);
	static int P=0;
	static int larg;
	static int haut;
	static int xF = 1 + (int)(Math.random()*8);
	static int yF = 1 + (int)(Math.random()*8);
	static int OxF = 1 + (int)(Math.random()*8);
	static int OyF = 1 + (int)(Math.random()*8);
	static int keyflag = 0;
	static JLabel win = new JLabel("Vous avez gagnee !");
	static JLabel lost = new JLabel("Vous avez perdu !");
	static JButton JeuSouris = new JButton("Jouer avec la souris ?");
	static JButton JeuTouche = new JButton("Jouer au clavier ?");
	static JButton again = new JButton("Recommencer ?");
	static JButton stop = new JButton("Arreter ?");
	static JButton compris = new JButton("Compris ?");
	static JButton normal = new JButton("Normale");
	static JButton mortel = new JButton("Mortelle");
	static JButton upDifficult = new JButton("Hausser la difficultee ?");
	JLabel RFinNoC = new JLabel("Couleur Carre pas bon");
	JLabel OFinNoC = new JLabel("Couleur Ronde pas bon");
	JLabel RFinNoF = new JLabel("Forme Carre !");
	JLabel OFinNoF = new JLabel("Forme Ronde !");
	static JLabel ToucheExpli = new JLabel("Appuyez sur C pour changer de couleur | Appuyez sur F pour changer de forme");
	static JLabel SourisExpli = new JLabel("Clique gauche pour changer de couleur | Clique droit pour changer de forme");
	static JLabel ChoixDifficult = new JLabel("Choisissez la difficultee");
	JTextArea Results = new JTextArea();
	JTextArea Gris = new JTextArea();
	JButton gris = new JButton();
	SourisMoveListener sourisMove = new SourisMoveListener();
	SourisColor sourisC = new SourisColor();
	public Pan(int larg, int haut){
		this.larg=larg;
		this.haut=haut;
		this.setPreferredSize(new Dimension(larg, haut));
		this.setVisible(true);
		ToucheListener touche = new ToucheListener();
		
		win.setPreferredSize(new Dimension(200, 100));
		Font font = new Font("Arial", Font.BOLD, 20);
		win.setFont(font);
		win.setVisible(false);
		win.setForeground(Color.WHITE);
		this.add(win);

		SourisButtonAgain Sagain = new SourisButtonAgain();
		again.setPreferredSize(new Dimension(150, 100));
		again.addMouseListener(Sagain);
		again.setVisible(false);
		this.add(again);
		
		gris.setPreferredSize(new Dimension(0,0));
		gris.addKeyListener(touche);
		gris.setVisible(true);
		this.add(gris);

		Gris.setPreferredSize(new Dimension(0,0));
		Gris.addKeyListener(touche);
		Gris.setVisible(true);
		//this.add(Gris);

		RFinNoC.setFont(font);
		RFinNoC.setPreferredSize(new Dimension(250, 100));
		RFinNoC.setVisible(false);
		this.add(RFinNoC);

		OFinNoC.setFont(font);
		OFinNoC.setPreferredSize(new Dimension(250, 100));
		OFinNoC.setVisible(false);
		this.add(OFinNoC);

		RFinNoF.setFont(font);
		RFinNoF.setPreferredSize(new Dimension(200, 100));
		RFinNoF.setVisible(false);
		this.add(RFinNoF);

		OFinNoF.setFont(font);
		OFinNoF.setPreferredSize(new Dimension(200, 100));
		OFinNoF.setVisible(false);
		this.add(OFinNoF);

		SourisStop Sstop = new SourisStop();
		stop.setPreferredSize(new Dimension(150, 100));
		stop.addMouseListener(Sstop);
		stop.setVisible(false);
		this.add(stop);

		Results.setFont(font);
		Results.setPreferredSize(new Dimension(250, 200));
		Results.setVisible(false);
		this.add(Results);

		SourisDebutTouche STouche = new SourisDebutTouche();
		JeuTouche.setPreferredSize(new Dimension(150, 100));
		JeuTouche.addMouseListener(STouche);
		JeuTouche.setVisible(true);
		this.add(JeuTouche);

		SourisDebutSouris SSouris = new SourisDebutSouris();
		JeuSouris.setPreferredSize(new Dimension(160, 100));
		JeuSouris.addMouseListener(SSouris);
		JeuSouris.setVisible(true);
		this.add(JeuSouris);

		ToucheExpli.setPreferredSize(new Dimension(780, 100));
		ToucheExpli.setFont(font);
		ToucheExpli.setVisible(false);
		ToucheExpli.setForeground(Color.WHITE);
		this.add(ToucheExpli);

		SourisExpli.setPreferredSize(new Dimension(780, 100));
		SourisExpli.setFont(font);
		SourisExpli.setVisible(false);
		SourisExpli.setForeground(Color.WHITE);
		this.add(SourisExpli);

		SourisCompris sourisCps = new SourisCompris();
		compris.setPreferredSize(new Dimension(100, 80));
		compris.setVisible(false);
		compris.addMouseListener(sourisCps);
		this.add(compris);

		lost.setPreferredSize(new Dimension(200, 100));
		lost.setFont(font);
		lost.setVisible(false);
		lost.setForeground(Color.WHITE);
		this.add(lost);

		ChoixDifficult.setPreferredSize(new Dimension(780, 100));
		ChoixDifficult.setFont(font);
		ChoixDifficult.setVisible(false);
		ChoixDifficult.setForeground(Color.WHITE);
		this.add(ChoixDifficult);

		SourisDiffNormal sourisFacile = new SourisDiffNormal();
		normal.setPreferredSize(new Dimension(100, 80));
		normal.setVisible(false);
		normal.addMouseListener(sourisFacile);
		this.add(normal);

		SourisDiffDeadfull sourisMortelle = new SourisDiffDeadfull();
		mortel.setPreferredSize(new Dimension(100, 80));
		mortel.setVisible(false);
		mortel.addMouseListener(sourisMortelle);
		this.add(mortel);

		SourisUpDifficult sourisUpDiff = new SourisUpDifficult();
		upDifficult.setPreferredSize(new Dimension(180, 80));
		upDifficult.setVisible(false);
		upDifficult.addMouseListener(sourisUpDiff);
		this.add(upDifficult);
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		for(int i=0; i<=this.getWidth(); i+=this.getWidth()/10){
			g.drawLine(i,0,i, this.getHeight());
			}
		for(int i=0; i<=this.getHeight(); i+=this.getHeight()/10){
			g.drawLine(0,i, this.getWidth(),i);
			}
		g.setColor(Color.red);
		g.fillRect((this.getWidth()/10)-4, this.getHeight()/10, 8, 8*this.getHeight()/10);
		g.fillRect(this.getWidth()/10, (this.getHeight()/10)-4, 8*this.getWidth()/10, 8);
		g.fillRect((9*this.getWidth()/10)-4, this.getHeight()/10, 8, 8*this.getHeight()/10); 
		g.fillRect(this.getWidth()/10, (9*this.getHeight()/10)-4, 8*this.getWidth()/10, 8);
		switch(RFinC){
			case 0: g.setColor(Color.red); break;
			case 1: g.setColor(Color.blue); break;
			case 2: g.setColor(Color.green); break;
			case 3: g.setColor(Color.orange); break;
			case 4: g.setColor(Color.pink); break;
			case 5: g.setColor(Color.white); break;
			}
		g.fillRect((xF*this.getWidth()/10)+30, (yF*this.getHeight()/10)+10, 20, 60); 
		switch(OFinC){
			case 0: g.setColor(Color.red); break;
			case 1: g.setColor(Color.blue); break;
			case 2: g.setColor(Color.green); break;
			case 3: g.setColor(Color.orange); break;
			case 4: g.setColor(Color.pink); break;
			case 5: g.setColor(Color.white); break;
			}
		g.fillOval((OxF*this.getWidth()/10)+30, (OyF*this.getHeight()/10)+10, 20, 60);
		if (this.Invincible==0){
			switch(C){
				case 0: g.setColor(Color.red); break;
				case 1: g.setColor(Color.blue); break;
				case 2: g.setColor(Color.green); break;
				case 3: g.setColor(Color.orange); break;
				case 4: g.setColor(Color.pink); break;
				case 5: g.setColor(Color.white); break;
				case 6: this.C=0; break;
				}
			}
		else {
			this.C+=1;
			switch(C){
				case 0: g.setColor(Color.red); break;
				case 1: g.setColor(Color.blue); break;
				case 2: g.setColor(Color.green); break;
				case 3: g.setColor(Color.orange); break;
				case 4: g.setColor(Color.pink); break;
				case 5: g.setColor(Color.white); break;
				case 6: this.C=0; break;
				}
			}
		if (this.P==0) g.fillOval(x, y, ((this.getWidth())/10)/2, ((this.getHeight())/10)/2);
		if (this.P==1) g.fillRect(x, y, ((this.getWidth())/10)/2, ((this.getHeight())/10)/2);
		if (this.P==2) P=0;
			
		if (this.keyflag==0){
			try{
				Thread.sleep(7);
				} catch (InterruptedException e) {e.printStackTrace();}
			g.setColor(Color.black);
			g.fillOval(xP1, yP1, 15, 15);
			xP1+=1*xP1Signe;
			yP1+=1*yP1Signe;
			}
		if (this.keyflag==0 && this.nombreP>1){
			g.setColor(Color.black);
			g.fillOval(xP2, yP2, 15, 15);
			this.xP2+=1*this.xP2Signe;
			this.yP2+=1*this.yP2Signe;
			}
		if (this.keyflag==0 && this.nombreP>2){
			g.setColor(Color.black);
			g.fillOval(xP3, yP3, 15, 15);
			this.xP3+=1*this.xP3Signe;
			this.yP3+=1*this.yP3Signe;
			}
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Vie", 20, 280);
		if (this.keyflag==0){
			g.setColor(Color.green);
			g.fillRect(10, 300, 40, this.vieY);
			}
		g.setColor(Color.red);
		g.drawRect(9, 299, 42, 302);
		if (this.keyflag==0 && this.InvAlea==5){
//Losa Invincibilite
			g.setColor(Color.yellow);
//			    	  {20, 40, 60, 40}
//			     	 {40, 20, 40, 60}
			int losaX[] = {(xCoefInv*this.getWidth()/10)+x1Inv, (xCoefInv*this.getWidth()/10)+x2Inv, (xCoefInv*this.getWidth()/10)+x3Inv, (xCoefInv*this.getWidth()/10)+x4Inv};
			int losaY[] = {(yCoefInv*this.getHeight()/10)+y1Inv, (yCoefInv*this.getHeight()/10)+y2Inv, (yCoefInv*this.getHeight()/10)+y3Inv, (yCoefInv*this.getHeight()/10)+y4Inv};
			g.fillPolygon(losaX, losaY, 4);
			if (losaTourInv==0){
				if (x2Inv<60); x1Inv+=1;
				if (x4Inv>20); x3Inv-=1;
				if (y1Inv>20); y2Inv+=1;
				if (y3Inv<60); y4Inv-=1;
				losaTourInvk+=1;
				}
			if (losaTourInvk==40) losaTourInv=1;
			//if (x2Inv==60 && x4Inv==20 && y1Inv==20 && y3Inv==60) losaTourInv=1;
			if (losaTourInv==1){
				if (x2Inv>20); x1Inv-=1;
				if (x4Inv<60); x3Inv+=1;
				if (y1Inv<60); y2Inv-=1;
				if (y3Inv>20); y4Inv+=1;
				losaTourInvk+=1;
				}
			if (losaTourInvk==80){
				losaTourInvk=0;
				losaTourInv=0;
				}
			//if (x2Inv==20 && x4Inv==60 && y1Inv==60 && y3Inv==20) losaTourInv=0;
			}
		
//			      {20, 40, 60, 40}
//			      {120, 100, 120, 140}
		g.setColor(Color.white);
		g.fillOval(20, 100, 40, 40);
		g.setColor(Color.magenta);
		int losaDP1x[] = {x1DP1, x2DP1, x3DP1, x4DP1};
		int losaDP1y[] = {y1DP1, y2DP1, y3DP1, y4DP1};
		g.fillPolygon(losaDP1x, losaDP1y, 4);
		if (this.keyflag==0 && nombreP>3){
//----------------------------------Losa Violet TêteChercheuse
			try{
				Thread.sleep(5);
				} catch (InterruptedException e) {e.printStackTrace();}
			if (losaTourDP1==0){
				if (x1DP1<40); x1DP1+=1;
				if (x2DP1<60); x2DP1+=1;
				if (x3DP1>40); x3DP1-=1;
				if (x4DP1>20); x4DP1-=1;
				if (y1DP1>100); y1DP1-=1;
				if (y2DP1<120); y2DP1+=1;
				if (y3DP1<140); y3DP1+=1;
				if (y4DP1>120); y4DP1-=1;
				losaTourDP1k+=1;
				}
			if (losaTourDP1k==20) losaTourDP1=1;
			//if (x4DP==20 && x1DP==40 && x2DP==60 && x3DP==40 && y4DP==120 && y1DP==100 && y2DP==120 && y3DP==140) losaTourDP=1;
			if (losaTourDP1==1){
				if (x4DP1<40); x4DP1+=1;
				if (x1DP1<60); x1DP1+=1;
				if (x2DP1>40); x2DP1-=1;
				if (x3DP1>20); x3DP1-=1;
				if (y4DP1>100); y4DP1-=1;
				if (y1DP1<120); y1DP1+=1;
				if (y2DP1<140); y2DP1+=1;
				if (y3DP1>120); y3DP1-=1;
				losaTourDP1k+=1;
				}
			if (losaTourDP1k==40) losaTourDP1=2;
			//if (x3DP==20 && x4DP==40 && x1DP==60 && x2DP==40 && y3DP==120 && y4DP==100 && y1DP==120 && y2DP==140) losaTourDP=2;
			if (losaTourDP1==2){
				if (x3DP1<40); x3DP1+=1;
				if (x4DP1<60); x4DP1+=1;
				if (x1DP1>40); x1DP1-=1;
				if (x2DP1>20); x2DP1-=1;
				if (y3DP1>100); y3DP1-=1;
				if (y4DP1<120); y4DP1+=1;
				if (y1DP1<140); y1DP1+=1;
				if (y2DP1>120); y2DP1-=1;
				losaTourDP1k+=1;
				}
			if (losaTourDP1k==60) losaTourDP1=3;
			//if (x2DP==20 && x3DP==40 && x4DP==60 && x1DP==40 && y2DP==120 && y3DP==100 && y4DP==120 && y1DP==140) losaTourDP=3;
			if (losaTourDP1==3){
				if (x2DP1<40); x2DP1+=1;
				if (x3DP1<60); x3DP1+=1;
				if (x4DP1>40); x4DP1-=1;
				if (x1DP1>20); x1DP1-=1;
				if (y2DP1>100); y2DP1-=1;
				if (y3DP1<120); y3DP1+=1;
				if (y4DP1<140); y4DP1+=1;
				if (y1DP1>120); y1DP1-=1;
				losaTourDP1k+=1;
				}
			if (losaTourDP1k==80){
				losaTourDP1k=0;
				losaTourDP1=0;
				}
			if (x1DP1<this.x+20 && y1DP1<this.y+20){
				x1DP1+=1;
				x2DP1+=1;
				x3DP1+=1;
				x4DP1+=1;
				y1DP1+=1;
				y2DP1+=1;
				y3DP1+=1;
				y4DP1+=1;
				}
			if (x1DP1>this.x+20 && y1DP1<this.y+20){
				x1DP1-=1;
				x2DP1-=1;
				x3DP1-=1;
				x4DP1-=1;
				y1DP1+=1;
				y2DP1+=1;
				y3DP1+=1;
				y4DP1+=1;
				}
			if (x1DP1>this.x+20 && y1DP1>this.y+20){
				x1DP1-=1;
				x2DP1-=1;
				x3DP1-=1;
				x4DP1-=1;
				y1DP1-=1;
				y2DP1-=1;
				y3DP1-=1;
				y4DP1-=1;
				}
			if (x1DP1<this.x+20 && y1DP1>this.y+20){
				x1DP1+=1;
				x2DP1+=1;
				x3DP1+=1;
				x4DP1+=1;
				y1DP1-=1;
				y2DP1-=1;
				y3DP1-=1;
				y4DP1-=1;
				}
			}

		//System.out.println(x1Inv+"\t"+x2Inv+"\t"+x3Inv+"\t"+x4Inv+"\t"+y1Inv+"\t"+y2Inv+"\t"+y3Inv+"\t"+y4Inv);
		//System.out.println(x1DP1+"\t"+x2DP1+"\t"+x3DP1+"\t"+x4DP1+"\t"+y1DP1+"\t"+y2DP1+"\t"+y3DP1+"\t"+y4DP1);

		}
	
	public static int abs(int a){
       		return (a < 0) ? -a : a;
    		}
	public boolean FinR(){
		int fx=0; 
		int fy=0;
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){ 
			if (((xF*this.getWidth()/10)+30)<i && i<(xF*this.getWidth()/10)+50){ 
				fx=1; 
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){ 
			if (((yF*this.getHeight()/10)+10)<i && i<(yF*this.getHeight()/10)+70){ 
				fy=2; 
				break;
				}
			}
		return fx==1 && fy==2 && this.P==1 && this.C==this.RFinC;
		}
	public boolean FinO(){
		int fx=0; 
		int fy=0;
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){ 
			if (((OxF*this.getWidth()/10)+30)<i && i<(OxF*this.getWidth()/10)+50){ 
				fx=1; 
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){ 
			if (((OyF*this.getHeight()/10)+10)<i && i<(OyF*this.getHeight()/10)+70){ 
				fy=2; 
				break;
				}
			}
		return fx==1 && fy==2 && this.P==0 && this.C==this.OFinC;
		}
	public boolean FinRnoC(){
		int fx=0; 
		int fy=0;
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){ 
			if (((xF*this.getWidth()/10)+30)<i && i<(xF*this.getWidth()/10)+50){ 
				fx=1; 
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){ 
			if (((yF*this.getHeight()/10)+10)<i && i<(yF*this.getHeight()/10)+70){ 
				fy=2; 
				break;
				}
			}
		return fx==1 && fy==2 && this.C!=this.RFinC;
		}
	public boolean FinOnoC(){
		int fx=0; 
		int fy=0;
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){ 
			if (((OxF*this.getWidth()/10)+30)<i && i<(OxF*this.getWidth()/10)+50){ 
				fx=1; 
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){ 
			if (((OyF*this.getHeight()/10)+10)<i && i<(OyF*this.getHeight()/10)+70){ 
				fy=2; 
				break;
				}
			}
		return fx==1 && fy==2 && this.C!=this.OFinC;
		}
	public boolean FinOnoF(){
		int fx=0; 
		int fy=0;
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){ 
			if (((OxF*this.getWidth()/10)+30)<i && i<(OxF*this.getWidth()/10)+50){ 
				fx=1; 
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){ 
			if (((OyF*this.getHeight()/10)+10)<i && i<(OyF*this.getHeight()/10)+70){ 
				fy=2; 
				break;
				}
			}
		return fx==1 && fy==2 && this.P==1;
		}
	public boolean FinRnoF(){
		int fx=0; 
		int fy=0;
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){ 
			if (((xF*this.getWidth()/10)+30)<i && i<(xF*this.getWidth()/10)+50){ 
				fx=1; 
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){ 
			if (((yF*this.getHeight()/10)+10)<i && i<(yF*this.getHeight()/10)+70){ 
				fy=2; 
				break;
				}
			}
		return fx==1 && fy==2 && this.P==0;
		}
	
	public boolean ReduceLife(){
		int fx1=0; 
		int fy1=0;
		int fx2=0; 
		int fy2=0;
		int fx3=0; 
		int fy3=0;
		int fxDP=0;
		int fyDP=0;
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){ 
			if (i==this.x1DP1 || i==this.x2DP1 || i==this.x3DP1 || i==this.x4DP1){
				fxDP=1;
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){ 
			if (i==this.y1DP1 || i==this.y2DP1 || i==this.y3DP1 || i==this.y4DP1){
				fyDP=1;
				break;
				}
			}
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){ 
			if (this.xP1<i && i<this.xP1+10){
				fx1=1;
				break;
				}
			if (this.xP2<i && i<this.xP2+10){
				fx2=1;
				break;
				}
			if (this.xP3<i && i<this.xP3+10){
				fx3=1;
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){ 
			if (this.yP1<i && i<this.yP1+10){
				fy1=2;
				break;
				}
			if (this.yP2<i && i<this.yP2+10){
				fy2=2;
				break;
				}
			if (this.yP3<i && i<this.yP3+10){
				fy3=2;
				break;
				}
			}
		return (fx1==1 && fy1==2) || (fx2==1 && fy2==2) || (fx3==1 && fy3==2) || (fxDP==1 && fyDP==1);
		}
	public boolean Invincible(){
		int Ix=0;
		int Iy=0;
		for(int i=this.x; i<=this.x+((this.getWidth())/10)/2; i++){
			if (i==(xCoefInv*this.getWidth()/10)+this.x1Inv || i==(xCoefInv*this.getWidth()/10)+this.x2Inv || i==(xCoefInv*this.getWidth()/10)+this.x3Inv || i==(xCoefInv*this.getWidth()/10)+this.x4Inv){
				Ix=1;
				break;
				}
			}
		for(int i=this.y; i<=this.y+((this.getHeight())/10)/2; i++){
			if (i==(yCoefInv*this.getHeight()/10)+this.y1Inv || i==(yCoefInv*this.getHeight()/10)+this.y2Inv || i==(yCoefInv*this.getHeight()/10)+this.y3Inv || i==(yCoefInv*this.getHeight()/10)+this.y4Inv){
				Iy=1;
				break;
				}
			}
		return Ix==1 && Iy==1;
		}
	public void DebordeP(int X, int Y, int signeX, int signeY){
		if (X<(this.getWidth()/10)+6){
			signeX = signeX*(-1);
			}
		if ((X+15)>(9*this.getWidth()/10)-6){
			signeX = signeX*(-1);
			}
		if (Y<(this.getHeight()/10)+6){
			signeY = signeY*(-1);
			}
		if ((Y+15)>(9*this.getHeight()/10)-6){
			signeY = signeY*(-1);
			}
		}
	}
class ToucheListener implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		//System.out.println(Pan.keyflag);
		if (Pan.keyflag==0){
			if (c==0x25) Pan.x-=79;
			if (c==0x27) Pan.x+=79;
			if (c==0x28) Pan.y+=79;
			if (c==0x26) Pan.y-=79;
			}
		if (c==0x43) Pan.C+=1;
		if (c==0x46) Pan.P+=1;
		}
	public void keyReleased(KeyEvent event){}
	}
class SourisUpDifficult implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1){
			Pan.upDifficultOn=1;
			Pan.nombreP+=1;
			Pan.upDifficult.setVisible(false);
			if (Pan.nombreP==2){
				Pan.xP2=200+(int)(Math.random()*401);
				Pan.yP2=200+(int)(Math.random()*401);
				}
			if (Pan.nombreP==3){
				Pan.xP3=200+(int)(Math.random()*401);
				Pan.yP3=200+(int)(Math.random()*401);
				}
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisDiffNormal implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1) Pan.difficult=1;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisDiffDeadfull implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1) Pan.difficult=2;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisCompris implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1){
			Pan.Expli=1;
			Pan.compris.setVisible(false);
			Pan.ToucheExpli.setVisible(false);
			Pan.SourisExpli.setVisible(false);
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisDebutTouche implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1) Pan.gameplay=1;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisDebutSouris implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1) Pan.gameplay=2;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisStop implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1){
			Pan.Stop=1;
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisButtonAgain implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int rx = 0+(int)(Math.random()*2);
		int ry = 0+(int)(Math.random()*2);
		if (c==1){
			Pan.xP1=200+(int)(Math.random()*401);
			Pan.yP1=200+(int)(Math.random()*401);
			if (Pan.nombreP>1){
				Pan.xP2=200+(int)(Math.random()*401);
				Pan.yP2=200+(int)(Math.random()*401);
				}
			if (Pan.nombreP>2){
				Pan.xP3=200+(int)(Math.random()*401);
				Pan.yP3=200+(int)(Math.random()*401);
				}
			Pan.vieY=300;
			Pan.xF=1 + (int)(Math.random()*8);
			Pan.yF=1 + (int)(Math.random()*8);
			Pan.OxF=1 + (int)(Math.random()*8);
			Pan.OyF=1 + (int)(Math.random()*8);
			Pan.xCoefInv= 2+(int)(Math.random()*5);
	                Pan.yCoefInv= 2+(int)(Math.random()*5);
			Pan.InvAlea=1+(int)(Math.random()*10);
			Pan.nombreWinOn=0;
			Pan.nombreLostOn=0;
			Pan.Invincible=0;
			Pan.keyflag=0;
			Pan.x=100+(8*80*rx);
			Pan.y=100+(8*80*ry);
			Pan.again.setVisible(false);
			Pan.win.setVisible(false);
			Pan.lost.setVisible(false);
			Pan.stop.setVisible(false);
			Pan.C=0;
			Pan.P=0;
			Pan.OFinC=0+(int)(Math.random()*6);
			Pan.RFinC=0+(int)(Math.random()*6);
			Pan.upDifficult.setVisible(false);
			Pan.upDifficultOn=0;
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}

class SourisColor implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int Sx = event.getX();
		int Sy = event.getY();
		if (c==1 && Pan.x<Sx && Sx<Pan.x+Pan.larg/20 && Pan.y<Sy && Sy<Pan.y+Pan.haut/20) Pan.C+=1;
		if (c==3 && Pan.x<Sx && Sx<Pan.x+Pan.larg/20 && Pan.y<Sy && Sy<Pan.y+Pan.haut/20) Pan.P+=1;
		if (c==2 && Pan.keyflag==0) Pan.keyflag=1;
		//if (c==2 && Pan.keyflag==1) Pan.keyflag=0;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}

class SourisMoveListener implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){
		if (Pan.keyflag==0){
			int Sx = event.getX();
			int Sy = event.getY();
			Pan.x = Sx-20;
			Pan.y = Sy-20;
			}
		}
	public void mouseDragged(MouseEvent event){}
	}
	

public class Jeu{
	public static void main(String[] args){
		Fen fen = new Fen("Fenetre", 817, 840);
		fen.setLocationRelativeTo(null);
		Pan pan = new Pan(800, 800);
		fen.setContentPane(pan);
		while (pan.gameplay==0){
			System.out.print("\b");
			}
		pan.JeuTouche.setVisible(false);
		pan.JeuSouris.setVisible(false);
		pan.ChoixDifficult.setVisible(true);
		pan.normal.setVisible(true);
		pan.mortel.setVisible(true);
		while (pan.difficult==0){
			System.out.print("\b");
			}
		pan.ChoixDifficult.setVisible(false);
		pan.normal.setVisible(false);
		pan.mortel.setVisible(false);
		if (pan.gameplay==1){
			while (pan.Expli==0){
				pan.ToucheExpli.setVisible(true);
				pan.compris.setVisible(true);
				}
			fen.addKeyListener(fen.touche);
			}
		if (pan.gameplay==2){
			while (pan.Expli==0){
				pan.SourisExpli.setVisible(true);
				pan.compris.setVisible(true);
				}
			pan.addMouseMotionListener(pan.sourisMove);
			pan.addMouseListener(pan.sourisC);
			pan.gris.setVisible(false);
			}
		while (true){
			pan.repaint();
			//pan.DebordeP(pan.xP1, pan.yP1, pan.xP1Signe, pan.yP1Signe);
			//if (pan.nombreP>1) pan.DebordeP(pan.xP2, pan.yP2, pan.xP2Signe, pan.yP2Signe);
			//if (pan.nombreP>2) pan.DebordeP(pan.xP3, pan.yP3, pan.xP3Signe, pan.yP3Signe);

			if (((pan.xF*pan.getWidth()/10)+30)==(pan.OxF*pan.getWidth()/10)+30 && ((pan.yF*pan.getHeight()/10)+10)==(pan.OyF*pan.getHeight()/10)+10){
				pan.xF=1 + (int)(Math.random()*8);
				pan.yF=1 + (int)(Math.random()*8);
				}
			
			if (pan.xP1<(pan.getWidth()/10)+6){
				pan.xP1Signe = pan.xP1Signe*(-1);
				}
			if (pan.xP1+15>(9*pan.getWidth()/10)-6){
				pan.xP1Signe = pan.xP1Signe*(-1);
				}
			if (pan.yP1<(pan.getHeight()/10)+6){
				pan.yP1Signe = pan.yP1Signe*(-1);
				}
			if (pan.yP1+15>(9*pan.getHeight()/10)-6){
				pan.yP1Signe = pan.yP1Signe*(-1);
				}

			if (pan.xP2<(pan.getWidth()/10)+6){
				pan.xP2Signe = pan.xP2Signe*(-1);
				}
			if (pan.xP2+15>(9*pan.getWidth()/10)-6){
				pan.xP2Signe = pan.xP2Signe*(-1);
				}
			if (pan.yP2<(pan.getHeight()/10)+6){
				pan.yP2Signe = pan.yP2Signe*(-1);
				}
			if (pan.yP2+15>(9*pan.getHeight()/10)-6){
				pan.yP2Signe = pan.yP2Signe*(-1);
				}

			if (pan.xP3<(pan.getWidth()/10)+6){
				pan.xP3Signe = pan.xP3Signe*(-1);
				}
			if (pan.xP3+15>(9*pan.getWidth()/10)-6){
				pan.xP3Signe = pan.xP3Signe*(-1);
				}
			if (pan.yP3<(pan.getHeight()/10)+6){
				pan.yP3Signe = pan.yP3Signe*(-1);
				}
			if (pan.yP3+15>(9*pan.getHeight()/10)-6){
				pan.yP3Signe = pan.yP3Signe*(-1);
				}

			if (pan.x<pan.getWidth()/10){
				pan.x+=79;
				}
			if (pan.x>9*pan.getWidth()/10){
				pan.x-=79;
				}
			if (pan.y<pan.getHeight()/10){
				pan.y+=79;
				}
			if (pan.y>9*pan.getHeight()/10){
				pan.y-=79;
				}
			if (pan.FinRnoC()) pan.RFinNoC.setVisible(true);
			else pan.RFinNoC.setVisible(false);
			if (pan.FinOnoC()) pan.OFinNoC.setVisible(true);
			else pan.OFinNoC.setVisible(false);
			if (pan.FinOnoF()) pan.OFinNoF.setVisible(true);
			else pan.OFinNoF.setVisible(false);
			if (pan.FinRnoF()) pan.RFinNoF.setVisible(true);
			else pan.RFinNoF.setVisible(false);

			if (pan.Invincible() && pan.InvAlea==5){
				pan.InvAlea=0;
				pan.Invincible=1;
				}
				
			if (pan.FinR() || pan.FinO()){ 
				pan.keyflag=1;
				if (pan.nombreWinOn==0) pan.nombreWin+=1;
				pan.nombreWinOn=1;
				pan.Invincible=0;
				pan.win.setVisible(true);
				pan.again.setVisible(true);
				pan.stop.setVisible(true);
				if (pan.upDifficultOn==0 && pan.nombreP<4) pan.upDifficult.setVisible(true);
				else pan.upDifficult.setVisible(false);
				} 
			if (pan.difficult==2 & pan.ReduceLife() && pan.Invincible==0){
				pan.vieY=0;
				}
			if (pan.difficult==1 & pan.ReduceLife() && pan.Invincible==0){
				try{
					Thread.sleep(5);
					} catch (InterruptedException e) {e.printStackTrace();}
				pan.vieY-=1;
				}
			if (pan.vieY==0){
				pan.keyflag=1;
				if (pan.nombreLostOn==0) pan.nombreLost+=1;
				pan.nombreLostOn=1;
				pan.lost.setVisible(true);
				pan.again.setVisible(true);
				pan.stop.setVisible(true);
				//System.out.print("\b");
				}
			//System.out.println(pan.Case+"\t"+pan.x+"\t"+pan.y);
			//System.out.println((pan.getWidth()/10)+"\t"+9*(pan.getWidth()/10)+"\t"+(pan.getHeight()/10)+"\t"+9*(pan.getHeight()/10));
			if (pan.Stop==1) break;
			}
		//System.out.println(pan.Case+"\t"+pan.x+"\t"+pan.y);
		//System.out.println((pan.getWidth()/10)+"\t"+9*(pan.getWidth()/10)+"\t"+(pan.getHeight()/10)+"\t"+9*(pan.getHeight()/10));
		pan.win.setVisible(false);
		pan.upDifficult.setVisible(false);
		pan.lost.setVisible(false);
		pan.again.setVisible(false);
		pan.stop.setVisible(false);
		pan.Results.setText("Resultats = \nCases parcourues = "+pan.Case+"\nNombre de victoires = "+pan.nombreWin+"\nNombre de defaites = "+pan.nombreLost);
		pan.Results.setVisible(true);
		}
}
	