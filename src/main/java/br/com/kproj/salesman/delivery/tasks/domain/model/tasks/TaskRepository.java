package br.com.kproj.salesman.delivery.tasks.domain.model.tasks;


import br.com.kproj.salesman.delivery.tasks.domain.model.sales.SalesOrder;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.ChangeStatus;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.SubscribeTask;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository extends BaseRepository<Task, Long> {

    Optional<Subtask> save(Subtask subtask);

    Collection<Task> findAll(SalesOrder salesOrder);

    void generateTaskFor(SalesOrder salesOrder);

    void register(SubscribeTask subscribe);

    void unregister(SubscribeTask subscribe);

    void changeStatus(ChangeStatus changeStatus);
}
