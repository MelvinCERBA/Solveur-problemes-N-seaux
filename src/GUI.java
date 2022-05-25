import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GUI implements ActionListener{
    /**
     *  simple GUI to act as our Main class
     */


    private int count=0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton button;
    private JTextField userText;
    String[] nomsColonnes = {"INSTANCE","Solvabilité","Profondeur(ms)","Largeur(ms)", "Heur. basique(ms)", "Heur. opérations variées", "Heur. distances au carré"};
    //String[][] donnees = {{"","","","","","",""}};
    private DefaultTableModel model = new DefaultTableModel(nomsColonnes, 0);
    private JTable tableResultats = new JTable(model);
    JScrollPane scrollPane;

    public GUI(){
        frame = new JFrame();

        button = new JButton("Résoudre");
        //button.setSize(165,25);
        button.addActionListener(this);

        label = new JLabel("Fichier/Dossier (chemin depuis la racine) :");
        label.setBounds(10, 20, 80, 25);

        userText = new JTextField(1);
        userText.setBounds(10, 20, 165, 25);



        scrollPane = new JScrollPane(tableResultats);
        tableResultats.setFillsViewportHeight(true);

        panel = new JPanel();
        panel.setSize(70,50);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));

        panel.add(label);
        panel.add(userText);
        panel.add(button);
        panel.add(scrollPane);

        frame.add(panel, BorderLayout.CENTER);

        //frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = userText.getText();
        File fichier = new File(path);
        ProblemeSeaux pb;

        if(fichier.isDirectory()){
            System.out.println("Dossier !");
            File[] instances = fichier.listFiles();

            for(File f:instances){
                String chemin = f.getPath();
                pb = new ProblemeSeaux(chemin);

                String[] perfs = pb.tester();
                ajouterLigne(perfs);
                //model.addRow(perfs);
                System.out.println(perfs);
            }
        }
        else{
            System.out.println("Fichier !");
            pb = new ProblemeSeaux(path);
            String[] perfs = pb.tester();
            model.addRow(perfs);
            label.setText("SOLUTION : "+pb.solution().toString());
        }
    }

    public void ajouterLigne(String[] L){
        model.addRow(L);
    }
}
