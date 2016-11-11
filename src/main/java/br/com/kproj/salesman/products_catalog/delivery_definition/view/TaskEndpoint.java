package br.com.kproj.salesman.products_catalog.delivery_definition.view;


import br.com.kproj.salesman.infrastructure.exceptions.NotFoundException;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.kproj.salesman.products_catalog.delivery_definition.application.TaskFacade;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.product.Saleable;
import br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks.Task;
import br.com.kproj.salesman.products_catalog.delivery_definition.view.support.builders.TaskResourceBuilder;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController("taskEndpoinDefinitionModule")
public class TaskEndpoint {

    private TaskFacade service;

    private TaskResourceBuilder builder;

    private HttpServletRequest request;

    @Autowired
    public TaskEndpoint(TaskFacade service, TaskResourceBuilder builder, HttpServletRequest request) {
        this.service = service;
        this.builder = builder;
        this.request = request;
    }

    @RequestMapping(value = "/rs/saleables/{saleableId}/task-definitions", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItems getTaskDefinitions(@PathVariable Long saleableId) {
        Saleable saleable = new Saleable(saleableId);

        Collection<Task> rootTasks = service.findAll(saleable);

        return builder.build(rootTasks, request.getRequestURI());
    }

    @RequestMapping(value = "/rs/saleables/task-definitions/{taskId}", method = RequestMethod.GET)
    public @ResponseBody
    ResourceItem getOne(@PathVariable Long taskId) {

        Optional<Task> taskFound = service.getOne(taskId);

        Task task = taskFound.orElseThrow(() -> new NotFoundException());

        return builder.build(task, request.getRequestURI());
    }



}
