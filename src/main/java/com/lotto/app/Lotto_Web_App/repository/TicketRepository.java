package com.lotto.app.Lotto_Web_App.repository;

import com.lotto.app.Lotto_Web_App.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketNumber(String ticketNumber);
}
