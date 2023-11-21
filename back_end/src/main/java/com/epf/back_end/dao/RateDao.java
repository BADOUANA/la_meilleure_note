package com.epf.back_end.dao;

import com.epf.back_end.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateDao extends JpaRepository<Rate,Long> {
    @Query("SELECT r FROM Rate r WHERE r.note >= 0.8")
    List<Rate> getBestRates();

}
