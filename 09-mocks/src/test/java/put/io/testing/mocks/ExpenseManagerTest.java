package put.io.testing.mocks;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {

    @Test
    void calculateTotal() {
        ExpenseRepository expenseRepository = mock(ExpenseRepository.class);

        ArrayList<Expense> expenses = new ArrayList<Expense>();

        Expense expense = new Expense();
        expense.setTitle("Title");
        expense.setAmount(1);
        expense.setCategory("Urgent");

        for (int i = 0; i < 3; i++) {
            expenses.add(expense);
        }

        when(expenseRepository.getExpenses()).thenReturn(expenses);
        ExpenseManager expenseManager = new ExpenseManager(expenseRepository, new FancyService());

        assertEquals(3, expenseManager.calculateTotal());
    }

    @Test
    void calculateTotalForCategory() {
        ExpenseRepository expenseRepository = mock(ExpenseRepository.class);

        ArrayList<Expense> homeExpenses = new ArrayList<Expense>();

        Expense homeExpense = new Expense();
        homeExpense.setTitle("Title");
        homeExpense.setAmount(1);
        homeExpense.setCategory("Home");

        homeExpenses.add(homeExpense);
        homeExpenses.add(homeExpense);

        ArrayList<Expense> carExpenses = new ArrayList<Expense>();

        Expense carExpense = new Expense();
        carExpense.setTitle("Title");
        carExpense.setAmount(1);
        carExpense.setCategory("Car");

        carExpenses.add(carExpense);
        carExpenses.add(carExpense);
        carExpenses.add(carExpense);

        when(expenseRepository.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(expenseRepository.getExpensesByCategory("Home")).thenReturn(homeExpenses);
        when(expenseRepository.getExpensesByCategory("Car")).thenReturn(carExpenses);


        ExpenseManager expenseManager = new ExpenseManager(expenseRepository, new FancyService());

        assertEquals(2, expenseManager.calculateTotalForCategory("Home"));
        assertEquals(3, expenseManager.calculateTotalForCategory("Car"));
        assertEquals(0, expenseManager.calculateTotalForCategory("Food"));
        assertEquals(0, expenseManager.calculateTotalForCategory("Sport"));

        // Kolejność oczekiwań ma znaczenie gdyż kolejne oczekiwania nadpisują dodmenę podprzenich
    }

    @Test
    void calculateTotalInDollars() throws ConnectException {
        ExpenseRepository expenseRepository = mock(ExpenseRepository.class);
        FancyService fancyService = mock(FancyService.class);

        when(fancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer((Answer) invocation -> {
            Object[] args = invocation.getArguments();
            return ((double)args[0]) / 4.0;
        });

        ArrayList<Expense> expenses = new ArrayList<Expense>();

        Expense expense = new Expense();
        expense.setTitle("Title");
        expense.setAmount(1);
        expense.setCategory("Urgent");

        for (int i = 0; i < 4; i++) {
            expenses.add(expense);
        }

        when(expenseRepository.getExpenses()).thenReturn(expenses);
        ExpenseManager expenseManager = new ExpenseManager(expenseRepository, fancyService);

        assertEquals(1, expenseManager.calculateTotalInDollars());

        when(fancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenThrow(new ConnectException());

        assertEquals(-1, expenseManager.calculateTotalInDollars());
    }
}
