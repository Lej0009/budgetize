package co.budgetize.budgetize.dao;

import co.budgetize.budgetize.models.Expense;
import co.budgetize.budgetize.models.Role;
import co.budgetize.budgetize.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.security.Principal;

@Repository
@Transactional
public interface ExpenseDao extends CrudRepository<Expense, Integer> {
//    Object findByUsername(String name);
    public Expense findByUser(Principal principal);

}
