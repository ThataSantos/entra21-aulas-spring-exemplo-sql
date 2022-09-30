package br.com.entra21.spring.execicioQuery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import br.com.entra21.spring.execicioQuery.model.Exemplo;

@Repository
@EnableJpaRepositories
public interface IExemploRepository extends JpaRepository<Exemplo, Integer> {
 public List<Exemplo>findByAge(Integer age);
 public List<Exemplo>findByFirstnameAndAge(String name, Integer age );
 public List<Exemplo>findByAgeLessThanEqual(Integer age);
 public List<Exemplo>findByLastnameStartingWith(String prefix);
 public List<Exemplo>findByLastnameStartingWithAndAgeLessThanEqual(String prefix, Integer age);
 
 
	
}
