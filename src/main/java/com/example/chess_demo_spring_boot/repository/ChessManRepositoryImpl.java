//package com.example.chess_demo_spring_boot.repository;
//
//import com.example.chess_demo_spring_boot.domain.ChessMan;
//import com.example.chess_demo_spring_boot.domain.State;
//import lombok.NoArgsConstructor;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.logging.Logger;
//@NoArgsConstructor
//public class ChessManRepositoryImpl implements ChessManRepository{
//    // класс для логирования действий
//    private static final Logger logger = Logger.getLogger("ChessManRepositoryImpl");
//    private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public ChessMan findByEmail(String email) {
//        Session session = this.sessionFactory.getCurrentSession();
//        ChessMan chessMan = session.get(ChessMan.class, email);
//        logger.info("Chessman successfully find. Chessman details: " + chessMan);
//        return chessMan;
//    }
//
//    @Override
//    public List<ChessMan> findByEmailLike(String email) {
//        Session session = this.sessionFactory.getCurrentSession();
//        return session.createSelectionQuery("FROM CHESSMAN C WHERE C.EMAIL LIKE '*" + email + "*'", ChessMan.class).list();
//    }
//
//    @Override
//    public ChessMan getByNicName(String nicName) {
//        Session session = this.sessionFactory.getCurrentSession();
//        ChessMan chessMan = session.get(ChessMan.class, nicName);
//        logger.info("Chessman successfully find. Chessman details: " + chessMan);
//        return chessMan;
//    }
//
//    @Override
//    public List<ChessMan> getAllByNicNameLike(String nicName) {
//        return null;
//    }
//
//    @Override
//    public ChessMan getBy_Id(Long id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        ChessMan chessMan = session.get(ChessMan.class, id);
//        logger.info("Chessman successfully find. Chessman details: " + chessMan);
//        return chessMan;
//    }
//
//    @Override
//    public List<ChessMan> getAll() {
//        Session session = this.sessionFactory.getCurrentSession();
//        return (List<ChessMan>) session.createSelectionQuery("FROM Chessman", ChessMan.class).list();
//    }
//
//    @Override
//    public void save(ChessMan chessMan) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Transaction ta = session.beginTransaction();
//        session.persist(chessMan);
//        logger.info("Chessman successfully saved. Chessman details: " + chessMan);
//        ta.commit();
//        session.close();
//    }
//
//    @Override
//    public void updateChessMan(ChessMan chessMan) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Transaction ta = session.beginTransaction();
//        session.persist(chessMan);
//        logger.info("Chessman successfully updated. Chessman details: " + chessMan);
//        ta.commit();
//        session.close();
//    }
//
//    @Override
//    public void removeChessMan(Long id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Transaction ta = session.beginTransaction();
//        ChessMan chessMan = session.get(ChessMan.class, id);
//        if(chessMan != null) {
//            chessMan.setState(String.valueOf(State.DELETED));
//        }
//        session.persist(chessMan);
//        logger.info("Chessman successfully deleted. Chessman details: " + chessMan);
//        ta.commit();
//        session.close();
//    }
//}
