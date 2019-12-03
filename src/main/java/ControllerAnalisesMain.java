import DAO.ImageDAO;
import Exceptions.conexaoError;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Image;
import models.Region;
import tools.Firebase;

import javax.swing.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static analysis.Main.firebase;
import static com.itextpdf.text.pdf.BaseFont.TIMES_BOLD;
import static com.itextpdf.text.pdf.BaseFont.TIMES_ROMAN;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;

public class ControllerAnalisesMain extends ControllerUtil {


    /**
     * Carrega a tela do gerir analysis.Main
     */
    @FXML
    private void telaGerirMain() {
        loadUI("gerirMain");
    }

    private double averagePerRegion(List<Image> listaImage, int id) throws FileNotFoundException {
        double img1=0,img2=0,img3=0;
        boolean has1=false,has2=false,has3=false;
        int i = listaImage.size();
        int count = 1;
        if(listaImage.size()>0){
            while (i>listaImage.size()-3){
                Image el = listaImage.get(0);
                if(el.getRegionId()==id){
                    tools.Image image = new tools.Image(5,el.getScale());
                    image.lerImagem(el.getFileName(),el.getScale(),el.getRegionId(),el.getGeneratedTime());
                    if(count==1){
                        has1=true;
                        img1 = image.getIntensitiesAverage();
                    }
                    if(count==2){
                        has2=true;
                        img2 = image.getIntensitiesAverage();
                    }
                    if(count==3){
                        has3=true;
                        img3 = image.getIntensitiesAverage();
                    }
                }
                i--;
                if(i<0){
                    break;
                }
                count++;
            }
        }
        float avg =0;
        int div=0;
        if(has1){
            avg+=img1;
            div++;
        }
        if(has2){
            avg+=img2;
            div++;
        }
        if(has3){
            avg+=img3;
            div++;
        }
        return avg/div;
    }


    /**
     * Calcula como estão as queimadas pelas regiões nas fotos, e gera um relatório com os resultados
     *
     * @throws IOException
     * @throws GeneralSecurityException
     */
    @FXML
    private void gerarRelatorio() throws IOException, GeneralSecurityException {

        Document document = new Document();
        try {
            String filePath = System.getProperty("user.dir") +
                    File.separator +
                    "data" +
                    File.separator
                    +"relatorios";

            File dirDataFile = new File(filePath);

            if (!dirDataFile.exists()) {
                dirDataFile.mkdirs();
            }
            //adicionar metadados ao pdf
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = sdf.format(date);
            String title = "Relatorio de queimadas "+dataFormatada;

            document.addSubject(title);
            document.addKeywords("queimadas, amazônia");
            document.addCreator("POO Amazonia Analysis");
            document.addAuthor("Arildo Magno e Gabriel Rodrigues");

            int time = (int) (new Date().getTime()/1000);
            String fileName = "Relatorio_"+Integer.toString(time)+".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath + File.separator + fileName));
            document.open();

            document.addTitle("Relatório Queimadas");
            //criando fonte para titulo
            Font titleFont = new Font();
            titleFont.setFamily(TIMES_BOLD);
            titleFont.setSize(18);
            //criando fonte para paragrafos
            Font font = new Font();
            font.setFamily(TIMES_ROMAN);
            font.setSize(12);
            // inicio do conteudo do pdf
            Paragraph paragraphTitle = new Paragraph("");
            Paragraph paragraph = new Paragraph("");
            //configurando fonte dos paragrafos de titulo
            paragraphTitle.setAlignment(Paragraph.ALIGN_CENTER);
            paragraphTitle.setFont(titleFont);
            paragraphTitle.add(title);
            //adicionando titulo principal
            document.add(paragraphTitle);

            //adiciona titulo das regiões
            paragraphTitle.setAlignment(Paragraph.ALIGN_LEFT);
            paragraphTitle.clear();
            titleFont.setSize(14);
            paragraphTitle.add("1.REGIOES");
            document.add(paragraphTitle);


            //configura para listar as regiões
            paragraph.setAlignment(Paragraph.ALIGN_LEFT);
            paragraph.setFont(font);

            List<Region> regions = new ArrayList<>();

            List<QueryDocumentSnapshot> documents = firebase.read(0);
            for (DocumentSnapshot documentReaded : documents) {
                Region region = new Region();
                region = (documentReaded.toObject(Region.class));
                region.setId(Integer.parseInt(documentReaded.getId()));
                regions.add(region);
            }

            List<Image> listaImage;
            listaImage = ImageDAO.getImages();
            int countEnvironmentalProtection=0;
            int countUrbanRegion=0;
            int sessionCount = 1;
            for (Region region:regions) {
                if(!region.getEnvironmentalProtection().equals("Nao e uma area de Protecao Ambiental"))
                    countEnvironmentalProtection++;
                if(!region.getUrbanRegion().equals("Nao e uma regiao Urbana"))
                    countUrbanRegion++;
                paragraphTitle.clear();
                paragraphTitle.setIndentationLeft(20);
                paragraphTitle.add("1."+sessionCount+".Regiao "+region.getName());
                document.add(paragraphTitle);
                paragraph.setIndentationLeft(45);
                paragraph.clear();
                paragraph.add("ID: "+region.getSquadResponsable());
                document.add(paragraph);
                paragraph.clear();
                paragraph.add("Nome da Regiao: "+region.getName());
                document.add(paragraph);
                paragraph.clear();
                paragraph.add("ID do Esquadrao: "+region.getSquadResponsable());
                document.add(paragraph);
                paragraph.clear();
                paragraph.add("Protecao Ambiental: "+region.getEnvironmentalProtection());
                document.add(paragraph);
                paragraph.clear();
                paragraph.add("Regiao Urbana: "+region.getUrbanRegion());
                document.add(paragraph);
                double average = this.averagePerRegion(listaImage,region.getId())*100;
                DecimalFormat df = new DecimalFormat("0.00");
                paragraph.clear();
                paragraph.add("Aumento das queimadas: "+df.format(average)+"%");
                document.add(paragraph);
            }
            titleFont.setSize(14);
            paragraphTitle.clear();
            paragraphTitle.setIndentationLeft(0);
            paragraphTitle.add("2.ESTATISTICAS");
            paragraph.clear();
            paragraph.setIndentationLeft(20);
            paragraph.add("Regiões de Proteção Ambiental: "+countEnvironmentalProtection+"\n");
            paragraph.add("Regiões de area Urbana: "+countUrbanRegion);
            document.add(paragraphTitle);
            document.add(paragraph);
            document.close();
            JOptionPane.showMessageDialog(null, "Relatorio Gerado com Sucesso!");
        }catch (DocumentException | IOException | ExecutionException | InterruptedException | conexaoError e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
