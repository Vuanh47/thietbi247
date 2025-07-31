package com.thietbi247.backend.dto.responsitory;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryReponse {
    String id;
    String category_name;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy")
    LocalDateTime updated_at;
    Boolean status;
}