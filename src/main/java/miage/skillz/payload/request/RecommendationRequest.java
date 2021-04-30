package miage.skillz.payload.request;

import java.util.Date;

public class RecommendationRequest {
    private Long writerId;
    private Long receiverId;
    private String content;
    private String date;

    public RecommendationRequest(Long writerId, Long receiverId, String content, String date) {
        this.writerId = writerId;
        this.receiverId = receiverId;
        this.content = content;
        this.date = date;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
