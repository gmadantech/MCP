package com.example.mcpserverremote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcpserverremote.entity.HelpDeskTicket;

public interface HelpDeskTicketRepository extends JpaRepository<HelpDeskTicket, Long> {

    List<HelpDeskTicket> findByUsername(String username);

}
