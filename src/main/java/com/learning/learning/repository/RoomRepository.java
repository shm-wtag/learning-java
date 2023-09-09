package com.learning.learning.repository;

import com.learning.learning.entity.Room;
import com.learning.learning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
  List<Room> findByOwner(User user);
}
