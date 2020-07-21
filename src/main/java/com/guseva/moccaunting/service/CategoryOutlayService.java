package com.guseva.moccaunting.service;

import com.guseva.moccaunting.domain.CategoryOutlay;
import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.CategoryOutlayRepo;
import com.guseva.moccaunting.repo.OperationRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryOutlayService {
    private final CategoryOutlayRepo categoryRepo;
    private final OperationRepo operRepo;

    @Autowired
    public CategoryOutlayService(CategoryOutlayRepo categoryRepo, OperationRepo operRepo) {
        this.categoryRepo = categoryRepo;
        this.operRepo = operRepo;
    }

    public CategoryOutlay getOne(Long id) {
        return categoryRepo.findById(id).orElseThrow(NotFound::new);
    }

    public List<CategoryOutlay> list() {
        return categoryRepo.findAll();
    }

    // Постраничный вывод операций
    public OperationPageDto allOperations(Long id, Pageable pageable) {
        CategoryOutlay categoryFromDB = categoryRepo.findById(id).orElseThrow(NotFound::new);

        Page<Operation> page = operRepo.findAllByCategoryOutlay(categoryFromDB, pageable);
        return new OperationPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    public CategoryOutlay update(Long id, CategoryOutlay category) {
        CategoryOutlay categoryFromDB = categoryRepo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(category, categoryFromDB, "id");
        return categoryRepo.save(categoryFromDB);
    }

    public CategoryOutlay create(CategoryOutlay category) {
        category.setIsActive(true);
        return categoryRepo.save(category);
    }

    public void delete(Long id) {
        var toDelete = categoryRepo.findById(id).orElseThrow(NotFound::new);
        categoryRepo.delete(toDelete);
    }
}
