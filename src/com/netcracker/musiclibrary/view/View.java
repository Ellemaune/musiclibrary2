package com.netcracker.musiclibrary.view;

import com.netcracker.musiclibrary.controller.Controller;
import com.netcracker.musiclibrary.data.Genre;
import com.netcracker.musiclibrary.data.Track;
import com.netcracker.musiclibrary.model.Model;
import com.netcracker.musiclibrary.model.ModelChangeListener;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * View Model and Controller Operation
 */
public class View implements ModelChangeListener {

    private Model model;
    private Controller controller;
    private String searchLine;

    /**
     * Constructor - creating a view object
     * @see Model
     * @see Controller
     */
    public View(Model model, Controller controller){
        this.model = model;
        this.controller = controller;
        this.searchLine = "";
        model.addChangeListener(this);
        onModelChangeListener();
    }

    /**
     * Model control panel
     */
    public void onModelChangeListener() {
            System.out.println();
            System.out.printf("%-10s%-15s%n","Номер" ,"Жанр");
            final AtomicInteger num = new AtomicInteger(1);
            model.getGenresCollection().stream().filter(genre -> genre.name().toLowerCase(Locale.ROOT).contains(searchLine.toLowerCase(Locale.ROOT))).forEach(genre -> {
                System.out.printf("%-10s%-15s%n", num.getAndIncrement() , genre.name());
            });
            System.out.println();
            num.set(1);
            System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s%n","Номер", "Название трека", "Длительность", "Альбом", "Певец", "Жанр");
             model.getTracksCollection().stream().filter(track -> track.name().toLowerCase(Locale.ROOT).contains(searchLine.toLowerCase(Locale.ROOT))).forEach(track -> {
                 System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s%n", num.getAndIncrement(), track.name(), track.recordLength(), track.album(), track.singer(), track.genre());
             });
            Scanner in = new Scanner(System.in);
            System.out.println();
            System.out.println("МЕНЮ");
            System.out.println("1 - Добавить трек");
            System.out.println("2 - Добавить жанр");
            System.out.println("3 - Сохранить данные в файл");
            System.out.println("4 - Добавить данные из файла");
            System.out.println("5 - Поиск по названию трека");
            System.out.println("6 - Удаление трека");
            System.out.println("7 - Поменять трек");
            System.out.println("8 - Удаление жанра");
            System.out.println("9 - Поменять жанр");
            System.out.print("Введите действие: ");
            switch (in.nextLine()) {
                case ("1"):
                    System.out.print("Введите название песни: ");
                    String nameTrack = in.nextLine();
                    System.out.print("Введите время записи (в секундах): ");
                    Duration time = Duration.ofSeconds(Integer.parseInt(in.nextLine()));
                    System.out.print("Введите альбом: ");
                    String album = in.nextLine();
                    System.out.print("Введите певца: ");
                    String singer = in.nextLine();
                    System.out.print("Введите жанр: ");
                    String genre = in.nextLine();
                    controller.createTrack(nameTrack, singer, album, time, genre);
                    break;
                case ("2"):
                    System.out.print("Введите название жанра: ");
                    String nameGenre = in.nextLine();
                    try{
                        controller.createGenre(nameGenre, true);
                    }
                    catch(RuntimeException runtimeException){
                        System.out.print(runtimeException.getMessage());
                    }
                    break;
                case ("3"):
                    System.out.print("Введите имя файла: ");
                    String saveFileName = in.nextLine();
                    try {
                        controller.saveDataToFile(saveFileName);
                    }
                    catch(IOException ioException){
                        System.out.print("Ошибка доступа к файлу.");
                    }
                    onModelChangeListener();
                case ("4"):
                    System.out.print("Введите имя файла: ");
                    String updateFileName = in.nextLine();
                    try {
                        controller.updateDataFromFile(updateFileName);
                    }
                    catch(IOException ioException){
                        System.out.print("Ошибка доступа к файлу.");
                    }
                    catch(ClassNotFoundException classNotFoundException){
                        System.out.print("Класс не найден.");
                    }
                    break;
                case ("5"):
                    System.out.print("Введите название песни: ");
                    searchLine = in.nextLine();
                    onModelChangeListener();
                    break;
                case ("6"):
                    System.out.print("Введите индекс удаляемого трека: ");
                    int indexTrack = Integer.parseInt(in.nextLine());
                    controller.removeTrack(indexTrack);
                    break;
                case ("7"):
                    System.out.print("Введите индекс изменяемого трека: ");

                        int indexEdit = Integer.parseInt(in.nextLine());
                        System.out.print("Введите название песни: ");
                        String nameTrackToEdit = in.nextLine();
                        System.out.print("Введите время записи (в секундах): ");
                        Duration timeToEdit = Duration.ofSeconds(Integer.parseInt(in.nextLine()));
                        System.out.print("Введите альбом: ");
                        String albumToEdit = in.nextLine();
                        System.out.print("Введите певца: ");
                        String singerToEdit = in.nextLine();
                        System.out.print("Введите жанр: ");
                        String genreToEdit = in.nextLine();
                        controller.editTrack(indexEdit,nameTrackToEdit,singerToEdit,albumToEdit,timeToEdit,genreToEdit);
                    break;
                case ("8"):
                    System.out.println("Введите индекс удаляемого жанра: ");
                    int indexForRemoveGenre = Integer.parseInt(in.nextLine());
                    controller.removeGenre(indexForRemoveGenre);
                    break;
                case ("9"):
                    System.out.print("Введите индекс изменяемого жанра: ");
                    int indexEditGenre = Integer.parseInt(in.nextLine());
                    System.out.print("Введите жанр: ");
                    String genreToEditOnlyGenre = in.nextLine();
                    controller.editGerge(indexEditGenre,genreToEditOnlyGenre);

                default:
                    System.out.println("Вы ввели некорректные данные. Повторите попытку.");
                    onModelChangeListener();
            }
    }
}
