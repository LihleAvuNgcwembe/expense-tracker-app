package net.javaguides.expense.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.expense.dto.ExpenseDto;
import net.javaguides.expense.entity.Category;
import net.javaguides.expense.entity.Expense;
import net.javaguides.expense.mapper.ExpenseMapper;
import net.javaguides.expense.repository.CategoryRepository;
import net.javaguides.expense.repository.ExpenseRepository;
import net.javaguides.expense.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    // Inject ExpenseRepository
    private ExpenseRepository expenseRepository;

    // InjectCategoryRepository
    private CategoryRepository categoryRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {

        // Convert ExpenseDto to Expense entity
        Expense expense = ExpenseMapper.mapToExpense(expenseDto);

        // Save Expense entity to database
        Expense savedExpense = expenseRepository.save(expense);

        // Convert saved expense entity into ExpenseDto
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {

        // Get expense entity from database using expense id
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new RuntimeException("Expense not found with id" +  expenseId));

        // Convert expense entity to ExpenseDto
        return ExpenseMapper.mapToExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {

        List<Expense> expenses = expenseRepository.findAll();

        return expenses.stream()
                .map((expense) -> ExpenseMapper.mapToExpenseDto(expense))
                .collect(Collectors.toList());

    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new RuntimeException("Expense not found with id" + expenseId));

        // Update amount
        expense.setAmount(expenseDto.amount());

        // Update date
        expense.setExpenseDate(expenseDto.expenseDate());

        // Update category
        if(expenseDto.categoryDto() != null){
            // Get category entity by id
            Category category = categoryRepository.findById(expenseDto.categoryDto().id())
                    .orElseThrow(()-> new RuntimeException(
                            "Category not found with id" + expenseDto.categoryDto().id()));

            expense.setCategory(category);
        }

        // update expense entity
        Expense savedExpense = expenseRepository.save(expense);

        // Convert expense entity to ExpenseDto
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }
}
