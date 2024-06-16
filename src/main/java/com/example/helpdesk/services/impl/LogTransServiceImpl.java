package com.example.helpdesk.services.impl;

import com.example.helpdesk.models.Log;
import com.example.helpdesk.repositories.logTransRepository;
import com.example.helpdesk.services.LogTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogTransServiceImpl implements LogTransService {
    @Autowired
    private logTransRepository  logTransRepository;
    @Override
    public void save(Log log) {
        logTransRepository.save(log);
    }
}
