import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import tp.Ex4;

class Fen extends JFrame{
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
// Function name to get case i&j if pieces:Pij()
// Empty = 0
/*White
*Pawn = 1
*Knight = 2
*Bishop = 3
*Rook = 4
*Queen = 5
*King = 6
*/
/*Black
*Pawn = 7
*Knight = 8
*Bishop = 9
*Rook = 10
*Queen = 11
*King = 12
*/
class Pan extends JPanel{
	static int WP1x=25;
	static int WP1y=625;
	static int WP1Selected=0;
	static int WP1Taken=0;
	static int WP1Promotion=0;
	static int WP2x=125;
	static int WP2y=625;
	static int WP2Selected=0;
	static int WP2Taken=0;
	static int WP2Promotion=0;
	static int WP3x=225;
	static int WP3y=625;
	static int WP3Selected=0;
	static int WP3Taken=0;
	static int WP3Promotion=0;
	static int WP4x=325;
	static int WP4y=625;
	static int WP4Selected=0;
	static int WP4Taken=0;
	static int WP4Promotion=0;
	static int WP5x=425;
	static int WP5y=625;
	static int WP5Selected=0;
	static int WP5Taken=0;
	static int WP5Promotion=0;
	static int WP6x=525;
	static int WP6y=625;
	static int WP6Selected=0;
	static int WP6Taken=0;
	static int WP6Promotion=0;
	static int WP7x=625;
	static int WP7y=625;
	static int WP7Selected=0;
	static int WP7Taken=0;
	static int WP7Promotion=0;
	static int WP8x=725;
	static int WP8y=625;
	static int WP8Selected=0;
	static int WP8Taken=0;
	static int WP8Promotion=0;
	static int WK1x=125;
	static int WK1y=725;
	static int WK1Selected=0;
	static int WK1Taken=0;
	static int WK2x=625;
	static int WK2y=725;
	static int WK2Selected=0;
	static int WK2Taken=0;
	static int WB1x=225;
	static int WB1y=725;
	static int WB1Selected=0;
	static int WB1Taken=0;
	static int WB2x=525;
	static int WB2y=725;
	static int WB2Selected=0;
	static int WB2Taken=0;
	static int WR1x=25;
	static int WR1y=725;
	static int WR1Selected=0;
	static int WR1Taken=0;
	static int WR1NeverMoved=0;
	static int WR2x=725;
	static int WR2y=725;
	static int WR2Selected=0;
	static int WR2Taken=0;
	static int WR2NeverMoved=0;
	static int WQx=325;
	static int WQy=725;
	static int WQSelected=0;
	static int WQTaken=0;
	static int WKgx=425;
	static int WKgy=725;
	static int WKgSelected=0;
	static int WKgTaken=0;
	static int WKgNeverMoved=0;
	static int WKgCheck=0;

	static int BP1x=25;
	static int BP1y=125;
	static int BP1Selected=0;
	static int BP1Taken=0;
	static int BP1Promotion=0;
	static int BP2x=125;
	static int BP2y=125;
	static int BP2Selected=0;
	static int BP2Taken=0;
	static int BP2Promotion=0;
	static int BP3x=225;
	static int BP3y=125;
	static int BP3Selected=0;
	static int BP3Taken=0;
	static int BP3Promotion=0;
	static int BP4x=325;
	static int BP4y=125;
	static int BP4Selected=0;
	static int BP4Taken=0;
	static int BP4Promotion=0;
	static int BP5x=425;
	static int BP5y=125;
	static int BP5Selected=0;
	static int BP5Taken=0;
	static int BP5Promotion=0;
	static int BP6x=525;
	static int BP6y=125;
	static int BP6Selected=0;
	static int BP6Taken=0;
	static int BP6Promotion=0;
	static int BP7x=625;
	static int BP7y=125;
	static int BP7Selected=0;
	static int BP7Taken=0;
	static int BP7Promotion=0;
	static int BP8x=725;
	static int BP8y=125;
	static int BP8Selected=0;
	static int BP8Taken=0;
	static int BP8Promotion=0;
	static int BK1x=125;
	static int BK1y=25;
	static int BK1Selected=0;
	static int BK1Taken=0;
	static int BK2x=625;
	static int BK2y=25;
	static int BK2Selected=0;
	static int BK2Taken=0;
	static int BB1x=225;
	static int BB1y=25;
	static int BB1Selected=0;
	static int BB1Taken=0;
	static int BB2x=525;
	static int BB2y=25;
	static int BB2Selected=0;
	static int BB2Taken=0;
	static int BR1x=25;
	static int BR1y=25;
	static int BR1Selected=0;
	static int BR1Taken=0;
	static int BR1NeverMoved=0;
	static int BR2x=725;
	static int BR2y=25;
	static int BR2Selected=0;
	static int BR2Taken=0;
	static int BR2NeverMoved=0;
	static int BQx=325;
	static int BQy=25;
	static int BQSelected=0;
	static int BQTaken=0;
	static int BKgx=425;
	static int BKgy=25;
	static int BKgSelected=0;
	static int BKgTaken=0;
	static int BKgCheck=0;
	static int BKgNeverMoved=0;

	static int Turn=1;
	static int [][] Chessboard = new int [8][8];
	static int [][] Moveboard = new int [8][8];
	static int [][] Pieceboard = new int [8][8];
	static int [][] Whiteboard = new int [8][8];
	static int [][] Blackboard = new int [8][8];
	static int [][] Pawnboard = new int [8][8];
	static int SelectCaseX=0;
	static int SelectCaseY=0;
	static int Selected=0;
	static int CaseX=0;
	static int CaseY=0;
	static int Promotion=0;
	static int PromotionChoice=0;
	static int WhiteQueenNumber=1;
	static int WhiteRookNumber=2;
	static int BlackQueenNumber=1;
	static int BlackRookNumber=2;

	static int gameplay=0;
	static JButton Solo = new JButton("Solo");
	static JButton Duo = new JButton("Duo");
	static JButton PPA = new JButton("Piece Placing");
	static int Mat=0;

	static int OrdiSide = 0;
	JButton gris = new JButton();

	static int WhiteDangerOn=0;
	static int BlackDangerOn=0;

	static int T = 0;
	static int[][] Historical = new int[500][119];
	static int[][][] HistoChess = new int[500][8][8];
	static int[][][] HistoPiece = new int[500][8][8];
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));

		SourisSelection Sselect = new SourisSelection();
		this.addMouseListener(Sselect);

		SoloAction Saction = new SoloAction();
		Solo.setPreferredSize(new Dimension(150, 150));
		Solo.setVisible(true);
		Solo.addActionListener(Saction);
		this.add(Solo);

		DuoAction Sduo = new DuoAction();
		Duo.setPreferredSize(new Dimension(150, 150));
		Duo.setVisible(true);
		Duo.addActionListener(Sduo);
		this.add(Duo);

		gris.setPreferredSize(new Dimension(0,0));
		Touche touche = new Touche();
		gris.addKeyListener(touche);
		this.add(gris);

		PiecePlacingAction Sppa = new PiecePlacingAction();
		PPA.setPreferredSize(new Dimension(150, 150));
		PPA.setVisible(true);
		PPA.addActionListener(Sppa);
		this.add(PPA);
		}
	class SoloAction implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Pan.gameplay=1;
			}
		}
	class DuoAction implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Pan.gameplay=2;
			}
		}
	class PiecePlacingAction implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Pan.gameplay=3;
			}
		}
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		for(int i=0; i<3; i++) g.drawRect(22+i, 22+i, 805-2*i, 805-2*i);
		for(int i=0; i<8; i++){
			g.drawString(String.valueOf(i+1), 15, 775-i*100);
			}
		g.drawString("A", 75, 840);
		g.drawString("B", 175, 840);
		g.drawString("C", 275, 840);
		g.drawString("D", 375, 840);
		g.drawString("E", 475, 840);
		g.drawString("F", 575, 840);
		g.drawString("G", 675, 840);
		g.drawString("H", 775, 840);
		
		g.drawOval(420, 10, 10, 10);
		if (Turn==2) g.fillOval(420, 10, 10, 10);

		g.setColor(Color.gray);
		for(int i=0; i<8; i++){
			int D=0;
			if (i%2==0) D=100; 
			for(int j=0; j<4; j++){
				g.fillRect(25+j*200+D, 25+i*100, 100, 100);
				}
			}
//***Danger of pieces
		if (WhiteDangerOn==1){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Whiteboard[i][j]!=0){
						int C=Whiteboard[i][j]*28;
						g.setColor(new Color(0, 255-C, 255-C));
						g.fillRect(25+i*100, 725-j*100, 100, 100);
						g.setColor(Color.red);
						g.drawString(String.valueOf(Whiteboard[i][j]),25+i*100+50, 725-j*100+55);
						}
					}
				}
			}
		if (BlackDangerOn==1){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Blackboard[i][j]!=0){//&& (WhiteDangerOn==0 || Whiteboard[i][j]!=0){
						int C=Blackboard[i][j]*28;
						g.setColor(new Color(255-C, 255-C, 0));
						g.fillRect(25+i*100, 725-j*100, 100, 100);
						g.setColor(Color.red);
						g.drawString(String.valueOf(Blackboard[i][j]),25+i*100+50, 725-j*100+55);
						}
					}
				}
			}
		if (WhiteDangerOn==1 && BlackDangerOn==1){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Whiteboard[i][j]!=0 && Blackboard[i][j]!=0){
						int WC=Whiteboard[i][j]*28;
						int BC=Blackboard[i][j]*28;
						g.setColor(new Color(0, 255-WC, 255-WC));
						g.fillRect(25+i*100, 725-j*100, 100, 50);
						g.setColor(new Color(255-BC, 255-BC, 0));
						g.fillRect(25+i*100, 725-j*100+50, 100, 50);
						g.setColor(Color.red);
						g.drawString(String.valueOf(Whiteboard[i][j]),25+i*100+50, 725-j*100+30);						
						g.drawString(String.valueOf(Blackboard[i][j]),25+i*100+50, 725-j*100+80);						
						}
					}
				}
			}
//*End Danger

		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Moveboard[i][j]=0;
				Whiteboard[i][j]=0;
				Blackboard[i][j]=0;
				}
			}
if (gameplay!=3){
//***White Danger
		WhitePieceDanger();
		
//***Black Danger
		BlackPieceDanger();

//***Fin Danger
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (WKgx==25+i*100 && WKgy==725-j*100){
					if (Blackboard[i][j]==1){
						WKgCheck=1;
						g.setColor(Color.magenta);
						g.fillRect(25+i*100, 725-j*100, 100, 100);
						}
					else WKgCheck=0;
					}
				if (BKgx==25+i*100 && BKgy==725-j*100){
					if (Whiteboard[i][j]==1){
						BKgCheck=1;
						g.setColor(Color.magenta);
						g.fillRect(25+i*100, 725-j*100, 100, 100);
						}
					else BKgCheck=0;
					}
				}
			}
		
		if ((WP1y==25 && WP1Promotion==0 && WP1Taken==0) || (WP2y==25 && WP2Promotion==0 && WP2Taken==0) || (WP3y==25 && WP3Promotion==0 && WP3Taken==0) || (WP4y==25 && WP4Promotion==0 && WP4Taken==0) || (WP5y==25 && WP5Promotion==0 && WP5Taken==0) || (WP6y==25 && WP6Promotion==0 && WP6Taken==0) || (WP7y==25 && WP7Promotion==0 && WP7Taken==0) || (WP8y==25 && WP8Promotion==0 && WP8Taken==0) ||
		   (BP1y==725 && BP1Promotion==0 && BP1Taken==0) || (BP2y==725 && BP2Promotion==0 && BP2Taken==0) || (BP3y==725 && BP3Promotion==0 && BP3Taken==0) || (BP4y==725 && BP4Promotion==0 && BP4Taken==0) || (BP5y==725 && BP5Promotion==0 && BP5Taken==0) || (BP6y==725 && BP6Promotion==0 && BP6Taken==0) || (BP7y==725 && BP7Promotion==0 && BP7Taken==0) || (BP8y==725 && BP8Promotion==0 && BP8Taken==0)) PromotionChoice=1;
		else PromotionChoice=0;
		if (PromotionChoice==1){
			if (gameplay==1){
				if (OrdiSide==1 && Turn==2){
					if (WhiteQueenNumber<2) Promotion=5;
					else if (WhiteRookNumber<3) Promotion=4;
					else Promotion=2+(int)(Math.random()*2);
					}
				else if (OrdiSide==2 && Turn==1){
					if (BlackQueenNumber<2) Promotion=5;
					else if (BlackRookNumber<3) Promotion=4;
					else Promotion=2+(int)(Math.random()*2);
					}
				}
			if (WP1y==25 && WP1Promotion==0) WP1Promotion=Promotion;
			else if (WP2y==25 && WP2Promotion==0) WP2Promotion=Promotion;
			else if (WP3y==25 && WP3Promotion==0) WP3Promotion=Promotion;
			else if (WP4y==25 && WP4Promotion==0) WP4Promotion=Promotion;
			else if (WP5y==25 && WP5Promotion==0) WP5Promotion=Promotion;
			else if (WP6y==25 && WP6Promotion==0) WP6Promotion=Promotion;
			else if (WP7y==25 && WP7Promotion==0) WP7Promotion=Promotion;
			else if (WP8y==25 && WP8Promotion==0) WP8Promotion=Promotion;
			else if (BP1y==725 && BP1Promotion==0) BP1Promotion=Promotion;
			else if (BP2y==725 && BP2Promotion==0) BP2Promotion=Promotion;
			else if (BP3y==725 && BP3Promotion==0) BP3Promotion=Promotion;
			else if (BP4y==725 && BP4Promotion==0) BP4Promotion=Promotion;
			else if (BP5y==725 && BP5Promotion==0) BP5Promotion=Promotion;
			else if (BP6y==725 && BP6Promotion==0) BP6Promotion=Promotion;
			else if (BP7y==725 && BP7Promotion==0) BP7Promotion=Promotion;
			else if (BP8y==725 && BP8Promotion==0) BP8Promotion=Promotion;
			Promotion=0;
			}
 

		if ((Turn==1 && isWhiteKingPat()) || (Turn==2 && isBlackKingPat())) Mat=3;
		if (isWhiteKingMat()) Mat=1;
		if (isBlackKingMat()) Mat=2;
		if (Mat==0 && gameplay==1 && OrdiSide==2 && PromotionChoice==0) while (Turn==2) Ordi2(OrdiSide);
		if (Turn==3) Turn=1;
		if (Mat==0 && Selected==1){
			g.setColor(Color.blue);
			g.fillRect(SelectCaseX, SelectCaseY, 100, 100);
//*****Move
//***White
			if (Turn==1){
//***Pawn
				if (SelectCaseX==WP1x && SelectCaseY==WP1y && WP1Taken==0) WP1Selected=1;
				else WP1Selected=0;
				if (WP1Selected==1 && WP1Promotion==0){
					MoveWhitePawn(WP1y, CaseX, CaseY);
					}
				if (SelectCaseX==WP2x && SelectCaseY==WP2y && WP2Taken==0) WP2Selected=1;
				else WP2Selected=0;
				if (WP2Selected==1 && WP2Promotion==0){
					MoveWhitePawn(WP2y, CaseX, CaseY);
					}
				if (SelectCaseX==WP3x && SelectCaseY==WP3y && WP3Taken==0) WP3Selected=1;
				else WP3Selected=0;
				if (WP3Selected==1 && WP3Promotion==0){
					MoveWhitePawn(WP3y, CaseX, CaseY);
					}
				if (SelectCaseX==WP4x && SelectCaseY==WP4y && WP4Taken==0) WP4Selected=1;
				else WP4Selected=0;
				if (WP4Selected==1 && WP4Promotion==0){
					MoveWhitePawn(WP4y, CaseX, CaseY);
					}
				if (SelectCaseX==WP5x && SelectCaseY==WP5y && WP5Taken==0) WP5Selected=1;
				else WP5Selected=0;
				if (WP5Selected==1 && WP5Promotion==0){
					MoveWhitePawn(WP5y, CaseX, CaseY);
					}
				if (SelectCaseX==WP6x && SelectCaseY==WP6y && WP6Taken==0) WP6Selected=1;
				else WP6Selected=0;
				if (WP6Selected==1 && WP6Promotion==0){
					MoveWhitePawn(WP6y, CaseX, CaseY);
					}
				if (SelectCaseX==WP7x && SelectCaseY==WP7y && WP7Taken==0) WP7Selected=1;
				else WP7Selected=0;
				if (WP7Selected==1 && WP7Promotion==0){
					MoveWhitePawn(WP7y, CaseX, CaseY);
					}
				if (SelectCaseX==WP8x && SelectCaseY==WP8y && WP8Taken==0) WP8Selected=1;
				else WP8Selected=0;
				if (WP8Selected==1 && WP8Promotion==0){
					MoveWhitePawn(WP8y, CaseX, CaseY);
					}
//***Rook
				if (SelectCaseX==WR1x && SelectCaseY==WR1y && WR1Taken==0) WR1Selected=1;
				else WR1Selected=0;
				if (SelectCaseX==WR2x && SelectCaseY==WR2y && WR2Taken==0) WR2Selected=1;
				else WR2Selected=0;
				if ((WR1Selected==1 || WR2Selected==1) || (WP1Selected==1 && WP1Promotion==4) || (WP2Selected==1 && WP2Promotion==4) || (WP3Selected==1 && WP3Promotion==4) || (WP4Selected==1 && WP4Promotion==4) || (WP5Selected==1 && WP5Promotion==4) || (WP6Selected==1 && WP6Promotion==4) || (WP7Selected==1 && WP7Promotion==4) || (WP8Selected==1 && WP8Promotion==4)){
					MoveWhiteRook(CaseX, CaseY);
					}
//***Bishop
				if (SelectCaseX==WB1x && SelectCaseY==WB1y && WB1Taken==0) WB1Selected=1;
				else WB1Selected=0;
				if (SelectCaseX==WB2x && SelectCaseY==WB2y && WB2Taken==0) WB2Selected=1;
				else WB2Selected=0;
				if ((WB1Selected==1 || WB2Selected==1) || (WP1Selected==1 && WP1Promotion==3) || (WP2Selected==1 && WP2Promotion==3) || (WP3Selected==1 && WP3Promotion==3) || (WP4Selected==1 && WP4Promotion==3) || (WP5Selected==1 && WP5Promotion==3) || (WP6Selected==1 && WP6Promotion==3) || (WP7Selected==1 && WP7Promotion==3) || (WP8Selected==1 && WP8Promotion==3)){
					MoveWhiteBishop(CaseX, CaseY);
					}
//***Knight
				if (SelectCaseX==WK1x && SelectCaseY==WK1y && WK1Taken==0) WK1Selected=1;
				else WK1Selected=0;
				if (SelectCaseX==WK2x && SelectCaseY==WK2y && WK2Taken==0) WK2Selected=1;
				else WK2Selected=0;
				if ((WK1Selected==1 || WK2Selected==1) || (WP1Selected==1 && WP1Promotion==2) || (WP2Selected==1 && WP2Promotion==2) || (WP3Selected==1 && WP3Promotion==2) || (WP4Selected==1 && WP4Promotion==2) || (WP5Selected==1 && WP5Promotion==2) || (WP6Selected==1 && WP6Promotion==2) || (WP7Selected==1 && WP7Promotion==2) || (WP8Selected==1 && WP8Promotion==2)){
					MoveWhiteKnight(CaseX, CaseY);
					}				
//***Queen
				if (SelectCaseX==WQx && SelectCaseY==WQy && WQTaken==0) WQSelected=1;
				else WQSelected=0;
				if (WQSelected==1 || (WP1Selected==1 && WP1Promotion==5) || (WP2Selected==1 && WP2Promotion==5) || (WP3Selected==1 && WP3Promotion==5) || (WP4Selected==1 && WP4Promotion==5) || (WP5Selected==1 && WP5Promotion==5) || (WP6Selected==1 && WP6Promotion==5) || (WP7Selected==1 && WP7Promotion==5) || (WP8Selected==1 && WP8Promotion==5)){
					MoveWhiteQueen(CaseX, CaseY);
					}
//***King
				if (SelectCaseX==WKgx && SelectCaseY==WKgy && WKgTaken==0) WKgSelected=1;
				else WKgSelected=0;
				if (WKgSelected==1){
					MoveWhiteKing(CaseX, CaseY);
					}
				}

//***Black
			else if (Turn==2){
//***Pawn
				if (SelectCaseX==BP1x && SelectCaseY==BP1y && BP1Taken==0) BP1Selected=1;
				else BP1Selected=0;
				if (BP1Selected==1 && BP1Promotion==0){
					MoveBlackPawn(BP1y, CaseX, CaseY);
					}
				if (SelectCaseX==BP2x && SelectCaseY==BP2y && BP2Taken==0) BP2Selected=1;
				else BP2Selected=0;
				if (BP2Selected==1 && BP2Promotion==0){
					MoveBlackPawn(BP2y, CaseX, CaseY);
					}
				if (SelectCaseX==BP3x && SelectCaseY==BP3y && BP3Taken==0) BP3Selected=1;
				else BP3Selected=0;
				if (BP3Selected==1 && BP3Promotion==0){
					MoveBlackPawn(BP3y, CaseX, CaseY);
					}
				if (SelectCaseX==BP4x && SelectCaseY==BP4y && BP4Taken==0) BP4Selected=1;
				else BP4Selected=0;
				if (BP4Selected==1 && BP4Promotion==0){
					MoveBlackPawn(BP4y, CaseX, CaseY);
					}
				if (SelectCaseX==BP5x && SelectCaseY==BP5y && BP5Taken==0) BP5Selected=1;
				else BP5Selected=0;
				if (BP5Selected==1 && BP5Promotion==0){
					MoveBlackPawn(BP5y, CaseX, CaseY);
					}
				if (SelectCaseX==BP6x && SelectCaseY==BP6y && BP6Taken==0) BP6Selected=1;
				else BP6Selected=0;
				if (BP6Selected==1 && BP6Promotion==0){
					MoveBlackPawn(BP6y, CaseX, CaseY);
					}
				if (SelectCaseX==BP7x && SelectCaseY==BP7y && BP7Taken==0) BP7Selected=1;
				else BP7Selected=0;
				if (BP7Selected==1 && BP7Promotion==0){
					MoveBlackPawn(BP7y, CaseX, CaseY);
					}
				if (SelectCaseX==BP8x && SelectCaseY==BP8y && BP8Taken==0) BP8Selected=1;
				else BP8Selected=0;
				if (BP8Selected==1 && BP8Promotion==0){
					MoveBlackPawn(BP8y, CaseX, CaseY);
					}
//***Rook
				if (SelectCaseX==BR1x && SelectCaseY==BR1y && BR1Taken==0) BR1Selected=1;
				else BR1Selected=0;
				if (SelectCaseX==BR2x && SelectCaseY==BR2y && BR2Taken==0) BR2Selected=1;
				else BR2Selected=0;
				if ((BR1Selected==1 || BR2Selected==1) || (BP1Selected==1 && BP1Promotion==4) || (BP2Selected==1 && BP2Promotion==4) || (BP3Selected==1 && BP3Promotion==4) || (BP4Selected==1 && BP4Promotion==4) || (BP5Selected==1 && BP5Promotion==4) || (BP6Selected==1 && BP6Promotion==4) || (BP7Selected==1 && BP7Promotion==4) || (BP8Selected==1 && BP8Promotion==4)){
					MoveBlackRook(CaseX, CaseY);
					}
//***Bishop
				if (SelectCaseX==BB1x && SelectCaseY==BB1y && BB1Taken==0) BB1Selected=1;
				else BB1Selected=0;
				if (SelectCaseX==BB2x && SelectCaseY==BB2y && BB2Taken==0) BB2Selected=1;
				else BB2Selected=0;
				if ((BB1Selected==1 || BB2Selected==1) || (BP1Selected==1 && BP1Promotion==3) || (BP2Selected==1 && BP2Promotion==3) || (BP3Selected==1 && BP3Promotion==3) || (BP4Selected==1 && BP4Promotion==3) || (BP5Selected==1 && BP5Promotion==3) || (BP6Selected==1 && BP6Promotion==3) || (BP7Selected==1 && BP7Promotion==3) || (BP8Selected==1 && BP8Promotion==3)){
					MoveBlackBishop(CaseX, CaseY);
					}
//***Knight
				if (SelectCaseX==BK1x && SelectCaseY==BK1y && BK1Taken==0) BK1Selected=1;
				else BK1Selected=0;
				if (SelectCaseX==BK2x && SelectCaseY==BK2y && BK2Taken==0) BK2Selected=1;
				else BK2Selected=0;
				if ((BK1Selected==1 || BK2Selected==1) || (BP1Selected==1 && BP1Promotion==2) || (BP2Selected==1 && BP2Promotion==2) || (BP3Selected==1 && BP3Promotion==2) || (BP4Selected==1 && BP4Promotion==2) || (BP5Selected==1 && BP5Promotion==2) || (BP6Selected==1 && BP6Promotion==2) || (BP7Selected==1 && BP7Promotion==2) || (BP8Selected==1 && BP8Promotion==2)){
					MoveBlackKnight(CaseX, CaseY);
					}
//***Queen
				if (SelectCaseX==BQx && SelectCaseY==BQy && BQTaken==0) BQSelected=1;
				else BQSelected=0;
				if (BQSelected==1 || (BP1Selected==1 && BP1Promotion==5) || (BP2Selected==1 && BP2Promotion==5) || (BP3Selected==1 && BP3Promotion==5) || (BP4Selected==1 && BP4Promotion==5) || (BP5Selected==1 && BP5Promotion==5) || (BP6Selected==1 && BP6Promotion==5) || (BP7Selected==1 && BP7Promotion==5) || (BP8Selected==1 && BP8Promotion==5)){
					MoveBlackQueen(CaseX, CaseY);
					}
//***King
				if (SelectCaseX==BKgx && SelectCaseY==BKgy && BKgTaken==0) BKgSelected=1;
				else BKgSelected=0;
				if (BKgSelected==1){
					MoveBlackKing(CaseX, CaseY);
					}
				}
//***Fin Piece
 		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Moveboard[i][j]==1){
					g.setColor(Color.cyan);
					g.fillRect(25+i*100, 725-j*100, 100, 100);
					}
				if (Moveboard[i][j]==2 || Moveboard[i][j]==4){
					g.setColor(Color.red);
					g.fillRect(25+i*100, 725-j*100, 100, 100);
					}
				if (Moveboard[i][j]==3){
					g.setColor(Color.yellow);
					g.fillRect(25+i*100, 725-j*100, 100, 100);
					}
				}
			}
		}
		g.setColor(Color.black);
		for(int i=0; i<7; i++) g.drawLine(125+i*100, 25, 125+i*100, 825);
		for(int i=0; i<7; i++) g.drawLine(25, 125+i*100, 825, 125+i*100);

		PieceRefresh();
		try{
			Image WhitePawn = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Blanc Pion.png"));
			Image WhiteKnight = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Blanc Cheval.png"));
			Image WhiteBishop = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Blanc Fou.png"));
			Image WhiteRook = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Blanc Tour.png"));
			Image WhiteQueen = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Blanc Reine.png"));
			Image WhiteKing = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Blanc Roi.png"));
			Image BlackPawn = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Noir Pion.png"));
			Image BlackKnight = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Noir Cheval.png"));
			Image BlackBishop = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Noir Fou.png"));
			Image BlackRook = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Noir Tour.png"));
			Image BlackQueen = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Noir Reine.png"));
			Image BlackKing = ImageIO.read(new File("C:/Users/Utilisateur/Desktop/Programmes/Creations/Echecs/Images/Noir Roi.png"));
			if (WP1Taken==0){
				if (WP1Promotion==0) g.drawImage(WhitePawn, WP1x, WP1y, 100, 100, this);
				else if (WP1Promotion==2) g.drawImage(WhiteKnight, WP1x, WP1y, 100, 100, this);
				else if (WP1Promotion==3) g.drawImage(WhiteBishop, WP1x, WP1y, 100, 100, this);
				else if (WP1Promotion==4) g.drawImage(WhiteRook, WP1x, WP1y, 100, 100, this);
				else if (WP1Promotion==5) g.drawImage(WhiteQueen, WP1x, WP1y, 100, 100, this);
				}
			if (WP2Taken==0){
				if (WP2Promotion==0) g.drawImage(WhitePawn, WP2x, WP2y, 100, 100, this);
				else if (WP2Promotion==2) g.drawImage(WhiteKnight, WP2x, WP2y, 100, 100, this);
				else if (WP2Promotion==3) g.drawImage(WhiteBishop, WP2x, WP2y, 100, 100, this);
				else if (WP2Promotion==4) g.drawImage(WhiteRook, WP2x, WP2y, 100, 100, this);
				else if (WP2Promotion==5) g.drawImage(WhiteQueen, WP2x, WP2y, 100, 100, this);
				}
			if (WP3Taken==0){
				if (WP3Promotion==0) g.drawImage(WhitePawn, WP3x, WP3y, 100, 100, this);
				else if (WP3Promotion==2) g.drawImage(WhiteKnight, WP3x, WP3y, 100, 100, this);
				else if (WP3Promotion==3) g.drawImage(WhiteBishop, WP3x, WP3y, 100, 100, this);
				else if (WP3Promotion==4) g.drawImage(WhiteRook, WP3x, WP3y, 100, 100, this);
				else if (WP3Promotion==5) g.drawImage(WhiteQueen, WP3x, WP3y, 100, 100, this);
				}
			if (WP4Taken==0){
				if (WP4Promotion==0) g.drawImage(WhitePawn, WP4x, WP4y, 100, 100, this);
				else if (WP4Promotion==2) g.drawImage(WhiteKnight, WP4x, WP4y, 100, 100, this);
				else if (WP4Promotion==3) g.drawImage(WhiteBishop, WP4x, WP4y, 100, 100, this);
				else if (WP4Promotion==4) g.drawImage(WhiteRook, WP4x, WP4y, 100, 100, this);
				else if (WP4Promotion==5) g.drawImage(WhiteQueen, WP4x, WP4y, 100, 100, this);
				}
			if (WP5Taken==0){
				if (WP5Promotion==0) g.drawImage(WhitePawn, WP5x, WP5y, 100, 100, this);
				else if (WP5Promotion==2) g.drawImage(WhiteKnight, WP5x, WP5y, 100, 100, this);
				else if (WP5Promotion==3) g.drawImage(WhiteBishop, WP5x, WP5y, 100, 100, this);
				else if (WP5Promotion==4) g.drawImage(WhiteRook, WP5x, WP5y, 100, 100, this);
				else if (WP5Promotion==5) g.drawImage(WhiteQueen, WP5x, WP5y, 100, 100, this);
				}
			if (WP6Taken==0){
				if (WP6Promotion==0) g.drawImage(WhitePawn, WP6x, WP6y, 100, 100, this);
				else if (WP6Promotion==2) g.drawImage(WhiteKnight, WP6x, WP6y, 100, 100, this);
				else if (WP6Promotion==3) g.drawImage(WhiteBishop, WP6x, WP6y, 100, 100, this);
				else if (WP6Promotion==4) g.drawImage(WhiteRook, WP6x, WP6y, 100, 100, this);
				else if (WP6Promotion==5) g.drawImage(WhiteQueen, WP6x, WP6y, 100, 100, this);
				}
			if (WP7Taken==0){
				if (WP7Promotion==0) g.drawImage(WhitePawn, WP7x, WP7y, 100, 100, this);
				else if (WP7Promotion==2) g.drawImage(WhiteKnight, WP7x, WP7y, 100, 100, this);
				else if (WP7Promotion==3) g.drawImage(WhiteBishop, WP7x, WP7y, 100, 100, this);
				else if (WP7Promotion==4) g.drawImage(WhiteRook, WP7x, WP7y, 100, 100, this);
				else if (WP7Promotion==5) g.drawImage(WhiteQueen, WP7x, WP7y, 100, 100, this);
				}
			if (WP8Taken==0){
				if (WP8Promotion==0) g.drawImage(WhitePawn, WP8x, WP8y, 100, 100, this);
				else if (WP8Promotion==2) g.drawImage(WhiteKnight, WP8x, WP8y, 100, 100, this);
				else if (WP8Promotion==3) g.drawImage(WhiteBishop, WP8x, WP8y, 100, 100, this);
				else if (WP8Promotion==4) g.drawImage(WhiteRook, WP8x, WP8y, 100, 100, this);
				else if (WP8Promotion==5) g.drawImage(WhiteQueen, WP8x, WP8y, 100, 100, this);
				}
			if (WK1Taken==0) g.drawImage(WhiteKnight, WK1x, WK1y, 100, 100, this);
			if (WK2Taken==0) g.drawImage(WhiteKnight, WK2x, WK2y, 100, 100, this);
			if (WB1Taken==0) g.drawImage(WhiteBishop, WB1x, WB1y, 100, 100, this);
			if (WB2Taken==0) g.drawImage(WhiteBishop, WB2x, WB2y, 100, 100, this);
			if (WR1Taken==0) g.drawImage(WhiteRook, WR1x, WR1y, 100, 100, this);
			if (WR2Taken==0) g.drawImage(WhiteRook, WR2x, WR2y, 100, 100, this);
			if (WQTaken==0) g.drawImage(WhiteQueen, WQx, WQy, 100, 100, this);
			if (WKgTaken==0) g.drawImage(WhiteKing, WKgx, WKgy, 100, 100, this);

			if (BP1Taken==0){
				if (BP1Promotion==0) g.drawImage(BlackPawn, BP1x, BP1y, 100, 100, this);
				else if (BP1Promotion==2) g.drawImage(BlackKnight, BP1x, BP1y, 100, 100, this);
				else if (BP1Promotion==3) g.drawImage(BlackBishop, BP1x, BP1y, 100, 100, this);
				else if (BP1Promotion==4) g.drawImage(BlackRook, BP1x, BP1y, 100, 100, this);
				else if (BP1Promotion==5) g.drawImage(BlackQueen, BP1x, BP1y, 100, 100, this);
				}
			if (BP2Taken==0){
				if (BP2Promotion==0) g.drawImage(BlackPawn, BP2x, BP2y, 100, 100, this);
				else if (BP2Promotion==2) g.drawImage(BlackKnight, BP2x, BP2y, 100, 100, this);
				else if (BP2Promotion==3) g.drawImage(BlackBishop, BP2x, BP2y, 100, 100, this);
				else if (BP2Promotion==4) g.drawImage(BlackRook, BP2x, BP2y, 100, 100, this);
				else if (BP2Promotion==5) g.drawImage(BlackQueen, BP2x, BP2y, 100, 100, this);
				}
			if (BP3Taken==0){
				if (BP3Promotion==0) g.drawImage(BlackPawn, BP3x, BP3y, 100, 100, this);
				else if (BP3Promotion==2) g.drawImage(BlackKnight, BP3x, BP3y, 100, 100, this);
				else if (BP3Promotion==3) g.drawImage(BlackBishop, BP3x, BP3y, 100, 100, this);
				else if (BP3Promotion==4) g.drawImage(BlackRook, BP3x, BP3y, 100, 100, this);
				else if (BP3Promotion==5) g.drawImage(BlackQueen, BP3x, BP3y, 100, 100, this);
				}
			if (BP4Taken==0){
				if (BP4Promotion==0) g.drawImage(BlackPawn, BP4x, BP4y, 100, 100, this);
				else if (BP4Promotion==2) g.drawImage(BlackKnight, BP4x, BP4y, 100, 100, this);
				else if (BP4Promotion==3) g.drawImage(BlackBishop, BP4x, BP4y, 100, 100, this);
				else if (BP4Promotion==4) g.drawImage(BlackRook, BP4x, BP4y, 100, 100, this);
				else if (BP4Promotion==5) g.drawImage(BlackQueen, BP4x, BP4y, 100, 100, this);
				}
			if (BP5Taken==0){
				if (BP5Promotion==0) g.drawImage(BlackPawn, BP5x, BP5y, 100, 100, this);
				else if (BP5Promotion==2) g.drawImage(BlackKnight, BP5x, BP5y, 100, 100, this);
				else if (BP5Promotion==3) g.drawImage(BlackBishop, BP5x, BP5y, 100, 100, this);
				else if (BP5Promotion==4) g.drawImage(BlackRook, BP5x, BP5y, 100, 100, this);
				else if (BP5Promotion==5) g.drawImage(BlackQueen, BP5x, BP5y, 100, 100, this);
				}
			if (BP6Taken==0){
				if (BP6Promotion==0) g.drawImage(BlackPawn, BP6x, BP6y, 100, 100, this);
				else if (BP6Promotion==2) g.drawImage(BlackKnight, BP6x, BP6y, 100, 100, this);
				else if (BP6Promotion==3) g.drawImage(BlackBishop, BP6x, BP6y, 100, 100, this);
				else if (BP6Promotion==4) g.drawImage(BlackRook, BP6x, BP6y, 100, 100, this);
				else if (BP6Promotion==5) g.drawImage(BlackQueen, BP6x, BP6y, 100, 100, this);
				}
			if (BP7Taken==0){
				if (BP7Promotion==0) g.drawImage(BlackPawn, BP7x, BP7y, 100, 100, this);
				else if (BP7Promotion==2) g.drawImage(BlackKnight, BP7x, BP7y, 100, 100, this);
				else if (BP7Promotion==3) g.drawImage(BlackBishop, BP7x, BP7y, 100, 100, this);
				else if (BP7Promotion==4) g.drawImage(BlackRook, BP7x, BP7y, 100, 100, this);
				else if (BP7Promotion==5) g.drawImage(BlackQueen, BP7x, BP7y, 100, 100, this);
				}
			if (BP8Taken==0){
				if (BP8Promotion==0) g.drawImage(BlackPawn, BP8x, BP8y, 100, 100, this);
				else if (BP8Promotion==2) g.drawImage(BlackKnight, BP8x, BP8y, 100, 100, this);
				else if (BP8Promotion==3) g.drawImage(BlackBishop, BP8x, BP8y, 100, 100, this);
				else if (BP8Promotion==4) g.drawImage(BlackRook, BP8x, BP8y, 100, 100, this);
				else if (BP8Promotion==5) g.drawImage(BlackQueen, BP8x, BP8y, 100, 100, this);
				}
			if (BK1Taken==0) g.drawImage(BlackKnight, BK1x, BK1y, 100, 100, this);
			if (BK2Taken==0) g.drawImage(BlackKnight, BK2x, BK2y, 100, 100, this);
			if (BB1Taken==0) g.drawImage(BlackBishop, BB1x, BB1y, 100, 100, this);
			if (BB2Taken==0) g.drawImage(BlackBishop, BB2x, BB2y, 100, 100, this);
			if (BR1Taken==0) g.drawImage(BlackRook, BR1x, BR1y, 100, 100, this);
			if (BR2Taken==0) g.drawImage(BlackRook, BR2x, BR2y, 100, 100, this);
			if (BQTaken==0) g.drawImage(BlackQueen, BQx, BQy, 100, 100, this);
			if (BKgTaken==0) g.drawImage(BlackKing, BKgx, BKgy, 100, 100, this);

			if (PromotionChoice==1){
				g.setColor(Color.green);
				for(int i=0; i<4; i++) g.fillRect(225+i*100, 325, 100, 100);
				g.setColor(Color.black);
				for(int i=0; i<4; i++) g.drawRect(224+i*100, 324, 102, 102);
				if ((WP1y==25 && WP1Promotion==0) || (WP2y==25 && WP2Promotion==0) || (WP3y==25 && WP3Promotion==0) || (WP4y==25 && WP4Promotion==0) || (WP5y==25 && WP5Promotion==0) || (WP6y==25 && WP6Promotion==0) || (WP7y==25 && WP7Promotion==0) || (WP8y==25 && WP8Promotion==0)){
					g.drawImage(WhiteBishop, 225, 325, 100, 100, this);
					if (WhiteQueenNumber<2) g.drawImage(WhiteQueen, 325, 325, 100, 100, this);
					if (WhiteRookNumber<3) g.drawImage(WhiteRook, 425, 325, 100, 100, this);
					g.drawImage(WhiteKnight, 525, 325, 100, 100, this);
					}
		   		else if ((BP1y==725 && BP1Promotion==0) || (BP2y==725 && BP2Promotion==0) || (BP3y==725 && BP3Promotion==0) || (BP4y==725 && BP4Promotion==0) || (BP5y==725 && BP5Promotion==0) || (BP6y==725 && BP6Promotion==0) || (BP7y==725 && BP7Promotion==0) || (BP8y==725 && BP8Promotion==0)){ 
					g.drawImage(BlackBishop, 225, 325, 100, 100, this);
					if (BlackQueenNumber<2) g.drawImage(BlackQueen, 325, 325, 100, 100, this);
					if (BlackRookNumber<3) g.drawImage(BlackRook, 425, 325, 100, 100, this);
					g.drawImage(BlackKnight, 525, 325, 100, 100, this);
					}
				}
			} catch (IOException e) {}
			}
//*Danger Number
		if (WhiteDangerOn==8 || BlackDangerOn==8){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Whiteboard[i][j]!=0){
						g.setColor(Color.red);
						g.drawString(String.valueOf(Whiteboard[i][j]),25+i*100+45, 725-j*100+55);
						}
					else if (Blackboard[i][j]!=0){
						g.setColor(Color.red);
						g.drawString(String.valueOf(Blackboard[i][j]),25+i*100+45, 725-j*100+55);
						}
					}
				}
			}
		else if (WhiteDangerOn==8 && BlackDangerOn==8){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Whiteboard[i][j]!=0 && Blackboard[i][j]!=0){
						g.setColor(Color.red);
						g.drawString(String.valueOf(Whiteboard[i][j]),25+i*100+45, 725-j*100+30);						
						g.drawString(String.valueOf(Blackboard[i][j]),25+i*100+45, 725-j*100+80);						
						}
					}
				}
			}
		if (BlackDangerOn==9){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Blackboard[i][j]!=0){
						g.setColor(Color.red);
						g.drawString(String.valueOf(Blackboard[i][j]),25+i*100+45, 725-j*100+55);
						}
					}
				}
			}

//*End Danger Number

		//if (gameplay==3){}
			

		if (Mat==1){
			g.setColor(Color.white);
			g.fillRect(345, 400, 160, 50);
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Black Won", 375, 435);
			}
		if (Mat==2){
			g.setColor(Color.black);
			g.fillRect(345, 400, 160, 50);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("White Won", 375, 435);
			}
		if (Mat==3){
			g.setColor(Color.black);
			g.fillRect(345, 400, 160, 50);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString("Pat", 410, 435);
			}
		}
//***************************************************************************************************
//***************************************************************************************************
//***************************************************************************************************
//***************************************************************************************************
//***************************************************************************************************
//***************************************************************************************************
//***************************************************************************************************
//***************************************************************************************************
	public static boolean PieceAlive(int ID){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Pieceboard[i][j]==ID) return true;
				}
			}
		return false;
		}
	public static void PieceRefresh(){
		if (PieceAlive(11)) WP1Taken=0;
		else{
			WP1Taken=1;
			if (WP1Promotion==4) WhiteRookNumber-=1;
			else if (WP1Promotion==5) WhiteQueenNumber-=1;
			WP1Promotion=0;
			}
		if (PieceAlive(12)) WP2Taken=0;
		else{
			WP2Taken=1;
			if (WP2Promotion==4) WhiteRookNumber-=1;
			else if (WP2Promotion==5) WhiteQueenNumber-=1;
			WP2Promotion=0;
			}
		if (PieceAlive(13)) WP3Taken=0;
		else{
			WP3Taken=1;
			if (WP3Promotion==4) WhiteRookNumber-=1;
			else if (WP3Promotion==5) WhiteQueenNumber-=1;
			WP3Promotion=0;
			}
		if (PieceAlive(14)) WP4Taken=0;
		else{
			WP4Taken=1;
			if (WP4Promotion==4) WhiteRookNumber-=1;
			else if (WP4Promotion==5) WhiteQueenNumber-=1;
			WP4Promotion=0;
			}
		if (PieceAlive(15)) WP5Taken=0;
		else{
			WP5Taken=1;
			if (WP5Promotion==4) WhiteRookNumber-=1;
			else if (WP5Promotion==5) WhiteQueenNumber-=1;
			WP5Promotion=0;
			}
		if (PieceAlive(16)) WP6Taken=0;
		else{
			WP6Taken=1;
			if (WP6Promotion==4) WhiteRookNumber-=1;
			else if (WP6Promotion==5) WhiteQueenNumber-=1;
			WP6Promotion=0;
			}
		if (PieceAlive(17)) WP7Taken=0;
		else{
			WP7Taken=1;
			if (WP7Promotion==4) WhiteRookNumber-=1;
			else if (WP7Promotion==5) WhiteQueenNumber-=1;
			WP7Promotion=0;
			}
		if (PieceAlive(18)) WP8Taken=0;
		else{
			WP8Taken=1;
			if (WP8Promotion==4) WhiteRookNumber-=1;
			else if (WP8Promotion==5) WhiteQueenNumber-=1;
			WP8Promotion=0;
			}
		if (PieceAlive(21)) WK1Taken=0;
		else WK1Taken=1;
		if (PieceAlive(22)) WK2Taken=0;
		else WK2Taken=1;
		if (PieceAlive(31)) WB1Taken=0;
		else WB1Taken=1;
		if (PieceAlive(32)) WB2Taken=0;
		else WB2Taken=1;
		if (PieceAlive(41)) WR1Taken=0;
		else{
			if (WR1Taken==0) WhiteRookNumber-=1;
			WR1Taken=1;
			}
		if (PieceAlive(42)) WR2Taken=0;
		else{
			if (WR2Taken==0) WhiteRookNumber-=1;
			WR2Taken=1;
			}
		if (PieceAlive(5)) WQTaken=0;
		else{
			if (WQTaken==0) WhiteQueenNumber-=1;
			WQTaken=1;
			}
		if (PieceAlive(6)) WKgTaken=0;
		else WKgTaken=1;

		if (PieceAlive(71)) BP1Taken=0;
		else{
			BP1Taken=1;
			if (BP1Promotion==4) BlackRookNumber-=1;
			else if (BP1Promotion==5) BlackQueenNumber-=1;
			BP1Promotion=0;
			}
		if (PieceAlive(72)) BP2Taken=0;
		else{
			BP2Taken=1;
			if (BP2Promotion==4) BlackRookNumber-=1;
			else if (BP2Promotion==5) BlackQueenNumber-=1;
			BP2Promotion=0;
			}
		if (PieceAlive(73)) BP3Taken=0;
		else{
			BP3Taken=1;
			if (BP3Promotion==4) BlackRookNumber-=1;
			else if (BP3Promotion==5) BlackQueenNumber-=1;
			BP3Promotion=0;
			}
		if (PieceAlive(74)) BP4Taken=0;
		else{
			BP4Taken=1;
			if (BP4Promotion==4) BlackRookNumber-=1;
			else if (BP4Promotion==5) BlackQueenNumber-=1;
			BP4Promotion=0;
			}
		if (PieceAlive(75)) BP5Taken=0;
		else{
			BP5Taken=1;
			if (BP5Promotion==4) BlackRookNumber-=1;
			else if (BP5Promotion==5) BlackQueenNumber-=1;
			BP5Promotion=0;
			}
		if (PieceAlive(76)) BP6Taken=0;
		else{
			BP6Taken=1;
			if (BP6Promotion==4) BlackRookNumber-=1;
			else if (BP6Promotion==5) BlackQueenNumber-=1;
			BP6Promotion=0;
			}
		if (PieceAlive(77)) BP7Taken=0;
		else{
			BP7Taken=1;
			if (BP7Promotion==4) BlackRookNumber-=1;
			else if (BP7Promotion==5) BlackQueenNumber-=1;
			BP7Promotion=0;
			}
		if (PieceAlive(78)) BP8Taken=0;
		else{
			BP8Taken=1;
			if (BP8Promotion==4) BlackRookNumber-=1;
			else if (BP8Promotion==5) BlackQueenNumber-=1;
			BP8Promotion=0;
			}
		if (PieceAlive(81)) BK1Taken=0;
		else BK1Taken=1;
		if (PieceAlive(82)) BK2Taken=0;
		else BK2Taken=1;
		if (PieceAlive(91)) BB1Taken=0;
		else BB1Taken=1;
		if (PieceAlive(92)) BB2Taken=0;
		else BB2Taken=1;
		if (PieceAlive(101)) BR1Taken=0;
		else{
			if (BR1Taken==0) BlackRookNumber-=1;
			BR1Taken=1;
			}
		if (PieceAlive(102)) BR2Taken=0;
		else{
			if (BR2Taken==0) BlackRookNumber-=1;
			BR2Taken=1;
			}
		if (PieceAlive(111)) BQTaken=0;
		else{
			if (BQTaken==0) BlackQueenNumber-=1;
			BQTaken=1;
			}
		if (PieceAlive(121)) BKgTaken=0;
		else BKgTaken=1;
		}
//************************************************************************************************
	public static void PieceRefresh_AUTRE_A_TESTE(){
		if (PieceAlive(11)==false){
			WP1Taken=1;
			if (WP1Promotion==4) WhiteRookNumber-=1;
			else if (WP1Promotion==5) WhiteQueenNumber-=1;
			WP1Promotion=0;
			}
		if (PieceAlive(12)==false){
			WP2Taken=1;
			if (WP2Promotion==4) WhiteRookNumber-=1;
			else if (WP2Promotion==5) WhiteQueenNumber-=1;
			WP2Promotion=0;
			}
		if (PieceAlive(13)==false){
			WP3Taken=1;
			if (WP3Promotion==4) WhiteRookNumber-=1;
			else if (WP3Promotion==5) WhiteQueenNumber-=1;
			WP3Promotion=0;
			}
		if (PieceAlive(14)==false){
			WP4Taken=1;
			if (WP4Promotion==4) WhiteRookNumber-=1;
			else if (WP4Promotion==5) WhiteQueenNumber-=1;
			WP4Promotion=0;
			}
		if (PieceAlive(15)==false){
			WP5Taken=1;
			if (WP5Promotion==4) WhiteRookNumber-=1;
			else if (WP5Promotion==5) WhiteQueenNumber-=1;
			WP5Promotion=0;
			}
		if (PieceAlive(16)==false){
			WP6Taken=1;
			if (WP6Promotion==4) WhiteRookNumber-=1;
			else if (WP6Promotion==5) WhiteQueenNumber-=1;
			WP6Promotion=0;
			}
		if (PieceAlive(17)==false){
			WP7Taken=1;
			if (WP7Promotion==4) WhiteRookNumber-=1;
			else if (WP7Promotion==5) WhiteQueenNumber-=1;
			WP7Promotion=0;
			}
		if (PieceAlive(18)==false){
			WP8Taken=1;
			if (WP8Promotion==4) WhiteRookNumber-=1;
			else if (WP8Promotion==5) WhiteQueenNumber-=1;
			WP8Promotion=0;
			}
		if (PieceAlive(21)==false) WK1Taken=1;

		if (PieceAlive(22)==false) WK2Taken=1;

		if (PieceAlive(31)==false) WB1Taken=1;

		if (PieceAlive(32)==false) WB2Taken=1;

		if (PieceAlive(41)==false){
			if (WR1Taken==0) WhiteRookNumber-=1;
			WR1Taken=1;
			}
		if (PieceAlive(42)==false){
			if (WR2Taken==0) WhiteRookNumber-=1;
			WR2Taken=1;
			}
		if (PieceAlive(5)==false){
			if (WQTaken==0) WhiteQueenNumber-=1;
			WQTaken=1;
			}
		if (PieceAlive(6)==false) WKgTaken=1;


		if (PieceAlive(71)==false){
			BP1Taken=1;
			if (BP1Promotion==4) BlackRookNumber-=1;
			else if (BP1Promotion==5) BlackQueenNumber-=1;
			BP1Promotion=0;
			}
		if (PieceAlive(72)==false){
			BP2Taken=1;
			if (BP2Promotion==4) BlackRookNumber-=1;
			else if (BP2Promotion==5) BlackQueenNumber-=1;
			BP2Promotion=0;
			}
		if (PieceAlive(73)==false){
			BP3Taken=1;
			if (BP3Promotion==4) BlackRookNumber-=1;
			else if (BP3Promotion==5) BlackQueenNumber-=1;
			BP3Promotion=0;
			}
		if (PieceAlive(74)==false){
			BP4Taken=1;
			if (BP4Promotion==4) BlackRookNumber-=1;
			else if (BP4Promotion==5) BlackQueenNumber-=1;
			BP4Promotion=0;
			}
		if (PieceAlive(75)==false){
			BP5Taken=1;
			if (BP5Promotion==4) BlackRookNumber-=1;
			else if (BP5Promotion==5) BlackQueenNumber-=1;
			BP5Promotion=0;
			}
		if (PieceAlive(76)==false){
			BP6Taken=1;
			if (BP6Promotion==4) BlackRookNumber-=1;
			else if (BP6Promotion==5) BlackQueenNumber-=1;
			BP6Promotion=0;
			}
		if (PieceAlive(77)==false){
			BP7Taken=1;
			if (BP7Promotion==4) BlackRookNumber-=1;
			else if (BP7Promotion==5) BlackQueenNumber-=1;
			BP7Promotion=0;
			}
		if (PieceAlive(78)==false){
			BP8Taken=1;
			if (BP8Promotion==4) BlackRookNumber-=1;
			else if (BP8Promotion==5) BlackQueenNumber-=1;
			BP8Promotion=0;
			}
		if (PieceAlive(81)==false) BK1Taken=1;

		if (PieceAlive(82)==false) BK2Taken=1;

		if (PieceAlive(91)==false) BB1Taken=1;

		if (PieceAlive(92)==false) BB2Taken=1;

		if (PieceAlive(101)==false){
			if (BR1Taken==0) BlackRookNumber-=1;
			BR1Taken=1;
			}
		if (PieceAlive(102)==false){
			if (BR2Taken==0) BlackRookNumber-=1;
			BR2Taken=1;
			}
		if (PieceAlive(111)==false){
			if (BQTaken==0) BlackQueenNumber-=1;
			BQTaken=1;
			}
		if (PieceAlive(121)==false) BKgTaken=1;
		}
//******************************************************************************************

	public static void WhitePawnDanger(int X, int Y, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (X==25+i*100 && Y==725-j*100 && Taken==0){
					if (j+1<8){
						if (i-1>=0) Whiteboard[i-1][j+1]+=1;
						if (i+1<8) Whiteboard[i+1][j+1]+=1;
						}
					}
				 }
			}
		}

	public static void WhiteKnightDanger(int X, int Y, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (X==25+i*100 && Y==725-j*100 && Taken==0){
					if (i+1<8 && j+2<8) Whiteboard[i+1][j+2]+=1;
					if (i+2<8 && j+1<8) Whiteboard[i+2][j+1]+=1;
					if (i+2<8 && j-1>=0) Whiteboard[i+2][j-1]+=1;
					if (i+1<8 && j-2>=0) Whiteboard[i+1][j-2]+=1;
					if (i-1>=0 && j-2>=0) Whiteboard[i-1][j-2]+=1;
					if (i-2>=0 && j-1>=0) Whiteboard[i-2][j-1]+=1;
					if (i-2>=0 && j+1<8) Whiteboard[i-2][j+1]+=1;
					if (i-1>=0 && j+2<8) Whiteboard[i-1][j+2]+=1;
					}
				}
			}
		}

	public static void WhiteBishopDanger(int X, int Y, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (X==25+i*100 && Y==725-j*100 && Taken==0){
					for(int k=1; k+i<8 && k+j<8; k++){
						if (Chessboard[k+i][k+j]!=0 && Chessboard[k+i][k+j]!=12){
							Whiteboard[k+i][k+j]+=1;
							break;
							}
						else Whiteboard[k+i][k+j]+=1;
						}
					for(int k=1; k+i<8 && j-k>=0; k++){
						if (Chessboard[k+i][j-k]!=0 && Chessboard[k+i][j-k]!=12){
							Whiteboard[k+i][j-k]+=1;
							break;
							}
						else Whiteboard[k+i][j-k]+=1;
						}
					for(int k=1; i-k>=0 && j-k>=0; k++){
						if (Chessboard[i-k][j-k]!=0 && Chessboard[i-k][j-k]!=12){
							Whiteboard[i-k][j-k]+=1;
							break;
							}
						else Whiteboard[i-k][j-k]+=1;
						}
					for(int k=1; i-k>=0 && k+j<8; k++){
						if (Chessboard[i-k][k+j]!=0 && Chessboard[i-k][k+j]!=12){
							Whiteboard[i-k][k+j]+=1;
							break;
							}
						else Whiteboard[i-k][k+j]+=1;
						}
					}
				}
			}
		}

	public static void WhiteRookDanger(int X, int Y, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (X==25+i*100 && Y==725-j*100 && Taken==0){
					for(int k=i+1; k<8; k++){
						if (Chessboard[k][j]!=0 && Chessboard[k][j]!=12){
							Whiteboard[k][j]+=1;
							break;
							}
						else Whiteboard[k][j]+=1;
						}
					for(int k=i-1; k>=0; k--){
						if (Chessboard[k][j]!=0 && Chessboard[k][j]!=12){
							Whiteboard[k][j]+=1;
							break;
							}
						else Whiteboard[k][j]+=1;
						}
					for(int k=j+1; k<8; k++){
						if (Chessboard[i][k]!=0 && Chessboard[i][k]!=12){
							Whiteboard[i][k]+=1;
							break;
							}
						else Whiteboard[i][k]+=1;
						}
					for(int k=j-1; k>=0; k--){
						if (Chessboard[i][k]!=0 && Chessboard[i][k]!=12){
							Whiteboard[i][k]+=1;
							break;
							}
						else Whiteboard[i][k]+=1;
						}
					}
				}
			}
		}
		

	public static void WhiteQueenDanger(int X, int Y, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (X==25+i*100 && Y==725-j*100 && Taken==0){
					for(int k=i+1; k<8; k++){
						if (Chessboard[k][j]!=0 && Chessboard[k][j]!=12){
							Whiteboard[k][j]+=1;
							break;
							}
						else Whiteboard[k][j]+=1;
						}
					for(int k=i-1; k>=0; k--){
						if (Chessboard[k][j]!=0 && Chessboard[k][j]!=12){
							Whiteboard[k][j]+=1;
							break;
							}
						else Whiteboard[k][j]+=1;
						}
					for(int k=j+1; k<8; k++){
						if (Chessboard[i][k]!=0 && Chessboard[i][k]!=12){
							Whiteboard[i][k]+=1;
							break;
							}
						else Whiteboard[i][k]+=1;
						}
					for(int k=j-1; k>=0; k--){
						if (Chessboard[i][k]!=0 && Chessboard[i][k]!=12){
							Whiteboard[i][k]+=1;
							break;
							}
						else Whiteboard[i][k]+=1;
						}

					for(int k=1; k+i<8 && k+j<8; k++){
						if (Chessboard[k+i][k+j]!=0 && Chessboard[k+i][k+j]!=12){
							Whiteboard[k+i][k+j]+=1;
							break;
							}
						else Whiteboard[k+i][k+j]+=1;
						}
					for(int k=1; k+i<8 && j-k>=0; k++){
						if (Chessboard[k+i][j-k]!=0 && Chessboard[k+i][j-k]!=12){
							Whiteboard[k+i][j-k]+=1;
							break;
							}
						else Whiteboard[k+i][j-k]+=1;
						}
					for(int k=1; i-k>=0 && j-k>=0; k++){
						if (Chessboard[i-k][j-k]!=0 && Chessboard[i-k][j-k]!=12){
							Whiteboard[i-k][j-k]+=1;
							break;
							}
						else Whiteboard[i-k][j-k]+=1;
						}
					for(int k=1; i-k>=0 && k+j<8; k++){
						if (Chessboard[i-k][k+j]!=0 && Chessboard[i-k][k+j]!=12){
							Whiteboard[i-k][k+j]+=1;
							break;
							}
						else Whiteboard[i-k][k+j]+=1;
						}
					}
				}
			}
		}

	public static void WhiteKingDanger(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (WKgx==25+i*100 && WKgy==725-j*100 && WKgTaken==0){
					if (i+1<8){
						Whiteboard[i+1][j]+=1;
						if (j+1<8) Whiteboard[i+1][j+1]+=1;
						if (j-1>=0) Whiteboard[i+1][j-1]+=1;
						}
					if (i-1>=0){
						Whiteboard[i-1][j]+=1;
						if (j+1<8) Whiteboard[i-1][j+1]+=1;
						if (j-1>=0) Whiteboard[i-1][j-1]+=1;
						}
					if (j+1<8) Whiteboard[i][j+1]+=1;
					if (j-1>=0) Whiteboard[i][j-1]+=1;
					}
				}
			}
		}

	public static void BlackPawnDanger(int piece, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Pieceboard[i][j]==piece && Taken==0){
				//if (X==25+i*100 && Y==725-j*100 && Taken==0){
					if (j-1>=0){
						if (i-1>=0) Blackboard[i-1][j-1]+=1;
						if (i+1<8) Blackboard[i+1][j-1]+=1;
						}
					}
				}
			}
		}

	public static void BlackKnightDanger(int piece, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Pieceboard[i][j]==piece && Taken==0){
				//if (X==25+i*100 && Y==725-j*100 && Taken==0){
					if (i+1<8 && j+2<8) Blackboard[i+1][j+2]+=1;
					if (i+2<8 && j+1<8) Blackboard[i+2][j+1]+=1;
					if (i+2<8 && j-1>=0) Blackboard[i+2][j-1]+=1;
					if (i+1<8 && j-2>=0) Blackboard[i+1][j-2]+=1;
					if (i-1>=0 && j-2>=0) Blackboard[i-1][j-2]+=1;
					if (i-2>=0 && j-1>=0) Blackboard[i-2][j-1]+=1;
					if (i-2>=0 && j+1<8) Blackboard[i-2][j+1]+=1;
					if (i-1>=0 && j+2<8) Blackboard[i-1][j+2]+=1;
					}
				}
			}
		}


	public static void BlackBishopDanger(int piece, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Pieceboard[i][j]==piece && Taken==0){
				//if (X==25+i*100 && Y==725-j*100 && Taken==0){
					for(int k=1; k+i<8 && k+j<8; k++){
						if (Chessboard[k+i][k+j]!=0 && Chessboard[k+i][k+j]!=6){
							Blackboard[k+i][k+j]+=1;
							break;
							}
						else Blackboard[k+i][k+j]+=1;
						}
					for(int k=1; k+i<8 && j-k>=0; k++){
						if (Chessboard[k+i][j-k]!=0 && Chessboard[k+i][j-k]!=6){
							Blackboard[k+i][j-k]+=1;
							break;
							}
						else Blackboard[k+i][j-k]+=1;
						}
					for(int k=1; i-k>=0 && j-k>=0; k++){
						if (Chessboard[i-k][j-k]!=0 && Chessboard[i-k][j-k]!=6){
							Blackboard[i-k][j-k]+=1;
							break;
							}
						else Blackboard[i-k][j-k]+=1;
						}
					for(int k=1; i-k>=0 && k+j<8; k++){
						if (Chessboard[i-k][k+j]!=0 && Chessboard[i-k][k+j]!=6){
							Blackboard[i-k][k+j]+=1;
							break;
							}
						else Blackboard[i-k][k+j]+=1;
						}
					}
				}
			}
		}


	public static void BlackRookDanger(int piece, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Pieceboard[i][j]==piece && Taken==0){
				//if (X==25+i*100 && Y==725-j*100 && Taken==0){
					for(int k=i+1; k<8; k++){
						if (Chessboard[k][j]!=0 && Chessboard[k][j]!=6){
							Blackboard[k][j]+=1;
							break;
							}
						else Blackboard[k][j]+=1;
						}
					for(int k=i-1; k>=0; k--){
						if (Chessboard[k][j]!=0 && Chessboard[k][j]!=6){
							Blackboard[k][j]+=1;
							break;
							}
						else Blackboard[k][j]+=1;
						}
					for(int k=j+1; k<8; k++){
						if (Chessboard[i][k]!=0 && Chessboard[i][k]!=6){
							Blackboard[i][k]+=1;
							break;
							}
						else Blackboard[i][k]+=1;
						}
					for(int k=j-1; k>=0; k--){
						if (Chessboard[i][k]!=0 && Chessboard[i][k]!=6){
							Blackboard[i][k]+=1;
							break;
							}
						else Blackboard[i][k]+=1;
						}
					}
				}
			}
		}
	public static void BlackQueenDanger(int piece, int Taken){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Pieceboard[i][j]==piece && Taken==0){
				//if (X==25+i*100 && Y==725-j*100 && Taken==0){
					for(int k=i+1; k<8; k++){
						if (Chessboard[k][j]!=0 && Chessboard[k][j]!=6){
							Blackboard[k][j]+=1;
							break;
							}
						else Blackboard[k][j]+=1;
						}
					for(int k=i-1; k>=0; k--){
						if (Chessboard[k][j]!=0 && Chessboard[k][j]!=6){
							Blackboard[k][j]+=1;
							break;
							}
						else Blackboard[k][j]+=1;
						}
					for(int k=j+1; k<8; k++){
						if (Chessboard[i][k]!=0 && Chessboard[i][k]!=6){
							Blackboard[i][k]+=1;
							break;
							}
						else Blackboard[i][k]+=1;
						}
					for(int k=j-1; k>=0; k--){
						if (Chessboard[i][k]!=0 && Chessboard[i][k]!=6){
							Blackboard[i][k]+=1;
							break;
							}
						else Blackboard[i][k]+=1;
						}

					for(int k=1; k+i<8 && k+j<8; k++){
						if (Chessboard[k+i][k+j]!=0 && Chessboard[k+i][k+j]!=6){
							Blackboard[k+i][k+j]+=1;
							break;
							}
						else Blackboard[k+i][k+j]+=1;
						}
					for(int k=1; k+i<8 && j-k>=0; k++){
						if (Chessboard[k+i][j-k]!=0 && Chessboard[k+i][j-k]!=6){
							Blackboard[k+i][j-k]+=1;
							break;
							}
						else Blackboard[k+i][j-k]+=1;
						}
					for(int k=1; i-k>=0 && j-k>=0; k++){
						if (Chessboard[i-k][j-k]!=0 && Chessboard[i-k][j-k]!=6){
							Blackboard[i-k][j-k]+=1;
							break;
							}
						else Blackboard[i-k][j-k]+=1;
						}
					for(int k=1; i-k>=0 && k+j<8; k++){
						if (Chessboard[i-k][k+j]!=0 && Chessboard[i-k][k+j]!=6){
							Blackboard[i-k][k+j]+=1;
							break;
							}
						else Blackboard[i-k][k+j]+=1;
						}
					}
				}
			}
		}
	public static void BlackKingDanger(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (BKgx==25+i*100 && BKgy==725-j*100 && BKgTaken==0){
					if (i+1<8){
						Blackboard[i+1][j]+=1;
						if (j+1<8) Blackboard[i+1][j+1]+=1;
						if (j-1>=0) Blackboard[i+1][j-1]+=1;
						}
					if (i-1>=0){
						Blackboard[i-1][j]=1;
						if (j+1<8) Blackboard[i-1][j+1]+=1;
						if (j-1>=0) Blackboard[i-1][j-1]+=1;
						}
					if (j+1<8) Blackboard[i][j+1]+=1;
					if (j-1>=0) Blackboard[i][j-1]+=1;
					}
				}
			}
		}
	public static void WhitePieceDanger(){
		if (WP1Taken==0){
			if (WP1Promotion==0) WhitePawnDanger(WP1x, WP1y, WP1Taken);
			else if (WP1Promotion==2) WhiteKnightDanger(WP1x, WP1y, WP1Taken);
			else if (WP1Promotion==3) WhiteBishopDanger(WP1x, WP1y, WP1Taken);
			else if (WP1Promotion==4) WhiteRookDanger(WP1x, WP1y, WP1Taken);
			else if (WP1Promotion==5) WhiteQueenDanger(WP1x, WP1y, WP1Taken);
			}
		if (WP2Taken==0){
			if (WP2Promotion==0) WhitePawnDanger(WP2x, WP2y, WP2Taken);
			else if (WP2Promotion==2) WhiteKnightDanger(WP2x, WP2y, WP2Taken);
			else if (WP2Promotion==3) WhiteBishopDanger(WP2x, WP2y, WP2Taken);
			else if (WP2Promotion==4) WhiteRookDanger(WP2x, WP2y, WP2Taken);
			else if (WP2Promotion==5) WhiteQueenDanger(WP2x, WP2y, WP2Taken);
			}
		if (WP3Taken==0){
			if (WP3Promotion==0) WhitePawnDanger(WP3x, WP3y, WP3Taken);
			else if (WP3Promotion==2) WhiteKnightDanger(WP3x, WP3y, WP3Taken);
			else if (WP3Promotion==3) WhiteBishopDanger(WP3x, WP3y, WP3Taken);
			else if (WP3Promotion==4) WhiteRookDanger(WP3x, WP3y, WP3Taken);
			else if (WP3Promotion==5) WhiteQueenDanger(WP3x, WP3y, WP3Taken);
			}
		if (WP4Taken==0){
			if (WP4Promotion==0) WhitePawnDanger(WP4x, WP4y, WP4Taken);
			else if (WP4Promotion==2) WhiteKnightDanger(WP4x, WP4y, WP4Taken);
			else if (WP4Promotion==3) WhiteBishopDanger(WP4x, WP4y, WP4Taken);
			else if (WP4Promotion==4) WhiteRookDanger(WP4x, WP4y, WP4Taken);
			else if (WP4Promotion==5) WhiteQueenDanger(WP4x, WP4y, WP4Taken);
			}
		if (WP5Taken==0){
			if (WP5Promotion==0) WhitePawnDanger(WP5x, WP5y, WP5Taken);
			else if (WP5Promotion==2) WhiteKnightDanger(WP5x, WP5y, WP5Taken);
			else if (WP5Promotion==3) WhiteBishopDanger(WP5x, WP5y, WP5Taken);
			else if (WP5Promotion==4) WhiteRookDanger(WP5x, WP5y, WP5Taken);
			else if (WP5Promotion==5) WhiteQueenDanger(WP5x, WP5y, WP5Taken);
			}
		if (WP6Taken==0){
			if (WP6Promotion==0) WhitePawnDanger(WP6x, WP6y, WP6Taken);
			else if (WP6Promotion==2) WhiteKnightDanger(WP6x, WP6y, WP6Taken);
			else if (WP6Promotion==3) WhiteBishopDanger(WP6x, WP6y, WP6Taken);
			else if (WP6Promotion==4) WhiteRookDanger(WP6x, WP6y, WP6Taken);
			else if (WP6Promotion==5) WhiteQueenDanger(WP6x, WP6y, WP6Taken);
			}
		if (WP7Taken==0){
			if (WP7Promotion==0) WhitePawnDanger(WP7x, WP7y, WP7Taken);
			else if (WP7Promotion==2) WhiteKnightDanger(WP7x, WP7y, WP7Taken);
			else if (WP7Promotion==3) WhiteBishopDanger(WP7x, WP7y, WP7Taken);
			else if (WP7Promotion==4) WhiteRookDanger(WP7x, WP7y, WP7Taken);
			else if (WP7Promotion==5) WhiteQueenDanger(WP7x, WP7y, WP7Taken);
			}
		if (WP8Taken==0){
			if (WP8Promotion==0) WhitePawnDanger(WP8x, WP8y, WP8Taken);
			else if (WP8Promotion==2) WhiteKnightDanger(WP8x, WP8y, WP8Taken);
			else if (WP8Promotion==3) WhiteBishopDanger(WP8x, WP8y, WP8Taken);
			else if (WP8Promotion==4) WhiteRookDanger(WP8x, WP8y, WP8Taken);
			else if (WP8Promotion==5) WhiteQueenDanger(WP8x, WP8y, WP8Taken);
			}
		WhiteKnightDanger(WK1x, WK1y, WK1Taken);
		WhiteKnightDanger(WK2x, WK2y, WK2Taken);
		WhiteBishopDanger(WB1x, WB1y, WB1Taken);
		WhiteBishopDanger(WB2x, WB2y, WB2Taken);
		WhiteRookDanger(WR1x, WR1y, WR1Taken);
		WhiteRookDanger(WR2x, WR2y, WR2Taken);
		WhiteQueenDanger(WQx, WQy, WQTaken);
		WhiteKingDanger();
		}
	public static void BlackPieceDanger(){
		if (BP1Taken==0){
			if (BP1Promotion==0) BlackPawnDanger(71, BP1Taken);
			else if (BP1Promotion==2) BlackKnightDanger(71, BP1Taken);
			else if (BP1Promotion==3) BlackBishopDanger(71, BP1Taken);
			else if (BP1Promotion==4) BlackRookDanger(71, BP1Taken);
			else if (BP1Promotion==5) BlackQueenDanger(71, BP1Taken);
			}
		if (BP2Taken==0){
			if (BP2Promotion==0) BlackPawnDanger(72, BP2Taken);
			else if (BP2Promotion==2) BlackKnightDanger(72, BP2Taken);
			else if (BP2Promotion==3) BlackBishopDanger(72, BP2Taken);
			else if (BP2Promotion==4) BlackRookDanger(72, BP2Taken);
			else if (BP2Promotion==5) BlackQueenDanger(72, BP2Taken);
			}
		if (BP3Taken==0){
			if (BP3Promotion==0) BlackPawnDanger(73, BP3Taken);
			else if (BP3Promotion==2) BlackKnightDanger(73, BP3Taken);
			else if (BP3Promotion==3) BlackBishopDanger(73, BP3Taken);
			else if (BP3Promotion==4) BlackRookDanger(73, BP3Taken);
			else if (BP3Promotion==5) BlackQueenDanger(73, BP3Taken);
			}
		if (BP4Taken==0){
			if (BP4Promotion==0) BlackPawnDanger(74, BP4Taken);
			else if (BP4Promotion==2) BlackKnightDanger(74, BP4Taken);
			else if (BP4Promotion==3) BlackBishopDanger(74, BP4Taken);
			else if (BP4Promotion==4) BlackRookDanger(74, BP4Taken);
			else if (BP4Promotion==5) BlackQueenDanger(74, BP4Taken);
			}
		if (BP5Taken==0){
			if (BP5Promotion==0) BlackPawnDanger(75, BP5Taken);
			else if (BP5Promotion==2) BlackKnightDanger(75, BP5Taken);
			else if (BP5Promotion==3) BlackBishopDanger(75, BP5Taken);
			else if (BP5Promotion==4) BlackRookDanger(75, BP5Taken);
			else if (BP5Promotion==5) BlackQueenDanger(75, BP5Taken);
			}
		if (BP6Taken==0){
			if (BP6Promotion==0) BlackPawnDanger(76, BP6Taken);
			else if (BP6Promotion==2) BlackKnightDanger(76, BP6Taken);
			else if (BP6Promotion==3) BlackBishopDanger(76, BP6Taken);
			else if (BP6Promotion==4) BlackRookDanger(76, BP6Taken);
			else if (BP6Promotion==5) BlackQueenDanger(76, BP6Taken);
			}
		if (BP7Taken==0){
			if (BP7Promotion==0) BlackPawnDanger(77, BP7Taken);
			else if (BP7Promotion==2) BlackKnightDanger(77, BP7Taken);
			else if (BP7Promotion==3) BlackBishopDanger(77, BP7Taken);
			else if (BP7Promotion==4) BlackRookDanger(77, BP7Taken);
			else if (BP7Promotion==5) BlackQueenDanger(77, BP7Taken);
			}
		if (BP8Taken==0){
			if (BP8Promotion==0) BlackPawnDanger(78, BP8Taken);
			else if (BP8Promotion==2) BlackKnightDanger(78, BP8Taken);
			else if (BP8Promotion==3) BlackBishopDanger(78, BP8Taken);
			else if (BP8Promotion==4) BlackRookDanger(78, BP8Taken);
			else if (BP8Promotion==5) BlackQueenDanger(78, BP8Taken);
			}
		BlackKnightDanger(81, BK1Taken);
		BlackKnightDanger(82, BK2Taken);
		BlackBishopDanger(91, BB1Taken);
		BlackBishopDanger(92, BB2Taken);
		BlackRookDanger(101, BR1Taken);
		BlackRookDanger(102, BR2Taken);
		BlackQueenDanger(111, BQTaken);
		BlackKingDanger();
		}
//*****Move
//****White
//***Pawn
	public static void MoveReset(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Moveboard[i][j]=0;
				}
			}
		}
	public static void MoveWhitePawn(int Y, int Cx, int Cy){
		MoveReset();
		int FirstMove=0;
		if (Y==625) FirstMove=1;
		else FirstMove=0;
		if (Cy+1<8){
			for(int i=1; i<2+FirstMove; i++){
				if (Chessboard[Cx][Cy+i]!=0) break;
				if (Chessboard[Cx][Cy+i]==0) Moveboard[Cx][Cy+i]=1;
				}
			if (Cx-1>=0){
				if (Chessboard[Cx-1][Cy+1]>=7) Moveboard[Cx-1][Cy+1]=2;
				else if (Pawnboard[Cx-1][Cy]==2 && Chessboard[Cx-1][Cy+1]==0) Moveboard[Cx-1][Cy+1]=4;
				}
			if (Cx+1<8){
				if (Chessboard[Cx+1][Cy+1]>=7) Moveboard[Cx+1][Cy+1]=2;
				else if (Pawnboard[Cx+1][Cy]==2 && Chessboard[Cx+1][Cy+1]==0) Moveboard[Cx+1][Cy+1]=4;
				}
			}
		}
//***Knight
	public static void MoveWhiteKnight(int Cx, int Cy){
		MoveReset();
		if (Cx+1<8 && Cy+2<8){
			if (Chessboard[Cx+1][Cy+2]==0) Moveboard[Cx+1][Cy+2]=1;
			else if (Chessboard[Cx+1][Cy+2]>=7) Moveboard[Cx+1][Cy+2]=2;
			}
		if (Cx+2<8 && Cy+1<8){
			if (Chessboard[Cx+2][Cy+1]==0) Moveboard[Cx+2][Cy+1]=1;
			else if (Chessboard[Cx+2][Cy+1]>=7) Moveboard[Cx+2][Cy+1]=2;
			}
		if (Cx+2<8 && Cy-1>=0){
			if (Chessboard[Cx+2][Cy-1]==0) Moveboard[Cx+2][Cy-1]=1;
			else if (Chessboard[Cx+2][Cy-1]>=7) Moveboard[Cx+2][Cy-1]=2;
			}
		if (Cx+1<8 && Cy-2>=0){
			if (Chessboard[Cx+1][Cy-2]==0) Moveboard[Cx+1][Cy-2]=1;
			else if (Chessboard[Cx+1][Cy-2]>=7) Moveboard[Cx+1][Cy-2]=2;
			}
		if (Cx-1>=0 && Cy-2>=0){
			if (Chessboard[Cx-1][Cy-2]==0) Moveboard[Cx-1][Cy-2]=1;
			else if (Chessboard[Cx-1][Cy-2]>=7) Moveboard[Cx-1][Cy-2]=2;
			}
		if (Cx-2>=0 && Cy-1>=0){
			if (Chessboard[Cx-2][Cy-1]==0) Moveboard[Cx-2][Cy-1]=1;
			else if (Chessboard[Cx-2][Cy-1]>=7) Moveboard[Cx-2][Cy-1]=2;
			}
		if (Cx-2>=0 && Cy+1<8){
			if (Chessboard[Cx-2][Cy+1]==0) Moveboard[Cx-2][Cy+1]=1;
			else if (Chessboard[Cx-2][Cy+1]>=7) Moveboard[Cx-2][Cy+1]=2;
			}
		if (Cx-1>=0 && Cy+2<8){
			if (Chessboard[Cx-1][Cy+2]==0) Moveboard[Cx-1][Cy+2]=1;
			else if (Chessboard[Cx-1][Cy+2]>=7) Moveboard[Cx-1][Cy+2]=2;
			}
		}
//***Bishop
	public static void MoveWhiteBishop(int Cx, int Cy){
		MoveReset();
		for(int i=1; i+Cx<8 && Cy+i<8; i++){
			if (0<Chessboard[Cx+i][Cy+i] && Chessboard[Cx+i][Cy+i]<7) break;
			else if (Chessboard[Cx+i][Cy+i]>=7){
				Moveboard[Cx+i][Cy+i]=2;
				break;
				}
			else if (Chessboard[Cx+i][Cy+i]==0) Moveboard[Cx+i][Cy+i]=1;
			}
		for(int i=1; Cx-i>=0 && Cy+i<8; i++){
			if (0<Chessboard[Cx-i][Cy+i] && Chessboard[Cx-i][Cy+i]<7) break;
			else if (Chessboard[Cx-i][Cy+i]>=7){
				Moveboard[Cx-i][Cy+i]=2;
				break;
				}
			else if (Chessboard[Cx-i][Cy+i]==0) Moveboard[Cx-i][Cy+i]=1;
			}
		for(int i=1; i+Cx<8 && Cy-i>=0; i++){
			if (0<Chessboard[Cx+i][Cy-i] && Chessboard[Cx+i][Cy-i]<7) break;
			else if (Chessboard[Cx+i][Cy-i]>=7){
				Moveboard[Cx+i][Cy-i]=2;
				break;
				}
			else if (Chessboard[Cx+i][Cy-i]==0) Moveboard[Cx+i][Cy-i]=1;
			}
		for(int i=1; Cx-i>=0 && Cy-i>=0; i++){
			if (0<Chessboard[Cx-i][Cy-i] && Chessboard[Cx-i][Cy-i]<7) break;
			else if (Chessboard[Cx-i][Cy-i]>=7){
				Moveboard[Cx-i][Cy-i]=2;
				break;
				}
			else if (Chessboard[Cx-i][Cy-i]==0) Moveboard[Cx-i][Cy-i]=1;
			}
		}
//***Rook
	public static void MoveWhiteRook(int Cx, int Cy){
		MoveReset();
		for(int i=Cy+1; i<8; i++){
			if (0<Chessboard[Cx][i] && Chessboard[Cx][i]<7) break;
			else if (Chessboard[Cx][i]>=7){
				Moveboard[Cx][i]=2;
				break;
				}
			else if (Chessboard[Cx][i]==0) Moveboard[Cx][i]=1;
			}
		for(int i=Cy-1; i>=0; i--){
			if (0<Chessboard[Cx][i] && Chessboard[Cx][i]<7) break;
			else if (Chessboard[Cx][i]>=7){
				Moveboard[Cx][i]=2;
				break;
				}
			else if (Chessboard[Cx][i]==0) Moveboard[Cx][i]=1;
			}
		for(int i=Cx+1; i<8; i++){
			if (0<Chessboard[i][Cy] && Chessboard[i][Cy]<7) break;
			else if (Chessboard[i][Cy]>=7){
				Moveboard[i][Cy]=2;
				break;
				}
			else if (Chessboard[i][Cy]==0) Moveboard[i][Cy]=1;
			}
		for(int i=Cx-1; i>=0; i--){
			if (0<Chessboard[i][Cy] && Chessboard[i][Cy]<7) break;
			else if (Chessboard[i][Cy]>=7){
				Moveboard[i][Cy]=2;
				break;
				}
			else if (Chessboard[i][Cy]==0) Moveboard[i][Cy]=1;
			}
		}
//***Queen
	public static void MoveWhiteQueen(int Cx, int Cy){
		MoveReset();
		for(int i=Cy+1; i<8; i++){
			if (0<Chessboard[Cx][i] && Chessboard[Cx][i]<7) break;
			else if (Chessboard[Cx][i]>=7){
				Moveboard[Cx][i]=2;
				break;
				}
			else if (Chessboard[Cx][i]==0) Moveboard[Cx][i]=1;
			}
		for(int i=Cy-1; i>=0; i--){
			if (0<Chessboard[Cx][i] && Chessboard[Cx][i]<7) break;
			else if (Chessboard[Cx][i]>=7){
				Moveboard[Cx][i]=2;
				break;
				}
			else if (Chessboard[Cx][i]==0) Moveboard[Cx][i]=1;
			}
		for(int i=Cx+1; i<8; i++){
			if (0<Chessboard[i][Cy] && Chessboard[i][Cy]<7) break;
			else if (Chessboard[i][Cy]>=7){
				Moveboard[i][Cy]=2;
				break;
				}
			else if (Chessboard[i][Cy]==0) Moveboard[i][Cy]=1;
			}
		for(int i=Cx-1; i>=0; i--){
			if (0<Chessboard[i][Cy] && Chessboard[i][Cy]<7) break;
			else if (Chessboard[i][Cy]>=7){
				Moveboard[i][Cy]=2;
				break;
				}
			else if (Chessboard[i][Cy]==0) Moveboard[i][Cy]=1;
			}

		for(int i=1; i+Cx<8 && Cy+i<8; i++){
			if (0<Chessboard[Cx+i][Cy+i] && Chessboard[Cx+i][Cy+i]<7) break;
			else if (Chessboard[Cx+i][Cy+i]>=7){
				Moveboard[Cx+i][Cy+i]=2;
				break;
				}
			else if (Chessboard[Cx+i][Cy+i]==0) Moveboard[Cx+i][Cy+i]=1;
			}
		for(int i=1; Cx-i>=0 && Cy+i<8; i++){
			if (0<Chessboard[Cx-i][Cy+i] && Chessboard[Cx-i][Cy+i]<7) break;
			else if (Chessboard[Cx-i][Cy+i]>=7){
				Moveboard[Cx-i][Cy+i]=2;
				break;
				}
			else if (Chessboard[Cx-i][Cy+i]==0) Moveboard[Cx-i][Cy+i]=1;
			}
		for(int i=1; i+Cx<8 && Cy-i>=0; i++){
			if (0<Chessboard[Cx+i][Cy-i] && Chessboard[Cx+i][Cy-i]<7) break;
			else if (Chessboard[Cx+i][Cy-i]>=7){
				Moveboard[Cx+i][Cy-i]=2;
				break;
				}
			else if (Chessboard[Cx+i][Cy-i]==0) Moveboard[Cx+i][Cy-i]=1;
			}
		for(int i=1; Cx-i>=0 && Cy-i>=0; i++){
			if (0<Chessboard[Cx-i][Cy-i] && Chessboard[Cx-i][Cy-i]<7) break;
			else if (Chessboard[Cx-i][Cy-i]>=7){
				Moveboard[Cx-i][Cy-i]=2;
				break;
				}
			else if (Chessboard[Cx-i][Cy-i]==0) Moveboard[Cx-i][Cy-i]=1;
			}
		}
//***King
	public static void MoveWhiteKing(int Cx, int Cy){
		MoveReset();
		if (Cx-1>=0){
			if (Chessboard[Cx-1][Cy]==0 && Blackboard[Cx-1][Cy]==0) Moveboard[Cx-1][Cy]=1;
			else if (Chessboard[Cx-1][Cy]>=7 && Blackboard[Cx-1][Cy]==0) Moveboard[Cx-1][Cy]=2;
			if (Cy-1>=0){
				if (Chessboard[Cx-1][Cy-1]==0 && Blackboard[Cx-1][Cy-1]==0) Moveboard[Cx-1][Cy-1]=1;
				else if (Chessboard[Cx-1][Cy-1]>=7 && Blackboard[Cx-1][Cy-1]==0) Moveboard[Cx-1][Cy-1]=2;
				}
			if (Cy+1<8){
				if (Chessboard[Cx-1][Cy+1]==0 && Blackboard[Cx-1][Cy+1]==0) Moveboard[Cx-1][Cy+1]=1;
				else if (Chessboard[Cx-1][Cy+1]>=7 && Blackboard[Cx-1][Cy+1]==0) Moveboard[Cx-1][Cy+1]=2;
				}
			}
		if (Cx+1<8){
			if (Chessboard[Cx+1][Cy]==0 && Blackboard[Cx+1][Cy]==0) Moveboard[Cx+1][Cy]=1;
			else if (Chessboard[Cx+1][Cy]>=7 && Blackboard[Cx+1][Cy]==0) Moveboard[Cx+1][Cy]=2;
			if (Cy-1>=0){
				if (Chessboard[Cx+1][Cy-1]==0 && Blackboard[Cx+1][Cy-1]==0) Moveboard[Cx+1][Cy-1]=1;
				else if (Chessboard[Cx+1][Cy-1]>=7 && Blackboard[Cx+1][Cy-1]==0) Moveboard[Cx+1][Cy-1]=2;
				}
			if (Cy+1<8){
				if (Chessboard[Cx+1][Cy+1]==0 && Blackboard[Cx+1][Cy+1]==0) Moveboard[Cx+1][Cy+1]=1;
				else if (Chessboard[Cx+1][Cy+1]>=7 && Blackboard[Cx+1][Cy+1]==0) Moveboard[Cx+1][Cy+1]=2;
				}
			}
		if (Cy+1<8){
			if (Chessboard[Cx][Cy+1]==0 && Blackboard[Cx][Cy+1]==0) Moveboard[Cx][Cy+1]=1;
			else if (Chessboard[Cx][Cy+1]>=7 && Blackboard[Cx][Cy+1]==0) Moveboard[Cx][Cy+1]=2;
			}
		if (Cy-1>=0){
			if (Chessboard[Cx][Cy-1]==0 && Blackboard[Cx][Cy-1]==0) Moveboard[Cx][Cy-1]=1;
			else if (Chessboard[Cx][Cy-1]>=7 && Blackboard[Cx][Cy-1]==0) Moveboard[Cx][Cy-1]=2;
			}
		if (WKgNeverMoved==0 && WKgTaken==0 && WR2Taken==0 && WR2NeverMoved==0 && WKgCheck==0 && Chessboard[5][0]==0 && Chessboard[6][0]==0 && Blackboard[5][0]==0 && Blackboard[6][0]==0){
			Moveboard[6][0]=3;
			}
		if (WKgNeverMoved==0 && WKgTaken==0 && WR1Taken==0 && WR1NeverMoved==0 && WKgCheck==0 && Chessboard[1][0]==0 && Chessboard[2][0]==0 && Chessboard[3][0]==0 && Blackboard[1][0]==0 && Blackboard[2][0]==0 && Blackboard[3][0]==0){
			Moveboard[2][0]=3;
			}
		}
//****Black
//***Pawn
	public static void MoveBlackPawn(int Y, int Cx, int Cy){
		MoveReset();
		int FirstMove=0;
		if (Y==125) FirstMove=1;
		else FirstMove=0;
		if (Cy-1>=0){
			for(int i=1; i<2+FirstMove; i++){
				if (Chessboard[Cx][Cy-i]!=0) break;
				if (Chessboard[Cx][Cy-i]==0) Moveboard[Cx][Cy-i]=1;
				}
			if (Cx-1>=0){
				if (0<Chessboard[Cx-1][Cy-1] && Chessboard[Cx-1][Cy-1]<7) Moveboard[Cx-1][Cy-1]=2;
				else if (Pawnboard[Cx-1][Cy]==1 && Chessboard[Cx-1][Cy-1]==0) Moveboard[Cx-1][Cy-1]=4;
				}
			if (Cx+1<8){
				if (0<Chessboard[Cx+1][Cy-1] && Chessboard[Cx+1][Cy-1]<7) Moveboard[Cx+1][Cy-1]=2;
				else if (Pawnboard[Cx+1][Cy]==1 && Chessboard[Cx+1][Cy-1]==0) Moveboard[Cx+1][Cy-1]=4;
				}
			}
		}
//***Knight
	public static void MoveBlackKnight(int Cx, int Cy){
		MoveReset();
		if (Cx+1<8 && Cy+2<8){
			if (Chessboard[Cx+1][Cy+2]==0) Moveboard[Cx+1][Cy+2]=1;
			else if (0<Chessboard[Cx+1][Cy+2] && Chessboard[Cx+1][Cy+2]<7) Moveboard[Cx+1][Cy+2]=2;
			}
		if (Cx+2<8 && Cy+1<8){
			if (Chessboard[Cx+2][Cy+1]==0) Moveboard[Cx+2][Cy+1]=1;
			else if (0<Chessboard[Cx+2][Cy+1] && Chessboard[Cx+2][Cy+1]<7) Moveboard[Cx+2][Cy+1]=2;
			}
		if (Cx+2<8 && Cy-1>=0){
			if (Chessboard[Cx+2][Cy-1]==0) Moveboard[Cx+2][Cy-1]=1;
			else if (0<Chessboard[Cx+2][Cy-1] && Chessboard[Cx+2][Cy-1]<7) Moveboard[Cx+2][Cy-1]=2;
			}
		if (Cx+1<8 && Cy-2>=0){
			if (Chessboard[Cx+1][Cy-2]==0) Moveboard[Cx+1][Cy-2]=1;
			else if (0<Chessboard[Cx+1][Cy-2] && Chessboard[Cx+1][Cy-2]<7) Moveboard[Cx+1][Cy-2]=2;
			}
		if (Cx-1>=0 && Cy-2>=0){
			if (Chessboard[Cx-1][Cy-2]==0) Moveboard[Cx-1][Cy-2]=1;
			else if (0<Chessboard[Cx-1][Cy-2] && Chessboard[Cx-1][Cy-2]<7) Moveboard[Cx-1][Cy-2]=2;
			}
		if (Cx-2>=0 && Cy-1>=0){
			if (Chessboard[Cx-2][Cy-1]==0) Moveboard[Cx-2][Cy-1]=1;
			else if (0<Chessboard[Cx-2][Cy-1] && Chessboard[Cx-2][Cy-1]<7) Moveboard[Cx-2][Cy-1]=2;
			}
		if (Cx-2>=0 && Cy+1<8){
			if (Chessboard[Cx-2][Cy+1]==0) Moveboard[Cx-2][Cy+1]=1;
			else if (0<Chessboard[Cx-2][Cy+1] && Chessboard[Cx-2][Cy+1]<7) Moveboard[Cx-2][Cy+1]=2;
			}
		if (Cx-1>=0 && Cy+2<8){
			if (Chessboard[Cx-1][Cy+2]==0) Moveboard[Cx-1][Cy+2]=1;
			else if (0<Chessboard[Cx-1][Cy+2] && Chessboard[Cx-1][Cy+2]<7) Moveboard[Cx-1][Cy+2]=2;
			}
		}
//***Bishop
	public static void MoveBlackBishop(int Cx, int Cy){
		MoveReset();
		for(int i=1; i+Cx<8 && Cy+i<8; i++){
			if (Chessboard[Cx+i][Cy+i]>=7) break;
			else if (0<Chessboard[Cx+i][Cy+i] && Chessboard[Cx+i][Cy+i]<7){
				Moveboard[Cx+i][Cy+i]=2;
				break;
				}
			else if (Chessboard[Cx+i][Cy+i]==0) Moveboard[Cx+i][Cy+i]=1;
			}
		for(int i=1; Cx-i>=0 && Cy+i<8; i++){
			if (Chessboard[Cx-i][Cy+i]>=7) break;
			else if (0<Chessboard[Cx-i][Cy+i] && Chessboard[Cx-i][Cy+i]<7){
				Moveboard[Cx-i][Cy+i]=2;
				break;
				}
			else if (Chessboard[Cx-i][Cy+i]==0) Moveboard[Cx-i][Cy+i]=1;
			}
		for(int i=1; i+Cx<8 && Cy-i>=0; i++){
			if (Chessboard[Cx+i][Cy-i]>=7) break;
			else if (0<Chessboard[Cx+i][Cy-i] && Chessboard[Cx+i][Cy-i]<7){
				Moveboard[Cx+i][Cy-i]=2;
				break;
				}
			else if (Chessboard[Cx+i][Cy-i]==0) Moveboard[Cx+i][Cy-i]=1;
			}
		for(int i=1; Cx-i>=0 && Cy-i>=0; i++){
			if (Chessboard[Cx-i][Cy-i]>=7) break;
			else if (0<Chessboard[Cx-i][Cy-i] && Chessboard[Cx-i][Cy-i]<7){
				Moveboard[Cx-i][Cy-i]=2;
				break;
				}
			else if (Chessboard[Cx-i][Cy-i]==0) Moveboard[Cx-i][Cy-i]=1;
			}
		}
//***Rook
	public static void MoveBlackRook(int Cx, int Cy){
		MoveReset();
		for(int i=Cy+1; i<8; i++){
			if (Chessboard[Cx][i]>=7) break;
			else if (0<Chessboard[Cx][i] && Chessboard[Cx][i]<7){
				Moveboard[Cx][i]=2;
				break;
				}
			else if (Chessboard[Cx][i]==0) Moveboard[Cx][i]=1;
			}
		for(int i=Cy-1; i>=0; i--){
			if (Chessboard[Cx][i]>=7) break;
			else if (0<Chessboard[Cx][i] && Chessboard[Cx][i]<7){
				Moveboard[Cx][i]=2;
				break;
				}
			else if (Chessboard[Cx][i]==0) Moveboard[Cx][i]=1;
			}
		for(int i=Cx+1; i<8; i++){
			if (Chessboard[i][Cy]>=7) break;
			else if (0<Chessboard[i][Cy] && Chessboard[i][Cy]<7){
				Moveboard[i][Cy]=2;
				break;
				}
			else if (Chessboard[i][Cy]==0) Moveboard[i][Cy]=1;
			}
		for(int i=Cx-1; i>=0; i--){
			if (Chessboard[i][Cy]>=7) break;
			else if (0<Chessboard[i][Cy] && Chessboard[i][Cy]<7){
				Moveboard[i][Cy]=2;
				break;
				}
			else if (Chessboard[i][Cy]==0) Moveboard[i][Cy]=1;
			}
		}
//****Queen
	public static void MoveBlackQueen(int Cx, int Cy){
		MoveReset();
		for(int i=Cy+1; i<8; i++){
			if (Chessboard[Cx][i]>=7) break;
			else if (0<Chessboard[Cx][i] && Chessboard[Cx][i]<7){
				Moveboard[Cx][i]=2;
				break;
				}
			else if (Chessboard[Cx][i]==0) Moveboard[Cx][i]=1;
			}
		for(int i=Cy-1; i>=0; i--){
			if (Chessboard[Cx][i]>=7) break;
			else if (0<Chessboard[Cx][i] && Chessboard[Cx][i]<7){
				Moveboard[Cx][i]=2;
				break;
				}
			else if (Chessboard[Cx][i]==0) Moveboard[Cx][i]=1;
			}
		for(int i=Cx+1; i<8; i++){
			if (Chessboard[i][Cy]>=7) break;
			else if (0<Chessboard[i][Cy] && Chessboard[i][Cy]<7){
				Moveboard[i][Cy]=2;
				break;
				}
			else if (Chessboard[i][Cy]==0) Moveboard[i][Cy]=1;
			}
		for(int i=Cx-1; i>=0; i--){
			if (Chessboard[i][Cy]>=7) break;
			else if (0<Chessboard[i][Cy] && Chessboard[i][Cy]<7){
				Moveboard[i][Cy]=2;
				break;
				}
			else if (Chessboard[i][Cy]==0) Moveboard[i][Cy]=1;
			}

		for(int i=1; i+Cx<8 && Cy+i<8; i++){
			if (Chessboard[Cx+i][Cy+i]>=7) break;
			else if (0<Chessboard[Cx+i][Cy+i] && Chessboard[Cx+i][Cy+i]<7){
				Moveboard[Cx+i][Cy+i]=2;
				break;
				}
			else if (Chessboard[Cx+i][Cy+i]==0) Moveboard[Cx+i][Cy+i]=1;
			}
		for(int i=1; Cx-i>=0 && Cy+i<8; i++){
			if (Chessboard[Cx-i][Cy+i]>=7) break;
			else if (0<Chessboard[Cx-i][Cy+i] && Chessboard[Cx-i][Cy+i]<7){
				Moveboard[Cx-i][Cy+i]=2;
				break;
				}
			else if (Chessboard[Cx-i][Cy+i]==0) Moveboard[Cx-i][Cy+i]=1;
			}
		for(int i=1; i+Cx<8 && Cy-i>=0; i++){
			if (Chessboard[Cx+i][Cy-i]>=7) break;
			else if (0<Chessboard[Cx+i][Cy-i] && Chessboard[Cx+i][Cy-i]<7){
				Moveboard[Cx+i][Cy-i]=2;
				break;
				}
			else if (Chessboard[Cx+i][Cy-i]==0) Moveboard[Cx+i][Cy-i]=1;
			}
		for(int i=1; Cx-i>=0 && Cy-i>=0; i++){
			if (Chessboard[Cx-i][Cy-i]>=7) break;
			else if (0<Chessboard[Cx-i][Cy-i] && Chessboard[Cx-i][Cy-i]<7){
				Moveboard[Cx-i][Cy-i]=2;
				break;
				}
			else if (Chessboard[Cx-i][Cy-i]==0) Moveboard[Cx-i][Cy-i]=1;
			}
		}
//***King
	public static void MoveBlackKing(int Cx, int Cy){
		MoveReset();
		if (Cx-1>=0){
			if (Chessboard[Cx-1][Cy]==0 && Whiteboard[Cx-1][Cy]==0) Moveboard[Cx-1][Cy]=1;
			else if (0<Chessboard[Cx-1][Cy] && Chessboard[Cx-1][Cy]<7 && Whiteboard[Cx-1][Cy]==0) Moveboard[Cx-1][Cy]=2;
			if (Cy-1>=0){
				if (Chessboard[Cx-1][Cy-1]==0 && Whiteboard[Cx-1][Cy-1]==0) Moveboard[Cx-1][Cy-1]=1;
				else if (0<Chessboard[Cx-1][Cy-1] && Chessboard[Cx-1][Cy-1]<7 && Whiteboard[Cx-1][Cy-1]==0) Moveboard[Cx-1][Cy-1]=2;
				}
			if (Cy+1<8){
				if (Chessboard[Cx-1][Cy+1]==0 && Whiteboard[Cx-1][Cy+1]==0) Moveboard[Cx-1][Cy+1]=1;
				else if (0<Chessboard[Cx-1][Cy+1] && Chessboard[Cx-1][Cy+1]<7 && Whiteboard[Cx-1][Cy+1]==0) Moveboard[Cx-1][Cy+1]=2;
				}
			}
		if (Cx+1<8){
			if (Chessboard[Cx+1][Cy]==0 && Whiteboard[Cx+1][Cy]==0) Moveboard[Cx+1][Cy]=1;
			else if (0<Chessboard[Cx+1][Cy] && Chessboard[Cx+1][Cy]<7 && Whiteboard[Cx+1][Cy]==0) Moveboard[Cx+1][Cy]=2;
			if (Cy-1>=0){
				if (Chessboard[Cx+1][Cy-1]==0 && Whiteboard[Cx+1][Cy-1]==0) Moveboard[Cx+1][Cy-1]=1;
				else if (0<Chessboard[Cx+1][Cy-1] && Chessboard[Cx+1][Cy-1]<7 && Whiteboard[Cx+1][Cy-1]==0) Moveboard[Cx+1][Cy-1]=2;
				}
			if (Cy+1<8){
				if (Chessboard[Cx+1][Cy+1]==0 && Whiteboard[Cx+1][Cy+1]==0) Moveboard[Cx+1][Cy+1]=1;
				else if (0<Chessboard[Cx+1][Cy+1] && Chessboard[Cx+1][Cy+1]<7 && Whiteboard[Cx+1][Cy+1]==0) Moveboard[Cx+1][Cy+1]=2;
				}
			}
		if (Cy+1<8){
			if (Chessboard[Cx][Cy+1]==0 && Whiteboard[Cx][Cy+1]==0) Moveboard[Cx][Cy+1]=1;
			else if (0<Chessboard[Cx][Cy+1] && Chessboard[Cx][Cy+1]<7 && Whiteboard[Cx][Cy+1]==0) Moveboard[Cx][Cy+1]=2;
			}
		if (Cy-1>=0){
			if (Chessboard[Cx][Cy-1]==0 && Whiteboard[Cx][Cy-1]==0) Moveboard[Cx][Cy-1]=1;
			else if (0<Chessboard[Cx][Cy-1] && Chessboard[Cx][Cy-1]<7 && Whiteboard[Cx][Cy-1]==0) Moveboard[Cx][Cy-1]=2;
			}
		if (BKgNeverMoved==0 && BKgTaken==0 && BR2Taken==0 && BR2NeverMoved==0 && BKgCheck==0 && Chessboard[5][7]==0 && Chessboard[6][7]==0 && Whiteboard[5][7]==0 && Whiteboard[6][7]==0){
			Moveboard[6][7]=3;
			}
		if (BKgNeverMoved==0 && BKgTaken==0 && BR1Taken==0 && BR1NeverMoved==0 && BKgCheck==0 && Chessboard[1][7]==0 && Chessboard[2][7]==0 && Chessboard[3][7]==0 && Whiteboard[1][7]==0 && Whiteboard[2][7]==0 && Whiteboard[3][7]==0){
			Moveboard[2][7]=3;
			}
		}
//***Fin Move

//*****Play Turn
//****White
//***Pawn
	public static void PlayWhitePawn(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==4){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int C4 = 0;
			int P4 = 0;
			if (Cy-1>=0){
				C4 = Pan.Chessboard[Cx][Cy-1];
				P4 = Pan.Pieceboard[Cx][Cy-1];
				}
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.WP1x==25+i*100 && Pan.WP1y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP1y==625 && Cy==3) Pan.Pawnboard[Cx][Cy]=1;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.WP2x==25+i*100 && Pan.WP2y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP2y==625 && Cy==3) Pan.Pawnboard[Cx][Cy]=1;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==3) if (Pan.WP3x==25+i*100 && Pan.WP3y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP3y==625 && Cy==3) Pan.Pawnboard[Cx][Cy]=1;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==4) if (Pan.WP4x==25+i*100 && Pan.WP4y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP4y==625 && Cy==3) Pan.Pawnboard[Cx][Cy]=1;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==5) if (Pan.WP5x==25+i*100 && Pan.WP5y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP5y==625 && Cy==3) Pan.Pawnboard[Cx][Cy]=1;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==6) if (Pan.WP6x==25+i*100 && Pan.WP6y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP6y==625 && Cy==3) Pan.Pawnboard[Cx][Cy]=1;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==7) if (Pan.WP7x==25+i*100 && Pan.WP7y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP7y==625 && Cy==3) Pan.Pawnboard[Cx][Cy]=1;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==8) if (Pan.WP8x==25+i*100 && Pan.WP8y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP8y==625 && Cy==3) Pan.Pawnboard[Cx][Cy]=1;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;	
						}
					}
				}
			if (N==1){
				Pan.WP1x=25+Cx*100;
				Pan.WP1y=725-Cy*100;
				Pan.WP1Selected=0;
				Pan.Pieceboard[Cx][Cy]=11;
				}
			else if (N==2){
				Pan.WP2x=25+Cx*100;
				Pan.WP2y=725-Cy*100;
				Pan.WP2Selected=0;
				Pan.Pieceboard[Cx][Cy]=12;
				}
			else if (N==3){
				Pan.WP3x=25+Cx*100;
				Pan.WP3y=725-Cy*100;
				Pan.WP3Selected=0;
				Pan.Pieceboard[Cx][Cy]=13;
				}
			else if (N==4){
				Pan.WP4x=25+Cx*100;
				Pan.WP4y=725-Cy*100;
				Pan.WP4Selected=0;
				Pan.Pieceboard[Cx][Cy]=14;
				}
			else if (N==5){
				Pan.WP5x=25+Cx*100;
				Pan.WP5y=725-Cy*100;
				Pan.WP5Selected=0;
				Pan.Pieceboard[Cx][Cy]=15;
				}
			else if (N==6){
				Pan.WP6x=25+Cx*100;
				Pan.WP6y=725-Cy*100;
				Pan.WP6Selected=0;
				Pan.Pieceboard[Cx][Cy]=16;
				}
			else if (N==7){
				Pan.WP7x=25+Cx*100;
				Pan.WP7y=725-Cy*100;
				Pan.WP7Selected=0;
				Pan.Pieceboard[Cx][Cy]=17;
				}
			else if (N==8){
				Pan.WP8x=25+Cx*100;
				Pan.WP8y=725-Cy*100;
				Pan.WP8Selected=0;
				Pan.Pieceboard[Cx][Cy]=18;
				}
			Pan.Chessboard[Cx][Cy]=1;
			if (Pan.Moveboard[Cx][Cy]==4){
				Pan.Chessboard[Cx][Cy-1]=0;
				Pan.Pieceboard[Cx][Cy-1]=0;
				}
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				if (Cy-1>=0){
					Pan.Chessboard[Cx][Cy-1]=C4;
					Pan.Pieceboard[Cx][Cy-1]=P4;
					}
				Pan.Chessboard[I][J]=1;
				if (N==1){
					Pan.Pieceboard[I][J]=11;
					Pan.WP1x=25+I*100;
					Pan.WP1y=725-J*100;
					}
				else if (N==2){
					Pan.Pieceboard[I][J]=12;
					Pan.WP2x=25+I*100;
					Pan.WP2y=725-J*100;
					}
				else if (N==3){
					Pan.Pieceboard[I][J]=13;
					Pan.WP3x=25+I*100;
					Pan.WP3y=725-J*100;
					}
				else if (N==4){
					Pan.Pieceboard[I][J]=14;
					Pan.WP4x=25+I*100;
					Pan.WP4y=725-J*100;
					}
				else if (N==5){
					Pan.Pieceboard[I][J]=15;
					Pan.WP5x=25+I*100;
					Pan.WP5y=725-J*100;
					}
				else if (N==6){
					Pan.Pieceboard[I][J]=16;
					Pan.WP6x=25+I*100;
					Pan.WP6y=725-J*100;
					}
				else if (N==7){
					Pan.Pieceboard[I][J]=17;
					Pan.WP7x=25+I*100;
					Pan.WP7y=725-J*100;
					}
				else if (N==8){
					Pan.Pieceboard[I][J]=18;
					Pan.WP8x=25+I*100;
					Pan.WP8y=725-J*100;
					}
				}
			else{
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				//Switch();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][4]=0;
			Pan.Selected=0;
			}
		}
//***Knight
	public static void PlayWhiteKnight(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.WK1x==25+i*100 && Pan.WK1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.WK2x==25+i*100 && Pan.WK2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1){
				Pan.WK1x=25+Cx*100;
				Pan.WK1y=725-Cy*100;
				Pan.WK1Selected=0;
				Pan.Pieceboard[Cx][Cy]=21;
				}
			else if (N==2){
				Pan.WK2x=25+Cx*100;
				Pan.WK2y=725-Cy*100;
				Pan.WK2Selected=0;
				Pan.Pieceboard[Cx][Cy]=22;
				}
			Pan.Chessboard[Cx][Cy]=2;
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=2;
				if (N==1){
					Pan.Pieceboard[I][J]=21;
					Pan.WK1x=25+I*100;
					Pan.WK1y=725-J*100;
					}
				else if (N==2){
					Pan.Pieceboard[I][J]=22;
					Pan.WK2x=25+I*100;
					Pan.WK2y=725-J*100;
					}
				}
			else{
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][4]=0;
			Pan.Selected=0;
			}
		}
//***Bishop
	public static void PlayWhiteBishop(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.WB1x==25+i*100 && Pan.WB1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.WB2x==25+i*100 && Pan.WB2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1){
				Pan.WB1x=25+Cx*100;
				Pan.WB1y=725-Cy*100;
				Pan.WB1Selected=0;
				Pan.Pieceboard[Cx][Cy]=31;
				}
			else if (N==2){
				Pan.WB2x=25+Cx*100;
				Pan.WB2y=725-Cy*100;
				Pan.WB2Selected=0;
				Pan.Pieceboard[Cx][Cy]=32;
				}
			Pan.Chessboard[Cx][Cy]=3;
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=3;
				if (N==1){
					Pan.Pieceboard[I][J]=31;
					Pan.WB1x=25+I*100;
					Pan.WB1y=725-J*100;
					}
				else if (N==2){
					Pan.Pieceboard[I][J]=32;
					Pan.WB2x=25+I*100;
					Pan.WB2y=725-J*100;
					}
				}
			else{
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][4]=0;
			Pan.Selected=0;
			}
		}
//***Rook
	public static void PlayWhiteRook(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.WR1x==25+i*100 && Pan.WR1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.WR2x==25+i*100 && Pan.WR2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1){
				Pan.WR1x=25+Cx*100;
				Pan.WR1y=725-Cy*100;
				Pan.WR1Selected=0;
				Pan.Pieceboard[Cx][Cy]=41;
				}
			else if (N==2){
				Pan.WR2x=25+Cx*100;
				Pan.WR2y=725-Cy*100;
				Pan.WR2Selected=0;
				Pan.Pieceboard[Cx][Cy]=42;
				}
			Pan.Chessboard[Cx][Cy]=4;
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=4;
				if (N==1){
					Pan.Pieceboard[I][J]=41;
					Pan.WR1x=25+I*100;
					Pan.WR1y=725-J*100;
					}
				else if (N==2){
					Pan.Pieceboard[I][J]=42;
					Pan.WR2x=25+I*100;
					Pan.WR2y=725-J*100;
					}
				}
			else{
				if (N==1) Pan.WR1NeverMoved=1;
				else if (N==2) Pan.WR2NeverMoved=1;
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][4]=0;
			Pan.Selected=0;
			}
		}
//***Queen
	public static void PlayWhiteQueen(int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Pan.WQx==25+i*100 && Pan.WQy==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			Pan.WQx=25+Cx*100;
			Pan.WQy=725-Cy*100;
			Pan.WQSelected=0;
			Pan.Chessboard[Cx][Cy]=5;
			Pan.Pieceboard[Cx][Cy]=5;
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=5;
				Pan.Pieceboard[I][J]=5;
				Pan.WQx=25+I*100;
				Pan.WQy=725-J*100;
				}
			else{
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][4]=0;
			Pan.Selected=0;
			}
		}
//***King
	public static void PlayWhiteKing(int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==3){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Pan.WKgx==25+i*100 && Pan.WKgy==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			Pan.WKgx=25+Cx*100;
			Pan.WKgy=725-Cy*100;
			Pan.WKgSelected=0;
			Pan.Chessboard[Cx][Cy]=6;
			Pan.Pieceboard[Cx][Cy]=6;
			if (Pan.Moveboard[Cx][Cy]==3 && Cx==2){
				Pan.Chessboard[0][0]=0;
				Pan.Pieceboard[0][0]=0;
				Pan.Chessboard[3][0]=4;
				Pan.Pieceboard[3][0]=41;
				Pan.WR1x=325;
				}
			else if (Pan.Moveboard[Cx][Cy]==3 && Cx==6){
				Pan.Chessboard[7][0]=0;
				Pan.Pieceboard[7][0]=0;
				Pan.Chessboard[5][0]=4;
				Pan.Pieceboard[5][0]=42;
				Pan.WR2x=525;
				}
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=6;
				Pan.Pieceboard[I][J]=6;
				Pan.WKgx=25+I*100;
				Pan.WKgy=725-J*100;
				}
			else{
				Pan.WKgNeverMoved=1;
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][4]=0;
			Pan.Selected=0;
			}
		}

//****Black
//***Pawn
	public static void PlayBlackPawn(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==4){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int C4 = 0;
			int P4 = 0;
			if (Cy+1<8){
				C4 = Pan.Chessboard[Cx][Cy+1];
				P4 = Pan.Pieceboard[Cx][Cy+1];
				}
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.BP1x==25+i*100 && Pan.BP1y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP1y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.BP2x==25+i*100 && Pan.BP2y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP2y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==3) if (Pan.BP3x==25+i*100 && Pan.BP3y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP3y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==4) if (Pan.BP4x==25+i*100 && Pan.BP4y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP4y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==5) if (Pan.BP5x==25+i*100 && Pan.BP5y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP5y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==6) if (Pan.BP6x==25+i*100 && Pan.BP6y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP6y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==7) if (Pan.BP7x==25+i*100 && Pan.BP7y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP7y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==8) if (Pan.BP8x==25+i*100 && Pan.BP8y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP8y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;	
						}
					}
				}
			if (N==1){
				Pan.BP1x=25+Cx*100;
				Pan.BP1y=725-Cy*100;
				Pan.BP1Selected=0;
				Pan.Pieceboard[Cx][Cy]=71;
				}
			else if (N==2){
				Pan.BP2x=25+Cx*100;
				Pan.BP2y=725-Cy*100;
				Pan.BP2Selected=0;
				Pan.Pieceboard[Cx][Cy]=72;
				}
			else if (N==3){
				Pan.BP3x=25+Cx*100;
				Pan.BP3y=725-Cy*100;
				Pan.BP3Selected=0;
				Pan.Pieceboard[Cx][Cy]=73;
				}
			else if (N==4){
				Pan.BP4x=25+Cx*100;
				Pan.BP4y=725-Cy*100;
				Pan.BP4Selected=0;
				Pan.Pieceboard[Cx][Cy]=74;
				}
			else if (N==5){
				Pan.BP5x=25+Cx*100;
				Pan.BP5y=725-Cy*100;
				Pan.BP5Selected=0;
				Pan.Pieceboard[Cx][Cy]=75;
				}
			else if (N==6){
				Pan.BP6x=25+Cx*100;
				Pan.BP6y=725-Cy*100;
				Pan.BP6Selected=0;
				Pan.Pieceboard[Cx][Cy]=76;
				}
			else if (N==7){
				Pan.BP7x=25+Cx*100;
				Pan.BP7y=725-Cy*100;
				Pan.BP7Selected=0;
				Pan.Pieceboard[Cx][Cy]=77;
				}
			else if (N==8){
				Pan.BP8x=25+Cx*100;
				Pan.BP8y=725-Cy*100;
				Pan.BP8Selected=0;
				Pan.Pieceboard[Cx][Cy]=78;
				}
			Pan.Chessboard[Cx][Cy]=7;
			if (Pan.Moveboard[Cx][Cy]==4){
				Pan.Chessboard[Cx][Cy+1]=0;
				Pan.Pieceboard[Cx][Cy+1]=0;
				}
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				if (Cy+1<8){
					Pan.Chessboard[Cx][Cy+1]=C4;
					Pan.Pieceboard[Cx][Cy+1]=P4;
					}
				Pan.Chessboard[I][J]=7;
				if (N==1){
					Pan.Pieceboard[I][J]=71;
					Pan.BP1x=25+I*100;
					Pan.BP1y=725-J*100;
					}
				else if (N==2){
					Pan.Pieceboard[I][J]=72;
					Pan.BP2x=25+I*100;
					Pan.BP2y=725-J*100;
					}
				else if (N==3){
					Pan.Pieceboard[I][J]=73;
					Pan.BP3x=25+I*100;
					Pan.BP3y=725-J*100;
					}
				else if (N==4){
					Pan.Pieceboard[I][J]=74;
					Pan.BP4x=25+I*100;
					Pan.BP4y=725-J*100;
					}
				else if (N==5){
					Pan.Pieceboard[I][J]=75;
					Pan.BP5x=25+I*100;
					Pan.BP5y=725-J*100;
					}
				else if (N==6){
					Pan.Pieceboard[I][J]=76;
					Pan.BP6x=25+I*100;
					Pan.BP6y=725-J*100;
					}
				else if (N==7){
					Pan.Pieceboard[I][J]=77;
					Pan.BP7x=25+I*100;
					Pan.BP7y=725-J*100;
					}
				else if (N==8){
					Pan.Pieceboard[I][J]=78;
					Pan.BP8x=25+I*100;
					Pan.BP8y=725-J*100;
					}
				}
			else{
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][3]=0;
			Pan.Selected=0;
			}
		}
//***Knight
	public static void PlayBlackKnight(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.BK1x==25+i*100 && Pan.BK1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.BK2x==25+i*100 && Pan.BK2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1){
				Pan.BK1x=25+Cx*100;
				Pan.BK1y=725-Cy*100;
				Pan.BK1Selected=0;
				Pan.Pieceboard[Cx][Cy]=81;
				}
			else if (N==2){
				Pan.BK2x=25+Cx*100;
				Pan.BK2y=725-Cy*100;
				Pan.BK2Selected=0;
				Pan.Pieceboard[Cx][Cy]=82;
				}
			Pan.Chessboard[Cx][Cy]=8;
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=8;
				if (N==1){
					Pan.Pieceboard[I][J]=81;
					Pan.BK1x=25+I*100;
					Pan.BK1y=725-J*100;
					}
				else if (N==2){
					Pan.Pieceboard[I][J]=82;
					Pan.BK2x=25+I*100;
					Pan.BK2y=725-J*100;
					}
				}
			else{
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][3]=0;
			Pan.Selected=0;
			}
		}
//***Bishop
	public static void PlayBlackBishop(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.BB1x==25+i*100 && Pan.BB1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.BB2x==25+i*100 && Pan.BB2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1){
				Pan.BB1x=25+Cx*100;
				Pan.BB1y=725-Cy*100;
				Pan.BB1Selected=0;
				Pan.Pieceboard[Cx][Cy]=91;
				}
			else if (N==2){
				Pan.BB2x=25+Cx*100;
				Pan.BB2y=725-Cy*100;
				Pan.BB2Selected=0;
				Pan.Pieceboard[Cx][Cy]=92;
				}
			Pan.Chessboard[Cx][Cy]=9;
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=9;
				if (N==1){
					Pan.Pieceboard[I][J]=91;
					Pan.BB1x=25+I*100;
					Pan.BB1y=725-J*100;
					}
				else if (N==2){
					Pan.Pieceboard[I][J]=92;
					Pan.BB2x=25+I*100;
					Pan.BB2y=725-J*100;
					}
				}
			else{
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][3]=0;
			Pan.Selected=0;
			}
		}
//***Rook
	public static void PlayBlackRook(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.BR1x==25+i*100 && Pan.BR1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.BR2x==25+i*100 && Pan.BR2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1){
				Pan.BR1x=25+Cx*100;
				Pan.BR1y=725-Cy*100;
				Pan.BR1Selected=0;
				Pan.Pieceboard[Cx][Cy]=101;
				}
			else if (N==2){
				Pan.BR2x=25+Cx*100;
				Pan.BR2y=725-Cy*100;
				Pan.BR2Selected=0;
				Pan.Pieceboard[Cx][Cy]=102;
				}
			Pan.Chessboard[Cx][Cy]=10;
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=10;
				if (N==1){
					Pan.Pieceboard[I][J]=101;
					Pan.BR1x=25+I*100;
					Pan.BR1y=725-J*100;
					}
				else if (N==2){
					Pan.Pieceboard[I][J]=102;
					Pan.BR2x=25+I*100;
					Pan.BR2y=725-J*100;
					}
				}
			else{
				if (N==1) Pan.BR1NeverMoved=1;
				else if (N==2) Pan.BR2NeverMoved=1;
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][3]=0;
			Pan.Selected=0;
			}
		}
//***Queen
	public static void PlayBlackQueen(int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Pan.BQx==25+i*100 && Pan.BQy==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			Pan.BQx=25+Cx*100;
			Pan.BQy=725-Cy*100;
			Pan.BQSelected=0;
			Pan.Chessboard[Cx][Cy]=11;
			Pan.Pieceboard[Cx][Cy]=111;
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=11;
				Pan.Pieceboard[I][J]=111;
				Pan.BQx=25+I*100;
				Pan.BQy=725-J*100;
				}
			else{
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][3]=0;
			Pan.Selected=0;
			}
		}
//***King
	public static void PlayBlackKing(int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==3){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Pan.BKgx==25+i*100 && Pan.BKgy==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			Pan.BKgx=25+Cx*100;
			Pan.BKgy=725-Cy*100;
			Pan.BKgSelected=0;
			Pan.Chessboard[Cx][Cy]=12;
			Pan.Pieceboard[Cx][Cy]=121;
			if (Pan.Moveboard[Cx][Cy]==3 && Cx==2){
				Pan.Chessboard[0][7]=0;
				Pan.Pieceboard[0][7]=0;
				Pan.Chessboard[3][7]=10;
				Pan.Pieceboard[3][7]=101;
				Pan.BR1x=325;
				}
			else if (Pan.Moveboard[Cx][Cy]==3 && Cx==6){
				Pan.Chessboard[7][7]=0;
				Pan.Pieceboard[7][7]=0;
				Pan.Chessboard[5][7]=10;
				Pan.Pieceboard[5][7]=102;
				Pan.BR2x=525;
				}
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=12;
				Pan.Pieceboard[I][J]=121;
				Pan.BKgx=25+I*100;
				Pan.BKgy=725-J*100;
				}
			else{
				Pan.BKgNeverMoved=1;
				Pan.Turn+=1;
				T+=1;
				HistoRecord();
				}
			for(int i=0; i<8; i++) Pan.Pawnboard[i][3]=0;
			Pan.Selected=0;
			}
		}
//******Fin Play Turn

	public static boolean isWhiteKingCheck(){
		int Check=0;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Blackboard[i][j]=0;
				}
			}
		BlackPieceDanger();
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (WKgx==25+i*100 && WKgy==725-j*100) if (Blackboard[i][j]!=0) Check=1;
				}
			}
		return Check==1;
		}

	public static boolean isBlackKingCheck(){
		int Check=0;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Whiteboard[i][j]=0;
				}
			}
		WhitePieceDanger();
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (BKgx==25+i*100 && BKgy==725-j*100) if (Whiteboard[i][j]!=0) Check=1;
				}
			}
		return Check==1;
		}
//*****Playable Pieces
//****White
//**Pawn
	public static boolean PlayableWhitePawn(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==4){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int C4 = 0;
			int P4 = 0;
			if (Cy+1<8){
				C4 = Pan.Chessboard[Cx][Cy+1];
				P4 = Pan.Pieceboard[Cx][Cy+1];
				}
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.WP1x==25+i*100 && Pan.WP1y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP1y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.WP2x==25+i*100 && Pan.WP2y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP2y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==3) if (Pan.WP3x==25+i*100 && Pan.WP3y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP3y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==4) if (Pan.WP4x==25+i*100 && Pan.WP4y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP4y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==5) if (Pan.WP5x==25+i*100 && Pan.WP5y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP5y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==6) if (Pan.WP6x==25+i*100 && Pan.WP6y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP6y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==7) if (Pan.WP7x==25+i*100 && Pan.WP7y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP7y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==8) if (Pan.WP8x==25+i*100 && Pan.WP8y==725-j*100){
						I=i;
						J=j;
						if (Pan.WP8y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;	
						}
					}
				}
			if (N==1) Pan.Pieceboard[Cx][Cy]=11;
			else if (N==2) Pan.Pieceboard[Cx][Cy]=12;
			else if (N==3) Pan.Pieceboard[Cx][Cy]=13;
			else if (N==4) Pan.Pieceboard[Cx][Cy]=14;
			else if (N==5) Pan.Pieceboard[Cx][Cy]=15;
			else if (N==6) Pan.Pieceboard[Cx][Cy]=16;
			else if (N==7) Pan.Pieceboard[Cx][Cy]=17;
			else if (N==8) Pan.Pieceboard[Cx][Cy]=18;
			Pan.Chessboard[Cx][Cy]=1;
			if (Pan.Moveboard[Cx][Cy]==4){
				Pan.Chessboard[Cx][Cy+1]=0;
				Pan.Pieceboard[Cx][Cy+1]=0;
				}
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				if (Cy+1<8){
					Pan.Chessboard[Cx][Cy+1]=C4;
					Pan.Pieceboard[Cx][Cy+1]=P4;
					}
				Pan.Chessboard[I][J]=1;
				if (N==1) Pan.Pieceboard[I][J]=11;
				else if (N==2) Pan.Pieceboard[I][J]=12;
				else if (N==3) Pan.Pieceboard[I][J]=13;
				else if (N==4) Pan.Pieceboard[I][J]=14;
				else if (N==5) Pan.Pieceboard[I][J]=15;
				else if (N==6) Pan.Pieceboard[I][J]=16;
				else if (N==7) Pan.Pieceboard[I][J]=17;
				else if (N==8) Pan.Pieceboard[I][J]=18;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				if (Cy+1<8){
					Pan.Chessboard[Cx][Cy+1]=C4;
					Pan.Pieceboard[Cx][Cy+1]=P4;
					}
				Pan.Chessboard[I][J]=1;
				if (N==1) Pan.Pieceboard[I][J]=11;
				else if (N==2) Pan.Pieceboard[I][J]=12;
				else if (N==3) Pan.Pieceboard[I][J]=13;
				else if (N==4) Pan.Pieceboard[I][J]=14;
				else if (N==5) Pan.Pieceboard[I][J]=15;
				else if (N==6) Pan.Pieceboard[I][J]=16;
				else if (N==7) Pan.Pieceboard[I][J]=17;
				else if (N==8) Pan.Pieceboard[I][J]=18;
				return true;
				}
			}
		else return false;
		}

//***Knight
	public static boolean PlayableWhiteKnight(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.WK1x==25+i*100 && Pan.WK1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.WK2x==25+i*100 && Pan.WK2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1) Pan.Pieceboard[Cx][Cy]=21;
			else if (N==2) Pan.Pieceboard[Cx][Cy]=22;
			Pan.Chessboard[Cx][Cy]=2;
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=2;
				if (N==1) Pan.Pieceboard[I][J]=21;
				else if (N==2) Pan.Pieceboard[I][J]=22;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=2;
				if (N==1) Pan.Pieceboard[I][J]=21;
				else if (N==2) Pan.Pieceboard[I][J]=22;
				return true;
				}
			}
		else return false;
		}
//****Bishop
	public static boolean PlayableWhiteBishop(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.WB1x==25+i*100 && Pan.WB1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.WB2x==25+i*100 && Pan.WB2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1) Pan.Pieceboard[Cx][Cy]=31;
			else if (N==2) Pan.Pieceboard[Cx][Cy]=32;
			Pan.Chessboard[Cx][Cy]=3;
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=3;
				if (N==1) Pan.Pieceboard[I][J]=31;
				else if (N==2) Pan.Pieceboard[I][J]=32;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=3;
				if (N==1) Pan.Pieceboard[I][J]=31;
				else if (N==2) Pan.Pieceboard[I][J]=32;
				return true;
				}
			}
		else return false;
		}
//****Rook
	public static boolean PlayableWhiteRook(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.WR1x==25+i*100 && Pan.WR1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.WR2x==25+i*100 && Pan.WR2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1) Pan.Pieceboard[Cx][Cy]=41;
			else if (N==2) Pan.Pieceboard[Cx][Cy]=42;
			Pan.Chessboard[Cx][Cy]=4;
			Pan.PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=4;
				if (N==1) Pan.Pieceboard[I][J]=41;
				else if (N==2) Pan.Pieceboard[I][J]=42;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=4;
				if (N==1) Pan.Pieceboard[I][J]=41;
				else if (N==2) Pan.Pieceboard[I][J]=42;
				return true;
				}
			}
		else return false;
		}
//***Queen
	public static boolean PlayableWhiteQueen(int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==3){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Pan.WQx==25+i*100 && Pan.WQy==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			Pan.Chessboard[Cx][Cy]=5;
			Pan.Pieceboard[Cx][Cy]=5;
			PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=5;
				Pan.Pieceboard[I][J]=5;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=5;
				Pan.Pieceboard[I][J]=5;
				return true;
				}
			}
		else return false;
		}
//***King
	public static boolean PlayableWhiteKing(int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==3){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Pan.WKgx==25+i*100 && Pan.WKgy==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			Pan.Chessboard[Cx][Cy]=6;
			Pan.Pieceboard[Cx][Cy]=6;
			PieceRefresh();
			if (Pan.isWhiteKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=6;
				Pan.Pieceboard[I][J]=6;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=6;
				Pan.Pieceboard[I][J]=6;
				return true;
				}
			}
		else return false;
		}

//***Black
//**Pawn
	public static boolean PlayableBlackPawn(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==4){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int C4 = 0;
			int P4 = 0;
			if (Cy+1<8){
				C4 = Pan.Chessboard[Cx][Cy+1];
				P4 = Pan.Pieceboard[Cx][Cy+1];
				}
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.BP1x==25+i*100 && Pan.BP1y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP1y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.BP2x==25+i*100 && Pan.BP2y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP2y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==3) if (Pan.BP3x==25+i*100 && Pan.BP3y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP3y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==4) if (Pan.BP4x==25+i*100 && Pan.BP4y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP4y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==5) if (Pan.BP5x==25+i*100 && Pan.BP5y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP5y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==6) if (Pan.BP6x==25+i*100 && Pan.BP6y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP6y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==7) if (Pan.BP7x==25+i*100 && Pan.BP7y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP7y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==8) if (Pan.BP8x==25+i*100 && Pan.BP8y==725-j*100){
						I=i;
						J=j;
						if (Pan.BP8y==125 && Cy==4) Pan.Pawnboard[Cx][Cy]=2;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;	
						}
					}
				}
			if (N==1) Pan.Pieceboard[Cx][Cy]=71;
			else if (N==2) Pan.Pieceboard[Cx][Cy]=72;
			else if (N==3) Pan.Pieceboard[Cx][Cy]=73;
			else if (N==4) Pan.Pieceboard[Cx][Cy]=74;
			else if (N==5) Pan.Pieceboard[Cx][Cy]=75;
			else if (N==6) Pan.Pieceboard[Cx][Cy]=76;
			else if (N==7) Pan.Pieceboard[Cx][Cy]=77;
			else if (N==8) Pan.Pieceboard[Cx][Cy]=78;
			Pan.Chessboard[Cx][Cy]=7;
			if (Pan.Moveboard[Cx][Cy]==4){
				Pan.Chessboard[Cx][Cy+1]=0;
				Pan.Pieceboard[Cx][Cy+1]=0;
				}
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				if (Cy+1<8){
					Pan.Chessboard[Cx][Cy+1]=C4;
					Pan.Pieceboard[Cx][Cy+1]=P4;
					}
				Pan.Chessboard[I][J]=7;
				if (N==1) Pan.Pieceboard[I][J]=71;
				else if (N==2) Pan.Pieceboard[I][J]=72;
				else if (N==3) Pan.Pieceboard[I][J]=73;
				else if (N==4) Pan.Pieceboard[I][J]=74;
				else if (N==5) Pan.Pieceboard[I][J]=75;
				else if (N==6) Pan.Pieceboard[I][J]=76;
				else if (N==7) Pan.Pieceboard[I][J]=77;
				else if (N==8) Pan.Pieceboard[I][J]=78;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				if (Cy+1<8){
					Pan.Chessboard[Cx][Cy+1]=C4;
					Pan.Pieceboard[Cx][Cy+1]=P4;
					}
				Pan.Chessboard[I][J]=7;
				if (N==1) Pan.Pieceboard[I][J]=71;
				else if (N==2) Pan.Pieceboard[I][J]=72;
				else if (N==3) Pan.Pieceboard[I][J]=73;
				else if (N==4) Pan.Pieceboard[I][J]=74;
				else if (N==5) Pan.Pieceboard[I][J]=75;
				else if (N==6) Pan.Pieceboard[I][J]=76;
				else if (N==7) Pan.Pieceboard[I][J]=77;
				else if (N==8) Pan.Pieceboard[I][J]=78;
				return true;
				}
			}
		else return false;
		}

//***Knight
	public static boolean PlayableBlackKnight(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.BK1x==25+i*100 && Pan.BK1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.BK2x==25+i*100 && Pan.BK2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1) Pan.Pieceboard[Cx][Cy]=81;
			else if (N==2) Pan.Pieceboard[Cx][Cy]=82;
			Pan.Chessboard[Cx][Cy]=8;
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=8;
				if (N==1) Pan.Pieceboard[I][J]=81;
				else if (N==2) Pan.Pieceboard[I][J]=82;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=8;
				if (N==1) Pan.Pieceboard[I][J]=81;
				else if (N==2) Pan.Pieceboard[I][J]=82;
				return true;
				}
			}
		else return false;
		}
//****Bishop
	public static boolean PlayableBlackBishop(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.BB1x==25+i*100 && Pan.BB1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.BB2x==25+i*100 && Pan.BB2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1) Pan.Pieceboard[Cx][Cy]=91;
			else if (N==2) Pan.Pieceboard[Cx][Cy]=92;
			Pan.Chessboard[Cx][Cy]=9;
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=9;
				if (N==1) Pan.Pieceboard[I][J]=91;
				else if (N==2) Pan.Pieceboard[I][J]=92;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=9;
				if (N==1) Pan.Pieceboard[I][J]=91;
				else if (N==2) Pan.Pieceboard[I][J]=92;
				return true;
				}
			}
		else return false;
		}
//****Rook
	public static boolean PlayableBlackRook(int N, int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (N==1) if (Pan.BR1x==25+i*100 && Pan.BR1y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					if (N==2) if (Pan.BR2x==25+i*100 && Pan.BR2y==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			if (N==1) Pan.Pieceboard[Cx][Cy]=101;
			else if (N==2) Pan.Pieceboard[Cx][Cy]=102;
			Pan.Chessboard[Cx][Cy]=10;
			Pan.PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=10;
				if (N==1) Pan.Pieceboard[I][J]=101;
				else if (N==2) Pan.Pieceboard[I][J]=102;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=10;
				if (N==1) Pan.Pieceboard[I][J]=101;
				else if (N==2) Pan.Pieceboard[I][J]=102;
				return true;
				}
			}
		else return false;
		}
//***Queen
	public static boolean PlayableBlackQueen(int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==3){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Pan.BQx==25+i*100 && Pan.BQy==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			Pan.Chessboard[Cx][Cy]=11;
			Pan.Pieceboard[Cx][Cy]=111;
			PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=11;
				Pan.Pieceboard[I][J]=111;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=11;
				Pan.Pieceboard[I][J]=111;
				return true;
				}
			}
		else return false;
		}
//***King
	public static boolean PlayableBlackKing(int Cx, int Cy){
		if (Pan.Moveboard[Cx][Cy]==1 || Pan.Moveboard[Cx][Cy]==2 || Pan.Moveboard[Cx][Cy]==3){
			int C = Pan.Chessboard[Cx][Cy];
			int P = Pan.Pieceboard[Cx][Cy];
			int I=0;
			int J=0;
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (Pan.BKgx==25+i*100 && Pan.BKgy==725-j*100){
						I=i;
						J=j;
						Pan.Chessboard[i][j]=0;
						Pan.Pieceboard[i][j]=0;
						break;
						}
					}
				}
			Pan.Chessboard[Cx][Cy]=12;
			Pan.Pieceboard[Cx][Cy]=121;
			PieceRefresh();
			if (Pan.isBlackKingCheck()){
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=12;
				Pan.Pieceboard[I][J]=121;
				return false;
				}
			else{
				Pan.Chessboard[Cx][Cy]=C;
				Pan.Pieceboard[Cx][Cy]=P;
				Pan.Chessboard[I][J]=12;
				Pan.Pieceboard[I][J]=121;
				return true;
				}
			}
		else return false;
		}
//*****Fin Playable Pieces

	public static int [] XYPiece(int piece){
		int [] XY = new int [2];
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (piece==Pieceboard[i][j]){
					XY[0]=i;
					XY[1]=j;
					break;
					}
				}
			}
		return XY;
		}	
	public static boolean WhiteKingNoAround(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (WKgx==25+i*100 && WKgy==725-j*100){
					if (i+1<8){
						if (Blackboard[i+1][j]==0 && (Chessboard[i+1][j]==0 || Chessboard[i+1][j]>=7)) return false;
						if (j+1<8) if (Blackboard[i+1][j+1]==0 && (Chessboard[i+1][j+1]==0 || Chessboard[i+1][j+1]>=7)) return false;
						if (j-1>=0) if (Blackboard[i+1][j-1]==0 && (Chessboard[i+1][j-1]==0 || Chessboard[i+1][j-1]>=7)) return false;
						}
					if (i-1>=0){
						if (Blackboard[i-1][j]==0 && (Chessboard[i-1][j]==0 || Chessboard[i-1][j]>=7)) return false;
						if (j+1<8) if (Blackboard[i-1][j+1]==0 && (Chessboard[i-1][j+1]==0 || Chessboard[i-1][j+1]>=7)) return false;
						if (j-1>=0) if (Blackboard[i-1][j-1]==0 && (Chessboard[i-1][j-1]==0 || Chessboard[i-1][j-1]>=7)) return false;
						}
					if (j+1<8) if (Blackboard[i][j+1]==0 && (Chessboard[i][j+1]==0 || Chessboard[i][j+1]>=7)) return false;
					if (j-1>=0) if (Blackboard[i][j-1]==0 && (Chessboard[i][j-1]==0 || Chessboard[i][j-1]>=7)) return false;
					}
				}
			}
		return true;
		}		

	public static boolean BlackKingNoAround(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (BKgx==25+i*100 && BKgy==725-j*100){
					if (i+1<8){
						if (Whiteboard[i+1][j]==0 && Chessboard[i+1][j]<7) return false;
						if (j+1<8) if (Whiteboard[i+1][j+1]==0 && Chessboard[i+1][j+1]<7) return false;
						if (j-1>=0) if (Whiteboard[i+1][j-1]==0 && Chessboard[i+1][j-1]<7) return false;
						}
					if (i-1>=0){
						if (Whiteboard[i-1][j]==0 && Chessboard[i-1][j]<7) return false;
						if (j+1<8) if (Whiteboard[i-1][j+1]==0 && Chessboard[i-1][j+1]<7) return false;
						if (j-1>=0) if (Whiteboard[i-1][j-1]==0 && Chessboard[i-1][j-1]<7) return false;
						}
					if (j+1<8) if (Whiteboard[i][j+1]==0 && Chessboard[i][j+1]<7) return false;
					if (j-1>=0) if (Whiteboard[i][j-1]==0 && Chessboard[i][j-1]<7) return false;
					}
				}
			}
		return true;
		}
		
	public static boolean PlayableWhitePiece(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (WP1Taken==0){
					MoveWhitePawn(WP1y, XYPiece(11)[0], XYPiece(11)[1]);
					if (PlayableWhitePawn(1, i, j)) return true;
					}
				if (WP2Taken==0){
					MoveWhitePawn(WP2y, XYPiece(12)[0], XYPiece(12)[1]);
					if (PlayableWhitePawn(2, i, j)) return true;
					}
				if (WP3Taken==0){
					MoveWhitePawn(WP3y, XYPiece(13)[0], XYPiece(13)[1]);
					if (PlayableWhitePawn(3, i, j)) return true;
					}
				if (WP4Taken==0){
					MoveWhitePawn(WP4y, XYPiece(14)[0], XYPiece(14)[1]);
					if (PlayableWhitePawn(4, i, j)) return true;
					}
				if (WP5Taken==0){
					MoveWhitePawn(WP5y, XYPiece(15)[0], XYPiece(15)[1]);
					if (PlayableWhitePawn(5, i, j)) return true;
					}
				if (WP6Taken==0){
					MoveWhitePawn(WP6y, XYPiece(16)[0], XYPiece(16)[1]);
					if (PlayableWhitePawn(6, i, j)) return true;
					}
				if (WP7Taken==0){
					MoveWhitePawn(WP7y, XYPiece(17)[0], XYPiece(17)[1]);
					if (PlayableWhitePawn(7, i, j)) return true;
					}
				if (WP8Taken==0){
					MoveWhitePawn(WP8y, XYPiece(18)[0], XYPiece(18)[1]);
					if (PlayableWhitePawn(8, i, j)) return true;
					}
				if (WK1Taken==0){
					MoveWhiteKnight(XYPiece(21)[0], XYPiece(21)[1]);
					if (PlayableWhiteKnight(1, i, j)) return true;
					}
				if (WK2Taken==0){
					MoveWhiteKnight(XYPiece(22)[0], XYPiece(22)[1]);
					if (PlayableWhiteKnight(2, i, j)) return true;
					}
				if (WB1Taken==0){
					MoveWhiteBishop(XYPiece(31)[0], XYPiece(31)[1]);
					if (PlayableWhiteBishop(1, i, j)) return true;
					}
				if (WB2Taken==0){
					MoveWhiteBishop(XYPiece(32)[0], XYPiece(32)[1]);
					if (PlayableWhiteBishop(2, i, j)) return true;
					}
				if (WR1Taken==0){
					MoveWhiteRook(XYPiece(41)[0], XYPiece(41)[1]);
					if (PlayableWhiteRook(1, i, j)) return true;
					}
				if (WR2Taken==0){
					MoveWhiteRook(XYPiece(42)[0], XYPiece(42)[1]);
					if (PlayableWhiteRook(2, i, j)) return true;
					}
				if (WQTaken==0){
					MoveWhiteQueen(XYPiece(5)[0], XYPiece(5)[1]);
					if (PlayableWhiteQueen(i, j)) return true;
					}
				MoveWhiteKing(XYPiece(6)[0], XYPiece(6)[1]);
				if (PlayableWhiteKing(i, j)) return true;
				}
			}
		return false;
		}
	public static boolean NoPlayableBlackPiece(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (BP1Taken==0){
					MoveBlackPawn(BP1y, XYPiece(71)[0], XYPiece(71)[1]);
					if (PlayableBlackPawn(1, i, j)) return false;
					}
				if (BP2Taken==0){
					MoveBlackPawn(BP2y, XYPiece(72)[0], XYPiece(72)[1]);
					if (PlayableBlackPawn(2, i, j)) return false;
					}
				if (BP3Taken==0){
					MoveBlackPawn(BP3y, XYPiece(73)[0], XYPiece(73)[1]);
					if (PlayableBlackPawn(3, i, j)) return false;
					}
				if (BP4Taken==0){
					MoveBlackPawn(BP4y, XYPiece(74)[0], XYPiece(74)[1]);
					if (PlayableBlackPawn(4, i, j)) return false;
					}
				if (BP5Taken==0){
					MoveBlackPawn(BP5y, XYPiece(75)[0], XYPiece(75)[1]);
					if (PlayableBlackPawn(5, i, j)) return false;
					}
				if (BP6Taken==0){
					MoveBlackPawn(BP6y, XYPiece(76)[0], XYPiece(76)[1]);
					if (PlayableBlackPawn(6, i, j)) return false;
					}
				if (BP7Taken==0){
					MoveBlackPawn(BP7y, XYPiece(77)[0], XYPiece(77)[1]);
					if (PlayableBlackPawn(7, i, j)) return false;
					}
				if (BP8Taken==0){
					MoveBlackPawn(BP8y, XYPiece(78)[0], XYPiece(78)[1]);
					if (PlayableBlackPawn(8, i, j)) return false;
					}
				if (BK1Taken==0){
					MoveBlackKnight(XYPiece(81)[0], XYPiece(81)[1]);
					if (PlayableBlackKnight(1, i, j)) return false;
					}
				if (BK2Taken==0){
					MoveBlackKnight(XYPiece(82)[0], XYPiece(82)[1]);
					if (PlayableBlackKnight(2, i, j)) return false;
					}
				if (BB1Taken==0){
					MoveBlackBishop(XYPiece(91)[0], XYPiece(91)[1]);
					if (PlayableBlackBishop(1, i, j)) return false;
					}
				if (BB2Taken==0){
					MoveBlackBishop(XYPiece(92)[0], XYPiece(92)[1]);
					if (PlayableBlackBishop(2, i, j)) return false;
					}
				if (BR1Taken==0){
					MoveBlackRook(XYPiece(101)[0], XYPiece(101)[1]);
					if (PlayableBlackRook(1, i, j)) return false;
					}
				if (BR2Taken==0){
					MoveBlackRook(XYPiece(102)[0], XYPiece(102)[1]);
					if (PlayableBlackRook(2, i, j)) return false;
					}
				if (BQTaken==0){
					MoveBlackQueen(XYPiece(111)[0], XYPiece(111)[1]);
					if (PlayableBlackQueen(i, j)) return false;
					}
				//WhitePieceDanger();
				MoveBlackKing(XYPiece(121)[0], XYPiece(121)[1]);
				if (PlayableBlackKing(i, j)) return false;
				}
			}
		return true;
		}

	public static boolean isWhiteKingMat(){
		return isWhiteKingCheck() && WhiteKingNoAround() && PlayableWhitePiece()==false;
		}

	public static boolean isBlackKingMat(){
		return isBlackKingCheck() && BlackKingNoAround() && NoPlayableBlackPiece();
		}
	public static boolean isWhiteKingPat(){
		return WhiteKingNoAround() && PlayableWhitePiece()==false;
		}
	public static boolean isBlackKingPat(){
		return BlackKingNoAround() && NoPlayableBlackPiece();
		}
//*****Ordi
	public static int [] Pij(int piece){
		int [] Pxy = new int [2];
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Pieceboard[i][j]==piece){
					Pxy[0]=i;
					Pxy[1]=j;
					break;
					}
				}
			}
		return Pxy;
		}
					

	public static boolean isBlackCheckWhite(int piece, int X, int Y){
		int pI=Pij(piece)[0];
		int pJ=Pij(piece)[1];
		int P = Pieceboard[X][Y];
		Pieceboard[Pij(piece)[0]][Pij(piece)[1]]=0;
		Pieceboard[X][Y]=piece;
		BlackPieceDanger();
		if (isWhiteKingCheck()){
			Pieceboard[X][Y]=P;
			Pieceboard[pI][pJ]=piece;
			return true;
			}
		else{
			Pieceboard[X][Y]=P;
			Pieceboard[pI][pJ]=piece;
			return false;
			}
		}

	public static int OrdiX(){
		int n=0;
		int X[] = new int [64];
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Moveboard[i][j]!=0){
					X[n]=i;
					n+=1;
					}
				}
			}
		if (n==0) return 13;
		else{
			int N = (int)(Math.random()*n);
			return X[N];
			}
		}
	public static int OrdiY(){
		int n1=0;
		int n2=0;
		int Y1[] = new int [64];
		int Y2[] = new int [64];
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Moveboard[i][j]==1){
					Y1[n1]=j;
					n1+=1;
					}
				if (Moveboard[i][j]==2){
					Y2[n2]=j;
					n2+=1;
					}
				}
			}
		if (n1==0 && n2==0) return 13;
		else{
			if (n2!=0){
				int N2 = (int)(Math.random()*n2);
				return Y2[N2];
				}				
			else {
				int N1 = (int)(Math.random()*n1);
				return Y1[N1];
				}
			}
		}
	public static int [] OrdiXY(int piece){
		int [] XY = {0,0};
		int n1=0; 		//Compte les cases vides, roc ou passantes
		int n2=0; 		//Compte les cases prenables
		int X1[] = new int [64];  //Enregistre le x des cases vides
		int X2[] = new int [64];  //Enregistre le x des cases prenables
		int Y1[] = new int [64];  //Enregistre le y des cases vides
		int Y2[] = new int [64];  //Enregistre le y des cases prenables

		if (piece==71) MoveBlackPawn(BP1y, Pij(71)[0], Pij(71)[1]);
		else if (piece==72) MoveBlackPawn(BP2y, Pij(72)[0], Pij(72)[1]);
		else if (piece==73) MoveBlackPawn(BP3y, Pij(73)[0], Pij(73)[1]);
		else if (piece==74) MoveBlackPawn(BP4y, Pij(74)[0], Pij(74)[1]);
		else if (piece==75) MoveBlackPawn(BP5y, Pij(75)[0], Pij(75)[1]);
		else if (piece==76) MoveBlackPawn(BP6y, Pij(76)[0], Pij(76)[1]);
		else if (piece==77) MoveBlackPawn(BP7y, Pij(77)[0], Pij(77)[1]);
		else if (piece==78) MoveBlackPawn(BP8y, Pij(78)[0], Pij(78)[1]);
		else if (piece==81) MoveBlackKnight(Pij(81)[0], Pij(81)[1]);
		else if (piece==82) MoveBlackKnight(Pij(82)[0], Pij(82)[1]);
		else if (piece==91) MoveBlackBishop(Pij(91)[0], Pij(91)[1]);
		else if (piece==92) MoveBlackBishop(Pij(92)[0], Pij(92)[1]);
		else if (piece==101) MoveBlackRook(Pij(101)[0], Pij(101)[1]);
		else if (piece==102) MoveBlackRook(Pij(102)[0], Pij(102)[1]);
		else if (piece==111) MoveBlackQueen(Pij(111)[0], Pij(111)[1]);
		else if (piece==121) MoveBlackKing(Pij(121)[0], Pij(121)[1]);

		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Moveboard[i][j]!=2 && Moveboard[i][j]!=0){
					X1[n1]=i;
					Y1[n1]=j;
					n1+=1;
					}
				if (Moveboard[i][j]==2){
					X2[n2]=i;
					Y2[n2]=j;
					n2+=1;
					}
				}
			}
		if (n1==0 && n2==0) XY[0]=XY[1]=13;
		else{	
			for(int i=0; n1!=0 && i<n1; i++){
				if (isBlackCheckWhite(piece, X1[i], Y1[i])){
					XY[0]=X1[i];
					XY[1]=Y1[i];
					break;
					}
				}
			for(int i=0; n2!=0 && i<n2; i++){
				if (isBlackCheckWhite(piece, X2[i], Y2[i])){
					XY[0]=X2[i];
					XY[1]=Y2[i];
					break;
					}
				}
			if (n2!=0 && XY[0]==0 && XY[1]==0){
				for(int i=0; i<n2; i++){
					if (71<=piece && piece<=78){
						if (1<Chessboard[X2[i]][Y2[i]] && Chessboard[X2[i]][Y2[i]]<7){
							XY[0]=X2[i];
							XY[1]=Y2[i];
							break;
							}
						}	
					if (Chessboard[X2[i]][Y2[i]]==5 && piece!=111){
						XY[0]=X2[i];
						XY[1]=Y2[i];
						break;
						}
					if (Whiteboard[X2[i]][Y2[i]]==0){
						XY[0]=X2[i];
						XY[1]=Y2[i];
						break;
						}
					if (Blackboard[X2[i]][Y2[i]]>Whiteboard[X2[i]][Y2[i]]){
						XY[0]=X2[i];
						XY[1]=Y2[i];
						break;
						}
					}
				//int N2 = (int)(Math.random()*n2);
				//XY[0] = X2[N2];
				//XY[1] = Y2[N2];
				}
			if (n1!=0 && XY[0]==0 && XY[1]==0){
				for(int i=0; i<n1; i++){
					if (Whiteboard[X1[i]][Y1[i]]==0){
						XY[0]=X1[i];
						XY[1]=Y1[i];
						break;
						}
					if (Blackboard[X1[i]][Y1[i]]>0){
						XY[0]=X1[i];
						XY[1]=Y1[i];
						break;
						}
					if (Blackboard[X1[i]][Y1[i]]>Whiteboard[X1[i]][Y1[i]]){
						XY[0]=X1[i];
						XY[1]=Y1[i];
						break;
						}
					}
				//int N1 = (int)(Math.random()*n1);
				//XY[0] = X1[N1];
				//XY[1] = Y1[N1];
				}
			}
		if (XY[0]==0 && XY[1]==0){
			if (n2!=0){
				int N2 = (int)(Math.random()*n2);
				XY[0] = X2[N2];
				XY[1] = Y2[N2];
				}
			else if (n1!=0){
				int N1 = (int)(Math.random()*n1);
				XY[0] = X1[N1];
				XY[1] = Y1[N1];
				}
			}
		return XY;
		}

	public static void Ordi2(int Side){
		int WhitePiece[] = {11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 31, 32, 41, 42, 5, 6};
		int BlackPiece[] = {71, 72, 73, 74, 75, 76, 77, 78, 81, 82, 91, 92, 101, 102, 111, 121};
		int I=0;
		int J=0;
		int PieceChoice = (int)(Math.random()*16);
		if (Side==1) while (PieceAlive(WhitePiece[PieceChoice])==false) PieceChoice = (int)(Math.random()*16);
		else if (Side==2) while (PieceAlive(BlackPiece[PieceChoice])==false) PieceChoice = (int)(Math.random()*16);
		for(int i=0; i<16; i++){
			if (OrdiXY(BlackPiece[i])[0]!=13 && OrdiXY(BlackPiece[i])[1]!=13){
				int Cxy = Chessboard[OrdiXY(BlackPiece[i])[0]][OrdiXY(BlackPiece[i])[1]];
				int Wxy = Whiteboard[OrdiXY(BlackPiece[i])[0]][OrdiXY(BlackPiece[i])[1]];
				int Bxy = Blackboard[OrdiXY(BlackPiece[i])[0]][OrdiXY(BlackPiece[i])[1]];
				if (Whiteboard[Pij(BlackPiece[15-i])[0]][Pij(BlackPiece[15-i])[1]]>0 && Blackboard[Pij(BlackPiece[15-i])[0]][Pij(BlackPiece[15-i])[1]]==0){
					PieceChoice=i;
					break;
					}
				if (71<=BlackPiece[i] && 78<=BlackPiece[i] && 1<Cxy && Cxy<7){
					PieceChoice=i;
					break;
					}
				if (Cxy==5){
					PieceChoice=i;
					break;
					}
				if (0<Cxy && Cxy<7 && Wxy==0){
					PieceChoice=i;
					break;
					}
				}
			}
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Side==1) if (WhitePiece[PieceChoice]==Pieceboard[i][j]){
					I=i;
					J=j;
					break;
					}
				if (Side==2) if (BlackPiece[PieceChoice]==Pieceboard[i][j]){
					I=i;
					J=j;
					break;
					}
				}
			}
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Moveboard[i][j]=0;
				}
			}
		if (BlackPiece[PieceChoice]==71){
			if (BP1Promotion==0){
				if (OrdiXY(71)[0]!=13 && OrdiXY(71)[1]!=13) PlayBlackPawn(1, OrdiXY(71)[0], OrdiXY(71)[1]);
				}
			else if (BP1Promotion==2) {
				MoveBlackKnight(I, J);
				if (OrdiXY(71)[0]!=13 && OrdiXY(71)[1]!=13) PlayBlackPawn(1, OrdiXY(71)[0], OrdiXY(71)[1]);
				}
			else if (BP1Promotion==3){
				MoveBlackBishop(I, J);
				if (OrdiXY(71)[0]!=13 && OrdiXY(71)[1]!=13) PlayBlackPawn(1, OrdiXY(71)[0], OrdiXY(71)[1]);
				}
			else if (BP1Promotion==4){
				MoveBlackRook(I, J);
				if (OrdiXY(71)[0]!=13 && OrdiXY(71)[1]!=13) PlayBlackPawn(1, OrdiXY(71)[0], OrdiXY(71)[1]);
				}
			else if (BP1Promotion==5){
				MoveBlackQueen(I, J);
				if (OrdiXY(71)[0]!=13 && OrdiXY(71)[1]!=13) PlayBlackPawn(1, OrdiXY(71)[0], OrdiXY(71)[1]);
				}
			}
		else if (BlackPiece[PieceChoice]==72){
			if (OrdiXY(72)[0]!=13 && OrdiXY(72)[1]!=13) PlayBlackPawn(2, OrdiXY(72)[0], OrdiXY(72)[1]);
			}
		else if (BlackPiece[PieceChoice]==73){
			if (OrdiXY(73)[0]!=13 && OrdiXY(73)[1]!=13) PlayBlackPawn(3, OrdiXY(73)[0], OrdiXY(73)[1]);
			}
		else if (BlackPiece[PieceChoice]==74){
			if (OrdiXY(74)[0]!=13 && OrdiXY(74)[1]!=13) PlayBlackPawn(4, OrdiXY(74)[0], OrdiXY(74)[1]);
			}
		else if (BlackPiece[PieceChoice]==75){
			if (OrdiXY(75)[0]!=13 && OrdiXY(75)[1]!=13) PlayBlackPawn(5, OrdiXY(75)[0], OrdiXY(75)[1]);
			}
		else if (BlackPiece[PieceChoice]==76){
			if (OrdiXY(76)[0]!=13 && OrdiXY(76)[1]!=13) PlayBlackPawn(6, OrdiXY(76)[0], OrdiXY(76)[1]);
			}
		else if (BlackPiece[PieceChoice]==77){
			if (OrdiXY(77)[0]!=13 && OrdiXY(77)[1]!=13) PlayBlackPawn(7, OrdiXY(77)[0], OrdiXY(77)[1]);
			}
		else if (BlackPiece[PieceChoice]==78){
			if (OrdiXY(78)[0]!=13 && OrdiXY(78)[1]!=13) PlayBlackPawn(8, OrdiXY(78)[0], OrdiXY(78)[1]);
			}
		else if (BlackPiece[PieceChoice]==81){
			if (OrdiXY(81)[0]!=13 && OrdiXY(81)[1]!=13) PlayBlackKnight(1, OrdiXY(81)[0], OrdiXY(81)[1]);
			}
		else if (BlackPiece[PieceChoice]==82){
			if (OrdiXY(82)[0]!=13 && OrdiXY(82)[1]!=13) PlayBlackKnight(2, OrdiXY(82)[0], OrdiXY(82)[1]);
			}
		else if (BlackPiece[PieceChoice]==91){
			if (OrdiXY(91)[0]!=13 && OrdiXY(91)[1]!=13) PlayBlackBishop(1, OrdiXY(91)[0], OrdiXY(91)[1]);
			}
		else if (BlackPiece[PieceChoice]==92){
			if (OrdiXY(92)[0]!=13 && OrdiXY(92)[1]!=13) PlayBlackBishop(2, OrdiXY(92)[0], OrdiXY(92)[1]);
			}
		else if (BlackPiece[PieceChoice]==101){
			if (OrdiXY(101)[0]!=13 && OrdiXY(101)[1]!=13) PlayBlackRook(1, OrdiXY(101)[0], OrdiXY(101)[1]);
			}
		else if (BlackPiece[PieceChoice]==102){
			if (OrdiXY(102)[0]!=13 && OrdiXY(102)[1]!=13) PlayBlackRook(2, OrdiXY(102)[0], OrdiXY(102)[1]);
			}
		else if (BlackPiece[PieceChoice]==111){
			if (OrdiXY(111)[0]!=13 && OrdiXY(111)[1]!=13) PlayBlackQueen(OrdiXY(111)[0], OrdiXY(111)[1]);
			}
		else if (BlackPiece[PieceChoice]==121){
			if (OrdiXY(121)[0]!=13 && OrdiXY(121)[1]!=13) PlayBlackKing(OrdiXY(121)[0], OrdiXY(121)[1]);
			}
		}
		
	public static void PiecePlace(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Pieceboard[i][j]==11){
					WP1x=25+i*100;
					WP1y=725-j*100;
					}
				if (Pieceboard[i][j]==12){
					WP2x=25+i*100;
					WP2y=725-j*100;
					}
				if (Pieceboard[i][j]==13){
					WP3x=25+i*100;
					WP3y=725-j*100;
					}
				if (Pieceboard[i][j]==14){
					WP4x=25+i*100;
					WP4y=725-j*100;
					}
				if (Pieceboard[i][j]==15){
					WP5x=25+i*100;
					WP5y=725-j*100;
					}
				if (Pieceboard[i][j]==16){
					WP6x=25+i*100;
					WP6y=725-j*100;
					}
				if (Pieceboard[i][j]==17){
					WP7x=25+i*100;
					WP7y=725-j*100;
					}
				if (Pieceboard[i][j]==18){
					WP8x=25+i*100;
					WP8y=725-j*100;
					}
				if (Pieceboard[i][j]==21){
					WK1x=25+i*100;
					WK1y=725-j*100;
					}
				if (Pieceboard[i][j]==22){
					WK2x=25+i*100;
					WK2y=725-j*100;
					}
				if (Pieceboard[i][j]==31){
					WB1x=25+i*100;
					WB1y=725-j*100;
					}
				if (Pieceboard[i][j]==32){
					WB2x=25+i*100;
					WB2y=725-j*100;
					}
				if (Pieceboard[i][j]==41){
					WR1x=25+i*100;
					WR1y=725-j*100;
					}
				if (Pieceboard[i][j]==42){
					WR2x=25+i*100;
					WR2y=725-j*100;
					}
				if (Pieceboard[i][j]==5){
					WQx=25+i*100;
					WQy=725-j*100;
					}
				if (Pieceboard[i][j]==6){
					WKgx=25+i*100;
					WKgy=725-j*100;
					}

				if (Pieceboard[i][j]==71){
					BP1x=25+i*100;
					BP1y=725-j*100;
					}
				if (Pieceboard[i][j]==72){
					BP2x=25+i*100;
					BP2y=725-j*100;
					}
				if (Pieceboard[i][j]==73){
					BP3x=25+i*100;
					BP3y=725-j*100;
					}
				if (Pieceboard[i][j]==74){
					BP4x=25+i*100;
					BP4y=725-j*100;
					}
				if (Pieceboard[i][j]==75){
					BP5x=25+i*100;
					BP5y=725-j*100;
					}
				if (Pieceboard[i][j]==76){
					BP6x=25+i*100;
					BP6y=725-j*100;
					}
				if (Pieceboard[i][j]==77){
					BP7x=25+i*100;
					BP7y=725-j*100;
					}
				if (Pieceboard[i][j]==78){
					BP8x=25+i*100;
					BP8y=725-j*100;
					}
				if (Pieceboard[i][j]==81){
					BK1x=25+i*100;
					BK1y=725-j*100;
					}
				if (Pieceboard[i][j]==82){
					BK2x=25+i*100;
					BK2y=725-j*100;
					}
				if (Pieceboard[i][j]==91){
					BB1x=25+i*100;
					BB1y=725-j*100;
					}
				if (Pieceboard[i][j]==92){
					BB2x=25+i*100;
					BB2y=725-j*100;
					}
				if (Pieceboard[i][j]==101){
					BR1x=25+i*100;
					BR1y=725-j*100;
					}
				if (Pieceboard[i][j]==102){
					BR2x=25+i*100;
					BR2y=725-j*100;
					}
				if (Pieceboard[i][j]==111){
					BQx=25+i*100;
					BQy=725-j*100;
					}
				if (Pieceboard[i][j]==121){
					BKgx=25+i*100;
					BKgy=725-j*100;
					}
				}
			}
		}
	
	public static void Switch(){
		int [][] alterChessboard = Chessboard;
		int [][] alterPieceboard = Pieceboard;
		Afficherboard(alterChessboard);
		System.out.println();
		Afficherboard(alterPieceboard);
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (Chessboard[i][j]!=0) Chessboard[i][j]=0;
				if (Pieceboard[i][j]!=0) Pieceboard[i][j]=0;
				}
			}
		Afficherboard(alterChessboard);
		System.out.println();
		Afficherboard(alterPieceboard);
		for(int i=0; i<8; i++){
			Chessboard[i][0] = alterChessboard[7-i][7];
			Chessboard[i][1] = alterChessboard[7-i][6];
			Chessboard[i][2] = alterChessboard[7-i][5];
			Chessboard[i][3] = alterChessboard[7-i][4];
			Chessboard[i][4] = alterChessboard[7-i][3];
			Chessboard[i][5] = alterChessboard[7-i][2];
			Chessboard[i][6] = alterChessboard[7-i][1];
			Chessboard[i][7] = alterChessboard[7-i][0];
			Pieceboard[i][0] = alterPieceboard[7-i][7];
			Pieceboard[i][1] = alterPieceboard[7-i][6];
			Pieceboard[i][2] = alterPieceboard[7-i][5];
			Pieceboard[i][3] = alterPieceboard[7-i][4];
			Pieceboard[i][4] = alterPieceboard[7-i][3];
			Pieceboard[i][5] = alterPieceboard[7-i][2];
			Pieceboard[i][6] = alterPieceboard[7-i][1];
			Pieceboard[i][7] = alterPieceboard[7-i][0];
			}
		PiecePlace();	
		}
	public static void Afficherboard(int [][] B){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){	
				System.out.print("["+B[i][j]+"] ");
				if (j==7) System.out.print("\n");
				}
			}
		}


	public static void Reset(){
	Pan.gameplay=0;
	Turn=1;
	Mat=0;

	WP1x=25;
	WP1y=625;
	WP1Selected=0;
	WP1Taken=0;
	WP1Promotion=0;
	WP2x=125;
	WP2y=625;
	WP2Selected=0;
	WP2Taken=0;
	WP2Promotion=0;
	WP3x=225;
	WP3y=625;
	WP3Selected=0;
	WP3Taken=0;
	WP3Promotion=0;
	WP4x=325;
	WP4y=625;
	WP4Selected=0;
	WP4Taken=0;
	WP4Promotion=0;
	WP5x=425;
	WP5y=625;
	WP5Selected=0;
	WP5Taken=0;
	WP5Promotion=0;
	WP6x=525;
	WP6y=625;
	WP6Selected=0;
	WP6Taken=0;
	WP6Promotion=0;
	WP7x=625;
	WP7y=625;
	WP7Selected=0;
	WP7Taken=0;
	WP7Promotion=0;
	WP8x=725;
	WP8y=625;
	WP8Selected=0;
	WP8Taken=0;
	WP8Promotion=0;
	WK1x=125;
	WK1y=725;
	WK1Selected=0;
	WK1Taken=0;
	WK2x=625;
	WK2y=725;
	WK2Selected=0;
	WK2Taken=0;
	WB1x=225;
	WB1y=725;
	WB1Selected=0;
	WB1Taken=0;
	WB2x=525;
	WB2y=725;
	WB2Selected=0;
	WB2Taken=0;
	WR1x=25;
	WR1y=725;
	WR1Selected=0;
	WR1Taken=0;
	WR1NeverMoved=0;
	WR2x=725;
	WR2y=725;
	WR2Selected=0;
	WR2Taken=0;
	WR2NeverMoved=0;
	WQx=325;
	WQy=725;
	WQSelected=0;
	WQTaken=0;
	WKgx=425;
	WKgy=725;
	WKgSelected=0;
	WKgTaken=0;
	WKgNeverMoved=0;
	WKgCheck=0;

	BP1x=25;
	BP1y=125;
	BP1Selected=0;
	BP1Taken=0;
	BP1Promotion=0;
	BP2x=125;
	BP2y=125;
	BP2Selected=0;
	BP2Taken=0;
	BP2Promotion=0;
	BP3x=225;
	BP3y=125;
	BP3Selected=0;
	BP3Taken=0;
	BP3Promotion=0;
	BP4x=325;
	BP4y=125;
	BP4Selected=0;
	BP4Taken=0;
	BP4Promotion=0;
	BP5x=425;
	BP5y=125;
	BP5Selected=0;
	BP5Taken=0;
	BP5Promotion=0;
	BP6x=525;
	BP6y=125;
	BP6Selected=0;
	BP6Taken=0;
	BP6Promotion=0;
	BP7x=625;
	BP7y=125;
	BP7Selected=0;
	BP7Taken=0;
	BP7Promotion=0;
	BP8x=725;
	BP8y=125;
	BP8Selected=0;
	BP8Taken=0;
	BP8Promotion=0;
	BK1x=125;
	BK1y=25;
	BK1Selected=0;
	BK1Taken=0;
	BK2x=625;
	BK2y=25;
	BK2Selected=0;
	BK2Taken=0;
	BB1x=225;
	BB1y=25;
	BB1Selected=0;
	BB1Taken=0;
	BB2x=525;
	BB2y=25;
	BB2Selected=0;
	BB2Taken=0;
	BR1x=25;
	BR1y=25;
	BR1Selected=0;
	BR1Taken=0;
	BR1NeverMoved=0;
	BR2x=725;
	BR2y=25;
	BR2Selected=0;
	BR2Taken=0;
	BR2NeverMoved=0;
	BQx=325;
	BQy=25;
	BQSelected=0;
	BQTaken=0;
	BKgx=425;
	BKgy=25;
	BKgSelected=0;
	BKgTaken=0;
	BKgCheck=0;
	BKgNeverMoved=0;

		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Pan.Chessboard[i][j]=0;
				}
			}
		Pan.Chessboard[0][0] = 4;
		Pan.Chessboard[0][1] = 1;
		Pan.Chessboard[1][0] = 2;
		Pan.Chessboard[1][1] = 1;
		Pan.Chessboard[2][0] = 3;
		Pan.Chessboard[2][1] = 1;
		Pan.Chessboard[3][0] = 5;
		Pan.Chessboard[3][1] = 1;
		Pan.Chessboard[4][0] = 6;
		Pan.Chessboard[4][1] = 1;
		Pan.Chessboard[5][0] = 3;
		Pan.Chessboard[5][1] = 1;
		Pan.Chessboard[6][0] = 2;
		Pan.Chessboard[6][1] = 1;
		Pan.Chessboard[7][0] = 4;
		Pan.Chessboard[7][1] = 1;

		Pan.Pieceboard[0][0] = 41;
		Pan.Pieceboard[0][1] = 11;
		Pan.Pieceboard[1][0] = 21;
		Pan.Pieceboard[1][1] = 12;
		Pan.Pieceboard[2][0] = 31;
		Pan.Pieceboard[2][1] = 13;
		Pan.Pieceboard[3][0] = 5;
		Pan.Pieceboard[3][1] = 14;
		Pan.Pieceboard[4][0] = 6;
		Pan.Pieceboard[4][1] = 15;
		Pan.Pieceboard[5][0] = 32;
		Pan.Pieceboard[5][1] = 16;
		Pan.Pieceboard[6][0] = 22;
		Pan.Pieceboard[6][1] = 17;
		Pan.Pieceboard[7][0] = 42;
		Pan.Pieceboard[7][1] = 18;

		Pan.Chessboard[0][6] = 7;
		Pan.Chessboard[0][7] = 10;
		Pan.Chessboard[1][6] = 7;
		Pan.Chessboard[1][7] = 8;
		Pan.Chessboard[2][6] = 7;
		Pan.Chessboard[2][7] = 9;
		Pan.Chessboard[3][6] = 7;
		Pan.Chessboard[3][7] = 11;
		Pan.Chessboard[4][6] = 7;
		Pan.Chessboard[4][7] = 12;
		Pan.Chessboard[5][6] = 7;
		Pan.Chessboard[5][7] = 9;
		Pan.Chessboard[6][6] = 7;
		Pan.Chessboard[6][7] = 8;
		Pan.Chessboard[7][6] = 7;
		Pan.Chessboard[7][7] = 10;

		Pan.Pieceboard[0][6] = 71;
		Pan.Pieceboard[0][7] = 101;
		Pan.Pieceboard[1][6] = 72;
		Pan.Pieceboard[1][7] = 81;
		Pan.Pieceboard[2][6] = 73;
		Pan.Pieceboard[2][7] = 91;
		Pan.Pieceboard[3][6] = 74;
		Pan.Pieceboard[3][7] = 111;
		Pan.Pieceboard[4][6] = 75;
		Pan.Pieceboard[4][7] = 121;
		Pan.Pieceboard[5][6] = 76;
		Pan.Pieceboard[5][7] = 92;
		Pan.Pieceboard[6][6] = 77;
		Pan.Pieceboard[6][7] = 82;
		Pan.Pieceboard[7][6] = 78;
		Pan.Pieceboard[7][7] = 102;

		for(int i=1; i<200; i++){
			for(int j=0; j<119; j++){
				Historical[i][j]=0;
				}
			}
		for(int i=1; i<200; i++){
			for(int j=0; j<8; j++){
				for(int k=0; k<8; k++){
					HistoChess[i][j][k]=0;
					HistoPiece[i][j][k]=0;
					}
				}
			}
		T=0;
		HistoRecord();
		}

	public static void HistoRecord(){
		Historical[T][0]=WP1x;
		Historical[T][1]=WP1y;
		Historical[T][2]=WP1Taken;
		Historical[T][3]=WP1Promotion;
		Historical[T][4]=WP2x;
		Historical[T][5]=WP2y;
		Historical[T][6]=WP2Taken;
		Historical[T][7]=WP2Promotion;
		Historical[T][8]=WP3x;
		Historical[T][9]=WP3y;
		Historical[T][10]=WP3Taken;
		Historical[T][11]=WP3Promotion;
		Historical[T][12]=WP4x;
		Historical[T][13]=WP4y;
		Historical[T][14]=WP4Taken;
		Historical[T][15]=WP4Promotion;
		Historical[T][16]=WP5x;
		Historical[T][17]=WP5y;
		Historical[T][18]=WP5Taken;
		Historical[T][19]=WP5Promotion;
		Historical[T][20]=WP6x;
		Historical[T][21]=WP6y;
		Historical[T][22]=WP6Taken;
		Historical[T][23]=WP6Promotion;
		Historical[T][24]=WP7x;
		Historical[T][25]=WP7y;
		Historical[T][26]=WP7Taken;
		Historical[T][27]=WP7Promotion;
		Historical[T][28]=WP8x;
		Historical[T][29]=WP8y;
		Historical[T][30]=WP8Taken;
		Historical[T][31]=WP8Promotion;
		Historical[T][32]=WK1x;
		Historical[T][33]=WK1y;
		Historical[T][34]=WK1Taken;
		Historical[T][35]=WK2x;
		Historical[T][36]=WK2y;
		Historical[T][37]=WK2Taken;
		Historical[T][38]=WB1x;
		Historical[T][39]=WB1y;
		Historical[T][40]=WB1Taken;
		Historical[T][41]=WB2x;
		Historical[T][42]=WB2y;
		Historical[T][43]=WB2Taken;
		Historical[T][44]=WR1x;
		Historical[T][45]=WR1y;
		Historical[T][46]=WR1Taken;
		Historical[T][47]=WR1NeverMoved;
		Historical[T][48]=WR2x;
		Historical[T][49]=WR2y;
		Historical[T][50]=WR2Taken;
		Historical[T][51]=WR2NeverMoved;
		Historical[T][52]=WQx;
		Historical[T][53]=WQy;
		Historical[T][54]=WQTaken;
		Historical[T][55]=WKgx;
		Historical[T][56]=WKgy;
		Historical[T][57]=WKgTaken;
		Historical[T][58]=WKgNeverMoved;

		Historical[T][59]=BP1x;
		Historical[T][60]=BP1y;
		Historical[T][61]=BP1Taken;
		Historical[T][62]=BP1Promotion;
		Historical[T][63]=BP2x;
		Historical[T][64]=BP2y;
		Historical[T][65]=BP2Taken;
		Historical[T][66]=BP2Promotion;
		Historical[T][67]=BP3x;
		Historical[T][68]=BP3y;
		Historical[T][69]=BP3Taken;
		Historical[T][70]=BP3Promotion;
		Historical[T][71]=BP4x;
		Historical[T][72]=BP4y;
		Historical[T][73]=BP4Taken;
		Historical[T][74]=BP4Promotion;
		Historical[T][75]=BP5x;
		Historical[T][76]=BP5y;
		Historical[T][77]=BP5Taken;
		Historical[T][78]=BP5Promotion;
		Historical[T][79]=BP6x;
		Historical[T][80]=BP6y;
		Historical[T][81]=BP6Taken;
		Historical[T][82]=BP6Promotion;
		Historical[T][83]=BP7x;
		Historical[T][84]=BP7y;
		Historical[T][85]=BP7Taken;
		Historical[T][86]=BP7Promotion;
		Historical[T][87]=BP8x;
		Historical[T][88]=BP8y;
		Historical[T][89]=BP8Taken;
		Historical[T][90]=BP8Promotion;
		Historical[T][91]=BK1x;
		Historical[T][92]=BK1y;
		Historical[T][93]=BK1Taken;
		Historical[T][94]=BK2x;
		Historical[T][95]=BK2y;
		Historical[T][96]=BK2Taken;
		Historical[T][97]=BB1x;
		Historical[T][98]=BB1y;
		Historical[T][99]=BB1Taken;
		Historical[T][100]=BB2x;
		Historical[T][101]=BB2y;
		Historical[T][102]=BB2Taken;
		Historical[T][103]=BR1x;
		Historical[T][104]=BR1y;
		Historical[T][105]=BR1Taken;
		Historical[T][106]=BR1NeverMoved;
		Historical[T][107]=BR2x;
		Historical[T][108]=BR2y;
		Historical[T][109]=BR2Taken;
		Historical[T][110]=BR2NeverMoved;
		Historical[T][111]=BQx;
		Historical[T][112]=BQy;
		Historical[T][113]=BQTaken;
		Historical[T][114]=BKgx;
		Historical[T][115]=BKgy;
		Historical[T][116]=BKgTaken;
		Historical[T][117]=BKgNeverMoved;
		Historical[T][118]=Turn;

		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				HistoChess[T][i][j]=Chessboard[i][j];
				HistoPiece[T][i][j]=Pieceboard[i][j];
				}
			}
		for(int i=T+1; i<200; i++){
			for(int j=0; j<119; j++){
				Historical[i][j]=0;
				}
			}
		}
	public static void HistoRefresh(){
		WP1x=Historical[T][0];
		WP1y=Historical[T][1];
		WP1Taken=Historical[T][2];
		WP1Promotion=Historical[T][3];
		WP2x=Historical[T][4];
		WP2y=Historical[T][5];
		WP2Taken=Historical[T][6];
		WP2Promotion=Historical[T][7];
		WP3x=Historical[T][8];
		WP3y=Historical[T][9];
		WP3Taken=Historical[T][10];
		WP3Promotion=Historical[T][11];
		WP4x=Historical[T][12];
		WP4y=Historical[T][13];
		WP4Taken=Historical[T][14];
		WP4Promotion=Historical[T][15];
		WP5x=Historical[T][16];
		WP5y=Historical[T][17];
		WP5Taken=Historical[T][18];
		WP5Promotion=Historical[T][19];
		WP6x=Historical[T][20];
		WP6y=Historical[T][21];
		WP6Taken=Historical[T][22];
		WP6Promotion=Historical[T][23];
		WP7x=Historical[T][24];
		WP7y=Historical[T][25];
		WP7Taken=Historical[T][26];
		WP7Promotion=Historical[T][27];
		WP8x=Historical[T][28];
		WP8y=Historical[T][29];
		WP8Taken=Historical[T][30];
		WP8Promotion=Historical[T][31];
		WK1x=Historical[T][32];
		WK1y=Historical[T][33];
		WK1Taken=Historical[T][34];
		WK2x=Historical[T][35];
		WK2y=Historical[T][36];
		WK2Taken=Historical[T][37];
		WB1x=Historical[T][38];
		WB1y=Historical[T][39];
		WB1Taken=Historical[T][40];
		WB2x=Historical[T][41];
		WB2y=Historical[T][42];
		WB2Taken=Historical[T][43];
		WR1x=Historical[T][44];
		WR1y=Historical[T][45];
		WR1Taken=Historical[T][46];
		WR1NeverMoved=Historical[T][47];
		WR2x=Historical[T][48];
		WR2y=Historical[T][49];
		WR2Taken=Historical[T][50];
		WR2NeverMoved=Historical[T][51];
		WQx=Historical[T][52];
		WQy=Historical[T][53];
		WQTaken=Historical[T][54];
		WKgx=Historical[T][55];
		WKgy=Historical[T][56];
		WKgTaken=Historical[T][57];
		WKgNeverMoved=Historical[T][58];

		BP1x=Historical[T][59];
		BP1y=Historical[T][60];
		BP1Taken=Historical[T][61];
		BP1Promotion=Historical[T][62];
		BP2x=Historical[T][63];
		BP2y=Historical[T][64];
		BP2Taken=Historical[T][65];
		BP2Promotion=Historical[T][66];
		BP3x=Historical[T][67];
		BP3y=Historical[T][68];
		BP3Taken=Historical[T][69];
		BP3Promotion=Historical[T][70];
		BP4x=Historical[T][71];
		BP4y=Historical[T][72];
		BP4Taken=Historical[T][73];
		BP4Promotion=Historical[T][74];
		BP5x=Historical[T][75];
		BP5y=Historical[T][76];
		BP5Taken=Historical[T][77];
		BP5Promotion=Historical[T][78];
		BP6x=Historical[T][79];
		BP6y=Historical[T][80];
		BP6Taken=Historical[T][81];
		BP6Promotion=Historical[T][82];
		BP7x=Historical[T][83];
		BP7y=Historical[T][84];
		BP7Taken=Historical[T][85];
		BP7Promotion=Historical[T][86];
		BP8x=Historical[T][87];
		BP8y=Historical[T][88];
		BP8Taken=Historical[T][89];
		BP8Promotion=Historical[T][90];
		BK1x=Historical[T][91];
		BK1y=Historical[T][92];
		BK1Taken=Historical[T][93];
		BK2x=Historical[T][94];
		BK2y=Historical[T][95];
		BK2Taken=Historical[T][96];
		BB1x=Historical[T][97];
		BB1y=Historical[T][98];
		BB1Taken=Historical[T][99];
		BB2x=Historical[T][100];
		BB2y=Historical[T][101];
		BB2Taken=Historical[T][102];
		BR1x=Historical[T][103];
		BR1y=Historical[T][104];
		BR1Taken=Historical[T][105];
		BR1NeverMoved=Historical[T][106];
		BR2x=Historical[T][107];
		BR2y=Historical[T][108];
		BR2Taken=Historical[T][109];
		BR2NeverMoved=Historical[T][110];
		BQx=Historical[T][111];
		BQy=Historical[T][112];
		BQTaken=Historical[T][113];
		BKgx=Historical[T][114];
		BKgy=Historical[T][115];
		BKgTaken=Historical[T][116];
		BKgNeverMoved=Historical[T][117];
		Turn=Historical[T][118];

		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Chessboard[i][j]=HistoChess[T][i][j];
				Pieceboard[i][j]=HistoPiece[T][i][j];
				}
			}
		}		
	}
//************************************************************************************************
//************************************************************************************************
//************************************************************************************************
//************************************************************************************************
//************************************************************************************************
//************************************************************************************************
//************************************************************************************************
//************************************************************************************************
class SourisSelection implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==2){ 
			System.out.println("BLACKBOARD");
			Pan.Afficherboard(Pan.Blackboard); 
			System.out.println();
			System.out.println("WHITEBOARD");
			Pan.Afficherboard(Pan.Whiteboard);
			System.out.println();
			System.out.println("PIECEBOARD");
			Pan.Afficherboard(Pan.Pieceboard); 
			System.out.println("CHESSBOARD");
			Pan.Afficherboard(Pan.Chessboard); 
			}
		if (c==3){
			Pan.Selected=0;
			Pan.WP1Selected=0;
			Pan.WP2Selected=0;
			Pan.WP3Selected=0;
			Pan.WP4Selected=0;
			Pan.WP5Selected=0;
			Pan.WP6Selected=0;
			Pan.WP7Selected=0;
			Pan.WP8Selected=0;
			Pan.WK1Selected=0;
			Pan.WK2Selected=0;
			Pan.WB1Selected=0;
			Pan.WB2Selected=0;
			Pan.WR1Selected=0;
			Pan.WR2Selected=0;
			Pan.WQSelected=0;
			Pan.WKgSelected=0;
			Pan.BP1Selected=0;
			Pan.BP2Selected=0;
			Pan.BP3Selected=0;
			Pan.BP4Selected=0;
			Pan.BP5Selected=0;
			Pan.BP6Selected=0;
			Pan.BP7Selected=0;
			Pan.BP8Selected=0;
			Pan.BK1Selected=0;
			Pan.BK2Selected=0;
			Pan.BB1Selected=0;
			Pan.BB2Selected=0;
			Pan.BR1Selected=0;
			Pan.BR2Selected=0;
			Pan.BQSelected=0;
			Pan.BKgSelected=0;
			}
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (25+i*100<X && X<125+i*100 && 825-j*100>Y && Y>725-j*100 && c==1){
					Pan.SelectCaseX=25+i*100;
					Pan.SelectCaseY=725-j*100;
					Pan.Selected=1;
					Pan.CaseX=i;
					Pan.CaseY=j;
					String Piece="Empty";
					if (Pan.Chessboard[i][j]==0) Piece="Empty";
					else if (Pan.Chessboard[i][j]==1) Piece="White Pawn";
					else if (Pan.Chessboard[i][j]==2) Piece="White Knight";
					else if (Pan.Chessboard[i][j]==3) Piece="White Bishop";
					else if (Pan.Chessboard[i][j]==4) Piece="White Rook";
					else if (Pan.Chessboard[i][j]==5) Piece="White Queen";
					else if (Pan.Chessboard[i][j]==6) Piece="White King";
					else if (Pan.Chessboard[i][j]==7) Piece="Black Pawn";
					else if (Pan.Chessboard[i][j]==8) Piece="Black Knight";
					else if (Pan.Chessboard[i][j]==9) Piece="Black Bishop";
					else if (Pan.Chessboard[i][j]==10) Piece="Black Rook";
					else if (Pan.Chessboard[i][j]==11) Piece="Black Queen";
					else if (Pan.Chessboard[i][j]==12) Piece="Black King";
					System.out.println(i+"\t"+j+"\t"+Pan.Chessboard[i][j]+":"+Piece+"\t"+Pan.Pieceboard[i][j]);
					break;
					}
				}
			}
if (Pan.PromotionChoice==0){
//***White
//***Pawn
		if (c==1 && Pan.WP1Selected==1) Pan.PlayWhitePawn(1, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WP2Selected==1) Pan.PlayWhitePawn(2, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WP3Selected==1) Pan.PlayWhitePawn(3, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WP4Selected==1) Pan.PlayWhitePawn(4, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WP5Selected==1) Pan.PlayWhitePawn(5, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WP6Selected==1) Pan.PlayWhitePawn(6, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WP7Selected==1) Pan.PlayWhitePawn(7, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WP8Selected==1) Pan.PlayWhitePawn(8, Pan.CaseX, Pan.CaseY);
//***Knight
		else if (c==1 && Pan.WK1Selected==1) Pan.PlayWhiteKnight(1, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WK2Selected==1) Pan.PlayWhiteKnight(2, Pan.CaseX, Pan.CaseY);
//***Rook
		else if (c==1 && Pan.WR1Selected==1) Pan.PlayWhiteRook(1, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WR2Selected==1) Pan.PlayWhiteRook(2, Pan.CaseX, Pan.CaseY);
//***Bishop
		else if (c==1 && Pan.WB1Selected==1) Pan.PlayWhiteBishop(1, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.WB2Selected==1) Pan.PlayWhiteBishop(2, Pan.CaseX, Pan.CaseY);
//***Queen
		else if (c==1 && Pan.WQSelected==1) Pan.PlayWhiteQueen(Pan.CaseX, Pan.CaseY);
//***King
		else if (c==1 && Pan.WKgSelected==1) Pan.PlayWhiteKing(Pan.CaseX, Pan.CaseY);
		
//****Black
//***Pawn
		if (c==1 && Pan.BP1Selected==1) Pan.PlayBlackPawn(1, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BP2Selected==1) Pan.PlayBlackPawn(2, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BP3Selected==1) Pan.PlayBlackPawn(3, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BP4Selected==1) Pan.PlayBlackPawn(4, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BP5Selected==1) Pan.PlayBlackPawn(5, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BP6Selected==1) Pan.PlayBlackPawn(6, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BP7Selected==1) Pan.PlayBlackPawn(7, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BP8Selected==1) Pan.PlayBlackPawn(8, Pan.CaseX, Pan.CaseY);
//***Knight
		else if (c==1 && Pan.BK1Selected==1) Pan.PlayBlackKnight(1, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BK2Selected==1) Pan.PlayBlackKnight(2, Pan.CaseX, Pan.CaseY);
//***Rook
		else if (c==1 && Pan.BR1Selected==1) Pan.PlayBlackRook(1, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BR2Selected==1) Pan.PlayBlackRook(2, Pan.CaseX, Pan.CaseY);
//***Bishop
		else if (c==1 && Pan.BB1Selected==1) Pan.PlayBlackBishop(1, Pan.CaseX, Pan.CaseY);
		else if (c==1 && Pan.BB2Selected==1) Pan.PlayBlackBishop(2, Pan.CaseX, Pan.CaseY);
//***Queen
		else if (c==1 && Pan.BQSelected==1) Pan.PlayBlackQueen(Pan.CaseX, Pan.CaseY);
//***King
		else if (c==1 && Pan.BKgSelected==1) Pan.PlayBlackKing(Pan.CaseX, Pan.CaseY);
		}
//****Fin Turn
			
		if (c==1 && Pan.PromotionChoice==1 && Pan.CaseY==4 && (Pan.CaseX==2 || Pan.CaseX==3 || Pan.CaseX==4 || Pan.CaseX==5)){
			if (Pan.CaseY==4){
				if (Pan.CaseX==2) Pan.Promotion=3;
				else if (Pan.CaseX==3){
					if (Pan.WhiteQueenNumber<2 && Pan.Turn==2){
						Pan.Promotion=5;
						Pan.WhiteQueenNumber+=1;
						}
					else if (Pan.BlackQueenNumber<2 && Pan.Turn==1){
						Pan.Promotion=5;
						Pan.BlackQueenNumber+=1;
						}
					}
				else if (Pan.CaseX==4){
					if (Pan.WhiteRookNumber<3 && Pan.Turn==2){
						Pan.Promotion=4;
						Pan.WhiteRookNumber+=1;
						}
					else if (Pan.BlackRookNumber<3 && Pan.Turn==1){
						Pan.Promotion=4;
						Pan.BlackRookNumber+=1;
						}
					}
				else if (Pan.CaseX==5) Pan.Promotion=2;
				}
			Pan.PromotionChoice=0;
			}
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
class Touche implements KeyListener{
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		char K = event.getKeyChar();
		if (K=='w') Pan.WhiteDangerOn=1;
		if (K=='b') Pan.BlackDangerOn=1;
		}
	public void keyReleased(KeyEvent event){
		int k = event.getKeyCode();
		char K = event.getKeyChar();
		if (K=='t') Pan.Turn+=1;
		if (k==0x1B) Pan.Reset();
		if (k==0x25 && Pan.T>0){
			Pan.T-=1;
			Pan.HistoRefresh();
			}
		if (k==0x27 && Pan.Historical[Pan.T+1][0]!=0){
			Pan.T+=1;
			Pan.HistoRefresh();
			}
		if (K=='r'){
			//Pan.HistoRecord();
			if (Pan.WP1Selected==1) Pan.Pieceboard[Pan.Pij(11)[0]][Pan.Pij(11)[1]]=Pan.Chessboard[Pan.Pij(11)[0]][Pan.Pij(11)[1]]=0;
			else if (Pan.WP2Selected==1) Pan.Pieceboard[Pan.Pij(12)[0]][Pan.Pij(12)[1]]=Pan.Chessboard[Pan.Pij(12)[0]][Pan.Pij(12)[1]]=0;
			else if (Pan.WP3Selected==1) Pan.Pieceboard[Pan.Pij(13)[0]][Pan.Pij(13)[1]]=Pan.Chessboard[Pan.Pij(13)[0]][Pan.Pij(13)[1]]=0;
			else if (Pan.WP4Selected==1) Pan.Pieceboard[Pan.Pij(14)[0]][Pan.Pij(14)[1]]=Pan.Chessboard[Pan.Pij(14)[0]][Pan.Pij(14)[1]]=0;
			else if (Pan.WP5Selected==1) Pan.Pieceboard[Pan.Pij(15)[0]][Pan.Pij(15)[1]]=Pan.Chessboard[Pan.Pij(15)[0]][Pan.Pij(15)[1]]=0;
			else if (Pan.WP6Selected==1) Pan.Pieceboard[Pan.Pij(16)[0]][Pan.Pij(16)[1]]=Pan.Chessboard[Pan.Pij(16)[0]][Pan.Pij(16)[1]]=0;
			else if (Pan.WP7Selected==1) Pan.Pieceboard[Pan.Pij(17)[0]][Pan.Pij(17)[1]]=Pan.Chessboard[Pan.Pij(17)[0]][Pan.Pij(17)[1]]=0;
			else if (Pan.WP8Selected==1) Pan.Pieceboard[Pan.Pij(18)[0]][Pan.Pij(18)[1]]=Pan.Chessboard[Pan.Pij(18)[0]][Pan.Pij(18)[1]]=0;
			else if (Pan.WK1Selected==1) Pan.Pieceboard[Pan.Pij(21)[0]][Pan.Pij(21)[1]]=Pan.Chessboard[Pan.Pij(21)[0]][Pan.Pij(21)[1]]=0;
			else if (Pan.WK2Selected==1) Pan.Pieceboard[Pan.Pij(22)[0]][Pan.Pij(22)[1]]=Pan.Chessboard[Pan.Pij(22)[0]][Pan.Pij(22)[1]]=0;
			else if (Pan.WB1Selected==1) Pan.Pieceboard[Pan.Pij(31)[0]][Pan.Pij(31)[1]]=Pan.Chessboard[Pan.Pij(31)[0]][Pan.Pij(31)[1]]=0;
			else if (Pan.WB2Selected==1) Pan.Pieceboard[Pan.Pij(32)[0]][Pan.Pij(32)[1]]=Pan.Chessboard[Pan.Pij(32)[0]][Pan.Pij(32)[1]]=0;
			else if (Pan.WR1Selected==1) Pan.Pieceboard[Pan.Pij(41)[0]][Pan.Pij(41)[1]]=Pan.Chessboard[Pan.Pij(41)[0]][Pan.Pij(41)[1]]=0;
			else if (Pan.WR2Selected==1) Pan.Pieceboard[Pan.Pij(42)[0]][Pan.Pij(42)[1]]=Pan.Chessboard[Pan.Pij(42)[0]][Pan.Pij(42)[1]]=0;
			else if (Pan.WQSelected==1) Pan.Pieceboard[Pan.Pij(5)[0]][Pan.Pij(5)[1]]=Pan.Chessboard[Pan.Pij(5)[0]][Pan.Pij(5)[1]]=0;
			else if (Pan.WKgSelected==1) Pan.Pieceboard[Pan.Pij(6)[0]][Pan.Pij(6)[1]]=Pan.Chessboard[Pan.Pij(6)[0]][Pan.Pij(6)[1]]=0;
			else if (Pan.BP1Selected==1) Pan.Pieceboard[Pan.Pij(71)[0]][Pan.Pij(71)[1]]=Pan.Chessboard[Pan.Pij(71)[0]][Pan.Pij(71)[1]]=0;
			else if (Pan.BP2Selected==1) Pan.Pieceboard[Pan.Pij(72)[0]][Pan.Pij(72)[1]]=Pan.Chessboard[Pan.Pij(72)[0]][Pan.Pij(72)[1]]=0;
			else if (Pan.BP3Selected==1) Pan.Pieceboard[Pan.Pij(73)[0]][Pan.Pij(73)[1]]=Pan.Chessboard[Pan.Pij(73)[0]][Pan.Pij(73)[1]]=0;
			else if (Pan.BP4Selected==1) Pan.Pieceboard[Pan.Pij(74)[0]][Pan.Pij(74)[1]]=Pan.Chessboard[Pan.Pij(74)[0]][Pan.Pij(74)[1]]=0;
			else if (Pan.BP5Selected==1) Pan.Pieceboard[Pan.Pij(75)[0]][Pan.Pij(75)[1]]=Pan.Chessboard[Pan.Pij(75)[0]][Pan.Pij(75)[1]]=0;
			else if (Pan.BP6Selected==1) Pan.Pieceboard[Pan.Pij(76)[0]][Pan.Pij(76)[1]]=Pan.Chessboard[Pan.Pij(76)[0]][Pan.Pij(76)[1]]=0;
			else if (Pan.BP7Selected==1) Pan.Pieceboard[Pan.Pij(77)[0]][Pan.Pij(77)[1]]=Pan.Chessboard[Pan.Pij(77)[0]][Pan.Pij(77)[1]]=0;
			else if (Pan.BP8Selected==1) Pan.Pieceboard[Pan.Pij(78)[0]][Pan.Pij(78)[1]]=Pan.Chessboard[Pan.Pij(78)[0]][Pan.Pij(78)[1]]=0;
			else if (Pan.BK1Selected==1) Pan.Pieceboard[Pan.Pij(81)[0]][Pan.Pij(81)[1]]=Pan.Chessboard[Pan.Pij(81)[0]][Pan.Pij(81)[1]]=0;
			else if (Pan.BK2Selected==1) Pan.Pieceboard[Pan.Pij(82)[0]][Pan.Pij(82)[1]]=Pan.Chessboard[Pan.Pij(82)[0]][Pan.Pij(82)[1]]=0;
			else if (Pan.BB1Selected==1) Pan.Pieceboard[Pan.Pij(91)[0]][Pan.Pij(91)[1]]=Pan.Chessboard[Pan.Pij(91)[0]][Pan.Pij(91)[1]]=0;
			else if (Pan.BB2Selected==1) Pan.Pieceboard[Pan.Pij(92)[0]][Pan.Pij(92)[1]]=Pan.Chessboard[Pan.Pij(92)[0]][Pan.Pij(92)[1]]=0;
			else if (Pan.BR1Selected==1) Pan.Pieceboard[Pan.Pij(101)[0]][Pan.Pij(101)[1]]=Pan.Chessboard[Pan.Pij(101)[0]][Pan.Pij(101)[1]]=0;
			else if (Pan.BR2Selected==1) Pan.Pieceboard[Pan.Pij(102)[0]][Pan.Pij(102)[1]]=Pan.Chessboard[Pan.Pij(102)[0]][Pan.Pij(102)[1]]=0;
			else if (Pan.BQSelected==1) Pan.Pieceboard[Pan.Pij(111)[0]][Pan.Pij(111)[1]]=Pan.Chessboard[Pan.Pij(111)[0]][Pan.Pij(111)[1]]=0;
			else if (Pan.BKgSelected==1) Pan.Pieceboard[Pan.Pij(121)[0]][Pan.Pij(121)[1]]=Pan.Chessboard[Pan.Pij(121)[0]][Pan.Pij(121)[1]]=0;
			Pan.PieceRefresh();
			}
		if (K=='s'){
			for(int i=0; i<200; i++){
				for(int j=0; j<119; j++){
					Pan.Historical[i][j]=0;
					}
				for(int l=0; l<8; l++){
					for(int m=0; m<8; m++){
						Pan.HistoChess[i][l][m]=Pan.HistoPiece[i][l][m]=0;
						}
					}
				}
			Pan.T=0;
			Pan.HistoRecord();
			}
		if (K=='w') Pan.WhiteDangerOn=0;
		if (K=='b') Pan.BlackDangerOn=0;
		}
	}
public class Echecs{
	public static void main(String[] args){
		Fen fen = new Fen("Fenetre", 866, 890);//850
		Pan pan = new Pan(850, 850);
		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				pan.Chessboard[i][j]=0;
				}
			}
		pan.Chessboard[0][0] = 4;
		pan.Chessboard[0][1] = 1;
		pan.Chessboard[1][0] = 2;
		pan.Chessboard[1][1] = 1;
		pan.Chessboard[2][0] = 3;
		pan.Chessboard[2][1] = 1;
		pan.Chessboard[3][0] = 5;
		pan.Chessboard[3][1] = 1;
		pan.Chessboard[4][0] = 6;
		pan.Chessboard[4][1] = 1;
		pan.Chessboard[5][0] = 3;
		pan.Chessboard[5][1] = 1;
		pan.Chessboard[6][0] = 2;
		pan.Chessboard[6][1] = 1;
		pan.Chessboard[7][0] = 4;
		pan.Chessboard[7][1] = 1;

		pan.Pieceboard[0][0] = 41;
		pan.Pieceboard[0][1] = 11;
		pan.Pieceboard[1][0] = 21;
		pan.Pieceboard[1][1] = 12;
		pan.Pieceboard[2][0] = 31;
		pan.Pieceboard[2][1] = 13;
		pan.Pieceboard[3][0] = 5;
		pan.Pieceboard[3][1] = 14;
		pan.Pieceboard[4][0] = 6;
		pan.Pieceboard[4][1] = 15;
		pan.Pieceboard[5][0] = 32;
		pan.Pieceboard[5][1] = 16;
		pan.Pieceboard[6][0] = 22;
		pan.Pieceboard[6][1] = 17;
		pan.Pieceboard[7][0] = 42;
		pan.Pieceboard[7][1] = 18;
// Empty = 0
/*White
*Pawn = 1
*Knight = 2
*Bishop = 3
*Rook = 4
*Queen = 5
*King = 6
*/
/*Black
*Pawn = 7
*Knight = 8
*Bishop = 9
*Rook = 10
*Queen = 11
*King = 12
*/
		pan.Chessboard[0][6] = 7;
		pan.Chessboard[0][7] = 10;
		pan.Chessboard[1][6] = 7;
		pan.Chessboard[1][7] = 8;
		pan.Chessboard[2][6] = 7;
		pan.Chessboard[2][7] = 9;
		pan.Chessboard[3][6] = 7;
		pan.Chessboard[3][7] = 11;
		pan.Chessboard[4][6] = 7;
		pan.Chessboard[4][7] = 12;
		pan.Chessboard[5][6] = 7;
		pan.Chessboard[5][7] = 9;
		pan.Chessboard[6][6] = 7;
		pan.Chessboard[6][7] = 8;
		pan.Chessboard[7][6] = 7;
		pan.Chessboard[7][7] = 10;

		pan.Pieceboard[0][6] = 71;
		pan.Pieceboard[0][7] = 101;
		pan.Pieceboard[1][6] = 72;
		pan.Pieceboard[1][7] = 81;
		pan.Pieceboard[2][6] = 73;
		pan.Pieceboard[2][7] = 91;
		pan.Pieceboard[3][6] = 74;
		pan.Pieceboard[3][7] = 111;
		pan.Pieceboard[4][6] = 75;
		pan.Pieceboard[4][7] = 121;
		pan.Pieceboard[5][6] = 76;
		pan.Pieceboard[5][7] = 92;
		pan.Pieceboard[6][6] = 77;
		pan.Pieceboard[6][7] = 82;
		pan.Pieceboard[7][6] = 78;
		pan.Pieceboard[7][7] = 102;
		fen.setContentPane(pan);
		pan.HistoRecord();
		while (true){
			if (pan.gameplay==0){
				pan.OrdiSide=0;
				pan.Solo.setVisible(true);
				pan.Duo.setVisible(true);
				pan.PPA.setVisible(true);
				}
			else if (pan.gameplay!=0){
				pan.Solo.setVisible(false);
				pan.Duo.setVisible(false);
				pan.PPA.setVisible(false);
				}
			if (pan.gameplay==1) pan.OrdiSide=2;

			pan.repaint();
			}
		//while (true) pan.repaint();
		}
}