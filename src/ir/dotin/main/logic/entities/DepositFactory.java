package ir.dotin.main.logic.entities;

import java.math.BigDecimal;

public class DepositFactory {
    public Deposit getDeposit(String depositType, BigDecimal depositBalance) {
        if (depositType.equalsIgnoreCase("LongTerm"))
            return new LongTerm(depositBalance);
        if (depositType.equalsIgnoreCase("ShortTerm"))
            return new ShortTerm(depositBalance);
        if (depositType.equalsIgnoreCase("Qarz"))
            return new Qarz(depositBalance);
        return null;
    }
}
