package viettridao.mockproject.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.ReviewDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.Inspector;
import viettridao.mockproject.models.Review;
import viettridao.mockproject.models.User;
import viettridao.mockproject.repositories.InspectorRepository;
import viettridao.mockproject.repositories.ReviewRepository;
import viettridao.mockproject.responses.ReviewResponse;
import viettridao.mockproject.services.IReviewService;
import viettridao.mockproject.services.IUserService;

/**
 * ReviewService
 * Version: 1.0
 * Date: 5/27/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/27/2024 kiet-kun-afk Create
 */
@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final InspectorRepository inspectorRepository;
    private final IUserService userService;

    @Override
    public ReviewResponse addReview(ReviewDTO reviewDTO) throws DataNotFoundException {
        User user = userService.getAuth("USER");
        Inspector inspector = inspectorRepository.findByIdAndActiveTrue(reviewDTO.getInspectorId());
        if (user == null || inspector == null) {
            throw new DataNotFoundException("User or inspector not found");
        }
        Review review = new Review();
        review.setInspector(inspector);
        review.setUser(user);
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        reviewRepository.save(review);
        return ReviewResponse.fromReview(review);
    }

    @Override
    public void deleteReview(Long reviewId) throws Exception {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new DataNotFoundException("Review not found: " + reviewId));
        reviewRepository.delete(review);
    }

    @Override
    public ReviewResponse editReview(ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(reviewDTO.getId()).get();
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        return ReviewResponse.fromReview(review);
    }

    @Override
    public List<ReviewResponse> getReviewsByInspectorId(ReviewDTO reviewDTO) {
        List<Review> review = reviewRepository.findByInspectorId(reviewDTO.getInspectorId());
        return review.stream().map(ReviewResponse::fromReview).toList();
    }
}
