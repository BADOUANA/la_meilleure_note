package com.epf.back_end.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_data")
    @Lob
    private byte[] image_data;
    @Column(name = "title")
    private String title;

}
