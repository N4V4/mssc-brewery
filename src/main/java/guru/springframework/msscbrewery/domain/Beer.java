package guru.springframework.msscbrewery.domain;

import java.sql.Timestamp;
import java.util.UUID;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beer {
	
	private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;
    
    private Timestamp createdDate;
    private Timestamp lastUpdatedDate;

}
