package miage.skillz.payload.reponse;

public class RecommendationResponse {
    private Long id;
    private Long writerId;
    private String writerName;
    private Long receiverId;
    private String receiverName;
    private String content;
    private String date;

    public RecommendationResponse(Long id, Long writerId, String writerName, Long receiverId, String receiverName, String content, String date) {
        this.id = id;
        this.writerId = writerId;
        this.writerName = writerName;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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
