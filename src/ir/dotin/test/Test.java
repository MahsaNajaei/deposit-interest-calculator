package ir.dotin.test;

import ir.dotin.main.io.writer.WriterFactory;
import ir.dotin.main.logic.*;

import ir.dotin.main.logic.Exceptions.IllegalInputException;
import ir.dotin.main.logic.InputDataObjectHandler;
import ir.dotin.main.io.parser.ParserFactory;
import ir.dotin.main.logic.Sorter;
import ir.dotin.main.logic.entities.CustomerDeposit;
import org.w3c.dom.NodeList;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {

        new DefaultConfigHandler().readConfigs();

        try {
            NodeList inputEntityNodes = readInputs();
            List<InputDataObjectHandler.InputDataObject> inputs = extractInputObjects(inputEntityNodes);

            Map<Integer, BigDecimal> paidInterestPerCustomer = new HashMap<>();
            for (InputDataObjectHandler.InputDataObject inputObject : inputs) {
                CustomerDeposit customerDeposit = reflectCustomerDepositObject(inputObject);
                BigDecimal payedInterest = calculateInterest(customerDeposit, inputObject.getDurationInDays());
                paidInterestPerCustomer.put(customerDeposit.getBankCustomer().getCustomerNumber(), payedInterest);
            }
            Map<Integer, BigDecimal> sortedPaidInterestPerCustomer = Sorter.sortMapByValue(paidInterestPerCustomer);
            writeOutput(sortedPaidInterestPerCustomer);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static NodeList readInputs() throws IllegalInputException {
        NodeList inputEntityNodes = new ParserFactory().getParser("xml").parseByTagName("deposit");
        return inputEntityNodes;
    }

    private static List<InputDataObjectHandler.InputDataObject> extractInputObjects(NodeList inputEntityNodes) {
        List<InputDataObjectHandler.InputDataObject> inputs = new InputDataObjectHandler().extractInputObjectsFromInputNodes(inputEntityNodes);
        return inputs;
    }

    private static CustomerDeposit reflectCustomerDepositObject(InputDataObjectHandler.InputDataObject inputObject) {
        CustomerDeposit customerDeposit = new BankObjectsReflector().reflectCustomerDeposit(inputObject);
        return customerDeposit;
    }

    private static BigDecimal calculateInterest(CustomerDeposit customerDeposit, int durationInDays) {
        BigDecimal requestedPayedInterest = customerDeposit.getBankDeposit().calculateInterestByDuration(durationInDays);
        return requestedPayedInterest;
    }

    private static void writeOutput(Map<Integer, BigDecimal> output) {
        new WriterFactory().getWriter("file").write(output);
    }
}

