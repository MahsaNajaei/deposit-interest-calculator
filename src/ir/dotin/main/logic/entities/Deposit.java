package ir.dotin.main.logic.entities;

import java.math.BigDecimal;

public interface Deposit {
     String getType();
     double getInterestRate();
     BigDecimal getBalance();
     BigDecimal calculateInterestByDuration(int durationInDays);
}
