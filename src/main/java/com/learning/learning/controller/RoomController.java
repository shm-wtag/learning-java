package com.learning.learning.controller;

import com.learning.learning.entity.Room;
import com.learning.learning.entity.User;
import com.learning.learning.service.RoomService;
import com.learning.learning.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
  final private RoomService roomService;
  final private UserService userService;

  @GetMapping
  public List<Room> getAllRoom() {
    return this.roomService.getAll();
  }

  @GetMapping("/{id}")
  public Optional<Room> findRoomById(@PathVariable Long id) {
    return this.roomService.getRoomById(id);
  }

  @PostMapping
  public Room saveRoom(@RequestBody Room room) {
    return this.roomService.saveRoom(room);
  }
}
