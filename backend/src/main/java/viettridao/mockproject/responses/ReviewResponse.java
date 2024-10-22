package viettridao.mockproject.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import viettridao.mockproject.models.Inspector;
import viettridao.mockproject.models.Review;

/**
 * ReviewResponse
 * Version: 1.0
 * Date: 5/27/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/27/2024 kiet-kun-afk Create
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {

    private Long id;

    private Inspector inspector;

    private Long userId;

    private Integer rating;

    private String comment;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.inspector = review.getInspector();
        this.userId = review.getUser().getId();
        this.rating = review.getRating();
        this.comment = review.getComment();
    }

    public static ReviewResponse fromReview(Review review) {
        return new ReviewResponse(review);
    }
}
