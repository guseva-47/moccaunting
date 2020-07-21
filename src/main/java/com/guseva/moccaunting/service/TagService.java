package com.guseva.moccaunting.service;

import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.domain.Tag;
import com.guseva.moccaunting.dto.OperationPageDto;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.OperationRepo;
import com.guseva.moccaunting.repo.TagRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepo tagRepo;
    private final OperationRepo operRepo;

    public TagService(TagRepo tagRepo, OperationRepo operRepo) {
        this.tagRepo = tagRepo;
        this.operRepo = operRepo;
    }

    public List<Tag> list() {
        return tagRepo.findAll();
    }

    public Tag getOne(Long id) {
        return tagRepo.findById(id).orElseThrow(NotFound::new);
    }

    public Tag update(Long id, Tag tag) {
        Tag tagFromDB = tagRepo.findById(id).orElseThrow(NotFound::new);
        BeanUtils.copyProperties(tag, tagFromDB, "id");
        return tagRepo.save(tagFromDB);
    }

    public Tag create(Tag tag) {
        return tagRepo.save(tag);
    }

    public void delete(Long id) {
        var toDelete = tagRepo.findById(id).orElseThrow(NotFound::new);
        tagRepo.delete(toDelete);
    }

    // Постраничный вывод операций
    public OperationPageDto allOperations(Long id, Pageable pageable) {
        Tag tagFromDB = tagRepo.findById(id).orElseThrow(NotFound::new);

        Page<Operation> page = operRepo.findAllByTags(tagFromDB, pageable);
        return new OperationPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }
}
