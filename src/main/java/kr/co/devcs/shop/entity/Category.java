package kr.co.devcs.shop.entity;

import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;
}