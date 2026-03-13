import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

//ONWORK pour retrouver la zone de code en cours
//BALISE pour 1 problème particulier

//#Perso 1 = Anvar
//#Perso 2 = Braxus
//#Perso 3 = Rablaix
//#Perso 4 = Sallazar

//#DialogKind
// 1 persos principaux scénario
// 2 PNJ, perso secondaire
// 3 narration
// 4 is monster
// 5 is Zorca

//Rajouter de l'usure aux armes ? avec barre d'usure ?
// Variable d'usure, augmente de 1 a chaque utilisation
// Variable "(NomArme)broken"=1 si cassé, et nombre de l'arme -1


//Pendant Combat, choix Assaut ou choix Fuir
// Si Assaut, Choix de l'arme ou du sortilège

//Faire l'animation des sortilèges Boude de Feu et Foudre.
//Boule de Feu = orde rouge qui se déplace vers le centre de l'ennemi visé et crée des flammes sur lui
//Foudre = écran blanc subliminale puis éclair (dessiné sur paint ?) qui frappe le centre de l'ennemi visé

//Créer un flag avec la récupération d'un objet qui permet d'utiliser tous les sorttilèges
//avec 1 point de Magie ???

////***CHANGER LE GAMEPLAY DES COMBATS ? Pas de dé, chaque arme a une valeur d'attaque qui
////***s'ajoute à l'HABILETE et donne la FdA qui s'inflige aux HP de l'ennemi (qui doit
////***être bien plus considérable alors). Chaque armure a une valeur de défense qui se
////***soustrait à la FdA de chaque adversaire.
////***

//Trois modes de difficultés : Facile, Basique, Difficile
// 1 Facile : totaux de départ au maximum possible + plus grande probas de dés 
//	à valeur fortes, + attaques plus fortes, Pièces d'Or plus nombreuses au départ
// 2 Basique : jeu basique
// 2 Difficile : totaux de départ au minimum + plus grandes probas dés à valeur faibles
//Espace pour dialogName, 1 charactère = 2 espaces pour Jtext
class Fen extends JFrame{
	static int Perso=0;
	static int Flag=0;
	static int StartGame=0;
	static int MenuStatOn=0;
	static int Difficulty=0;
	static String ZoneName="";
	static String Question="";
	static int TenterChanceOn=0;
	static int TesterHabileteOn=0;
	static int TesterHabileteLocalisationOn=0;
	static int TenterChanceResult=0;
	static int TesterHabileteResult=0;
	static int TesterHabileteLocalisationResult=0;

	static int Habilete=0;
	static int Endurance=0;
	static int Chance=0;
	static int Magie=0;
	static int HabileteTotalDepart=0;
	static int EnduranceTotalDepart=0;
	static int EnduranceTotalDepartBis=0;
	static int ChanceTotalDepart=0;
	static int MagieTotalDepart=0;
	static int [] Equipement = new int [50];
	static int [] Weapon = new int [50];
	static int [] Armor = new int [50];
	static int [] Object = new int [50];
	static int [] Effects = new int [50];
	static int Gold=0;
	static int Provisions=0;
	static int TalismanOr=0;
	static int DagueArgent=0;
	static int [] Treasure = new int [50];
	static int [] Grimoire = new int [12];
	static int WeaponEquiped=0;
	static String WeaponEquipedName="";
	static int ArmorEquiped=0;
	static String ArmorEquipedName="";
	static int Magic12Used=0;
	static boolean IsLampFilled=true;
	static boolean IsLightMagicOn=false;
	static int SwiftHandMagicOn=0;
	static int PoisonCountRoom=0;
	static int PoisonOn=0;
	static int PreviousFlag=0; //Poison


	static int Dice=0;
	static int Dice2=0;
	static int Dice3=0;
	static int DiceThrow1=0;
	static int DiceThrow2=0;
	static int DiceThrow3=0;
	static int DiceOn=0;
	static int DiceTour=0;

	static int dialog=37;
	static int dialogOn=0;
	static int dialogChangedOn=0;
	static int dialogDefilement=0;
	static int dialogLength=0;
	static int dialogRecord=0;

	static int KeywordOn=0;
	static String Keyword="";

	static int ChoiceType2On=0;
	static int ChoiceType3On=0;
	static int ChoiceType4On=1;
	static int ChoiceType5On=0;
	static int Choice=0;
	static int ChoiceSet=2; //1=affichage drawString ; 2=affichage JTextArea
	static int ChoiceRecord=0;
	static int MenuBlockedOn=0;

	static int SelectedObject=0;
	static int SelectedObjectOn=0;
	static int BuyingX=0;
	static int BuyingY=0;
	static int Buying=0;

	static int FightOn=0;
	static int FightEnded=0;
	

	static int info=0;
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);

		JButton gris = new JButton();
		Touche touche = new Touche();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.addKeyListener(touche);

		SourisPan souris = new SourisPan();
		SourisMotionPan sourisM = new SourisMotionPan();
		this.addMouseListener(souris);
		this.addMouseMotionListener(sourisM);


		}
	public void paintComponent(Graphics g){
		//g.setColor(Color.black);
		//g.fillRect(0, 800, this.getWidth(), 200);
		}
//     **********************************************************************
	public static void DiceThrow(int D){
		if (D==1 && DiceThrow1==0){
			DiceThrow1=1;
			//Dice=Dice2=Dice3=0;
			}
		if (D==2 && DiceThrow2==0){
			DiceThrow2=1;
			//Dice=Dice2=Dice3=0;
			}
		if (D==3 && DiceThrow3==0){
			DiceThrow3=1;
			//Dice=Dice2=Dice3=0;
			}
		}
	public static void TenterChance(){
		if (TenterChanceOn==0) TenterChanceOn=1;
		if (TenterChanceOn==3){
			DiceThrow(2);
			TenterChanceOn=4;
			}
		if (DiceThrow2==3){
			if (Dice+Dice2<=Chance){
				String t = "Vous etes Chanceux !";
				//DisplayText(t,3);
				PanTab.Jtext.setText(t);
				TenterChanceResult=1;
				}
			else{
				String t = "Vous etes Malchanceux !";
				//DisplayText(t,3);
				PanTab.Jtext.setText(t);
				TenterChanceResult=2;
				}
			DiceThrow2=0;
			TenterChanceOn=5;
			if (Chance>0) Chance-=1;
			}		
		}
	public static void TenterChanceEnd(){
		DiceThrow2=TenterChanceResult=TenterChanceOn=0;
		}
//--------------------------------------------------------------------------
	public static void TesterHabilete(){
		TesterHabilete(0);
		}
	public static void TesterHabilete(int Ajout){
		if (TesterHabileteOn==0) TesterHabileteOn=1;
		if (TesterHabileteOn==3){
			DiceThrow(2);
			TesterHabileteOn=4;
			}
		if (DiceThrow2==3){
			if (Dice+Dice2+Ajout<=Fen.Habilete){
				String t = "Succes !";
				PanTab.Jtext.setText(t);
				TesterHabileteResult=1;
				}
			else{
				String t = "Echec !";
				PanTab.Jtext.setText(t);
				TesterHabileteResult=2;
				}
			//if (Dice+Dice2+Ajout<=Fen.Habilete) TesterHabileteResult=1;
			//else TesterHabileteResult=2;
			DiceThrow2=0;
			TesterHabileteOn=5;
			}		
		}
	public static void TesterHabileteEnd(){
		DiceThrow2=TesterHabileteResult=TesterHabileteOn=0;
		}
//--------------------------------------------------------------------------
	public static void TesterHabileteLocalisation(){
		TesterHabileteLocalisation(0);
		}
	public static void TesterHabileteLocalisation(int Ajout){
		if (TesterHabileteLocalisationOn==0) TesterHabileteLocalisationOn=1;
		if (TesterHabileteLocalisationOn==3){
			DiceThrow(2);
			TesterHabileteLocalisationOn=4;
			}
		if (DiceThrow2==3){
			if (Fen.Perso==4){
				if (Dice+Dice2+Ajout<=Fen.Habilete+2){
					String t = "Succes !";
					PanTab.Jtext.setText(t);
					TesterHabileteLocalisationResult=1;
					}
				else{
					String t = "Echec !";
					PanTab.Jtext.setText(t);
					TesterHabileteLocalisationResult=2;
					}
				//if (Dice+Dice2+Ajout<=Fen.Habilete+2) TesterHabileteLocalisationResult=1;
				//else TesterHabileteLocalisationResult=2;
				}
			else{
				if (Dice+Dice2+Ajout<=Fen.Habilete){
					String t = "Succes !";
					PanTab.Jtext.setText(t);
					TesterHabileteLocalisationResult=1;
					}
				else{
					String t = "Echec !";
					PanTab.Jtext.setText(t);
					TesterHabileteLocalisationResult=2;
					}
				//if (Dice+Dice2+Ajout<=Fen.Habilete) TesterHabileteLocalisationResult=1;
				//else TesterHabileteLocalisationResult=2;
				}
			DiceThrow2=0;
			TesterHabileteLocalisationOn=5;
			}		
		}
	public static void TesterHabileteLocalisationEnd(){
		DiceThrow2=TesterHabileteResult=TesterHabileteOn=0;
		}
//--------------------------------------------------------------------------
	public static void ChoiceReset(){
		Choice=0;
		ChoiceType2On=PanTab.ChoiceType2Color=0;
		ChoiceType3On=PanTab.ChoiceType3Color=0;
		ChoiceType4On=PanTab.ChoiceType4Color=0;
		ChoiceType5On=PanTab.ChoiceType5Color=0;
		PanTab.Text1=PanTab.Text2=PanTab.Text3=PanTab.Text4=PanTab.Text5="";
		PanTab.Text1bis=PanTab.Text2bis=PanTab.Text3bis=PanTab.Text4bis=PanTab.Text5bis="";
		ZoneName="";
		Question="";
		SelectedObject=0;
		SelectedObjectOn=0;
		Buying=0;
		BuyingX=BuyingY=0;
		PanTab.fontplainOn=0;
		PreviousFlag=Flag;

		PanTab.JText1.setVisible(false);
		PanTab.JText2.setVisible(false);
		PanTab.JText3.setVisible(false);
		PanTab.JText4.setVisible(false);
		PanTab.JText5.setVisible(false);

		if (Effects[3]==1){
			PoisonCountRoom+=1;
			if (PoisonCountRoom>=20){ //A modifier si possible
				Endurance-=2;
				EnduranceTotalDepart-=2;
				}
			}
		else if (Effects[3]==0) PoisonCountRoom=0;
		
		}
	public static String [] defileText(String T){
		int S=0;
		for(int i=0; i<T.length(); i++){
			if (T.charAt(i)==' ' || i==T.length()-1) S+=1;
			}
		//Fen.dialogLength=S;
		//System.out.println(S);
		String [] dT = new String[S];
		String mot = "";
		S=0;
		for(int i=0; i<T.length(); i++){
			if (T.charAt(i)!=' ' && i!=T.length()-1) mot+=T.charAt(i);
			else{
				dT[S]=mot+" ";
				S+=1;
				mot="";
				}
			}
		return dT;
		}
	public static void DisplayText(String t){
		String T="";
		dialogLength=t.length();
		//for(int i=0; i<dialogDefilement; i++) T+=defileText(t)[i];
		//PanTab.Jtext.setText(T);
		//if (dialogDefilement<defileText(t).length) dialogDefilement+=1;

		for(int i=0; i<dialogDefilement; i++) T+=t.charAt(i);;
		PanTab.Jtext.setText(T);
		if (dialogDefilement<t.length()) dialogDefilement+=1;
		}
	public static void DisplayText(String t, int F){
		String T="";
		dialogLength=t.length();
		switch(F){
			case 1:PanTab.Jtext.setFont(PanTab.fontplain);break;
			case 2:PanTab.Jtext.setFont(PanTab.fontbold);break;
			case 3:PanTab.Jtext.setFont(PanTab.fontitalic);break;
			case 4:PanTab.Jtext.setFont(PanTab.fontbolditalic);break;
			}
		for(int i=0; i<dialogDefilement; i++) T+=t.charAt(i);;
		PanTab.Jtext.setText(T);
		if (dialogDefilement<t.length()) dialogDefilement+=1;
		}
//#DialogKind
// 1 persos principaux scénario
// 2 PNJ, perso secondaire
// 3 narration
// 4 is monster
// 5 is Zorca
	public static void DisplayText(String t, int F, String Name, int NameColor){
		String T="";
		dialogLength=t.length();
		switch(F){
			case 1:PanTab.Jtext.setFont(PanTab.fontplain);break;
			case 2:PanTab.Jtext.setFont(PanTab.fontbold);break;
			case 3:PanTab.Jtext.setFont(PanTab.fontitalic);break;
			case 4:PanTab.Jtext.setFont(PanTab.fontbolditalic);break;
			}
		PanTab.dialogNameKind=NameColor;
		for(int i=0; i<dialogDefilement; i++) T+=t.charAt(i);;
		PanTab.Jtext.setText(T);
		if (dialogDefilement<t.length()) dialogDefilement+=1;
		String Espace="";
		for(int i=0; i<Name.length(); i++) Espace+="  ";
		PanTab.Jtext.insert(Espace,0);
		PanTab.dialogName=Name;
		}
	public static void DisplayText(String t, int F, String Name, int NameColor, int S){
		String T="";
		dialogLength=t.length();
		switch(F){
			case 1:PanTab.Jtext.setFont(PanTab.fontplain);break;
			case 2:PanTab.Jtext.setFont(PanTab.fontbold);break;
			case 3:PanTab.Jtext.setFont(PanTab.fontitalic);break;
			case 4:PanTab.Jtext.setFont(PanTab.fontbolditalic);break;
			}
		PanTab.dialogNameKind=NameColor;
		for(int i=0; i<dialogDefilement; i++) T+=t.charAt(i);;
		PanTab.Jtext.setText(T);
		if (dialogDefilement<t.length()) dialogDefilement+=1;
		String Espace="";
		for(int i=0; i<S; i++) Espace+="  ";
		PanTab.Jtext.insert(Espace,0);
		PanTab.dialogName=Name;
		}		
	public static void dialogEnd(){
		dialog=-1;
		dialogOn=0;
		dialogChangedOn=0;
		dialogLength=0;
		dialogDefilement=0;
		}
	public static void dialogStart(){
		if (dialogOn==0 && NoChoiceOn()){
			dialog=0;
			if (dialogRecord!=0){
				dialog=dialogRecord;
				dialogRecord=0;
				}
			dialogOn=1;
			dialogChangedOn=0;
			dialogLength=0;
			dialogDefilement=0;
			PanTab.Jtext.setText("");
			}
		}
	public static boolean NoChoiceOn(){
		if (ChoiceType2On==1) return false;
		if (ChoiceType3On==1) return false;
		if (ChoiceType4On==1) return false;
		if (ChoiceType5On==1) return false;
		return true;
		}
	public static void FightStart(){
		if (Fen.FightOn==0) Fen.FightOn=1;
		PanFight.FightPhase=0;
		Fen.FightEnded=0;
		}
	public static void FightEnd(){
		Fen.FightOn=0;
		Fen.FightEnded=1;
		}

	public static void Question(String t){
		Question=t;
		ChoiceType2On=1;
		PanTab.Text1="OUI";
		PanTab.Text2="NON";
		}
	public static void TakeMeal(){
		if (Provisions>0) Provisions-=1;
		else Endurance-=2;
		}
	public static void ChoiceRecord(){
		if (ChoiceType2On==1) ChoiceRecord=2;
		else if (ChoiceType3On==1) ChoiceRecord=3;
		else if (ChoiceType4On==1) ChoiceRecord=4;
		else if (ChoiceType5On==1) ChoiceRecord=5;
		}
	public static void AddTreasure(int ID){
		//for(int i=0; i<Treasure.length; i++){
		for(int i=0; i<21; i++){
			if (Treasure[i]==-1){
				Treasure[i]=ID;
				break;
				}
			}
		}
	public static void FightStart(int Fightmode, int Number, int EK1, int EK2, int EK3, int EK4, int EK5, boolean Flee){
		FightOn=1;
		//FightMode=Fightmode;
		PanFight.EnemyNumber=PanFight.EnemyAliveNumber=Number;
		//PanFight.Enemy[0]
		}
		
//-------------------------------------------------------------------------------------
	public static void FlagChange(){
		Scanner sc = new Scanner(System.in);
		int F = -1;
		while (F<1){
			System.out.print("Flag ? = ");
			F = sc.nextInt();
			}
		Flag=F;
		ChoiceReset();
		dialogEnd();
		}


	public static void Afficher(int [] T){
		for(int i=0; i<T.length; i++) System.out.println(i+":"+T[i]);
		}
	public static int Distance(int x1, int y1, int x2, int y2){
		return (int)(Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)));
		}
	public static int YSlideLine(int X, int X1, int Y1, int X2, int Y2){
		int a = Y2-Y1;
		int b = -(X2-X1);
		int c = -a*X1-b*Y1;
		return (int)((-a*X-c)/b);
		}
	public static int [] KeywordDigits(String K){
		int n = K.length();
		int [] Kdigit = new int [n];
		for(int i=0; i<n; i++){
			switch(K.charAt(i)){
				case 'a':Kdigit[i]=1;break;
				case 'b':Kdigit[i]=2;break;
				case 'c':Kdigit[i]=3;break;
				case 'd':Kdigit[i]=4;break;
				case 'e':Kdigit[i]=5;break;
				case 'f':Kdigit[i]=6;break;
				case 'g':Kdigit[i]=7;break;
				case 'h':Kdigit[i]=8;break;
				case 'i':Kdigit[i]=9;break;
				case 'j':Kdigit[i]=10;break;
				case 'k':Kdigit[i]=11;break;
				case 'l':Kdigit[i]=12;break;
				case 'm':Kdigit[i]=13;break;
				case 'n':Kdigit[i]=14;break;
				case 'o':Kdigit[i]=15;break;
				case 'p':Kdigit[i]=16;break;
				case 'q':Kdigit[i]=17;break;
				case 'r':Kdigit[i]=18;break;
				case 's':Kdigit[i]=19;break;
				case 't':Kdigit[i]=20;break;
				case 'u':Kdigit[i]=21;break;
				case 'v':Kdigit[i]=22;break;
				case 'w':Kdigit[i]=23;break;
				case 'x':Kdigit[i]=24;break;
				case 'y':Kdigit[i]=25;break;
				case 'z':Kdigit[i]=26;break;

				case 'A':Kdigit[i]=1;break;
				case 'B':Kdigit[i]=2;break;
				case 'C':Kdigit[i]=3;break;
				case 'D':Kdigit[i]=4;break;
				case 'E':Kdigit[i]=5;break;
				case 'F':Kdigit[i]=6;break;
				case 'G':Kdigit[i]=7;break;
				case 'H':Kdigit[i]=8;break;
				case 'I':Kdigit[i]=9;break;
				case 'J':Kdigit[i]=10;break;
				case 'K':Kdigit[i]=11;break;
				case 'L':Kdigit[i]=12;break;
				case 'M':Kdigit[i]=13;break;
				case 'N':Kdigit[i]=14;break;
				case 'O':Kdigit[i]=15;break;
				case 'P':Kdigit[i]=16;break;
				case 'Q':Kdigit[i]=17;break;
				case 'R':Kdigit[i]=18;break;
				case 'S':Kdigit[i]=19;break;
				case 'T':Kdigit[i]=20;break;
				case 'U':Kdigit[i]=21;break;
				case 'V':Kdigit[i]=22;break;
				case 'W':Kdigit[i]=23;break;
				case 'X':Kdigit[i]=24;break;
				case 'Y':Kdigit[i]=25;break;
				case 'Z':Kdigit[i]=26;break;

				case ' ':Kdigit[i]=0;break;
				}
			}
		return Kdigit;
		}	
	public static boolean TestKeyword(String K){
		int n = K.length();
		if (Keyword.length()!=n) return false;
		int [] Kdigits = KeywordDigits(K);
		int [] KeyD = KeywordDigits(Keyword);
		for(int i=0; i<n; i++){
			if (KeyD[i]!=Kdigits[i]) return false;
			}
		return true;
		}
	}
//*********************************************************************************
class Touche implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		int k = event.getKeyChar();
		
		if (k=='i') Fen.info=1;
		if (k=='I') System.out.println(Fen.MenuStatOn);
		if (k=='c') System.out.println(Fen.Dice);
		if (k=='d') Fen.DiceThrow1=1;
		//if (c==0x1B && Fen.Flag==0 && Fen.dialog>=1) Fen.dialog=37;

		if (c==0x1B && Fen.MenuStatOn==0 && Fen.FightOn==0 && Fen.Flag>=1 && Fen.MenuBlockedOn==0){
			Fen.MenuStatOn=1;
			Fen.dialogOn=1;
			PanTab.Jtext.setFont(PanTab.fontplain);
			Fen.ChoiceRecord();
			Fen.ChoiceReset();
			//Fen.dialogRecord=Fen.dialog;
			}
		else if (c==0x1B && Fen.MenuStatOn==1){
			Fen.MenuStatOn=0;
			PanStat.ObjectSelected=PanStat.WeaponSelected=PanStat.ArmorSelected=PanStat.MagicSelected=-1;
			Fen.dialogEnd();
			Fen.ChoiceReset();
			//Fen.dialog=Fen.dialogRecord;
			switch(Fen.ChoiceRecord){
				case 0:break;
				case 2:Fen.ChoiceType2On=1;break;
				case 3:Fen.ChoiceType3On=1;break;
				case 4:Fen.ChoiceType4On=1;break;
				case 5:Fen.ChoiceType5On=1;break;
				}
			Fen.ChoiceRecord=0;
			}

		//if (k=='t') Fen.TenterChanceOn=1;

		//if (k=='p') 

		if (k=='D' && Fen.Endurance>0) Fen.Endurance-=1;
		if (k=='E' && Fen.Endurance<Fen.EnduranceTotalDepart) Fen.Endurance+=1;
		if (k=='C' && Fen.Chance<Fen.ChanceTotalDepart) Fen.Chance+=1;
		if (k=='U' && Fen.Chance>0) Fen.Chance-=1;
		if (k=='M') Fen.Magie+=1;
		if (k=='P') Fen.Gold+=1;
		if (k=='G'){
			if (Fen.DiceThrow1<=1) Fen.DiceThrow1=2;
			if (Fen.DiceThrow2<=2) Fen.DiceThrow2=3;
			if (Fen.DiceThrow3<=3) Fen.DiceThrow3=4;
			if (Fen.TenterChanceOn!=0 || Fen.TesterHabileteOn!=0 || Fen.TesterHabileteLocalisationOn!=0 || PanFight.FightPhase==3){
				Fen.Dice=Fen.Dice2=Fen.Dice3=0;
				}
			else Fen.Dice=Fen.Dice2=Fen.Dice3=6;
			if (PanFight.FightPhase==1) PanFight.FightPhase=7;
			}
		if (k=='F') Fen.FlagChange();


		}
	public void keyReleased(KeyEvent event){
		int c = event.getKeyCode();
		int k = event.getKeyChar();
		if (k=='i') Fen.info=0;
		}
	}
class SourisXY implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1) System.out.println("X="+X+"\t"+"Y="+Y);
		}
	public void mousePressed(MouseEvent event){
		int c = event.getButton();
		if (c==2) Fen.info=1;
		}
	public void mouseReleased(MouseEvent event){
		int c = event.getButton();
		if (c==2){
			Fen.info=0;
			if (Fen.MenuStatOn==1){
				Fen.MenuStatOn=0;
				Fen.dialogEnd();
				}
			}
		}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PAN
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
class Pan extends JPanel{
	JButton gris = new JButton();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		this.setVisible(true);
		
		//this.setLayout(new BorderLayout());
		//this.setBorder(BorderFactory.createLineBorder(Color.red));

		SourisPan souris = new SourisPan();
		SourisMotionPan sourisM = new SourisMotionPan();
		this.addMouseListener(souris);
		this.addMouseMotionListener(sourisM);

		Touche touche = new Touche();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		//gris.addKeyListener(touche); //#Fait bugger ?
		//this.addKeyListener(touche);
		}
	public void paintComponent(Graphics g){
		if (Fen.FightOn==0) g.setColor(Color.yellow);
		else if (Fen.FightOn==1) g.setColor(Color.red);
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		}
	}
class SourisPan implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1) System.out.println("X="+X+"\t"+"Y="+Y);
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisMotionPan implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){
		int X = event.getX();
		int Y = event.getY();
		}
	public void mouseDragged(MouseEvent event){}
	}

//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PAN End
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************



//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PANTAB
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
class TEXT extends JTextArea{
	public TEXT(){}
	public TEXT(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		this.setEditable(false);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		//this.setVisible(true);
		this.setBackground(Color.black);
		this.setForeground(Color.white);
		}
	public void paintChildren(Graphics g){
		if (Fen.dialogOn==1){
			if (PanTab.dialogName!=""){
				g.setFont(PanTab.fontbold);
				switch(PanTab.dialogNameKind){
// 1 perso scénario
// 2 PNJ, perso secondaire
// 3
// 4 is monster
// 5 is Zorca
					case 1:g.setColor(Color.yellow);break;
					case 2:g.setColor(Color.cyan);break;
					case 3:g.setColor(Color.green);break;
					case 4:g.setColor(Color.red);break;
					case 5:g.setColor(Color.magenta);break;
					}
				g.drawString(PanTab.dialogName+":",5,22);
				}
			}
		}
	}

//ONWORK
class PanTab extends JPanel{
	

	static JTextArea JText1 = new JTextArea();
	static JTextArea JText2 = new JTextArea();
	static JTextArea JText3 = new JTextArea();
	static JTextArea JText4 = new JTextArea();
	static JTextArea JText5 = new JTextArea();
	static JTextArea JText6 = new JTextArea();
	static JTextArea JText7 = new JTextArea();
	static int T1Gap=0;
	static int T2Gap=0;
	static int T3Gap=0;
	static int T4Gap=0;
	static int T5Gap=0;
		static int ChoiceJColor1=0;
		static int ChoiceJColor2=0;
		static int ChoiceJColor3=0;
		static int ChoiceJColor4=0;
		static int ChoiceJColor5=0;


	static String Text1="";
	static String Text1bis="";
	static String Text1tri="";
	static String Text2="";
	static String Text2bis="";
	static String Text2tri="";
	static String Text3="";
	static String Text3bis="";
	static String Text3tri="";
	static String Text4="";
	static String Text4bis="";
	static String Text4tri="";
	static String Text5="";
	static String Text5bis="";
	static String Text5tri="";
	static String Text6="";
	static String Text6bis="";
	static String Text6tri="";
	static String Text7="";
	static String Text7bis="";
	static String Text7tri="";

	static int ChoiceType2Color=0;
	static int ChoiceType3Color=0;
	static int ChoiceType4Color=0;
	static int ChoiceType5Color=0;
	static int ChoiceType6Color=0;
	static int ChoiceType7Color=0;
	static Font fontplain = new Font("Sylfaen", Font.PLAIN, 24);
	static Font fontplain2 = new Font("Sylfaen", Font.PLAIN, 15);
	static int fontplainOn=0; //1=plain, 2=plain2
	static Font fontbold = new Font("Sylfaen", Font.BOLD, 24);
	static Font fontitalic = new Font("Sylfaen", Font.ITALIC, 24);
	static Font fontbolditalic = new Font("Sylfaen", 3, 24);
	static Font fontzonename = new Font("Sylfaen", Font.BOLD, 35);

	static String dialogName="";
	static int dialogNameKind=0;
	
	static TEXT Jtext = new TEXT();
	//static JTextArea Jtext = new JTextArea();
	static JButton gris = new JButton();
	public PanTab(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		


		Jtext.setLineWrap(true);
		Jtext.setWrapStyleWord(true);
		Jtext.setBackground(Color.black);
		Jtext.setForeground(Color.white);
		Jtext.setPreferredSize(new Dimension(larg, haut));
		Jtext.setEditable(false);
		//Jtext.setFont(new Font("Times new roman",Font.BOLD,35));
		this.add(Jtext);

		JText1.setLineWrap(true);
		JText1.setWrapStyleWord(true);
		JText1.setForeground(Color.white);
		JText1.setEditable(false);
		JText1.setFont(fontplain);
		JText1.setVisible(false);
		SourisPanTabJtext1 sourisJT1 = new SourisPanTabJtext1();
		JText1.addMouseListener(sourisJT1);
		JText1.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(JText1);
		JText2.setLineWrap(true);
		JText2.setWrapStyleWord(true);
		JText2.setForeground(Color.white);
		JText2.setEditable(false);
		JText2.setFont(fontplain);
		JText2.setVisible(false);
		SourisPanTabJtext2 sourisJT2 = new SourisPanTabJtext2();
		JText2.addMouseListener(sourisJT2);
		JText2.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(JText2);
		JText3.setLineWrap(true);
		JText3.setWrapStyleWord(true);
		JText3.setForeground(Color.white);
		JText3.setEditable(false);
		JText3.setFont(fontplain);
		JText3.setVisible(false);
		SourisPanTabJtext3 sourisJT3 = new SourisPanTabJtext3();
		JText3.addMouseListener(sourisJT3);
		JText3.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(JText3);
		JText4.setLineWrap(true);
		JText4.setWrapStyleWord(true);
		JText4.setForeground(Color.white);
		JText4.setEditable(false);
		JText4.setFont(fontplain);
		JText4.setVisible(false);
		SourisPanTabJtext4 sourisJT4 = new SourisPanTabJtext4();
		JText4.addMouseListener(sourisJT4);
		JText4.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(JText4);
		JText5.setLineWrap(true);
		JText5.setWrapStyleWord(true);
		JText5.setForeground(Color.white);
		JText5.setEditable(false);
		JText5.setFont(fontplain);
		JText5.setVisible(false);
		SourisPanTabJtext5 sourisJT5 = new SourisPanTabJtext5();
		JText5.addMouseListener(sourisJT5);
		JText5.setBorder(BorderFactory.createLineBorder(Color.white));
		this.add(JText5);



		

		SourisPanTab sourisTab = new SourisPanTab();
		SourisMotionPanTab sourisMTab = new SourisMotionPanTab();
		Jtext.addMouseListener(sourisTab);
		Jtext.addMouseMotionListener(sourisMTab);
		this.addMouseListener(sourisTab);
		this.addMouseMotionListener(sourisMTab);

		TouchePanTab toucheP = new TouchePanTab();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(toucheP);
		this.addKeyListener(toucheP);
		this.add(gris);

		Touche touche = new Touche();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.addKeyListener(touche);
		this.add(gris);
		//this.setBorder(BorderFactory.createLineBorder(Color.white));
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), 200);
		//Font fontplain = new Font("Sylfaen", Font.PLAIN, 25);
		//Font fontbold = new Font("Sylfaen", Font.BOLD, 25);
		//Font fontitalic = new Font("Sylfaen", Font.ITALIC, 25);

		if (Fen.dialogOn==1 || Fen.KeywordOn>0 || Fen.MenuStatOn==1){
			Jtext.setVisible(true);
			Jtext.repaint();
			}
		else if (Fen.dialogOn==0) Jtext.setVisible(false);
		
		if (fontplainOn==0) g.setFont(fontplain);
		else if (fontplainOn==2) g.setFont(fontplain2);
		int dist=13;
		if (Fen.MenuStatOn==0){
//****
		if (Fen.ChoiceSet==1){
//****
		if (Fen.ChoiceType2On==1){
			g.setColor(Color.cyan);
			if (ChoiceType2Color==1) g.fillRect(0,0,750,200);
			else if (ChoiceType2Color==2) g.fillRect(750,0,750,200);
			if (Fen.Choice!=0) g.setColor(Color.magenta);
			if (Fen.Choice==1) g.fillRect(0,0,750,200);
			else if (Fen.Choice==2) g.fillRect(750,0,750,200);
			g.setColor(Color.white);
			g.drawLine(750, 0, 750, 200);
			g.drawString(Text1,375-(int)(Text1.length()/2)*dist, 110);
			g.drawString(Text2,1125-(int)(Text2.length()/2)*dist, 110);

			g.drawString(Text1bis,375-(int)(Text1bis.length()/2)*dist, 135);
			g.drawString(Text2bis,1125-(int)(Text2bis.length()/2)*dist, 135);
			}
		if (Fen.ChoiceType3On==1){
			g.setColor(Color.cyan);
			if (ChoiceType3Color==1) g.fillRect(0,0,500,200);
			else if (ChoiceType3Color==2) g.fillRect(500,0,500,200);
			else if (ChoiceType3Color==3) g.fillRect(1000,0,500,200);
			if (Fen.Choice!=0) g.setColor(Color.magenta);
			if (Fen.Choice==1) g.fillRect(0,0,500,200);
			else if (Fen.Choice==2) g.fillRect(500,0,500,200);
			else if (Fen.Choice==3) g.fillRect(1000,0,500,200);
			g.setColor(Color.white);
			for(int i=0; i<2; i++) g.drawLine(500+i*500, 0, 500+i*500, 200);
			g.drawString(Text1,250-(int)(Text1.length()/2)*dist, 110);
			g.drawString(Text2,750-(int)(Text2.length()/2)*dist, 110);
			g.drawString(Text3,1250-(int)(Text3.length()/2)*dist, 110);

			g.drawString(Text1bis,250-(int)(Text1bis.length()/2)*dist, 135);
			g.drawString(Text2bis,750-(int)(Text2bis.length()/2)*dist, 135);
			g.drawString(Text3bis,1250-(int)(Text3bis.length()/2)*dist, 135);
			}
		if (Fen.ChoiceType4On==1){
			g.setColor(Color.cyan);
			//if (Fen.Choice!=0) g.setColor(Color.magenta);
			if (ChoiceType4Color==1) g.fillRect(0,0,375,200);
			else if (ChoiceType4Color==2) g.fillRect(375,0,375,200);
			else if (ChoiceType4Color==3) g.fillRect(750,0,375,200);
			else if (ChoiceType4Color==4) g.fillRect(1125,0,375,200);
			if (Fen.Choice!=0) g.setColor(Color.magenta);
			if (Fen.Choice==1) g.fillRect(0,0,375,200);
			else if (Fen.Choice==2) g.fillRect(375,0,375,200);
			else if (Fen.Choice==3) g.fillRect(750,0,375,200);
			else if (Fen.Choice==4) g.fillRect(1125,0,375,200);
			g.setColor(Color.white);
			for(int i=0; i<3; i++) g.drawLine(375+i*375, 0, 375+i*375, 200);
			g.drawString(Text1,0+187-(int)(Text1.length()/2)*dist, 110);
			g.drawString(Text2,375+187-(int)(Text2.length()/2)*dist, 110);
			g.drawString(Text3,750+187-(int)(Text3.length()/2)*dist, 110);
			g.drawString(Text4,1125+187-(int)(Text4.length()/2)*dist, 110);

			g.drawString(Text1bis,0+187-(int)(Text1bis.length()/2)*dist, 135);
			g.drawString(Text2bis,375+187-(int)(Text2bis.length()/2)*dist, 135);
			g.drawString(Text3bis,750+187-(int)(Text3bis.length()/2)*dist, 135);
			g.drawString(Text4bis,1125+187-(int)(Text4bis.length()/2)*dist, 135);

			}
		if (Fen.ChoiceType5On==1){
			dist=12;
			g.setColor(Color.cyan);
			if (Fen.Choice!=0) g.setColor(Color.magenta);
			if (ChoiceType5Color==1) g.fillRect(0,0,300,200);
			else if (ChoiceType5Color==2) g.fillRect(300,0,300,200);
			else if (ChoiceType5Color==3) g.fillRect(600,0,300,200);
			else if (ChoiceType5Color==4) g.fillRect(900,0,300,200);
			else if (ChoiceType5Color==5) g.fillRect(1200,0,300,200);
			g.setColor(Color.white);
			for(int i=0; i<4; i++) g.drawLine(300+i*300, 0, 300+i*300, 200);
		
			//if ((int)(Text1.length()/2)*dist>=150 && T1Gap==0) T1Gap=1;
			
			g.drawString(Text1,0+150-(int)(Text1.length()/2)*dist, 110);
			g.drawString(Text2,300+150-(int)(Text2.length()/2)*dist, 110);
			g.drawString(Text3,600+150-(int)(Text3.length()/2)*dist, 110);
			g.drawString(Text4,900+150-(int)(Text4.length()/2)*dist, 110);
			g.drawString(Text5,1200+150-(int)(Text5.length()/2)*dist, 110);

			g.drawString(Text1bis,0+150-(int)(Text1bis.length()/2)*dist, 135);
			g.drawString(Text2bis,300+150-(int)(Text2bis.length()/2)*dist, 135);
			g.drawString(Text3bis,600+150-(int)(Text3bis.length()/2)*dist, 135);
			g.drawString(Text4bis,900+150-(int)(Text4bis.length()/2)*dist, 135);
			g.drawString(Text5bis,1200+150-(int)(Text5bis.length()/2)*dist, 135);
			}
		if (Fen.ChoiceType6On==1){
			dist=12;
			g.setColor(Color.cyan);
			if (Fen.Choice!=0) g.setColor(Color.magenta);
			if (ChoiceType6Color==1) g.fillRect(0,0,250,200);
			else if (ChoiceType6Color==2) g.fillRect(250,0,250,200);
			else if (ChoiceType6Color==3) g.fillRect(500,0,250,200);
			else if (ChoiceType6Color==4) g.fillRect(750,0,250,200);
			else if (ChoiceType6Color==5) g.fillRect(1000,0,250,200);
			else if (ChoiceType6Color==6) g.fillRect(1250,0,250,200);
			g.setColor(Color.white);
			for(int i=0; i<5; i++) g.drawLine(250+i*250, 0, 250+i*250, 200);
			
			g.drawString(Text1,0+125-(int)(Text1.length()/2)*dist, 85);
			g.drawString(Text2,250+125-(int)(Text2.length()/2)*dist, 85);
			g.drawString(Text3,500+125-(int)(Text3.length()/2)*dist, 85);
			g.drawString(Text4,750+125-(int)(Text4.length()/2)*dist, 85);
			g.drawString(Text5,1000+125-(int)(Text5.length()/2)*dist, 85);
			g.drawString(Text6,1250+125-(int)(Text6.length()/2)*dist, 85);

			g.drawString(Text1bis,0+125-(int)(Text1bis.length()/2)*dist, 110);
			g.drawString(Text2bis,250+125-(int)(Text2bis.length()/2)*dist, 110);
			g.drawString(Text3bis,500+125-(int)(Text3bis.length()/2)*dist, 110);
			g.drawString(Text4bis,750+125-(int)(Text4bis.length()/2)*dist, 110);
			g.drawString(Text5bis,1000+125-(int)(Text5bis.length()/2)*dist, 110);
			g.drawString(Text6bis,1250+125-(int)(Text6bis.length()/2)*dist, 110);

			g.drawString(Text1tri,0+125-(int)(Text1tri.length()/2)*dist, 135);
			g.drawString(Text2tri,250+125-(int)(Text2tri.length()/2)*dist, 135);
			g.drawString(Text3tri,500+125-(int)(Text3tri.length()/2)*dist, 135);
			g.drawString(Text4tri,750+125-(int)(Text4tri.length()/2)*dist, 135);
			g.drawString(Text5tri,1000+125-(int)(Text5tri.length()/2)*dist, 135);
			g.drawString(Text6tri,1250+125-(int)(Text6tri.length()/2)*dist, 135);
			}
//****
			} //Fin ChoiceSet
//****


		if (Fen.ChoiceSet==2 && (Fen.ChoiceType2On==1 || Fen.ChoiceType3On==1 || Fen.ChoiceType4On==1 || Fen.ChoiceType5On==1)){
			Dimension D = new Dimension(0,0);
			if (Fen.ChoiceType2On==1) D.setSize(750-16,200);
			if (Fen.ChoiceType3On==1) D.setSize(500-16,200);
			if (Fen.ChoiceType4On==1) D.setSize(375-16,200);
			if (Fen.ChoiceType5On==1) D.setSize(300-16,200);
			JText1.setPreferredSize(new Dimension(D));
			JText2.setPreferredSize(new Dimension(D));
			JText3.setPreferredSize(new Dimension(D));
			JText4.setPreferredSize(new Dimension(D));
			JText5.setPreferredSize(new Dimension(D));

			//JText1.setRows(1);
			if (Fen.info==1){
				System.out.println("LineCount="+JText1.getLineCount());
				System.out.println("Rows="+JText1.getRows());
				System.out.println("Columns="+JText1.getColumns());
				System.out.println("TabSize="+JText1.getTabSize());
				//System.out.println("RowHeight="+JText1.getRowHeight());
				
				}

			JText1.setVisible(true);
			JText2.setVisible(true);
			g.setColor(Color.cyan);
			if (Fen.ChoiceType2On==1){
				//if (ChoiceJColor1==1) g.fillRect(0,0,750,200);
				//else if (ChoiceJColor2==1) g.fillRect(750,0,750,200);
				}
			if (Fen.ChoiceType3On==1){
				JText3.setVisible(true);
				//if (ChoiceJColor1==1) g.fillRect(0,0,500,200);
				//else if (ChoiceJColor2==1) g.fillRect(500,0,500-5,200);
				//else if (ChoiceJColor3==1) g.fillRect(1000-5,0,500+5,200);
				}
			if (Fen.ChoiceType4On==1){
				JText3.setVisible(true);
				JText4.setVisible(true);
				//if (ChoiceJColor1==1) g.fillRect(0,0,375,200);
				//else if (ChoiceJColor2==1) g.fillRect(375,0,375,200);
				//else if (ChoiceJColor3==1) g.fillRect(750,0,375,200);
				//else if (ChoiceJColor4==1) g.fillRect(1125,0,375,200);
				}
			if (Fen.ChoiceType5On==1){
				JText3.setVisible(true);
				JText4.setVisible(true);
				JText5.setVisible(true);
				//if (ChoiceJColor1==1) g.fillRect(0,0,300+8,200);
				//else if (ChoiceJColor2==1) g.fillRect(300+8,0,300-8,200);
				//else if (ChoiceJColor3==1) g.fillRect(600,0,300-4,200);
				//else if (ChoiceJColor4==1) g.fillRect(900-4,0,300+4,200);
				//else if (ChoiceJColor5==1) g.fillRect(1200-8,0,300+8,200);
				}
			if (ChoiceJColor1==1) JText1.setBackground(Color.cyan);
			else JText1.setBackground(Color.black);
			if (ChoiceJColor2==1) JText2.setBackground(Color.cyan);
			else JText2.setBackground(Color.black);
			if (ChoiceJColor3==1) JText3.setBackground(Color.cyan);
			else JText3.setBackground(Color.black);
			if (ChoiceJColor4==1) JText4.setBackground(Color.cyan);
			else JText4.setBackground(Color.black);
			if (ChoiceJColor5==1) JText5.setBackground(Color.cyan);
			else JText5.setBackground(Color.black);
			JText1.setText("\n"+"\n"+Text1);
			JText2.setText("\n"+"\n"+Text2);
			JText3.setText("\n"+"\n"+Text3);
			JText4.setText("\n"+"\n"+Text4);
			JText5.setText("\n"+"\n"+Text5);
			}
			} // Fin MenuStatOn


		if (Fen.KeywordOn==1){
			//Jtext.setEditable(true);
			//Jtext.setText(TextKeyword);
			Jtext.setText(Fen.Keyword);
			//Fen.DisplayText(TextKeyword,3);
			}
		//else Jtext.setEditable(false);
	
		if (Fen.info==1){
			//System.out.println("Editable="+Jtext.isEditable());
			//System.out.println("Jtext="+Jtext.getText());
			}
		}

		
	}
//****************************************************************************************
class SourisPanTab implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1 && Fen.MenuStatOn==0){
/*			System.out.println("MenuStatOn="+Fen.MenuStatOn+"\n"+
*					   "dialogOn="+Fen.dialogOn+"\n"+
*					   "dialog="+Fen.dialog+"\n");
*/			if (Fen.MenuStatOn==1) Fen.MenuStatOn=0;			
			if (Fen.dialogOn==1){
					//System.out.println("bon");
				if (Fen.dialogDefilement!=Fen.dialogLength){
					Fen.dialogDefilement=Fen.dialogLength;
					//System.out.println("bon1");
					}
				else if ((Fen.TenterChanceOn==0 || Fen.TenterChanceOn==5) 
					&& (Fen.TesterHabileteOn==0  || Fen.TesterHabileteOn==5)
					&& (Fen.TesterHabileteLocalisationOn==0 || Fen.TesterHabileteLocalisationOn==5)){
					//System.out.println("bon2");
					Fen.dialog+=1;
					Fen.dialogChangedOn=1;
					Fen.dialogDefilement=0;
					Fen.dialogLength=0;
					PanTab.Jtext.setText("");
					PanTab.dialogName="";
					PanTab.dialogNameKind=0;
					}
				else if (Fen.DiceThrow2<3){
					Fen.DiceThrow2=3;
					Fen.DiceTour=0;
					Fen.Dice=1+(int)(Math.random()*6);
					Fen.Dice2=1+(int)(Math.random()*6);
					}
				}

			if (PanTab.ChoiceType2Color!=0) Fen.Choice=PanTab.ChoiceType2Color;
			if (PanTab.ChoiceType3Color!=0) Fen.Choice=PanTab.ChoiceType3Color;
			if (PanTab.ChoiceType4Color!=0) Fen.Choice=PanTab.ChoiceType4Color;
			if (PanTab.ChoiceType5Color!=0) Fen.Choice=PanTab.ChoiceType5Color;
			if (PanTab.ChoiceType6Color!=0) Fen.Choice=PanTab.ChoiceType6Color;
			if (PanTab.ChoiceType7Color!=0) Fen.Choice=PanTab.ChoiceType7Color;
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){
		PanTab.ChoiceType2Color=PanTab.ChoiceType3Color=PanTab.ChoiceType4Color=PanTab.ChoiceType5Color=0;
		}
	}
class SourisMotionPanTab implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){
		int X = event.getX();
		int Y = event.getY();
		if (Fen.MenuStatOn==0){
		if (Fen.ChoiceType2On==1){
			if (0<X && X<750) PanTab.ChoiceType2Color=1;
			else if (750<X && X<1500) PanTab.ChoiceType2Color=2;
			}
		else if (Fen.ChoiceType3On==1){
			if (0<X && X<500) PanTab.ChoiceType3Color=1;
			else if (500<X && X<1000) PanTab.ChoiceType3Color=2;
			else if (1000<X && X<1500) PanTab.ChoiceType3Color=3;
			}
		else if (Fen.ChoiceType4On==1){
			if (0<X && X<375) PanTab.ChoiceType4Color=1;
			else if (375<X && X<750) PanTab.ChoiceType4Color=2;
			else if (750<X && X<1125) PanTab.ChoiceType4Color=3;
			else if (1125<X && X<1500) PanTab.ChoiceType4Color=4;
			}
		else if (Fen.ChoiceType5On==1){
			if (0<X && X<300) PanTab.ChoiceType5Color=1;
			else if (300<X && X<600) PanTab.ChoiceType5Color=2;
			else if (600<X && X<900) PanTab.ChoiceType5Color=3;
			else if (900<X && X<1200) PanTab.ChoiceType5Color=4;
			else if (1200<X && X<1500) PanTab.ChoiceType5Color=5;
			}
			}
		}
	public void mouseDragged(MouseEvent event){}
	}

class SourisPanTabJtext1 implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1 && PanTab.ChoiceJColor1==1) Fen.Choice=1;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){
		PanTab.ChoiceJColor1=1;
		}
	public void mouseExited(MouseEvent event){
		PanTab.ChoiceJColor1=0;
		}
	}
class SourisPanTabJtext2 implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1 && PanTab.ChoiceJColor2==1) Fen.Choice=2;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){
		PanTab.ChoiceJColor2=1;
		}
	public void mouseExited(MouseEvent event){
		PanTab.ChoiceJColor2=0;
		}
	}
class SourisPanTabJtext3 implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1 && PanTab.ChoiceJColor3==1) Fen.Choice=3;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){
		PanTab.ChoiceJColor3=1;
		}
	public void mouseExited(MouseEvent event){
		PanTab.ChoiceJColor3=0;
		}
	}
class SourisPanTabJtext4 implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1 && PanTab.ChoiceJColor4==1) Fen.Choice=4;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){
		PanTab.ChoiceJColor4=1;
		}
	public void mouseExited(MouseEvent event){
		PanTab.ChoiceJColor4=0;
		}
	}
class SourisPanTabJtext5 implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1 && PanTab.ChoiceJColor5==1) Fen.Choice=5;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){
		PanTab.ChoiceJColor5=1;
		}
	public void mouseExited(MouseEvent event){
		PanTab.ChoiceJColor5=0;
		}
	}

class TouchePanTab implements KeyListener{
	public void keyTyped(KeyEvent event){
		char k = event.getKeyChar();
		if (Fen.KeywordOn==1 && k!='\b') Fen.Keyword+=String.valueOf(k);
		//PanTab.Jtext.append(String.valueOf(k));
		//PanTab.Jtext.insert(String.valueOf(k), PanTab.Jtext.getText().length());
		}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		if (c=='\b' && Fen.Keyword.length()>0){
			//System.out.println("BON EFFACER");
			String K = Fen.Keyword;
			Fen.Keyword=K.substring(0,K.length()-1);
			}
		if (Fen.KeywordOn==1 && c=='\n'){
			Fen.KeywordOn=2;
			//Fen.Keyword=PanTab.Jtext.getText();
			//System.out.println(PanTab.Jtext.getText());
			PanTab.Jtext.setText("");
			}
		}
	public void keyReleased(KeyEvent event){}
	}
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PANTAB End
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************


//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PANSTAT
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
class PanStat extends JPanel{
	
//Refaire le menu avec des onglets EQUIPEMENT, INVENTAIRE, PARAMETRES

//#ONWORK

	static int [] Weapon = new int [50];
	static int [] Armor = new int [50];
	static int [] Object = new int [50];
	static int [] Treasure = new int [50];
	static int [] Effects = new int [50];
	static int W=0;
	static int A=0;
	static int O=0;
	static int T=0;
	static int E=0;
	static int WeaponSelected=-1;
	static int WeaponSelectedOn=0;
	static int ArmorSelected=-1;
	static int ArmorSelectedOn=0;
	static int ObjectSelected=-1;
	static int ObjectSelectedOn=0;
	static int MagicSelected=-1;
	static int MagicSelectedOn=0;
	static int EffectsSelected=-1;
	static int EffectsSelectedOn=0;
	static int TreasureSelected=-1;
	static int TreasureSelectedOn=0;

	static int [][] TreasureTab = new int [7][3];

	static int RavitaillementOn=0;
	static int Magic12UseOn=0;
	static int PoisonOn=0;

	static int UseOn=0; // Utiliser ou Equiper ou Lancer
	static int Use=0;
	
	static int X=0;
	static int Y=0;
	static int panStatOn=0;
	JButton gris = new JButton();
	public PanStat(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
	
		

		SourisPanStat sourisStat = new SourisPanStat();
		SourisMotionPanStat sourisMStat = new SourisMotionPanStat();

		this.addMouseListener(sourisStat);
		this.addMouseMotionListener(sourisMStat);

		SourisXY s = new SourisXY();
		this.addMouseListener(s);

		Touche touche = new Touche();
		this.addKeyListener(touche);	

		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		//this.add(gris);	

		this.setBorder(BorderFactory.createLineBorder(Color.white));
		}
	public void paintComponent(Graphics g){
		WeaponSelectedOn=-1;
		ArmorSelectedOn=-1;
		ObjectSelectedOn=-1;
		MagicSelectedOn=-1;
		EffectsSelectedOn=-1;
		TreasureSelectedOn=-1;
		Fen.dialogOn=1;
		if (Fen.Effects[3]>0) PoisonOn=1;
		else if (Fen.Effects[3]==0){
			PoisonOn=0;
			Fen.EnduranceTotalDepart=Fen.EnduranceTotalDepartBis;
			}
		for(int i=0; i<50; i++){
			Weapon[i]=Armor[i]=Object[i]=Treasure[i]=Effects[i]=-1;
			}
		W=A=O=T=E=0;
		for(int i=0; i<50; i++){
			if (Fen.Weapon[i]>0){
				Weapon[W]=i;
				W+=1;
				}
			if (Fen.Armor[i]>0){
				Armor[A]=i;
				A+=1;
				}
			if (Fen.Object[i]>0){
				Object[O]=i;
				O+=1;
				}
			//if (Fen.Treasure[i]>0){ //#1 exemplaire par emplacement mais plusieurs
			if (Fen.Treasure[i]>-1){ //#1 exemplaire par emplacement
				//for(int j=0; j<Fen.Treasure[i]; j++){
					//Treasure[T]=i;
					//T+=1;
					//}
				Treasure[T]=Fen.Treasure[i];
				T+=1;
				}
			if (Fen.Effects[i]>0){
				Effects[E]=i;
				E+=1;
				}
			}
		if (Fen.info==1){
			//Fen.Afficher(Weapon);
			System.out.println("W:"+WeaponSelected+"\t"+"wOn:"+WeaponSelectedOn);
			System.out.println("A:"+ArmorSelected+"\t"+"aOn:"+ArmorSelectedOn);
			System.out.println("O:"+ObjectSelected+"\t"+"oOn:"+ObjectSelectedOn);
			System.out.println("M:"+MagicSelected+"\t"+"mOn:"+MagicSelectedOn);
			System.out.println("T:"+TreasureSelected+"\t"+"tOn:"+TreasureSelectedOn);
			System.out.println();
			}


		//if (Fen.MenuStatOn==1) panStatOn=1;
		//else if (Fen.MenuStatOn==0) panStatOn=0;
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), 800);
		Font fontplain = new Font("Sylfaen", Font.PLAIN, 30);
		Font fontbold = new Font("Sylfaen", Font.BOLD, 30);
		g.setFont(fontplain);
		g.setColor(Color.white);
		g.drawLine(375, 0, 375, 715  +85);
		g.drawLine(570+20, 0, 570+20, 715  +85);
		g.drawLine(830, 65, 830, 715  +85);
		g.drawLine(1070, 65, 1070, 715  +85);
		g.drawLine(0,715,1500,715);
		g.drawLine(1310, 0, 1310, 715  +85);
		g.drawLine(375, 65+0*25, 1310, 65+0*25);
		g.drawLine(100, 715, 100, 800);

		for(int i=0; i<2; i++) g.drawLine(100, 743+i*28, 1500, 743+i*28);

		g.drawRect(186-75+0*375, 200-100, 150, 200);
		if (Fen.Perso==1) g.drawString("ANVAR, le Barbare",10+40, 50);
		else if (Fen.Perso==2) g.drawString("BRAXUS, le Guerrier",10+30, 50);
		else if (Fen.Perso==3) g.drawString("RABLAIX, le Nain",10+40, 50);
		else if (Fen.Perso==4) g.drawString("SALLAZAR, le Magicien",5+20, 50);

		//g.drawString("Equipement", (375+562)-145+20,50);
		g.drawString("Equipement", 410,50);
		g.drawString("Inventaire", 890,50);
		//g.drawString("Tresors",750-45+120,685);
		g.drawString("Tresors",5,765);
		g.drawString("Sortileges",1350,50);
		g.drawString("Effets",1350+20,410);
		fontplain = new Font("Sylfaen", Font.PLAIN, 15);
		fontbold = new Font("Sylfaen", Font.BOLD, 15);
		if (Fen.Perso==1){
			g.setFont(fontbold);
			g.drawString("Avantages:",5,315);
			g.setFont(fontplain);
			g.drawString("Tres vigilant, ne peut se laisser prendre par surprise",5,330);
			g.setFont(fontbold);
			g.drawString("Inconvenients:",5,355);
			g.setFont(fontplain);
			g.drawString("-Incapable de porter une armure a plates",5,370);
			g.drawString("-le port d'une cotte de mailles n'ajoure aucun point bonus",5,370+15*1);
			g.drawString("  a la Force d'Attaque (FdA)",5,370+15*2);
			g.drawString("-le maniement d'une arbalete diminue de 2 la FdA",5,370+15*3);
			}
		else if (Fen.Perso==2){
			g.setFont(fontbold);
			g.drawString("Avantages:",5,315);
			g.setFont(fontplain);
			g.drawString("Peut manier n'importe quel type d'arme",5,330);
			g.setFont(fontbold);
			g.drawString("Inconvenients:",5,345);
			g.setFont(fontplain);
			g.drawString("Aucun",5,360);
			}
		else if (Fen.Perso==3){
			g.setFont(fontbold);
			g.drawString("Avantages:",5,315);
			g.setFont(fontplain);
			g.drawString("Votre FdA augmente de 2 en cas de combat avec un",5,330);
			g.drawString("monstre de pierre",5,345);
			g.setFont(fontbold);
			g.drawString("Inconvenients:",5,360);
			g.setFont(fontplain);
			g.drawString("Incapable d'utiliser un arc ou une arme a deux mains",5,375);
			}
		else if (Fen.Perso==4){
			g.setFont(fontbold);
			g.drawString("Avantages:",5,315);
			g.setFont(fontplain);
			g.drawString("HABILETE augmente de 2 quand il faut",5,330);
			g.drawString("<Tester son HABILETE DE LOCALISATION>",5,345);
			g.setFont(fontbold);
			g.drawString("Inconvenients:",5,360);
			g.setFont(fontplain);
			g.drawString("-Incapable de porter une armure metallique",5,375);
			g.drawString("-Incapable d'utiliser un arc",5,390);
			g.drawString("-Incapable d'utiliser une arme a deux mains",5,405);
			}

			g.setFont(fontbold);
			g.setColor(Color.red);
			g.drawString("ENDURANCE=",5,450);
			g.setColor(Color.white);
			g.drawString(String.valueOf(Fen.Endurance)+"/"+String.valueOf(Fen.EnduranceTotalDepart),115,450);

			g.setFont(fontbold);
			g.setColor(Color.red);
			g.drawString("HABILETE=",5,475);
			g.setColor(Color.white);
			g.drawString(String.valueOf(Fen.Habilete)+"/"+String.valueOf(Fen.HabileteTotalDepart),93,475);

			g.setFont(fontbold);
			g.setColor(Color.red);
			g.drawString("CHANCE=",5,500);
			g.setColor(Color.white);
			g.drawString(String.valueOf(Fen.Chance)+"/"+String.valueOf(Fen.ChanceTotalDepart),82,500);

			g.setFont(fontbold);
			g.setColor(Color.red);
			g.drawString("MAGIE=",5,525);
			g.setColor(Color.white);
			g.drawString(String.valueOf(Fen.Magie),68,525);

			g.setColor(Color.yellow);			
			g.drawString("Pieces d'Or=",5,550);
			g.setColor(Color.white);
			g.drawString(String.valueOf(Fen.Gold),96, 550);



			g.setColor(Color.green);
			if (Fen.SelectedObjectOn==1) g.fillRect(130-5, 580-20, 130+5,25);
			g.drawString("Provisions=",5,580);
			g.setColor(Color.white);
			g.drawString(String.valueOf(Fen.Provisions),89, 580);
			g.drawRect(130-5, 580-20, 130+5,25);
			g.drawString("Prendre un Repas", 130, 580);


			g.setColor(Color.cyan);
			g.drawString("Talisman d'Or=",5,610);
			g.setColor(Color.white);
			g.drawString(String.valueOf(Fen.TalismanOr),113, 610);
			g.setColor(Color.cyan);
			g.drawString("Dague d'Argent=",5,640);
			g.setColor(Color.white);
			g.drawString(String.valueOf(Fen.DagueArgent),126, 640);


			switch(Fen.WeaponEquiped){
				case 0:Fen.WeaponEquipedName="Petit couteau";break;
				case 1:Fen.WeaponEquipedName="Epee";break;
				case 2:Fen.WeaponEquipedName="Hache de combat";break;
				case 3:Fen.WeaponEquipedName="Matraque en bois";break;
				case 4:Fen.WeaponEquipedName="Arc et fleches";break;
				case 5:Fen.WeaponEquipedName="Arbalete et carreaux";break;
				}
			switch(Fen.ArmorEquiped){
				case 0:Fen.ArmorEquipedName="Legere cuirasse en peau";break;
				case 1:Fen.ArmorEquipedName="Cotte de mailles";break;
				}

			g.drawString("Arme: "+Fen.WeaponEquipedName,5,670);
			g.drawString("Armure: "+Fen.ArmorEquipedName,5,700);

//**SORTILEGE
			for(int i=0; i<12; i++){
				if (1310<X && X<1500 && 65+i*25<Y && Y<65+(i+1)*25 && Fen.Grimoire[i]==1){
					MagicSelectedOn=i;
					g.setColor(Color.cyan);
					g.fillRect(1310+1, 65+i*25+1, 190-1, 25-1);
					}
				if (MagicSelected==i){
					g.setColor(Color.magenta);
					g.fillRect(1310+1, 65+i*25+1, 190-1, 25-1);
					}
				}
			if (1310>=X || X>=1500 || 65>=Y || Y>=650) MagicSelectedOn=-1;
			g.setColor(Color.white);
			for(int i=0; i<13; i++) g.drawLine(1310, 65+i*25, 1500, 65+i*25);
			if (Fen.Grimoire[0]==1) g.drawString("Ravitaillement", 1320, 85+0*25);
			if (Fen.Grimoire[1]==1) g.drawString("Saut", 1320, 85+1*25);
			if (Fen.Grimoire[2]==1) g.drawString("Lumiere", 1320, 85+2*25);
			if (Fen.Grimoire[3]==1) g.drawString("Chance", 1320, 85+3*25);
			if (Fen.Grimoire[4]==1) g.drawString("Habilete", 1320, 85+4*25);
			if (Fen.Grimoire[5]==1) g.drawString("Main Vive", 1320, 85+5*25);
			if (Fen.Grimoire[6]==1) g.drawString("Boule de Feu", 1320, 85+6*25);
			if (Fen.Grimoire[7]==1) g.drawString("Ecran Magique", 1320, 85+7*25);
			if (Fen.Grimoire[8]==1) g.drawString("Mort", 1320, 85+8*25);
			if (Fen.Grimoire[9]==1) g.drawString("Foudre", 1320, 85+9*25);
			if (Fen.Grimoire[10]==1) g.drawString("Teleportation", 1320, 85+10*25);
			if (Fen.Grimoire[11]==1) g.drawString("Appropriation de tresor", 1320, 85+11*25);

			
			if (RavitaillementOn==1){
				Fen.dialogEnd();
				Fen.ChoiceType4On=1;
				PanTab.Text1="Depenser 1 point de Magie";
				PanTab.Text2="Depenser 2 point de Magies";
				PanTab.Text3="Depenser 3 point de Magies";
				PanTab.Text4="Renoncer";
				switch(Fen.Choice){
					case 1:Fen.Magie-=1;Fen.Provisions+=1;RavitaillementOn=0;Fen.ChoiceReset();MagicSelected=-1;break;
					case 2:if (Fen.Magie>=2){
						Fen.Magie-=2;
						if (12-Fen.Provisions==1) Fen.Provisions+=1;
						else if (12-Fen.Provisions==2) Fen.Provisions+=2;
						else Fen.Provisions+=3;
						RavitaillementOn=0;
						Fen.ChoiceReset();
						MagicSelected=-1;
						}
						break;
					case 3:if (Fen.Magie>=3){
						Fen.Magie-=3;
						if (12-Fen.Provisions==1) Fen.Provisions+=1;
						else if (12-Fen.Provisions==2) Fen.Provisions+=2;
						else if (12-Fen.Provisions==3) Fen.Provisions+=3;
						else if (12-Fen.Provisions==4) Fen.Provisions+=4;
						else Fen.Provisions+=5;
						RavitaillementOn=0;
						Fen.ChoiceReset();
						MagicSelected=-1;
						}
						break;
					case 4:RavitaillementOn=0;Fen.ChoiceReset();MagicSelected=-1;break;
					}
				}
			if (Magic12UseOn==1){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				PanTab.Text1="Talisman d'Or";
				PanTab.Text2="Dague d'Argent";
				switch(Fen.Choice){
					case 1:Fen.TalismanOr+=1;Magic12UseOn=0;Fen.ChoiceReset();break;
					case 2:Fen.DagueArgent+=1;Magic12UseOn=0;Fen.ChoiceReset();break;
					}
				}
//**FIN SORTILEGE

//**EQUIPEMENT & INVENTAIRE


			//g.drawLine(375, 65+0*25, 570, 65+0*25);

			//g.drawLine(375, 65+(0+1)*25, 590, 65+(0+1)*25);
			//g.drawString("Petit couteau",380, 85+0*25);

			for(int i=0; i<50; i++){
				if (Weapon[i]!=-1){
					//g.drawLine(375, 65+i*25, 590, 65+i*25);
					g.drawLine(375, 65+(i+1)*25, 590, 65+(i+1)*25);
					if (375<X && X<590 && 65+i*25<Y && Y<65+(i+1)*25){
						WeaponSelectedOn=i;
						g.setColor(Color.cyan);
						g.fillRect(375+1, 65+i*25+1, 215-1, 25-1);
						}
					g.setColor(Color.magenta);
					if (WeaponSelected==i) g.fillRect(375+1, 65+i*25+1, 215-1, 25-1);
					g.setColor(Color.white);
					switch(Weapon[i]){
						case 0:g.drawString("Petit couteau",380, 85+i*25);break;
						case 1:g.drawString("Epee (x"+String.valueOf(Fen.Weapon[Weapon[i]])+")",380, 85+i*25);break;
						case 2:g.drawString("Hache de combat (x"+String.valueOf(Fen.Weapon[Weapon[i]])+")",380, 85+i*25);break;
						case 3:g.drawString("Matraque en bois (x"+String.valueOf(Fen.Weapon[Weapon[i]])+")",380, 85+i*25);break;
						case 4:g.drawString("Arc et fleches (x"+String.valueOf(Fen.Weapon[Weapon[i]])+")",380, 85+i*25);break;
						case 5:g.drawString("Arbalete et carreaux (x"+String.valueOf(Fen.Weapon[Weapon[i]])+")",380, 85+i*25);break;
						}
					}
				if (Weapon[i]==-1 && Weapon[i-1]!=-1){
					for(int j=0; j<50; j++){
						if (Armor[j]!=-1){
							if (375<X && X<590 && 65+i*25+j*25<Y && Y<65+(i+1)*25+j*25){
								ArmorSelectedOn=j;
								g.setColor(Color.cyan);
								g.fillRect(375+1, 65+i*25+j*25+1, 215-1, 25-1);
								}
							g.setColor(Color.magenta);
							if (ArmorSelected==j) g.fillRect(375+1, 65+i*25+j*25+1, 215-1, 25-1);
							g.setColor(Color.white);
							//g.drawLine(375, 65+i*25+j*25, 590, 65+i*25+j*25);
							g.drawLine(375, 65+(i+1)*25+j*25, 590, 65+(i+1)*25+j*25);
							switch(Armor[j]){
								case 0:g.drawString("Legere cuirasse en peau",380, 85+i*25+j*25);break;
								case 1:g.drawString("Cotte de mailles (x"+String.valueOf(Fen.Armor[Armor[j]])+")",380, 85+i*25+j*25);break;
								}
							}
						}
					}
				}
			if (375>=X || X>=590 || 65>=Y || Y>=650) WeaponSelectedOn=ArmorSelectedOn=-1;
			for(int i=0; i<50; i++){
				if (Object[i]!=-1){
					g.drawLine(590, 65+(i+1)*25, 830, 65+(i+1)*25);
					if (590<X && X<830 && 65+i*25<Y && Y<65+(i+1)*25){
						ObjectSelectedOn=i;
						g.setColor(Color.cyan);
						g.fillRect(590+1, 65+i*25+1, 240-1, 25-1);
						}
					g.setColor(Color.magenta);
					if (ObjectSelected==i) g.fillRect(590+1, 65+i*25+1, 240-1, 25-1);
					g.setColor(Color.white);
					switch(Object[i]){
						case 0:if (Fen.IsLampFilled){
							g.drawString("Lanterne pleine",595,85+i*25);
							}
							else{
							g.drawString("Lanterne vide",595,85+i*25);
							}
							break;
						case 1:g.drawString("Potion d'Endurance (x"+String.valueOf(Fen.Object[Object[i]])+")",595,85+i*25);break;
						case 2:g.drawString("Anneau Magique (x"+String.valueOf(Fen.Object[Object[i]])+")",595,85+i*25);break;
						case 3:g.drawString("Antidote contre la peste (x"+String.valueOf(Fen.Object[Object[i]])+")",595,85+i*25);break;
						case 4:g.drawString("Bouteille vide (x"+String.valueOf(Fen.Object[Object[i]])+")",595,85+i*25);break;
						case 5:g.drawString("Rouleau de corde (10m) (x"+String.valueOf(Fen.Object[Object[i]])+")",595,85+i*25);break;
						case 6:g.drawString("Flacon de Liqueur d'Herbes (x"+String.valueOf(Fen.Object[Object[i]])+")",595,85+i*25);break;
						case 7:g.drawString("Potion de Resistance au Feu (x"+String.valueOf(Fen.Object[Object[i]])+")",595,85+i*25);break;
						case 8:g.drawString("Pied de biche en acier (x"+String.valueOf(Fen.Object[Object[i]])+")",595,85+i*25);break;
						}
					}
				}
		if (590>=X || X>=830 || 65>=Y || Y>=650) ObjectSelectedOn=-1;
//**FIN EQUIPEMENT & INVENTAIRE	


//**EFFECTS
		for(int i=0; i<50; i++){
			if (Effects[i]!=-1){
				if (Effects[i]==0){
					g.setColor(Color.red);
					g.drawString("- Resistance au Feu",1315,430+i*25);
					}
				if (Effects[i]==1){
					g.setColor(new Color(128,255,128));
					g.drawString("- Ecran Magique",1315,430+i*25);
					}
				if (Effects[i]==2){
					g.setColor(Color.cyan);
					g.drawString("- Main Vive",1315,430+i*25);
					}
				if (Effects[i]==3){
					g.setColor(new Color(0,150,0));
					g.drawString("- Peste Bubonique",1315,430+i*25);
					}
				}
			}


//**FIN EFFECTS


//**TRESORS
		g.setColor(Color.white);
		g.drawLine(235, 715, 235, 800);		
		for(int i=0; i<21; i++){
			int I=i%7;
			int J=0;
			if (7<=i && i<=13) J=1;
			if (14<=i && i<=21) J=2;
			String Name="";
			if (Treasure[i]>-1){
				g.setColor(Color.cyan);
				if (I==0 || I==1){
					if (100+I*135<=X && X<=235+I*135){
						if (715+28*J<=Y && Y<=743+28*J){
							TreasureSelectedOn=I+J*7;
							g.fillRect(100+I*135+1, 715+28*J+1, 135+I*5-1, 28-1);
							}
						}
					}
				else if (I==2){
					if (375<=X && X<=590){
						if (715+28*J<=Y && Y<=743+28*J){
							TreasureSelectedOn=I+J*7;
							g.fillRect(375+1, 715+28*J+1, 215-1, 28-1);
							}
						}
					}
				else if (I==3 || I==4 || I==5){
					if (590+(I-3)*240<=X && X<=830+(I-3)*240){
						if (715+28*J<=Y && Y<=743+28*J){
							TreasureSelectedOn=I+J*7;
							g.fillRect(590+(I-3)*240+1, 715+28*J+1, 240-1, 28-1);
							}
						}
					}
				else if (I==6){
					if (1310<=X && X<=1500){
						if (715+28*J<=Y && Y<=743+28*J){
							TreasureSelectedOn=I+J*7;
							g.fillRect(1310+1, 715+28*J+1, 190-1, 28-1);
							}
						}
					}
				//switch(i){
				switch(Treasure[i]){
					case 0: Name="Tapisserie en soie";break;
					case 1: Name="Bol en faience";break;
					}
				if (TreasureSelected>-1){
					g.setColor(Color.magenta);
					int IS=TreasureSelected%7;
					int JS=0;
					if (7<=TreasureSelected && TreasureSelected<=13) JS=1;
					if (14<=TreasureSelected && TreasureSelected<=20) JS=2;
					if (IS==0 || IS==1) g.fillRect(100+IS*135+1,715+JS*28+1, 135+IS*5-1, 28-1);
					else if (IS==2) g.fillRect(375+1, 715+JS*28+1, 215-1, 28-1);
					else if (IS==3 || IS==4 || IS==5) g.fillRect(590+(IS-3)*240+1, 715+JS*28+1, 240-1, 28-1);
					else if (IS==6) g.fillRect(1310+1, 715+JS*28+1, 190-1, 28-1);
					g.setColor(Color.white);
					if (IS==0 || IS==1) g.drawString(Name,102+IS*135, 735+JS*28);
					else if (IS==2) g.drawString(Name,375+2, 735+JS*28);
					else if (IS==3 || IS==4 || IS==5) g.drawString(Name,590+(IS-3)*240+2, 735+JS*28);
					else if (IS==6) g.drawString(Name, 1312, 735+JS*28);
					}
				g.setColor(Color.white);
				if (I==0 || I==1) g.drawString(Name,102+I*135, 735+J*28);
				else if (I==2) g.drawString(Name,375+2, 735+J*28);
				else if (I==3 || I==4 || I==5) g.drawString(Name,590+(I-3)*240+2, 735+J*28);
				else if (I==6) g.drawString(Name, 1312, 735+J*28);
				}
			}
		if (100>X || X>1500 || 715>Y || Y>800) TreasureSelectedOn=-1;
				

//**FIN TRESORS



//**UTILISER, EQUIPER, LANCER
		if (WeaponSelected!=-1 || ArmorSelected!=-1) UseOn=1;
		else if (ObjectSelected!=-1){
			if (Object[ObjectSelected]==1 || Object[ObjectSelected]==2 || 
				(Object[ObjectSelected]==3 && PoisonOn==1) || Object[ObjectSelected]==6 || 
				Object[ObjectSelected]==7) UseOn=2;
			else UseOn=0;
			}
		else if (MagicSelected!=-1){
			if (MagicSelected==0 || MagicSelected==2 || MagicSelected==3 || 
				MagicSelected==4 || MagicSelected==7 || MagicSelected==11) UseOn=3;
			else UseOn=0;
			}
		else UseOn=0;


		String T="";
		if (UseOn!=0){
			if (225<X && X<305 && 435<Y && Y<455){
				Use=1;
				g.setColor(Color.cyan);
				g.fillRect(225+1, 435+1, 80-1,20-1);
				}
			else Use=0;
			g.setColor(Color.white);
			g.drawRect(225, 435, 80,20);
			g.setFont(fontbold);
			if (UseOn==1) T="EQUIPER";
			else if (UseOn==2) T="UTILISER";
			else if (UseOn==3) T="LANCER";
			}
		g.drawString(T,230,450);
//**FIN UTILISER, EQUIPER, LANCER
		
//**SELECTED DESCRIPTION

		String Description="";
		if (ObjectSelected!=-1){
			if (Object[ObjectSelected]==0) Description="Permet de s'eclairer dans des lieus sombres. Si votre lanterne se vide de son huile, il vous faudra user d'un sortilege de Lumiere pour vous eclairer. Vous ne pouvez pas perdre cet objet.";
			else if (Object[ObjectSelected]==1) Description="Redonne la moitie de l'ENDURANCE Total de Depart.";
			else if (Object[ObjectSelected]==2) Description="Octroie 1 point de Magie supplementaire.";
			else if (Object[ObjectSelected]==3) Description="Guerit d'une infection de la peste a la fois.";
			else if (Object[ObjectSelected]==4) Description="Une bouteille vide.";
			else if (Object[ObjectSelected]==5) Description="Un Rouleau de corde de 10m de long";
			else if (Object[ObjectSelected]==6) Description="Permet de recuperer 4 points d'ENDURANCE.";
			else if (Object[ObjectSelected]==7) Description="Confere une protection qui divise par deux les attaques de feu subies. L'effet dure jusqu'au prochain combat.";
			else if (Object[ObjectSelected]==8) Description="Permet de reduire de 1 point le resultat aux des a la fin d'un test d'HABILETE pour ouvrir une porte ou forcer une serrure.";

			}
		else if (WeaponSelected!=-1){
			if (Weapon[WeaponSelected]==0) Description="Vous serez rejoui de posseder ce petit couteau si par malheur vous perdiez votre arme. "+ 
							"Cependant, cette arme ne fera perdre 1 point seulement d'ENDURANCE a vos ennemis. Vous ne pouvez pas perdre cette arme.";
			else if (Weapon[WeaponSelected]==1) Description="Arme a une main.";
			else if (Weapon[WeaponSelected]==2) Description="Arme a une main.";
			else if (Weapon[WeaponSelected]==3) Description="Arme a une main.";
			else if (Weapon[WeaponSelected]==4) Description="Arc et fleches.";
			else if (Weapon[WeaponSelected]==5) Description="Arbalete et carreaux.";
				
			}
		else if (ArmorSelected!=-1){
			if (Armor[ArmorSelected]==0) Description="Cette legere cuirasse en peau ne vous confere pas une grande protection mais a le benefice de ne pas vous laisser le corps a nu face a vos ennemis ! "+
								"Vous ne pouvez pas perdre cet objet."; 		
			else if (Armor[ArmorSelected]==1) Description="La cotte de mailles n'augmente pas votre Force d'Attaque (FdA) lors d'un combat mais elle vous protegera contre  certains mauvais coups "+
									"que vous ne manquerez pas d'essuyer tout au long de votre perilleuse aventure.";
			}
		else if (MagicSelected!=-1){
			if (MagicSelected==0 && RavitaillementOn==0) Description="Cout = 1, 2 ou 3 points de Magie. Permet de recuperer des Provisions. Si vous depensez 1 point vous obtiendrez 1 Provision. "+
								"Si vous depenser 2 points vous obtiendrez 3 Provisions. Si vous depenser 3 points vous obtiendrez 5 Provisions. Sortilege inutilisable en combat. "+
								"N'oubliez pas que vous ne pouvez transporter que 12 Provisions au maximum.";
			else if (MagicSelected==1) Description="Cout = 1 point de Magie. Permet de parcourir une distance de 6 metres maximum pour franchir un obstacle (puits, couloir piege, fosse). Sortilege inutilisable en combat.";
			else if (MagicSelected==2) Description="Cout = 1 point de Magie. Si vous perdez votre lanterne ou si celle-ci ne fonctionne plus, ce sortilege generera une petite sphere de lumineuse qui eclairera votre chemin. "+
								"Le sortilege s'estompe des que vous entrez dans une nouvelle zone. Sortilege inutilisable en combat.";
			else if (MagicSelected==3) Description="Cout = 1 point de Magie. Augmente de 1 point le total de CHANCE. Sortilege inutilisable en combat.";
			else if (MagicSelected==4) Description="Cout = 1 point de Magie. Augmente de 1 point le total d'HABILETE. Utilisable uniquement avant un combat ou avant un Test d'HABILETE. "+
								"L'effet s'estompe aussitot le combat termine ou le Test effectue. Sortilege inutilisable en combat.";
			else if (MagicSelected==5) Description="Cout = 2 points de Magie. Permet lors des 3 premiers Assauts d'un combat, de lancer 2 des et conserver le meilleur resultat pour calculer la Force d'Attaque (FdA). "+
								"Utilisable uniquement avant un combat.";
			else if (MagicSelected==6) Description="Cout = 2 points de Magie. Lors d'un Assaut, si votre FdA est strictement superieur a celle de votre adversaire, la Boule de Feu embrasera son corps et lui "+
								"infligera 5 points de degats. Sinon la Boule de Feu rate sa cible. Utilisable uniquement en combat.";
			else if (MagicSelected==7) Description="Cout = 2 points de Magie. Confere la protection contre 1 sort lance par un ennemi. Sortilege inutilisable en combat.";
			else if (MagicSelected==8) Description="Cout = 3 points de Magie. Lors d'un Assaut, si votre FdA est strictement superieur a celle de votre adversaire, ce dernier perira sur-le-champ. "+
								"N'affecte que les ennemis dont l'HABILETE est inferieur ou egal a 9. N'affecte pas les Morts Vivants et Zagor.";
			else if (MagicSelected==9) Description="Cout = 3 points de Magie. Lors d'un Assaut, si votre FdA est strictement superieur a celle de votre adversaire, la Foudre le frappera de plein fouet et lui "+
								"infligera 7 points de degats. Sinon la Foudre rate sa cible. Utilisable uniquement en combat.";
			else if (MagicSelected==10) Description="Cout = 4 points de Magie. Permet de passer instantanement d'un endroit a un autre, qu'importe la distance, vous evitant ainsi moult embuches. "+
								"Utilisable uniquement face a certains portails magiques.";
			else if (MagicSelected==11 && Magic12UseOn==0) Description="Cout = 5 points de Magie. Permet d'obtenir au choix un Talisman d'Or ou une Dague d'Argent. Utilisable seulement 2 fois pour toute l'aventure. Le Talisman ou la Dague "+
								"obtenues ainsi ne vous procureront pas un point de CHANCE supplementaire. Vous avez utilise ce sort "+String.valueOf(Fen.Magic12Used)+" fois.";
			}
		if (TreasureSelected!=-1){
			if (Treasure[TreasureSelected]==0) Description="Une magnifique tapisserie en soie d'une valeur de 4 Pieces d'Or si vous trouvez quelqu'un a qui la vendre.";
			//if (TreasureSelected==0) Description="Une magnifique tapisserie en soie d'une valeur de 4 Pieces d'Or si vous trouvez quelqu'un a qui la vendre.";
			else if (Treasure[TreasureSelected]==1) Description="Un bol en faience finement ouvrage. Il doit surement avoir de la valeur pour quelqu'un.";

			}
		PanTab.Jtext.setText(Description);

//**FIN SELECTED DESCRIPTION

		if (1==1){
			g.setColor(Color.red);
			//g.drawLine(0, 725, 1500, 725);
			//g.drawLine(937, 650, 937, 800);

			//g.drawLine(1405, 0, 1405, 650);

			//g.drawLine(375, 325, 1500, 325);
			//g.drawLine(482, 0, 482, 650);
			//g.drawLine(950, 0, 950, 650);
			//g.drawLine(0,757, 1500, 757);
			}
		}
//****************************************************************************************



	}
//****************************************************************************************
class SourisPanStat implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1){
			
			if (Fen.SelectedObjectOn==1){
				if (Fen.Endurance<Fen.EnduranceTotalDepart && Fen.Provisions>0){
					Fen.Provisions-=1;
					if (Fen.Endurance==Fen.EnduranceTotalDepart-1) Fen.Endurance+=1;
					else if (Fen.Endurance==Fen.EnduranceTotalDepart-2) Fen.Endurance+=2;
					else if (Fen.Endurance==Fen.EnduranceTotalDepart-3) Fen.Endurance+=3;
					else  Fen.Endurance+=4;
					//Fen.SelectedObjectOn=0;
					}
				}

			if (PanStat.ObjectSelectedOn!=-1){
				PanStat.ObjectSelected=PanStat.ObjectSelectedOn;
				PanStat.WeaponSelected=-1;
				PanStat.ArmorSelected=-1;
				PanStat.MagicSelected=-1;
				PanStat.TreasureSelected=-1;
				}
			if (PanStat.WeaponSelectedOn!=-1){
				PanStat.WeaponSelected=PanStat.WeaponSelectedOn;
				PanStat.ObjectSelected=-1;
				PanStat.ArmorSelected=-1;
				PanStat.MagicSelected=-1;
				PanStat.TreasureSelected=-1;
				}
			if (PanStat.ArmorSelectedOn!=-1){
				PanStat.ArmorSelected=PanStat.ArmorSelectedOn;
				PanStat.ObjectSelected=-1;
				PanStat.WeaponSelected=-1;
				PanStat.MagicSelected=-1;
				PanStat.TreasureSelected=-1;
				}
			if (PanStat.MagicSelectedOn!=-1){
				PanStat.MagicSelected=PanStat.MagicSelectedOn;
				PanStat.WeaponSelected=-1;
				PanStat.ObjectSelected=-1;
				PanStat.ArmorSelected=-1;
				PanStat.TreasureSelected=-1;
				}
			if (PanStat.TreasureSelectedOn!=-1){
				PanStat.TreasureSelected=PanStat.TreasureSelectedOn;
				PanStat.WeaponSelected=-1;
				PanStat.ObjectSelected=-1;
				PanStat.ArmorSelected=-1;
				PanStat.MagicSelected=-1;
				}


			
			if (PanStat.Use==1){
				if (PanStat.ObjectSelected!=-1){
					switch(PanStat.Object[PanStat.ObjectSelected]){
						case 1:if (Fen.Endurance<Fen.EnduranceTotalDepart){
							int Heal=Fen.EnduranceTotalDepart/2;
							for(int i=1; i<=Heal; i++){
								if (Fen.EnduranceTotalDepart-i==Fen.Endurance){
									Fen.Endurance+=i;
									Heal=0;
									break;
									}
								}
							if (Heal!=0) Fen.Endurance+=Heal;
							Fen.Object[1]-=1;
							}
							break;
						case 2:Fen.Magie+=1; Fen.Object[2]-=1;break;
						case 3:for(int i=0; i<10; i++){
								if (PanStat.Effects[i]>=3){
									Fen.Effects[PanStat.Effects[i]]=0;
									break;
									}
								}
							break;
						case 6:if (Fen.Endurance<Fen.EnduranceTotalDepart){
							if (Fen.EnduranceTotalDepart-1==Fen.Endurance) Fen.Endurance+=1;
							else if (Fen.EnduranceTotalDepart-2==Fen.Endurance) Fen.Endurance+=2;
							else if (Fen.EnduranceTotalDepart-3==Fen.Endurance) Fen.Endurance+=3;
							else Fen.Endurance+=4;
							Fen.Object[6]-=1;
							}
							break;
						case 7:if (Fen.Effects[0]==0){
							Fen.Effects[0]=1;
							Fen.Object[7]-=1;
							}
							break;
						}
					}
				if (PanStat.WeaponSelected!=-1){
					switch(PanStat.Weapon[PanStat.WeaponSelected]){
						case 0:Fen.Weapon[Fen.WeaponEquiped]+=1;Fen.WeaponEquiped=0;Fen.Weapon[0]-=1;break;
						case 1:Fen.Weapon[Fen.WeaponEquiped]+=1;Fen.WeaponEquiped=1;Fen.Weapon[1]-=1;break;
						case 2:Fen.Weapon[Fen.WeaponEquiped]+=1;Fen.WeaponEquiped=2;Fen.Weapon[2]-=1;break;
						case 3:Fen.Weapon[Fen.WeaponEquiped]+=1;Fen.WeaponEquiped=3;Fen.Weapon[3]-=1;break;
						case 4:if (Fen.Perso!=3 && Fen.Perso!=4){
							Fen.Weapon[Fen.WeaponEquiped]+=1;Fen.WeaponEquiped=4;Fen.Weapon[4]-=1;
							}
							break;
						case 5:Fen.Weapon[Fen.WeaponEquiped]+=1;Fen.WeaponEquiped=5;Fen.Weapon[5]-=1;break;
						}
					}
				if (PanStat.ArmorSelected!=-1){
					switch(PanStat.Armor[PanStat.ArmorSelected]){
						case 0:Fen.Armor[Fen.ArmorEquiped]+=1;Fen.ArmorEquiped=0;Fen.Armor[0]-=1;break;
						case 1:Fen.Armor[Fen.ArmorEquiped]+=1;Fen.ArmorEquiped=1;Fen.Armor[1]-=1;break;
						}
					}
				if (PanStat.MagicSelected!=-1){
					switch(PanStat.MagicSelected){
						case 0:if (Fen.Magie>0 && Fen.Provisions<12 && PanStat.RavitaillementOn==0){
							PanStat.RavitaillementOn=1;
							}
							break;
						case 2:break;
						case 3:if (Fen.Magie>0 && Fen.Chance<Fen.ChanceTotalDepart){
							Fen.Magie-=1;
							Fen.Chance+=1;
							}
							break;
						case 5:if (Fen.Magie>=2 && Fen.Effects[2]==0){
							Fen.Magie-=2;
							Fen.Effects[2]=1;
							}
							break;
						case 7:if (Fen.Magie>=2 && Fen.Effects[1]==0){
							Fen.Magie-=2;
							Fen.Effects[1]=1;
							}
							break;
						case 11:if (Fen.Magie>=5 && Fen.Magic12Used<2){
							Fen.Magie-=5;
							PanStat.Magic12UseOn=1;
							Fen.Magic12Used+=1;
							}
							break;
						}
					}
				}

			}
		if (c==3) PanStat.WeaponSelectedOn=PanStat.WeaponSelected=PanStat.MagicSelectedOn=PanStat.MagicSelected=PanStat.ArmorSelectedOn=PanStat.ArmorSelected=PanStat.ObjectSelectedOn=PanStat.ObjectSelected=PanStat.TreasureSelected=PanStat.TreasureSelectedOn=-1;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisMotionPanStat implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){
		int X = event.getX();
		int Y = event.getY();

		if (130-5<X && X<130-5+130+5 && 580-20<Y && Y<580+5) Fen.SelectedObjectOn=1;

		


		else Fen.SelectedObjectOn=0;

		PanStat.X=X;
		PanStat.Y=Y;
		}
	public void mouseDragged(MouseEvent event){}
	}

class TouchePanStat implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){}
	public void keyReleased(KeyEvent event){}
	}
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PANSTAT End
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************

//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PANFIGHT
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************


class PanFight extends JPanel{

//ONWORK

	static int EnemyNumber=0;
	static int EnemyAliveNumber=0;
	static int [][] Enemy = new int [5][7];
	static String [] EnemyName = new String [5];
	static int EnemyAttackTurn=0;
	static int EnemyAttack=0;
	static int EnemyAttackOn=0;
	static int EnemyAttackValue=0;
	static int EnemyDisapearanceOval=0;
	static int EnemySameFdA=0;

	static int EnemyAttackType=0;
//*0=X
//*1=Y
//*2=Kind
//*3=Habileté
//*4=Endurance
//*5=Force d'Attaque
//*6=Damaged
	static int PersoFdA=0;
	static int PersoX=200;	
	static int PersoY=400;
	static int PersoAttack=0;
	static int PersoDamaged=0;
	static int EnemyTargetX=0;
	static int EnemyTargetY=0;
	static int EnemyDistanceSplited=0;
	static int EnemyDistanceTour=0;
	static int PersoAttackOn=0;
	static int PersoAttackValue=0;
	static int PersoAttackType=0;

	static int FireBallX=0;
	static int FireBallY=0;
	static int FireBallTour=0;
	
	static int AttackTimeOn=0;
	static int AttackOn=0;

	static int AnalyseOn=0;
	static int AnalyseEnemyNumber=0;

	static int FightMode=0;
	static int FightPhase=0;
	static int FightPhase2=0;
	static boolean FleePossibility=false;

	static int AssaultNumber=1; //Pour magie Main Vive
	static int PersoFdA2=0;
//**Spécificités
	static int Flag43ElveFirstAttackDone=0;
	static int Flag43PlayerMagicAttackUsedOn=0;

	static int Flag52BadLuck=0;
//**
	JButton gris = new JButton();
	public PanFight(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		this.setVisible(true);
		
		SourisPanFight souris = new SourisPanFight();
		SourisMotionPanFight sourisM = new SourisMotionPanFight();
		this.addMouseListener(souris);
		this.addMouseMotionListener(sourisM);

		Touche touche = new Touche();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		//gris.addKeyListener(touche); //#Fait bugger ?
		this.addKeyListener(touche);
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		g.setFont(PanTab.fontbold);
		g.setColor(Color.white);
		g.drawString("ENDURANCE="+String.valueOf(Fen.Endurance)+"/"+String.valueOf(Fen.EnduranceTotalDepart),5,25);
		g.drawString("HABILETE="+String.valueOf(Fen.Habilete)+"/"+String.valueOf(Fen.HabileteTotalDepart),5,50);
		g.drawString("CHANCE="+String.valueOf(Fen.Chance)+"/"+String.valueOf(Fen.ChanceTotalDepart),5,75);
		g.drawString("MAGIE="+String.valueOf(Fen.Magie),5,100);

		if (Fen.DiceThrow1==1){
			g.setColor(Color.white);
			g.fillRect(750-50, 400-50, 100, 100);
			g.setColor(Color.black);
			g.drawRect(750-50, 400-50, 100, 100);
			g.setFont(new Font("Arial",Font.BOLD,80));
			//g.drawString(String.valueOf(Fen.Dice),730, 440);
			if (Fen.Dice==1) g.fillOval(750-13, 400-13, 25, 25);
			else if (Fen.Dice==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==5){
				g.fillOval(750-13, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50,400-42+j*30, 25, 25);
						}
					}
				}
			if (Fen.DiceOn==0){
				new TimeDice().start();
				Fen.DiceOn=1;
				Fen.DiceTour+=1;
				if (Fen.DiceTour<100 && Fen.DiceTour%4==0) Fen.Dice=1+(int)(Math.random()*6);
				if (Fen.DiceTour==150){
					Fen.DiceTour=0;
					Fen.DiceThrow1=2;
					}
				}
			}
		if (Fen.DiceThrow2>=1 && Fen.DiceThrow2!=3){
			g.setColor(Color.white);
			g.fillRect(750-100, 400-50, 100, 100);
			g.fillRect(750, 400-50, 100, 100);
			g.setColor(Color.black);
			g.drawRect(750-100, 400-50, 100, 100);
			g.drawRect(750, 400-50, 100, 100);
			if (Fen.Dice==1) g.fillOval(750-13-50, 400-13, 25, 25);
			else if (Fen.Dice==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50-50,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30-50,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50-50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==5){
				g.fillOval(750-13-50, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50-50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50-50,400-42+j*30, 25, 25);
						}
					}
				}
			if (Fen.DiceThrow2==2){
				if (Fen.Dice2==1) g.fillOval(750-13+50, 400-13, 25, 25);
				else if (Fen.Dice2==2){
					for(int i=0; i<2; i++) g.fillOval(750-38+i*50+50,400-38+i*50, 25, 25);
					}
				else if (Fen.Dice2==3){
					for(int i=0; i<3; i++) g.fillOval(750-42+i*30+50,400-42+i*30, 25, 25);
					}
				else if (Fen.Dice2==4){
					for(int i=0; i<2; i++){
						for(int j=0; j<2; j++){
							g.fillOval(750-38+i*50+50,400-38+j*50, 25, 25);
							}
						}
					}
				else if (Fen.Dice2==5){
					g.fillOval(750-13+50, 400-13, 25, 25);
					for(int i=0; i<2; i++){
						for(int j=0; j<2; j++){
							g.fillOval(750-38+i*50+50,400-38+j*50, 25, 25);
							}
						}
					}
				else if (Fen.Dice2==6){
					for(int i=0; i<2; i++) {
						for(int j=0; j<3; j++){
							g.fillOval(750-38+i*50+50,400-42+j*30, 25, 25);
							}
						}
					}
				}
			if (Fen.DiceOn==0){
				new TimeDice().start();
				Fen.DiceOn=1;
				Fen.DiceTour+=1;
				if (Fen.DiceTour<100 && Fen.DiceTour%4==0){
					if (Fen.DiceThrow2==1 && Fen.DiceThrow2!=3) Fen.Dice=1+(int)(Math.random()*6);
					if (Fen.DiceThrow2==2 && Fen.DiceThrow2!=3) Fen.Dice2=1+(int)(Math.random()*6);
					}
				if (Fen.DiceTour==150 && Fen.DiceThrow2!=3){
					Fen.DiceTour=0;
					Fen.DiceThrow2+=1;
					}
				}
			}
		if (Fen.DiceThrow3>=1 && Fen.DiceThrow3!=4){
			g.setColor(Color.white);
			g.fillRect(750-150, 400-50, 100, 100);
			g.fillRect(750-50, 400-50, 100, 100);
			g.fillRect(750+50, 400-50, 100, 100);
			g.setColor(Color.black);
			g.drawRect(750-150, 400-50, 100, 100);
			g.drawRect(750-50, 400-50, 100, 100);
			g.drawRect(750+50, 400-50, 100, 100);
			if (Fen.Dice==1) g.fillOval(750-13-100, 400-13, 25, 25);
			else if (Fen.Dice==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50-100,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30-100,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50-100,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==5){
				g.fillOval(750-13-100, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50-100,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50-100,400-42+j*30, 25, 25);
						}
					}
				}

			if (Fen.Dice2==1) g.fillOval(750-13, 400-13, 25, 25);
			else if (Fen.Dice2==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice2==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice2==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice2==5){
				g.fillOval(750-13, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice2==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50,400-42+j*30, 25, 25);
						}
					}
				}
			if (Fen.Dice3==1) g.fillOval(750-13+100, 400-13, 25, 25);
			else if (Fen.Dice3==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50+100,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice3==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30+100,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice3==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50+100,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice3==5){
				g.fillOval(750-13+100, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50+100,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice3==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50+100,400-42+j*30, 25, 25);
						}
					}
				}
				
			if (Fen.DiceOn==0){
				new TimeDice().start();
				Fen.DiceOn=1;
				Fen.DiceTour+=1;
				if (Fen.DiceTour<100 && Fen.DiceTour%4==0){
					if (Fen.DiceThrow3==1) Fen.Dice=1+(int)(Math.random()*6);
					if (Fen.DiceThrow3==2) Fen.Dice2=1+(int)(Math.random()*6);
					if (Fen.DiceThrow3==3) Fen.Dice3=1+(int)(Math.random()*6);
					}
				if (Fen.DiceTour==150){
					Fen.DiceTour=0;
					Fen.DiceThrow3+=1;
					}
				}
			}
// Fin Dés           *********************************************************************
		Color colorD = new Color(255,64,64);

//FAIRE LE CAS égalité des FdA POUR LES COMBATS MULTIPLES ##################

//ONWORK
//**FIGHT
//*0=X
//*1=Y
//*2=Kind
//*3=Habileté
//*4=Endurance
//*5=Force d'Attaque
//*6=DamagedOn
		if (Fen.info==1) System.out.println("FightPhase="+FightPhase+"\n"+
						"FightMode="+FightMode+"\n"+
						"AssaultNumber="+AssaultNumber+"\n"+
						"dialog="+Fen.dialog+"\n"+
						"DiceThrow2="+Fen.DiceThrow2+"\n"+
						"PersoAttack="+PersoAttack+"\n"+
						"PersoAttackType="+PersoAttackType+"\n"+
						"PersoAttackValue="+PersoAttackValue+"\n"+
						"EnemyAttack="+EnemyAttack+"\n"+
						"EnemyAttackType="+EnemyAttackType+"\n"+
						"EnemyAttackValue="+EnemyAttackValue+"\n"+
						"EnemyAttackTurn="+EnemyAttackTurn+"\n"+
						"EnemyAliveNumber="+EnemyAliveNumber+"\n"+
						"TenterChanceOn="+Fen.TenterChanceOn+"\n"+
						"TenterChanceResult="+Fen.TenterChanceResult+"\n");
		if (FightPhase>=1){
			if (PersoDamaged==0) g.setColor(Color.white);
			else if (PersoDamaged==1) g.setColor(colorD);
			g.fillRect(PersoX-75, PersoY-100, 150, 200);
			
			for(int i=0; i<5; i++){
				if (Enemy[i][4]>0){
					if (Enemy[i][6]==1) g.setColor(colorD);
					else g.setColor(Color.gray);
					g.fillRect(Enemy[i][0]-25,Enemy[i][1]-25,50,50);
					}
				}

			if (PersoFdA!=0){
				g.setColor(Color.cyan);
				g.drawString("FdA="+String.valueOf(PersoFdA),150,720);
				}
			g.setColor(Color.red);
			if (FightMode==1){
				for(int i=0; i<5; i++){
					if (Enemy[i][5]!=0) g.drawString("FdA="+String.valueOf(Enemy[i][5]),985,720);
					}
				}
			else if (FightMode==2){
				for(int i=0; i<5; i++){
					if (Enemy[i][5]!=0) g.drawString("FdA="+String.valueOf(Enemy[i][5]),Enemy[i][0]-20,Enemy[i][1]+75);
					}
				}
//Spécificités
			if (AssaultNumber>1) Flag43ElveFirstAttackDone=1;
//Fin Spécificités			
//*********
			if (FightPhase==1){
				if (FleePossibility && AnalyseOn==0){
					Fen.ChoiceType3On=1;
					PanTab.Text1="Lancer un Assaut";
					if (EnemyNumber==1) PanTab.Text2="Analyser l'ennemi";
					else PanTab.Text2="Analyser les ennemis";
					PanTab.Text3="Prendre la fuite";
					switch(Fen.Choice){
						case 1:FightPhase=2;Fen.ChoiceReset();break;
						case 2:AnalyseOn=1;Fen.ChoiceReset();break;
						case 3:Fen.FightOn=0;Fen.ChoiceReset();
							if (Fen.Flag==43){ Fen.Flag=46;}
							break;
						}
					}
				else if (FleePossibility==false && AnalyseOn==0){
					Fen.ChoiceType2On=1;
					PanTab.Text1="Lancer un Assaut";
					if (EnemyNumber==1) PanTab.Text2="Analyser l'ennemi";
					else PanTab.Text2="Analyser les ennemis";
					switch(Fen.Choice){
						case 1:FightPhase=2;Fen.ChoiceReset();break;
						case 2:AnalyseOn=1;Fen.ChoiceReset();break;
						}
					}
				else if (AnalyseOn==1){
					if (EnemyNumber==1){
						AnalyseEnemyNumber=0;
						AnalyseOn=2;
						}
					else{
						if (EnemyNumber==2) Fen.ChoiceType2On=1;
						else if (EnemyNumber==3) Fen.ChoiceType3On=1;
						else if (EnemyNumber==4) Fen.ChoiceType4On=1;
						else if (EnemyNumber==5) Fen.ChoiceType5On=1;
						if (Enemy[0][4]>0) PanTab.Text1="Ennemi 1";
						if (Enemy[1][4]>0 && EnemyNumber>=2)  PanTab.Text2="Ennemi 2";
						if (Enemy[2][4]>0 && EnemyNumber>=3)  PanTab.Text3="Ennemi 3";
						if (Enemy[3][4]>0 && EnemyNumber>=4)  PanTab.Text4="Ennemi 4";
						if (Enemy[4][4]>0 && EnemyNumber==5)  PanTab.Text5="Ennemi 5";
						switch(Fen.Choice){
							case 1:if (Enemy[0][4]>0){
								AnalyseEnemyNumber=0;
								AnalyseOn=2;
								Fen.ChoiceReset();
								}
								break;
							case 2:if (Enemy[1][4]>0){
								AnalyseEnemyNumber=1;
								AnalyseOn=2;
								Fen.ChoiceReset();
								}
								break;
							case 3:if (Enemy[2][4]>0){
								AnalyseEnemyNumber=2;
								AnalyseOn=2;
								Fen.ChoiceReset();
								}
								break;
							case 4:if (Enemy[3][4]>0){
								AnalyseEnemyNumber=3;
								AnalyseOn=2;
								Fen.ChoiceReset();
								}
								break;
							case 5:if (Enemy[4][4]>0){
								AnalyseEnemyNumber=4;
								AnalyseOn=2;
								Fen.ChoiceReset();
								}
								break;
							}
						}
					}
				else if (AnalyseOn==2){
					Fen.dialogStart();
					PanTab.Jtext.setText(EnemyName[AnalyseEnemyNumber]+"\n"+"\n"+"HABILETE:"+String.valueOf(Enemy[AnalyseEnemyNumber][3])+"\t"+"\t"+"ENDURANCE:"+String.valueOf(Enemy[AnalyseEnemyNumber][4]));
					String t=EnemyNameSpecificDescription(Enemy[AnalyseEnemyNumber][2]);
					PanTab.Jtext.insert("\n"+"Info:"+t, PanTab.Jtext.getText().length());
					if (Fen.dialog==1){
						Fen.dialogEnd();
						AnalyseOn=0;
						}
					}
				}
//**Calcul FdA
			else if (FightPhase==2){
				if (FightPhase2==0 && Fen.Grimoire[6]==0 && Fen.Grimoire[8]==0 && Fen.Grimoire[9]==0){
					PersoAttackType=1;
					FightPhase2=2;
					}
				else if (FightPhase2==0){
					Fen.ChoiceType3On=1;
					PanTab.Text1="Attaquer avec votre arme";
					PanTab.Text2="Lancer un sort";
					PanTab.Text3="Retour";
					switch(Fen.Choice){
						case 1:PersoAttackType=1;FightPhase2=2;Fen.ChoiceReset();break;
						case 2:FightPhase2=1;Fen.ChoiceReset();break;
						case 3:FightPhase=1;Fen.ChoiceReset();break;
						}
					}
				else if (FightPhase2==1){
					Fen.ChoiceType4On=1;
					if (Fen.Grimoire[6]==1){
						PanTab.Text1="Boule de Feu";
						PanTab.Text1bis="cout = 2";
						}
					if (Fen.Grimoire[9]==1){
						PanTab.Text2="Foudre";
						PanTab.Text2bis="cout = 3";
						}
					if (Fen.Grimoire[8]==1){
						PanTab.Text3="Mort";
						PanTab.Text3bis="cout = 3";
						}
					PanTab.Text4="Retour";
					switch(Fen.Choice){
						case 1:if (Fen.Magie>=2 && Fen.Grimoire[6]==1){
							PersoAttackType=2;
							Fen.Magie-=2;
							Fen.ChoiceReset();
							}
							break;
						case 2:if (Fen.Magie>=3 && Fen.Grimoire[9]==1){
							PersoAttackType=3;
							Fen.Magie-=3;
							Fen.ChoiceReset();
							}
							break;
						case 3:if (Fen.Magie>=3 && Fen.Grimoire[8]==1){
							PersoAttackType=4;
							Fen.Magie-=3;
							Fen.ChoiceReset();
							}
							break;
						case 4:FightPhase=1;FightPhase2=0;Fen.ChoiceReset();break;
						}
					if (Fen.ChoiceType4On==0 && FightPhase!=1) FightPhase2=2;
					}
				else if (FightPhase2==2){
					Fen.dialogStart();
					if (Fen.dialog==0){
						Fen.DisplayText("Calcul de votre Force d'Attaque...",3);
						Fen.DiceThrow(2);
						if (Fen.DiceThrow2==3) Fen.dialog=1;
						}
					else if (Fen.dialog==1){
						if (Fen.DiceThrow2<3){
							Fen.Dice=1+(int)(Math.random()*6);
							Fen.Dice2=1+(int)(Math.random()*6);
							}
						Fen.DiceThrow2=0;
						PersoFdA=Fen.Dice+Fen.Dice2+Fen.Habilete;
//Spécificités
						if (Pan1.Flag26FightBadLuck==1) PersoFdA-=2;
						if (Fen.Flag==43 && PersoAttackType>1) PersoFdA-=2;
						if (Flag52BadLuck==1) PersoFdA-=2;
//Fin Spécificités
						Fen.dialogEnd();
						FightPhase=3;
						//System.out.println("bon");
						}
					}
				}
				
			else if (FightPhase==3 && FightMode==1){
				//System.out.println("bon3");
				Fen.dialogStart();
				if (Fen.dialog==0){
					Fen.DisplayText("Calcul de la Force d'Attaque de "+EnemyName[EnemyAttackTurn]+"...",3);
					Fen.DiceThrow(2);
					if (Fen.DiceThrow2==3) Fen.dialog=1;
					}
				else if (Fen.dialog==1){
					if (Fen.DiceThrow2<3){
						Fen.Dice=1+(int)(Math.random()*6);
						Fen.Dice2=1+(int)(Math.random()*6);
						}
					Fen.DiceThrow2=0;
					Enemy[EnemyAttackTurn][5]=Fen.Dice+Fen.Dice2+Enemy[EnemyAttackTurn][3];
					Fen.dialogEnd();
					FightPhase=4;
					}
				}
			else if (FightPhase==3 && FightMode==2){
				Fen.dialogStart();
				//EnemyAttackTurn=0;
				for(int i=0; i<EnemyNumber; i++){
					if (Enemy[i][4]>0 && Enemy[i][5]==0){
						EnemyAttackTurn=i;
						break;
						}
					}
				if (1==0 && Enemy[EnemyAttackTurn][4]<=0){
					for(int i=EnemyAttackTurn; i<EnemyNumber; i++){
						if (Enemy[i][4]<=0) EnemyAttackTurn+=1;
						else break;
						}
					}
				if (Fen.dialog==0){
					Fen.DisplayText("Calcul de la Force d'Attaque de "+EnemyName[EnemyAttackTurn]+"...",3);
					Fen.DiceThrow(2);
					if (Fen.DiceThrow2==3) Fen.dialog=1;
					}
				else if (Fen.dialog==1){
					if (Fen.DiceThrow2<3){
						Fen.Dice=1+(int)(Math.random()*6);
						Fen.Dice2=1+(int)(Math.random()*6);
						}
					Fen.DiceThrow2=0;
					Enemy[EnemyAttackTurn][5]=Fen.Dice+Fen.Dice2+Enemy[EnemyAttackTurn][3];
					Fen.dialogEnd();
					if (PersoFdA<Enemy[EnemyAttackTurn][5]){
						FightPhase=4;
						//EnemyAttackValue=2;
						//EnemyAttack=-1;
						//EnemyAttackTurn=-1;
						}
					else if (EnemyAttackTurn+1<EnemyNumber){
						//EnemyAttackTurn+=1;
						Fen.dialog=0;
						}
					else if (EnemyAttackTurn+1==EnemyNumber){
						for(int i=0; i<EnemyNumber; i++){
							if (Enemy[i][4]>0 && Enemy[i][5]==PersoFdA) EnemySameFdA+=1;
							}
						if (EnemySameFdA==EnemyAliveNumber) FightPhase=4;
						else{
							FightPhase=4;
							//PersoAttackValue=2;
							//PersoAttack=-2;
							//EnemyAttackTurn=-1;
							EnemySameFdA=0;
							}
						}						
					}
				}


			else if (FightPhase==4){  //&& FightMode==1){
				if (PersoFdA>Enemy[EnemyAttackTurn][5] && EnemySameFdA==0){
					if (PersoAttackType==1) PersoAttackValue=2;
					else if (PersoAttackType==2) PersoAttackValue=5;
					else if (PersoAttackType==3) PersoAttackValue=7;
					if (FightMode==1){
						EnemyTargetX=Enemy[EnemyAttackTurn][0];
						EnemyTargetY=Enemy[EnemyAttackTurn][1];
						PersoAttack=-1;
						}
					else if (FightMode==2){
						if (EnemyAliveNumber>1){
							EnemyAttackTurn=-1;
							PersoAttack=-2;
							}
						else if (EnemyAliveNumber==1){
							for(int i=0; i<EnemyNumber; i++){
								if (Enemy[i][4]>0){
									EnemyAttackTurn=i;
									EnemyTargetX=Enemy[EnemyAttackTurn][0];
									EnemyTargetY=Enemy[EnemyAttackTurn][1];
									break;
									}
								}
							if (PersoAttackType==1) PersoAttack=-1;
							else if (PersoAttackType>1) PersoAttack=1;
							}							
						//System.out.println("PASSER");
						}
					if (PersoAttackType>1 && FightMode==1) PersoAttack=1;
					FightPhase=5;

					//EnemyDistanceSplited=(int)(Fen.Distance(PersoX,PersoY,Enemy[EnemyAttackTurn][0],Enemy[EnemyAttackTurn][1])%50;
					//PersoFdA=0;
					//for(int i=0; i<5; i++) Enemy[i][5]=0;
					}
				else if (PersoFdA<Enemy[EnemyAttackTurn][5] && EnemySameFdA==0){
					FightPhase=6;
//Spécificités--------------------------------------
					//if (Fen.Flag==43 && Flag43ElveFirstAttackDone==0){
					if (Fen.Flag==43 && AssaultNumber==1){
						EnemyAttack=-2;
						EnemyAttackType=2;
						//System.out.println("PASSER1");
						}

					else{
						EnemyAttackType=1;
						EnemyAttack=-1;
						//System.out.println("PASSER2");
						if (Fen.Flag==48) Fen.Effects[3]=1;
						}
//Fin spécificités----------------------------------
					//System.out.println("PASSER3");
					switch(EnemyAttackType){
						case 1:EnemyAttackValue=2;break;
						case 2:EnemyAttackValue=4;break;
						}


					//PersoFdA=0;
					//for(int i=0; i<5; i++) Enemy[i][5]=0;
					}
				else{
					Fen.dialogStart();
					if (Fen.dialog==0){	
						PanTab.Jtext.setText("Vous esquivez chacun les coups echanges.");
						}
					else if (Fen.dialog==1){
						Fen.dialogEnd();
						FightPhase=1;
						FightPhase2=0;
						PersoFdA=0;
						for(int i=0; i<5; i++) Enemy[i][5]=0;
						EnemySameFdA=0;
						}
					}				
				}
//**Fin Calcul FdA
//**Perso Attack
			else if (FightPhase==5){
				if (PersoAttack==-2){
					Fen.Question="Qui attaquer ?";
					if (EnemyNumber==2) Fen.ChoiceType2On=1;
					else if (EnemyNumber==3) Fen.ChoiceType3On=1;
					else if (EnemyNumber==4) Fen.ChoiceType4On=1;
					else if (EnemyNumber==5) Fen.ChoiceType5On=1;
					if (Enemy[0][4]>0) PanTab.Text1="Ennemi 1";
					if (Enemy[1][4]>0 && EnemyNumber>=2)  PanTab.Text2="Ennemi 2";
					if (Enemy[2][4]>0 && EnemyNumber>=3)  PanTab.Text3="Ennemi 3";
					if (Enemy[3][4]>0 && EnemyNumber>=4)  PanTab.Text4="Ennemi 4";
					if (Enemy[4][4]>0 && EnemyNumber==5)  PanTab.Text5="Ennemi 5";
					switch(Fen.Choice){
						case 1:if (Enemy[0][4]>0){
							EnemyAttackTurn=0;
							EnemyTargetX=Enemy[0][0];
							EnemyTargetY=Enemy[0][1];
							Fen.ChoiceReset();
							}
							break;
						case 2:if (Enemy[1][4]>0){
							EnemyAttackTurn=1;
							EnemyTargetX=Enemy[1][0];
							EnemyTargetY=Enemy[1][1];
							Fen.ChoiceReset();
							}
							break;
						case 3:if (Enemy[2][4]>0){
							EnemyAttackTurn=2;
							EnemyTargetX=Enemy[2][0];
							EnemyTargetY=Enemy[2][1];
							Fen.ChoiceReset();
							}
							break;
						case 4:if (Enemy[3][4]>0){
							EnemyAttackTurn=3;
							EnemyTargetX=Enemy[3][0];
							EnemyTargetY=Enemy[3][1];
							Fen.ChoiceReset();
							}
							break;
						case 5:if (Enemy[4][4]>0){
							EnemyAttackTurn=4;
							EnemyTargetX=Enemy[4][0];
							EnemyTargetY=Enemy[4][1];
							Fen.ChoiceReset();
							}
							break;
						}
					if (EnemyAttackTurn!=-1){
						if (PersoAttackType==1) PersoAttack=-1;
						else if (PersoAttackType>1) PersoAttack=1;
						}
					}



				if (PersoAttack==-1 && Fen.Chance>1){
					Fen.Question("Tenter votre CHANCE pour aggraver les degats ingliges ?");
					switch(Fen.Choice){
						case 1:PersoAttack=0;Fen.ChoiceReset();break;
						case 2:PersoAttack=1;Fen.ChoiceReset();break;
						}
					}
				else if (PersoAttack==-1) PersoAttack=1;
				else if (PersoAttack==0){
					Fen.dialogStart();
					if (Fen.dialog==0){
						if (Fen.TenterChanceOn==0) Fen.TenterChanceOn=3;
						Fen.TenterChance();
						}
					else if (Fen.dialog==1){
						if (Fen.TenterChanceResult==1) PersoAttackValue=4;
						else if (Fen.TenterChanceResult==2) PersoAttackValue=1;
						PersoAttack=1;
						Fen.dialogEnd();
						//Fen.DiceThrow2=Fen.TenterChanceResult=0;
						Fen.TenterChanceEnd();
						}
					}
				else if (PersoAttack==1){
					if (1==0 && EnemyDistanceTour<EnemyDistanceSplited-25){
						EnemyDistanceTour+=1;
						PersoX+=2;
						PersoSlideLine(PersoX,PersoY,Enemy[EnemyAttackTurn][0],Enemy[EnemyAttackTurn][1]);
						}
					else{
						PersoAttack=2;
						}
					}
//Définir type d'image d'attaque
//en fonction de l'arme équipé
					
				else if (PersoAttack==2){
					if (PersoAttackType==1){
						if (AttackTimeOn==0){
							AttackTimeOn=1;
							new AttackTime().start();
							AttackOn+=1;
							}
						g.setColor(Color.lightGray);
						if (AttackOn==1){
							for(int i=0; i<5; i++) g.drawLine(EnemyTargetX-26,EnemyTargetY-26-i,EnemyTargetX-13,EnemyTargetY-13-i);
							}
						if (AttackOn==2){
							for(int i=0; i<5; i++) g.drawLine(EnemyTargetX-26,EnemyTargetY-26-i,EnemyTargetX,EnemyTargetY-i);
							}
						if (AttackOn==3){
							for(int i=0; i<5; i++) g.drawLine(EnemyTargetX-26,EnemyTargetY-26-i,EnemyTargetX+13,EnemyTargetY+13-i);
							Enemy[EnemyAttackTurn][6]=1;
							}
						if (AttackOn==4){
							for(int i=0; i<5; i++) g.drawLine(EnemyTargetX-26,EnemyTargetY-26-i,EnemyTargetX+26,EnemyTargetY+26-i);
							}
						if (AttackOn==5){
							for(int i=0; i<5; i++) g.drawLine(EnemyTargetX-26,EnemyTargetY-26-i,EnemyTargetX+26,EnemyTargetY+26-i);
							Enemy[EnemyAttackTurn][6]=0;
							AttackOn=0;
							PersoAttack=3;
							}
						}
					else if (PersoAttackType==2){
						if (AttackOn==0){
							FireBallX=PersoX;
							FireBallY=PersoY;
							FireBallTour=0;
							AttackOn=1;
							g.setColor(Color.red);
							}
						else if (AttackOn==1){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime10().start();
								FireBallTour+=1;
								}
							g.drawOval(FireBallX-100+FireBallTour, FireBallY-100+FireBallTour, 200-FireBallTour*2,200-FireBallTour*2);
							if (FireBallTour==100){
								FireBallTour=0;
								AttackOn=2;
								}
							}
						else if (AttackOn==2){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime10().start();
								FireBallTour+=1;
								}
							for(int i=0; i<FireBallTour; i++){
								g.setColor(new Color(255-80+i*2,0,0));
								g.drawOval(FireBallX-i,FireBallY-i, i*2, i*2);
								}
							if (FireBallTour==40) AttackOn=3;
							}
						else if (AttackOn==3){
							//if (AttackTimeOn==0){
								//AttackTimeOn=1;
								//new AttackTime().start();
								FireBallX+=7;
								//}
							for(int i=0; i<FireBallTour; i++){
								g.setColor(new Color(255-80+i*2,0,0));
								g.drawOval(FireBallX-i,FireBallY-i, i*2, i*2);
								}
							FireBallY=Fen.YSlideLine(FireBallX, PersoX, PersoY, EnemyTargetX, EnemyTargetY);
							//if (FireBallX==EnemyTargetX){
							if (EnemyTargetX-3<=FireBallX && FireBallX<=EnemyTargetX+3){
								AttackOn=4;
								FireBallTour=0;
								}
							}
						else if (AttackOn==4){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime10().start();
								FireBallTour+=1;
								}
							for(int i=0; i<40-FireBallTour; i++){
								g.setColor(new Color(255-80+i*2,0,0));
								g.drawOval(FireBallX-i-FireBallTour*3,FireBallY-i-FireBallTour*3, (i+FireBallTour*3)*2, (i+FireBallTour*3)*2);
								}
							if (FireBallTour==20) Enemy[EnemyAttackTurn][6]=1;
							if (FireBallTour==40){
								FireBallTour=FireBallX=FireBallY=0;
								Enemy[EnemyAttackTurn][6]=0;
								PersoAttack=3;
								}
							}
						}
					else if (PersoAttackType==3){
						if (AttackOn==0){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime10().start();
								FireBallTour+=1;
								}
							g.setColor(Color.yellow);
							g.drawOval(PersoX-100+FireBallTour, PersoY-100+FireBallTour, 200-FireBallTour*2,200-FireBallTour*2);
							if (FireBallTour==100){
								FireBallTour=0;
								AttackOn=1;
								}
							}
						else if (AttackOn==1){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime().start();
								FireBallTour+=1;
								}
							g.setColor(Color.white);
							g.fillRect(0,0,this.getWidth(),this.getHeight());
							if (FireBallTour==2){
								FireBallTour=0;
								AttackOn=2;
								}
							}
						else if (AttackOn>=2){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime().start();
								AttackOn+=1;
								}
							FireBallTour+=1;
							FireBallX=(int)(Math.pow(-1,FireBallTour));
							Enemy[EnemyAttackTurn][6]=1;
							try{
								Image Eclair = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Aventure Zorca/Images/EclairReversed.png"));
								g.drawImage(Eclair, EnemyTargetX+FireBallX, EnemyTargetY, -100, -EnemyTargetY, this);
								} catch (IOException e){}
							if (AttackOn==6){
								FireBallTour=FireBallX=0;
								Enemy[EnemyAttackTurn][6]=0;
								PersoAttack=3;
								}
							}
						}
					else if (PersoAttackType==4){
						int EX = Enemy[EnemyAttackTurn][0];
						int EY = Enemy[EnemyAttackTurn][1];
						if (AttackTimeOn==0){
							AttackTimeOn=1;
							if (AttackOn<25){
								new AttackTime().start();
								AttackOn+=1;
								}
							else{
								new AttackTime10().start();
								FireBallTour+=1;
								}
							}
						g.setColor(new Color(128,0,128));
						if (AttackOn<5) g.drawOval(EX-100,EY-100,200,200);
						else if (AttackOn-5<5){
							g.drawOval(EX-100,EY-100,200,200);
							g.drawOval(EX-75,EY-75,150,150);
							}
						else if (AttackOn-10<5){
							g.drawOval(EX-100,EY-100,200,200);
							g.drawOval(EX-75,EY-75,150,150);
							g.drawOval(EX-50,EY-50,100,100);
							}
						else if (AttackOn-15<5){
							g.drawOval(EX-100,EY-100,200,200);
							g.drawOval(EX-75,EY-75,150,150);
							g.drawOval(EX-50,EY-50,100,100);
							g.drawOval(EX-25,EY-25,50,50);
							}
						else if (AttackOn-20<5){
							g.drawOval(EX-100,EY-100,200,200);
							g.drawOval(EX-75,EY-75,150,150);
							g.drawOval(EX-50,EY-50,100,100);
							g.drawOval(EX-25,EY-25,50,50);
							g.setColor(Color.red);
							g.fillOval(EX-10,EY-10,20,20);
							}
						else if (AttackOn>=25){
							g.drawOval(EX-100,EY-100,200,200);
							g.drawOval(EX-75,EY-75,150,150);
							g.drawOval(EX-50,EY-50,100,100);
							g.drawOval(EX-25,EY-25,50,50);
							g.setColor(Color.red);
							g.fillOval(EX-FireBallTour,EY-FireBallTour,FireBallTour*2,FireBallTour*2);
							Enemy[EnemyAttackTurn][6]=1;
							}
						if (FireBallTour==100){
							FireBallTour=AttackOn=0;
							PersoAttack=3;
							Enemy[EnemyAttackTurn][6]=0;
							PersoAttackValue=Enemy[EnemyAttackTurn][4];
							}
						}
					}
				else if (PersoAttack==3){
					if (AttackTimeOn==0){
						AttackTimeOn=1;
						new AttackTime().start();
						AttackOn+=1;
						}
					g.setColor(Color.red);
					g.setFont(new Font("Sylfaen",Font.BOLD,35));
					g.drawString(String.valueOf(PersoAttackValue),EnemyTargetX,EnemyTargetY);
					if (AttackOn==10){
						AttackOn=0;
						Enemy[EnemyAttackTurn][4]-=PersoAttackValue;
						if (Enemy[EnemyAttackTurn][4]>0){
							FightPhase=1;
							FightPhase2=0;
							}
						else PersoAttack=4;
						AssaultNumber+=1;
						PersoFdA=0;
						for(int i=0; i<5; i++) Enemy[i][5]=0;
						EnemyAttackTurn=0;
						}
					}
				else if (PersoAttack==4){
					for(int i=0; i<13-PersoAttackOn; i++){
						if (i%2==0) g.setColor(Color.red);
						else g.setColor(Color.magenta);
						g.drawOval(EnemyTargetX-EnemyDisapearanceOval-i, EnemyTargetY-EnemyDisapearanceOval-i, EnemyDisapearanceOval*2+i*2,EnemyDisapearanceOval*2+i*2);
						}
					EnemyDisapearanceOval+=2;
					if (EnemyDisapearanceOval%20==0) PersoAttackOn+=1;
					if (EnemyDisapearanceOval==260){
						EnemyDisapearanceOval=0;
						PersoAttackOn=0;
						EnemyAliveNumber-=1;
						if (EnemyAliveNumber>0){
							FightPhase=1;
							FightPhase2=0;
							EnemyAttackTurn=0;
							}
						else FightPhase=7;
						}
					}
				}

//**Fin Perso Attack
//**Enemy Attack Phases
			else if (FightPhase==6){
				if (EnemyAttack==-2 && Fen.dialog==-1){
					Fen.dialogStart();
					Fen.dialog=1;
					}
				else if (EnemyAttack==-2 && Fen.dialog==1){
					if (EnemyAttackType==2) Fen.DisplayText(EnemyName[EnemyAttackTurn]+" lance une Boule de Feu qui vous embrase de la tete au pied");
					}
				else if (EnemyAttack==-2 && Fen.dialog==2){
					Fen.dialogEnd();
					EnemyAttack=1;
					}
				else if (EnemyAttack==-1 && Fen.Chance>1){
					Fen.Question("Tenter votre CHANCE pour diminuer les degats subis ?");
					switch(Fen.Choice){
						case 1:EnemyAttack=0;Fen.ChoiceReset();break;
						case 2:EnemyAttack=1;Fen.ChoiceReset();break;
						}
					}
				else if (EnemyAttack==-1) EnemyAttack=1;
				else if (EnemyAttack==0){
					Fen.dialogStart();
					if (Fen.dialog==0){
						if (Fen.TenterChanceOn==0) Fen.TenterChanceOn=3;
						Fen.TenterChance();
						}
					else if (Fen.dialog==1){
						if (Fen.TenterChanceResult==1) EnemyAttackValue=1;
						else if (Fen.TenterChanceResult==2) EnemyAttackValue=3;
						EnemyAttack=1;
						Fen.dialogEnd();
						Fen.TenterChanceEnd();
						}
					}
				else if (EnemyAttack==1){
					if (EnemyAttackType==1){
						if (AttackTimeOn==0){
							AttackTimeOn=1;
							new AttackTime().start();
							AttackOn+=1;
							}
						g.setColor(Color.gray);
						if (AttackOn==1){
							for(int i=0; i<5; i++) g.drawLine(PersoX-26,PersoY-26-i,PersoX-13,PersoY-13-i);
							}
						if (AttackOn==2){
							for(int i=0; i<5; i++) g.drawLine(PersoX-26,PersoY-26-i,PersoX,PersoY-i);
							}
						if (AttackOn==3){
							for(int i=0; i<5; i++) g.drawLine(PersoX-26,PersoY-26-i,PersoX+13,PersoY+13-i);
							PersoDamaged=1;
							}
						if (AttackOn==4){
							for(int i=0; i<5; i++) g.drawLine(PersoX-26,PersoY-26-i,PersoX+26,PersoY+26-i);
							}
						if (AttackOn==5){
							for(int i=0; i<5; i++) g.drawLine(PersoX-26,PersoY-26-i,PersoX+26,PersoY+26-i);
							PersoDamaged=0;
							AttackOn=0;
							EnemyAttack=2;
							}
						}
					else if (EnemyAttackType==2){
						if (AttackOn==0){
							FireBallX=Enemy[EnemyAttackTurn][0];
							FireBallY=Enemy[EnemyAttackTurn][1];
							FireBallTour=0;
							AttackOn=1;
							g.setColor(Color.red);
							}
						else if (AttackOn==1){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime10().start();
								FireBallTour+=1;
								}
							g.drawOval(FireBallX-100+FireBallTour, FireBallY-100+FireBallTour, 200-FireBallTour*2,200-FireBallTour*2);
							if (FireBallTour==100){
								FireBallTour=0;
								AttackOn=2;
								}
							}
						else if (AttackOn==2){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime10().start();
								FireBallTour+=1;
								}
							for(int i=0; i<FireBallTour; i++){
								g.setColor(new Color(255-80+i*2,0,0));
								g.drawOval(FireBallX-i,FireBallY-i, i*2, i*2);
								}
							if (FireBallTour==40) AttackOn=3;
							}
						else if (AttackOn==3){
							//if (AttackTimeOn==0){
								//AttackTimeOn=1;
								//new AttackTime10().start();
								FireBallX-=7;
								//}
							for(int i=0; i<FireBallTour; i++){
								g.setColor(new Color(255-80+i*2,0,0));
								g.drawOval(FireBallX-i,FireBallY-i, i*2, i*2);
								}
							FireBallY=Fen.YSlideLine(FireBallX, PersoX, PersoY, Enemy[EnemyAttackTurn][0], Enemy[EnemyAttackTurn][1]);
							//if (FireBallX==PersoX){
							if (PersoX-3<=FireBallX && FireBallX<=PersoX+3){
								AttackOn=4;
								FireBallTour=0;
								}
							}
						else if (AttackOn==4){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime10().start();
								FireBallTour+=1;
								}
							for(int i=0; i<40-FireBallTour; i++){
								g.setColor(new Color(255-80+i*2,0,0));
								g.drawOval(FireBallX-i-FireBallTour*3,FireBallY-i-FireBallTour*3, (i+FireBallTour*3)*2, (i+FireBallTour*3)*2);
								}
							if (FireBallTour==20) PersoDamaged=1;
							if (FireBallTour==40){
								FireBallTour=FireBallX=FireBallY=0;
								PersoDamaged=0;
								EnemyAttack=2;
								EnemyAttackValue=4;
								}
							}
						}
					else if (EnemyAttackType==3){
						if (AttackOn==0){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime().start();
								FireBallTour+=1;
								}
							g.setColor(Color.yellow);
							g.drawOval(Enemy[EnemyAttackTurn][0]-100+FireBallTour, Enemy[EnemyAttackTurn][1]-100+FireBallTour, 200-FireBallTour*2,200-FireBallTour*2);
							if (FireBallTour==100){
								FireBallTour=0;
								AttackOn=1;
								}
							}
						else if (AttackOn==1){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime().start();
								FireBallTour+=1;
								}
							g.setColor(Color.white);
							g.fillRect(0,0,this.getWidth(),this.getHeight());
							if (FireBallTour==2){
								FireBallTour=0;
								AttackOn=2;
								}
							}
						else if (AttackOn>=2){
							if (AttackTimeOn==0){
								AttackTimeOn=1;
								new AttackTime().start();
								AttackOn+=1;
								}
							FireBallTour+=1;
							FireBallX=(int)(Math.pow(-1,FireBallTour));
							PersoDamaged=1;
							try{
								Image Eclair = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Aventure Zorca/Images/EclairReversed.png"));
								g.drawImage(Eclair, PersoX+FireBallX, PersoY, -100, -PersoY, this);
								} catch (IOException e){}
							if (AttackOn==6){
								FireBallTour=FireBallX=0;
								PersoDamaged=0;
								EnemyAttackValue=6;
								EnemyAttack=2;
								}
							}
						}
					}
				else if (EnemyAttack==2){
					if (AttackTimeOn==0){
						AttackTimeOn=1;
						new AttackTime().start();
						AttackOn+=1;
						}
					g.setColor(Color.red);
					g.setFont(new Font("Sylfaen",Font.BOLD,35));
					g.drawString(String.valueOf(EnemyAttackValue),PersoX,PersoY);
					if (AttackOn==10){
						AttackOn=0;
//----Résistances
						if (Fen.Effects[1]==1 && EnemyAttackType>=2){
							EnemyAttackValue=0;
							Fen.Effects[1]=0;
							}
						else if (Fen.Effects[0]==1 && (EnemyAttackType==2)){
							EnemyAttackValue=(int)(EnemyAttackValue/2);
							Fen.Effects[0]=0;
							}


//----Fin Résistances
						Fen.Endurance-=EnemyAttackValue;
						if (Fen.Endurance>0){
							FightPhase=1;
							FightPhase2=0;
							AssaultNumber+=1;
							}
						else FightPhase=8;
						PersoFdA=0;
						for(int i=0; i<5; i++) Enemy[i][5]=0;
						EnemyAttackTurn=0;
						//System.out.println("TEST2");
						}
					}

				}
			else if (FightPhase==6 && FightMode==2){}
//**Fin Enemy Attack
			else if (FightPhase==7){
				Fen.dialogStart();
				if (Fen.dialog==0){
					Fen.DisplayText("Vous avez remporte le combat !",3);
					}
				else{
					Fen.dialogEnd();
					FightPhase=0;
					Fen.FightOn=0;
					Fen.FightEnded=1;
					}
				}

			else if (FightPhase==8){} //Défaite
			}
		
//*0=X
//*1=Y
//*2=Kind
//*3=Habileté
//*4=Endurance
//*5=Force d'Attaque
//*6=Damaged

//**End FIGHT

//**FLAG
//AVOIR UNE VARIABLE Decor QUI DONNE LE DECOR DES COMBATS EN FONCTION DU FLAG
		if (FightPhase==0){
			FleePossibility=false;
			for(int i=0; i<5; i++){
				EnemyName[i]="";
				for(int j=0; j<7; j++) Enemy[i][j]=0;
				}
			if (Fen.Flag==11){
				FightMode=1;
				EnemyNumber=EnemyAliveNumber=1;
				Enemy[0][2]=1;	
				}
			else if (Fen.Flag==13){
				FightMode=2;
				EnemyNumber=EnemyAliveNumber=3;
				Enemy[0][2]=2;
				Enemy[1][2]=2;
				Enemy[2][2]=3;
				}
			else if (Fen.Flag==19){
				FightMode=2;
				EnemyNumber=EnemyAliveNumber=2;
				Enemy[0][2]=2;
				Enemy[1][2]=2;
				}
			else if (Fen.Flag==26){
				FightMode=2;
				EnemyNumber=EnemyAliveNumber=2;
				Enemy[0][2]=4;
				Enemy[1][2]=4;
				}
			else if (Fen.Flag==43){
				FightMode=1;
				EnemyNumber=EnemyAliveNumber=1;
				Enemy[0][2]=5;
				FleePossibility=true;
				}
			else if (Fen.Flag==48){
				FightMode=1;
				EnemyNumber=EnemyAliveNumber=1;
				Enemy[0][2]=6;
				}
			else if (Fen.Flag==52){
				FightMode=1;
				EnemyNumber=EnemyAliveNumber=1;
				Enemy[0][2]=7;
				}


			for(int i=0; i<EnemyNumber; i++){
				switch(Enemy[i][2]){
					case 1:Enemy[i][3]=10;Enemy[i][4]=16;EnemyName[i]=EnemyName(1);break;
					case 2:Enemy[i][3]=7;Enemy[i][4]=6;EnemyName[i]=EnemyName(2);break;
					case 3:Enemy[i][3]=8;Enemy[i][4]=9;EnemyName[i]=EnemyName(3);break;
					case 4:Enemy[i][3]=6;Enemy[i][4]=6;EnemyName[i]=EnemyName(4);break;
					case 5:Enemy[i][3]=8;Enemy[i][4]=10;EnemyName[i]=EnemyName(5);break;
					case 6:Enemy[i][3]=7;Enemy[i][4]=8;EnemyName[i]=EnemyName(6);break;
					case 7:Enemy[i][3]=7;Enemy[i][4]=9;EnemyName[i]=EnemyName(7);break;
					
					}
				}
			if (EnemyNumber==1){
				Enemy[0][0]=1000;
				Enemy[0][1]=400;
				}
			else if (EnemyNumber==2){
				Enemy[0][0]=800;
				Enemy[0][1]=300;
				Enemy[1][0]=1000;
				Enemy[1][1]=500;
				}
			else if (EnemyNumber==3){
				Enemy[0][0]=800;
				Enemy[0][1]=250;
				Enemy[1][0]=1000;
				Enemy[1][1]=450;
				Enemy[2][0]=1200;
				Enemy[2][1]=650;
				}
			else if (EnemyNumber==4){
				Enemy[0][0]=800;
				Enemy[0][1]=300;
				Enemy[1][0]=900;
				Enemy[1][1]=600;
				Enemy[2][0]=1000;
				Enemy[2][1]=300;
				Enemy[3][0]=1100;
				Enemy[3][1]=600;
				}
			else if (EnemyNumber==5){
				Enemy[0][0]=900;
				Enemy[0][1]=250;
				Enemy[1][0]=1000;
				Enemy[1][1]=350;
				Enemy[2][0]=1100;
				Enemy[2][1]=450;
				Enemy[3][0]=1000;
				Enemy[3][1]=550;
				Enemy[4][0]=900;
				Enemy[4][1]=650;
				}
			FightPhase=1;
			}
//ONWORK

//**End FLAG

		if (Fen.Question!=""){
			g.setColor(Color.black);
			g.fillRect(750-(int)(Fen.Question.length()/2)*15-5, 780-30, ((int)(Fen.Question.length()/2)*15)*2, 38);
			g.setColor(Color.white);
			g.drawRect(750-(int)(Fen.Question.length()/2)*15-5, 780-30, ((int)(Fen.Question.length()/2)*15)*2, 38);
			g.setFont(PanTab.fontbold);
			g.drawString(Fen.Question,750-(int)(Fen.Question.length()/2)*15,780);
			}
		if (Fen.TenterChanceOn==1 || Fen.TenterChanceOn==2){
			if (Fen.TenterChanceOn==1) g.setColor(Color.black);
			else if (Fen.TenterChanceOn==2) g.setColor(Color.cyan);
			g.fillRect(750-190-5, 400-30, 380+10, 60);
			g.setColor(Color.yellow);
			g.drawRect(750-190-5, 400-30, 380+10, 60);
			g.setFont(new Font("Sylfaen", Font.BOLD, 40));
			g.setColor(Color.white);
			g.drawString("Tentez votre Chance",750-190, 410);
			}
		try{
			Thread.sleep(10);
			} catch (InterruptedException e){}

		}
//***********************************************************************************
	public static String EnemyName(int kind){
		String Name="";
		switch(kind){
			case 1:Name="VARAN DES BRUMES";break;
			case 2:Name="GRAND ORQUE";break;
			case 3:Name="ORQUE MUTANT";break;
			case 4:Name="GOBELIN";break;
			case 5:Name="ELFE NOIR";break;
			case 6:Name="HORDE DE RATS";break;
			case 7:Name="HETERO-LEZARD";break;
			}
		return Name;
		}
	public static String EnemyNameSpecificDescription(int kind){
		String D = "";
		if (kind==6) D = "La morsure vous affectera de la peste bubonique.";

		else D="Rien de particulier";

		return D;
		}


	public static void PersoSlideLine(int X1, int Y1, int X2, int Y2){
		int a=-(Y2-Y1);
		int b=X2-X1;
		int c=-a*X1-b*Y1;
		PersoY=(int)((-a*PersoX-c)/b);
		}
	public static void EnemySlideLine(int EnemyNumber, int X1, int Y1, int X2, int Y2){
		int a=-(Y2-Y1);
		int b=X2-X1;
		int c=-a*X1-b*Y1;
		Enemy[EnemyNumber][1]=(int)((-a*Enemy[EnemyNumber][0]-c)/b);
		}


	}
class AttackTime extends Thread{
	public void run(){
		try{
			sleep(100);
			} catch (InterruptedException e){}
		PanFight.AttackTimeOn=0;
		}
	}
class AttackTime10 extends Thread{
	public void run(){
		try{
			sleep(10);
			} catch (InterruptedException e){}
		PanFight.AttackTimeOn=0;
		}
	}



class SourisPanFight implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1) System.out.println("X="+X+"\t"+"Y="+Y);
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisMotionPanFight implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){
		int X = event.getX();
		int Y = event.getY();
		}
	public void mouseDragged(MouseEvent event){}
	}
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PANFIGHT End
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************

//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PAN0
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
class Pan0 extends JPanel{
	
	JTextArea T = new JTextArea();
	public Pan0(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));

		SourisPan0 souris0 = new SourisPan0();
		SourisMotionPan0 sourisM0 = new SourisMotionPan0();
		this.addMouseListener(souris0);
		this.addMouseMotionListener(sourisM0);	
		
		JButton gris = new JButton();
		Touche touche = new Touche();
		gris.setPreferredSize(new Dimension(0,0));
		gris.setVisible(true);
		gris.addKeyListener(touche);
		this.addKeyListener(touche);
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0,0,this.getWidth(), this.getHeight());
				
//                  *********************************************************************
		if (Fen.Perso==0){//##############################

		if (Fen.StartGame<=1){
		g.setColor(Color.white);
		for(int i=0; i<4; i++) g.drawRect(186-75+i*375, 200-100, 150, 200);
		for(int i=0; i<3; i++) g.drawLine(375+i*375, 0, 375+i*375, 800);
		g.setFont(new Font("Sylfaen",Font.BOLD, 30));
		g.drawString("ANVAR, le Barbare",10+40, 50);
		g.drawString("BRAXUS, le Guerrier",385+20, 50);
		g.drawString("RABLAIX, le Nain",760+40, 50);
		g.drawString("SALLAZAR, le Magicien",1135, 50);
		PanTab.Text1="ANVAR";
		PanTab.Text2="BRAXUS";
		PanTab.Text3="RABLAIX";
		PanTab.Text4="SALLAZAR";
			}//##StartGame
		if (Fen.Choice!=0){
			if (Fen.StartGame==0) g.setColor(Color.black);
			else g.setColor(Color.yellow);
			g.fillRect(750-180, 750, 360, 40);
			g.setColor(Color.red);
			g.drawRect(750-180, 750, 360, 40);
			g.setColor(Color.white);
			g.drawString("Commencer l'aventure",750-170, 780);
			}
		g.setColor(Color.white);
		Font fontplain = new Font("Sylfaen",Font.PLAIN, 15);
		Font fontbold = new Font("Sylfaen",Font.BOLD, 15);
		g.setFont(fontplain);
		if (Fen.Choice==1){
			g.drawString("Comme tous les natifs de Barbarie, vous etes d'une force et ",5,316);
			g.drawString("d'une resistance exceptionnelles. Depuis votre tendre",5,316+14*1);
			g.drawString("enfance, vous menez une vie rude, chassant et voyageant",5,316+14*2);
			g.drawString("sans relache a travers collines et montagnes. Vous etes fort",5,316+14*3);
			g.drawString("habile au combat et vous n'avez rien a envier aux plus",5,316+14*4);
			g.drawString("valeureux guerriers d'Amarillie. Une seule chose vous fait",5,316+14*5);
			g.drawString("defaut: vous n'etes guere verse dans l'art de la Magie. Mais",5,316+14*6);
			g.drawString("a dire vrai, vous n'en avez cure: vous n'etes pas du genre a",5,316+14*7);
			g.drawString("passer le plus clair de votre existence la tete plongee dans",5,316+14*8);
			g.drawString("les livres et les grimoires-cela est bon pour les freluquets",5,316+14*9);
			g.drawString("Pour un colosse de votre trempe, rien ne vaut une bonne",5,316+14*10);
			g.drawString("baguarre pour resoudre les problemes !",5,316+14*11);

			g.setFont(fontbold);
			g.drawString("Avantages:",5,490);
			g.setFont(fontplain);
			g.drawString("Tres vigilant, ne peut se laisser prendre par surprise",5,505);
			g.setFont(fontbold);
			g.drawString("Inconvenients:",5,525);
			g.setFont(fontplain);
			g.drawString("-Incapable de porter une armure a plates",5,540);
			g.drawString("-le port d'une cotte de mailles n'ajoure aucun point bonus",5,540+15*1);
			g.drawString("  a la Force d'Attaque (FdA)",5,540+15*2);
			g.drawString("-le maniement d'une arbalete diminue de 2 la FdA",5,540+15*3);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("HABILETE:",5,620);
			g.setColor(Color.white);
			g.drawString("Total de depart =",5,635);
			g.setFont(fontplain);
			g.drawString("1 de + 6",130,635);
			//if (Fen.info==1) System.out.println(Character.getType('e'));
			//if (Fen.info==1) System.out.println("bon");

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("ENDURANCE:",5,655);
			g.setColor(Color.white);
			g.drawString("Total de depart =",5,670);
			g.setFont(fontplain);
			g.drawString("1 de + 18",130,670);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("CHANCE:",5,690);
			g.setColor(Color.white);
			g.drawString("Total de depart =",5,705);
			g.setFont(fontplain);
			g.drawString("1 de + 4",130,705);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("MAGIE:",5,725);
			g.setColor(Color.white);
			g.drawString("Total de depart =",5,740);
			g.setFont(fontplain);
			g.drawString("1",130,740);
			}
		if (Fen.Choice==2){
//			g.drawString("Comme tous les natifs de Barbarie, vous etes d'une force et ",380,316);
			g.drawString("Vous comptez parmi vos ancetres un illustre roi d'Amarillie",380,316+14*0);
			g.drawString("qui, outre son nom, vous a transmis en heritage toutes les",380,316+14*1);
			g.drawString("qualites qui font de vous un homme d'honneur. Vos talents",380,316+14*2);
			g.drawString("de guerrier ne sont plus a prouver et, si votre force",380,316+14*3);
			g.drawString("physique n'egale pas celle d'un Barbare, vous beneficier",380,316+14*4);
			g.drawString("en revanche d'une meilleur connaissance dans le domaine de",380,316+14*5);
			g.drawString("la Magie. Parmi tous les aventuriers, vous etes sans doute",380,316+14*6);
			g.drawString("le plus complet, capable de manier l'epee, l'arc et le",380,316+14*7);
			g.drawString("poignard avec autant de dexterite que les armes magiques de",380,316+14*8);
			g.drawString("toute sorte",380,316+14*9);

			g.setFont(fontbold);
			g.drawString("Avantages:",380,490);
			g.setFont(fontplain);
			g.drawString("Peut manier n'importe quel type d'arme",380,505);
			g.setFont(fontbold);
			g.drawString("Inconvenients:",380,525);
			g.setFont(fontplain);
			g.drawString("Aucun",380,540);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("HABILETE:",380,620);
			g.setColor(Color.white);
			g.drawString("Total de depart =",380,635);
			g.setFont(fontplain);
			g.drawString("1 de + 6",505,635);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("ENDURANCE:",380,655);
			g.setColor(Color.white);
			g.drawString("Total de depart =",380,670);
			g.setFont(fontplain);
			g.drawString("2 des + 12",505,670);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("CHANCE:",380,690);
			g.setColor(Color.white);
			g.drawString("Total de depart =",380,705);
			g.setFont(fontplain);
			g.drawString("1 de + 3",505,705);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("MAGIE:",380,725);
			g.setColor(Color.white);
			g.drawString("Total de depart =",380,740);
			g.setFont(fontplain);
			g.drawString("3",505,740);
			}
		if (Fen.Choice==3){
//			g.drawString("Comme tous les natifs de Barbarie, vous etes d'une force et ",380,316);
			g.drawString("Generalement d'humeur maussade et renferme, vous merite",754,316+14*0);
			g.drawString("bien le surnom de Rablaix-la-Teigne, qui reflete egalement",754,316+14*1);
			g.drawString("votre temperament belliqueux. Outre votre audace et votre",754,316+14*2);
			g.drawString("vaillance, vous avez le don, comme bon nombre de Nains",754,316+14*3);
			g.drawString("originaires de Grundie, d'avoir souvent la chance de votre",754,316+14*4);
			g.drawString(" cote. Lorsque vous serez amena a explorer des passages",754,316+14*5);
			g.drawString("souterrains, vous beneficierez de certains avantages par",754,316+14*6);
			g.drawString("rapport aux humains de taille normale. En cas de besoin,",754,316+14*7);
			g.drawString("recourir a la Magie mais, la plupart du temps, vous preferez",754,316+14*8);
			g.drawString("vous pouvez aussi vous fier aux vertus expeditives",754,316+14*9);
			g.drawString("de votre hache !",754,316+14*10);

			g.setFont(fontbold);
			g.drawString("Avantages:",754,490);
			g.setFont(fontplain);
			g.drawString("Votre FdA augmente de 2 en cas de combat avec un",754,505);
			g.drawString("monstre de pierre",754,520);
			g.setFont(fontbold);
			g.drawString("Inconvenients:",754,545);
			g.setFont(fontplain);
			g.drawString("Incapable d'utiliser un arc ou une arme a deux mains",754,560);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("HABILETE:",754,620);
			g.setColor(Color.white);
			g.drawString("Total de depart =",754,635);
			g.setFont(fontplain);
			g.drawString("1 de + 5",879,635);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("ENDURANCE:",754,655);
			g.setColor(Color.white);
			g.drawString("Total de depart =",754,670);
			g.setFont(fontplain);
			g.drawString("2 des + 12",754+125,670);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("CHANCE:",754,690);
			g.setColor(Color.white);
			g.drawString("Total de depart =",754,705);
			g.setFont(fontplain);
			g.drawString("1 de + 5",754+125,705);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("MAGIE:",754,725);
			g.setColor(Color.white);
			g.drawString("Total de depart =",754,740);
			g.setFont(fontplain);
			g.drawString("2",754+125,740);
			}
		if (Fen.Choice==4){
//			g.drawString("Comme tous les natifs de Barbarie, vous etes d'une force et ",380,316);
			g.drawString("On ne chantera jamais les louanges de votre habilete aux",1130,316+14*0);
			g.drawString("armes... mais au diable pareilles prouesses ! Vous etes en",1130,316+14*1);
			g.drawString("effet passe maitre en matiere de sorcellerie et vous pouvez,",1130,316+14*2);
			g.drawString("par consequent, contrer, affaiblir et vaincre vos ennemis par",1130,316+14*3);
			g.drawString("le seul exercice de votre art. Vous savez utiliser a votre",1130,316+14*4);
			g.drawString("profit des objets magiques qui demeurent parfaitement",1130,316+14*5);
			g.drawString("inefficaces aux mains de simples guerriers. La ruse et la",1130,316+14*6);
			g.drawString("discretion sont vos tactiques de predilection, mais si vous",1130,316+14*7);
			g.drawString("devez lutter de front contre un adversaire, vous n'hesitez",1130,316+14*8);
			g.drawString("pas a defendre cherement votre peau ! Le destin a voulu que",1130,316+14*9);
			g.drawString("vous portiez le meme nom qu'un des Grands Magicien",1130,316+14*10);
			g.drawString("d'Amarillie. En toute modestie, vous esperez pouvoir un jour",1130,316+14*11);
			g.drawString("atteindre cette haute dignite si le sort n'en decide pas",1130,316+14*12);
			g.drawString("autrement.",1130,316+14*13);

			g.setFont(fontbold);
			g.drawString("Avantages:",1130,490+30);
			g.setFont(fontplain);
			g.drawString("HABILETE augmente de 2 quand il faut",1130,505+30);
			g.drawString("<Tester son HABILETE DE LOCALISATION>",1130,520+30);
			g.setFont(fontbold);
			g.drawString("Inconvenients:",1130,540+30);
			g.setFont(fontplain);
			g.drawString("-Incapable de porter une armure metallique",1130,565+15);
			g.drawString("-Incapable d'utiliser un arc",1130,565+15*1+15);
			g.drawString("-Incapable d'utiliser une arme a deux mains",1130,565+15*2+15);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("HABILETE:",1130,620+30);
			g.setColor(Color.white);
			g.drawString("Total de depart =",1130,635+30);
			g.setFont(fontplain);
			g.drawString("1 de + 4",1130+125,635+30);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("ENDURANCE:",1130,655+30);
			g.setColor(Color.white);
			g.drawString("Total de depart =",1130,670+30);
			g.setFont(fontplain);
			g.drawString("3 des + 6",1130+125,670+30);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("CHANCE:",1130,690+30);
			g.setColor(Color.white);
			g.drawString("Total de depart =",1130,705+30);
			g.setFont(fontplain);
			g.drawString("1 de + 3",1130+125,705+30);

			g.setFont(fontbold);
			g.setColor(Color.yellow);
			g.drawString("MAGIE:",1130,725+30);
			g.setColor(Color.white);
			g.drawString("Total de depart =",1130,740+30);
			g.setFont(fontplain);
			g.drawString("7",1130+125,740+30);
			}
			}//StartGame ##################################################
			//String t="";
		if (Fen.StartGame==2){
			if (Fen.dialog==0){
				T.setLineWrap(true);
				T.setWrapStyleWord(true);
				T.setPreferredSize(new Dimension(1500, 800));
				T.setEditable(false);
				T.setVisible(true);
				T.setBackground(Color.black);
				T.setForeground(Color.white);
				this.add(T);
				String t = "Cinq ans se sont ecoules depuis la defaite "+
				"du Demon Squelette. Cinq longues et penibles annees qui n'ont "+
				"pas pu faire oublier le conflit devastateur qui a secoue l'Amarillie "+
				"toute entiere. Des milliers d'Hommes, de Nains, d'Elfes et de Centaures "+
				"ont trouve la mort sous les coups des Zombies, des Orques et surtout des "+
				"Dragons de Guerre qui avaient infeste tout le pays. Une fois le Demon pris "+
				"au piege dans le cercueil des Ames, la victoire a  enfin ete remportee... mais "+
				"a quel prix ! Aujourd'hui encore, des Zombies et autres individus de la pire "+
				"espece continuent a perpetrer le mal en toute impunite, car il ne reste pratiquement "+
				"plus de guerriers capables de les aneantir. On dit que la cour d'Irian, l'enfant-roi, "+
				"est le foyer de toutes sortes d'intrigues et de querelles minables. Les gens vivent terres "+
				"chez eux, volets clos et portes verrouillees. L'hospitalite envers les etrangers de passage, "+
				"tel que vous, n'est plus qu'un souvenir.";
				Fen.dialogLength=t.length();
				//PanTab.Jtext.setFont(PanTab.fontitalic);
				//Fen.DisplayText(t);

				//String T="";
				//for(int i=0; i<Fen.dialogDefilement; i++) T+=Fen.defileText(t)[i];
				//PanTab.Jtext.setFont(PanTab.fontitalic);
				//PanTab.Jtext.setText(T);
				//if (Fen.dialogDefilement<Fen.defileText(t).length) Fen.dialogDefilement+=1;

				String T1="";
				for(int i=0; i<Fen.dialogDefilement; i++) T1+=t.charAt(i);
				T.setFont(PanTab.fontitalic);
				T.setText(T1);
				//if (Fen.dialogDefilement<Fen.defileText(t).length) Fen.dialogDefilement+=1;
				if (Fen.dialogDefilement<t.length()) Fen.dialogDefilement+=1;
				}
			else if (Fen.dialog==1){
				if (Fen.dialogChangedOn==1){
					T.setVisible(false);
					Fen.dialogChangedOn=0;
					}
				String t = "Apres plusieurs jours de marche, vous arrivez a Kerpierre, capitale royale du Chaudron, "+
					"l'ile la plus lointain de l'archipel du cap des Glaces. Lorsque vous demandez audience, "+ 
					"on vous introduit aupres du roi. Vous etes immediatement frappe par l'apparence d'Irian: "+ 
					"la plupart des gens l'appellent toujours <<l'enfant-roi>>, mais c'est presque un adulte a "+ 
					"present et, s'il a encore beaucoup a apprendre en matiere de gouvernement, sa voix est forte, "+ 
					"autoritaire, et ses manieres pleines d'assurances.";
				PanTab.Jtext.setFont(PanTab.fontitalic);
				Fen.DisplayText(t);
				}
			else if (Fen.dialog==2){
				PanTab.dialogName="Roi Irian";
				PanTab.dialogNameKind=1;
				String t = "                  Bienvenue a toi !";
				PanTab.Jtext.setFont(PanTab.fontplain);
				Fen.DisplayText(t);
				}
			else if (Fen.dialog==3){
				String t = "Vous vous agenouillez devant le trone.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				String t = "Ceux qui m'entourent passent leur temps a s'aplatir "+
					"devant moi, ne les imite pas ! Suis-moi.";
				Fen.DisplayText(t,1,"Roi Irian",1);
				}
			else if (Fen.dialog==5){
				String t = "Sur ces mots, le jeune roi se leve, quitte la salle du trone et "+
					"s'engage dans l'etroit corridor qui mene a ses appartements prives. "+
					"Un peu etonne de ce manque de protocole, vous lui emboitez le pas.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6){
				String t = "Regarde bien.";
				Fen.DisplayText(t,1,"Roi Irian",1);
				}
			else if (Fen.dialog==7){
				String t = "Le roi designe un gros bloc de cristal bleute pose sur une table. "+
					"Il s'empare d'un carre de tissu noir brode de runes d'or, en frotte doucement "+
					"le cristal qui emet un leger bourdonnement. Puis une apparition fantomatique "+
					"se dessine au milieu de la piece: un vieillard chenu, entoure d'un indescriptible "+
					"capharnaum de papiers eparpilles, de parchemins en vrac, de cornues, de boules de cristal, d'ailes "+
					"de chauves-souris, de bocaux d'herbes et de fioles diverses. Vous n'avez jamais vu pareil desordre ! "+
					"Sentant votre presence, le vieil homme leve les yeux et demande d'une voix agacee:";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==8){
				String t = "Eh bien ? Qu'y a-t-il ? Que voulez-vous ?";
				Fen.DisplayText(t,1);
				}
			else if (Fen.dialog==9){
				String t = "Voici le heros dont je vous ai parle, Gereth. Racontez-lui "+
					"ce que vous m'avez dit, et meme plus, si vous en savez davantage depuis "+
					"la derniere fois.";
				Fen.DisplayText(t,1,"Roi Irian",1);
				}
			else if (Fen.dialog==10){
				String t = "Oh, oui, hum... je vois... Bon, je suppose que, "+
					"de votre cote, vous lui avez deja parle du Trone du Demon "+
					"et des lois de la Resonance Fractale, ainsi que...";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==11){
				String t = "Le roi l'interrompt d'un geste.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==12){
				String t = "Le temps presse, Gereth. Vous saurez lui expliquer "+
					"tout cela bien mieux que moi.";
				Fen.DisplayText(t,1,"Roi Irian",1);
				}
			else if (Fen.dialog==13){
				String t = "Le personnage spectral se tourne alors vers vous et vous "+
					"inspecte d'un oeil critique.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==14){
				String t = "Bff... Hmmm... Bon, d'accord, mais alors ecoute-moi bien, "+
					"jeune blanc-bec. J'ose esperer que tu as tout de meme entendu parler "+
					"du roi Kraal et de la defaite du Demon-Squelette au Puits Sans Fin.";
				Fen.DisplayText(t,1,"Gereth ",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==15){
				String t = "Vous fremissez imperceptiblement. Nul n'ignore en effet "+
					"que les armees du Demon-Squelette ont finalement ete vaincues "+
					"et que le Demon lui-meme a ete emprisonne dans le Cercueil des Ames, "+
					"puis transporte au milieu des grandes etendues des Plaines du Peril, ou "+
					"le Puits Sans Fin continue a bouillonner et a fumer de facon inquietante depuis "+
					"lors. Ces evenements datent de plusieurs annees, mais l'Amarillie en porte encore "+
					"les cicatrices.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==16){
				String t = "Et le Cerceuil des Ames, ca te dit quelque chose ?";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==17){
				String t = "Comme vous acquiescez, il poursuit:";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==18){
				String t = "Bien. Ce que tu ne sais peut-etre pas, c'est que la Magie dont on "+
					"s'est servi pour fabriquer ce cercueil avait pour but d'expedier le Demon "+
					"dans un espace surdimensionnel. Malheureusement, il y a eu une petite imperfection. "+
					"Et, si le puissant sortilege dont il etait impregne a permis d'expulser le Demon vers "+
					"un autre monde, il en a resulte une resonance en retour qui a permis a une creature surnaturelle "+
					"de penetrer dans ce monde. Or, je crains...";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==19){
				String t = "Le spectre parle sur un ton docte et professoral, tout en "+
					"ajustant de ridicules lorgnons sur le bout de son nez";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==20){
				String t = "Je crains, dis-je, que ce soit exactement ce qui vient de se produire: "+
					"un etre surnaturel s'est infiltre dans votre monde ou, si tu preferes, s'est echappe du sien. "+
					"A dire vrai, je ne suis pas fache de nous voir debarasses d'un individu de cette engeance ! "+
					"Mais vous y perdrez ce que nous y gagnons. A moins, bien entendu, que tu puisses l'arreter avant "+
					"qu'il n'ait recupere tous ses pouvoirs. Mais ce qui est pire encore, c'est qu'il est en train de se "+
					"regenerer a l'endroit meme ou le Cercueil des Ames a ete depose apres le bannissement du Demon. "+
					"Je veux parler du Chateau d'Argent.";
				Fen.DisplayText(t,1,"Gereth ",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==21){
				String t = "Le vieillard vous laisse digerer toutes ces informations puis il reprend:";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==22){
				String t = "Il s'appelle Zagor. C'est un grand sorcier, assurement l'un des plus puissants "+
					"que Titanus ait connus. Titanus est le monde auquel j'appartiens. Je suppose que vos soi-disant "+
					"savants y font parfois allusion, non ? Le sombre port de Sable-Noir et son miserable gouverneur, "+
					"le seigneur Azzur... les grandes civilisations de l'Ancien Monde, le chaos rampant de Khul... "+
					"on t'a sans doute parle de tout ca, n'est-ce pas ? Enfin, je ne tiens pas a m'etendre sur la description "+
					"de mon pays, sinon, je me connais, je vais devenir sentimental...";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==23){
				String t = "Revenons plutot a Zagor. Par deux fois nous l'avons cru aneanti mais il a ressuscite a deux reprises. "+
					"Il ne restait plus de lui que l'os du bras, mais cela a suffi pour qu'il reprenne du poil de la bete ! "+
					"Lorsqu'il a reintegre votre monde, son etre a fusionne avec le demon emprisonne dans le cercueil, et maintenant "+
					"c'est une forme demoniaque de l'ancien Zagor qui est en train de croitre en secret ! Il grandit de minute en minute "+
					"et ses pouvoirs augmentent a mesure que je te parle. Il m'a ete impossible de faire quoi que ce soit pour empecher "+
					"tout cela d'arriver mais, du fin fond de ma solitude, j'ai pu detecter le moment precis de l'intrusion de Zagor dans votre "+
					"pays et j'ai depeche quelques sortileges a ses trousses: des Talismans d'Or et des Dagues d'Argent. Ces objets magiques sont "+
					"disperses a l'interieur du repaire que Zagor a choisi pour se regenerer.";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==24){
				String t = "Chacun d'eux a le pouvoir d'affaiblir ses forces. Un "+
					"bon conseil: en arrivant la-bas, fais en sorte de t'en procurer le maximum avant d'affronter cette entite demoniaque !";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==25){
				String t = "Pour votre interlocuteur, il est evident que vous allez marcher d'un pas ferme et resolu "+
					"vers les sinistres ruines du Chateau d'Argent. Pour vous, c'est deja moins sur... "+
					"Plusieurs centaines de kilometres a parcourir a travers les deserts de Givre et les mers glaciaires "+
					"infestees de monstres: voila une perspective peu rejouissante, meme pour le plus valeureux des aventutiers !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==26){
				String t = "Cette fois-ci, Zagor doit etre definitivement extermine. Mais il ne suffit pas de le tuer. "+
					"Il faudra t'assurer que son corps soit reduit en cendres dans les Entrailles de Feu qui se trouvent "+
					"sous le Chateau d'Argent. C'est la que les Grands Magiciens de votre monde, depuis les points les plus "+
					"recules d'Amarillie, ont concentre tous les pouvoirs conjurateurs du Grand Mur de Feu. Mais je supporse que tu "+
					"es deja au courant de tout cela, n'est-ce pas ? ";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==27){
				String t = "Ne te meprend pas sur le sens de ces paroles. Je pensais qu'il s'agissait d'une legende ou d'une "+
					"supercherie... jusqu'a ce que Gereth m'informe de certains changements survenus depuis peu au Chateau "+
					"d'Argent. Mes propres espions ont pu verifier ses dires.";
				Fen.DisplayText(t,1,"Roi Irian",1);
				}
			else if (Fen.dialog==28){
				String t = "Aussitot, le regard du vieillard s'illumine.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==29){
				String t = "Ah ! oui, le chateau, parlons-en ! Il est justement en train de se metamorphoser sous l'influence "+
					"malefique de Zagor et de ses serviteurs. Zagor en tire toute la substance magique qui avait fait sa "+
					"reputation. Mais les forces occultes n'ont pas encore completement triomphe. Peut-etre meme trouveras-tu "+
					"de l'aide dans cet endroit, en plus des objets que j'y ai places. En revanche, je crains fort que l'un des "+
					"plus dangereux Dragons de Guerre du demon, un des rares survivants de l'ultime bataille, ne soit pour quelque "+
					"chose dans les changements radicaux qui sont en train de s'operer a l'interieur du chateau.";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==30){
				String t = "Ce dragon, expert en magie noire, cheche a prendre sa revanche en s'alliant a Zagor pour semer la "+
					"desolation sur l'Amarillie. Dans la folie qui l'anime, il compare sans doute Zagor a l'ancien Demon qu'il "+
					"a servi... Ce qui est d'ailleurs vrai dans une certaine mesure.";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==31){
				String t = "Tout a coup, l'apparition spectral semble vaciller. De petites etincelles blanches se mettent "+
					"a crepiter a la base du cristal. La voix du vieillard s'eleve de nouveau, mais elle est empreinte "+
					"d'un trouble evident.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==32){
				String t = "Attendez ! Il faut encore que je leur parle de...";
				Fen.DisplayText(t,1,"Gereth",1);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==33){
				String t = "C'est alors que le cristal explose en un millier de fragments. Irian et vous contemplez avec "+
					"stupeur les eclats fumants eparpilles dans toute la piece. D'ou proviennent les forces surnaturelles "+
					"qui ont renvoye le spectre dans sa propre dimension ? Vous l'ignorez, mais vous sentez planer la menace du mal "+
					"autour de vous.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==34){
				String t = "Tu es notre unique salut, mon brave. Personne d'autre que toi ne peut mener a bien cette mission. "+
					"Mes Magiciens font tout ce qu'ils peuvent pour maintenir le Mur de Feu qui protege l'Amarillie, "+
					"et mes fideles chevaliers luttent jour et nuit pour defendre le pays. Quant aux quelques nobles qui "+
					"meritent encore ma confiance, ils s'attellent a maintenir un semblant d'ordre dans le royaume, ce qui "+
					"n'est pas une tache de tout repos, crois-moi ! ";
				Fen.DisplayText(t,1,"Roi Irian",1);
				}
			else if (Fen.dialog==35){
				String t = "Aussi, je t'en conjure, rends-toi au Chateau d'Argent ! Un vaisseau est pret qui t'attend deja "+
					"avec son equipage. Si tu parviens a nous debarrasser une fois pour toutes du sorcier diabolique qui "+
					"se prepare a aneantir notre pays, je puis t'assurer que ma reconnaissance ne sera pas un vain mot !";
				Fen.DisplayText(t,1,"Roi Irian",1);
				}
			else if (Fen.dialog==36){
				String t = "Le destin du monde est entre vos mains et, bien que cette mission vous paraisse un fardeau bien "+
					"lourd pour une seule personne, vous ne pouvez pas decliner cette requete !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==37){
				Fen.dialogOn=0;
				Fen.dialog=0;
				Fen.Flag=1;
				}
			try{
				Thread.sleep(10);
				} catch (InterruptedException e){}
			}
			//System.out.println("T="+Fen.defileText(t).length+"\t"+Fen.dialogDefilement+"\t"+Fen.dialog+"\t"+Fen.dialogOn+"\t"+Fen.StartGame);
		}
	}
class SourisPan0 implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1){
			System.out.println("X="+X+"\t"+"Y="+Y);
			if (Fen.StartGame==1){
				Fen.StartGame=2;		
				Fen.Perso=Fen.Choice;
				PanTab.Text1=PanTab.Text2=PanTab.Text3=PanTab.Text4="";
				if (Fen.Perso==1){
					int D = 1+(int)(Math.random()*6);
					Fen.HabileteTotalDepart=Fen.Habilete=D+6;
					D = 1+(int)(Math.random()*6);
					Fen.EnduranceTotalDepart=Fen.Endurance=D+18;
					D = 1+(int)(Math.random()*6);
					Fen.ChanceTotalDepart=Fen.Chance=D+4;
					Fen.MagieTotalDepart=Fen.Magie=1;
					}
				if (Fen.Perso==2){
					int D = 1+(int)(Math.random()*6);
					int D2 = 1+(int)(Math.random()*6);
					Fen.HabileteTotalDepart=Fen.Habilete=D+6;
					D = 1+(int)(Math.random()*6);
					Fen.EnduranceTotalDepart=Fen.Endurance=D+D2+12;
					D = 1+(int)(Math.random()*6);
					Fen.ChanceTotalDepart=Fen.Chance=D+3;
					Fen.MagieTotalDepart=Fen.Magie=3;
					}
				if (Fen.Perso==3){
					int D = 1+(int)(Math.random()*6);
					int D2 = 1+(int)(Math.random()*6);
					Fen.HabileteTotalDepart=Fen.Habilete=D+5;
					D = 1+(int)(Math.random()*6);
					Fen.EnduranceTotalDepart=Fen.Endurance=D+D2+12;
					D = 1+(int)(Math.random()*6);
					Fen.ChanceTotalDepart=Fen.Chance=D+5;
					Fen.MagieTotalDepart=Fen.Magie=2;
					}
				if (Fen.Perso==4){
					int D = 1+(int)(Math.random()*6);
					int D2 = 1+(int)(Math.random()*6);
					int D3 = 1+(int)(Math.random()*6);
					Fen.HabileteTotalDepart=Fen.Habilete=D+4;
					D = 1+(int)(Math.random()*6);
					Fen.EnduranceTotalDepart=Fen.Endurance=D+D2+D3+6;
					D = 1+(int)(Math.random()*6);
					Fen.ChanceTotalDepart=Fen.Chance=D+3;
					Fen.MagieTotalDepart=Fen.Magie=7;
					}
				Fen.EnduranceTotalDepartBis=Fen.EnduranceTotalDepart;
				Fen.Provisions=12;
				int D = 1+(int)(Math.random()*6);
				int D2 = 1+(int)(Math.random()*6);
				int D3 = 1+(int)(Math.random()*6);
				Fen.Gold=D+D2+D3+2;
				if (Fen.Perso==3) Fen.Gold+=5;
				//Fen.Equipement[0]=Fen.Armor[0]=1;
				Fen.ArmorEquiped=0;
				Fen.Equipement[1]=Fen.Object[0]=1;
				Fen.Equipement[2]=Fen.Weapon[0]=1;
				if (Fen.Perso==2){
					//Fen.Equipement[3]=Fen.Weapon[1]=1;
					Fen.WeaponEquiped=1;
					}
				if (Fen.Perso==4){
					//Fen.Equipement[5]=Fen.Weapon[3]=1;
					Fen.WeaponEquiped=3;
					}
				if (Fen.Perso==1 || Fen.Perso==3){
					//Fen.Equipement[4]=Fen.Weapon[2]=1;
					Fen.WeaponEquiped=2;
					}
				Fen.ChoiceReset();
				Fen.dialogOn=1;
				}
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisMotionPan0 implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){
		int X = event.getX();
		int Y = event.getY();
		if (Fen.StartGame!=2){
			if (750-180<X && X<750+180 && 750<Y && Y<790 && Fen.Choice!=0) Fen.StartGame=1;
			else Fen.StartGame=0;
			}


		}
	public void mouseDragged(MouseEvent event){}
	}
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PAN0 End
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************


//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PAN1
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
class Pan1 extends JPanel{
//ONWORK

	static int SelectedObject=0;
	static int BuyingX=0;
	static int BuyingY=0;
	static int Buying=0;

	static int Flag7Gambit=0;
	static int Flag4Done=0;
	static int Flag9Done=0;
	static int Flag13Done=0;
	static int Flag14Done=0;
	static int Flag19FightPart=0;
	static int Flag20Done=0;
	static int Flag21Done=0;
	static int Flag26FightBadLuck=0;
	static int Flag32Done=0;
	static int Flag33Done=0;
	static int Flag34Done=0;
	static int Flag35Done=0;
	static int Flag37Done=0;
	static int Flag48FightDone=0;
	static int Flag49SearchDone=0;
	static int Flag49Done=0;
	static int Flag51Done=0;
	static int Flag52FightDone=1;
	static int Flag31ObjectCollected=0;
	static int Flag54Done=0;

	static int pan1On=0;
	public Pan1(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		SourisPan1 souris = new SourisPan1();
		SourisMotionPan1 sourisM = new SourisMotionPan1();
		this.addMouseListener(souris);
		this.addMouseMotionListener(sourisM);

		SourisXY s = new SourisXY();
		this.addMouseListener(s);

		Touche touche = new Touche();
		this.addKeyListener(touche);
		}
	public void paintComponent(Graphics g){
		if (Fen.MenuStatOn==0) pan1On=1;
		else if (Fen.MenuStatOn==1) pan1On=0;
		g.setColor(Color.black);
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		g.setFont(PanTab.fontbold);
		g.setColor(Color.white);
		g.drawString("ENDURANCE="+String.valueOf(Fen.Endurance)+"/"+String.valueOf(Fen.EnduranceTotalDepart),5,25);
		g.drawString("HABILETE="+String.valueOf(Fen.Habilete)+"/"+String.valueOf(Fen.HabileteTotalDepart),5,50);
		g.drawString("CHANCE="+String.valueOf(Fen.Chance)+"/"+String.valueOf(Fen.ChanceTotalDepart),5,75);
		g.drawString("MAGIE="+String.valueOf(Fen.Magie),5,100);
		g.drawString("Pieces d'Or="+String.valueOf(Fen.Gold),5,795);
		g.drawString("Provisions="+String.valueOf(Fen.Provisions),5,770);
		g.drawString("Flag="+String.valueOf(Fen.Flag),5,745);
// Dés ***************************************************************************
		if (Fen.DiceThrow1==1){
			g.setColor(Color.white);
			g.fillRect(750-50, 400-50, 100, 100);
			g.setColor(Color.black);
			g.drawRect(750-50, 400-50, 100, 100);
			g.setFont(new Font("Arial",Font.BOLD,80));
			//g.drawString(String.valueOf(Fen.Dice),730, 440);
			if (Fen.Dice==1) g.fillOval(750-13, 400-13, 25, 25);
			else if (Fen.Dice==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==5){
				g.fillOval(750-13, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50,400-42+j*30, 25, 25);
						}
					}
				}
			if (Fen.DiceOn==0){
				new TimeDice().start();
				Fen.DiceOn=1;
				Fen.DiceTour+=1;
				if (Fen.DiceTour<100 && Fen.DiceTour%4==0) Fen.Dice=1+(int)(Math.random()*6);
				if (Fen.DiceTour==150){
					Fen.DiceTour=0;
					Fen.DiceThrow1=2;
					}
				}
			}
		if (Fen.DiceThrow2>=1 && Fen.DiceThrow2!=3){
			g.setColor(Color.white);
			g.fillRect(750-100, 400-50, 100, 100);
			g.fillRect(750, 400-50, 100, 100);
			g.setColor(Color.black);
			g.drawRect(750-100, 400-50, 100, 100);
			g.drawRect(750, 400-50, 100, 100);
			if (Fen.Dice==1) g.fillOval(750-13-50, 400-13, 25, 25);
			else if (Fen.Dice==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50-50,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30-50,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50-50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==5){
				g.fillOval(750-13-50, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50-50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50-50,400-42+j*30, 25, 25);
						}
					}
				}
			if (Fen.DiceThrow2==2){
				if (Fen.Dice2==1) g.fillOval(750-13+50, 400-13, 25, 25);
				else if (Fen.Dice2==2){
					for(int i=0; i<2; i++) g.fillOval(750-38+i*50+50,400-38+i*50, 25, 25);
					}
				else if (Fen.Dice2==3){
					for(int i=0; i<3; i++) g.fillOval(750-42+i*30+50,400-42+i*30, 25, 25);
					}
				else if (Fen.Dice2==4){
					for(int i=0; i<2; i++){
						for(int j=0; j<2; j++){
							g.fillOval(750-38+i*50+50,400-38+j*50, 25, 25);
							}
						}
					}
				else if (Fen.Dice2==5){
					g.fillOval(750-13+50, 400-13, 25, 25);
					for(int i=0; i<2; i++){
						for(int j=0; j<2; j++){
							g.fillOval(750-38+i*50+50,400-38+j*50, 25, 25);
							}
						}
					}
				else if (Fen.Dice2==6){
					for(int i=0; i<2; i++) {
						for(int j=0; j<3; j++){
							g.fillOval(750-38+i*50+50,400-42+j*30, 25, 25);
							}
						}
					}
				}
			if (Fen.DiceOn==0){
				new TimeDice().start();
				Fen.DiceOn=1;
				Fen.DiceTour+=1;
				if (Fen.DiceTour<100 && Fen.DiceTour%4==0){
					if (Fen.DiceThrow2==1) Fen.Dice=1+(int)(Math.random()*6);
					if (Fen.DiceThrow2==2) Fen.Dice2=1+(int)(Math.random()*6);
					}
				if (Fen.DiceTour==150){
					Fen.DiceTour=0;
					Fen.DiceThrow2+=1;
					}
				}
			}
		if (Fen.DiceThrow3>=1 && Fen.DiceThrow3!=4){
			g.setColor(Color.white);
			g.fillRect(750-150, 400-50, 100, 100);
			g.fillRect(750-50, 400-50, 100, 100);
			g.fillRect(750+50, 400-50, 100, 100);
			g.setColor(Color.black);
			g.drawRect(750-150, 400-50, 100, 100);
			g.drawRect(750-50, 400-50, 100, 100);
			g.drawRect(750+50, 400-50, 100, 100);
			if (Fen.Dice==1) g.fillOval(750-13-100, 400-13, 25, 25);
			else if (Fen.Dice==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50-100,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30-100,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50-100,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==5){
				g.fillOval(750-13-100, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50-100,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50-100,400-42+j*30, 25, 25);
						}
					}
				}

			if (Fen.Dice2==1) g.fillOval(750-13, 400-13, 25, 25);
			else if (Fen.Dice2==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice2==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice2==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice2==5){
				g.fillOval(750-13, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice2==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50,400-42+j*30, 25, 25);
						}
					}
				}
			if (Fen.Dice3==1) g.fillOval(750-13+100, 400-13, 25, 25);
			else if (Fen.Dice3==2){
				for(int i=0; i<2; i++) g.fillOval(750-38+i*50+100,400-38+i*50, 25, 25);
				}
			else if (Fen.Dice3==3){
				for(int i=0; i<3; i++) g.fillOval(750-42+i*30+100,400-42+i*30, 25, 25);
				}
			else if (Fen.Dice3==4){
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50+100,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice3==5){
				g.fillOval(750-13+100, 400-13, 25, 25);
				for(int i=0; i<2; i++){
					for(int j=0; j<2; j++){
						g.fillOval(750-38+i*50+100,400-38+j*50, 25, 25);
						}
					}
				}
			else if (Fen.Dice3==6){
				for(int i=0; i<2; i++) {
					for(int j=0; j<3; j++){
						g.fillOval(750-38+i*50+100,400-42+j*30, 25, 25);
						}
					}
				}
				
			if (Fen.DiceOn==0){
				new TimeDice().start();
				Fen.DiceOn=1;
				Fen.DiceTour+=1;
				if (Fen.DiceTour<100 && Fen.DiceTour%4==0){
					if (Fen.DiceThrow3==1) Fen.Dice=1+(int)(Math.random()*6);
					if (Fen.DiceThrow3==2) Fen.Dice2=1+(int)(Math.random()*6);
					if (Fen.DiceThrow3==3) Fen.Dice3=1+(int)(Math.random()*6);
					}
				if (Fen.DiceTour==150){
					Fen.DiceTour=0;
					Fen.DiceThrow3+=1;
					}
				}
			}
// Fin Dés           *********************************************************************
//FLAG 1
		if (Fen.Flag==1){
			Fen.ZoneName="Kerpierre";
			Fen.ChoiceType4On=1;
			PanTab.Text1="Aller au Chateau d'Argent";
			PanTab.Text2="Aller aux marches";
			PanTab.Text3="Chercher un gain d'argent";
			PanTab.Text4="Consulter un sage du roi";
			switch(Fen.Choice){
				case 1:Fen.Flag=2;Fen.ChoiceReset();break;
				case 2:Fen.Flag=3;Fen.ChoiceReset();break;
				case 3:Fen.Flag=4;Fen.ChoiceReset();break;
				case 4:Fen.Flag=5;Fen.ChoiceReset();break;
				}
			}
//FLAG 3
		else if (Fen.Flag==3){
			Fen.ZoneName="Marches de Kerpierre";
			if (Flag4Done==0){
				Fen.ChoiceType3On=1;
				PanTab.Text1="Aller au Chateau d'Argent";
				PanTab.Text2="Chercher un gain d'argent";
				PanTab.Text3="Consulter un sage du roi";
				switch(Fen.Choice){
					case 1:Fen.Flag=2;Fen.ChoiceReset();break;
					case 2:Fen.Flag=4;Fen.ChoiceReset();break;
					case 3:Fen.Flag=5;Fen.ChoiceReset();break;
					}
				}
			else if (Flag4Done==1){
				Fen.ChoiceType2On=1;
				PanTab.Text1="Aller au Chateau d'Argent";
				PanTab.Text2="Consulter un sage du roi";
				switch(Fen.Choice){
					case 1:Fen.Flag=2;Fen.ChoiceReset();break;
					case 2:Fen.Flag=5;Fen.ChoiceReset();break;
					}
				}	
			g.setColor(Color.black);
			g.fillRect(185, 80, 1500-185*2, 700);

			g.setColor(Color.cyan);
			if (Fen.SelectedObjectOn==1) g.fillRect(185, 80+70*0, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==2) g.fillRect(185, 80+70*1, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==3) g.fillRect(185, 80+70*2, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==4) g.fillRect(185, 80+70*3, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==5) g.fillRect(185, 80+70*4, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==6) g.fillRect(185, 80+70*5, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==7) g.fillRect(185, 80+70*6, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==8) g.fillRect(185, 80+70*7, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==9) g.fillRect(185, 80+70*8, 1500-185*2, 70);
			if (Fen.SelectedObjectOn==10) g.fillRect(185, 80+70*9, 1500-185*2, 70);
			g.setColor(Color.magenta);
			if (Fen.SelectedObject==1) g.fillRect(185, 80+70*0, 1500-185*2, 70);
			if (Fen.SelectedObject==2) g.fillRect(185, 80+70*1, 1500-185*2, 70);
			if (Fen.SelectedObject==3) g.fillRect(185, 80+70*2, 1500-185*2, 70);
			if (Fen.SelectedObject==4) g.fillRect(185, 80+70*3, 1500-185*2, 70);
			if (Fen.SelectedObject==5) g.fillRect(185, 80+70*4, 1500-185*2, 70);
			if (Fen.SelectedObject==6) g.fillRect(185, 80+70*5, 1500-185*2, 70);
			if (Fen.SelectedObject==7) g.fillRect(185, 80+70*6, 1500-185*2, 70);
			if (Fen.SelectedObject==8) g.fillRect(185, 80+70*7, 1500-185*2, 70);
			if (Fen.SelectedObject==9) g.fillRect(185, 80+70*8, 1500-185*2, 70);
			if (Fen.SelectedObject==10) g.fillRect(185, 80+70*9, 1500-185*2, 70);



			g.setColor(Color.white);
			g.drawRect(185, 80, 1500-185*2, 700);
			for(int i=0; i<9; i++) g.drawLine(185, 150+i*70, 1500-185,150+i*70);
			g.setFont(PanTab.fontbold);
			g.drawString("Cotte de mailles", 190, 125+0*70);
			g.drawString("Arc et fleches", 190, 125+1*70);
			g.drawString("Arbalete et carreaux", 190, 125+2*70);
			g.drawString("Potion d'Endurance", 190, 125+3*70);
			g.drawString("Anneau Magique", 190, 125+4*70);
			g.drawString("Antidote contre la peste", 190, 125+5*70);
			g.drawString("Bouteille vide", 190, 125+6*70);
			g.drawString("Rouleau de corde (10m)", 190, 125+7*70);
			g.drawString("Flacon de Liqueur d'Herbes", 190, 125+8*70);
			g.drawString("Potion de Resistance au Feu", 190, 125+9*70);
			for(int i=0; i<10; i++){
				if (i==0 || i==3) g.drawString("7 Pieces d'Or", 1160,125+i*70);
				if (i==1 || i==2) g.drawString("4 Pieces d'Or", 1160,125+i*70);
				if (i==4 || i==9) g.drawString("5 Pieces d'Or", 1160,125+i*70);
				if (i==5) g.drawString("6 Pieces d'Or", 1160,125+i*70);
				if (i==6 || i==7) g.drawString("1 Piece d'Or", 1160,125+i*70);
				if (i==8) g.drawString("3 Pieces d'Or", 1160,125+i*70);
				}
			if (Fen.SelectedObject!=0){
				if (Fen.Buying==0) g.setColor(Color.black);
				else if (Fen.Buying==1) g.setColor(Color.yellow);
				g.fillRect(1350, 400-25, 100, 50);
				g.setColor(Color.red);
				g.drawRect(1350, 400-25, 100, 50);
				g.setColor(Color.white);
				g.drawString("Acheter",1355,410);
				}
			if (Fen.Buying==2){
				Fen.Buying=0;
				int price=0;
				switch(Fen.SelectedObject){
					case 1:price=7;break;
					case 2:price=4;break;
					case 3:price=4;break;
					case 4:price=7;break;
					case 5:price=5;break;
					case 6:price=6;break;
					case 7:price=1;break;
					case 8:price=1;break;
					case 9:price=3;break;
					case 10:price=5;break;
					}
				if (Fen.Gold>=price){
					Fen.Gold-=price;
					switch(Fen.SelectedObject){
						case 1:Fen.Equipement[6]+=1;
							Fen.Armor[1]+=1;
							break;
						case 2:Fen.Equipement[7]+=1;
							Fen.Weapon[4]+=1;
							break;
						case 3:Fen.Equipement[8]+=1;
							Fen.Weapon[5]+=1;
							break;
						case 4:Fen.Equipement[9]+=1;
							Fen.Object[1]+=1;
							break;
						case 5:Fen.Equipement[10]+=1;
							Fen.Object[2]+=1;
							break;
						case 6:Fen.Equipement[11]+=1;
							Fen.Object[3]+=1;
							break;
						case 7:Fen.Equipement[12]+=1;
							Fen.Object[4]+=1;
							break;
						case 8:Fen.Equipement[13]+=1;
							Fen.Object[5]+=1;
							break;
						case 9:Fen.Equipement[14]+=1;
							Fen.Object[6]+=1;
							break;
						case 10:Fen.Equipement[15]+=1;
							Fen.Object[7]+=1;
							break;
						}
					}
				Fen.SelectedObject=0;;
				}
			}
//FLAG 4
		else if (Fen.Flag==4){
			Fen.ZoneName="Kerpierre";
			Fen.ChoiceType5On=1;
			PanTab.Text1="Travailler sur les docks";
			PanTab.Text2="Jouer dans une taverne";
			PanTab.Text3="Aller au Chateau d'Argent";
			PanTab.Text4="Aller aux marches";
			PanTab.Text5="Consulter un sage du roi";
			switch(Fen.Choice){
				case 1:Fen.Flag=6;Fen.ChoiceReset();break;
				case 2:Fen.Flag=7;Fen.ChoiceReset();break;
				case 3:Fen.Flag=2;Fen.ChoiceReset();break;
				case 4:Fen.Flag=3;Fen.ChoiceReset();break;
				case 5:Fen.Flag=5;Fen.ChoiceReset();break;
				}
			}
//FLAG 5
		else if (Fen.Flag==5){
			Fen.ZoneName="Chez Yondell";
			if (Flag9Done==0 && Fen.NoChoiceOn()){
				Fen.dialogOn=1;
				if (Fen.dialog==0){
					String t = "Comme bon nombre de ports, Kerpierre regorge de diseurs de bonne aventure, "+
						"de tireurs de cartes et charlatans de tout acabit mais, en tant que Sage du roi, "+
						"Yondell beneficie d'une solide reputation de voyant, aussi decidez-vous d'aller lui rendre visite.";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==1){
					String t = "A votre arrivee, vous trouvez le vieil homme en pleine discussion avec un marchand qui tient sous le bras "+
						"un coffre en bois.";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==2){
					String t = "Que voulez-vous ?";
					Fen.DisplayText(t,1,"Yondell",2);
					PanTab.Jtext.insert("  ",0);
					}
				else if (Fen.dialog==3){
					String t = "Vous commencez a lui expliquer que vous aimeriez quelques conseils, mais il vous "+
						"interrompt d'un geste autoritaire et reprend:";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==4){
					String t = "Ce miserable me demande 5 Pieces d'Or en echange d'une poignee de queues de Luminolezards de Barrabangie, "+
						"vous vous rendez compte ? Quelle honte de pratiquer de tels prix ! Payez-le a ma place et je verrai "+ 
						"ce que je peux faire pour vous.";
					Fen.DisplayText(t,1,"Yondell",2);
					PanTab.Jtext.insert("  ",0);
					}
				else if (Fen.dialog==5){
					if (Flag4Done==0 && Flag9Done==0) Fen.ChoiceType4On=1;
					if (Flag4Done==1 && Flag9Done==0) Fen.ChoiceType3On=1;
					if (Flag4Done==0 && Flag9Done==1) Fen.ChoiceType4On=1;
					if (Flag4Done==1 && Flag9Done==1) Fen.ChoiceType3On=1;
					Fen.dialogEnd();
					}
				}
			else if (Flag9Done==1 && Fen.dialog<6){
				if (Flag4Done==0 && Flag9Done==0) Fen.ChoiceType4On=1;
				if (Flag4Done==1 && Flag9Done==0) Fen.ChoiceType3On=1;
				if (Flag4Done==0 && Flag9Done==1) Fen.ChoiceType4On=1;
				if (Flag4Done==1 && Flag9Done==1) Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType4On==1){
				if (Flag4Done==0 && Flag9Done==0){
					PanTab.Text1="Payer 5 Pieces d'Or";
					PanTab.Text2="Aller au Chateau d'Argent";
					PanTab.Text3="Chercher un gain d'argent";
					PanTab.Text4="Aller aux marches";
					switch(Fen.Choice){
						case 1:if (Fen.Gold>=5){
							Fen.Flag=9;
							Fen.ChoiceReset();
							Fen.Gold-=5;
							}
							break;
						case 2:Fen.Flag=2;Fen.ChoiceReset();break;
						case 3:Fen.Flag=4;Fen.ChoiceReset();break;
						case 4:Fen.Flag=3;Fen.ChoiceReset();break;
						}
					}
				else if (Flag4Done==0 && Flag9Done==1){
					PanTab.Text1="Redemander la prophetie";
					PanTab.Text2="Aller au Chateau d'Argent";
					PanTab.Text3="Chercher un gain d'argent";
					PanTab.Text4="Aller aux marches";
					switch(Fen.Choice){
						case 1:Fen.dialog=6;Fen.dialogOn=1;Fen.ChoiceReset();break;
						case 2:Fen.Flag=2;Fen.ChoiceReset();break;
						case 3:Fen.Flag=4;Fen.ChoiceReset();break;
						case 4:Fen.Flag=3;Fen.ChoiceReset();break;
						}
					}
				}
			if (Fen.ChoiceType3On==1){
				if (Flag4Done==1 && Flag9Done==0){
					PanTab.Text1="Payer 5 Pieces d'Or";
					PanTab.Text2="Aller au Chateau d'Argent";
					PanTab.Text3="Aller aux marches";
					switch(Fen.Choice){
						case 1:if (Fen.Gold>=5){
							Fen.Flag=9;
							Fen.ChoiceReset();
							Fen.Gold-=5;
							}
							break;
						case 2:Fen.Flag=2;Fen.ChoiceReset();break;
						case 3:Fen.Flag=3;Fen.ChoiceReset();break;
						}
					}
				else if (Flag4Done==1 && Flag9Done==1){
					Fen.ChoiceType3On=1;
					PanTab.Text1="Redemander la prophetie";
					PanTab.Text2="Aller au Chateau d'Argent";
					PanTab.Text3="Aller aux marches";
					switch(Fen.Choice){
						case 1:Fen.dialog=6;Fen.dialogOn=1;Fen.ChoiceReset();break;
						case 2:Fen.Flag=2;Fen.ChoiceReset();break;
						case 3:Fen.Flag=3;Fen.ChoiceReset();break;
						}
					}
				}
			if (Fen.dialog==6){
				String t = "Tu as deja oublie ce que je t'ai dit ? Quel pantoufle tu fais ! Heureusement que moi, j'ai une bonne memoire. "+
					"Voici ce que les vers disaient:";
				Fen.DisplayText(t,1,"Yondell",2);
				PanTab.Jtext.insert("  ",0);
				}
			else if (Fen.dialog==7){
				String t = "Le Chevalier sera detruit par le rouge et les lions."+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+
					"Le Champion des Cornus d'Enfer trouvera la mort par le bleu et les chevrons."+"\t"+"\t"+"\t"+"\t"+"\t"+
					"       Le Dragon de Guerre perira par les etoiles jaunes du firmament."+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+
					"     Les coupes et le blanc causeront la perte de l'Ogre Mutant."+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+
					"       Le Baton d'Onyx vous donnera la victoire, si vous l'utilisez a bon escient.";
				PanTab.Jtext.setForeground(Color.orange);
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==8){
				PanTab.Jtext.setForeground(Color.white);
				String t = "Et voila. J'ignore ce que cela signifie, mais je suis sur qu'il s'agit la d'un message important.";
				Fen.DisplayText(t,1,"Yondell",2);
				PanTab.Jtext.insert("  ",0);
				}
			else if (Fen.dialog==9){
				if (Flag4Done==0 && Flag9Done==1) Fen.ChoiceType4On=1;
				if (Flag4Done==1 && Flag9Done==1) Fen.ChoiceType3On=1;
				Fen.dialogEnd();
				}				
			}
//FLAG 9
		else if (Fen.Flag==9){
			Fen.ZoneName="Chez Yondell";
			//Fen.dialogOn=1;
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Apres avoir recu son argent, le marchant remet le coffret au vieil homme qui le lance "+
				"negligemment sur un plateau de bronze, puis il se tourne vers vous.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Alors comme ca, vous avez l'intention d'aller au Chateau d'Argent ? Hmmmm !";
				Fen.DisplayText(t,1,"Yondell",2);
				PanTab.Jtext.insert("  ",0);
				}
			else if (Fen.dialog==2){
				String t = "Cette declaration vous laisse quelque peu ebahi. Ce vieillard "+
					"possederait-il vraiment le don de double vue ?";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				String t = "Non, c'est le roi qui m'a dit le but de votre mission. Cependant, le Chateau d'Argent m'est apparu en reve, l'autre nuit. "+
					"Voyons... ou ai-je bien pu fourrer mes notes...? Ah oui !";
				Fen.DisplayText(t,1,"Yondell",2);
				PanTab.Jtext.insert("  ",0);
				}
			else if (Fen.dialog==4){
				String t = "Sur ce, le vieux sage se dirige vers une echelle branlantee et, avec une agilite surprenante pour un homme de son age, il "+
					"va prendre un registre poussiereux, tout en haut de la bibliotheque. Il en feuillette quelques pages puis, d'un air triomphant, "+
					"se met a declamer les vers suivants:";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==5){
				String t = "Le Chevalier sera detruit par le rouge et les lions."+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+
					"Le Champion des Cornus d'Enfer trouvera la mort par le bleu et les chevrons."+"\t"+"\t"+"\t"+"\t"+"\t"+
					"       Le Dragon de Guerre perira par les etoiles jaunes du firmament."+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+
					"     Les coupes et le blanc causeront la perte de l'Ogre Mutant."+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+
					"       Le Baton d'Onyx vous donnera la victoire, si vous l'utilisez a bon escient.";
				PanTab.Jtext.setForeground(Color.orange);
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6){
				PanTab.Jtext.setForeground(Color.white);
				String t = "Et voila. J'ignore ce que cela signifie, mais je suis sur qu'il s'agit la d'un message important.";
				Fen.DisplayText(t,1,"Yondell",2);
				PanTab.Jtext.insert("  ",0);
				}
			else if (Fen.dialog==7){
				String t = "Quant a vous, ces paroles vous laissent pour le moins perplexe...";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==8){
				if (Flag4Done==0) Fen.ChoiceType3On=1;
				else if (Flag4Done==1) Fen.ChoiceType2On=1;
				Fen.dialogEnd();
				Flag9Done=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Aller au Chateau d'Argent";
				PanTab.Text2="Chercher un gain d'argent";
				PanTab.Text3="Aller aux marches";
				switch(Fen.Choice){
					case 1:Fen.Flag=2;Fen.ChoiceReset();break;
					case 2:Fen.Flag=4;Fen.ChoiceReset();break;
					case 3:Fen.Flag=3;Fen.ChoiceReset();break;
					}
				}
			else if (Fen.ChoiceType2On==1){
				PanTab.Text1="Aller au Chateau d'Argent";
				PanTab.Text2="Aller aux marches";
				switch(Fen.Choice){
					case 1:Fen.Flag=2;Fen.ChoiceReset();break;
					case 2:Fen.Flag=3;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 6
		else if (Fen.Flag==6){
			Fen.ZoneName="Kerpierre";
			Fen.dialogOn=1;
			if (Fen.dialog==0){
				if (Fen.Perso==4){
					String t = "Vous n'arrivez pas a vous faire embaucher. Les Magiciens sont "+
						"soi-disant trop malingre pour faire de bons dockers !";
					Fen.DisplayText(t,3);
					}
				else{
					String t = "On vous engage pour decharger un vaisseau et, au bout de deux heures d'un "+
						"travail ereintant, on vous paie royalement 2 Pieces d'Or !";
					Fen.DisplayText(t,3);
					}
				}
			else if (Fen.dialog==1){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					if (Fen.Perso!=4){
						Fen.Gold+=2;
						Fen.Flag=8;
						Fen.ChoiceReset();
						Fen.dialogEnd();
						}
					else{
						Fen.ChoiceType2On=1;
						PanTab.Text1="Jouer dans une taverne";
						PanTab.Text2="S'en aller";
						switch(Fen.Choice){
							case 1:Fen.Flag=7;Fen.ChoiceReset();break;
							case 2:Fen.Flag=8;Fen.ChoiceReset();break;
							}
						if (Fen.Choice!=0) Fen.dialogEnd();					
						}
					}
				}
			}
//FLAG 7
		else if (Fen.Flag==7){
			Fen.ZoneName="Kerpierre";
			if (Fen.DiceThrow1<=2 || Fen.DiceThrow1==4) Fen.dialogOn=1;
			else Fen.dialogOn=0;
			if (Fen.dialog==0){
				String t = "Restez sur vos gardes et tenez vos partenaires a l'oeil si vous comptez faire "+
					"de l'argent au jeu. En effet, les tavernes du coin sont reputees pour etre le royaume "+
					"des tricheurs de tout poil, des cartes marquees et des pipes !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				if (Fen.DiceThrow1==0) Fen.DiceThrow1=1;
				if (Fen.DiceThrow1==2){
					Flag7Gambit=Fen.Dice;
					if (Fen.Perso==1) Flag7Gambit+=1;
					Fen.DiceThrow1=3;
					}
				else if (Fen.DiceThrow1==3){
					Fen.Question="Depenser 1 point de CHANCE pour augmenter la probabilite de gagner ?";
					Fen.ChoiceType2On=1;
					PanTab.Text1="OUI";
					PanTab.Text2="NON";
					if (Fen.Choice==1){
						Fen.Chance-=1;
						Flag7Gambit+=1;
						}
					if (Fen.Choice!=0){
						Fen.DiceThrow1=4;
						Fen.ChoiceReset();
						}
					}
				else if (Fen.DiceThrow1==4){
					if (Flag7Gambit<=3){
						String t = "Vous avez perdu au jeu. Vous perdez 2 Pieces d'Or.";
						Fen.DisplayText(t,3);
						}
					if (Flag7Gambit==4){
						String t = "Vous en sortez sans gain ni perte.";
						Fen.DisplayText(t,3);
						}
					if (Flag7Gambit>=5){
						String t = "Vous avez gagne au jeu ! Vous gagnez 4 Piece d'Or !";
						Fen.DisplayText(t,3);
						}
					}
				}
			else if (Fen.dialog==2){
				if (Flag7Gambit<=3) Fen.Gold-=2;
				if (Flag7Gambit>=5) Fen.Gold+=4;
				Fen.DiceThrow1=0;
				Fen.dialogEnd();
				Fen.Flag=8;
				}
			}
//FLAG 8			
		else if (Fen.Flag==8){
			Fen.ZoneName="Kerpierre";
			if (Fen.ChoiceType3On==0) Fen.dialogOn=1;
			if (Fen.dialog==0){
				String t = "Voici des heures que vous trainez dans les quartiers louches de la ville, ou voleurs "+
					"a la tire et coupe-jarrets operent en toute impunite.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1) Fen.TenterChance();
			else if (Fen.dialog==2){
				if (Fen.TenterChanceResult==1){
					String t = "Vous sortez des quartiers louches en preservant tous vos biens.";
					Fen.DisplayText(t,3);
					}
				else if (Fen.TenterChanceResult==2){
					String t = "Un voleur vous a pris la moitie de votre or !";
					Fen.DisplayText(t,3);
					}
				}
			else if (Fen.dialog==3){
				if (Fen.TenterChanceResult==2) Fen.Gold-=(int)(Fen.Gold/2);
				Fen.TenterChanceOn=Fen.TenterChanceResult=0;
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				Flag4Done=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Aller au Chateau d'Argent";
				PanTab.Text2="Aller aux marches";
				PanTab.Text3="Consulter un sage du roi";
				switch(Fen.Choice){
					case 1:Fen.Flag=2;Fen.ChoiceReset();break;
					case 2:Fen.Flag=3;Fen.ChoiceReset();break;
					case 3:Fen.Flag=5;Fen.ChoiceReset();break;
					}
				}		
			}
//FLAG 2
		else if (Fen.Flag==2){
			Fen.ZoneName="A bord de la Gloire d'Amarillie";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "La Gloire d'Amarillie est un vaisseau de guerre superbement gree. Mais quelle n'est pas votre surprise en "+
						"decouvrant que le capitaine est un Centaure du Barrabang ! Les Centaures ne sont pas reputes pour etre des navigateurs de "+
						"premier ordre. Cependant, celui-ci possede des fers magiques a ses sabots, qui lui permettent de marcher sur l'eau.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Les vents sont favorables. Nous devrions atteindre l'archipel du cap des Glaces sans etre embetes par le brouillard. "+
						"A part cela, il n'y a plus qu'a esperer que nous n'irons pas embrasser un iceberg et que les creatures des abysses "+
						"nous laisseront en paix, pas vrai ?";
				Fen.DisplayText(t,1,"Carannas",2);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==2){
				String t = "Le voyage commence donc sous les meilleurs auspices. Le bateau fend les flots a bonne allure, contourne sans encombre "+
						"le courant chaud des Zephyrs et, quelques jours plus tard, poursuit sa course vers le sud-ouest, apres avoir longe les cotes des deserts "+
						"de Glace. Malheureusement, le brouillard se leve aux abords des courants chauds qui passent au large de l'ile de la Tour. Pourvu que les monstres "+
						"des grandes profondeurs ne decelent pas la presence de votre vaisseau !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				}
//BALISE
			if (Fen.ChoiceType2On==1){
				Fen.Question="Depenser 1 point de CHANCE pour eviter une facheuse rencontre ?";
				PanTab.Text1="OUI";
				PanTab.Text2="NON";
				switch(Fen.Choice){
					case 1:if (Fen.Chance>=1){
						Fen.Chance-=1;
						Fen.Flag=10;
						Fen.ChoiceReset();
						}
						break;
					case 2:Fen.Flag=11;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 11
		else if (Fen.Flag==11){
			Fen.ZoneName="A bord de la Gloire d'Amarillie";
			Fen.dialogStart();
			if (Fen.FightEnded==0){
				if (Fen.dialog==0){
					String t = "Peu apres le lever du jour, vous allez prendre l'air sur le pont du bateau. Soudain, "+
							"vous remarquez que les flots s'agitent tumultueusement a tribord. Vous tirez instinctivement "+
							"votre arme tout en inspectant les remous, mais c'est par en haut qu'arrive le danger ! A travers l'epaisse brume "+
							"qui voile le ciel surgit un immense reptile aile de plusieurs metres de long ! "+
							"La gueule grande ouverte sur des crocs a faire fremir, les pattes dotees de puissantes serres, la queue herissee d'ecailles acerees, "+
							"cette creature d'enfer possede apparemment tout ce qu'il faut pour vous mettre en pieces !";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==1){
					String t="";
					if (Fen.Perso!=1){
						t = "D'un vigoureux coup de griffes, le monstre vous prend par surprise et vous fait perdre d'emblee 3 points d'ENDURANCE.";
						}
					else t = "D'un vigoureux coup de griffes, le monstre vous prend par surprise, mais grace a vos reflexes hors du commun, vous parvenez a esquiver ce coup.";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==2){
					if (Fen.dialogChangedOn==1){
						Fen.dialogChangedOn=0;
						if (Fen.Perso!=1) Fen.Endurance-=3;
						}
					String t = "Il va falloir defendre cherement votre peau.";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==3){
					Fen.dialogEnd();
					Fen.FightOn=1;
					}
				}
			if (Fen.FightEnded==1){
				if (Fen.dialog==0){
					String t = "Le capitaine et son equipage accourent pour vous remettre sur pied. Apres vous etre restaure et avoir "+
						"avale un bouillon d'herbes toniques, vous recuperez votre Total de Depart d'ENDURANCE.";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==1){
					if (Fen.dialogChangedOn==1){
						Fen.dialogChangedOn=0;
						Fen.Endurance=Fen.EnduranceTotalDepart;
						}
					String t = "Pendant ce temps, les membres de l'equipage s'efforcent de faire passer la carcasse de l'enorme reptile par-dessus bord. "+
						"Carannas l'examine avec inquietude.";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==2){				
					String t = "Ce n'est pas un monstre ordinaire. Je connais bien ces Varans. Regardez cette queue malformee, ces machoires meurtrieres et cette epine "+
						"dorsale en saillie : il y a de la magie noire dans tout cela.";
					Fen.DisplayText(t,1,"Carannas",2);
					PanTab.Jtext.insert("   ",0);
					}
				else if (Fen.dialog==3){
					String t = "L'air sombre, le Centaure se joint a ses marins pour les aider a se debarrasser du cadavre du Varan.";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==4){
					Fen.dialogEnd();
					Fen.Flag=10;
					Fen.FightEnded=0;
					}
				}
			}
								

//FLAG 10
		else if (Fen.Flag==10){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Apres plusieurs jours de traversee, votre vaisseau arrive a cinq kilometres de l'ile de la Tour. "+
					"Malgre la distance, vous distinguez l'imposante masse du Chateau d'Argent qui se decoupe sous les cieux plombes, non loin du littoral.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Comme le bateau ne peut pas s'aventurer dans les eaux peu profondes de la baie, vous remerciez l'equipage qui vous a conduit jusqu'ici et, "+
					"apres avoir ramasse votre equipement, vous descendez dans la chaloupe que l'on vient de mettre a la mer. Le capitaine Carannas vous donne une "+
					"vigoureuse poignee de main en disant:";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Nous allons maintenant faire route vers l'embouchure du fleuve Geld. Je compte en effet rejoindre mon peuple, ou du moins ce qu'il en reste, dans les terres "+
					"qui se trouvent en amont. Nous reviendrons vous chercher a la nouvelle lune, en esperant vous retrouver sain et sauf... et porteur de bonnes nouvelles. Si vous n'etes pas "+
					"au rendez-vous, nous reviendrons nous poster dans les parages a la prochaine lune. Sachez que nous sommes de tout coeur avec vous !";
				Fen.DisplayText(t,1,"Carannas",2);
				PanTab.Jtext.insert("   ",0);
				}
			else if (Fen.dialog==3){
				String t = "Apres avoir pris conge de Carannas, vous empoignez les rames et vous vous dirigez vers la cote.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				Fen.dialogEnd();
				Fen.Flag=12;
				}
			}
//FLAG 12
		else if (Fen.Flag==12){
			Fen.ZoneName="Ile de la Tour";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Vous mettez enfin pied a terre sur l'ile de la Tour. Devant vous une longue voie dallee de pierres mene directement aux portes du Chateau d'Argent, paysage sinistre s'il en est : "+
					"la route est jalonnee d'habitations que les Orques et les Zombies ont reduites a l'etat de pitoyables ruines, au cours de la guerre qui a oppose le pays aux forces du Demon-Squelette. "+
					"Apres le massacre qui a eu lieu ici, il y a peu de chances de trouver quoi que ce soit d'interessant. En revanche, il n'est pas impossible que quelques anciens membres de l'armee du Demon trainent "+
					"encore dans les parages. Si vous souhaitez malgre tout examiner ces ruines de plus pres, libre a vous !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Se diriger vers les ruines a l'ouest";				
				PanTab.Text2="Aller directement au Chateau d'Argent";
				PanTab.Text3="Se diriger vers les ruines a l'est";
				switch(Fen.Choice){
					case 1:Fen.Flag=13;Fen.ChoiceReset();break;
					case 2:Fen.Flag=15;Fen.ChoiceReset();break;
					case 3:Fen.Flag=14;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 13
		else if (Fen.Flag==13){
			//if (Fen.info==1) System.out.println("Fen.Flag="+Fen.Flag+"\n"+"dialog="+Fen.dialog+"\n");		
			Fen.ZoneName="Ile de la Tour";
			Fen.dialogStart();
			if ((Fen.FightEnded==1 || Flag13Done==1) && Fen.NoChoiceOn() && Fen.dialog!=7){
				Fen.FightEnded=0;
				Fen.dialog=6;
				}
			if (Fen.dialog==0){
				String t = "Comme vous allez passer plusieurs heures a inspecter les maisons qui n'ont pas ete entierement detruites, vous devez vous accorder un instant pour prendre un Repas.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Fen.TakeMeal();
					}
				String t = "En fin de compte, vous trouvez un rouleau de corde de dix metres, une bouteille vide et une bourse en cuir dissimulee dans un trou.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Fen.Object[5]+=1;
					Fen.Object[4]+=1;
					Fen.Dice=1+(int)(Math.random()*6);
					Fen.Gold+=Fen.Dice;
					}
				String t = "";
				if (Fen.Dice==1) t = "La bourse en cuir contient "+String.valueOf(Fen.Dice)+" Piece d'Or !";
				else if (Fen.Dice>1) t = "La bourse en cuir contient "+String.valueOf(Fen.Dice)+" Pieces d'Or !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				String t = "Malheureusement, vous tombez aussi sur une patrouille d'Orques en maraude au moment ou vous rejoignez la grand-route ! "+
					"Trois d'entre eux surgissent de derriere un muret de pierre et, si leurs armures sont dans un pietre etat, les haches qu'ils brandissent sont, en revanche, "+
					"bien affutees. De plus, vous constatez qu'il s'agit de Grands Orques et, comble de malchance, le troisieme est un Mutant dote d'une carrure d'athlete.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				String t = "Combattez-les tous ensemble !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==5){
				Fen.FightStart();
				Fen.dialogEnd();
				}
			//---------------------------------
			else if (Fen.dialog==6){
				String t = "Vous fouillez le corps de vos victimes mais vous ne trouvez rien d'interessant sur eux... excepte des gibecieres garnies de rats creves, ce qui n'est guere "+
					"appetissant !";
				if (Flag13Done==1) t = "Devant vous ne s'etend que des maisons en ruines.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==7){
				Fen.ChoiceType2On=1;
				Flag13Done=1;
				Fen.dialogEnd();
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Aller au Chateau d'Argent";
				PanTab.Text2="Se diriger vers les ruines a l'est";
				switch(Fen.Choice){
					case 1:Fen.Flag=15;Fen.ChoiceReset();break;
					case 2:Fen.Flag=14;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 14
		else if (Fen.Flag==14){
			Fen.ZoneName="Ile de la Tour";
			if (Flag14Done==1) Fen.ChoiceType2On=1;
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Vous ne trouvez rien d'interessant dans les decombres de la partie orientale du village. Etant donne le temps que vous y avez "+
					"passe, prenez un Repas pour vous restaurer.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				if (Fen.dialogChangedOn==1){
					Fen.TakeMeal();
					Fen.dialogChangedOn=0;
					}
				String t = "Au moment ou vous repartez en direction de la route principale, vous decouvrez un petit puits en pierre. Curieusement, il n'y a ni corde "+
					"ni poulie permettant de puiser de l'eau avec un seau. C'est alors que vous vous souvenez d'une vague legende a propos d'un Puits de Voyance se trouvant aux alentours "+
					"du Chateau d'Argent. Se pourrait-il que ce modeste ouvrage soit le fameux puits ?";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				Fen.dialogEnd();
				if (Flag14Done==0) Fen.ChoiceType3On=1;
				else if (Flag14Done==1) Fen.ChoiceType2On=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Y jeter 1 Piece d'Or";						
				PanTab.Text2="Se diriger vers les ruines a l'ouest";
				PanTab.Text3="Aller au Chateau d'Argent";
				switch(Fen.Choice){
					case 1:if (Fen.Gold>=1){
						Fen.Gold-=1;
						Fen.Flag=16;
						Fen.ChoiceReset();
						}
						break;
					case 2:Fen.Flag=13;Fen.ChoiceReset();break;
					case 3:Fen.Flag=15;Fen.ChoiceReset();break;
					}
				}
			else if (Fen.ChoiceType2On==1){
				PanTab.Text1="Se diriger vers les ruines a l'ouest";
				PanTab.Text2="Aller au Chateau d'Argent";
				switch(Fen.Choice){
					case 1:Fen.Flag=13;Fen.ChoiceReset();break;
					case 2:Fen.Flag=15;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 16
		else if (Fen.Flag==16){
			Fen.ZoneName="Ile de la Tour";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Des que votre Piece d'Or touche le fond du puits, l'eau se met a bouillonner puis redevient parfaitement calme. C'est alors qu'apparait le visage d'une "+
					"vieille femme a la surface, et le message suivant s'impose a votre esprit:";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Mefie-toi du magicien Remstar, derriere les Entrailles de Feu... c'est un traitre !";
				PanTab.Jtext.setForeground(Color.orange);
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Sur ce, la vision s'evanouit aussi vite qu'elle s'etait formee.";
				PanTab.Jtext.setForeground(Color.white);
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.dialogEnd();
				Flag14Done=1;
				Fen.ChoiceType2On=1;
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Se diriger vers les ruines a l'ouest";
				PanTab.Text2="Aller au Chateau d'Argent";
				switch(Fen.Choice){
					case 1:Fen.Flag=13;Fen.ChoiceReset();break;
					case 2:Fen.Flag=15;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 15
		else if (Fen.Flag==15){
			Fen.ZoneName="Ile de la Tour";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Vous voyez devant vous la silhouette massive et imposante du Chateau d'Argent. Au bout de la route, les enormes portes bardees de fer qui en marquent l'entree "+
					"sont situees nettement en contrebas. C'est donc par des salles souterraines qu'on penetre dans ces lieux, car il est impossible d'escalader les hautes murailles de pierre pour "+
					"acceder aux etages superieurs. Le chateau se compose d'un corps de batiment central flanque, a l'est et a l'ouest, de deux grandes ailes. Au nord, culmine une Grande Tour harcelee par "+
					"les mouettes.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Tout en haut se trouve la salle d'Intronisation ou plusieurs generations de rois ont du se soumettre au Rituel de l'Epee avant de pouvoir gouverner l'Amarillie a partir de cette puissante "+
					"forteresse. Votre infaillible instinct vous dit que c'est la que Zagor est en train de rassembler ses forces malefiques. Mais il n'y a pas de temps a perdre: vous vous dirigez d'un pas resolu vers la "+
					"citadelle abandonnee.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Arrive au pied des portes de fer dont les panneaux sont souilles de gribouillages grossiers (vous y reconnaissez la patte des Orques), une premiere difficulte se presente: ces portes sont fermees.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Utiliser un sortilege d'Ouverture pour 1 point de Magie";
				PanTab.Text2="Essayer d'entrer de force";
				switch(Fen.Choice){
					case 1:if (Fen.Magie>=1){
						Fen.Magie-=1;
						Fen.Flag=17;
						Fen.ChoiceReset();
						}
						break;
					case 2:Fen.Flag=18;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 18
		else if (Fen.Flag==18){
			Fen.ZoneName="Ile de la Tour";
			Fen.dialogStart();
			if (Fen.dialog==0) Fen.TesterHabilete(2);
			else if (Fen.dialog==1){
				String t ="";
				if (Fen.TesterHabileteOn!=5){
					if (Fen.Dice+Fen.Dice2+2<=Fen.Habilete) Fen.TesterHabileteResult=1;
					else Fen.TesterHabileteResult=2;
					}
				else if (Fen.TesterHabileteOn==5){
					if (Fen.TesterHabileteResult==1){
						t = "Vous ouvrez les battants d'une vigoureuse poussee.";
						}
					if (Fen.TesterHabileteResult==2){
						t = "Vous parvenez a ouvrir les battants mais l'effort fourni est tel que vous vous froissez un muscle et perdez 2 points d'ENDURANCE.";
						}
					}
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					if (Fen.TesterHabileteResult==2) Fen.Endurance-=2;
					Fen.TesterHabileteEnd();
					}
				Fen.TenterChance();
				}
			else if (Fen.dialog==3){
				if (Fen.TenterChanceResult==1) Fen.Flag=17;
				else if (Fen.TenterChanceResult==2) Fen.Flag=19;
				Fen.TenterChanceEnd();
				Fen.dialogEnd();
				}
			}
//FLAG 19
		else if (Fen.Flag==19){
			Fen.ZoneName="Dans le Chateau d'Argent";
			Fen.dialogStart();
			if (Fen.FightEnded==1 && Flag19FightPart==1){
				Fen.FightEnded=0;
				Fen.dialog=3;
				}
			else if (Fen.FightEnded==1 && Flag19FightPart==2){
				Fen.FightEnded=0;
				Fen.dialog=5;
				}
			if (Fen.dialog==0){
				String t = "Vous frolez par megarde un fil tendu en travers du passage et vous tombez a terre. Par les deux portes qui vous encadrent, vous entendez aussitot s'elever "+
					"des cris d'alarme. Vous avez a peine le temps de vous remettre sur pieds qu'un Grand Orque surgit de chaque cote. Mais ce n'est pas tout : dans chaque piece, "+
					"vous apercevez une autre creature en train de passer une armure. Ce n'est pas a deux, mais a quatre adversaires, qu'il va falloir faire face !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Combattez-les deux par deux !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Flag19FightPart=1;
					}
				Fen.dialogEnd();
				Fen.FightOn=1;
				}
			//-----------------------------------------------
			else if (Fen.dialog==3){
				String t = "Les deux Orques suivants foncent sur vous !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Flag19FightPart=2;
					}
				Fen.dialogEnd();
				Fen.FightOn=1;
				}
			//-----------------------------------------------
			else if (Fen.dialog==5){
				String t = "Vous fouillez a la hate les salles de garde de part et d'autre du couloir. A part des restes de nourriture avariee et des vetements puant l'Orque a quinze pas, "+
					"vous trouvez 1 Piece d'Or. Ensuite, vous continuez d'avancer dans le hall d'entree.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Fen.Gold+=1;
					}
				String t = "En face de vous, il y a une petite porte a gauche, une autre a droite et, entre les deux, une large porte a deux battants.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==7){
				Fen.dialogEnd();
				Fen.Flag=17;
				}
			}
//FLAG 17
		else if (Fen.Flag==17){
			Fen.ZoneName="Dans le Chateau d'Argent";
			if (Flag19FightPart==2) Fen.ChoiceType3On=1;
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "On a tendu un fil piege derriere la porte, mais, heureusement, vous l'apercevez a temps et vous l'enjambez avec precaution. ";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Vous voici maintenant dans le grand hall d'entree du Chateau d'Argent. Les murs sont ornes de bannieres et de blasons correspondants aux diverses provinces d'Amarillie, "+
					"mais beaucoup sont barbouilles, dechires ou jetes a terre. Les torches accrochees ca et la procurent un faible eclairage; on y voit a peine, mais cela prouve que le chateau est "+
					"habite et qu'il faut dont que vous soyez sur vos gardes.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Il y a une porte de chaque cote de l'entree. Derriere l'une, vous entendez des grognements et des sons rauques, inarticules. Une chose est sure : il y a des Orques dans les parages ! "+
					"Ce sont certainement de simples gardes qui ne possedent ni or ni objet de valeur. Inutile de perdre votre temps et votre energie avec eux.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				String t = "Vous avancez aussi silencieusement que possible. Au bout de plusieurs metres, vous decouvrez une petite porte sur la gauche, une autre sur la droite, et une grande porte a double battant, juste en face de vous.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Ouvrir la porte de gauche";
				PanTab.Text2="Ouvrir la porte en face";
				PanTab.Text3="Ouvrir la porte de droite";
				switch(Fen.Choice){
					case 1:Fen.Flag=21;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					case 3:Fen.Flag=20;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 20
		else if (Fen.Flag==20){
			Fen.ZoneName="Dans le Chateau d'Argent";
			if (Flag20Done==0) Fen.dialogStart();
			else if (Flag20Done==1) Fen.Flag=25;
			if (Fen.dialog==0){
				String t = "Vous voici dans le quartier des casernes ou etaient jadis loges les valeureux soldats du Chateau d'Argent, avant que le roi Kraal et ses partisans ne tombent sous le joug du Demon-Squelette et ses Dragons de Guerre. "+
					"L'atmosphere est confinee, le sol couvert de poussiere. De toute evidence, il y a longtemps que personne n'est venu ici.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Inspecter les lieux";			
				PanTab.Text2="Sortir pour ouvrir la porte de gauche";
				PanTab.Text3="Sortir pour ouvrir la porte centrale";
				switch(Fen.Choice){
					case 1:Fen.Flag=23;Fen.ChoiceReset();break;
					case 2:Fen.Flag=21;Fen.ChoiceReset();break;
					case 3:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 23
		else if (Fen.Flag==23){
			Fen.ZoneName="Dans le Chateau d'Argent";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Vous devez prendre un Repas durant le temps que necessitent vos recherches a travers les chambrees et les enfilades de cellules individuelles.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Fen.TakeMeal();
					}
				String t = "En definitive, vous ne trouvez rien d'interessant.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2) Fen.TesterHabilete();
			else if (Fen.dialog==3){
				if (Fen.TesterHabileteResult==1) Fen.Flag=24;
				else if (Fen.TesterHabileteResult==2) Fen.Flag=25;
				Fen.dialogEnd();
				Fen.TesterHabileteEnd();
				}
			}
//FLAG 24
		else if (Fen.Flag==24){
			Fen.ZoneName="Dans le Chateau d'Argent";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Debout au milieu d'une grande piece, vous reflechissez a ce que vous allez faire, lorsque vous entendez soudain des voix etouffees qui proviennent "+
					"du sous-sol. Vous vous agenouillez et, collant l'oreille contre les dalles de pierre, vous parvenez a saisir quelques mots:";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Au bout du boyau... un tresor... a coup sur... coffre... attention... piege.";
				PanTab.Jtext.setForeground(Color.orange);
				Fen.DisplayText(t,1);
				}
			else if (Fen.dialog==2){
				String t = "Vous examinez soigneusement le sol pour voir s'il n'y aurait pas une dalle descellee, mais en vain. Vous ne saurez jamais qui habite les sous-sol du chateau... "+
					"Pourtant, c'etaient des voix humaines, vous en etes presque certain.";
				PanTab.Jtext.setForeground(Color.white);
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.dialogEnd();
				Fen.Flag=25;
				}
			}
//FLAG 25
		else if (Fen.Flag==25){
			Fen.ZoneName="Dans le Chateau d'Argent";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Il n'y a plus rien a decouvrir dans cette salle. Vous sortez de la caserne et revenez dans le hall d'entree.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Flag20Done=1;
				Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Ouvrir la porte de gauche";
				PanTab.Text2="Ouvrir la porte en face";
				PanTab.Text3="Ouvrir la porte de droite";
				switch(Fen.Choice){
					case 1:Fen.Flag=21;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					case 3:Fen.Flag=20;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 21
		else if (Fen.Flag==21){
			Fen.ZoneName="Dans le Chateau d'Argent";
			if (Flag21Done==0) Fen.dialogStart();
			else if (Flag21Done==1) Fen.Flag=26;
			if (Fen.dialog==0){
				String t = "Vous penetrez dans une immense salle de manoeuves, tres haute de plafond, ornee de bannieres flamboyantes et de portraits d'illustres guerriers, ou du moins ce qu'il en reste, "+
					"car les serviteurs du Demon en ont saccage une bonne partie.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Au fond de la piece, vous apercevez un monceau d'etendards en loques, a moitie calcines, et des armes brisees entassees en vrac. Si vous souhaitez aller fouiller cet amas de materiel, "+
					"l'echo de vos pas risque de se repercuter bruyamment !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Fouiller l'amas de materiel";
				PanTab.Text2="Sortir pour ouvrir la porte centrale";
				PanTab.Text3="Sortir pour ouvrir la porte de droite";
				switch(Fen.Choice){
					case 1:Fen.Flag=26;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					case 3:Fen.Flag=20;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 26
		else if (Fen.Flag==26){
			Fen.ZoneName="Dans le Chateau d'Argent";
			Fen.dialogStart();
			if (Fen.FightEnded==1){
				Fen.FightEnded=0;
				Fen.dialog=5;
				}
			if (Flag21Done==1 && Fen.dialog!=9) Fen.dialog=8;
			if (Fen.dialog==0){
				String t = "Tout a coup, un grand filet plombe s'abat sur vous, tandis que deux Gobelins braillards degringolent du plafond en se laissant glisser le long de cordes accrochees aux poutres !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				if (Fen.Perso==1) Fen.dialog=2;
				else Fen.TesterHabilete();
				}
			else if (Fen.dialog==2){
				String t ="";
				if (Fen.TesterHabileteResult==1) t = "Vous echappez aux mailles du filet.";
				else if (Fen.TesterHabileteResult==2){
					t = "Vous etes pris dans les mailles du filet. Votre Force d'Attaque serez systematiquement reduite de 2 points durant le combat.";
					Flag26FightBadLuck=1;
					}
				else if (Fen.Perso==1) t = "Grace a vos reflexes hors du commun, vous echappez sans souci aux mailles du filet.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				String t = "Combattez-les simultanement !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				Fen.dialogEnd();
				Fen.FightOn=1;
				}
			//----------------------------------------------------------
			else if (Fen.dialog==5){
				String t = "Vous allez fouiller parmi les armes entassees au fond de la salle.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6) Fen.TenterChance();
			else if (Fen.dialog==7){
				String t = "";
				if (Fen.TenterChanceResult==1) t = "Vous denichez un arc intact et un carquois garni de fleches.";
				else if (Fen.TenterChanceResult==2) t = "Vous ne trouvez rien d'utilisable.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==8){
				String t = "";
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					if (Fen.TenterChanceResult==1) Fen.Weapon[4]+=1;
					Fen.TenterChanceEnd();
					}
				if (Flag21Done==0) t = "Comme vous ne tenez pas a gacher votre energie a grimper jusqu'au plafond pour voir ce que ces miserables Gobelins ont pu amasser, assurement des "+
					"babioles ridicules, vous sortez de la salle et revenez dans le hall d'entree.";
				else if (Flag21Done==1) t = "Il n'y a plus rien a decouvrir dans cette salle. Vous sortez de la caserne et revenez dans le hall d'entree.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==9){
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				Flag26FightBadLuck=0;
				Flag21Done=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Ouvrir la porte de gauche";
				PanTab.Text2="Ouvrir la porte en face";
				PanTab.Text3="Ouvrir la porte de droite";
				switch(Fen.Choice){
					case 1:Fen.Flag=21;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					case 3:Fen.Flag=20;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 22
		else if (Fen.Flag==22){
			Fen.ZoneName="Dans le Chateau d'Argent";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Vous voici a l'extremite du hall d'entree, dos a la porte, tourne vers l'ouest. Face a vous, il y a une autre porte a double battant. Au nord, "+
					"vous apercevez un couloir comportant deux issues sur la gauche et une troisieme tout au bout, avant que le passage n'oblique vers l'est. Cote sud, le meme "+
					"couloir tourne vers l'ouest apres quelques metres. Qu'allez-vous faire ?";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Fen.ChoiceType5On=1;
				}
			if (Fen.ChoiceType5On==1){
				//PanTab.fontplainOn=2;
				PanTab.Text1="Aller au bout du";
				PanTab.Text1bis="couloir nord";
				PanTab.Text2="Prendre le couloir sud,";
				PanTab.Text2bis="puis ouest";
				PanTab.Text3="Ouvrir la porte en face";
				PanTab.Text4="Ouvrir la premiere porte";
				PanTab.Text4bis="a gauche";
				PanTab.Text5="Ouvrir la deuxieme porte";
				PanTab.Text5bis="a gauche";
				switch(Fen.Choice){
					case 1:Fen.Flag=27;Fen.ChoiceReset();break;
					case 2:Fen.Flag=28;Fen.ChoiceReset();break;
					case 3:Fen.Flag=29;Fen.ChoiceReset();break;
					case 4:Fen.Flag=30;Fen.ChoiceReset();break;
					case 5:Fen.Flag=31;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 27
		else if (Fen.Flag==27){
			Fen.ZoneName="Dans le Chateau d'Argent";
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Juste avant que le couloir oblique vers l'est, il y a une porte en face de vous, dans le mur nord. Par ailleurs "+
					"il y a aussi une petite porte dans le passage est, toujours cote nord.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Ouvrir la premiere porte";
				PanTab.Text2="Ouvrir la deuxieme porte";
				PanTab.Text3="Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=32;Fen.ChoiceReset();break;
					case 2:Fen.Flag=33;Fen.ChoiceReset();break;
					case 3:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 32
		else if (Fen.Flag==32){
			Fen.ZoneName="Dans le Chateau d'Argent";
			Fen.dialogStart();
			//if (Flag32Done==1) Fen.dialog=5;
			if (Fen.dialog==0){
				if (Flag32Done==1) Fen.dialog=5;
				String t = "Vous essayez de forcer la serrure.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1) Fen.TenterChance();
			else if (Fen.dialog==2){
				String t = "";
				if (Fen.TenterChanceResult==1) t="Vous ouvrez la porte sans probleme.";
				else if (Fen.TenterChanceResult==2) t="Vous declenchez une lame empoisonnee qui vous fait perdre 4 points d'ENDURANCE.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					if (Fen.TenterChanceResult==2) Fen.Endurance-=4;
					Fen.TenterChanceEnd();
					Fen.Armor[1]+=1;
					}
				String t = "La piece dans laquelle vous penetez est austere et chichement meublee. C'est la qu'habitait l'ancien maitre armurier du chateau. C'est "+
					"probablement lui qui a installe le dispositif piege sur la porte afin de dissuader les Orques d'entrer dans son domaine.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				String t = "Vous trouvez une cotte de mailles en parfait etat ! Elle n'augmentera pas votre Force d'Attaque en cas de combat, mais peut-etre serez-vous "+
					"bien content de l'avoir en certaines occasions !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==5){
				String t = "Il n'y a plus rien a decouvrir dans cette salle.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				}
			if (Fen.ChoiceType2On==1){
				Flag32Done=1;
				PanTab.Text1="Ouvrir la deuxieme porte";
				PanTab.Text2="Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=33;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 33
		else if (Fen.Flag==33){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "La porte est entrouverte. Vous la poussez doucement et vous decouvrez une vaste piece, probablement une chambre d'invite, a en juger par la richesse du mobilier et des tapis. "+
					"Mais, ici comme ailleurs, les Orques s'en sont donne a coeur joie: ils ont grave leurs noms sur la table et les fauteuils en bois de rose, et tellement saute sur le lit qu'il est completement "+
					"defonce.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.ChoiceType2On=1;
				Fen.dialogEnd();
				}
			if (Fen.ChoiceType2On==1){
				if (Flag34Done==0){
					switch(Fen.Choice){
						default:Fen.Question("Voulez-vous approfondir les recherches ?");break;
						case 1:Fen.Flag=34;Fen.ChoiceReset();break;
						case 2:Fen.Flag=35;Fen.ChoiceReset();break;
						}
					}
				else if (Flag35Done==0){
					Fen.Flag=35;
					Fen.ChoiceReset();
					}
				else if (Flag35Done==1){
					switch(Fen.Choice){
						default:Fen.Question("Aller au bout du couloir ?");break;
						case 1:Fen.Flag=36;Fen.ChoiceReset();break;
						case 2:Fen.Flag=22;Fen.ChoiceReset();break;
						}
					}
				}
			}
							

//FLAG 34
		else if (Fen.Flag==34){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Vous devez prendre 1 Repas durant cet intermede.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Fen.TakeMeal();
					Fen.Object[2]+=1;
					}
				String t = "Une fois restaure, vous decouvrez un Anneau Magique cache a l'interieur d'un oreiller !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Sous le matelas, vous denichez egalement un parchemin portant un sortilege d'Ouverture. Ces trouvailles vous rapportent 1 point de CHANCE.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.Flag=35;
				Flag34Done=1;
				if (Fen.Chance<Fen.ChanceTotalDepart) Fen.Chance+=1;
				Fen.dialogEnd();
				}
			}
//FLAG 35
		else if (Fen.Flag==35){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "En sortant de la chambre d'hotes, vous decidez de retourner vers l'ouest.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1) Fen.TesterHabileteLocalisation();
			else if (Fen.dialog==2){
				String t = "";
				if (Fen.TesterHabileteLocalisationResult==1);
				if (Fen.TesterHabileteLocalisationResult==2) t = "Vous ne trouvez rien et rebroussez chemin.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				if (Fen.TesterHabileteLocalisationResult==1) Fen.Flag=36;
				if (Fen.TesterHabileteLocalisationResult==2) Fen.Flag=22;
				Fen.TesterHabileteLocalisationEnd();
				Fen.dialogEnd();
				}
			}
//FLAG 36
		else if (Fen.Flag==36){
			Flag35Done=1;
			if (Fen.dialog!=2) Fen.dialogStart();
			else Fen.dialogOn=1;
			if (Fen.dialog==0){
				String t = "Au bout du couloir, vous reperez une porte derobee habilement dissimulee dans la paroi ouest.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.ChoiceType2On=1;
				Fen.dialogEnd();
				}
			else if (Fen.dialog==2){
				String t = "Vous rebroussez chemin.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.Flag=22;
				Fen.dialogEnd();
				}
			if (Fen.ChoiceType2On==1){
				switch(Fen.Choice){
					default:Fen.Question("Souhaitez-vous ouvrir la porte ?");break;
					case 1:Fen.Flag=37;Fen.ChoiceReset();break;
					case 2:Fen.dialog=2;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 37
		else if (Fen.Flag==37){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Derriere la porte secrete, vous decouvrez un escalier descendant vers le nord. Les marches sont taillees dans le roc et couvertes d'un limon visqueux, "+
					"aussi devez-vous prendre garde de ne pas glisser.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Il fait noir comme dans un four.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "";
				if (Fen.IsLampFilled) t = "Grace a votre lanterne, vous parvenez a ne pas trebucher.";
				else if (Fen.Grimoire[2]==1 && Fen.Magie>0){
					//Fen.Question("Lancer le sortilege Lumiere ?");
					Fen.dialogEnd();
					}
				else if (Fen.IsLightMagicOn) t = "Grace au sortilege de Lumiere, vous parvenez a ne pas trebucher.";
				else Fen.dialog=3;
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				String t = "";
				if (Fen.IsLampFilled || Fen.IsLightMagicOn) Fen.dialog=4;
				else t = "Vous n'y voyez rien, vous trebuchez, tombez, et perdez 2 points d'ENDURANCE.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					if (!Fen.IsLampFilled && !Fen.IsLightMagicOn) Fen.Endurance-=2;
					Fen.IsLightMagicOn=false;
					}
				String t = "L'escalier se termine face a une porte qui ne semble pas tres solide. Elle n'a pas de serrure mais elle est ornee d'un motif scintillant ou s'entrecroisent des runes magiques.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==5){
				String t ="";
				if (Fen.Perso==4) t = "Puisque vous etes Sallazar, vous pouvez decrypter ces runes.";
				else t = "Vous n'etes pas Magicien, vous n'arrivez pas a decrypter ces runes.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6){
				if (Fen.Perso==4) Fen.Flag=38;
				else Fen.ChoiceType3On=1;
				Fen.dialogEnd();
				}				
			if (Fen.ChoiceType2On==1){ //Pour Lumière
				Fen.Question("Lancer le sortilege Lumiere ?");
				switch(Fen.Choice){
					case 1:Fen.Magie-=1;Fen.dialog=2;Fen.IsLightMagicOn=true;Fen.ChoiceReset();break;
					case 2:Fen.dialog=3;Fen.ChoiceReset();break;
					}
				}
			else if (Fen.ChoiceType3On==1){
				PanTab.Text1="Pousser la porte";
				PanTab.Text2="Lancer un sortilege d'Ouverture";
				PanTab.Text2bis="pour 1 point de Magie";
				PanTab.Text3="Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=39;Fen.ChoiceReset();break;
					case 2:if (Fen.Magie>=1){
						Fen.Magie-=1;
						Fen.Flag=40;
						Fen.ChoiceReset();
						}break;
					case 3:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 38
		else if (Fen.Flag==38){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Ces runes vous avertissent de ne pas entrer. D'apres leur dessin, il vous semble reconnaitre un element ayant trait aux Elfes Noirs. Neanmoins, "+
					"la facon dont elles ont ete tracees vous laisse perplexe: on n'y retrouve pas le style caracteristique des Magiciens. De quel genre de creature peuvent-elles "+
					"donc provenir ?";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1) Fen.TesterHabileteLocalisation();
			else if (Fen.dialog==2){
				if (Fen.TesterHabileteLocalisationResult==1) Fen.Flag=41;
				else if (Fen.TesterHabileteLocalisationResult==2) Fen.ChoiceType3On=1;
				Fen.TesterHabileteLocalisationEnd();
				Fen.dialogEnd();
				}		
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Pousser la porte";
				PanTab.Text2="Lancer un sortilege d'Ouverture";
				PanTab.Text2bis="pour 1 point de Magie";
				PanTab.Text3="Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=39;Fen.ChoiceReset();break;
					case 2:if (Fen.Magie>=1){
						Fen.Magie-=1;
						Fen.Flag=40;
						Fen.ChoiceReset();
						}break;
					case 3:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 39
		else if (Fen.Flag==39){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Cette salle souterraine est encombree d'un amoncellement de meubles et d'un invraisemblable "+
					"bric-a-brac d'objets divers. Les murs sont couverts de symboles magiques et de signes cabalistiques. "+
					"Soudain, vous reperez une creature recroquevillee dans un coin. C'est un Elfe Noir, sec comme un coup de trique. "+
					"Il tient un long poignard dans la main droite et, dans l'autre, une poignee de cendres qui filent entre ses doigts.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Nul doute qu'il se prepare a vous jeter un sort !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				Fen.dialogEnd();
				Fen.ChoiceType4On=1;
				}
			if (Fen.ChoiceType4On==1){
				PanTab.Text1="Attaquer l'Elfe Noir";
				PanTab.Text2="Prononcer son nom";
				PanTab.Text3="Tenter de negocier";
				PanTab.Text4="Prendre la fuite";
				switch(Fen.Choice){
					case 1:Fen.Flag=43;Fen.ChoiceReset();break;
					case 2:Fen.Flag=44;Fen.ChoiceReset();break;
					case 3:Fen.Flag=45;Fen.ChoiceReset();break;
					case 4:Fen.Flag=46;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 40
		else if (Fen.Flag==40){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Helas ! Ces runes sont destinees a reagir contre toute espece de sortilege. Elles explosent brusquement, liberant un arc d'electricite incandescent.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "";
				if (Fen.ArmorEquiped==2) t = "Vous perdez 6 points d'ENDURANCE, car, comme vous venez d'avoir la preuve, le metal est un excellent conducteur !";
				else t = "Vous perdez 4 points d'ENDURANCE.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					if (Fen.ArmorEquiped==2) Fen.Endurance-=6;
					else Fen.Endurance-=4;
					}
				String t = "Derriere la porte, vous percevez des bruits confus : grattements, grognements et cris. Vous n'avez pas le temps de prendre un Repas ou de boire une potion pour reprendre des forces. "+
					"Etant donne votre faiblesse, il serait peut-etre plus prudent de vous esquiver et de retourner dans le hall principal. Ou alors, si vous preferez en avoir le coeur net, vous pouvez affronter le danger "+
					"qui se trouve derriere cette porte.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.ChoiceType2On=1;
				Fen.MenuBlockedOn=1;
				Fen.dialogEnd();
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Rebrousser chemin";
				PanTab.Text2="Ouvrir la porte";
				switch(Fen.Choice){
					case 1:Fen.Flag=22;Fen.ChoiceReset();Fen.MenuBlockedOn=0;break;
					case 2:Fen.Flag=39;Fen.ChoiceReset();Fen.MenuBlockedOn=0;break;
					}
				}
			}
//FLAG 41
		else if (Fen.Flag==41){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Ces runes sont etrangement deformees... En realite, elles recelent un traquenard contre les tentatives d'intrusion magique ! Vous vous felicitez de n'avoir pas "+
					"jete de sortilege d'Ouverture, car cela aurait inevitablement declenche le piege en question. Avec la perspicacite qui vous caracterise, vous en deduisez que celui qui se "+
					"cache derriere cette porte craint particulierement les Magiciens !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.ChoiceType2On=1;
				Fen.dialogEnd();
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Ouvrir la porte";
				PanTab.Text2="Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=39;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 42
		else if (Fen.Flag==42){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Il est evident que ces cercles magiques consituent un systeme de protection : l'occupant de ces lieux doit redouter plus que tout les attaques de sorcellerie, "+
					"et il est peu probable qu'il accepte de discuter avec vous. Si vous tenez a utiliser vos pouvoirs magiques dans le combat qui s'annonce, votre Force d'Attaque "+
					"diminuera de 2 points a cause des cercles defensifs que l'Elfe Noir a disposes a l'entree de son repaire. Mais que voulez-vous faire au juste ?";
				Fen.DisplayText(t,3);
				}
			if (Fen.dialog==0){
				Fen.ChoiceType2On=1;
				Fen.dialogEnd();
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Attaquer l'Elfe Noir";
				PanTab.Text2="Tourner les talons";
				switch(Fen.Choice){
					case 1:Fen.Flag=43;Fen.ChoiceReset();break;
					case 2:Fen.Flag=46;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 43
		else if (Fen.Flag==43){
			Fen.dialogStart();
			if (Fen.FightEnded==1){
				Fen.FightEnded=0;
				Fen.dialog=1;
				}
			if (Fen.dialog==0){
				Fen.dialogEnd();
				Fen.FightOn=1;
				}
			else if (Fen.dialog==1){
				String t = "Vous commencez a fouiller la piece. Cela vous prendra un certain temps car elle est tres encombree. Profitez-en pour prendre un Repas.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Fen.TakeMeal();
					}
				String t = "En fin de compte, vous trouvez 3 Piece d'Or, une tapisserie en soie estimee a 4 Piece d'Or (si vous arrivez a la revendre a quelqu'un), "+
					"un parchemin portant un sortilege d'Ouverture, et un Anneau Magique. Ce butin vous rapporte 1 point de CHANCE.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					Fen.Gold+=3;
					Fen.Object[2]+=1;
					Fen.Treasure[0]+=1;
					if (Fen.Chance<Fen.ChanceTotalDepart) Fen.Chance+=1;
					}				
				String t = "Dans un coin, vous denichez egalement un journal ou sont consignees les notes personnelles de l'Elfe Noir.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){				
				String t = "Vous apprenez, en le lisant, qu'il etait a la solde du Demon-Squelette - dont les armees ont decime le Chateau d'Argent - mais qu'il redoutait "+
					"particulierement les pouvoirs d'un certain Remstar, Magicien Fantome tapi derriere les Entrailles de Feu.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==5){
				String t = "L'Elfe Noir fait aussi etat des experiences de necromancie qu'il pratiquait dans la salle d'Argent. Il se vante d'avoir donne vie a d'innombrables monstres mutants "+
					"dont la description ne vous dit rien qui vaille.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6){				
				String t = "Maintenant, il est l'heure de poursuivre ailleurs vos recherches.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==7){
				Fen.Flag=22;
				Fen.dialogEnd();
				}
			}
//FLAG 44

		else if (Fen.Flag==44){
			if (Fen.KeywordOn==0){
				Fen.KeywordOn=1;
				Fen.Keyword="";
				}
			if (Fen.KeywordOn==2){
				if (Fen.TestKeyword("BonNom")){
					Fen.Flag=22; //TEST
					Fen.KeywordOn=0;
					}
				//else Fen.KeywordOn=1;
				else Fen.dialogStart();
				if (Fen.dialog==0){
					String t = "Vous vous etes trompe de nom et allez devoir combattre l'Efle Noir !";
					Fen.DisplayText(t,3);
					}
				else if (Fen.dialog==1){
					Fen.dialogEnd();
					Fen.Flag=43;
					Fen.KeywordOn=0;
					}
				}
			}
//FLAG 45
		else if (Fen.Flag==45){
			Fen.dialogStart();
			if (Fen.dialog==0) Fen.TenterChance();
			else if (Fen.dialog==1){
				String t = "";
				if (Fen.TenterChanceResult==1) t = "L'Elfe Noir se laisse convaincre et ne vous attaque pas.";
				else if (Fen.TenterChanceResult==2){
					t = "L'Elfe Noir profere quelques paroles magiques qui concentrent sur votre personne un eclair de feu : vous perdez 4 points d'ENDURANCE ! "+
					"Il ne vous reste plus qu'a choisir entre la lutte et la fuite.";
					}
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				if (Fen.TenterChanceResult==1) Fen.Flag=47;
				else if (Fen.TenterChanceResult==2){
					Fen.Endurance-=4;
					Fen.ChoiceType2On=1;
					}
				Fen.dialogEnd();
				Fen.TenterChanceEnd();
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Attaquer l'Efle Noir";
				PanTab.Text2="Prendre la fuite";
				switch(Fen.Choice){
					case 1:Fen.Flag=43;Fen.ChoiceReset();break;
					case 2:Fen.Flag=46;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 46
		else if (Fen.Flag==46){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Alors que vous remontez l'escalier quatre a quatre, l'Elfe Noir lance un poignard dans votre direction.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1) Fen.TesterHabilete(1);
			else if (Fen.dialog==2){
				String t = "";
				if (Fen.TesterHabileteResult==1) t = "Vous esquivez l'attaque !";
				else if (Fen.TesterHabileteResult==2) t = "La lame se plante dans votre dos et vous perdez 2 points d'ENDURANCE.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				if (Fen.TesterHabileteResult==2) Fen.Endurance-=2;
				Fen.dialogEnd();
				Fen.Flag=22;
				Fen.TesterHabileteEnd();
				}
			}
//FLAG 47
		else if (Fen.Flag==47){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "L'Elfe Noir ne parait nullement dispose a bavarder avec vous, bien au contraire ! Il vous ordonne de sortir sur-le-champ, tout en prenant soin de se caler, dos au mur. "+
					"Il n'a pas l'air de vouloir prendre l'initiative de vous attaquer mais il n'hesitera sans doute pas a vous jeter un sort si c'est vous qui l'agressez.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Attaquer l'Elfe Noir";
				PanTab.Text2="Quitter les lieux pour explorer un autre endroit";
				switch(Fen.Choice){
					case 1:Fen.Flag=43;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}

//FLAG 29
		else if (Fen.Flag==29){
			Fen.dialogStart();
			if (Flag48FightDone==1){
				if (Flag49SearchDone==1) Fen.Flag=49;
				else Fen.Flag=48;
				Fen.dialogEnd();
				}
			if (Fen.dialog==0){		
				String t = "Apres avoir pousse les deux battants, vous penetrez dans une immense piece faiblement eclairee. Grace a votre Lanterne, vous avez un meilleur apercu des lieux : il s'agit d'une "+
					"salle a manger occupee en son centre par une tres longue table autour de laquelle sont assis de bien tristes convives : des squelettes en haillons, miserables loques de ce que furent "+
					"jadis de superbes atours.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Tandis que vous contemplez pensivement cette scene macabre, une HORDE DE RATS en furie fait soudain irruption !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				Fen.ChoiceType2On=1;
				Fen.dialogEnd();
				}	
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Combattre la Horde de Rats";
				PanTab.Text2="Prendre la fuite";
				switch(Fen.Choice){
					case 1:Fen.Flag=48;Fen.ChoiceReset();break;
					case 2:Fen.ChoiceReset();Fen.ChoiceType2On=2;Fen.dialogStart();Fen.dialog=3;break;
					}
				}
			if (Fen.ChoiceType2On==2){
				if (Fen.dialog==3) Fen.TesterHabilete();
				else if (Fen.dialog==4){
					if (Fen.TesterHabileteResult==1) Fen.Flag=22;
					else if (Fen.TesterHabileteResult==2) Fen.Flag=48;
					Fen.TesterHabileteEnd();
					//System.out.println("Passer1");
					Fen.dialogEnd();
					Fen.ChoiceReset();
					//System.out.println("Passer2");
					}
				}
			}
//FLAG 48
		else if (Fen.Flag==48){
			Fen.dialogStart();
			if (Flag48FightDone==1){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				}
			if (Fen.FightEnded==1){
				Fen.FightEnded=0;
				Fen.dialog=2;
				}
			if (Fen.dialog==0){
				String t = "La vermine se jette sur vous comme un seul homme !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Fen.FightOn=1;
				}
			else if (Fen.dialog==2){
				String t = "D'autres Rats sont deja en train de se rapprocher... vous les entendez couiner avec voracite de l'autre cote de la cloison !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				Flag48FightDone=1;
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Fouiller la salle a manger";
				PanTab.Text2="Quitter les lieux";
				switch(Fen.Choice){
					case 1:Fen.Flag=49;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 49
		else if (Fen.Flag==49){
			Fen.dialogStart();
//###
			if (Flag49SearchDone==1 && Fen.ChoiceType3On==0){
//###
				Fen.dialog=4;
				Flag49SearchDone=0;
				}
			if (Fen.dialog==0){
				String t = "Autour de la table siegent les nobles et les gens de la cour du roi Kraal - ou du moins ce qu'il en reste, car ils ne sont plus que l'ombre d'eux-memes: "+
					" squelettes raides et silencieux, vetus de loques et de pourpoints manges aux mites.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Par les passe-plats amenages dans le mur nord de cette vaste et sombre salle, vous apercevez les cuisines. On y accede probablement par l'exterieur, en empruntant la "+
					"premiere porte du couloir qui longe la salle vers le nord.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Sur la table, toute l'argenterie a disparu (les pillards sont evidemment passes par-la depuis belle lurette). Vous remarquez toutefois deux bols en faience finement ouvrages "+
					"qui ont surement une certaine valeur et vous les prenez.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				if (Fen.dialogChangedOn==1){
					Fen.dialogChangedOn=0;
					//Fen.Treasure[1]=2;
					Fen.AddTreasure(1);
					Fen.AddTreasure(1);
					Fen.TakeMeal();
					}
				String t = "Etant donne les dimensions de la piece, il faudra un bon moment pour en examiner tous les recoins, vous devez donc prendre un Repas pendant ce temps.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				String t = "Une atmosphere etrange et troublante se degage de ces lieux empreints de mort. Vous avez l'impression que l'esprit des disparus plane encore autour de la table.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==5){
				Fen.ChoiceType3On=1;
				Flag49SearchDone=1;
				Fen.dialogEnd();
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Depenser 1 point de MAGIE pour entrer en";
				PanTab.Text1bis="contact avec les manes des anciens nobles";
				PanTab.Text2="Aller vers les cuisines";
				PanTab.Text3="Quitter les lieux";
				switch(Fen.Choice){
					case 1:if (Fen.Magie>=1){
						Fen.Magie-=1;
						Fen.Flag=50;
						Fen.ChoiceReset();
						}
						break;
					case 2:Fen.Flag=30;Fen.ChoiceReset();break;
					case 3:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}

//FLAG 50
		else if (Fen.Flag==50){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Sous l'influence de l'energie magique que vous venez de liberer, l'atmosphere de la salle se trouble pendant une fraction de seconde, puis vous voyez "+
					"se dessiner la silhouette fantomatique d'un jeune Chevalier. Il vous observe avec mefiance mais, lorsque vous lui revelez le but de votre mission, il se declare "+
					"pret a vous parler.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Vous obtiendrez sans doute de l'aide dans le temple qui se trouve a l'ouest d'ici. En face, vous trouvez une fontaine magique dont les eaux ont des pouvoirs curatifs - "+
					"meme s'il existe une forme de pouvoir bien plus puissante au-dessus.";
				Fen.DisplayText(t,1,"Fantome du jeune Chevalier",2,28);
				}
			else if (Fen.dialog==2){
				String t = "A l'etage superieur, mefiez-vous du grand chef des Orques qui a elu domicile dans la salle du Trone. Il repond au nom de Thulu et regne sur toute une population de monstres "+
					"Morts Vivants, de mutants et d'Orques de la pire espece. Cet ignoble personnage possede cependant une epee magique de grande valeur !";
				Fen.DisplayText(t,1,"Fantome du jeune Chevalier",2,28);
				}
			else if (Fen.dialog==3){
				String t = "On accede a la salle du Trone par de grandes portes en fer forge, vous ne pourrez pas les rater. Je vous conseille de ne pas aller dans les oubliettes du chateau ; elles sont gardees "+
					"par un redoutable Golem. Neanmoins, les anciens Magiciens qui y ont ete enfermes ont certainement laisse des tresors de magie.";
				Fen.DisplayText(t,1,"Fantome du jeune Chevalier",2,28);
				}
			else if (Fen.dialog==4){
				String t = "En revanche, il serait bon d'essayer de rencontrer Triple-Oeil, un marchant surnomme ainsi parce qu'il ne se separe jamais de son monocle de verre. C'est un vieux fou, mais il fait le commerce "+
					"de tout un tas d'objets interessants, y compris certains articles magiques. Aussi incroyable que cela puisse paraitre, il s'est barricade dans un coin du chateau et a reussi a survivre durant toutes "+
					"ces annees. Si vous penetrez dans les ailes ouest et est, faites bien attention...";
				Fen.DisplayText(t,1,"Fantome du jeune Chevalier",2,28);
				}
			else if (Fen.dialog==5){
				String t = "L'image du jeune Chevalier s'estompe petit a petit.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6){
				String t = "Et aussi, prenez bien garde a Grool, gardien de...";
				Fen.DisplayText(t,1,"Fantome du jeune Chevalier",2,28);
				}
			else if (Fen.dialog==7){
				String t = "Mais le fantome a disparu.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==8){
				String t = "Il est temps de vous remettre en route, car deja d'autres rats se rapprochent.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==9){
				Fen.dialogEnd();
				Fen.Flag=22;
				Flag49Done=1;
				}
			}

//FLAG 30
		else if (Fen.Flag==30){
			if (Flag51Done==0) Fen.dialogStart();
			else Fen.ChoiceType2On=1;
			if (Fen.dialog==0){
				String t = "En contemplant la grande cheminee du mur nord avec ses cremailleres, ses chaudrons et ses broches a rotir, vous comprenez "+
					"que vous vous trouvez dans les anciennes cuisines de la partie basse du chateau.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Sur votre gauche, le mur est perce de quelques passe-plats a travers lesquels vous apercevez une tres vaste salle a manger ; il devrait etre possible d'y "+
					"acceder par la double porte donnant dans le couloir qui longe les cuisines.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Pour l'instant, vous n'arrivez pas a distinguer nettement ce qu'il y a dans cette piece, car elle est plongee dans la penombre.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				String t = "A part une bouteille vide, vous ne trouvez rien d'interessant dans ces cuisines. A tout hasard, vous ouvrez la porte du mur ouest. Elle donne sur un petit passage "+
					"desservant deux minuscules debarras et un grand cellier garni de placards. Il y a peu de chances d'y trouver quelque chose de comestible apres toutes ces annees.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1 = "Utiliser un sortilege de Lumiere";
				PanTab.Text2 = "Passer la porte du mur ouest";
				PanTab.Text3 = "Rebrousser chemin";
				switch(Fen.Choice){
					case 1:if (Fen.Grimoire[2]==1 && Fen.Magie>=1){
						Fen.Magie-=1;
						Fen.Flag=51;
						Fen.ChoiceReset();
						}
						break;
					case 2:Fen.Flag=52;Fen.ChoiceReset();break;
					case 3:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1 = "Passer la porte du mur ouest";
				PanTab.Text2 = "Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=52;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}

//FLAG 51
		else if (Fen.Flag==51){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Votre sortilege de Lumiere vous permet d'assister a un repoussant spectacle dans la salle a manger : la table et les sieges sont couverts de "+
					"gros Rats noirs ! Mieux vaut ne pas risquer a entrer la-dedans.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Flag51Done=1;
				Fen.ChoiceType2On=1;
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1 = "Passer la porte du mur ouest";
				PanTab.Text2 = "Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=52;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 52
		else if (Fen.Flag==52){
			Fen.dialogStart();
			if (Fen.FightEnded==1){
				Fen.FightEnded=0;
				Fen.dialog=9;
				}
			if (Flag52FightDone==1){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				}
			if (Fen.dialog==0){
				String t = "Il n'y a partout que des sacs de farine et de céréales moisies, des debris de vaisselle et des restes de nourritures avariee. Par bonheur, "+
					"vous denichez tout de meme quelques bocaux de fruits intacts, ainsi que des conserves de garva, une racine comestible tres nutritive que l'on cultive "+
					"dans la lointaine ile du Crabe.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				if (Fen.dialogChangedOn==1){
					Fen.Provisions+=4;
					if (Fen.Provisions>12) Fen.Provisions=12;
					Fen.Chance+=1;
					Fen.dialogChangedOn=0;
					}
				String t = "Vous arrivez a vous faire 4 PROVISIONS supplémentaires et votre CHANCE augmente de 1 point.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Helas ! La joie d'avoir fait ces trouvailles est immediatement gachee par l'echo d'un ignoble reniflement qui vous fait fremir de la tete aux pieds.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				String t = "D'apres la direction du bruit, une creature semble se frayer un chemin jusqu'a vous, vous barrant le passage vers la sortie. Autant en avoir le coeur net "+
					"et ammer voir de quoi il s'agit !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==4){
				String t = "Ce qui apparait alors vous glace le sang et vous souleve le coeur de degout : une espece de grand Lezard blanc de deux metres de long, a la peau couverte de "+
					"pustules jaunes cernees d'aureoles rosatres. Mais le pire, c'est que cette horreur rampante semble etre faite de plusieurs morceaux de creatures differentes (y compris "+
					"un ou deux humains, a en juger par les mains qui terminent les pattes avant).";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==5){
				String t = "Tout a coup, le monstre pousse un sifflement rauque et crache un jet de bave infecte !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==6) Fen.TesterHabilete();
			else if (Fen.dialog==7){
				String t = "";
				if (Fen.TesterHabileteResult==1) t = "Vous evitez le flux immonde.";
				if (Fen.TesterHabileteResult==2){
					t = "Vous recevez le flux immonde en pleine figure. Suffocant et bleme, a moitie aveugle, votre Force d'Attaque sera reduite de 2 points durant tout le combat.";
					PanFight.Flag52BadLuck=1;
					}
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==8){
				Fen.TesterHabileteEnd();
				Fen.dialogEnd();
				Fen.FightOn=1;
				}
			else if (Fen.dialog==9){
				String t = "Le Lezard a laisse une trainee d'humeur visqueuse.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==10){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				Flag52FightDone=1;
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Suivre la trace";
				PanTab.Text2="Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=53;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
//FLAG 53
		else if (Fen.Flag==53){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "La trace visqueuse laissee par l'Heterolezard vous conduit hors des cuisines.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Une fois sorti, vous la suivez vers le nord, jusqu'a l'endroit ou le couloir tourne vers l'est. Il n'y a qu'une seule porte "+
					"sur la gauche, mais la trace, elle, continue tout droit... puis s'arrete au pied du mur !";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				String t = "Tout porte a croire qu'il y a donc une porte derobee dans les parages. Effectivement, vous en decouvrez une, parfaitement dissimulee "+
					"dans la paroi. Comme les Lezards n'ont pas l'habitude de refermer les portes derriere eux, vous en deduisez qu'un etre doue d'intelligence a "+
					"declenche l'ouverture de la porte et l'a ensuite refermee a sa place.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				String t = "Si vous decidez d'ouvrir cette porte secrete, vous risquez de tomber sur un individu ruse et probablement malveillant";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				}
			if (Fen.ChoiceType2On==1){
				PanTab.Text1="Ouvrir la porte secrete";
				PanTab.Text2="Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=37;Fen.ChoiceReset();break;
					case 2:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}
			
//FLAG 31
		else if (Fen.Flag==31){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Malgre le solide verrou qui protege l'entree, vous parvenez a enfoncer la porte et vous penetrez dans la forge du chateau.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				String t = "Une immense cheminee occupe le mur sud, mais il y a longtemps que le feu ne rugit plus dans l'atre. "+
					"De toute evidence, les Orques et leurs acolytes se sont empares de toutes les armes et armures confectionnee par les forgerons.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==2){
				if (Flag31ObjectCollected==1) Fen.dialog=4;
				String t = "Vous decouvrez tout de meme une espece de pied de biche en acier. Si vous decidez de le garder, vous pourrez deduire 1 du nombre "+
					"obtenu aux des lorsque l'on vous demandera de tester votre HABILETE avant d'ouvrir une porte ou de forcer une serrure.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==3){
				Fen.dialogEnd();
				Fen.ChoiceType2On=1;
				}
			else if (Fen.dialog==4){
				String t = "Ensuite, vous quittez les lieux et, scrutant le couloir qui part vers l'est, vous apercevez deux portes sur la gauche. La premiere, munie d'un enorme verrou, semble particulierement robuste.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==5){
				Fen.dialogEnd();
				Fen.ChoiceType3On=1;
				}
			if (Fen.ChoiceType2On==1){
				Fen.Question("Garder le pied de biche en acier ?");
				switch(Fen.Choice){
					case 1:Fen.ChoiceReset();Fen.dialogStart();Fen.dialog=4;Fen.Object[8]+=1;Flag31ObjectCollected=1;break;
					case 2:Fen.ChoiceReset();Fen.dialogStart();Fen.dialog=4;break;
					}
				}
			if (Fen.ChoiceType3On==1){
				PanTab.Text1="Ouvrir la premiere porte";
				PanTab.Text2="Ouvrir la deuxieme porte";
				PanTab.Text3="Rebrousser chemin";
				switch(Fen.Choice){
					case 1:Fen.Flag=32;Fen.ChoiceReset();break;
					case 2:Fen.Flag=33;Fen.ChoiceReset();break;
					case 3:Fen.Flag=22;Fen.ChoiceReset();break;
					}
				}
			}

//FLAG 28
		else if (Fen.Flag==28){
			Fen.dialogStart();
			if (Fen.dialog==0){
				String t = "Vous voici a l'extremite d'un long couloir oriente vers l'ouest. Trois portes sont encastrees dans la paroi sud. En face de la premiere et de la deuxieme, vous apercevez l'entree d'un "+
					"passage partant vers le nord. Il y a egalement une porte dans la paroi nord, vis-a-vis de la troisieme porte cote sud. Face a vous, vous distinguez une autre porte, tout au bout du couloir, "+
					"juste avant que celui-ci oblique vers le nord. Vous etes a peu pres certain que ce couloir vous menera dans les profondeurs du Chateau d'Argent.";
				Fen.DisplayText(t,3);
				}
			else if (Fen.dialog==1){
				Fen.dialogEnd();
				}
			}

//
//ONWORK













//**************************************************************************************
//End Flag
		if (Fen.ZoneName!=""){
			g.setFont(PanTab.fontzonename);
			g.drawString(Fen.ZoneName,750-(int)(Fen.ZoneName.length()/2)*20,50);
			}
		if (Fen.Question!=""){
			g.setColor(Color.black);
			g.fillRect(750-(int)(Fen.Question.length()/2)*15-5, 780-30, ((int)(Fen.Question.length()/2)*15)*2, 38);
			g.setColor(Color.white);
			g.drawRect(750-(int)(Fen.Question.length()/2)*15-5, 780-30, ((int)(Fen.Question.length()/2)*15)*2, 38);
			g.setFont(PanTab.fontbold);
			g.drawString(Fen.Question,750-(int)(Fen.Question.length()/2)*15,780);
			}
		if (Fen.TenterChanceOn==1 || Fen.TenterChanceOn==2){
			if (Fen.TenterChanceOn==1) g.setColor(Color.black);
			else if (Fen.TenterChanceOn==2) g.setColor(Color.cyan);
			g.fillRect(750-190-5, 400-30, 380+10, 60);
			g.setColor(Color.yellow);
			g.drawRect(750-190-5, 400-30, 380+10, 60);
			g.setFont(new Font("Sylfaen", Font.BOLD, 40));
			g.setColor(Color.white);
			g.drawString("Tentez votre Chance",750-190, 410);
			}
		if (Fen.TesterHabileteOn==1 || Fen.TesterHabileteOn==2){
			if (Fen.TesterHabileteOn==1) g.setColor(Color.black);
			else if (Fen.TesterHabileteOn==2) g.setColor(Color.cyan);
			g.fillRect(750-200-5, 400-30, 400+10, 60);
			g.setColor(Color.green);
			g.drawRect(750-200-5, 400-30, 400+10, 60);
			g.setFont(new Font("Sylfaen", Font.BOLD, 40));
			g.setColor(Color.white);
			g.drawString("Testez votre Habilete",750-200, 410);
			}
		if (Fen.TesterHabileteLocalisationOn==1 || Fen.TesterHabileteLocalisationOn==2){
			if (Fen.TesterHabileteLocalisationOn==1) g.setColor(Color.black);
			else if (Fen.TesterHabileteLocalisationOn==2) g.setColor(Color.cyan);
			g.fillRect(750-290-50-5, 400-30, 580+100+10, 60);
			g.setColor(Color.blue);
			g.drawRect(750-290-50-5, 400-30, 580+100+10, 60);
			g.setFont(new Font("Sylfaen", Font.BOLD, 40));
			g.setColor(Color.white);
			g.drawString("Testez votre Habilete de localisation",750-290-50, 410);
			}




		//else if (Fen.TenterChanceOn==3){
			//Fen.DiceThrow(2);
			//}

				

		if (1==0){
			g.setColor(Color.red);
			g.drawLine(750,0,750,800);
			g.drawLine(0,400,1500,400);
			}
		}
	}
class SourisPan1 implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int X = event.getX();
		int Y = event.getY();
		if (Fen.Flag==3){
			if (185<X && X<1500-185){
				if (Fen.SelectedObjectOn==1) Fen.SelectedObject=1;
				if (Fen.SelectedObjectOn==2) Fen.SelectedObject=2;
				if (Fen.SelectedObjectOn==3) Fen.SelectedObject=3;
				if (Fen.SelectedObjectOn==4) Fen.SelectedObject=4;
				if (Fen.SelectedObjectOn==5) Fen.SelectedObject=5;
				if (Fen.SelectedObjectOn==6) Fen.SelectedObject=6;
				if (Fen.SelectedObjectOn==7) Fen.SelectedObject=7;
				if (Fen.SelectedObjectOn==8) Fen.SelectedObject=8;
				if (Fen.SelectedObjectOn==9) Fen.SelectedObject=9;
				if (Fen.SelectedObjectOn==10) Fen.SelectedObject=10;
				}
			if (Fen.Buying==1) Fen.Buying=2;
			}
		if (Fen.TenterChanceOn==2) Fen.TenterChanceOn=3;
		if (Fen.TesterHabileteOn==2) Fen.TesterHabileteOn=3;
		if (Fen.TesterHabileteLocalisationOn==2) Fen.TesterHabileteLocalisationOn=3;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class SourisMotionPan1 implements MouseMotionListener{
	public void mouseMoved(MouseEvent event){
		int X = event.getX();
		int Y = event.getY();
		if (Fen.Flag==3){
			if (185<X && X<1500-185){
				if (85+70*0<Y && Y<85+70*1) Fen.SelectedObjectOn=1;
				else if (85+70*1<Y && Y<85+70*2) Fen.SelectedObjectOn=2;
				else if (85+70*2<Y && Y<85+70*3) Fen.SelectedObjectOn=3;
				else if (85+70*3<Y && Y<85+70*4) Fen.SelectedObjectOn=4;
				else if (85+70*4<Y && Y<85+70*5) Fen.SelectedObjectOn=5;
				else if (85+70*5<Y && Y<85+70*6) Fen.SelectedObjectOn=6;
				else if (85+70*6<Y && Y<85+70*7) Fen.SelectedObjectOn=7;
				else if (85+70*7<Y && Y<85+70*8) Fen.SelectedObjectOn=8;
				else if (85+70*8<Y && Y<85+70*9) Fen.SelectedObjectOn=9;
				else if (85+70*9<Y && Y<85+70*10) Fen.SelectedObjectOn=10;
				else Fen.SelectedObjectOn=0;
				}
			else Fen.SelectedObjectOn=0;
			if (Fen.SelectedObject!=0 && 1350<X && X<1450 && 400-25<Y && Y<400+25) Fen.Buying=1;
			else Fen.Buying=0;
			}
//			g.fillRect(750-190-5, 400-30, 380+10, 60);
		if (Fen.TenterChanceOn==1 || Fen.TenterChanceOn==2){
			if (750-190-5<X && X<750-190-5+380+10 && 400-30<Y && Y<400-30+60){
				Fen.TenterChanceOn=2;
				}
			else Fen.TenterChanceOn=1;
			}
		if (Fen.TesterHabileteOn==1 || Fen.TesterHabileteOn==2){
			if (750-200-5<X && X<750-200-5+400+10 && 400-30<Y && Y<400-30+60){
				Fen.TesterHabileteOn=2;
				}
			else Fen.TesterHabileteOn=1;
			}
		if (Fen.TesterHabileteLocalisationOn==1 || Fen.TesterHabileteLocalisationOn==2){
			if (750-290-5<X && X<750-290-5+580+10 && 400-30<Y && Y<400-30+60){
				Fen.TesterHabileteLocalisationOn=2;
				}
			else Fen.TesterHabileteLocalisationOn=1;
			}
		}
	public void mouseDragged(MouseEvent event){}
	}

class TouchePan1 implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){}
	public void keyReleased(KeyEvent event){}
	}
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************
//PAN1
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************




class TimeDice extends Thread{
	public void run(){
		try{
			sleep(10);
			} catch (InterruptedException e){}
		Fen.DiceOn=0;
		//Fen.DiceTour+=1;
		}
	}


public class Zorca{
	public static void main(String[] args){
		int L=1500;
		int H=1000;
		Fen fen = new Fen("Zorca Adventure", L+16, H+40);
		Pan pan = new Pan(L,H);

		PanTab panTab = new PanTab(L,200);
		PanStat panS = new PanStat(L,800);
		PanFight panF = new PanFight(L,800);
		Pan0 pan0 = new Pan0(L,800);
		Pan1 pan1 = new Pan1(L,800);


		//pan.add(pan1, BorderLayout.NORTH);
		//pan.add(panTab, BorderLayout.SOUTH);
		pan.add(panS);
		pan.add(panF);
		pan.add(pan0);
		pan.add(pan1);
		pan.add(panTab);
		panS.setVisible(false);
		panF.setVisible(false);
		pan1.setVisible(false);
		pan1.pan1On=1;


		


		//title.setVisible(false);

		//fen.setLayout(new GridBagLayout());
		//fen.add(title);
		//fen.add(panText);
		fen.setContentPane(pan);

		for(int i=0; i<8; i++) fen.Object[i]=5;
		for(int i=0; i<8; i++) fen.Weapon[i]=5;
		for(int i=0; i<8; i++) fen.Armor[i]=5;
		for(int i=0; i<50; i++) fen.Treasure[i]=-1;
		for(int i=0; i<2; i++) fen.AddTreasure(i);
		for(int i=0; i<12; i++) fen.Grimoire[i]=1;



		while (true){
			pan.repaint();
			//if (panS.panStatOn==1) panS.setVisible(true);
			//else if (panS.panStatOn==0) panS.setVisible(false);

			//if (fen.dialogOn==0) panTab.Jtext.setVisible(false);

			if (fen.Flag==0) pan0.repaint();
			else if (fen.Flag>=1 && fen.MenuStatOn==0 && fen.FightOn==0){
				pan0.setVisible(false);
				panS.setVisible(false);
				panF.setVisible(false);
				//pan1.pan1On=1;
				if (fen.FightOn==0) pan1.setVisible(true);
				else pan1.setVisible(false);
				pan1.repaint();
				}

			else if (fen.MenuStatOn==1 && fen.FightOn==0){
				pan1.setVisible(false);
				panS.setVisible(true);
				panS.repaint();
				}
			else if (fen.FightOn==1){
				pan1.setVisible(false);
				panF.setVisible(true);
				panF.repaint();
				}


			panTab.repaint();
			if (fen.Flag>=20) fen.ZoneName="Dans le Chateau d'Argent";

			//if (fen.info==1) System.out.println(fen.MenuStatOn);

			try{
				Thread.sleep(0);
				} catch (InterruptedException e){}
			}
		
		}
}