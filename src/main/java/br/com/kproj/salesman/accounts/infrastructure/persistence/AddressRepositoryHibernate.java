package br.com.kproj.salesman.accounts.infrastructure.persistence;

import br.com.kproj.salesman.accounts.domain.model.address.Address;
import br.com.kproj.salesman.accounts.domain.model.address.AddressRepository;
import br.com.kproj.salesman.accounts.infrastructure.persistence.springdata.AddressEntityRepositorySpringData;
import br.com.kproj.salesman.accounts.infrastructure.persistence.translate.AddressEntityToAddressConverter;
import br.com.kproj.salesman.infrastructure.entity.accounts.AddressEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryHibernate extends BaseRespositoryImpl<Address, AddressEntity> implements AddressRepository {

    @Autowired
    private AddressEntityRepositorySpringData repository;

    @Autowired
    private AddressEntityToAddressConverter converter;



    @Override
    public BaseRepositoryLegacy<AddressEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<AddressEntity, Address> getConverter() {
        return converter;
    }
}
