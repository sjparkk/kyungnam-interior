package com.kninterior.repository;

import com.kninterior.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "select c from Contact c")
    Page<Object[]> getContact(Pageable pageable);

    @Query(value = "select c from Contact c where c.name =:name")
    Page<Contact> getContactByName(@Param("name") String name, Pageable pageable);

    @Query(value = "select c from Contact c where c.address like concat('%', :address, '%')")
    Page<Contact> getContactByAddress(@Param("address") String address, Pageable pageable);

    @Query(value = "select count(c.id), c from Contact c where c.regDate  =:date")
    List<Contact> getContactCountByDay(@Param("date") LocalDate date);




//    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
//    Object getBoardWithWriter(@Param("bno") Long bno);
//
//    @Query("select b, r from Board b left join Reply r on r.board = b where b.bno = :bno")
//    List<Object[]> getBoardWithReply(@Param("bno") Long bno);
//
//    @Query(value = "select b, w, count(r)" +
//            "from Board b " +
//            "left join b.writer w " +
//            "left join Reply r on r.board = b " +
//            "group by b",
//            countQuery = "select count(b) from Board b")
//    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
//
//    @Query("select b, w, count(r) " +
//            "from Board b left join b.writer w " +
//            "left outer join Reply r on r.board = b " +
//            "where b.bno = :bno")
//    Object getBoardByBno(@Param("bno") Long bno);
}
