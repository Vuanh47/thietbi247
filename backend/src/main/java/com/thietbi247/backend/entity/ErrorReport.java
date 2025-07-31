package com.thietbi247.backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorReport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String discribe;
    Date errorDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "errorReport", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Device> deviceList;

    @OneToOne(mappedBy = "errorReport", cascade = CascadeType.ALL)
     Approval approval;


}
