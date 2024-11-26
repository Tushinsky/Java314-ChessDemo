//package com.example.chess_demo_spring_boot.repository;
//
//import com.example.chess_demo_spring_boot.domain.City;
//import com.example.chess_demo_spring_boot.domain.Country;
//import lombok.NoArgsConstructor;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.logging.Logger;
//@Repository
//@NoArgsConstructor
//public class CountryRepositoryImpl implements CountryRepository {
//    // класс для логирования действий
////    private static final Logger logger = (Logger) LoggerFactory.getLogger(CountryRepositoryImpl.class);
//    private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public Country getBy_Id(Long id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Country country = session.get(Country.class, id);
////        logger.info("Country successfully find. Country details: " + country);
//        return country;
//    }
//
//    @Override
//    public Country getByName(String name) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Country country = session.get(Country.class, name);
////        logger.info("Country successfully find. Country details: " + country);
//        return country;
//    }
//
//    @Override
//    public List<Country> getAll() {
//        Session session = this.sessionFactory.getCurrentSession();
//        return session.createSelectionQuery("FROM COUNTRY", Country.class).list();
//    }
//
//    @Override
//    public void save(Country country) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Transaction ta = session.beginTransaction();
//        session.persist(country);
//        ta.commit();
//        session.close();
//    }
//
//    @Override
//    public void updateCountry(Country country) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Transaction ta = session.beginTransaction();
//        session.persist(country);
//        ta.commit();
//        session.close();
//    }
//
//    @Override
//    public void removeCountry(Long id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Transaction ta = session.beginTransaction();
//        Country country = session.get(Country.class, id);
////        logger.info("Country successfully find. Country details: " + country);
//        session.remove(country);
//        ta.commit();
//        session.close();
//    }
//}
