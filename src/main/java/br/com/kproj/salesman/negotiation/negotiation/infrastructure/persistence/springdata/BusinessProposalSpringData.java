package br.com.kproj.salesman.negotiation.negotiation.infrastructure.persistence.springdata;

import br.com.kproj.salesman.infrastructure.entity.accounts.CustomerEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.BusinessProposalEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessProposalSpringData extends BaseRepositoryLegacy<BusinessProposalEntity, Long> {

    @Query("SELECT b FROM BusinessProposalEntity AS b WHERE b.customer = :customer")
    List<BusinessProposalEntity> findAll(@Param("customer") CustomerEntity account);
}
