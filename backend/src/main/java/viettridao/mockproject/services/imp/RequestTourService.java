package viettridao.mockproject.services.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import viettridao.mockproject.dtos.RequestTourDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.Property;
import viettridao.mockproject.models.RequestTour;
import viettridao.mockproject.models.User;
import viettridao.mockproject.repositories.PropertyRepository;
import viettridao.mockproject.repositories.RequestTourRepository;
import viettridao.mockproject.services.IRequestTourService;
import viettridao.mockproject.services.IUserService;

/**
 * RequestTourService
 * Version: 1.0
 * Date: 5/29/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/29/2024 kiet-kun-afk Create
 * 5/30/2024 kiet-kun-afk Update
 */
@Service
@RequiredArgsConstructor
public class RequestTourService implements IRequestTourService {

	private final RequestTourRepository requestTourRepository;
	private final PropertyRepository propertyRepository;
	private final FormatterService dateTimeFormatterService;
	private final IUserService userService;

	@Override
	public RequestTourDTO createNewRequestTour(RequestTourDTO requestDTO) throws Exception {

		User user = userService.getAuth("USER");
		Property property = propertyRepository.findByIdAndDeletedFalse(requestDTO.getPropertyId());
		if (property == null) {
			throw new DataNotFoundException("Property not found");
		}
		if (user == null) {
			throw new DataNotFoundException("Viewer not found");
		}
		LocalDate requestDate = dateTimeFormatterService.stringToDate(requestDTO.getRequestDate());
		if (!dateTimeFormatterService.isFuture(requestDate)) {
			throw new DataNotFoundException("Request date must be a future date");
		}

		RequestTour request = RequestTour.builder()
				.viewer(user)
				.property(property)
				.deleted(requestDTO.getDeleted() == null ? false : requestDTO.getDeleted())
				.requestDate(requestDate)
				.requestTime(dateTimeFormatterService.stringToTime(requestDTO.getRequestTime()))
				.status(requestDTO.getStatus() == null ? 1 : requestDTO.getStatus())
				.build();
		return RequestTourDTO.fromRequestTour(requestTourRepository.save(request));
	}

	@Override
	public RequestTourDTO updateRequestTour(RequestTourDTO requestDTO) throws Exception {

		User user = userService.getAuth("USER");
		RequestTour request = requestTourRepository.findById(requestDTO.getId())
				.orElseThrow(() -> new DataNotFoundException("Request tour not found"));

		if (requestDTO.getPropertyId() == null) {
			request.setProperty(request.getProperty());
		} else {
			Property property = propertyRepository.findByIdAndDeletedFalse(requestDTO.getPropertyId());
			request.setProperty(property);
		}
		LocalDate requestDate = dateTimeFormatterService.stringToDate(requestDTO.getRequestDate());
		if (!dateTimeFormatterService.isFuture(requestDate)) {
			throw new DataNotFoundException("Request date must be a future date");
		}

		request.setViewer(user);
		request.setRequestDate(requestDTO.getRequestDate() == null ? request.getRequestDate()
				: dateTimeFormatterService.stringToDate(requestDTO.getRequestDate()));
		request.setRequestTime(requestDTO.getRequestTime() == null ? request.getRequestTime()
				: dateTimeFormatterService.stringToTime(requestDTO.getRequestTime()));
		request.setStatus(requestDTO.getStatus() == null ? request.getStatus()
				: requestDTO.getStatus());
		request.setDeleted(requestDTO.getDeleted() == null ? request.getDeleted()
				: requestDTO.getDeleted());

		return RequestTourDTO.fromRequestTour(requestTourRepository.save(request));
	}

	@Override
	public void deleteRequestTour(Long id) throws DataNotFoundException {
		RequestTour request = requestTourRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("RequestTour not found"));
		request.setStatus(0);
		request.setDeleted(true);
		requestTourRepository.save(request);
	}

	@Override
	public List<RequestTourDTO> getAll() {
		List<RequestTour> list = requestTourRepository.findAll();
		return list.stream().map(RequestTourDTO::fromRequestTour).toList();
	}

	@Override
	public RequestTourDTO getRequestTour(Long id) throws Exception {
		RequestTour request = requestTourRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("RequestTour not found"));
		return RequestTourDTO.fromRequestTour(request);
	}

	@Override
	public void recover(Long id) throws Exception {
		RequestTour requestTour = requestTourRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("RequestTour not found"));
		requestTour.setDeleted(false);
		requestTourRepository.save(requestTour);
	}
}
