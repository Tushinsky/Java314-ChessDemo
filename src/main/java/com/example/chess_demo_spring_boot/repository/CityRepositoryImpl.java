//package com.example.chess_demo_spring_boot.repository;
//
//import com.example.chess_demo_spring_boot.domain.City;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.logging.Logger;
//@Repository
//@RequiredArgsConstructor
//public class CityRepositoryImpl implements CityRepository {
//    // класс для логирования действий
////    private static final Logger logger = (Logger) LoggerFactory.getLogger(CityRepositoryImpl.class);
//    private final SessionFactory sessionFactory;
//
////    public void setSessionFactory(SessionFactory sessionFactory) {
////        this.sessionFactory = sessionFactory;
////    }
//
//    @Override
//    public City getBy_Id(Long id) {
//        Session session = this.sessionFactory.openSession();
//        City city = session.get(City.class, id);
////        logger.info("City successfully find. City details: " + city);
//        return city;
//    }
//
//    @Override
//    public City getByName(String name) {
//        Session session = this.sessionFactory.openSession();
//        City city = session.get(City.class, name);
////        logger.info("Cities successfully find. City details: " + city);
//        return null;
//    }
//
//    @Override
//    public List<City> getAll() {
//        Session session = this.sessionFactory.openSession();
//        if (session.isOpen()) {
//            return session.createSelectionQuery("FROM CITY", City.class).list();
//        } else {
//            System.out.println("Error. Hibernate session is not open!");
//            return null;
//        }
//
//    }
//
//    @Override
//    public void save(City city) {
//        Session session = this.sessionFactory.openSession();
//        Transaction ta = session.beginTransaction();
//        session.persist(city);
//        ta.commit();
//        session.close();
//    }
//
//    @Override
//    public void updateCity(City city) {
//        Session session = this.sessionFactory.openSession();
//        Transaction ta = session.beginTransaction();
//        session.persist(city);
//        ta.commit();
//        session.close();
//    }
//
//    @Override
//    public void removeCity(Long id) {
//        Session session = this.sessionFactory.openSession();
//        Transaction ta = session.beginTransaction();
//        City city = session.get(City.class, id);
////        logger.info("City successfully find. City details: " + city);
//        session.remove(city);
//        ta.commit();
//        session.close();
//    }
//}
