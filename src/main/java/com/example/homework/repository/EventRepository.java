package com.example.homework.repository;

import com.example.homework.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
}
