package br.com.kproj.salesman.infrastructure.configuration.initial;

import br.com.kproj.salesman.infrastructure.configuration.parsers.RegionsParser;
import br.com.kproj.salesman.infrastructure.configuration.parsers.UserPositionParser;
import br.com.kproj.salesman.infrastructure.entity.OperationRegion;
import br.com.kproj.salesman.infrastructure.entity.UserPosition;
import br.com.kproj.salesman.infrastructure.repository.RegionRepository;
import br.com.kproj.salesman.infrastructure.repository.UserPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class OperationRegionInit implements InitialProcess {


	@Autowired
	private RegionRepository repository;


	@Override
	@PostConstruct
	public void run() {

		long count = repository.count();
		if (count < 1) {
            List<OperationRegion> result = RegionsParser.getRegions();

            repository.save(result);
        }
	}

	
}
