package fudan.se.lab2.service;

import fudan.se.lab2.Assist.ComRepRocord;
import fudan.se.lab2.Assist.CommentRecord;
import fudan.se.lab2.controller.request.CommentRequest;
import fudan.se.lab2.controller.request.DeleteRequest;
import fudan.se.lab2.controller.request.ReplyRequest;
import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.Comment;
import fudan.se.lab2.domain.Reply;
import fudan.se.lab2.domain.Reversion;
import fudan.se.lab2.exception.BookNotFoundException;
import fudan.se.lab2.exception.CommentNotFoundException;
import fudan.se.lab2.repository.BookRepository;
import fudan.se.lab2.repository.CommentRepository;
import fudan.se.lab2.repository.ReplyRepository;
import fudan.se.lab2.repository.ReversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReversionRepository reversionRepository;
    @Autowired
    private ReplyRepository replyRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

    @Transactional
    public String comment(CommentRequest request){
        String isbn = request.getIsbn();
        Book book = bookRepository.findByIsbn(isbn);
        if(book == null){
            throw new BookNotFoundException("Book "+isbn+" not found!");
        }
        else{
            String isbnBook = isbn.substring(0, 10);
            Book b = bookRepository.findBookByIsbn(isbnBook);
            int num = b.getCommentNumber();
            if(num == -1) num = 0;
            double rate = request.getRate();
            double score = (b.getRate() * num + rate) / (num + 1);
            num++;


            b.setRate(score);
            b.setCommentNumber(num);
            bookRepository.save(b);


            Date nowTime = new Date();
            String time = sdf.format(nowTime);
//String username, String content, String time, int valid, double score, Book book
            Comment comment = new Comment(request.getUserName(), request.getEvaluation(), time, 1, rate, isbnBook);
            commentRepository.save(comment);

            return "comment successfully!";

        }


    }
//String isbn, String title, String cover, String comment, String author, String reverseTime, boolean isComment, boolean isDelete
    public List<CommentRecord> getRecord(String username){//用户处读记录
        List<Reversion> reversionList = reversionRepository.findAllByBorrowUsernameAndOvertime(username, 0);
        List<CommentRecord> recordList = new ArrayList<>();
        int len = reversionList.size();
        for(int i = 0; i < len; i++){
            Reversion r = reversionList.get(i);

            Book book = r.getBook();
            String isbn = book.getIsbn().substring(0, 10);
            Book b = bookRepository.findBookByIsbn(isbn);
//int bookId, String isbn, String title, String cover, String author, String reverseTime

            Comment comment = commentRepository.findByUsernameAndIsbn(username, isbn);
            CommentRecord commentRecord = new CommentRecord(b.getId(), isbn, b.getTitle(), b.getCover(), b.getAuthor(), r.getReturnTime());
            if(comment == null){
                commentRecord.setCommentId(-1);
                //commentRecord.setComment(false);
                commentRecord.setRate(false);
                commentRecord.setDelete(false);
                commentRecord.setComment(null);
                //CommentRecord commentRecord = new CommentRecord(isbn, b.getTitle(), b.getCover(), null, b.getAuthor(), r.getReturnTime(), false, false);
                //recordList.add(commentRecord);
            }else{
                //commentRecord.setComment(true);
                commentRecord.setRate(true);
                commentRecord.setCommentId(comment.getId());
                commentRecord.setComment(comment.getContent());
                if(comment.getValid() == 0){
                    commentRecord.setDelete(true);
                   // commentRecord.setComment("This comment is deleted!");
                }else {
                    commentRecord.setDelete(false);
                    //commentRecord.setComment(comment.getContent());
                }

            }

            recordList.add(commentRecord);

        }

        return recordList;


    }
    @Transactional
    public String delete(DeleteRequest request){
        Comment comment = commentRepository.findById(request.getCommentId());
        if(comment == null){
            throw new CommentNotFoundException("comment is not found");
        }else{
            comment.setValid(0);

            Date nowTime = new Date();
            String time = sdf.format(nowTime);
            comment.setDeleteTime(time);
            comment.setDeleteUsername(request.getUserName());//删评论的人



            Book book = bookRepository.findByIsbn(comment.getIsbn());
            int num = book.getCommentNumber();
            double rate = (book.getRate() * num - comment.getScore()) / (num - 1);
            num--;

            book.setRate(rate);
            book.setCommentNumber(num);

            bookRepository.save(book);
            commentRepository.save(comment);

            return "delete successfully!";
        }
    }

    public String reply(ReplyRequest request){
        //String username, //String replyUsername, String content, Comment comment, //rid, time

        Comment comment = commentRepository.findById(request.getParentId());
        if(comment == null) System.out.println("nulllllllll");
        Date nowTime = new Date();
        String time = sdf.format(nowTime);
        System.out.println(comment.getUsername());
        Reply r = new Reply(request.getUserName(), request.getReply(), comment.getId(), time);
        System.out.println(r.getUsername());
        Reply reply = replyRepository.findById(request.getReplyId());
        if(reply == null){
            r.setReplyUsername(comment.getUsername());
            r.setRid(0);

        }
        else{
            r.setReplyUsername(reply.getUsername());
            r.setRid(reply.getId());
        }

        replyRepository.save(r);

        return "ok";


    }

    public List<ComRepRocord> getList(String isbn){
        List<ComRepRocord> list = new ArrayList<>();


        List<Comment> commentList = commentRepository.findAllByIsbn(isbn);

        int len = commentList.size();
        System.out.println("hello");
        //if(len == 0) return null;
        /*
        for(int i = 0; i < len; i++){
            Comment comment = commentList.get(i);
            List<Reply> replyList = replyRepository.findAllByCid(comment.getId());
            System.out.println("hi");


         */
        //if(len == 1) return null;
        for(int i = 0; i < len; i++){
            Comment comment = commentList.get(i);
            List<Reply> replyList = replyRepository.findAllByCid(comment.getId());
            //if(replyList == null) return  null;

            //(int commentId, String userName, double rate, String time, //String content, List<Reply> replyList){
            ComRepRocord comRepRocord = new ComRepRocord(comment.getId(), comment.getUsername(), comment.getScore(),comment.getTime(),replyList);
            if(comment.getValid() == 0){
                comRepRocord.setContent("This comment has been deleted.");
            }
            else {
                comRepRocord.setContent(comment.getContent());
            }

            list.add(comRepRocord);

        }
        return list;
    }

}
