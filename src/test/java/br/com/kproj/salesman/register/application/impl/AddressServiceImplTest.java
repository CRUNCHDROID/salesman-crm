package br.com.kproj.salesman.register.application.impl;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.Contact;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AddressRepository;
import br.com.kproj.salesman.infrastructure.repository.ContactRepository;
import br.com.kproj.salesman.register.application.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl service;

    @Mock
    private AddressRepository contactRepository;

    @Mock
    private ClientService clientService;

    @Test
    public void shouldSaveANewContact() {
        Person personMocked = Mockito.mock(Person.class);
        Person personLoadedMocked = Mockito.mock(Person.class);
        Address address = new Address();

        given(personMocked.getId()).willReturn(2l);
        given(clientService.getOne(2l)).willReturn(Optional.of(personLoadedMocked));

        service.register(personMocked, address);

        verify(personLoadedMocked).addAddress(address);
        verify(clientService).save(personLoadedMocked);

    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenPersonNotExist() {
        Person personMocked = Mockito.mock(Person.class);
        Person personLoadedMocked = null;
        Address address = new Address();

        given(personMocked.getId()).willReturn(2l);
        given(clientService.getOne(2l)).willReturn(Optional.ofNullable(personLoadedMocked));

        service.register(personMocked, address);

        verify(personLoadedMocked).addAddress(address);
    }

    @Test
    public void shouldUpdateContact() {
        Person personMocked = Mockito.mock(Person.class);
        Address address = new Address();
        address.setId(1l);
        Address addressLoaded = Mockito.mock(Address.class);

        given(contactRepository.findOne(1l)).willReturn(addressLoaded);

        service.register(personMocked, address);

        verify(contactRepository).save(addressLoaded);
    }
}