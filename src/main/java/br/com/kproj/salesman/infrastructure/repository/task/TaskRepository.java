package br.com.kproj.salesman.infrastructure.repository.task;


import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends BaseRepository<Task, Long> {

    @Query("SELECT t FROM Task AS t WHERE t.salesOrder = :salesOrder AND t.parentId is null ORDER BY t.deadline ASC")
    List<Task> findBySalesOrder(@Param("salesOrder") SalesOrder salesOrder);

    @Query("SELECT " +
            "   CASE WHEN count(*) > 0 " +
            "       THEN true " +
            "       ELSE false " +
            "   END  " +
            "FROM Task AS t JOIN t.tasksChilds AS taskChild " +
            "WHERE taskChild = :task")
    Boolean isSomeonesSon(@Param("task")Task task);


    @Query("SELECT COUNT(t) FROM Task AS t WHERE t.status = :status")
    Long countByStatus(@Param("status")TaskStatus status);

    @Query("SELECT COUNT(t) FROM Task AS t WHERE t.status = :status AND t.salesOrder = :salesOrder")
    Long countByStatus(@Param("status")TaskStatus status, @Param("salesOrder") SalesOrder salesOrder);

    @Query("SELECT COUNT(t) FROM Task AS t WHERE t.salesOrder = :salesOrder")
    Long countBySalesOrder(@Param("salesOrder")SalesOrder salesOrder);

    @Query("SELECT t FROM Task AS t WHERE t.id = :id")
    Optional<Task> getOne(@Param("id")Long id);

}
