package kr.co.devcs.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId = null;
    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;
    @Column(name = "message", nullable = false)
    private String message;

    @Builder
    public ChatMessage(ChatRoom chatRoom, Member member, String message) {
        this.chatRoom = chatRoom;
        this.member = member;
        this.message = message;
    }
}
