package com.guseva.moccaunting.controller;

import com.guseva.moccaunting.domain.CategoryOutlay;
import com.guseva.moccaunting.domain.Period;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.service.CategoryOutlayService;
import com.guseva.moccaunting.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category_outlay")
public class CategoryOutlayController {
    private final CategoryOutlayService categoryOutlayService;
    private final PeriodService periodService;

    @Autowired
    public CategoryOutlayController(CategoryOutlayService categoryOutlayService, PeriodService periodService) {
        this.categoryOutlayService = categoryOutlayService;
        this.periodService = periodService;
    }

    @GetMapping
    public List<CategoryOutlay> list() {
        return categoryOutlayService.list();
    }

    @GetMapping("{id}")
    public CategoryOutlay getOne(@PathVariable Long id){
        return categoryOutlayService.getOne(id);
    }

    @GetMapping("{id}/operations")
    public OperationPageDto allOperations(
            @PathVariable Long id,
            @PageableDefault(size = OperationPageDto.PAGE_SIZE, sort = {"operationDate", "id"}) Pageable pageable
    ) {
        return categoryOutlayService.allOperations(id, pageable);
    }

    @PutMapping("{id}")
    public CategoryOutlay update(@PathVariable Long id, @RequestBody CategoryOutlay category) {
        return categoryOutlayService.update(id, category);
    }

    @PostMapping
    public CategoryOutlay create(@RequestBody CategoryOutlay category) {
        return categoryOutlayService.create(category);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        categoryOutlayService.delete(id);
    }

    @GetMapping("/{id}/statistic")
    public List<List<String>> getStatistic(@PathVariable Long id) {
        var c = categoryOutlayService.getOne(id);
        return periodService.getAllStatistics(c);
    }

}
