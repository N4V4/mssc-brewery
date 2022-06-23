package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<HttpHeaders> handlePost(@RequestBody BeerDto beerDto){
    	BeerDto savedDTO = beerService.saveNewBeer(beerDto);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Location", "/api/v1/beer" + savedDTO.getId().toString());
    	
    	return new ResponseEntity(headers,HttpStatus.CREATED);
    }
    
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, BeerDto beerDto) {
    	
    	beerService.updateBeer(beerId, beerDto);
    	
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    	
    }
    
    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerID) {
    	
    	beerService.deleteBeer(beerID);
    	
    }
    

}
