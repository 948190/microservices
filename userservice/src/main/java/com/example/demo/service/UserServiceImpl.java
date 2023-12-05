package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.model.Rating;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate resttemplate;
	
	 private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Override
	public User saveUser(User user) {

		//String randomUserId = UUID.randomUUID().toString();
		//user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		//get user from userid
		User user= userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
		//fetch the rating from the rating service
		//http://localhost:8083/ratings/users/121
		Rating[] ratingUser=resttemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(),Rating[].class);
		logger.info("{} ",ratingUser);
		List<Rating> ratings=Arrays.stream(ratingUser).toList();
		
		
		List<Rating> ratinglist=ratings.stream().map(rating->{
			
			ResponseEntity<Hotel> forEntity=resttemplate.getForEntity("http://localhost:8081/hotels/"+rating.getHotelId(),Hotel.class);
			Hotel hotel=forEntity.getBody();
			logger.info("response status code: {}",forEntity.getStatusCode());
				
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		
		user.setRatings(ratinglist);
				
		return user;
	}

}
