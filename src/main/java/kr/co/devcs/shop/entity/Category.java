package kr.co.devcs.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Entity(name = "categories")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"category_name", "parent_category_id"}))
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_name", nullable = false, length = 300)
    private String categoryName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category parentCategory;
}