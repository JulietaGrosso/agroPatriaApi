package com.agropatriaapp.agropatriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agropatriaapp.agropatriaapi.model.WebHookLogs;

public interface WebhookLogsRepository extends JpaRepository<WebHookLogs, Integer> {
  
}
