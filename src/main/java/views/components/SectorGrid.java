package views.components;

import javafx.scene.layout.GridPane;

public class SectorGrid extends GridPane {
    public CheckboxGrid[] sectorGrid;

    public SectorGrid(int n) {
        int rows = (int) Math.ceil(n/1.0);

        System.out.println(rows);

        setHgap(30);
        setVgap(30);

        this.sectorGrid =new CheckboxGrid[n];

        int cont = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 1; j++) {
                this.sectorGrid[cont]=new CheckboxGrid(20,"Norte"+cont);
                add(this.sectorGrid[cont],j,i);
                cont++;
                if(cont==n){
                    break;
                }
            }
        }
    }
}
