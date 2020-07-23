package com.guseva.moccaunting.repo;

import com.guseva.moccaunting.domain.CategoryOutlay;
import com.guseva.moccaunting.domain.Period;
import com.guseva.moccaunting.domain.SourceMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PeriodRepo extends JpaRepository<Period, Long> {
    @Query("select p from Period p " +
            "where p.startPeriod <= :o_date and p.endPeriod > :o_date and p.sourceMoney = :id_source")
    Period getOneOfSource(
            @Param("o_date") Date operationDate,
            @Param("id_source") Long id_source);

    @Query("select p from Period p " +
            "where p.startPeriod <= :o_date and p.endPeriod > :o_date and p.categoryOutlay = :id_category"
    )
    Period getOneOfCategoryOutlay(
            @Param("o_date") Date operationDate,
            @Param("id_category") Long id_category
    );
    @Query("select DISTINCT p.startPeriod from Period p")
    List<Date> getAllStartPeriod();

    List<Period> findAllByStartPeriodOrderByStartPeriod(Date date);

    Period findOneByCategoryOutlayAndStartPeriod(CategoryOutlay categoryOutlay, Date date);
    Period findOneBySourceMoneyAndStartPeriod(SourceMoney sourceMoney, Date date);
}
