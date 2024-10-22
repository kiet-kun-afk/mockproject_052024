package viettridao.mockproject.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.properties.PropertyDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.Location;
import viettridao.mockproject.models.Photo;
import viettridao.mockproject.models.Property;
import viettridao.mockproject.models.PropertyType;
import viettridao.mockproject.models.User;
import viettridao.mockproject.repositories.LocationRepository;
import viettridao.mockproject.repositories.PhotoRepository;
import viettridao.mockproject.repositories.PropertyRepository;
import viettridao.mockproject.repositories.PropertyTypeRepository;
import viettridao.mockproject.repositories.UserRepository;
import viettridao.mockproject.repositories.specifications.PropertySpecifications;
import viettridao.mockproject.responses.PropertyResponse;
import viettridao.mockproject.services.IPropertyService;

/**
 * PropertyService
 * Version: 1.0
 * Date: 5/22/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/22/2024 kiet-kun-afk Create
 * 5/23/2024 kiet-kun-afk Update
 * 5/24/2024 kiet-kun-afk Update
 * 5/27/2024 kiet-kun-afk Update
 * 5/30/2024 kiet-kun-afk Update
 */
@Service
@RequiredArgsConstructor
public class PropertyService implements IPropertyService {
	private final PropertyRepository propertyRepository;
	private final PropertyTypeRepository propertyTypeRepository;
	private final LocationRepository locationRepository;
	private final UserRepository userRepository;
	private final FileService fileService;
	private final PhotoRepository photoRepository;

	/**
	 * get all properties
	 * 
	 * @return
	 */
	@Override
	public List<PropertyResponse> getProperties() {
		List<Property> properties = propertyRepository.findAll();
		return properties.stream().map(PropertyResponse::fromProperty).toList();
	}

	/**
	 * get properties not deleted
	 * 
	 * @return
	 */
	@Override
	public List<PropertyResponse> getPropertiesNotDeleted() {
		List<Property> properties = propertyRepository.findByDeletedFalse();
		return properties.stream().map(PropertyResponse::fromProperty).toList();
	}

	/**
	 * get property by id
	 * 
	 * @param propertyId
	 * @return
	 */
	@Override
	public PropertyResponse getPropertyById(Long id) throws Exception {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Property does not exist"));
		return PropertyResponse.fromProperty(property);
	}

	/**
	 * create new property
	 * 
	 * @param propertyDTO
	 * @return
	 */
	@Override
	public PropertyResponse createProperty(PropertyDTO propertyDTO) throws Exception {

		User user = userRepository.findById(propertyDTO.getOwnerId())
				.orElseThrow(() -> new DataNotFoundException("Owner does not exist"));
		PropertyType propertyType = propertyTypeRepository.findById(propertyDTO.getPropertyTypeId())
				.orElseThrow(() -> new DataNotFoundException("Property type does not exist"));
		Location location = locationRepository.findById(propertyDTO.getLocationId())
				.orElseThrow(() -> new DataNotFoundException("Location does not exist"));

		Property property = Property.builder()
				.owner(user)
				.location(location)
				.propertyType(propertyType)
				.landArea(propertyDTO.getLandArea())
				.usableArea(propertyDTO.getUsableArea())
				.legalStatus(propertyDTO.getLegalStatus())
				.listingDate(propertyDTO.getListingDate())
				.price(propertyDTO.getPrice())
				.status(propertyDTO.getStatus())
				.numberBedroom(propertyDTO.getNumberBedroom())
				.numberBathroom(propertyDTO.getNumberBathroom())
				.numberCarSpace(propertyDTO.getNumberCarSpace())
				.numberLivingRoom(propertyDTO.getNumberLivingRoom())
				.description(propertyDTO.getDescription() == null ? null
						: propertyDTO.getDescription())
				.notes(propertyDTO.getNotes() == null ? null
						: propertyDTO.getNotes())
				.deleted(propertyDTO.getDeleted() == null ? false
						: propertyDTO.getDeleted())
				.build();

		propertyRepository.save(property);
		if (propertyDTO.getFiles() == null) {
			property.setPhotos(null);
		} else {
			List<Photo> photos = getPhotos(propertyDTO, property);
			deleteAllPhotoByPropertyId(property.getId());
			property.setPhotos(photos);
			photoRepository.saveAll(photos);
		}

		return PropertyResponse.fromProperty(property);
	}

	public List<Photo> getPhotos(PropertyDTO propertyDTO, Property property) throws Exception {
		List<Photo> photos = new ArrayList<Photo>();
		for (String fileName : fileService.saveImages(propertyDTO.getFiles())) {
			Photo photo = new Photo();
			photo.setProperty(property);
			photo.setUrl(fileName);
			photo.setDescription(fileName);
			photos.add(photo);
		}
		return photos;
	}

	/**
	 * update information about a property
	 * 
	 * @param propertyId, propertyDTO
	 * @return
	 */
	@Override
	public PropertyResponse updateProperty(PropertyDTO propertyDTO) throws Exception {

		Property property = propertyRepository.findByIdAndDeletedFalse(propertyDTO.getId());
		PropertyType propertyType = propertyTypeRepository
				.findByIdAndDeletedFalse(propertyDTO.getPropertyTypeId());
		Location location = locationRepository.findByIdAndDeletedFalse(propertyDTO.getLocationId());
		User user = userRepository.findCustomer("USER", propertyDTO.getOwnerId());

		property.setOwner(propertyDTO.getOwnerId() == null ? property.getOwner()
				: user);
		property.setLocation(propertyDTO.getLocationId() == null ? property.getLocation()
				: location);
		property.setPropertyType(propertyDTO.getPropertyTypeId() == null ? property.getPropertyType()
				: propertyType);
		property.setLandArea(propertyDTO.getLandArea() == null ? property.getLandArea()
				: propertyDTO.getLandArea());
		property.setUsableArea(propertyDTO.getUsableArea() == null ? property.getUsableArea()
				: propertyDTO.getUsableArea());
		property.setLegalStatus(propertyDTO.getLegalStatus() == null ? property.getLegalStatus()
				: propertyDTO.getLegalStatus());
		property.setPrice(propertyDTO.getPrice() == null ? property.getPrice()
				: propertyDTO.getPrice());
		property.setDescription(propertyDTO.getDescription() == null ? property.getDescription()
				: propertyDTO.getDescription());
		property.setStatus(propertyDTO.getStatus() == null ? property.getStatus()
				: propertyDTO.getStatus());
		property.setNumberBedroom(propertyDTO.getNumberBedroom() == null ? property.getNumberBedroom()
				: propertyDTO.getNumberBedroom());
		property.setNumberBathroom(propertyDTO.getNumberBathroom() == null ? property.getNumberBathroom()
				: propertyDTO.getNumberBathroom());
		property.setNumberCarSpace(propertyDTO.getNumberCarSpace() == null ? property.getNumberCarSpace()
				: propertyDTO.getNumberCarSpace());
		property.setNumberLivingRoom(propertyDTO.getNumberLivingRoom() == null ? property.getNumberLivingRoom()
				: propertyDTO.getNumberLivingRoom());
		property.setNotes(propertyDTO.getNotes() == null ? property.getNotes()
				: propertyDTO.getNotes());
		property.setDeleted(propertyDTO.getDeleted() == null ? property.getDeleted()
				: propertyDTO.getDeleted());

		propertyRepository.save(property);
		if (propertyDTO.getFiles() == null) {
			property.setPhotos(property.getPhotos());
		} else {
			List<Photo> photos = getPhotos(propertyDTO, property);
			deleteAllPhotoByPropertyId(property.getId());
			property.setPhotos(photos);
			photoRepository.saveAll(photos);
		}

		return PropertyResponse.fromProperty(property);
	}

	@Transactional
	public void deleteAllPhotoByPropertyId(Long propertyId) {
		photoRepository.deleteByPropertyId(propertyId);
	}

	/**
	 * delete a property (update deleted to true)
	 * 
	 * @param propertyId
	 * @return
	 */
	@Override
	public void deleteProperty(Long id) throws Exception {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Property does not exist"));
		property.setDeleted(true);
		propertyRepository.save(property);
	}

	@Override
	public void recover(Long id) throws Exception {
		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Property does not exist"));
		property.setDeleted(false);
		propertyRepository.save(property);
	}

	/**
	 * get list properties by ownerId
	 * 
	 * @param ownerId
	 * @return
	 */
	@Override
	public List<PropertyResponse> getPropertiesByOwnerId(Long id) throws Exception {
		List<Property> properties = propertyRepository.findByOwnerId(id);
		return properties.stream().map(PropertyResponse::fromProperty).toList();
	}

	@Override
	public List<PropertyResponse> getPropertiesBySearch(
			Integer numberBedroom, Integer numberBathroom,
			Double price, String sortBy) throws Exception {
		// Create Specification
		Specification<Property> spec = Specification.where(PropertySpecifications.hasBedroom(numberBedroom))
				.and(PropertySpecifications.hasBathroom(numberBathroom))
				.and(PropertySpecifications.hasPrice(0.0, price));
		// Create Sort
		Sort sort = sortBy != null ? Sort.by(sortBy) : Sort.unsorted();
		return propertyRepository.findAll(spec, sort).stream().map(PropertyResponse::fromProperty).toList();
	}

	@Override
	public Page<PropertyResponse> findAllPropertiesNotDeleted(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Property> properties = propertyRepository.findByDeletedFalse(pageable);
		return properties.map(PropertyResponse::fromProperty);
	}

	@Override
	public Page<PropertyResponse> findAll(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Property> properties = propertyRepository.findAll(pageable);
		return properties.map(PropertyResponse::fromProperty);
	}

	@Override
	public Page<PropertyResponse> getPropertiesBySearch(
			Integer numberBedroom, Integer numberBathroom,
			Double price, String sortBy,
			Integer pageNumber, Integer pageSize) throws Exception {
		// Create Specification
		Specification<Property> spec = Specification.where(PropertySpecifications.hasBedroom(numberBedroom))
				.and(PropertySpecifications.hasBathroom(numberBathroom))
				.and(PropertySpecifications.hasPrice(0.0, price));

		// Create Sort
		Sort sort = sortBy != null ? Sort.by(sortBy) : Sort.unsorted();

		// Create Pageable
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		// Query database with Specification, Sort, and Pageable
		Page<Property> propertyPage = propertyRepository.findAll(spec, pageable);

		// Map Page<Property> to Page<PropertyResponse>
		return propertyPage.map(PropertyResponse::fromProperty);
	}

	@Override
	public Page<PropertyResponse> getPropertiesBySearch(String keyword, Integer pageNumber, Integer pageSize)
			throws Exception {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Property> properties = propertyRepository.findByKeyword(keyword, pageable);
		return properties.map(PropertyResponse::fromProperty);
	}
}
