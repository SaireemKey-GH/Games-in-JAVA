package tp;

import java.util.Scanner;
class Matrice{
	static double[][] Transpose(double[][] M, int l, int c){
		double Mt[][] = new double [c][l];
		for (int i=0; i<l; i++){
			for(int j=0; j<c; j++){
				Mt[j][i] = M[i][j];
				}
			}
		return Mt;
		}
	static double [][] Somme(double[][] M1, double[][] M2, int l, int c){
		double M3[][] = new double[l][c];
		for (int i=0; i<l; i++){
			for(int j=0; j<c; j++){
				M3[i][j] = M1[i][j]+M2[i][j];
				}
			}
		return M3;
		}
	static double [][] Produit(double[][] M1, double[][] M2, int l, int k, int c){
		double M3[][] = new double [l][c];
		for (int i=0; i<l; i++){
			double s = 0;
			for(int j=0; j<c; j++){
				s = 0;
				for(int m=0; m<k; m++){
					s = s+M1[i][m]*M2[m][j]; 
					if (m==k-1) M3[i][j] = s;
					}
				}
			}
		return M3;
		}
	}

public class Ex4{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("nombre de ligne l = ? ");
		int l = sc.nextInt();
		System.out.print("nombre de colonne c = ? ");
		int c = sc.nextInt();
		double M[][] = new double [l][c];
		for (int i=0; i<l; i++){
			for (int j=0; j<c; j++){
				System.out.print("facteur a"+(i+1)+","+(j+1)+" ? ");
				M[i][j] = sc.nextDouble();
				}
			}
		System.out.println("Matrice M = ");
		for (int i=0; i<l; i++){
			System.out.print("\t(");
			for (int j=0; j<c; j++){
				if (j==c-1) System.out.print(M[i][j]+")\n");
				else System.out.print(M[i][j]+"  ");
				}
			}
		System.out.println();
		double Mt[][] = Matrice.Transpose(M,l,c);
		System.out.println("Matrice Mt = ");
		for (int i=0; i<c; i++){
			System.out.print("\t(");
			for (int j=0; j<l; j++){
				if (j==l-1) System.out.print(Mt[i][j]+")\n");
				else System.out.print(Mt[i][j]+"  ");
				}
			}


		System.out.println("\nSomme de deux matrices");
		System.out.print("nombre de ligne l = ? ");
		l = sc.nextInt();
		System.out.print("nombre de colonne c = ? ");
		c = sc.nextInt();
		double M1[][] = new double[l][c];
		double M2[][] = new double[l][c];
		System.out.println("Matrice M1 ?");
		for (int i=0; i<l; i++){
			for (int j=0; j<c; j++){
				System.out.print("facteur a"+(i+1)+","+(j+1)+" ? ");
				M1[i][j] = sc.nextDouble();
				}
			}
		System.out.println("Matrice M2 ?");
		for (int i=0; i<l; i++){
			for (int j=0; j<c; j++){
				System.out.print("facteur a"+(i+1)+","+(j+1)+" ? ");
				M2[i][j] = sc.nextDouble();
				}
			}
		System.out.println("Matrice M1 = ");
		for (int i=0; i<l; i++){
			System.out.print("\t(");
			for (int j=0; j<c; j++){
				if (j==c-1) System.out.print(M1[i][j]+")\n");
				else System.out.print(M1[i][j]+"  ");
				}
			}
		System.out.println("Matrice M2 = ");
		for (int i=0; i<l; i++){
			System.out.print("\t(");
			for (int j=0; j<c; j++){
				if (j==c-1) System.out.print(M2[i][j]+")\n");
				else System.out.print(M2[i][j]+"  ");
				}
			}
		double M3[][] = Matrice.Somme(M1,M2,l,c);
		System.out.println("Matrice M1+M2 = ");
		for (int i=0; i<l; i++){
			System.out.print("\t(");
			for (int j=0; j<c; j++){
				if (j==c-1) System.out.print(M3[i][j]+")\n");
				else System.out.print(M3[i][j]+"  ");
				}
			}

		System.out.println("Produit de deux matrices M1 et M2");
		System.out.print("nombre de ligne de M1 l = ? ");
		l = sc.nextInt();
		System.out.print("nombre de colonne de M1 et de ligne de M2 k = ? ");
		int k = sc.nextInt();
		System.out.print("nombre de colonne de M2 c = ? ");
		c = sc.nextInt();
		double m1[][] = new double[l][k];
		double m2[][] = new double[k][c];


		System.out.println("Matrice M1 ?");
		for (int i=0; i<l; i++){
			for (int j=0; j<k; j++){
				System.out.print("facteur a"+(i+1)+","+(j+1)+" ? ");
				m1[i][j] = sc.nextDouble();
				}
			}
		System.out.println("Matrice M2 ?");
		for (int i=0; i<k; i++){
			for (int j=0; j<c; j++){
				System.out.print("facteur a"+(i+1)+","+(j+1)+" ? ");
				m2[i][j] = sc.nextDouble();
				}
			}
		System.out.println("Matrice M1 = ");
		for (int i=0; i<l; i++){
			System.out.print("\t(");
			for (int j=0; j<k; j++){
				if (j==k-1) System.out.print(m1[i][j]+")\n");
				else System.out.print(m1[i][j]+"  ");
				}
			}
		System.out.println("Matrice M2 = ");
		for (int i=0; i<k; i++){
			System.out.print("\t(");
			for (int j=0; j<c; j++){
				if (j==c-1) System.out.print(m2[i][j]+")\n");
				else System.out.print(m2[i][j]+"  ");
				}
			}
		double m3[][] = Matrice.Produit(m1,m2,l,k,c);
		System.out.println("Matrice M1*M2 = ");
		for (int i=0; i<l; i++){
			System.out.print("\t(");
			for (int j=0; j<c; j++){
				if (j==c-1) System.out.print(m3[i][j]+")\n");
				else System.out.print(m3[i][j]+"  ");
				}
			}
	}
}