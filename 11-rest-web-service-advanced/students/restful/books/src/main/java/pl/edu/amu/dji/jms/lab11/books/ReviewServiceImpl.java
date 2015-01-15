package pl.edu.amu.dji.jms.lab11.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Iterable<Review> getReviews(String isbn) {
        ParameterizedTypeReference<Iterable<Review>> responseType;
        responseType = new ParameterizedTypeReference<Iterable<Review>>() {};
        
        ResponseEntity<Iterable<Review>> entity = restTemplate.exchange(
                "http://localhost:8090/review/{isbn}",
                HttpMethod.GET, 
                null, 
                responseType,
                isbn
                );
        
        return entity.getBody();
    }

}
