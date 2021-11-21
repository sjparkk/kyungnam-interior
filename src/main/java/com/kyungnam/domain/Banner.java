package com.kyungnam.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "banner")
@Builder
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePath;
    private int viewOrder;

}
