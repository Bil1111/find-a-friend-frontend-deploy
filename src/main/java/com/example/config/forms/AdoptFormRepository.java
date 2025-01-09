package com.example.config.forms;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptFormRepository extends JpaRepository<AdoptForm,Long> {

}
