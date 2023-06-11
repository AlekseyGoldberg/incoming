package com.example.incoming.service;

import com.example.incoming.dto.request.AccountReqDto;
import com.example.incoming.dto.request.DebtReqDto;
import com.example.incoming.dto.response.AccountRespDto;
import com.example.incoming.dto.response.AccountsRespDto;
import com.example.incoming.entity.Account;
import com.example.incoming.entity.Debt;
import com.example.incoming.entity.User;
import com.example.incoming.exception.ExistAccountException;
import com.example.incoming.repository.Impl.AccountRepositoryImpl;
import com.example.incoming.repository.Impl.DebtRepositoryImpl;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService {
    private AuthService authService;
    private UserRepositoryImpl userRepository;
    private AccountRepositoryImpl accountRepository;

    public List<String> getAccountNames(String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        return accountRepository.getAccountNames(user);
    }

    public void saveAccount(AccountReqDto dto, String jwt) {
        authService.checkJwt(jwt);
        if (accountRepository.existAccount(dto.getNameAccount()))
            throw new ExistAccountException("Account with name " + dto.getNameAccount() + " already exist");
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Account account = new Account(dto.getNameAccount(), user, Double.parseDouble(dto.getInitSum()));
        accountRepository.saveAccount(account);
    }

    public AccountsRespDto getAccounts(String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        List<Tuple> accounts = accountRepository.getAllAccounts(user);
        List<AccountRespDto> result = accounts.stream()
                .map(a -> new AccountRespDto(a))
                .collect(Collectors.toList());

        Double totalAmount = accountRepository.getTotalAmount(user);
        return new AccountsRespDto(result, totalAmount);
    }


}
