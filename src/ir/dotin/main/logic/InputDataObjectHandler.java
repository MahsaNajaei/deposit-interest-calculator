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
                int customerNumber = Integer.parseInt(inputElement.getElementsByTagName("customerNumber").item(0).getTextContent());
                String depositType = inputElement.getElementsByTagName("depositType").item(0).getTextContent();
                BigDecimal depositBalance = new BigDecimal(inputElement.getElementsByTagName("depositBalance").item(0).getTextContent());
                int durationInDays = Integer.parseInt(inputElement.getElementsByTagName("durationInDays").item(0).getTextContent());
                try {
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
        else if (depositBalance.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalInputException("Illegal depositBalance!");
        else if (durationInDays <= 0)
            throw new IllegalInputException("Illegal time duration!");
    }
}