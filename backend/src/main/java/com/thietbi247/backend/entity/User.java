package com.thietbi247.backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String userName;
    String email;
    String password;
    String phone;
    String address;
    Integer room;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Notification> notificationList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<History>  historyList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ReturnDevice>  returnDeviceList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<RequestBorrow>  requestBorrowList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ErrorReport>  errorReportList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Approval>  approvalList;

    @ManyToMany
    @JoinTable(
            name = "user_roles", // tên bảng trung gian
            joinColumns = @JoinColumn(name = "user_id"), // FK tới User
            inverseJoinColumns = @JoinColumn(name = "roles_name", referencedColumnName = "name") // FK tới Role.name
    )
    Set<Role> roles;
}
