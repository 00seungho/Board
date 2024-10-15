package com.example.board.repository.search;

import com.example.board.entity.Board;
import com.example.board.entity.QBoard;
import com.example.board.entity.QMember;
import com.example.board.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl(){
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search1() 메소드 호출됨");
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);

        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

//        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count()).groupBy(board, member, reply);
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());
        tuple.groupBy(board, member, reply);



        log.info("=========================================================================");
        log.info(jpqlQuery);
        log.info("=========================================================================");
//      JPQL 실행 방법
//        jpqlQuery.fetch();
        List<Tuple> result = tuple.fetch();
        for (Tuple tuple1 : result){
            log.info(tuple1);
        }
        return null;
    }
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable){
        log.info("searPage() 메서드 호출됨");
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);

        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());
        tuple.groupBy(board, member, reply);


        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression booleanexpression = board.bno.gt(0L);

        if(type != null){
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for(String t: typeArr){
                switch (t){
                case "t":
                    conditionBuilder.or(board.title.contains(keyword));
                    break;

                case "w":
                    conditionBuilder.or(member.email.contains(keyword));
                    break;
                case "c":
                    conditionBuilder.or(board.content.contains(keyword));
                    break;
                }//end switch
            }//end for
            booleanBuilder.and(conditionBuilder);
        }//end if

        tuple.where(booleanBuilder);
        tuple.groupBy(board, member);
        List <Tuple> result = tuple.fetch();
        log.info(result);


        return null;
    };


}
