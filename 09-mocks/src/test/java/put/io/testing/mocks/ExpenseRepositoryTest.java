package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepositoryTest {

    @Test
    void loadExpenses() {
        IFancyDatabase database = mock(MyDatabase.class);
        ExpenseRepository expenseRepository = new ExpenseRepository(database);
        expenseRepository.loadExpenses();

        List<Expense> expenseList = expenseRepository.getExpenses();

        assertEquals(expenseList.size(), 0);

        InOrder inOrder = inOrder(database);
        inOrder.verify(database).connect();
        inOrder.verify(database).queryAll();
        inOrder.verify(database).close();
    }

    @Test
    void saveExpenses() {
        IFancyDatabase database = mock(MyDatabase.class);
        ExpenseRepository expenseRepository = new ExpenseRepository(database);

        for (int i = 0; i < 5; i++) {
            Expense expense = new Expense();
            expenseRepository.addExpense(expense);
        }
        expenseRepository.saveExpenses();

        verify(database, times(5)).persist(any());
    }
}
