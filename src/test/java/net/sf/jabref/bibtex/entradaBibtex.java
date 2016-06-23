package net.sf.jabref.bibtex;

import java.io.IOException;
import java.io.StringWriter;

import net.sf.jabref.Globals;
import net.sf.jabref.JabRefPreferences;
import net.sf.jabref.bibtex.BibEntryWriter;
import net.sf.jabref.exporter.LatexFieldFormatter;
import net.sf.jabref.model.database.BibDatabaseMode;
import net.sf.jabref.model.entry.BibEntry;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class entradaBibtex {

    private BibEntryWriter writer;
    private static JabRefPreferences backup;


    @BeforeClass
    public static void setUp() {
        Globals.prefs = JabRefPreferences.getInstance();
        backup = Globals.prefs;
    }

    @AfterClass
    public static void tearDown() {
        Globals.prefs.overwritePreferences(backup);
    }

    @Before
    public void setUpWriter() {
        writer = new BibEntryWriter(new LatexFieldFormatter(), true);
    }

    @Test
    public void testLivroSemCampos() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry("1", "book");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

        String esperado = Globals.NEWLINE + "@Book{," + Globals.NEWLINE +

                "}" + Globals.NEWLINE;

        assertEquals(esperado, atual);

    }

    @Test
    public void testArtigoSemCampos() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry("1", "article");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

        String esperado = Globals.NEWLINE + "@Article{," + Globals.NEWLINE +

                "}" + Globals.NEWLINE;

        assertEquals(esperado, atual);

    }

    @Test
    public void LivroCamposObrigatorios() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry("1", "book");
        entry.setField("author", "Vitor");
        entry.setField("title", "teste");
        //entry.setField("journal", "teste");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

        String esperado = Globals.NEWLINE + "@Book{," + Globals.NEWLINE + "  title = {teste}," + Globals.NEWLINE
                + "  author  = {Vitor}," + Globals.NEWLINE +
                //"  journal = {teste}," + Globals.NEWLINE +
                "}" + Globals.NEWLINE;

        assertEquals(esperado, atual);

    }

    @Test
    public void testBookEntradaBibtex() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry("1", "article");
        entry.setField("author", "Vitor");
        entry.setField("title", "teste");
        //entry.setField("journal", "teste");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

        String esperado = Globals.NEWLINE + "@Article{," + Globals.NEWLINE + "  author = {Vitor}," + Globals.NEWLINE
                + "  title  = {teste}," + Globals.NEWLINE +
                //"  journal = {teste}," + Globals.NEWLINE +
                "}" + Globals.NEWLINE;

        assertEquals(esperado, atual);

    }
}