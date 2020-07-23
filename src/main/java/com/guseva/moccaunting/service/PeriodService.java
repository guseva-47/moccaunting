package com.guseva.moccaunting.service;

import com.guseva.moccaunting.domain.CategoryOutlay;
import com.guseva.moccaunting.domain.Operation;
import com.guseva.moccaunting.domain.Period;
import com.guseva.moccaunting.domain.SourceMoney;
import com.guseva.moccaunting.exception.NotFound;
import com.guseva.moccaunting.repo.PeriodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Service
public class PeriodService {
    private final PeriodRepo periodRepo;

    @Autowired
    public PeriodService(PeriodRepo periodRepo) {
        this.periodRepo = periodRepo;
    }

    public List<Period> list() {
        return periodRepo.findAll();
    }

    public void addOperation(Operation operation) {
        // операция с плюсом может быть:
        // пришли деньги из источника в кошелек
        // перешли из кошелька в кошелек -- не влияет на периоды
        // пришли из кошелька в категорию трат
        if (operation.getPurse() == null)
            throw new NotFound();

        if (operation.getSourceMoney() != null) {
            Period periodFromDb = periodRepo.getOneOfSource(operation.getOperationDate(), operation.getSourceMoney().getId());
            if (periodFromDb == null) {
                // todo возможно, добавляют с "неправильным" временем операции (в это время данной категории не существовало)
                throw new NotFound();
            }
            update(periodFromDb, operation.getMoneyAmount());

        } else if (operation.getCategoryOutlay() != null) {
            Period periodFromDb = periodRepo.getOneOfCategoryOutlay(operation.getOperationDate(), operation.getCategoryOutlay().getId());
            if (periodFromDb == null) {
                throw new NotFound();
            }
            update(periodFromDb, operation.getMoneyAmount());

        } else if (operation.getPurseSponsor() == null){
            throw new NotFound();
        }
    }

    private void update(Period periodFromDb, Long moneyAmount){
        long sum = periodFromDb.getCurrentMoney() + moneyAmount;
        periodFromDb.setCurrentMoney(sum);
        periodRepo.save(periodFromDb);
    }

    public void updateExpectedMoney(CategoryOutlay categoryOutlay, Date date, Long money) {
        Period periodFromDb = periodRepo.
                findOneByCategoryOutlayAndStartPeriod(categoryOutlay, date);
        periodFromDb.setCurrentMoney(money);
        periodRepo.save(periodFromDb);
    }

    public void updateExpectedMoney(SourceMoney sourceMoney, Date date, Long money) {
        Period periodFromDb = periodRepo.
                findOneBySourceMoneyAndStartPeriod(sourceMoney, date);
        periodFromDb.setCurrentMoney(money);
        periodRepo.save(periodFromDb);
    }

    public void deleteOperation(Operation operation) {
        Long sum = -operation.getMoneyAmount();
        operation.setMoneyAmount(sum);
        this.addOperation(operation);
        operation.setMoneyAmount(-sum);
    }

    // расходы по периодам
    public List<List<String>> getAllOutlay() {
        Predicate<Period> function = period -> period.getCategoryOutlay() != null;
        return findStartDateAndSumMoney(function);
    }
    // доходы по периодам
    public List<List<String>> getAllSource() {
        Predicate<Period> function = period -> period.getSourceMoney() != null;
        return findStartDateAndSumMoney(function);
    }

    private List<List<String>> findStartDateAndSumMoney(Predicate<Period> function) {
        List<List<String>> result = new ArrayList<>();

        List<Date> allDates = periodRepo.getAllStartPeriod();
        for (Date d : allDates) {
            Long sum = periodRepo.findAllByStartPeriodOrderByStartPeriod(d)
                    .stream()
                    .filter(function)
                    .map(Period::getCurrentMoney)
                    .reduce(Long::sum).orElse(0L);
            result.add(Arrays.asList(d.toString(), sum.toString()));
        }
        return result;
    }

    // расходы в определенной категории по периодам
    public List<List<String>> getAllStatistics(CategoryOutlay category) {
        List<List<String>> result = new ArrayList<>();

        List<Date> allDates = periodRepo.getAllStartPeriod();
        for (Date d : allDates) {
            Long sum = periodRepo.findOneByCategoryOutlayAndStartPeriod(category, d).getCurrentMoney();
            result.add(Arrays.asList(d.toString(), sum.toString()));
        }
        return result;
    }

    // доходы из определенного источника по периодам
    public List<List<String>> getAllStatistics(SourceMoney sourceMoney) {
        List<List<String>> result = new ArrayList<>();

        List<Date> allDates = periodRepo.getAllStartPeriod();
        for (Date d : allDates) {
            Long sum = periodRepo.findOneBySourceMoneyAndStartPeriod(sourceMoney, d).getCurrentMoney();
            result.add(Arrays.asList(d.toString(), sum.toString()));
        }
        return result;
    }
}
