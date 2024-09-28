package uz.muydinovs.appnewssite.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.muydinovs.appnewssite.entity.template.AbstractEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends AbstractEntity {

    @Column(nullable = false,columnDefinition = "text")
    private String title;

    @Column(nullable = false,columnDefinition = "text")
    private String text;

    @Column(nullable = false,columnDefinition = "text")
    private String url;
}
