import javafx.fxml.FXML;
import tools.Firebase;

import javax.swing.*;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ControllerAnalisesMain extends ControllerUtil {

    public static Firebase firebase;

    static {
        try {
            firebase = new Firebase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Carrega a tela do gerir analysis.Main
     */
    @FXML
    private void telaGerirMain() {
        loadUI("gerirMain");
    }



    /**
     * Calcula como estão as queimadas pelas regiões nas fotos, e gera um relatório com os resultados
     *
     * @throws IOException
     * @throws GeneralSecurityException
     */
    @FXML
    private void gerarRelatorio() throws IOException, GeneralSecurityException {
 JOptionPane.showMessageDialog(null,"FALTA UPAR AS FOTO PÔ");
        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmacao");
        alert.setHeaderText("Tem certeza que deseja gerar o relatorio?");
        //alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            File fileSquad = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "squad.json");
            File fileRegion = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "region.json");


            if (fileSquad.exists() && fileRegion.exists()) {
                GoogleDrive gd = new GoogleDrive();
                File dir = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator + "pictures.json");

                tools.Image image = new tools.Image();
                if (dir.exists()) {
                    dir.delete();
                }
                try {
                    gd.downloadFile("application/json", "pictures.json", System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator);
                } catch (IOException e) {
                    return;
                }

                Reader reader = new FileReader(dir);
                Type listType = new TypeToken<ArrayList<Image>>() {
                }.getType();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                List<Image> listaImage;
                listaImage = gson.fromJson(reader, listType);
                List<Double> id1 = new ArrayList<>();
                List<Double> id2 = new ArrayList<>();
                List<Double> id3 = new ArrayList<>();
                List<Double> id4 = new ArrayList<>();
                String auxS1 = "", auxS2 = "", auxS3 = "", auxS4 = "", auxS = "";
                int controle1 = 0, controle2 = 0, controle3 = 0, controle4 = 0;
                for (Image el : listaImage) {
                    gd.downloadFileById("image/jpeg", el.getFileId(), System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator, el.getFileName());
                    if (el.getRegionId() == 1) {
                        if (id1.size() >= 3) {
                            id1.remove(controle1);
                            controle1++;
                            if (controle1 >= 3) {
                                controle1 = 0;
                            }
                        }
                        image.lerImagem(el.getFileName(), el.getScale(), el.getRegionId(), el.getGeneratedTime(), id1);
                    }
                    if (el.getRegionId() == 2) {

                        if (id2.size() >= 3) {
                            id2.remove(controle2);
                            controle2++;
                            if (controle2 >= 3) {
                                controle2 = 0;
                            }
                        }
                        image.lerImagem(el.getFileName(), el.getScale(), el.getRegionId(), el.getGeneratedTime(), id2);

                    }
                    if (el.getRegionId() == 3) {
                        if (id3.size() >= 3) {
                            id3.remove(controle3);
                            controle3++;
                            if (controle3 >= 3) {
                                controle3 = 0;
                            }
                        }
                        image.lerImagem(el.getFileName(), el.getScale(), el.getRegionId(), el.getGeneratedTime(), id3);
                    }
                    if (el.getRegionId() == 4) {
                        if (id4.size() >= 3) {
                            id4.remove(controle4);
                            controle4++;
                            if (controle4 >= 3) {
                                controle4 = 0;
                            }
                        }
                        image.lerImagem(el.getFileName(), el.getScale(), el.getRegionId(), el.getGeneratedTime(), id4);
                    }

                }
                if (id1.size() > 0) {
                    double aux1 = 0.0;
                    aux1 = id1.get(id1.size() - 1) - id1.get(0);
                    aux1 = (aux1 / id1.get(0)) * 100;
                    aux1 = Math.round(aux1);
                    if (aux1 > id1.get(0)) {
                        auxS1 = "\tA regiao de ID1 -> Aumentando as queimadas em : " + aux1 + "% ";
                    } else {
                        auxS1 = "\tA regiao de ID1 -> Diminuindo as queimadas em : " + aux1 + "% ";
                    }
                    auxS = auxS.concat(auxS1);
                }


                if (id2.size() > 0) {
                    double aux2 = 0.0;
                    aux2 = id2.get(id2.size() - 1) - id2.get(0);
                    aux2 = (aux2 / id2.get(0)) * 100;
                    aux2 = Math.round(aux2);
                    if (aux2 > id2.get(0)) {
                        auxS2 = "\tA regiao de ID2 -> Aumentando as queimadas em : " + aux2 + "% ";
                    } else {
                        auxS2 = "\tA regiao de ID2 -> Diminuindo as queimadas em : " + aux2 + "% ";
                    }
                    auxS = auxS.concat(auxS2);

                }

                if (id3.size() > 0) {
                    double aux3 = 0.0;
                    aux3 = id3.get(id3.size() - 1) - id3.get(0);
                    aux3 = (aux3 / id3.get(0)) * 100;
                    aux3 = Math.round(aux3);
                    if (aux3 > id3.get(0)) {
                        auxS3 = "\tA regiao de ID3 -> Aumentando as queimadas em : " + aux3 + "% ";
                    } else {
                        auxS3 = "\tA regiao de ID3 -> Diminuindo as queimadas em : " + aux3 + "% ";
                    }
                    auxS = auxS.concat(auxS3);

                }

                if (id4.size() > 0) {
                    double aux4 = 0.0;
                    aux4 = id4.get(id4.size() - 1) - id4.get(0);
                    aux4 = (aux4 / id4.get(0)) * 100;
                    aux4 = Math.round(aux4);
                    if (aux4 > id4.get(0)) {
                        auxS4 = "\tA regiao de ID4 -> Aumentando as queimadas em : " + aux4 + "% ";
                    } else {
                        auxS4 = "\tA regiao de ID4 -> Diminuindo as queimadas em : " + aux4 + "% ";
                    }
                    auxS = auxS.concat(auxS4);

                }


                File dirDataFile = new File(System.getProperty("user.dir") + File.separator + "data");

                if (dirDataFile.exists()) {//SE A PASTA EXISTIR
                    File dirSave = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator + "relatorio.txt");
                    FileWriter salvaArq = new FileWriter(dirSave);
                    salvaArq.write(auxS);
                    salvaArq.close();
                } else {
                    dirDataFile.mkdir();
                    File dirSave = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "relatorio" + File.separator + "relatorio.txt");
                    FileWriter salvaArq = new FileWriter(dirSave);
                    salvaArq.write(auxS);
                    salvaArq.close();
                    JOptionPane.showMessageDialog(null, "Acao Concluida", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    java.awt.Desktop.getDesktop().edit(dirSave);


                }

            } else {
                JOptionPane.showMessageDialog(null, "Primeiro realize os Cadastros");

            }


        } else {
            return;
        }


    }
    */
    }
}
