package net.javaguides.expense.controller;

import lombok.AllArgsConstructor;
import net.javaguides.expense.dto.ExpenseDto;
import net.javaguides.expense.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/expenses") // Base URI
public class ExpenseController {

    // Inject the ExpenseService using constructor base Di
    private ExpenseService expenseService;

    // Build create expense REST API
    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto){

       ExpenseDto savedExpense = expenseService.createExpense(expenseDto);

        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    // Build get Expense by id REST API
    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable("id") Long expenseId){

        ExpenseDto expense = expenseService.getExpenseById(expenseId);

        return ResponseEntity.ok(expense);
    }

}
