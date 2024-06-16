package com.example.helpdesk.repositories;

import com.example.helpdesk.models.Historial;
import com.example.helpdesk.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface logTransRepository extends JpaRepository<Log, Long> {
}
