package br.com.kproj.salesman.assistants.calendar.activities.specialization.view.support.resource;

import br.com.kproj.salesman.assistants.calendar.domain.model.activity.specialization.ActivityContact;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.ActivityResource;
import br.com.kproj.salesman.assistants.calendar.view.support.resource.ContactResource;
import br.com.uol.rest.apiconverter.resources.Item;
import br.com.uol.rest.infrastructure.annotations.ResourceItem;
import br.com.uol.rest.infrastructure.annotations.Selectable;
import br.com.uol.rest.infrastructure.annotations.SuperClass;
import com.fasterxml.jackson.annotation.JsonIgnore;


@ResourceItem(name="activities-contacts", modelReference = ActivityContact.class, parent = ActivityResource.class)
public class ActivityContactResource extends Item {

    private Long id;

    @SuperClass
    private ActivityResource activity;

    private ContactResource contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Selectable(expression = "is-a", expandByDefault = true)
    public ActivityResource getActivity() {
        return activity;
    }

    public void setActivity(ActivityResource activity) {
        this.activity = activity;
    }

    @Selectable(expression = "of-contact", externalLink = true)
    public ContactResource getContact() {
        return contact;
    }

    public void setContact(ContactResource contact) {
        this.contact = contact;
    }

    @JsonIgnore
    public Long getContactId() {
        return contact == null ? null : contact.getId();
    }

}
