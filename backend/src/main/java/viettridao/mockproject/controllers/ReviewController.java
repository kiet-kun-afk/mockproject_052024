package viettridao.mockproject.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.ReviewDTO;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.responses.ReviewResponse;
import viettridao.mockproject.services.IReviewService;

/**
 * ReviewController
 * Version: 1.0
 * Date: 5/27/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/27/2024 kiet-kun-afk Create
 */
@RestController
@RequestMapping("${api.prefix}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final IReviewService reviewService;

    /**
     * create a new review
     * 
     * @param reviewDTO
     * @return reviewResponse
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public ResponseEntity<ResponseObject> createReview(@Valid @ModelAttribute ReviewDTO reviewDTO,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(ResponseObject.builder()
                        .status(400)
                        .data(errors)
                        .message("Create review failed")
                        .build());
            }
            ReviewResponse review = reviewService.addReview(reviewDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .data((review))
                    .message("Create review successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Create review failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * delete a review
     * 
     * @param reviewId
     * @return reviewResponse
     */
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("")
    public ResponseEntity<ResponseObject> deleteReview(@RequestParam("id") Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .message("Delete review successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Delete review failed")
                    .data(e.getMessage())
                    .build());
        }
    }

    /**
     * edit a review
     * 
     * @param reviewDTO
     * @return reviewResponse
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("")
    public ResponseEntity<ResponseObject> editReview(@Valid @ModelAttribute ReviewDTO reviewDTO,
            BindingResult bindingResult) {
        try {

            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(ResponseObject.builder()
                        .status(400)
                        .data(errors)
                        .message("Edit review failed")
                        .build());
            }
            ReviewResponse review = reviewService.editReview(reviewDTO);
            return ResponseEntity.ok(ResponseObject.builder()
                    .status(200)
                    .data((review))
                    .message("Edit review successfully")
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseObject.builder()
                    .status(400)
                    .message("Edit review failed")
                    .data(e.getMessage())
                    .build());
        }
    }
}
