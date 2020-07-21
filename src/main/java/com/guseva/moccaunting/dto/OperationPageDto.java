package com.guseva.moccaunting.dto;

import com.guseva.moccaunting.domain.Operation;

import java.util.List;

// todo @JsonView(Views.FullMessage.class
public class OperationPageDto {
    public static final int PAGE_SIZE = 1;
    private List<Operation> operations;
    private int currentPage;
    private int totalPage;

    public OperationPageDto(List<Operation> operations, int currentPage, int totalPage) {
        this.operations = operations;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
