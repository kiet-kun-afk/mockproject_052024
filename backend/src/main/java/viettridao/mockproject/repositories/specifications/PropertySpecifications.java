package viettridao.mockproject.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import viettridao.mockproject.models.Property;

public class PropertySpecifications {
    public static Specification<Property> hasBedroom(Integer bedroom) {
        return (root, query, cb) -> bedroom == null ? null : cb.equal(root.get("numberBedroom"), bedroom);
    }

    public static Specification<Property> hasBathroom(Integer bathroom) {
        return (root, query, cb) -> bathroom == null ? null : cb.equal(root.get("numberBathroom"), bathroom);
    }

    public static Specification<Property> hasPrice(Double priceMin, Double priceMax) {
        Specification<Property> spec = (root, query, cb) -> {
            if (priceMin == null && priceMax == null) {
                return null;
            } else if (priceMin != null && priceMax != null) {
                return cb.between(root.get("price"), priceMin, priceMax);
            } else if (priceMin != null) {
                return cb.greaterThanOrEqualTo(root.get("price"), priceMin);
            } else {
                return cb.lessThanOrEqualTo(root.get("price"), priceMax);
            }
        };
        return spec;
    }
}
