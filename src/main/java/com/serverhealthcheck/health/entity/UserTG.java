package com.serverhealthcheck.health.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "UsersTG")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTG extends BaseEntity {
    Long chatId;
    String name;
}
