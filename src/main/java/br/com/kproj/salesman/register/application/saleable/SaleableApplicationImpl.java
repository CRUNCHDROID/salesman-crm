package br.com.kproj.salesman.register.application.saleable;

import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableTypeEntity;
import br.com.kproj.salesman.infrastructure.entity.saleable.SaleableUnitEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.Saleable.SaleableUnitRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import br.com.kproj.salesman.register.application.contract.saleable.SaleableApplication;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("saleableApplication")
public class SaleableApplicationImpl extends BaseModelServiceImpl<SaleableUnitEntity> implements SaleableApplication {



    private SaleableUnitRepository saleableRepository;

    @Autowired
    public SaleableApplicationImpl(SaleableUnitRepository saleableRepository) {
        this.saleableRepository = saleableRepository;
    }


    public BaseRepository<SaleableUnitEntity, Long> getRepository() {
        return saleableRepository;
    }

    @Override
    public List<SaleableUnitEntity> getByType(SaleableTypeEntity saleableType) {
        if (saleableType == null) {
            Lists.newArrayList();
        }
        return saleableRepository.getByType(saleableType);
    }
}
