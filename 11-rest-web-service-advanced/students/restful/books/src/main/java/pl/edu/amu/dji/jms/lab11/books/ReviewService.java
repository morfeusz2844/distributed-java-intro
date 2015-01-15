package pl.edu.amu.dji.jms.lab11.books;

public interface ReviewService {
    Iterable<Review> getReviews(String isbn);
    
}
