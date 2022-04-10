package ir.dotin.main.io.parser;

import org.w3c.dom.NodeList;

public interface Parser {
   NodeList parseByTagName(String tagName);
}
