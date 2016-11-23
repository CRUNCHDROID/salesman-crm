package br.com.kproj.salesman.accounts.addresses.view.support.builders;



import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.view.support.resources.ContactResource;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItem;
import br.com.kproj.salesman.infrastructure.http.response.handler.resources.ResourceItems;
import br.com.uol.rest.apiconverter.ConverterToResource;
import br.com.uol.rest.infrastructure.libraries.ContextArguments;
import com.google.common.collect.Lists;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.uol.rest.infrastructure.libraries.SelectableArguments.createEmpty;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component("contactResourceBuilderAccountsModule")
public class AddressResourceBuilder {


    private HttpServletRequest request;

    @Autowired
    public AddressResourceBuilder(HttpServletRequest request) {
        this.request = request;
    }

    public ResourceItem build(Contact contact) {
        ContactResource resource = buildItem(contact);

        return new ResourceItem(resource, request.getRequestURI());
    }

    public ResourceItems build(Iterable<Contact> contacts) {

        List<ContactResource> resources = Lists.newArrayList(contacts).stream()
                .map(item -> buildItem(item)).collect(Collectors.toList());

        return new ResourceItems(resources, request.getRequestURI());
    }

    public ContactResource buildItem(Contact contact) {
        ContextArguments context = ContextArguments.create(createEmpty(), EMPTY);

        ContactResource resource = new ContactResource();

        ConverterToResource.convert(contact, resource, context);
        return resource;
    }

}