package viettridao.mockproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import viettridao.mockproject.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByInspectorId(Long inspectorId);
}
