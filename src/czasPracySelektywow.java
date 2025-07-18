import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class czasPracySelektywow {
    public static void main(String[] args) {

        System.out.println("Wybierz numer selektywu (1, 2 lub 3):");
        Scanner scan = new Scanner(System.in);
        int ktorySelektyw;

        while (true) {
            ktorySelektyw = scan.nextInt();
            if (ktorySelektyw == 1 || ktorySelektyw == 2 || ktorySelektyw == 3) {
                break;
            } else {
                System.out.println("Nie ma takiego agregatu. Wybierz jeszcze raz (1, 2 lub 3):");
            }
        }
        String daneDoSelektywow = "";
        String daneDoSel1 = "Czas pracy - SEL001";
        String daneDoSel2 = "Czas pracy - SEL002";
        String daneDoSel3 = "Czas pracy - SEL003";
        final int[] totalSum = {0};

        switch (ktorySelektyw) {
            case 1:
                daneDoSelektywow = daneDoSel1;
                break;
            case 2:
                daneDoSelektywow = daneDoSel2;
                break;
            case 3:
                daneDoSelektywow = daneDoSel3;
                break;

        }

        try {
            Files.walk(Paths.get(daneDoSelektywow))
                    .filter(Files::isRegularFile).forEach(path -> {
                if (path.toString().toLowerCase().endsWith(".xml")) {
                    System.out.println("Przetwarzam plik: " + path.toAbsolutePath());
                    totalSum[0] += processXMLFile(path);
                }
            });

            System.out.println("Całkowity czas pracy SEL00" + ktorySelektyw + " : " + totalSum[0] + " sekund");

            double totalHours = totalSum[0] / 3600.0;
            double totalDays = totalHours / 24.0;

            System.out.printf("Całkowity czas pracy w godzinach: %.2f godzin%n", totalHours);
            System.out.printf("Całkowity czas pracy w dniach: %.2f dni%n", totalDays);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int processXMLFile(Path filePath) {
        int fileSum = 0;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(filePath.toFile());
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("LoetProtElement");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String name = element.getAttribute("LoetProtElementName");

                    if (name.contains("Czas w module - Moduł lutowania 1")) {
                        String istwert = element.getAttribute("LoetProtElementIstwert");

                        if (!istwert.isEmpty()) {
                            try {
                                int value = Integer.parseInt(istwert);
                                fileSum += value;
                            } catch (NumberFormatException e) {
                                System.err.println("Błąd formatu liczby: " + istwert);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Błąd podczas przetwarzania pliku: " + filePath.getFileName());
            e.printStackTrace();
        }
        return fileSum;
    }
}