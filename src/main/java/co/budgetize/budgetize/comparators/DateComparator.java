package co.budgetize.budgetize.comparators;

import co.budgetize.budgetize.models.Expense;
import java.util.Comparator;

public class DateComparator implements Comparator<Expense> {

    @Override
    public int compare(Expense o1, Expense o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}