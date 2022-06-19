package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDTO getBeerById(UUID beerId) {
        return BeerDTO.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

	@Override
	public BeerDTO saveNewBeer(BeerDTO beerDTO) {
		// TODO Auto-generated method stub
		return BeerDTO.builder().id(UUID.randomUUID())
                .beerName("Nastro Azzurro")
                .beerStyle("Bionda")
                .build();
	}

	@Override
	public void updateBeer(UUID beerId, BeerDTO beerDTO) {
		// TODO Auto-generated method stub
		
	}
}
