package com.learning.learning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "_room")
@NoArgsConstructor
@AllArgsConstructor
public class Room {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @Column
  private String address;

  @Column
  private Integer price;

  @ManyToOne(fetch = FetchType.LAZY)
  private User owner;
}
