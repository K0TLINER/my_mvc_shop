package kr.co.devcs.shop.entity;

import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "products")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;
    @Column(name = "product_description", nullable = false, length = 500)
    private String productDescription;
    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;
    @Column(name = "price", nullable = false)
    @ColumnDefault("0")
    private Long price;
    @Column(name = "stock", nullable = false)
    @ColumnDefault("0")
    private Long stock;
    @Column(name = "view_count", nullable = false)
    @ColumnDefault("0")
    private Long viewCount;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
