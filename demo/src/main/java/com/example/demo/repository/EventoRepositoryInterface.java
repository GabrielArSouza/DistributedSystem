package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Evento;

public interface EventoRepositoryInterface extends CrudRepository<Evento, String> {

}
