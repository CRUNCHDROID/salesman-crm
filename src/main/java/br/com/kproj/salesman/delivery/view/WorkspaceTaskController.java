package br.com.kproj.salesman.delivery.view;

import br.com.kproj.salesman.delivery.application.ActDeliverySalesApplication;
import br.com.kproj.salesman.infrastructure.entity.ActDeliverySales;
import br.com.kproj.salesman.infrastructure.entity.builders.ActDeliverySalesBuilder;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder.createSalesOrder;

@RestController
public class WorkspaceTaskController {

    @Autowired
    private SecurityHelper security;

    @Autowired
    private ActDeliverySalesApplication application;

    @RequestMapping(value="/delivery/workspace/list")
    public ModelAndView showMyWorkspace(Model model) {

        List<SalesOrder> salesToWork = application.findBy(security.getPrincipal().getUser());
        model.addAttribute("salesOrders", salesToWork);
        return new ModelAndView("/delivery/tasks/list");
    }



}