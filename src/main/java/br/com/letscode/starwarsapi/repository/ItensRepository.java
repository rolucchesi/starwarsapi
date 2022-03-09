package br.com.letscode.starwarsapi.repository;

import br.com.letscode.starwarsapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensRepository extends JpaRepository<Item, String> {
}
