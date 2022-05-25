import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Etat {
    /**
     * Represents a "state" of the problem at a given moment ( a node of the graph )
     */
    int[] capacite_Seaux;           // maximum capacity of each bucket
    int[] seaux;                    // current content of each bucket

    LinkedList<String> chemin;      // path, set of rules that led to this "Etat"

    int scoreHeuristique;           // evaluation of the proximity of this "Etat" to the solution
    int nbrT=0;                     // number of times the rule "transvaser" has been used to procduce this state               // Depending on the heuristique chosen, theses
    int nbrRV=0;                    // number of times the rules "vider" or "remplir" have been used to procduce this state     // are used to calculate the scoreHeuristique

    public Etat(int[] s, int[] c){
        seaux               = s;
        capacite_Seaux      = c;
        chemin              = new LinkedList<>();
    }

    public Etat(int[] s, int[] c, LinkedList<String> chem){
        seaux               = s;
        capacite_Seaux      = c;
        chemin              = chem;
    }

    public boolean estEgal(Etat e){
        /**
         * Checks whether this object is "equal" to e, in that the content of their buckets is similar
         */
        for(int i = 0; i < seaux.length; i++){
            if (seaux[i] != e.seaux[i]){
                return false;
            }
        }
        return true;
    }

    public void print(){
        /**
         * Displays the content of each bucket
         */
        System.out.println(Arrays.toString(seaux));
    }


    // PRODUCTION RULES //


    public Etat remplir(int seau){
        /**
         * Returns the "Etat" object where the selected bucket has been filled
         */

        // Copy the path (succession of rules) that led to the previous "Etat"
        LinkedList<String> new_chemin   = new LinkedList<>();
        new_chemin.addAll(chemin);

        // Copy the buckets content from the previous "Etat"
        int[] new_seaux                 = new int[seaux.length];
        System.arraycopy(seaux, 0, new_seaux, 0, seaux.length);

        // Fill the selected bucket
        new_seaux[seau]                 = capacite_Seaux[seau];

        // Add the rule "remplir" to the new "Etat"'s path
        new_chemin.add("R[" + capacite_Seaux[seau] + "]");

        // Increment the number of "remplir" and "vider" rules used
        nbrRV += 1;

        //System.out.println("R["+capacite_Seaux[seau]+"]");
        return new Etat(new_seaux, capacite_Seaux, new_chemin);
    }


    public Etat vider(int seau){
        /**
         * Returns the "Etat" object where the selected bucket has been emptied
         */
        // Copy the path (succession of rules) that led to the previous "Etat"
        LinkedList<String> new_chemin   = new LinkedList<>();
        new_chemin.addAll(chemin);

        // Copy the buckets content from the previous "Etat"
        int[] new_seaux                 = new int[seaux.length];
        System.arraycopy(seaux, 0, new_seaux, 0, seaux.length);

        // Empty the selected bucket
        new_seaux[seau]                 = 0;

        // Add the "vider" rule to the path of the new "Etat"
        new_chemin.add("V[" + capacite_Seaux[seau] + "]");

        // Increment the number of "remplir" and "vider" rules used
        nbrRV+=1;

        //System.out.println("V["+capacite_Seaux[seau]+"]");
        return new Etat(new_seaux, capacite_Seaux, new_chemin);
    }


    public Etat transvaser(int s1, int s2){
        /**
         * Returns the "Etat" object where the first selected bucket has been emptied into the second (until the second is full)
         */

        // Copy the path (succession of rules) that led to the previous "Etat"
        LinkedList<String> new_chemin   = new LinkedList<>();
        new_chemin.addAll(chemin);

        // Copy the buckets content from the previous "Etat"
        int[] new_seaux                 = new int[seaux.length];
        System.arraycopy(seaux, 0, new_seaux, 0, seaux.length);

        // If the receiving bucket is full before the giving bucket is empty, we stop
        int total                       = seaux[s1]+seaux[s2];
        if (total > capacite_Seaux[s2]) {
            new_seaux[s1]               = total-capacite_Seaux[s2];
            new_seaux[s2]               = capacite_Seaux[s2];
        }
        else {
            new_seaux[s2]               += new_seaux[s1];
            new_seaux[s1]               = 0;
        }

        // Add the rule "transvaser" to the new "Etat"'s path
        new_chemin.add("T[" + capacite_Seaux[s1] + "," + capacite_Seaux[s2] + "]");

        // Increment the number of "transvaser" rules used
        nbrT                            += 1;

        //System.out.println("T["+capacite_Seaux[s1]+","+capacite_Seaux[s2]+"]");
        return new Etat(new_seaux, capacite_Seaux, new_chemin);
    }


    // UTILITARY FUNCTIONS //


    public ArrayList<Integer> seaux_nonPleins(){
        /**
         * Returns the array of every bucket that is not full
         */
        ArrayList<Integer> s_nP = new ArrayList<>();
        for(int i = 0; i < seaux.length; i++){
            if(seaux[i] != capacite_Seaux[i]) {s_nP.add(i);}
        }
        return s_nP;
    }


    public ArrayList<Integer> seaux_nonVides(){
        /**
         * Returns the array of every bucket that is not empty
         */

        ArrayList<Integer> s_nV = new ArrayList<>();
        for(int i = 0; i < seaux.length; i++){
            if(seaux[i] != 0) {s_nV.add(i);}
        }
        return s_nV;
    }
}

