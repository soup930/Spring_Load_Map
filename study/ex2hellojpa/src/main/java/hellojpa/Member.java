package hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;  // 팀의 참조를 보관

    public Member() {
    }

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {

        // 기존 팀과 관계를 제거
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        team.getMembers().add(this);
    }
}
