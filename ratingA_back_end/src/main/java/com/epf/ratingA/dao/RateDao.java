package com.epf.ratingA.dao;

import com.epf.ratingA.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateDao extends JpaRepository<Rate, Long> {

}
