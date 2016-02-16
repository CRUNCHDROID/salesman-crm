package br.com.kproj.salesman.delivery.infrastructure.repository;

import br.com.kproj.salesman.infra.AbstractIntegrationTest;
import br.com.kproj.salesman.infrastructure.entity.User;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.UserBuilder.createUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class UserWorkOnTasksRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private UserWorkOnTasksRepository repository;

    @Test
    public void shouldReturnAllUsersWorksInTasks() {

        List<User> result = repository.findUsersWorksInTasks();

        assertThat(result.size(), is(2));
        assertThat(result.contains(createUser(1l).build()), is(Boolean.TRUE));
        assertThat(result.contains(createUser(2l).build()), is(Boolean.TRUE));
    }

    @Test
    public void shouldCountActingByOnTasksByUser() {
        User user = createUser(1l).build();

        Long result = repository.countOnAllTasksBy(user, TaskStatus.WAITING);

        assertThat(result, is(2l));
    }

    @Test
    public void shouldReturnUsersWorkInTask() {
        SalesOrder salesOrder = SalesOrderBuilder.createSalesOrder(1l).build();

        List<User> result = repository.findUsersWorksOnSalesOrder(salesOrder);

        assertThat(result.size(), is(2));
        assertThat(result.contains(createUser(1l).build()), is(Boolean.TRUE));
        assertThat(result.contains(createUser(2l).build()), is(Boolean.TRUE));
    }

    @Test
    public void shouldCountActingOnTaskByUserAndStatus() {
        User user = createUser(1l).build();
        TaskStatus status = TaskStatus.PROBLEM;
        SalesOrder salesOrder = SalesOrderBuilder.createSalesOrder(1l).build();

        Long result = repository.countOnTaskBy(user, salesOrder, status);

        assertThat(result, is(2l));
    }
}