package br.com.kproj.salesman.register.application;

import br.com.kproj.salesman.infrastructure.entity.Address;
import br.com.kproj.salesman.infrastructure.entity.person.Person;
import br.com.kproj.salesman.infrastructure.entity.person.client.Client;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.AddressRepository;
import br.com.kproj.salesman.register.application.contract.ClientApplication;
import br.com.kproj.salesman.register.domain.contract.AddressDomainService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddressApplicationImplTest {

    @InjectMocks
    private AddressApplicationImpl service;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ClientApplication clientApplication;

    @Mock
    private AddressDomainService addressDomainService;


    @Test
    public void shouldSaveANewAddress() {
        Client clientMocked = Mockito.mock(Client.class);
        Person personLoadedMocked = Mockito.mock(Person.class);
        Address address = Mockito.mock(Address.class);

        given(clientMocked.getId()).willReturn(2l);
        given(clientApplication.getOne(2l)).willReturn(Optional.of(personLoadedMocked));
        given(address.isNew()).willReturn(Boolean.TRUE);
        given(clientMocked.to()).willReturn(personLoadedMocked);

        service.register(address, clientMocked);

        verify(address).setPerson(personLoadedMocked);
        verify(addressDomainService).prepareToSave(address);
        verify(addressRepository).save(address);

    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenPersonNotExist() {
        Client personMocked = Mockito.mock(Client.class);
        Person personLoadedMocked = null;
        Address address = new Address();


        given(personMocked.getId()).willReturn(2l);
        given(clientApplication.getOne(2l)).willReturn(Optional.ofNullable(personLoadedMocked));

        service.register(address, personMocked);

    }

    @Test
    public void shouldUpdateAddress() {
        Client clientMocked = Mockito.mock(Client.class);
        Person personLoadedMocked = Mockito.mock(Person.class);
        Address address = Mockito.mock(Address.class);
        Address addressLoaded = Mockito.mock(Address.class);

        given(clientMocked.getId()).willReturn(2l);
        given(clientApplication.getOne(2l)).willReturn(Optional.of(personLoadedMocked));
        given(address.isNew()).willReturn(Boolean.FALSE);
        given(address.getId()).willReturn(1l);
        given(addressRepository.findOne(1l)).willReturn(addressLoaded);
        given(clientMocked.to()).willReturn(personLoadedMocked);

        service.register(address, clientMocked);

        verify(address).setPerson(personLoadedMocked);
        verify(addressDomainService).prepareToSave(address);
        verify(addressRepository).save(addressLoaded);
    }
}