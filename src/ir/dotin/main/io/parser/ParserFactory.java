package ir.dotin.main.io.parser;

public class ParserFactory {
    public Parser getParser(String parserName) {
        if (parserName != null && parserName.equalsIgnoreCase("xml"))
            return new XmlParser();
        return null;
    }
}
