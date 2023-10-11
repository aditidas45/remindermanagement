package com.aditi.springboot.reminder.repository;

import com.aditi.springboot.reminder.model.Reminder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends CrudRepository<Reminder, Long> {

}
