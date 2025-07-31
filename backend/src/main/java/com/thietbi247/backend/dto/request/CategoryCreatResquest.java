package com.thietbi247.backend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CategoryCreatResquest {
    String category_name;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy")
    LocalDateTime updated_at;
    Boolean status;
}
