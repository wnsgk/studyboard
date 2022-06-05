package jboard.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String username;
    private String password;

//    @CreationTimestamp
//    private Timestamp createDate; // 회원가입한 날짜

//    @OneToMany(mappedBy = "member")
//    private List<Board> boards = new ArrayList<>();
}
