package com.thietbi247.backend.dto.responsitory;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatAIReponse {
    String message;    // câu trả lời

}
