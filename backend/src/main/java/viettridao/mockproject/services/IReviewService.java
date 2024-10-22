package viettridao.mockproject.services;

import java.util.List;

import viettridao.mockproject.dtos.ReviewDTO;
import viettridao.mockproject.responses.ReviewResponse;

public interface IReviewService {

    public ReviewResponse addReview(ReviewDTO reviewDTO) throws Exception;

    public void deleteReview(Long reviewId) throws Exception;

    public ReviewResponse editReview(ReviewDTO reviewDTO);

    public List<ReviewResponse> getReviewsByInspectorId(ReviewDTO reviewDTO);
}
