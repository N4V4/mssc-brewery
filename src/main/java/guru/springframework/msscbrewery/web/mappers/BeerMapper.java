package guru.springframework.msscbrewery.web.mappers;

import org.mapstruct.Mapper;

import guru.springframework.msscbrewery.domain.Beer;
import guru.springframework.msscbrewery.web.model.BeerDto;

@Mapper(uses = DataMapper.class)
public interface BeerMapper {
	
	BeerDto beerDtoToBeer(BeerDto beerDto);
	
	Beer beerToBeerDto(Beer beer);

}
