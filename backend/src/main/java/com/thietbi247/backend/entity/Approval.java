package com.thietbi247.backend.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    boolean isAccept;
    Date approveDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne
    @JoinColumn(name = "error_report_id") // FK ở bảng approval
    ErrorReport errorReport;

    @OneToOne
    @JoinColumn(name = "return_device_id") // FK ở bảng approval
    ReturnDevice returnDevice;

    @OneToOne
    @JoinColumn(name = "request_borrow_id") // FK ở bảng approval
    RequestBorrow requestBorrow;


}
