package com.thietbi247.backend.service;

import com.thietbi247.backend.dto.request.ChatAIRequest;
import com.thietbi247.backend.dto.responsitory.ChatAIReponse;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ChatAIService {
    private final ChatClient chatClient;
    private final Parser markdownParser = Parser.builder().build();
    private final HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();

    public ChatAIService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    public ChatAIReponse chatImageOptional(MultipartFile file, String message) {
        // Tin nhắn hệ thống
        String systemPrompt = """
        Mày đang nói chuyện với tao – AI của thietbi247 😎.
        Tao trả lời kiểu mày tao cho gần gũi, thỉnh thoảng chọc ghẹo cho vui.
        Trả lời chính xác nhưng thêm chút lầy lội để bớt nhàm chán.
        Không bịa thông tin kỹ thuật và trả lời ngắn gọn.
    """;

        var promptBuilder = chatClient.prompt()
                .system(systemPrompt);

        // Nếu có ảnh thì thêm media
        if (file != null && !file.isEmpty()) {
            Media media = Media.builder()
                    .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                    .data(file.getResource())
                    .build();

            promptBuilder.user(userSpec ->
                    userSpec.media(media).text(message));
        } else {
            promptBuilder.user(message);
        }

        String data = promptBuilder.call().content();

        // Convert Markdown → HTML
        String html = htmlRenderer.render(markdownParser.parse(data));

        return ChatAIReponse.builder()
                .message(html)
                .build();
    }

}
