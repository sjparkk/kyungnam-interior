package com.kninterior.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"work"})
@Table(name = "work_image")
public class WorkImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`image_order`")
    private int imageOrder;
    @Column(name = "`image_path`")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;

    public WorkImage(int imageOrder) {
        this.imageOrder = imageOrder;
    }

    public WorkImage(int imageOrder, Work work) {
        this.imageOrder = imageOrder;
        if (work != null) {
            changeWork(work);
        }
    }

    public void changeWork(Work work) {
        this.work = work;
        work.getWorkImages().add(this);
    }
}
