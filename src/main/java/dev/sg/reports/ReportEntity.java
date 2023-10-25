package dev.sg.reports;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "reports")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {

    @Id
    @SequenceGenerator(
            name = "report_sequence",
            sequenceName = "report_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "report_sequence"
    )
    private Long id;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;*/

    @OneToMany(mappedBy = "report")
    List<LinkEntity> links;

    private String title;
    private String body;
    private String address;
    private String geotag;
    private Byte status;
    private Boolean isStatusChanged;
    private LocalDateTime createdAt;
    private LocalDateTime publishedAt;
}
