package com.example.incoming.service;

import com.example.incoming.dto.request.ActionReqDto;
import com.example.incoming.dto.response.ActionDetailRespDto;
import com.example.incoming.dto.response.ActionRespDto;
import com.example.incoming.entity.Account;
import com.example.incoming.entity.Action;
import com.example.incoming.entity.Category;
import com.example.incoming.entity.User;
import com.example.incoming.mapper.ActionMapper;
import com.example.incoming.repository.Impl.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActionService {
    private AuthService authService;
    private UserRepositoryImpl userRepository;
    private CategoryRepositoryImpl categoryRepository;

    private AccountRepositoryImpl accountRepository;

    private BankAccountRepository bankAccountRepository;
    private ActionRepositoryImpl actionRepository;


    private ActionMapper actionMapper;

    @Transactional
    public void saveAction(ActionReqDto dto, String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        if (dto.getTypeAction().equals("доход")) {
            Category incomeCategory = categoryRepository.getIncomeCategory(dto.getCategory());
            Account account = accountRepository.getAllAccounts(user, dto.getAccount());
            Action income = new Action(user, Double.parseDouble(dto.getAmount()), dto.getComment(), account, incomeCategory, "доход");
            actionRepository.saveAction(income);
            account.setTotalAmount(account.getTotalAmount() + Double.parseDouble(dto.getAmount()));
            bankAccountRepository.saveAccount(account);
            return;
        }
        Category expenditureCategory = categoryRepository.getExpenditureCategory(dto.getCategory());
        Account account = accountRepository.getAllAccounts(user, dto.getAccount());
        Action expenditure = new Action(user, Double.parseDouble(dto.getAmount()), dto.getComment(), account, expenditureCategory, "расход");
        actionRepository.saveAction(expenditure);
        account.setTotalAmount(account.getTotalAmount() - Double.parseDouble(dto.getAmount()));
        bankAccountRepository.saveAccount(account);
    }

    @Transactional
    public List<ActionRespDto> getActions(String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        List<Action> listOfActions = actionRepository.getActions(user);
        return listOfActions.stream()
                .map(action -> actionMapper.toDto(action))
                .collect(Collectors.toList());
    }

    public ActionDetailRespDto detailPage(Long id, String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Action action = actionRepository.getAction(id, user);
        return actionMapper.toDetailDto(action);
    }

    @Transactional
    public void deletePage(Long id, String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Action action = actionRepository.getAction(id, user);
        Account account = action.getAccount();
        if (action.getTypeOperation().equals("доход"))
            account.setTotalAmount(account.getTotalAmount() - action.getAmount());
        else
            account.setTotalAmount(account.getTotalAmount() + action.getAmount());
        accountRepository.saveAccount(account);
        actionRepository.deleteAction(id, user);
    }

    @Transactional
    public void editPage(ActionReqDto dto, String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Action action = actionRepository.getAction(Long.parseLong(dto.getId()), user);
        Account oldAccount = action.getAccount();

        if (dto.getTypeAction().equals("доход")) {
            oldAccount.setTotalAmount(oldAccount.getTotalAmount() - action.getAmount());
            accountRepository.saveAccount(oldAccount);
            Category incomeCategory = categoryRepository.getIncomeCategory(dto.getCategory());
            Action income = new Action(Long.parseLong(dto.getId()), user, Double.parseDouble(dto.getAmount()), dto.getComment(), oldAccount, incomeCategory, "доход");
            actionRepository.saveAction(income);
            Account newAccount = accountRepository.getAllAccounts(user, dto.getAccount());
//            if (!newAccount.getNameAccount().equals(dto.getAccount())) {
                newAccount.setTotalAmount(newAccount.getTotalAmount() + Double.parseDouble(dto.getAmount()));
                bankAccountRepository.saveAccount(newAccount);
//            }
            return;
        }
        oldAccount.setTotalAmount(oldAccount.getTotalAmount() + action.getAmount());
        accountRepository.saveAccount(oldAccount);
        Category expenditureCategory = categoryRepository.getExpenditureCategory(dto.getCategory());
        Action expenditure = new Action(Long.parseLong(dto.getId()), user, Double.parseDouble(dto.getAmount()), dto.getComment(), oldAccount, expenditureCategory, "расход");
        actionRepository.saveAction(expenditure);
        Account newAccount = accountRepository.getAllAccounts(user, dto.getAccount());
//        if (!newAccount.getNameAccount().equals(dto.getAccount())) {
            newAccount.setTotalAmount(newAccount.getTotalAmount() - Double.parseDouble(dto.getAmount()));
            bankAccountRepository.saveAccount(newAccount);
//        }
    }
}
