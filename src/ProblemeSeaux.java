import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class ProblemeSeaux {
    /**
     * Represents an instance of the "buckets problem"
     */

    String nom;                 // name of the problem (ex: "17_20_a")
    int[] capacite_Seaux;       // max capacity of each bucket
    Etat EtatFinal;             // final state, goal of the problem

    public ProblemeSeaux(String filename){
        nom = filename.replaceAll(".+\\\\","").replaceAll("\\..+","");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));

            reader.readLine(); // lit la première ligne = le nombre de seaux (inutile ici)

            String line = reader.readLine();
            capacite_Seaux = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();

            line = reader.readLine();
            EtatFinal = new Etat(Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray(), capacite_Seaux);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        print();
    }

    public void print(){
        System.out.println("Probleme de "+ capacite_Seaux.length + " seaux : \n" +
                Arrays.toString(capacite_Seaux));
        EtatFinal.print();
    }

    public ProblemeSeaux(Etat f, int[] c){
        capacite_Seaux = c;
        EtatFinal = f;
    }

    public LinkedList<String> solution(){
        Graphe G = new Graphe(EtatFinal,"l","OperationsVariees");
        G.resoudre();
        return G.solution;
    }

    public String[] tester(){
        long tempsProfondeur = 0;
        long tempsLargeur = 0;
        long tempsBasique = 0;
        long tempsOperationsVariees = 0;
        long tempsDistCarree = 0;

        long debut;
        long fin;

        boolean possible;
        String solvable;

        Graphe G;

        debut = System.currentTimeMillis();
        G = new Graphe(EtatFinal,"p","none");
        possible = G.resoudre();
        fin = System.currentTimeMillis();
        tempsProfondeur=fin-debut;
//        System.out.println(debut+ "->"+ fin+ " P: "+tempsProfondeur);
//        System.out.println("Profondeur: "+ (G.Fermes.size()+G.Ouverts.size()) +" états parcourus");

//        debut = System.currentTimeMillis();
//        G = new Graphe(EtatFinal,"l","none");
//        G.resoudre();
//        fin = System.currentTimeMillis();
//        tempsLargeur=fin-debut;
////        System.out.println("L: "+tempsLargeur);
////        System.out.println("Largeur: "+ (G.Fermes.size()+G.Ouverts.size()) +" états parcourus");
//
//        debut = System.currentTimeMillis();
//        G = new Graphe(EtatFinal,"h","basique");
//        G.resoudre();
//        fin = System.currentTimeMillis();
//        tempsBasique=fin-debut;
////        System.out.println("basique: "+tempsBasique);
////        System.out.println("Basique: "+ (G.Fermes.size()+G.Ouverts.size()) +" états parcourus");
//
//        debut = System.currentTimeMillis();
//        G = new Graphe(EtatFinal,"h","OperationsVariees");
//        G.resoudre();
//        fin = System.currentTimeMillis();
//        tempsOperationsVariees=fin-debut;
////        System.out.println("OpVar: "+tempsOperationsVariees);
////        System.out.println("OpsVar: "+ (G.Fermes.size()+G.Ouverts.size()) +" états parcourus");
////
//        debut = System.currentTimeMillis();
//        G = new Graphe(EtatFinal,"h","DistParSeau");
//        possible = G.resoudre();
//        fin = System.currentTimeMillis();
//        tempsDistCarree=fin-debut;
////        System.out.println("DistParSeau: "+tempsDistParSeauCompensation);
////        System.out.println("DistCarree: "+ (G.Fermes.size()+G.Ouverts.size()) +" états parcourus");

        if(possible){
         solvable="solvable";
        }
        else{
            solvable="impossible";
        }

        String[] res = new String[] {nom, solvable, Long.toString(tempsProfondeur), Long.toString(tempsLargeur), Long.toString(tempsBasique), Long.toString(tempsOperationsVariees), Long.toString(tempsDistCarree)};
        return(res);
        //return (nom +" -> "+ solvable +" \n TEMPS(ms) : profondeur= "+tempsProfondeur+" , largeur= "+tempsLargeur+" , heuristique basique= "+tempsBasique+" , heuristique \"Opérations variées\"= "+tempsOperationsVariees+" , heuristique \"Distances carrées\"= "+tempsDistCarree);
    }
}
