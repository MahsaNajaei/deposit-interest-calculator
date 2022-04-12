package ir.dotin.main.logic;

import ir.dotin.main.logic.Exceptions.IllegalInputException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InputDataObjectHandler {
    private List<InputDataObject> inputDataObjects;

    public InputDataObjectHandler() {
        inputDataObjects = new ArrayList<>();
    }

    public class InputDataObject {
        int customerNumber;
        String depositType;
        BigDecimal depositBalance;
        int durationInDays;

        public InputDataObject(int customerNumber, String depositType, BigDecimal depositBalance, int durationInDays) {
            this.customerNumber = customerNumber;
            this.depositType = depositType;
            this.depositBalance = depositBalance;
            this.durationInDays = durationInDays;
        }

        public int getCustomerNumber() {
            return customerNumber;
        }

        public String getDepositType() {
            return depositType;
        }

        public BigDecimal getDepositBalance() {
            return depositBalance;
        }

        public int getDurationInDays() {
            return durationInDays;
        }
    }

    public List<InputDataObject> extractInputObjectsFromInputNodes(NodeList inputNodes) {
        for (int i = 0; i < inputNodes.getLength(); i++) {
            if (Node.ELEMENT_NODE == inputNodes.item(i).getNodeType()) {
                Element inputElement = (Element) inputNodes.item(i);
                try {
                    int customerNumber = Integer.parseInt(getTextValueByTagName(inputElement, "customerNumber"));
                    String depositType = getTextValueByTagName(inputElement, "depositType");
                    BigDecimal depositBalance = new BigDecimal(getTextValueByTagName(inputElement, "depositBalance"));
                    int durationInDays = Integer.parseInt(getTextValueByTagName(inputElement, "durationInDays"));
                    checkInputValidation(depositType, depositBalance, durationInDays);
                    inputDataObjects.add(new InputDataObject(customerNumber, depositType, depositBalance, durationInDays));
                } catch (IllegalInputException e) {
                    e.printStackTrace();
                }

            }
        }
        return inputDataObjects;
    }

    private void checkInputValidation(String depositType, BigDecimal depositBalance, int durationInDays) throws IllegalInputException {
        if (!depositType.equalsIgnoreCase("ShortTerm") && !depositType.equalsIgnoreCase("LongTerm") && !depositType.equalsIgnoreCase("Qarz"))
            throw new IllegalInputException("Illegal depositType!");
        if (depositBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalInputException("Illegal depositBalance!");
        if (durationInDays <= 0)
            throw new IllegalInputException("Illegal time duration!");
    }

    private String getTextValueByTagName(Element inputElement, String tagName) throws IllegalInputException {
        if (inputElement.getElementsByTagName(tagName).getLength() == 0) {
            throw new IllegalInputException("<" + tagName + "> does not exist among input tags!");
        }
        String textContent = inputElement.getElementsByTagName(tagName).item(0).getTextContent();
        return textContent;
    }
}