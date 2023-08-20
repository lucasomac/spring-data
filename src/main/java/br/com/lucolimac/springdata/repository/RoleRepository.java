package br.com.lucolimac.springdata.repository;

import br.com.lucolimac.springdata.orm.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
