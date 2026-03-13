import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Fen extends JFrame{
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		}
	}
//*************** INVERSER i et j POUR AFFICHER CORRECTEMENT
class Pan extends JPanel{
	static int [] P1x = new int [12];
	static int [] P1y = new int [12];
	static int [] P2x = new int [12];
	static int [] P2y = new int [12];

	static int [] P1Selected = new int [12];
	static int [] P2Selected = new int [12];
	static int [] P1Promotion = new int [12];
	static int [] P2Promotion = new int [12];
	static int [] P1Taken = new int [12];
	static int [] P2Taken = new int [12];
	static int [] P1SelectedChain = new int [12];
	static int [] P2SelectedChain = new int [12];
	

	static int CaseX=0;
	static int CaseY=0;
	static int ChainCaseX=-1;
	static int ChainCaseY=-1;	
	static int SelectedCaseX=0;
	static int SelectedCaseY=0;
	static int DoubleSelect=0;

	static int Turn=1;
	static int gameplay=1;
	static int [][] Pieceboard = new int [8][8];
	static int [][] Chain = new int [8][5];
	static int ChainS = 0;

	static int [][] Moveboard = new int [8][8];
	static int [][] Takenboard = new int [8][8];
	static int [][] PieceboardBis = new int [8][8];
	static int [][] MoveboardBis = new int [8][8];
	static int [][] TakenboardBis = new int [8][8];
	static int [][][] Histoboard = new int [200][8][8];
	static int [][] Historical = new int [200][100];
	static int T=0;
	static int TBis=0;
	static int info=0;
	static JButton Solo = new JButton("Solo");
	static JButton Duo = new JButton("Duo");
	JButton gris = new JButton();
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

		Touche touche = new Touche();
		gris.setPreferredSize(new Dimension(0,0));
		gris.addKeyListener(touche);
		this.add(gris);
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
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		for(int i=0; i<3; i++) g.drawRect(22+i, 22+i, 805-2*i, 805-2*i);
		g.setColor(Color.gray);
		for(int i=0; i<8; i++){
			for(int j=0; j<4; j++){
				if (i%2==0){
					g.fillRect(125+j*200, 25+i*100, 100, 100);
					}
				else{
					g.fillRect(25+j*200, 25+i*100, 100, 100);
					}
		 		}
			}

		
		g.setColor(Color.blue);
		if (SelectedCaseX!=0 && SelectedCaseY!=0) g.fillRect(SelectedCaseX, SelectedCaseY, 100, 100);

//***Move
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Moveboard[i][j]=Takenboard[i][j]=0;
				}

			}
		for(int i=0; i<8; i++){
			//if (Chain[i][2]==4) Moveboard[Chain[i][0]][Chain[i][1]]=Chain[i][2];
			}
		for(int k=0; k<12; k++){
//*****Move P1
			if (Turn==1 && (P1Selected[k]==1 || P1SelectedChain[k]==1)){
				int X=GetP1CaseX(k);
				int Y=GetP1CaseY(k);
				if (ChainCaseX!=-1){
					//X=ChainCaseX;
					//Y=ChainCaseY;
					}
				if (Chain[0][2]!=0){
					X=GetLastChainX();
					Y=GetLastChainY();
					//X=Chain[ChainS][0];
					//Y=Chain[ChainS][1];
					}
				//if (info==1) System.out.println("X="+X+"\t"+"Y="+Y);
				if (info==1 && Chain[0][2]!=0) afficherChain();
				if (P1Promotion[k]==0){
					if (X+1!=8 && Y+1<8){
						if (Pieceboard[X+1][Y+1]==0 && Chain[0][2]==0) Moveboard[X+1][Y+1]=1;
						else if (Pieceboard[X+1][Y+1]==2){
							if (Y+2<8 && X+2<8){
								if (info==1) System.out.println("X="+X+"\t"+"Y="+Y);
								if (Pieceboard[X+2][Y+2]==0 && IsChainPossibleP1(X+2,Y+2)==false){
									Moveboard[X+2][Y+2]=2;
									Takenboard[X+1][Y+1]=1;
									}
								else if (Pieceboard[X+2][Y+2]==0 && IsChainPossibleP1(X+2,Y+2)){
									Moveboard[X+2][Y+2]=3;
									//Takenboard[X+1][Y+1]=1;
									//PutInChain(X+2, Y+2, 3,X+1,Y+1);
									Chain[ChainS][0]=X+2;
									Chain[ChainS][1]=Y+2;
									//Chain[ChainS][2]=3;
									Chain[ChainS][3]=X+1;
									Chain[ChainS][4]=Y+1;
									}								
								}
							}
						if (Y-1>-1){	
							if (Pieceboard[X+1][Y-1]==2){
								if (Y-2>-1 && X+2<8){
									if (Pieceboard[X+2][Y-2]==0 && IsChainPossibleP1(X+2,Y-2)==false){
										Moveboard[X+2][Y-2]=2;
										Takenboard[X+1][Y-1]=1;
										}
									else if (Pieceboard[X+2][Y-2]==0 && IsChainPossibleP1(X+2,Y-2)){
										Moveboard[X+2][Y-2]=3;
										Chain[ChainS][0]=X+2;
										Chain[ChainS][1]=Y-2;
										Chain[ChainS][3]=X+1;
										Chain[ChainS][4]=Y-1;
										}
									}
								}
							}
						}
					if (X-1!=-1 && Y+1<8){
						if (Pieceboard[X-1][Y+1]==0 && Chain[0][2]==0) Moveboard[X-1][Y+1]=1;
						else if (Pieceboard[X-1][Y+1]==2){
							if (Y+2<8 && X-2>-1){
								if (Pieceboard[X-2][Y+2]==0 && IsChainPossibleP1(X-2,Y+2)==false){
									Moveboard[X-2][Y+2]=2;
									Takenboard[X-1][Y+1]=1;
									}
								else if (Pieceboard[X-2][Y+2]==0 && IsChainPossibleP1(X-2,Y+2)){
									Moveboard[X-2][Y+2]=3;
									Chain[ChainS][0]=X-2;
									Chain[ChainS][1]=Y+2;
									Chain[ChainS][3]=X-1;
									Chain[ChainS][4]=Y+1;
									}
								}
							}
						if (Y-1>-1){	
							if (Pieceboard[X-1][Y-1]==2){
								if (Y-2>-1 && X-2>-1){
									if (Pieceboard[X-2][Y-2]==0 && IsChainPossibleP1(X-2,Y-2)==false){
										Moveboard[X-2][Y-2]=2;
										Takenboard[X-1][Y-1]=1;
										}
									else if (Pieceboard[X-2][Y-2]==0 && IsChainPossibleP1(X-2,Y-2)){
										Moveboard[X-2][Y-2]=3;
										Chain[ChainS][0]=X-2;
										Chain[ChainS][1]=Y-2;
										Chain[ChainS][3]=X-1;
										Chain[ChainS][4]=Y-1;
										}
									}
								}
							}
						}

					}
				else if (P1Promotion[k]==1){
					for(int i=1; X-i>-1 && Y-i>-1; i++){
						if (Pieceboard[X-i][Y-i]==0) Moveboard[X-i][Y-i]=1;
						else if (Pieceboard[X-i][Y-i]==2){
							if (X-i-1>-1 && Y-i-1>-1){
								if (Pieceboard[GetP1CaseX(k)-i-1][GetP1CaseY(k)-i-1]==0 && IsChainPossibleP1Promoted(X-i-1,Y-i-1)==false){
									Moveboard[GetP1CaseX(k)-i-1][GetP1CaseY(k)-i-1]=2;
									Takenboard[GetP1CaseX(k)-i][GetP1CaseY(k)-i]=1;
									}
								else if (Pieceboard[X-i-1][Y-i-1]==0 && IsChainPossibleP1Promoted(X-i-1,Y-i-1)){
									Moveboard[X-i-1][Y-i-1]=3;
									Chain[ChainS][0]=X-i-1;
									Chain[ChainS][1]=Y-i-1;
									Chain[ChainS][3]=X-i;
									Chain[ChainS][4]=Y-i;
									}
								}
							break;
							}
						else break;
						}
					for(int i=1; GetP1CaseX(k)+i<8 && GetP1CaseY(k)+i<8; i++){
						if (Pieceboard[GetP1CaseX(k)+i][GetP1CaseY(k)+i]==0) Moveboard[GetP1CaseX(k)+i][GetP1CaseY(k)+i]=1;
						else if (Pieceboard[GetP1CaseX(k)+i][GetP1CaseY(k)+i]==2){
							if (GetP1CaseX(k)+i+1<8 && GetP1CaseY(k)+i+1<8){
								if (Pieceboard[GetP1CaseX(k)+i+1][GetP1CaseY(k)+i+1]==0 && IsChainPossibleP1Promoted(X+i+1,Y+i+1)==false){
									Moveboard[GetP1CaseX(k)+i+1][GetP1CaseY(k)+i+1]=2;
									Takenboard[GetP1CaseX(k)+i][GetP1CaseY(k)+i]=1;
									}
								else if (Pieceboard[X+i+1][Y+i+1]==0 && IsChainPossibleP1Promoted(X+i+1,Y+i+1)){
									Moveboard[X+i+1][Y+i+1]=3;
									Chain[ChainS][0]=X+i+1;
									Chain[ChainS][1]=Y+i+1;
									Chain[ChainS][3]=X+i;
									Chain[ChainS][4]=Y+i;
									}
								}
							break;
							}
						else break;
						}
					for(int i=1; GetP1CaseX(k)-i>-1 && GetP1CaseY(k)+i<8; i++){
						if (Pieceboard[GetP1CaseX(k)-i][GetP1CaseY(k)+i]==0) Moveboard[GetP1CaseX(k)-i][GetP1CaseY(k)+i]=1;
						else if (Pieceboard[GetP1CaseX(k)-i][GetP1CaseY(k)+i]==2){
							if (GetP1CaseX(k)-i-1>-1 && GetP1CaseY(k)+i+1<8){
								if (Pieceboard[GetP1CaseX(k)-i-1][GetP1CaseY(k)+i+1]==0 && IsChainPossibleP1Promoted(X-i-1,Y+i+1)==false){
									Moveboard[GetP1CaseX(k)-i-1][GetP1CaseY(k)+i+1]=2;
									Takenboard[GetP1CaseX(k)-i][GetP1CaseY(k)+i]=1;
									}
								else if (Pieceboard[X-i-1][Y+i+1]==0 && IsChainPossibleP1Promoted(X-i-1,Y+i+1)){
									Moveboard[X-i-1][Y+i+1]=3;
									Chain[ChainS][0]=X-i-1;
									Chain[ChainS][1]=Y+i+1;
									Chain[ChainS][3]=X-i;
									Chain[ChainS][4]=Y+i;
									}
								}
							break;
							}
						else break;
						}
					for(int i=1; GetP1CaseX(k)+i<8 && GetP1CaseY(k)-i>-1; i++){
						if (Pieceboard[GetP1CaseX(k)+i][GetP1CaseY(k)-i]==0) Moveboard[GetP1CaseX(k)+i][GetP1CaseY(k)-i]=1;
						else if (Pieceboard[GetP1CaseX(k)+i][GetP1CaseY(k)-i]==2){
							if (GetP1CaseX(k)+i+1<8 && GetP1CaseY(k)-i-1>-1){
								if (Pieceboard[GetP1CaseX(k)+i+1][GetP1CaseY(k)-i-1]==0 && IsChainPossibleP1Promoted(X+i+1,Y-i-1)==false){
									Moveboard[GetP1CaseX(k)+i+1][GetP1CaseY(k)-i-1]=2;
									Takenboard[GetP1CaseX(k)+i][GetP1CaseY(k)-i]=1;
									}
								else if (Pieceboard[X+i+1][Y-i-1]==0 && IsChainPossibleP1Promoted(X+i+1,Y-i-1)){
									Moveboard[X+i+1][Y-i-1]=3;
									Chain[ChainS][0]=X+i+1;
									Chain[ChainS][1]=Y-i-1;
									Chain[ChainS][3]=X+i;
									Chain[ChainS][4]=Y-i;
									}
								}
							break;
							}
						else break;
						}	
					}
				//if (ChainCaseX!=-1){
					
				if (Moveboard[CaseX][CaseY]==1){
					Pieceboard[GetP1CaseX(k)][GetP1CaseY(k)]=0;
					P1x[k]=25+CaseX*100;
					P1y[k]=25+CaseY*100;
					P1Selected[k]=0;
					//Pieceboard[CaseX][CaseY]=1;
					Turn=2;
					//if (T>0) SoufP1();
					PieceRefresh();
					T+=1;
					HistoRecord();
					HistoResetNext();
					SoufP1();

					}

				else if (Moveboard[CaseX][CaseY]==2 || Moveboard[CaseX][CaseY]==4){
					int S=0;
					for(int i=0; i<8; i++){
						if (Chain[i][0]==CaseX && Chain[i][1]==CaseY) S=i;
						}
					if (S!=0) for(int i=0; i<S; i++) Takenboard[Chain[i][3]][Chain[i][4]]=1;
					else for(int i=0; i<8; i++) Takenboard[Chain[i][3]][Chain[i][4]]=1;

					TakeAll();
					Pieceboard[GetP1CaseX(k)][GetP1CaseY(k)]=0;
					P1x[k]=25+CaseX*100;
					P1y[k]=25+CaseY*100;
					P1Selected[k]=P1SelectedChain[k]=0;
					//Pieceboard[CaseX][CaseY]=1;
					Turn=2;
					DoubleSelect=0;
					PieceRefresh();
					T+=1;
					HistoRecord();
					HistoResetNext();
					SoufP1();		
					}
				else if (Moveboard[CaseX][CaseY]==3){
				//else if (Chain[CaseX][CaseY]==3){
					P1SelectedChain[k]=1;
					//Moveboard[CaseX][CaseY]=4;
					//Chain[0][0]=CaseX;
					//Chain[0][1]=CaseY;
					//ChainCaseX=CaseX;
					//ChainCaseY=CaseY;

					Chain[ChainS][2]=4;

					ChainS+=1;
					System.out.println("bon");
					}
				}
//****Move P2
			else if (Turn==2 && P2Selected[k]==1){
				int X=GetP2CaseX(k);
				int Y=GetP2CaseY(k);
				if (P2Promotion[k]==0){
					if (X+1!=8 && Y-1>-1){
						if (Pieceboard[X+1][Y-1]==0) Moveboard[X+1][Y-1]=1;
						else if (Pieceboard[X+1][Y-1]==1){
							if (Y-2>-1 && X+2<8){
								if (Pieceboard[X+2][Y-2]==0){
									Moveboard[X+2][Y-2]=2;
									Takenboard[X+1][Y-1]=1;
									}
								}
							}
						if (Y+1<8){
							if (Pieceboard[X+1][Y+1]==1){
								if (Y+2<8 && X+2<8){
									if (Pieceboard[X+2][Y+2]==0){
										Moveboard[X+2][Y+2]=2;
										Takenboard[X+1][Y+1]=1;
										}
									}
								}
							}							
						}
					if (X-1!=-1 && Y-1>-1){
						if (Pieceboard[X-1][Y-1]==0) Moveboard[X-1][Y-1]=1;
						else if (Pieceboard[X-1][Y-1]==1){
							if (Y-2>-1 && X-2>-1){
								if (Pieceboard[X-2][Y-2]==0){
									Moveboard[X-2][Y-2]=2;
									Takenboard[X-1][Y-1]=1;
									}
								}
							}
						if (Y+1<8){
							if (Pieceboard[X-1][Y+1]==1){
								if (Y+2<8 && X-2>-1){
									if (Pieceboard[X-2][Y+2]==0){
										Moveboard[X-2][Y+2]=2;
										Takenboard[X-1][Y+1]=1;
										}
									}
								}
							}
						}

					}
				else if (P2Promotion[k]==1){
					for(int i=1; GetP2CaseX(k)-i>-1 && GetP2CaseY(k)-i>-1; i++){
						if (Pieceboard[GetP2CaseX(k)-i][GetP2CaseY(k)-i]==0) Moveboard[GetP2CaseX(k)-i][GetP2CaseY(k)-i]=1;
						else if (Pieceboard[GetP2CaseX(k)-i][GetP2CaseY(k)-i]==1){
							if (GetP2CaseX(k)-i-1>-1 && GetP2CaseY(k)-i-1>-1){
								if (Pieceboard[GetP2CaseX(k)-i-1][GetP2CaseY(k)-i-1]==0){
									Moveboard[GetP2CaseX(k)-i-1][GetP2CaseY(k)-i-1]=2;
									Takenboard[GetP2CaseX(k)-i][GetP2CaseY(k)-i]=1;
									}
								}
							break;
							}
						else break;
						}
					for(int i=1; GetP2CaseX(k)+i<8 && GetP2CaseY(k)+i<8; i++){
						if (Pieceboard[GetP2CaseX(k)+i][GetP2CaseY(k)+i]==0) Moveboard[GetP2CaseX(k)+i][GetP2CaseY(k)+i]=1;
						else if (Pieceboard[GetP2CaseX(k)+i][GetP2CaseY(k)+i]==1){
							if (GetP2CaseX(k)+i+1<8 && GetP2CaseY(k)+i+1<8){
								if (Pieceboard[GetP2CaseX(k)+i+1][GetP2CaseY(k)+i+1]==0){
									Moveboard[GetP2CaseX(k)+i+1][GetP2CaseY(k)+i+1]=2;
									Takenboard[GetP2CaseX(k)+i][GetP2CaseY(k)+i]=1;
									}
								}
							break;
							}
						else break;
						}
					for(int i=1; GetP2CaseX(k)-i>-1 && GetP2CaseY(k)+i<8; i++){
						if (Pieceboard[GetP2CaseX(k)-i][GetP2CaseY(k)+i]==0) Moveboard[GetP2CaseX(k)-i][GetP2CaseY(k)+i]=1;
						else if (Pieceboard[GetP2CaseX(k)-i][GetP2CaseY(k)+i]==1){
							if (GetP2CaseX(k)-i-1>-1 && GetP2CaseY(k)+i+1<8){
								if (Pieceboard[GetP2CaseX(k)-i-1][GetP2CaseY(k)+i+1]==0){
									Moveboard[GetP2CaseX(k)-i-1][GetP2CaseY(k)+i+1]=2;
									Takenboard[GetP2CaseX(k)-i][GetP2CaseY(k)+i]=1;
									}
								}
							break;
							}
						else break;
						}
					for(int i=1; GetP2CaseX(k)+i<8 && GetP2CaseY(k)-i>-1; i++){
						if (Pieceboard[GetP2CaseX(k)+i][GetP2CaseY(k)-i]==0) Moveboard[GetP2CaseX(k)+i][GetP2CaseY(k)-i]=1;
						else if (Pieceboard[GetP2CaseX(k)+i][GetP2CaseY(k)-i]==1){
							if (GetP2CaseX(k)+i+1<8 && GetP2CaseY(k)-i-1>-1){
								if (Pieceboard[GetP2CaseX(k)+i+1][GetP2CaseY(k)-i-1]==0){
									Moveboard[GetP2CaseX(k)+i+1][GetP2CaseY(k)-i-1]=2;
									Takenboard[GetP2CaseX(k)+i][GetP2CaseY(k)-i]=1;
									}
								}
							break;
							}
						else break;
						}
					}
				if (Moveboard[CaseX][CaseY]==1){
					Pieceboard[GetP2CaseX(k)][GetP2CaseY(k)]=0;
					P2x[k]=25+CaseX*100;
					P2y[k]=25+CaseY*100;
					P2Selected[k]=0;
					//Pieceboard[CaseX][CaseY]=2;
					Turn=1;
					PieceRefresh();
					T+=1;
					HistoRecord();
					HistoResetNext();
					SoufP2();
					}
				else if (Moveboard[CaseX][CaseY]==2){

					if (DoubleSelect==0){
						TakeAll();
						Pieceboard[GetP2CaseX(k)][GetP2CaseY(k)]=0;
						P2x[k]=25+CaseX*100;
						P2y[k]=25+CaseY*100;
						P2Selected[k]=0;
						//Pieceboard[CaseX][CaseY]=2;
						Turn=1;
						DoubleSelect=0;
						PieceRefresh();
						T+=1;
						HistoRecord();
						HistoResetNext();
						SoufP2();
						}
					}
				}
			}
				


		for(int i=0; i<8; i++){
			if (Chain[i][2]==4) Moveboard[Chain[i][0]][Chain[i][1]]=Chain[i][2];
			for(int j=0; j<8; j++){
				if (Moveboard[i][j]==1) g.setColor(Color.cyan);
				//if (Moveboard[i][j]!=0) g.fillRect(25+i*100, 25+j*100, 100, 100);

				else if (Moveboard[i][j]==2) g.setColor(Color.red);
				//if (Moveboard[i][j]!=0) g.fillRect(25+i*100, 25+j*100, 100, 100);

				else if (Moveboard[i][j]==3) g.setColor(Color.magenta);
				//if (Moveboard[i][j]!=0) g.fillRect(25+i*100, 25+j*100, 100, 100);

				else if (Moveboard[i][j]==4) g.setColor(Color.orange);
				if (Moveboard[i][j]!=0) g.fillRect(25+i*100, 25+j*100, 100, 100);
				}
			}
		
//***Pieces
		for(int k=0; k<12; k++){
			if (P1y[k]==725) P1Promotion[k]=1;
			else if (P2y[k]==25) P2Promotion[k]=1;

			if (P1Taken[k]==1) P1x[k]=P1y[k]=0;
			if (P2Taken[k]==1) P2x[k]=P2y[k]=0;
			}

		for(int k=0; k<12; k++){
			if (P1x[k]==SelectedCaseX && P1y[k]==SelectedCaseY && P1Taken[k]==0){
				P1Selected[k]=1;
				}
			else if (P2x[k]==SelectedCaseX && P2y[k]==SelectedCaseY && P2Taken[k]==0){
				P2Selected[k]=1;
				}
			else P1Selected[k]=P2Selected[k]=0;
			if (Chain[0][2]==0) P1SelectedChain[k]=P2SelectedChain[k]=0;
			}
		for(int i=0; i<8; i++){//####
			for(int j=0; j<8; j++){
				//Pieceboard[i][j]=0;
				for(int k=0; k<12; k++){
					//if (P1x[k]==25+i*100 && P1y[k]==25+j*100 && P1Taken[k]==0) Pieceboard[i][j]=1;
					//if (P2x[k]==25+i*100 && P2y[k]==25+j*100 && P2Taken[k]==0) Pieceboard[i][j]=2;
					}
				PieceboardBis[j][i]=Pieceboard[i][j];
				MoveboardBis[j][i]=Moveboard[i][j];
				TakenboardBis[j][i]=Takenboard[i][j];
				}
			}

		
		for(int k=0; k<12; k++){
			g.setColor(Color.red);
			if (P1Taken[k]==0){
				g.fillOval(P1x[k], P1y[k], 100, 100);
				if (P1Promotion[k]==1){
					g.setColor(Color.yellow);
					g.fillOval(P1x[k]+25, P1y[k]+25, 50, 50);
					}
				}
			g.setColor(Color.pink);
			if (P2Taken[k]==0){
				g.fillOval(P2x[k], P2y[k], 100, 100);
				if (P2Promotion[k]==1){
					g.setColor(Color.yellow);
					g.fillOval(P2x[k]+25, P2y[k]+25, 50, 50);
					}
				}
			}


		if (Turn==1) g.setColor(Color.red);
		else if (Turn==2) g.setColor(Color.pink);
		g.fillOval(420+1, 10+1, 10-2, 10-2);	
		g.setColor(Color.black);
		g.drawOval(420, 10, 10, 10);
		for(int i=0; i<7; i++){
			g.drawLine(125+i*100, 25, 125+i*100, 825);
			g.drawLine(25, 125+i*100, 825, 125+i*100);
			}
		if (P1Win()){
			Turn=0;
			g.setColor(Color.black);
			g.drawRect(400-1, 425-1, 100+2, 50+2);
			g.setColor(Color.white);
			g.fillRect(400, 425, 100, 50);
			g.setColor(Color.red);
			g.setFont(new Font("Arial",Font.BOLD, 20));
			g.drawString("P1 won !",410, 460);
			}
		else if (P2Win()){
			Turn=0;
			g.setColor(Color.black);
			g.drawRect(400-1, 425-1, 100+2, 50+2);
			g.setColor(Color.white);
			g.fillRect(400, 425, 100, 50);
			g.setColor(Color.pink);
			g.setFont(new Font("Arial",Font.BOLD, 20));
			g.drawString("P2 won !",410, 460);
			}


		g.drawString("T="+String.valueOf(T),25, 20);
		g.drawString("TBis="+String.valueOf(TBis),60, 20);
		}
//***************************************************************************************************
//***************************************************************************************************
//***************************************************************************************************
//***************************************************************************************************
	public int GetP1CaseX(int k){
		int I=0;
		for(int i=0; i<8; i++){
			if (P1x[k]==25+i*100) I=i;
			}
		return I;
		}
	public int GetP1CaseY(int k){
		int I=0;
		for(int i=0; i<8; i++){
			if (P1y[k]==25+i*100) I=i;
			}
		return I;
		}
	public int GetP2CaseX(int k){
		int I=0;
		for(int i=0; i<8; i++){
			if (P2x[k]==25+i*100) I=i;
			}
		return I;
		}
	public int GetP2CaseY(int k){
		int I=0;
		for(int i=0; i<8; i++){
			if (P2y[k]==25+i*100) I=i;
			}
		return I;
		}
	public int HistoGetP1CaseX(int k, int T){
		int I=0;
		for(int i=0; i<8; i++){
			if (Historical[T][k]==25+i*100) I=i;
			}
		return I;
		}
	public int HistoGetP1CaseY(int k, int T){
		int I=0;
		for(int i=0; i<8; i++){
			if (Historical[T][k+12]==25+i*100) I=i;
			}
		return I;
		}
	public int HistoGetP2CaseX(int k, int T){
		int I=0;
		for(int i=0; i<8; i++){
			if (Historical[T][k+48]==25+i*100) I=i;
			}
		return I;
		}
	public int HistoGetP2CaseY(int k, int T){
		int I=0;
		for(int i=0; i<8; i++){
			if (Historical[T][k+60]==25+i*100) I=i;
			}
		return I;
		}
	public void TakeAll(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				for(int k=0; k<12; k++){
					if (Takenboard[i][j]==1 && GetP1CaseX(k)==i && GetP1CaseY(k)==j){
						Pieceboard[i][j]=0;
						P1Taken[k]=1;
						P1x[k]=P1y[k]=0;
						}
					else if (Takenboard[i][j]==1 && GetP2CaseX(k)==i && GetP2CaseY(k)==j){
						Pieceboard[i][j]=0;
						P2Taken[k]=1;
						P2x[k]=P2y[k]=0;
						}
					}
				}
			}
		}
//## Petit mot "Souf" en jaune apparaît sur le centre de la case et part dans une direction aléatoire
//## en disparaissant
	public void SoufP1(){
		for(int k=0; k<12; k++){
			int X=GetP1CaseX(k);
			int Y=GetP1CaseY(k);
			int X1=HistoGetP1CaseX(k,T-1);
			int Y1=HistoGetP1CaseY(k,T-1);
			if (P1Promotion[k]==0){
				if (X1+1!=8){
					if (Y1+1!=8){
						if (Histoboard[T-1][X1+1][Y1+1]==2){
							if (X1+2<8 && Y1+2<8){
								if (Histoboard[T-1][X1+2][Y1+2]==0){
									if (Pieceboard[X1+1][Y1+1]==2) P1Taken[k]=1;
									}
								}	
							}
						}
					if (Y1-1!=-1){
						if (Histoboard[T-1][X1+1][Y1-1]==2){
							if (X1+2<8 && Y1-2>-1){
								if (Histoboard[T-1][X1+2][Y1-2]==0){
									if (Pieceboard[X1+1][Y1-1]==2) P1Taken[k]=1;
									}
								}	
							}
						}
					}
				if (X1-1!=-1){
					if (Y1+1!=8){
						if (Histoboard[T-1][X1-1][Y1+1]==2){
							if (X1-2>-1 && Y1+2<8){
								if (Histoboard[T-1][X1-2][Y1+2]==0){
									if (Pieceboard[X1-1][Y1+1]==2) P1Taken[k]=1;
									}
								}	
							}
						}
					if (Y1-1!=-1){
						if (Histoboard[T-1][X1-1][Y1-1]==2){
							if (X1-2>-1 && Y1-2>-1){
								if (Histoboard[T-1][X1-2][Y1-2]==0){
									if (Pieceboard[X1-1][Y1-1]==2) P1Taken[k]=1;
									}
								}	
							}
						}
					}
				}
			else if (P1Promotion[k]==1){
				for(int i=1; X1+i<8 && Y1+i<8; i++){
					if (Histoboard[T-1][X1+i][Y1+i]==2){
						if (X1+i+1<8 && Y1+i+1<8){
							if (Histoboard[T-1][X1+i+1][Y1+i+1]==0 && Pieceboard[X1+i][Y1+i]==2) P1Taken[k]=1;
							}
						}
					}
				for(int i=1; X1+i<8 && Y1-i>-1; i++){
					if (Histoboard[T-1][X1+i][Y1-i]==2){
						if (X1+i+1<8 && Y1-i-1>-1){
							if (Histoboard[T-1][X1+i+1][Y1-i-1]==0 && Pieceboard[X1+i][Y1-i]==2) P1Taken[k]=1;
							}
						}
					}
				for(int i=1; X1-i>-1 && Y1+i<8; i++){
					if (Histoboard[T-1][X1-i][Y1+i]==2){
						if (X1-i-1>-1 && Y1+i+1<8){
							if (Histoboard[T-1][X1-i-1][Y1+i+1]==0 && Pieceboard[X1-i][Y1+i]==2) P1Taken[k]=1;
							}
						}
					}
				for(int i=1; X1-i>-1 && Y1-i>-1; i++){
					if (Histoboard[T-1][X1-i][Y1-i]==2){
						if (X1-i-1>-1 && Y1-i-1>-1){
							if (Histoboard[T-1][X1-i-1][Y1-i-1]==0 && Pieceboard[X1-i][Y1-i]==2) P1Taken[k]=1;
							}
						}
					}
				}
			if (P1Taken[k]==1){
				Pieceboard[X][Y]=0;
				HistoRecord();
				}
			}
		}

	public void SoufP2(){
		for(int k=0; k<12; k++){
			int X=GetP2CaseX(k);
			int Y=GetP2CaseY(k);
			int X1=HistoGetP2CaseX(k,T-1);
			int Y1=HistoGetP2CaseY(k,T-1);
			if (P2Promotion[k]==0){
				if (X1+1!=8){
					if (Y1+1!=8){
						if (Histoboard[T-1][X1+1][Y1+1]==1){
							if (X1+2<8 && Y1+2<8){
								if (Histoboard[T-1][X1+2][Y1+2]==0){
									if (Pieceboard[X1+1][Y1+1]==1) P2Taken[k]=1;
									}
								}	
							}
						}
					if (Y1-1!=-1){
						if (Histoboard[T-1][X1+1][Y1-1]==1){
							if (X1+2<8 && Y1-2>-1){
								if (Histoboard[T-1][X1+2][Y1-2]==0){
									if (Pieceboard[X1+1][Y1-1]==1) P2Taken[k]=1;
									}
								}	
							}
						}
					}
				if (X1-1!=-1){
					if (Y1+1!=8){
						if (Histoboard[T-1][X1-1][Y1+1]==1){
							if (X1-2>-1 && Y1+2<8){
								if (Histoboard[T-1][X1-2][Y1+2]==0){
									if (Pieceboard[X1-1][Y1+1]==1) P2Taken[k]=1;
									}
								}	
							}
						}
					if (Y1-1!=-1){
						if (Histoboard[T-1][X1-1][Y1-1]==1){
							if (X1-2>-1 && Y1-2>-1){
								if (Histoboard[T-1][X1-2][Y1-2]==0){
									if (Pieceboard[X1-1][Y1-1]==1) P2Taken[k]=1;
									}
								}	
							}
						}
					}
				}
			else if (P2Promotion[k]==1){
				for(int i=1; X1+i<8 && Y1+i<8; i++){
					if (Histoboard[T-1][X1+i][Y1+i]==1){
						if (X1+i+1<8 && Y1+i+1<8){
							if (Histoboard[T-1][X1+i+1][Y1+i+1]==0 && Pieceboard[X1+i][Y1+i]==1) P2Taken[k]=1;
							}
						}
					}
				for(int i=1; X1+i<8 && Y1-i>-1; i++){
					if (Histoboard[T-1][X1+i][Y1-i]==1){
						if (X1+i+1<8 && Y1-i-1>-1){
							if (Histoboard[T-1][X1+i+1][Y1-i-1]==0 && Pieceboard[X1+i][Y1-i]==1) P2Taken[k]=1;
							}
						}
					}
				for(int i=1; X1-i>-1 && Y1+i<8; i++){
					if (Histoboard[T-1][X1-i][Y1+i]==1){
						if (X1-i-1>-1 && Y1+i+1<8){
							if (Histoboard[T-1][X1-i-1][Y1+i+1]==0 && Pieceboard[X1-i][Y1+i]==1) P2Taken[k]=1;
							}
						}
					}
				for(int i=1; X1-i>-1 && Y1-i>-1; i++){
					if (Histoboard[T-1][X1-i][Y1-i]==1){
						if (X1-i-1>-1 && Y1-i-1>-1){
							if (Histoboard[T-1][X1-i-1][Y1-i-1]==0 && Pieceboard[X1-i][Y1-i]==1) P2Taken[k]=1;
							}
						}
					}
				}
			if (P2Taken[k]==1){
				Pieceboard[X][Y]=0;
				HistoRecord();
				}
			}
		}
					

	public static boolean P1Win(){
		for(int k=0; k<12; k++) if (P2Taken[k]==0) return false;
		return true;
		}
	public static boolean P2Win(){
		for(int k=0; k<12; k++) if (P1Taken[k]==0) return false;
		return true;
		}
	public static void HistoRefresh(){
		for(int k=0; k<12; k++){
			P1x[k]=Historical[T][k];
			P1y[k]=Historical[T][12+k];
			P1Taken[k]=Historical[T][24+k];
			P1Promotion[k]=Historical[T][36+k];

			P2x[k]=Historical[T][48+k];
			P2y[k]=Historical[T][60+k];
			P2Taken[k]=Historical[T][72+k];
			P2Promotion[k]=Historical[T][84+k];

			Turn=Historical[T][96];
			}
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Pieceboard[i][j]=Histoboard[T][i][j];
				}
			}
		}
	public static void HistoRecord(){
		for(int k=0; k<12; k++){
			Historical[T][k]=P1x[k];
			Historical[T][12+k]=P1y[k];
			Historical[T][24+k]=P1Taken[k];
			Historical[T][36+k]=P1Promotion[k];

			Historical[T][48+k]=P2x[k];
			Historical[T][60+k]=P2y[k];
			Historical[T][72+k]=P2Taken[k];
			Historical[T][84+k]=P2Promotion[k];
			Historical[T][96]=Turn;
			}
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				Histoboard[T][i][j]=Pieceboard[i][j];
				}
			}
		}
	public static void HistoResetNext(){
		for(int i=T+1; i<200; i++){
			for(int j=0; j<100; j++){
				Historical[i][j]=0;
				}
			}
		for(int l=T+1; l<200; l++){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					Histoboard[l][i][j]=0;
					}
				}
			}
		}

	public static void afficherBoard(int [][] T){
		for(int i=0; i<8; i++){
			System.out.print("[");
			for(int j=0; j<8; j++){
				if ((i+j)%2==1){
					if (j<7) System.out.print(T[i][j]+", ");
					else System.out.println(T[i][j]+"]");
					}
				else{
					if (j<7) System.out.print(". ");
					else System.out.println(".]");
					}
				}
			}
		System.out.println();
		}
	public static void afficherP(){
		System.out.println("P1");
		for(int i=0; i<12; i++){
			System.out.println(i+": "+"Selected="+P1Selected[i]+"\t"+"x="+P1x[i]+"\t"+"y="+P1y[i]+"\t"+"Taken="+P1Taken[i]+"\t"+"Promotion="+P1Promotion[i]);
			}
		System.out.println("P2");
		for(int i=0; i<12; i++){
			System.out.println(i+": "+"Selected="+P2Selected[i]+"\t"+"x="+P2x[i]+"\t"+"y="+P2y[i]+"\t"+"Taken="+P2Taken[i]+"\t"+"Promotion="+P2Promotion[i]);
			}
		}
	public static int [][] GetHistoboard(int T){
		int [][] H = new int [8][8];
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				H[j][i]=Histoboard[T][i][j];
				}
			}
		return H;
		}
	public static void PieceRefresh(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				for(int k=0; k<12; k++){
					if (P1x[k]==25+i*100 && P1y[k]==25+j*100 && P1Taken[k]==0) Pieceboard[i][j]=1;
					if (P2x[k]==25+i*100 && P2y[k]==25+j*100 && P2Taken[k]==0) Pieceboard[i][j]=2;
					}
				PieceboardBis[j][i]=Pieceboard[i][j];
				MoveboardBis[j][i]=Moveboard[i][j];
				TakenboardBis[j][i]=Takenboard[i][j];
				}
			}
		}
	public boolean IsChainPossibleP1(int X, int Y){
		if (X+2<8){
			if (Y+2<8){
				int I = GetChainMoveI(X+1, Y+1);
				//if (Pieceboard[X+1][Y+1]==2 && Pieceboard[X+2][Y+2]==0 && Chain[I][2]==0) return true;
				if (Pieceboard[X+1][Y+1]==2 && Pieceboard[X+2][Y+2]==0 && NotInChainXY(X+2,Y+2)) return true;
				}
			if (Y-2>-1){
				int I = GetChainMoveI(X+1, Y-1);
				//if (Pieceboard[X+1][Y-1]==2 && Pieceboard[X+2][Y-2]==0 && Chain[I][2]==0) return true;
				if (Pieceboard[X+1][Y-1]==2 && Pieceboard[X+2][Y-2]==0 && NotInChainXY(X+2,Y-2)) return true;
				}
			}
		if (X-2>-1){
			if (Y+2<8){
				int I = GetChainMoveI(X-1, Y+1);
				//if (Pieceboard[X-1][Y+1]==2 && Pieceboard[X-2][Y+2]==0 && Chain[I][2]==0) return true;
				if (Pieceboard[X-1][Y+1]==2 && Pieceboard[X-2][Y+2]==0 && NotInChainXY(X-2,Y+2)) return true;
				}
			if (Y-2>-1){
				int I = GetChainMoveI(X-1, Y-1);
				//if (Pieceboard[X-1][Y-1]==2 && Pieceboard[X-2][Y-2]==0 && Chain[I][2]==0) return true;
				if (Pieceboard[X-1][Y-1]==2 && Pieceboard[X-2][Y-2]==0 && NotInChainXY(X-2,Y-2)) return true;
				}
			}
		return false;
		}
	public boolean IsChainPossibleP1Promoted(int X, int Y){
		for(int i=1; X+i<8-2 && Y+i<8-2; i++){
			if (Pieceboard[X+i+1][Y+i+1]==2 && Pieceboard[X+i+2][Y+i+2]==0 && NotInChainXY(X+i+2,Y+i+2)) return true;
			}
		for(int i=1; X-i>-1+2 && Y+i<8-2; i++){
			if (Pieceboard[X-i-1][Y+i+1]==2 && Pieceboard[X-i-2][Y+i+2]==0 && NotInChainXY(X-i-2,Y+i+2)) return true;
			}
		for(int i=1; X+i<8-2 && Y-i>-1+2; i++){
			if (Pieceboard[X+i+1][Y-i-1]==2 && Pieceboard[X+i+2][Y-i-2]==0 && NotInChainXY(X+i+2,Y-i-2)) return true;
			}
		for(int i=1; X-i>-1+2 && Y-i>-1+2; i++){
			if (Pieceboard[X-i-1][Y-i-1]==2 && Pieceboard[X-i-2][Y-i-2]==0 && NotInChainXY(X-i-2,Y-i-2)) return true;
			}
		return false;
		}
	//public int [][] ChainP1(int X, int Y){
	
	public int GetChainMoveI(int X, int Y){
		int I=0;
		for(int i=0; i<8; i++){
			if (Chain[i][0]==X && Chain[i][1]==Y) I=i;
			}
		return I;
		}
	public boolean NotInChainXY(int X, int Y){
		for(int i=0; i<8; i++){
			if (Chain[i][0]==X && Chain[i][1]==Y) return false;
			}
		return true;
		}
		

	public boolean IsChainPossibleP2(int X, int Y){
		if (X+2<8){
			if (Y+2<8){
				if (Pieceboard[X+1][Y+1]==1 && Pieceboard[X+2][Y+2]==0) return true;
				}
			if (Y-2>-1){
				if (Pieceboard[X+1][Y-1]==1 && Pieceboard[X+2][Y-2]==0) return true;
				}
			}
		if (X-2>-1){
			if (Y+2<8){
				if (Pieceboard[X-1][Y+1]==1 && Pieceboard[X-2][Y+2]==0) return true;
				}
			if (Y-2>-1){
				if (Pieceboard[X-1][Y-1]==1 && Pieceboard[X-2][Y-2]==0) return true;
				}
			}
		return false;
		}


	public int GetLastChainX(){
		for(int i=1; i<8; i++){
			if (Chain[i][2]==0) return Chain[i-1][0];
			}
		return Chain[7][0];
		}
	public int GetLastChainY(){
		for(int i=1; i<8; i++){
			if (Chain[i][2]==0) return Chain[i-1][1];
			}
		return Chain[7][1];
		}
	public void PutInChain(int X, int Y, int move, int TX, int TY){
		for(int i=0; i<8; i++){
			if (Chain[i][2]==0){
				Chain[i][0]=X;
				Chain[i][1]=Y;
				Chain[i][2]=move;
				Chain[i][3]=TX;
				Chain[i][4]=TY;
				break;
				}
			}
		}
	public void afficherChain(){
		for(int i=0; i<8; i++){
			System.out.println(i+":ChainCaseX="+Chain[i][0]+"\t"+"ChainCaseY="+Chain[i][1]+"\t"+"Move="+Chain[i][2]+"\t"+"TakenCaseX="+Chain[i][3]+"\t"+"TakenCaseY="+Chain[i][4]);
			}
		}
	}
//****************************************************************************************
//****************************************************************************************
//****************************************************************************************
//****************************************************************************************
class SourisSelection implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		int X = event.getX();
		int Y = event.getY();
		if (c==1){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (25+i*100<=X && X<=125+i*100 && 25+j*100<Y && Y<125+j*100){
						Pan.SelectedCaseX = 25+i*100;
						Pan.SelectedCaseY = 25+j*100;
						Pan.CaseX=i;
						Pan.CaseY=j;
						for(int k=0; k<12; k++){
							if (Pan.P1x[k]==25+i*100 && Pan.P1y[k]==25+j*100){
								//Pan.P1Selected[k]=1;
								System.out.println("P1 - "+k);
								}
							else if (Pan.P2x[k]==25+i*100 && Pan.P2y[k]==25+j*100){
								//Pan.P2Selected[k]=1;
								System.out.println("P2 - "+k);
								}
							}
						}
					}
				}
			}
		else if (c==3){
			Pan.SelectedCaseX=0;
			Pan.SelectedCaseY=0;
			for(int i=0; i<12; i++){
				Pan.P1Selected[i]=0;
				Pan.P2Selected[i]=0;
				Pan.ChainS=0;
				if (i<8){
					for(int j=0; j<5; j++){
						Pan.Chain[i][j]=0;
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
class Touche implements KeyListener{
	public void keyTyped(KeyEvent event){
		char k = event.getKeyChar();
		if (k=='p') Pan.afficherP();
		if (k=='t') Pan.afficherBoard(Pan.TakenboardBis);
		if (k=='m') Pan.afficherBoard(Pan.MoveboardBis);
		if (k=='P') Pan.afficherBoard(Pan.PieceboardBis);
		if (k=='T') Pan.afficherBoard(Pan.GetHistoboard(Pan.TBis));
		if (k=='n' && Pan.Turn==1) Pan.Turn=2; 
		else Pan.Turn=1;
		}
	public void keyPressed(KeyEvent event){
		int c = event.getKeyCode();
		char k = event.getKeyChar();
		if (k=='i') Pan.info=1;
		if (c==0x25 && Pan.T-1!=-1){
			Pan.SelectedCaseX=0;
			Pan.SelectedCaseY=0;
			for(int i=0; i<12; i++){
				Pan.P1Selected[i]=0;
				Pan.P2Selected[i]=0;
				}
			Pan.T-=1;
			Pan.HistoRefresh();
			}
		if (c==0x27 && Pan.Historical[Pan.T+1][0]!=0){//////////////////////////################# touche
			Pan.SelectedCaseX=0;
			Pan.SelectedCaseY=0;
			for(int i=0; i<12; i++){
				Pan.P1Selected[i]=0;
				Pan.P2Selected[i]=0;
				}
			Pan.T+=1;
			Pan.HistoRefresh();
			}
		if (c==0x26 && Pan.TBis<Pan.T) Pan.TBis+=1;
		if (c==0x28 && Pan.TBis>0) Pan.TBis-=1;
		}
	public void keyReleased(KeyEvent event){
		char k = event.getKeyChar();
		if (k=='i') Pan.info=0;
		}
	}

public class Dames{
	public static void main(String args[]){
		Fen fen = new Fen("fenetre", 850+16, 850+40);
		Pan pan = new Pan(850, 850);
		fen.setContentPane(pan);
//*Initialisation
		for(int i=0; i<4; i++){
			pan.P1x[i]=125+i*200;
			pan.P1x[4+i]=25+i*200;
			pan.P1x[8+i]=125+i*200;
			pan.P1y[i]=25;
			pan.P1y[4+i]=125;
			pan.P1y[8+i]=225;

			pan.P2x[i]=25+i*200;
			pan.P2x[4+i]=125+i*200;
			pan.P2x[8+i]=25+i*200;
			pan.P2y[i]=525;
			pan.P2y[4+i]=625;
			pan.P2y[8+i]=725;
			}
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				for(int k=0; k<12; k++){
					if (pan.P1x[k]==25+i*100 && pan.P1y[k]==25+j*100 && pan.P1Taken[k]==0) pan.Pieceboard[i][j]=1;
					if (pan.P2x[k]==25+i*100 && pan.P2y[k]==25+j*100 && pan.P2Taken[k]==0) pan.Pieceboard[i][j]=2;
					}
				}
			}
		pan.HistoRecord();
		while (true){
			if (pan.gameplay==0){
				pan.Solo.setVisible(true);
				pan.Duo.setVisible(true);
				}
			else if (pan.gameplay!=0){
				pan.Solo.setVisible(false);
				pan.Duo.setVisible(false);
				}

			pan.repaint();
			}
		}
}