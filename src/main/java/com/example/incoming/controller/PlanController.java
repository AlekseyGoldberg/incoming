package com.example.incoming.controller;

import com.example.incoming.dto.request.PlanReqDto;
import com.example.incoming.dto.response.OkRespDto;
import com.example.incoming.dto.response.PlansRespDto;
import com.example.incoming.exception.NotUnauthorizedException;
import com.example.incoming.service.PlanService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Log4j2
public class PlanController {
    private PlanService planService;

    @PostMapping("/createPlan")
    public ResponseEntity<OkRespDto> createPlan(@RequestHeader HttpHeaders headers, @RequestBody PlanReqDto dto) {
        try {
            log.info("accept request");
            planService.savePlan(headers.get("auth").get(0), dto);
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlansRespDto>> getPlans(@RequestHeader HttpHeaders headers) {
        try {
            log.info("accept request");
            List<PlansRespDto> result = planService.getPlans(headers.get("auth").get(0)).stream()
                    .map(PlansRespDto::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/completePlan/{id}")
    public ResponseEntity<OkRespDto> completePlan(@RequestHeader HttpHeaders headers, @PathVariable Long id) {
        try {
            log.info("accept request");
            planService.completePlan(headers.get("auth").get(0), id);
            return new ResponseEntity<>(new OkRespDto(), HttpStatus.OK);
        } catch (NotUnauthorizedException | ExpiredJwtException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletePlan/{id}")
    public ResponseEntity<OkRespDto> deletePlan(@RequestHeader HttpHeaders headers, @PathVariable Long id) {
        try {
            log.info("accept request");
            planService.deletePlan(headers.get("auth").get(0), id);
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
