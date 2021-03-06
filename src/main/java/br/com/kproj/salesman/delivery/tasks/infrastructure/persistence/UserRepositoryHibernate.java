package br.com.kproj.salesman.delivery.tasks.infrastructure.persistence;


import br.com.kproj.salesman.delivery.tasks.domain.model.user.User;
import br.com.kproj.salesman.delivery.tasks.domain.model.user.UserRepository;
import br.com.kproj.salesman.delivery.tasks.infrastructure.persistence.springdata.UserRepositorySpringData;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userRepositoryHibernateDeliveryTaskModule")
public class UserRepositoryHibernate extends BaseRespositoryImpl<User, UserEntity> implements UserRepository {


    @Autowired
    private UserRepositorySpringData repository;


    @Override
    public BaseRepositoryLegacy<UserEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<UserEntity, User> getConverter() {
        return ((userEntity, args) -> new User(userEntity.getId()));
    }
}
