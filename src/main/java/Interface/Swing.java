package Interface;

import informationAnalysis.Squad;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Swing{
    //Funcoes para as telas SWING

    public void menuOptions() {//MENU DE REGISTRO

        JMenu menu;
        JMenuItem i1, i2, i3, i4, i5; //Novo item
        JFrame f = new JFrame("Safe the Amazonia"); //Novo Frame

        f.getContentPane().setBackground(Color.DARK_GRAY); //background do Jframe f, usando definições tipo RGB
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Registration");
        i1 = new JMenuItem("Squad Registration");
        i2 = new JMenuItem("Region Registration");
        i3 = new JMenuItem("Item 3");
        i4 = new JMenuItem("Item 4");
        i5 = new JMenuItem("Item 5");
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        mb.add(menu);
        f.setJMenuBar(mb);
        f.setSize(600, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

        //ACOES DOS ITENS
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                registrationSquad();
            }
        });


        i2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                registrationRegion();
            }

        });
    }

    //SUB MENUS DO REGISTRATION
    public void registrationSquad() {
        JFrame f = new JFrame("Squad Registration"); //declara o frame
        f.getContentPane().setBackground(Color.DARK_GRAY);//cor do background da janela
        final JTextField t1 = new JTextField(""); //declaram o text

        final JTextField t2 = new JTextField("");//text2
        JLabel l1 = new JLabel("Put the name of the squad:");
        l1.setForeground(Color.white);//cor do texto
        JLabel l2 = new JLabel("Number of soldiers in squad:");
        l2.setForeground(Color.white);

        JLabel l3 = new JLabel("Select responsible region:");
        l3.setForeground(Color.white);

        JButton b1 = new JButton("Confirm");
        String country[] = {"Select","North", "South", "East", "West"};
        final JComboBox cb = new JComboBox(country);


        //DEFINIÇÕES TAMANHO + POSICAO TELA

                    //  x       y    width   height
        l1.setBounds(50, 70, 200, 30);//atributos label
        f.add(l1);
        t1.setBounds(50, 100, 200, 30); //atributos do text
        f.add(t1); //adiciona o text no frame

        l2.setBounds(300, 70, 200, 30);//atributos label
        f.add(l2);
        t2.setBounds(300, 100, 200, 30);//atributos do text
        f.add(t2);

        l3.setBounds(300,190,200,30);
        f.add(l3);
        cb.setBounds(300, 220, 100, 30);//atributos do checkbox
        f.add(cb);
        b1.setBounds(50, 280, 100, 50); //atributos do button
        f.add(b1);

        //ACOES:
        //ENVIANDO OS VALORES DIGITADOS NA GUI PARA A CLASSE
        b1.addActionListener(new ActionListener() { //ACAO DO BUTTON
            public void actionPerformed(ActionEvent actionEvent) {
            Squad squadButton= new Squad();//INSTANCIA PARA ENVIAR OS DADOS QUANDO CLICA NO BOTAO
            squadButton.setName(t1.getText());
            int qntdSold= Integer.parseInt(t2.getText());//CONVERTENDO A STRING PARA INT
            squadButton.setQuantityOfSoldiers(qntdSold);
            String specSold= (String) cb.getSelectedItem();
            squadButton.setSpecialty(specSold);



                JOptionPane.showMessageDialog(null, "\nNome:"+squadButton.getName()+"\n"+
                        "Quantidade de Soldados:"+squadButton.getQuantityOfSoldiers()+"\nRegiao:"+squadButton.getSpecialty());
            }
        });



        f.setSize(600, 400); //tamanho do frame
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }

    public void registrationRegion() {
        JFrame f = new JFrame("Region Registration"); //declara o frame
        f.getContentPane().setBackground(Color.DARK_GRAY);
        JLabel l1 = new JLabel("Select region:");
        l1.setForeground(Color.white);
        JLabel l2 = new JLabel("Protected area:");
        l2.setForeground(Color.white);
        JButton b1 = new JButton("Confirm");
        String country[] = {"North", "South", "East", "West"};

        JComboBox cb = new JComboBox(country);
        JRadioButton r1 = new JRadioButton("A) Yes");
        JRadioButton r2 = new JRadioButton("B) No");


        //  x       y    width   height
        l1.setBounds(50, 70, 200, 30);//atributos label
        f.add(l1);
        cb.setBounds(50, 100, 200, 30);//atributos do checkbox
        f.add(cb);

        l2.setBounds(300, 60, 200, 30);//atributos label
        f.add(l2);

        r1.setBounds(300, 90, 200, 30);//atributos do radioButton
        r2.setBounds(300, 110, 200, 30);
        f.add(r1);
        f.add(r2);


        b1.setBounds(50, 200, 100, 50); //atributos do button
        f.add(b1);


        f.setSize(600, 400); //tamanho do frame
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }


}
