package com.example.incoming.service;

import com.example.incoming.dto.request.InvestEditReqDto;
import com.example.incoming.dto.request.InvestReqDto;
import com.example.incoming.dto.response.InvestAllRespDto;
import com.example.incoming.dto.response.InvestDetalizationRespDto;
import com.example.incoming.dto.response.InvestLisRespDto;
import com.example.incoming.entity.Account;
import com.example.incoming.entity.Invest;
import com.example.incoming.entity.User;
import com.example.incoming.repository.Impl.AccountRepositoryImpl;
import com.example.incoming.repository.Impl.InvestRepositoryImpl;
import com.example.incoming.repository.Impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvestService {
    private InvestRepositoryImpl investRepository;
    private AuthService authService;
    private UserRepositoryImpl userRepository;
    private AccountRepositoryImpl accountRepository;

    public void createInvest(String jwt, InvestReqDto dto) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Account account = accountRepository.getAccountByName(user, dto.getAccountName());
        account.setTotalAmount(account.getTotalAmount() - Double.parseDouble(dto.getSum()));
        accountRepository.saveAccount(account);
        Invest invest = new Invest(user, dto.getName(), Double.parseDouble(dto.getSum()), account, dto.getDateReturn());
        investRepository.saveInvest(invest);
    }

    public InvestAllRespDto getInvests(String jwt) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        List<InvestLisRespDto> listOfInvest = investRepository.getInvests(user).stream()
                .map(InvestLisRespDto::new)
                .collect(Collectors.toList());
        double totalAmountInvest = investRepository.getTotalAmountInvest(user);
        return new InvestAllRespDto(listOfInvest, totalAmountInvest);
    }

    public InvestDetalizationRespDto getInvest(String jwt, Long id) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Invest invest = investRepository.getInvest(user, id);
        return new InvestDetalizationRespDto(invest);
    }

    public void deleteInvest(String jwt, Long id) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Invest invest = investRepository.getInvest(user, id);
        Account account = invest.getAccount();
        account.setTotalAmount(account.getTotalAmount() + invest.getSumInvest());
        accountRepository.saveAccount(account);
        investRepository.deleteInvest(invest);
    }

    public void editInvest(String jwt, InvestEditReqDto dto) {
        authService.checkJwt(jwt);
        User user = userRepository.getUser(authService.getLoginFromJWT(jwt));
        Invest invest = investRepository.getInvest(user, Long.parseLong(dto.getId()));
        Account oldAccount = invest.getAccount();
        oldAccount.setTotalAmount(oldAccount.getTotalAmount() + invest.getSumInvest());
        accountRepository.saveAccount(oldAccount);
        Account newAccount = accountRepository.getAccountByName(user, dto.getAccountName());
        newAccount.setTotalAmount(newAccount.getTotalAmount() - Double.parseDouble(dto.getSum()));
        invest.setNameInvest(dto.getName());
        invest.setSumInvest(Double.parseDouble(dto.getSum()));
        invest.setAccount(newAccount);
        invest.setDateReturn(dto.getDateReturn());
        investRepository.saveInvest(invest);

    }
}
