class Graphe{
    //Classe permettant  de représenter des graphes orientés"""
    public Graphe(){
        //Initialiser le graphe à vide"""
        private int this.n = 0;
        private int this.m = 0;
	}
    
    public int nombre_de_sommets(){
        //Renvoie le nombre de sommets"""
        return this.n;
	}
    public int nombre_d_arcs(){
        //Renvoie le nombre d'arcs"""
        return this.m;
	}
    public void ajouter_sommet(String x){
        //ajouter le sommet x"""
        this.n = this.n + 1
	}
    public String [] liste_sommets(){
        //Renvoie la liste des sommets"""
        pass;
	}
    public boolean existe_arc(String x, String y){
        //"""Renvoie True s'il existe un arc de x vers y, False sinon"""
        pass;
	}
    public void ajouter_arc(String x, String y){
        //"""Ajoute un arc de x vers y"""
        this.m = this.m + 1
	}
    public void supprimer_arc(String x,String y){
        //"""Supprime l'arc de x vers y"""
        this.m = this.m - 1;
	}
    public String [] liste_prédécesseurs(String x){
        //"""Renvoie la liste des prédécesseurs de x"""
        pass;
	}
    public String [] liste_successeurs(String x){
        //"""Renvoie la liste des successeurs de x"""
        pass;
	}
    public void afficher{
        //"""Renvoie une chaîne de caractères décrivant le graphe"""
        pass;
	}
	public boolean IsIn(String x, String T[]){
		for(int i=0; i<T.length; i++){
			if (T[i]==x) return true;
			}
		return false;
		}
}


class Graphe_Liste extends Graphe{
    /*"""Classe permettant de représenter des graphes orientés sour la forme 
    */de listes d'adjacence"""
    public Graphe_Liste(){
        //"""Initialise le graphe à vide"""
        super();
        private String [][] this.liste = new int [65][5];//indice [i][0] est les sommets
        private String [][] this.p_arc = new int [65][5];
	}
    public void ajouter_sommet(String x):
        //"""Ajouter le sommet x"""
	boolean X=false;
	int I=0;
	for(int i=0; i<this.liste.length; i++){
		if (this.liste[i][0]==x) X=true;
		if (this.liste[i][0]==null){
			I=i;
			break;
			}
		}
	if (X==false){
		//String L [][] = new String[this.list.length+1][1];
		//L[this.list.length][0] = 



		this.liste[I][0] = x;
		//super().ajouter_sommet(x);
	   	}
	}
    public String [] liste_sommets(){
        //"""Renvoie la liste des sommets"""
	String [] L = new String[65];
	for(int i=0; i<this.liste.length; i++){
		L[i]=this.liste[i][0];
		}
        return L;
	}
    public String [][] liste_p_arc(){
        //"""Renvoie la liste des poids selon les arcs"""
        return this.p_arc;
	}
    public boolean existe_arc(String x, String y){
        //"""Renvoie True s'il existe un arc de x vers y, False sinon"""
	if (IsIn(x, this.liste) && IsIn(y, this.liste))
		for(int i=0; i<65; i++){
			if (this.liste[i][0]==x){
				for(int j=1; j<5; j++){
					if (this.liste[i][j]==y) return true;
					}
				}
			}
		return false;
		}
        else{
		System.out.println("Sommet inconnu");
		return false;
		}
	}
    public void ajouter_arc(String x, String y){
        //"""Ajoute un arc de x vers y"""
	if (this.existe_arc(x,y)==false){
		for(int i=0; i<65; i++){
			if (this.liste[i][0]==x){
				for(int j=1; j<5; j++){
					if (this.liste[i][j]==0) this.liste[i][j]=y;
					}
				}
			}
		}
	super().ajouter_arc(x, y);
	}
    public void ajouter_poids_arc(self,x,y,p):
        //"""Ajoute le poids p à l'arc (x,y)"""
        if self.existe_arc(x, y):
            if x not in self.__p_arc.keys():
                self.__p_arc.update({x:{y:p}})
            else:
                self.__p_arc[x].update({y:p})
    public void ajouter_arc_p(self,x,y,p):
        self.ajouter_arc(x, y)
        self.ajouter_poids_arc(x, y, p)
    public int poids_arc(self,x,y):
        //"""Renvoie le poids de l'arc (x,y)"""
        return self.__p_arc[x][y]
    public void supprimer_arc(self,x,y):
        //"""Supprime l'arc de x vers y"""
        if self.existe_arc(x, y):
            self.__liste[x].remove(y)
            super().supprimer_arc(x, y)
    public String [] liste_prédécesseurs(self, x):
        //"""Renvoie la liste des prédécesseurs de x"""
        l = []
        for y in self.__liste.keys():
            if x in self.__liste[y]:
                l.append(y)
        return l
    public String [] liste_successeurs(String x):
        //"""Renvoie la liste des successeurs de x"""
	if (IsIn(x, this.liste)){
		String L [] = new String [5];
		for(int i=0; i<65; i++){
			if (this.liste[i][0]==x){
				for(int j=0; j<5; j++){
					L[j]=this.liste[i][j];
					}
				break;
				}
			}
		}
	return L;
	}
    public void afficher{
        //"""Renvoie une chaîne de caractères décrivant le graphe"""
        String chaine = "";
        for x in self.__liste.keys():
            chaine = chaine + "Successeur de " + str(x) + " : " \
                + str(self.__liste[x]) + "\n"
        return chaine
    
    
    
    
    



def Dijkstra(Graphe G, String s):
    d,P={},{}
    for i in G.liste_sommets():
        d.update({i:1000})
        P.update({i:"T"})
    d[s]=0
    F = {s}
    while len(F)!=0:
        #u=F[0]
        #for i in F:
        #    if (d[i]<d[u]): u=i
        D={}
        for i in F: 
            D.update({i:d[i]})
        u0=min(list(D.values()))
        for i in F:
            if (D[i]==u0): u=i
        F.remove(u)
        for x in G.liste_successeurs(u):
            if (d[u]+G.poids_arc(u,x)<d[x]):
                d[x]=d[u]+G.poids_arc(u,x)
                P[x]=u
                F = F|{x}
    return P



public class Graphe{
	public static void main(String[] args){


	}
}