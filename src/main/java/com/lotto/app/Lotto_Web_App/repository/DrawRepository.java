package com.lotto.app.Lotto_Web_App.repository;

import com.lotto.app.Lotto_Web_App.entity.Draw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrawRepository extends JpaRepository<Draw, Long> {
    Draw findTopByOrderByDrawDateDesc();
    List<Draw> findAllByOrderByDrawDateDesc();
}

