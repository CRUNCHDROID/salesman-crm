package br.com.kproj.salesman.delivery2.tasks_template.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.task.ChecklistTemplateEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplateEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("checklistTemplateRepository")
public interface ChecklistRepositorySpringData extends BaseRepositoryLegacy<ChecklistTemplateEntity, Long> {

    @Query("SELECT c FROM ChecklistTemplateEntity AS c WHERE c.taskTemplateEntity = :task")
    List<ChecklistTemplateEntity> findCheckListBy(@Param("task") TaskTemplateEntity taskEntity);

}
