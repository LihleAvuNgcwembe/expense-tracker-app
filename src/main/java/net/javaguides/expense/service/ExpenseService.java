package net.javaguides.expense.service;

import net.javaguides.expense.dto.ExpenseDto;

public interface ExpenseService {

    ExpenseDto createExpense(ExpenseDto expenseDto);
}
