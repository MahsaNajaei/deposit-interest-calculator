package ir.dotin.test;

import ir.dotin.main.io.writer.WriterFactory;
import ir.dotin.main.logic.*;

import ir.dotin.main.logic.entities.BankCustomer;
import ir.dotin.main.logic.entities.Deposit;
import ir.dotin.main.logic.InputDataObjectHandler;
import ir.dotin.main.io.parser.ParserFactory;
import ir.dotin.main.logic.Sorter;
import org.w3c.dom.NodeList;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {
        //To read config files
        new DefaultConfigHandler().readConfigs();

        //To read input
        NodeList inputEntityNodes = new ParserFactory().getParser("xml").parseByTagName("deposit");

        //To extract a list of input objects
        List<InputDataObjectHandler.InputDataObject> inputs = new InputDataObjectHandler().extractInputObjectsFromInputNodes(inputEntityNodes);

        //To get corresponding bank objects from input Objects using reflection and then calculate the requested interest
        Map<Integer, BigDecimal> paidInterestPerCustomer = new HashMap<>();
        for (InputDataObjectHandler.InputDataObject inputObject : inputs) {

            //reflection
            BankObjectsReflector bankObjectsReflector = new BankObjectsReflector();
            Deposit deposit = bankObjectsReflector.reflectDeposit(inputObject.getDepositType(), inputObject.getDepositBalance());
            BankCustomer customer = bankObjectsReflector.reflectCustomer(inputObject.getCustomerNumber(), deposit);

            //calculation of interest during requested time
            BigDecimal requestedPayedInterest = customer.getDeposit().calculateInterestByDuration(inputObject.getDurationInDays());
            paidInterestPerCustomer.put(customer.getCustomerNumber(), requestedPayedInterest);
        }

        //Sorting the map
        Map<Integer, BigDecimal> sortedPaidInterestPerCustomer = Sorter.sortMapByValue(paidInterestPerCustomer);

        //writing the output in a file
        new WriterFactory().getWriter("file").write(sortedPaidInterestPerCustomer);

    }
}

