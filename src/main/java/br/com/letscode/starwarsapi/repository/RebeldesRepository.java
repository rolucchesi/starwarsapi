package br.com.letscode.starwarsapi.repository;

import br.com.letscode.starwarsapi.model.Rebelde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RebeldesRepository extends JpaRepository<Rebelde, String>  {

    List<Rebelde> findAllByTraidor(Boolean traidor);

}
