package org.zeorck.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zeorck.board.dto.BoardDTO;
import org.zeorck.board.dto.PageRequestDTO;
import org.zeorck.board.dto.PageResultDTO;
import org.zeorck.board.entity.Board;
import org.zeorck.board.entity.Member;
import org.zeorck.board.repository.BoardRepository;
import org.zeorck.board.repository.ReplyRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;   // 게시글 repository
    private final ReplyRepository replyRepository;  // 댓글 repository

    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board = dtoTOEntity(dto);

        repository.save(board);

        repository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (Member) en[1], (Long) en[2]));

        /*age<Object[]> result = repository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("bno").descending()) );*/

        Page<Object[]> result = repository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {

        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {

        // 댓글 부터 삭제
        replyRepository.deleteByBno(bno);

        repository.deleteById(bno);

    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Optional<Board> result = repository.findById(boardDTO.getBno());

        if (result.isPresent()) {

            Board board = result.get();
            board.changeTitle(board.getTitle());
            board.changeContent(board.getContent());

            repository.save(board);
        }
    }


}
