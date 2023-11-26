package com.epf.back_end.dao;

import com.epf.back_end.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<Image,Long> {
    Optional<Image> findByTitle(String name);
    Optional<Image> findById(Long id);

}
