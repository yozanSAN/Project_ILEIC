package com.ProjetILEIC.ILIEC.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;

@Entity
@Table(name = "documents")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Stagiaire stagiaire;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", nullable = false)
    private User uploadedBy;

    @Column(name = "document_type", length = 100)
    private String documentType;

    @Column(name = "file_url", length = 255)
    private String fileUrl;

    @Column(length = 50)
    private String status;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
}
