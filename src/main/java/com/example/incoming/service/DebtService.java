package com.example.incoming.service;

import com.example.incoming.dto.request.DebtReqDto;
import com.example.incoming.dto.request.EditDebtReqDto;
import com.example.incoming.dto.response.DebtDetalizationRespDto;
import com.example.incoming.dto.response.DebtRespDto;
import com.example.incoming.dto.response.DebtsRespDto;
import com.example.incoming.entity.Account;
import com.example.incoming.entity.Debt;
import com.example.incoming.entity.User;
import com.example.incoming.repository.Impl.AccountRepositoryImpl;
import com.example.incoming.repository.Impl.DebtRepositoryImpl;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DebtService {
    private AuthService authService;
    private UserRepositoryImpl userRepository;
    private AccountRepositoryImpl accountRepository;

    private DebtRepositoryImpl debtRepository;

    public void createDebt(String jwt, DebtReqDto dto) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Account account = accountRepository.getAccountByName(user, dto.getAccount());
        account.setTotalAmount(account.getTotalAmount() + Double.parseDouble(dto.getSum_debt()));
        accountRepository.saveAccount(account);
        Debt debt = new Debt(user, dto.getNameDebt(), Double.parseDouble(dto.getSum_debt()), account, dto.getDateReturn());
        debtRepository.saveDebt(debt);
    }

    public DebtsRespDto getDebts(String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        List<DebtRespDto> result = debtRepository.getListDebt(user).stream()
                .map(DebtRespDto::new)
                .collect(Collectors.toList());
        Double totalAmount = debtRepository.getTotalAmount(user);
        return new DebtsRespDto(result, String.valueOf(totalAmount));
    }

    public DebtDetalizationRespDto getDebt(String jwt, Long id) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Debt debt = debtRepository.getDebt(user, id);
        return new DebtDetalizationRespDto(debt);
    }

    public void editDebt(String jwt, EditDebtReqDto dto) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Debt debt = debtRepository.getDebt(user, Long.parseLong(dto.getId()));
        Account oldAccount = debt.getAccount();
        oldAccount.setTotalAmount(oldAccount.getTotalAmount() - debt.getSum_debt());
        accountRepository.saveAccount(oldAccount);
        Account newAccount = accountRepository.getAccountByName(user, dto.getAccount());
        newAccount.setTotalAmount(newAccount.getTotalAmount() + Double.parseDouble(dto.getSum_debt()));
        debt.setSum_debt(Double.parseDouble(dto.getSum_debt()));
        debt.setNameDebt(dto.getNameDebt());
        debt.setDateReturn(dto.getDateReturn());
        debt.setAccount(newAccount);
        debtRepository.saveDebt(debt);
    }

    public void deleteDebt(String jwt, Long id) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Debt debt = debtRepository.getDebt(user, id);
        Account account = debt.getAccount();
        account.setTotalAmount(account.getTotalAmount() + debt.getSum_debt());
        accountRepository.saveAccount(account);
        debtRepository.deleteDebt(debt);
    }
}
