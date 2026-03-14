import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

class Fen extends JFrame{
	public Fen(String nom, int larg, int haut){
		this.setTitle(nom);
		this.setSize(larg, haut);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

class Pan extends JPanel{
	int nbBulles=0;
	static int ColorChoixIn=1;
	static Color couleurFond = new Color(0, 0, 255);
	JColorChooser Couleurs = new JColorChooser();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		
		Couleurfond ChoixCouleurFond = new Couleurfond();
		//Couleurs.setPreferredSize(new Dimension(150, 150));
		//Couleurs.addActionListener(ChoixCouleurFond);
		//this.add(Couleurs);
		}
	
	public void paintComponent(Graphics g){
		g.setColor(couleurFond);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.yellow);
		
		for(int i=0; i<nbBulles; i++){
			int taille = 10+(int)(Math.random()*(100-10)+1);
			g.drawOval(10+(int)(Math.random()*(this.getWidth()-20)+1), 10+(int)(Math.random()*(this.getHeight()-20)+1), taille, taille);
			}
		}
	}
class Couleurfond implements ActionListener{
	public void actionPerformed(ActionEvent event){
		//Pan.couleurFond = event.getColor();
		}
	}

public class Bulles{
	public static void main(String[] args){
		Pan pan = new Pan(800, 800);
		Scanner sc = new Scanner(System.in);
		System.out.print("Combien de bulles ?");
		pan.nbBulles = sc.nextInt();
		Fen fen = new Fen("Fenetre", 816, 800);
		fen.setContentPane(pan);
		}
}