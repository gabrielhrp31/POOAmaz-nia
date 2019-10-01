package views.components;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class CheckboxGrid extends GridPane {
    public CheckBox[][] cbGrid;

    public CheckboxGrid(int n, String name){
        cbGrid = new CheckBox[n][n];

        //criando e adicionando titulo
        Text title = new Text(name);
        title.setTextAlignment(TextAlignment.CENTER);
        add(title,0,0,5,1);

        for (int i = 0; i < cbGrid.length; i++) {
            for (int j = 1; j < cbGrid.length+1; j++) {
                cbGrid[i][j-1] =  new CheckBox();
                cbGrid[i][j-1].setSelected(true);
                add(cbGrid[i][j-1],i,j);
            }
        }
    }

}
