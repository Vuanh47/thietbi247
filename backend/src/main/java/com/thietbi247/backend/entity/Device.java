package com.thietbi247.backend.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String productName;
    int quantity;
    String status;
    String image;
    String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy")
    LocalDateTime datePurchase;

    @ManyToOne
    @JoinColumn(name = "history_id")
    History history;

    @ManyToOne
    @JoinColumn(name = "return_device_id")
    ReturnDevice returnDevice;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL)
    @JsonIgnore
    private RequestBorrow requestBorrow;

    @ManyToOne
    @JoinColumn(name = "error_report_id")
    ErrorReport errorReport;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
