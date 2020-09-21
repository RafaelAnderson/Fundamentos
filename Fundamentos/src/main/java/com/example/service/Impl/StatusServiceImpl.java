package com.example.service.Impl;

import com.example.model.Status;
import com.example.repository.StatusRepository;
import com.example.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Status> getStatus() {
        return statusRepository.findAll();
    }
}
