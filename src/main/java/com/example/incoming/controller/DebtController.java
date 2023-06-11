package com.example.incoming.controller;

import com.example.incoming.dto.request.DebtReqDto;
import com.example.incoming.dto.request.EditDebtReqDto;
import com.example.incoming.dto.response.DebtDetalizationRespDto;
import com.example.incoming.dto.response.DebtsRespDto;
import com.example.incoming.dto.response.OkRespDto;
import com.example.incoming.exception.NotUnauthorizedException;
import com.example.incoming.service.DebtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@AllArgsConstructor
public class DebtController {
    private final DebtService debtService;

    @PostMapping("/createDebt")
    public ResponseEntity<OkRespDto> createDebt(@RequestBody DebtReqDto dto, @RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            debtService.createDebt(headers.get("auth").get(0), dto);
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/debt")
    public ResponseEntity<DebtsRespDto> getDebts(@RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            DebtsRespDto result = debtService.getDebts(headers.get("auth").get(0));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detalizationDebt/{id}")
    public ResponseEntity<DebtDetalizationRespDto> detalizationDebt(@PathVariable Long id, @RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            DebtDetalizationRespDto result = debtService.getDebt(headers.get("auth").get(0), id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/detalizationDebt/edit")
    public ResponseEntity<OkRespDto> detalizationDebt(@RequestBody EditDebtReqDto dto, @RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            debtService.editDebt(headers.get("auth").get(0), dto);
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/detalizationDebt/delete/{id}")
    public ResponseEntity<OkRespDto> deleteDebt(@RequestHeader HttpHeaders headers, @PathVariable Long id) {
        try {
            log.info("accept request");
            debtService.deleteDebt(headers.get("auth").get(0), id);
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
