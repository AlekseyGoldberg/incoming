package com.example.incoming.controller;

import com.example.incoming.dto.request.InvestEditReqDto;
import com.example.incoming.dto.request.InvestReqDto;
import com.example.incoming.dto.response.InvestAllRespDto;
import com.example.incoming.dto.response.InvestDetalizationRespDto;
import com.example.incoming.dto.response.OkRespDto;
import com.example.incoming.exception.NotUnauthorizedException;
import com.example.incoming.service.InvestService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Log4j2
public class InvestController {
    private InvestService investService;

    @PostMapping("/createInvest")
    public ResponseEntity<OkRespDto> createInvest(@RequestHeader HttpHeaders headers, @RequestBody InvestReqDto dto) {
        try {
            log.info("accept request");
            investService.createInvest(headers.get("auth").get(0), dto);
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invest")
    public ResponseEntity<InvestAllRespDto> getInvests(@RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            InvestAllRespDto resp = investService.getInvests(headers.get("auth").get(0));
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detalizationInvest/{id}")
    public ResponseEntity<InvestDetalizationRespDto> detalizationInvest(@RequestHeader HttpHeaders headers, @PathVariable Long id) {
        try {
            log.info("accept request");
            InvestDetalizationRespDto resp = investService.getInvest(headers.get("auth").get(0), id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/detalizationInvest/delete/{id}")
    public ResponseEntity<OkRespDto> deleteInvest(@RequestHeader HttpHeaders headers, @PathVariable Long id) {
        try {
            log.info("accept request");
            investService.deleteInvest(headers.get("auth").get(0), id);
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/detalizationInvest/edit")
    public ResponseEntity<OkRespDto> editInvest(@RequestHeader HttpHeaders headers, @RequestBody InvestEditReqDto dto) {
        try {
            log.info("accept request");
            investService.editInvest(headers.get("auth").get(0), dto);
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
