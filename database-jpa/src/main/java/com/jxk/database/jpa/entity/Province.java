package com.jxk.database.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "province")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Integer type;

}
