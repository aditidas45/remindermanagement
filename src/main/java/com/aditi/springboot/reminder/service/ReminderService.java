package com.aditi.springboot.reminder.service;

import com.aditi.springboot.reminder.model.Reminder;
import com.aditi.springboot.reminder.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReminderService {

    private ReminderRepository repository;

    @Autowired
    public ReminderService(ReminderRepository repository) {
        this.repository = repository;
    }

    public List<Reminder> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Reminder> findById(Long id) {
        return repository.findById(id);
    }

    public Reminder save(Reminder stock) {
        return repository.save(stock);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
