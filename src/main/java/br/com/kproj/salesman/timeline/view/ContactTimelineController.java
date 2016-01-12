package br.com.kproj.salesman.timeline.view;

import br.com.kproj.salesman.infrastructure.entity.timeline.Timeline;
import br.com.kproj.salesman.infrastructure.entity.timeline.items.LogActivity;
import br.com.kproj.salesman.infrastructure.security.helpers.SecurityHelper;
import br.com.kproj.salesman.timeline.view.dto.LogActivityVO;
import br.com.kproj.salesman.timeline.application.TimelineActivitiesApplication;
import br.com.kproj.salesman.timeline.application.TimelineApplication;
import br.com.kproj.salesman.timeline.infrastructure.TimelineActivitiesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static br.com.kproj.salesman.infrastructure.entity.builders.ContactBuilder.createContact;

@RestController
public class ContactTimelineController {

    @Autowired
    private TimelineActivitiesApplication service;

    @Autowired
    private TimelineApplication timelineApplication;

    @Autowired
    private TimelineActivitiesValidator validator;

    @Autowired
    private SecurityHelper security;


    @RequestMapping(value = "/contact/{contactId}/logactivity/save", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity saveOfContact(@PathVariable Long contactId, @ModelAttribute LogActivityVO logActivityVO,
                                                      BindingResult bindingResult) throws IOException {

        LogActivity logActivity = logActivityVO.getLogActivity();
        validator.validate(logActivity, new BindException(bindingResult));

        logActivity.setFiles(logActivityVO.getAppFiles());
        logActivity.setUser(security.getPrincipal().getUser());
        service.register(createContact(contactId).build(), logActivity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO alterar o a uri para /contact/id/activities
    @RequestMapping(value = "/contact/{contactId}/logactivity", method = RequestMethod.GET)
    public ModelAndView getTimelineContact(@PathVariable Long contactId, Model model) {

        Timeline timeline = timelineApplication.register(createContact(contactId).build());

        model.addAttribute(createContact(contactId).build());
        model.addAttribute(timeline);
        return new ModelAndView("/timeline/contact-timeline");
    }


}