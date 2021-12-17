package org.zeorck.board.service;

import org.zeorck.board.dto.BoardDTO;
import org.zeorck.board.dto.PageRequestDTO;
import org.zeorck.board.dto.PageResultDTO;
import org.zeorck.board.entity.Board;
import org.zeorck.board.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);
    
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);   // 목록 처리

    BoardDTO get(Long bno);

    void removeWithReplies(Long bno);   // 삭제 기능

    void modify(BoardDTO boardDTO);

    default Board dtoTOEntity(BoardDTO dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();

        return boardDTO;
    }

}
