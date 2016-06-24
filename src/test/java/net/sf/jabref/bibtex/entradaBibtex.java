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

import static org.junit.Assert.*;


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
    public void testLivroTodosCampos() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry();
        entry.setType("book");

        entry.setField("author", "João ");
        entry.setField("publisher", "XV");
        entry.setField("title", "Livro1");
        entry.setField("year", "1992");
        entry.setField("editor", "editora1");
        entry.setField("volume", "555");
        entry.setField("number", "25");
        entry.setField("series", "8");
        entry.setField("adress", "centro 22");
        entry.setField("edition", "5");
        entry.setField("month", "12");
        entry.setField("note", "teste");
        entry.setField("bibtexkey", "livro1");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

     // @formatter:off
        String esperado = Globals.NEWLINE + "@Book{livro1," + Globals.NEWLINE +
                "  title     = {Livro1}," + Globals.NEWLINE +
                "  publisher = {XV}," + Globals.NEWLINE +
                "  year      = {1992}," + Globals.NEWLINE +
                "  author    = {João}," + Globals.NEWLINE +
                "  editor    = {editora1}," + Globals.NEWLINE +
                "  volume    = {555}," + Globals.NEWLINE +
                "  number    = {25}," + Globals.NEWLINE +
                "  series    = {8}," + Globals.NEWLINE +
                "  edition   = {5}," + Globals.NEWLINE +
                "  month     = {12}," + Globals.NEWLINE +
                "  note      = {teste}," + Globals.NEWLINE +
                "  adress    = {centro 22}," + Globals.NEWLINE +

                "}" + Globals.NEWLINE;
      // @formatter:on
        assertEquals(esperado, atual);

    }

    @Test
    public void testLivroTodosCamposValorLimiteAno() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry();
        entry.setType("book");

        entry.setField("author", "João ");
        entry.setField("publisher", "XV");
        entry.setField("title", "Livro1");
        entry.setField("year", "2017");
        entry.setField("editor", "editora1");
        entry.setField("volume", "555");
        entry.setField("number", "25");
        entry.setField("series", "8");
        entry.setField("adress", "centro 22");
        entry.setField("edition", "5");
        entry.setField("month", "12");
        entry.setField("note", "teste");
        entry.setField("bibtexkey", "livro1");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

     // @formatter:off
        String esperado = Globals.NEWLINE + "@Book{livro1," + Globals.NEWLINE +
                "  title     = {Livro1}," + Globals.NEWLINE +
                "  publisher = {XV}," + Globals.NEWLINE +
                "  year      = {2016}," + Globals.NEWLINE +
                "  author    = {João}," + Globals.NEWLINE +
                "  editor    = {editora1}," + Globals.NEWLINE +
                "  volume    = {555}," + Globals.NEWLINE +
                "  number    = {25}," + Globals.NEWLINE +
                "  series    = {8}," + Globals.NEWLINE +
                "  edition   = {5}," + Globals.NEWLINE +
                "  month     = {12}," + Globals.NEWLINE +
                "  note      = {teste}," + Globals.NEWLINE +
                "  adress    = {centro 22}," + Globals.NEWLINE +

                "}" + Globals.NEWLINE;
      // @formatter:on
        assertTrue(entry.AnoValido(entry.getField("year")));

    }

    @Test
    public void testLivroTodosCamposValorLimiteBibtex() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry();
        entry.setType("book");

        entry.setField("author", "João ");
        entry.setField("publisher", "XV");
        entry.setField("title", "Livro1");
        entry.setField("year", "2010");
        entry.setField("editor", "editora1");
        entry.setField("volume", "555");
        entry.setField("number", "25");
        entry.setField("series", "8");
        entry.setField("adress", "centro 22");
        entry.setField("edition", "5");
        entry.setField("month", "12");
        entry.setField("note", "teste");
        entry.setField("bibtexkey", "a");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

     // @formatter:off
        String esperado = Globals.NEWLINE + "@Book{a," + Globals.NEWLINE +
                "  title     = {Livro1}," + Globals.NEWLINE +
                "  publisher = {XV}," + Globals.NEWLINE +
                "  year      = {2010}," + Globals.NEWLINE +
                "  author    = {João}," + Globals.NEWLINE +
                "  editor    = {editora1}," + Globals.NEWLINE +
                "  volume    = {555}," + Globals.NEWLINE +
                "  number    = {25}," + Globals.NEWLINE +
                "  series    = {8}," + Globals.NEWLINE +
                "  edition   = {5}," + Globals.NEWLINE +
                "  month     = {12}," + Globals.NEWLINE +
                "  note      = {teste}," + Globals.NEWLINE +
                "  adress    = {centro 22}," + Globals.NEWLINE +

                "}" + Globals.NEWLINE;
      // @formatter:on
        assertNotSame(entry.getField("bibtexkey"), "a");

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
    public void testArtigoTodosCampos() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry("1", "article");
        entry.setField("author", "joão");
        entry.setField("title", "teste");
        entry.setField("journal", "AS");
        entry.setField("year", "1999");
        entry.setField("volume", "12");
        entry.setField("number", "455");
        entry.setField("pages", "255");
        entry.setField("month", "feb");
        entry.setField("note", "teste");
        //entry.setField("journal", "teste");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

        String esperado = Globals.NEWLINE + "@Article{," + Globals.NEWLINE +
                "  author  = {joão}," + Globals.NEWLINE +
                "  title   = {teste}," + Globals.NEWLINE+
                "  journal = {AS}," + Globals.NEWLINE+
                "  year    = {1999}," + Globals.NEWLINE+
                "  volume  = {12}," + Globals.NEWLINE+
                "  number  = {455}," + Globals.NEWLINE +
                "  pages   = {255}," + Globals.NEWLINE+
                "  month   = {feb}," + Globals.NEWLINE +
                "  note    = {teste}," + Globals.NEWLINE+

                "}" + Globals.NEWLINE;

        assertEquals(esperado, atual);

    }


    @Test
    public void caracteresEspeciais() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry("1", "article");
        entry.setField("author", "'-.@#'!%*©[]()_-+-");
        entry.setField("title", "'-.@#'!%*©[]()_-+-");
        //entry.setField("journal", "teste");

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

        String esperado = Globals.NEWLINE + "@Article{," + Globals.NEWLINE + "  author = {'-.@#'!%*©[]()_-+-},"
                + Globals.NEWLINE + "  title  = {'-.@#'!%*©[]()_-+-}," + Globals.NEWLINE +
                //"  journal = {teste}," + Globals.NEWLINE +
                "}" + Globals.NEWLINE;

        assertEquals(esperado, atual);

    }

    String cGrande = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
            + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
            + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    String nGrande = "9999999999999999999999999999999999999999999999999999999999999999999"
            + "99999999999999999999999999999999999999999999999999999999999999999999999999"
            + "99999999999999999999999999999999999999999999999999999999999999999999999999";
    @Test
    public void stringsGrandesArtigo() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry("1", "article");
        entry.setField("author", cGrande);
        entry.setField("title", cGrande);
        entry.setField("journal",cGrande);
        entry.setField("year", nGrande);
        entry.setField("volume", nGrande);
        entry.setField("number", nGrande);
        entry.setField("pages", nGrande);
        entry.setField("month", nGrande);
        entry.setField("note", cGrande);

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

        String esperado = Globals.NEWLINE + "@Article{," + Globals.NEWLINE +
                "  author  = {"+cGrande+"}," + Globals.NEWLINE+
                "  title   = {"+cGrande+"}," + Globals.NEWLINE+
                "  journal = {"+cGrande+"}," + Globals.NEWLINE+
                "  year    = {"+nGrande+"}," + Globals.NEWLINE+
                "  volume  = {"+nGrande+"}," + Globals.NEWLINE+
                "  number  = {"+nGrande+"}," + Globals.NEWLINE +
                "  pages   = {"+nGrande+"}," + Globals.NEWLINE+
                "  month   = {"+nGrande+"}," + Globals.NEWLINE +
                "  note    = {"+cGrande+"}," + Globals.NEWLINE+

                "}" + Globals.NEWLINE;

        assertTrue(entry.AnoValido(entry.getField("year")));

    }

    @Test
    public void stringsGrandesLivro() throws IOException {
        StringWriter stringWriter = new StringWriter();
        BibEntry entry = new BibEntry("1", "book");
        entry.setField("title", cGrande);
        entry.setField("publisher", cGrande);
        entry.setField("year",nGrande);
        entry.setField("author", cGrande);
        entry.setField("editor", cGrande);
        entry.setField("volume", nGrande);
        entry.setField("number", nGrande);
        entry.setField("series", nGrande);
        entry.setField("edition", nGrande);
        entry.setField("month", nGrande);
        entry.setField("note", cGrande);
        entry.setField("adress", cGrande);

        writer.write(entry, stringWriter, BibDatabaseMode.BIBTEX);

        String atual = stringWriter.toString();

        String esperado = Globals.NEWLINE + "@Book{," + Globals.NEWLINE +
                "  title     = {"+cGrande+"}," + Globals.NEWLINE+
                "  publisher = {"+cGrande+"}," + Globals.NEWLINE+
                "  year      = {"+nGrande+"}," + Globals.NEWLINE+
                "  author    = {"+cGrande+"}," + Globals.NEWLINE+
                "  editor    = {"+cGrande+"}," + Globals.NEWLINE+
                "  volume    = {"+nGrande+"}," + Globals.NEWLINE +
                "  number    = {"+nGrande+"}," + Globals.NEWLINE+
                "  series    = {"+nGrande+"}," + Globals.NEWLINE +
                "  edition   = {"+nGrande+"}," + Globals.NEWLINE+
                "  month     = {"+nGrande+"}," + Globals.NEWLINE+
                "  note      = {"+cGrande+"}," + Globals.NEWLINE+
                "  adress    = {"+cGrande+"}," + Globals.NEWLINE+

                "}" + Globals.NEWLINE;

        assertTrue(entry.AnoValido(entry.getField("year")));

    }




}