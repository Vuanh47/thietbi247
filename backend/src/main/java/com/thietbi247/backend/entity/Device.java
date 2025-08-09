package com.thietbi247.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String productName;
    int quantity;
    String status;
    String image;
    String description;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    @JsonIgnore
    List<History>  historyList;

    @ManyToOne
    @JoinColumn(name = "return_device_id")
    ReturnDevice returnDevice;

    @ManyToMany(mappedBy = "devices")
    @JsonIgnore
    Set<RequestBorrow> requestBorrows;


    @ManyToMany(mappedBy = "devices")
    @JsonIgnore
    Set<ErrorReport> errorReports;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Approval approval;
}
