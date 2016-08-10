package br.com.kproj.salesman.delivery.application.tasktemplates;


import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskTemplate;
import br.com.kproj.salesman.infrastructure.service.ModelService;

import java.util.List;

public interface TaskTemplateApplication extends ModelService<TaskTemplate> {

    TaskTemplate register(TaskTemplate taskTemplate);

    List<TaskTemplate> findTaskTemplateBy(SaleableUnitEntity saleable);

    List<TaskTemplate> findTaskTemplateOnlyRootBy(SaleableUnitEntity saleable);

    void remove(TaskTemplate taskTemplate);

}
