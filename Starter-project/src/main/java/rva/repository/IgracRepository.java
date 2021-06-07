package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Igrac;

public interface IgracRepository extends JpaRepository <Igrac, Integer>{
	
	Collection<Igrac> findByImeContainingIgnoreCase(String naziv); 
}


