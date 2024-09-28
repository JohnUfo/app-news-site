package uz.muydinovs.appnewssite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.muydinovs.appnewssite.entity.enums.Permission;
import uz.muydinovs.appnewssite.entity.template.AbstractEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissions;

    @Column(length = 600)
    private String description;
}
