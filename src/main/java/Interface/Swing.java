package Interface;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing {
    //Funcoes para as telas SWING


    public void MenuOptions() {//O MAIS IMPORTANTE DOS FRAMES
        JMenu menu, submenu;
        JMenuItem i1, i2, i3, i4, i5; //Novo item
        JFrame f = new JFrame("Ant-Bolsonaro"); //Novo Frame
        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Menu");
        submenu = new JMenu("Sub Menu");
        i1 = new JMenuItem("Squad Registration");
        i2 = new JMenuItem("Item 2");
        i3 = new JMenuItem("Item 3");
        i4 = new JMenuItem("Item 4");
        i5 = new JMenuItem("Item 5");
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        submenu.add(i4);
        submenu.add(i5);
        menu.add(submenu);
        mb.add(menu);
        f.setJMenuBar(mb);
        f.setSize(600, 400);
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

    }


    public void registrationSquad() {
        JFrame f = new JFrame("Squad Registration"); //declara o frame
        JTextField t1 = new JTextField(""); //declaram o text
        JTextField t2 = new JTextField("");//text2
        JLabel l1 = new JLabel("Put the name of the squad:");
        JLabel l2 = new JLabel("Number of soldiers in squad");
        JButton b1 = new JButton("Confirm");


        //  x       y    width   height
        l1.setBounds(50, 70, 200, 30);//atributos label
        f.add(l1);
        t1.setBounds(50, 100, 200, 30); //atributos do text
        f.add(t1); //adiciona o text no frame

        l2.setBounds(300, 70, 200, 30);//atributos label
        f.add(l2);
        t2.setBounds(300, 100, 200, 30);//atributos do text
        f.add(t2);


        b1.setBounds(50, 200, 100, 50); //atributos do button
        f.add(b1);


        f.setSize(600, 300); //tamanho do frame
        f.setLayout(null);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }


}
