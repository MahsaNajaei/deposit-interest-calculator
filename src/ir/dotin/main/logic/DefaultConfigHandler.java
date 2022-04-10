package ir.dotin.main.logic;

import ir.dotin.main.io.writer.FileWriter;
import ir.dotin.main.logic.entities.LongTerm;
import ir.dotin.main.logic.entities.Qarz;
import ir.dotin.main.logic.entities.ShortTerm;
import ir.dotin.main.io.parser.XmlParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DefaultConfigHandler implements ConfigHandler {

    public void readConfigs() throws IOException {
        Properties appProperties = new Properties();
        appProperties.load(new FileInputStream("src\\ir\\dotin\\conf.txt"));
        FileWriter.filePath = appProperties.getProperty("OutPutFilePath");
        XmlParser.filePath = appProperties.getProperty("InputFilePath");
        LongTerm.interestRate = Double.parseDouble(appProperties.getProperty("LongTermInterest"));
        ShortTerm.interestRate = Double.parseDouble(appProperties.getProperty("ShortTermInterest"));
        Qarz.interestRate = Double.parseDouble(appProperties.getProperty("QarzInterest"));
    }
}
