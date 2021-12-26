package com.mock.docker.app.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@Setter
@Getter
@NoArgsConstructor
public class PermissionTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "type")
    private String type;

    @Column(name = "resource_type")
    private String resourceType;
}
