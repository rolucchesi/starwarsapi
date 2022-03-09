package br.com.letscode.starwarsapi.repository;

import br.com.letscode.starwarsapi.model.Rebelde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldesRepository extends JpaRepository<Rebelde, String> {
}
