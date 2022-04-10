package ir.dotin.main.io.parser;

import ir.dotin.main.logic.Exceptions.IllegalInputException;
import org.w3c.dom.NodeList;

public interface Parser {
   NodeList parseByTagName(String tagName) throws IllegalInputException;
}
