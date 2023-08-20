package br.com.lucolimac.springdata.repository;

import br.com.lucolimac.springdata.orm.UnitWork;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitWorkRepository extends CrudRepository<UnitWork, Long> {
}
