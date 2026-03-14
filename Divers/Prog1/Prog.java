import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

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
	static int Nx=0+(int)(Math.random()*571);
	static int Ny=50;
	static int Rx=0+(int)(Math.random()*571);
	static int Ry=100;
	static int Bx=0+(int)(Math.random()*571);
	static int By=150;
	static int Vx=0+(int)(Math.random()*571);
	static int Vy=200;
	static int Yx=0+(int)(Math.random()*571);
	static int Yy=250;
	static int Mx=0+(int)(Math.random()*571);
	static int My=300;
	static int Cx=0+(int)(Math.random()*571);
	static int Cy=350;
	static int Px=0+(int)(Math.random()*571);
	static int Py=400;
	static int Ox=0+(int)(Math.random()*571);
	static int Oy=450;
	static int Gx=0+(int)(Math.random()*571);
	static int Gy=500;
	static int Ns=1;
	static int NS=1;
	static int Rs=1;
	static int RS=1;
	static int Bs=1;
	static int BS=1;
	static int Vs=1;
	static int VS=1;
	static int Ys=1;
	static int YS=1;
	static int Ms=1;
	static int MS=1;
	static int Cs=1;
	static int CS=1;
	static int Ps=1;
	static int PS=1;
	static int Os=1;
	static int OS=1;
	static int Gs=1;
	static int GS=1;
	static int Motion=0;
	static int SwingColor=0;
	SourisListener souris = new SourisListener();
	public Pan(int larg, int haut){
		this.setPreferredSize(new Dimension(larg, haut));
		this.addMouseListener(souris);
		}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.fillRect(Nx, Ny, 30, 100);
		if (Motion==1){
			Nx+=1*Ns;
			if (Rebondx(Nx, 30)) Ns=Ns*(-1);
			Ny+=1*NS;
			if (Rebondy(Ny, 100)) NS=NS*(-1);
			}

		if (SwingColor==0) g.setColor(Color.red);
		else g.setColor(Color.black);
		g.fillRect(Rx, Ry, 30, 100);
		if (Motion==1){
			Rx+=1*Rs;
			if (Rebondx(Rx, 30)) Rs=Rs*(-1);
			Ry+=1*RS;
			if (Rebondy(Ry, 100)) RS=RS*(-1);
			}

		if (SwingColor==0) g.setColor(Color.blue);
		else g.setColor(Color.black);
		g.fillRect(Bx, By, 30, 100);
		if (Motion==1){
			Bx+=1*Bs;
			if (Rebondx(Bx, 30)) Bs=Bs*(-1);
			By+=1*BS;
			if (Rebondy(By, 100)) BS=BS*(-1);
			}

		if (SwingColor==0) g.setColor(Color.green);
		else g.setColor(Color.black);
		g.fillRect(Vx, Vy, 30, 100);
		if (Motion==1){
			Vx+=1*Vs;
			if (Rebondx(Vx, 30)) Vs=Vs*(-1);
			Vy+=1*VS;
			if (Rebondy(Vy, 100)) VS=VS*(-1);
			}

		if (SwingColor==0) g.setColor(Color.yellow);
		else g.setColor(Color.black);
		g.fillRect(Yx, Yy, 30, 100);
		if (Motion==1){
			Yx+=1*Ys;
			if (Rebondx(Yx, 30)) Ys=Ys*(-1);
			Yy+=1*YS;
			if (Rebondy(Yy, 100)) YS=YS*(-1);
			}

		if (SwingColor==0) g.setColor(Color.magenta);
		else g.setColor(Color.black);
		g.fillRect(Mx, My, 30, 100);
		if (Motion==1){
			Mx+=1*Ms;
			if (Rebondx(Mx, 30)) Ms=Ms*(-1);
			My+=1*MS;
			if (Rebondy(My, 100)) MS=MS*(-1);
			}

		if (SwingColor==0) g.setColor(Color.cyan);
		else g.setColor(Color.black);
		g.fillRect(Cx, Cy, 30, 100);
		if (Motion==1){
			Cx+=1*Cs;
			if (Rebondx(Cx, 30)) Cs=Cs*(-1);
			Cy+=1*CS;
			if (Rebondy(Cy, 100)) CS=CS*(-1);
			}
			
		if (SwingColor==0) g.setColor(Color.pink);
		else g.setColor(Color.black);
		g.fillRect(Px, Py, 30, 100);
		if (Motion==1){
			Px+=1*Ps;
			if (Rebondx(Px, 30)) Ps=Ps*(-1);
			Py+=1*PS;
			if (Rebondy(Py, 100)) PS=PS*(-1);
			}
		if (SwingColor==0) g.setColor(Color.orange);
		else g.setColor(Color.black);
		g.fillRect(Ox, Oy, 30, 100);
		if (Motion==1){
			Ox+=1*Os;
			if (Rebondx(Ox, 30)) Os=Os*(-1);
			Oy+=1*OS;
			if (Rebondy(Oy, 100)) OS=OS*(-1);
			}
		if (SwingColor==0) g.setColor(Color.gray);
		else g.setColor(Color.black);
		g.fillRect(Gx, Gy, 30, 100);
		if (Motion==1){
			Gx+=1*Gs;
			if (Rebondx(Gx, 30)) Gs=Gs*(-1);
			Gy+=1*GS;
			if (Rebondy(Gy, 100)) GS=GS*(-1);
			}
		}
	public boolean Rebondx(int x, int width){
		return (x==0) || (x+width==this.getWidth());
		}
	public boolean Rebondy(int y, int height){
		return (y==0) || (y+height==this.getHeight());
		}
	}
class SourisListener implements MouseListener{
	public void mouseClicked(MouseEvent event){
		int c = event.getButton();
		if (c==1 && Pan.SwingColor==1) Pan.SwingColor=0;
		else if (c==1) Pan.SwingColor=1;
		if (c==3 && Pan.Motion==0) Pan.Motion=1;
		else if (c==3) Pan.Motion=0;
		}
	public void mousePressed(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	}
public class Prog{
	public static void main(String[] args){
		Fen fen = new Fen("Fenetre", 616, 640);
		Pan pan = new Pan(600, 600);
		fen.setContentPane(pan);
		while (true){
			pan.repaint();
			}
		}
}