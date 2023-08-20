package br.com.lucolimac.springdata.repository;

import br.com.lucolimac.springdata.orm.Employee;
import br.com.lucolimac.springdata.orm.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    List<Employee> findByName(String nome);

    @Query("SELECT e FROM Employee e WHERE e.name = :nome " + "AND e.salary >= :salario AND e.dateContract = :data")
    List<Employee> findNameSalaryBiggerDataContract(String name, Double salary, LocalDate data);

    @Query(value = "SELECT * FROM employee e WHERE e.data_contract >= :data", nativeQuery = true)
    List<Employee> findDateContractBigger(LocalDate data);

    @Query(value = "SELECT e.id, e.name, e.salary FROM employee e", nativeQuery = true)
    List<EmployeeProjection> findEmployeeSalary();
}
