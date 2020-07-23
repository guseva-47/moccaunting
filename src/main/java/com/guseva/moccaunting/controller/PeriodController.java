package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.Period;
import com.guseva.moccaunting.service.PeriodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("period")
public class PeriodController {
    private final PeriodService periodService;

    public PeriodController(PeriodService periodService) {
        this.periodService = periodService;
    }

    @GetMapping
    public List<Period> list() {
        return periodService.list();
    }
}
