/*  Copyright (C) 2003-2015 JabRef contributors.
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package net.sf.jabref.importer.fileformat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jabref.importer.ImportFormatReader;
import net.sf.jabref.importer.OutputPrinter;
import net.sf.jabref.model.entry.BibEntry;

/**
 * Autor: Rodrigo Rubino
 *Permite a importação de arquivos no formato CSV
 *
 */
public class CSVImporter extends ImportFormat {

    /**
     * Retorna o nome do formato
     */
    @Override
    public String getFormatName() {
        return "CSV";
    }

    /*
     *  (non-Javadoc)
     * @see net.sf.jabref.imports.ImportFormat#getCLIId()
     */
    @Override
    public String getCLIId() {
        return "CSV";
    }

    /**
     * Verifica se o destino esta no formato correto.
     */
    @Override
    public boolean isRecognizedFormat(InputStream in) throws IOException {
        return true;
    }

    /**
     * Realiza o parse de cada virgula e transforma em lista de BibEntry
     */
    @Override
    public List<BibEntry> importEntries(InputStream stream, OutputPrinter status) throws IOException {

        List<BibEntry> bibItems = new ArrayList<>();
        String line = "";
        Map<String, String> hm = new HashMap<>();
        List<String> allLines = new ArrayList<>();
        List<Map<String, String>> parsedFile = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(ImportFormatReader.getReaderDefaultEncoding(stream))) {
            while ((line = in.readLine()) != null) {
                if (line.isEmpty()) {
                    continue; // ignora linhas nulas
                }
                allLines.add(line);
            }

            parsedFile = CSVParser.parseAllLines(allLines);

            for (int i = 0; i < parsedFile.size(); i++) {
                BibEntry b = new BibEntry(DEFAULT_BIBTEXENTRY_ID, parsedFile.get(i).get("bibliographytype"));
                b.setField(parsedFile.get(i));
                b.setCiteKey(parsedFile.get(i).get("identifier"));

                bibItems.add(b);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bibItems;
    }

}
