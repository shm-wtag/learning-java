package com.learning.learning.service;

import com.learning.learning.entity.Room;
import com.learning.learning.entity.User;
import com.learning.learning.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
  final private UserService userService;
  final private RoomRepository roomRepo;

  public Room saveRoom(Room room) {
    Authentication authentication = SecurityContextHolder
      .getContext()
      .getAuthentication();

    UserDetails userDetails = (UserDetails) authentication
      .getPrincipal();

    User user = (User) this.userService
      .userDetailsService()
      .loadUserByUsername(userDetails.getUsername());

    Room newRoom = Room
      .builder()
      .name(room.getName())
      .address(room.getAddress())
      .price(room.getPrice())
      .owner(user)
      .build();

    return this.roomRepo.save(newRoom);
  }

  public Optional<Room> getRoomById(Long id) {
    return this.roomRepo.findById(id);
  }

  public List<Room> getAll() {
    String username = SecurityContextHolder
      .getContext()
      .getAuthentication()
      .getName();

    User user = (User) this.userService
      .userDetailsService()
      .loadUserByUsername(username);

    return this.roomRepo.findByOwner(user);
  }
}
