--------------------------------
    Présentation
--------------------------------
Ce programme est un solveur de problèmes à N seaux qui utilise des graphes d'états et diffférents algorithmes d'exploration.
Il prend en entrée un fichier .txt représentant un problème à N seaux de capacités maximales S1, S2, ..., SN et de contenu final
F1, F2, ..., FN selon le format suivant :

N                  <- nombre de seaux
S1 S2 ... SN       <- capacité max de chaque seau
F1 F2 ... FN       <- contenu souhaité dans chaque seau

exemple:
"2
3 5
0 4"


Le programme permet de savoir si un problème est solvable. Si tel est le cas, il retourne un solution.
Afin de comparer les performances de différentes heuristiques, le programme affiche le temps d'exploration
pour chaque algorithme d'exploration implémenté.

ATTENTION : Le programme a une complexité exponentielle. Les problèmes à 4 seaux ou plus sont très longs à résoudre.
Un rapport détaillé des performances du programme sur une sélection variée de problème est disponible dans ce dossier
(PbSeaux_Rapport_FalilouBOP_MelvinCERBA.pdf)

--------------------------------
    Le problème des seaux
--------------------------------

--- Caractérisation ---
Un problème des seaux est caractérisé par :
- Le nombre de seaux
- La capacité maximale de chaque seau
- La quantité souhaitée pour chaque seau
(la quantité initiale est de 0L dans chaque seau)

exemple:
- Vous avez 2 seaux
- Le seau 1 (S1) peut contenir 3L et le seau 2 (S2) peut contenir 5L
- L'objectif est d'avoir 0L dans le premier seau et 4L dans le second

--- Regles ---
A chaque instant, vous pouvez :
- Remplir un seau à sa capacité maximale
- Vider totalement un seau
- Transvaser le contenu d'un seau dans un autre (jusqu'à ce que le premier soit vide ou que le second soit plein)

exemple:
Dans le problème à 2 seaux (3 et 5 litres), on peut :
Remplir S1 (3L,0L) -> Transvaser S1 dans S2 (0L,3L) -> Remplir S1 (3L,3L)
-> Transvaser S1 dans S2 (1L,5L) -> Vider S2 (1L,0L) -> Transvaser S1 dans S2 (0L,1L)
Remplir S1 (3L,1L) -> Transvaser S1 dans S2 (0L,4L) = Objectif atteint


--------------------------------
    Mode d'emploi
--------------------------------
1) Lancer le programme

2) Entrer dans le champ prévu à cet effet le chemin depuis la racine d’un fichier représentant une instance du problème
   des seaux, ou d’un dossier contenant uniquement de tels fichiers.

3) Cliquer sur « résoudre ».

4) Après quelques secondes, les résultats apparaissent dans le tableau. Si vous avez analysé un fichier seul, une
   solution apparait en haut de la page. Vous pouvez alors recommencer.


