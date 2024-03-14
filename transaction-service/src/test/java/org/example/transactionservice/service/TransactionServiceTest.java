package org.example.transactionservice.service;

import org.example.transactionservice.common.AccountType;
import org.example.transactionservice.common.TransactionType;
import org.example.transactionservice.dto.transaction.DepositDto;
import org.example.transactionservice.dto.transaction.TransferDto;
import org.example.transactionservice.dto.transaction.WithdrawDto;
import org.example.transactionservice.exception.InsufficientBalanceException;
import org.example.transactionservice.model.BankAccount;
import org.example.transactionservice.model.Transaction;
import org.example.transactionservice.repository.TransactionRepository;
import org.example.transactionservice.service.implement.BankAccountService;
import org.example.transactionservice.service.implement.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @Mock
    private BankAccountService bankAccountService;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Before
    public void setUp() throws Exception {
        // Giả lập hành vi của bankAccountService
        when(bankAccountService.getBankAccById(1L)).thenReturn(new BankAccount(1L, null, 100000000D, AccountType.CHECKOUT, LocalDateTime.now()));
        when(bankAccountService.getBankAccById(2L)).thenReturn(new BankAccount(2L, null, 100000000D, AccountType.CHECKOUT, LocalDateTime.now()));
    }

    @Test
    public void testTransferWithSufficientBalance() throws Exception {
        TransferDto transferDto = new TransferDto(1L, 2L, 2000D, "test");

        Transaction transaction = transactionService.transfer(transferDto);

        Assert.assertNotNull(transaction);
    }

    @Test(expected = InsufficientBalanceException.class)
    public void testTransferWithInsufficientBalance() throws Exception {
        TransferDto transferDto = new TransferDto(1L, 2L, 15000000D, "test");

        transactionService.transfer(transferDto); // Phải ném ra InsufficientBalanceException
    }

    // test function withdraw when it has enough money to do a transaction
    @Test
    public void testWithdrawSufficientBalance() throws Exception {
        WithdrawDto withdrawDto = new WithdrawDto();
        withdrawDto.setAccountId(12345L);
        withdrawDto.setAmount(100.0);
        withdrawDto.setDescription("Test Withdrawal");

        BankAccount sourceAcc = new BankAccount();
        sourceAcc.setAccountId(12345L);
        sourceAcc.setBalance(200.0);

        when(bankAccountService.getBankAccById(12345L)).thenReturn(sourceAcc);

        Transaction expectedTransaction = Transaction
                .builder()
                .sourceAccountId(12345L)
                .transactionDate(LocalDateTime.now())
                .amount(100.0)
                .description("Test Withdrawal")
                .transactionType(TransactionType.WITHDRAWAL).build();

        Transaction savedTransaction = new Transaction();
        savedTransaction.setSourceAccountId(12345L);
        savedTransaction.setTransactionDate(expectedTransaction.getTransactionDate());
        savedTransaction.setAmount(expectedTransaction.getAmount());
        savedTransaction.setDescription(expectedTransaction.getDescription());
        savedTransaction.setTransactionType(expectedTransaction.getTransactionType());

        when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(savedTransaction);

        Transaction result = transactionService.withdraw(withdrawDto);

        assertEquals(expectedTransaction.getSourceAccountId(), result.getSourceAccountId());
        assertEquals(expectedTransaction.getAmount(), result.getAmount(), 0.001);
        assertEquals(expectedTransaction.getDescription(), result.getDescription());
        assertEquals(expectedTransaction.getTransactionType(), result.getTransactionType());

        verify(bankAccountService, times(1)).updateAccountBalance(12345L, -100.0);
    }

    @Test
    public void depositSuccessfulDepositReturnsTransaction() throws Exception {

        DepositDto depositDto = new DepositDto();
        depositDto.setAccountId(1L);
        depositDto.setAmount(200000D);
        depositDto.setDescription("Nap tien");

        BankAccount destinationAcc = new BankAccount();
        destinationAcc.setAccountId(1L);

        Mockito.when(bankAccountService.getBankAccById(1L)).thenReturn(destinationAcc);

        // Act
        Transaction result = transactionService.deposit(depositDto);

        // Assert
        Mockito.verify(transactionRepository, Mockito.times(1)).save(result);
        Mockito.verify(bankAccountService, Mockito.times(1)).updateAccountBalance(1L, 200000D);

        assertEquals(1L, result.getDestinationAccountId());
        assertEquals(LocalDateTime.now().getDayOfYear(), result.getTransactionDate().getDayOfYear()); // You might want to adjust this assertion based on your requirements.
        assertEquals(200000D, result.getAmount());
        assertEquals("Nap tien", result.getDescription());
        assertEquals(TransactionType.DEPOSIT, result.getTransactionType());
    }

}
