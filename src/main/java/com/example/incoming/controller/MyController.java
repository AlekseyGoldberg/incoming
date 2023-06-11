package com.example.incoming.controller;

import com.example.incoming.dto.request.AccountReqDto;
import com.example.incoming.dto.request.ActionReqDto;
import com.example.incoming.dto.request.DebtReqDto;
import com.example.incoming.dto.request.UserReqDto;
import com.example.incoming.dto.response.*;
import com.example.incoming.exception.NotFoundUserException;
import com.example.incoming.exception.NotUnauthorizedException;
import com.example.incoming.exception.UserExistException;
import com.example.incoming.service.*;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController
@AllArgsConstructor
@Log4j2
public class MyController {
    private final UserService userService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    private final ActionService actionService;


    @PostMapping("/registry")
    public ResponseEntity<UserRespDto> registry(@RequestBody UserReqDto dto) {
        try {
            log.info("accept request with login: {}", dto.getLogin());
            UserRespDto resp = userService.saveUser(dto);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (UserExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserRespDto> login(@RequestBody UserReqDto dto) {
        try {
            log.info("accept request with login: {}", dto.getLogin());
            UserRespDto resp = userService.getJwt(dto);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NotUnauthorizedException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (NotFoundUserException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/acountsName")
    public ResponseEntity<List<String>> getAccountsName(@RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            return new ResponseEntity<>(accountService.getAccountNames(headers.get("auth").get(0)), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createAccount")
    public ResponseEntity<OkRespDto> createAccount(@RequestBody AccountReqDto dto, @RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            accountService.saveAccount(dto, headers.get("auth").get(0));
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incomeCategory")
    public ResponseEntity<List<String>> getIncomeCategory(@RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            List<String> result = categoryService.getAllIncomeCategory(headers.get("auth").get(0));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expenditureCategory")
    public ResponseEntity<List<String>> getExpenditureCategory(@RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            List<String> result = categoryService.getAllExpenditureCategory(headers.get("auth").get(0));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createActions")
    public ResponseEntity<OkRespDto> createAction(@RequestBody ActionReqDto dto, @RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            actionService.saveAction(dto, headers.get("auth").get(0));
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/actions")
    public ResponseEntity<List<ActionRespDto>> getActions(@RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            List<ActionRespDto> result = actionService.getActions(headers.get("auth").get(0));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detailPage")
    public ResponseEntity<ActionDetailRespDto> detailPage(@RequestParam Long id, @RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            ActionDetailRespDto result = actionService.detailPage(id, headers.get("auth").get(0));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/detailPage/delete")
    public ResponseEntity<OkRespDto> deletePage(@RequestParam Long id, @RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            actionService.deletePage(id, headers.get("auth").get(0));
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/detailPage/edit")
    public ResponseEntity<OkRespDto> editPage(@RequestBody ActionReqDto dto, @RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            actionService.editPage(dto, headers.get("auth").get(0));
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<AccountsRespDto> getAccounts(@RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            AccountsRespDto resp = accountService.getAccounts(headers.get("auth").get(0));
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
