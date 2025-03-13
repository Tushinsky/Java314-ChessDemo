package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Opponent;
import com.example.chess_demo_spring_boot.dto.HistoryDto;

import java.util.List;
import java.util.Optional;

public interface ChessManService {
    /**
     * Возвращает пользователя по имени
     * @param name имя пользователя для поиска
     * @return пользователь с заданным именем, если найден
     */
    ChessMan getByName(String name);

    /**
     * Возвращает пользователя по адресу электронной почты
     * @param email адрес электронной почты для поиска
     * @return пользователь с заданным адресом, если найден
     */
    ChessMan getByEmail(String email);

    /**
     * Возвращает список пользователей по образцу адреса эл. почты
     * @param email образец для поиска
     * @return список пользователей с заданным адресом, если найден
     */
    List<ChessMan> getByEmailLike(String email);

    /**
     * Возвращает пользователя по его нику
     * @param nic образец для поиска
     * @return пользователь с заданным ником, если найден
     */
    ChessMan getByNic(String nic);

    /**
     * Возвращает список пользователей по образцу ника
     * @param nicName образец для поиска
     * @return список пользователей с заданным ником, если найден
     */
    List<ChessMan> getAllByNicLike(String nicName);

    /**
     * Возвращает пользователя по идентификатору
     * @param id идентификатор пользователя
     * @return пользователь, если найден
     */
    Optional<ChessMan> getBy_Id(Long id);

    /**
     * Возвращает список пользователей по заданному образцу имени
     * @param name образец для поиска
     * @return список пользователей, если найден
     */
    List<ChessMan> getAllByName(String name);

    /**
     * Возвращает список пользователей из заданного города
     * @param city название города для поиска
     * @return список пользователей, если найден
     */
    List<ChessMan> getAllByCity(String city);

    /**
     * Возвращает список пользователей из заданной страны
     * @param country название страны для поиска
     * @return список пользователей, если найден
     */
    List<ChessMan> getAllByCountry(String country);

    /**
     * Возвращает список пользователей с заданным состоянием учётной записи
     * @param state состояние учётной записи
     * @return список пользователей, если найден
     */
    List<ChessMan> getAllByState(String state);

    /**
     * Сохраняет пользователя в базе данных
     * @param chessMan объект пользователя для сохранения
     */
    void save(ChessMan chessMan);

    /**
     * Обновляет пользователя в базе данных
     * @param chessMan объект пользователя для сохранения
     */
    void updateChessMan(ChessMan chessMan);

    /**
     * Удаляет пользователя с заданным идентификатором из базы данных
     * @param id идентификатор пользователя для удаления
     */
    void removeChessMan(Long id);

    /**
     * Возвращает список всех зарегистрированных пользователей
     * @return список всех пользователей
     */
    List<ChessMan> getAll();

    /**
     * Возвращает список шахматных партий, в которых участвует заданный пользователь
     * @param chessMan пользователь, чьи шахматные партии возвращаются
     * @param isFinished флаг завершения шахматной партии
     * @return список шахматных партий, если найдены
     */
    List<HistoryDto> getAllHistoryByChessMan(ChessMan chessMan, boolean isFinished);

}
