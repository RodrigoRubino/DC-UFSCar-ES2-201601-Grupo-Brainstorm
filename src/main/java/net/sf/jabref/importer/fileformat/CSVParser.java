package net.sf.jabref.importer.fileformat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVParser {

    public static List<Map<String, String>> parseAllLines(List<String> allLines) {

        List<Map<String, String>> parsedFile = new ArrayList<>();
        List<String> primeiraLinha = new ArrayList<>();
        boolean isPrimeiraLinha = true;

        for (String linha : allLines) {
            String[] parseVirgula = linha.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            for (int i = 0; i < parseVirgula.length; i++) {
                parseVirgula[i] = parseVirgula[i].replace("\"", "");

            }

            if (isPrimeiraLinha) {
                for (int i = 0; i < parseVirgula.length; i++) {
                    primeiraLinha.add(parseVirgula[i]);
                    isPrimeiraLinha = false;
                }
            } else {
                Map<String, String> parsedLine = new HashMap<>();
                for (int i = 0; i < parseVirgula.length; i++) {
                    if (primeiraLinha.size() > i) {
                        parsedLine.put(primeiraLinha.get(i).toLowerCase(), parseVirgula[i]);

                    }
                }
                writeBibliographyTypeEnum(parsedLine);
                parsedFile.add(parsedLine);
            }


        }


        return parsedFile;

    }

    private static void writeBibliographyTypeEnum(Map<String, String> parsedLine) {
        String tipoNum = parsedLine.get("bibliographytype");
        parsedLine.remove("bibliographytype");

        switch (tipoNum) {
        case "1":
            parsedLine.put("bibliographytype", "book");
            break;
        case "2":
            parsedLine.put("bibliographytype", "booklet");
            break;
        case "5":
            parsedLine.put("bibliographytype", "inbook");
            break;
        case "7":
            parsedLine.put("bibliographytype", "article");
            break;
        case "8":
            parsedLine.put("bibliographytype", "manual");
            break;
        case "14":
            parsedLine.put("bibliographytype", "umpublished");
            break;
        default:
            parsedLine.put("bibliographytype", "other");

        }

    }

}
